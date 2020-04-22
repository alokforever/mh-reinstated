;-------get directories For all mods -------
;load mods
Function setModDirs()

; Define what folder to start with
folder$ = mapsBaseDir$

; Open up the directory, and assign the handle to myDir
myDir=ReadDir(folder$)
; Let's loop forever Until we run out of files/folders to list!
n = 1
modFolder$(1) = folder$+"Original"+"\"
modsAmount=1
Repeat
; Assign the Next entry in the folder to file$
file$=NextFile$(myDir)

file$=Lower(file$)
If file$="." Or file$=".." Or file$="original" Then
    
Else
    If file$="" Then Exit
    ; Use FileType to determine If it is a folder (value 2)
    If FileType(folder$+file$) = 2 Then
        n=n+1
        modFolder$(n) = folder$+file$+"\"
        modName(n) = Lower(file$)
        modsAmount=modsAmount+1
        ;print n+"="+modFolder$(n)
    End If
EndIf

Forever
; Properly close the open folder
CloseDir myDir

End Function 
;-------save maps/secret stuff ----
Function saveMaps()
file = WriteFile(modFolder(curModId) + "save.dat")

For i=1 To maxAmap
    WriteInt file, mapOpen(i)
    WriteInt file, mapSecret(i)
Next
For i=1 To maxVsmap
    WriteInt file, vsMapOpen(i)
Next
For i=1 To maxCTFmap
    WriteInt file, CTFmapOpen(i)
Next

CloseFile file

End Function
;--------- load maps/secret stuff -------
Function loadMaps()

If FileType(modFolder(curModId) + "save.dat")=0 Then
    temp=WriteFile(modFolder(curModId) + "save.dat")    ;create file
    For n=1 To 500
        WriteInt temp, 0
    Next
    CloseFile temp
EndIf

file=ReadFile(modFolder(curModId) + "save.dat")

For i=1 To maxAmap
    mapOpen(i) = ReadInt (file)
    mapSecret(i) = ReadInt (file)
    totalSecrets = totalSecrets + mapSecret(i)
Next
For i=1 To maxVsmap
    vsMapOpen(i) = ReadInt (file)
Next
For i=1 To maxCTFmap
    CTFmapOpen(i) = ReadInt (file)
Next

For n=1 To 3    ;unlock first 3 characters by default for any mod
    characterOpen(n)=1
Next

If curModId = 1 Then
    mapOpen(1)=1    ;unlock first adventure stage by default
    For n=1 To 4    ;unlock first 4 versus levels
        vsmapOpen(n)=1
    Next
    CTFmapOpen(1)=1    ;unlock first CTF stage
Else
    mapOpen(1)=1    ;unlock first adventure stage by default
    For i=1 To 50    ;unlock all versus levels
        vsmapOpen(i)=1
        CTFmapOpen(i)=1
    Next
EndIf

CloseFile file

End Function 

;------------------save general maps data----
Function saveData()
file = WriteFile("cfg/maps.dat")

WriteInt file, maxAmap
WriteInt file, maxVsMap
WriteInt file, maxCTFMap
WriteInt file, lastAmap
WriteInt file, 0
WriteInt file, 0
WriteInt file, 0
WriteInt file, 0
WriteInt file, 0
WriteInt file, 0

CloseFile file

End Function 
;-------Load general maps data ---------------------
Function loadData()

file=ReadFile("cfg/maps.dat")

maxAmap = ReadInt (file)
maxVsMap = ReadInt (file)
maxCTFMap = ReadInt (file)
lastAmap = ReadInt (file)
CloseFile file

End Function 

;--------- load speed run timers -------
Function loadSpeedrun()

If FileType("cfg/speedrun.dat")=0 Then
    temp=WriteFile("cfg/speedrun.dat")    ;create file
    For n=1 To 5100
        WriteInt temp, 0
    Next
    CloseFile temp
EndIf

file=ReadFile("cfg/speedrun.dat")

For i=1 To maxMap
    bestMapTime(i) = ReadInt (file)
    fastestHeroPerMap(i) = ReadInt (file)
    For j=1 To 100 ;Max Heroes
        fastestHeroTimePerMap(i,j) = ReadInt (file)
    Next
Next

End Function

;--------- save speed run timers -------
Function saveSpeedrun()
    Local timeTaken = (mapTimeLapse)
    Local isTimeChanged=0
    
    ;Check if new record
    If timeTaken < bestMapTime(curMap) Or bestMapTime(curMap)=0 Then
        isTimeChanged=1:bestMapTime(curMap)=timeTaken
        fastestHeroPerMap(curMap)=curGuy(1)
    End If
    For i=1 To zamountPlaying
        If timeTaken < fastestHeroTimePerMap(curMap,curGuy(i)) Or fastestHeroTimePerMap(curMap,curGuy(i))=0
            If zLife(i) > 0 Then
                isTimeChanged=1
                fastestHeroTimePerMap(curMap,curGuy(i)) = timeTaken
            End If
        End If
    Next
    
    If isTimeChanged <> 0 Then
        file = WriteFile("cfg/speedrun.dat")
        For i=1 To maxMap
            WriteInt file, bestMapTime(i)
            WriteInt file, fastestHeroPerMap(i)
            For j=1 To 100 ;Max Heroes
                WriteInt file, fastestHeroTimePerMap(i,j)
            Next
        Next
        
        CloseFile file
    End If
End Function
;------------------- Start menu For vs stage Select ---------------------------------
Function vsSelectMap()

ClsColor 0,0,100
Color 200,200,200   ;For the white rectangles around thumbnails
buttonAmount=54 ;maxAmap
SetBuffer BackBuffer()
Repeat

If KeyHit(1) Then
    gameStart=2
    If vsmode=0 Then
        defineButtons(1)
    Else
        defineButtons(0)
    EndIf
EndIf
pointers
For n=1 To ButtonAmount
    If vsMode=0 Then open(n)=mapOpen(n)
    If vsMode=1 Then
        If gameMode=2 Then
            open(n)=CTFmapOpen(n)
        Else
            open(n)=VsmapOpen(n)
        EndIf
    EndIf

    If clickedBut(n) And open(n)=1 Then
        If gamesound Then PlaySound clicksnd
        curMap=n:gameStart=1
    EndIf
Next

If clickedBut(54) Or clickedBut(53) Then    ;clicked arrow to change mod
    If gamesound Then PlaySound clicksnd
    If clickedBut(54) Then
        curModId=curModId+1
        If curModId > modsAmount Then curModId=1
    Else
        curModId=curModId-1
        If curModId < 1 Then curModId=modsAmount
    EndIf
    loadMaps()
        
    gameStart=3
EndIf

;-------stage menu rendering ----------
Cls
DrawImage butpic(70),24,8
pri 85,16,strinfo$(22) 

x=400 : y=8
fontType=1
strSize = pri(x+48, 11, modName(curModId))
fontType=2
;draws arrows for selecting mod
DrawImage arrow1,x+8,y+5
DrawImage arrow2,x+56+strSize, y+5
b=53
xBut(b)=x+8:yBut(b)=y+5:wBut(b)=32:hBut(b)=24
b=54
xBut(b)=x+56+strSize : yBut(b)=y+5:wBut(b)=32:hBut(b)=24


y=64;:mn=0
x=32
sp1=200 : sp2=144    ;x/y space between maps
If vsMode=0 Then
    sp1=96 : sp2=72
EndIf
For n = 1 To 50 ;maxAmap
  If vsMode=0 Then  ;If adventure mode
    If mapOpen(n)=1 And mapTn(n) <> 0 Then
        xBut(n)=x:yBut(n)=y:wBut(n)=ImageWidth(mapTn(n)):hBut(n)=ImageHeight(mapTn(n))
    EndIf
    If mapOpen(n)=1 And mapTn(n) <> 0 Then
        DrawImage mapTn(n),x,y
    EndIf
    
    If mapOpen(n)=1 And mapTn(n) <> 0 Then Rect x,y,wBut(n),hBut(n),0

  ElseIf gameMode = 2 Then  ;If CTF mode
    If CTFmapOpen(n)=1 And mapTn(n) <> 0 Then
        xBut(n)=x:yBut(n)=y:wBut(n)=ImageWidth(mapTn(n)):hBut(n)=ImageHeight(mapTn(n))
    EndIf
    If CTFmapOpen(n)=1 And mapTn(n) <> 0 Then
        DrawImage mapTn(n),x,y
    Else
        nada=nada
    EndIf
    Color 200,200,200
    If CTFmapOpen(n)=1 And mapTn(n) <> 0 Then Rect x,y,ImageWidth(mapTn(n)),ImageHeight(mapTn(n)) ,0

  ElseIf gameMode <> 2 Then ;If any other vs mode
    If vsmapOpen(n)=1 And mapTn(n) <> 0 Then
        xBut(n)=x:yBut(n)=y:wBut(n)=ImageWidth(mapTn(n)):hBut(n)=ImageHeight(mapTn(n))
    EndIf
    If vsmapOpen(n)=1 And mapTn(n) <> 0 Then
        DrawImage mapTn(n),x,y
    Else
        nada=nada
    EndIf
    Color 200,200,200
    If vsmapOpen(n)=1 And mapTn(n) <> 0 Then Rect x,y,ImageWidth(mapTn(n)),ImageHeight(mapTn(n)) ,0

  EndIf

    x=x+sp1
    If x > 960 Then x=32: y=y+sp2
Next

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

Flip
Until gameStart > 0

End Function 
;----- draws box ---------------
Function textBox(w,h)
n1=1024-(w) : x=(n1/2)
n2=768-(h) : y=(n2/2)

Color 0,0,0
Rect x,y,w,h,1
Color 255,255,255
Rect x,y,w,h,0
Color 120,120,120
Rect x+1,y+1,w-2,h-2,0

Return y

End Function 
;------------ displays after stage screen ----------------
Function statsScreen()

SetBuffer BackBuffer()
ClsColor 0,50,100
Cls
statsImg=LoadImage(gfxStuffDir$ + "stats.bmp")
Color 255,255,255
DrawImage statsImg,0,0
Local timeTaken=mapTimeLapse
strInfo$(36)="stage " + (curMap-1) + " complete!"
If timeTaken < bestMapTime(curMap) Or bestMapTime(curMap)=0
    strInfo$(36)=strInfo$(36) + " new record!"
