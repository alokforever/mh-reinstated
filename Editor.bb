Graphics 1920,1080,32,2
;Graphics 800,600,16

SetBuffer BackBuffer()
HidePointer
Global mapsdir$="maps\Original\"    ;Path for maps
Global gfxdir$="gfx\stuff\"            ;Path for graphics

AppTitle "MH Editor"

;sa= safe Area for AI
;da= danger area for AI
Dim tempN(10)
Dim saX(50),saY(50),saW(50),saH(50)
Dim saLLimit(50),saRlimit(50),dangerArea(50),fleeDir(50)
Dim edges(50),saMovedBy(50),daMovedBy(50),saDrawn(50)
Dim zxStart(10),zyStart(10),zxRespawn(10),zyRespawn(10)
Dim daX(50),daY(50),daW(50),daH(50),daType(50) ;1=danger , 2=jump
Dim daLLimit(50),daRlimit(50),dfleeDir(50),daTargetH(50)
Dim dmovedBy(50),daDrawn(50)
Global aiAreaMode, snapedOK, duckLine, noAirStrike

Dim xFlag(4),yFlag(4)
Dim xFlagStart(4),yFlagStart(4)
Dim xBase(4),yBase(4)
Dim nextMap(5)
Global flagAmount,curFlag, lScrLimit, rScrLimit,dScrLimit, yScrCameraLimit,uScrLimit, simulate
Global msg,msgSeq,msgTxt$,butClicked, musicN1,musicN2, showEvents, showOneLayer,mouseWheel

Global Famount,facMode
Dim curF(100),FdelaySeq(100),Fevent(100),FfacAmount(100),Floop(100)
Dim xfac(100,100),yfac(100,100), facdir(100,100), facLife(100,100),facLives(100,100),facDamage(100,100),facAiLevel(100,100)
Dim facTeam(100,100),facCategory(100,100), facType(100,100), facDelay(100,100), facDeadEvent(100,100),facWaitEvent(100,100)
Dim facChunk(100,100),facSound(100,100),facVar1(100,100),facVar2(100,100),facVar3(100,100),facVar4(100,100),facVar5(100,100)

Dim mx(10),my(10),mw(10),mh(10)
Dim xBut(200),yBut(200),wBut(200),hBut(200), Nbut(200),strBut$(200),typeBut(200)

Global forceAiAreaDraw, forcePlatDraw, ForceBoxDraw

Global curAni,tAniAmount,taniMEnu
Dim tAniSeq(200),tAniFrames(200)
Dim tAniBg(200,200),tAniN(200,200),tAniTime(200,200),tAniCurFrame(200)
Dim taniBgSel(200),taniNSel(200)

Global platAmount, platMode,endN,artMode=1
Dim xplatDraw(200),yPlatDraw(200),platFinalDest(200)
Dim xoldPlat#(200),xplat#(200),yPlat#(200),xPlatPoint(200,200),yPlatPoint(200,200)
Dim platXSpeed#(200),platYSpeed#(200),platPointsAmount(200)
Dim drawPlat(200),dangerPlat(200),zOnPlatN(200),platUseTrigger(200), platEventN(200), platEventN2(200)
Dim platCurPoint(200),platXDir(200),platYDir(200),platWidth(200),platHeight(200),platOn(200)
Dim xplatdest(200),yplatDest(200),platPic(200),platBreak(200),platChunk(200),platSound(200),platBreakable(200)

Global boxAmount, boxMode, fightMode, xScrStart, yScrStart
Dim xbox#(200),ybox#(200),xBoxPoint(200,200),yBoxPoint(200,200),boxXSpeed#(200),boxYSpeed#(200)
Dim boxCurPoint(200),boxWidth(200),boxPointsAmount(200),drawBox(200),boxBreak(200)
Dim boxheight(200),boxDamage(200),BoxType(200),targetBox(200),boxHitSound(200), boxEventN2(200)
Dim boxhitmode(200),boxhitTime#(200),boxHitSpeed#(200),boxHitYspeed#(200),boxChunkType(200)
Dim boxUseTrigger(200),boxEventN(200),boxFinalDest(200),boxSound(200),boxBreakable(200)
Dim boxXdir(200),boxYDir(200),xOldBox(200),yOldBox(200),xboxDest(200),yBoxDest(200)
Dim boxReachedDest(200),boxEventN2(200)

Global wallAmount,ColorR,ColorG,ColorB,wallMode,tileMode,lastLoaded,lastStartN,selectTile,bg,tileSet,maxTileSet
Global curPawn, pawnAmount,hideWalls,scrollMap,scrLock,showTileNumber,maxTileBG=1500,bgAmount=5
Dim xTile2(5,1500),yTile2(5,1500),tileFollowType(5,1500)
Dim tileAmount(5),xTile#(5,1500),yTile#(5,1500),tilePic(5,1500),tileDrawn(1500),tileFixed(5,1500),tileSetS(1500),tileSetE(1500),tileSetNumber(500,1500)
Dim tileXend1(5,1500),tileXend2(5,1500), tileXrand1(5,1500), tileXrand2(5,1500), tileXspeed#(5,1500)
Dim tileYend1(5,1500),tileYend2(5,1500), tileYrand1(5,1500), tileYrand2(5,1500), tileYspeed#(5,1500)
Dim tileXstart(5,1500), tileYstart(5,1500),tileFollow(5,1500),tileTarget(5,1500)
Dim xWall(1500),yWall(1500),wallWidth(1500),wallHeight(1500),wallType(1500),wallDrawn(1500)
Dim xtNail(1500),ytNail(1500),tWidth(1500),tHeight(1500),tPic(1500),tileNumber(5,1500),tNumber(1500),tTileSet(1500)
Dim xTileScrSpeed#(5,1500),yTileScrSpeed#(5,1500)

Dim Tx(100),Ty(100),Tw(100),Th(100),Tway(100),Tevent(100),Ton(100),Tdraw(100),TimageN(100),Tonce(100)
Dim TpassBy(100), TzAction(100), TobjHit(100),EventN(100),Timage(100,5),TimgX(100),TimgY(100),Tsound(100)
Dim TonStatus(100),ToffStatus(100),Tplatx(100),Tplaty(100),Tfollow(100)
Dim eventUsedByPlat(100),eventUsedByBox(100),eventUsedByTrigger(100),eventUsedByFevent(100),Taffect(100)
Dim EventUsedByDeadEvent(100),EventUsedByWaitEvent(100)
Global triggerAmount,triggerMode, triggerImageAmount=20

Dim ptImage(200),shotImage(50)
Global maxWidth    ;test

Dim SoundStr$(20)
SoundStr$(0)="N/A"
SoundStr$(1)="slash"
SoundStr$(2)="high punch"
SoundStr$(3)="s.Door"
SoundStr$(4)="m.Door"
SoundStr$(5)="fire"
SoundStr$(6)="laser"
SoundStr$(7)="shot"
SoundStr$(8)="click"
SoundStr$(9)="sbb"
SoundStr$(10)="bats"
SoundStr$(11)="electr."
;soundFx(1)=slashSnd           ;sound list
;soundFx(2)=highpunchsnd
;soundFx(3)=sDoorSnd
;soundFx(4)=mDoorSnd
;soundFx(5)=fireHitSnd
;soundFx(6)=laserSnd
;soundFx(7)=shotSnd
;soundFx(8)=clickSnd
;soundFx(9)=marioFierceSnd

;Paths For directories / mods
Dim modFolder$(500), modName$(500)
Global modsAmount, modSelected
Global mapsBaseDir$="maps\"
mapsdir$="maps\original\"
modName(1) = "original"

setModDirs()
initMenu()

pawnAmount=4
curPawn=1
bg=1 : uScrLimit=-50000
Dim tileBmp(20,500)

Global platImageAmount=3, boxImageAmount=6, characterAmount=6 , tAmount

Dim platImage(10), boxImage(10), guyImage(100),objImage(50),objImageN(50)

For i=1 To 52     ;add characterAmount
    gfxdir$="gfx\"+i+"\"
    guyImage(i)=LoadImage(gfxdir$ + "zwalk0.bmp")
Next

gfxdir$="gfx\stuff\"
For i=1 To platImageAmount
    platImage(i)=LoadImage(gfxdir$ + "plat" + i + ".bmp")
Next
For i=1 To triggerImageAmount
    Timage(i,1)=LoadImage(gfxdir$ + "trig" + i + "_a1.bmp")
Next
For i=1 To boxImageAmount
    boxImage(i)=LoadImage(gfxdir$ + "box" + i + "_a1.bmp")
Next
For n=1 To 50
    ptImage(n)=LoadImage( gfxdir$ + "pt" +n+ "_a1.bmp" )
Next

For i=1 To 20
    gfxdir$="gfx\stuff\"
    objImageN(i)=LoadImage(gfxdir$ + "obj"+i+"_1.bmp")
Next

For n=1 To 50
    shotImage(n)=LoadImage( gfxdir$ + "shot" +n+ ".bmp" )
Next

;organize object images
objImage(1)=objImageN(1)
objImage(2)=objImageN(2)
objImage(3)=objImageN(3)
objImage(4)=objImageN(4)
objImage(5)=objImageN(5)
objImage(6)=objImageN(6)
objImage(7)=objImageN(7)
objImage(8)=objImageN(8)
objImage(9)=objImageN(9)
objImage(10)=objImageN(10)
objImage(11)=objImageN(11)
objImage(12)=shotImage(30)
objImage(13)=objImageN(13)
objImage(14)=objImageN(14)
objImage(15)=shotImage(33)
objImage(16)=objImageN(12)
objImage(17)=objImageN(16)
objImage(18)=objImageN(17)
objImage(19)=objImageN(18)

;organize shot images
shotImage(27)=shotImage(33)
shotImage(26)=shotImage(32)
shotImage(25)=shotImage(30)
shotImage(24)=shotImage(29)
shotImage(23)=shotImage(28)
shotImage(22)=shotImage(26)
shotImage(21)=shotImage(24)
shotImage(20)=shotImage(23)
shotImage(19)=shotImage(20)
shotImage(18)=shotImage(18)
shotImage(17)=shotImage(17)
shotImage(16)=shotImage(14)
shotImage(15)=shotImage(12)
shotImage(14)=shotImage(7)
shotImage(13)=shotImage(7)
shotImage(12)=shotImage(11)
shotImage(10)=shotImage(2)    ;Shot list organization

Dim flagImage(2)
flagImage(1)=LoadImage(gfxdir$ + "flag1.bmp")
flagImage(2)=LoadImage(gfxdir$ + "flag2.bmp")

Global cameraImage=LoadImage(gfxdir$ + "camera.bmp")
Global pawnImage=LoadImage(gfxdir$ + "pawn.bmp")
Global respawnImage=LoadImage(gfxdir$ + "respawn.bmp")

Global areaAmount,dAreaAmount,areaType,mapShow
Global xScr=200, yScr=0
Global cn=1, cn2, cn3, cn4, cn5, cn6, cn7, cn8 ;current number of iten being manipulated
Global click1=0,map,map2,curLoop
mapShow=1
areaType=1
areaAmount=10
dareaAmount=10
cn7=1

For b=1 To 5
tileAmount(b)=0
Next
For i=1 To 200
    tAniTime(i,1)=1
    tAniFrames(i)=1
Next

Global curMap, mapN
Global mapStr$, map_Str$
mapStr$= "map"
map_str$= "_map"
;mapStr$= "CTFmap"        ;<- use this To edit CTF maps
;map_str$= "_CTFmap"    

saveall=0
If saveall=1 Then
    an$=Input("SAVE ALL! how many maps? ") 
    ;mapStr$= "amap"
    ;mapStr$= "map"
    mapStr$= "CTFmap"
    saveAll(an$) 
EndIf

Print "type a number for versus level, a+# for Adventure mode, c+# for CTF" 
Print "Example: typing 'a1' would open/create level 1 For Adventure mode"
an$=Input("-> ")
If Mid$(an$,1,1 ) = "c" Then
    mapStr$= "CTFmap"        ;<- use this To edit CTF maps
    map_str$= "_CTFmap"    
    curMap=Mid$(an$,2,3)    
ElseIf Mid$(an$,1,1) = "a" Then
    mapStr$= "amap"        ;<- use this To edit adventure maps
    map_str$= "_amap"    
    curMap=Mid$(an$,2,3)
Else
    curMap =an$    ;map number
EndIf

FlushKeys

tileSet=1
CurAni=1
loadMap()

tileSet=1
For n=1 To areaAmount
    saDrawn(n)=1
Next
For n=1 To dareaAmount
    daDrawn(n)=1
Next

defineTiles
loadTileSet()
curLoop=1

;-------------------------------
;-------- MAIN LOOP--------------
;-------------------------------
While Not KeyHit(1)

If KeyHit(19) Then
    AdjustScreen
End If

;delay 10

If KeyHit(181) Then
    If duckLine=1 Then duckLine=0 Else duckLine=1
EndIf
If KeyHit(50) Then
    If mapShow=0 Then mapShow=1 Else mapShow=0
EndIf
If KeyDown(75) Then xScr=xScr+20
If KeyDown(77) Then xScr=xScr-20
If KeyDown(72) Then yScr=yScr+20
If KeyDown(80) Then yScr=yScr-20 

mouseWheel=MouseZSpeed()
If mouseWheel=1 Then yScr=yScr+20
If mouseWheel=-1 Then yScr=yScr-20

If KeyHit(31) Then 
    If simulate=0 Then
        saveMap() 
        If msg=0 Then msg=1:msgseq=0:msgTxt$="MAP SAVED."
    Else
        msg=1:msgseq=0:msgTxt$="CAN'T SAVE DUE TO SIMULATION."
    EndIf
EndIf

If KeyHit(15) Then 
    If showEvents=1 Then showEvents=0 Else showEvents=1
EndIf

If KeyHit(38) Then
Locate 500,500
    FlushKeys()
    curMap=Input("Load map# => ")
    FlushKeys()
    loadMap()
    simulate=0
EndIf

If KeyHit(59) Then
    curLoop=1 :click1=0
    If cn > 20 Then cn=1
EndIf
If KeyHit(60) Then
    If cn > platAmount Then cn=1
    FlushKeys()
    curLoop=2:click1=0
EndIf
If KeyHit(61) Then
    If cn > boxAmount Then cn=1
    FlushKeys()
    curLoop=3 :click1=0
EndIf
If KeyHit(62) Then curLoop=4
If KeyHit(63) Then
    If cn=0 Then cn=1
    FlushKeys()
    If bg > 5 Then bg=0
    curLoop=5:click1=0
EndIf
If KeyHit(64) Then
    If cn > 100 Then cn=1
    curLoop=6:click1=0
EndIf
If KeyHit(65) Then
    curLoop=7:click1=0
EndIf
If KeyHit(66) Then
    curLoop=0:click1=0
EndIf

If KeyHit(87) Then    ;F11 to take snap shot
  If mapStr$= "amap" Then
    temppic=CreateImage(1022,766)
    CopyRect 0,0,1022,766,0,0,FrontBuffer(),ImageBuffer(temppic)
    ScaleImage temppic,.075,.075
    SaveBuffer(ImageBuffer(temppic)), mapsdir$ + mapStr$ + "_"+curMap+".jpg"
    FreeImage temppic
    msg=1:msgSeq=0:msgTxt="Snap shot taken."
  Else
    temppic=CreateImage(1022,766)
    CopyRect 0,0,1022,766,0,0,FrontBuffer(),ImageBuffer(temppic)
    ScaleImage temppic,.155,.155
    SaveBuffer(ImageBuffer(temppic)), mapsdir$ + mapStr$ + "_"+curMap+".jpg"
    FreeImage temppic
    msg=1:msgSeq=0:msgTxt="Snap shot taken."
  EndIf

EndIf

If KeyHit(88) Then    ;change map number And keep current map
FlushKeys()
Locate 500,500
curMap=Input("Type current map# ")
EndIf

Select curLoop
    Case 1: AiArea
    Case 2: plataform
    Case 3: box
    Case 4: buildWall
    Case 5: buildArt
    Case 6: triggers
    Case 7: factory
End Select

;*************************************************
;----------------- rendering ---------------------
;*************************************************
Cls
Line 0,0,0,0    ;oddly, by rendering a line, it makes sure mouse input isn't laggy
Select mapShow
    Case 0
      For i= 1 To wallAmount
        Color 128,128,128
        Rect xWall(i)+xScr,yWall(i)+yScr,wallWidth(i),wallHeight(i),1
      Next
    Case 1 
      Color colorR,colorG,colorB
      Rect 0+xScr,332+yScr,1920,1080,1
      
      If showOneLayer=1 Then
        For i=1 To tileAmount(bg)
            If tilePic(bg,i) <> 0 Then DrawImage tilePic(bg,i),xTile(bg,i)+xScr,yTile(bg,i)+yScr
            Color 255,43,234
            If showTileNumber Then Text xTile(bg,i)+xScr+2,yTile(bg,i)+yScr+2, i
        Next
      Else
          For b=0 To bgAmount
                For i=1 To tileAmount(b)
                If tilePic(b,i) <> 0 Then DrawImage tilePic(b,i),xTile(b,i)+xScr,yTile(b,i)+yScr
                
                Next
            Next
      EndIf
      
      
      If showTileNumber Then  
        For i=1 To tileAmount(bg)  
            Color 0,0,0
            Rect xTile(bg,i)+xScr+1 , yTile(bg,i)+yScr+1, 30,17,1
            Color 255,43,234
            Text xTile(bg,i)+xScr+2,yTile(bg,i)+yScr+2, i      
        Next
      EndIf
      
      If bg>bgamount Then bg=0
      If tilePic(bg,cn) <>0 And curLoop=5 Then
        Color Rand(1,255),Rand(1,255),Rand(1,255)
        Rect xTile(bg,cn)+xScr+10,yTile(bg,cn)+yScr,10,1,0
        Rect xTile(bg,cn)+xScr,yTile(bg,cn)+yScr+10,1,10,0
        Rect xTile(bg,cn)+xScr+ImageWidth(tilePic(bg,cn)) ,yTile(bg,cn)+yScr+ImageHeight(tilePic(bg,cn))-10,1,10,0
        Rect xTile(bg,cn)+xScr+ImageWidth(tilePic(bg,cn))-10 ,yTile(bg,cn)+yScr+ImageHeight(tilePic(bg,cn)),10,1,0  
      EndIf  
      If hideWalls=0 Then
        For i=1 To wallAmount
            Color 255,255,255
            Rect xWall(i)+xScr,yWall(i)+yScr,wallWidth(i),wallHeight(i),0
          Next
      EndIf
End Select
;---------------------
Select curLoop
    Case 1:renderAiArea
    Case 2:renderPlat
    Case 3:renderBox
    Case 4:RenderWall
    Case 5:renderArt
    Case 6:renderTrigger
    Case 7:renderFactory
End Select
If forceAiAreaDraw =1 Then renderAiArea
If forcePlatDraw =1 Then renderPlat
If forceBoxDraw =1 Then renderBox

;draws mouse cross
If curLoop <> 0 Then
    Color 255,255,255
    Rect MouseX()-5 , MouseY()   ,10,1 ,0
    Rect MouseX()   , MouseY()-5 ,1 ,11,0
    ;draws line to show player duck height
    If duckLine Then Rect MouseX()+3 , MouseY()-25 ,1 ,25,0
    ;Text 200, 710, "platPic(1)= " + platPic(1) + "  cn = " + cn
    Text 400, 745, "X=" + (MouseX()-xScr) + " Y=" + (MouseY()- yScr) + "  "+modName(modSelected) +"\"+ mapStr$+curMap
End If

;--------------------
If msg=1 Then
    msgseq=msgseq+1
    If msgseq > 130 Then msgseq=0:msg=0
    x=600: y=730
    w=StringWidth(msgTxt$)+20 
    h=30
    Color 0,0,0
    Rect x,y,w,h,1
    Color Rand(1,255),Rand(1,255),Rand(1,255)
    Rect x+2,y+2,w-4,h-4,0
    Color 255,255,255
    Text x+10,y+10, msgTxt$
EndIf
;--------debug--------------
;Text 600,600,"xScr= " + xScr
;Text 600,615,"mouseclicked= " + mouseclicked
;Text 600,630,"mouseclicked2= " + mouseclicked2

Flip
Wend
End
;----------------------Tiles set definition ------------------------------------
Function defineTiles()
i=1
;define max amount of tiles per set
For i=1 To 10    ;# of tile sets
    tileSetS(i)=1 : tileSetE(i)=120  :  maxTileSet=i
Next
End Function
;------- Loads tile set -----------
Function loadTileSet()
gfxDir$="gfx\tiles\"

ii=1
x=4:y=4
maxWidth=0
For i=1 To tileSetE(tileSet)
    If TileBmp(tileSet,i) = 0 Then tileBmp(tileSet,i)=LoadImage(gfxDir$ + tileSet +"_"+ i +".bmp")
    If TileBmp(tileSet,i) = 0 Then Exit
    tPic(ii) = tileBmp(tileSet,i)
    xtNail(ii)=x
    ytNail(ii)=y
    tWidth(ii)=ImageWidth(tPic(ii))
    
    If tWidth(ii) > maxWidth Then maxWidth=tWidth(ii)
    If maxWidth > 200 Then maxWidth=200
    
    tHeight(ii)=ImageHeight(tPic(ii))
    tnumber(ii)=i
    tTileSet(ii)=tileSet
    y = y + ImageHeight(tPic(ii))
    tAmount=i
    If y+tHeight(ii) > 700 Then
        x=x + maxWidth : y=4
        maxWidth=0
    EndIf
    ii=ii+1
    
Next

End Function

Function AIarea()
;-------LOOP (AI area)------
If MouseHit(1) Then mouseClicked=1 Else mouseClicked=0

If KeyDown(29) And mouseclicked=1 Then    ;hold down CTRL + click to select AI sa area
        mouseclicked=0
        x = MouseX()
        y = MouseY()
    For n=1 To dareaAmount
        If x > dax(n)+xScr And x < dax(n)+daw(n)+xScr Then
            If y > day(n)+yScr And y < day(n)+dah(n)+yScr Then
                cn=n
                areaType=2
                gotda=1
                Exit
            EndIf
        EndIf
    Next
    If gotda=0 Then
      For n=1 To areaAmount
        If x > sax(n)+xScr And x < sax(n)+saw(n)+xScr Then
            If y > say(n)+yScr And y < say(n)+sah(n)+yScr Then
                cn=n
                areaType=1
                Exit
            EndIf
        EndIf
      Next
           EndIf
EndIf


If mouseClicked=1 Then
Select areaType
Case 1
    If click1=0 Then
        click1=1
        saDrawn(cn)=0
        saX(cn)=(MouseX()-xScr):saY(cn)=MouseY()- yScr
        saW(cn)=0:saH(cn)=0
        Goto clickDone
    EndIf
    If click1=1 Then
        click1=0
        saDrawn(cn)=1
        saW(cn)= (MouseX()-saX(cn))-xScr
        saH(cn)= (MouseY()-saY(cn))- yScr
        Goto clickDone
    EndIf

