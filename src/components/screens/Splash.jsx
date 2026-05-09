import { useEffect, useState } from 'react';
import Lottie from 'lottie-react';

export function Splash({ onComplete }) {
  const [animationData, setAnimationData] = useState(null);

  useEffect(() => {
    fetch('/splash_v1.json')
      .then(res => res.json())
      .then(data => setAnimationData(data));
  }, []);

  useEffect(() => {
    const timer = setTimeout(onComplete, 3000);
    return () => clearTimeout(timer);
  }, [onComplete]);

  if (!animationData) return null;

  return (
    <div className="splash-container">
      <Lottie
        animationData={animationData}
        loop={false}
        autoplay={true}
        style={{ width: '100%', height: '100%' }}
      />
    </div>
  );
}
