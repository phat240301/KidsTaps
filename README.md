# 🎮 KidsTap — Android Game for Children with Developmental Delay

Complete Kotlin + Jetpack Compose implementation of an accessible tap game for special needs children.

---

## 📋 Project Status

**Sprint 1 & 2 Delivered** (Foundation + Core Game Engine)

- ✅ Domain models, DifficultyCalculator, GameEngine
- ✅ Theme, Navigation, Data layer (DataStore)
- ✅ HomeScreen, GameSetupScreen, CountdownScreen, GameScreen (Canvas)
- ✅ GameViewModel with StateFlow
- ✅ Audio system (SoundManager) with SoundPool + ExoPlayer
- ✅ Strings (EN + VI i18n)
- ⏳ **Audio Files** — Need to add .mp3 files to `res/raw/`
- ⏳ **ResultScreen & SettingsScreen** — Sprint 4 TODO

---

## 🚀 Quick Start

### 1. Clone & Open in Android Studio
```bash
cd KidsTap
# Open in Android Studio (File > Open)
```

### 2. **CRITICAL: Add Audio Files**

The app references audio files that don't yet exist. You have two options:

#### Option A: Use Open-Source Audio (Recommended)

Download royalty-free SFX from **Freesound.org** or **OpenGameArt.org** (CC0/MIT license):

1. Create folder: `app/src/main/res/raw/`
2. Download and add these files (convert to .mp3 if needed):
   - `correct.mp3` — bright chime/coin sound (0.3–0.5s)
   - `wrong.mp3` — soft neutral tone (0.3s)
   - `miss.mp3` — gentle poof (0.3s)
   - `win.mp3` — happy fanfare (1–2s)
   - `countdown.mp3` — beep countdown (0.2s)
   - `bgm.mp3` — calm looping background music (2–4 min loop)

Example: Search Freesound.org for "coin sound" (correct), "error tone" (wrong), etc.

#### Option B: Create Placeholder Silent Files (for quick build)

```bash
# Quick workaround — silent MP3s (won't play audio, app will build)
# Place empty .mp3 files in app/src/main/res/raw/
touch app/src/main/res/raw/correct.mp3
touch app/src/main/res/raw/wrong.mp3
touch app/src/main/res/raw/miss.mp3
touch app/src/main/res/raw/win.mp3
touch app/src/main/res/raw/countdown.mp3
touch app/src/main/res/raw/bgm.mp3
```

### 3. Build & Run

```bash
# Sync Gradle
# Build > Make Project (or Ctrl+F9)

# Run on device/emulator
# Run > Run 'app' (or Shift+F10)
```

---

## 📁 Project Structure

```
KidsTap/
├── app/
│   ├── build.gradle.kts                    # App-level Gradle config
│   ├── proguard-rules.pro                  # Obfuscation rules (release build)
│   └── src/main/
│       ├── AndroidManifest.xml             # 100% offline, no INTERNET permission
│       ├── java/com/kidstap/app/
│       │   ├── MainActivity.kt             # Entry point
│       │   ├── KidsTapApplication.kt       # Hilt setup
│       │   ├── di/
│       │   │   └── AppModule.kt            # Dependency injection
│       │   ├── domain/
│       │   │   ├── model/
│       │   │   │   ├── GameMode.kt         # SHAPES | ANIMALS enum
│       │   │   │   ├── ShapeType.kt        # Circle, Square, Triangle, Star, Heart
│       │   │   │   ├── AnimalType.kt       # 10 animals with emoji
│       │   │   │   ├── GameConfig.kt       # Teacher-configured game settings
│       │   │   │   ├── SpawnableItem.kt    # Shape or Animal on canvas
│       │   │   │   └── GameState.kt        # Idle | Active | Finished
│       │   │   ├── DifficultyCalculator.kt # Age → config formula
│       │   │   └── GameEngine.kt           # Game loop, spawn, hit-test
│       │   ├── data/
│       │   │   └── SettingsRepository.kt   # DataStore R/W (config, high scores)
│       │   ├── audio/
│       │   │   └── SoundManager.kt         # SoundPool + ExoPlayer
│       │   └── ui/
│       │       ├── theme/
│       │       │   ├── Color.kt            # Accessible palette for kids
│       │       │   ├── Type.kt             # Large, bold typography
│       │       │   └── Theme.kt            # Material3 KidsTapTheme
│       │       ├── navigation/
│       │       │   ├── Route.kt            # Navigation route definitions
│       │       │   └── NavGraph.kt         # Compose navigation setup
│       │       ├── home/
│       │       │   └── HomeScreen.kt       # Mode selection + language toggle
│       │       ├── setup/
│       │       │   └── GameSetupScreen.kt  # Teacher age input + sliders
│       │       ├── countdown/
│       │       │   └── CountdownScreen.kt  # 3…2…1…GO animation
│       │       ├── game/
│       │       │   ├── GameScreen.kt       # Canvas drawing + tap detection
│       │       │   ├── GameViewModel.kt    # Game state management
│       │       │   └── AnimalDrawing.kt    # (TODO) Animal canvas drawers
│       │       ├── result/
│       │       │   └── ResultScreen.kt     # Score summary (Sprint 4)
│       │       ├── settings/
│       │       │   └── SettingsScreen.kt   # Parent gate + settings (Sprint 4)
│       │       └── components/
│       │           └── ParentGateDialog.kt # (TODO) 3-sec hold unlock
│       └── res/
│           ├── values/
│           │   └── strings.xml             # English (EN)
│           ├── values-vi/
│           │   └── strings.xml             # Vietnamese (VI)
│           └── raw/
│               ├── correct.mp3             # (User adds this)
│               ├── wrong.mp3               # (User adds this)
│               ├── miss.mp3                # (User adds this)
│               ├── win.mp3                 # (User adds this)
│               ├── countdown.mp3           # (User adds this)
│               └── bgm.mp3                 # (User adds this)
│
├── build.gradle.kts                       # Project-level Gradle
├── settings.gradle.kts                    # Gradle settings
├── gradle/libs.versions.toml               # Version catalog (dependencies)
└── README.md                               # This file
```