End If
pri priW(strInfo$(36)),120, strInfo$(36)

If checkWhatsOpen()=1 Then pri 160,160, strInfo$(61)

pri 90,190, strInfo$(35)+" "+secretsFound+" / "+secretsAmount
drawimage clockPic,530,176
Local timeTakenStr$=getTimeTaken$(timeTaken)
Local bestTime$=getTimeTaken$(bestMapTime(curMap))
Local timeTakenPerHero$
If bestTime$ = "00:00:00" Then bestTime$=timeTakenStr$
pri 567,190, timeTakenStr$ + " / " + bestTime$
x=160:y=265
For i=1 To 4
  xOffset=0:yOffset=0
  Select curGuy(i)
  Case 2:
    xOffset=3
  Case 3:
    xOffset=4
  Case 4:
    xOffset=5
  Case 5:
    xOffset=8
    yOffset=8
  Case 6:
    xOffset=32
  Case 7:
    xOffset=15
  Case 8:
    yOffset=10
  Case 14:
    xOffset=15
    yOffset=10
  Case 15:
    xOffset=45
    yOffset=14
  Case 16:
    yOffset=8
  Case 17:
    xOffset=30
    yOffset=3
  Case 18:
    xOffset=35
    yOffset=10
  Case 19:
    xOffset=30
  default:
    xOffset=0
  End Select
  
  If zwason(i)=1 Then
    ;Character-specific hits taken:
    drawimage zPic(curGuy(i),1,0),x-(64+xOffset),y-(16+yOffset)
    pri x,y, strInfo$(38) + zGotHitsAmount(i)
    
    ;Character-specific timer:
    If vsMode=0 Then
        drawimage clockPic,x+348,y-8
        timeTakenPerHero$=getTimeTaken$(fastestHeroTimePerMap(curMap,curGuy(i)))
        If timeTakenPerHero$="00:00:00" Then timeTakenPerHero$=timeTakenStr$
        pri x+388,y, timeTakenStr$ + " / " + timeTakenPerHero$
    End If
    y=y+90
  EndIf
Next

pri priW(strInfo$(37)),600, strInfo$(37)
pri priW(strInfo$(101)),650, strInfo$(101)
saveSpeedrun()

Flip
Delay 200
FlushKeys : flushjoy
waitInput()
closeScreen(Rand(1,4),0)

FreeImage statsImg:statsImg=0

End Function 
;------------ versus results ----------------
Function vsStatsScreen()

ClsColor 0,50,100
Cls
SetBuffer BackBuffer()
statsImg=LoadImage(gfxStuffDir$ + "stats.bmp")
Color 255,255,255
DrawImage statsImg,0,0

y = 160
If winner=1000 Then ;If its a draw
    pri priW(strInfo(34)),y, strInfo$(34)
Else
    Select gameMode
    Case 2  ;check CTF winner
        If winner=1 Then ts$= Strinfo$(26) Else ts$= Strinfo$(27)
        pri priW(strInfo(28)+ts$),y, ts$ + Strinfo$(28)
    Default ;check winner
        If zTeam(winner)=1 Then
            pri priW(strInfo(24)),y, Strinfo$(24)
        EndIf
        If zTeam(winner)=2 Then
            pri priW(strInfo(25)),y, Strinfo$(25)
        EndIf
        If zteam(winner) <> 1 And zteam(winner) <> 2 Then
            drawimage zPic(curGuy(winner),1,0),(priW(strInfo(12)+winner+Strinfo$(13)))-45 ,135-imageheight(zPic(curGuy(winner),1,0))
            pri priW(strInfo(12)+winner+Strinfo$(13)),y, Strinfo$(12) +winner+ Strinfo$(13)
        EndIf
    End Select
EndIf

x=160:y=280:xOffset=0
For i=1 To 4
 If curGuy(i)=6 Then
    xOffset=32
 Else
   xOffset=0
 End If
 If zwason(i)=1 Then
  DrawImage zPic(curGuy(i),1,0),x-(64+xOffset),y-16
  pri x,y, strInfo$(38) + zGotHitsAmount(i)
  If zGotHitsAmount(i)=0 Then pri x+368,y,strInfo$(39)
  y=y+80
 EndIf
Next

pri priW(strInfo$(37)),624, strInfo$(37)

Flip
Delay 300
FlushKeys() : flushjoy()
waitInput()
closeScreen(Rand(1,4),0)

FreeImage statsImg:statsImg=0
End Function 
;--------- wait any key/joy button to be pressed ----------
Function waitInput()
pressed=0
flushkeys() : flushjoy()
Repeat
 If KeyHit(1) Then pressed=1
 If KeyHit(28) Then pressed=1
 If KeyHit(57) Then pressed=1
 If KeyHit(63)=1 Then curMap=curMap-1:pressed=1
 For n=1 To 4
  Select zController(n)
   Case 0
    If KeyHit(shotK(n)) Or KeyHit(specialK(n)) Then pressed=1
   Case 1
    If JoyHit(shotK(n),controllerPort(n)) Or JoyHit(specialK(n),controllerPort(n)) Then pressed=1
  End Select
 Next
Delay(10)
Until pressed=1
flushkeys() : flushjoy()

End Function 

;------------- draws font------------------------------
Function pri(x,y,st$)

xstart=x
For i=1 To Len(st$)
  Select Mid$(st$,i,1)
    Case "0" : DrawImage letter(fontType,0),x,y : x = x + (letterWidth(fontType,0) + fontSpace)
    Case "1" : DrawImage letter(fontType,1),x,y : x = x + (letterWidth(fontType,1) + fontSpace)
    Case "2" : DrawImage letter(fontType,2),x,y : x = x + (letterWidth(fontType,2) + fontSpace)
    Case "3" : DrawImage letter(fontType,3),x,y : x = x + (letterWidth(fontType,3) + fontSpace)
    Case "4" : DrawImage letter(fontType,4),x,y : x = x + (letterWidth(fontType,4) + fontSpace)
    Case "5" : DrawImage letter(fontType,5),x,y : x = x + (letterWidth(fontType,5) + fontSpace)
    Case "6" : DrawImage letter(fontType,6),x,y : x = x + (letterWidth(fontType,6) + fontSpace)
    Case "7" : DrawImage letter(fontType,7),x,y : x = x + (letterWidth(fontType,7) + fontSpace)
    Case "8" : DrawImage letter(fontType,8),x,y : x = x + (letterWidth(fontType,8) + fontSpace)
    Case "9" : DrawImage letter(fontType,9),x,y : x = x + (letterWidth(fontType,9) + fontSpace)
    Case "a" : DrawImage letter(fontType,10),x,y : x = x + (letterWidth(fontType,10) + fontSpace)
    Case "b" : DrawImage letter(fontType,11),x,y : x = x + (letterWidth(fontType,11) + fontSpace)
    Case "c" : DrawImage letter(fontType,12),x,y : x = x + (letterWidth(fontType,12) + fontSpace)
    Case "d" : DrawImage letter(fontType,13),x,y : x = x + (letterWidth(fontType,13) + fontSpace)
    Case "e" : DrawImage letter(fontType,14),x,y : x = x + (letterWidth(fontType,14) + fontSpace)
    Case "f" : DrawImage letter(fontType,15),x,y : x = x + (letterWidth(fontType,15) + fontSpace)
    Case "g" : DrawImage letter(fontType,16),x,y : x = x + (letterWidth(fontType,16) + fontSpace)
    Case "h" : DrawImage letter(fontType,17),x,y : x = x + (letterWidth(fontType,17) + fontSpace)
    Case "i" : DrawImage letter(fontType,18),x,y : x = x + (letterWidth(fontType,18) + fontSpace)
    Case "j" : DrawImage letter(fontType,19),x,y : x = x + (letterWidth(fontType,19) + fontSpace)
    Case "k" : DrawImage letter(fontType,20),x,y : x = x + (letterWidth(fontType,20) + fontSpace)
    Case "l" : DrawImage letter(fontType,21),x,y : x = x + (letterWidth(fontType,21) + fontSpace)
    Case "m" : DrawImage letter(fontType,22),x,y : x = x + (letterWidth(fontType,22) + fontSpace)
    Case "n" : DrawImage letter(fontType,23),x,y : x = x + (letterWidth(fontType,23) + fontSpace)
    Case "o" : DrawImage letter(fontType,24),x,y : x = x + (letterWidth(fontType,24) + fontSpace)
    Case "p" : DrawImage letter(fontType,25),x,y : x = x + (letterWidth(fontType,25) + fontSpace)
    Case "q" : DrawImage letter(fontType,26),x,y : x = x + (letterWidth(fontType,26) + fontSpace)
    Case "r" : DrawImage letter(fontType,27),x,y : x = x + (letterWidth(fontType,27) + fontSpace)
    Case "s" : DrawImage letter(fontType,28),x,y : x = x + (letterWidth(fontType,28) + fontSpace)
    Case "t" : DrawImage letter(fontType,29),x,y : x = x + (letterWidth(fontType,29) + fontSpace)
    Case "u" : DrawImage letter(fontType,30),x,y : x = x + (letterWidth(fontType,30) + fontSpace)
    Case "v" : DrawImage letter(fontType,31),x,y : x = x + (letterWidth(fontType,31) + fontSpace)
    Case "x" : DrawImage letter(fontType,32),x,y : x = x + (letterWidth(fontType,32) + fontSpace)
    Case "w" : DrawImage letter(fontType,33),x,y : x = x + (letterWidth(fontType,33) + fontSpace)
    Case "y" : DrawImage letter(fontType,34),x,y : x = x + (letterWidth(fontType,34) + fontSpace)
    Case "z" : DrawImage letter(fontType,35),x,y : x = x + (letterWidth(fontType,35) + fontSpace)
    Case "." : DrawImage letter(fontType,36),x,y : x = x + (letterWidth(fontType,36) + fontSpace)
    Case "," : DrawImage letter(fontType,37),x,y : x = x + (letterWidth(fontType,37) + fontSpace)
    Case "-" : DrawImage letter(fontType,38),x,y : x = x + (letterWidth(fontType,38) + fontSpace)
    Case "(" : DrawImage letter(fontType,39),x,y : x = x + (letterWidth(fontType,39) + fontSpace)
    Case ")" : DrawImage letter(fontType,40),x,y : x = x + (letterWidth(fontType,40) + fontSpace)
    Case "/" : DrawImage letter(fontType,41),x,y : x = x + (letterWidth(fontType,41) + fontSpace)
    Case " " : DrawImage letter(fontType,42),x,y : x = x + (letterWidth(fontType,42) + fontSpace)
    Case "+" : DrawImage letter(fontType,43),x,y : x = x + (letterWidth(fontType,43) + fontSpace)
    Case "*" : DrawImage letter(fontType,44),x,y : x = x + (letterWidth(fontType,44) + fontSpace)
    Case "&" : DrawImage letter(fontType,45),x,y : x = x + (letterWidth(fontType,45) + fontSpace)
    Case "=" : DrawImage letter(fontType,46),x,y : x = x + (letterWidth(fontType,46) + fontSpace)
    Case ":" : DrawImage letter(fontType,47),x,y : x = x + (letterWidth(fontType,47) + fontSpace)
    Case "!" : DrawImage letter(fontType,48),x,y : x = x + (letterWidth(fontType,48) + fontSpace)
    Case "?" : DrawImage letter(fontType,49),x,y : x = x + (letterWidth(fontType,49) + fontSpace)
    Case "'" : DrawImage letter(fontType,50),x,y : x = x + (letterWidth(fontType,50) + fontSpace)
    Case "?" : DrawImage letter(fontType,51),x,y : x = x + (letterWidth(fontType,51) + fontSpace)
    Case "?" : DrawImage letter(fontType,52),x,y : x = x + (letterWidth(fontType,52) + fontSpace)
    Case "?" : DrawImage letter(fontType,53),x,y : x = x + (letterWidth(fontType,53) + fontSpace)

    Default
    x=x+12

    End Select
