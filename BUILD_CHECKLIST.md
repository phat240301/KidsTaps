# ✅ KidsTap Build Checklist

Follow this checklist to build the project successfully.  
Print this or keep open in another window.

---

## 📋 Pre-Build Setup (5 min)

### Audio Files Setup
- [ ] **Step 1a:** Extract KidsTap_Project_Updated.tar.gz to D:\KidsTap
  
  OR if already extracted:
  - [ ] **Step 1b:** Go to D:\KidsTap folder in Explorer

- [ ] **Step 2:** Setup audio files (choose ONE):
  
  **Option A - PowerShell (Recommended):**
  ```
  [ ] Open PowerShell (Windows key + R → powershell)
  [ ] Type: cd D:\KidsTap
  [ ] Paste: powershell -ExecutionPolicy Bypass -File setup_audio.ps1
  [ ] Press Enter
  [ ] Wait for "✓ Setup Complete!" message
  [ ] Read on-screen instructions
  [ ] Press Enter to close
  ```
  
  **Option B - Batch File:**
  ```
  [ ] Go to D:\KidsTap in Explorer
  [ ] Double-click: SETUP_AUDIO.bat
  [ ] Wait for "✓ Audio files created!" message
  [ ] Press any key
  ```
  
  **Option C - Manual (Last resort):**
  ```
  [ ] Create folder: D:\KidsTap\app\src\main\res\raw
  [ ] Create 6 empty files (right-click > New > Text Document):
      [ ] correct.wav
      [ ] wrong.wav
      [ ] miss.wav
      [ ] win.wav
      [ ] countdown.wav
      [ ] bgm.wav
  [ ] Verify all 6 files exist in that folder
  ```

