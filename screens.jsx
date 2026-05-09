/* global React */

/* ──────────── 01 — HOME ──────────── */
function HomeScreen() {
  return (
    <div className="kt-screen s-home">
      <div className="kt-topbar">
        <div className="kt-icon-btn flat">⚙️</div>
        <div className="kt-pill">🔥 Streak · 3</div>
      </div>
      <div className="hero">
        <div className="logo-mark">KT</div>
        <h1>Kid Taps</h1>
        <div className="sub">Pick a game to play</div>
      </div>
      <div className="modes">
        <div className="mode-card shapes">
          <div className="glyph">🔶</div>
          <div className="label">Shapes</div>
          <div className="hi">High · 24</div>
        </div>
        <div className="mode-card animals">
          <div className="glyph">🐰</div>
          <div className="label">Animals</div>
          <div className="hi">High · 18</div>
        </div>
      </div>
      <div className="lang-row">
        <div className="opt on">EN</div>
        <div className="opt">VI</div>
      </div>
      <div style={{ flex: 1 }} />
      <div style={{ padding: '0 20px 20px' }}>
        <div className="kt-btn red">▶ Quick Play</div>
      </div>
    </div>
  );
}

/* ──────────── 02 — SETUP (Teacher) ──────────── */
function SetupScreen() {
  const Slider = ({ name, val, fillPct, knobPct }) => (
    <div className="slider-card">
      <div className="row">
        <div className="name">{name}</div>
        <div className="val">{val}</div>
      </div>
      <div className="track">
        <div className="fill" style={{ width: `${fillPct}%` }}></div>
        <div className="knob" style={{ left: `${knobPct}%` }}></div>
      </div>
    </div>
  );
  return (
    <div className="kt-screen s-setup">
      <div className="kt-topbar">
        <div className="kt-icon-btn">←</div>
        <div className="kt-pill">Teacher</div>
        <div style={{ width: 48 }} />
      </div>
      <div className="body">
        <div>
          <div className="step-tag">Step 1 of 2</div>
          <h1>Set up the game</h1>
        </div>

        <div className="age-card">
          <div className="label">Child's Age</div>
          <div className="num-row">
            <div className="num">7</div>
            <div className="unit">years</div>
            <div className="step-btns">
              <div className="step-btn">−</div>
              <div className="step-btn">+</div>
            </div>
          </div>
          <div className="auto-btn">✦ Auto-suggest difficulty</div>
        </div>

        <Slider name="Spawn speed" val="3.4 s" fillPct={45} knobPct={45} />
        <Slider name="Hold time" val="3.9 s" fillPct={50} knobPct={50} />
        <Slider name="Target score" val="17 pts" fillPct={42} knobPct={42} />

        <div className="toggles">
          <div className="toggle-card">
            <div className="h">Music <div className="kt-switch on"></div></div>
            <div className="d">Calm loop</div>
          </div>
          <div className="toggle-card">
            <div className="h">Calm <div className="kt-switch"></div></div>
            <div className="d">Reduce motion</div>
          </div>
        </div>

        <div style={{ flex: 1 }} />
        <div className="kt-btn blue">✓ Start game</div>
      </div>
    </div>
  );
}

/* ──────────── 03 — COUNTDOWN ──────────── */
function CountdownScreen() {
  return (
    <div className="kt-screen s-count">
      <div className="kt-topbar">
        <div className="kt-icon-btn flat" style={{ color: '#FFF8EC' }}>×</div>
        <div style={{ width: 48 }} />
      </div>
      <div className="center">
        <div className="ring-bg"></div>
        <div className="ready">Get ready</div>
        <div className="num">3</div>
        <div className="dots">
          <div className="dot on"></div>
          <div className="dot"></div>
          <div className="dot"></div>
        </div>
      </div>
    </div>
  );
}

