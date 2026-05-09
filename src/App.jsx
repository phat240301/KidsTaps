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
        return <Home />
      case 'setup':
        return <Setup />
      case 'countdown':
        return <Countdown />
      case 'game':
        return <Game />
      case 'result':
        return <Result />
      case 'settings':
        return <Settings />
      case 'language_loading':
        return <LanguageLoading />
      default:
        return <Home />
    }
  }

  return (
    <Tablet>
      {renderScreen()}
    </Tablet>
  )
}
