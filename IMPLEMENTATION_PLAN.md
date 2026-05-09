# Kid Taps — Tablet UI Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Implement a fully functional Kid Taps game app with 7 tablet screens (1280×800 landscape), animations, and interactive game mechanics.

**Architecture:** React-based web app with component-per-screen structure. Each screen is a self-contained component. Shared utilities handle game logic, state, and animations. CSS provides all styling and keyframe animations (no external animation libraries).

**Tech Stack:** React 18, CSS3 (animations, flexbox, grid), Google Fonts (Fredoka, Space Grotesk), JavaScript ES6+

---

## File Structure

```
src/
├── App.jsx                      # Main app root, routing
├── components/
│   ├── Sidebar.jsx              # Shared sidebar navigation
│   ├── screens/
│   │   ├── Home.jsx             # Screen 1: Home
│   │   ├── Setup.jsx            # Screen 2: Teacher Setup
│   │   ├── Countdown.jsx        # Screen 3: Countdown
│   │   ├── Game.jsx             # Screen 4: Game (active & paused)
│   │   ├── Result.jsx           # Screen 5: Result
│   │   ├── Settings.jsx         # Screen 6: Settings
│   │   └── LanguageLoading.jsx  # Screen 7: Language Loading
│   └── shared/
│       ├── Button.jsx           # Reusable button component
│       ├── Switch.jsx           # Reusable toggle switch
│       └── Slider.jsx           # Reusable slider component
├── styles/
│   ├── index.css                # Global styles & variables
│   ├── tablet.css               # All screen-specific styles
│   ├── animations.css           # Keyframe animations
│   └── components.css           # Component styles
├── utils/
│   ├── gameLogic.js             # Game mechanics (scoring, timer)
│   ├── gameShapes.js            # Shape definitions for game modes
│   └── constants.js             # App constants & color palette
├── hooks/
│   ├── useGameState.js          # Game state management
│   └── useSettings.js           # Settings persistence
└── index.jsx                    # React entry point
```

---

## Task 1: Project Setup & Dependencies

**Files:**
- Create: `package.json`
- Create: `public/index.html`
- Create: `src/index.jsx`

- [ ] **Step 1: Initialize package.json**

```json
{
  "name": "kid-taps",
  "version": "1.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  },
  "dependencies": {
    "react": "^18.3.1",
    "react-dom": "^18.3.1"
  },
  "devDependencies": {
    "@vitejs/plugin-react": "^4.0.0",
    "vite": "^4.4.0"
  }
}
```

- [ ] **Step 2: Create Vite config**

```javascript
// vite.config.js
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000
  }
})
```

- [ ] **Step 3: Create public/index.html**

```html
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@500;600;700&family=Space+Grotesk:wght@500;600;700&display=swap" rel="stylesheet">
  <title>Kid Taps — Tablet Game</title>
</head>
<body>
  <div id="root"></div>
  <script type="module" src="/src/index.jsx"></script>
</body>
</html>
```

- [ ] **Step 4: Create src/index.jsx**

```jsx
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './styles/index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
)
```

- [ ] **Step 5: Install dependencies**

```bash
npm install
```

Expected: Dependencies installed in node_modules

- [ ] **Step 6: Commit**

```bash
git add package.json package-lock.json vite.config.js public/index.html src/index.jsx
git commit -m "chore: initialize project with vite and react"
```

---

## Task 2: Global Styles & Design System

**Files:**
- Create: `src/utils/constants.js`
- Create: `src/styles/index.css`
- Create: `src/styles/animations.css`

- [ ] **Step 1: Create design system constants**

```javascript
// src/utils/constants.js
export const COLORS = {
  red: '#FF3B3B',
  yellow: '#FFD23F',
  blue: '#1E88FF',
  green: '#22C55E',
  cream: '#FFF8EC',
  ink: '#1A1A2E',
  soft: '#F4ECDA',
}

export const GAME_SHAPES = ['circle', 'square', 'star', 'heart', 'triangle']

export const GAME_ANIMALS = ['rabbit', 'bear', 'cat', 'dog', 'elephant']

export const GAME_MODES = {
  shapes: {
    name: 'Shapes',
    icon: '🔶',
    description: 'Tap circles, squares, stars and hearts as they appear.',
  },
  animals: {
    name: 'Animals',
    icon: '🐰',
    description: 'Tap the animals as they appear. Each tap names the animal aloud.',
  },
}

export const DIFFICULTY_PRESETS = {
  age3: { spawnSpeed: 5.2, holdTime: 6.0, targetScore: 8 },
  age5: { spawnSpeed: 4.0, holdTime: 4.5, targetScore: 12 },
  age7: { spawnSpeed: 3.4, holdTime: 3.9, targetScore: 17 },
  age9: { spawnSpeed: 2.2, holdTime: 2.8, targetScore: 24 },
}
```

- [ ] **Step 2: Create global styles**

```css
/* src/styles/index.css */
:root {
  --red: #FF3B3B;
  --yellow: #FFD23F;
  --blue: #1E88FF;
  --green: #22C55E;
  --cream: #FFF8EC;
  --ink: #1A1A2E;
  --soft: #F4ECDA;
  --shadow: 0 4px 0 rgba(26,26,46,0.18);
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  width: 100%;
  height: 100%;
  font-family: 'Fredoka', system-ui, sans-serif;
  color: var(--ink);
  background: #f0eee9;
}

#root {
  width: 100vw;
  height: 100vh;
}

/* Typography */
h1 { font-size: 40px; font-weight: 700; line-height: 1; letter-spacing: -0.01em; }
h2 { font-size: 56px; font-weight: 700; line-height: 1; letter-spacing: -0.02em; }
h3 { font-size: 22px; font-weight: 700; }

.crumb {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
  letter-spacing: 0.16em;
  text-transform: uppercase;
  color: var(--blue);
}

/* Tab Screen Base */
.tab-screen {
  width: 100%;
  height: 100%;
  background: var(--cream);
  position: relative;
  overflow: hidden;
  display: flex;
}

/* Tablet Frame */
.tablet-frame {
  width: 100%;
  height: 100%;
  border-radius: 24px;
  overflow: hidden;
  background: var(--ink);
  border: 14px solid var(--ink);
  box-sizing: border-box;
  box-shadow: 0 30px 80px rgba(0,0,0,0.25);
  position: relative;
}

.tablet-frame .cam {
  position: absolute;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #555;
  top: 50%;
  right: 4px;
  transform: translateY(-50%);
}

.tablet-inner {
  width: 100%;
  height: 100%;
  border-radius: 10px;
  overflow: hidden;
  background: var(--cream);
}
```