Case 2
    If click1=0 Then
        click1=1
        daDrawn(cn)=0
        daX(cn)=(MouseX()-xScr):daY(cn)=MouseY()- yScr
        daW(cn)=0:daH(cn)=0
        Goto clickDone
    EndIf
    If click1=1 Then
        click1=0
        daDrawn(cn)=1
        daW(cn)= (MouseX()-daX(cn))-xScr
        daH(cn)= (MouseY()-daY(cn))- yScr
        Goto clickDone
    EndIf

End Select
.clickDone
EndIf


If KeyHit(33) Then
    If forceAiAreaDraw=1 Then forceAiAreaDraw=0 Else forceAiAreaDraw=1
EndIf

If KeyHit(14) Then
    If areaType=1 Then areaType=2 Else areaType=1
EndIf

If KeyHit(205) Then 
    cn=cn+1
    If cn > 50 Then cn=1
EndIf
If KeyHit(203) Then 
    cn=cn-1
    If cn < 1 Then cn=50
EndIf

If KeyHit(2) Then
Select areaType
Case 1
    If fleeDir(cn)=2 Then fleeDir(cn)=4 Else fleeDir(cn)=2
    If fleeDir(cn)=0 Then fleeDir(cn)=2
Case 2
    If dfleeDir(cn)=2 Then dfleeDir(cn)=4 Else dfleeDir(cn)=2
    If dfleeDir(cn)=0 Then dfleeDir(cn)=2
End Select
EndIf

If KeyHit(3) Then
Select areaType
Case 1
    If dangerArea(cn)=1 Then dangerArea(cn)=0 Else dangerArea(cn)=1
Case 2
    If daType(cn)=1 Then daType(cn)=0 Else daType(cn)=1

End Select
EndIf

If KeyHit(4) Then
Select areaType
Case 1
    If edges(cn)=1 Then edges(cn)=0 Else edges(cn)=1
Case 2
    daTargetH(cn)=daTargetH(cn)+5
    If daTargetH(cn)>200 Then daTargetH(cn)=0 

End Select
EndIf

shift=0
If KeyDown(42) Then shift=1 Else shift=0

If KeyHit(5) Then
    Select areaType
    Case 1
        If shift=0 Then
            saMovedBy(cn) = saMovedBy(cn) + 1
        Else
            saMovedBy(cn) = saMovedBy(cn) - 1
        EndIf

    Case 2
        If shift=0 Then
            daMovedBy(cn) = daMovedBy(cn) + 1
        Else
                daMovedBy(cn) = daMovedBy(cn) - 1
        EndIf

    End Select
EndIf

;If KeyHit(6) Then
;    If AiAreaMode<>1 Then aiAreaMode=1 Else aiAreaMode=0
;EndIf

;If KeyHit(7) Then
;    If AiAreaMode<>2 Then aiAreaMode=2 Else aiAreaMode=0
;EndIf

If KeyHit(201) Then
Select areaType
Case 1
    areaAmount=areaAmount+1
    If areaAmount > 50 Then areaAmount=1
Case 2
    dAreaAmount=dAreaAmount+1
    If dareaAmount > 50 Then dareaAmount=1

End Select
EndIf

If KeyHit(209) Then
Select areaType
Case 1
    areaAmount=areaAmount-1
    If areaAmount < 1 Then areaAmount=20
Case 2
    dAreaAmount=dAreaAmount-1
    If dareaAmount < 1 Then dareaAmount=20

End Select
EndIf
End Function
;-------LOOP (plataforms)------
Function plataform()

mouseClicked=0
mouseClicked2=0
If MouseHit(1) Then mouseClicked=1
If MouseHit(2) Then mouseClicked2=1

If KeyDown(29) And mouseclicked=1 Then    ;hold down CTRL + click to select plat
For n=1 To platAmount
    pw=5 : ph=5
    If MouseX() > xplat(n)+xScr And MouseX() < xplat(n)+pw+xScr Then
        If MouseY() > yPlat(n)+yScr And MouseY() < yplat(n)+ph+yScr Then
            cn=n
            Goto gotplat
        EndIf
    EndIf
Next

For n=1 To platAmount
    If platHeight(n) > 4 Then
        ph=platHeight(n)
    Else
        ph=5
    EndIf
    If MouseX() > xplat(n)+xScr And MouseX() < xplat(n)+platWidth(n)+xScr Then
        If MouseY() > yPlat(n)+yScr And MouseY() < yplat(n)+ph+yScr Then
            cn=n
            Goto gotplat
        EndIf
    EndIf
Next
.gotplat
mouseclicked=0
EndIf

If KeyDown(42) Then shift=1 Else shift=0
If KeyDown(56) Then alt=1 Else alt=0

If shift Then
    If KeyHit(2) Then
        platMode=11
        mouseClicked=0
    EndIf
EndIf
If shift Then
    If KeyHit(3) Then platMode=12
EndIf

For i=1 To 10
    If KeyHit(i) Then platMode= (i-1)
    
Next

If mouseCLicked=1 Then
    Select platMode
    Case 4
        xPlatPoint(cn,platCurPoint(cn)) = MouseX()-xScr
        yPlatPoint(cn,platCurPoint(cn)) = MouseY()-yScr
    Default : xPlat(cn)=(MouseX()-xScr):yPlat(cn)=(MouseY()- yScr)
    End Select


EndIf

If mouseClicked2=1 Then
    If click1=0 Then
        click1=1
        xplat(cn)=(MouseX()-xScr) : yplat(cn)=MouseY()- yScr
        platWidth(cn)=0:platHeight(cn)=0
        Goto clickDonep
    EndIf
    If click1=1 Then
        click1=0
        platWidth(cn)= (MouseX()-xplat(cn))-xScr
        platHeight(cn)= (MouseY()-yplat(cn))- yScr
        If platHeight(cn) < 5 Then platHeight(cn)=0
        Goto clickDonep
    EndIf
EndIf
.clickDonep

If KeyHit(11) Then platMode=0

If KeyHit(46) Then      ;copy platform attributes to another, x/y offset optional
    FlushKeys()
    l=500
    Locate 500,l
    n=Input( "Copy from # ") :l=l+15
    Locate 500,l
    nn=Input( "       to # ") :l=l+15
    Locate 500,l
    xd=Input( "X offset: ") :l=l+15
    Locate 500,l
    yd=Input( "Y offset: ") :l=l+15
    FlushKeys()    

platPointsAmount(nn)=platPointsAmount(n):platWidth(nn)=platWidth(n):platHeight(nn)=platHeight(n)
platCurpoint(nn)=platCurpoint(n):platXSpeed(nn)=platXspeed(n):platYSpeed(nn)=platYspeed(n)
drawPlat(nn)=drawPlat(n) : platSound(nn)=platSound(n): platChunk(nn)=platChunk(n)
dangerPlat(nn)=dangerPlat(n): platEventN2(nn)=platEventN2(n)
platPic(nn)=platPic(n):platEventN(nn)=platEventN(n)
platFinalDest(nn)=platFinalDest(n)
  For i= 1 To platPointsAmount(n)
    xPlatPoint(nn,i)=xPlatPoint(n,i)+xd
    yPlatPoint(nn,i)=yPlatPoint(n,i)+yd
  Next
;xplat(nn)=xplat(n)+xd:yplat(nn)=yPlat(n)+yd

EndIf

If KeyHit(18) Then    ;Toggle platform event on/off
    If eventN(platEventN(cn))=1 Then eventN(platEventN(cn))=0 Else eventN(platEventN(cn))=1
EndIf

If KeyHit(33) Then
    If forceplatDraw=1 Then forceplatDraw=0 Else forceplatDraw=1
EndIf

sn#=.5
If alt=1 Then sn#=.1

If KeyHit(13) Then
    Select platMode
    Case 1:    
        platwidth(cn)=platWidth(cn)+2
        If KeyDown(42) Then platwidth(cn)=platWidth(cn)+5
    Case 2: platXspeed(cn)=platXspeed(cn)+sn#
    Case 3: platYspeed(cn)=platYspeed(cn)+sn#
    Case 4
        platCurPoint(cn)=platCurPoint(cn)+1
        If platCurPoint(cn) > platPointsAmount(cn) Then platCurPoint(cn)=1    
    Case 5
        platPointsAmount(cn)=platPointsAmount(cn)+1
        If platPointsAmount(cn) > 200 Then platPointsAmount(cn) =1
    Case 6: If dangerPlat(cn)=0 Then dangerPlat(cn)=1 Else dangerPlat(cn)=0
    Case 7: If drawPlat(cn)=0 Then drawPlat(cn)=1 Else drawPlat(cn)=0    
    Case 8: 
        platPic(cn)=platPic(cn)+1
        If platPic(cn) > platImageAmount Then platPic(cn)=1

    Case 9: If platUseTrigger(cn)=1 Then platUseTrigger(cn)=0 Else platUseTrigger(cn)=1
    Case 0:    platEventN(cn)=platEventN(cn)+1
    Case 11:platFinalDest(cn)=platFinalDest(cn)+1
    Case 12:platEventN2(cn)=platEventN2(cn)+1
            
    End Select
EndIf

If KeyHit(12) Then
    Select platMode
    Case 1:    platwidth(cn)=platWidth(cn)-2
    Case 2: platXspeed(cn)=platXspeed(cn)-sn#
    Case 3: platYspeed(cn)=platYspeed(cn)-sn#
    Case 4
        platCurPoint(cn)=platCurPoint(cn)-1
        If platCurPoint(cn) < 1 Then platCurPoint(cn)=platPointsAmount(cn)    
    Case 5
        platPointsAmount(cn)=platPointsAmount(cn)-1
        If platPointsAmount(cn) < 1 Then platPointsAmount(cn) =200
    Case 6: If dangerPlat(cn)=0 Then dangerPlat(cn)=1 Else dangerPlat(cn)=0
    Case 7: If drawPlat(cn)=0 Then drawPlat(cn)=1 Else drawPlat(cn)=0    
    Case 8: 
        platPic(cn)=platPic(cn)-1
        If platPic(cn) < 1 Then platPic(cn)=platImageAmount
    Case 9: If platUseTrigger(cn)=1 Then platUseTrigger(cn)=0 Else platUseTrigger(cn)=1
    Case 0:    platEventN(cn)=platEventN(cn)-1
    Case 11:platFinalDest(cn)=platFinalDest(cn)-1
    Case 12:platEventN2(cn)=platEventN2(cn)-1
    
    End Select
EndIf

If KeyHit(16) Then
    xplat(cn)=xplatpoint(cn,platCurPoint(cn))
    yplat(cn)=yplatpoint(cn,platCurPoint(cn))
EndIf

If KeyHit(205) Then ;  -> arrow
    cn=cn+1
    If cn > platAmount Then cn=1
EndIf
If KeyHit(203) Then ;  <- arrow
    cn=cn-1
    If cn < 1 Then cn=platAmount
EndIf

If KeyHit(201) Then ; +
    platAmount=platAmount+1
    If platAmount > 200 Then platAmount=0
EndIf
If KeyHit(209) Then ; -
    platAmount=platAmount-1
    If platAmount < 0 Then platAmount=200
EndIf
End Function
;-----LOOP (boxes)-------------------
Function box()

mouseClicked=0
mouseClicked2=0
If MouseHit(1) Then mouseClicked=1
If MouseHit(2) Then mouseClicked2=1

If KeyDown(29) And mouseclicked=1 Then    ;hold down CTRL + click to select box
For n=1 To boxAmount
    If MouseX() > xBox(n)+xScr And MouseX() < xBox(n)+BoxWidth(n)+xScr Then
        If MouseY() > yBox(n)+yScr And MouseY() < yBox(n)+boxHeight(n)+yScr Then
            cn=n
            Goto gotBox
        EndIf        
    EndIf
Next
.gotbox
mouseclicked=0
EndIf

If mouseClicked Then
    Select boxMode
    Case 4
        xboxPoint(cn,boxCurPoint(cn)) = MouseX()-xScr
        yboxPoint(cn,boxCurPoint(cn)) = MouseY()- yScr
                
    Default : xbox(cn)=(MouseX()-xScr):ybox(cn)=MouseY()- yScr
    End Select

EndIf

hk=0
For i=2 To 11
    If KeyHit(i) Then
        hk=i:boxMode= (i-1)
        Exit
    EndIf
Next

If KeyDown(42) And hk=2 Then boxMode=11
If KeyDown(42) And hk=3 Then boxMode=12
If KeyDown(42) And hk=4 Then boxMode=13
If KeyDown(42) And hk=5 Then
    If boxBreak(cn)=1 Then boxBreak(cn)=0 Else boxBreak(cn)=1
EndIf
If KeyDown(42) And hk=6 Then boxMode=14

If mouseClicked2=1 Then
    If click1=0 Then
        click1=1
        xbox(cn)=(MouseX()-xScr) : ybox(cn)=MouseY()- yScr
        boxWidth(cn)=0:boxHeight(cn)=0
    Else
        click1=0
        boxWidth(cn)= (MouseX()-xbox(cn))-xScr
        boxHeight(cn)= (MouseY()-ybox(cn))- yScr
    EndIf
EndIf

If KeyHit(13) Then
    Select boxMode
    Case 1:    boxwidth(cn)=boxWidth(cn)+2
    Case 2: boxXspeed(cn)=boxXspeed(cn)+.2
    Case 3: boxYspeed(cn)=boxYspeed(cn)+.2
    Case 4
        boxCurPoint(cn)=boxCurPoint(cn)+1
        If boxCurPoint(cn) > boxPointsAmount(cn) Then boxCurPoint(cn)=1    
    Case 5
        boxPointsAmount(cn)=boxPointsAmount(cn)+1
        If boxPointsAmount(cn) > 200 Then boxPointsAmount(cn) =1
    Case 6: boxHeight(cn)=boxHeight(cn)+2
    Case 7: If drawbox(cn)=0 Then drawbox(cn)=1 Else drawbox(cn)=0    
    Case 8: If targetbox(cn)=0 Then targetbox(cn)=1 Else targetbox(cn)=0
    Case 9: 
        boxType(cn)=boxType(cn)+1
        If boxType(cn) > boxImageAmount Then boxType(cn)=1
    Case 10: 
        boxChunkType(cn)=boxChunkType(cn)+1
        If boxChunkType(cn) > 500 Then boxChunkType(cn)=1
    Case 11: If boxUseTrigger(cn)=1 Then boxUseTrigger(cn)=0 Else boxUseTrigger(cn)=1
    Case 12: boxEventN(cn)=boxEventN(cn)+1
    Case 13: boxFinalDest(cn)=boxFinalDest(cn)+1
    Case 14: boxEventN2(cn)=boxEventN2(cn)+1
    
    End Select
EndIf

If KeyHit(12) Then
    Select boxMode
    Case 1:    boxwidth(cn)=boxWidth(cn)-2
    Case 2: boxXspeed(cn)=boxXspeed(cn)-.2
    Case 3: boxYspeed(cn)=boxYspeed(cn)-.2
    Case 4
        boxCurPoint(cn)=boxCurPoint(cn)-1
        If boxCurPoint(cn) < 1 Then boxCurPoint(cn)=boxPointsAmount(cn)    
    Case 5
        boxPointsAmount(cn)=boxPointsAmount(cn)-1
        If boxPointsAmount(cn) < 1 Then boxPointsAmount(cn) =200
    Case 6: boxHeight(cn)=boxHeight(cn)-2
    Case 7: If drawbox(cn)=0 Then drawbox(cn)=1 Else drawbox(cn)=0    
    Case 8: If targetbox(cn)=0 Then targetbox(cn)=1 Else targetbox(cn)=0
    Case 9: 
        boxType(cn)=boxType(cn)-1
        If boxType(cn) < 1 Then boxType(cn)=boxImageAmount
    Case 10: 
        boxChunkType(cn)=boxChunkType(cn)-1
        If boxChunkType(cn) < 1 Then boxChunkType(cn)=500
    Case 11: If boxUseTrigger(cn)=1 Then boxUseTrigger(cn)=0 Else boxUseTrigger(cn)=1
    Case 12: boxEventN(cn)=boxEventN(cn)-1
    Case 13: boxFinalDest(cn)=boxFinalDest(cn)-1
    Case 14: boxEventN2(cn)=boxEventN2(cn)-1
    
    End Select
EndIf

If KeyHit(46) Then      ;Copy box
    FlushKeys()
    n=500
    Locate 500,n
    k=Input( "Copy from # ") :n=n+15
    Locate 500,n
    kk=Input( "       to # ") :n=n+15
    Locate 500,n
    xd=Input( "X offset : ") :n=n+15
    Locate 500,n
    yd=Input( "Y offset : ") :n=n+15
    FlushKeys()    

boxType(kk)=boxType(k)
boxPointsAmount(kk)=boxPointsAmount(k):boxWidth(kk)=boxWidth(k):boxHeight(kk)=boxHeight(k)
boxDamage(kk)=boxDamage(k):boxHitMode(kk)=boxHitMode(k):boxHitSpeed(kk)=boxHitSpeed(k)
boxHitYSpeed(kk)=boxHitYspeed(k):boxHitTime(kk)=boxHitTime(k)
boxCurpoint(kk)=boxCurpoint(k):boxXSpeed(kk)=boxXspeed(k):boxYSpeed(kk)=boxYspeed(k)
boxChunkType(kk)=boxChunkType(k):boxHitSound(kk)=boxHitSound(k)
drawBox(kk)=drawBox(k): targetBox(kk)=targetBox(k)
xBox(kk)=xBox(k) + xd: yBox(kk)=yBox(k) + yd
boxEventN(kk)=boxEventN(k) : boxUseTrigger(kk)=boxUseTrigger(k) :boxSound(kk)=boxSound(k)
boxFinalDest(kk)=boxFinalDest(k) : boxBreak(kk)=boxBreak(k): boxEventN2(kk)=boxEventN2(k)
For i= 1 To boxPointsAmount(k)
    xboxPoint(kk,i)=xBoxPoint(k,i) + xd
    yboxPoint(kk,i)=yboxPoint(k,i) + yd
Next
    
EndIf

If KeyHit(18) Then    ;Toggle box event on/off
    If eventN(boxEventN(cn))=1 Then eventN(boxEventN(cn))=0 Else eventN(boxEventN(cn))=1
EndIf

If KeyHit(47) Then    ;copy only damages/sound/chunk attributes
    FlushKeys()

    n=500
    Locate 500,n
    k=Input( "Copy this# ") :n=n+15
    Locate 500,n
    bb=Input( "    from# ") :n=n+15
    Locate 500,n
    ee=Input( "      to# ") :n=n+15
    
    FlushKeys()    

For kk=bb To ee
    boxType(kk)=boxType(k)
    boxDamage(kk)=boxDamage(k):boxHitMode(kk)=boxHitMode(k):boxHitSpeed(kk)=boxHitSpeed(k)
    boxHitYSpeed(kk)=boxHitYspeed(k):boxHitTime(kk)=boxHitTime(k)
    boxChunkType(kk)=boxChunkType(k):boxHitSound(kk)=boxHitSound(k)
    boxSound(kk)=boxSound(k)
Next
    
EndIf

If KeyHit(33) Then
    If forceBoxDraw=1 Then forceBoxDraw=0 Else forceBoxDraw=1
EndIf

If KeyHit(17) Then
    FlushKeys()
    n=500
    Locate 500,n
    boxDamage(cn)=Input( "box Damage   (" + boxDamage(cn) + ") = " ) :n=n+15
    Locate 500,n
    boxHitMode(cn)=Input("box Hit Mode (" + boxhitmode(cn) + ") = " ):n=n+15
    Locate 500,n
    boxHitTime(cn)=Input("box Hit Time (" + boxhittime(cn) + ") = " ):n=n+15    
    Locate 500,n
    boxHitSpeed(cn)=Input("box Hit X Speed(" + boxhitspeed(cn) + ") = " ):n=n+15
    Locate 500,n
    boxHitYSpeed(cn)=Input("box Hit Y Speed(" + boxhitYspeed(cn) + ") = " ):n=n+15
    Locate 500,n
    boxHitSound(cn)=Input("box Hit Sound(" + boxhitSound(cn) + ") = " ):n=n+15
    
    FlushKeys()
EndIf

If KeyHit(16) Then
    xbox(cn)=xboxpoint(cn,boxCurPoint(cn))
    ybox(cn)=yboxpoint(cn,boxCurPoint(cn))
EndIf

If KeyHit(205) Then ;  -> arrow
    cn=cn+1
    If cn > boxAmount Then cn=1
EndIf
If KeyHit(203) Then ;  <- arrow
    cn=cn-1
    If cn < 1 Then cn=boxAmount
EndIf

If KeyHit(201) Then ; +
    boxAmount=boxAmount+1
    If boxAmount > 200 Then boxAmount=0
EndIf
If KeyHit(209) Then ; -
    boxAmount=boxAmount-1
    If boxAmount < 0 Then boxAmount=200
EndIf
End Function
;-----Loop buildMap--------------
Function buildWall()

mouseClicked=0
mouseClicked2=0
If MouseHit(1) Then mouseClicked=1
If MouseHit(2) Then mouseClicked2=1

If KeyDown(51) And mouseClicked=1 Then
    mouseClicked=0
    lScrLimit=MouseX()-xScr
EndIf
If KeyDown(52) And mouseClicked=1 Then
    mouseClicked=0
    rScrLimit=MouseX()-xScr
EndIf
If KeyDown(53) And mouseClicked=1 Then
    mouseClicked=0
    yScrCameraLimit=(MouseY()-yScr)-768
EndIf
If KeyDown(40) And mouseClicked=1 Then
    mouseClicked=0
    uScrLimit=(MouseY()-yScr)
EndIf

If mouseClicked=1 And wallMode < 2 Then
    If click1=0 Then
        click1=1
        wallDrawn(cn)=0
        xwall(cn)=(MouseX()-xScr) : yWall(cn)=MouseY()- yScr
        wallWidth(cn)=0:wallHeight(cn)=0
        Goto clickDone2
    EndIf
    If click1=1 Then
        click1=0
        saDrawn(cn)=1
        wallWidth(cn)= (MouseX()-xWall(cn))-xScr
        wallHeight(cn)= (MouseY()-yWall(cn))- yScr
        cn=cn+1
        If cn > wallAmount Then cn=wallAmount
        Goto clickDone2
    EndIf

EndIf
.clickDone2

If mouseClicked2=1 Then
    If wallMode <> 4 Then wallMode=4 Else wallMode=5
EndIf

If mouseClicked=1 And wallMode=4 Then 
    zxStart(curPawn)=MouseX()-xScr
    zyStart(curPawn)=MouseY()-yScr
EndIf

If mouseClicked=1 And wallMode=5 Then 
    zxRespawn(curPawn)=MouseX()-xScr
    zyRespawn(curPawn)=MouseY()-yScr
EndIf

If mouseClicked=1 And wallMode=6 Then 
    xFlag(curFlag)=MouseX()-xScr
    yFlag(curFlag)=MouseY()-yScr
EndIf

If mouseClicked=1 And wallMode=10 Then 
    xScrStart=MouseX()-xScr
    yScrStart=MouseY()-yScr
EndIf

If KeyHit(9) Then
    FlushKeys()
    Locate 500,520
    musicN1=Input("Music 1 =("+musicN1 +") ")
    Locate 500,535
    musicN2=Input("Music 2 =("+musicN2 +") ")
    FlushKeys()
