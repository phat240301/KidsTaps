# 🔐 Keystore Setup for APK Signing

This guide explains how to generate a keystore file and use it to sign release APKs.

---

## ⚠️ IMPORTANT SECURITY NOTES

- **NEVER commit `*.keystore` or `*.jks` files to git** (already ignored by `.gitignore`)
- **Keep your keystore file safe** - losing it means you can't update the app
- **Store the password securely** - you'll need it for every release
- **Back up the keystore file** in a safe location

---

## 1️⃣ Generate a Keystore File

### Option A: Using Android Studio (Recommended)

```
1. Build > Generate Signed Bundle/APK
2. Click "Create new"
3. Fill in:
   - Key store path: app/keystore/kidstaps.jks
   - Password: [Create strong password]
   - Confirm password: [Re-enter]
   - Alias: kidstaps
   - Password: [Same or different password]
   - Validity: 25 years
   - Fill in your info (name, company, etc.)
4. Click "Create"
```

### Option B: Using Command Line

```bash
# Windows (PowerShell)
& 'C:\Program Files\Android\Android Studio\jre\bin\keytool.exe' `
  -genkey -v -keystore kidstaps.jks `
  -keyalg RSA -keysize 2048 -validity 9125 `
  -alias kidstaps

# Mac/Linux
keytool -genkey -v -keystore kidstaps.jks \
  -keyalg RSA -keysize 2048 -validity 9125 \
  -alias kidstaps
```

**When prompted:**
```
Enter keystore password: [password]
Confirm password: [password]
What is your first and last name? Kid Taps
What is the name of your organizational unit? Development
What is the name of your organization? Your Company
What is the name of your City or Locality? Your City
What is the name of your State or Province? Your State
What is the two-letter country code? US
Is <CN=...> correct? yes
Enter key password for <kidstaps>: [same or different password]
```

---

## 2️⃣ Store the Keystore File

```
project-root/
├── app/
│   └── keystore/
│       └── kidstaps.jks        ← Store here (add to git ignore)
├── build.gradle.kts
└── .gitignore                  ← Already ignores *.jks and *.keystore
```

---

## 3️⃣ Configure Gradle for Signing

Edit `app/build.gradle.kts`:

```kotlin
android {
    // ... existing config

    signingConfigs {
        create("release") {
            storeFile = file("keystore/kidstaps.jks")
            storePassword = "YOUR_KEYSTORE_PASSWORD"
            keyAlias = "kidstaps"
            keyPassword = "YOUR_KEY_PASSWORD"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

⚠️ **Better approach:** Use `local.properties` instead of hardcoding passwords:

```properties
# local.properties (never commit this file!)
keystorePath=keystore/kidstaps.jks
keystorePassword=YOUR_KEYSTORE_PASSWORD
keyAlias=kidstaps
keyPassword=YOUR_KEY_PASSWORD
```

Then in `build.gradle.kts`:

```kotlin
android {
    signingConfigs {
        create("release") {
            val props = Properties()
            props.load(project.rootProject.file("local.properties").inputStream())
            
            storeFile = file(props.getProperty("keystorePath"))
            storePassword = props.getProperty("keystorePassword")
            keyAlias = props.getProperty("keyAlias")
            keyPassword = props.getProperty("keyPassword")
        }
    }
    // ... rest of config
}
```

---

## 4️⃣ Build Release APK

### In Android Studio:
```
Build > Build Bundle(s) / APK(s) > Build APK(s)
```

### Via Command Line:
```bash
./gradlew assembleRelease
```

Output: `app/release/app-release.apk`

---

## 5️⃣ Sign Manually (if needed)

```bash
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 \
  -keystore kidstaps.jks \
  app/release/app-release.apk kidstaps
```

Then verify:
```bash
jarsigner -verify -verbose app/release/app-release.apk
```

---

## 📋 Checklist for Release

- [ ] Keystore file generated and stored safely
- [ ] `local.properties` configured with keystore details
- [ ] `.gitignore` includes `*.jks`, `*.keystore`, `local.properties`
- [ ] Keystore password backed up in secure location
- [ ] `app/build.gradle.kts` configured for signing
- [ ] Build release APK successfully
- [ ] Test on real device
- [ ] Verify signature: `jarsigner -verify app/release/app-release.apk`

---

## 🆘 Common Issues

**"Keystore was tampered with, or password was incorrect"**
- Check keystore password is correct
- Verify file path is correct

**"Key was rejected by the signature algorithm"**
- Update to newer keystore format or use RSA algorithm

**"Lost keystore file"**
- You cannot update the app without the original keystore
- Would need to create new app with new package name

---

## 🔗 Resources

- [Android Official: App Signing](https://developer.android.com/studio/publish/app-signing)
- [Android Studio: Generate Signed APK](https://developer.android.com/studio/publish/app-signing)
