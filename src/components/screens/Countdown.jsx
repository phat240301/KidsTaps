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
