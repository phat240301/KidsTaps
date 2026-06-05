// src/utils/constants.js
export const COLORS = {
  red: '#FF3B3B',
  yellow: '#FFD23F',
  blue: '#1E88FF',
  green: '#22C55E',
  cream: '#FFF8EC',
  ink: '#1A1A2E',
  soft: '#F4ECDA',
}

export const GAME_SHAPES = ['circle', 'square', 'star', 'heart', 'triangle']

export const GAME_ANIMALS = ['rabbit', 'bear', 'cat', 'dog', 'elephant']

export const GAME_MODES = {
  shapes: {
    name: 'Shapes',
    icon: '🔶',
    description: 'Tap circles, squares, stars and hearts as they appear.',
  },
  animals: {
    name: 'Animals',
    icon: '🐰',
    description: 'Tap the animals as they appear. Each tap names the animal aloud.',
  },
}

export const DIFFICULTY_PRESETS = {
  age3: { spawnSpeed: 5.2, holdTime: 6.0, targetScore: 8 },
  age5: { spawnSpeed: 4.0, holdTime: 4.5, targetScore: 12 },
  age7: { spawnSpeed: 3.4, holdTime: 3.9, targetScore: 17 },
  age9: { spawnSpeed: 2.2, holdTime: 2.8, targetScore: 24 },
}