- [ ] **Step 3: Create animations CSS**

```css
/* src/styles/animations.css */

/* Countdown rings rotation */
@keyframes rotateOrbit {
  to { transform: rotate(360deg); }
}

/* Language card flip */
@keyframes flipCard {
  0%, 100% { transform: rotateY(0deg); }
  50% { transform: rotateY(180deg); }
}

/* Progress fill */
@keyframes loadFill {
  0% { width: 0%; }
  100% { width: 100%; }
}

/* Bouncing dots */
@keyframes bounceDot {
  0%, 100% { opacity: 0.3; transform: translateY(0); }
  50% { opacity: 1; transform: translateY(-8px); background: var(--red); }
}

/* Pulse animation for game items */
@keyframes itemPulse {
  0% { transform: scale(0.8); opacity: 0; }
  50% { transform: scale(1.05); }
  100% { transform: scale(1); opacity: 1; }
}

/* Score burst pop */
@keyframes scoreBurst {
  0% { transform: scale(0) translateY(0); opacity: 1; }
  100% { transform: scale(1.5) translateY(-60px); opacity: 0; }
}

/* Ripple effect */
@keyframes ripple {
  0% { opacity: 0.6; }
  100% { opacity: 0; transform: scale(1.8); }
}
```

- [ ] **Step 4: Commit**

```bash
git add src/utils/constants.js src/styles/index.css src/styles/animations.css
git commit -m "feat: add design system constants and global styles"
```

---

## Task 3: Create Shared Components

**Files:**
- Create: `src/components/Sidebar.jsx`
- Create: `src/components/shared/Button.jsx`
- Create: `src/components/shared/Switch.jsx`
- Create: `src/components/shared/Slider.jsx`
- Create: `src/styles/components.css`

- [ ] **Step 1: Create Button component**

```jsx
// src/components/shared/Button.jsx
export function Button({ children, variant = 'primary', ...props }) {
  const classNames = `tab-btn ${variant === 'red' ? 'red' : variant === 'blue' ? 'blue' : variant === 'cream' ? 'cream' : ''} ${props.className || ''}`
  return <button {...props} className={classNames}>{children}</button>
}
```

- [ ] **Step 2: Create Switch component**

```jsx
// src/components/shared/Switch.jsx
export function Switch({ checked = false, onChange }) {
  return (
    <div 
      className={`kt-switch ${checked ? 'on' : ''}`}
      onClick={() => onChange(!checked)}
      role="switch"
      aria-checked={checked}
    />
  )
}
```

- [ ] **Step 3: Create Slider component**

```jsx
// src/components/shared/Slider.jsx
export function Slider({ name, value, min = 0, max = 100, hint, onChange }) {
  const fillPercent = ((value - min) / (max - min)) * 100
  
  return (
    <div className="slider-row">
      <div className="label-row">
        <div className="name">{name}</div>
        <div className="val">{value}</div>
      </div>
      <div className="track">
        <div className="fill" style={{ width: `${fillPercent}%` }}></div>
        <input
          type="range"
          min={min}
          max={max}
          value={value}
          onChange={(e) => onChange(parseFloat(e.target.value))}
          className="slider-input"
        />
        <div className="knob" style={{ left: `${fillPercent}%` }}></div>
      </div>
      {hint && <div className="hint">{hint}</div>}
    </div>
  )
}
```

- [ ] **Step 4: Create Sidebar component**

```jsx
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
```

- [ ] **Step 5: Create components CSS**

```css
/* src/styles/components.css */

/* Shared Sidebar */
.tab-sidebar {
  width: 280px;
  background: var(--ink);
  color: var(--cream);
  padding: 28px 22px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  flex-shrink: 0;
}

.tab-sidebar .brand {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 28px;
}

.tab-sidebar .brand .mark {
  width: 56px;
  height: 56px;
  border-radius: 16px;
  background: var(--yellow);
  border: 3px solid var(--cream);
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  font-size: 26px;
  color: var(--ink);
  letter-spacing: -0.04em;
}

.tab-sidebar .brand .name {
  font-weight: 700;
  font-size: 22px;
  line-height: 1.05;
}

.tab-sidebar .brand .ver {
  font-family: 'Space Grotesk';
  font-size: 11px;
  opacity: 0.5;
  letter-spacing: 0.1em;
  text-transform: uppercase;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 14px;
  border-radius: 14px;
  font-size: 16px;
  font-weight: 600;
  color: var(--cream);
  opacity: 0.65;
  cursor: pointer;
}

.nav-item.on {
  background: rgba(255,255,255,0.1);
  opacity: 1;
  border-left: 4px solid var(--yellow);
  padding-left: 10px;
}

.nav-item .ic {
  width: 28px;
  text-align: center;
  font-size: 18px;
}

.tab-sidebar .footer {
  margin-top: auto;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.tab-sidebar .lang-toggle {
  display: flex;
  gap: 4px;
  padding: 4px;
  background: rgba(255,255,255,0.08);
  border-radius: 999px;
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
}

.tab-sidebar .lang-toggle .opt {
  flex: 1;
  text-align: center;
  padding: 8px 0;
  border-radius: 999px;
  opacity: 0.6;
  letter-spacing: 0.06em;
  cursor: pointer;
}

.tab-sidebar .lang-toggle .opt.on {
  background: var(--yellow);
  color: var(--ink);
  opacity: 1;
}

.tab-sidebar .teacher-pill {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 14px;
  background: var(--blue);
  border-radius: 14px;
  font-weight: 600;
  font-size: 14px;
}

/* Shared Buttons */
.tab-btn {
  height: 64px;
  padding: 0 28px;
  border-radius: 20px;
  font-family: 'Fredoka';
  font-weight: 700;
  font-size: 20px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  border: 3px solid var(--ink);
  box-shadow: 0 5px 0 var(--ink);
  background: var(--yellow);
  color: var(--ink);
  cursor: pointer;
}

.tab-btn.red {
  background: var(--red);
  color: var(--cream);
}

.tab-btn.blue {
  background: var(--blue);
  color: var(--cream);
}

.tab-btn.cream {
  background: var(--cream);
  color: var(--ink);
}

.tab-btn.lg {
  height: 76px;
  font-size: 24px;
  padding: 0 36px;
}

/* Switch */
.kt-switch {
  width: 44px;
  height: 24px;
  border-radius: 999px;
  background: var(--soft);
  border: 2px solid var(--ink);
  position: relative;
  flex-shrink: 0;
  cursor: pointer;
}

.kt-switch::after {
  content: '';
  position: absolute;
  width: 16px;
  height: 16px;
  border-radius: 50%;
  background: var(--cream);
  border: 2px solid var(--ink);
  top: 1px;
  left: 1px;
  transition: left 0.2s ease;
}

.kt-switch.on {
  background: var(--green);
}

.kt-switch.on::after {
  left: 21px;
}

/* Slider */
.slider-row {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.slider-row .label-row {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}

.slider-row .name {
  font-size: 16px;
  font-weight: 600;
}

.slider-row .val {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 18px;
  color: var(--blue);
}

.slider-row .hint {
  font-family: 'Space Grotesk';
  font-size: 11px;
  opacity: 0.55;
}

.track {
  height: 14px;
  border-radius: 999px;
  background: var(--soft);
  border: 2px solid var(--ink);
  position: relative;
}

.track .fill {
  position: absolute;
  inset: 0 auto 0 0;
  border-radius: 999px;
  background: var(--blue);
  border-right: 2px solid var(--ink);
}

.track .knob {
  position: absolute;
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background: var(--yellow);
  border: 3px solid var(--ink);
  top: 50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 3px 0 var(--ink);
  pointer-events: none;
}

.slider-input {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}
```

