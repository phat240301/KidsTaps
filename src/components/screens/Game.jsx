// src/components/screens/Game.jsx
import { useState, useEffect, useRef } from 'react'
import { GAME_ITEMS } from '../../utils/gameLogic'
import { useGame } from '../../context/GameContext'

export function Game({ navigateTo }) {
  const { config, setResults } = useGame()
  const [paused, setPaused] = useState(false)
  const [score, setScore] = useState(0)
  const [time, setTime] = useState(120)
  const [items, setItems] = useState([])
  const [currentStreak, setCurrentStreak] = useState(0)
  const [maxStreak, setMaxStreak] = useState(0)
  const [tapCount, setTapCount] = useState(0)
  const [missCount, setMissCount] = useState(0)
  const gameOverRef = useRef(false)

  // Countdown timer
  useEffect(() => {
    if (paused || gameOverRef.current) return
    const timer = setInterval(() => setTime(t => t - 1), 1000)
    return () => clearInterval(timer)
  }, [paused])

  // End condition
  useEffect(() => {
    if (gameOverRef.current) return
    if (time <= 0 || score >= config.targetScore) {
      gameOverRef.current = true
      const totalAttempts = tapCount + missCount
      const accuracy = totalAttempts > 0
        ? Math.round((tapCount / totalAttempts) * 100)
        : 100
      const stars = score >= config.targetScore
        ? (time > 30 ? 3 : 2)
        : 1
      setResults({
        finalScore: score,
        targetScore: config.targetScore,
        timeLeft: Math.max(time, 0),
        streak: maxStreak,
        accuracy,
        stars,
      })
      const t = setTimeout(() => navigateTo('result'), 800)
      return () => clearTimeout(t)
    }
  }, [time, score])

  // Item spawn loop
  useEffect(() => {
    if (paused || gameOverRef.current) return
    const interval = setInterval(() => {
      const template = GAME_ITEMS[Math.floor(Math.random() * GAME_ITEMS.length)]
      const id = Date.now() + Math.random()
      const newItem = {
        ...template,
        id,
        top: `${10 + Math.random() * 60}%`,
        left: `${5 + Math.random() * 75}%`,
      }
      setItems(prev => [...prev, newItem])
      setTimeout(() => {
        setItems(prev => {
          const stillThere = prev.some(it => it.id === id)
          if (stillThere) {
            setMissCount(m => m + 1)
            setCurrentStreak(0)
          }
          return prev.filter(it => it.id !== id)
        })
      }, config.holdTime * 1000)
    }, config.spawnSpeed * 1000)
    return () => clearInterval(interval)
  }, [paused, config.spawnSpeed, config.holdTime])

  const handleItemTap = (id) => {
    setItems(prev => prev.filter(it => it.id !== id))
    setScore(prev => prev + 1)
    setTapCount(t => t + 1)
    setCurrentStreak(s => {
      const next = s + 1
      setMaxStreak(m => Math.max(m, next))
      return next
    })
  }

  const handleQuit = () => {
    gameOverRef.current = true
    navigateTo('home')
  }

  const formatTime = (seconds) => {
    const s = Math.max(seconds, 0)
    const mins = Math.floor(s / 60)
    const secs = s % 60
    return `${mins}:${secs.toString().padStart(2, '0')}`
  }

  return (
    <div className="tab-screen game-bg">
      <div className="game-hud">
        <div className="quit" onClick={handleQuit}>✕</div>
        <div className="pill">★ {score} / {config.targetScore}</div>
        <div className="progress">
          <div className="fill" style={{ width: `${Math.min((score / config.targetScore) * 100, 100)}%` }}></div>
          <div className="lbl">Round progress</div>
        </div>
        <div className="pill">⏱ {formatTime(time)}</div>
        <div className="pill">🔥 {currentStreak}</div>
        <div className="quit" onClick={() => setPaused(p => !p)}>⏸</div>
      </div>
      <div className="game-canvas">
        <div className="grid"></div>
        {items.map((item) => (
          <div
            key={item.id}
            className={`game-item ${item.color} ${item.shape === 'circle' ? 'c' : ''}`}
            style={{ top: item.top, left: item.left }}
            onClick={() => handleItemTap(item.id)}
          >
            {item.symbol}
          </div>
        ))}
        {paused && (
          <div className="pause-overlay">
            <div className="pause-card">
              <h3>Paused</h3>
              <div style={{ fontFamily: 'Space Grotesk', fontSize: 14, opacity: 0.65 }}>
                Take a breath. Tap resume when ready.
              </div>
              <div className="row">
                <div className="tab-btn cream" style={{ flex: 1 }} onClick={handleQuit}>🏠 Home</div>
                <div className="tab-btn blue" style={{ flex: 1 }} onClick={() => setPaused(false)}>▶ Resume</div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