EndIf

If KeyHit(10) Then
    FlushKeys()
    Locate 500,520
    nextMap(1)=Input("nextMap1 =("+nextMap(1) +") ")
    Locate 500,535
    nextMAp(2)=Input("nextMap2 =("+nextMap(2) +") ")
    FlushKeys()
EndIf

If KeyHit(11) Then wallMode=10

If KeyHit(30) Then
    If noAirStrike=1 Then noAirStrike=0 Else noAirStrike=1
EndIf

For i=2 To 8
    If KeyHit(i) Then wallMode= (i-1)
Next

If KeyHit(13) Then
Select wallMode
    Case 1; draw mode
    Case 2:curPawn=curPawn+1
    Case 4; select location to start
    Case 5; select location to respawn 
    Case 6: If curFlag=1 Then curFlag=2 Else curFlag=1
    Case 7: If ScrollMap=1 Then ScrollMap=0 Else ScrollMap=1
End Select
EndIf

If KeyHit(12) Then
Select wallMode
    Case 1; draw mode
    Case 2:curPawn=curPawn-1
    Case 4; select location to start
    Case 5; select location to respawn 
    Case 6: If curFlag=1 Then curFlag=2 Else curFlag=1
    Case 7: If ScrollMap=1 Then ScrollMap=0 Else ScrollMap=1
End Select
EndIf

If KeyHit(205) Then 
    cn=cn+1
    If cn > wallAmount Then cn=1
EndIf
If KeyHit(203) Then 
    cn=cn-1
    If cn < 1 Then cn=wallAmount
EndIf

If KeyHit(209) Then
    wallAmount=wallAmount-1
    If wallAmount < 1 Then wallAmount=100
EndIf
If KeyHit(201) Then
    wallAmount=wallAmount+1
    If wallAmount > 100 Then wallAmount=0
EndIf


End Function
;-----Loop Triggers--------------
Function triggers()

mouseClicked=0
mouseClicked2=0
If MouseHit(1) Then mouseClicked=1
If MouseHit(2) Then mouseClicked2=1

If triggerMode=2 And mouseClicked=1 Then
    nu=( xplat(Tfollow(cn))+xScr ) - (Tx(cn)+xScr)
    Tplatx(cn) = (nu-nu*2)
    nu=( yplat(Tfollow(cn))+yScr ) - (Ty(cn)+yScr)
    Tplaty(cn) = (nu-nu*2)
    
    mouseClicked=0
EndIf

If triggerMode=1 And mouseClicked=1 Then    ;Select platform to stick to Trigger area
For n=1 To platAmount
    pw=5 : ph=5
    If MouseX() > xplat(n)+xScr And MouseX() < xplat(n)+pw+xScr Then
        If MouseY() > yPlat(n)+yScr And MouseY() < yplat(n)+ph+yScr Then
            Tfollow(cn) = n
            Goto gotele4
        EndIf
    EndIf
Next

For n=1 To platAmount
    If platHeight(n) > 4 Then
        ph=platHeight(n)
    Else
        ph=5
    EndIf
    If MouseX() > xplat(n)+xScr And MouseX() < xplat(n)+platWidth(n)+xScr Then
        If MouseY() > yPlat(n)+yScr And MouseY() < yplat(n)+ph+yScr Then
            Tfollow(cn) = n
            Goto gotele4
        EndIf        
    EndIf
Next
.gotele4
mouseClicked=0
EndIf

If KeyDown(29) And mouseclicked=1 Then    ;hold down CTRL + click to select trigger
    For n=1 To triggerAmount
        If MouseX() > Tx(n)+xScr And MouseX() < Tx(n)+Tw(n)+xScr Then
            If MouseY() > Ty(n)+yScr And MouseY() < Ty(n)+Th(n)+yScr Then
                cn=n
                gotT=1
            EndIf
        EndIf
    If gotT=1 Then Exit
    Next
    mouseclicked=0
EndIf

If mouseClicked=1 Then    ;position trigger ,set width And height
    If click1=0 Then
        click1=1
        Tx(cn)=(MouseX()-xScr) : Ty(cn)=MouseY()- yScr
        Tw(cn)=0:Th(cn)=0
        Goto clickDone3
    EndIf
    If click1=1 Then
        click1=0
        Tw(cn)= (MouseX()-Tx(cn))-xScr
        Th(cn)= (MouseY()-Ty(cn))- yScr
        Goto clickDone3
    EndIf

EndIf
.clickDone3


If KeyHit(2) Then 
        If TzAction(cn)=1 Then Tzaction(cn)=0 Else Tzaction(cn)=1
EndIf
If KeyHit(3) Then
    If Tpassby(cn)=1 Then Tpassby(cn)=0 Else Tpassby(cn)=1
EndIf
If KeyHit(4) Then
    If Tobjhit(cn)=1 Then Tobjhit(cn)=0 Else Tobjhit(cn)=1
EndIf
If KeyHit(5) Then
    Tway(cn)=Tway(cn)+1
    If Tway(cn)>14 Then Tway(cn)=1
EndIf
If KeyHit(6) Then triggerMode= 5
If KeyHit(7) Then
    If Tdraw(cn)=1 Then Tdraw(cn)=0 Else Tdraw(cn)=1
EndIf
If KeyHit(8) Then triggerMode= 7

If KeyHit(9) Then
    If Taffect(cn)=0 Then Taffect(cn)=1 Else Taffect(cn)=0
EndIf

If KeyHit(10) Then triggerMode=8    ;Sound selection

If KeyHit(11) Then
    If triggerMode<>1 Then triggerMode=1 Else triggerMode=0
EndIf

If KeyHit(24) Then
    If triggerMode<>2 Then triggerMode=2 Else aiAreaMode=0
EndIf


If KeyHit(18) Then
    If EventN(Tevent(cn))=1 Then EventN(Tevent(cn))=0 Else EventN(Tevent(cn))=1
EndIf

If KeyHit(13) Then
Select triggerMode
    Case 5:Tevent(cn)=Tevent(cn)+1
    Case 7:
        TimageN(cn)=TimageN(cn)+1
        If TimageN(cn) > triggerImageAmount Then TimageN(cn)=triggerImageAmount
    Case 8:Tsound(cn)=Tsound(cn)+1
End Select
EndIf

If KeyHit(12) Then
Select triggerMode
    Case 5:Tevent(cn)=Tevent(cn)-1
    Case 7:
        TimageN(cn)=TimageN(cn)-1
        If TimageN(cn) < 0 Then TimageN(cn)=0
    Case 8:Tsound(cn)=Tsound(cn)-1
End Select
EndIf

If KeyHit(19) Then
    If TonStatus(cn)=0 Then 
        TonStatus(cn)=1 : ToffStatus(cn)=0
    Else
        TonStatus(cn)=0 : ToffStatus(cn)=1
    EndIf
EndIf

If KeyHit(205) Then 
    cn=cn+1
    If cn > triggerAmount Then cn=1
EndIf
If KeyHit(203) Then 
    cn=cn-1
    If cn < 1 Then cn=triggerAmount
EndIf

If KeyHit(209) Then
    triggerAmount=triggerAmount-1
    If triggerAmount < 0 Then triggerAmount=100
EndIf
If KeyHit(201) Then
    triggerAmount=triggerAmount+1
    If triggerAmount > 100 Then triggerAmount=0
EndIf

If KeyHit(16) Then
    FlushKeys()
    x=500:y=500
    Locate x,y
    TimgX(cn)=Input("imgX(" +TimgX(cn)+ ") -> ") : y=y+15
    Locate x,y
    TimgY(cn)=Input("imgY(" +TimgY(cn)+ ") -> ") : y=y+15
    FlushKeys()
EndIf

End Function

;-----Loop build ART--------------
Function buildArt()

mouseClicked=0
mouseClicked2=0
;mouseButDown=0
;If mouseDown(1) Then mouseButDown=1
If MouseHit(1) Then mouseClicked=1
If MouseHit(2) Then
    If taniMEnu=1 Then 
        mouseClicked2=1
    Else
        tileFixed(bg,cn)=0
    EndIf
EndIf

If KeyDown(41) Then     ;temp test

n=2 : nn=3
tileAmount(n)=154
For i=1 To 154

    xTile(n,i)=xTile(nn,i)
    yTile(n,i)=yTile(nn,i)
    tilePic(n,i)=tilePic(nn,i)
    tileNumber(n,i)=tileNumber(nn,i)
    tileSetNumber(n,i)=tileSetNumber(nn,i)
    tileFixed(n,i)=tileFixed(nn,i)
    tileXend1(n,i)=tileXend1(nn,i)
    tileXend2(n,i)=tileXend2(nn,i)
    tileXrand1(n,i)=tileXrand1(nn,i)
    tileXrand2(n,i)=tileXrand2(nn,i)
    tileXspeed(n,i)=tileXspeed(nn,i)
    tileYend1(n,i)=tileYend1(nn,i)
    tileYend2(n,i)=tileYend2(nn,i)
    tileYrand1(n,i)=tileYrand1(nn,i)
    tileYrand2(n,i)=tileYrand2(nn,i)
    tileYspeed(n,i)=tileYspeed(nn,i)
    tileXstart(n,i)=tileXstart(nn,i)
    tileYstart(n,i)=tileYstart(nn,i)
    tileFollow(n,i)=tileFollow(nn,i)
    tileTarget(n,i)=tileTarget(nn,i)
    xTileScrSpeed(n,i)=xTileScrSpeed(nn,i)
    yTileScrSpeed(n,i)=yTileScrSpeed(nn,i)
    tileFollowType(n,i)=tileFollowType(nn,i)
    xTile2(n,i)=xTile2(nn,i)
    yTile2(n,i)=yTile2(nn,i)
Next

EndIf

If KeyDown(42) Then shift=1 Else shift=0
If KeyDown(56) Then alt=1 Else alt=0

If KeyHit(33) Then
    If showOnelayer=1 Then showOneLayer=0 Else showOneLayer=1
EndIf

If KeyHit(30) Then 
    If taniMenu=1 Then taniMenu=0 : artMode=1 Else taniMenu=1
EndIf

If KeyHit(48) Then    ;copy animation#/followers attributes to specific tiles
    FlushKeys()
    x=380: y=500
    Locate x,y
    n=Input("Copy ani.# "):y=y+15
    Locate x,y
    aa=Input("next ani.# "):y=y+15
    Locate x,y
    i=Input("from tile# "):y=y+15
    Locate x,y
    ii=Input("   to tile# "):y=y+15
    FlushKeys()

a=aa
  For k=i To ii
    taniseq(a)=taniseq(n)
    tAniFrames(a)=tAniFrames(n)
    taniBgSel(a)=bg
    tAniNSel(a)=k
    taniCurFrame(a)=taniCurFrame(n)

    For o=1 To taniFrames(a)
         If tAniFrames(a)>1 Then
      taniBg(a,o)=taniBg(n,o)
      taniN(a,o)=taniN(n,o)
     Else
      taniBg(a,o)=taniBg(n,o)
      taniN(a,o)=k
     EndIf
      tAniTime(a,o)=tAniTime(n,o)
    Next
;----
    n1=taniBgSel(a)
    n2=taniNSel(a)
    nn1=taniBgSel(n)
    nn2=taniNSel(n)
    tileFollowType(n1,n2)=tileFollowType(nn1,nn2)
    tileTarget(n1,n2)=tileTarget(nn1,nn2)
    
    If tileFollowType(n1,n2)=1 Then
        ttx = xplat(tileTarget(nn1,nn2))
        tty = yplat(tileTarget(nn1,nn2))
    EndIf
    If tileFollowType(n1,n2)=2 Then
        ttx=xbox(tileTarget(nn1,nn2))
        tty=ybox(tileTarget(nn1,nn2))
    EndIf
    If Not tileFollowType(n1,n2)=0 Then
     nu=( ttx+xScr) - (xTile(taniBgSel(a),taniNSel(a))+xScr)
      xTile2(n1,n2)= (nu-nu*2)
      nu=( tty+yScr) - (yTile(taniBgSel(a),taniNSel(a))+yScr)
      yTile2(n1,n2)= (nu-nu*2)
    EndIf
 ;---

    If tAniAmount < a Then tAniAmount=a
    a=a+1
  Next
    
EndIf


If taniMenu=1 Then   ;get user input for animation menu (If on), define buttons
    If taniFrames(curAni) > 6 Then
        mx(1)=5 : mw(1)=1020
    Else
        mx(1)=500 : mw(1)=700
    EndIf
    in=1
    If shift=1 Then shift=0 : in=5
    If alt=1 Then alt=0 : in=50
    
    my(1)=500 :mh(1)=250    ;Define Menu position
    ;----------
    xbut(1)=mx(1)+3:yBut(1)=my(1)+3:wbut(1)=70:hBut(1)=18   ;ANI# button
    xbut(2)=mx(1)+100:yBut(2)=my(1)+3:wbut(2)=110:hBut(2)=18   ;TILE# button
    xbut(3)=mx(1)+3:yBut(3)=my(1)+20:wbut(3)=80:hBut(3)=18   ;FRAMES# button
    xbut(50)=mx(1)+250:yBut(50)=my(1)+3:wbut(50)=200:hBut(50)=18   ;TILE FOLLOW?# button
    xbut(51)=mx(1)+250:yBut(51)=my(1)+23:wbut(51)=80:hBut(51)=18   ;X TILE FOLLOW?# button
    xbut(52)=mx(1)+350:yBut(52)=my(1)+23:wbut(52)=80:hBut(52)=18   ;Y TILE FOLLOW?# button
    x=0
    For i=4 To taniFrames(curAni)+4
        xbut(i)=mx(1)+5+x:yBut(i)=my(1)+50:wbut(i)=80:hBut(i)=80   ;FRAME BOX# button    
        x=x+wbut(i)+5
    Next
    x=0
    For i=20 To taniFrames(curAni)+20
        xbut(i)=mx(1)+5+x:yBut(i)=my(1)+135:wbut(i)=80:hBut(i)=20   ;FRAME TIME BOX# button    
        x=x+wbut(i)+5
    Next
    ;----------
    button(xbut(1),yBut(1),wBut(1),hBut(1))
    If butClicked=1 Then 
        If mouseClicked=1 Then mouseClicked=0 : curAni=curAni+in
        If mouseClicked2=1 Then 
            mouseClicked2=0
            If curAni>0 Then curAni=curAni-in Else curAni=50
        End If
    EndIf
    button(xbut(2),yBut(2),wBut(2),hBut(2))
    If butClicked=1 Then
        If mouseClicked=1 Then mouseClicked=0 : artMode=3
    EndIf
    button(xbut(3),yBut(3),wBut(3),hBut(3))
    If butClicked=1 Then
        If mouseClicked=1 Then mouseClicked=0 : taniFrames(curAni)=taniFrames(curAni)+in
        If mouseClicked2=1 Then mouseClicked2=0 : taniFrames(curAni)=taniFrames(curAni)-in
    EndIf
    button(xbut(50),yBut(50),wBut(50),hBut(50))
    If butClicked=1 Then
        If mouseClicked=1 Then
            mouseClicked=0
            tileFollowType(taniBgSel(curAni),taniNSel(curAni))=tileFollowType(taniBgSel(curAni),taniNSel(curAni))+in
            If tileFollowType(taniBgSel(curAni),taniNSel(curAni)) > 2 Then tileFollowType(taniBgSel(curAni),taniNSel(curAni))=0 
        EndIf
        If mouseClicked2=1 Then
            mouseClicked2=0
            tileFollowType(taniBgSel(curAni),taniNSel(curAni))=tileFollowType(taniBgSel(curAni),taniNSel(curAni))-in
            If tileFollowType(taniBgSel(curAni),taniNSel(curAni)) < 0 Then tileFollowType(taniBgSel(curAni),taniNSel(curAni))=2 
        EndIf
        If tileFollowType(taniBgSel(curAni),taniNSel(curAni))=1 Then artMode=5
        If tileFollowType(taniBgSel(curAni),taniNSel(curAni))=2 Then artMode=6
    EndIf
    b=51
    button(xbut(b),yBut(b),wBut(b),hBut(b))
    If butClicked=1 And mouseClicked=1 Then mouseClicked=0 : artMode=7
    
    b=52
    button(xbut(b),yBut(b),wBut(b),hBut(b))
    If butClicked=1 And mouseClicked=1 Then mouseClicked=0 : yTile2(taniBgSel(curAni),taniNSel(curAni))=yTile2(taniBgSel(curAni),taniNSel(curAni))+in
    If butClicked=1 And mouseClicked2=1 Then mouseClicked2=0:yTile2(taniBgSel(curAni),taniNSel(curAni))=yTile2(taniBgSel(curAni),taniNSel(curAni))-in
    
    For i=4 To taniFrames(curAni)+4
    button(xbut(i),yBut(i),wBut(i),hBut(i))
        If butClicked=1 Then
            If mouseClicked=1 Then mouseClicked=0 : artMode=4:taniCurFrame(curAni)=i-3
        EndIf
    Next
    For i=20 To taniFrames(curAni)+20
    button(xbut(i),yBut(i),wBut(i),hBut(i))
     If butClicked=1 Then
        If mouseClicked=1 Then mouseClicked=0 : taniTime(curAni,i-19)=taniTime(curAni,i-19)+in
        If mouseClicked2=1 Then mouseClicked2=0 : taniTime(curAni,i-19)=taniTime(curAni,i-19)-in
     EndIf
    Next
    
    If artMode <> 3 And artMode <> 4 And artMode <> 5 And artMode <> 6 And artMode <> 7 Then mouseClicked=0

EndIf

If KeyHit(24) And artmode=7 Then    ;select x/y offset tile Position from plat/box (auto select)
    n1=taniBgSel(curAni)
    n2=taniNSel(curAni)
    If tileFollowType(n1,n2)=1 Then 
        ttx = xplat(tileTarget(n1,n2))
        tty = yplat(tileTarget(n1,n2))
    EndIf
    If tileFollowType(n1,n2)=2 Then 
        ttx=xbox(tileTarget(n1,n2))
        tty=ybox(tileTarget(n1,n2))
    EndIf
    nu=( ttx+xScr) - (xTile(taniBgSel(curAni),taniNSel(curAni))+xScr)
    xTile2(n1,n2)= (nu-nu*2)
    nu=( tty+yScr) - (yTile(taniBgSel(curAni),taniNSel(curAni))+yScr)
    yTile2(n1,n2)= (nu-nu*2)
EndIf

If mouseClicked=1 And artMode=7 Then    ;select x/y offset tile Position from plat/box
    mouseClicked=0
    n1=taniBgSel(curAni)
    n2=taniNSel(curAni)
    If tileFollowType(n1,n2)=1 Then 
        ttx = xplat(tileTarget(n1,n2))
        tty = yplat(tileTarget(n1,n2))
    EndIf
    If tileFollowType(n1,n2)=2 Then 
        ttx=xbox(tileTarget(n1,n2))
        tty=ybox(tileTarget(n1,n2))
    EndIf
    nu=( ttx+xScr) - (MouseX())
    xTile2(n1,n2)= (nu-nu*2)
    nu=( tty+yScr) - (MouseY())
    yTile2(n1,n2)= (nu-nu*2)
EndIf

If artMode=6 And mouseClicked=1 Then    ;Select box to stick to tile
For n=1 To boxAmount
    If MouseX() > xbox(n)+xScr And MouseX() < xbox(n)+boxWidth(n)+xScr Then
        If MouseY() > ybox(n)+yScr And MouseY() < ybox(n)+boxHeight(n)+yScr Then
            tileTarget( taniBgSel(curAni), taniNsel(curAni) ) = n
            Goto gotele2
        EndIf        
    EndIf
Next
.gotele2
mouseClicked=0
EndIf

If artMode=5 And mouseClicked=1 Then    ;Select platform to stick to tile
For n=1 To platAmount
    pw=8 : ph=8
    If MouseX() > xplat(n)+xScr And MouseX() < xplat(n)+pw+xScr Then
        If MouseY() > yPlat(n)+yScr And MouseY() < yplat(n)+ph+yScr Then
            tileTarget( taniBgSel(curAni), taniNsel(curAni) ) = n
            Goto gotele
        EndIf        
    EndIf
Next

For n=1 To platAmount
    If platHeight(n) > 4 Then
        ph=platHeight(n)
    Else
        ph=5
    EndIf
    If MouseX() > xplat(n)+xScr And MouseX() < xplat(n)+platWidth(n)+xScr Then
        If MouseY() > yPlat(n)+yScr And MouseY() < yplat(n)+ph+yScr Then
            tileTarget( taniBgSel(curAni), taniNsel(curAni) ) = n
            Goto gotele
        EndIf
    EndIf
Next
.gotele
mouseClicked=0
EndIf

If artMode=3 And mouseClicked=1 Then    ;Select tile for this Animation#
b=bg
For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+20+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+20+yScr Then
            taniBgSel(curAni)=b
            taniNSel(curAni)=n
            tanicurFrame(curAni)=1
            taniBg(curAni,tanicurFrame(curAni))=b
            taniN(curAni,taniCurFrame(curAni))=n
            Goto gottani
        EndIf
    EndIf
Next

For b=0 To bgAmount
For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+20+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+20+yScr Then
            taniBgSel(curAni)=b
            taniNSel(curAni)=n
            tanicurFrame(curAni)=1
            taniBg(curAni,tanicurFrame(curAni))=b
            taniN(curAni,taniCurFrame(curAni))=n
            Goto gottani
        EndIf
    EndIf
Next
Next
For b=0 To bgAmount
For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+ImageWidth(tilePic(b,n))+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+ImageHeight(tilePic(b,n))+yScr Then
            taniBgSel(curAni)=b
            taniNSel(curAni)=n
            tanicurFrame(curAni)=1
            taniBg(curAni,tanicurFrame(curAni))=b
            taniN(curAni,taniCurFrame(curAni))=n
            Goto gottani
        EndIf
    EndIf
Next
Next
.gottani
mouseClicked=0
EndIf

If artMode=4 And mouseClicked=1 Then    ;Select current frame for tile for this Animation#
For b=0 To bgAmount
For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+20+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+20+yScr Then
            taniBg(curAni,tanicurFrame(curAni))=b
            taniN(curAni,taniCurFrame(curAni))=n
            Goto gotframe
        EndIf
    EndIf
Next
Next
For b=0 To bgAmount
For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+ImageWidth(tilePic(b,n))+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+ImageHeight(tilePic(b,n))+yScr Then
            taniBg(curAni,tanicurFrame(curAni))=b
            taniN(curAni,taniCurFrame(curAni))=n
            Goto gotframe
        EndIf
    EndIf
Next
Next
.gotframe
mouseClicked=0
EndIf


If KeyDown(45) And mouseClicked=1 Then    ;Select target tile to follow
For n=1 To tileAmount(bg)
    artMode=2
    If MouseX() > xTile(bg,n)+xScr And MouseX() < (xTile(bg,n)+8)+xScr Then
        If MouseY() > yTile(bg,n)+yScr And MouseY() < (yTile(bg,n)+8)+yScr Then
            tileTarget(bg,cn)=n
            Goto gotTileF
            End
        EndIf
    EndIf
    If MouseX() > xTile(bg,n)+xScr And MouseX() < xTile(bg,n)+ImageWidth(tilePic(bg,n))+xScr Then
        If MouseY() > yTile(bg,n)+yScr And MouseY() < yTile(bg,n)+ImageHeight(tilePic(bg,n))+yScr Then
            tileTarget(bg,cn)=n
            ;Exit
        EndIf
    EndIf
