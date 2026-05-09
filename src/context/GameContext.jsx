// src/context/GameContext.jsx
import { createContext, useState, useContext } from 'react'

const DEFAULT_CONFIG = {
  mode: 'shapes',
  spawnSpeed: 3.4,
  holdTime: 3.9,
  targetScore: 17,
  music: true,
  calmMode: false,
  voicePrompts: true,
  haptics: true,
}

const DEFAULT_RESULTS = {
  finalScore: 0,
  targetScore: 17,
  timeLeft: 120,
  streak: 0,
  accuracy: 0,
  stars: 0,
}

export const GameContext = createContext(null)

export function GameProvider({ children }) {
  const [config, setConfig] = useState(DEFAULT_CONFIG)
  const [results, setResults] = useState(DEFAULT_RESULTS)
  const [lang, setLang] = useState('EN')

  return (
    <GameContext.Provider value={{ config, setConfig, results, setResults, lang, setLang }}>
      {children}
    </GameContext.Provider>
  )
}

export function useGame() {
  const context = useContext(GameContext)
  if (!context) throw new Error('useGame must be used within GameProvider')
  return context
}