---

## 🔧 Configuration

### Target Device

- **Min SDK:** 24 (Android 7.0) — covers ~98% of devices
- **Target SDK:** 34 (Android 14)
- **Orientation:** Portrait (locked, no rotation)
- **Language:** English (EN) + Vietnamese (VI) — changeable at runtime

### Difficulty Formula (Age-based)

```
spawnInterval  = max(1.5,  5.5 − (age × 0.3))   seconds
holdDuration   = max(2.0,  6.0 − (age × 0.3))   seconds
targetScore    = min(30,   3 + (age × 2))        points
```

Example:
- **Age 5:** spawn every 4.0s, show for 4.5s, target 13 points
- **Age 8:** spawn every 3.1s, show for 3.6s, target 19 points
- **Age 10:** spawn every 2.5s, show for 3.0s, target 23 points

Teacher can adjust all three via sliders before each game.

---

## 🎮 Game Flow

```
[ HOME ]
  ↓ Select mode (Shapes or Animals)
[ SETUP ] (Teacher)
  ↓ Input age → Auto-suggest config → Adjust sliders → Confirm
[ COUNTDOWN ]
  ↓ 3 … 2 … 1 … GO! (animated, with sound)
[ GAME ]
  ↓ Items spawn randomly, child taps
  ↓ Score feedback + SFX (correct/wrong)
  ↓ Win when score reaches target
[ RESULT ]
  ↓ Show final score → Replay or Home
```

---

## 🎨 Screens & Components

### HomeScreen
- Mode selection: **Shapes** (🔶) vs **Animals** (🐾)
- Language toggle: EN ↔ VI
- Settings button (⚙️)

### GameSetupScreen (★ Critical)
- **Age Input:** Slider or text field (3–12)
- **Auto-Suggest:** Button calculates difficulty from age
- **Spawn Interval:** Slider (1.5–8.0s)
- **Hold Duration:** Slider (2.0–10.0s)
- **Target Score:** Slider (5–30 points)
- **BGM Toggle:** Enable/disable background music
- **Calm Mode Toggle:** Reduce animations for sensory-sensitive kids

### CountdownScreen
- Animated countdown: **3** (purple) → **2** (yellow) → **1** (red) → **GO** (green)
- Scale + fade animations
- Plays countdown SFX before game starts

### GameScreen (★ Critical — Most Complex)
- **Canvas:** Draws shapes (Circle, Square, Triangle, Star, Heart) or animals (emojis)
- **Gradient Fills:** Each shape has top-to-bottom gradient for visual appeal
- **Hit Detection:** Tap-to-hit collision detection for each item
- **HUD:** Score (current/target) + elapsed time at top
- **Feedback:** Correct tap = bright sound + animation; wrong = neutral tone
- **Game Over:** Overlay shows win/score, auto-navigates to result

### ResultScreen
- Shows final score + win/lose message
- "Play Again" button (returns to setup)
- "Home" button (returns to mode selection)

### SettingsScreen (★ Sprint 4 TODO)
- Parent Gate: 3-second hold to unlock settings
- BGM Volume slider
- SFX Volume slider
- Calm Mode toggle
- Reset High Scores (requires gate unlock)

---

## 🔊 Audio System

### SoundManager (`audio/SoundManager.kt`)

**SoundPool** (low-latency SFX):
- `playCorrect()` — bright chime (on correct tap)
- `playWrong()` — soft tone (on wrong tap)
- `playMiss()` — gentle poof (when item disappears unshopped)
- `playWin()` — fanfare (when game ends with win)
- `playCountdown()` — beep (3, 2, 1 countdown)