Next
.gottileF
mouseClicked=0
EndIf

If KeyDown(52) Then  ;Simulate moving tiles/plataforms

For n=1 To platamount
p=platCurPoint(n)

xOldplat(n)=xplat(n)

;If (platUseTrigger(n) And eventN(platEventN(n))=1) Then Goto skipPlatMove

If xPlat(n) < xPlatPoint(n,p) And xplatDest(n)=0 Then
    xplat(n)=xPlat(n)+platXSpeed(n)
    platXDir(n)=2
    If xPlat(n) => xPlatPoint(n,p) Then xplatDest(n)=1
Else
    If xPlat(n) > xPlatPoint(n,p) And xplatDest(n)=0 Then
        xplat(n)=xPlat(n)-platXSpeed(n)
        platXDir(n)=4
        If xPlat(n) <= xPlatPoint(n,p) Then xplatDest(n)=1
    EndIf
EndIf
If xPlat(n)=xPlatPoint(n,p) Then xplatDEst(n)=1
    
If yPlat(n) < yPlatPoint(n,p) And yplatDest(n)=0 Then
    yplat(n)=yPlat(n)+platYSpeed(n)
    plaTYDir(n)=3
    If yPlat(n) => yPlatPoint(n,p) Then yplatDest(n)=1
Else
    If yPlat(n) > yPlatPoint(n,p) And yplatDest(n)=0 Then
        yplat(n)=yPlat(n)-platYSpeed(n)
        platyDir(n)=1
        If yPlat(n) =< yPlatPoint(n,p) Then yplatDest(n)=1
    EndIf
EndIf
If yPlat(n)=yPlatPoint(n,p) Then yplatDEst(n)=1

If xplatDest(n)=1 And yplatDest(n)=1 Then
    If platCurPoint(n) = platFinalDest(n) Then
    Else
        xplatDest(n)=0:yPlatDest(n)=0
        platCurPoint(n)=platCurPoint(n)+1
    EndIf
    If platCurPoint(n) > PlatPointsAmount(n) Then platCurPoint(n)=1
EndIf

.skipPlatMove
Next

For n=1 To boxAmount    ;simulate boxes

p=boxCurPoint(n)
boxXDir(n)=0:boxYDir(n)=0
xOldbox(n)=xbox(n)

;If (boxUseTrigger(n) And eventN(boxEventN(n))=1) Then Goto skipBoxMove

If xbox(n) < xboxPoint(n,p) And xboxDest(n)=0 Then
    xbox(n)=xbox(n)+boxXSpeed(n)
    boxXDir(n)=2
    If xbox(n) => xboxPoint(n,p) Then xboxDest(n)=1
Else
    If xbox(n) > xboxPoint(n,p) And xboxDest(n)=0 Then
        xbox(n)=xbox(n)-boxXSpeed(n)
        boxXDir(n)=4
        If xbox(n) <= xboxPoint(n,p) Then xboxDest(n)=1
    EndIf
EndIf
If xbox(n)=xboxPoint(n,p) Then xboxDEst(n)=1
    
If ybox(n) < yboxPoint(n,p) And yboxDest(n)=0 Then
    ybox(n)=ybox(n)+boxYSpeed(n)
    boxYDir(n)=3
    If ybox(n) => yboxPoint(n,p) Then yboxDest(n)=1
Else
    If ybox(n) > yboxPoint(n,p) And yboxDest(n)=0 Then
        ybox(n)=ybox(n)-boxYSpeed(n)
        boxyDir(n)=1
        If ybox(n) =< yboxPoint(n,p) Then yboxDest(n)=1
    EndIf
EndIf
If ybox(n)=yboxPoint(n,p) Then yboxDEst(n)=1

.skipBoxMove

If xboxDest(n)=1 And yboxDest(n)=1 Then
    If boxCurPoint(n) = boxFinalDest(n) Then
        If boxBreak(n)=1 Then
            ;makeChunk(0,xBox(n)+(boxWidth(n)/2),yBox(n)+boxHeight(n),0,boxChunkType(n))
            ;If gameSound Then PlaySound boxHitSound(n)
            ;boxon(n)=0:drawBox(n)=0
        EndIf
        
        If boxReachedDest(n)=0 And boxEventN2(n) > 0 Then
            boxReachedDest(n)=1
            If EventN(boxEventN2(n))=1 Then eventN(boxEventN2(n))=0 Else eventN(boxEventN2(n))=1
        EndIf
    Else
        boxCurPoint(n)=boxCurPoint(n)+1
        xboxDest(n)=0:yboxDest(n)=0
        If boxCurPoint(n) > boxPointsAmount(n) Then boxCurPoint(n)=1
    EndIf
EndIf

Next

simulate=1

For i=0 To 4 Step 4
For n=1 To tileAmount(i)
    
    If xTile(i,n) => tileXEnd1(i,n) And xTile(i,n) =< tileXEnd2(i,n) Then
        If tileXRand1(i,n) <> 0 Then
            xTile(i,n)=Rand((tileXstart(i,n)+tileXrand1(i,n)), (tileXstart(i,n)+tileXrand2(i,n)))
        Else
            If tileFollow(i,n)=1 Then
                If tileXspeed(i,n) > 0 Then
                    xTile(i,n)=xTile(i,tileTarget(i,n))-ImageWidth(tilePic(i,tileTarget(i,n)))
                Else
                    If tilePic(i,tileTarget(i,n)) =0 Then
                            RuntimeError "tile b="+i+" n="+tileTarget(i,n)
                    EndIf
                    xTile(i,n)=xTile(i,tileTarget(i,n))+ImageWidth(tilePic(i,tileTarget(i,n)))
                EndIf
            Else
                xTile(i,n) = tileXStart(i,n)
            EndIf
        EndIf
    EndIf
    
    If yTile(i,n) => tileYEnd1(i,n) And yTile(i,n) =< tileYEnd2(i,n) Then
        If tileYRand1(i,n) <> 0 Then
            yTile(i,n)=Rand(tileystart(i,n)+tileyrand1(i,n), tileystart(i,n)+tileyrand2(i,n))
        Else
            If tileFollow(i,n)=1 Then
                If tileYspeed(i,n) > 0 Then
                    yTile(i,n)=yTile(i,tileTarget(i,n))-ImageHeight(tilePic(i,tileTarget(i,n)))
                Else
                    yTile(i,n)=yTile(i,tileTarget(i,n))+ImageHeight(tilePic(i,tileTarget(i,n)))
                EndIf
            Else
                yTile(i,n) = tileyStart(i,n)
            EndIf
        EndIf
    EndIf
    xTile(i,n)=xTile(i,n) + tileXspeed(i,n)
     yTile(i,n)=yTile(i,n) + tileYspeed(i,n)
Next
Next

For n=1 To taniAmount    ;tile follows target plat/box
    If tileFollowType(taniBgSel(n),taniNSel(n))=1 Then
        xTile( taniBgSel(n),taniNSel(n) )=xplat(tileTarget( taniBgSel(n),taniNSel(n) ))+xTile2( taniBgSel(n),taniNSel(n) )
        yTile( taniBgSel(n),taniNSel(n) )=yplat(tileTarget( taniBgSel(n),taniNSel(n) ))+yTile2( taniBgSel(n),taniNSel(n) )
    EndIf
    If tileFollowType(taniBgSel(n),taniNSel(n))=2 Then
        xTile( taniBgSel(n),taniNSel(n) )=xbox(tileTarget( taniBgSel(n),taniNSel(n) ))+xTile2( taniBgSel(n),taniNSel(n) )
        yTile( taniBgSel(n),taniNSel(n) )=ybox(tileTarget( taniBgSel(n),taniNSel(n) ))+yTile2( taniBgSel(n),taniNSel(n) )
    EndIf
    
    taniseq(n)=tAniseq(n)+1
    If tAniSeq(n) > tAniTime(n,tAniCurFrame(n)) Then
        tAniSeq(n)=0 : tAniCurFrame(n)=tAniCurFrame(n)+1
        If tAniCurFrame(n) > tAniFrames(n) Then tAniCurFrame(n)=1
        tilePic( taniBgSel(n),taniNSel(n) ) = tilePic( taniBg(n,taniCurFrame(n)) ,tAniN(n,taniCurFrame(n)) )    
    EndIf
Next

EndIf

If shift Then    ;Align tile with other tiles by pressing shift
d=20    ;height/width distance from tile x/y 
If alt Then d=5
b=bg
;For b=0 To bgAmount
  For n=1 To tileAmount(b)    ;stick to upper right corner
    If xTile(b,n)+ImageWidth(tilePic(b,n)) => xtile(bg,cn)-d And xTile(b,n)+ImageWidth(tilePic(b,n)) =< xtile(bg,cn)+d Then
        If yTile(b,n) => ytile(bg,cn)-d And yTile(b,n) =< ytile(bg,cn)+d Then
            xtile(bg,cn)=xtile(b,n)+ImageWidth(tilePic(b,n))
            ytile(bg,cn)=ytile(b,n)
            MoveMouse xtile(bg,cn)+xscr,ytile(bg,cn)+yscr
            Goto pickedit2
        EndIf
    EndIf
  Next
;Next
;For b=0 To bgAmount
  For n=1 To tileAmount(b)    ;stick to upper left corner
    If xTile(b,n) => xtile(bg,cn)+ImageWidth(tilePic(bg,cn)) And xTile(b,n) =< xtile(bg,cn)+ ImageWidth(tilePic(bg,cn))+d Then
        If yTile(b,n) => ytile(bg,cn)-d And yTile(b,n) =< ytile(bg,cn)+d Then
            xtile(bg,cn)=xtile(b,n)-ImageWidth(tilePic(bg,cn))
            ytile(bg,cn)=ytile(b,n)
            MoveMouse xtile(bg,cn)+xscr,ytile(bg,cn)+yscr
            Goto pickedit2
        EndIf
    EndIf
  Next
;Next

;For b=0 To bgAmount
  For n=1 To tileAmount(b)    ;stick to lower left corner
    If xTile(b,n) => xtile(bg,cn)-d And xTile(b,n) =< xtile(bg,cn)+d Then
        If yTile(b,n)+ImageHeight(tilePic(b,n)) => ytile(bg,cn)-d And yTile(b,n)+ImageHeight(tilePic(b,n)) =< ytile(bg,cn)+d Then
            xtile(bg,cn)=xtile(b,n)
            ytile(bg,cn)=ytile(b,n)+ImageHeight(tilePic(b,n))
            MoveMouse xtile(bg,cn)+xscr,ytile(bg,cn)+yscr
            Goto pickedit2
        EndIf
    EndIf
  Next
;Next
  For n=1 To tileAmount(b)    ;stick from above
    If xTile(b,n) => xtile(bg,cn)-d And xTile(b,n) =< xtile(bg,cn)+d Then
        If yTile(b,n) => yTile(bg,cn)+ImageHeight(tilePic(bg,cn)) And yTile(b,n) =< yTile(bg,cn)+ImageHeight(tilePic(bg,cn))+d Then
            xtile(bg,cn)=xtile(b,n)
            ytile(bg,cn)=ytile(b,n)-ImageHeight(tilePic(bg,cn))
            MoveMouse xtile(bg,cn)+xscr,ytile(bg,cn)+yscr
            Goto pickedit2
        EndIf
    EndIf
  Next
;Next

EndIf
.pickedit2

If KeyDown(29) And mouseClicked Then    ;Select Tile by holding Ctrl + click
b=bg
  For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+20+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+20+yScr Then
            bg=b
            cn=n
            Goto pickedit1
        EndIf
    EndIf
  Next

For b=0 To bgAmount
  For n=1 To tileAmount(b)
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+20+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+20+yScr Then
            bg=b
            cn=n
            Goto pickedit1
        EndIf
    EndIf
  Next
Next
For b=0 To bgAmount
  For n=1 To tileAmount(b)
    
    If MouseX() > xTile(b,n)+xScr And MouseX() < xTile(b,n)+ImageWidth(tilePic(b,n))+xScr Then
        If MouseY() > yTile(b,n)+yScr And MouseY() < yTile(b,n)+ImageHeight(tilePic(b,n))+yScr Then

            bg=b
            cn=n
        Goto pickedit1
        EndIf
    EndIf
  Next
Next
.pickedit1
mouseClicked=0
EndIf

If KeyHit(21) Then              ;position x amount of tiles at once (rows x columns)
 FlushKeys()
 Color 255,255,255
 Locate 500,500
 col=Input("cols ")
 Locate 500,518
 row=Input("rows ")
 Locate 500,536
 dir=0
 dir=Input("up=0 down=1? ")
 xtile(bg,cn)=(MouseX()-xScr) : ytile(bg,cn)=MouseY()- yScr
 tileFixed(bg,cn)=1
 prevPic=tilePic(bg,cn)
 x=(MouseX()-xScr)
 y=(MouseY()-yScr)
 ox=x
 
For c=1 To row
  For r=1 To col
   xtile(bg,cn)=x : ytile(bg,cn)=y
   tileFixed(bg,cn)=1
   x=x+ImageWidth(tilePic(bg,cn))

   cn=cn+1
   tilePic(bg,cn)=prevPic
   tileNumber(bg,cn)=tileNumber(bg,cn-1)
   tileSetNumber(bg,cn)=tileSetNumber(bg,cn-1)
   If cn > tileAmount(bg) Then tileAmount(bg)=tileAmount(bg)+1
  Next

If dir=0 Then
   y=y-ImageHeight(tilePic(bg,cn))
Else
   y=y+ImageHeight(tilePic(bg,cn))
EndIf
x=ox
Next
mouseClicked=0
FlushKeys()
EndIf

If mouseClicked=1 And selectTile=0 Then    ;Position tile by click
    mouseClicked=0
    If click1=0 Then
        click1=0
        xtile(bg,cn)=(MouseX()-xScr) : ytile(bg,cn)=MouseY()- yScr
        tileFixed(bg,cn)=1
        prevPic=tilePic(bg,cn)
        If cn+1 > tileAmount(bg) Then cn=cn+1:tileAmount(bg)=tileAmount(bg)+1
        If cn > tileAmount(bg) Then cn=tileAmount(bg)
        If tilePic(bg,cn)=0 Then
            tilePic(bg,cn)=tilePic(bg,cn-1):tileNumber(bg,cn)=tileNumber(bg,cn-1)
            tileSetNumber(bg,cn)=tileSetNumber(bg,cn-1)
        EndIf
        Goto clickDone3
    EndIf
    If click1=1 Then click1=0:tileFixed(bg,cn)=0

EndIf
.clickDone3

If KeyHit(23) Then
    If showTileNumber=1 Then showTileNumber=0 Else showTileNumber=1
EndIf


For i=2 To 11
    If KeyHit(i) Then artMode= (i-1)
Next

If KeyHit(44) Then 
    If tileFollow(bg,cn)=1 Then tileFollow(bg,cn)=0 Else tileFollow(bg,cn)=1
EndIf

;If artMode=9 Then End;test

If KeyHit(13) Then
Select artMode
    Case 1
    bg=bg+1
    If bg > 5 Then bg=0
    Case 5:taniCurFrame(curAni)=taniCurFrame(curAni)+1
    Case 6:taniTime(curAni,taniCurFrame(curAni))=taniTime(curAni,taniCurFrame(curAni))+2
    Case 7:curAni=curAni+1
    Case 8:taniAmount=taniAmount+1
    Case 9:If bg=0 Or bg=4 Then xTileScrSpeed(bg,cn)=xTileScrSpeed(bg,cn)+.5
    Case 10:If bg=0 Or bg=4 Then yTileScrSpeed(bg,cn)=yTileScrSpeed(bg,cn)+.5
            
End Select
EndIf

If KeyHit(12) Then
Select artMode
    Case 1
    bg=bg-1
    If bg < 0 Then bg=bgAmount
    Case 5:taniCurFrame(curAni)=taniCurFrame(curAni)-1
    Case 6:taniTime(curAni,taniCurFrame(curAni))=taniTime(curAni,taniCurFrame(curAni))-2
    Case 7:curAni=curAni-1
    Case 8:taniAmount=taniAmount-1
    Case 9:If bg=0 Or bg=4 Then xTileScrSpeed(bg,cn)=xTileScrSpeed(bg,cn)-.5
    Case 10:If bg=0 Or bg=4 Then yTileScrSpeed(bg,cn)=yTileScrSpeed(bg,cn)-.5
        
End Select
EndIf

If KeyHit(51) Then    ;Move selected tile to back
temp1=xTile(bg,cn)
temp2=yTile(bg,cn)    
temp3=TilePic(bg,cn)
temp4=tileNumber(bg,cn)    
temp5=tileSetNumber(bg,cn)
temp6=tileFixed(bg,cn)
temp7=tileXend1(bg,cn)
temp8=tileXend2(bg,cn)
temp9=tileXrand1(bg,cn)
temp10=tileXrand2(bg,cn)
temp11=tileXspeed(bg,cn)
temp12=tileYend1(bg,cn)
temp13=tileYend2(bg,cn)
temp14=tileYrand1(bg,cn)
temp15=tileYrand2(bg,cn)
temp16=tileYspeed(bg,cn)
temp17=tileXstart(bg,cn)
temp18=tileYstart(bg,cn)
temp19=tileFollow(bg,cn)
temp20=tileTarget(bg,cn)
temp21=xTileScrSpeed(bg,cn)
temp22=yTileScrSpeed(bg,cn)
temp23=tileFollowType(bg,cn)
temp24=xTile2(bg,cn)
temp25=yTile2(bg,cn)

i=cn
Repeat
      xTile(bg,i)=xTile(bg,i-1)
    yTile(bg,i)=yTile(bg,i-1)
    tilePic(bg,i)=tilePic(bg,i-1)
    tileNumber(bg,i)=tileNumber(bg,i-1)
    tileSetNumber(bg,i)=tileSetNumber(bg,i-1)
    tileFixed(bg,i)=tileFixed(bg,i-1)
    tileXend1(bg,i)=tileXend1(bg,i-1)
    tileXend2(bg,i)=tileXend2(bg,i-1)
    tileXrand1(bg,i)=tileXrand1(bg,i-1)
    tileXrand2(bg,i)=tileXrand2(bg,i-1)
    tileXspeed(bg,i)=tileXspeed(bg,i-1)
    tileYend1(bg,i)=tileYend1(bg,i-1)
    tileYend2(bg,i)=tileYend2(bg,i-1)
    tileYrand1(bg,i)=tileYrand1(bg,i-1)
    tileYrand2(bg,i)=tileYrand2(bg,i-1)
    tileYspeed(bg,i)=tileYspeed(bg,i-1)
    tileXstart(bg,i)=tileXstart(bg,i-1)
    tileYstart(bg,i)=tileYstart(bg,i-1)
    tileFollow(bg,i)=tileFollow(bg,i-1)
    tileTarget(bg,i)=tileTarget(bg,i-1)+1
    ;tileTarget(bg,i)=tileTarget(bg,i)+1
    xTileScrSpeed(bg,i)=xTileScrSpeed(bg,i-1)
    yTileScrSpeed(bg,i)=yTileScrSpeed(bg,i-1)
    tileFollowType(bg,i)=tileFollowType(bg,i-1)
    xTile2(bg,i)=xTile2(bg,i-1)
    yTile2(bg,i)=yTile2(bg,i-1)
    i=i-1
Until i=1
xTile(bg,1)=temp1
yTile(bg,1)=temp2
TilePic(bg,1)=temp3
tileNumber(bg,1)=temp4
tileSetNumber(bg,1)=temp5
tileFixed(bg,1)=temp6
tileXend1(bg,1)=temp7
tileXend2(bg,1)=temp8
tileXrand1(bg,1)=temp9
tileXrand2(bg,1)=temp10
tileXspeed(bg,1)=temp11
tileYend1(bg,1)=temp12
tileYend2(bg,1)=temp13
tileYrand1(bg,1)=temp14
tileYrand2(bg,1)=temp15
tileYspeed(bg,1)=temp16
tileXstart(bg,1)=temp17
tileYstart(bg,1)=temp18
tileFollow(bg,1)=temp19
tileTarget(bg,1)=temp20 + 1
xTileScrSpeed(bg,1)=temp21
yTileScrSpeed(bg,1)=temp22
tileFollowType(bg,1)=temp23
xTile2(bg,1)=temp24
yTile2(bg,1)=temp25

For k=1 To taniAmount
  If taniBgSel(k)=bg And tAniNSel(k) < cn Then
    tAniNSel(k)=tAniNSel(k)+1
  EndIf
  
  For o=1 To taniFrames(k)
     If taniBg(k,o)=bg And tAniN(k,o) < cn Then
     taniN(k,o)=taniN(k,o)+1
    EndIf
  Next
Next


EndIf

If KeyHit(211) Then        ;Delete selected tile
    For i=cn To tileAmount(bg)
        xTile(bg,i)=xTile(bg,i+1)
        yTile(bg,i)=yTile(bg,i+1)
        tilePic(bg,i)=tilePic(bg,i+1)
        tileNumber(bg,i)=tileNumber(bg,i+1)
        tileSetNumber(bg,i)=tileSetNumber(bg,i+1)
        tileFixed(bg,i)=tileFixed(bg,i+1)
        tileXend1(bg,i)=tileXend1(bg,i+1)
        tileXend2(bg,i)=tileXend2(bg,i+1)
        tileXrand1(bg,i)=tileXrand1(bg,i+1)
        tileXrand2(bg,i)=tileXrand2(bg,i+1)
        tileXspeed(bg,i)=tileXspeed(bg,i+1)
        tileYend1(bg,i)=tileYend1(bg,i+1)
        tileYend2(bg,i)=tileYend2(bg,i+1)
        tileYrand1(bg,i)=tileYrand1(bg,i+1)
        tileYrand2(bg,i)=tileYrand2(bg,i+1)
        tileYspeed(bg,i)=tileYspeed(bg,i+1)
        tileXstart(bg,i)=tileXstart(bg,i+1)
        tileYstart(bg,i)=tileYstart(bg,i+1)
        tileFollow(bg,i)=tileFollow(bg,i+1)
        tileTarget(bg,i)=tileTarget(bg,i+1)
        xTileScrSpeed(bg,i)=xTileScrSpeed(bg,i+1)
        yTileScrSpeed(bg,i)=yTileScrSpeed(bg,i+1)
        
        tileFollowType(bg,i)=tileFollowType(bg,i+1)
        xTile2(bg,i)=xTile2(bg,i+1)
        yTile2(bg,i)=yTile2(bg,i+1)
        
    Next
    tilePic(bg,tileAmount(bg))=0
    tileAmount(bg)=tileAmount(bg)-1

    For k=1 To taniAmount
      If taniBgSel(k)=bg And tAniNSel(k) > cn Then
        tAniNSel(k)=tAniNSel(k)-1
      EndIf
      
      For o=1 To taniFrames(k)
         If taniBg(k,o)=bg And tAniN(k,o) > cn Then
         taniN(k,o)=taniN(k,o)-1
        EndIf
      Next
    Next
    
