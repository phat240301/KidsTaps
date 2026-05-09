// src/components/screens/Result.jsx
import { useGame } from '../../context/GameContext'

export function Result({ navigateTo }) {
  const { results } = useGame()
  if (!results) return null
  const { finalScore, targetScore, timeLeft, streak, accuracy, stars } = results

  const timeUsed = 120 - timeLeft
  const headings = ['Good try!', 'Great job!', 'Amazing!']
  const heading = headings[Math.min(stars - 1, 2)]

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
          <h1>{heading}</h1>
          <div className="desc">
            {finalScore >= targetScore
              ? `You reached your target score with ${timeLeft}s to spare. Try a higher target next round?`
              : `You scored ${finalScore} out of ${targetScore}. Keep practising!`}
          </div>
          <div className="stars">
            {[1, 2, 3].map(i => (
              <div key={i} className={`star ${i <= stars ? 'on' : ''}`}>★</div>
            ))}
          </div>
          <div className="actions">
            <button className="tab-btn red lg" onClick={() => navigateTo('countdown')}>↻ Play again</button>
            <button className="tab-btn cream" onClick={() => navigateTo('home')}>🏠 Home</button>
          </div>
        </div>
        <div className="r-right">
          <div className="r-score-card">
            <div className="lbl">Final score</div>
            <div className="big">{finalScore}</div>
            <div className="meta">Target: {targetScore} pts</div>
          </div>
          <div className="r-stat-row">
            <div className="r-stat"><div className="v">{timeUsed}s</div><div className="l">Time used</div></div>
            <div className="r-stat"><div className="v">{streak}</div><div className="l">Best streak</div></div>
            <div className="r-stat"><div className="v">{accuracy}%</div><div className="l">Accuracy</div></div>
          </div>
        </div>
      </div>
    </div>
  )
}
