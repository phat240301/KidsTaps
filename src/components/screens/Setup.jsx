// src/components/screens/Setup.jsx
import { useState } from 'react'
import { Sidebar } from '../Sidebar'
import { Slider } from '../shared/Slider'
import { Switch } from '../shared/Switch'
import { Button } from '../shared/Button'
import { DIFFICULTY_PRESETS } from '../../utils/constants'
import { useGame } from '../../context/GameContext'

export function Setup({ navigateTo }) {
  const { config, setConfig } = useGame()
  const [age, setAge] = useState(7)
  const [spawnSpeed, setSpawnSpeed] = useState(3.4)
  const [holdTime, setHoldTime] = useState(3.9)
  const [targetScore, setTargetScore] = useState(17)
  const [music, setMusic] = useState(true)
  const [calmMode, setCalmMode] = useState(false)
  const [voicePrompts, setVoicePrompts] = useState(true)
  const [haptics, setHaptics] = useState(true)

  const handleAutoSuggest = () => {
    const preset = DIFFICULTY_PRESETS[`age${age}`] || DIFFICULTY_PRESETS.age7
    setSpawnSpeed(preset.spawnSpeed)
    setHoldTime(preset.holdTime)
    setTargetScore(preset.targetScore)
  }

  const handleStart = () => {
    setConfig(prev => ({
      ...prev,
      spawnSpeed,
      holdTime,
      targetScore,
      music,
      calmMode,
      voicePrompts,
      haptics,
    }))
    navigateTo('countdown')
  }

  return (
    <div className="tab-screen">
      <Sidebar active="play" navigateTo={navigateTo} />
      <div className="tab-content">
        <div className="top">
          <div>
            <div className="crumb">Step 1 of 2 · Teacher</div>
            <h1>Set up the round</h1>
          </div>
          <div className="actions">
            <div
              className="tab-pill"
              onClick={() => navigateTo('home')}
              style={{ cursor: 'pointer' }}
            >← Back</div>
            <Button variant="blue" style={{ height: 52, fontSize: 18 }} onClick={handleStart}>✓ Start game</Button>
          </div>
        </div>
        <div className="set-grid">
          <div className="set-card">
            <div>
              <div className="ttl">Difficulty</div>
              <h3>Tune for the child</h3>
            </div>
            <div className="age-block">
              <div className="num">{age}</div>
              <div className="col">
                <div className="l">Child's age</div>
                <div className="label">years old</div>
              </div>
              <div className="stepper">
                <div className="step-btn" onClick={() => setAge(Math.max(3, age - 1))}>−</div>
                <div className="step-btn" onClick={() => setAge(Math.min(12, age + 1))}>+</div>
              </div>
            </div>
            <div className="auto-btn" onClick={handleAutoSuggest}>✦ Auto-suggest difficulty</div>
            <Slider
              name="Spawn speed"
              value={spawnSpeed}
              min={1.0} max={8.0} step={0.1}
              hint="How often a new item appears (seconds)"
              onChange={setSpawnSpeed}
            />
            <Slider
              name="Hold time"
              value={holdTime}
              min={1.0} max={8.0} step={0.1}
              hint="How long an item stays visible (seconds)"
              onChange={setHoldTime}
            />
            <Slider
              name="Target score"
              value={targetScore}
              min={5} max={30} step={1}
              hint="Round ends when reached"
              onChange={(v) => setTargetScore(Math.round(v))}
            />
          </div>
          <div className="set-card">
            <div>
              <div className="ttl">Mode · {config.mode === 'animals' ? 'Animals' : 'Shapes'}</div>
              <h3>Preview & comfort</h3>
            </div>
            <div className="preview-box">
              <div className="label">Live preview</div>
              <div className="preview-shape"></div>
            </div>
            <div className="opt-row">
              <div className="opt-card">
                <div className="col">
                  <div className="h">Music</div>
                  <div className="d">Calm loop</div>
                </div>
                <Switch checked={music} onChange={setMusic} />
              </div>
              <div className="opt-card">
                <div className="col">
                  <div className="h">Calm Mode</div>
                  <div className="d">Reduce motion</div>
                </div>
                <Switch checked={calmMode} onChange={setCalmMode} />
              </div>
            </div>
            <div className="opt-row">
              <div className="opt-card">
                <div className="col">
                  <div className="h">Voice prompts</div>
                  <div className="d">Name each item</div>
                </div>
                <Switch checked={voicePrompts} onChange={setVoicePrompts} />
              </div>
              <div className="opt-card">
                <div className="col">
                  <div className="h">Haptics</div>
                  <div className="d">Vibrate on tap</div>
                </div>
                <Switch checked={haptics} onChange={setHaptics} />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