Next

Return x - xstart

End Function 
;----------- get word width ------------------------------------------
Function priW(s$)
w=0

For i=1 To Len(s$)
 Select Mid(s$,i,1)
  Case "0": w=w+letterWidth(fontType,0)+ fontSpace
  Case "1": w=w+letterWidth(fontType,1)+ fontSpace
  Case "2": w=w+letterWidth(fontType,2)+ fontSpace
  Case "3": w=w+letterWidth(fontType,3)+ fontSpace
  Case "4": w=w+letterWidth(fontType,4)+ fontSpace
  Case "5": w=w+letterWidth(fontType,5)+ fontSpace
  Case "6": w=w+letterWidth(fontType,6)+ fontSpace
  Case "7": w=w+letterWidth(fontType,7)+ fontSpace
  Case "8": w=w+letterWidth(fontType,8)+ fontSpace
  Case "9": w=w+letterWidth(fontType,9)+ fontSpace
  Case "a": w=w+letterWidth(fontType,10)+ fontSpace
  Case "b": w=w+letterWidth(fontType,11)+ fontSpace
  Case "c": w=w+letterWidth(fontType,12)+ fontSpace
  Case "d": w=w+letterWidth(fontType,13)+ fontSpace
  Case "e": w=w+letterWidth(fontType,14)+ fontSpace
  Case "f": w=w+letterWidth(fontType,15)+ fontSpace
  Case "g": w=w+letterWidth(fontType,16)+ fontSpace
  Case "h": w=w+letterWidth(fontType,17)+ fontSpace
  Case "i": w=w+letterWidth(fontType,18)+ fontSpace
  Case "j": w=w+letterWidth(fontType,19)+ fontSpace
  Case "k": w=w+letterWidth(fontType,20)+ fontSpace
  Case "l": w=w+letterWidth(fontType,21)+ fontSpace
  Case "m": w=w+letterWidth(fontType,22)+ fontSpace
  Case "n": w=w+letterWidth(fontType,23)+ fontSpace
  Case "o": w=w+letterWidth(fontType,24)+ fontSpace
  Case "p": w=w+letterWidth(fontType,25)+ fontSpace
  Case "q": w=w+letterWidth(fontType,26)+ fontSpace
  Case "r": w=w+letterWidth(fontType,27)+ fontSpace
  Case "s": w=w+letterWidth(fontType,28)+ fontSpace
  Case "t": w=w+letterWidth(fontType,29)+ fontSpace
  Case "u": w=w+letterWidth(fontType,30)+ fontSpace
  Case "v": w=w+letterWidth(fontType,31)+ fontSpace
  Case "x": w=w+letterWidth(fontType,32)+ fontSpace
  Case "w": w=w+letterWidth(fontType,33)+ fontSpace
  Case "y": w=w+letterWidth(fontType,34)+ fontSpace
  Case "z": w=w+letterWidth(fontType,35)+ fontSpace
  Case ".": w=w+letterWidth(fontType,36)+ fontSpace
  Case ",": w=w+letterWidth(fontType,37)+ fontSpace
  Case "-": w=w+letterWidth(fontType,38)+ fontSpace
  Case "(": w=w+letterWidth(fontType,39)+ fontSpace
  Case ")": w=w+letterWidth(fontType,40)+ fontSpace
  Case "/": w=w+letterWidth(fontType,41)+ fontSpace
  Case " ": w=w+letterWidth(fontType,42)+ fontSpace
  Case "+": w=w+letterWidth(fontType,43)+ fontSpace
  Case "*": w=w+letterWidth(fontType,44)+ fontSpace
  Case "&": w=w+letterWidth(fontType,45)+ fontSpace
  Case "=": w=w+letterWidth(fontType,46)+ fontSpace
  Case ":": w=w+letterWidth(fontType,47)+ fontSpace
  Case "!": w=w+letterWidth(fontType,48)+ fontSpace
  Case "?": w=w+letterWidth(fontType,49)+ fontSpace
  Case "'": w=w+letterWidth(fontType,50)+ fontSpace
  Case "?": w=w+letterWidth(fontType,51)+ fontSpace
  Case "?": w=w+letterWidth(fontType,52)+ fontSpace
  Case "?": w=w+letterWidth(fontType,53)+ fontSpace

 End Select
Next

n1=1024-w :w = (n1/2)

Return w

End Function 
;----------------- Select player/ check If locked ---------------------------
Function selectPlayer(i,n,dir)

If zAi(i)=1    Then
  If dir=1 Then di=1 Else di=-1   ;Select Next or previous

    oldGuy=curGuy(i)
    curGuy(i)=curGuy(i)+di
    If characterOpen(curGuy(i))=0 Then curGuy(i) = oldGuy
    If curGuy(i) < 1 Then curGuy(i) = 8
    zThumbNail(i)=butpic(n)
EndIf

End Function 

;----------- MENU (character select)----------------------------------------
Function displayCharMenu()

pointers

