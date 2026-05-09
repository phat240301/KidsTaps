/* global React */

const { useState, useEffect, useRef } = React;

// Force restart of CSS animations by remounting via key
function useReplayKey() {
  const [k, setK] = useState(0);
  return [k, () => setK(x => x + 1)];
}

function ReplayBtn({ onClick }) {
  return (
    <button className="replay-btn" onClick={onClick}>↻ Replay</button>
  );
}

/* ─────────── Variation 1: Bouncing Blocks ─────────── */
function SplashV1({ replayKey }) {
  const letters = ['K', 'I', 'D', '·', 'T', 'A', 'P', 'S'];
  return (
    <div className="splash-stage v1" key={replayKey}>
      <div className="grid-bg"></div>
      <div className="stage">
        <div className="wordmark">
          {letters.map((ch, i) => (
            <div className={`letter ${ch === '·' ? 'gap' : ''}`} key={i}>
              {ch === '·' ? '' : ch}
            </div>
          ))}
        </div>
        <div className="bars">
          {Array.from({ length: 12 }).map((_, i) => (
            <div className="bar" key={i}></div>
          ))}
        </div>
      </div>
    </div>
  );
}

/* ─────────── Variation 2: Wave Burst ─────────── */
function SplashV2({ replayKey }) {
  // Heights for swept bars — symmetric arc
  const heights = [60, 110, 170, 240, 320, 420, 320, 240, 170, 110, 60];
  return (
    <div className="splash-stage v2" key={replayKey}>
      <div className="corner-dots">
        <span></span><span></span><span></span><span></span>
      </div>
      <div className="stage">
        <div className="swept-bars">
          {heights.map((h, i) => (
            <div
              className="bar"
              key={i}
              style={{
                '--h': `${h}px`,
                background: i % 3 === 0 ? '#FFD23F' : i % 3 === 1 ? '#FF3B3B' : '#FFF8EC',
                animationDelay: `${i * 0.06}s, ${1.4 + i * 0.04}s`,
              }}
            ></div>
          ))}
        </div>
        <div className="shockwave"></div>
        <div className="shockwave r2"></div>
        <div className="shockwave r3"></div>
        <div className="wordmark-burst">KID TAPS</div>
      </div>
    </div>
  );
}

/* ─────────── Variation 3: Stacked Pop ─────────── */
function SplashV3({ replayKey }) {
  return (
    <div className="splash-stage v3" key={replayKey}>
      <div className="checker"></div>
      <div className="confetti">
        <span></span><span></span><span></span><span></span>
        <span></span><span></span><span></span><span></span>
      </div>
      <div className="stage">
        <div className="wordmark-stack">
          <div className="row">
            <span className="stack-letter">K</span>
            <span className="stack-letter">I</span>
            <span className="stack-letter">D</span>
          </div>
          <div className="row">
            <span className="stack-letter">T</span>
            <span className="stack-letter">A</span>
            <span className="stack-letter">P</span>
            <span className="stack-letter">S</span>
          </div>
        </div>
        <div className="radial-bars">
          {Array.from({ length: 13 }).map((_, i) => (
            <div className="bar" key={i}></div>
          ))}
        </div>
      </div>
    </div>
  );
}

/* ─────────── Variation 4: Coin Drop ─────────── */
function SplashV4({ replayKey }) {
  return (
    <div className="splash-stage v4" key={replayKey}>
      <div className="stripes"></div>
      <div className="stage">
        <div className="coin-row">
          <div className="side-bars left">
            <div className="b"></div>
            <div className="b"></div>
            <div className="b"></div>
            <div className="b"></div>
            <div className="b"></div>
          </div>
          <div style={{ position: 'relative' }}>
            <div className="impact-ring"></div>
            <div className="impact-ring r2"></div>
            <div className="coin">
              <div className="inner-ring"></div>
              <div className="shine"></div>
              KT
            </div>
          </div>
          <div className="side-bars right">
            <div className="b"></div>
            <div className="b"></div>
            <div className="b"></div>
            <div className="b"></div>
            <div className="b"></div>
          </div>
        </div>
        <div className="v4-wordmark">KID TAPS</div>
        <div className="v4-tag">TAP · PLAY · WIN</div>
      </div>
    </div>
  );
}

/* ─────────── Wrapper that adds replay button ─────────── */
function SplashCard({ Component }) {
  const [k, replay] = useReplayKey();
  return (
    <>
      <Component replayKey={k} />
      <ReplayBtn onClick={replay} />
    </>
  );
}

/* ─────────── Variation 5: Lottie-style Mascot ─────────── */
function SplashV5({ replayKey }) {
  const letters = ['K', 'I', 'D', ' ', 'T', 'A', 'P', 'S'];
  return (
    <div className="splash-stage v5" key={replayKey}>
      <div className="rays">
        <span></span><span></span><span></span><span></span><span></span><span></span>
      </div>
      <div className="scene">
        <div className="mascot">
          <div className="body"></div>
          <div className="eye l"></div>
          <div className="eye r"></div>
          <div className="cheek l"></div>
          <div className="cheek r"></div>
          <div className="mouth"></div>
          <div className="hand"></div>
        </div>
        <div className="particle c1"></div>
        <div className="particle c2"></div>
        <div className="particle c3"></div>
        <div className="particle c4"></div>
        <div className="particle c5"></div>
        <div className="particle c6"></div>
        <div className="particle c7"></div>
        <div className="particle c8"></div>
        <div className="v5-wordmark">
          {letters.map((ch, i) => (
            <span
              className="ch"
              key={i}
              style={{
                animationDelay: `${1.7 + i * 0.06}s`,
                width: ch === ' ' ? '24px' : 'auto',
              }}
            >
              {ch === ' ' ? '\u00A0' : ch}
            </span>
          ))}
        </div>
        <div className="v5-underline"></div>
        <div className="v5-tag">Tap · Play · Learn</div>
      </div>
      <div className="loader">
        <span></span><span></span><span></span>
      </div>
    </div>
  );
}

Object.assign(window, { SplashV1, SplashV2, SplashV3, SplashV4, SplashV5, SplashCard });
