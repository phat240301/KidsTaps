# 🏗️ KidsTap Build Guide

**Date:** April 26, 2025  
**Platform:** Windows 10/11 with Android Studio

---

## ⚡ Quick Start (5 minutes)

### Step 1: Setup Audio Files

**Option A: PowerShell (Recommended for Windows)**
```powershell
# From project root (D:\KidsTap)
powershell -ExecutionPolicy Bypass -File setup_audio.ps1
```

**Option B: Batch File**
```cmd
# From project root
SETUP_AUDIO.bat
```

**Option C: Manual (if scripts don't work)**
1. Create folder: `D:\KidsTap\app\src\main\res\raw`
2. Create 6 empty files with any content:
   - `correct.wav`
   - `wrong.wav`
   - `miss.wav`
   - `win.wav`
   - `countdown.wav`
   - `bgm.wav`

### Step 2: Open in Android Studio
1. Launch Android Studio
2. File > Open
3. Select `D:\KidsTap` folder
4. Click OK

### Step 3: Gradle Sync
1. Wait for "Indexing" to finish (bottom right)
2. File > Sync Now (or it may prompt automatically)
3. Wait for sync to complete (might take 1-2 min first time)

**Expected output:**
```
✓ Sync successful
```

### Step 4: Build Project
1. Build > Make Project (or Ctrl+F9)
2. Wait for build to complete
3. Check Build output (bottom panel)

**Expected output:**
```
Build completed successfully
```

### Step 5: Run on Device/Emulator
1. Run > Run 'app' (or Shift+F10)
2. Select emulator or connected device
3. App should launch in 10-30 seconds

**Expected on first launch:**
```
HomeScreen with:
  - Title "🎮 KidsTap"
  - Two buttons: "🔶 Shapes" and "🐾 Animals"
  - Language selector and ⚙️ Settings button
```

---

## 🔍 Expected Build Output

When build completes successfully, you should see:

```
BUILD SUCCESSFUL in 45s
16 actionable tasks: 16 up-to-date
```

### Gradle Tasks Run:
```
:app:generateDebugSources
:app:kaptDebugKotlin
:app:compileDebugKotlin
:app:compileDebugResources
:app:assembleDebug
```

**All should show: ✓ (green checkmark)**

---

## ❌ Common Build Errors & Fixes

### Error 1: "Unresolved reference: R.raw.correct"

**Cause:** Audio files missing

**Fix:**
```
1. Run setup_audio.ps1 or SETUP_AUDIO.bat
2. File > Sync Now
3. Build > Rebuild Project
```

### Error 2: "MinSdk (24) is higher than targetSdk (21)"

**Cause:** Device/emulator is too old

**Fix:**
```
Create new AVD:
1. Tools > Device Manager
2. Create Device
3. Device: Pixel 5 (or newer)
4. API: 30 or higher
5. Click Finish
```

### Error 3: "Unable to add window — token null"

**Cause:** AndroidManifest.xml not applying theme

**Fix:** Already fixed in our code, but check:
```xml
<!-- AndroidManifest.xml should have -->
android:theme="@style/Theme.KidsTap"
```

### Error 4: "Gradle sync failed"

**Cause:** Corrupted Gradle cache or network issue

**Fix:**
```
1. File > Invalidate Caches
2. Click "Invalidate and Restart"
3. Wait for restart
4. File > Sync Now
```

### Error 5: "Kotlin/Java compilation error"

**Cause:** Hilt annotation processor not running

**Fix:** Already configured in build.gradle.kts, but verify:
```kotlin
// app/build.gradle.kts should have
plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

ksp(libs.hilt.compiler)
```

### Error 6: "Resource not found: color/..."

**Cause:** Theme colors not defined

**Fix:** Already included in Color.kt, check it compiled

### Error 7: "ExoPlayer library not found"

**Cause:** Dependency missing

**Fix:** Already in libs.versions.toml, try:
```
Build > Clean Project
Build > Rebuild Project
```

---

## ✅ Verification Checklist

After successful build, verify:

- [ ] HomeScreen displays (title + 2 mode buttons)
- [ ] Can tap "Shapes" button → navigates to Setup
- [ ] Can tap "Animals" button → navigates to Setup
- [ ] Can tap language selector → changes text
- [ ] Can tap Settings button → navigates to SettingsScreen
- [ ] Can enter age (try "5") and tap "Auto"
- [ ] Sliders update values (visible on screen)
- [ ] Can tap "Confirm & Start" → CountdownScreen
- [ ] CountdownScreen shows 3...2...1...GO animation
- [ ] After countdown → GameScreen launches
- [ ] GameScreen shows shapes spawning randomly
- [ ] Can tap shapes → score increments
- [ ] When score reaches target → Result screen

If all ✓, build is **successful**.

---

## 📊 Build Metrics

| Metric | Expected |
|--------|----------|
| Build time | 45-90 seconds |
| APK size | 3-5 MB (debug) |
| Gradle tasks | 16 total |
| Compilation errors | 0 |
| Warnings (OK) | 5-10 (safe to ignore) |
| Test runs | 0 (no tests yet) |

---

## 🐛 Debugging Tips

### Enable Verbose Gradle Output
```
File > Settings > Build, Execution, Deployment > Gradle
☑ Run tasks with verbose output
```

### Check Logcat During App Run
```
View > Tool Windows > Logcat
Search for "KidsTap" or "GameEngine"
```

### Common Log Messages (Normal)
```
I/GameEngine: Spawn loop started
I/SoundManager: SoundPool initialized
I/GameScreen: Canvas size: 400x700
D/Navigation: Navigating to countdown
```

### Red Flags in Logcat
```
E/AndroidRuntime: FATAL EXCEPTION
E/Hilt: Missing binding
E/SoundManager: Audio file not found
```

If you see red errors, **screenshot and share with me**.

---

## 📱 Testing on Device

### Connected Android Phone via USB

```
1. Phone settings > Developer Options > USB Debugging (ON)
2. Connect USB cable
3. Run > Run 'app'
4. Select your phone in dialog
5. App installs in 30-60 seconds
```

### Android Emulator

```
1. Tools > Device Manager
2. Click Play icon next to device
3. Wait for emulator to boot (30-60s first time)
4. Run > Run 'app'
5. Select running emulator
```

**Tip:** Emulator is slower but easier to test. Device is faster.

---

## 🎯 After Successful Build

Once build succeeds:

1. ✅ Take screenshot of HomeScreen
2. ✅ Test all screens (Setup → Countdown → Game → Result)
3. ✅ Reply with "Build successful!" message
4. ✅ We proceed to **Sprint 3 — Animals + Polish**

---

## 🆘 If Build Fails

Send me:
1. **Full error message** (copy from Build output)
2. **Screenshot of error**
3. **Project path** (you said D:\KidsTap — confirm)
4. **Android Studio version** (Help > About)
5. **Java/Kotlin version** (File > Settings > Build Tools)

I'll fix code and re-send updated project.

---

## 📞 Need Help?

If stuck, **do NOT modify any .kt files**. Instead:

1. Try steps 1-5 again (sometimes race conditions)
2. Close Android Studio completely
3. Delete: `KidsTap/.gradle` folder
4. Reopen Android Studio
5. File > Sync Now

90% of build issues are cache-related and this fixes them.

---

**Expected result:** App launches with HomeScreen visible, all screens navigable.

**Estimated time:** 5-10 minutes total.

**Next:** Report build status and we start Sprint 3! 🚀
