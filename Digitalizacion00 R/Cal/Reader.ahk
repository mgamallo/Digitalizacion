#NoEnv  ; Recommended for performance and compatibility with future AutoHotkey releases.
SendMode Input  ; Recommended for new scripts due to its superior speed and reliability.
SetWorkingDir %A_ScriptDir%  ; Ensures a consistent starting directory.
#WinActivateForce
#SingleInstance ignore

t_max=15
StringLeft, unidad, A_WorkingDir, 2
rutapdf=%unidad%\DIGITALIZACIÓN\DOC. ANULADO.pdf
p=

WinClose,ahk_class AdobeAcrobat,,%t_max%
WinClose,ahk_class AcrobatSDIWindow,,%t_max%

;/** Abre administrador de tareas
Run, taskmgr
WinWaitActive,Administrador, ,%t_max%
Sleep,250
Send, {Altdown}a{Altup}
Sleep,100
Send, {Shiftdown}n{shiftup}
Sleep,250

;/**Abre ventana "Abrir con" para abrir el archivo indicado en la ruta especificada
Sendinput, rundll32.exe shell32.dll, OpenAs_RunDLL %rutapdf%
Sleep, 100
Send, {ENTER}
Sleep, 250
WinWaitActive,Abrir con, ,%t_max%
Sleep,1
if A_ComputerName=HPCHPMAHC33p
{
	ControlFocus,SysListView321,Abrir,,,
	Sendinput,Adobe Reader
	Sleep, 250
	ControlGet,item_seleccionado, List, Selected, SysListView321,Abrir,,,
	if item_seleccionado contains Adobe Reader
	{
		Control, Check,,Usar,Abrir,,,
		Sleep, 250
		ControlClick,Aceptar,Abrir,,,2,,,
		Sleep, 100
	}else{
		MsgBox, El programa "Adobe Reader" no se encuentra en la lista
		ExitApp
	}
}else{
	Sendinput, {DOWN}
	Control, Check,,Utilizar,Abrir,,,
	Sleep, 250
	ControlClick,Aceptar,Abrir,,,2,,,
	Sleep, 100
     }

WinKill,ahk_class #32770,,%t_max%
Sleep,1
WinKill,ahk_class #32770,,%t_max%
Sleep,1
WinWaitActive,Adobe, ,3
WinClose,ahk_class AcrobatSDIWindow,,%t_max%
WinClose,ahk_class AdobeAcrobat,,%t_max%

ExitApp