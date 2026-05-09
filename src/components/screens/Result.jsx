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