EndIf


If KeyHit(37) Then    ;change places
FlushKeys()
Locate 500,500
n=Input("target #1: ")
Locate 500,515
nn=Input("target #2: ")
FlushKeys()

temps1#=xTile(bg,n)
temps2#=yTile(bg,n)    
;temps3=TilePic(bg,n)
temps4=tileNumber(bg,n)    
temps5=tileSetNumber(bg,n)
temps6=tileFixed(bg,n)
temps7#=tileXend1(bg,n)
temps8#=tileXend2(bg,n)
temps9#=tileXrand1(bg,n)
temps10#=tileXrand2(bg,n)
temps11#=tileXspeed(bg,n)
temps12#=tileYend1(bg,n)
temps13#=tileYend2(bg,n)
temps14#=tileYrand1(bg,n)
temps15#=tileYrand2(bg,n)
temps16#=tileYspeed(bg,n)
temps17#=tileXstart(bg,n)
temps18#=tileYstart(bg,n)
temps19=tileFollow(bg,n)
temps20=tileTarget(bg,n)
temps21#=xTileScrSpeed(bg,n)
temps22#=yTileScrSpeed(bg,n)

xTile(bg,n)=xTile(bg,nn)
yTile(bg,n)=yTile(bg,nn)
;tilePic(bg,n)=tilePic(bg,nn)
tileNumber(bg,n)=tileNumber(bg,nn)
tileSetNumber(bg,nn)=tileSetNumber(bg,nn)
tileFixed(bg,n)=tileFixed(bg,nn)
tileXend1(bg,n)=tileXend1(bg,nn)
tileXend2(bg,n)=tileXend2(bg,nn)
tileXrand1(bg,n)=tileXrand1(bg,nn)
tileXrand2(bg,n)=tileXrand2(bg,nn)
tileXspeed(bg,n)=tileXspeed(bg,nn)
tileYend1(bg,n)=tileYend1(bg,nn)
tileYend2(bg,n)=tileYend2(bg,nn)
tileYrand1(bg,n)=tileYrand1(bg,nn)
tileYrand2(bg,n)=tileYrand2(bg,nn)
tileYspeed(bg,n)=tileYspeed(bg,nn)
tileXstart(bg,n)=tileXstart(bg,nn)
tileYstart(bg,n)=tileYstart(bg,nn)
tileFollow(bg,n)=tileFollow(bg,nn)
tileTarget(bg,n)=tileTarget(bg,nn)
xTileScrSpeed(bg,n)=xTileScrSpeed(bg,nn)
yTileScrSpeed(bg,n)=yTileScrSpeed(bg,nn)

xTile(bg,nn)=temps1
yTile(bg,nn)=temps2
;TilePic(bg,nn)=temps3
tileNumber(bg,nn)=temps4    
tileSetNumber(bg,nn)=temps5
tileFixed(bg,nn)=temps6
tileXend1(bg,nn)=temps7
tileXend2(bg,nn)=temps8
tileXrand1(bg,nn)=temps9
tileXrand2(bg,nn)=temps10
tileXspeed(bg,nn)=temps11
tileYend1(bg,nn)=temps12
tileYend2(bg,nn)=temps13
tileYrand1(bg,nn)=temps14
tileYrand2(bg,nn)=temps15
tileYspeed(bg,nn)=temps16
tileXstart(bg,nn)=temps17
tileYstart(bg,nn)=temps18
tileFollow(bg,nn)=temps19
tileTarget(bg,nn)=temps20
xTileScrSpeed(bg,nn)=temps21
yTileScrSpeed(bg,nn)=temps22

EndIf

If (mouseWheel = 1 Or KeyHit(200)) And selectTile=1 Then    ;show previous set of tiles
    tileSet=tileSet-1
    If tileSet < 1 Then tileSet=1
    loadTileset
    FlushMouse()
EndIf
If (mouseWheel = -1 Or KeyHit(208)) And selectTile=1 Then    ;show next set of tiles
    tileSet=tileSet+1
    If tileSet > maxTileSet Then tileSet=maxTileSet
    loadTileset
    FlushMouse()
EndIf



If KeyHit(205) Then     ;next tile
    cn=cn+1
    If cn > tileAmount(bg) Then cn=1
EndIf
If KeyHit(203) Then     ;previous tile
    cn=cn-1
    If cn < 1 Then cn=tileAmount(bg)
EndIf

If KeyHit(209) Then        ;decrease tile amount
    tileFixed(bg,tileAmount(bg))=0
    tileAmount(bg)=tileAmount(bg)-1
    If tileAmount(bg) < 0 Then tileAmount(bg)=maxTileBG
EndIf
If KeyHit(201) Then        ;increase tile amount
    tileAmount(bg)=tileAmount(bg)+1
    cn=tileAmount(bg)
    If tileAmount(bg) > maxTileBG Then tileAmount(bg)=0
EndIf

If SelectTile=1 Then    ;select tile from tileset
  If MouseClicked=1 Then    
    For i = 1 To tAmount
        If MouseX() => xTnail(i) And MouseX() =< xTnail(i)+200 Then
            If MouseY() => yTnail(i) And MouseY() =< yTnail(i) + tHeight(i) Then
                tilePic(bg,cn) = tPic(i)
                tileNumber(bg,cn)=tNumber(i)
                tileSetNumber(bg,cn)=ttileSet(i)
                SelectTile=0
                click1=0
            EndIf
        EndIf
    Next
  EndIf
EndIf

If KeyHit(20) Then    ;toggle display for selecting tiles
    If selectTile=1 Then selectTile=0 Else SelectTile=1
EndIf

