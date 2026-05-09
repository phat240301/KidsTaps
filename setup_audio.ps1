# KidsTap Audio Setup - PowerShell Script
# Run this from project root: powershell -ExecutionPolicy Bypass -File setup_audio.ps1

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "KidsTap Audio Setup Helper" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Create directory
$rawPath = "app\src\main\res\raw"
if (-not (Test-Path $rawPath)) {
    Write-Host "Creating audio directory..." -ForegroundColor Yellow
    New-Item -ItemType Directory -Path $rawPath -Force | Out-Null
    Write-Host "✓ Directory created" -ForegroundColor Green
} else {
    Write-Host "✓ Directory already exists" -ForegroundColor Green
}

Write-Host ""
Write-Host "Creating placeholder audio files..." -ForegroundColor Yellow
Write-Host "(These are silent - replace with real .mp3 files from Freesound.org)" -ForegroundColor Gray
Write-Host ""

# Create placeholder WAV files
$files = @("correct.wav", "wrong.wav", "miss.wav", "win.wav", "countdown.wav", "bgm.wav")

foreach ($file in $files) {
    $filePath = Join-Path $rawPath $file
    # Create minimal WAV file structure
    $wavHeader = @(
        0x52, 0x49, 0x46, 0x46,  # "RIFF"
        0x24, 0x00, 0x00, 0x00,  # File size - 8
        0x57, 0x41, 0x56, 0x45,  # "WAVE"
        0x66, 0x6D, 0x74, 0x20,  # "fmt "
        0x10, 0x00, 0x00, 0x00,  # Subchunk size
        0x01, 0x00,              # PCM format
        0x01, 0x00,              # 1 channel
        0x44, 0xAC,              # 44100 Hz sample rate
        0x88, 0x58,              # Byte rate
        0x02, 0x00,              # Block align
        0x10, 0x00,              # Bits per sample
        0x64, 0x61, 0x74, 0x61,  # "data"
        0x00, 0x00, 0x00, 0x00   # Subchunk2 size (0 samples = silent)
    ) -as [byte[]]
    
    [System.IO.File]::WriteAllBytes($filePath, $wavHeader)
    Write-Host "✓ Created $file" -ForegroundColor Green
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "✓ Setup Complete!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Next Steps:" -ForegroundColor Yellow
Write-Host "1. Open Android Studio" -ForegroundColor White
Write-Host "2. File > Open > select KidsTap folder" -ForegroundColor White
Write-Host "3. File > Sync Now (wait for Gradle)" -ForegroundColor White
Write-Host "4. Build > Make Project" -ForegroundColor White
Write-Host "5. Run > Run 'app'" -ForegroundColor White
Write-Host ""
Write-Host "To add real audio:" -ForegroundColor Yellow
Write-Host "- Download .mp3 from Freesound.org" -ForegroundColor Gray
Write-Host "- Drop into: app/src/main/res/raw/" -ForegroundColor Gray
Write-Host "- Rebuild project" -ForegroundColor Gray
Write-Host ""
Read-Host "Press Enter to close"
