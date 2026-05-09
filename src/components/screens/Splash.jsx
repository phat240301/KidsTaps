import { useState, useEffect } from 'react';

function SplashV2({ replayKey }) {
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

export function Splash({ onComplete }) {
  const [splashKey] = useState(0);

  useEffect(() => {
    const timer = setTimeout(onComplete, 3000);
    return () => clearTimeout(timer);
  }, [onComplete]);

  return (
    <div className="splash-container">
      <SplashV2 replayKey={splashKey} />
    </div>
  );
}
