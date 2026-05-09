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
