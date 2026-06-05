// src/components/Sidebar.jsx
import { useGame } from '../context/GameContext'

export function Sidebar({ active = 'home', navigateTo }) {
  const { lang, setLang } = useGame()

  const items = [
    { id: 'home',     ic: '🏠', label: 'Home',        screen: 'home' },
    { id: 'play',     ic: '▶',  label: 'Play',         screen: 'setup' },
    { id: 'scores',   ic: '⭐', label: 'High Scores',  screen: 'home' },
    { id: 'settings', ic: '⚙',  label: 'Settings',     screen: 'settings' },
    { id: 'about',    ic: '?',  label: 'About',         screen: 'home' },
  ]

  const handleLanguageChange = (newLang) => {
    if (newLang !== lang) {
      setLang(newLang)
      navigateTo('language_loading')
    }
  }

  return (
    <div className="tab-sidebar">
      <div className="brand">
        <div className="mark">KT</div>
        <div>
          <div className="name">Kid Taps</div>
          <div className="ver">v1.0 · Offline</div>
        </div>
      </div>
      {items.map(i => (
        <div
          className={`nav-item ${active === i.id ? 'on' : ''}`}
          key={i.id}
          onClick={() => navigateTo(i.screen)}
          style={{ cursor: 'pointer' }}
        >
          <span className="ic">{i.ic}</span>
          <span>{i.label}</span>
        </div>
      ))}
      <div className="footer">
        <div className="lang-toggle">
          <div className={`opt ${lang === 'EN' ? 'on' : ''}`} onClick={() => handleLanguageChange('EN')}>EN</div>
          <div className={`opt ${lang === 'VI' ? 'on' : ''}`} onClick={() => handleLanguageChange('VI')}>VI</div>
        </div>
        <div className="teacher-pill">👩‍🏫 Teacher mode</div>
      </div>
    </div>
  )
}
