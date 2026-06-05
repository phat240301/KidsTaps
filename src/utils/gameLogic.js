// src/utils/gameLogic.js
export class GameState {
  constructor() {
    this.score = 0
    this.targetScore = 17
    this.timeLeft = 120
    this.itemsSpawned = 0
    this.itemsHit = 0
    this.streak = 0
    this.gameActive = true
  }

  addScore(points = 1) {
    this.score += points
    this.itemsHit++
    this.streak++
    return this.score >= this.targetScore
  }

  resetStreak() {
    this.streak = 0
  }

  tick() {
    if (this.timeLeft > 0) {
      this.timeLeft--
    }
    return this.timeLeft <= 0 || this.score >= this.targetScore
  }

  togglePause() {
    this.gameActive = !this.gameActive
  }
}

export const GAME_ITEMS = [
  { symbol: '●', color: 'r', shape: 'circle' },
  { symbol: '★', color: 'default', shape: 'star' },
  { symbol: '■', color: 'b', shape: 'square' },
  { symbol: '♥', color: 'g', shape: 'heart' },
  { symbol: '▲', color: 'default', shape: 'triangle' },
]