/* ──────────── 04 — GAME ──────────── */
function GameScreen() {
  return (
    <div className="kt-screen s-game">
      <div className="hud">
        <div className="pill">★ 12 / 17</div>
        <div className="progress"><div className="fill" style={{ width: '70%' }}></div></div>
        <div className="pill">⏱ 0:42</div>
      </div>
      <div className="canvas">
        <div className="grid"></div>
        <div className="item r c" style={{ top: 30, left: 24 }}>●</div>
        <div className="item" style={{ top: 110, right: 32, transform: 'rotate(8deg)' }}>★</div>
        <div className="item b" style={{ top: 240, left: 60 }}>■</div>
        <div className="ripple" style={{ top: 238, left: 58 }}></div>
        <div className="item" style={{ top: 360, right: 24, background: 'var(--green)', color: 'var(--cream)' }}>♥</div>
        <div className="item r c" style={{ bottom: 30, left: 90, width: 90, height: 90, fontSize: 50 }}>●</div>
      </div>
      <div className="footer">
        <div className="pause">⏸</div>
        <div className="stat-pill">
          <div className="stat"><div className="v">12</div><div className="l">Score</div></div>
          <div className="stat"><div className="v">5</div><div className="l">Streak</div></div>
          <div className="stat"><div className="v">17</div><div className="l">Goal</div></div>
        </div>
      </div>
    </div>
  );
}

/* ──────────── 05 — RESULT ──────────── */
function ResultScreen() {
  return (
    <div className="kt-screen s-result">
      <div className="confetti">
        <span className="c1"></span><span className="c2"></span><span className="c3"></span>
        <span className="c4"></span><span className="c5"></span><span className="c6"></span>
      </div>
      <div className="kt-topbar" style={{ position: 'relative', zIndex: 1 }}>
        <div className="kt-icon-btn">✕</div>
        <div className="kt-pill">Round 1</div>
        <div style={{ width: 48 }} />
      </div>
      <div className="body">
        <h1>Great job!</h1>
        <div className="sub">You finished the round 🎉</div>
        <div className="stars">
          <div className="star on">★</div>
          <div className="star on">★</div>
          <div className="star">★</div>
        </div>
        <div className="score-card">
          <div className="label">Final Score</div>
          <div className="big">17</div>
          <div className="small">New high score · +3 from last time</div>
        </div>
        <div className="actions">
          <div className="kt-btn red">↻ Play again</div>
          <div className="kt-btn cream">🏠 Home</div>
        </div>
      </div>
    </div>
  );
}

/* ──────────── 06 — SETTINGS (Parent) ──────────── */
function SettingsScreen() {
  const VolRow = ({ name, val, fill }) => (
    <div className="row-card">
      <div className="top">
        <div className="name">{name}</div>
        <div className="val">{val}%</div>
      </div>
      <div className="track" style={{ marginTop: 0 }}>
        <div className="fill" style={{ width: `${fill}%` }}></div>
        <div className="knob" style={{ left: `${fill}%` }}></div>
      </div>
    </div>
  );
  return (
    <div className="kt-screen s-set">
      <div className="kt-topbar">
        <div className="kt-icon-btn">←</div>
        <h1>Settings</h1>
        <div style={{ width: 48 }} />
      </div>
      <div className="body">
        <div className="gate">
          <div className="lock">🔒</div>
          <div className="t">
            <div className="h">Hold to unlock</div>
            <div className="d">Press &amp; hold for 3 seconds</div>
          </div>
          <div className="holdring"></div>
        </div>

        <div className="group-title">Audio</div>
        <VolRow name="Music volume" val={60} fill={60} />
        <VolRow name="Sound effects" val={80} fill={80} />

        <div className="group-title">Comfort</div>
        <div className="row-card">
          <div className="top">
            <div className="name">Calm Mode</div>
            <div className="kt-switch on"></div>
          </div>
          <div style={{ fontFamily: 'Space Grotesk', fontSize: 12, opacity: 0.6 }}>
            Reduces animations and effects for sensory comfort
          </div>
        </div>
        <div className="row-card">
          <div className="top">
            <div className="name">Background music</div>
            <div className="kt-switch on"></div>
          </div>
        </div>

        <div className="group-title">Data</div>
        <div className="reset-btn">⟲ Reset high scores</div>
      </div>
    </div>
  );
}

Object.assign(window, {
  HomeScreen, SetupScreen, CountdownScreen, GameScreen, ResultScreen, SettingsScreen
});