- [ ] **Verify:** Check that `D:\KidsTap\app\src\main\res\raw\` has 6 files
  - [ ] correct.wav exists
  - [ ] wrong.wav exists
  - [ ] miss.wav exists
  - [ ] win.wav exists
  - [ ] countdown.wav exists
  - [ ] bgm.wav exists

---

## 🔧 Android Studio Setup (10 min)

### Open Project
- [ ] **Step 3:** Launch Android Studio
- [ ] **Step 4:** File > Open
- [ ] **Step 5:** Navigate to D:\KidsTap (select folder, not a file)
- [ ] **Step 6:** Click "OK" or "Open"
- [ ] **Wait:** For "Indexing..." to finish (bottom right, looks like progress bar)

### Gradle Sync
- [ ] **Step 7:** File > Sync Now (or wait for auto-prompt)
- [ ] **Expected:** Bottom right shows "Gradle sync in progress..."
- [ ] **Wait:** 1-3 minutes (first time is slower)
- [ ] **Verify:** Build output shows: ✅ **Sync successful** (green checkmark)

---

## 🏗️ Build Project (5 min)

### Build Execution
- [ ] **Step 8:** Build > Make Project (or Ctrl+F9)
- [ ] **Expected:** Build output opens at bottom
- [ ] **Watch:** See list of tasks running:
  ```
  :app:generateDebugSources
  :app:kaptDebugKotlin
  :app:compileDebugKotlin
  :app:compileDebugResources
  :app:assembleDebug
  ```
- [ ] **Wait:** 45-90 seconds

### Build Completion
- [ ] **Verify:** Final output shows:
  ```
  ✓ BUILD SUCCESSFUL in 45s
  ```
  
  OR (if slightly different format):
  ```
  Build completed successfully
  ```

- [ ] **Check:** No red X marks or "FAILED" in output
- [ ] **Note:** Yellow warnings are OK (safe to ignore)

---

## 📱 Run on Device/Emulator (2 min)

### Create Emulator (if needed)
- [ ] **Check:** Do you have an Android emulator or physical device?
  - [ ] **Yes:** Skip to "Run App" section below
  - [ ] **No:** Do this:
    ```
    [ ] Tools > Device Manager
    [ ] Click "+ Create Device"
    [ ] Device: "Pixel 5" (or "Pixel 6")
    [ ] Next
    [ ] API Level: "30" or higher (select Recommended)
    [ ] Next
    [ ] AVD Name: "Pixel_5" (or keep default)
    [ ] Finish
    [ ] Wait for device creation (30 seconds)
    ```

### Start Emulator (if using Android Emulator)
- [ ] **Step 9:** Tools > Device Manager (or View > Tool Windows > Device Manager)
- [ ] **Look for:** Your device in the list
- [ ] **Click:** Play button (▶️) next to your device
- [ ] **Wait:** Emulator launches (30-60 seconds first time)
- [ ] **Verify:** Emulator window shows Android home screen

### Run App
- [ ] **Step 10:** Run > Run 'app' (or Shift+F10)
- [ ] **Dialog appears:** Select your device
- [ ] **Check:** Device is highlighted (emulator or phone)
- [ ] **Click:** OK or double-click device name
- [ ] **Wait:** "Install running..." message (bottom right)
- [ ] **Expected time:** 10-30 seconds

---

## ✅ App Launch Verification

### Check HomeScreen Appears
- [ ] **On emulator/phone screen, you should see:**
  
  ```
  ┌──────────────────────────────┐
  │                              │
  │     🎮 KidsTap              │
  │                              │
  │   [🔶 Shapes Button]         │
  │                              │
  │   [🐾 Animals Button]        │
  │                              │
  │   English ↕️    ⚙️           │
  │                              │
  └──────────────────────────────┘
  ```

- [ ] **Text visible:** "🎮 KidsTap" title
- [ ] **Buttons visible:** "🔶 Shapes" and "🐾 Animals"
- [ ] **No crash:** App didn't close/crash
- [ ] **Responsive:** Try tapping "Shapes" button

### Test Basic Navigation
- [ ] **Tap:** "🔶 Shapes" button → Goes to Setup screen
  - [ ] Setup screen shows: Age input, sliders, buttons
  - [ ] Text visible: "Teacher Setup", "Confirm & Start"
  
- [ ] **Tap:** Back button → Returns to Home
  
- [ ] **Tap:** "🐾 Animals" button → Goes to Setup screen
  - [ ] Same setup screen appears (but animals mode)
  
- [ ] **Tap:** Back button → Returns to Home
  
- [ ] **Tap:** ⚙️ Settings button → Goes to Settings screen
  - [ ] Text visible: "Parent Settings"
  
- [ ] **Tap:** Back button → Returns to Home

---

## 🎉 Success Indicators

If you see ALL of these, build is **100% SUCCESSFUL**:

- ✅ Build shows "BUILD SUCCESSFUL"
- ✅ App launches on device/emulator without crashing
- ✅ HomeScreen displays with "🎮 KidsTap" title
- ✅ Can tap Shapes button → Setup screen appears
- ✅ Can tap Animals button → Setup screen appears
- ✅ Can tap Settings button → Settings screen appears
- ✅ Can tap back button → Returns to Home
- ✅ Language selector visible
- ✅ No red errors in Logcat

---

## ❌ Troubleshooting

### Build Says "Unresolved reference: R.raw"
```
[ ] Run setup_audio.ps1 again (or SETUP_AUDIO.bat)
[ ] File > Sync Now
[ ] Build > Rebuild Project (Ctrl+Shift+F10)
```

### Emulator Won't Start
```
[ ] Close Android Studio
[ ] Delete: C:\Users\[YourUsername]\.android\avd (backup first!)
[ ] Reopen Android Studio
[ ] Create new emulator (Tools > Device Manager > Create Device)
```

### App Crashes on Startup
```
[ ] Check Logcat (View > Tool Windows > Logcat)
[ ] Search for red "E/" errors
[ ] Screenshot the error and send to me
```

### Build takes 5+ minutes
```
[ ] Normal for first build (downloading dependencies)
[ ] Subsequent builds: 30-45 seconds
[ ] If stuck > 10 min: Close Android Studio, delete .gradle folder
```

---

## 📞 Report Status

Once complete, reply with:

```
✅ Build Status Report:

Build result: [SUCCESS / FAILED]
Device used: [Emulator name or phone model]
HomeScreen visible: [YES / NO]
All buttons working: [YES / NO]
Any errors: [YES / NO] (if yes, paste error message)
```

---

## 🚀 Next Steps (After Build Success)

Once build succeeds and app runs:

1. ✅ Report: "Build successful!"
2. ✅ We review Logcat output
3. ✅ Start **Sprint 3** (Animals rendering + animations)
4. ✅ Continue building until Sprint 4 complete

---

**Estimated Total Time:** 20-30 minutes  
**Difficulty:** Very easy (just follow checklist)  
**Risk Level:** Zero (just building, no code changes)

**Let's go!** 🚀