- [ ] **Step 6: Commit**

```bash
git add src/components/
git commit -m "feat: create shared components (button, switch, slider, sidebar)"
```

---

## Task 4: Create Home Screen

**Files:**
- Create: `src/components/screens/Home.jsx`
- Create: `src/styles/screens/home.css`

- [ ] **Step 1: Create Home screen component**

```jsx
// src/components/screens/Home.jsx
import { Sidebar } from '../Sidebar'

export function Home() {
  return (
    <div className="tab-screen">
      <Sidebar active="home" />
      <div className="tab-content">
        <div className="top">
          <div>
            <div className="crumb">Welcome back</div>
            <h1>Pick a game to play</h1>
          </div>
          <div className="actions">
            <div className="tab-pill">🔥 Streak · 3 days</div>
            <div className="tab-pill dark">▶ Quick play</div>
          </div>
        </div>
        <div className="h-grid">
          <div className="h-mode shapes">
            <div className="tag">Mode 01</div>
            <h2>Shapes</h2>
            <div className="desc">Tap circles, squares, stars and hearts as they appear. Great for early shape recognition.</div>
            <div className="stats">
              <div className="stat"><div className="v">24</div><div className="l">High score</div></div>
              <div className="stat"><div className="v">12</div><div className="l">Sessions</div></div>
              <div className="stat"><div className="v">5</div><div className="l">Shapes</div></div>
            </div>
            <div className="glyph">🔶</div>
          </div>
          <div className="h-side">
            <div className="h-mode animals" style={{ flex: 1 }}>
              <div className="tag">Mode 02</div>
              <h2 style={{ fontSize: 44 }}>Animals</h2>
              <div className="desc">Tap the animals as they appear. Each tap names the animal aloud.</div>
              <div className="glyph" style={{ width: 130, height: 130, fontSize: 80 }}>🐰</div>
            </div>
            <div className="h-card yellow" style={{ flex: 0 }}>
              <div className="ttl">Recent</div>
              <div className="row"><div className="name"><span className="em">🔶</span>Shapes · age 7</div><div className="v">17 pts</div></div>
              <div className="row"><div className="name"><span className="em">🐰</span>Animals · age 7</div><div className="v">14 pts</div></div>
              <div className="row"><div className="name"><span className="em">🔶</span>Shapes · age 6</div><div className="v">11 pts</div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
```

- [ ] **Step 2: Create home screen styles**

```css
/* src/styles/screens/home.css */

.tab-content {
  flex: 1;
  padding: 32px 40px;
  display: flex;
  flex-direction: column;
  gap: 24px;
  min-width: 0;
}

.tab-content .top {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 24px;
}

.tab-content .actions {
  display: flex;
  gap: 10px;
}

.tab-pill {
  height: 44px;
  padding: 0 16px;
  border-radius: 999px;
  background: var(--cream);
  border: 2px solid var(--ink);
  display: inline-flex;
  align-items: center;
  gap: 8px;
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 13px;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  cursor: pointer;
}

.tab-pill.dark {
  background: var(--ink);
  color: var(--cream);
}

.h-grid {
  flex: 1;
  display: grid;
  grid-template-columns: 1.4fr 1fr;
  gap: 20px;
}

.h-mode {
  border-radius: 28px;
  border: 3px solid var(--ink);
  box-shadow: 0 6px 0 var(--ink);
  padding: 28px;
  display: flex;
  flex-direction: column;
  position: relative;
  overflow: hidden;
}

.h-mode.shapes {
  background: var(--red);
  color: var(--cream);
}

.h-mode.animals {
  background: var(--blue);
  color: var(--cream);
}

.h-mode .tag {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
  letter-spacing: 0.16em;
  text-transform: uppercase;
  opacity: 0.8;
}

.h-mode h2 {
  font-size: 56px;
  font-weight: 700;
  margin: 4px 0 8px;
  letter-spacing: -0.02em;
}

.h-mode .desc {
  font-family: 'Space Grotesk';
  font-size: 14px;
  opacity: 0.85;
  max-width: 280px;
  line-height: 1.5;
}

.h-mode .glyph {
  margin-top: auto;
  align-self: flex-end;
  width: 200px;
  height: 200px;
  border-radius: 32px;
  background: var(--cream);
  border: 3px solid var(--ink);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 120px;
}

.h-mode .stats {
  display: flex;
  gap: 20px;
  margin-top: 16px;
}

.h-mode .stat {
  font-family: 'Space Grotesk';
}

.h-mode .stat .v {
  font-size: 28px;
  font-weight: 700;
  line-height: 1;
}

.h-mode .stat .l {
  font-size: 11px;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  opacity: 0.8;
}

.h-side {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.h-card {
  border-radius: 28px;
  border: 3px solid var(--ink);
  background: var(--cream);
  box-shadow: 0 6px 0 var(--ink);
  padding: 22px 24px;
  flex: 1;
  display: flex;
  flex-direction: column;
}

.h-card.yellow {
  background: var(--yellow);
}

.h-card .ttl {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  opacity: 0.6;
  margin-bottom: 10px;
}

.h-card .row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0;
  border-top: 1.5px dashed rgba(26,26,46,0.2);
  font-family: 'Space Grotesk';
  font-size: 14px;
}

.h-card .row:first-of-type {
  border-top: none;
}

.h-card .row .name {
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 10px;
}

.h-card .row .name .em {
  font-size: 22px;
}

.h-card .row .v {
  font-weight: 700;
  font-size: 18px;
}
```

