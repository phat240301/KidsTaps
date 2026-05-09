/* global React */

function LogoCard({ concept, name, desc, tags, hero, icon }) {
  return (
    <div className={`logo-board ${concept}`}>
      <div className="hero">{hero}</div>
      <div className="icon-cell">
        <div className="app-icon icon">{icon}</div>
      </div>
      <div className="meta">
        <div className="name">{name}</div>
        <div className="desc">{desc}</div>
        <div className="tags">
          {tags.map((t, i) => <span className="tag" key={i}>{t}</span>)}
        </div>
      </div>
    </div>
  );
}

/* ─────────── Concept 1 — Tap Target ─────────── */
function LogoC1() {
  const Target = () => (
    <div className="target">
      <div className="ring r1"></div>
      <div className="ring r2"></div>
      <div className="ring r3"></div>
    </div>
  );
  return (
    <LogoCard
      concept="c1"
      name="Tap Target"
      desc="The 'O' in TAPS becomes a bullseye — a tap is a target. Reads as both a wordmark and a play button."
      tags={['Wordmark', 'Iconic']}
      hero={
        <div className="wordmark">
          KIDTAP<Target />S
        </div>
      }
      icon={<Target />}
    />
  );
}

/* ─────────── Concept 2 — Block Stack ─────────── */
function LogoC2() {
  const Stack = () => (
    <div className="stack-mini">
      <div className="block-mini k">K</div>
      <div className="block-mini t">T</div>
    </div>
  );
  return (
    <LogoCard
      concept="c2"
      name="Block Stack"
      desc="KT monogram as chunky tilted blocks — toy-like, tappable. Works tiny on a phone home screen."
      tags={['Monogram', 'Playful']}
      hero={
        <div className="stack">
          <div className="block k">K<span className="dot"></span></div>
          <div className="block t">T</div>
        </div>
      }
      icon={<Stack />}
    />
  );
}

/* ─────────── Concept 3 — Speech / Tap Bubble ─────────── */
function LogoC3() {
  return (
    <LogoCard
      concept="c3"
      name="Tap Bubble"
      desc="A friendly bubble that begs to be pressed. The red dot reads as a notification — kids love a badge."
      tags={['Mark', 'Friendly']}
      hero={<div className="bubble">KT</div>}
      icon={<div className="bubble-mini">KT</div>}
    />
  );
}

/* ─────────── Concept 4 — Pixel Burst ─────────── */
function LogoC4() {
  // 11 cols x 7 rows pixel "KT" mark
  // K rows: each row 0..6, K occupies cols 0..3
  const grid = [
    // r0
    'c....c.....',
    'cc...cccccc',
    'cc..c....c.',
    'cccc.....c.',
    'cc..c....c.',
    'cc...c...c.',
    'c....c...c.',
  ];
  // legend: c=cream, .=ink (background), use yellow for accent on top-left "T" stem
  const cells = [];
  let idx = 0;
  for (let r = 0; r < grid.length; r++) {
    for (let c = 0; c < 11; c++) {
      const ch = grid[r][c];
      if (ch === 'c') {
        // tint the center of T cream, edges a bit different
        const cls = (c >= 5 && r === 0) ? 'y' : 'c';
        cells.push(<div className={`px ${cls}`} key={idx++} style={{ gridColumn: c+1, gridRow: r+1 }}></div>);
      }
    }
  }
  // Burst dots around grid
  const burst = [
    { top: -18, left: -8, bg: 'var(--red)' },
    { top: -22, right: 30, bg: 'var(--yellow)' },
    { top: 20, right: -22, bg: 'var(--blue)' },
    { bottom: -18, right: -10, bg: 'var(--red)' },
    { bottom: -16, left: 40, bg: 'var(--yellow)' },
    { bottom: 30, left: -22, bg: 'var(--blue)' },
  ];

  // Mini icon: 7x5 simple "KT"
  const miniGrid = [
    'c.c.ccc',
    'cc...c.',
    'c....c.',
    'cc...c.',
    'c.c..c.',
  ];
  const miniCells = [];
  let mi = 0;
  for (let r = 0; r < 5; r++) {
    for (let c = 0; c < 7; c++) {
      const ch = miniGrid[r][c];
      if (ch === 'c') {
        miniCells.push(<div className="mp c" key={mi++} style={{ gridColumn: c+1, gridRow: r+1 }}></div>);
      }
    }
  }

  return (
    <LogoCard
      concept="c4"
      name="Pixel Burst"
      desc="8-bit KT monogram on ink with a halo of pop dots. Reads as 'arcade game' instantly."
      tags={['Pixel', 'Retro']}
      hero={
        <div className="pixel-grid">
          {cells}
          {burst.map((b, i) => (
            <div className="ray" key={i} style={{
              ...b,
              background: b.bg,
              position: 'absolute',
            }}></div>
          ))}
        </div>
      }
      icon={<div className="mini-grid">{miniCells}</div>}
    />
  );
}

/* ─────────── Concept 5 — Smiley Tap ─────────── */
function LogoC5() {
  return (
    <LogoCard
      concept="c5"
      name="Smiley Tap"
      desc="A character mark — the app has a face. Easy for very young kids to recognize and pick out of a folder."
      tags={['Character', 'Mascot']}
      hero={
        <div className="smiley">
          <div className="face">
            <div className="eye l"></div>
            <div className="eye r"></div>
            <div className="blush l"></div>
            <div className="blush r"></div>
            <div className="mouth"></div>
          </div>
        </div>
      }
      icon={
        <div className="smiley-mini">
          <div className="e l"></div>
          <div className="e r"></div>
          <div className="m"></div>
        </div>
      }
    />
  );
}

/* ─────────── Concept 6 — Arcade Badge ─────────── */
function LogoC6() {
  return (
    <LogoCard
      concept="c6"
      name="Arcade Badge"
      desc="Game-cartridge style label with hard ink shadow. Strongest 'this is a game' read of the set."
      tags={['Badge', 'Bold']}
      hero={
        <div className="badge">
          <div className="top-tag">PLAY</div>
          <div className="corners"><span></span><span></span><span></span><span></span></div>
          <div className="name">KID<br/>TAPS</div>
        </div>
      }
      icon={<div className="badge-mini">KT</div>}
    />
  );
}

Object.assign(window, { LogoC1, LogoC2, LogoC3, LogoC4, LogoC5, LogoC6 });
