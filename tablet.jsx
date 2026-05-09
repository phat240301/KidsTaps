/* global React */

/* ──────────── Sidebar (shared) ──────────── */
function Sidebar({ active = 'home', lang = 'EN' }) {
  const items = [
    { id: 'home',     ic: '🏠', label: 'Home' },
    { id: 'play',     ic: '▶',  label: 'Play' },
    { id: 'scores',   ic: '⭐', label: 'High Scores' },
    { id: 'settings', ic: '⚙',  label: 'Settings' },
    { id: 'about',    ic: '?',  label: 'About' },
  ];
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
          <div className={`opt ${lang === 'EN' ? 'on' : ''}`}>EN</div>
          <div className={`opt ${lang === 'VI' ? 'on' : ''}`}>VI</div>
        </div>
        <div className="teacher-pill">👩‍🏫 Teacher mode</div>
      </div>
    </div>
  );
}

/* ──────────── 01 — HOME ──────────── */
function THome() {
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
  );
}

/* ──────────── 02 — TEACHER SETUP ──────────── */
function TSetup() {
  const Slider = ({ name, val, hint, fill }) => (
    <div className="slider-row">
      <div className="label-row">
        <div className="name">{name}</div>
        <div className="val">{val}</div>
      </div>
      <div className="track">
        <div className="fill" style={{ width: `${fill}%` }}></div>
        <div className="knob" style={{ left: `${fill}%` }}></div>
      </div>
      <div className="hint">{hint}</div>
    </div>
  );
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
            <div className="tab-btn blue" style={{ height: 52, fontSize: 18 }}>✓ Start game</div>
          </div>
        </div>
        <div className="set-grid">
          <div className="set-card">
            <div>
              <div className="ttl">Difficulty</div>
              <h3>Tune for the child</h3>
            </div>
            <div className="age-block">
              <div className="num">7</div>
              <div className="col">
                <div className="l">Child's age</div>
                <div className="label">years old</div>
              </div>
              <div className="stepper">
                <div className="step-btn">−</div>
                <div className="step-btn">+</div>
              </div>
            </div>
            <div className="auto-btn">✦ Auto-suggest difficulty</div>
            <Slider name="Spawn speed" val="3.4 s" hint="How often a new item appears" fill={45} />
            <Slider name="Hold time" val="3.9 s" hint="How long an item stays visible" fill={55} />
            <Slider name="Target score" val="17 pts" hint="Round ends when reached" fill={42} />
          </div>
          <div className="set-card">
            <div>
              <div className="ttl">Mode · Shapes</div>
              <h3>Preview &amp; comfort</h3>
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
                <div className="kt-switch on"></div>
              </div>
              <div className="opt-card">
                <div className="col">
                  <div className="h">Calm Mode</div>
                  <div className="d">Reduce motion</div>
                </div>
                <div className="kt-switch"></div>
              </div>
            </div>
            <div className="opt-row">
              <div className="opt-card">
                <div className="col">
                  <div className="h">Voice prompts</div>
                  <div className="d">Name each item</div>
                </div>
                <div className="kt-switch on"></div>
              </div>
              <div className="opt-card">
                <div className="col">
                  <div className="h">Haptics</div>
                  <div className="d">Vibrate on tap</div>
                </div>
                <div className="kt-switch on"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

/* ──────────── 03 — COUNTDOWN ──────────── */
function TCountdown() {
  return (
    <div className="tab-screen cd">
      <div className="ring-bg r2"></div>
      <div className="ring-bg"></div>
      <div className="quit-btn">×</div>
      <div className="center">
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
function TGame({ paused = false }) {
  return (
    <div className="tab-screen game-bg">
      <div className="game-hud">
        <div className="quit">✕</div>
        <div className="pill">★ 12 / 17</div>
        <div className="progress">
          <div className="fill" style={{ width: '70%' }}></div>
          <div className="lbl">Round progress</div>
        </div>
        <div className="pill">⏱ 0:42</div>
        <div className="pill">🔥 5</div>
        <div className="quit">⏸</div>
      </div>
      <div className="game-canvas">
        <div className="grid"></div>
        <div className="game-item r c" style={{ top: 40, left: 80 }}>●</div>
        <div className="game-item" style={{ top: 80, right: 120, transform: 'rotate(8deg)' }}>★</div>
        <div className="game-item b" style={{ top: 240, left: 280 }}>■</div>
        <div className="game-ripple" style={{ top: 232, left: 272, width: 146, height: 146 }}></div>
        <div className="score-burst" style={{ top: 200, left: 320 }}>+1</div>
        <div className="game-item g" style={{ top: 220, right: 220 }}>♥</div>
        <div className="game-item" style={{ bottom: 80, left: 200, width: 110, height: 110, fontSize: 60 }}>▲</div>
        <div className="game-item r c" style={{ bottom: 50, right: 80, width: 110, height: 110, fontSize: 60 }}>●</div>
        <div className="game-item b" style={{ bottom: 130, left: '46%', width: 110, height: 110, fontSize: 60, borderRadius: 30 }}>■</div>

        {paused && (
          <div className="pause-overlay">
            <div className="pause-card">
              <h3>Paused</h3>
              <div style={{ fontFamily: 'Space Grotesk', fontSize: 14, opacity: 0.65 }}>Take a breath. Tap resume when ready.</div>
              <div className="row">
                <div className="tab-btn cream" style={{ flex: 1 }}>🏠 Home</div>
                <div className="tab-btn blue" style={{ flex: 1 }}>▶ Resume</div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

/* ──────────── 05 — RESULT ──────────── */
function TResult() {
  return (
    <div className="tab-screen r-bg">
      <div className="confetti">
        <span className="c1"></span><span className="c2"></span><span className="c3"></span>
        <span className="c4"></span><span className="c5"></span><span className="c6"></span><span className="c7"></span>
      </div>
      <div className="r-grid">
        <div className="r-left">
          <div className="crumb">Round complete</div>
          <h1>Great job!</h1>
          <div className="desc">You reached your target score with 18 seconds to spare. Try a higher target next round?</div>
          <div className="stars">
            <div className="star on">★</div>
            <div className="star on">★</div>
            <div className="star">★</div>
          </div>
          <div className="actions">
            <div className="tab-btn red lg">↻ Play again</div>
            <div className="tab-btn cream">🏠 Home</div>
          </div>
        </div>
        <div className="r-right">
          <div className="r-score-card">
            <div className="lbl">Final score</div>
            <div className="big">17</div>
            <div className="meta">★ New high score · +3 from last time</div>
          </div>
          <div className="r-stat-row">
            <div className="r-stat"><div className="v">82s</div><div className="l">Time</div></div>
            <div className="r-stat"><div className="v">5</div><div className="l">Best streak</div></div>
            <div className="r-stat"><div className="v">94%</div><div className="l">Accuracy</div></div>
          </div>
        </div>
      </div>
    </div>
  );
}

/* ──────────── 06 — SETTINGS ──────────── */
function TSettings() {
  const VolSlider = ({ name, desc, fill }) => (
    <div className="row" style={{ flexDirection: 'column', alignItems: 'stretch', gap: 8 }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'baseline' }}>
        <div>
          <div className="n">{name}</div>
          <div className="d">{desc}</div>
        </div>
        <div style={{ fontFamily: 'Space Grotesk', fontWeight: 700, fontSize: 16, color: 'var(--blue)' }}>{fill}%</div>
      </div>
      <div className="track">
        <div className="fill" style={{ width: `${fill}%` }}></div>
        <div className="knob" style={{ left: `${fill}%` }}></div>
      </div>
    </div>
  );
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
            <div className="d">Press &amp; hold for 3 seconds — keeps little hands away</div>
          </div>
          <div className="ring"></div>
        </div>
        <div className="s-grid">
          <div className="s-card">
            <div className="h"><h3>Audio</h3><span style={{ fontFamily: 'Space Grotesk', fontSize: 12, opacity: 0.5 }}>2 controls</span></div>
            <div className="set-list">
              <VolSlider name="Music volume" desc="Calm background loop during play" fill={60} />
              <VolSlider name="Sound effects" desc="Tap sounds, fanfares, countdown" fill={80} />
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
                <div className="kt-switch on"></div>
              </div>
              <div className="row">
                <div>
                  <div className="n">Background music</div>
                  <div className="d">Play during games</div>
                </div>
                <div className="kt-switch on"></div>
              </div>
              <div className="row">
                <div>
                  <div className="n">Voice prompts</div>
                  <div className="d">Name each item aloud</div>
                </div>
                <div className="kt-switch on"></div>
              </div>
              <div className="row">
                <div>
                  <div className="n">Haptics</div>
                  <div className="d">Soft vibration on correct tap</div>
                </div>
                <div className="kt-switch"></div>
              </div>
            </div>
          </div>
          <div className="s-card">
            <div className="h"><h3>Language</h3></div>
            <div className="set-list">
              <div className="row">
                <div>
                  <div className="n">App language</div>
                  <div className="d">Switch between English &amp; Vietnamese</div>
                </div>
                <div style={{ display: 'flex', gap: 4, padding: 4, background: 'var(--soft)', borderRadius: 999, border: '2px solid var(--ink)', fontFamily: 'Space Grotesk', fontWeight: 700, fontSize: 12 }}>
                  <div style={{ padding: '6px 14px', borderRadius: 999, background: 'var(--ink)', color: 'var(--cream)' }}>EN</div>
                  <div style={{ padding: '6px 14px', borderRadius: 999, opacity: 0.6 }}>VI</div>
                </div>
              </div>
            </div>
          </div>
          <div className="s-card">
            <div className="h"><h3>Data</h3></div>
            <div className="set-list">
              <div className="row">
                <div>
                  <div className="n">High scores</div>
                  <div className="d">Shapes: 24 · Animals: 18</div>
                </div>
                <div className="tab-pill">View</div>
              </div>
            </div>
            <div className="danger-btn">⟲ Reset high scores</div>
            <div className="danger-btn">🗑 Clear all settings</div>
          </div>
        </div>
      </div>
    </div>
  );
}

/* ──────────── 07 — LANGUAGE LOADING (animated) ──────────── */
function TLangLoad() {
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
  );
}

Object.assign(window, {
  THome, TSetup, TCountdown, TGame, TResult, TSettings, TLangLoad
});
