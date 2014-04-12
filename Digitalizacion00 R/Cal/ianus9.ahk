IfWinExist, ahk_class IEFrame,,,
{
	GroupAdd, GrupoWinIanus, ahk_class IEFrame,,,
	GroupActivate, GrupoWinIanus, R
	WinGet, ianus1, ID, ahk_class IEFrame,,,
	GroupActivate, GrupoWinIanus,
	WinGet, ianus2, ID, ahk_class IEFrame,,,
}

F5::
	WinSet, AlwaysOnTop, Off, ahk_class IEFrame
	Sleep, 100

	GroupAdd, GrupoWinIanus, ahk_class IEFrame,,,
	GroupActivate, GrupoWinIanus, R
	WinGet, ianus1, ID, ahk_class IEFrame,,,
	GroupActivate, GrupoWinIanus,
	WinGet, ianus2, ID, ahk_class IEFrame,,,

return



F7::
	WinActivate, ahk_id %ianus1%
	WinWaitActive, ahk_id %ianus1%,,%tmax%,,
	WinMaximize, ahk_id %ianus1%,,,
	Sleep, 100
	WinSet, AlwaysOnTop, Off, ahk_id %ianus2%
	Sleep, 100
	WinSet, AlwaysOnTop, On, ahk_id %ianus1%
return
	

F8::
	WinActivate, ahk_id %ianus2%
	WinWaitActive, ahk_id %ianus2%,,%tmax%,,
	WinMaximize, ahk_id %ianus2%,,,
	Sleep, 100
	WinSet, AlwaysOnTop, Off, ahk_id %ianus1%
	Sleep, 100
	WinSet, AlwaysOnTop, On, ahk_id %ianus2%
return


F9::
	WinActivate, ahk_class IEFrame,,,
	Sleep, 100
	Click 990, 100
return


F10::
	WinActivate, ahk_id %ianus1%
	WinWaitActive, ahk_id %ianus1%,,%tmax%,,
	WinMaximize, ahk_id %ianus1%,,,
	Sleep, 100
	Click 310, 671
return
	

F12::
	WinActivate, ahk_id %ianus2%
	WinWaitActive, ahk_id %ianus2%,,%tmax%,,
	WinMaximize, ahk_id %ianus2%,,,
	Sleep, 100
	Click 310, 671
return


Escape::
	ExitApp
Return