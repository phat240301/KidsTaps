// src/components/shared/Slider.jsx
export function Slider({ name, value, min = 0, max = 100, step = 1, hint, onChange }) {
  const fillPercent = ((value - min) / (max - min)) * 100
  const displayValue = step < 1 ? Number(value).toFixed(1) : Math.round(value)

  return (
    <div className="slider-row">
      <div className="label-row">
        <div className="name">{name}</div>
        <div className="val">{displayValue}</div>
      </div>
      <div className="track">
        <div className="fill" style={{ width: `${fillPercent}%` }}></div>
        <input
          type="range"
          min={min}
          max={max}
          step={step}
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
