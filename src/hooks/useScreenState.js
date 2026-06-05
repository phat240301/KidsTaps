import { useState, useCallback } from 'react'

export function useScreenState() {
  const [currentScreen, setCurrentScreen] = useState('splash')

  const navigateTo = useCallback((screen) => setCurrentScreen(screen), [])

  return {
    currentScreen,
    setCurrentScreen,
    navigateTo,
    isHome: currentScreen === 'home',
    isSetup: currentScreen === 'setup',
    isCountdown: currentScreen === 'countdown',
    isGame: currentScreen === 'game',
    isResult: currentScreen === 'result',
    isSettings: currentScreen === 'settings',
    isLanguageLoading: currentScreen === 'language_loading'
  }
}