- [ ] **Step 3: Commit**

```bash
git add src/components/screens/Home.jsx src/styles/screens/home.css
git commit -m "feat: implement home screen with game mode selection"
```

---

## Task 5: Create Setup Screen

**Files:**
- Create: `src/components/screens/Setup.jsx`
- Create: `src/styles/screens/setup.css`

- [ ] **Step 1: Create Setup screen component**

```jsx
// src/components/screens/Setup.jsx
import { useState } from 'react'
import { Sidebar } from '../Sidebar'
import { Slider } from '../shared/Slider'
import { Switch } from '../shared/Switch'
import { Button } from '../shared/Button'
import { DIFFICULTY_PRESETS } from '../../utils/constants'

export function Setup() {
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

  return (
    <div className="tab-screen">
      <Sidebar active="play" />
      <div className="tab-content">
        <div className="top">
          <div>
            <div className="crumb">Step 1 of 2 · Teacher</div>
            <h1>Set up the round</h1>
          </div>
          <div className="actions">
            <div className="tab-pill">← Back</div>
            <Button variant="blue" style={{ height: 52, fontSize: 18 }}>✓ Start game</Button>
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
            <Slider name="Spawn speed" value={spawnSpeed.toFixed(1)} hint="How often a new item appears" />
            <Slider name="Hold time" value={holdTime.toFixed(1)} hint="How long an item stays visible" />
            <Slider name="Target score" value={Math.round(targetScore)} hint="Round ends when reached" />
          </div>
          <div className="set-card">
            <div>
              <div className="ttl">Mode · Shapes</div>
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
```

- [ ] **Step 2: Create setup screen styles**

```css
/* src/styles/screens/setup.css */

.set-grid {
  flex: 1;
  display: grid;
  grid-template-columns: 1.1fr 1fr;
  gap: 24px;
  min-height: 0;
}

.set-card {
  border-radius: 28px;
  border: 3px solid var(--ink);
  background: var(--cream);
  box-shadow: 0 6px 0 var(--ink);
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 16px;
  overflow: hidden;
}

.set-card .ttl {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  opacity: 0.6;
}

.set-card h3 {
  font-size: 22px;
  font-weight: 700;
  margin: 0;
}

.age-block {
  background: var(--yellow);
  border-radius: 22px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 18px;
}

.age-block .num {
  font-size: 88px;
  font-weight: 700;
  line-height: 1;
}

.age-block .col {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.age-block .col .l {
  font-family: 'Space Grotesk';
  font-size: 11px;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  opacity: 0.7;
}

.age-block .col .label {
  font-size: 18px;
  font-weight: 600;
}

.age-block .stepper {
  margin-left: auto;
  display: flex;
  gap: 8px;
}

.step-btn {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  background: var(--cream);
  border: 2.5px solid var(--ink);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 26px;
  font-weight: 700;
  box-shadow: 0 3px 0 var(--ink);
  cursor: pointer;
}

.auto-btn {
  height: 52px;
  border-radius: 16px;
  background: var(--ink);
  color: var(--cream);
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  cursor: pointer;
}

.preview-box {
  flex: 1;
  background: var(--soft);
  border: 3px dashed var(--ink);
  border-radius: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.preview-box .label {
  position: absolute;
  top: 14px;
  left: 18px;
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 11px;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  opacity: 0.55;
}

.preview-shape {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background: var(--red);
  border: 3px solid var(--ink);
  box-shadow: 0 5px 0 var(--ink);
}

.opt-row {
  display: flex;
  gap: 10px;
}

.opt-card {
  flex: 1;
  border: 2px solid var(--ink);
  border-radius: 16px;
  padding: 14px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
}

.opt-card .col .h {
  font-size: 15px;
  font-weight: 700;
}

.opt-card .col .d {
  font-family: 'Space Grotesk';
  font-size: 11px;
  opacity: 0.6;
}
```

- [ ] **Step 3: Commit**

```bash
git add src/components/screens/Setup.jsx src/styles/screens/setup.css
git commit -m "feat: implement setup screen with difficulty and settings controls"
```

---

## Task 6: Create Countdown Screen

**Files:**
- Create: `src/components/screens/Countdown.jsx`
- Create: `src/styles/screens/countdown.css`

- [ ] **Step 1: Create Countdown screen component**

```jsx
// src/components/screens/Countdown.jsx
import { useEffect, useState } from 'react'

export function Countdown() {
  const [count, setCount] = useState(3)
  const [activeDot, setActiveDot] = useState(0)

  useEffect(() => {
    if (count > 0) {
      const timer = setTimeout(() => {
        setCount(count - 1)
        setActiveDot((count - 1) % 3)
      }, 1000)
      return () => clearTimeout(timer)
    }
  }, [count])

  return (
    <div className="tab-screen cd">
      <div className="ring-bg r2"></div>
      <div className="ring-bg"></div>
      <div className="quit-btn">×</div>
      <div className="center">
        <div className="ready">Get ready</div>
        <div className="num">{count > 0 ? count : 'GO!'}</div>
        <div className="dots">
          <div className={`dot ${activeDot === 0 ? 'on' : ''}`}></div>
          <div className={`dot ${activeDot === 1 ? 'on' : ''}`}></div>
          <div className={`dot ${activeDot === 2 ? 'on' : ''}`}></div>
        </div>
      </div>
    </div>
  )
}
```

- [ ] **Step 2: Create countdown screen styles**

```css
/* src/styles/screens/countdown.css */

.cd {
  background: var(--blue);
  position: relative;
}

.cd .ring-bg {
  position: absolute;
  width: 600px;
  height: 600px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 8px dashed rgba(255,255,255,0.18);
  border-radius: 50%;
  animation: rotateOrbit 8s linear infinite;
}

.cd .ring-bg.r2 {
  width: 760px;
  height: 760px;
  border-width: 4px;
  border-color: rgba(255,255,255,0.1);
  animation: rotateOrbit 6s linear infinite reverse;
}

.cd .center {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 32px;
  position: relative;
  z-index: 1;
}

.cd .ready {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 18px;
  letter-spacing: 0.32em;
  text-transform: uppercase;
  color: var(--cream);
  opacity: 0.85;
}

.cd .num {
  width: 320px;
  height: 320px;
  border-radius: 50%;
  background: var(--yellow);
  border: 8px solid var(--ink);
  box-shadow: 0 14px 0 rgba(0,0,0,0.25);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 240px;
  font-weight: 700;
  color: var(--ink);
  line-height: 1;
}

.cd .dots {
  display: flex;
  gap: 16px;
}

.cd .dot {
  width: 18px;
  height: 18px;
  border-radius: 50%;
  background: var(--cream);
  opacity: 0.4;
  transition: opacity 0.3s ease;
}

.cd .dot.on {
  opacity: 1;
}

.cd .quit-btn {
  position: absolute;
  top: 24px;
  right: 24px;
  width: 56px;
  height: 56px;
  border-radius: 18px;
  background: rgba(255,255,255,0.12);
  border: 2px solid var(--cream);
  color: var(--cream);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  cursor: pointer;
}
```

