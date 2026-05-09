// src/components/screens/Game.jsx
import { useState, useEffect } from 'react'
import { GameState, GAME_ITEMS } from '../../utils/gameLogic'

export function Game({ paused: initialPaused = false }) {
  const [gameState] = useState(() => new GameState())
  const [paused, setPaused] = useState(initialPaused)
  const [score, setScore] = useState(0)
  const [time, setTime] = useState(120)
  const [items, setItems] = useState([])

  useEffect(() => {
    if (!paused && time > 0) {
      const timer = setInterval(() => {
        setTime(t => t - 1)
      }, 1000)
      return () => clearInterval(timer)
    }
  }, [paused, time])

  const handleItemTap = (index) => {
    setScore(prev => {
      const newScore = prev + 1
      return newScore >= gameState.targetScore ? newScore : newScore
    })
    setItems(prev => prev.filter((_, i) => i !== index))
  }

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60)
    const secs = seconds % 60
    return `${mins}:${secs.toString().padStart(2, '0')}`
  }

  return (
    <div className="tab-screen game-bg">
      <div className="game-hud">
        <div className="quit">✕</div>
        <div className="pill">★ {score} / {gameState.targetScore}</div>
        <div className="progress">
          <div className="fill" style={{ width: `${(score / gameState.targetScore) * 100}%` }}></div>
          <div className="lbl">Round progress</div>
        </div>
        <div className="pill">⏱ {formatTime(time)}</div>
        <div className="pill">🔥 {gameState.streak}</div>
        <div className="quit" onClick={() => setPaused(!paused)}>⏸</div>
      </div>
      <div className="game-canvas">
        <div className="grid"></div>
        {items.map((item, idx) => (
          <div
            key={idx}
            className={`game-item ${item.color} ${item.shape === 'circle' ? 'c' : ''}`}
            style={{ top: item.top, left: item.left }}
            onClick={() => handleItemTap(idx)}
          >
            {item.symbol}
          </div>
        ))}

        {paused && (
          <div className="pause-overlay">
            <div className="pause-card">
              <h3>Paused</h3>
              <div style={{ fontFamily: 'Space Grotesk', fontSize: 14, opacity: 0.65 }}>Take a breath. Tap resume when ready.</div>
              <div className="row">
                <div className="tab-btn cream" style={{ flex: 1 }}>🏠 Home</div>
                <div className="tab-btn blue" style={{ flex: 1 }} onClick={() => setPaused(false)}>▶ Resume</div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
