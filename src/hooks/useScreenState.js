import { useState } from 'react'

export function useScreenState() {
  const [currentScreen, setCurrentScreen] = useState('splash')

  return {
    currentScreen,
    setCurrentScreen,
    navigateTo: (screen) => setCurrentScreen(screen),
    isHome: currentScreen === 'home',
    isSetup: currentScreen === 'setup',
    isCountdown: currentScreen === 'countdown',
    isGame: currentScreen === 'game',
    isResult: currentScreen === 'result',
    isSettings: currentScreen === 'settings',
    isLanguageLoading: currentScreen === 'language_loading'
  }
}