- [ ] **Step 3: Commit**

```bash
git add src/components/screens/Countdown.jsx src/styles/screens/countdown.css
git commit -m "feat: implement countdown screen with animated rings and countdown"
```

---

## Task 7: Create Game Screen

**Files:**
- Create: `src/components/screens/Game.jsx`
- Create: `src/styles/screens/game.css`
- Create: `src/utils/gameLogic.js`

- [ ] **Step 1: Create game logic utilities**

```javascript
// src/utils/gameLogic.js
export class GameState {
  constructor() {
    this.score = 0
    this.targetScore = 17
    this.timeLeft = 120
    this.itemsSpawned = 0
    this.itemsHit = 0
    this.streak = 0
    this.gameActive = true
  }

  addScore(points = 1) {
    this.score += points
    this.itemsHit++
    this.streak++
    return this.score >= this.targetScore
  }

  resetStreak() {
    this.streak = 0
  }

  tick() {
    if (this.timeLeft > 0) {
      this.timeLeft--
    }
    return this.timeLeft <= 0 || this.score >= this.targetScore
  }

  togglePause() {
    this.gameActive = !this.gameActive
  }
}

export const GAME_ITEMS = [
  { symbol: '●', color: 'r', shape: 'circle' },
  { symbol: '★', color: 'default', shape: 'star' },
  { symbol: '■', color: 'b', shape: 'square' },
  { symbol: '♥', color: 'g', shape: 'heart' },
  { symbol: '▲', color: 'default', shape: 'triangle' },
]
```

- [ ] **Step 2: Create Game screen component**

```jsx
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
```

- [ ] **Step 3: Create game screen styles**

```css
/* src/styles/screens/game.css */

.game-bg {
  background: linear-gradient(180deg, #FFF8EC 0%, #FFEED1 100%);
  flex-direction: column;
  padding: 16px;
  gap: 12px;
}

.game-hud {
  display: flex;
  align-items: center;
  gap: 12px;
}

.game-hud .pill {
  background: var(--ink);
  color: var(--cream);
  border-radius: 16px;
  padding: 10px 18px;
  display: flex;
  align-items: center;
  gap: 10px;
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 16px;
}

.game-hud .progress {
  flex: 1;
  height: 18px;
  border-radius: 999px;
  background: var(--soft);
  border: 2.5px solid var(--ink);
  position: relative;
  overflow: hidden;
}

.game-hud .progress .fill {
  height: 100%;
  background: var(--green);
  border-right: 2.5px solid var(--ink);
  transition: width 0.3s ease;
}

.game-hud .progress .lbl {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 11px;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: var(--ink);
}

.game-hud .quit {
  width: 52px;
  height: 52px;
  border-radius: 16px;
  background: var(--cream);
  border: 2.5px solid var(--ink);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  box-shadow: 0 3px 0 var(--ink);
  cursor: pointer;
}

.game-canvas {
  flex: 1;
  position: relative;
  border-radius: 28px;
  border: 3px solid var(--ink);
  background: var(--cream);
  box-shadow: 0 6px 0 var(--ink);
  overflow: hidden;
}

.game-canvas .grid {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(var(--ink) 1px, transparent 1px),
    linear-gradient(90deg, var(--ink) 1px, transparent 1px);
  background-size: 60px 60px;
  opacity: 0.04;
}

.game-item {
  position: absolute;
  width: 130px;
  height: 130px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 70px;
  border-radius: 32px;
  border: 3.5px solid var(--ink);
  box-shadow: 0 6px 0 var(--ink);
  background: var(--yellow);
  cursor: pointer;
  animation: itemPulse 0.3s ease-out;
}

.game-item.r {
  background: var(--red);
  color: var(--cream);
}

.game-item.b {
  background: var(--blue);
  color: var(--cream);
}

.game-item.g {
  background: var(--green);
  color: var(--cream);
}

.game-item.c {
  border-radius: 50%;
}

.game-item:active {
  transform: scale(0.95);
}

.pause-overlay {
  position: absolute;
  inset: 0;
  background: rgba(26,26,46,0.55);
  backdrop-filter: blur(2px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
}

.pause-card {
  width: 480px;
  background: var(--cream);
  border: 3px solid var(--ink);
  border-radius: 28px;
  box-shadow: 0 8px 0 var(--ink);
  padding: 32px;
  display: flex;
  flex-direction: column;
  gap: 14px;
  align-items: center;
}

.pause-card h3 {
  font-size: 32px;
  font-weight: 700;
  margin: 0;
}

.pause-card .row {
  display: flex;
  gap: 12px;
  width: 100%;
}
```

- [ ] **Step 4: Commit**

```bash
git add src/components/screens/Game.jsx src/styles/screens/game.css src/utils/gameLogic.js
git commit -m "feat: implement game screen with interactive items and pause overlay"
```

---

## Task 8: Create Result Screen

**Files:**
- Create: `src/components/screens/Result.jsx`
- Create: `src/styles/screens/result.css`

- [ ] **Step 1: Create Result screen component**

```jsx
// src/components/screens/Result.jsx
export function Result() {
  const finalScore = 17
  const targetScore = 17
  const stars = 2
  const time = 82
  const streak = 5
  const accuracy = 94

  return (
    <div className="tab-screen r-bg">
      <div className="confetti">
        <span className="c1"></span>
        <span className="c2"></span>
        <span className="c3"></span>
        <span className="c4"></span>
        <span className="c5"></span>
        <span className="c6"></span>
        <span className="c7"></span>
      </div>
      <div className="r-grid">
        <div className="r-left">
          <div className="crumb">Round complete</div>
          <h1>Great job!</h1>
          <div className="desc">You reached your target score with 18 seconds to spare. Try a higher target next round?</div>
          <div className="stars">
            {[1, 2, 3].map(i => (
              <div key={i} className={`star ${i <= stars ? 'on' : ''}`}>★</div>
            ))}
          </div>
          <div className="actions">
            <button className="tab-btn red lg">↻ Play again</button>
            <button className="tab-btn cream">🏠 Home</button>
          </div>
        </div>
        <div className="r-right">
          <div className="r-score-card">
            <div className="lbl">Final score</div>
            <div className="big">{finalScore}</div>
            <div className="meta">★ New high score · +3 from last time</div>
          </div>
          <div className="r-stat-row">
            <div className="r-stat"><div className="v">{time}s</div><div className="l">Time</div></div>
            <div className="r-stat"><div className="v">{streak}</div><div className="l">Best streak</div></div>
            <div className="r-stat"><div className="v">{accuracy}%</div><div className="l">Accuracy</div></div>
          </div>
        </div>
      </div>
    </div>
  )
}
```

