# Nexus Core AML - Script de ejecución automatizada
$ErrorActionPreference = "Stop"

Write-Host "--- 🛠️ Iniciando compilación híbrida (Java + C + COBOL) ---" -ForegroundColor Cyan

# 1. Configurar rutas de MSYS2 y Java
$MSYS_PATH = "C:\msys64\ucrt64\bin"
$env:Path = "$MSYS_PATH;$env:Path"

# 2. Limpieza de compilaciones previas
if (Test-Path bin) { Remove-Item -Recurse -Force bin }
mkdir bin | Out-Null
if (Test-Path lib\nativelink.dll) { Remove-Item lib\nativelink.dll }

# 3. Compilar COBOL a objeto C
Write-Host "[1/4] Compilando Core COBOL..." -ForegroundColor Yellow
Set-Location src/main/cobol
& cobc -c core.cbl -o core.o
Set-Location ../../../

# 4. Generar Header JNI y Compilar Java
Write-Host "[2/4] Compilando clases Java y Headers..." -ForegroundColor Yellow
javac -h src/main/cobol -d bin src/main/java/com/nexus/*/*.java

# 5. Compilar Puente C y enlazar DLL final
Write-Host "[3/4] Enlazando puente nativo JNI (64-bit)..." -ForegroundColor Yellow
$JAVA_INC = "$env:JAVA_HOME/include"
$JAVA_WIN = "$env:JAVA_HOME/include/win32"

& gcc -shared -I"$JAVA_INC" -I"$JAVA_WIN" -I"$MSYS_PATH/../include" `
    src/main/cobol/bridge.c src/main/cobol/core.o `
    -L"$MSYS_PATH/../lib" -lcob `
    -o lib/nativelink.dll

# 6. Ejecución del sistema
Write-Host "[4/4] Lanzando Sistema de Core Bancario..." -ForegroundColor Green
Write-Host "---------------------------------------------------"
java "-Djava.library.path=lib" -cp bin com.nexus.api.TransactionController