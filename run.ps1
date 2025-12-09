# run.ps1 - compile and run for PowerShell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force
if (!(Test-Path out)) { New-Item -ItemType Directory -Path out | Out-Null }
# Gather all .java files (absolute paths)
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
if ($files.Count -eq 0) {
    Write-Host "No .java files found. Make sure you're in project root." -ForegroundColor Red
    exit 1
}
# Compile
javac -d out $files
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    exit $LASTEXITCODE
}
# Run
java -cp out Main
