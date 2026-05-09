// src/components/Sidebar.jsx
import { useState } from 'react'

export function Sidebar({ active = 'home', onLanguageChange }) {
  const [lang, setLang] = useState('EN')

  const items = [
    { id: 'home',     ic: '🏠', label: 'Home' },
    { id: 'play',     ic: '▶',  label: 'Play' },
    { id: 'scores',   ic: '⭐', label: 'High Scores' },
    { id: 'settings', ic: '⚙',  label: 'Settings' },
    { id: 'about',    ic: '?',  label: 'About' },
  ]

  const handleLanguageChange = (newLang) => {
    setLang(newLang)
    onLanguageChange?.(newLang)
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
        <div className={`nav-item ${active === i.id ? 'on' : ''}`} key={i.id}>
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
