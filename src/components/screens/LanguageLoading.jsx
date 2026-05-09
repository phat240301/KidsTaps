// src/components/screens/LanguageLoading.jsx
export function LanguageLoading() {
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
  )
}
