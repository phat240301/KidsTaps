// src/components/shared/Button.jsx
export function Button({ children, variant = 'primary', ...props }) {
  const classNames = `tab-btn ${variant === 'red' ? 'red' : variant === 'blue' ? 'blue' : variant === 'cream' ? 'cream' : ''} ${props.className || ''}`
  return <button {...props} className={classNames}>{children}</button>
}