- [ ] **Step 2: Create result screen styles**

```css
/* src/styles/screens/result.css */

.r-bg {
  background: var(--yellow);
  position: relative;
  padding: 0;
}

.r-bg .confetti {
  position: absolute;
  inset: 0;
  pointer-events: none;
}

.r-bg .confetti span {
  position: absolute;
}

.r-bg .c1 { left: 8%; top: 10%; width: 18px; height: 18px; background: var(--red); border-radius: 50%; }
.r-bg .c2 { left: 92%; top: 14%; width: 16px; height: 16px; background: var(--blue); }
.r-bg .c3 { left: 5%; top: 60%; width: 14px; height: 14px; background: var(--blue); border-radius: 50%; }
.r-bg .c4 { left: 95%; top: 55%; width: 18px; height: 18px; background: var(--red); transform: rotate(45deg); }
.r-bg .c5 { left: 18%; top: 86%; width: 14px; height: 14px; background: var(--ink); border-radius: 50%; }
.r-bg .c6 { left: 80%; top: 86%; width: 14px; height: 14px; background: var(--ink); transform: rotate(45deg); }
.r-bg .c7 { left: 50%; top: 6%; width: 12px; height: 12px; background: var(--blue); border-radius: 50%; }

.r-grid {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 0;
}

.r-left {
  padding: 60px;
  display: flex;
  flex-direction: column;
  gap: 14px;
  justify-content: center;
}

.r-left .crumb {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  opacity: 0.7;
}

.r-left h1 {
  font-size: 88px;
  font-weight: 700;
  margin: 0;
  line-height: 0.95;
  letter-spacing: -0.02em;
}

.r-left .desc {
  font-family: 'Space Grotesk';
  font-size: 18px;
  max-width: 440px;
  line-height: 1.5;
}

.r-left .stars {
  display: flex;
  gap: 14px;
  margin-top: 12px;
}

.r-left .star {
  width: 64px;
  height: 64px;
  border-radius: 50%;
  background: var(--cream);
  border: 3px solid var(--ink);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 32px;
  box-shadow: 0 4px 0 var(--ink);
}

.r-left .star.on {
  background: var(--red);
  color: var(--cream);
}

.r-left .actions {
  display: flex;
  gap: 12px;
  margin-top: 18px;
}

.r-right {
  padding: 60px;
  display: flex;
  flex-direction: column;
  gap: 16px;
  justify-content: center;
}

.r-score-card {
  background: var(--cream);
  border: 3px solid var(--ink);
  border-radius: 28px;
  box-shadow: 0 8px 0 var(--ink);
  padding: 32px;
  text-align: center;
}

.r-score-card .lbl {
  font-family: 'Space Grotesk';
  font-weight: 700;
  font-size: 12px;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  opacity: 0.5;
}

.r-score-card .big {
  font-size: 140px;
  font-weight: 700;
  line-height: 1;
  margin: 6px 0;
  color: var(--blue);
  letter-spacing: -0.03em;
}

.r-score-card .meta {
  font-family: 'Space Grotesk';
  font-weight: 600;
  font-size: 14px;
  opacity: 0.7;
}

.r-stat-row {
  display: flex;
  gap: 12px;
}

.r-stat {
  flex: 1;
  background: var(--cream);
  border: 2.5px solid var(--ink);
  border-radius: 18px;
  padding: 14px 16px;
  display: flex;
  flex-direction: column;
}

.r-stat .v {
  font-size: 28px;
  font-weight: 700;
  line-height: 1;
}

.r-stat .l {
  font-family: 'Space Grotesk';
  font-size: 11px;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  opacity: 0.6;
  margin-top: 4px;
}
```

- [ ] **Step 3: Commit**

```bash
git add src/components/screens/Result.jsx src/styles/screens/result.css
git commit -m "feat: implement result screen with score, stars, and stats"
```

---

## Task 9: Create Settings Screen

**Files:**
- Create: `src/components/screens/Settings.jsx`
- Create: `src/styles/screens/settings.css`

- [ ] **Step 1: Create Settings screen component**

```jsx
// src/components/screens/Settings.jsx
import { useState } from 'react'
import { Sidebar } from '../Sidebar'
import { Slider } from '../shared/Slider'
import { Switch } from '../shared/Switch'

export function Settings() {
  const [musicVol, setMusicVol] = useState(60)
  const [sfxVol, setSfxVol] = useState(80)
  const [calmMode, setCalmMode] = useState(true)
  const [bgMusic, setBgMusic] = useState(true)
  const [voicePrompts, setVoicePrompts] = useState(true)
  const [haptics, setHaptics] = useState(false)
  const [locked, setLocked] = useState(true)
  const [lang, setLang] = useState('EN')

  return (
    <div className="tab-screen">
      <Sidebar active="settings" />
      <div className="tab-content">
        <div className="top">
          <div>
            <div className="crumb">Parent · Locked</div>
            <h1>Settings</h1>
          </div>
          <div className="actions">
            <div className="tab-pill">← Back</div>
          </div>
        </div>
        <div className="gate-card">
          <div className="lock">🔒</div>
          <div className="t">
            <div className="h">Hold to unlock parent settings</div>
            <div className="d">Press & hold for 3 seconds — keeps little hands away</div>
          </div>
          <div className="ring"></div>
        </div>
        {!locked && (
          <div className="s-grid">
            <div className="s-card">
              <div className="h"><h3>Audio</h3><span style={{ fontFamily: 'Space Grotesk', fontSize: 12, opacity: 0.5 }}>2 controls</span></div>
              <div className="set-list">
                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'stretch', gap: 8 }}>
                  <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'baseline' }}>
                    <div>
                      <div className="n">Music volume</div>
                      <div className="d">Calm background loop during play</div>
                    </div>
                    <div style={{ fontFamily: 'Space Grotesk', fontWeight: 700, fontSize: 16, color: 'var(--blue)' }}>{musicVol}%</div>
                  </div>
                </div>
              </div>
            </div>
            <div className="s-card">
              <div className="h"><h3>Comfort</h3></div>
              <div className="set-list">
                <div className="row">
                  <div>
                    <div className="n">Calm Mode</div>
                    <div className="d">Reduces animations and visual effects</div>
                  </div>
                  <Switch checked={calmMode} onChange={setCalmMode} />
                </div>
                <div className="row">
                  <div>
                    <div className="n">Background music</div>
                    <div className="d">Play during games</div>
                  </div>
                  <Switch checked={bgMusic} onChange={setBgMusic} />
                </div>
              </div>
            </div>
            <div className="s-card">
              <div className="h"><h3>Language</h3></div>
              <div className="set-list">
                <div className="row">
                  <div>
                    <div className="n">App language</div>
                    <div className="d">Switch between English & Vietnamese</div>
                  </div>
                  <div style={{ display: 'flex', gap: 4, padding: 4, background: 'var(--soft)', borderRadius: 999, border: '2px solid var(--ink)', fontFamily: 'Space Grotesk', fontWeight: 700, fontSize: 12 }}>
                    <div style={{ padding: '6px 14px', borderRadius: 999, background: lang === 'EN' ? 'var(--ink)' : 'transparent', color: lang === 'EN' ? 'var(--cream)' : 'var(--ink)' }}>EN</div>
                    <div style={{ padding: '6px 14px', borderRadius: 999, background: lang === 'VI' ? 'var(--ink)' : 'transparent', color: lang === 'VI' ? 'var(--cream)' : 'var(--ink)' }}>VI</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
```

