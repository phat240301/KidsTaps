// src/components/screens/Settings.jsx
import { useState } from 'react'
import { Sidebar } from '../Sidebar'
import { Slider } from '../shared/Slider'
import { Switch } from '../shared/Switch'

export function Settings() {
  const [musicVol, setMusicVol] = useState(60)
  const [sfxVol, setSfxVol] = useState(80)
  const [calmMode, setCalmMode] = useState(true)
  const [bgMusic, setBgMusic] = useState(true)
  const [voicePrompts, setVoicePrompts] = useState(true)
  const [haptics, setHaptics] = useState(false)
  const [locked, setLocked] = useState(true)
  const [lang, setLang] = useState('EN')

  return (
    <div className="tab-screen">
      <Sidebar active="settings" />
      <div className="tab-content">
        <div className="top">
          <div>
            <div className="crumb">Parent · Locked</div>
            <h1>Settings</h1>
          </div>
          <div className="actions">
            <div className="tab-pill">← Back</div>
          </div>
        </div>
        <div className="gate-card">
          <div className="lock">🔒</div>
          <div className="t">
            <div className="h">Hold to unlock parent settings</div>
            <div className="d">Press & hold for 3 seconds — keeps little hands away</div>
          </div>
          <div className="ring"></div>
        </div>
        {!locked && (
          <div className="s-grid">
            <div className="s-card">
              <div className="h"><h3>Audio</h3><span style={{ fontFamily: 'Space Grotesk', fontSize: 12, opacity: 0.5 }}>2 controls</span></div>
              <div className="set-list">
                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'stretch', gap: 8 }}>
                  <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'baseline' }}>
                    <div>
                      <div className="n">Music volume</div>
                      <div className="d">Calm background loop during play</div>
                    </div>
                    <div style={{ fontFamily: 'Space Grotesk', fontWeight: 700, fontSize: 16, color: 'var(--blue)' }}>{musicVol}%</div>
                  </div>
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
                    <div className="n">Background music</div>
                    <div className="d">Play during games</div>
                  </div>
                  <Switch checked={bgMusic} onChange={setBgMusic} />
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
                    <div style={{ padding: '6px 14px', borderRadius: 999, background: lang === 'EN' ? 'var(--ink)' : 'transparent', color: lang === 'EN' ? 'var(--cream)' : 'var(--ink)' }}>EN</div>
                    <div style={{ padding: '6px 14px', borderRadius: 999, background: lang === 'VI' ? 'var(--ink)' : 'transparent', color: lang === 'VI' ? 'var(--cream)' : 'var(--ink)' }}>VI</div>
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
