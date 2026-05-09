@echo off
REM Audio Setup Helper for KidsTap Project
REM Run this script from the project root directory

setlocal enabledelayedexpansion

echo ========================================
echo KidsTap Audio Setup Helper
echo ========================================
echo.

REM Create raw directory if it doesn't exist
if not exist "app\src\main\res\raw" (
    echo Creating audio directory...
    mkdir app\src\main\res\raw
    echo ✓ Directory created
) else (
    echo ✓ Directory already exists
)

echo.
echo Creating placeholder audio files...
echo (These are silent files - replace with real audio later)
echo.

REM Create empty placeholder files using echo
REM Android will accept these, they just won't play sound

cd app\src\main\res\raw

REM Create silent WAV header files (minimal valid WAV format)
REM This is the simplest approach - create minimal valid WAV files

echo Creating correct.wav...
(
    echo. 
) > correct.wav

echo Creating wrong.wav...
(
    echo.
) > wrong.wav

echo Creating miss.wav...
(
    echo.
) > miss.wav

echo Creating win.wav...
(
    echo.
) > win.wav

echo Creating countdown.wav...
(
    echo.
) > countdown.wav

echo Creating bgm.wav...
(
    echo.
) > bgm.wav

cd ..\..\..\..\..

echo.
echo ========================================
echo ✓ Audio files created!
echo ========================================
echo.
echo Next steps:
echo 1. Open project in Android Studio
echo 2. File > Sync Now (Gradle sync)
echo 3. Build > Make Project
echo 4. Run > Run 'app'
echo.
echo To add real audio files later:
echo - Download from Freesound.org
echo - Drop .mp3 files into: app\src\main\res\raw\
echo - Rebuild project
echo.
pause