- [ ] **Step 2: Create settings screen styles**

```css
/* src/styles/screens/settings.css */

.s-grid {
  flex: 1;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
}

.s-card {
  border-radius: 28px;
  border: 3px solid var(--ink);
  background: var(--cream);
  box-shadow: 0 6px 0 var(--ink);
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.s-card .h {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.s-card .h h3 {
  font-size: 22px;
  font-weight: 700;
  margin: 0;
}

.gate-card {
  background: var(--ink);
  color: var(--cream);
  border-radius: 28px;
  padding: 24px;
  display: flex;
  align-items: center;
  gap: 18px;
  margin-bottom: 24px;
}

.gate-card .lock {
  width: 72px;
  height: 72px;
  border-radius: 22px;
  background: var(--yellow);
  color: var(--ink);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 30px;
  border: 3px solid var(--cream);
}

.gate-card .t .h {
  font-weight: 700;
  font-size: 18px;
}

.gate-card .t .d {
  font-family: 'Space Grotesk';
  font-size: 13px;
  opacity: 0.7;
}

.gate-card .ring {
  margin-left: auto;
  width: 64px;
  height: 64px;
  border-radius: 50%;
  background: conic-gradient(var(--green) 0 65%, rgba(255,255,255,0.15) 0);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.gate-card .ring::after {
  content: '';
  width: 46px;
  height: 46px;
  border-radius: 50%;
  background: var(--ink);
}

.set-list .row {
  padding: 14px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-top: 1.5px dashed rgba(26,26,46,0.18);
}

.set-list .row:first-child {
  border-top: none;
  padding-top: 4px;
}

.set-list .row .n {
  font-size: 16px;
  font-weight: 600;
}

.set-list .row .d {
  font-family: 'Space Grotesk';
  font-size: 12px;
  opacity: 0.6;
  margin-top: 2px;
}

.danger-btn {
  height: 52px;
  border-radius: 16px;
  background: var(--cream);
  border: 2.5px dashed var(--red);
  color: var(--red);
  font-family: 'Fredoka';
  font-weight: 700;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  cursor: pointer;
}
```

- [ ] **Step 3: Commit**

```bash
git add src/components/screens/Settings.jsx src/styles/screens/settings.css
git commit -m "feat: implement settings screen with parent gate and audio/comfort controls"
```

---

## Task 10: Create Language Loading Screen

**Files:**
- Create: `src/components/screens/LanguageLoading.jsx`
- Create: `src/styles/screens/language.css`

- [ ] **Step 1: Create Language Loading screen component**

```jsx
// src/components/screens/LanguageLoading.jsx
export function LanguageLoading() {
  return (
    <div className="tab-screen lang">
      <div className="anim">
        <div className="orbit"></div>
        <div className="orbit r2"></div>
        <div className="planet p1"></div>
        <div className="planet p2"></div>
        <div className="planet p3"></div>
        <div className="planet p4"></div>
        <div className="flag-card">
          <div className="face">EN</div>
          <div className="face back">VI</div>
        </div>
      </div>
      <div>
        <div className="label" style={{ marginBottom: 6 }}>Switching language…</div>
        <div className="sub" style={{ textAlign: 'center' }}>EN &nbsp;→&nbsp; Tiếng Việt</div>
      </div>
      <div className="progress-bar"><div className="pf"></div></div>
      <div className="dots-row">
        <div className="d"></div>
        <div className="d"></div>
        <div className="d"></div>
      </div>
    </div>
  )
}
```

- [ ] **Step 2: Create language loading styles**