**ExoPlayer** (background music):
- `playBGM()` — looping background music (configurable, toggleable)
- `stopBGM()` — pause BGM
- `setBGMVolume(float)` — volume 0.0–1.0

**Anti-Overlap:** SoundPool rejects same sound if played within 200ms (prevents stutter on rapid taps).

---

## 📊 Game Engine Logic

### GameEngine (`domain/GameEngine.kt`)

**Spawn Loop:**
1. Wait `spawnInterval` seconds
2. Create random Shape or Animal at safe position (avoid edges)
3. Emit to UI
4. Wait `holdDuration` seconds
5. Remove item (or keep if tapped before timeout)

**Tap Detection:**
- Bounds checking: `isHit(tapX, tapY)` returns true if tap inside shape/animal
- Score increment on correct tap
- Win condition: `score >= targetScore` → finish game

**Game State Flow:**
- `Idle` → `Active(currentScore, targetScore, visibleItems)` → `Finished(finalScore, isWin)`
- Emitted via `StateFlow<GameState>` to ViewModel

---

## 🎯 Special Needs UX Features

✅ **Large Touch Targets:** All items min 80dp × 80dp (ideal 120dp)
✅ **No Negative Scoring:** Score only goes up; wrong taps don't deduct
✅ **Calm Audio:** No harsh buzzers or alarms; gentle sounds for misses
✅ **Calm Mode:** Toggle to reduce animations, reduce overstimulation risk
✅ **Parent Gate:** 3-second hold prevents child from changing settings mid-game
✅ **High Contrast + Bold Colors:** Accessible palette (no harsh red/black combos)
✅ **Slow Minimum Speed:** Spawn interval floor 1.5s, hold duration floor 2.0s
✅ **Illustrations, Not Photos:** Stylized animals (emojis) easier to process
✅ **Portrait Lock:** No accidental rotation disorientation
✅ **Countdown Before Game:** 3…2…1 gives child time to prepare

---

## 🛠️ Developer Notes

### Dependency Injection (Hilt)

```kotlin
@HiltViewModel
class GameViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val soundManager: SoundManager,
) : ViewModel()
```

All singleton services provided in `di/AppModule.kt`.

### State Management (StateFlow)

```kotlin
private val _gameState = MutableStateFlow<GameState>(GameState.Idle)
val gameState: StateFlow<GameState> = _gameState.asStateFlow()

// Collect in Compose
val state by gameState.collectAsState()
```

### Canvas Drawing

Shapes drawn using `DrawScope` primitives:
- **Circle:** `drawCircle()`
- **Square:** `drawRect()`
- **Triangle:** Manual line drawing
- **Star:** 5-point path
- **Heart:** Composed circles + manual drawing
- **Animals:** Unicode emoji (simple; upgrade to vector drawables in production)

---

## 🚀 Next Steps (Sprint 3 & 4)

### Sprint 3 (Week 3)
- [ ] Implement `AnimalDrawing.kt` — stylized vector animal drawings
- [ ] Add more animals (currently: Cat, Dog, Elephant, Bird, Fish, Rabbit, Tiger, Chick, Monkey, Lion)
- [ ] Implement tap animation (scale burst + fade out)
- [ ] Add confetti/celebration effects (disable in Calm Mode)
- [ ] Finalize ResultScreen with star rating

### Sprint 4 (Week 4)
- [ ] Implement ParentGateDialog (3-second hold unlock)
- [ ] Complete SettingsScreen (volume sliders, calm mode, reset scores)
- [ ] Implement LocaleManager for runtime language switching
- [ ] ProGuard configuration for release build
- [ ] QA testing on multiple devices (phone + tablet)
- [ ] Build signed APK for release

---

## 🐛 Known Issues & TODOs

- **Audio Files Missing:** Add .mp3 files to `res/raw/` (see Quick Start section)
- **Animal Rendering:** Currently using emojis; can upgrade to Canvas drawing or vector drawables
- **Config Passing:** GameSetupScreen → GameScreen needs shared state (ViewModel or SavedState)
- **Parent Gate:** Not yet implemented in SettingsScreen
- **Accessibility:** No TalkBack support yet; needs content descriptions on all buttons/canvas items
- **Tablet Testing:** Needs landscape layout support (currently portrait-only)

---

## 📝 License & Attribution

**Code:** Proprietary (internal use only)

**Audio:** User to source from royalty-free platforms:
- Freesound.org (CC0/CC-BY)
- OpenGameArt.org (CC0/MIT)
- Zapsplat.com (CC0)

---

## 👨‍💼 Contact & Support

For questions about this implementation, refer to the **Implementation Plan** document (Word file) included in the project.

---

**Last Updated:** 2025-04-26  
**Version:** 1.0.0 (Sprint 1-2 complete)
