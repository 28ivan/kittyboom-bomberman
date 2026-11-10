@echo off
title KittyBoom - Bomberman
set "JAVA_MODERNO=%USERPROFILE%\.p2\pool\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_23.0.2.v20250131-0604\jre\bin\javaw.exe"

if exist "%JAVA_MODERNO%" (
    start "" "%JAVA_MODERNO%" -jar "%~dp0Bomberman.jar"
) else (
    start "" javaw -jar "%~dp0Bomberman.jar"
)