```css
/* src/styles/screens/language.css */

.lang {
  background: var(--cream);
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0;
  gap: 32px;
}

.lang .anim {
  position: relative;
  width: 320px;
  height: 320px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.lang .orbit {
  position: absolute;
  inset: 0;
  border: 4px dashed var(--ink);
  border-radius: 50%;
  opacity: 0.18;
  animation: rotateOrbit 6s linear infinite;
}

.lang .orbit.r2 {
  inset: 30px;
  border-style: dotted;
  border-color: var(--blue);
  opacity: 0.3;
  animation: rotateOrbit 4s linear infinite reverse;
}

.lang .flag-card {
  width: 200px;
  height: 200px;
  border-radius: 32px;
  background: var(--yellow);
  border: 4px solid var(--ink);
  box-shadow: 0 8px 0 var(--ink);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 100px;
  animation: flipCard 1.6s ease-in-out infinite;
  perspective: 600px;
  position: relative;
}

.lang .flag-card .face {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  backface-visibility: hidden;
  font-family: 'Fredoka';
  font-weight: 700;
  font-size: 64px;
  color: var(--ink);
}

.lang .flag-card .face.back {
  transform: rotateY(180deg);
  background: var(--red);
  color: var(--cream);
  border-radius: 28px;
}

.lang .planet {
  position: absolute;
  width: 28px;
  height: 28px;
  border-radius: 50%;
}

.lang .planet.p1 { background: var(--red); top: -8px; left: 50%; transform: translateX(-50%); }
.lang .planet.p2 { background: var(--blue); top: 50%; right: -8px; transform: translateY(-50%); }
.lang .planet.p3 { background: var(--green); bottom: -8px; left: 50%; transform: translateX(-50%); }
.lang .planet.p4 { background: var(--ink); top: 50%; left: -8px; transform: translateY(-50%); }

.lang .label {
  font-size: 36px;
  font-weight: 700;
  text-align: center;
}

.lang .sub {
  font-family: 'Space Grotesk';
  font-weight: 600;
  font-size: 16px;
  letter-spacing: 0.16em;
  text-transform: uppercase;
  color: var(--blue);
}

.lang .progress-bar {
  width: 360px;
  height: 14px;
  border-radius: 999px;
  background: var(--soft);
  border: 2.5px solid var(--ink);
  overflow: hidden;
  position: relative;
}

.lang .progress-bar .pf {
  height: 100%;
  background: var(--blue);
  border-right: 2.5px solid var(--ink);
  animation: loadFill 1.6s ease-in-out infinite;
}

.lang .dots-row {
  display: flex;
  gap: 10px;
}

.lang .dots-row .d {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background: var(--ink);
  opacity: 0.3;
  animation: bounceDot 1s ease-in-out infinite;
}

.lang .dots-row .d:nth-child(1) { animation-delay: 0s; }
.lang .dots-row .d:nth-child(2) { animation-delay: 0.15s; }
.lang .dots-row .d:nth-child(3) { animation-delay: 0.3s; }
```

- [ ] **Step 3: Commit**

```bash
git add src/components/screens/LanguageLoading.jsx src/styles/screens/language.css
git commit -m "feat: implement language loading screen with 3D flip card animation"
```

---

## Task 11: Create Main App Component & Routing

**Files:**
- Create: `src/App.jsx`
- Create: `src/hooks/useScreenState.js`

- [ ] **Step 1: Create screen state hook**

```javascript
// src/hooks/useScreenState.js
import { useState } from 'react'

export function useScreenState() {
  const [currentScreen, setCurrentScreen] = useState('home')
  
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
```

- [ ] **Step 2: Create App component**

```jsx
// src/App.jsx
import { useScreenState } from './hooks/useScreenState'
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
```

- [ ] **Step 3: Update styles imports**

```css
/* src/styles/index.css - Add imports at top */
@import url('./animations.css');
@import url('./components.css');
@import url('./screens/home.css');
@import url('./screens/setup.css');
@import url('./screens/countdown.css');
@import url('./screens/game.css');
@import url('./screens/result.css');
@import url('./screens/settings.css');
@import url('./screens/language.css');
```

- [ ] **Step 4: Test app runs**

```bash
npm run dev
```

Expected: Vite dev server starts on http://localhost:3000 and app displays without errors

- [ ] **Step 5: Commit**

```bash
git add src/App.jsx src/hooks/useScreenState.js src/styles/index.css
git commit -m "feat: create main app component with screen routing"
```

---

## Task 12: Testing & Verification

**Files:**
- Test: All screens visually match design file

- [ ] **Step 1: Visual verification checklist**

Open http://localhost:3000 in browser at 1280×800 resolution and verify:

- [ ] **Home screen:**
  - [ ] Sidebar displays with KT logo, navigation, language toggle
  - [ ] Two mode cards (Shapes/Animals) with correct colors (red/blue)
  - [ ] Recent games card shows in yellow
  - [ ] All text renders in correct fonts (Fredoka for headings, Space Grotesk for metadata)

- [ ] **Setup screen:**
  - [ ] Age selector with ± buttons works
  - [ ] Three sliders render correctly
  - [ ] Switch components toggle on/off
  - [ ] Preview shape shows in dashed box

- [ ] **Countdown screen:**
  - [ ] Blue background with rotating dashed rings
  - [ ] Yellow countdown circle displays current number
  - [ ] Dots progress indicator works
  - [ ] Rings animate (outer + inner in opposite directions)

- [ ] **Game screen:**
  - [ ] HUD shows score, progress bar, time, streak
  - [ ] Game canvas has subtle grid background
  - [ ] Colored game items render at correct positions
  - [ ] Pause button works and shows pause overlay with modal

- [ ] **Result screen:**
  - [ ] Yellow background with confetti sprites positioned correctly
  - [ ] Split layout: left (results) and right (score card)
  - [ ] Star rating displays (1-3 stars filled)
  - [ ] Stats cards show time, streak, accuracy

- [ ] **Settings screen:**
  - [ ] Lock gate card displays before unlock
  - [ ] Settings cards visible with proper styling
  - [ ] Switch components work
  - [ ] Language toggle shows EN/VI options

- [ ] **Language Loading screen:**
  - [ ] Orbiting rings rotate (one forward, one backward)
  - [ ] Card flips between EN and VI
  - [ ] Progress bar fills
  - [ ] Bouncing dots animate with stagger delay
  - [ ] Planets positioned at cardinal points

- [ ] **Step 2: Responsive layout check**

Resize browser to tablet 4:3 aspect ratio (1280×800) and verify:
- [ ] No horizontal scrolling
- [ ] No content cutoff
- [ ] All interactive elements remain clickable

- [ ] **Step 3: Animation check**

Verify animations are smooth:
- [ ] Countdown number updates every 1s
- [ ] Language card flip is 3D smooth
- [ ] Bouncing dots have correct timing
- [ ] No jank or stuttering

- [ ] **Step 4: Commit**

```bash
git add -A
git commit -m "test: visual verification of all screens against design spec"
```

---

## Summary

This plan implements all 7 tablet screens from the Kid Taps design:

1. ✅ **Home** — Mode selection with sidebar
2. ✅ **Setup** — Difficulty configuration for teachers
3. ✅ **Countdown** — Pre-game countdown with animations
4. ✅ **Game** — Active gameplay with score tracking
5. ✅ **Result** — Post-game results and stats
6. ✅ **Settings** — Parent-locked settings panel
7. ✅ **Language Loading** — Animated language switcher

All screens use the Kid Taps design system (colors, fonts, spacing) and include CSS animations for smooth, engaging UX suitable for children.

---

**Next Step:** Choose execution approach:

**1. Subagent-Driven (recommended)** — I dispatch a fresh subagent per task, you review between tasks for fast iteration

**2. Inline Execution** — Execute tasks in this session using executing-plans, batch execution with checkpoints

Which approach do you prefer?