If KeyHit(18) Then
    If bg=1 Or bg=2 Or bg=3 Or bg=5 Then msg=1:msgseq=0:msgTxt$="WRONG `BG` DEPTH!"
    FlushKeys()
    x=500: y=500
    Locate x,y
    tileXstart(bg,cn)=Input("tileXstart("+tileXstart(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileYstart(bg,cn)=Input("tileYstart("+tileYstart(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileXend1(bg,cn)=Input("tileXend1("+tileXend1(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileXend2(bg,cn)=Input("tileXend2("+tileXend2(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileYend1(bg,cn)=Input("tileYend1("+tileYend1(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileYend2(bg,cn)=Input("tileYend2("+tileYend2(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileXspeed(bg,cn)=Input("tileXspeed("+tileXspeed(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileYspeed(bg,cn)=Input("tileYspeed("+tileYspeed(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileXrand1(bg,cn)=Input("tileXrand1("+tileXrand1(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileXrand2(bg,cn)=Input("tileXrand2("+tileXrand2(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileYrand1(bg,cn)=Input("tileYrand1("+tileYrand1(bg,cn)+ ") "):y=y+15
    Locate x,y
    tileYrand2(bg,cn)=Input("tileYrand2("+tileYrand2(bg,cn)+ ") "):y=y+15
    FlushKeys()
EndIf

If KeyHit(16) Then
    FlushKeys()
    x=500: y=500
    Locate x,y
    colorR=Input("R bg("+colorR+ ") "):y=y+15
    Locate x,y
    colorG=Input("G bg("+colorG+ ") "):y=y+15
    Locate x,y
    colorB=Input("B bg("+colorB+ ") "):y=y+15
    FlushKeys()
EndIf
    
If KeyHit(46) Then
    FlushKeys()
    x=500: y=500
    Locate x,y
    nn=Input("Copy From# "):y=y+15
    Locate x,y
    ii=Input("      from# "):y=y+15
    Locate x,y
    i=Input("        to# "):y=y+15
    FlushKeys()
    b=bg
    For n=ii To i
    TileXStart(b,n)=TileXStart(b,nn)
    tileXEnd1(b,n)=tileXEnd1(b,nn)
    tileXend2(b,n)=tileXend2(b,nn)
    tileXrand1(b,n)=tileXrand1(b,nn)
    tileXrand2(b,n)=tileXrand2(b,nn)
    tileXspeed(b,n)=tileXspeed(b,nn)
    xTileScrSpeed(b,n)=xTileScrSpeed(b,nn)
    
    TileYStart(b,n)=TileYStart(b,nn)
    tileYEnd1(b,n)=tileYEnd1(b,nn)
    tileYend2(b,n)=tileYend2(b,nn)
    tileYrand1(b,n)=tileYrand1(b,nn)
    tileYrand2(b,n)=tileYrand2(b,nn)
    tileYspeed(b,n)=tileYspeed(b,nn)
    yTileScrSpeed(b,n)=yTileScrSpeed(b,nn)
    
    tileFollow(b,n)=tileFollow(b,nn)
    Next
EndIf

End Function


;-------RENDERING (AI areas)------
Function renderAiArea()

If forceAiAreaDraw Then
    Color 255,43,234
    For n=1 To AreaAmount
        Rect saX(n)+xScr,saY(n)+ yScr,saW(n),saH(n),0
    Next
    Color 250,250,45
    For n=1 To DareaAmount
        Rect daX(n)+xScr,daY(n)+ yScr,daW(n),daH(n),0
    Next
    Goto endFunc1
EndIf

Color 155,183,234
If click1=0 Then Text 2,490,"Click for starting X, Y"
If click1=1 Then Text 2,490,"Click for ending X, Y"
If aiAreaMode=1 Then Text 2,490,"Click platform to follow"
If aiAreaMode=2 Then Text 2,490,"Click x,y position from platform"

Select areaType
Case 1
    Color 255,255,255
    n=510
    Text 2,n, "Press BACKSPACE to change to jumping mode": n=n+15 
    Text 2,n, "Number: " + cn + " AreaType: " + "Normal": n=n+15 
    If fleeDir(cn)=2 Then drawDir$=">" Else drawDir$="<"
    Text 2,n, "1) Flee Direction : " + drawDir$  :n=n+15
    Text 2,n, "2) Danger  : " + val(dangerArea(cn)):n=n+15
    Text 2,n, "3) Edges   : " + edges(cn)   :n=n+15
    Text 2,n, "4) Moved by platform: " + samovedBy(cn):n=n+15
    Text 2,n, "AreaAmount : " + areaAmount:n=n+15
    Text 2,n, "x: " + saX(cn) + " y: " + saY(cn):n=n+15
    Text 2,n, "w: " + saW(cn) + " h: " + saH(cn):n=n+15

Case 2    
    Color 255,255,255
    n=510
    Text 2,n, "Press BACKSPACE to change to normal mode": n=n+15 
    Text 2,n, "Number: " + cn + " AreaType: " + "Jumping": n=n+15 
    If dfleeDir(cn)=2 Then drawDir=">" Else drawDir="<"
    Text 2,n, "1) Flee Direction : " + drawDir  :n=n+15
    Text 2,n, "2) Danger  : " + val(daType(cn)):n=n+15
    Text 2,n, "3) Target Height : " + daTargetH(cn)   :n=n+15
    Text 2,n, "4) Moved By platform: " + damovedBy(cn):n=n+15
    Text 2,n, "J.AreaAmount: " + dareaAmount:n=n+15
    Text 2,n, "x: " + daX(cn) + " y: " + daY(cn):n=n+15
    Text 2,n, "w: " + daW(cn) + " h: " + daH(cn):n=n+15

End Select

If click1=1 Then
Select areaType
Case 1
    Color 128,128,128
    Rect saX(cn)+xScr,saY(cn)+ yScr,saW(cn)+(MouseX()-saX(cn))-xScr,saH(cn)+ (MouseY()-saY(cn))- yScr ,0
Case 2
    Color 128,128,128
    Rect daX(cn)+xScr,daY(cn)+ yScr,daW(cn)+(MouseX()-daX(cn))-xScr,daH(cn)+ (MouseY()-daY(cn)- yScr) ,0

End Select
EndIf

For n=1 To areaAmount
    If saDrawn(n)=1 Then
        Select dangerArea(n)
        Case 0 
            Color 0,255,0
            Rect saX(n)+xScr+18,saY(n)+3+ yScr,10,10,1
            Color 0,0,255
            If fleeDir(n)=2 Then Text saX(n)+xScr+18,saY(n)+1+ yScr, ">"
            If fleeDir(n)=4 Then Text saX(n)+xScr+18,saY(n)+1+ yScr, "<"
        Case 1
            Color 255,0,0
            Rect saX(n)+xScr+18,saY(n)+4+ yScr,10,10,1
            Color 0,0,255
            If fleeDir(n)=2 Then Text saX(n)+xScr+19,saY(n)+1+ yScr, ">"
            If fleeDir(n)=4 Then Text saX(n)+xScr+19,saY(n)+1+ yScr, "<"
            
        End Select
        Color 255,43,234
        Rect saX(n)+xScr,saY(n)+ yScr,saW(n),saH(n),0
        Text saX(n)+2+xScr,saY(n)+2+ yScr, n
        If cn = n And areaType=1 Then
            Color Rand(150,255),Rand(150,255),Rand(150,255): Rect saX(n)+xScr,saY(n)+ yScr,saW(n),saH(n),0
        EndIf
    EndIf
Next

For n=1 To dareaAmount
    If daDrawn(n)=1 Then
        Select daType(n)
        Case 0 
            Color 0,255,0
            Rect daX(n)+xScr+18,daY(n)+3+ yScr,10,10,1
            Rect daX(n)+xScr+21,daY(n)-daTargetH(n)+ yScr,1,daTargetH(n),0
            Color 0,0,255
            If dfleeDir(n)=2 Then Text daX(n)+xScr+18,daY(n)+1+ yScr, ">"
            If dfleeDir(n)=4 Then Text daX(n)+xScr+18,daY(n)+1+ yScr, "<"
        Case 1
            Color 255,0,0
            Rect daX(n)+xScr+18,daY(n)+4+ yScr,10,10,1
            Color 0,0,255
            If dfleeDir(n)=2 Then Text daX(n)+xScr+19,daY(n)+1+ yScr, ">"
            If dfleeDir(n)=4 Then Text daX(n)+xScr+19,daY(n)+1+ yScr, "<"
        End Select
        
        Color 250,250,45
        ;Color 255,43,235
        Rect daX(n)+xScr,daY(n)+ yScr,daW(n),daH(n),0
        Text daX(n)+2+xScr,daY(n)+2+ yScr, n
        If cn = n And areaType=2 Then
            Color Rand(150,255),Rand(150,255),Rand(150,255): Rect daX(cn)+xScr,daY(cn)+ yScr,daW(cn),daH(cn),0
        EndIf
    EndIf
Next

If showEvents Then renderEvent()

.endFunc1
End Function

;-------RENDERING (platforms)------
Function renderPlat()

If forcePlatDraw Then
    For i=1 To platAmount
        Color 255,166,0
        If platHeight(i) =0 Then ph=5 Else ph=platHeight(i)
        Rect xplat(i) + xScr, yplat(i)+ yScr, platWidth(i), ph , 0
        For ii=1 To platPointsAmount(i)
            Color 0,180,0
            If platHeight(cn) =0 Then ph=5 Else ph=platHeight(cn)
            Rect xplatPoint(cn,ii) + xScr, yplatPoint(cn,ii)+ yScr, platWidth(cn), ph , 0
            Text xplatPoint(cn,ii) + xScr, yplatPoint(cn,ii)+ yScr, platWidth(cn), ph , 0
        Next
    Next
    Goto platForceDraw
EndIf

For i=1 To platAmount
  If platWidth(i) > 0 Then
    If platMode <> 4 And cn=i Then
        Color Rand(150,255),Rand(150,255),Rand(150,255)
    Else
        Color 255,166,0
        ;Color 0,255,0
    EndIf
    
    If platHeight(i) =0 Then ph=5 Else ph=platHeight(i)
    Rect xplat(i) + xScr, yplat(i)+ yScr, platWidth(i), ph , 0
    Text xplat(i) + xScr +2 ,yplat(i)+ yScr+1, i 
    
        
    For ii=1 To platpointsAmount(cn)
        
        a=1
        If ii=platPointsAmount(cn) Then a=-(platPointsAmount(cn)-1)
        Color 100,0,0
        Line xplatPoint(cn,ii) + xScr, yplatPoint(cn,ii)+ yScr,xplatPoint(cn,ii+a) + xScr, yplatPoint(cn,ii+a)+ yScr
        
        If ii= platCurPoint(cn) And platMode=4 Then
            Color Rand(150,255),Rand(150,255),Rand(150,255)
        Else
            Color 0,180,0
        EndIf
        
        If platHeight(cn) =0 Then ph=5 Else ph=platHeight(cn)
        Rect xplatPoint(cn,ii) + xScr, yplatPoint(cn,ii)+ yScr, platWidth(cn), ph , 0
        Text xplatpoint(cn,ii) + xScr -10 ,yplatpoint(cn,ii)+ yScr, ii
    Next
    
  EndIf
Next

If click1=1 Then
    Color 228,228,128
    Rect xplat(cn)+xScr,yplat(cn)+ yScr,platWidth(cn)+(MouseX()-xplat(cn))-xScr,platHeight(cn)+ (MouseY()-yplat(cn))- yScr ,0
    Text xplat(cn)+xScr,(yplat(cn)-15)+yScr, Int(platWidth(cn)+(MouseX()-xplat(cn))-xScr)
    Text (xplat(cn)-30)+xScr,yplat(cn)+yScr, Int(platHeight(cn)+ (MouseY()-yplat(cn))- yScr)
EndIf

For n=1 To platamount
    If platUseTrigger(n) And click1=0 Then
        Text xplat(n)+xScr,(yplat(n)-15)+yScr, "E1="+ platEventN(n) + ", E2="+ platEventN2(n)
    EndIf
Next

Color 0,0,0
Rect 0,508,210,240,1
Color 155,183,234
Select platMode
    Case 1: Text 2,490,"Set platform width"
    Case 2: Text 2,490,"Set platform X speed"
    Case 3: Text 2,490,"Set platform Y speed"
    Case 4: Text 2,490,"Set platform current point"
    Case 5: Text 2,490,"Set platform points amount"
    Case 6: Text 2,490,"Set platform danger"
    Case 7: Text 2,490,"Set platform visibility"
    Case 8: Text 2,490,"Set platform picture"
    Case 9: Text 2,490,"Set platform to depend on event"
    Case 0: Text 2,490,"Set platform event number to depend on"
    Case 11: Text 2,490,"Set platform final point destination"
    Case 12: Text 2,490,"Trigger this event number when it reaches final point"
        
End Select

Color 255,255,255
n=510
Text 2,n, "Plataform #: " + cn :n=n+15 
Text 2,n, "1) Width: " + platWidth(cn)+ " Height: "+ platHeight(cn)  :n=n+15
Text 2,n, "2) X speed      : " + platXspeed(cn):n=n+15
Text 2,n, "3) Y speed      : " + platYspeed(cn)   :n=n+15
Text 2,n, "4) Cur. point   : " + platCurPoint(cn):n=n+15
Text 2,n, "5) Points amount: " + platPointsAmount(cn):n=n+15
Text 2,n, "6) Dangerous    : " + val(dangerPlat(cn)):n=n+15
Text 2,n, "7) Visible      : " + val(drawPlat(cn)):n=n+15
Text 2,n, "8) Picture      : " 
If platPic(cn) <> 0 Then
    DrawImage platImage(platPic(cn)) ,150, n
    Text 155+ImageWidth(platImage(platPic(cn))),n, "W="+ImageWidth(platImage(platPic(cn))) +" H="+ImageHeight(platImage(platPic(cn)))
Else
    Text 150,n, "N/A"
EndIf
n=n+15

Text 2,n, "9) platUseEvent: " + val(platUseTrigger(cn)):n=n+15
Text 2,n, "0) plat Event #  : " + PlatEventN(cn):n=n+15
Text 2,n, "S1) plat Final Point: " + PlatFinalDest(cn):n=n+15
Text 2,n, "S2) final point Event#: " + PlatEventN2(cn):n=n+15
Text 2,n, "Q) place starting plat on the selected point" :n=n+20
Text 2,n, "Plat. amount    : " + platAmount:n=n+15
Text 2,n, "curX: " + xplatPoint(cn,platCurPoint(cn)) + " curY: " + yplatPoint(cn,platCurPoint(cn))

If showEvents Then renderEvent()

.platForceDraw

End Function
;-------RENDERING (boxes)------
Function renderBox()

If forceBoxDraw Then
    For i=1 To BoxAmount
        Color 255,166,0
        If BoxHeight(i) =0 Then ph=5 Else ph=BoxHeight(i)
        Rect xBox(i) + xScr, yBox(i)+ yScr, BoxWidth(i), ph , 0
        For ii=1 To BoxPointsAmount(i)
            Color 0,180,0
            If BoxHeight(cn) =0 Then ph=5 Else ph=BoxHeight(cn)
            Rect xBoxPoint(cn,ii) + xScr, yBoxPoint(cn,ii)+ yScr, BoxWidth(cn), ph , 0
            Text xBoxPoint(cn,ii) + xScr, yBoxPoint(cn,ii)+ yScr, BoxWidth(cn), ph , 0
        Next
    Next
    Goto boxForceDraw
EndIf

For i=1 To boxAmount
  If boxWidth(i) > 0 Then
    If boxMode <> 4 And cn=i Then
        Color Rand(150,255),Rand(150,255),Rand(150,255)
    Else
        Color 255,166,0
    EndIf
    
    Rect xbox(i) + xScr, ybox(i)+ yScr, boxWidth(i), boxHeight(i) , 0
    Text xbox(i) + xScr -10 ,ybox(i)+ yScr, i 
    
        
    For ii=1 To boxpointsAmount(cn)
        
        a=1
        If ii=boxPointsAmount(cn) Then a=-(boxPointsAmount(cn)-1)
        Color 100,0,0
        Line xboxPoint(cn,ii) + xScr, yboxPoint(cn,ii)+ yScr,xboxPoint(cn,ii+a) + xScr, yboxPoint(cn,ii+a)+ yScr
        
        If ii= boxCurPoint(cn) And boxMode=4 Then
            Color Rand(150,255),Rand(150,255),Rand(150,255)
        Else
            Color 0,180,0
        EndIf
        
        Rect xboxPoint(cn,ii) + xScr, yboxPoint(cn,ii)+ yScr, boxWidth(cn), boxHeight(cn) , 0
        Text xboxpoint(cn,ii) + xScr -10 ,yboxpoint(cn,ii)+ yScr, ii
    Next
  EndIf
Next

Color 155,183,234
Select boxMode
    Case 1: Text 2,490,"Set box width"
    Case 2: Text 2,490,"Set box X speed"
    Case 3: Text 2,490,"Set box Y speed"
    Case 4: Text 2,490,"Set box current point"
    Case 5: Text 2,490,"Set box points amount"
    Case 6: Text 2,490,"Set box height"
    Case 7: Text 2,490,"Set box visibility"
    Case 8: Text 2,490,"Set box to be target"
    Case 9: Text 2,490,"Set box type"
    Case 10:Text 2,490,"Set box chunk type"
    Case 11:Text 2,490,"Set box to depend on event"
    Case 12:Text 2,490,"Set box event number to depend on"
    Case 13:Text 2,490,"Set box final point destination"
    Case 14:Text 2,490,"Trigger this event number when it reaches final point"
            
End Select

Color 0,0,0
Rect 0,508,210,240,1
Color 255,255,255
n=510
Text 2,n, "Box #: " + cn :n=n+15 
Text 2,n, "1) Width        : " + boxWidth(cn)  :n=n+15
Text 2,n, "2) X speed      : " + boxXspeed(cn):n=n+15
Text 2,n, "3) Y speed      : " + boxYspeed(cn)   :n=n+15
Text 2,n, "4) Cur. point   : " + boxCurPoint(cn):n=n+15
Text 2,n, "5) Points amount: " + boxPointsAmount(cn):n=n+15
Text 2,n, "6) Height       : " + boxHeight(cn):n=n+15
Text 2,n, "7) Visible      : " + val(drawbox(cn)):n=n+15
Text 2,n, "8) Target       : " + targetBox(cn) : n=n+15
Text 2,n, "9) Type         : ---->" 
If boxType(cn) =< boxImageAmount And drawBox(cn)=1 Then
    DrawImage boxImage(boxType(cn)),200,n-20  
    Text 205+ImageWidth(boxImage(boxType(cn))),n, "W="+ImageWidth(boxImage(boxType(cn))) +" H="+ImageHeight(boxImage(boxType(cn)))
Else
    Text 200,n, "N/A"
EndIf
n=n+15
Text 2,n, "0) Chunk type   :" 
If ptImage(boxChunkType(cn)) <> 0 Then 
    DrawImage ptImage(boxChunkType(cn)),150,n -2
Else
    Text 150,n, boxChunkType(cn) +" => N/A"
EndIf
n=n+15
Text 2,n, "W) HitMode("+boxHitMode(cn)+") Damage("+boxDamage(cn)+") Xsp("+boxHitSpeed(cn)+") Ysp("+boxHitYSpeed(cn)+") Snd("+soundStr(boxHitSound(cn))+")":n=n+15
Text 2,n, "Q) Place starting box on the selected point" :n=n+20
Text 2,n, "box. amount    : " + boxAmount:n=n+15
;Text 2,n, "curX: " + xboxPoint(cn,boxCurPoint(cn)) + " curY: " + yboxPoint(cn,boxCurPoint(cn)):n=n+15
Text 2,n, "curX: " + xBoxPoint(cn,boxCurPoint(cn)) + " curY: " + yboxPoint(cn,boxCurPoint(cn)):n=n+15
Text 2,n, "(C) copy box, (V) copy damage attributes" 
x=230:n=510
Text x,n, "S1) box Use Event   : " + val(boxUseTrigger(cn))  :n=n+15
Text x,n, "S2) box event number: " + boxEventN(cn)  :n=n+15
Text x,n, "S3) boxFinalDest#   : " + boxFinalDest(cn):n=n+15
Text x,n, "S4) box Breaks      : " + val(boxBreak(cn)):n=n+15
Text x,n, "S5) box final event : " + boxEventN2(cn)  :n=n+15

If click1=1 Then
    Color 228,228,128
    Rect xbox(cn)+xScr,ybox(cn)+ yScr,boxWidth(cn)+(MouseX()-xbox(cn))-xScr,boxHeight(cn)+ (MouseY()-ybox(cn))- yScr ,0
    Text xbox(cn)+xScr,(ybox(cn)-15)+yScr, Int(boxWidth(cn)+(MouseX()-xbox(cn))-xScr)
    Text (xbox(cn)-30)+xScr,ybox(cn)+yScr, Int(boxHeight(cn)+ (MouseY()-ybox(cn))- yScr)
EndIf

If showEvents Then renderEvent()

.boxForceDraw

End Function
;-------RENDERING (walls)------
Function renderWall()

Color 155,183,234

Select wallMode
    Case 2:Text 2,490, "Select pawn"
    Case 4:Text 2,490, "Click player# "+curPawn+" START location"
    Case 5:Text 2,490, "Click player# "+curPawn+" RESPAWN location"
    Case 6:Text 2,490, "Click flag# "+curFlag+" for its location"
    Case 7:Text 2,490, "set map scroll (On/Off)"
    Case 10:Text 2,490, "set camera X/Y position"
    Default
        If click1=0 Then Text 2,490,"Click for starting X, Y"
        If click1=1 Then Text 2,490,"Click for ending X, Y"        
End Select

Color 255,255,255
n=510
;Text 2,n, "1)Number(wall): " + cn : n=n+15 
;Text 2,n, "wallAmount : " + wallAmount:n=n+15
;Text 2,n, "x: " + xWall(cn) + " y: " + yWall(cn):n=n+15
;Text 2,n, "w: " + wallWidth(cn) + " h: " + wallHeight(cn):n=n+20
Text 2,n, "2) Current player: " + curPawn : n=n+15
Text 2,n, "4) Select player# "+curPawn+" start location": n=n+15
Text 2,n, "5) Select player# "+curPawn+" respawn location": n=n+15
Text 2,n, "6) Select flag# "+curFlag: n=n+15
Text 2,n, "7) Scroll Map= " +val(scrollMap):n=n+15
Text 2,n, "8) Musics: 1= "+musicN1+", 2= "+musicN2:n=n+15
Text 2,n, "9) exit1= "+nextMap(1)+", exit2= "+nextMAp(2) :n=n+15
Text 2,n, "10) Set Camera starting X= "+xScrStart+ " Y= "+yScrStart :n=n+15
Text 2,n, "A) disable air strike= "+ val(noAirStrike) :n=n+15
If scrollMap Then
    Text 2,n, "Hold '<', '>' or '/' + click for map edge limits" :n=n+15
EndIf


If click1=1 Then
    Color 228,228,128
    Rect xWall(cn)+xScr,yWall(cn)+ yScr,wallWidth(cn)+(MouseX()-xWall(cn))-xScr,wallHeight(cn)+ (MouseY()-yWall(cn))- yScr ,0
EndIf

For i= 1 To wallAmount
    Color 255,255,255
    Rect xWall(i)+xScr,yWall(i)+yScr,wallWidth(i),wallHeight(i),0
    Rect xWall(i)+xScr+1,(yWall(i)+yScr)+1,wallWidth(i)-2,wallHeight(i)-2,0
    Color 255,43,234
    Text xwall(i)+xScr+3,yWall(i)+yScr+3, i
Next

Color 0,255,0
For i=1 To pawnAmount
    DrawImage pawnImage,zxStart(i)-10+xScr,zyStart(i)-30+ySCr
    Text zxStart(i)-5+xScr,zyStart(i)-15+ySCr,i
    DrawImage respawnImage,zxRespawn(i)-10+xScr,zyRespawn(i)-30+yScr
    Text zxRespawn(i)-5+xScr,zyRespawn(i)-15+yScr,i
Next

For i=1 To 2
    DrawImage flagImage(i),xFlag(i)+xScr, (yFlag(i)-ImageHeight(flagImage(i))) + yScr
Next

DrawImage cameraImage, (xScrStart-15)+xScr, (yScrStart-20) + yScr

If scrollMap=1 Then
    Rect lScrLimit+xScr,100,1,300,0
    Text (lScrLimit+xScr)-15,200,"<"
    Rect rScrLimit+xScr,100,1,300,0
    Text (rScrLimit+xScr)+3,200,">"
    
    Rect 450,(yScrCameraLimit+yScr)+768,250,1,0
    Text 575,(yScrCameraLimit+yScr)+483,"|"
    Rect 450,(uScrLimit+yScr)+0,250,1,0
    Text 575,(uScrLimit+yScr)+3,"|"
EndIf

If showEvents Then renderEvent()

End Function
;-------RENDERING (TRIGGERS)------
Function renderTrigger()

Color 155,183,234

Select triggerMode
    Case 1:Text 2,490, "Click on plataform to follow"
    Case 2:Text 2,490, "Click on x/y offset position from plataform"
    Case 5:Text 2,490, "Select Event number to trigger"
    Case 7:Text 2,490, "Select trigger Image"
    Case 8:Text 2,490, "Select sound for trigger"
    Default
        If click1=0 Then Text 2,490,"Click for starting X, Y"
        If click1=1 Then Text 2,490,"Click for ending X, Y"        
        
End Select

Color 0,0,0
Rect 0,508,290,240,1
Color 255,255,255
n=510
Text 2,n, "Number(trigger): " + cn +"  X= "+Tx(cn)+ " Y= "+Ty(cn)  : n=n+15 
Text 2,n, "1) action  : " + val(TzAction(cn)): n=n+15
Text 2,n, "2) pass by : " + val(TpassBy(cn)): n=n+15
Text 2,n, "3) obj hit : " + val(TobjHit(cn)): n=n+15
If Tway(cn)=0 Then Tsrt$="N/A"
If Tway(cn)=1 Then Tsrt$="Once"
If Tway(cn)=2 Then Tsrt$="Toggle"
If Tway(cn)=3 Then Tsrt$="Hold"
If Tway(cn)=4 Then Tsrt$="Next Music"
If Tway(cn)=5 Then Tsrt$="Exit 1"
If Tway(cn)=6 Then Tsrt$="Exit 2"
If Tway(cn)=7 Then Tsrt$="Exit 3"
If Tway(cn)=8 Then Tsrt$="Exit 4"
If Tway(cn)=9 Then Tsrt$="Exit 5"
If Tway(cn)=10 Then Tsrt$="fight Mode"
If Tway(cn)=11 Then Tsrt$="screen Lock"
If Tway(cn)=12 Then Tsrt$="air-Special permission"
If Tway(cn)=13 Then Tsrt$="double jump permission"
If Tway(cn)=14 Then Tsrt$="secret area"
Text 2,n, "4) function: " + Tsrt$ :n=n+15
Text 2,n, "5) use event#: " + Tevent(cn)+ " EventN("+Tevent(cn)+")= "+EventN(Tevent(cn)):n=n+15
Text 2,n, "6) draw : " + val(Tdraw(cn)):n=n+15
Text 2,n, "7) image: " 
If Timage(TimageN(cn),1)=0 Then 
    Text 100,620, "N/A"
Else
    DrawImage Timage(TimageN(cn),1), 100,620
EndIf
n=n+15

If Taffect(cn)=0 Then Tsrt$="Only players" Else Tsrt$="Everyone"
Text 2,n, "8) affected by: " + Tsrt$ :n=n+15
Text 2,n, "9) sound: " + soundStr$(Tsound(cn)) :n=n+15
Text 2,n, "0) Select plat to follow: " + Tfollow(cn) :n=n+15
Text 2,n, "o) x/y offset from plat: X=" + Tplatx(cn)+ " Y=" +Tplaty(cn)  :n=n+15
Text 2,n, "R) toggle TonStatus= "+TonStatus(cn)+", ToffStatus= "+ToffStatus(cn)  :n=n+15
Text 2,n, "triggerAmount: " + triggerAmount :n=n+15
Text 2,n, "(Q) Set trigger X/Y image position (E) Toggle Event On/Off"

If click1=1 Then
    Color 228,228,128
    Rect Tx(cn)+xScr,Ty(cn)+ yScr,Tw(cn)+(MouseX()-Tx(cn))-xScr,Th(cn)+ (MouseY()-Ty(cn))- yScr ,0
EndIf

For i= 1 To triggerAmount
    If Tdraw(i)=1 Then
        If Not Timage(TimageN(i),1) = False Then DrawImage Timage(TimageN(i),1),(Tx(i)+TimgX(i))+xScr, (Ty(i)+TimgY(i))+yScr
    EndIf
    Color 255,255,50
    Rect Tx(i)+xScr,Ty(i)+yScr,Tw(i),Th(i),0
    Color 255,0,255
    Text Tx(i)+xScr+3,Ty(i)+yScr+3, i
    Text Tx(i)+xScr+3,Ty(i)+yScr+18, "E"+Tevent(i)
    If i=cn Then 
        Color Rand(0,255),Rand(0,255),Rand(0,255)
        Rect Tx(i)+xScr,Ty(i)+yScr,Tw(i),Th(i),0
    EndIf
Next

renderEvent()

End Function
;-------------------------------  renders events -----------------------------------------------
Function renderEvent()

For n=1 To 30
    EventUsedByPlat(n)=0
    EventUsedByBox(n)=0
    EventUsedByTrigger(n)=0
    EventUsedByFevent(n)=0
    EventUsedByDeadEvent(n)=0
    EventUsedByWaitEvent(n)=0
Next

For n=1 To platAmount
    If platEventN(n) > 0 Then EventUsedByPlat(platEventN(n)) = 1
Next
For n=1 To boxAmount
    If boxEventN(n) > 0 Then EventUsedByBox(boxEventN(n)) = 1
Next
For n=1 To triggerAmount
    If TEvent(n) > 0 Then EventUsedByTrigger(TEvent(n)) = 1
Next
For n=1 To FAmount
    If FEvent(n) > 0 Then EventUsedByFevent(FEvent(n)) = 1
    For i=1 To FfacAmount(n)
       If facDeadEvent(n,i) > 0 And facCategory(n,i)=1 Then EventUsedByDeadEvent(facDeadEvent(n,i)) = 1
       If facWaitEvent(n,i) > 0 And facCategory(n,i)=1 Then EventUsedByWaitEvent(facWaitEvent(n,i)) = 1
    Next
Next

x=5:y=2                    ;show events current state on top of screen
Color 0,0,0
Rect 0,0,1000,40,1
Rect 0,0,250,380,1
Color 255,255,255
For n=1 To 20
    h=14
    If eventUsedByPlat(n) =1 Then Color 255,150,50 : Rect x,y+h+2,3,2,0
    If eventUsedByBox(n) =1 Then Color 120,120,120 : Rect x+5,y+h,3,4,1
    If eventUsedByTrigger(n) =1 Then Color 255,255,0 : Rect x+10,y+h,3,4,0
    If eventUsedByFevent(n) =1 Then Color 255,0,255 : Rect x+15,y+h,3,4,1
      If eventUsedByDeadEvent(n) =1 Then Color 255,0,0 : Rect x+20,y+h,1,4,1
      If eventUsedByWaitEvent(n) =1 Then Color 0,255,0 : Rect x+23,y+h,1,4,1

    If EventN(n)=0 Then Color 255,255,255:Text x,y, "E(" +n+ ")=" + EventN(n)
    If EventN(n)=1 Then Color 255,0,0:Text x,y, "E(" +n+ ")=" + EventN(n)
    If EventN(n)>1 Then Color 255,255,0:Text x,y, "E(" +n+ ")=" + EventN(n)
    x=x+80
    If x > 900 Then y=y+20: x=5
Next

Color 50,50,255
x=5 : y=40
For n= 1 To platAmount
    If platuseTrigger(n)=1 Then 
        Text x,y,"Plat#"+n+" -> event: " + platEventn(n)
        y=y+14
    EndIf
    If platEventN2(n) > 0 Then 
        If platuseTrigger(n)=1 Then 
            Text x+10,y,"on destination trigger event: " + platEventN2(n)
        Else
            Text x+10,y,"Plat#"+n+" on destination trigger event: " + platEventN2(n)
        EndIf
        y=y+14
    EndIf
Next

Color 50,100,255
For n= 1 To boxAmount
    If boxuseTrigger(n)=1 Then 
        Text x,y,"Box#"+n+" -> event: " + boxEventn(n)
        y=y+14
    EndIf
    If boxEventN2(n) > 0 Then 
        If boxuseTrigger(n)=1 Then 
            Text x+10,y,"on destination trigger event: " + boxEventN2(n)
        Else
            Text x+10,y,"Box#"+n+" on destination trigger event: " + boxEventN2(n)
        EndIf
        y=y+14
    EndIf
Next

Color 100,80,255
y=y+5
For n=1 To Famount
    Text x,y, "Fac#"+n+" -> event: " + Fevent(n)
    For i= 1 To FfacAmount(n)
        changedY=0
        If facDeadEvent(n,i) > 0 And facCategory(n,i)=1 Then 
            y=y+14 : changedY=1
            Text x+10,y, "item#"+i+" dead -> event: "+facDeadEvent(n,i)
        EndIf
        If facWaitEvent(n,i) > 0 Then
            y=y+14
            Text x+10,y,"item#"+i+" wait -> event: " + facWaitEvent(n,i)

        EndIf
    Next
    y=y+14
Next

End Function


;-----RENDERING (art / tile set)
Function renderArt()

For b=0 To bgAmount
    For i=0 To tileAmount(b)
        If i= cn And tileFixed(b,cn)=0 And KeyDown(42)=0 Then
            xTile(b,i)=MouseX()-xScr: yTile(b,i)=MouseY()-yScr
            ;Color 200,200,130
            ;Rect xTile(b,i),yTile(b,i),ImageWidth(tilePic(b,i)),ImageHeight(tilePic(b,i)),0
        EndIf
    Next
Next

If KeyDown(47) Then
    Color 255,255,255
    x=500:y=500
    Text x,y,"tileXstart " + tileXstart(bg,cn) : y=y+15
    Text x,y,"tileXend1  " + tileXend1(bg,cn) : y=y+15
    Text x,y,"tileXend2  " + tileXend2(bg,cn) : y=y+15
    Text x,y,"tileXspeed " + tileXspeed(bg,cn) : y=y+15
    Text x,y,"tileXrand1 " + tileXrand1(bg,cn) : y=y+15
    Text x,y,"tileXrand2 " + tileXrand2(bg,cn) : y=y+15
    
    Text x,y,"tileYstart " + tileYstart(bg,cn) : y=y+15
    Text x,y,"tileYend1  " + tileYend1(bg,cn) : y=y+15
    Text x,y,"tileYend2  " + tileYend2(bg,cn) : y=y+15
    Text x,y,"tileYspeed " + tileYspeed(bg,cn) : y=y+15
    Text x,y,"tileYrand1 " + tileYrand1(bg,cn) : y=y+15
    Text x,y,"tileYrand2 " + tileYrand2(bg,cn) : y=y+15
EndIf

Color 255,255,255
n=510
Color 0,0,0
Rect 0,508,310,250,1
Color 255,255,255
Text 2,n, "Number: " + cn + "  bg layer: " + bg: n=n+15 
Text 2,n, "tileAmount : " + tileAmount(bg):n=n+15
Text 2,n, "x: " + xtile(bg,cn) + " y: " + yTile(bg,cn):n=n+15
Text 2,n, "Z) tileFollow : " + tileFollow(bg,cn):n=n+15
Text 2,n, "X) tileTarget : " + tileTarget(bg,cn):n=n+15
Text 2,n, "A) Animation mode: " + val(taniMenu) :n=n+15
Text 2,n, "8) Ani. Amount: " + taniAmount:n=n+15

Text 2,n, "9) xTile scroll speed / by " + xTileScrSpeed(bg,cn):n=n+15
Text 2,n, "10)yTile scroll speed / by " + yTileScrSpeed(bg,cn):n=n+15

Text 2,n, "(+/-) Change background layer":n=n+15
Text 2,n, "(Q) Bg color, (<)move tile id to back, (T) select texture":n=n+15
Text 2,n, "(I) Show tile number, (Y) Grid, (DEL) delete tile":n=n+15
Text 2,n, "(E) Set moving tiles, (V) Show tile moving attributes":n=n+15
Text 2,n, "(C) Copy tiles, (K) change places, (B) Copy ani. (F) One layer":n=n+15


Color 155,183,234
Select artMode
    Case 1: Text 2,490,"Select background layer to work on"
    Case 2: Text 2,490,"Hold (X) And click target tile"
    Case 3: Text 2,490,"Select tile to be animated"
    Case 4: Text 2,490,"Select tile frame# "+tanicurframe(curani)+" for tile("+taniBgSel(curAni)+","+taniNSel(curAni)+")"
    Case 7: Text 2,490,"Set tile X/Y position related to plat/box#"
    Case 8: Text 2,490,"Set the amount of animations"
    Case 9: Text 2,490,"Set tile X scroll Speed"
    Case 10: Text 2,490,"Set tile Y scroll Speed"
End Select

;draws animation menu when activated
If taniMenu=1 Then
;mx(1)=500:my(1)=500 : mw(1)=700:mh(1)=250
Color 128,128,128
Rect mx(1),my(1),mw(1),mh(1),1
Color 255,255,255
b=1
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "ANI# "+curAni
b=2
If artMode=3 Then Color Rand(150,255),Rand(150,255),Rand(150,255) Else Color 255,255,255
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "TILE("+taniBgSel(curAni)+","+taniNSel(curAni)+")"
Color 255,255,255
b=3
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "FRAMES# "+taniFrames(curAni)
b=50
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
If tileFollowType( taniBgSel(curAni), taniNSel(curAni) )=0 Then
    tempS$="NONE"
EndIf
If tileFollowType( taniBgSel(curAni), taniNSel(curAni) )=1 Then
    tempS$="PLAT# "+ tileTarget(taniBgSel(curAni), taniNSel(curAni))+"."
EndIf
If tileFollowType( taniBgSel(curAni), taniNSel(curAni) )=2 Then
    tempS$="BOX# "+ tileTarget(taniBgSel(curAni), taniNSel(curAni))+"."
EndIf
Text xbut(b)+2,yBut(b)+2, "TILE FOLLOWS: "+ tempS$
b=51
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "+X: "+ xtile2(taniBgSel(curAni), taniNSel(curAni))
b=52
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "+Y: "+ yTile2(taniBgSel(curAni), taniNSel(curAni))
Text xbut(b)+85,ybut(b)+2, "Press O"


For b=4 To taniFrames(curAni)+3
    Color 0,0,0
    Rect xbut(b),yBut(b),wbut(b),hbut(b),1
    If taniCurFrame(curAni)=b-3 Then
        Color 255,0,0
        Rect xbut(b),yBut(b),wbut(b),hbut(b),0
    Else
        Color 255,255,255
        Rect xbut(b),yBut(b),wbut(b),hbut(b),0
    EndIf
    
    If tilePic( taniBg(curAni,b-3),taniN(curAni,b-3 )) <> 0 Then
        DrawImage tilePic( taniBg(curAni,b-3),taniN(curAni,b-3 )), xbut(b)+1,ybut(b)+1
    EndIf
Next

Color 255,255,255
For b=20 To taniFrames(curAni)+19
    Rect xbut(b),yBut(b),wbut(b),hbut(b),0
    Text xbut(b)+2,yBut(b)+2, "TIME:"+taniTime(curAni,b-19)
Next

Color 255,0,0
If taniNsel(curAni) > 0 Then
    Line xbut(2),ybut(2), xTile(taniBgSel(curAni),taniNSel(curAni))+xScr, yTile(taniBgSel(curAni),taniNSel(curAni))+yScr
EndIf

EndIf

If selectTile=1
    Color 0,0,0
    Rect 0,0,1024,780,1
    For i=1 To tAmount
        DrawImage tPic(i),xtNail(i), ytNAil(i)
        Color 255,255,200
        Rect xtNail(i),ytNail(i),tWidth(i),tHeight(i),0
    Next
EndIf

If showEvents Then renderEvent()

End Function
;-----build factory-----------------------
Function factory()
mouseClicked=0
mouseClicked2=0
If MouseHit(1) Then mouseClicked =1
If MouseHit(2) Then mouseClicked2=1

If KeyDown(42) Then shift=1 Else shift=0
If KeyDown(56) Then alt=1 Else alt=0

mx(1)=10:my(1)=400 : mw(1)=950:mh(1)=350    ;Define Menu position
;----------
b=190
xbut(b)=mx(1)+3:yBut(b)=my(1)+3  :wbut(b)=100:hBut(b)=18   ;FACTORY# button
b=191
xbut(b)=mx(1)+110:yBut(b)=my(1)+3:wbut(b)=100:hBut(b)=18   ;FfacAMount# button
b=192
xbut(b)=mx(1)+220:yBut(b)=my(1)+3:wbut(b)=100:hBut(b)=18   ;Factory Event# button
b=193
xbut(b)=mx(1)+330:yBut(b)=my(1)+3:wbut(b)=100:hBut(b)=18   ;Event# button
b=194
xbut(b)=mx(1)+440:yBut(b)=my(1)+3:wbut(b)=100:hBut(b)=18   ;F. loop# button

b=1    ;row start (Test)
x= mx(1)+3
i=1
bh=16
For n=1 To 8
    xbut(i)=mx(1)+x:yBut(i)=my(1)+30:               wbut(i)=50:hBut(i)=bh:Nbut(i)=b:   ;Category# button
    strBut$(i)="C: " + facCategory(cn7,nbut(i)) :typeBut(i)=1 : i=i+1
    xbut(i)=mx(1)+x+50:yBut(i)=my(1)+30:            wbut(i)=50:hBut(i)=bh:Nbut(i)=b:   ;Type N# button        
    strBut$(i)="N:" + facType(cn7,nbut(i)) :typeBut(i)=2 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=80:Nbut(i)=b:   ;picture# button
    strBut$(i)= nbut(i) :typeBut(i)=3 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;x/y# button
    strBut$(i)="X:"+xfac(cn7,nbut(i))+" Y:"+yfac(cn7,nbut(i)) :typeBut(i)=4 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;life# button
    If facCategory(cn7,nbut(i))=1 Then s$="Life: "
    If facCategory(cn7,nbut(i))=2 Then s$="y speed: "
    If facCategory(cn7,nbut(i))=3 Then s$="y speed: "
    strBut$(i)=s$ + facLife(cn7,nbut(i)) :typeBut(i)=5 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;lives# button
    If facCategory(cn7,nbut(i))=1 Then s$="Lives: "
    If facCategory(cn7,nbut(i))=2 Then s$="objHurts: "
    If facCategory(cn7,nbut(i))=3 Then s$="Drills: "
    If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=50 Then s$="Dir: "
    strBut$(i)=s$ + facLives(cn7,nbut(i)) :typeBut(i)=6 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;damage# button
    strBut$(i)="Damage:" + facDamage(cn7,nbut(i)) :typeBut(i)=7 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;team# button
    s$="Team: "
    If facCategory(cn7,nbut(i))=2 Or facCategory(cn7,nbut(i))=3 Then s$="Owner: "
    strBut$(i)=s$ + facTeam(cn7,nbut(i)) :typeBut(i)=8 : i=i+1    
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;aiLevel# button
    If facCategory(cn7,nbut(i))=1 Then s$="AiLevel: "
    If facCategory(cn7,nbut(i))=2 Then s$="objDir : "    
    If facCategory(cn7,nbut(i))=3 Then s$="shotDir: "
    If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=50 Then s$="Facing: "
    strBut$(i)=s$ + facAiLevel(cn7,nbut(i)) :typeBut(i)=9 : i=i+1    
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;Dead event# button
    If facCategory(cn7,nbut(i))=1 Then s$="deadEvent:"
    If facCategory(cn7,nbut(i))=2 Then s$="x speed: "
    If facCategory(cn7,nbut(i))=3 Then s$="x speed: "
    
    strBut$(i)=s$ + facDeadEvent(cn7,nbut(i)) :typeBut(i)=10 : i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;Wait Event# button
    strBut$(i)="WaitEvent:" + facWaitEvent(cn7,nbut(i)) :typeBut(i)=11 : i=i+1    
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;delay time# button
    strBut$(i)="Delay:" + facDelay(cn7,nbut(i)) :typeBut(i)=12 : i=i+1    
    
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;projectile# button
    strBut$(i)="Prjctl:" + facChunk(cn7,nbut(i)) :typeBut(i)=13 : i=i+1
    
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;Sound# button 
    strBut$(i)="snd:" + soundStr(facSound(cn7,nbut(i))) :typeBut(i)=14: i=i+1
    
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;var1# button 
      s$="Var1: "      
      ;If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=43 Then s$="Var1: "
        If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=41 Then s$="xSpeed: "  
      If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=47 Then s$="Bullet: "
      If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=48 Then s$="Bullet: "
      If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=50 Then s$="x.y.max:"
      If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=52 Then s$="has life:"
      If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=37 Then s$="Strong: "
      If facCategory(cn7,nbut(i))=2 Or facCategory(cn7,nbut(i))=3 Then s$="Super: "
      If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=45 Then s$="Damager: "
          
    strBut$(i)=s$ + facVar1(cn7,nbut(i)) :typeBut(i)=15: i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;var2# button 
    s$="Var2: "
    If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=50 Then s$="damager: "
    If facCategory(cn7,nbut(i))=2 Then s$="noGrav: "
    If facCategory(cn7,nbut(i))=3 Then s$="drill: "
    If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=45 Then s$="Dir.: "
    strBut$(i)=s$ + facVar2(cn7,nbut(i)) :typeBut(i)=16: i=i+1
    xbut(i)=mx(1)+x:yBut(i)=ybut((i)-1)+hbut((i)-1):wbut(i)=100:hBut(i)=bh:Nbut(i)=b:   ;var3# button 
    s$="Var3: "
    If facCategory(cn7,nbut(i))=1 And facType(cn7,nbut(i))=50 Then s$="speed: "
    If facCategory(cn7,nbut(i))=2 Then s$="hitMode: "
    strBut$(i)=s$ + facVar3(cn7,nbut(i)) :typeBut(i)=17: i=i+1
    
    b=b+1
    x=x+115
Next
;----------
b=190 : button(xbut(b),yBut(b),wBut(b),hBut(b))
If mouseClicked=1 And butClicked=1 Then mouseClicked=0 : cn7=cn7+1
If mouseClicked2=1 And butClicked=1 Then mouseClicked2=0 : cn7=cn7-1
b=191 : button(xbut(b),yBut(b),wBut(b),hBut(b))
If mouseClicked=1 And butClicked=1 Then mouseClicked=0 : FfacAmount(cn7)=FfacAmount(cn7)+1
If mouseClicked2=1 And butClicked=1 Then mouseClicked2=0 : FfacAmount(cn7)=FfacAmount(cn7)-1
b=192 : button(xbut(b),yBut(b),wBut(b),hBut(b))
If mouseClicked=1 And butClicked=1 Then mouseClicked=0 : Fevent(cn7)=Fevent(cn7)+1
If mouseClicked2=1 And butClicked=1 Then mouseClicked2=0 : Fevent(cn7)=Fevent(cn7)-1
b=193 : button(xbut(b),yBut(b),wBut(b),hBut(b))
If mouseClicked=1 And butClicked=1 Then mouseClicked=0 : eventN(Fevent(cn7))=eventN(Fevent(cn7))+1
If mouseClicked2=1 And butClicked=1 Then mouseClicked2=0 : eventN(Fevent(cn7))=eventN(Fevent(cn7))-1
b=194 : button(xbut(b),yBut(b),wBut(b),hBut(b))
If mouseClicked=1 And butClicked=1 Then mouseClicked=0 : Floop(cn7)=Floop(cn7)+1
If mouseClicked2=1 And butClicked=1 Then mouseClicked2=0 : Floop(cn7)=Floop(cn7)-1

For i=1 To 136
cli=0
button(xbut(i),yBut(i),wBut(i),hBut(i))
If shift=1 Then nnn1=-5 : nnn2=-10 Else nnn1=-1 : nnn2=-5
If alt=1 Then nnn1=-50 : nnn2=-50
If butClicked=1 And mouseClicked =1 Then mouseClicked=0 :n1= Abs(nnn1):n2= Abs(nnn2) :cli=1
If butClicked=1 And mouseClicked2=1 Then mouseClicked2=0:n1= nnn1     :n2= nnn2 :cli=1
If cli=1 Then
    facMode=0
    Select typeBut(i)
    Case 1: facCategory(cn7,nbut(i))=facCategory(cn7,nbut(i))+n1:Exit
    Case 2: facType(cn7,nbut(i))=facType(cn7,nbut(i))+n1:
            If facType(cn7,nbut(i)) = 9 And facCategory(cn7,nbut(i)) =1 Then facType(cn7,nbut(i))=30
            If facType(cn7,nbut(i)) = 29 And facCategory(cn7,nbut(i)) =1 Then facType(cn7,nbut(i))=8
            Exit
    
    Case 4: facMode=4:curF(cn7)=nbut(i): Exit
    Case 5:
      If facCategory(cn7,nbut(i))=1 Then facLife(cn7,nbut(i))=facLife(cn7,nbut(i))+n2:Exit
      If facCategory(cn7,nbut(i))>1 Then facLife(cn7,nbut(i))=facLife(cn7,nbut(i))+n1:Exit
    Case 6: facLives(cn7,nbut(i))=facLives(cn7,nbut(i))+n1:Exit
    Case 7: facDamage(cn7,nbut(i))=facDamage(cn7,nbut(i))+n1:Exit
    Case 8: facTeam(cn7,nbut(i))=facTeam(cn7,nbut(i))+n1:Exit
    Case 9: facAiLevel(cn7,nbut(i))=facAiLevel(cn7,nbut(i))+n1:Exit
    Case 10:facDeadEvent(cn7,nbut(i))=facDeadEvent(cn7,nbut(i))+n1:Exit
    Case 11:facWaitEvent(cn7,nbut(i))=facWaitEvent(cn7,nbut(i))+n1:Exit
    Case 12:facDelay(cn7,nbut(i))=facDelay(cn7,nbut(i))+n2:Exit
    Case 13:facChunk(cn7,nbut(i))=facChunk(cn7,nbut(i))+n1:Exit
    Case 14:facSound(cn7,nbut(i))=facSound(cn7,nbut(i))+n1:Exit
    Case 15:facMode=15: facVar1(cn7,nbut(i))=facVar1(cn7,nbut(i))+n1:Exit
    Case 16:facVar2(cn7,nbut(i))=facVar2(cn7,nbut(i))+n1:Exit
    Case 17:facVar3(cn7,nbut(i))=facVar3(cn7,nbut(i))+n1:Exit
    
    End Select
EndIf
If cli=1 Then Exit
Next

If mouseCLicked=1 Then
    Select facMode
    Case 4: xfac(cn7, curF(cn7))=(MouseX()-xScr) : yfac(cn7,curF(cn7))=(MouseY()- yScr)
    Case 15
        If facCategory(cn7, curF(cn7))=1 And facType(cn7, curF(cn7))=50 Then
            Select facLives(cn7, curF(cn7))
                Case 1 facVar1(cn7, curF(cn7))=(MouseY()-yScr)
                Case 2 facVar1(cn7, curF(cn7))=(MouseX()-yScr)
                Case 3 facVar1(cn7, curF(cn7))=(MouseY()-yScr)
                Case 4 facVar1(cn7, curF(cn7))=(MouseX()-yScr)
            End Select        
        EndIf
    End Select
EndIf

If KeyHit(201) Then ; +
    Famount=Famount+1
    If Famount > 100 Then Famount=0
EndIf
If KeyHit(209) Then ; -
    Famount=Famount-1
    If Famount < 0 Then Famount=100
EndIf
If KeyHit(18) Then    ;Toggle factory event on/off
    If eventN(Fevent(cn7))=1 Then eventN(Fevent(cn7))=0 Else eventN(Fevent(cn7))=1
EndIf

End Function
;-----RENDER FACTORY-----------------------
Function renderFactory()

Color 120,120,120
Rect mx(1),my(1),mw(1),mh(1),1
Color 255,255,255
Text mx(1),my(1)-17, "Factory Amount: " + Famount
b=190
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "FACTORY# "+ cn7
b=191
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "amount# "+FfacAmount(cn7)
Color 255,255,255
b=192
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "F. event# "+Fevent(cn7)
b=193
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "Event# "+ eventN(Fevent(cn7))
b=194
Rect xbut(b),yBut(b),wbut(b),hbut(b),0
Text xbut(b)+2,yBut(b)+2, "F. loop# "+ Floop(cn7)

For i=1 To 136    ;draws buttons
    If typeBut(i)=3 Then
      Select facCategory(cn7,nbut(i))
      Case 1    
        If guyImage(facType(cn7,nbut(i))) <> 0 Then
            DrawImage guyImage(facType(cn7,nbut(i))), xbut(i)+10 , ybut(i)+10
        EndIf
      Case 2    
        If objImage(facType(cn7,nbut(i))) <> 0 Then
            DrawImage objImage(facType(cn7,nbut(i))), xbut(i)+10 , ybut(i)+10
        EndIf
      Case 3    
        If shotImage(facType(cn7,nbut(i))) <> 0 Then
            DrawImage shotImage(facType(cn7,nbut(i))), xbut(i)+10 , ybut(i)+10
        EndIf
          Case 4
        If ptImage(facType(cn7,nbut(i))) <> 0 Then
            DrawImage ptImage(facType(cn7,nbut(i))), xbut(i)+10 , ybut(i)+10
        EndIf
        
      End Select
    EndIf
    
    Rect xbut(i),yBut(i),wbut(i),hbut(i),0
    Text xbut(i)+2,yBut(i)+1, StrBut$(i) 
        
    If typeBut(i)=13 And ptImage( facChunk( cn7,nbut(i) ) ) <> 0 Then 
        DrawImage ptImage( facChunk( cn7,nbut(i) ) ),xbut(i)+70,yBut(i)+1
    EndIf
    
Next

Color 255,0,0
x=50
y=470
For n=1 To FfacAmount(cn7)
    Line x,y, xfac(cn7,n)+xscr,yfac(cn7,n)+yscr
    x=x+115
    ;If facCategory(cn7,n)=1 And facType(cn7,n) =50 Then
    ;    Line xfac(cn7,n)+xscr,yfac(cn7,n)+yscr, zfacVar1(cn7,n)
    ;EndIf
Next

If showEvents Then renderEvent()

End Function
;-----BUTTON TO BE CLICKED-------
Function button(x,y,w,h)
butClicked=0
If  ( MouseX() => x And MouseX() =< x+w ) Then
    If MouseY() => y And MouseY() =< y+h Then
        butClicked=1 
    EndIf
EndIf        
End Function

;-----SAVE MAP------------------
Function saveMap()
file=WriteFile(mapsDir$ + mapStr$ + curMap + ".dat")

WriteInt file, areaAmount
For n=1 To areaAmount    
    WriteInt file, saX(n)
    WriteInt file, saY(n)
    WriteInt file, saW(n)
    WriteInt file, saH(n)
    WriteInt file, fleeDir(n)
    WriteInt file, dangerArea(n)
    WriteInt file, edges(n)
    WriteInt file, samovedBy(n)
Next

WriteInt file, dAreaAmount
For n=1 To dAreaAmount
    WriteInt file, daX(n)
    WriteInt file, daY(n)
    WriteInt file, daW(n)
    WriteInt file, daH(n)
    WriteInt file, dfleeDir(n)
    WriteInt file, daType(n)
    WriteInt file, daTargetH(n)
    WriteInt file, damovedBy(n)
Next

WriteInt file, platAmount
For n=1 To platAmount

WriteFloat file, xplat(n)
WriteFloat file, yplat(n)
WriteFloat file, platXspeed(n)
WriteFloat file, platYspeed(n)
WriteInt file, dangerplat(n)
WriteInt file, drawplat(n)
WriteInt file, platWidth(n)
WriteInt file, platHeight(n)
WriteInt file, platCurPoint(n)
WriteInt file, platPointsAmount(n)
WriteInt file, platPic(n)
WriteInt file, platUseTrigger(n)
WriteInt file, platEventN(n)
WriteInt file, platFinalDest(n)
WriteInt file, platBreak(n)
WriteInt file, platChunk(n)
WriteInt file, platSound(n)
WriteInt file, platBreakable(n)
WriteInt file, platEventN2(n)

    For i=1 To platPointsAmount(n)
        WriteInt file, xplatpoint(n,i)
        WriteInt file, yplatpoint(n,i)
    Next

Next

WriteInt file, boxAmount
For n=1 To boxAmount

WriteFloat file, xbox(n)
WriteFloat file, ybox(n)
WriteFloat file, boxXspeed(n)
WriteFloat file, boxYspeed(n)
WriteInt file, targetBox(n)
WriteInt file, drawbox(n)
WriteInt file, boxWidth(n)
WriteInt file, boxHeight(n)
WriteInt file, boxCurPoint(n)
WriteInt file, boxPointsAmount(n)
WriteInt file, boxType(n)
WriteInt file, boxChunkType(n)
WriteInt file, boxHitMode(n)
WriteFloat file, boxHitTime(n)
WriteFloat file, boxHitSpeed(n)
WriteFloat file, boxHitYSpeed(n)
WriteInt file, boxDamage(n)

WriteInt file, boxHitSound(n)
WriteInt file, boxUseTrigger(n)
WriteInt file, boxEventN(n)
WriteInt file, boxFinalDest(n)
WriteInt file, boxBreak(n)
WriteInt file, boxSound(n)
WriteInt file, boxBreakable(n)
WriteInt file, boxEventN2(n)
    
    For i=1 To boxPointsAmount(n)
        WriteInt file, xboxpoint(n,i)
        WriteInt file, yboxpoint(n,i)
    Next

Next

WriteInt file, wallAmount
For n=1 To wallAmount

WriteInt file, xWall(n)
WriteInt file, yWall(n)
WriteInt file, wallWidth(n)
WriteInt file, wallHeight(n)

Next

WriteInt file, colorR
WriteInt file, colorG
WriteInt file, colorB
For b=0 To bgAmount
    WriteInt file, tileAmount(b)
    For n=1 To tileAmount(b)
        WriteFloat file, xTile(b,n)
        WriteFloat file, yTile(b,n)
        WriteInt file, tileNumber(b,n)
        WriteInt file, tileSetNumber(b,n)
        
        ;Check If any tile is inexistent And warn
        If tileSetNumber(b,n) = 0 Or tileNumber(b,n) = 0 Then 
            msg=1:msgseq=0:msgTxt$="Tile# " +n+ " on BG# " +b+ " error!"
        EndIf
                
        WriteInt file, tileXend1(b,n) 
        WriteInt file, tileXend2(b,n) 
        WriteInt file, tileXrand1(b,n)
        WriteInt file, tileXrand2(b,n)
        WriteFloat file, tileXspeed(b,n)
        WriteInt file, tileYend1(b,n)
        WriteInt file, tileYend2(b,n)
        WriteInt file, tileYrand1(b,n)
        WriteInt file, tileYrand2(b,n)
        WriteFloat file, tileYspeed(b,n)
        WriteInt file, tileXstart(b,n)
        WriteInt file, tileYstart(b,n)
        WriteInt file, tileFollow(b,n)
        WriteInt file, tileTarget(b,n)
        WriteInt file, xTile2(b,n)
        WriteInt file, yTile2(b,n)
        WriteInt file, tileFollowType(b,n)
        WriteFloat file, xTileScrSpeed(b,n)
        WriteFloat file, yTileScrSpeed(b,n)
    Next
Next

WriteInt file, pawnAmount
For n=1 To pawnAmount
    WriteInt file, zxStart(n)
    WriteInt file, zyStart(n)
    WriteInt file, zxRespawn(n)
    WriteInt file, zyRespawn(n)
Next

For n=1 To 2
    WriteInt file, xFlag(n)
    WriteInt file, yFlag(n)
Next

WriteInt file, scrollMap

For n=1 To 100
    WriteInt file, EventN(n)
Next

WriteInt file, triggerAmount
For n=1 To triggerAmount
    WriteInt file, Tx(n)
    WriteInt file, Ty(n)
    WriteInt file, Tw(n)
    WriteInt file, Th(n)
    WriteInt file, Tway(n)
    WriteInt file, Ton(n)
    WriteInt file, TzAction(n)
    WriteInt file, TpassBy(n)
    WriteInt file, Tobjhit(n)
    WriteInt file, Tevent(n)
    WriteInt file, Tdraw(n)
    WriteInt file, TimageN(n)
    WriteInt file, TimgX(n)
    WriteInt file, TimgY(n)
    WriteInt file, Taffect(n)
    WriteInt file, Tsound(n)
    WriteInt file, Tfollow(n)
    WriteInt file, TplatX(n)
    WriteInt file, TplatY(n)
    WriteInt file, TonStatus(n)
    WriteInt file, ToffStatus(n)
Next

For n=1 To 5
    WriteInt file, nextMap(n)
Next

WriteInt file, xScrStart
WriteInt file, yScrStart
WriteInt file, fightMode
WriteInt file, curMap
WriteInt file, ScrLock
WriteInt file, vsMode
WriteInt file, yScrCameraLimit
WriteInt file, uScrLimit
WriteInt file, noAirStrike
WriteInt file, var4
WriteInt file, var5
WriteInt file, var6
WriteInt file, var7
WriteInt file, var8
WriteInt file, var9
WriteInt file, var10
WriteString file, Stri$
WriteString file, Stri$
WriteString file, Stri$


WriteInt file, lScrLimit
WriteInt file, rScrLimit
WriteInt file, musicN1
WriteInt file, musicN2



WriteInt file, taniAmount
For n=1 To taniAmount
    WriteInt file, taniseq(n)
    WriteInt file, tAniFrames(n)
    WriteInt file, taniBgSel(n)
    WriteInt file, tAniNSel(n)
    WriteInt file, taniCurFrame(n)
    For i=1 To taniFrames(n)
        WriteInt file, taniBg(n,i)
        WriteInt file, taniN(n,i)
        WriteInt file, tAniTime(n,i)
    Next
Next

WriteInt file, Famount
For n=1 To Famount
    WriteInt file, curF(n)
    WriteInt file, FdelaySeq(n)
    WriteInt file, Fevent(n)
    WriteInt file, FfacAmount(n)
    WriteInt file, Floop(n)
    For i=1 To FfacAmount(n)
        WriteInt file, xfac(n,i)
        WriteInt file, yfac(n,i)
        WriteInt file, facDir(n,i)
        WriteInt file, facLife(n,i)
        WriteInt file, facLives(n,i)
        WriteInt file, facTeam(n,i)
        WriteInt file, facDamage(n,i)
        WriteInt file, facAiLevel(n,i)
        WriteInt file, facTeam(n,i)
        WriteInt file, facCategory(n,i)
        WriteInt file, facType(n,i)
        WriteInt file, facDelay(n,i)
        WriteInt file, facDeadEvent(n,i)
        WriteInt file, facWaitEvent(n,i)
        WriteInt file, facChunk(n,i)
        WriteInt file, facSound(n,i)
        WriteInt file, facVar1(n,i)
        WriteInt file, facVar2(n,i)
        WriteInt file, facVar3(n,i)
        WriteInt file, facVar4(n,i)
        WriteInt file, facVar5(n,i)
        
        If facCategory(n,i)=2 And facAiLevel(n,i) = 0 Then
            msg=1:msgseq=0:msgTxt$="objDir=0 on Factory " +n+ " sub "+i
        End If
        
    Next
Next

CloseFile file

End Function

;----LOAD MAP-------------------
Function loadMap()

If FileType(mapsDir$ + mapStr$ + curMap + ".dat")=0
    For n=1 To 20
        platCurPoint(i)=1 : drawPlat(i)=1 :  platPointsAmount(i)=1 : platPic(i)=1
        boxCurPoint(i)=1 : drawbox(i)=1 :  boxPointsAmount(i)=1 : boxType(i)=1:boxChunkType(i)=1:boxHitSound(i)=1
        saY(n)=-500:daY(n)=-500
    Next
    ;saveMap()
    Return False
Else 
    file=ReadFile(mapsDir$ + mapStr$ + curMap + ".dat")
EndIf

;-------START----------
areaAmount = ReadInt (file)
For n=1 To areaAmount
saX(n) = ReadInt (file)
saY(n) = ReadInt (file)
saW(n)= ReadInt (file)
saH(n)= ReadInt (file)
fleeDir(n)= ReadInt (file)
dangerArea(n)= ReadInt (file)
edges(n)= ReadInt (file)
samovedBy(n)= ReadInt (file)
Next

dAreaAmount = ReadInt (file)
For n=1 To dAreaAmount
daX(n) = ReadInt (file)
daY(n) = ReadInt (file)
daW(n)= ReadInt (file)
daH(n)= ReadInt (file)
dfleeDir(n)= ReadInt (file)
daType(n)= ReadInt (file)
daTargetH(n)= ReadInt (file)
damovedBy(n)= ReadInt (file)
Next

platAmount = ReadInt (file)
For n=1 To platAmount
xplat(n) = ReadFloat (file)
yplat(n) = ReadFloat (file)
platXspeed(n) = ReadFloat (file)
platYspeed(n) = ReadFloat (file)
dangerplat(n) = ReadInt (file)
drawplat(n) = ReadInt (file)
platWidth(n) = ReadInt (file)
platHeight(n) = ReadInt (file)
platCurPoint(n) = ReadInt (file)
platPointsAmount(n) = ReadInt (file)
platPic(n) = ReadInt (file)
platUseTrigger(n)= ReadInt (file)   ;;
platEventN(n)= ReadInt (file)       ;;
platFinalDest(n) = ReadInt (file)
platBreak(n) = ReadInt (file)
platChunk(n) = ReadInt (file)
platSound(n) = ReadInt (file)
platBreakable(n) = ReadInt (file)
platEventN2(n) = ReadInt (file)

    For i=1 To platPointsAmount(n)
        xplatpoint(n,i) = ReadInt (file)
        yplatpoint(n,i) = ReadInt (file)
    Next
Next

boxAmount = ReadInt (file)
For n=1 To boxAmount
    xbox(n) = ReadFloat (file)
    ybox(n) = ReadFloat (file)
    boxXspeed(n) = ReadFloat (file)
    boxYspeed(n) = ReadFloat (file)
    targetBox(n) = ReadInt (file)
    drawbox(n) = ReadInt (file)
    boxWidth(n) = ReadInt (file)
    boxHeight(n) = ReadInt (file)
    boxCurPoint(n) = ReadInt (file)
    boxPointsAmount(n) = ReadInt (file)
    boxType(n) = ReadInt (file)
    boxChunkType(n) = ReadInt (file)
    boxHitMode(n) = ReadInt (file)
    boxHitTime(n) = ReadFloat (file)
    boxHitSpeed(n) = ReadFloat (file)
    boxHitYSpeed(n) = ReadFloat (file)
    boxDamage(n) = ReadInt (file)
    boxHitSound(n) = ReadInt (file)
    boxUseTrigger(n)=ReadInt (file)        ;;
    boxEventN(n)=ReadInt (file)            ;;
    boxFinalDest(n)= ReadInt (file)
    boxBreak(n)= ReadInt (file)
    boxSound(n) = ReadInt (file)
    boxBreakable(n) = ReadInt (file)
    boxEventN2(n) = ReadInt (file)

    For i=1 To boxPointsAmount(n)
        xboxpoint(n,i) = ReadInt (file)
        yboxpoint(n,i) = ReadInt (file)
    Next
Next

wallAmount = ReadInt (file)
For n=1 To wallAmount

xWall(n) = ReadInt (file)
yWall(n) = ReadInt (file)
wallWidth(n) = ReadInt (file)
wallHeight(n) = ReadInt (file)

Next

colorR=ReadInt (file)
colorG=ReadInt (file)
colorB=ReadInt (file)
For b=0 To bgAmount  ;
    tileAmount(b) = ReadInt (file)
    For n=1 To tileAmount(b)
        xTile(b,n) = ReadFloat (file)
        yTile(b,n) = ReadFloat (file)
        tileNumber(b,n) = ReadInt (file)
        tileSetNumber(b,n) = ReadInt (file)
        tileXend1(b,n) = ReadInt (file);;
        tileXend2(b,n) = ReadInt (file);;
        tileXrand1(b,n) = ReadInt (file);;
        tileXrand2(b,n) = ReadInt (file);;
        tileXspeed(b,n) = ReadFloat (file);;
        tileYend1(b,n) = ReadInt (file);;
        tileYend2(b,n) = ReadInt (file);;
        tileYrand1(b,n) = ReadInt (file);;
        tileYrand2(b,n) = ReadInt (file);;
        tileYspeed(b,n) = ReadFloat (file);;
        tileXstart(b,n) = ReadInt (file);;
        tileYstart(b,n) = ReadInt (file);;
        tileFollow(b,n)= ReadInt (file)
        tileTarget(b,n)= ReadInt (file)
        xtile2(b,n) = ReadInt (file)
        ytile2(b,n) = ReadInt (file)
        tileFollowType(b,n) = ReadInt (file)
        xTileScrSpeed(b,n) = ReadFloat (file)
        yTileScrSpeed(b,n) = ReadFloat (file)
    Next
Next

pawnAmount=ReadInt (file)
For n=1 To pawnAmount
    zxStart(n)=ReadInt (file)
    zyStart(n)=ReadInt (file)
    zxRespawn(n)=ReadInt (file)
    zyRespawn(n)=ReadInt (file)
Next

For n=1 To 2
    xFlag(n)=ReadInt (file)
    yFlag(n)=ReadInt (file)
Next

scrollMap = ReadInt (file)

For n=1 To 100
    EventN(n)= ReadInt (file)
Next

triggerAmount= ReadInt (file)   ;;;
For n=1 To triggerAmount
    Tx(n)= ReadInt (file)
    Ty(n)= ReadInt (file)
    Tw(n)= ReadInt (file)
    Th(n)= ReadInt (file)
    Tway(n)= ReadInt (file)
    Ton(n)= ReadInt (file)
    Tzaction(n)= ReadInt (file)
    TpassBy(n)= ReadInt (file)
    TobjHit(n)= ReadInt (file)
    Tevent(n)= ReadInt (file)
    Tdraw(n)= ReadInt (file)
    TimageN(n)= ReadInt (file)
    TimgX(n)= ReadInt (file)
    TimgY(n)= ReadInt (file)
    Taffect(n) = ReadInt (file)        ;Trigger affected by everyone?
    Tsound(n) = ReadInt (file)
    Tfollow(n) = ReadInt (file)
    Tplatx(n) = ReadInt (file)
    Tplaty(n) = ReadInt (file)
    TonStatus(n) = ReadInt (file)
    ToffStatus(n) = ReadInt (file)
Next

For n=1 To 5
    nextMap(n) = ReadInt (file)
Next
xScrStart = ReadInt (file)
yScrStart = ReadInt (file)
fightMode = ReadInt (file)
MapN = ReadInt (file)
scrLock = ReadInt (file)
vsMode = ReadInt (file)
yScrCameraLimit = ReadInt (file)
uScrLimit = ReadInt (file)
noAirStrike = ReadInt (file)
var4 = ReadInt (file)
var5 = ReadInt (file)
var6 = ReadInt (file)
var7 = ReadInt (file)
var8 = ReadInt (file)
var9 = ReadInt (file)
var10 = ReadInt (file)
stri = ReadString (file)
stri = ReadString (file)
stri = ReadString (file)

lScrLimit= ReadInt (file)
rScrLimit= ReadInt (file)
musicN1 = ReadInt (file)
musicN2 = ReadInt (file)

taniAmount= ReadInt (file)
For n=1 To taniAmount
    taniseq(n)= ReadInt (file)
    tAniFrames(n)= ReadInt (file)
    taniBgSel(n)= ReadInt (file)
    tAniNSel(n)= ReadInt (file)
    taniCurFrame(n)= ReadInt (file)
    For i=1 To taniFrames(n)
        taniBg(n,i)= ReadInt (file)
        taniN(n,i)= ReadInt (file)
        tAniTime(n,i)= ReadInt (file)
    Next
Next

Famount= ReadInt (file)
For n=1 To Famount
    curF(n)= ReadInt (file)
    FdelaySeq(n)= ReadInt (file)
    Fevent(n)= ReadInt (file)
    FfacAmount(n)= ReadInt (file)
    Floop(n)= ReadInt (file)
    For i=1 To FfacAmount(n)
        xfac(n,i)= ReadInt (file)
        yfac(n,i)= ReadInt (file)
    facDir(n,i)= ReadInt (file)
        facLife(n,i)= ReadInt (file)
        facLives(n,i)= ReadInt (file)
        facTeam(n,i)= ReadInt (file)
        facDamage(n,i)= ReadInt (file)
        facAiLevel(n,i)= ReadInt (file)
        facTeam(n,i)= ReadInt (file)
        facCategory(n,i)= ReadInt (file)
        facType(n,i)= ReadInt (file)
        facDelay(n,i)= ReadInt (file)
        facDeadEvent(n,i)= ReadInt (file)
        facWaitEvent(n,i)= ReadInt (file)
        facChunk(n,i)= ReadInt (file)
        facSound(n,i)= ReadInt (file)
        facVar1(n,i) = ReadInt (file)
        facVar2(n,i) = ReadInt (file)
        facVar3(n,i) = ReadInt (file)
        facVar4(n,i) = ReadInt (file)
        facVar5(n,i) = ReadInt (file)
        
    Next
Next

;-------END----------

CloseFile file
;--------

gfxDir$="gfx\tiles\"
;For bg=0 To bgAmount   
;    For i=1 To tileAmount(bg)
;        If tileBmp(tileSetNumber(bg,i),tileNumber(bg,i)) = 0 Then
;            tileBmp(tileSetNumber(bg,i),tileNumber(bg,i)) = LoadImage(gfxDir$ + tileSetNumber(bg,i) +"_" + tileNumber(bg,i) + ".bmp" )
;        EndIf
;        tilePic(bg,i)=tileBmp(tileSetNumber(bg,i),tileNumber(bg,i))
;    Next
;Next

For b=0 To bgAmount   
    For i=1 To tileAmount(b)
        
        If i > maxTileBG Then RuntimeError "i= "+i
        If b > 5 Then RuntimeError "b= "+b
        ;If tileSetNumber(b,i) > 5 Then tileSetNumber(b,i)=0 ;RuntimeError "tsn= "+tileSetNumber(b,i)
        If tileNumber(b,i) > 500 Then RuntimeError "tn= "+tileNumber(b,i)
        
        If tileBmp(tileSetNumber(b,i),tileNumber(b,i)) = 0 Then
            tileBmp(tileSetNumber(b,i),tileNumber(b,i)) = LoadImage(gfxDir$ + tileSetNumber(b,i) +"_" + tileNumber(b,i) + ".bmp" )
        EndIf
        tilePic(b,i)=tileBmp(tileSetNumber(b,i),tileNumber(b,i))
    Next
Next

For b=0 To bgAmount     
    For i=1 To tileAmount(b)
        tileFixed(b,i)=1
    Next
Next

;fixes possible bug when numbers load as 2.4534e+56 etc...
For i=1 To boxAmount
    If xbox(i) > 100000 Then xbox(i)=100
    If ybox(i) > 100000 Then ybox(i)=100
    If boxXspeed(i) > 10000 Then boxXspeed(i)=1    
    If boxYspeed(i) > 10000 Then boxYspeed(i)=1
    
Next


End Function
;------------------------ Save all maps -----------------------------------
Function SaveAll(nu)

setbuffer backbuffer()
For curMap=1 To nu
cls
    loadMap
    saveMap
    ma=ma+1
    text 10,40,"saved-> "+curMap
flip
Next

RuntimeError  "Operation Sucessful! "+ma+" map(s) saved!"
End

End Function
;---------- mod/level menu -----------
Function initMenu()

modSelected=1
Repeat
    
    If KeyHit(200) Then        ;if pressed UP
        modSelected=modSelected-1
        If modSelected < 1 Then modSelected=modsAmount
    EndIf
    If KeyHit(208) Then        ;if pressed DOWN
        modSelected=modSelected+1
        If modSelected > modsAmount Then modSelected=1
    EndIf
    If KeyHit(1) Then End;if pressed ESC then end program

    ;render level mod menu
    Cls
    x=5 y=25 w=200 h=25
    Text 4,2, "Press UP/DOWN and then ENTER to select a mod folder"
    For n = 1 To modsAmount
        If modSelected = n Then
            Color 100,0,0
            Rect x, y+1, w, h-1, 1
        EndIf
        Color 155,155,155
        Rect x, y, w, h, 0

        Color 255,255,255
        Text x+5,y+5, Upper(modName(n))
        y=y+h
        
    Next
    
    Delay 20
    Flip
Until KeyHit(28)
mapsdir$ = modFolder$(modSelected)
Cls
Color 255,255,255
FlushKeys()

End Function
;------- Return true/false -------------------------
Function val$(value)
    If value = 1 Then Return "true"
    If value = 0 Then Return "false"
    Return value
End Function
;------------------------- Load mods directories ------------------------------
Function setModDirs()

; Define what folder to start with ...
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

Function AdjustScreen()
    xOrigin=-32:yOrigin=-569
    xFactor=0:yFactor=0
    
    For n=1 To platAmount
        xFactor = 1.6 * ((xPlat(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yPlat(n) - yOrigin) / 2.7)
            
        xPlat(n)=xPlat(n) + xFactor
        platWidth(n)=platWidth(n) * 1.6
        yPlat(n)=yPlat(n) + yFactor
        platHeight(n)=platHeight(n) * 1.6
        
        For i= 1 To platPointsAmount(n)
            xFactor = 1.6 * ((xPlatPoint(n,i) - xOrigin) / 2.7)
            yFactor = 1.6 * ((yPlatPoint(n,i) - yOrigin) / 2.7)
            xPlatPoint(n,i)=xPlatPoint(n,i) + xFactor
            yPlatPoint(n,i)=yPlatPoint(n,i) + yFactor
        Next
        
        platXSpeed#(n)=platXSpeed#(n) * 1.6
        platYSpeed#(n)=platYSpeed#(n) * 1.6

        xFactor = 1.6 * ((xplatDest(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yplatDest(n) - yOrigin) / 2.7)
        xplatDest(n)=xplatDest(n) + xFactor
        yplatDest(n)=yplatDest(n) + yFactor
    Next
    
    For n=0 To bgAmount
        For m=1 To tileAmount(n)
            xFactor = 1.6 * ((xTile(n,m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((yTile(n,m) - yOrigin) / 2.7)
            
            xTile(n,m)=xTile(n,m) + xFactor
            yTile(n,m)=yTile(n,m) + yFactor
            
            tty = yplat(tileTarget(n,m))
            
            xTile2(n,m)=(xTile2(n,m) * 1.595)
            yTile2(n,m)=(yTile2(n,m) * 1.595)
            
            xFactor = 1.6 * ((tileXstart(n,m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((tileYstart(n,m) - yOrigin) / 2.7)
            tileXstart(n,m)=tileXstart(n,m) + xFactor
            tileYstart(n,m)=tileYstart(n,m) + yFactor
            
            xFactor = 1.6 * ((tileXend1(n,m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((tileYend1(n,m) - yOrigin) / 2.7)
            tileXend1(n,m)=tileXend1(n,m) + xFactor
            tileYend1(n,m)=tileYend1(n,m) + yFactor
            
            xFactor = 1.6 * ((tileXend2(n,m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((tileYend2(n,m) - yOrigin) / 2.7)
            tileXend2(n,m)=tileXend2(n,m) + xFactor
            tileYend2(n,m)=tileYend2(n,m) + yFactor
            
            xFactor = 1.6 * ((tileXrand1(n,m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((tileYrand1(n,m) - yOrigin) / 2.7)
            tileXrand1(n,m)=tileXrand1(n,m) + xFactor
            tileYrand1(n,m)=tileYrand1(n,m) + yFactor
            
            xFactor = 1.6 * ((tileXrand2(n,m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((tileYrand2(n,m) - yOrigin) / 2.7)
            tileXrand2(n,m)=tileXrand2(n,m) + xFactor
            tileYrand2(n,m)=tileYrand2(n,m) + yFactor
            
            tileXspeed#(n,m)=tileXspeed#(n,m) * 1.6
            tileYspeed#(n,m)=tileYspeed#(n,m) * 1.6
            
            If (xTileScrSpeed#(n,m) > 0) Then
                xFactor = 1.6 * ((xTileScrSpeed(n,m) - xOrigin) / 2.7)
                xTileScrSpeed#(n,m)=xTileScrSpeed#(n,m) + xFactor
            End If
            
            If (yTileScrSpeed#(n,m) > 0) Then
                yFactor = 1.6 * ((yTileScrSpeed(n,m) - yOrigin) / 2.7)
                yTileScrSpeed#(n,m)=yTileScrSpeed#(n,m) + yFactor
            End If
        Next
    Next
    
    For n=1 To boxAmount
        xFactor = 1.6 * ((xBox(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yBox(n) - yOrigin) / 2.7)
        
        xBox(n)=xBox(n) + xFactor
        BoxWidth(n)=BoxWidth(n) * 1.6
        yBox(n)=yBox(n) + yFactor
        BoxHeight(n)=BoxHeight(n) * 1.6
        
        boxXspeed(n)=boxXspeed(n) * 1.6
        boxYspeed(n)=boxYspeed(n) * 1.6
        boxHitSpeed(n)=boxHitSpeed(n) * 1.6
        boxHitYSpeed(n)=boxHitYSpeed(n) * 1.6
        
        xFactor = 1.6 * ((xboxDest(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yboxDest(n) - yOrigin) / 2.7)
        xboxDest(n) = xboxDest(n) + xFactor
        yboxDest(n) = yboxDest(n) + yFactor
        
        For i=1 To boxPointsAmount(n)
            xFactor = 1.6 * ((xboxpoint(n,i) - xOrigin) / 2.7)
            yFactor = 1.6 * ((yboxpoint(n,i) - yOrigin) / 2.7)
            
            xboxpoint(n,i) = xboxpoint(n,i) + xFactor
            yboxpoint(n,i) = yboxpoint(n,i) + yFactor
        Next
    Next
    
    For n=1 To triggerAmount
        xFactor = 1.6 * ((Tx(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((Ty(n) - yOrigin) / 2.7)
        
        Tx(n)=Tx(n) + xFactor
        Tw(n)=Tw(n) * 1.6
        Ty(n)=Ty(n) + yFactor
        Th(n)=Th(n) * 1.6
        
        Tplatx(n)=Tplatx(n) * 1.6
        Tplaty(n)=Tplaty(n) * 1.6
        
        TimgX(n)=TimgX(n) * 1.6
        TimgY(n)=TimgY(n) * 1.6
    Next
    
    For n=1 To FAmount
        For m=1 To FfacAmount(n)
            xFactor = 1.6 * ((xfac(n, m) - xOrigin) / 2.7)
            yFactor = 1.6 * ((yfac(n, m) - yOrigin) / 2.7)
        
            xfac(n, m)=xfac(n, m) + xFactor
            yfac(n, m)=yfac(n, m) + yFactor
        Next
    Next
    
    For n=1 To 4
        xFactor = 1.6 * ((xFlag(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yFlag(n) - yOrigin) / 2.7)
        
        xFlag(n)=xFlag(n) + xFactor
        yFlag(n)=yFlag(n) + yFactor
        
        xFactor = 1.6 * ((xFlagStart(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yFlagStart(n) - yOrigin) / 2.7)
        
        xFlagStart(n)=xFlagStart(n) + xFactor
        yFlagStart(n)=yFlagStart(n) + yFactor
        
        xFactor = 1.6 * ((xBase(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yBase(n) - yOrigin) / 2.7)
        
        xBase(n)=xBase(n) + xFactor
        yBase(n)=yBase(n) + yFactor
    Next
    
    For n=1 To dareaAmount
        xFactor = 1.6 * ((dax(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((day(n) - yOrigin) / 2.7)
        
        dax(n)=dax(n) + xFactor
        daw(n)=daw(n) * 1.6
        day(n)=day(n) + yFactor
        daH(n)=daH(n) * 1.6
        
        daTargetH(n)=daTargetH(n) * 1.6
    Next
    
    For n=1 To areaAmount
        xFactor = 1.6 * ((sax(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((say(n) - yOrigin) / 2.7)
        
        sax(n)=sax(n) + xFactor
        saw(n)=saw(n) * 1.6
        say(n)=say(n) + yFactor
        saH(n)=saH(n) * 1.6
    Next
    
    xFactor = 1.6 * ((xScrStart - xOrigin) / 2.7)
    yFactor = 1.6 * ((yScrStart - yOrigin) / 2.7)
    xScrStart=xScrStart + xFactor
    yScrStart=yScrStart + yFactor
    
    For n=1 To pawnAmount
        xFactor = 1.6 * ((zxStart(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((zyStart(n) - yOrigin) / 2.7)
        
        zxStart(n)=zxStart(n) + xFactor
        zyStart(n)=zyStart(n) + yFactor
        
        xFactor = 1.6 * ((zxRespawn(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((zyRespawn(n) - yOrigin) / 2.7)
        zxRespawn(n)=zxRespawn(n) + xFactor
        zyRespawn(n)=zyRespawn(n) + yFactor
    Next
    
    For n=1 To 10
        xFactor = 1.6 * ((mx(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((my(n) - yOrigin) / 2.7)
        
        mx(n)=mx(n) + xFactor
        my(n)=my(n) + yFactor
        
        mw(n)=mw(n) * 1.6
        my(n)=my(n) * 1.6
    Next
    
    rScrLimit = rScrLimit * 1.6
    dScrLimit = dScrLimit * 1.6
    yScrCameraLimit = yScrCameraLimit * 2.48
    lScrLimit = lScrLimit + 14
    
    For n=1 To wallAmount
        xFactor = 1.6 * ((xWall(n) - xOrigin) / 2.7)
        yFactor = 1.6 * ((yWall(n) - yOrigin) / 2.7)
        xWall(n)=xWall(n) + xFactor
        yWall(n)=yWall(n) + yFactor
        
        wallWidth(n)=wallWidth(n) * 1.6
        wallHeight(n)=wallHeight(n) * 1.6
    Next
End Function