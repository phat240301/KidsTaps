# 🎯 NEXT STEPS — What to Do Now

This document guides you through getting the KidsTap project up and running.

---

## Phase 1: Setup (Today)

### 1.1 Add Audio Files (CRITICAL ⚠️)

The app will **NOT compile** or **will crash at runtime** without audio files.

**Choose ONE approach:**

#### Approach A: Real Audio (Recommended for Production)

Go to **Freesound.org** (free account):

1. Search for these sounds and download as .mp3:
   - "coin sound" or "chime" → `correct.mp3` (0.3–0.5s)
   - "soft error tone" → `wrong.mp3` (0.3s)
   - "poof" or "pop" → `miss.mp3` (0.3s)
   - "fanfare" or "ta-da" → `win.mp3` (1–2s)
   - "beep" or "digital blip" → `countdown.mp3` (0.2s)
   - "relaxing loop" or "calm music" → `bgm.mp3` (2–4 min, loopable)

2. Create folder in Android Studio:
   ```
   app → src → main → res → right-click → New → Directory → "raw"
   ```

3. Drag .mp3 files into `res/raw/`

4. If files aren't recognized, refresh Gradle:
   - File > Sync Now

#### Approach B: Quick Placeholder (for development only)

Create empty .mp3 files so the build succeeds (no audio, but app won't crash):

```bash
# In project root
cd app/src/main/res
mkdir -p raw
touch raw/correct.mp3 raw/wrong.mp3 raw/miss.mp3 raw/win.mp3 raw/countdown.mp3 raw/bgm.mp3
# Then sync Gradle
```

### 1.2 Build Project

```bash
# In Android Studio
1. File > Sync Now (wait for Gradle sync)
2. Build > Make Project (or Ctrl+F9)
   - Should complete WITHOUT errors
   - Some warnings are OK

# If errors about R.raw.*:
   - Verify audio files exist in app/src/main/res/raw/
   - Clean > Rebuild Project (Ctrl+Shift+F10)
```

### 1.3 Run on Device/Emulator

```bash
# Create AVD (Android Virtual Device) if you don't have one
# Tools > Device Manager > Create Device (Pixel 4, API 30+)

# Or connect a physical Android phone via USB

# Run app
# Run > Run 'app' (or Shift+F10)
   - Should launch HomeScreen
   - No crashes, can select Shapes or Animals
```

---

## Phase 2: Test Current Build (Tomorrow)

### 2.1 Navigate Through Screens

Flow to test:
```
Home → Select "Shapes" 
  → Setup (enter age 5, tap Auto, confirm)
  → Countdown (3...2...1...GO)
  → Game (taps should work, score increments)
  → Result (should auto-navigate after 2 sec)
```

### 2.2 Check Gameplay Mechanics

- [ ] Items spawn in random positions on screen
- [ ] Items appear for 3-4 seconds (configurable)
- [ ] Tapping an item increments score
- [ ] Score HUD updates in real-time
- [ ] When score reaches target, game ends with win message
- [ ] "Play Again" takes you back to Setup
- [ ] "Home" takes you back to HomeScreen

### 2.3 Check Audio (if audio files added)

- [ ] BGM plays on game start (soft background)
- [ ] "Correct" sound on tap
- [ ] "Wrong" sound if tapping wrong area (currently not implemented yet)
- [ ] Volume not too loud (respect kid's hearing)

### 2.4 Test Language Switch

- [ ] HomeScreen: tap "English" → "Tiếng Việt"
- [ ] Setup screen text changes to Vietnamese
- [ ] All strings display correctly (no truncation)

---

## Phase 3: Fill in Missing Pieces (Sprint 3 & 4)

### 3.1 Complete ResultScreen (Sprint 4)

File: `ui/result/ResultScreen.kt`

**TODO:**
- Receive actual final score from GameScreen
- Display score
- Add star rating (⭐⭐⭐ based on performance)
- "Play Again" → navigate back to HomeScreen (ready for next setup)

**Pass data via:**
```kotlin
// In GameScreen when game finishes
navController.navigate(Route.Result.route) {
    // TODO: Pass final score via SavedStateHandle or ViewModel
}
```

### 3.2 Complete SettingsScreen (Sprint 4)

File: `ui/settings/SettingsScreen.kt`

**TODO:**
- Implement ParentGateDialog: 3-second hold to unlock
- BGM Volume slider (connect to SoundManager.setBGMVolume)
- SFX Volume slider (connect to SoundManager.setSFXVolume)
- Calm Mode toggle (disable animations in GameScreen)
- Reset High Scores button (call SettingsRepository.resetHighScore)
- All changes persist to DataStore

### 3.3 GameViewModel Missing Link

File: `ui/game/GameViewModel.kt`

**TODO:**
- Receive GameConfig from GameSetupScreen
- Pass it to GameEngine via shared ViewModel state
- Currently hardcoded to default config; make it dynamic

**Fix:**
```kotlin
// In GameSetupScreen
val viewModel: GameViewModel = hiltViewModel()
viewModel.setGameConfig(config)

// In GameViewModel
fun setGameConfig(config: GameConfig) {
    _gameConfig.value = config
}
```

### 3.4 Canvas Animals Drawing

File: `ui/game/AnimalDrawing.kt` (create new)

**TODO:**
- Replace emoji rendering with stylized Canvas drawings
- Example: Cat = two triangles (ears) + circle (face) + dots (eyes)
- Keep it simple and bold for visibility

**Simplest approach:** Draw using Canvas shapes in GameScreen:
```kotlin
fun drawAnimal(animal: AnimalType) {
    when (animal) {
        AnimalType.CAT -> {
            // Draw cat face: circle + 2 ear triangles
        }
        AnimalType.DOG -> {
            // Draw dog: round head + snout
        }
        // etc.
    }
}
```

### 3.5 Tap Animation

File: `ui/game/GameScreen.kt` — enhance drawSpawnableItem()

**TODO:**
- When item is tapped correctly, animate it:
  - Scale up (1.0 → 1.3) then shrink back
  - Fade out gently (alpha 1.0 → 0.0)
  - Rotate slightly for fun effect
- Use Animatable or rememberUpdatedState

---

## Phase 4: Testing & Polish (Week 4)

### 4.1 Device Testing

- [ ] Test on **small phone** (360dp width)
  - HomeScreen buttons fit and tap targets are large enough
  - Setup sliders don't overflow
  
- [ ] Test on **large tablet** (600dp+ width)
  - Canvas game area expands nicely
  - Items spawn in safe area (not off-screen)
  
- [ ] Test on **child's actual device** (if available)
  - Responsiveness to taps
  - Audio volume appropriate
  - No crashes during long play sessions

### 4.2 Accessibility

- [ ] Enable TalkBack on device:
  - Settings > Accessibility > TalkBack
  - Navigate all screens with TalkBack on
  - All buttons read correctly
  
- [ ] Add content descriptions:
  ```kotlin
  Button(..., modifier = Modifier.semantics { 
      contentDescription = "Start Shapes Game" 
  })
  ```

### 4.3 Build Release APK

```bash
# 1. Build signed APK
#    Build > Build Bundle(s) / APK(s) > Build APK(s)
#    Create new keystore (store password safely!)
#
# 2. Output: app/release/app-release.apk
#
# 3. Test on real device:
#    adb install -r app/release/app-release.apk
#
# 4. Verify:
#    - All features work on release build
#    - No crashes (check Logcat)
#    - ProGuard obfuscation didn't break anything
```

---

## 🎯 Checklist: Sprint Completion

### Sprint 1 (Foundation) — ✅ DONE
- [x] Gradle setup + dependencies
- [x] Hilt DI
- [x] Domain models
- [x] Theme + Navigation
- [x] DataStore repository
- [x] HomeScreen + GameSetupScreen
- [x] Strings (EN + VI)

### Sprint 2 (Game Core) — ✅ DONE
- [x] GameEngine with spawn loop
- [x] GameViewModel + StateFlow
- [x] CountdownScreen
- [x] GameScreen canvas + tap detection
- [x] SoundManager
- [x] Audio integration

### Sprint 3 (Animals + Polish) — ⏳ TODO
- [ ] AnimalsDrawing.kt (replace emojis with vector-style)
- [ ] Tap animation (scale + fade)
- [ ] Confetti effect (optional, disable in Calm Mode)
- [ ] ResultScreen completion
- [ ] Shape color variants (gradient fill)
- [ ] Test on tablet landscape (optional)

### Sprint 4 (Settings + Release) — ⏳ TODO
- [ ] ParentGateDialog (3-sec hold)
- [ ] SettingsScreen (volume, calm mode, reset scores)
- [ ] LocaleManager (runtime language switch)
- [ ] Accessibility (TalkBack, content descriptions)
- [ ] ProGuard + release APK build
- [ ] Final QA on multiple devices

---

## 📚 Code References

### Key Files to Understand First

1. **GameEngine.kt** — Core game loop
   - `spawnLoop()` — item spawning coroutine
   - `onTap(x, y)` — hit detection + scoring
   - State emission via StateFlow

2. **GameViewModel.kt** — UI ↔ Engine bridge
   - Receives config from Setup
   - Manages SoundManager + SettingsRepository
   - Exposes gameState for Compose to collect

3. **GameScreen.kt** — Canvas + Tap Input
   - `Canvas { drawSpawnableItem() }` — render shapes/animals
   - `pointerInput { detectTapGestures }` — tap handling
   - HUD display (score, time)

4. **GameSetupScreen.kt** — Config input
   - Age slider → auto-suggest formula
   - 3 sliders: spawn, hold, targetScore
   - Toggles: BGM, Calm Mode
   - Pass config to GameViewModel

### Extension Points (Where to Add Features)

- **Add new shape:** Add to `ShapeType` enum, then add `drawXxx()` case in GameScreen
- **Add new animal:** Add emoji to `AnimalType`, emoji renders immediately
- **Add new UI screen:** Create in `ui/[screenname]/`, add Route, add NavGraph composable
- **Add audio:** Add .mp3 to `res/raw/`, load in SoundManager, call `playXxx()`
- **Add setting:** Add DataStore key in SettingsRepository, expose Flow, wire to SettingsScreen

---

## 🆘 Troubleshooting

**Q: "Unresolved reference: R.raw.correct"**
A: Audio files missing. Create `res/raw/` and add .mp3 files (see Phase 1).

**Q: "Class 'GameViewModel' uses Hilt but no ActivityViewModel component"**
A: Make sure MainActivity is @AndroidEntryPoint. Check MainActivity.kt line 1.

**Q: App compiles but crashes on startup**
A: Check Logcat for error message. Likely SoundManager.init() failing due to missing audio files.

**Q: GameScreen is blank / no items appear**
A: Check GameEngine.start() is being called in GameViewModel.startGame(). Also verify gameAreaWidth/Height are set correctly.

**Q: Sliders don't update when I change age input**
A: Need to implement `updateFromAge()` button click in GameSetupScreen to recalculate difficulty.

---

## 📞 Questions?

Refer to:
1. **README.md** — Architecture + feature overview
2. **Implementation Plan (Word)** — High-level design + Sprint checklist
3. **Code comments** — Each class has KDoc explaining logic

Good luck! 🚀
