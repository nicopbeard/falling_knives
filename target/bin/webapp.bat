@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\postgresql\postgresql\42.2.12\postgresql-42.2.12.jar;"%REPO%"\com\google\cloud\sql\postgres-socket-factory\1.1.0\postgres-socket-factory-1.1.0.jar;"%REPO%"\com\google\cloud\sql\jdbc-socket-factory-core\1.1.0\jdbc-socket-factory-core-1.1.0.jar;"%REPO%"\com\google\apis\google-api-services-sqladmin\v1beta4-rev20190827-1.30.1\google-api-services-sqladmin-v1beta4-rev20190827-1.30.1.jar;"%REPO%"\com\google\api-client\google-api-client\1.30.1\google-api-client-1.30.1.jar;"%REPO%"\com\google\oauth-client\google-oauth-client\1.30.1\google-oauth-client-1.30.1.jar;"%REPO%"\com\google\auth\google-auth-library-oauth2-http\0.21.1\google-auth-library-oauth2-http-0.21.1.jar;"%REPO%"\com\google\auto\value\auto-value-annotations\1.7.2\auto-value-annotations-1.7.2.jar;"%REPO%"\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;"%REPO%"\com\google\auth\google-auth-library-credentials\0.21.1\google-auth-library-credentials-0.21.1.jar;"%REPO%"\com\google\http-client\google-http-client\1.36.0\google-http-client-1.36.0.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.12\httpclient-4.5.12.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\commons-codec\commons-codec\1.11\commons-codec-1.11.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.13\httpcore-4.4.13.jar;"%REPO%"\com\google\j2objc\j2objc-annotations\1.3\j2objc-annotations-1.3.jar;"%REPO%"\io\opencensus\opencensus-api\0.24.0\opencensus-api-0.24.0.jar;"%REPO%"\io\grpc\grpc-context\1.22.1\grpc-context-1.22.1.jar;"%REPO%"\io\opencensus\opencensus-contrib-http-util\0.24.0\opencensus-contrib-http-util-0.24.0.jar;"%REPO%"\com\google\http-client\google-http-client-jackson2\1.36.0\google-http-client-jackson2-1.36.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.11.1\jackson-core-2.11.1.jar;"%REPO%"\com\google\guava\guava\29.0-android\guava-29.0-android.jar;"%REPO%"\com\google\guava\failureaccess\1.0.1\failureaccess-1.0.1.jar;"%REPO%"\com\google\guava\listenablefuture\9999.0-empty-to-avoid-conflict-with-guava\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;"%REPO%"\org\checkerframework\checker-compat-qual\2.5.5\checker-compat-qual-2.5.5.jar;"%REPO%"\com\google\errorprone\error_prone_annotations\2.3.4\error_prone_annotations-2.3.4.jar;"%REPO%"\com\github\jnr\jnr-unixsocket\0.38.0\jnr-unixsocket-0.38.0.jar;"%REPO%"\com\github\jnr\jnr-ffi\2.2.0\jnr-ffi-2.2.0.jar;"%REPO%"\com\github\jnr\jffi\1.3.0\jffi-1.3.0.jar;"%REPO%"\com\github\jnr\jffi\1.3.0\jffi-1.3.0-native.jar;"%REPO%"\org\ow2\asm\asm-commons\7.1\asm-commons-7.1.jar;"%REPO%"\com\github\jnr\jnr-a64asm\1.0.0\jnr-a64asm-1.0.0.jar;"%REPO%"\com\github\jnr\jnr-x86asm\1.0.2\jnr-x86asm-1.0.2.jar;"%REPO%"\com\github\jnr\jnr-constants\0.10.0\jnr-constants-0.10.0.jar;"%REPO%"\com\github\jnr\jnr-enxio\0.32.0\jnr-enxio-0.32.0.jar;"%REPO%"\com\github\jnr\jnr-posix\3.1.0\jnr-posix-3.1.0.jar;"%REPO%"\org\ow2\asm\asm-util\8.0.1\asm-util-8.0.1.jar;"%REPO%"\org\ow2\asm\asm\8.0.1\asm-8.0.1.jar;"%REPO%"\org\ow2\asm\asm-tree\8.0.1\asm-tree-8.0.1.jar;"%REPO%"\org\ow2\asm\asm-analysis\8.0.1\asm-analysis-8.0.1.jar;"%REPO%"\com\example\FallingKnives\1.0-SNAPSHOT\FallingKnives-1.0-SNAPSHOT.war

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" App %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
