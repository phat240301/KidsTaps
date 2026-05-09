// src/components/shared/Switch.jsx
export function Switch({ checked = false, onChange }) {
  return (
    <div
      className={`kt-switch ${checked ? 'on' : ''}`}
      onClick={() => onChange(!checked)}
      role="switch"
      aria-checked={checked}
    />
  )
}
