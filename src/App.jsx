import { useScreenState } from './hooks/useScreenState'
import { Splash } from './components/screens/Splash'
import { Home } from './components/screens/Home'
import { Setup } from './components/screens/Setup'
import { Countdown } from './components/screens/Countdown'
import { Game } from './components/screens/Game'
import { Result } from './components/screens/Result'
import { Settings } from './components/screens/Settings'
import { LanguageLoading } from './components/screens/LanguageLoading'

function Tablet({ children }) {
  return (
    <div className="tablet-frame">
      <div className="tablet-inner">{children}</div>
      <div className="cam"></div>
    </div>
  )
}

export default function App() {
  const screen = useScreenState()

  const renderScreen = () => {
    switch (screen.currentScreen) {
      case 'splash':
        return <Splash onComplete={() => screen.navigateTo('home')} />
      case 'home':
        return <Home navigateTo={screen.navigateTo} />
      case 'setup':
        return <Setup navigateTo={screen.navigateTo} />
      case 'countdown':
        return <Countdown navigateTo={screen.navigateTo} />
      case 'game':
        return <Game navigateTo={screen.navigateTo} />
      case 'result':
        return <Result navigateTo={screen.navigateTo} />
      case 'settings':
        return <Settings navigateTo={screen.navigateTo} />
      case 'language_loading':
        return <LanguageLoading navigateTo={screen.navigateTo} />
      default:
        return <Home navigateTo={screen.navigateTo} />
    }
  }

  return (
    <Tablet>
      {renderScreen()}
    </Tablet>
  )
}
