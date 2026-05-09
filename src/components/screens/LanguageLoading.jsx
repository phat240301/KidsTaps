import { useEffect, useState } from 'react';
import Lottie from 'lottie-react';

export function LanguageLoading({ navigateTo }) {
  const [animationData, setAnimationData] = useState(null);

  useEffect(() => {
    fetch('/switching_language.json')
      .then(res => res.json())
      .then(data => setAnimationData(data))
      .catch(() => navigateTo('home'))
  }, [navigateTo]);

  useEffect(() => {
    // Animation is 96 frames at 60fps = 1.6s. Navigate after 2 full loops (~3.2s).
    const timer = setTimeout(() => navigateTo('home'), 3200)
    return () => clearTimeout(timer)
  }, [navigateTo])

  if (!animationData) return null;

  return (
    <div className="tab-screen lang-lottie">
      <Lottie
        animationData={animationData}
        loop={true}
        autoplay={true}
        style={{ width: '100%', height: '100%' }}
      />
    </div>
  )
}
