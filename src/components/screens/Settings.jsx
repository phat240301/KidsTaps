// src/components/screens/Settings.jsx
import { useState, useRef } from 'react'
import { Sidebar } from '../Sidebar'
import { Switch } from '../shared/Switch'
import { useGame } from '../../context/GameContext'

export function Settings({ navigateTo }) {
  const { lang, setLang } = useGame()
  const [calmMode, setCalmMode] = useState(true)
  const [bgMusic, setBgMusic] = useState(true)
  const [voicePrompts, setVoicePrompts] = useState(true)
  const [haptics, setHaptics] = useState(false)
  const [locked, setLocked] = useState(true)
  const [holdProgress, setHoldProgress] = useState(0)
  const holdInterval = useRef(null)

  const startHold = () => {
    if (!locked) return
    let progress = 0
    holdInterval.current = setInterval(() => {
      progress += 100 / 30
      setHoldProgress(progress)
      if (progress >= 100) {
        clearInterval(holdInterval.current)
        setLocked(false)
        setHoldProgress(0)
      }
    }, 100)
  }

  const endHold = () => {
    clearInterval(holdInterval.current)
    setHoldProgress(0)
  }

  const handleLangChange = (newLang) => {
    if (newLang !== lang) {
      setLang(newLang)
      navigateTo('language_loading')
    }
  }

  return (
    <div className="tab-screen">
      <Sidebar active="settings" navigateTo={navigateTo} />
      <div className="tab-content">
        <div className="top">
          <div>
            <div className="crumb">Parent · {locked ? 'Locked' : 'Unlocked'}</div>
            <h1>Settings</h1>
          </div>
          <div className="actions">
            <div
              className="tab-pill"
              onClick={() => navigateTo('home')}
              style={{ cursor: 'pointer' }}
            >← Back</div>
          </div>
        </div>
        {locked && (
          <div
            className="gate-card"
            onMouseDown={startHold}
            onMouseUp={endHold}
            onMouseLeave={endHold}
            onTouchStart={startHold}
            onTouchEnd={endHold}
          >
            <div className="lock">🔒</div>
            <div className="t">
              <div className="h">Hold to unlock parent settings</div>
              <div className="d">Press & hold for 3 seconds — keeps little hands away</div>
            </div>
            <div className="ring" style={{ '--hold-progress': `${holdProgress}%` }}></div>
          </div>
        )}
        {!locked && (
          <div className="s-grid">
            <div className="s-card">
              <div className="h"><h3>Audio</h3></div>
              <div className="set-list">
                <div className="row">
                  <div>
                    <div className="n">Background music</div>
                    <div className="d">Play during games</div>
                  </div>
                  <Switch checked={bgMusic} onChange={setBgMusic} />
                </div>
                <div className="row">
                  <div>
                    <div className="n">Voice prompts</div>
                    <div className="d">Name each item aloud</div>
                  </div>
                  <Switch checked={voicePrompts} onChange={setVoicePrompts} />
                </div>
              </div>
            </div>
            <div className="s-card">
              <div className="h"><h3>Comfort</h3></div>
              <div className="set-list">
                <div className="row">
                  <div>
                    <div className="n">Calm Mode</div>
                    <div className="d">Reduces animations and visual effects</div>
                  </div>
                  <Switch checked={calmMode} onChange={setCalmMode} />
                </div>
                <div className="row">
                  <div>
                    <div className="n">Haptics</div>
                    <div className="d">Vibrate on tap</div>
                  </div>
                  <Switch checked={haptics} onChange={setHaptics} />
                </div>
              </div>
            </div>
            <div className="s-card">
              <div className="h"><h3>Language</h3></div>
              <div className="set-list">
                <div className="row">
                  <div>
                    <div className="n">App language</div>
                    <div className="d">Switch between English & Vietnamese</div>
                  </div>
                  <div style={{ display: 'flex', gap: 4, padding: 4, background: 'var(--soft)', borderRadius: 999, border: '2px solid var(--ink)', fontFamily: 'Space Grotesk', fontWeight: 700, fontSize: 12 }}>
                    <div
                      style={{ padding: '6px 14px', borderRadius: 999, background: lang === 'EN' ? 'var(--ink)' : 'transparent', color: lang === 'EN' ? 'var(--cream)' : 'var(--ink)', cursor: 'pointer' }}
                      onClick={() => handleLangChange('EN')}
                    >EN</div>
                    <div
                      style={{ padding: '6px 14px', borderRadius: 999, background: lang === 'VI' ? 'var(--ink)' : 'transparent', color: lang === 'VI' ? 'var(--cream)' : 'var(--ink)', cursor: 'pointer' }}
                      onClick={() => handleLangChange('VI')}
                    >VI</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
