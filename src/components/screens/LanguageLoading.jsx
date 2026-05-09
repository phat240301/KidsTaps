import { useEffect, useState } from 'react';
import Lottie from 'lottie-react';

export function LanguageLoading() {
  const [animationData, setAnimationData] = useState(null);

  useEffect(() => {
    fetch('/switching_language.json')
      .then(res => res.json())
      .then(data => setAnimationData(data));
  }, []);

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
