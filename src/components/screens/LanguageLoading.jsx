import { useCallback, useEffect, useRef, useState } from 'react';
import Lottie from 'lottie-react';

export function LanguageLoading({ navigateTo }) {
  const [animationData, setAnimationData] = useState(null);
  const navigatedRef = useRef(false);

  const safeNavigate = useCallback(() => {
    if (navigatedRef.current) return;
    navigatedRef.current = true;
    navigateTo('home');
  }, [navigateTo]);

  useEffect(() => {
    fetch('/switching_language.json')
      .then(res => res.json())
      .then(data => setAnimationData(data))
      .catch(safeNavigate);
  }, [safeNavigate]);

  // Animation is 96 frames at 60fps = 1.6s. Navigate after 2 full loops (~3.2s).
  useEffect(() => {
    const timer = setTimeout(safeNavigate, 3200);
    return () => clearTimeout(timer);
  }, [safeNavigate]);

  return (
    <div className="tab-screen lang-lottie">
      {animationData && (
        <Lottie
          animationData={animationData}
          loop={true}
          autoplay={true}
          style={{ width: '100%', height: '100%' }}
        />
      )}
    </div>
  );
}