For n=1 To ButtonAmount
    If clickedBut(n) Then
        If n <= mainCharAmt Then
            stanceMode(clickedBy(n))=1
        End If
        Select n    ;Add character, add CASE 11 for your new guy, set curGuy(clickedBy(n)=11 in the new line

        Case 1:
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 2
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 3
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 4
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 5
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 6
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 7
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 8
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 9
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 10
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 11
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 12
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 13
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 14
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 15
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 16
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 17
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 18
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 19
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        Case 20
            If characterOpen(n)=1 Then curGuy(clickedBy(n))=n:zThumbNail(clickedBy(n))=butpic(n)
        
        Case 50 ;Select game mode on vs
            gamemode=gamemode+1
            If gamemode >4 Then gamemode=1
            If gamemode=1 Then flagAmount=0:gmStr$=strInfo$(6)
            If gamemode=2 Then flagAmount=2:gmStr$=strInfo$(7)
            If gamemode=3 Then flagAmount=1:gmStr$=strInfo$(8)
            If gamemode=4 Then flagAmount=0:gmStr$=strInfo$(9)
        Case 51 ;Select how many points / lives / time
            Select gameMode
                Case 2
                flagMaxScore=flagMaxScore-1
                If flagMaxScore < 1 Then flagMaxScore=99
                Case 3
                flagMaxTime=flagMaxTime-50
                If flagMaxTime < 1 Then flagMaxTime=1000
                Case 1
                gameLives=gameLives-1
                If gameLives < 1 Then gameLives=99
                Case 4
                targetMaxScore=targetMaxScore-1
                If targetMAxScore < 1 Then targetMAxScore=99

            End Select
        Case 52 ;Select how many points / lives / time
            Select gameMode
                Case 2
                    flagMaxScore=flagMaxScore+1
                    If flagMaxScore > 99 Then flagMaxScore=1
                Case 3
                    flagMaxTime=flagMaxTime+50
                    If flagMaxTime > 1000 Then flagMaxTime=50
                Case 1
                    gameLives=gameLives+1
                    If gameLives >99 Then gameLives=1
                Case 4
                    targetMaxScore=targetMaxScore+1
                    If targetMAxScore > 99 Then targetMAxScore=1

            End Select
        Case 54
            If teamAttack=1 Then
                teamAttack=0
            Else
                teamAttack=1
            EndIf
        Case 55
            k=1
            zTeam(k)=zTeam(k)+1
            If zTeam(k) > 2 Then zTeam(k)=0
        Case 56
            k=2
            zTeam(k)=zTeam(k)+1
            If zTeam(k) > 2 Then zTeam(k)=0
        Case 57
            k=3
            zTeam(k)=zTeam(k)+1
            If zTeam(k) > 2 Then zTeam(k)=0
        Case 58
            k=4
            zTeam(k)=zTeam(k)+1
            If zTeam(k) > 2 Then zTeam(k)=0

        Case 60
            k=1
            Select ButSeq(60)
            Case 0:ButSeq(60)=1:zon(k)=1:zAI(k)=0
            Case 1:ButSeq(60)=2:zon(k)=1:zAI(k)=1
    .anotherCharacter1
                   curGuy(1)=Rand(1,8):zThumbNail(1)=butpic(curGuy(1))
                   If characterOpen(curGuy(1))=0 Then Goto anotherCharacter1
                  If vsMode=0 Then ButSeq(60)=0:zon(k)=0:zAI(k)=0
            Case 2:ButSeq(60)=0:zon(k)=0:zAI(k)=0
            End Select
        Case 61
            k=2
            Select ButSeq(61)
            Case 0:ButSeq(61)=1:zon(k)=1:zAI(k)=0
            Case 1:ButSeq(61)=2:zon(k)=1:zAI(k)=1
    .anotherCharacter2
                   curGuy(2)=Rand(1,8):zThumbNail(2)=butpic(curGuy(2))
                   If characterOpen(curGuy(2))=0 Then Goto anotherCharacter2
                   If vsMode=0 Then ButSeq(61)=0:zon(k)=0:zAI(k)=0
            Case 2:ButSeq(61)=0:zon(k)=0:zAI(k)=0
            End Select
        Case 62
            k=3
            Select ButSeq(62)
            Case 0:ButSeq(62)=1:zon(k)=1:zAI(k)=0
            Case 1:ButSeq(62)=2:zon(k)=1:zAI(k)=1
    .anotherCharacter3
                   curGuy(3)=Rand(1,8):zThumbNail(3)=butpic(curGuy(3))
                   If characterOpen(curGuy(3))=0 Then Goto anotherCharacter3
                   If vsMode=0 Then ButSeq(62)=0:zon(k)=0:zAI(k)=0
            Case 2:ButSeq(62)=0:zon(k)=0:zAI(k)=0
            End Select
        Case 63
            k=4
            Select ButSeq(63)
            Case 0:ButSeq(63)=1:zon(k)=1:zAI(k)=0
            Case 1:ButSeq(63)=2:zon(k)=1:zAI(k)=1
    .anotherCharacter4
                  curGuy(4)=Rand(1,8):zThumbNail(4)=butpic(curGuy(4))
                  If characterOpen(curGuy(4))=0 Then Goto anotherCharacter4
                  If vsMode=0 Then ButSeq(63)=0:zon(k)=0:zAI(k)=0
            Case 2:ButSeq(63)=0:zon(k)=0:zAI(k)=0
            End Select
        Case 64    ;PLAYER# 1 A.I LEVEL
            k=1
            aiLevel(k)=aiLevel(k)+1
            If aiLevel(k) > 5 Then aiLevel(k)=1
        Case 65    ;PLAYER# 2 A.I LEVEL
            k=2
            aiLevel(k)=aiLevel(k)+1
            If aiLevel(k) > 5 Then aiLevel(k)=1
        Case 66    ;PLAYER# 3 A.I LEVEL
            k=3
            aiLevel(k)=aiLevel(k)+1
            If aiLevel(k) > 5 Then aiLevel(k)=1
        Case 67    ;PLAYER# 4 A.I LEVEL
            k=4
            aiLevel(k)=aiLevel(k)+1
            If aiLevel(k) > 5 Then aiLevel(k)=1
        Case 70    ;START BUTTON
            ;vsMode=1
            gameStart=1
            If gamemode=2 And vsMode=1 Then
                For k=1 To zzamount
                    If zteam(k)=0 And zon(k)=1 Then    ;checks so that everyone has a team on CTF mode
                        gameStart=0
                        defineButtons(0)
                        warning=1:warnSeq=0:strWarning$=strInfo$(33)
                        Exit
                    EndIf
                Next

            Else
                If vsMode=1 Then
                     If zamountPlaying < 2 Then gameStart=0
                EndIf
                If vsMode=0 Then
                     If zamountPlaying < 1 Then gameStart=0
                EndIf
                defineButtons(0)
            EndIf
            If gameStart=0 Then If gamesound Then PlaySound brokensnd

        Case 71    ;TOGGLE ITEMS ON / OFF
            If noItems=1 Then
                noItems=0
            Else
                noItems=1
            EndIf
        Case 72
            selectPlayer(1,n,0)
        Case 73
            selectPlayer(1,n,1)
        Case 74
            selectPlayer(2,n,0)
        Case 75
            selectPlayer(2,n,1)
        Case 76
            selectPlayer(3,n,0)
        Case 77
            selectPlayer(3,n,1)
        Case 78
            selectPlayer(4,n,0)
        Case 79
            selectPlayer(4,n,1)
        End Select
        If gamesound And butOn(n)=1 Then PlaySound clicksnd
    EndIf

    If rightClickedBut(n) Then
        stanceMode(rightClickedBy(n))=1
        Select n
        Case 1
            If characterOpen(n)=1 Then curGuy(rightClickedBy(n))=n:stanceMode(rightClickedBy(n))=2
        Case 15
            If characterOpen(n)=1 Then curGuy(rightClickedBy(n))=n:stanceMode(rightClickedBy(n))=2
        End Select
    End If

Next

selectSecretChars()

zamountPlaying=0
For n=1 To zzamount
    If zon(n)=1 Then zamountPlaying=zamountplaying+1
Next

;---------- render menu (char screen) -----------------
;----------Buttons attributes--------------------------
x=0
y=0
buttonAmount = 80
fontType = 2

For b = 1 To mainCharAmt
If x Mod 800 = 0 And x <> 0 Then y=y+96:x=0
xBut(b)=112+x:yBut(b)=72+y:wBut(b)=80:hBut(b)=96
x=x+80
pri xbut(b)+8,ybut(b)+16, butTExt(b)
Next

x=0
For b= 55 To 58
xBut(b)=80+x:yBut(b)=400:wBut(b)=144:hBut(b)=32
x=x+240
Next

x=0
For b= 60 To 63 ;human, CPU, none
xBut(b)=176+x:yBut(b)=339:wBut(b)=56:hBut(b)=24
x=x+240
Next

x=0
For b= 72 To 79 Step 2  ;arrows to Select CPU player
xBut(b)=80 +  x :yBut(b)  =496:wBut(b)  =48:hBut(b)  =24
xBut(b+1)=184+x :yBut(b+1)=496:wBut(b+1)=48:hBut(b+1)=24
x=x+240
Next

x=0
For b= 64 To 67 ;AI level button
xBut(b)=93+x:yBut(b)=563:wBut(b)=102:hBut(b)=35
x=x+240
Next

xBut(50)=314:yBut(50)=16:wBut(50)=362:hBut(50)=32
xBut(51)=776:yBut(51)=19:wBut(51)=32:hBut(51)=27
xBut(52)=952:yBut(52)=19:wBut(52)=32:hBut(52)=27
xBut(54)=16:yBut(54)=16:wBut(54)=256:hBut(54)=32
xBut(70)=320:yBut(70)=720:wBut(70)=360:hBut(70)=32
xBut(71)=752:yBut(71)=720:wBut(71)=253:hBut(71)=32

;-----Rendering menu --------------------------------

TileImage backg,xtileimg,ytileimg
;xtileimg=xtileimg+.1:ytileimg=ytileimg+.1

For b = 1 To mainCharAmt
    drawimage board3,xbut(b),ybut(b)
    ; Draw the board with priority than the hero thumbnails
Next

For b = 1 To mainCharAmt ;characters to select
    If charSelectable(b)=0 Then Goto skipButtonRender
    ;Color 100,100,100:Rect xbut(b),ybut(b),wbut(b),hBut(b),1
    ;Color 200,200,200:Rect xbut(b),ybut(b),wbut(b),hBut(b),0
    If characterOpen(b)=1 Then
        Local xOffset=getButPicXOffset(b)
        Local yOffset=getButPicYOffset(b)

        If stanceMode(n)=1 Then
            DrawImage stanceButPic(b, 1),xbut(b)+xOffset,( ybut(b)-ImageHeight(stanceButPic(b, 1)) ) + yOffset
        Else
            DrawImage stanceButPic2(b, 1),xbut(b)+xOffset,( ybut(b)-ImageHeight(stanceButPic2(b, 1)) ) + yOffset
        End If
    Else
        DrawImage lock,xbut(b)+16, ybut(b)+24
    EndIf
    .skipButtonRender
Next

b=50
If vsMode=1 Then
 DrawImage butpic(b),xbut(b),ybut(b)
 pri xbut(b)+14,ybut(b)+8,strInfo$(17) + gmStr$
EndIf
b=51
If vsMode=1 Then
 DrawImage butpic(54),xbut(b)-32,ybut(b)-5
 DrawImage arrow1,xbut(b),ybut(b)
 DrawImage arrow2,xbut(b+1),ybut(b+1)
 If gameMode=1 Then pri xbut(b)+25,ybut(b)+5,strInfo$(29) + gameLives
 If gameMode=2 Then pri xbut(b)+25,ybut(b)+5,strInfo$(11) + flagMAxScore
 If gameMode=3 Then pri xbut(b)+25,ybut(b)+5,strInfo$(10) + flagMAxTime
 If gameMode=4 Then pri xbut(b)+25,ybut(b)+5,strInfo$(11) + targetMAxScore
EndIf

b=54
If vsMode=1 Then
 DrawImage butpic(54),xbut(b),ybut(b)
 If teamAttack=1 Then pri xbut(b)+13,ybut(b)+8,strInfo$(2)
 If teamAttack=0 Then pri xbut(b)+13,ybut(b)+8,strInfo$(3)
EndIf
b=70    ;Start the game!
 DrawImage butpic(b),xbut(b),ybut(b)
 pri xbut(b)+104,ybut(b)+8,strInfo$(1)

b=71   ;items on/off
DrawImage butpic(b),xbut(b),ybut(b)
If noItems=1 Then
    pri xbut(b)+80,ybut(b)+8,strInfo$(97)
Else
    pri xbut(b)+80,ybut(b)+8,strInfo$(96)
EndIf

x=64:y=336:xx=0:n=0
For b=1 To zzamount ;board, player # and pad
    n=n+1
    ;Color 100,100,100:Rect x+xx,y,110,55,1
    ;Color 200,200,200:Rect x+xx,y,110,55,0
    drawimage board,(x+xx)-16,y-13
    drawimage pad,x+xx+8,y+200
    pri x+xx+8,y+5,strInfo$(12)+ n
    xx=xx+240
Next

n=0
For b=55 To 58  ;team, selected player
    n=n+1
    If curGuy(n)<=maxCharAmt Then
        If zStanceFrames(curGuy(n))>0 And zOn(n)=1 Then
            butFrame=getCurStanceFrame(n, b)
        Else
            butFrame=1
        End If
    End If
    If vsMode=1 Then
        Color 200,200,200
        Rect xbut(b),ybut(b),wbut(b),hBut(b),0
    
        If zTeam(n) < 1 Or zTeam(n) > 2 Then
            Color 200,200,200:pri xbut(b)+8,ybut(b)+5,strInfo$(30)
        Else
            Select zteam(n)
            Case 1:Color 250,0,0:Rect xbut(b)+80,ybut(b)+3,48,16,1
            Case 2:Color 0,250,0:Rect xbut(b)+80,ybut(b)+3,48,16,1
            End Select
            pri xbut(b)+8,ybut(b)+5,strInfo$(31)
        EndIf
    EndIf
    xOffset=38
    If curGuy(n)=1 Then xOffset=30
    If curGuy(n)=3 Then xOffset=25
    If curGuy(n)=6 Then xOffset=20
    If curGuy(n)=7 Then xOffset=31
    If curGuy(n)=8 Then xOffset=43
    If curGuy(n)=9 Then xOffset=34
    If curGuy(n)=15 Then xOffset=32
    If curGuy(n)=17 Then xOffset=31
    If curGuy(n)=18 Then xOffset=12
    If curGuy(n)=19 Then xOffset=34
    If curGuy(n)=20 Then xOffset=40
    If curGuy(n) > 0 And CurGuy(n) < maxCharAmt And zon(n) > 0 Then
        If stanceMode(n)=1 Then
            If stanceButPic(curGuy(n), butFrame)=0 Then butFrame=1
            DrawImage stanceButPic(curGuy(n), butFrame),xbut(b)+xOffset,560-ImageHeight(stanceButPic(curGuy(n), butFrame))
        Else
            If stanceButPic2(curGuy(n), butFrame)=0 Then butFrame=1
            DrawImage stanceButPic2(curGuy(n), butFrame),xbut(b)+xOffset,560-ImageHeight(stanceButPic2(curGuy(n), butFrame))
        End If
    End If
Next

n=0
For b=60 To 63
    ;Color 200,200,200
    ;Rect xbut(b),ybut(b),wbut(b),hBut(b),0
    Select butSeq(b)
    Case 0:DrawImage butNA,xbut(b),ybut(b)
    Case 1:DrawImage butHum,xbut(b),ybut(b)
    Case 2:DrawImage butCPU,xbut(b),ybut(b)
    End Select
Next

n=0
For b=72 To 79 Step 2    ;draws arrows For selecting CPU player
    n=n+1
    If zai(n)=1 Then
        DrawImage arrow1,xbut(b),ybut(b)
        DrawImage arrow2,xbut(b+1),ybut(b+1)
    EndIf
Next

n=0
For b=64 To 67  ;Level Select For AI
    n=n+1
    If zai(n)=1 Then
        drawimage board2, xbut(b),ybut(b)
        pri xbut(b)+10,ybut(b)+10,strInfo$(32) + aiLevel(n)
    EndIf
Next

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

If warning=1 Then
    fontType=2
    warningMsg()
    fontType=1
EndIf

End Function 
;----------- Warning on menu -------------------------------------------------
Function warningMsg()
warnSeq=warnSeq+1
If warnSeq > 140 Then warning=0
    x=300: y=264: w=400: h=48
    Color 192,192,192
    Rect x,y,w,h,1
    Color Rand(1,255),Rand(1,255),Rand(1,255)
    Rect x+2,y+2,w-4,h-4,0
    pri priW(strWarning$),y+16, strWarning$

End Function 

;----------- Main menu ---------------------------------------------------------
Function mainMenu()

pointers
For n=1 To 200
    If JoyDown(n)=1 Then
        DebugLog "JoyDown!: " + n
    End If
Next

waitCheats()

For n=1 To ButtonAmount
If clickedBut(n) Then
  Select n
    Case 1: menuOption=charSelectVal:vsMode=0 :defineButtons(1)
    Case 2: menuOption=charSelectVal:vsMode=1:changeMusic(music12):initCharSelect() ;go to character Select screen (vs mode)
    Case 3: menuOption=optionsMenuVal  ;go to options screen
    Case 4: menuOption=recordsMenuVal : showRecords()
    Case 5: rollCredits()
    Case 6: saveConfig() : end
  End Select
    If gamesound Then PlaySound ddhitsnd
EndIf
Next

;----------Buttons attributes--------------------------
x=0 : y=0
buttonAmount = 6
For b = 1 To buttonAmount
    xBut(b)=320+x:yBut(b)=320+y:wBut(b)=384:hBut(b)=35
    y=y+48
Next

butText$(1)=strInfo$(14)
butText$(2)=strInfo$(21)
butText$(3)=strInfo$(15)
butText$(4)=strInfo$(99)
butText$(5)=strInfo$(40)
butText$(6)=strInfo$(50)

;-----Rendering menu --------------------------------
fontType=1
For b=1 To buttonAmount
    pri priW(butText$(b)), yBut(b), butText$(b)
Next

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

End Function 
;----------- in game menu ---------------------------------------------------------
Function inGameMenu()

pointers

For n=1 To ButtonAmount
If clickedBut(n) Then
  Select n
    Case 1: menuOption=0: gamePaused = 0    ;resume game
    Case 2: menuOption=3                        ;go to options screen
    Case 3: menuOption=2:gameDone=1:gamePaused=0 ;quit to main menu
    Case 4: saveConfig() : end              ;quit game
  End Select
    If gamesound Then PlaySound ddhitsnd
EndIf
Next

;----------Buttons attributes--------------------------
x=0 : y=0
buttonAmount = 4
For b= 1 To 4
    xBut(b)=320+x:yBut(b)=320+y:wBut(b)=384:hBut(b)=35
    y=y+48
Next

butText$(1)=strInfo$(63)
butText$(2)=strInfo$(15)
butText$(3)=strInfo$(64)
butText$(4)=strInfo$(65)

;-----Rendering menu --------------------------------
fontType=1
For b=1 To buttonAmount
    pri priW(butText$(b)), yBut(b), butText$(b)
Next

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

End Function 
;----------- Options menu ---------------------------------------------------------
Function optionsMenu()

pointers

For n=1 To ButtonAmount
If clickedBut(n) Then
  Select n
    Case 1:If windowMode=0 Then windowMode=1 Else windowMode=0
           warning=1:warnSeq=0:strWarning$=strInfo$(66)
    Case 2:If videoColorDepth=16 Then videoColorDepth=32 Else videoColorDepth=16
           warning=1:warnSeq=0:strWarning$=strInfo$(66)
    Case 3:If gameSound=1 Then gameSound=0 Else gameSound=1
    Case 4:If gameMusic=1 Then
             gameMusic=0
             stopchannel chMusic
           Else
             gameMusic=1
             LoopSound music
             chMusic = PlaySound (music)
              EndIf
           
    Case 5:menuOption=4
    Case 6:If curIdiom=1 Then curIdiom=2 Else curIdiom=1
        idioms(curIdiom)
    Case 7:
        If menuThemeIdx=1 Then menuThemeIdx=0 Else menuThemeIdx=1
        If gamestart <> 1 Then changeMusic(menuTheme(menuThemeIdx))
    Case 8:
        If showTutorial=1 Then showTutorial=0 Else showTutorial=1
    Case 9:
        If duringGameMenu=1 Then menuOption=5 Else menuOption=2
  End Select
    If gamesound Then PlaySound ddhitsnd
    saveConfig()
EndIf
Next

;----------Buttons attributes--------------------------
x=0 : y=0
buttonAmount = 9
For b= 1 To buttonAmount
    If b=9 Then y=y+80
    xBut(b)=240+x:yBut(b)=260+y:wBut(b)=464:hBut(b)=35
    y=y+48
Next

butText$(1)=strInfo$(19)
butText$(2)=strInfo$(18)
butText$(3)=strInfo$(45)
butText$(4)=strInfo$(46)
butText$(5)=strInfo$(49)
butText$(6)=strInfo$(16)
butText$(7)=strInfo$(100)
butText$(8)=strInfo$(98)
butText$(9)=strInfo$(48)

;-----Rendering menu --------------------------------
fontType=1
For b=1 To buttonAmount
    pri xBut(b), yBut(b), butText$(b)
    ;color 200,200,200
    ;rect xBut(b),yBut(b),wBut(b),hBut(b),0
Next
o=320
If windowMode=0 Then
    pri xBut(1)+o, yBut(1), strInfo$(41)
Else
    pri xBut(1)+o, yBut(1), strInfo$(42)
EndIf

If videoColorDepth=16 Then
    pri xBut(2)+o, yBut(2), strInfo$(43)
Else
    pri xBut(2)+o, yBut(2), strInfo$(44)
EndIf

If gameSound=1 Then
    pri xBut(3)+o, yBut(3), strInfo$(41)
Else
    pri xBut(3)+o, yBut(3), strInfo$(42)
EndIf

If gameMusic=1 Then
    pri xBut(4)+o, yBut(4), strInfo$(41)
Else
    pri xBut(4)+o, yBut(4), strInfo$(42)
EndIf

pri xBut(6)+o, yBut(6), strInfo$(47) ;ENGLISH/PORTUGUES

If menuThemeIdx=1 Then
    pri xBut(7)+o, yBut(7), "2"
Else
    pri xBut(7)+o, yBut(7), "1"
End If

If showTutorial=1 Then
    pri xBut(8)+o, yBut(8), strInfo$(41)
Else
    pri xBut(8)+o, yBut(8), strInfo$(42)
End If

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

End Function 

;----------- Controls menu ---------------------------------------------------------
Function controlsMenu()

pointers

For n=1 To ButtonAmount
If clickedBut(n) Then
  If gamesound Then PlaySound ddhitsnd
  Select n
    Case 1: setcontroller(1)
    Case 2: setcontroller(2)
    Case 3: setcontroller(3)
    Case 4: setcontroller(4)
    Case 5: configKeys(1)
    Case 6: configKeys(2)
    Case 7: configKeys(3)
    Case 8: configKeys(4)
    Case 9: menuOption = 3 :savekeys()
  End Select
    
EndIf
Next

;----------Buttons attributes--------------------------
x=160: y=352
buttonAmount = 13
For b= 1 To 4
    xBut(b) = x : yBut(b) = y : wBut(b)=160:hBut(b)=96

    If zController(b)=0 Then
        tpic(b) = keyboardPic
    Else
        tPic(b) = controllerPic
    EndIf

    x=x+192
Next

x=160: y=464
For b = 5 To 8
    xBut(b) = x : yBut(b) = y : wBut(b)=160:hBut(b)=32
    x=x+192
    butText$(b) = strInfo$(60)
Next

x=216 : y=307
For b=10 To 13
    xBut(b) = x : yBut(b) = y : wBut(b)=0:hBut(b)=0
    x=x+192
    butText$(b) = "p"+(b-9)
Next

xBut(9)=160 : yBut(9)=672: wBut(9)=128: hBut(9)=40: butText$(9)=strInfo$(48)

;-----Rendering menu --------------------------------
fontType=1
For b=1 To 8
    If b < 5 Then
        drawimage tPic(b), xBut(b), yBut(b)
        If zController(b)=1 Then pri xBut(b)+3,yBut(b)+3,controllerPort(b)+1
   EndIf
    If b > 4 Then pri xBut(b), yBut(b), butText$(b)
Next

For b=10 To 13
    pri xBut(b),yBut(b),butText$(b)
Next

pri xBut(9), yBut(9), butText$(9)

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

End Function 

;----------Idioms----------------------
Function idioms(n)

curIdiom=n
Select n
Case 1
strInfo$(1)="START GAME!"
strInfo$(2)="Team Attack = ON"
strInfo$(3)="Team Attack = OFF"
strInfo$(4)="SOUND = ON"
strInfo$(5)="SOUND = OFF"
strInfo$(6)="DEATH MATCH"
strInfo$(7)="CAPTURE THE FLAG"
strInfo$(8)="KEEP THE FLAG"
strInfo$(9)="HIT THE TARGET"
strInfo$(10)="TIME: "
strInfo$(11)="SCORE: "
strInfo$(12)="PLAYER "
strInfo$(13)=" WON!!!"
strInfo$(14)="ADVENTURE MODE"
strInfo$(15)="OPTIONS"
strInfo$(16)="LANGUAGE"
strInfo$(17)="GAME MODE:"
strInfo$(18)="VIDEO"
strInfo$(19)="FULL SCREEN"
strInfo$(20)="WINDOW MODE"
strInfo$(21)="VS MODE"
strInfo$(22)="S T A G E   S E L E C T"
strInfo$(23)="L O A D I N G . . ."
strInfo$(24)="RED TEAM WON!!!"
strInfo$(25)="GREEN TEAM WON!!!"
strInfo$(26)="RED"
strInfo$(27)="GREEN"
strInfo$(28)=" TEAM WON!!!"
strInfo$(29)="LIVES: "
strInfo$(30)="TEAM: NONE"
strInfo$(31)="TEAM: "
strInfo$(32)="LEVEL: "
strInfo$(33)="All players MUST have a TEAM."
strInfo$(34)="DRAW GAME!"
strInfo$(35)="SECRET AREAS:"
strInfo$(36)="STAGE COMPLETE!"
strInfo$(37)="PRESS ANY KEY TO CONTINUE"
strInfo$(38)="HITS TAKEN: "
strInfo$(39)="UNTOUCHABLE!"
strInfo$(40)="CREDITS"
strInfo$(41)="ON"
strInfo$(42)="OFF"
strInfo$(43)="16 BITS"
strInfo$(44)="32 BITS"
strInfo$(45)="SFX"
strInfo$(46)="MUSIC"
strInfo$(47)="ENGLISH"
strInfo$(48)="BACK"
strInfo$(49)="CONTROLS"
strInfo$(50)="EXIT"
strInfo$(51)="UP"
strInfo$(52)="DOWN"
strInfo$(53)="LEFT"
strInfo$(54)="RIGHT"
strInfo$(55)="ATTACK"
strInfo$(56)="SPECIAL"
strInfo$(57)="JUMP"
strInfo$(58)="BLOCK"
strInfo$(59)="THROW"
strInfo$(60)="CONFIG."
strInfo$(61)="NEW CHARACTER UNLOCKED!"
strInfo$(62)="NEW VS STAGE UNLOCKED!"
strInfo$(63)="RESUME"
strInfo$(64)="MAIN MENU"
strInfo$(65)="EXIT GAME"
strInfo$(66)="MUST RESTART TO TAKE EFFECT"

strInfo$(67)="press jump while in"
strInfo$(68)="the air to double jump."
strInfo$(69)="press up + special"
strInfo$(70)="while in the air to"
strInfo$(71)="go up even further."
strInfo$(72)="press attack or special"
strInfo$(73)="to fight, you can use"
strInfo$(74)="combinations by pressing"
strInfo$(75)="up or down. ex: down + special."
strInfo$(76)="stand close to the switch"
strInfo$(77)="and press up to use it."
strInfo$(78)="stand on an item and"
strInfo$(79)="press attack to pick it up."
strInfo$(80)="then press throw or attack"
strInfo$(81)="to throw it."
strInfo$(82)="to go down from platforms"
strInfo$(83)="press down + jump when"
strInfo$(84)="standing on it."
strInfo$(85)="you can throw items at"
strInfo$(86)="different directions, even"
strInfo$(87)="diagonally, simply hold"
strInfo$(88)="the direction you wish to"
strInfo$(89)="throw and press attack"
strInfo$(90)="when the red bar at the top"
strInfo$(91)="of the screen turns green,"
strInfo$(92)="you can release a super move"
strInfo$(93)="by pressing block + special."
strInfo$(94)="no air special."
strInfo$(95)="secrets found: "

strInfo$(96)="items: on"
strInfo$(97)="items: off"

strInfo$(98)="tutorials"
strInfo$(99)="records"
strInfo$(100)="menu theme: "
strInfo$(101)="f5 to restart"

Case 2
strInfo$(1)="INICIAR JOGO!"
strInfo$(2)="Time Ataca =SIM"
strInfo$(3)="Time Ataca =N?O"
strInfo$(4)="SOM = SIM"
strInfo$(5)="SOM = N?O"
strInfo$(6)="LUTA"
strInfo$(7)="CAPTURE BANDEIRA"
strInfo$(8)="SEGURE A BANDEIRA"
strInfo$(9)="ACERTE O ALVO"
strInfo$(10)="TEMPO:"
strInfo$(11)="PLACAR: "
strInfo$(12)="JOGADOR "
strInfo$(13)=" VENCEU!"
strInfo$(14)="MODO AVENTURA"
strInfo$(15)="OP??ES"
strInfo$(16)="IDIOMA"
strInfo$(17)="MODO JOGO:"
strInfo$(18)="VIDEO"
strInfo$(19)="TELA CHEIA"
strInfo$(20)="MODO JANELA"
strInfo$(21)="MODO VS"
strInfo$(22)="SELECIONE  A  FASE"
strInfo$(23)="C A R R E G A N D O . . ."
strInfo$(24)="VERVELHO VENCEU!!!"
strInfo$(25)="VERDE VENCEU!!!"
strInfo$(26)="TIME VERMELHO"
strInfo$(27)="TIME VERDE"
strInfo$(28)=" VENCEU!"
strInfo$(29)="VIDAS: "
strInfo$(30)="TIME: N/D"
strInfo$(31)="TIME: "
strInfo$(32)="NIVEL: "
strInfo$(33)="todos DEVEM ter um time."
strInfo$(34)="EMPATE!"
strInfo$(35)="AREAS SECRETAS:"
strInfo$(36)="FASE COMPLETA!"
strInfo$(37)="APERTE QUALQUER TECLA"
strInfo$(38)="PANCADAS: "
strInfo$(39)="INTOCAVEL!"
strInfo$(40)="CREDITOS"
strInfo$(41)="SIM"
strInfo$(42)="N?O"
strInfo$(43)="16 BITS"
strInfo$(44)="32 BITS"
strInfo$(45)="SOM"
strInfo$(46)="MUSICA"
strInfo$(47)="PORTUGUES"
strInfo$(48)="VOLTAR"
strInfo$(49)="CONTROLES"
strInfo$(50)="SAIR"
strInfo$(51)="CIMA"
strInfo$(52)="BAIXO"
strInfo$(53)="ESQUERDA"
strInfo$(54)="DIREITA"
strInfo$(55)="ATAQUE"
strInfo$(56)="ESPECIAL"
strInfo$(57)="PULO"
strInfo$(58)="DEFESA"
strInfo$(59)="ARREMESSAR"
strInfo$(60)="CONFIG."
strInfo$(61)="NOVO PERSONAGEM !"
strInfo$(62)="NOVA FASE VS ABERTA!"
strInfo$(63)="CONTINUAR"
strInfo$(64)="MENU PRINCIPAL"
strInfo$(65)="SAIR DO JOGO"
strInfo$(66)="REINICIE PARA FAZER EFEITO"

strInfo$(67)="aperte pulo enquanto estiver no"
strInfo$(68)="ar para executar um pulo duplo."
strInfo$(69)="aperte cima + especial"
strInfo$(70)="enquanto estiver no ar"
strInfo$(71)="para ir ainda mais alto."
strInfo$(72)="aperte ataque ou especial"
strInfo$(73)="para lutar, voce pode usar"
strInfo$(74)="COMBINA??ES apertando cima"
strInfo$(75)="ou baixo. ex: baixo + especial."
strInfo$(76)="fique proximo a alavanca"
strInfo$(77)="e aperte para cima para usa-la."
strInfo$(78)="aproxime-se de um item e"
strInfo$(79)="aperte ataque para pega-lo."
strInfo$(80)="ENT?O aperte arremessar ou"
strInfo$(81)="ataque para joga-lo."
strInfo$(82)="para descer de plataformas"
strInfo$(83)="aperte para baixo + pulo"
strInfo$(84)="quando estiver em uma."
strInfo$(85)="voce pode jogar itens em"
strInfo$(86)="DIRE??ES diferentes, ate"
strInfo$(87)="na diagonal, basta segurar"
strInfo$(88)="na DIRE??O que deseja jogar"
strInfo$(89)="e apertar ataque."
strInfo$(90)="quando a barra vermelha na parte"
strInfo$(91)="superior da tela ficar verde, "
strInfo$(92)="pode se executar um super ataque"
strInfo$(93)="apertando defesa + especial."
strInfo$(94)="sem especial no ar."
strInfo$(95)="segredos encontrados: "

strInfo$(96)="itens: sim"
strInfo$(97)="itens: N?O"

strInfo$(98)="tutoriais"
strInfo$(99)="registro"
strInfo$(100)="tema do menu"
strInfo$(101)="pressione f5 para reiniciar"

End Select

For i=1 To 100
    strInfo$(i)=Lower(strInfo$(i))
Next

End Function 
;--------------------------------- Controls menu pointers ------------------------
Function pointers()
For n=1 To buttonAmount
clickedBut(n)=0:clickedBy(n)=0
rightClickedBut(n)=0:rightClickedBy(n)=0
Next

For n=1 To zzamount
    upKey(n)=0:leftKey(n)=0:rightKey(n)=0:downKey(n)=0:jumpKey(n)=0:shotKey(n)=0
    jumpKeyDown(n)=0:runkey(n)=0:blockKey(n)=0:specialkey(n)=0
Next

;If zController(n)=0 Then ;Keyboard
    xpointer(1)=MouseX()
    ypointer(1)=MouseY()
;End If

For n = 2 To zzamount
Select zController(n)
Case 0
    If KeyDown(upK(n)) Then upKey(n)=1
    If KeyDown(downK(n)) Then downKey(n)=1
    If KeyDown(leftK(n)) Then leftKey(n)=1
    If KeyDown(rightK(n)) Then rightKey(n)=1
    If KeyHit(shotK(n)) Then shotKey(n)=1

Case 1
    If JoyYDir(controllerPort(n))=-1 Then upKey(n)=1
    If JoyXDir(controllerPort(n))=-1 Then leftKey(n)=1
    If JoyXDir(controllerPort(n))=1 Then rightKey(n)=1
    If JoyYDir(controllerPort(n))=1 Then downkey(n)=1
    If JoyHit(shotK(n),controllerPort(n)) Then shotKey(n)=1

End Select
Next

pve=5
For n=1 To 4
    If upKey(n)=1 Then ypointer(n)=ypointer(n)-pve
    If downKey(n)=1 Then ypointer(n)=ypointer(n)+pve
    If leftKey(n)=1 Then xpointer(n)=xpointer(n)-pve
    If rightKey(n)=1 Then xpointer(n)=xpointer(n)+pve
    If shotKey(n)=1 Then clickButton(n)
    If specialkey(n)=1 Then rightClickButton(n)

    If xpointer(n) < 1 Then xpointer(n) = 1
    If xpointer(n) > 1014 Then xpointer(n) = 1014
    If ypointer(n) < 1 Then ypointer(n) = 1
    If ypointer(n) > 758 Then ypointer(n) = 758
Next

If MouseHit(1) Then clickbutton(1)
If MouseHit(2) Then rightClickButton(1)
End Function 
;------------ define buttons For vs/adventure mode -----------------
Function defineButtons(n)

If n = 0 Then   ;For vs mode
For i=1 To 100
 buton(i)=1
Next

    If zteam(1)=1 And zteam(2)=1 And zteam(3)=1 And zteam(4)=1 Then
        zteam(1)=0 : zteam(2)=0 :zteam(3)=0 : zteam(4)=0
    EndIf

EndIf

If n = 1 Then   ;For adventure mode

For i=1 To 4
    If zAi(i)=1 Then zAi(i)=0:zon(i)=0:ButSeq(59+i)=0
Next
For i= 50 To 58
 buton(i)=0
Next
For i= 64 To 67
 buton(i)=0
Next
For i= 71 To 79
 buton(i)=0
Next

EndIf

End Function 
;------------ closing screens ----------------------------------------------------
Function closeScreen(n,s)

If curWindowMode=0 Then
    If n > 0 Then SetBuffer FrontBuffer()
Else
    SetBuffer BackBuffer()
EndIf

Color 10,10,10

Select n

Case 1    ;horizontal lines
y1=0 : y2=768
For i=1 To 384
    Rect 0,y1,1025,5,1
    Rect 0,y2,1025,5,1
    If curWindowMode=1 Then
        Flip
    Else
        VWait
    EndIf
    y1=y1+5
    y2=y2-5
    If y1 > 389 Then Exit
Next

Case 2    ;vertical lines
x1=0 : x2=1025
For i=1 To 512
    Rect x1,0,5,769,1
    Rect x2,0,5,769,1
    If curWindowMode=1 Then
        Flip
    Else
        VWait
    EndIf
    x1=x1+5
    x2=x2-5
    If x1 > 517 Then Exit
Next

Case 3    ;horizontal and vertical lines

x1=0 : x2=1025
y1=0 : y2=768
For i=1 To 512
    Rect x1,0,5,769,1
    Rect x2,0,5,769,1
    Rect 0,y1,1025,5,1
    Rect 0,y2,1025,5,1
    If curWindowMode=1 Then
        Flip
    Else
        VWait
    EndIf
    x1=x1+5 : x2=x2-5
    y1=y1+5 : y2=y2-5
    If x1 > 517 Then Exit
Next

Case 4 ;spreading vertical lines

x1=32
For i=1 To 68 Step 2
    For ii= 0 To 1024 Step 64
        Rect x1+ii,0,i,789,1
    Next
    x1=x1-1
    If curWindowMode=1 Then
        Flip
    Else
        VWait
    EndIf
Next


End Select

End Function 


;---------- BUTTON CLICK -----------------------------------------------
Function clickButton(n)

For nn=1 To buttonAmount
    If xpointer(n) => xBut(nn) And xpointer(n) =< xbut(nn)+wbut(nn) And butOn(nn) Then
    If ypointer(n) => yBut(nn) And ypointer(n) =< ybut(nn)+hbut(nn) Then
        clickedBut(nn)=1:clickedBy(nn)=n
    EndIf
    EndIf
Next

End Function 

;---------- RIGHT BUTTON CLICK -----------------------------------------------
Function rightClickButton(n)

For nn=1 To buttonAmount
    If xpointer(n) => xBut(nn) And xpointer(n) =< xbut(nn)+wbut(nn) And butOn(nn) Then
    If ypointer(n) => yBut(nn) And ypointer(n) =< ybut(nn)+hbut(nn) Then
        rightClickedBut(nn)=1:rightClickedBy(nn)=n
    EndIf
    EndIf
Next

End Function 

;----------- Set controller type ----------------------------
Function setController(n)

If zController(n)=0 Then
    zController(n)=1 : controllerPort(n)=0
ElseIf controllerPort(n)=0 Then
    controllerPort(n)=1
ElseIf controllerPort(n)=1 Then
    controllerPort(n)=2
ElseIf controllerPort(n)=2 Then
    controllerPort(n)=3
ElseIf controllerPort(n)=3 Then
    zController(n)=0 : controllerPort(n)=99
EndIf

End Function 
;-------------Configure Keys!-----------------------------------------------------------------
Function configKeys(player)

Local isKeyPressed=0
pn = player

If zController(pn) = 1 Then  keyschosen=5 Else keyschosen=1

Repeat
setbuffer backbuffer()
FlushKeys()

For b=1 To 8
    If b < 5 Then
        drawimage tPic(b), xBut(b), yBut(b)
        If zController(b)=1 Then pri xBut(b)+3,yBut(b)+3,controllerPort(b)+1
    EndIf
    If b > 4 Then pri xBut(b), yBut(b), butText$(b)
Next

For b=10 To 13
    pri xBut(b),yBut(b),butText$(b)
Next

If pn=1 Then x = 160
If pn=2 Then x = 352
If pn=3 Then x = 544
If pn=4 Then x = 736
y = 512
color 28,28,28
rect x,y,352,32,1

Select keyschosen
Case 1:pri x,y,strInfo$(51): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then upK(pn)=key:isKeyPressed=1
Case 2:pri x,y,strInfo$(52): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then downK(pn)=key:isKeyPressed=1
Case 3:pri x,y,strInfo$(53): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then leftK(pn)=key:isKeyPressed=1
Case 4:pri x,y,strInfo$(54): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then rightK(pn)=key:isKeyPressed=1
Case 5:pri x,y,strInfo$(55): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then shotK(pn)=key:isKeyPressed=1
Case 6:pri x,y,strInfo$(56): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then specialK(pn)=key:isKeyPressed=1
Case 7:pri x,y,strInfo$(57): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then jumpK(pn)=key:isKeyPressed=1
Case 8:pri x,y,strInfo$(58): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then blockK(pn)=key:isKeyPressed=1
Case 9:pri x,y,strInfo$(59): flip
    key=getTheKey(controllerPort(pn))
    If key <> 0 Then grabK(pn)=key:isKeyPressed=1
End Select

If isKeyPressed=1 Then 
    keyschosen=keyschosen+1
    If gameSound Then PlaySound clickSnd
    isKeyPressed=0
End If

Until keyschosen > 9
For i=1 To 50
  clickedBut(i)=0
Next

End Function 
;-------------------- Get Key pressed ---------------------
Function getTheKey(port)

pressed=0
For n=2 To 221
    If KeyHit(n) Then Return n
Next
If zcontroller(pn)=1 Then
    For n=1 To 10
        If JoyHit(n,port) Then Return n
    Next
EndIf

Return 0
End Function 

;-------Load keys---------------------
Function loadkeys()

If FileType("cfg/keys.cfg")=0 Then
    temp=WriteFile("cfg/keys.cfg")    ;create dumb file
    For n=1 To 60
        WriteInt temp, 0
    Next
    CloseFile temp
EndIf

file=ReadFile("cfg/keys.cfg")
For n=1 To 4

zController(n) = ReadInt (file)
controllerPort(n) = ReadInt (file)
upK(n) = ReadInt (file)
leftK(n)= ReadInt (file)
rightK(n)= ReadInt (file)
downK(n)= ReadInt (file)
shotK(n)= ReadInt (file)
specialK(n)= ReadInt (file)
jumpK(n)= ReadInt (file)
blockK(n)= ReadInt (file)
grabK(n)= ReadInt (file)

Next

CloseFile file

End Function 


;------------------save keys to file----
Function savekeys()
file=WriteFile("cfg/keys.cfg")
For n=1 To 4
    WriteInt file, zController(n)
    WriteInt file, controllerPort(n)
    WriteInt file, upK(n)
    WriteInt file, leftK(n)
    WriteInt file, rightK(n)
    WriteInt file, downK(n)
    WriteInt file, shotK(n)
    WriteInt file, specialK(n)
    WriteInt file, jumpK(n)
    WriteInt file, blockK(n)
    WriteInt file, grabK(n)
Next
CloseFile file

End Function 
;-------Load game config ---------------------
Function loadConfig()

If FileType("cfg/game.cfg")=0 Then ;create file If it doesn`t exist
    temp=WriteFile("cfg/game.cfg")
    For n=1 To 20
        WriteInt temp, 0
    Next
    CloseFile temp
    Return False
EndIf

file=ReadFile("cfg/game.cfg")

windowMode = ReadInt (file)
videoColorDepth = ReadInt (file)
gameSound = ReadInt (file)
gameMusic = ReadInt (file)
curIdiom = ReadInt (file)
menuThemeIdx = ReadInt (file)
showTutorial = ReadInt (file)
curModId = ReadInt (file)

CloseFile file

Return True

End Function 

;------------------save game config to file----
Function saveConfig()
file=WriteFile("cfg/game.cfg")

WriteInt file, windowMode
WriteInt file, videoColorDepth
WriteInt file, gameSound
WriteInt file, gameMusic
WriteInt file, curIdiom
WriteInt file, menuThemeIdx
WriteInt file, showTutorial
WriteInt file, curModId        ;Current MOD id

CloseFile file

End Function 
;-------------------- wait this time or keyhit ------------------
Function waitThis(n)
time1 = millisecs()

Repeat

Delay(10)
If KeyHit(1) Then keyh=1
If KeyHit(28) Then keyh=1
If KeyHit(57) Then keyh=1

Until (MilliSecs() => time1 + n) Or keyh=1

End Function 
;--------------------- game intro --------------------------
Function gameIntro()

If gameMusic=1 Then PlaySound intro

fontType=1
SetBuffer BackBuffer()
If po=1 Then
    pri priW("a fan made game"),336,"a fan made game"
    pri priW("p r e s e n t s"),384,"p r e s e n t s"
Else
    pri priW("a fan made game"),352,"a fan made game"
EndIf
flip
cls
waitThis(8000)
If po=1 Then
    drawimage logo,235,203
Else
    pri priW("p r e s e n t s"),352,"p r e s e n t s"
EndIf
flip
waitThis(8200)
freesound intro
freeimage logo
End Function

;----------------- Show in-game statistics ------------------
Function showRecords()

pointers

For n=1 To ButtonAmount
If clickedBut(n) Then
  Select n
    Case 1: If duringGameMenu=1 Then menuOption=5 Else menuOption=2
  End Select
  If gamesound Then PlaySound ddhitsnd
EndIf
Next

;----------Buttons attributes--------------------------
x=0 : y=0
buttonAmount = 1
xBut(1)=320+x:yBut(1)=320+240:wBut(1)=384:hBut(1)=35

butText$(1)=strInfo$(48)

;-----Rendering menu --------------------------------
fontType=1
pri priW(butText$(1)), yBut(1), butText$(1)

For n=1 To 4
    DrawImage pointerPic(n),xpointer(n),ypointer(n)
Next

End Function

;---------------------- Roll Credits ------------------------
Function rollCredits()
n=1
s=40
s2=80
credits$(n)="credits" :ySpace(n)=s2 :n=n+1
credits$(n)="thanks to all the great" :ySpace(n)=s :n=n+1
credits$(n)="characters, art and sound" :ySpace(n)=s :n=n+1
credits$(n)="created by their respective owners" :ySpace(n)=s :n=n+1
credits$(n)="this game was made possible" :ySpace(n)=s2 :n=n+1
credits$(n)="game design" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="programming" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="graphics editing" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="additional gfx editing" :ySpace(n)=s :n=n+1
credits$(n)="paula" :ySpace(n)=s2 :n=n+1
credits$(n)="additional art" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s :n=n+1
credits$(n)="flowerjosy" :ySpace(n)=s2 :n=n+1
credits$(n)="sound editing" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="animation" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="level design" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="testing" :ySpace(n)=s :n=n+1
credits$(n)="the author" :ySpace(n)=s2 :n=n+1
credits$(n)="additional testing" :ySpace(n)=s :n=n+1
credits$(n)="alex" :ySpace(n)=s :n=n+1
credits$(n)="andre" :ySpace(n)=s :n=n+1
credits$(n)="julia" :ySpace(n)=s2 :n=n+1
credits$(n)="extra vs levels" :ySpace(n)=s :n=n+1
credits$(n)="benjamin woods" :ySpace(n)=s :n=n+1
credits$(n)="colin clark" :ySpace(n)=s :n=n+1
credits$(n)="mod creator: zeto" :ySpace(n)=s :n=n+1

If gameSound Then PlaySound ddhitSnd
lines = n
yCredit(0)=736
For i=1 To lines
    yCredit(i)=yCredit(i-1) + ySpace(i-1)
Next

SetBuffer BackBuffer()
ClsColor 0,0,0
Repeat
 WaitTimer(frameTimer)
 Cls
 For n=1 To lines
   If yCredit(n) > -48 And yCredit(n) < 800     ;only draw texts on screen
       pri priW(credits$(n)),yCredit(n),credits$(n)
   EndIf
   yCredit(n)=yCredit(n)-1
   If KeyDown(57)=1 Then yCredit(n)=yCredit(n)-2
 Next
 Flip
Until KeyHit(1) Or yCredit(lines) < -32

FlushKeys() : FlushJoy() : FlushMouse()
menuOption=2

End Function 

Function waitCheats()
    For i=2 To 5 ; Player index to apply the cheat
        If KeyHit(i) Then LastKeyPressed=i-1
    Next

    Local key=getKeyPressed()

    If key = 14 Then  ;BACKSPACE key
        cheatSeq(1)=cheatSeq(1)+1
        resetOtherCheats(1)
    EndIf
    
    If key = cheatKeys(2,cheatSeq(2)) And key <> 0 Then  ;slowpoke cheat
        cheatSeq(2)=cheatSeq(2)+1
        resetOtherCheats(2)
    Else If key <> 28 And key <> 0 Then ; If a key is pressed but not enter key
        cheatSeq(2)=0 ; Reset keys since wrong key is pressed
    End If
    
    If key = 28 Then  ;ENTER key
        If cheatSeq(1)=5 And cheat(1)=0 Then
            cheat(1)=1  ;cheat_1 activated
            If gameSound Then PlaySound energySnd
            For n=4 To mainCharAmt ;unlock all playable characters
                characterOpen(n)=1
            Next
            
            For n=1 To 30
                vsMapOpen(n)=1
            Next
        EndIf
        
        If cheatSeq(2)=8 Then
            cheat(2)=lastKeyPressed  ;cheat_2 activated
            If gameSound Then PlaySound energySnd
            CurGuy(lastKeyPressed)=40 ;set selected character to turlte
        End If
        
        cheatSeq(1)=0
        cheatSeq(2)=0
    EndIf
End Function

Function setStanceFrame(n)
    guy=curGuy(n)
    zStanceSeq(n)=zStanceSeq(n)+1
    If zStanceSpeed(guy) > 0 Then
        If zStanceSeq(n) Mod zStanceSpeed(guy) = 0 Then 
            zStanceSeq(n)=0
            menuStanceFrame(n)=menuStanceFrame(n)+1
        End If
    End If
End Function

Function selectSecretChars()
    If cheat(2) > 0 Then
        curGuy(cheat(2))=40:cheat(2)=0
    End If
End Function

Function getKeyPressed()
    retVal=0
    For i=0 To 105
        If KeyHit(i)=1 Then
            retVal=i
        End If
    Next
    
    return retVal
End Function

Function resetOtherCheats(currCheatIdx)
    Local maxCheats=20
    
    For i=0 To maxCheats
        If i <> currCheatIdx Then
            cheatSeq(i)=0
        End If
    Next
End Function

Function getTimeTaken$(totalMilliSecs)
    Local timeTaken$=""
    Local milliSecs=totalMilliSecs
    Local secs=milliSecs/1000
    Local minutes=secs/60
    
    If minutes > 99 Then
        timeTaken$="99:99:99"
        return timeTaken$
    End If
    
    If minutes < 10 Then timeTaken$ = "0"
    timeTaken$ = timeTaken$ + minutes + ":"
    
    secs = secs Mod 60
    If secs < 10 Then timeTaken$ = timeTaken$ + "0"
    timeTaken$ = timeTaken$ + secs + ":"
    
    milliSecs = milliSecs Mod 1000
    milliSecs = milliSecs / 10
    If milliSecs < 10 Then timeTaken$ = timeTaken$ + "0"
    timeTaken$ = timeTaken$ + milliSecs
    
    return timeTaken$
End Function

Function getButPicXOffset(b)
    xOffset=10
    Select b
    Case 2
        xOffset=12
    Case 3
        xOffset=-5
    Case 4
        xOffset=14
    Case 6
        xOffset=-9
    Case 7
        xOffset=-1
    Case 8
        xOffset=14
    Case 9
        xOffset=4
    Case 11
        xOffset=4
    Case 12
        xOffset=14
    Case 13
        xOffset=16
    Case 14
        xOffset=8
    Case 15
        xOffset=-8
    Case 16
        xOffset=13
    Case 17
        xOffset=1
    Case 18
        xOffset=-14
    Case 19
        xOffset=-11
    Case 20
        xOffset=13
    End Select

    return xOffset
End Function

Function getButPicYOffset(b)
    yOffset=90
    If b=15
        yOffset=93
    End If
    
    return yOffset
End Function

Function getCurStanceFrame(n, b)
    Local butFrame
    If curGuy(n)=1 Then
        menuStanceFrame(n)=getEvilRyuStance(n, xbut(b)+60, 550)
    Else If curGuy(n)=2 Then
        menuStanceFrame(n)=getRashStance(n)
    Else If curGuy(n)=5 Then
        menuStanceFrame(n)=getLeonardoStance(n)
    Else If curGuy(n)=7 Then
        menuStanceFrame(n)=getBatmanStance(n)
    Else If curGuy(n)=8 Then
        menuStanceFrame(n)=getSolStance(n)
    Else If curGuy(n)=19 Then
        menuStanceFrame(n)=getLeiLeiStance(n)
    Else If curGuy(n)=20 Then
        menuStanceFrame(n)=getKenshiroStance(n)
    Else
        setStanceFrame(n)
    End If
    
    If menuStanceFrame(n) > zStanceFrames(curGuy(n)) Then menuStanceFrame(n)=1:zStanceSeq(n)=0
    butFrame=menuStanceFrame(n)
    if butFrame=0 Then butFrame=1
    
    return butFrame
End Function