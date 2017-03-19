Global windowMode, videoColorDepth, curWindowMode
Global curIdiom, gameSound, gameMusic
Global modsAmount, curModId

If loadConfig() = False Then
	gameSound=1
	gameMusic=1
	curIdiom=1
	videoColorDepth=16
	curModId=1
EndIf

If CommandLine$() = "-nosound" Then
	gameSound = 0
	gameMusic = 0
EndIf

curWindowMode = windowMode
If windowMode = 0 Then
	Graphics 640,480,videoColorDepth,1  ;full screen
Else
	Graphics 640,480,videoColorDepth,2  ;window
EndIf

Const gameVersion$ = "0.96"

;------------- listed version 0.96 -------------------
;mod selection screen bug solved
;unlocking the rights things even of loaded mod isn't original one

;------------- listed version 0.95 -------------------
;Goku can't teleport through thick walls anymore
;disappearing item when using axe(belmont) bug fixed
;belmont's axe can't be used as item anymore to hit switches
;Game level editor included

;------------- listed version 0.94 -------------------
;items bounce on landing
;Richter Belmont added
;option to turn items on/off on versus mode


AppTitle "MultiHero"
SeedRnd (MilliSecs())

Global message, messageN, mapN
Global maxAmap = 100
Global lastAmap = 50    ;beat this map to beat the game
Global maxVsMap = 20
Global maxCTFMap = 20
Global totalSecrets, noAirStrike
Dim tutorial(10)
Dim credits$(100), ySpace(100),yCredit(100)
Dim mapOpen(200), mapSecret(200), vsMapOpen(200), CTFmapOpen(200),open(200)
Dim cheat(20),cheatSeq(20)
	
Global choosemap,gameLives, map, map_,backg,title,curMap,sndStr$,loadOnce,Tn,strWarning$,Warning,WarnSeq, mapRestart
Global buttonAmount,gmStr$,gamestart,mapAmount,lastgamemode,butNA,butHum,butCPU, mapComplete, secretsFound,secretsAmount
Global fontType=1, fontSpace=1, previousMap, screenShotN
Global zFreezer
Dim wolverineRage(30)
Dim NextMap(5)
Dim butOn(100),xBut(100),yBut(100),wbut(100),hBut(100),clickedBut(100),lastBut(100)
Dim clickedBy(100),butPic(100),butPic2(100),butText$(100),butSeq(100),tpic(100)
Dim xPointer(10),yPointer(10),zName$(14),zThumbNail(14),mapTn(100)

Dim tempN#(10), strinfo$(200), characterOpen(30)
Dim zx#(30),zy#(30),zdi(30),zface(30),zoldx#(30),zoldy#(30),zWasOn(30),zon(30),prevZOn(30),CurGuy(30),lastZon(30),lastzAI(30)
Dim zxStart(30),zyStart(30),zxRespawn(30),zyRespawn(30),zjump2(30),zjump2seq(30),zFallDir(30),zDeadEvent(30)
Dim zlife(30),zhit(30),zhitseq(30),Zshield(30),zTempShield(30),Zshieldseq(30),ZshieldedTime(30),zHit2(30)
Dim zjump(30),zjumpseq(30),zjumplimit(30),zongnd(30),zfallenSeq(30),zFallImpact#(30),zFallSpeed#(30)
Dim zFallTime#(30),zUpFallTime#(30), zUpFallSpeed#(30),zDamage#(30),zBouncedgnd(30),zGotHitsAmount(30)
Dim zHitSpeed#(30),zHitUpSpeed#(30),zHitTime#(30),zHitMode(30),zHitModeTaken(30),zBlowUplimit(30)			
Dim zUpHeight(30),zDuckHeight(30),z(30),zHitHead(30),zIcon(60),zRollOnImpact(30)
Dim zheight(30),zduck(30),zgravity(30),zSpeed#(30),Zside(30), zxHand(30,20),zyHand(30,20)
Dim Zrun(30), zCurWeapon(30),dangerMove9(30),dangerMove5(30),zGotObj(30), zLeftCollide(30), zRightCollide(30)
Dim zLives(30), zJumping(30),zonplat(30),zonThickPlat(30),justMovedByplat(30)
Dim zantiPlatTime(30),zantiPlatSeq(30),zForceAntiPlat(30)
Dim zantiPlat(30),zHitByBox(30),zChunkType(30),zAI(30),aiTarget(30),aiCurLevel(30),zblowalert(30)
Dim aiWalk(30),aiLevel(30),zBlowDist(30,30),NextBlow(30),blockDist(30)
Dim zTrail(30),zTrailSeq(30),zTrailTime(30),zTrailType(30),zHitHold(30),zBlowHold(30),zHitCount(30)
Dim xDist(30),yDist(30), zBlowTrailType(30), zBlowHit(30), zJumpSnd(30), zJumpSnd2(30)
Dim zSuperMove(30),zSuperMoveSeq(30),zSuperX(30),zSuperY(30),zSuperDir(30),zSuperBar(30)
Dim zGrabbed(30),zGrabbedBy(30),zGrabs(30),zGrabsThis(30),zGrabSeq(30),zNoAirSpecial(30)
Dim xOval(30),yOval(30),woval(30),hOval(30), zGrabDist(30),shotFireSound(30)
Dim zWalkAni(30),zCurPic(30),zBlowSound(30),zani(30),zf(30),zPrevAni(30),zPrevf(30),zDontPickItem(30),zFlyAni(30),zfa(30)
Dim zWalkSeq(30), startFreezeTime(30), currentFreezeTime(30), endFreezeTime(30), canGetTime(30), freezeSeq(30), canMakeShot(30)
Dim rageSeq(30), startRageTime(30), currentRageTime(30), endRageTime(30), canGetRageTime(30), wolvSpdFctr(30)
Dim zShotByN(30),zShotHitSeq(30,200), zDontJump(30),zDeathChunk(30),zStone(30),zUngrabable(30)
Dim yRange(30), zMyShot(30), zUseSpecialAI(30), zCanFly(30), zPushedForce(30),zShootThis(30)
Dim zLetGoSeq(30), zLetGoAmount(30),zHelper(30), zFlySeq(30), zWalkSeq2(30), zBeenHere(30)

Dim aiJumpedRand(30),pointerPic(30),drawObjOnZ(30)
Dim upK(30),leftK(30),rightK(30),downK(30),shotK(30),specialK(30),jumpK(30),blockK(30),grabK(30)
Dim zMoveSide(30),xblow(30,50),yblow(30,50),wblow(30,50),hblow(30,50),zBlowEffect(30),zBlowStillSeq(30),zBlowStillTime(30)
Dim zxStill#(30),zyStill(30),zBlowdir(30),zNoJump(30),zNoMove(30),zNoGrav(30),zblowPamount(30),zBlowBlockTime(30),zFrozen(30)
Dim zImuneTo(30,30),zImuneSeq(30,30),zImuneTime(30,30),zImune(30,30)
Dim hitKey(30),upKey(30),downKey(30),leftKey(30),rightKey(30),shotKey(30),jumpKey(30),jumpKeyDown(30),blockKey(30)
Dim rightKeyHit(30),leftKeyHit(30),specialkey(30),zController(30),controllerPort(30),grabKey(30),superKey(30),counterkey(30),extraSpecialkey(30)
Dim runkey(30),zDacc#(30),zAcc#(30),zTopSpeed#(30),zDtopSpeed#(30)

Dim zpic(200,50,50),zpic_(200,50,50)
Dim guyLoaded(100)

Dim extraDraw(30),extraPic(30), xED(30),yED(30), epic(20,20), epic_(20,20),eAni(20),ef(20)
Global quake, quakeSeq,mapsLoaded,xScr,yScr,xScr_,yScr_,scrollMap,scrollXspeed#, scrollYspeed#, scrLock
Global fightMode, xScrStart, yScrStart, noAirSpecial, noDoubleJump, fileBkp, noItems=1, xAxis, yAxis

Global Famount
Dim curF(100),FdelaySeq(100),Fevent(100),FfacAmount(100),Floop(100)
Dim xfac(100,100),yfac(100,100), facdir(100,100), facLife(100,100),facLives(100,100),facDamage(100,100),facAiLevel(100,100)
Dim facTeam(100,100),facCategory(100,100), facType(100,100), facDelay(100,100), facDeadEvent(100,100),facWaitEvent(100,100)
Dim facChunk(100,100),facSound(100,100),facVar1(100,100),facVar2(100,100),facVar3(100,100),facVar4(100,100),facVar5(100,100)
Dim zVar1(30),zVar2(30),zVar3(30),zVar4(30),zVar5(30)
Dim Fon(100)

Global curAni,tAniAmount,taniMEnu
Dim tAniSeq(200),tAniFrames(200)
Dim tAniBg(200,200),tAniN(200,200),tAniTime(200,200),tAniCurFrame(200)
Dim taniBgSel(200),taniNSel(200)

;sa= safe Area For AI
;da= danger/jumping area For AI
Dim saX(50),saY(50),saW(50),saH(50),saX2(50),saY2(50)
Dim saLLimit(50),saRlimit(50),dangerArea(50),fleeDir(50),edges(50),samovedBy(50)
Dim saAN(50),daAN(50)
Dim daX2(50),daY2(50),daX(50),daY(50),daW(50),daH(50),daType(50) ;type 1=danger , 2=jump
Dim daLLimit(50),daRlimit(50),dfleeDir(50),daTargetH(50),damovedBy(50)
Global areaMoves, saAreaMovesAmount,daAreaMovesAmount

Global rectAmount
Dim rectHit(50),zGotRect(30),rectOwner(50),xRect(50),yRect(50),rectDir(50),wRect(50),hRect(50),rectSide(50)
Dim	RectHitMode(50),rectDamage(50),rectHitHold(50),rectXHitSpeed(50),rectYHitSpeed(50)
Dim	rectChunkType(50),rectHitSound(50),zHitByRect(50)

Dim shotsfired(200),zShotLimit(200),zAmmo(200),shotDraw(200),shotUseAcc(200),shotHold(200),shotTrailType(200)
Dim shotHitXspeed(200),shotHitYspeed#(200),shotFallTime(200),shotHitMode(200),oldxShot(200), shotExplosive(200)
Dim xshot(200),yshot(200),shot(200),shotDir(200),shotowner(200), shotspeed#(200),shotSound(200)
Dim shotdamage(200),shotsize(200),shotsizeL(200),shotPic(200,10),shotPic_(200,10),shotImpact(200)
Dim shotImage(51), shotImage_(51), shotHitTrail(200), shotSuper(200), shotBounce(200),shotExplosionSound(200)
Dim shotHeight(200),shotWidth(200),shotside(200),shotChunkType(200), shotType(200), shotPushForce(200)
Dim justShot(200),shotSeq(200),shotDuration(200),shotDurationSeq(200), shotDrill(200),shotDuration2(200)
Dim shotAcc#(200),shotMaxSpeed#(200),shotUturn(200),shotReturnOnHit(200), shotFollowOwner(200),shotUturnseq(200)
Dim shotFramesAmount(200), shotCurFrame(200), shotFrameSeq(200), shotFrameTime(200),shotImmuneTime(200),shotUturnAmount(200)

Dim ObjType(400),objThrow(400),objHurt(400),objId(400),objHitSound(400)
Dim xobj#(400),yobj#(400),obj(400),objdir(400),objowner(400), objXspeed#(400),objYSpeed#(400),objAmmo(400)
Dim objdamage(400),objsize(400),objsizeL(400),objPic(100,10),objPic_(100,10),objImpact(400),objTaken(400),objWithZ(400)
Dim objHeight(400),objSide(400),objLife(400),objExplosive(400),objHit(400),objYForce(400),objTypeOut(400)
Dim objHitChunk(400),objNoGrav(400), objTrailType(400), objSuper(400), objExplosionSound(400)
Dim objHitMode(400),objHitXspeed(400),objHitYspeed(400),objFallTime(400),objEat(400),xobjHand(400),yObjHand(400)
Dim objCurFrame(400),objFrameTime(400), objFrameAmount(400), objFrameSeq(400), objZMade(400)
Dim objBounce(400)

Dim xIten(400),yIten(400),itenOn(400),itenOwner(400),itenPic(400),itenType(400),beatIten(400),beatITenType(400)

Dim xWall(1500),yWall(1500),wallWidth(1500),wallHeight(1500),xTileScrSpeed#(5,1500),yTileScrSpeed#(5,1500)
Dim xTile#(5,1500),yTile#(5,1500),tilePic(5,1500),tileAmount(5),tileBmp(20,1500),tileNumber(5,1500),tileSetNumber(5,1500)
Dim tileXend1(5,1500),tileXend2(5,1500), tileXrand1(5,1500), tileXrand2(5,1500), tileXspeed#(5,1500)
Dim tileYend1(5,1500),tileYend2(5,1500), tileYrand1(5,1500), tileYrand2(5,1500), tileYspeed#(5,1500)
Dim tileXstart(5,1500), tileYstart(5,1500),tileFollow(5,1500),tileTarget(5,1500)
Dim xTile2(5,1500),yTile2(5,1500),tileFollowType(5,1500)
Global colorR,colorG,colorB,bg,bgAmount=5

Dim zBlowBack(30),zBlow(30),zCurBlow(30),zBlowSeq(30),zBlowSeq2(30),zBlowDamage(30),zBlowImpact(30),zBlowStill(30)
Dim zBlock(30),zBlocked(30),zBlockDir(30),zBlockSpeed#(30),zBlockSeq(30),zBlockTime(30),zBlockLife#(30),zblockfull(30)
Dim zShot1Pic(30),zShot2Pic(30),zShot1Pic_(30),zShot2Pic_(30)

Dim xplatDraw(200),yPlatDraw(200),platFinalDest(200), platBreak(200), platChunk(200)
Dim xoldPlat#(200),xplat#(200),yPlat#(200),xPlatPoint(200,200),yPlatPoint(200,200),platXSpeed#(200),platYSpeed#(200),platPointsAmount(200)
Dim drawPlat(200),dangerPlat(200),zOnPlatN(200),platHeight(200),platUseTrigger(200), platEventN(200),platSpecial(200)
Dim platCurPoint(200),platXDir(200),platYDir(200),platWidth(200),platOn(200),xplatdest(200),yplatDest(200),platPic(200)
Dim platSound(200),platBreakable(200),platEventN2(200), platReachedDest(200)

Dim xoldbox#(200),xbox#(200),ybox#(200),xBoxPoint(200,200),yBoxPoint(200,200),boxXSpeed#(200),boxYSpeed#(200),boxPointsAmount(200)
Dim boxCurPoint(200),boxXDir(200),boxYDir(200),boxWidth(200),boxOn(200),xboxdest(200),yboxDest(200),boxPic(200),boxHarmless(200)
Dim boxheight(200),boxDamage(200),boxAni(200),boxAniTime(200),BoxType(200),box(200),targetBox(200),boxHitSound(200)
Dim boxhitmode(200),boxhitTime#(200),boxHitSpeed#(200),boxHitYspeed#(200),boxChunkType(200),drawBox(200),boxBreak(200)
Dim boxUseTrigger(200),boxEventN(200),boxFinalDest(200),boxSound(200),boxBreakable(200), boxEventN2(200),boxReachedDest(200)

Dim Tx(100),Ty(100),Tw(100),Th(100),Tway(100),Tevent(100),Ton(100),Tdraw(100),TimageN(100),Tonce(100)
Dim TpassBy(100), TzAction(100), TobjHit(100),EventN(100),Timage(100,5),TimgX(100),TimgY(100),Taffect(100)
Dim TonStatus(100),ToffStatus(100),Tplatx(100),Tplaty(100),Tfollow(100)
Dim EventAction(100),Tsound(100)
Global triggerAmount,triggerMode, triggerImageAmount,amountAffected

Dim xChunk(1500),yChunk(1500),chunk(1500),chunkType(1500),chunkSeq(1500),chunkCategory(1500),chunkHeight(1500),chunkStr$(1500,20)
Dim chunkPic(1500),chunkPic_(1500),chunkDir(1500),ptPic(1500,10),ptPic_(1500,10),chunkColor(1500),chunkWidth(1500),chunkLines(1500)
Dim chunkOwner(1500)

Dim explosion(100),xExp(100),yExp(100),expDamage(100), expSide(100),expHeight(100),expImpact(100),expType(100),explosionSound(100)

Dim xFlag(4),yFlag(4), flagOwner(4),flagCarried(4),flagOnbase(4)
Dim xFlagStart(4),yFlagStart(4)
Dim xBase(4),yBase(4),zTeam(30),zTeamBefore(30),TeamScore(30),zScore(30),zTargetScore(30)
Dim flagPic(4)

Dim zFlagTime#(30)

Global zzamount,zamountPlaying,flagAmount,itenAmount, shotamount, objAmount,chunkAmount,platAmount,boxAmount,expAmount
Global wallAmount,gameDone, aliveAmountNeeded, prevZAmountPlaying,prevZzamount
Global twoPlayersKeyb,bgColor,maxScore,ScoreDone,showVidMem,level,renderdelay,showBlowArea
Global xTileImg#,yTileImg#,winner,flagMaxTime,showZColor,teamAttack,gameMode, vsMode 
Global gamePaused,b_joyhit,timePassed#, keypressed, keyschosen,pn,ifiniteLives,flagMaxScore,targetMaxScore
Global endGame,gameTime,gameTime2,NoUserInput,tarN,areaAmount,dAreaAmount,objFrequency, alwaysSpawnObj
Global rScrLimit=1400,lScrLimit=-760,uScrLimit=-50000,dScrLimit=540, yScrCameraBottomLimit
Global rendert, renderFreq, maxObjAmount
Global characterAmount=14	;Add character, 1=ryu, 2=rash ... change the value from 10 to 11, 11=your new character id
Global menuOption, duringGameMenu

;zeto's variables
Dim zStanceFrames(30), zStanceSeq(30), zStanceSpeed(30), zWalkFrames(30), zWalkFrameSpeed#(30), deathSnd(60)
Dim rightKeyHitTimer(30), leftKeyHitTimer(30)
Dim isRunning(30), zTopRunningSpeed#(30), zRunSeq(30), zRunFrames(30), zRunFrameSpeed#(30), zRunGruntSound(30)
Dim zStaminaBar#(30), zRunFootSound(30), zCharSpeed#(30)

;Paths For directories / mods
Dim modFolder$(500), modName$(500)
Global mapsBaseDir$="maps\"

modName(1) = "original"
Global gfxStuffDir$="gfx\stuff\"
Global gfxdir$="gfx\stuff\"
Global soundsdir$="sounds\"

;Find all mod directories and set their paths/name 
setModDirs()
If curModId = 0 Then curModId = 1
If curModId > modsAmount Then curModId = 1

pointerPic(1)=LoadImage(gfxdir$ + "p1.bmp")
pointerPic(2)=LoadImage(gfxdir$ + "p2.bmp")
pointerPic(3)=LoadImage(gfxdir$ + "p3.bmp")
pointerPic(4)=LoadImage(gfxdir$ + "p4.bmp")
Global arrow1=LoadImage(gfxdir$ + "arrow1.bmp")
Global arrow2=LoadImage(gfxdir$ + "arrow2.bmp")
Global greenSign=LoadImage(gfxdir$ + "sign.bmp")
Global pad=LoadImage(gfxdir$ + "pad.bmp")
Global board=LoadImage(gfxdir$ + "board.bmp")
Global board2=LoadImage(gfxdir$ + "board2.bmp")
Global board3=LoadImage(gfxdir$ + "board3.bmp")
Global lock=LoadImage(gfxdir$ + "lock.bmp")
Global noPic=LoadImage(gfxdir$ + "no.bmp")

Global frameTimer = CreateTimer(54) ;define FPS game will run

For i=1 To 20
	Timage(i,1)=LoadImage(gfxdir$ + "trig" + i + "_a1.bmp")
	Timage(i,2)=LoadImage(gfxdir$ + "trig" + i + "_a2.bmp")
Next
Dim platImage(20)
For i=1 To 20
	platImage(i)=LoadImage(gfxdir$ + "plat" + i + ".bmp")
Next
Dim boxImage(20,5)
For i=1 To 20
	For ii=1 To 5
		boxImage(i,ii)=LoadImage(gfxdir$ + "box" + i + "_a" + ii + ".bmp")
	Next
Next

gfxdir$="gfx\"
For i=1 To characterAmount
	zIcon(i)=LoadImage(gfxdir$ + i + "\zIcon.bmp")
Next

gfxdir$="gfx\stuff\"



;**** Objects ******
;Loads ciment club images
epic(1,1)=LoadImage(gfxdir$ + "obj\obj6_1.bmp")
epic(1,2)=LoadImage(gfxdir$ + "obj\obj6_2.bmp")
epic(1,3)=LoadImage(gfxdir$ + "obj\obj6_3.bmp")
epic(1,4)=LoadImage(gfxdir$ + "obj\obj6_4.bmp")
epic_(1,1)=LoadImage(gfxdir$ + "obj\obj6_1_.bmp")
epic_(1,2)=LoadImage(gfxdir$ + "obj\obj6_2_.bmp")
epic_(1,3)=LoadImage(gfxdir$ + "obj\obj6_3_.bmp")
epic_(1,4)=LoadImage(gfxdir$ + "obj\obj6_4_.bmp")

;Loads gun images
epic(2,1)=LoadImage(gfxdir$ + "obj\obj7_1.bmp")	
epic(2,2)=LoadImage(gfxdir$ + "obj\obj7_2.bmp")
epic_(2,1)=LoadImage(gfxdir$ + "obj\obj7_1_.bmp")
epic_(2,2)=LoadImage(gfxdir$ + "obj\obj7_2_.bmp")

;Loads bazooka images
epic(3,1)=LoadImage(gfxdir$ + "obj\obj10_1.bmp")	
epic(3,2)=LoadImage(gfxdir$ + "obj\obj10_2.bmp")
epic_(3,1)=LoadImage(gfxdir$ + "obj\obj10_1_.bmp")
epic_(3,2)=LoadImage(gfxdir$ + "obj\obj10_2_.bmp")

;Loads ray gun images
epic(4,1)=LoadImage(gfxdir$ + "obj\obj11_1.bmp")	
epic(4,2)=LoadImage(gfxdir$ + "obj\obj11_2.bmp")
epic_(4,1)=LoadImage(gfxdir$ + "obj\obj11_1_.bmp")
epic_(4,2)=LoadImage(gfxdir$ + "obj\obj11_2_.bmp")

;Loads hammer images
epic(5,1)=LoadImage(gfxdir$ + "obj\obj13_1.bmp")	
epic(5,2)=LoadImage(gfxdir$ + "obj\obj13_2.bmp")
epic(5,3)=LoadImage(gfxdir$ + "obj\obj13_3.bmp")
epic(5,4)=LoadImage(gfxdir$ + "obj\obj13_4.bmp")
epic_(5,1)=LoadImage(gfxdir$ + "obj\obj13_1_.bmp")
epic_(5,2)=LoadImage(gfxdir$ + "obj\obj13_2_.bmp")
epic_(5,3)=LoadImage(gfxdir$ + "obj\obj13_3_.bmp")
epic_(5,4)=LoadImage(gfxdir$ + "obj\obj13_4_.bmp")

;Loads dragon fire balls images
epic(6,1)=LoadImage(gfxdir$ + "obj\obj14_1.bmp")	
epic(6,2)=LoadImage(gfxdir$ + "obj\obj14_2.bmp")
epic_(6,1)=LoadImage(gfxdir$ + "obj\obj14_1_.bmp")
epic_(6,2)=LoadImage(gfxdir$ + "obj\obj14_2_.bmp")

;Loads flying bat
epic(7,1)=LoadImage(gfxdir$ + "obj\obj12_1.bmp")	
epic(7,2)=LoadImage(gfxdir$ + "obj\obj12_2.bmp")
epic_(7,1)=LoadImage(gfxdir$ + "obj\obj12_1_.bmp")
epic_(7,2)=LoadImage(gfxdir$ + "obj\obj12_2_.bmp")

;loads big rocks
epic(8,1)=LoadImage(gfxdir$ + "obj\obj16_1.bmp")	
epic(8,2)=LoadImage(gfxdir$ + "obj\obj16_2.bmp")
epic(8,3)=LoadImage(gfxdir$ + "obj\obj16_3.bmp")
epic(8,4)=LoadImage(gfxdir$ + "obj\obj16_4.bmp")

;loads little rocks
epic(9,1)=LoadImage(gfxdir$ + "obj\obj17_1.bmp")	
epic(9,2)=LoadImage(gfxdir$ + "obj\obj17_2.bmp")
epic(9,3)=LoadImage(gfxdir$ + "obj\obj17_3.bmp")
epic(9,4)=LoadImage(gfxdir$ + "obj\obj17_4.bmp")

;loads little lava rocks
epic(10,1)=LoadImage(gfxdir$ + "obj\obj18_1.bmp")	
epic(10,2)=LoadImage(gfxdir$ + "obj\obj18_2.bmp")
epic(10,3)=LoadImage(gfxdir$ + "obj\obj18_3.bmp")
epic(10,4)=LoadImage(gfxdir$ + "obj\obj18_4.bmp")

;Loads axe
For i=1 To 8
    epic(11,i)=LoadImage(gfxdir$ + "obj\obj19_"+i+".bmp")
    epic_(11,i)=LoadImage(gfxdir$ + "obj\obj19_"+i+"_.bmp")
Next


Global obj1P=LoadImage(gfxdir$ + "obj\obj1_1.bmp")
Global obj2P=LoadImage(gfxdir$ + "obj\obj2_1.bmp")
Global obj3P=LoadImage(gfxdir$ + "obj\obj3_1.bmp")
Global obj4p=LoadImage(gfxdir$ + "obj\obj4_1.bmp")
Global obj5p=LoadImage(gfxdir$ + "obj\obj5_1.bmp")
Global obj8p=LoadImage(gfxdir$ + "obj\obj8_1.bmp")
Global obj9p=LoadImage(gfxdir$ + "obj\obj9_1.bmp")
Global obj9p_=LoadImage(gfxdir$ + "obj\obj9_1_.bmp")

Global objArrow=LoadImage(gfxdir$ + "objArrow.bmp")
Global flag1P=LoadImage(gfxdir$ + "flag1.bmp")
Global flag2P=LoadImage(gfxdir$ + "flag2.bmp")
Global controllerPic=LoadImage(gfxdir$ + "controller.bmp")
Global keyboardPic=LoadImage(gfxdir$ + "keyboard.bmp")

For n=1 To 51   ;load shots
	shotImage(n)=LoadImage(gfxdir$ + "shot\shot"+n+".bmp")	
	shotImage_(n)=LoadImage(gfxdir$ + "shot\shot"+n+"_.bmp")
Next

For n=1 To 103   ; load chunks
	For nn=1 To 10
		ptPic(n,nn)=LoadImage( gfxdir$ + "part\pt"+n+"_a"+nn+ ".bmp" )
		ptPic_(n,nn)=LoadImage( gfxdir$ + "part\pt"+n+"_a"+nn+"_.bmp" )
	Next
Next

;Load Sounds
Global intro=LoadSound(soundsdir$ + "intro.mp3")
Global victorySnd=LoadSound(soundsdir$ + "victory.wav")
Global sDoorSnd=LoadSound(soundsdir$ + "sDoor.wav")
Global mDoorSnd=LoadSound(soundsdir$ + "mDoor.wav")
Global energySnd=LoadSound(soundsdir$ + "energy.wav")
Global batsSnd=LoadSound(soundsdir$ + "bats.wav")
Global dragonRoarSnd=LoadSound(soundsdir$ + "dragonRoar.wav")
Global bazookaSnd=LoadSound(soundsdir$ + "bazooka.wav")
Global shockSnd=LoadSound(soundsdir$ + "shock.wav")
Global RaySnd=LoadSound(soundsdir$ + "ray.wav")
Global flyBySnd=LoadSound(soundsdir$ + "flyBy.wav")
Global jokerSnd=LoadSound(soundsdir$ + "joker.wav")
Global predHitSnd=LoadSound(soundsdir$ + "predHit.wav")
Global PredatorRaySnd=LoadSound(soundsdir$ + "predatorRay.wav")
Global PredatorSnd=LoadSound(soundsdir$ + "predator.wav")
Global NoSnd=LoadSound(soundsdir$ + "NoSound.wav")
Global ReadySnd=LoadSound(soundsdir$ + "ready.wav")
Global FightSnd=LoadSound(soundsdir$ + "fight.wav")
Global clapSnd=LoadSound(soundsdir$ + "clap.wav")
Global ohclapSnd=LoadSound(soundsdir$ + "ohclap.wav")
Global eeeeSnd=LoadSound(soundsdir$ + "eeee.wav")
Global TucupSnd=LoadSound(soundsdir$ + "tucup.wav")
Global ThrowSnd=LoadSound(soundsdir$ + "Throw.wav")
Global gotShotSnd=LoadSound(soundsdir$ + "gotShot.wav")
Global vooSnd=LoadSound(soundsdir$ + "voo.wav")
Global smashSnd=LoadSound(soundsdir$ + "smash.wav")
Global blow2Snd=LoadSound(soundsdir$ + "blow2.wav")
Global bhitSnd=LoadSound(soundsdir$ + "bhit.wav")
Global CapeSnd=LoadSound(soundsdir$ + "cape.wav")
Global RashHitSnd=LoadSound(soundsdir$ + "RashHit.wav")
Global MarioUahaSnd=LoadSound(soundsdir$ + "uaha.wav")
Global MarioWeakSnd=LoadSound(soundsdir$ + "marioWeak.wav")
Global MarioFierceSnd=LoadSound(soundsdir$ + "marioFierce.wav")
Global ddHitSnd=LoadSound(soundsdir$ + "ddHit.wav")
Global shredderSnd=LoadSound(soundsdir$ + "shredder.wav")
Global shredder2Snd=LoadSound(soundsdir$ + "shredder2.wav")
Global mikePunchSnd=LoadSound(soundsdir$ + "mikePunch.wav")
Global mikeKickSnd=LoadSound(soundsdir$ + "mikeKick.wav")
Global mikeSnd=LoadSound(soundsdir$ + "mikeSnd.wav")
Global mikeUpperCutSnd=LoadSound(soundsdir$ + "mikeUpperCut.wav")
Global mikeFlipSnd=LoadSound(soundsdir$ + "mikeFlip.wav")
Global mikeBreathSnd=LoadSound(soundsdir$ + "mikeBreath.wav")
Global spiderstingsnd=LoadSound(soundsdir$ + "spidersting.wav")
Global huasnd=LoadSound(soundsdir$ + "hua.wav")
Global swordSnd=LoadSound(soundsdir$ + "sword.wav")
Global hayabusaSnd=LoadSound(soundsdir$ + "hayabusa.wav")
Global shurikenSnd=LoadSound(soundsdir$ + "shuriken.wav")
Global webshotsnd=LoadSound(soundsdir$ + "webshot.wav")
Global webhitsnd=LoadSound(soundsdir$ + "webhit.wav")
Global fireballsnd=LoadSound(soundsdir$ + "fireball.wav")
Global firehitsnd=LoadSound(soundsdir$ + "firehit.wav")
Global hiasnd=LoadSound(soundsdir$ + "hia.wav")
Global hiahuusnd=LoadSound(soundsdir$ + "hiahuu.wav")
Global coinsnd=LoadSound(soundsdir$ + "coin.wav")
Global hueSnd=LoadSound(soundsdir$ + "hue.wav")
Global uppercutsnd=LoadSound(soundsdir$ + "uppercut.wav")
Global mariouppercutsnd=LoadSound(soundsdir$ + "marioUppercut.wav")
Global brokensnd=LoadSound(soundsdir$ + "broken.wav")
Global blockedsnd=LoadSound(soundsdir$ + "blocked.wav")
Global kicksnd=LoadSound(soundsdir$ + "highkick.wav")
Global blowsnd=LoadSound(soundsdir$ + "highBlow.wav")
Global highpunchsnd=LoadSound(soundsdir$ + "highpunch.wav")
Global lasersnd=LoadSound(soundsdir$ + "laser.wav")
Global shotsnd=LoadSound(soundsdir$ + "shot.wav")
Global shotwallsnd=LoadSound(soundsdir$ + "shotwall.wav")
Global zhitwallsnd=LoadSound(soundsdir$ + "zhitwall.wav")
Global jumpsnd=LoadSound(soundsdir$ + "jump.wav")
Global floorSlideSnd=LoadSound(soundsDir$ + "floorSlide.wav")
Global ryuBallsnd
Global ryuSpinsnd
Global sonyaUpperSnd
Global sonyaBallSnd
Global sonyaSpinSnd
Global sonyaBreathSnd
Global sonyaFlameSnd
Global sonyaFlameHitSnd
Global sonyaSnd
Global brolyBallSnd
Global brolyBall2Snd
Global brolyBall3Snd
Global brolySnd

Global brolyPunchSnd
Global brolyPunchHitSnd

Global brolyHitSnd
Global brolyBigHitSnd
Global brolyLaughSnd
Global brolyTauntSnd
Global brolyOopsSnd
Global brolyKickSnd
Global brolyNHitSnd

Global whipSnd, crossSnd, richterSnd, fastThrowSnd
Global gokuSnd, goku1Snd, teleportSnd
Global dbzHitSnd=LoadSound(soundsdir$ + "dbzHit.wav")
Global explodesnd=LoadSound(soundsdir$ + "explode.wav")
Global clicksnd=LoadSound(soundsdir$ + "click.wav")
Global slashsnd=LoadSound(soundsdir$ + "slash.wav")
Global ctfSnd=LoadSound(soundsdir$ + "ctf.wav")
Global pickupSnd=LoadSound(soundsdir$ + "pickup.wav")
Global mkMaleGrunt1Snd=LoadSound(soundsdir$ + "mk\mkMaleGrunt1.mp3")
Global subZeroAirSnd
Global subZeroFreeze1Snd
Global subZeroFreeze2Snd
Global subZeroFreeze3Snd
Global subZeroHitSnd
Global subZeroIceBlastSnd
Global subZeroKickSnd
Global subZeroSlideKickSnd
Global subZeroLaughSnd
Global subZeroPunchSnd
Global subZeroPunch2Snd
Global subZeroThrowSnd
Global subZeroJumpSnd
Global subZeroJump2Snd
Global subZeroExcellentSnd
Global subZeroOutstandingSnd
Global subZeroSuperbSnd
Global subZeroStrongHitSnd
Global subZeroWelldoneSnd
Global subZeroWindSnd
Global wolverineJumpSnd
Global wolverineBarrageSnd
Global wolverineSlashSnd, wolverineSlash2Snd, wolverineSlash3Snd, wolverineSlash4Snd
Global wolverineTornadoClawSnd
Global wolverineShoutSnd, wolverineShout2Snd, wolverineShout3Snd, wolverineShout4Snd
Global wolverineKickSnd
Global wolverineSuper1Snd, wolverineSuper2Snd
Global wolverineGrabSnd
Global wolverineDrillClawSnd, wolverineDrillClawHitSnd
Global wolverineClawSnd, wolverineLetsGoSnd

Global mkFootstepSnd=LoadSound(soundsdir$ + "mk\mkFootstep.mp3")

Dim letter(20,100), letterWidth(20,100)	
;LOADS fonts

For t=1 To 2
  For n=0 To 53
	letter(t,n)=LoadImage("gfx\fonts\l" + t + "_" + n + ".bmp")
	If Not letter(t,n) = 0 Then letterWidth(t,n) = ImageWidth(letter(t,n))
  Next
Next

Dim soundFx(20)	;sound fx, obj list
soundFx(1)=slashSnd       	;sound list
soundFx(2)=highpunchsnd	
soundFx(3)=sDoorSnd
soundFx(4)=mDoorSnd
soundFx(5)=fireHitSnd
soundFx(6)=laserSnd
soundFx(7)=shotSnd
soundFx(8)=clickSnd
soundFx(9)=marioFierceSnd
soundFx(10)=batsSnd
soundFx(11)=shockSnd

Global menuMusic

Global justIntroduced
Global music
Global music2
Global chMusic
Global chMusic2
Global musicN1
Global musicN2

renderFreq=1
Global timestart#=MilliSecs()

Global gframe,fps
Global milli#
Global Nfps=52	;52 Number of frames per second the game should render
milli=1000 / Nfps
timePassed= MilliSecs() + milli

loadkeys()	;loads keys configuration from keys.cfg


butNA=LoadImage(gfxdir$ + "butNA.bmp")
butHum=LoadImage(gfxdir$ + "butHum.bmp")
butCPU=LoadImage(gfxdir$ + "butCPU.bmp")

Global objTypeN=19	;Amount of existing objects

Include "menus.bb"	;includes
Include "mh2.bb"
Include "moves1.bb"
Include "moves2.bb"

loadData()  ;Loads general maps data

oldModId = curModId
curModId=1	;Forces the mod id to be the original
loadMaps()	;Loads original maps data so it can check and unlock stuff based on the original levels
checkWhatsOpen()
curModId = oldModId
loadMaps()  ;Loads the maps from the last used mod 


idioms(curIdiom)

zzamount=4	;test
curMap=1
curGuy(1)=1:lastzon(1)=1:lastBut(60)=1
curGuy(2)=2:lastzon(2)=1:lastBut(61)=2:zai(2)=1:lastzAI(2)=1
curGuy(3)=3:lastzon(3)=0:
curGuy(4)=1:lastzon(4)=0:
n=5
curGuy(n)=1:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=2:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=3:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=4:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=5:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=6:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=7:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=8:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=1:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=2:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=3:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=4:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=5:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=6:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=7:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2
n=n+1
curGuy(n)=8:lastzon(n)=1lastzAI(n)=1:;zteam(n)=2


For n=5 To 30
	zai(n)=1
Next


gameLives=5
flagMaxScore=3
flagMaxTime=500
targetMaxScore=10
gameMode=1:gmStr=strinfo$(6)
For n=1 To zzamount
	aiLevel(n)=3 ;max 5
	aiCurLevel(n)=3
Next
HidePointer

For n=1 To characterAmount
	butPic2(n)=LoadImage("gfx\" + n + "\zwalk0.bmp")
Next

music=LoadSound(soundsdir$ + "music10.mp3")

gameIntro()
justIntroduced=1

menuOption=2
.menuStart	;----STARTS MENU -------------------------------

SetBuffer BackBuffer()

If justIntroduced=1 Then    ;the menu music is loaded For sure, just play
    If gameMusic=1 Then
        StopChannel chMusic
		LoopSound music
		chMusic=PlaySound(music)
		
  	EndIf
	justIntroduced=0
Else
	freeMap()
	If menuOption <> 1 Or scoreDone=1 Then
		FreeSound music
	 	music=LoadSound(soundsdir$ + "music10.mp3")
		If gameMusic=1 Then
	     StopChannel chMusic
	 	 LoopSound music
	 	 chMusic=PlaySound(music)
		EndIf
	EndIf
EndIf


FlushKeys
FlushMouse
FlushJoy
warning=0:warnSeq=0
scoreDone=0:winner=0
teamScore(1)=0:teamScore(2)=0
If gamesound Then sndStr=strinfo$(4) Else sndStr=strinfo$(5)

buttonamount=80
For n=60 To 63
butSeq(n)=lastBut(n)
Next
x=0

zzamount=4	
For n=1 To 4
	zOn(n)=lastZon(n)
	zAI(n)=lastzAI(n)
	xpointer(n)=100+x:ypointer(n)=400
	x=x+150
	If zteam(n) > 2 Then zteam(n)=0

Next

gfxdir$="gfx\stuff\"

backg=LoadImage(gfxdir$ + "bg.png")
backg1=LoadImage(gfxdir$ + "backg1.bmp")
title=LoadImage(gfxdir$ + "title.bmp")
butPic(54)=LoadImage(gfxdir$ + "but_ta.bmp")
butPic(50)=LoadImage(gfxdir$ + "but_gm.bmp")
butPic(70)=LoadImage(gfxdir$ + "but_start.bmp")
butPic(71)=LoadImage(gfxdir$ + "but_sound.bmp")
butPic(55)=LoadImage(gfxdir$ + "but_team.bmp")
For n=56 To 58
	butPic(n)=butPic(55)
Next


butPic(72)=butPic(71)

gamestart=0
If gameMode=2 Then gmStr=strinfo$(7)
ClsColor 0,0,0
fontType=1
defineButtons(0)
Repeat 	;--------Menus loop-------------------------
WaitTimer(frameTimer)
Cls

If menuOption=2 Or menuOption=3 Or menuOption=4 Then
	DrawImage backg1,0,0
	DrawImage title,24,24
	fontType=2
	pri 565,94,"v "+gameVersion
	fontType=1
EndIf

If KeyHit(1) Then
  defineButtons(0)
  If gameSound Then PlaySound clickSnd
  If menuOption = 2 Then saveConfig() : End
  If menuOption = 1 Then menuOption = 2
  If menuOption = 3 Then menuOption = 2
  If menuOption = 4 Then menuOption = 3 : savekeys()
EndIf

Select menuOption
	Case 1: menu()  ;character Select screen
	Case 2: mainMenu()  ;main menu, first screen
			clearSubStates()
	Case 3: optionsMenu()   ;Options menu
	Case 4: controlsMenu()   ;Controls menu
End Select

If warning=1 Then
	fontType=2
	warningMsg()
	fontType=1
EndIf


;If MilliSecs() => timestart+1000 Then fps=gframe:gframe=0:timestart#=MilliSecs()
;gframe=gframe+1
;Repeat
;Until MilliSecs() => timePassed
;timePassed = MilliSecs() + milli
Flip

Until gamestart=1

.changeMod
;-------------- Ends character select screen loop And Loads/display map thumbnails -------------------------

For n=60 To 63
	lastBut(n)=butSeq(n)
Next
For n=1 To zzamount
	lastZon(n)=zon(n): lastzAI(n)=zAI(n)
Next

mapAmount=0:mapsLoaded=0

n=1
If vsMode=1 Then
  If gameMode=2 Then
	For n=1 To maxCTFMap
	    If CTFMapOpen(n)=1 Then
            mapTn(n)=LoadImage(modFolder$(curModId) + "CTFmap_" + n + ".jpg")
	    EndIf
	Next
  Else
    For n=1 To 20
	    If vsMapOpen(n)=1 Then
            mapTn(n)=LoadImage(modFolder$(curModId) + "map_" + n + ".jpg")
	    EndIf
	Next
  EndIf

Else
	For n=1 To maxAmap
	    If mapOpen(n)=1 Then
			mapTn(n)=LoadImage(modFolder$(curModId) + "amap_" + n + ".jpg")
		EndIf
	Next

EndIf

loadMapTn=1:lastgamemode=gamemode
;.mtl
gameStart=0

;------------------------- menu For stage Select -----------------------------

For n=1 To 100
	xbut(n)=0 : yBut(n)=0 : wBut(n)=0 : hBut(n)=0
Next

vsSelectMap()
If gameStart=3 Then
	Goto changeMod
EndIf

For n=1 To 100
	If mapTn(n) <> 0 Then FreeImage mapTn(n):mapTn(n)=0
Next
If backg <> 0 Then FreeImage backg:backg=0
If backg1 <> 0 Then FreeImage backg1:backg1=0
If title <> 0 Then FreeImage title:title=0
For n= 0 To characterAmount
	If zThumbnail(n) <> 0 Then FreeImage zThumbnail(n):zThumbnail(n)=0
Next
For n=0 To 100
	If butPic(n) <> 0 Then FreeImage butPic(n):butPic(n)=0
Next

If gameStart=2 Then
	Goto menustart
EndIf

;-----------Load map--------------------------------
fontType=1
For n=1 To 4
	prevZOn(n)=zon(n)
Next
prevZzAmount=zzamount

.restartMap

For n=1 To 4	;must remember who was\were playing before map restart
	zon(n)=prevZOn(n)
Next
zzamount=prevZzamount

SetBuffer BackBuffer()
Cls
ClsColor 200,200,200
pri priw(strinfo$(23)),220,strinfo$(23)        ;L O A D I N G . . .   message

Flip

FreeSound music : music=0
For n=1 To 4
	If zteam(n)=0 Then zteam(n)=zteam(n)+(100+n)
Next

FlushKeys

For n=1 To zzamount
	zlife(n)=100				;Player's energy
	zHit(n)=0:zHitSeq(n)=0
	zheight(n)=45				;Player's current height
	zUpHeight(n)=45
	zDuckHeight(n)=25
	zside(n)=9-1 				;Z width size / 2
	zface(n)=2					;Which way it's facing
	zSpeed#(n)=0				;Player current speed
	zShieldedTime(n)=150		;Time(frames) player stays invincible when recover
	zTempShield(n)=0
	zBlockFull(n)=80
	zBlockLife(n)=zBlockFull(n)
	zCurWeapon(n)=0
	zDacc(n)=.2
	
	zAcc#(n)=.2         ;.2
	zgravity(n)=3       ;3		;Gravity force when falling or going up
	zjumplimit(n)=20    ;20		;Jump height (per frame), not pixels!
	zDtopSpeed#(n)=2    ;2
	zTopSpeed#(n)=zDtopSpeed(n)
	zTopRunningSpeed#(n)=zDtopSpeed(n)*zCharSpeed#(curGuy(n))
	zBlockSpeed(n)=.8
	zBLow(n)=0
	ztargetScore(n)=0
	zflagTime(n)=0
	zGotobj(n)=0
	zhitbybox(n)=0
	zdamage(n)=0
	zScore(n)=0
	zJumping(n)=0
	zBlowStill(n)=0
	zBlow(n)=0:zcurBlow(n)=0
	zBlowseq(n)=0:zBlowseq2(n)=0
	zBlock(n)=0
	zBlocked(n)=0
	zjump(n)=0
	zSuperMove(n)=0
	zSuperMoveSeq(n)=0
	zSuperBar(n)=0
	zStaminaBar#(n)=100
	zTrail(n)=0
	isRunning(n)=0
	If zon(n) And guyLoaded(curGuy(n))=0 Then loadPics(curGuy(n))
	If zon(n) Then initZ(n)
Next

chunkAmount=0   ;number of projectiles on screen
shotamount=0	;Number of shots on screen
expAmount=10	;Number of expolsions on screen

For n=5 To 30	;add extra computer
	zon(n)=0
Next
For n= 1 To 100
	shot(n)=0
Next
For n= 1 To 50
	obj(n)=0:objhitsolid(n)
Next
For n= 1 To 2
	flagcarried(n)=0
Next
For n= 1 To expamount
	Explosion(n)=0
Next
For n=1 To boxamount
boxpointsamount(n)=0:targetBox(n)=0:boxReachedDest(n)=0:boxHarmless(n)=0
boxCurPoint(n)=0:boxXDir(n)=0:boxYDir(n)=0:boxOn(n)=0:xboxdest(n)=0:yboxDest(n)=0
Next
For n=1 To platamount
xoldPlat(n)=0:xplat(n)=0:yPlat(n)=0:platXSpeed(n)=0:platYSpeed(n)=0:platPointsAmount(n)=0
platCurPoint(n)=0:platXDir(n)=0:platYDir(n)=0:platWidth(n)=0:platOn(n)=0:xplatdest(n)=0:yplatDest(n)=0
platSpecial(n)=0: platReachedDest(n)=0
Next
For n=1 To 1500
	chunk(n)=0
Next
boxAmount=0:platAmount=0
For n=1 To 30
	zdeadEvent(n)=0
	zSuperMove(n)=0
	zSuperMoveSeq(n)=0
	zSuperBar(n)=0
	zGotObj(n)=0
Next



If vsMode=0 Then gameMode=1
If gamemode=2 Then setPos_ctf Else setpos   ;Load map definitions

;Load helpers
If guyLoaded(43)=0 Then loadPics(43)
If guyLoaded(45)=0 Then loadPics(45)
If guyLoaded(46)=0 Then loadPics(46)

For i=1 To Famount	;loads character For factory If not done yet
	For ii=1 To FfacAmount(i)
		If facCategory(i,ii)=1 And guyLoaded(facType(i,ii))=0 Then loadPics(facType(i,ii))
	Next
Next

areaMoves=0
saAreaMovesAmount=0
daAreaMovesAmount=0
For n=1 To areaAmount	;builds sa area For AI
	saLlimit(n)=saX(n): saRlimit(n)=sax(n)+saW(n)
	If saMovedBy(n) > 0 Then
	 saAreaMovesAmount=saAreaMovesAmount+1
	 saAN(saAreaMovesAmount)=n
	 saX2(n) = saX(n) - xPlat(saMovedBy(n))
	 saY2(n) = saY(n) - yPlat(saMovedBy(n))
	EndIf
Next
For n=1 To dareaAmount	;builds da area For AI
	daLlimit(n)=daX(n): daRlimit(n)=dax(n)+daW(n)
	If daMovedBy(n) > 0 Then
	 daAreaMovesAmount=daAreaMovesAmount+1
	 daAN(daAreaMovesAmount)=n
	 daX2(n) = daX(n) - xPlat(daMovedBy(n))
	 daY2(n) = daY(n) - yPlat(daMovedBy(n))
	EndIf
Next
For n=1 To platAmount
	platPic(n)=platImage(platPic(n))
Next 
For n=1 To boxAmount
	If TargetBox(n)=0 Then boxHitSound(n) = soundFx(boxHitSound(n))
	If boxHitSound(n)=0 Then boxHitSound(n) = soundFx(2)	;This sound is chosen If none was selected
	boxon(n)=1
Next
objAmount=0 : shotAmount=0 : chunkAmount=0 : rectAmount=0
secretsAmount=0
secretsFound=0
For n=0 To 100
	Fon(n)=1
	eventAction(n)=0
	FdelaySeq(n) = 1	
	curF(n)=1
Next
For n=1 To triggerAmount
	Tonce(n)=0
	Ton(n)=0
	If Tway(n)=14 Then secretsAmount=secretsAmount+1
Next


buildMap	;builds non-visible map For collision

gfxDir$="gfx\tiles\"	;loads tiles For current map
For b=0 To bgAmount   
	For i=1 To tileAmount(b)
		;If tileSetNumber(b,i) > 5 Then RuntimeError "1 b= "+b+" i= "+i
		;If tileNumber(b,i) > 500 Then RuntimeError "2 b= "+b+" i= "+i
				
		If tileBmp(tileSetNumber(b,i),tileNumber(b,i)) = 0 Then
			tileBmp(tileSetNumber(b,i),tileNumber(b,i)) = LoadImage(gfxDir$ + tileSetNumber(b,i) +"_" + tileNumber(b,i) + ".bmp" )
		EndIf
		tilePic(b,i)=tileBmp(tileSetNumber(b,i),tileNumber(b,i))
	Next
Next

If curMap <> mapN Then End

For n=1 To zzamount
    zWasOn(n) = zon(n)
	zx(n)=zxStart(n):zy(n)=zyStart(n)		
	zGotHitsAmount(n)=0
	If ifiniteLives=1 Then zLives(n)=0 Else zlives(n)=gameLives
Next

;If music=0 Then music=LoadSound(soundsdir$ + "music" + musicN1+ ".wav")
;If music2=0 Then music2=LoadSound(soundsdir$ + "music" + musicN2+ ".wav")
If music=0 Then music=LoadSound(soundsdir$ + "music" + musicN1+ ".mp3")
If music2=0 Then music2=LoadSound(soundsdir$ + "music" + musicN2+ ".mp3")

If music <> 0 And gameMusic=1 Then
	StopChannel chMusic
	LoopSound music
	chMusic=PlaySound(music)
	;ChannelVolume chMusic ,3
EndIf

noDoubleJump=0
noAirSpecial=0
If ScrollMap=0 Then
	rScrLimit=1040:lScrLimit=-400:uScrLimit=-50000:dScrLimit=540	
Else
        If uScrLimit=0 Then uScrLimit=-50000
	dScrLimit = yScrCameraBottomLimit+540
EndIf

;If scrollMap=1 Then
;	If yScr > yScrCameraBottomLimit Then yScr = yScrCameraBottomLimit
;        If yScr < uScrLimit Then yScr = uScrLimit
;	If xScr < lScrLimit Or xScr+640 > rScrLimit Then xScr=xScrOld
;EndIf

ClsColor colorR,colorG,colorB

warning=0 : warnSeq=0
scoreDone=0:winner=0
teamScore(1)=0:teamScore(2)=0
xScr=xScrStart:yScr=yScrStart
gameTime=0:gameTime2=0
NoUserInput=0 
mapComplete=0
mapRestart=0
gameDone=0
fightMode = 1
If vsMode=0 Then
	For i=1 To 4
		zlives(i)=1
		zTeam(i)=1
	Next
	fightMode=2 
	teamAttack=0
	Select zamountPlaying 
		Case 1:aliveAmountNeeded=1
		Case 2:aliveAmountNeeded=2
		Case 3:aliveAmountNeeded=2
		Case 4:aliveAmountNeeded=2
		Default: aliveAmountNeeded=1
	End Select
Else
	NoUserInput=1
	makechunk(0,330,-30,2,2)
EndIf
FlushKeys() : FlushJoy()

;------*-------*-------------------*--------*--------
;------*-------*--- MAIN LOOP -----*--------*--------
;------*-------*-------------------*--------*--------
While Not gameDone=1
Getinput
flags
For n= 1 To zzamount
    zBeenHere(n)=0
	zJumping(n)=1:zNoGrav(n)=0:zNoJump(n)=0:zNoMove(n)=0:zTopSpeed(n)=zDtopSpeed(n)
	zonPlat(n)=0:zShield(n)=0:zAntiPLat(n)=0:zBlowBack(n)=0:zblowAlert(n)=0:extraDraw(n)=0
	zBlowHold(n)=4: zGrabbed(n)=0: zonThickPlat(n)=0: zTopRunningSpeed(n)=zDtopSpeed(n)*zCharSpeed#(n)
	zLeftCollide(n)=0: zRightCollide(n)=0
	
	If zFrozen(n) Then zNoMove(n)=1
	If zCanFly(n)=1 Then zNoGrav(n)=1: zForceAntiPlat(n)=1 : zantiPlatSeq(n)=0
	
	If zForceAntiPlat(n)=1 Then ;For when going down from plataform
		zantiPlat(n)=1
		zantiPlatSeq(n)=zantiPlatSeq(n)+1
		If zantiPlatSeq(n) > zantiPlatTime(n) Then zForceAntiPlat(n)=0
	EndIf
	If zTempShield(n)=1 And zon(n)=1 Then
		x=Rand(-10,10) : y=Rand(-40,0)
		sa=Rand(1,3)
		If sa=2 Then makechunk(n,zx(n)+x,zy(n)+y,2,13)
		zShield(n)=1
		Zshieldseq(n)=Zshieldseq(n)+1
		If Zshieldseq(n) > ZshieldedTime(n) Then zTempShield(n)=0
	EndIf
	If zTrail(n) > 0 And rendert =1 And zon(n)=1 Then
		ztrailseq(n)=ztrailseq(n)+1
		x=Rand(-8,8) : y=Rand(-30,-10)
		Select zTrailType(n)
			Case 1:zTrailTime(n)=10 : makechunk(n,zx(n)+x,zy(n)+y,zFace(n),24)	;on fire
			Case 2:zTrailTime(n)=20 : makechunk(n,zx(n)+x,zy(n)+y,zFace(n),29)	;rising little smoke
			Case 3:zTrailTime(n)=20 : makechunk(n,zx(n)+x,zy(n)+y,zFace(n),13)	;bright dots
			Case 4:zTrailTime(n)=15 : makechunk(n,zx(n)+x,(zy(n)+10)+y,zFace(n),31)	;electrified
						
		End Select
		If ztrailseq(n) > ztrailtime(n) Then ztrail(n)=0
	EndIf
Next
For n=1 To zzamount
        If (zblow(n) =1 Or zblocked(n)) And zgrabbed(n)=0 And zon(n)=1 Then
		;Add character, add another CASE call to your new function, will probably be 
		;something like: CASE 11:DoGuyNameHere(n)
		Select curguy(n)	
		
			Case 1:doRyu(n)
			Case 2:DoRash(n)
			Case 3:DoSpiderMan(n)
			Case 4:DoMario(n)
			Case 5:DoMike(n)
			Case 6:DoGaiden(n)
			Case 7:DoBatman(n)
			Case 8:DoPredator(n)
			Case 9:DoGoku(n)
			Case 10:DoRitcher(n)
			Case 11:DoWolverine(n)
			Case 12:DoSonya(n)
			Case 13:DoBroly(n)
			Case 14:DoSubZero(n)
			Case 30:DoPig(n)
			Case 31:DoAlien(n)
			Case 32:DoFootClan(n)
			Case 33:DoShredder(n)
			Case 34:DoThug(n)
			Case 35:DoRedHorns(n)
			Case 36:DoGargola(n)
			Case 37:DoRedPlant(n)
			Case 38:DoBowser(n)
			Case 39:DoThief(n)
			Case 40:DoTurtle(n)
			Case 41:DoTurtleCloud(n)
			Case 42:DoJoker(n)
			Case 43:DoLaserHelper(n)
			Case 44:DoVenom(n)
			Case 45:DoBombingShip(n)
			Case 46:DoRayBalls(n)
			Case 47:DoSoldier(n)
			Case 48:DoCylinder(n)
			Case 49:DoDragon(n)
			Case 50:DoLaserBeam(n)
			Case 51:DoGaiden(n)
			Case 52:DoBag(n)
			Case 53:DoBroly(n)

		End Select

	EndIf

	zBlowHit(n)=0
	zHitHead(n)=0
Next

For n=1 To rectAmount
	If rectHit(n) Then rects(n)
Next
For n= 1 To zzamount
	If zon(n) > 0 Then blows(n)
Next
For n=1 To zzamount
	If zHit(n) =1  Then zBlow(n)=0:zBlowStill(n)=0:isRunning(n)=0
Next

stages();Special stuff that goes on the current map

plat(n)	;do all plataforms

For n= 1 To boxAmount
	If boxOn(n) Then boxes(n)
Next

For n= 1 To shotamount
	If shot(n) Then shots(n)
Next
For n= 1 To objAmount
	If obj(n) Then
		objs(n)
		objFrameSeq(n) = objFrameSeq(n) + 1
		If objFrameSeq(n) > objFrameTime(n) Then
			objFrameSeq(n)=0 : objCurFrame(n)=objCurFrame(n)+1
			If objCurFrame(n) > objFrameAmount(n) Then objCurFrame(n)=1
		EndIf
		If xobj(n) > rscrlimit Then
			objHitSolid(n):obj(n)=0
		ElseIf xobj(n) < lscrlimit Then
			objHitSolid(n):obj(n)=0
		ElseIf yobj(n) > dscrlimit Then
			objHitSolid(n):obj(n)=0
		EndIf
	EndIf
Next

If rendert=3 Then	;delete stuff as needed, every 3 frames
	If shot(shotAmount)=0 Then
		shotAmount=shotAmount-1
		If rectHit(rectAmount)=0 Then rectAmount=rectAmount-1
		If rectAmount < 0 Then rectAmount=0
		If shotAmount < 0 Then shotAMount=0
	EndIf
	
	If obj(objAMount)=0 Then
		objAmount=objAmount-1
		If objAmount < 0 Then objAmount=0
	EndIf
	
	If zon(zzamount)=0 Then
		zzamount=zzamount-1
		If zzamount < 0 Then zzamount=0
	EndIf
	
	If vsMode=1 And noItems=0 Then spawnObj()       ;spawn objects on versus mode

	For n=1 To saAreaMovesAmount    ;move assigned areas
	  saX(saAN(n)) = xPlat(saMovedBy(saAN(n))) + saX2(saAN(n))
	  saY(saAN(n)) = yPlat(saMovedBy(saAN(n))) + saY2(saAN(n))
	  saLlimit(saAN(n))=saX(saAN(n)): saRlimit(saAN(n))=sax(saAN(n))+saW(saAN(n))
  	Next

	For n = 1 To daAreaMovesAmount
	   daX(daAN(n)) = xPlat(daMovedBy(daAN(n))) + daX2(daAN(n))
	   daY(daAN(n)) = yPlat(daMovedBy(daAN(n))) + daY2(daAN(n))
	   daLlimit(daAN(n))=daX(daAN(n)): daRlimit(daAN(n))=dax(daAN(n))+daW(daAN(n))
  	Next

EndIf

For n=1 To expAmount
	If explosion(n) Then explo(n)
Next

For n=1 To triggerAmount
	triggers(n)
Next

For n=1 To Famount
	If eventN(Fevent(n))=1 And Fon(n) Then factory(n)
Next

For n= 1 To zzamount
	If zon(n) > 0 And zGrabbed(n)=0 Then zman(n)
	
	checkInputs(n)
	If zStaminaBar#(n) < 100 And isRunning(n)=0 Then 
		zStaminaBar#(n)=zStaminaBar#(n)+0.5
	End If
	
	If zon(n) Then SelectDraw(n)
Next

If chunk(chunkAmount)=0 Then chunkAmount=chunkAmount-1
If chunkAMount < 0 Then chunkAMount=0
For n= 1 To chunkAmount
	If chunk(n) Then chunks(n)
Next

If scrollMap=1 Then
	;camera control
	zRight=0
	zLeft=0
	zTop=0
	zDown=0
	moved=0
	For n=1 To 4
		If zon(n)=1 Then			
			If zx(n) > xscr+440 Then zRight=1
			If zx(n) < xscr+200 Then zLeft=1		
			If zy(n) > yScr+400 Then zDown=1
			If zy(n) < yScr+200 Then zTop=1
			
			If zx(n) > xscr+640 Then zx(n)=zoldx(n)
			If zx(n) < xscr Then zx(n)=zoldx(n)
			If zy(n) > yscr+525 Then
				playDeathSnd(n)
                zlives(n)=zlives(n)-1
				killZ(n)
			ElseIf zy(n) < yscr-45 Then
				playDeathSnd(n)
			    zlives(n)=zlives(n)-1
				killZ(n)
			EndIf

		EndIf
			
	Next
	scrollxSpeed=0
	scrollySpeed=0
	xScrOld=xScr 
	yScrOld=yScr 
	
	For n=1 To 4
	  If zon(n)=1 Then  
		If zamountPlaying > 1 Then	; more players
			If zx(n) > xscr+440 And zLeft=0 Then moved=1:zLeft=1:xScr = xScr+2:Goto XscrMoved
			If zx(n) < xscr+200 And zRight=0 Then moved=1:zRight=1:xScr = xScr-2:Goto XscrMoved
;			If zx(n) > xscr+440 And zLeft=0 Then moved=1:zLeft=1:xScr = Int(zx(n)-440):Goto XscrMoved
;			If zx(n) < xscr+200 And zRight=0 Then moved=1:zRight=1:xScr = Int(zx(n)-200):Goto xscrMoved
			.XscrMoved
			
;			If zy(n) > yScr+400 And zTop=0 Then moved=1:zTop=1:yScr = Int(yScr+zGravity(n)):Goto yscrMoved
;			If zy(n) < yScr+200 And zDown=0 Then moved=1:zDown=1:yScr = Int(yScr-zGravity(n)):Goto yscrMoved
			If zy(n) > yScr+405 And zTop=0 Then moved=1:zTop=1:yScr = yScr+3:Goto yscrMoved
			If zy(n) < yScr+205 And zDown=0 Then moved=1:zDown=1:yScr = yScr-3 :Goto yscrMoved
			.yscrMoved
		Else	;one player	-- working better
			If zx(n) > xscr+440 And zLeft=0 Then moved=1:zLeft=1:xScr = Int(zx(n)-440):Goto XscrMoved2
			If zx(n) < xscr+200 And zRight=0 Then moved=1:zRight=1:xScr = Int(zx(n)-200):Goto xscrMoved2
			.XscrMoved2
				
			If zy(n) > yScr+400 And zTop=0 Then moved=1:zTop=1:yScr = Int(zy(n)-400):Goto yscrMoved2
			If zy(n) < yScr+200 And zDown=0 Then moved=1:zDown=1:yScr = Int(zy(n)-200):Goto yscrMoved2
			.yscrMoved2
		EndIf
	  EndIf
	  If moved=1 Then Exit
	Next
	
	If yScr > yScrCameraBottomLimit Then yScr = yScrCameraBottomLimit	

	If yScr < uScrLimit Then yScr = uScrLimit

	If xScr < lScrLimit Then
		xScr=lScrLimit
	ElseIf xScr+640 > rScrLimit Then
		xScr=rScrLimit-640
	EndIf
	
	scrollxSpeed=(xScr-xScrOld)
	scrollySpeed=(yScr-yScrOld)
EndIf

For i=0 To 4 Step 4		;Tile movement, layer 0 And 4 only
For n=1 To tileAmount(i)
	re#=0
	If scrollXspeed <> 0 And xTileScrSpeed(i,n) <> 0 Then	;tiles scrolls along with background If set
		re#= scrollxspeed - ( scrollxspeed / xTileScrSpeed(i,n) )
		If xTileScrSpeed(i,n)=1 Then xTile(i,n)=xTile(i,n)+scrollxspeed Else xTile(i,n)=xTile(i,n)+re#
	EndIf
	If scrollYspeed <> 0 And yTileScrSpeed(i,n) <> 0 Then
		re#=scrollyspeed - ( scrollyspeed / yTileScrSpeed(i,n) )
		If yTileScrSpeed(i,n)=1 Then yTile(i,n)=yTile(i,n)+scrollyspeed Else yTile(i,n)=yTile(i,n)+re#
	EndIf
	
	If xTile(i,n) => tileXEnd1(i,n) And xTile(i,n) =< tileXEnd2(i,n) Then
		If tileXRand1(i,n) <> 0 Then
			xTile(i,n)=Rand((tileXstart(i,n)+tileXrand1(i,n)), (tileXstart(i,n)+tileXrand2(i,n)))
		Else
			If tileFollow(i,n)=1 Then
				If tileXspeed(i,n) > 0 Then
					xTile(i,n)=xTile(i,tileTarget(i,n))-ImageWidth(tilePic(i,tileTarget(i,n)))
				Else
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

For n=1 To taniAmount	;executes indepedent animations For tiles
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

If vsMode=1 Then
	Select gameMode	;check score And define a winner If any
	Case 2
		If teamScore(1) => flagMaxScore Then winner=1:ScoreDone=1:gameDone=1
		If teamScore(2) => flagMaxScore Then winner=2:ScoreDone=1:gameDone=1
		
	Case 3
		For n=1 To zzamount
			If zflagTime(n) => FlagMaxTime And zon(n) > 0 Then winner=n:scoreDone=1:gameDone=1
		Next
	Case 4
		For n=1 To zzamount
			If zTargetScore(n) => targetMaxScore And zon(n) > 0 Then winner=n:scoreDone=1:gameDone=1
		Next
	Default
		If zzamount=0 Then
			winner=1000:scoreDone=1:gameDone=1	;it's a draw, all players died
		EndIf
		For n=1 To 4 
			If zon(n) > 0 Then
				alive=0
				For nn=1 To 4
					If nn <> n And zteam(n) <> zteam(nn) Then
						If zon(nn) = 1 Then alive=alive+1
					EndIf
				Next
				If alive=0 And zongnd(n)=1 Then winner=n:scoreDone=1:gameDone=1
			EndIf
		Next
	End Select
Else	;If passed level
	alive=0
	For n=1 To 4
		If zon(n)=1 Then alive=alive+1	
	Next
	If alive < aliveAmountNeeded Then 
		gameDone=1
		mapRestart=1
	EndIf
EndIf

.noScore	;test


;-----------------------------------------------------------------------
;--------- GAME RENDERING ----RENDER GAME----------------------------------
;-----------------------------------------------------------------------
.drawMouse
.RenderOnly
WaitTimer(frameTimer)

Cls

If quake=1 Then
	quakeSeq = quakeSeq + 1
	yqk#=Rand(-1,1)
	If quakeSeq > 8 Then 
		quake=0
		yqk#=0
	EndIf
EndIf

For b=0 To 2
	For i=1 To tileAmount(b)
		DrawImage tilePic(b,i), xTile(b,i)+xqk-xscr, yTile(b,i)+yqk-yscr
	Next
Next

;If flagamount=2
;	y=17
;	Color 255,0,0:Rect 5,y,20,14,1
;	pri 5,y, teamScore(1)
;	Color 0,255,0:Rect 615,y,20,14,1
;	pri 617,y, teamScore(2)
;EndIf

For n = 1 To flagamount
	DrawImage flagPic(n),xFlag(n)-xscr,(yFlag(n)-32)-yscr
Next

For n= 1 To platAmount
	If drawPlat(n)=1 Then DrawImage platPic(n),xplat(n)-xscr,(yplat(n)-3)-yscr
Next

For i=1 To triggerAmount
	If Tdraw(i)=1 Then
		If EventN(Tevent(i))=0 Then DrawImage Timage(TimageN(i),1),(Tx(i)+TimgX(i))-xscr, (Ty(i)+TimgY(i))-yscr
		If EventN(Tevent(i))=1 Then DrawImage Timage(TimageN(i),2),(Tx(i)+TimgX(i))-xscr, (Ty(i)+TimgY(i))-yscr
	EndIf
Next

For n=1 To zzamount
	If zon(n) > 0 Then renderZ(n)
Next

For n = 1 To objAmount
	If obj(n) And objTaken(n) Then
		xobj(n)=zx(objOwner(n)):yobj(n)=zy(objOwner(n))-20
	Else
		If obj(n) Then
			Select objDir(n)
			 Case 2: DrawImage objPic(n,objCurFrame(n)),(xObj(n)-objSide(n))-xscr,(yObj(n)-objHeight(n))+1-yscr
			 Case 4: DrawImage objPic_(n,objCurFrame(n)),(xObj(n)-objSide(n))-xscr,(yObj(n)-objHeight(n))+1-yscr	
			 Default: DrawImage objPic(n,objCurFrame(n)),(xObj(n)-objSide(n))-xscr,(yObj(n)-objHeight(n))+1-yscr
			End Select
			If objHurt(n)=0 Then 
				DrawImage objArrow,(xObj(n)-2)-xscr,(yObj(n)-(objHeight(n)+7))-yscr
			EndIf
		EndIf
	EndIf
Next

For n= 1 To boxAmount
	If drawbox(n) Then renderBoxes(n)
Next

;draw bullets/shots
For n = 1 To shotamount
	If shot(n) Then
		Select shotDir(n)
		Case 2:	DrawImage shotpic(n,shotCurFrame(n)), (xshot(n)-shotside(n))-xscr, (yshot(n)-shotHeight(n))-yscr
		Case 4:	DrawImage shotpic_(n,shotCurFrame(n)), (xshot(n)-shotside(n))-xscr, (yshot(n)-shotHeight(n))-yscr
		End Select
	EndIf
Next

For n= 1 To chunkAmount
  If chunk(n) = 1 Then
	Local yImageHeight,xImageHeight
	yImageHeight=yChunk(n)-ImageHeight(chunkPic(n))
	xImageHeight=(xChunk(n)-ImageWidth(chunkPic(n))/2)-xscr
	;Select chunkCategory(n)
	;Case 1	;normal chunks
	  
	  Select chunkDir(n)
		Case 0:DrawImage chunkPic(n),(xChunk(n)-ImageWidth(chunkPic(n))/2)-xscr,(yChunk(n)-ImageHeight(chunkPic(n)))-yscr
		Case 2:DrawImage chunkPic(n),(xChunk(n)-ImageWidth(chunkPic(n))/2)-xscr,(yChunk(n)-ImageHeight(chunkPic(n)))-yscr 
		Case 4:DrawImage chunkPic_(n),(xChunk(n)-ImageWidth(chunkPic(n))/2)-xscr,(yChunk(n)-ImageHeight(chunkPic(n)))-yscr 
	  End Select
	;Case 2	;text messages
;
;	  yChunk(n) = textBox(chunkWidth(n),chunkHeight(n))
;	  y=0
 ;	  For i=1 To chunkLines(n)
;		;fontType=2
;		pri priW(chunkStr$(n,i)), yChunk(n)+5+y, chunkStr$(n,i)
;		;fontType=1
;		y=y+22
;	  Next
;	End Select
  EndIf
Next

For b=3 To 5
  For i=1 To tileAmount(b)
	DrawImage tilePic(b,i),(xTile(b,i)+xqk)-xscr,(yTile(b,i)+yqk)-yscr
  Next
Next


x=15:y=3	;draws bars, super bar, damage/life, lives, etc.
For n=1 To 4
	xx=0
 If zon(n) Then
	For k=1 To zlives(n)
		DrawImage zIcon(curGuy(n)),x+xx,y
		xx=xx+15
		If k > 6 Then Exit
	Next
	If zDamage(n) > 999 Then zDamage(n)=999
	Color 255,0,0
	If fightMode=1 Then 
		pri x+115,y, Int(zDamage(n))
	Else
		pri x+115,y, Int(zLife(n))
	EndIf

	Select gameMode
		Case 2:
		    Color 255,0,0:Rect 4,y+19,20,14,1
			pri 5,y+20, teamScore(1)
			Color 0,255,0:Rect 615,y+19,20,14,1
			pri 617,y+20, teamScore(2)
		Case 3: pri x+115,y+20, Int(zFlagTime(n))
		Case 4: pri x+115,y+20, Int(zTargetScore(n))
	End Select
 	If zSuperBar(n) < 100 Then Color 255,0,0 Else Color 0,255,0
	Rect x,y+15,zSuperbar(n),4,1
	If zStaminaBar#(n) < 70 Then Color 231,76,60 Else Color 52,152,219  
	Rect x,y+20,zStaminaBar#(n),4,1
	Color 74,35,90
	Rect x,y+25,(zBlockLife(n)*1.28),4,1
	Color 120,120,120
	Rect x,y+15,100,4,0	 
 EndIf
x=x+155
Next


For n=1 To zzamount		;Draws big pictures of characters when performing super special move
	If zSuperMove(n) And zhit(n)=0 Then
		If zSuperMove(n) And zSuperMoveSeq(n) > 1 Then 
			DrawImage ptPic(23,1), (zx(n)+Rand(-50,50))-xscr,(zy(n)+Rand(-50,50))-yscr
			Color 255,255,255
			;Oval xOval(n)-xscr,yOval(n)-yscr,wOval(n),hOval(n),0
			Color 0,0,230
			;Oval (xOval(n)-50)-xscr,(yOval(n)-50)-yscr,wOval(n)+100,hOval(n)+100,0
			;Oval (xOval(n)-100)-xscr,(yOval(n)-100)-yscr,wOval(n)+200,hOval(n)+200,0
		EndIf
		xOval(n)=xOval(n)+20:yOval(n)=yOval(n)+20
		wOval(n)=wOval(n)-40:hOval(n)=hOval(n)-40
		zSuperMoveSeq(n)=zSuperMoveSeq(n)+1
		If zSuperMoveSeq(n)=1 Then 
			xOval(n)=zx(n)-400 : yOval(n)=zy(n)-(400+10)
			wOval(n)=800 : hOval(n)=800
			If zFace(n)=2 Then
				zSuperX(n)=0   - ImageWidth(zpic(curGuy(n),20,1)) : zSuperDir(n)=2
			Else
				zSuperX(n)=640 : zSuperDir(n)=4
			EndIf
			If zy(n)-yscr > 240 Then zSuperY(n)=100 Else zSuperY(n)=250
		EndIf
		a=15 : b=30 : c=50
		Select zSuperDir(n)
		Case 2
			If zSuperMoveSeq(n) > 0 And zSuperMoveSeq(n) =< a Then zSuperX(n)=zSuperX(n)+20
			If zSuperMoveSeq(n) > a And zSuperMoveSeq(n) =< b Then zSuperX(n)=zSuperX(n)+0
			If zSuperMoveSeq(n) > b And zSuperMoveSeq(n) =< c Then zSuperX(n)=zSuperX(n)-20
			If zSuperMoveSeq(n) > c Then zSuperMove(n)=0:zSuperBar(n)=0
		Case 4
			If zSuperMoveSeq(n) > 0 And zSuperMoveSeq(n) =< a Then zSuperX(n)=zSuperX(n)-20
			If zSuperMoveSeq(n) > a And zSuperMoveSeq(n) =< b Then zSuperX(n)=zSuperX(n)+0
			If zSuperMoveSeq(n) > b And zSuperMoveSeq(n) =< c Then zSuperX(n)=zSuperX(n)+20
			If zSuperMoveSeq(n) > c Then zSuperMove(n)=0:zSuperBar(n)=0
		End Select						
		y=zSuperY(n)
		For i=0 To 110 Step 10
			Color 0,0,Rand(80,200)
			Rect 0,y,640,10,1
			y=y+10
		Next	
		DrawImage zPic(curGuy(n),20,1),zSuperX(n),zSuperY(n)
		
	EndIf
Next

If message=1 Then
 n = messageN
	 yChunk(n) = textBox(chunkWidth(n),chunkHeight(n))
	  y=0
 	  For i=1 To chunkLines(n)
		;fontType=2
		pri priW(chunkStr$(n,i)), yChunk(n)+5+y, chunkStr$(n,i)
		;fontType=1
		y=y+22
	  Next
EndIf

;debug
;Color 255,43,234
;n=2

;y=50:pri 15,y, "zzamount " + zzamount
;y=y+18:pri 15,y, "shotAmount " + shotAmount
;y=y+18:pri 15,y, "objAmount " + objAmount
;y=y+18:pri 15,y, "chunkAmount " + chunkAmount

;y=y+18:pri 15,y, "zy(1) " + zy(1)
;y=y+18:pri 15,y, "zblowuplimit(1) " + zblowuplimit(1)
;y=y+18:pri 15,y, "noairstrike= "+noairstrike
;y=y+18:pri 15,y, "zAi(5)= " + zAi(5)
;y=y+18:pri 15,y, "zon(4) " + zon(4)
;For n=1 To 4
;	Text xTile(0,n)+2,yTile(0,n)+2, n
;Next


If showBlowArea=1 Then	;renders developer`s stuff!

Color 255,43,234
For n=1 To zzamount
	For bn=1 To zblowpamount(n)
		If zBlow(n) And zFace(n)=2 And zBlowEffect(n) Then Rect (zx(n)+xBlow(n,bn))-xscr,(zy(n)-yBlow(n,bn))-yscr,wBlow(n,bn),hBlow(n,bn),0
		If zBlow(n) And zFace(n)=4 And zBlowEffect(n) Then Rect (zx(n)-(xBlow(n,bn)+wBlow(n,bn)))-xscr,(zy(n)-yBlow(n,bn))-yscr,wBlow(n,bn),hBlow(n,bn),0
	Next
Next
For n=1 To areaAmount
	Rect saX(n)-xscr,saY(n)-yscr,saW(n),saH(n),0
	Text saX(n)+2-xscr,saY(n)+2-yscr, n
Next
For n=1 To dareaAmount
	Rect daX(n)-xscr,daY(n)-yscr,daW(n),daH(n),0
	Text daX(n)+2-xscr,daY(n)+2-yscr, n
Next
Color 255,255,50
For n=1 To triggerAmount
	Rect TX(n)-xscr,TY(n)-yscr,Tw(n),Th(n),0
	Text TX(n)+2-xscr,TY(n)+2-yscr, n
Next
For n=1 To rectAmount
  If rectHit(n)=1 Then
	Rect xrect(n),yrect(n),wRect(n),hRect(n),0
  EndIf
Next

EndIf

Goto Nodevkeys2
;Plot zx(1),zy(1)
If MouseDown(1) Then 
	Text 15,80, "X= " +MouseX()+"  Y= "+MouseY()
	;Text
	Text 15,80, "X= " + (MouseX()-zx(1)) +"  Y= "+ (MouseY()+zy(1))
	Color 255,255,255
	Plot (MouseX(),MouseY())
	Plot (MouseX()-3,MouseY()-3)
	Plot (MouseX()+3,MouseY()-3)
	Plot (MouseX()-3,MouseY()+3)
	Plot (MouseX()+3,MouseY()+3)
	If MouseHit(2) Then zx(1)=MouseX():zy(1)=MouseY():letgo=1

	;GetColor MouseX(),MouseY()
;	Text 15,95,"RGB: " + ColorRed() + "," + ColorGreen() + "," + ColorBlue() 
EndIf
.Nodevkeys2

If showVidMem Then
	;Color 255,43,234
	;pri 505,50, "fps: "+fps
	pri 505,70, AvailVidMem()
	;Select renderFreq
	;	Case 1:Text 550,30,"frmSkip=0"
	;	Case 2:Text 550,30,"frmSkip=1"
	;	Case 3:Text 550,30,"frmSkip=2"
	;End Select
	
	;Rect xScy+200,yScr+200,xScr+440,yScr+400,0
	
EndIf

If renderDelay Then Delay 50

;If MilliSecs() => timestart+1000 Then fps=gframe:gframe=0:timestart#=MilliSecs()
;gframe=gframe+1
;Repeat
;Until MilliSecs() => timePassed
;timePassed = MilliSecs() + milli




rendert=rendert+1
Select renderFreq
Case 1
	Select rendert
	Case 1:Flip(false)
	Case 2:Flip(false)
	Case 3:Flip(false)
	Case 4:Flip(false):rendert=0
	End Select
Case 2
	Select rendert
	Case 1:Flip
	Case 2:;Flip
	Case 3:Flip
	Case 4:Flip:rendert=0
	End Select
Case 3
	Select rendert
	Case 1:;Flip
	Case 2:Flip
	Case 3:;Flip
	Case 4:Flip:rendert=0
	End Select

End Select


If gamePaused Then  ;game is paused
duringGameMenu=1
clscolor 30,30,30
Repeat

	cls
	If KeyHit(1) Then
	  ;defineButtons(0)
	  If gameSound Then PlaySound clickSnd
      If menuOption = 5 Then gamePaused=0
	  If menuOption = 3 Then menuOption = 5
	  If menuOption = 4 Then menuOption = 3 : savekeys()

	EndIf

	Select menuOption
		Case 3: optionsMenu()   ;Options menu
		Case 4: controlsMenu()  ;Controls menu
		Case 5: inGameMenu()    ;during gameplay menu
	
	End Select

If warning=1 Then
	fontType=2
	warningMsg()
	fontType=1
EndIf

Flip
Until gamePaused=0
duringGameMenu=0
ClsColor colorR,colorG,colorB
EndIf

For n=1 To zzamount
	If zSupermove(n) And zon(n) Then Goto renderOnly
Next

Wend	;******* ENDS MAIN LOOP + GAME RENDER LOOP **********

If (mapComplete=1 Or scoreDone=1) And gameMusic=1 Then
	StopChannel chMusic
	StopChannel chMusic2
	PlaySound victorySnd
EndIf

closeScreen(Rand(1,4),0)

If mapComplete=1 Then
    If secretsFound > mapSecret(previousMap) Then
		mapSecret(previousMap) = secretsFound
	EndIf
	saveMaps()
	;checkWhatsOpen()
	If previousMap = lastAMap Then   ;If beat the game
        statsScreen()
		freesound music
		music=LoadSound(soundsdir$ + "music" + 10 + ".wav")
		 If gameMusic=1 Then
		  LoopSound music
 		  chMusic=PlaySound(music)
		 EndIf
		rollCredits()
		freeMap()
 	Else
 		statsScreen()
 		freeMap()
		Goto restartMap
	EndIf
EndIf

If vsMode=1 And scoreDone=1 Then
	delay 500
	vsStatsScreen()
	freeMap()
EndIf

If mapRestart=1 Then
	;freeMap()
	Goto restartMap
EndIf

If menuOption=2 Then menuOption=2 Else menuOption = 1
Goto menuStart
End
;---------- check what characters are open ---------------
Function checkWhatsOpen()
 
	response=0
	
	If curModId=1 Then	;Only do the unlocking checking of the current mod is the original one
		If mapOpen(9)=1 And characterOpen(4)=0 Then characterOpen(4)=1 : response=1
		If mapOpen(17)=1 And characterOpen(9)=0 Then characterOpen(9)=1 : response=1
		If mapOpen(17)=1 And characterOpen(10)=0 Then characterOpen(10)=1 : response=1
		If mapOpen(17)=1 And characterOpen(11)=0 Then characterOpen(11)=1 : response=1
		If mapOpen(18)=1 And characterOpen(10)=0 Then characterOpen(10)=1 : response=1
		If mapOpen(18)=1 And characterOpen(12)=0 Then characterOpen(12)=1 : response=1
		If mapOpen(21)=1 And characterOpen(5)=0 Then characterOpen(5)=1 : response=1
		If mapOpen(21)=1 And characterOpen(13)=0 Then characterOpen(13)=1 : response=1
		If mapOpen(21)=1 And characterOpen(14)=0 Then characterOpen(14)=1 : response=1
		If mapOpen(30)=1 And characterOpen(6)=0 Then characterOpen(6)=1 : response=1
		If mapOpen(44)=1 And characterOpen(7)=0 Then characterOpen(7)=1 : response=1
		If mapOpen(51)=1 And characterOpen(8)=0 Then characterOpen(8)=1 : response=1
		
		If mapOpen(9)=1 Then vsMapOpen(5)=1
		If mapOpen(21)=1 Then vsMapOpen(6)=1: vsMapOpen(7)=1: ctfMapOpen(2)=1
		If mapOpen(35)=1 Then vsMapOpen(8)=1: vsMapOpen(9)=1 : vsMapOpen(10)=1
		If mapOpen(40)=1 Then
		 For i=11 To 30
		  vsMapOpen(i)=1
		 Next
		EndIf
		
		If totalSecrets => 10 Then vsMapOpen(10)=1
		If totalSecrets => 20 Then vsMapOpen(11)=1:ctfMapOpen(2)=1 :ctfMapOpen(3)=1 : ctfMapOpen(4)=1
		
		If mapOpen(15)=1 Then ctfMapOpen(2)=1
		If mapOpen(35)=1 Then ctfMapOpen(3)=1 : ctfMapOpen(4)=1
	EndIf
	
	Return response

End Function
;-----------MAN/ITEM FACTORY-------------------------------
Function factory(n)

FdelaySeq(n)=FdelaySeq(n)+1
If FdelaySeq(n) => facDelay(n,curF(n)) Then
	 If facWaitEvent(n,curF(n)) > 0 Then
	 	If EventN(facWaitEvent(n,curF(n)))=1 Then
		;If eventAction(facWaitEvent(n,curF(n))=1 Then
			;eventAction(facWaitEvent(n,curF(n))=0
			:
		Else
			;FdelaySeq(n)=FdelaySeq(n)-1
			Goto noFac
		EndIf
	 EndIf	
	
		FdelaySeq(n)=0
		Select facCategory(n,curF(n))
		Case 1	;players, enemies...
			
			For i=5 To 30
				If zon(i)=0 Then
					nn=i : found=1
					If nn > zzamount Then zzamount=nn
					Exit
				EndIf
				
			Next
			curGuy(nn)=facType(n,curF(n))
			zVar1(nn)=facVar1(n,curF(n))
			zVar2(nn)=facVar2(n,curF(n))
			zVar3(nn)=facVar3(n,curF(n))
			zVar4(nn)=facVar4(n,curF(n))
			zVar5(nn)=facVar5(n,curF(n))
			initZ(nn)
			zon(nn)=1
			zx(nn)=xfac(n,curF(n))
			zy(nn)=yfac(n,curF(n))
			;zFace(nn)=4 ;facDir(n,curF(n))
			zLife(nn)=facLife(n,curF(n))
			zLives(nn)=facLives(n,curF(n))
			zDamage(nn)=facDamage(n,curF(n))
			AiLevel(nn)=facAiLevel(n,curF(n))
			zTeam(nn)=facTeam(n,curF(n))
			zDeadEvent(nn)=facDeadEvent(n,curF(n))
						
			If facChunk(n,curF(n)) > 0 Then makeChunk(nn,zx(nn),zy(nn),2,facChunk(n,curF(n)))
			If facSound(n,curF(n)) > 0 And gamesound Then PlaySound soundFx(facSound(n,curF(n)))
			ztempShield(nn)=0
			zTrail(nn)=0
														
		Case 2	;Itens
			
			For i=1 To 100
				If obj(i)=0 Then
					nn=i
					If nn > objAmount Then objAmount=nn
					Exit
				EndIf
				
			Next		
			objType(nn)=facType(n,curF(n))
			objData(nn,0)
			xobj(nn)=xfac(n,curF(n))
			yobj(nn)=yfac(n,curF(n))
			objDir(nn)=facAiLevel(n,curF(n))
			objXspeed(nn)=facDeadEvent(n,curF(n))
			objYspeed(nn)=facLife(n,curF(n))
			objHurt(nn)=facLives(n,curF(n))
			objOwner(nn)=facTeam(n,curF(n))
			zTeam(objOwner(nn))=objOwner(nn)
			If objHurt(nn) Then objTaken(nn)=0:objThrow(nn)=1
			If facDamage(n,curF(n)) > 0 Then objDamage(nn)=facDamage(n,curF(n))
			Obj(nn)=1
			If facChunk(n,curF(n)) > 0 Then makechunk(nn,xobj(nn),yobj(nn),objDir(nn),facChunk(n,curF(n)))
			If facSound(n,curF(n)) > 0 And gamesound Then PlaySound soundFx(facSound(n,curF(n)))
			objSuper(nn) = facVar1(n,curF(n))
			objNoGrav(nn) = facVar2(n,curF(n))  
			objHitMode(nn) = facVar3(n,curF(n))
						
		Case 3	;shots
			
			For i=1 To 200
				If shot(i)=0 Then
					nn=i : found=1
					If nn > shotAmount Then shotAmount=nn
					Exit
				EndIf
				
			Next
			xshot(nn)=xfac(n,curF(n))
			yshot(nn)=yfac(n,curF(n))
			shotDir(nn)=facAiLevel(n,curF(n))
			shotData(facType(n,curF(n)),nn)
			shotDrill(nn)=facLives(n,curF(n))
			shotOwner(nn)=facTeam(n,curF(n))
									
			If facDeadEvent(n,curF(n)) > 0 Then shotSpeed(nn)=facDeadEvent(n,curF(n))
			If facLife(n,curF(n)) > 0 Then shotHitYspeed(nn)=facLife(n,curF(n))
			If facDamage(n,curF(n)) > 0 Then shotDamage(nn)=facDamage(n,curF(n))
			shot(nn)=1
			If facChunk(n,curF(n)) > 0 Then makechunk(nn,xshot(nn),yshot(nn),shotDir(nn),facChunk(n,curF(n)))
			If facSound(n,curF(n)) > 0 And gamesound Then PlaySound soundFx(facSound(n,curF(n)))
			shotSuper(nn) = facVar1(n,curF(n))
			shotDrill(nn) = facVar2(n,curF(n))
			
		 Case 4 ;chunks

			makeChunk( 0,xfac(n,curF(n)),yfac(n,curF(n)),2,facType(n,curF(n)) )
			
		End Select
		
		curF(n)=curF(n)+1
		If curF(n) > FfacAmount(n) Then
			If Floop(n) =0 Then Fon(n)=0 Else curF(n)=1
		EndIf
EndIf
.noFac

End Function
;---- DECIDE PLAYER FRAME TO DRAW -------
Function selectDraw(n)
	If zFrozen(n) Then 
		drawFrozenState(n)
		Goto drawZ
	EndIf
	If wolvSpdFctr(n) = 0 Then wolvSpdFctr(n) = 1
	If wolverineRage(n) Then
		drawRageEffect(n)
	EndIf

	If zBlow(n)=1 Or zGrabbed(n)=1 Then 
		If zCurBlow(n)=5 Then isRunning(n)=0
		Goto drawZ
	End If
	
	If zDuck(n)=1 Then 
		zani(n)=3:zf(n)=1
		If isRunning(n) And zSpeed(n)=0 Then isRunning(n)=0
		Goto drawZ		
	End If			;ducking
	
	If zongnd(n)=0 And zhit(n)=0 And zjump2(n)=1 Then
		If isRunning(n) Then depleteStaminaBar(n, 1)
		Select True													;Jump flip
		Case (zjump2seq(n)=>1 And zjump2seq(n)=<5):zani(n)=5:zf(n)=1
		Case (zjump2seq(n)=>6 And zjump2seq(n)=<10):zani(n)=5:zf(n)=2
		Case (zjump2seq(n)=>11 And zjump2seq(n)=<15):zani(n)=5:zf(n)=3
		Case (zjump2seq(n)=>16 And zjump2seq(n)=<20):zani(n)=5:zf(n)=4
		Case (zjump2seq(n)>20):zani(n)=4:zf(n)=1
		End Select
		Goto drawZ
	EndIf
	
	If zongnd(n)=0 And zhit(n)=0 Then 	;mid air,,,,,,,
		If isRunning(n) Then depleteStaminaBar(n, 1)
		zani(n)=4:zf(n)=1:Goto drawZ	
	End If  
	If Not zhit(n) Then ;on ground
		If zspeed(n) <> 0 Then	;walking
			If isRunning(n) Then
				zRunSeq(n)=zRunSeq(n)+1
			Else
				zwalkseq(n)=zwalkseq(n)+1
				If zwalkseq(n)=1 Then zStanceSeq(n) = 0
			End If
		Else	;not walking/running
			zwalkseq(n)=0
			zRunSeq(n)=0
		EndIf
		If isRunning(n) Then drawRunSequence(n):Goto drawZ
		drawWalkSequence(n):Goto drawZ
	EndIf
	
	If zhit(n) And zongnd(n)=1 And zhitseq(n) > 15 Then 
		zani(n)=2:zf(n)=0:Goto drawZ
		Else
		
		a=10:b=25:c=35
		If zhitseq(n) => 1 And zhitseq(n) =< a Then zani(n)=2:zf(n)=1
		If zhitseq(n) > a And zhitseq(n) =< b Then zani(n)=2:zf(n)=2	
		If zhitseq(n) > b And zhitseq(n) =< c Then zani(n)=2:zf(n)=3
		If zhitseq(n) > c Then zani(n)=2:zf(n)=4
		
		Goto drawZ
	EndIf

	If zani(n)=0 Then zani(n)=4 : zf(n)=1
	
	.drawZ
	Select zFace(n)
	Case 4:
		zcurpic(n)=zpic_(curGuy(n),zani(n),zf(n))
	Default:
		zcurpic(n)=zpic(curGuy(n),zani(n),zf(n))
	End Select
	
	If EXTRAdRAW(N)=1
		Select zFace(n)
		Case 4:extrapic(n)=epic_(eani(n),ef(n))
		Default:extrapic(n)=epic(eani(n),ef(n))
		End Select
	EndIf
	
;------------Decided frame finished--------------------------------------------

End Function

;--------MAN CONTROL------------------------------------------------------------------------------
Function zman(n)

If zBlocked(n) Then
	zBlockSeq(n)=zBlockSeq(n)+1
	zNoMove(n)=1:zNoJump(n)=1:zBlock(n)=1
		If zBlockseq(n) > 8 Then 
			zHitByRect(n)=0
			Select zBlockDir(n)
			Case 2:zx(n)=zx(n)+zBlockSpeed(n)
			Case 4:zx(n)=zx(n)-zBlockSpeed(n)
			End Select 
		EndIf
	
	If zongnd(n)=0 And zblockseq(n) < zblocktime(n) -5 Then zBlockSeq(n)=zBlockTime(n)-5
		
	If zBlockSeq(n) > zBlockTime(n) Then zBlocked(n)=0:zBlockSeq(n)=0::zblowseq(n)=0:zblowstill(n)=0
EndIf

If zShotHitSeq(n,zShotByN(n)) > 10000 Then zShotHitSeq(n,zShotByN(n))=0:zShotByN(n)=0
If zHit(n)=0 And zBlocked(n)=0 Then zHitByRect(n)=0

If zStone(n)=1 And fightMode=2 Then
	If zLife(n) < 1 Then
		makeChunk(n,zx(n),zy(n)-15,2,zDeathChunk(n))
		playDeathSnd(n)
		zlives(n)=zlives(n)-1
		killZ(n)
	EndIf
EndIf

If zhit(n)=1 Then
	If zFrozen(n)=1 Then unFreeze(n)
	zhitseq(n)=zhitseq(n)+1 
	zSuperMove(n)=0
	If zhitseq(n) < zHitHold(n) Then justGotHit=1 Goto dontmove
			
		zUpFallspeed#(n)=zUpFallSpeed#(n)-.1
		If zUpFallSpeed#(n) < 2 Then zUpFallSpeed#(n)=2
		zFallspeed#(n)=zFallSpeed#(n)-.1
		If zFallSpeed#(n) < 0 Then zFallSpeed#(n)=0
		If zongnd(n)=0 Then
			If zFallDir(n)=4 Then zx(n)=zx(n)-zFallSpeed#(n)
			If zFallDir(n)=2 Then zx(n)=zx(n)+zFallSpeed#(n)
		EndIf
		
		If (zFallSpeed(n) > 8 Or zUpFallSpeed(n) > 8) And rendert > 1 Then makeChunk(n,zx(n),zy(n),2,16)
		
		If zhitseq(n) > 20 Then zhitbybox(n)=0
					
		If zhitseq(n) < zFallTime#(n) And zUpFallSpeed#(n) > 2 Then
			zy(n)=zy(n)-zUpFallSpeed#(n)
		EndIf
		
		If fightMode=2 And zFalltime(n) > 15 Then
			If zLife(n) < 1 Then
				makeChunk(n,zx(n),zy(n)-15,2,zDeathChunk(n))
				playDeathSnd(n)
				zlives(n)=zlives(n)-1
				killZ(n)
			EndIf
		EndIf
		
		;evasive roll when on ground
		If blockKey(n)=1 And (leftKey(n)=1 Or rightKey(n)=1) And zRollOnImpact(n)=1 And zongnd(n)=1 And zHitSeq(n) > 20 Then
			zHitByRect(n)=0
			zShotByN(n)=0 : zShotHitSeq(n,zShotByN(n))=0
			zhitbybox(n)=0
			zhit(n)=0:zhitseq(n)=0
			zBlockLife(n)=zBlockFull(n):zBouncedgnd(n)=0
			zCurBlow(n)=8 : zBlow(n)=1 : zBlowSeq(n)=7
			If gameSound=1 Then PlaySound shotwallsnd
			If rightKey(n)=1 Then
				zFace(n)=2:zBlowDir(n)=2
			Else
				zFace(n)=4:zBlowDir(n)=4
			EndIf
		EndIf
		
		If zhitSeq(n) > zFallTime#(n) And hitkey(n)=1 Then
			zHitByRect(n)=0
			zShotByN(n)=0 : zShotHitSeq(n,zShotByN(n))=0
			zhitbybox(n)=0
			zhit(n)=0:zhitseq(n)=0
			zBlockLife(n)=zBlockFull(n):zBouncedgnd(n)=0
		EndIf
		
		If zhitSeq(n) > 2 And zongnd(n)=1 And zBouncedgnd(n)=0 And zUpFallSpeed#(n) < 2.1 Then 
			If gameSound=1 Then PlaySound zhitwallsnd
			If zGotobj(n)>0 Then objhitsolid(zGotobj(n)):zGotobj(n)=0
			makeChunk(n,zx(n)-10,zy(n),2,16)
			makeChunk(n,zx(n)+10,zy(n),2,16)
			zhitbybox(n)=0
			zBouncedgnd(n)=1
			zhitSeq(n)=35
			zFallSpeed#(n)=3
			zFallTime#(n)=75
			zUpFallSpeed#(n)=4.5
			quake=1:quakeSeq=0
			zHitByRect(n)=0
		EndIf
.dontmove
If zhitseq(n) > 6 Then zHitByRect(n)=0		
EndIf

zShotHitSeq(n,zShotByN(n))=zShotHitSeq(n,zShotByN(n))+1

If ImageRectCollide(map,0,0,0,zoldx#(n)+zside(n),zy(n)-zheight(n),1,1) Then	;Head x ceiling  collision detection
	zy(n)=zy(n)+3:zjump(n)=0:zUpFallSpeed(n)=0:zUpFallTime(n)=0:zHitHead(n)=1
	Else
		If ImageRectCollide(map,0,0,0,zoldx#(n)-zside(n),zy(n)-zheight(n),1,1) Then
			zjump(n)=0 :zUpFallSpeed(n)=0:zUpFallTime(n)=0:zHitHead(n)=1
			zy(n)=zy(n)+3
		EndIf
EndIf

If zjump(n)=1 Or zblock(n)=1 Then zheight(n)=zupheight(n)
For vn=3 To zheight(n) Step 4	;Detects wall collision Next to player
	If ImageRectCollide(map,0,0,0,zx(n)+zside(n),zy(n)-vn,1,1) Then
		If zhit(n)=1 And zHitSeq(n) > 2 Then 
			makeChunk(n,zx(n)+zside(n)+3,zy(n)-5,2,16)
			makeChunk(n,zx(n)+zside(n)+3,zy(n)-25,2,16)
			quake=1:quakeSeq=0
		Select zFalldir(n)
			Case 4:zface(n)=4:zFallDir(n)=2
			Case 2:zFace(n)=2:zFallDir(n)=4
			End Select
			If gameSound = 1 Then PlaySound zhitwallsnd 
		EndIf
		zx(n)=zoldx(n):Exit
	EndIf
	If ImageRectCollide(map,0,0,0,zx(n)-zside(n),zy(n)-vn,1,1) Then
		If zhit(n)=1 And zHitSeq(n) > 2 Then
			makeChunk(n,zx(n)-(zside(n)+3),zy(n)-5,2,16)
			makeChunk(n,zx(n)-(zside(n)+3),zy(n)-25,2,16)
			quake=1:quakeSeq=0
		Select zFalldir(n)
			Case 4:zface(n)=4:zFallDir(n)=2
			Case 2:zFace(n)=2:zFallDir(n)=4
			End Select
			If gameSound = 1 Then PlaySound zhitwallsnd
		EndIf
		zx(n)=zoldx(n):Exit
	EndIf
	If ImageRectCollide(map,0,0,0,zx(n),zy(n)-vn,1,1) Then
		If zhit(n)=1 And zHitSeq(n) > 2 Then
			quake=1:quakeSeq=0
		Select zFalldir(n)
			Case 4:zface(n)=4:zFallDir(n)=2
			Case 2:zFace(n)=2:zFallDir(n)=4
			End Select
			If gameSound = 1 Then PlaySound zhitwallsnd 
		EndIf
		zx(n)=zoldx(n):Exit
	EndIf
Next

If zjump(n)=1 And zhit(n)=0 And zBlowStill(n)=0 And zJumping(n)=1 Then		;Makes it jump!
	zjumpseq(n)=zjumpseq(n)+1:zongnd(n)=0
	If zjumpseq(n) = 11 And jumpKeyDown(n)=0 And zjump2(n)=0 Then zjump(n)=0
	If zjumpSeq(n) <= zJumpLimit(n) - 5 Then zy(n)=zy(n)-zgravity(n):Goto asd
	If zjumpSeq(n) > zJumpLimit(n) - 5 Then zy(n)=zy(n)-(zgravity(n) / 2):Goto asd
 	.asd
	If zjumpseq(n) => zjumplimit(n) Then zjump(n)=0
EndIf
If zjump2(n)=1 Then zjump2seq(n)=zjump2seq(n)+1

;If player's feet is inside solid space, Then make it go up!
.checkFootAgain
If ImageRectCollide(map,0,0,0,zx(n)+zside(n),zy(n),1,1) Then zy(n)=zy(n)-1:zongnd(n)=1:Goto checkFootAgain
If ImageRectCollide(map,0,0,0,zx(n)-zside(n),zy(n),1,1) Then zy(n)=zy(n)-1:zongnd(n)=1:Goto checkFootAgain

zongnd(n)=1
If zjump(n)=0 Then
	If Not ImageRectCollide(map,0,0,0,zx(n)+zside(n),zy(n)+1,1,1) Then	;Is there ground under the player? If not Then fall!
		If Not ImageRectCollide(map,0,0,0,zx(n),zy(n)+1,1,1) Then
			If Not ImageRectCollide(map,0,0,0,zx(n)-zside(n),zy(n)+1,1,1) Then 
				If zNoGrav(n)=0 And zBlowStill(n)=0 And justGotHit=0 Then zy(n)=zy(n)+zgravity(n) 
				zongnd(n)=0
				If zonplat(n)=1 Then zongnd(n)=1
				;Goto isFalling
			EndIf
		EndIf	
	EndIf
EndIf

If zjump(n)=1 Then zongnd(n)=0
If zongnd(n)=1 Then zjump2(n)=0

If (zy(n) > dscrlimit Or zx(n) > rscrlimit+50 Or zx(n) < lscrlimit-50) And zon(n)=1 Then ;If off screen limits Then loses lives
	playDeathSnd(n)
	zLives(n)=zLives(n)-1
	killZ(n)
	
EndIf
	
End Function
;------------- Kill player -----------------------
Function killZ(n)
If gameMode <> 2 Then
	zx(n)=zxRespawn(n):zy(n)=zyRespawn(n)
Else
	zx(n)=xBase(zTeam(n)) : zy(n)=yBase(zTeam(n))
EndIf

If zGotObj(n) > 0 Then
	objHitSolid(zGotObj(n))
	obj(zGotObj(n))=0 : zGotObj(n)=0
EndIf

If (zLives(n) =< 0 And ifiniteLives=0) Or zHelper(n)=1 Then
	If zon(n)=1 Then
		If eventN(zdeadEvent(n))=0 Then eventN(zdeadEvent(n))=1 Else eventN(zdeadEvent(n))=0
		zon(n)=0:zx(n)=320:zy(n)=1000
		ztempShield(n)=0:zShieldSeq(n)=0
	EndIf
Else
	initZ(n)
	ztempShield(n)=1:zShieldSeq(n)=0
EndIf

End Function


;--------RENDER Z---------------------------------------------------------------------------------
Function renderZ(n)

	l_zani=zani(n) : l_zf=zf(n) : l_zpic=zcurpic(n)

If scrollMap=0 Then
	If zx(n) < -30 And zHelper(n)=0 Then 
		DrawImage zCurPic(n),20-(ImageWidth (zCurpic(n))/2),zy(n)-ImageHeight (zCurPic(n))+1
		DrawImage greenSign,20-(ImageWidth (greenSign)/2),zy(n)-ImageHeight (greenSign)+1
	Else
		If zx(n) > 670 And zHelper(n)=0 Then 
		DrawImage zCurPic(n),620-(ImageWidth (zCurpic(n))/2) ,zy(n)-ImageHeight (zCurPic(n))+1
		DrawImage greenSign,620-(ImageWidth (greenSign)/2) ,zy(n)-ImageHeight (greenSign)+1
		EndIf
	EndIf
EndIf


;If zCurPic(n) <> 0 Then 	;test
	DrawImage zCurPic(n),(zx(n)-(ImageWidth(zCurpic(n))/2))-xscr,(zy(n)-ImageHeight(zCurPic(n)) +2)-yscr
;Else
;	runtimeerror "paused! n="+n+" ani=" +zani(n) + "f="+zf(n)	;test
;EndIf

If zGotObj(n) <> 0 Then
	If drawObjOnZ(n)=1 Then
		If zani(n)=1 Then
			xED(n)=(zxHand(n,zf(n)) + xObjHand(zGotObj(n))): yED(n)=(zyHAnd(n,zf(n)) + yObjHand(zGotObj(n)))
		Else
			xED(n)=3:yED(n)=15
			If curGuy(n) = 14 Then xED(n)=0:yED(n)=22
		EndIf
		Select zFace(n)
			Case 2:DrawImage objPic(zGotObj(n),objCurFrame(zGotObj(n))),((zx(n)-(ImageWidth (objpic(zGotObj(n),objCurFrame(zGotObj(n))))/2)) + xED(n))-xscr, ((zy(n)-ImageHeight(objPic(zGotObj(n),objCurFrame(zGotObj(n))))) -yED(n))-yscr
			Case 4:DrawImage objPic_(zGotObj(n),objCurFrame(zGotObj(n))),((zx(n)-(ImageWidth (objpic_(zGotObj(n),objCurFrame(zGotObj(n))))/2)) - xED(n))-xscr, ((zy(n)-ImageHeight(objpic_(zGotObj(n),objCurFrame(zGotObj(n))))) -yED(n))-yscr

		End Select
	EndIf
	drawObjOnZ(n)=1
EndIf

If extraDraw(n)=1 Then
	Select zFace(n)
		Case 2: DrawImage extraPic(n),((zx(n)-(ImageWidth (extrapic(n))/2)) + xED(n))-xscr, ((zy(n)-ImageHeight(extrapic(n))) -yED(n))-yscr
		Case 4: DrawImage extraPic(n),((zx(n)-(ImageWidth (extrapic(n))/2)) - xED(n))-xscr, ((zy(n)-ImageHeight(extrapic(n))) -yED(n))-yscr
	End Select
EndIf

End Function


;--------KEY INPUTS-----------------------------------------------------------------------------------
Function Getinput()

For n= 1 To zzamount
	zoldx(n)=zx(n):zoldy(n)=zy(n)
Next
For n=1 To zzamount
	hitKey(n)=0:upKey(n)=0:leftKey(n)=0:rightKey(n)=0:downKey(n)=0:jumpKey(n)=0:shotKey(n)=0
	jumpKeyDown(n)=0:runkey(n)=0:blockKey(n)=0:specialkey(n)=0:grabKey(n)=0:superKey(n)=0
	rightKeyhit(n)=0:leftKeyHit(n)=0:counterkey(n)=0:extraSpecialkey(n)=0
	If zBlocked(n) Then zBlock(n)=1:zBlow(n)=1 Else zblock(n)=0
Next
If KeyHit(1) Then   ;pause button
	gamePaused=1 : menuOption=5
	delay(100)
	flushkeys() : flushmouse() : flushjoy()
	If gameSound Then PlaySound ddhitSnd
EndIf

If KeyHit(87) Then	;F11 to take snap shot/ screen shot
temppic=CreateImage(640,480)
CopyRect xscr,yscr,640,480,0,0,FrontBuffer(),ImageBuffer(temppic)
SaveBuffer(ImageBuffer(temppic)),"pic"+ screenShotN +".bmp"
screenShotN = screenShotN + 1
FreeImage temppic
EndIf

If NoUserInput=0 Then 

For k= 1 To zzamount
	If zAI(k)=1 And zon(k)=1 Then
		If zFrozen(k)=1 Then Goto SkipAI
		If Not zUseSpecialAI(k) Then 
			AI(k,aiTarget(k))
		Else
			zSpecialAI(k,aiTarget(k))
		EndIf
		.SkipAI
	EndIf
Next
	
For n = 1 To zzamount		

If Not zai(n) Then 
Select zController(n)
Case 0  
	If KeyDown(upK(n)) Then upKey(n)=1:hitKey(n)=1
	If KeyDown(downK(n)) Then downKey(n)=1:hitKey(n)=1
	If KeyDown(leftK(n)) Then leftKey(n)=1:hitKey(n)=1
	If KeyDown(rightK(n)) Then rightKey(n)=1:hitKey(n)=1
	If KeyHit(leftK(n)) Then leftKeyhit(n)=1:hitKey(n)=1
	If KeyHit(rightK(n)) Then rightKeyhit(n)=1:hitKey(n)=1
	If KeyHit(specialK(n)) Then specialkey(n)=1:hitKey(n)=1
	If KeyHit(shotK(n)) Then shotKey(n)=1:hitKey(n)=1
	If KeyHit(jumpK(n)) Then jumpKey(n)=1 :hitKey(n)=1
	If KeyDown(jumpK(n)) Then jumpKeyDown(n)=1:hitKey(n)=1
	If KeyHit(grabK(n)) Then grabKey(n)=1:hitKey(n)=1
	If KeyDown(blockK(n)) Then blockKey(n)=1:hitKey(n)=1
	;If shotkey(n)=1 And blockkey(n)=1 Then  grabKey(n)=1
	If blockKey(n)=1 And specialKey(n)=1 Then superKey(n)=1
	If blockKey(n)=1 And shotkey(n)=1 Then counterKey(n)=1
	If blockKey(n)=1 And grabKey(n)=1 Then extraSpecialkey(n)=1:grabKey(n)=0

Case 1
	If JoyYDir(controllerPort(n))=-1 Then upKey(n)=1:hitKey(n)=1
	If JoyXDir(controllerPort(n))=-1 Then leftKey(n)=1:hitKey(n)=1
	If JoyXDir(controllerPort(n))=1 Then rightKey(n)=1:hitKey(n)=1
	
	b_JoyHit = False
	If JoyX(controllerPort(n)) < -.5 And b_JoyHit = False Then ; Hit Left
	    leftKeyhit(n)=1 : b_JoyHit = True:hitKey(n)=1
	EndIf
	If JoyX(controllerPort(n)) > .5 And b_JoyHit = False Then ; Hit Right
	     rightKeyhit(n)=1 : b_JoyHit = True:hitKey(n)=1
	EndIf
	If JoyX(controllerPort(n)) > -.1 And JoyX(controllerPort(n)) < .1 Then b_JoyHit = False ; Reset Flag
	If JoyYDir(controllerPort(n))=1 Then downkey(n)=1:hitKey(n)=1
	
	If JoyHit(specialK(n),controllerPort(n)) Then specialkey(n)=1:hitKey(n)=1
	If JoyHit(shotK(n),controllerPort(n)) Then shotKey(n)=1:hitKey(n)=1
	If JoyHit(jumpK(n),controllerPort(n)) Then jumpKey(n)=1 :hitKey(n)=1
	If JoyHit(grabK(n),controllerPort(n)) Then grabKey(n)=1 :hitKey(n)=1
	If JoyDown(jumpK(n),controllerPort(n)) Then jumpKeyDown(n)=1 :hitKey(n)=1
	If JoyDown(blockK(n),controllerPort(n)) Then blockKey(n)=1:hitKey(n)=1
	;If shotkey(n) And blockkey(n) Then grabKey(n)=1:hitKey(n)=1
	If blockKey(n)=1 And specialKey(n)=1 Then superKey(n)=1
	If blockKey(n)=1 And shotkey(n)=1 Then counterKey(n)=1
	If blockKey(n)=1 And grabkey(n)=1 Then extraSpecialKey(n)=1::grabKey(n)=0

End Select
EndIf
Next

EndIf

For n = 1 To zzamount
If zon(n)=0 Then Goto noshot

zDuck(n)=0:zHeight(n)=zUpHeight(n)
If downKey(n)=1 And zHit(n)=0 And zongnd(n)=1 And zBlow(n)=0 Then
	zDuck(n)=1:zHeight(n)=zDuckHeight(n)
	If isRunning(n)=0 Then
		zSpeed(n)=0
	Else
		If zSpeed#(n) < 0.1 And zSpeed#(n) > -0.1 Then
			zSpeed(n)=0
			isRunning(n)=0
		Else
			If zFace(n)=2 Then zSpeed#(n)=zSpeed#(n)-(zAcc#(n)*1.5)
			If zFace(n)=4 Then zSpeed#(n)=zSpeed#(n)+(zAcc#(n)*1.5)
		End If
	End If
EndIf

If downKey(n)=1  And jumpKey(n)=1 And zonplat(n)=1 And zonThickPlat(n)=0 And zHit(n)=0 And zblow(n)=0 Then
	zForceAntiplat(n)=1:zantiPLatTime(n)=5:zantiPlatSeq(n)=0:Goto noshot
EndIf

If zhit(n)=1 And zongnd(n)=1 Then zHeight(n)=zDuckHeight(n)
;--walking/running/speed/accelaration stuff---------------------------------------------------------------------
If rightkey(n)=1 Then
	zSpeed#(n)=zSpeed#(n)+zAcc#(n):rk=1
	If isRunning(n) Then
		If zSpeed#(n) > zTopRunningSpeed#(n) Then zSpeed#(n) = zTopRunningSpeed#(n)
	Else
		If zSpeed#(n) > zTopSpeed#(n) Then zSpeed#(n) = zTopSpeed#(n)
	End If
	Goto PressedDi
EndIf

If leftkey(n)=1 Then
	zSpeed#(n)=zSpeed#(n)-zAcc#(n):lk=1
	If isRunning(n) Then
		If zSpeed#(n) < zTopRunningSpeed#(n) - (zTopRunningSpeed#(n)*2) Then zSpeed#(n) = zTopRunningSpeed#(n) - (zTopRunningSpeed#(n)*2)
	Else
		If zSpeed#(n) < zTopSpeed#(n) - (zTopSpeed(n)*2) Then zSpeed#(n) = zTopSpeed#(n) - (zTopSpeed(n)*2)
	End If
	Goto PressedDi
EndIf

If zspeed(n) > 0 Then
	zSpeed#(n)=zSpeed#(n)-zAcc#(n)
	If ZSPEED(N) < 0 Then zspeed(n)=0:isRunning(n)=0
EndIf
If zspeed(n) < 0 Then
	zSpeed#(n)=zSpeed#(n)+zAcc#(n)
	If ZSPEED(N) > 0 Then zspeed(n)=0:isRunning(n)=0
EndIf
.PressedDi

If isRunning(n) Then
	If zSpeed#(n) > zTopRunningSpeed(n) Then zSpeed#(n) = zTopRunningSpeed#(n)
	If zSpeed#(n) < zTopRunningSpeed(n)-(zTopRunningSpeed(n)*2) Then zSpeed#(n)=zTopRunningSpeed(n)-(zTopRunningSpeed(n)*2)
Else
	If zSpeed#(n) > zTopSpeed(n) Then zSpeed#(n) = zTopSpeed#(n)
	If zSpeed#(n) < zTopSpeed(n)-(zTopSpeed(n)*2) Then zSpeed#(n)=zTopSpeed(n)-(ztopSpeed(n)*2)
End If

;------------------------------------------------------------------------------------------------------

If zhit(n)=0 And zNoMove(n)=0 And zBlowStill(n)=0 Then
	If rightkey(n) And rk=1 Then zface(n)=2
	If leftkey(n) And lk=1 Then zface(n)=4
	If zDuck(n)=0 Or isRunning(n) Then zx(n)=zx(n)+zSpeed#(n)
EndIf

;--blocking--------------------------------------------------------------------------------------------
If blockKey(n)=1 And (leftKeyhit(n)=1 Or rightkeyhit(n)=1) Then 
	If zhit(n)=0 And zongnd(n)=1 And zBlow(n)=1 And zCurBlow(n)=0 And zBlocked(n)=0 Then
		If leftKeyhit(n) Then zFace(n)=4 Else zFace(n)=2
		zBlow(n)=1:zBlowSeq(n)=0	
		zCurBlow(n)=8:zBlowDir(n)=zFace(n)  ;Dodge move
	EndIf
EndIf
;------------------------------------------------------------------------------------------------------

;----pickup iten---------------------------
If shotKey(n) And downkey(n)=0 And upkey(n)=0 And zblow(n)=0 And zHit(n)=0 And zGotobj(n)=0 Then
	For nn=1 To objAmount
		If xobj(nn) => zx(n)-14 And xObj(nn) =< zx(n)+14 And objTaken(nn)=0 And objHurt(nn)=0 And obj(nn)=1 Then
			If yobj(nn) => zy(n) -20 And yobj(nn) =< zy(n) +3 Then 
				zblow(n)=1:zblowseq(n)=0:zCurBlow(n)=13:zBlowDir(n)=zFace(n)
			EndIf
		EndIf
	Next
EndIf

;----iten use-----------------------------
If grabKey(n) And zGotObj(n)>0 And obj(zgotObj(n))=1 And zblow(n)=0 And zHit(n)=0 Then
	zblow(n)=1:zblowseq(n)=0:zCurBlow(n)=6:zBlowDir(n)=zFace(n)
	Goto noShot
EndIf

If grabKey(n) And zGotObj(n)=0 And zblow(n)=0 And zHit(n)=0 Then
	zblow(n)=1:zblowseq(n)=0:zCurBlow(n)=15:zBlowDir(n)=zFace(n)
	Goto noShot
EndIf

If shotkey(n) And zGotObj(n)>0 And zblow(n)=0 And zHit(n)=0 And zduck(n)=0 Then
	If beatIten(zGotObj(n)) = 1 And zongnd(n)=1 And upkey(n)=0 Then 
		zblow(n)=1:zblowseq(n)=0:zCurBlow(n)=beatItenType(zGotObj(n)):zBlowDir(n)=zFace(n):remImune(n)
		Goto noshot
	Else
	  If beatIten(zGotObj(n))=0	Then
		zblow(n)=1:zblowseq(n)=0:zCurBlow(n)=6:zBlowDir(n)=zFace(n)
		;throwObj(n,zx(n),zy(n)-20,zFace(n))
		Goto noShot
	  EndIf
	EndIf
EndIf
;---------------------------------------------
If superKey(n)=1 And zhit(n)=0 And zBLocked(n)=0 And (zCurBlow(n) < 1 Or zBlow(n)=0) And zSuperBar(n) => 100 Then
	zBlow(n)=1:zBlowSeq(n)=0:zBlowseq2(n)=0:remImune(n)
	zCurBlow(n)=14:zBlowDir(n)=zFace(n)
	Goto noShot	
EndIf

If counterKey(n)=1 And zhit(n)=0 And zBLocked(n)=0 And (zCurBlow(n) < 1 Or zBlow(n)=0) Then
	zBlow(n)=1:zBlowSeq(n)=0:zBlowseq2(n)=0:remImune(n)
	zCurBlow(n)=16:zBlowDir(n)=zFace(n)
EndIf

If extraSpecialKey(n)=1 And zhit(n)=0 And zBLocked(n)=0 And (zCurBlow(n) < 1 Or zBlow(n)=0) Then
	zBlow(n)=1:zBlowSeq(n)=0:zBlowseq2(n)=0:remImune(n)
	zCurBlow(n)=17:zBlowDir(n)=zFace(n)
EndIf

If specialKey(n)=1 And (zhit(n)=0 And zBlow(n)=0) Then
	
  If Not (noAirSpecial=1 And zongnd(n)=0) Then	
	If upKey(n)=1 Then
		zBlow(n)=1:zBlowSeq(n)=0:zBlowseq2(n)=0:remImune(n)
		zCurBlow(n)=5:zBlowDir(n)=zFace(n)
		Goto noShot	
	EndIf
	If downKey(n)=1 Then
		zBlow(n)=1:zBlowSeq(n)=0:zBlowseq2(n)=0:remImune(n)
		zCurBlow(n)=9:zBlowDir(n)=zFace(n)
		Goto noShot	
	EndIf

		zBlow(n)=1:zBlowSeq(n)=0:zBlowseq2(n)=0:remImune(n)
		zCurBlow(n)=7:zBlowDir(n)=zFace(n)
		Goto noShot	
  EndIf	
	
EndIf


If shotKey(n)=1 And zhit(n)=0 And zBlow(n)=0 Then
	
	If zongnd(n)=0 Then
		zBlow(n)=1:zBlowSeq(n)=0:remImune(n)
		zCurBlow(n)=2:zBlowDir(n)=zFace(n)
		Goto noShot	
	EndIf
	If zongnd(n)=1 And upKey(n)=1 Then
		zBlow(n)=1:zBlowSeq(n)=0:remImune(n)
		zCurBlow(n)=10:zBlowDir(n)=zFace(n)
		Goto noShot	
	EndIf
	If zongnd(n)=1 And downKey(n)=1 Then
		zBlow(n)=1:zBlowSeq(n)=0:remImune(n)
		zCurBlow(n)=4:zBlowDir(n)=zFace(n)
		Goto noShot	
	EndIf
	If zongnd(n)=1 Then
		zBlow(n)=1:zBlowSeq(n)=0:remImune(n)
		zCurBlow(n)=1:zBlowDir(n)=zFace(n)
		Goto noShot	
	EndIf
EndIf

If jumpKey(n)=1 Then    ;jumping
	If zhit(n)=0 And zBlow(n)=0 And zNoJump(n)=0 Then
	downKey(n)=0
		If zjump(n)=0 And zongnd(n)=1 Then
			If gamesound Then PlaySound zJumpSnd(n)
			zjump(n)=1:zjumpseq(n)=0:zongnd(n)=0
				If zblockLife(n) > zblockfull(n) Then zblockLife(n)=zblockfull(n)
		Else
			If zjump2(n)=0 And noDoubleJump=0 Then
				zjump2(n)=1:zjump(n)=1:zjumpseq(n)=0:zjump2seq(n)=0
				If gamesound And zJumpSnd2(n) <> 0 Then 
					PlaySound zJumpSnd2(n)
				Else
					PlaySound zJumpSnd(n)
				EndIf
			EndIf
		EndIf
	EndIf
EndIf


If blockKey(n)=1 And zHit(n)=0 And zBlow(n)=0 And zongnd(n)=1 Then 
	zCurBlow(n)=0:zBlow(n)=1:zBlowDir(n)=zFace(n):downKey(n)=0:Goto noShot
EndIf

.NoShot


Next

End Function
;--------- Return Next shot number -----------------------------------------------------------
Function getShot()

For i=1 To 200
	If shot(i)=0 Then
		Return i
	EndIf	
Next
		
End Function

;--------SHOTS---------------------------------------------------------------------------------
Function shots(n)

For nn=1 To shotamount	;shot x shot collision
	If shot(nn) And nn<>n Then
		For q=0 To shotsize(n) Step 2
			If xshot(n)+q => xshot(nn) And xshot(n)+q =< xshot(nn)+shotsize(nn) And zteam(shotOwner(n)) <> zteam(shotOwner(nn)) Then
				If yshot(n)-(shotHeight(n)/2) => yshot(nn)-shotheight(nn) And yshot(n)-(shotHeight(n)/2) =< yshot(nn) Then
					If shotSuper(n)=0 Then
						shot(n)=0:
						makeChunk(0,xshot(n),yshot(n),2,shotChunkType(n))
					EndIf
					If shotSuper(nn)=0 Then	
						shot(nn)=0:
						makeChunk(0,xshot(nn),yshot(nn),2,shotChunkType(nn))
					EndIf
					If gamesound Then PlaySound shotSound(n):Exit
				EndIf
			EndIf	
		Next
	EndIf
Next

;shot trails
If shotTrailType(n) > 0 Then
  Select shotTrailType(n)
	Case 1     ;little smokes
	  If rendert=1 Then makeChunk(0,xShot(n),yshot(n),2,28)
	Case 2     ;bright dots
	  If shotCurFrame(n) = 2 And rendert=1 Then
	  	makeChunk(n,xShot(n)+rand(-3,3),yShot(n)-rand(0,3),shotDir(n),13)
	  	makeChunk(n,xShot(n)+rand(-3,3),yShot(n)-rand(0,3),shotDir(n),13)
	  EndIf
	
  End Select
EndIf
Local newShotHeight,xAxisShotPos,yAxisShotPos,objShotWidth,objShotHeight

Select shotDir(n)	;shot x wall 
Case 2
	shotsizeL(n)=shotspeed(n)
	For q=0 To shotspeed(n) Step 2
		If shotBounce(n)=1 And ImageRectCollide(map,0,0,0,xshot(n)+q,yshot(n)+1,1,1)Then
  			yshot(n)=yshot(n)-2
		Else
			If shotBounce(n)=1 And ImageRectCollide(map,0,0,0,xshot(n)+q,yshot(n)-(shotHeight(n)+1),1,1) Then
				yshot(n)=yshot(n)+2
			EndIf
		EndIf
		If ImageRectCollide(map,0,0,0,xshot(n)+q,yshot(n),1,1) Or ImageRectCollide(map,0,0,0,xshot(n)+q,yshot(n)-shotHeight(n),1,1) Then
			If shotBounce(n)=1 Then
				If shotDir(n)=2 Then
					xshot(n)=xshot(n)-shotsize(n)
				Else
					xshot(n)=xshot(n)+shotsize(n)
				EndIf
			EndIf
						
			If shotBounce(n)=1 Then
				shotsizeL(n)=q
				makechunk(shotDir(n),xshot(n)+q,yShot(n),2,1)
				If gameSound =1 Then PlaySound zhitwallSnd
				If shotDir(n)=2 Then shotDir(n)=4 Else shotDir(n)=2
			Else
				shot(n)=0:
				shotsizeL(n)=q
				makechunk(shotDir(n),xshot(n)+q,yShot(n),2,shotChunkType(n))
				;If gameSound =1 Then PlaySound shotsound(n)
				If gameSound =1 Then PlaySound zHitWallSnd
				shothit=1
			EndIf
		EndIf
		For nn=1 To platAmount	;shot x plat collision  ,    dir = 2
			
			If xShot(n)+q > xplat(nn) And xShot(n)+q < xplat(nn)+platWidth(nn) And platHeight(nn)>1 Then 
			  If shotBounce(n)=1 And yshot(n)+1 > yplat(nn) And yshot(n)+1 < yplat(nn)+4 Then
			  	yshot(n)=yshot(n)-2
			  Else
				If shotBounce(n)=1 And yshot(n)-(shotHeight(n)+1) > yplat(nn)+(platHeight(nn)-4) And yshot(n)-(shotHeight(n)+1) < yplat(nn)+platHeight(nn) Then
				  	yshot(n)=yshot(n)+2
				EndIf
			  EndIf	
				If yshot(n) => yplat(nn) And yshot(n) < yplat(nn)+platHeight(nn)+shotHeight(n) Then
					
					If shotBounce(n)=1 Then
						shotsizeL(n)=q
						makechunk(shotDir(n),xshot(n)+q,yShot(n),2,1)
						If gameSound =1 Then PlaySound zhitwallSnd
						;If xShot(n) =< xPlat(nn)+(platWitdh(nn)/2)shotDir(n)=2 Then shotDir(n)=4 Else shotDir(n)=2
						If xShot(n) =< xPlat(nn)+(platWidth(nn)/2) Then
							xShot(n)=xPlat(nn) : shotDir(n)=4
						Else
							xShot(n)=xPlat(nn)+platWidth(nn) : shotDir(n)=2
						EndIf
						shothit=1
					Else
						If shotExplosive(n) > 0 Then shotexp(n,xShot(n),yShot(n),shotExplosive(n)):shot(n)=0
						shot(n)=0:
						shotsizeL(n)=q
						makechunk(shotDir(n),xshot(n)+q,yShot(n),2,shotChunkType(n))
						;If gameSound =1 Then PlaySound shotsound(n)	
						shothit=1
					EndIf
				EndIf
			EndIf
		Next
		If shothit Then Exit
	Next 
	
	If shotHitMode(n) > 2 And shotHitMode(n) < 6 Then 
		handleSubZeroProjectiles(nn, n, xshot(n), yshot(n))
		Goto shotDone
	EndIf
	For qh=0 To shotHeight(n) Step 6 ;shot x player collision
	For q=0 To shotsizeL(n) Step 1
		xAxisShotPos=xshot(n)+q
		yAxisShotPos=yshot(n)-qh
		objShotWidth=1
		objShotHeight=1
		For nn= 1 To zzamount
			oldshield = zShield(nn)
			If shotSuper(n)=1 Then zShield(nn)=0
			If zShield(nn)=0 And zon(nn)=1 And (teamAttack=1 Or zteam(shotOwner(n)) <> zteam(nn)) Then
			If Not nn=shotowner(n) Then
			  If Not (zShotByN(nn) = n And zShotHitSeq(nn, n) < shotImmuneTime(n)) Then	
				If teamAttack=0 And zteam(shotOwner(n)) = zteam(nn) Then Exit ;Goto shotDone
				If ImageRectCollide(zCurPic(nn),zx(nn)-(ImageWidth(zCurPic(nn))/2),zy(nn)-ImageHeight(zCurPic(nn))+1,0,xAxisShotPos,yAxisShotPos,objShotWidth,objShotHeight) Then
					If shotExplosive(n) > 0 Then shotexp(n,xShot(n),yShot(n),shotExplosive(n)):shot(n)=0
					If Not shotDrill(n) Then shot(n)=0
					zShotByN(nn)=n : zShotHitSeq(nn,n)=0
					makechunk(shotDir(n),zx(nn),yShot(n),2,shotChunkType(n))
					If zblock(nn)=1 Then
						zBlocked(nn)=1:zBlockSeq(nn)=0:zface(nn)=4:zblowDir(nn)=zface(nn)
						zBlockTime(nn)=shotImpact(n)*2:zBlockDir(nn)=2
						zBLockLife(nn)=zBlockLife(nn)-shotDamage(n)
						If gameSound Then PlaySound blockedsnd
						If zBlockLife(nn) <1 Then
							zBlock(nn)=0:zBlocked(nn)=0
							If gameSound Then PlaySound brokensnd
						EndIf
					EndIf
					If Not zBlock(nn) And shotSuper(n)=0 Then
						zlife(nn)=zlife(nn)-shotdamage(n)
						zDamage#(nn)=zDamage#(nn)+shotDamage(n)
						If zStone(nn)=0 Then
							zFallDir(nn)=2
							zface(nn)=4:zjump(nn)=0:zBouncedgnd(nn)=0
							zhit(nn)=1
							calcShot(nn,n)
							zBlow(nn)=0:zBlowStill(nn)=0:zHitSeq(nn)=0
						EndIf
						If shotHitTrail(n) > 0 Then zTrail(nn)=1:zTrailSeq(nn)=0:zTrailType(nn)=shotHitTrail(n)
						
						If gameSound =1 Then PlaySound shotsound(n)							
					EndIf
					If zLife(nn) < 1 Then zScore(shotOwner(n))=zScore(shotOwner(n))+1
					Goto shotDone
				EndIf 
			  EndIf
			EndIf
			EndIf
			zShield(nn)=oldShield
		Next	
	Next
	Next
	
	If xshot(n) > rscrlimit Then shot(n)=0

Case 4
	shotsizeL(n)=shotspeed(n)
	For q=0 To shotspeed(n) Step 1
		If shotBounce(n)=1 And ImageRectCollide(map,0,0,0,xshot(n)-q,yshot(n)+1,1,1)Then
			yshot(n)=yshot(n)-2
        Else
			If shotBounce(n)=1 And ImageRectCollide(map,0,0,0,xshot(n)-q,yshot(n)-(shotHeight(n)+1),1,1) Then
				yshot(n)=yshot(n)+2
			EndIf
		EndIf
		If ImageRectCollide(map,0,0,0,xshot(n)-q,yshot(n),1,1) Or ImageRectCollide(map,0,0,0,xshot(n)-q,yshot(n)-shotheight(n),1,1)Then
			If shotBounce(n)=1 Then
				If shotDir(n)=2 Then
					xshot(n)=xshot(n)-shotsize(n)
				Else
					xshot(n)=xshot(n)+shotsize(n)
				EndIf
			EndIf
			If shotBounce(n)=1 Then
			 	shotsizeL(n)=q
				makechunk(shotDir(n),xshot(n)-q,yShot(n),2,1)
				If gameSound =1 Then PlaySound zhitwallSnd
				If shotDir(n)=2 Then shotDir(n)=4 Else shotDir(n)=2
			Else
				shot(n)=0
				shotsizeL(n)=q
				makechunk(shotDir(n),xshot(n)-q,yShot(n),2,shotchunktype(n))
				;If gameSound =1 Then PlaySound shotsound(n)
				shothit=1
			EndIf
		EndIf
		For nn=1 To platAmount	;shot x plat collsion ,  dir = 4
			If xShot(n)-q > xplat(nn) And xShot(n)-q < xplat(nn)+platWidth(nn) And platHeight(nn)>1 Then 
			  If shotBounce(n)=1 And yshot(n)+1 > yplat(nn) And yshot(n)+1 < yplat(nn)+4 Then
			  	yshot(n)=yshot(n)-2
			  Else
				If shotBounce(n)=1 And yshot(n)-(shotHeight(n)+1) > yplat(nn)+(platHeight(nn)-4) And yshot(n)-(shotHeight(n)+1) < yplat(nn)+platHeight(nn) Then
				  	yshot(n)=yshot(n)+2
				EndIf
			  EndIf
				If yshot(n) > yplat(nn) And yshot(n) < yplat(nn)+platHeight(nn)+shotHeight(n) Then
					
					If shotBounce(n)=1 Then
						shotsizeL(n)=q
						makechunk(shotDir(n),xshot(n)+q,yShot(n),2,1)
						If gameSound =1 Then PlaySound zhitwallSnd
						;If shotDir(n)=2 Then shotDir(n)=4 Else shotDir(n)=2
						If xShot(n) =< xPlat(nn)+(platWidth(nn)/2) Then
							xShot(n)=xPlat(nn) : shotDir(n)=4
						Else
							xShot(n)=xPlat(nn)+platWidth(nn) : shotDir(n)=2
						EndIf
						shothit=1
					Else
						If shotExplosive(n) > 0 Then shotexp(n,xShot(n),yShot(n),shotExplosive(n)):shot(n)=0
						shot(n)=0:
						shotsizeL(n)=q
						makechunk(shotDir(n),xshot(n)-q,yShot(n),2,shotChunkType(n))
						;If gameSound =1 Then PlaySound shotsound(n)	
						shothit=1 
					EndIf
				EndIf
			EndIf
		Next
		If shothit Then Exit
	Next 
	
	If shotHitMode(n) > 2 And shotHitMode(n) < 6 Then 
		handleSubZeroProjectiles(nn, n, xshot(n), yshot(n))
		Goto shotDone
	EndIf
	For qh=0 To shotHeight(n) Step 6
	For q=0 To shotsizeL(n) Step 1
		xAxisShotPos=xshot(n)+q
		yAxisShotPos=yshot(n)-qh
		objShotWidth=1
		objShotHeight=1
		For nn= 1 To zzamount
			oldshield = zShield(nn)
			If shotSuper(n)=1 Then zShield(nn)=0
			If zShield(nn)=0 And zon(nn)=1 And (teamAttack=1 Or zteam(shotOwner(n)) <> zteam(nn)) Then

			If Not nn=shotowner(n) Then
			  If Not (zShotByN(nn) = n And zShotHitSeq(nn,n) < shotImmuneTime(n)) Then
				If teamAttack=0 And zteam(shotOwner(n)) = zteam(nn) Then Goto shotDone
				If ImageRectCollide(zCurPic(nn),zx(nn)-(ImageWidth(zCurPic(nn))/2),zy(nn)-ImageHeight(zCurPic(nn))+1,0,xAxisShotPos,yAxisShotPos,objShotWidth,objShotHeight) Then
					If shotExplosive(n) > 0 Then shotexp(n,xShot(n),yShot(n),shotExplosive(n)):shot(n)=0
					If Not shotDrill(n) Then shot(n)=0
					zShotByN(nn)=n : zShotHitSeq(nn,n)=0
					makechunk(shotDir(n),zx(nn),yShot(n),2,shotChunkType(n))
					If zblock(nn)=1 Then
						zBlocked(nn)=1:zBlockSeq(nn)=0:zface(nn)=2:zblowDir(nn)=zface(nn)
						zBlockTime(nn)=shotImpact(n)*2:zBlockDir(nn)=4
						zBLockLife(nn)=zBlockLife(nn)-shotDamage(n)
						If gameSound Then PlaySound blockedsnd
						If zBlockLife(nn) <1 Then 
							zBlock(nn)=0:zBlocked(nn)=0
							If gameSound Then PlaySound brokensnd
						EndIf

					EndIf
					If Not zBlock(nn) And shotSuper(n)=0 Then
						zlife(nn)=zlife(nn)-shotdamage(n)
						zDamage#(nn)=zDamage#(nn)+shotDamage(n)
						If zStone(nn)=0 Then
							zFallDir(nn)=4:zhit(nn)=1
							zface(nn)=2:zjump(nn)=0:zBouncedgnd(nn)=0
							calcShot(nn,n)
							zBlow(nn)=0:zBlowStill(nn)=0:zHitSeq(nn)=0
						EndIf
						If gameSound =1 Then PlaySound shotsound(n)
						If shotHitTrail(n) > 0 Then zTrail(nn)=1:zTrailSeq(nn)=0:zTrailType(nn)=shotHitTrail(n)
					EndIf					
					If zLife(nn) < 1 Then zScore(shotOwner(n))=zScore(shotOwner(n))+1
					Goto shotDone
				EndIf
			  EndIf 
			EndIf
			EndIf
			zShield(nn)=oldshield
		Next	
	Next
	Next
	If xshot(n) < lscrlimit Then shot(n)=0

End Select

.shotDone
;xShot(n)=oldxshot(n)
If shotHitMode(n)=5 Then yshot(n)=yshot(n)+shotspeed(n)
Select shotDir(n)
	Case 2:
		xshot(n)=xshot(n)+shotspeed(n)
	Case 4:
		xshot(n)=xshot(n)-shotspeed(n)
End Select

If shotFollowOwner(n) Then 
	If yShot(n) > zy(shotOwner(n))-20 Then yShot(n)=yShot(n)-1
	If yShot(n) < zy(shotOwner(n))-20 Then yShot(n)=yShot(n)+1
EndIf

shotFrameSeq(n)=shotFrameSeq(n)+1
If shotFrameSeq(n) > shotFrameTime(n) Then
	shotFrameSeq(n)=0 : shotCurFrame(n)=shotCurFrame(n)+1

	If shotCurFrame(n) > shotFramesAmount(n) Then shotCurFrame(n)=1
EndIf

shotDurationSeq(n)=shotDurationSeq(n)+1
If shotUturn(n)=1 And shotDurationSeq(n) => shotDuration(n) Then
		shotDurationSeq(n)=shotDurationSeq(n)-1
		shotSpeed(n)=shotSpeed(n) - shotAcc(n) 
		If shotspeed(n) <= 0 Then 
			shotDurationSeq(n)=0
			If shotUturnseq(n) => shotUturnAmount(n) Then
				shotUturn(n)=0
			Else
				shotUturnSeq(n)=shotUturnseq(n)+1
			EndIf
			shotDuration(n)=shotDuration2(n)
			If shotDir(n) =2 Then shotDir(n)=4 Else shotDir(n)=2
		EndIf
Else	
	If shotUseAcc(n)=1 Then
		shotSpeed(n)=shotSpeed(n) + shotAcc(n)
		If shotSpeed(n) > shotmaxSpeed(n) Then shotspeed(n)=shotMaxSpeed(n)
	EndIf
EndIf
	
If shotDurationSeq(n) > shotDuration(n) Then
	shot(n)=0:makechunk(shotDir(n),xShot(n),yShot(n),2,shotchunktype(n))
EndIf
End Function


;--------DO RECT HITS---------------------------------------------------------------------------
Function Rects(n)

For nn=1 To shotAmount
	If shot(nn) And yShot(nn) > yRect(n) And yShot(nn) < yRect(n)+hRect(n) Then
		If xShot(nn) > xRect(n) And xShot(nn) < xRect(n)+wRect(n) Then
			shot(nn)=0
			makechunk(0,xshot(nn),yShot(nn),2,shotChunkType(nn))
		EndIf
	EndIf
Next

For nn=1 To zzamount
  If teamAttack=0 And (zteam(rectOwner(n)) = zteam(nn)) Then Goto skipRect
	If zx(nn) => xrect(n)-zside(nn) And zx(nn) =< xrect(n)+(wRect(n)+zside(nn)) And zon(nn)=1 And zTempShield(nn) =0 And rectOwner(n) <> nn Then
		If zy(nn)-2 => yRect(n) And zy(nn)-(zHeight(nn)-2) =< yRect(n)+ hRect(n) And zHitByRect(nn)=0 Then
			If zblock(nn)=1 Then
				If rectDir(n)=2 Then zFace(nn)=4:zBlockDir(nn)=2 Else zFace(nn)=2:zBlockDir(nn)=4
				zBlocked(nn)=1:zBlockSeq(nn)=0::zblowDir(nn)=zface(nn)
				zBlockTime(nn)=rectHitHold(n)*3
				zBLockLife(nn)=zBlockLife(nn)-rectDamage(n)
				zHitByRect(nn)=1
				If zy(nn) < yRect(n)+(hRect(n)/2) Then
						makechunk(n,zx(nn),yRect(n),2,1)
					Else
						makechunk(n,zx(nn),yRect(n)+hRect(n) ,2,1)
				EndIf
				If gameSound Then PlaySound blockedsnd
				If zBlockLife(nn) <1 Then
					zBlock(nn)=0:zBlocked(nn)=0
					If gameSound Then PlaySound brokensnd
				EndIf
			EndIf
			If Not zBlock(nn) Then
				zLife(nn)=zlife(nn)-rectDamage(n)
				zDamage#(nn)=zDamage#(nn)+rectDamage(n)
				
				If zStone(nn)=0 Then 
					zjump(nn)=0
					zBouncedgnd(nn)=0: zHitByRect(nn)=1
					If rectDir(n)=2 Then zFace(nn)=4:zFallDir(nn)=2 Else zFace(nn)=2:zFallDir(nn)=4
					zHitHold(nn)=rectHitHold(n)
					If rectHitMode(n)=2 Then
						calcRect2(nn,n,30)
					Else
						calcBlow(nn,n,rectHitMode(n),zDamage(nn))
					EndIf
				EndIf
				If zy(nn) < yRect(n)+(hRect(n)/2) Then
					makechunk(n,zx(nn),yRect(n),rectDir(n),rectChunkType(n))
				Else
					makechunk(n,zx(nn),yRect(n)+hRect(n) ,rectDir(n),rectChunkType(n))
				EndIf
				If gameSound =1 Then
				  If zStone(nn)=1 Then
				  	If rendert=1 Then PlaySound rectHitSound(n)
				  Else
				        PlaySound rectHitSound(n)
				  EndIf
				EndIf
			EndIf		
		EndIf
	EndIf
.skipRect
Next
rectHit(n)=0

End Function
;-----------------Check If stuff is on trigger area----------------------------------------
Function Triggers(n)

If Tway(n)=3 Then eventN(Tevent(n))=ToffStatus(n) : Ton(n)=ToffStatus(n)
If Tfollow(n) > 0 Then Tx(n)=xplat(Tfollow(n))+Tplatx(n) : Ty(n)=yplat(Tfollow(n))+Tplaty(n)

Select Taffect(n)
	Case 1 : amountAffected = zzamount
	Default: amountAffected = 4
End Select

If TpassBy(n) Then 	;If player is on Trigger Area, Then trigger it
	For nn=1 To amountAffected
		If zx(nn) => Tx(n) And zx(nn) =< Tx(n)+Tw(n) And zon(nn)=1 Then
			If zy(nn) => Ty(n) And zy(nn) =< Ty(n)+Th(n) Then
				TriggerIt(n)
				Goto didT
			EndIf
		EndIf
	Next
	
;	For nn=1 To objAmount
;		If xObj(nn) => Tx(n) And xObj(nn) =< Tx(n)+Tw(n) And obj(nn)=1 Then
;			If yObj(nn) => Ty(n) And yObj(nn) =< Ty(n)+Th(n) Then
;				TriggerIt(n)
;				Goto didT
;			EndIf
;		EndIf
;	Next
EndIf

If TzAction(n) Then 	;If player action(press up) on trigger area, Then trigger it
	For nn=1 To amountAffected
		If upkey(nn)=1 And zx(nn) => Tx(n) And zx(nn) =< Tx(n)+Tw(n) And zhit(nn)=0 And zon(nn)=1 Then
			If zy(nn) => Ty(n) And zy(nn) =< Ty(n)+Th(n) Then
				TriggerIt(n)
				Goto didT
			EndIf
		EndIf
	Next
EndIf

If TobjHit(n) Then	;If item hits trigger area, Then trigger it
	For nn=1 To objAmount
		If objHurt(nn)=1 And xObj(nn) => Tx(n) And xObj(nn) =< Tx(n)+Tw(n) And obj(nn)=1 And Tonce(n)=0 Then
			If yObj(nn) => Ty(n) And yObj(nn) =< Ty(n)+Th(n) And objZMade(nn)=0 Then
				If objExplosive(nn) >0 Then
					objexp(nn,xObj(nn),yobj(nn)-5,objExplosive(nn)):objHitSolid(nn):obj(nn)=0
				Else
					objHitSolid(nn)
				EndIf
				TriggerIt(n)
				Goto didT
			EndIf
		EndIf
	Next
EndIf

.didT

End Function
;----------Check trigger type-------------------------------------------
Function TriggerIt(n)

Select Tway(n)
Case 1	;Triggers only once
	If Tonce(n)=0 Then
		Tonce(n)=1
		If Tsound(n) > 0 And gameSound Then PlaySound SoundFx(Tsound(n))
		If EventN(Tevent(n))=0 Then
			eventN(Tevent(n))=1 : Ton(n)=1
		Else
			eventN(Tevent(n))=0 : Ton(n)=0
		EndIf
	EndIf
	
Case 2	;On/Off Trigger
	If Ton(n)=0 Then
		eventN(Tevent(n))=1 : Ton(n)=1
		If Tsound(n) > 0 And gameSound Then PlaySound SoundFx(Tsound(n))
	Else
		eventN(Tevent(n))=0 : Ton(n)=0
		If Tsound(n) > 0 And gameSound Then PlaySound SoundFx(Tsound(n))
	EndIf
	
Case 3	;Triggers only on real time action
	eventN(Tevent(n))=TonStatus(n) : Ton(n)=TonStatus(n)

Case 4	;play Next game music
	If Tonce(n)=0 Then
		Tonce(n)=1
		If EventN(Tevent(n))=0 Then
			eventN(Tevent(n))=1 : Ton(n)=1
			If gameMusic Then
				StopChannel chMusic 
				LoopSound music2
				chMusic=PlaySound(music2)
			EndIf
		Else
			eventN(Tevent(n))=0 : Ton(n)=0
			If gameMusic Then
				StopChannel chMusic 
				LoopSound music2
				chMusic=PlaySound(music2)
			EndIf
		EndIf
	EndIf

Case 10	;change fight mode, damage based or life
	If Tonce(n)=0 Then
		Tonce(n)=1
		makeChunk(0,200,200,2,49)
		If fightMode=1 Then fightMode=2 Else fightMode=1
	EndIf
	
Case 11	;change screen lock status on/off
	If Tonce(n)=0 Then
		Tonce(n)=1
		If scrLock=0 Then scrLock=1 Else scrLock=0
	EndIf

Case 12 ;toggle For no air-special allowed
	If Tonce(n)=0 Then
		Tonce(n)=1
		If noAirSpecial=0 Then noAirSpecial=1 Else noAirSpecial=0
	EndIf

Case 13 ;toggle For no double jump allowed
	If Tonce(n)=0 Then
		Tonce(n)=1
		If noDoubleJump=0 Then noDoubleJump=1 Else noDoubleJump=0
	EndIf

Case 14 ;secret area discovery
	If Tonce(n)=0 Then
		Tonce(n)=1
		secretsFound=secretsFound+1
		
		If EventN(Tevent(n))=0 Then
			eventN(Tevent(n))=1 : Ton(n)=1
		Else
			eventN(Tevent(n))=0 : Ton(n)=0
		EndIf
	EndIf
	

End Select

If Tway(n) >4 And Tway(n) < 10 Then	;check If player's on Exit
	For nn=1 To 4
		If zx(nn) => Tx(n) And zx(nn) =< Tx(n)+Tw(n) And zon(nn)=1 Then
			If zy(nn) => Ty(n) And zy(nn) =< Ty(n)+Th(n) Then
				
				allp=allp+1
				If allp=aliveAmountNeeded Then
					;Exit level
					mapComplete=1
					gameDone=1
					mapOpen(curMap)=1
					previousMap = curMap
					curMap=NextMap(Tway(n)-4)
					mapOpen(curMap)=1
				EndIf
			EndIf
		EndIf
	Next
EndIf

End Function
;--------MAKE RECTHIT-----------------------------------------------------------------------------
Function makeRectHit(n,x,y,w,h,dir,hitMode,xHit,yHit,damage,hitHold,chunk,HitSOund)

For i=1 To 20
	If rectHit(i)=0 Then
		If i > rectAmount Then rectAmount=i
		rectHit(i)=1
		zGotRect(n)=i
		rectOwner(i)=n
		xRect(i)=x : yRect(i)=y : rectDir(i)=dir
		wRect(i)=w : hRect(i)=h ;: rectSide(i)=wRect(i) / 2
		RectHitMode(i)=hitMode : rectDamage(i)=damage : rectHitHold(i)=hitHold
		rectXHitSpeed(i)=xHit : rectYHitSpeed(i)=yHit
		rectChunkType(i)=chunk : rectHitSound(i)=hitSound
		Exit
	EndIf
Next

End Function

;--------MAKE SHOTS-------------------------------------------------------------------------------
Function makeshot(o,n,x,y,dir)

For i=1 To 200
	If shot(i)=0 Then
		If i > shotAmount Then shotAmount=i
		shotData(n,i)
		shot(i)=1:shotowner(i)=o
			Select dir
				Case 2:shotDir(i)=2:xshot(i)=x:yshot(i)=y
				Case 4:shotDir(i)=4:xshot(i)=x:yshot(i)=y
			End Select
		Return i
	EndIf	
Next

End Function
;-------------THROW OBJECTS----------------------------------------------------------------------
Function throwObj(n,x,y,dir)

objTaken(zGotObj(n))=0:objHurt(zGotObj(n))=1
objThrow(zGotObj(n))=1:
objdir(zGotObj(n))=dir:xobj(zGotObj(n))=x:yobj(zGotObj(n))=y

If upkey(n)=1 And (leftKey(n)=1 Or rightKey(n)) Then objYSpeed(zGotObj(n))=objYForce(zGotObj(n)) Goto objThrown
If downkey(n)=1 And (leftKey(n)=1 Or rightKey(n)) Then objYSpeed(zGotObj(n))=5 Goto objThrown
If upkey(n)=1 Then objYSpeed(zGotObj(n))=objYForce(zGotobj(n)):objXspeed(zgotObj(n))=0 Goto objThrown
If downkey(n)=1 Then objYSpeed(zGotObj(n))=5:objXspeed(zgotObj(n))=0 Goto objThrown
.objThrown

zGotObj(n)=0                         

End Function

;-----------MAKE CHUNKS ------------------------------------------------------------------------
Function makeChunk(n,x,y,dir,kind)

For i=1 To 1500
	If chunk(i)=0 Then
		If i > chunkAmount Then chunkAmount=i
		chunkPic(i)=0
		chunkOwner(i)=n
		chunkSeq(i)=0
		chunkDir(i)=dir
		chunk(i)=1:chunkType(i)=kind
		xChunk(i)=x:yChunk(i)=y
		chunkCategory(i)=1
		Exit
	EndIf	
Next

End Function


;----LOAD RIGHT TYPE OF MAP -------------------
Function loadMap()

If gameMode=2 Then file=ReadFile(modFolder$(curModId) + "CTFmap" + curMap + ".dat")
If gameMode<>2 And vsMode=1 Then file=ReadFile(modFolder$(curModId) + "map" + curMap + ".dat")
If vsMode=0 Then file=ReadFile(modFolder$(curModId) + "amap" + curMap + ".dat")

;----START---------
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
boxUseTrigger(n)=ReadInt (file)		
boxEventN(n)=ReadInt (file)			
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
		tileXend1(b,n) = ReadInt (file)
		tileXend2(b,n) = ReadInt (file)
		tileXrand1(b,n) = ReadInt (file)
		tileXrand2(b,n) = ReadInt (file)
		tileXspeed(b,n) = ReadFloat (file)
		tileYend1(b,n) = ReadInt (file)
		tileYend2(b,n) = ReadInt (file)
		tileYrand1(b,n) = ReadInt (file)
		tileYrand2(b,n) = ReadInt (file)
		tileYspeed(b,n) = ReadFloat (file)
		tileXstart(b,n) = ReadInt (file)
		tileYstart(b,n) = ReadInt (file)
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
	Taffect(n) = ReadInt (file)
	Tsound(n) = ReadInt (file)
	Tfollow(n) = ReadInt (file)
	Tplatx(n) = ReadInt (file)
	Tplaty(n) = ReadInt (file)
	TonStatus(n) = ReadInt (file)
	ToffStatus(n) = ReadInt (file)
Next

For n=1 To 5
	NextMap(n) = ReadInt (file)
Next
xScrStart = ReadInt (file)
yScrStart = ReadInt (file)
fightMode = ReadInt (file)
MapN = ReadInt (file)
scrLock = ReadInt (file)
loadvsMode = ReadInt (file)
yScrCameraBottomLimit = ReadInt (file)
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

;------END---------

CloseFile file
End Function
;---------------Build map (walls)-------------------------------
Function buildMap()

If wallAmount=0 Then
	If map=0 Then map = CreateImage(2,2)
Else
	map = CreateImage(640,480)
	SetBuffer ImageBuffer(map)
	Color 128,128,128
	For i=1 To wallAmount
	 Rect xwall(i),ywall(i),wallWidth(i),wallHeight(i),1
	Next
	SetBuffer BackBuffer()
EndIf

End Function
;---------------Position setup-----------------------------------
Function SETPOS()

flagPic(1)=flag1P
flagPic(2)=flag2P

loadMap()
For i=1 To zzamount
	zx(i)=zxStart(i) : zy(i)=zyStart(i)
	
Next
defineStuff()
xFlagStart(1)=xBase(1):yflagStart(1)=yBase(1)
xFlagStart(2)=xBase(2):yflagStart(2)=yBase(2)
xFlag(1)=xFlagStart(1):yflag(1)=yFlagStart(1)
xFlag(2)=xFlagStart(2):yflag(2)=yFlagStart(2)

End Function
;---------------Position setup For CTF -----------------------------------
Function SETPOS_CTF()

flagPic(1)=flag1P
flagPic(2)=flag2P

loadMap()
For i=1 To zzamount
	zx(i)=zxStart(i) : zy(i)=zyStart(i)
Next
xFlagStart(1)=xFlag(1):yflagStart(1)=yFlag(1)
xFlagStart(2)=xFlag(2):yflagStart(2)=yFlag(2)
xBase(1)=xFlag(1) :  yBase(1)=yFlag(1)
xBase(2)=xFlag(2) :  yBase(2)=yFlag(2)
defineStuff()

End Function

;-----------------Remove Immunity from player ----------------------
Function remImune(n)

zHitCount(n)=0
For nn=1 To zzamount
	If zImuneTo(nn,n)=n And nn <> n Then zImuneTo(nn,n)=0
Next

End Function
;-------------------Stages special stuff ----------------------------
Function stages()

If vsMode=1 And gameMode <> 2 Then
	Select curMap

	Case 2 ;lasers from the sides

		boxHarmless(2)=1    ;box below to help go up
		If shotSeq(1)=0 Then
			For nn=1 To zzamount
				k=1 ; <- laser box number
				boxHarmless(k)=1
				If yBox(k) > zy(nn)-45 And yBox(k) < zy(nn)-6 And zon(nn)=1 Then
					shotseq(1)=1
					If gamesound Then PlaySound lasersnd
					If xbox(k) < 300 Then dir=2 Else dir=4
					makeshot(0,8,xBox(k),yBox(k)+5,dir)
				EndIf
			Next
		Else
			shotseq(1)=shotseq(1)+1
			If shotseq(1) > 100 Then shotseq(1)=0
		EndIf
	
	Case 4 ;MArio stage
		n=1
		If ybox(n) > 325 Then drawbox(n)=0 Else drawbox(n)=1
		N=2
		If ybox(n) > 195 Then drawbox(n)=0 Else drawbox(n)=1
	
	End Select

EndIf

End Function
;--------------- Quick check agaist wall---------------------------------------------
Function zCheckWall(n)

For vn=0 To zHeight(n) Step 5
	If ImageRectCollide (map,0,0,0,zx(n) - zSide(n),zy(n) - vn,1,1) Or ImageRectCollide (map,0,0,0,zx(n) + zSide(n),zy(n) - vn,1,1) Then
		zx(n)=zOldx(n) : zy(n)=zOldy(n)
		Goto zCheckedWallDone     		
	EndIf
Next
.zCheckedWallDone

End Function

;----------------- Grabbing oponent--------------------------
Function grabbing(n,x,y,w,h)

zGrabs(n)=0:zGrabsThis(n)=0
For nn=1 To zzamount
	If zon(nn) And zTempShield(nn)=0 And nn <> n And zGrabbed(nn)=0 And zShield(nn)=0 And zUngrabable(nn)=0 Then
		Select zFace(n)
		Case 2
			If zx(nn) => x And zx(nn) <= x+w And zy(nn) => y And zy(nn) <= y+h Then
				zLetGoSeq(nn)=0
				zGrabbed(nn)=1
				zGrabbedBy(nn)=n
				zGrabs(n)=1
				zGrabs(nn)=0
				zGrabsThis(n)=nn
				zBlow(nn)=0:zBlowEffect(nn)=0
				zBlock(nn)=0:zBlocked(nn)=0
				zJump(nn)=0: zJump2(nn)=0
				zongnd(nn)=0
			EndIf
			
		Case 4
			If zx(nn) =< x And zx(nn) >= x-w And zy(nn) => y And zy(nn) <= y+h Then
				zLetGoSeq(nn)=0
				zGrabbed(nn)=1
				zGrabbedBy(nn)=n
				zGrabs(n)=1
				zGrabs(nn)=0
				zGrabsThis(n)=nn
				zBlow(nn)=0:zBlowEffect(nn)=0
				zBlock(nn)=0:zBlocked(nn)=0
				zJump(nn)=0: zJump2(nn)=0
				zongnd(nn)=0
			EndIf
		
		End Select
	EndIf 
Next


End Function

;----------------------Blows-------------------------------------------
Function Blows(n)

If zFrozen(n)=1 And zHitModeTaken(n) <> 3 Then unFreeze(n)

For nn=1 To zzamount
If zImune(nn,n)=1 Then
	zImuneSeq(nn,n)=zImuneSeq(nn,n)+1:
	If zImuneSeq(nn,n) > zImuneTime(nn,n) Then zImune(nn,n)=0
EndIf
Next

If zBlow(n)=1 And zBlowEffect(n) =1 Then 

Select zblowdir(n)
Case 2
	For nn = 1 To zzamount
		
		If zShield(nn)=0 And zon(nn)=1 Then 
		If Not nn=n Then
			If teamAttack=0 And (zteam(n) = zteam(nn)) Then Goto tryz1
			If zx(nn) < zx(n)-10 And zBlowBack(n)=0 Then Goto tryz1
			If Not zImune(nn,n)=1 And zImuneTo(nn,n)=n Then 
				For bn=1 To zblowPamount(n)
					yp=Int(zy(nn)-ImageHeight(zCurPic(nn))+1)
					xp=Int(zx(nn)-(ImageWidth(zCurPic(nn))/2))
					yb=Int(zy(n)-yBlow(n,bn))
					xb=Int(zx(n)+xBlow(n,bn))
					 If zCurPic(nn) = 0 Then RuntimeError "nn="+nn
					  n_zani=zani(n) : n_zf=zf(n)
			          nn_zani=zani(nn) : nn_zf=zf(nn)
				      nn_curPic=zCurPic(nn)
				      DrawImage zCurPic(nn),10,10

					If ImageRectCollide(nn_curPic,xp,yp,0,xb,yb,wBlow(n,bn),hBlow(n,bn)) Then
						zBlowHit(n)=1
						If zBlowStill(n)=1 Then 
							zBlowStillSeq(n)=zBlowStillSeq(n)
						Else
							zBlowStill(n)=1: zBlowStillSeq(n)=0
						EndIf
						zBlockLife(n)=zBlockLife(n)+10
						If zblockLife(n) > zblockfull(n) Then zblockLife(n)=zblockfull(n)
						If zCurBlow(n) <> 14 Then zSuperBar(n)=zSuperBar(n)+zBlowDamage(n)/2
						If zSuperBar(n) > 100 Then zSuperBar(n)=100
						zSuperBar(nn)=zSuperBar(nn)+zBlowDamage(n)/2
						If zSuperBar(nn) > 100 Then zSuperBar(nn)=100
						zxStill#(n)=zx(n):zyStill(n)=zy(n)
						If zstone(nn)=0 Then zface(nn)=4
						zblockDir(nn)=2
						zImune(nn,n)=1:zImuneTo(nn,n)=n:zImuneSeq(nn,n)=0:zImuneTime(nn,n)=zBlowImpact(n)
						If zblock(nn)=1 Then
							If zblowback(n)=1 Then
								If zx(n) => zx(nn) Then zface(nn)=2:zFallDir(nn)=4:zblockDir(nn)=4
								If zx(n) < zx(nn) Then zface(nn)=4:zFallDir(nn)=2:zblockDir(nn)=2
							EndIf
							zBlocked(nn)=1:zBlockSeq(nn)=0:zblowDir(nn)=zface(nn)
							zBlockTime(nn)=zBlowBlockTime(n)
							makechunk(n,zx(nn),zy(n)-(yblow(n,bn)-hblow(n,bn)),2,1)
							zBLockLife(nn)=zBlockLife(nn)-zBlowDamage(n)
							If zBlockLife(nn) <1 Then
								zBlock(nn)=0:zBlocked(nn)=0
								If gameSound=1 Then PlaySound brokensnd
							Else
								If gameSound=1 Then PlaySound blockedsnd
							EndIf
						EndIf	
						If zblock(nn) =0 Then 
							zlife(nn)=zlife(nn)-zBlowDamage(n)
							zDamage#(nn)=zDamage#(nn)+zBlowDamage(n)
							zhitbybox(nn)=0
							zHitCount(n)=zHitCount(n)+1
							If zStone(nn)=0 Then
								If zblowback(n)=1 Then
									If zx(n) => zx(nn) Then zface(nn)=2:zFallDir(nn)=4
									If zx(n) < zx(nn) Then zface(nn)=4:zFallDir(nn)=2
								Else
									zFallDir(nn)=zBlowDir(n)
								EndIf
								
								zjump(nn)=0:zBouncedgnd(nn)=0
								zHitHold(nn)=zBlowHold(n)
							
								If zHitMode(n)=0 Or zHitMode(n)=1 Then
									calcBlow(nn,n,zHitMode(n),zDamage(nn))
									Else
									calcBlow2(nn,n,zHitTime(n))
								EndIf
							EndIf
													
							makechunk(n,zx(nn),zy(n)-yblow(n,bn),2,zChunkType(n))
							If zLife(nn) < 1 Then zScore(n)=zScore(n)+1
							If gameSound =1 Then PlaySound zBlowSound(n)
						EndIf
						Goto BlowDone
				 	End If
				Next
			EndIf
		EndIf
		EndIf
		.tryz1
	Next
		
Case 4
	For nn= 1 To zzamount
		
		If zShield(nn)=0 And zon(nn)=1 Then
		If Not nn=n Then
		If teamAttack=0 And (zteam(n) = zteam(nn)) Then Goto tryz2
		If zx(nn) > zx(n)+10 And zBlowback(n)=0 Then Goto tryz2
		If Not zImune(nn,n)=1 And zImuneTo(nn,n)=n Then 
		For bn=1 To zblowpamount(n)	
			yp=Int(zy(nn)-ImageHeight(zCurPic(nn))+1)
			xp=Int(zx(nn)-(ImageWidth(zCurPic(nn))/2))
			;zx(n)-(xBlow(n,bn)+wBlow(n,bn)),zy(n)-yBlow(n,bn)
			yb=Int(zy(n)-yBlow(n,bn))
			xb=Int(zx(n)-(xBlow(n,bn)+wBlow(n,bn)))
             If zCurPic(nn) = 0 Then RuntimeError "nn="+nn
			 ;If curGuy(n)=40 Then 
			   n_zani=zani(n) : n_zf=zf(n)
			   nn_zani=zani(nn) : nn_zf=zf(nn)
			   nn_curPic=zCurPic(nn)
			   DrawImage zCurPic(nn),10,10
			 ;EndIf 

			If ImageRectCollide(nn_curPic,xp,yp,0,xb,yb,wBlow(n,bn),hBlow(n,bn)) Then
				zBlowHit(n)=1
				If zBlowStill(n)=1 Then 
					zBlowStillSeq(n)=zBlowStillSeq(n)
				Else
					zBlowStill(n)=1: zBlowStillSeq(n)=0
				EndIf
				zBlockLife(n)=zBlockLife(n)+10
				If zblockLife(n) > zblockfull(n) Then zblockLife(n)=zblockfull(n)
				If zCurBlow(n) <> 14 Then zSuperBar(n)=zSuperBar(n)+zBlowDamage(n)/2
				If zSuperBar(n) > 100 Then zSuperBar(n)=100
				zSuperBar(nn)=zSuperBar(nn)+zBlowDamage(n)/2
				If zSuperBar(nn) > 100 Then zSuperBar(nn)=100
				zxStill#(n)=zx(n):zyStill(n)=zy(n)
				If zStone(nn)=0 Then zface(nn)=2
				zblockDir(nn)=4
				zImune(nn,n)=1:zImuneTo(nn,n)=n:zImuneSeq(nn,n)=0:zImuneTime(nn,n)=zBlowImpact(n)
				If zblock(nn)=1 Then
						If zblowback(n)=1 Then
							If zx(n) => zx(nn) Then zface(nn)=2:zFallDir(nn)=4:zblockDir(nn)=4
							If zx(n) < zx(nn) Then zface(nn)=4:zFallDir(nn)=2:zblockDir(nn)=2
						EndIf
						zBlocked(nn)=1:zBlockSeq(nn)=0:zblowdir(nn)=zface(nn)
						zBlockTime(nn)=zBlowBlockTime(n)
						makechunk(n,zx(nn),zy(n)-(yblow(n,bn)-hblow(n,bn)),2,1)
						zBLockLife(nn)=zBlockLife(nn)-zBlowDamage(n)
						If zBlockLife(nn) <1 Then
							zBlock(nn)=0:zBlocked(nn)=0
							If gameSound=1 Then PlaySound brokensnd
						Else
							If gameSOund=1 Then PlaySound blockedsnd
						EndIf
				EndIf
				If zblock(nn)=0 Then 
					zlife(nn)=zlife(nn)-zBlowDamage(n)
					zDamage#(nn)=zDamage#(nn)+zBlowDamage(n)
					zhitbybox(nn)=0
					zHitCount(n)=zHitCount(n)+1		
					If zStone(nn)=0 Then
						If zblowback(n)=1 Then
							If zx(n) => zx(nn) Then zface(nn)=2:zFallDir(nn)=4
							If zx(n) < zx(nn) Then zface(nn)=4:zFallDir(nn)=2
						Else
							zFallDir(nn)=zBlowDir(n)
						EndIf
						
						zjump(nn)=0:zBouncedgnd(nn)=0
						zHitHold(nn)=zBlowHold(n)
					
						If zHitMode(n)=0 Or zHitMode(n)=1 Then
							calcBlow(nn,n,zHitMode(n),zDamage(nn))
							Else
							calcBlow2(nn,n,zHitTime(n))
						EndIf
					EndIf
											
					makechunk(n,zx(nn),zy(n)-yblow(n,bn),4,zChunkType(n))
					If zLife(nn) < 1 Then zScore(n)=zScore(n)+1
					If gameSound =1 Then PlaySound zBlowSound(n)
				EndIf
				Goto BlowDone
			End If
		Next
		EndIf
		EndIf
		EndIf
		.tryz2
	Next
		
End Select
.blowDone
If zHitCount(n) > 2 And zHitMode(n) <> 2 And gamesound Then PlaySound clapSnd: zHitCount(n)=0

EndIf

End Function
;---------------------------Calculate Blow Impact------------------------------------------------
Function calcBlow(nn,n,hitMode,damage)

;fightMode=1	
zBLock(nn)=0:zBlocked(nn)=0
zgrabs(nn)=0 : zGrabsThis(nn)=0
zGotHitsAmount(nn)=zGotHitsAmount(nn)+1
If fightMode=1 Then
	zHitModeTaken(nn)=HitMode
	zhit(nn)=1:zHitSeq(nn)=0
	If hitmode=1 Then zFallSpeed(nn)=2 Else zFallSpeed#(nn)=damage/10
	If zFallSpeed#(nn) < 1 Then zFallSpeed#(nn)=1
	If zFallSpeed#(nn) > 23 Then zFallSpeed#(nn)=23
	zFallTime#(nn)=zDamage#(nn)/1.2
	If zFalltime(nn) < 25 Then zfalltime(nn)=25
	
	If hitmode=1 Then
		zUpFallSpeed#(nn)=damage/14
		zFallTime(nn)=zFallTime(nn) * 1.5
	Else
		zUpFallSpeed#(nn)=zFallSpeed#(nn)/2
	EndIf
	
	zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
	
	If zDamage(nn)>110 And fightMode = 1 Then
		If gamesound Then PlaySound eeeeSnd 
		zHitHold(nn)=15
	EndIf
    If zGrabbed(nn)=1 Then
	ZFallTime(nn)=20
	zFallSpeed(nn)=0
	zUpFallSpeed(nn)=0
	zFallDir(nn)=0
	EndIf
Else
	zHitModeTaken(nn)=HitMode
	zhit(nn)=1:zHitSeq(nn)=0
	zFallSpeed#(nn)=5
	zFallTime#(nn)=50
	zUpFallSpeed#(nn)=2
	zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
	
EndIf


End Function
;--------------------------Calculate Rect impact 2 --------------------------------------------------
Function calcRect2(nn,n,hitTime)

zgrabs(nn)=0: zGrabsThis(nn)=0
zblock(nn)=0:zBlocked(nn)=0
zHitModeTaken(nn)=rectHitMode(n)
zGotHitsAmount(nn)=zGotHitsAmount(nn)+1
zhit(nn)=1:zHitSeq(nn)=0
zFallSpeed#(nn)=rectXhitSpeed(n)
If zFallSpeed#(nn) > 14 Then zFallSpeed#(nn)=14
zFallTime#(nn)=HitTime
zUpFallSpeed#(nn)=rectYHitSpeed(n)
zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
If zGotobj(nn)>0 Then
	objLife(zgotObj(nn))=objLife(zgotObj(nn))-4
	If objLife(zGotobj(nn)) =< 0 Then obj(zGotobj(nn))=0:makeChunk(0,xObj(zGotobj(nn)),yObj(zGotobj(nn)),2,objHitChunk(zGotobj(nn)))
	objhitsolid(zGotobj(nn)):zGotobj(nn)=0
EndIf
If zGrabbed(nn)=1 Then
	ZFallTime(nn)=20
	zFallSpeed(nn)=0
	zUpFallSpeed(nn)=0
	zFallDir(nn)=0
EndIf

End Function
;---------------------------Calculate Blow Impact 2 ------------------------------------------------
Function calcBlow2(nn,n,hitTime)

isRunning(nn)=0
zgrabs(nn)=0: zGrabsThis(nn)=0
zBlock(nn)=0:zBlocked(nn)=0
zHitModeTaken(nn)=zHitMode(n)
zGotHitsAmount(nn)=zGotHitsAmount(nn)+1
zhit(nn)=1:zHitSeq(nn)=0
zFallSpeed#(nn)=zhitSpeed#(n)
If zFallSpeed#(nn) > 14 Then zFallSpeed#(nn)=14
zFallTime#(nn)=HitTime
zUpFallSpeed#(nn)=zHitUpSpeed#(n)
zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
If zGotobj(nn)>0 Then
	objLife(zgotObj(nn))=objLife(zgotObj(nn))-4
	If objLife(zGotobj(nn)) =< 0 Then obj(zGotobj(nn))=0:makeChunk(0,xObj(zGotobj(nn)),yObj(zGotobj(nn)),2,objHitChunk(zGotobj(nn)))
	objhitsolid(zGotobj(nn)):zGotobj(nn)=0
EndIf
If zGrabbed(nn)=1 Then
	ZFallTime(nn)=20
	zFallSpeed(nn)=0
	zUpFallSpeed(nn)=0
	zFallDir(nn)=0
EndIf

End Function

;------------------------calculate box impact ---------------------------------------------------
Function calcBox(nn,n)

isRunning(nn)=0
zHitHold(nn)=8
zgrabs(nn)=0: zGrabsThis(nn)=0
zBlock(nn)=0::zBlocked(nn)=0
zBlow(nn)=0:zblowseq(nn)=0:zblowseq2(nn)=0
zHitModeTaken(nn)=boxHitMode(n)
zGotHitsAmount(nn)=zGotHitsAmount(nn)+1
zhit(nn)=1:zHitSeq(nn)=0
zFallSpeed#(nn)=boxhitSpeed#(n)
If zFallSpeed#(nn) > 14 Then zFallSpeed#(nn)=14
zFallTime#(nn)=boxHitTime#(n)
zUpFallSpeed#(nn)=boxHitYSpeed#(n)
zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
If zGrabbed(nn)=1 Then
	ZFallTime(nn)=20
	zFallSpeed(nn)=0
	zUpFallSpeed(nn)=0
	zFallDir(nn)=0
EndIf
		
End Function

;---------------------------Calculate object impact ------------------------------------------------
Function calcObj(nn,n)

isRunning(nn)=0
zgrabs(nn)=0: zGrabsThis(nn)=0
zBlock(nn)=0:zBlocked(nn)=0
zBlow(nn)=0:zblowseq(nn)=0:zblowseq2(nn)=0
zHitModeTaken(nn)=2
zGotHitsAmount(nn)=zGotHitsAmount(nn)+1
zhit(nn)=1:zHitSeq(nn)=0
zFallTime#(nn)=objFallTime(n)
zFallSpeed#(nn)=objHitXspeed(n)
zUpFallSpeed#(nn)=objHitYspeed(n)
zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3

If zGrabbed(nn)=1 Then
	ZFallTime(nn)=20
	zFallSpeed(nn)=0
	zUpFallSpeed(nn)=0
	zFallDir(nn)=0
EndIf
If zGotobj(nn)>0 Then
	objLife(zgotObj(nn))=objLife(zgotObj(nn))-4
	If objLife(zGotobj(nn)) =< 0 Then obj(zGotobj(nn))=0:makeChunk(0,xObj(zGotobj(nn)),yObj(zGotobj(nn)),2,objHitChunk(zGotobj(nn)))
	objhitsolid(zGotobj(nn)):zGotobj(nn)=0
EndIf

End Function

;---------------------------Calculate Shot Impact ---------------------
Function calcShot(nn,n)

zHitHold(nn)=shotHold(n)
zgrabs(nn)=0: zGrabsThis(nn)=0
zBlock(nn)=0:zBlocked(nn)=0

zGotHitsAmount(nn)=zGotHitsAmount(nn)+1
If shotHitMode(n)=2 Then ;normal projectile
	isRunning(nn)=0
	zBlow(nn)=0:zblowseq(nn)=0:zblowseq2(nn)=0
	zHitModeTaken(nn)=2
	zhit(nn)=1:zHitSeq(nn)=0
	zFallSpeed#(nn)=shotHitXspeed(n)
	zFallTime#(nn)=shotFallTime(n)
	zUpFallSpeed#(nn)=shotHitYspeed(n)+3
EndIf

If shotHitMode(n)=3 Or shotHitMode(n)=5 Or shotHitMode(n)=6 Then ; sub zero freeze attacks
	isRunning(nn)=0
	zBlow(nn)=0:zblowseq(nn)=0:zblowseq2(nn)=0
	zHitModeTaken(nn)=3
	zhit(nn)=0:zHitSeq(nn)=0
	zFallSpeed#(nn)=shotHitXspeed(n)
	zFallTime#(nn)=shotFallTime(n)
	zUpFallSpeed#(nn)=shotHitYspeed(n)
	zFreezer=n
	freezeVictim(nn)
EndIf

If shotHitMode(n)=4 Then ;sub zero freeze ground
	isRunning(nn)=0
	zBlow(nn)=0:zblowseq(nn)=0:zblowseq2(nn)=0
	zHitModeTaken(nn)=4
	zhit(nn)=1:zHitSeq(nn)=0
	zFallSpeed#(nn)=shotHitXspeed(n)
	zFallTime#(nn)=shotFallTime(n)
	zUpFallSpeed#(nn)=shotHitYspeed(n)
EndIf

If shotHitMode(n)=0 Then
  If fightMode=1 Then
	zHitModeTaken(nn)=0
	zhit(nn)=1:zHitSeq(nn)=0
	zFallSpeed#(nn)=zdamage(nn)/10
	If zFallSpeed#(nn) < 1 Then zFallSpeed#(nn)=1
	If zFallSpeed#(nn) > 23 Then zFallSpeed#(nn)=23
	zFallTime#(nn)=zDamage#(nn)/1.2
	If zFalltime(nn) < 25 Then zfalltime(nn)=25
	zUpFallSpeed#(nn)=zFallSpeed#(nn)/2
	zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
	
  Else
	zHitModeTaken(nn)=shotHitMode(n)
	zhit(nn)=1:zHitSeq(nn)=0
	zFallSpeed#(nn)=5
	zFallTime#(nn)=50
	zUpFallSpeed#(nn)=2
	zUpFallSpeed#(nn)=zUpFallSpeed#(nn)+3
  EndIf
EndIf
End Function

;------------------------- make Object (Return #)----------------------------------------------	
Function getObj(n,t)

For i=1 To 100
	If obj(i)=0 Then
		nn=i 
		If nn > objAmount Then objAmount=nn
		Exit
	EndIf
Next

obj(nn)=1
objOwner(nn)=n
zGotObj(n)=nn
objType(nn)=t	
objData(nn,n)
		
Return nn

End Function
;------------------------- make Object (Return #)----------------------------------------------
Function getObjAway(n,t)
; creates object which has no one holding it
For i=1 To 100
	If obj(i)=0 Then
		nn=i
		If nn > objAmount Then objAmount=nn
		Exit
	EndIf
Next

obj(nn)=1
objOwner(nn)=n
objType(nn)=t
objData(nn,n)

Return nn

End Function

;--------------------------- Objects -------------------------------------------------------------
Function objs(n)

objHit(n)=0
If objTaken(n)=0 Then
	If objHurt(n)=0 And objNoGrav(n)=0 Then objXspeed(n)=objXspeed(n)-.02
	If objXspeed(n)<0 Then objXspeed(n)=0
	If objNoGrav(n)=0 Then objYSpeed(n)=objYSpeed(n)+.1
	If objYSpeed(n) > 3 And objHurt(n)=0 Then objYSpeed(n)=3
	If objYSpeed(n) > 6 Then objYSpeed(n)=6
	If Not ImageRectCollide(map,0,0,0,xObj(n)-objSide(n),yObj(n)+1,1,1) Then
		If Not ImageRectCollide(map,0,0,0,xObj(n)+objSide(n),yObj(n)+1,1,1) Then
			yobj(n)=yobj(n)+objYSpeed(n)
		Else
			If objHurt(n)=1 Then objHit(n)=1
			objYspeed(n)=0:objhitsolid(n)
		EndIf
	EndIf
	.checkObjAgain
	If ImageRectCollide(map,0,0,0,xObj(n)-objSide(n),yObj(n),1,1) Or ImageRectCollide(map,0,0,0,xObj(n)+objSide(n),yObj(n),1,1) And objXspeed(n)=<0 Then
			If objHurt(n)=1 Then objHit(n)=1
			yObj(n)=yObj(n)-1:objXspeed(n)=0:objYSpeed(n)=0:objHitSolid(n):Goto checkobjAgain
	EndIf
	If ImageRectCollide(map,0,0,0,xObj(n),yObj(n)+1,1,1) Then
			If objHurt(n)=1 Then objHit(n)=1
			objXspeed(n)=0:objYSpeed(n)=0:objHitSolid(n)
		Else
		If ImageRectCollide(map,0,0,0,xObj(n)-objSide(n),yObj(n)-(objheight(n)),1,1) Or ImageRectCollide(map,0,0,0,xObj(n)+objSide(n),yObj(n)-(objheight(n)),1,1) Then
			If objHurt(n)=1 Then objHit(n)=1
			If objNoGrav(n)=0 Then yobj(n)=yobj(n)+1.5
			objYspeed(n)=1:objXspeed(n)=1:objHitSolid(n):objhitroof=1
		EndIf
	EndIf
	
For q=2 To objheight(n)-2 Step 2
		If ImageRectCollide(map,0,0,0,xobj(n)+objSide(n),yobj(n)-q,1,1) Then
			xobj(n)=xobj(n)-2:objYSpeed(n)=0
			If objHurt(n)=1 Then objHit(n)=1
			ithit=1:objDir(n)=4:objHitSolid(n)			
			Exit
		EndIf
		If ImageRectCollide(map,0,0,0,xobj(n)-objSide(n),yobj(n)-q,1,1) Then
			xobj(n)=xobj(n)+2:objYSpeed(n)=0
			If objHurt(n)=1 Then objHit(n)=1
			ithit=1:objDir(n)=2:objHitSolid(n)			
			Exit
		EndIf
Next	

For nn=1 To shotAmount	;object x shot collision
  If shot(nn) And objHurt(n) Then
	If xshot(nn) => xobj(n)-objSide(n) And xshot(nn) =< xObj(n)+objSide(n) Then 
	  For hh=0 To shotHeight(nn) Step 4	
		If (yshot(nn) => yObj(n)-objheight(n) And yshot(nn) <= yObj(n)) Or (yshot(nn)-hh => yObj(n)-objheight(n) And yshot(nn)-hh <= yObj(n)) Then
			If shotSuper(nn)=0 And objSuper(n)=0 Then
			  If gameSound Then PlaySound shotSound(nn)
			EndIf
			If objSuper(n)=0 Then	
				objHitSolid(n)
				objLife(n)=objLife(n)-5
				If objLife(n) =< 0 Then obj(n)=0:makeChunk(0,xObj(n),yObj(n),2,objHitChunk(n))
				objYspeed(n)=-1
				objXspeed(n)=1
				ithit=1:objHit(n)=1
				makechunk(n,xobj(n),yobj(n),2,1)
				If xobj(n) > xshot(nn) Then
					objdir(n)=2
					Else
					objdir(n)=4
				EndIf
			EndIf
			If shotSuper(nn)=0 Then 	
				If shotExplosive(nn) > 0 Then shotexp(nn,xShot(nn),yShot(nn),shotExplosive(nn)):shot(nn)=0
				shot(nn)=0
				makechunk(shotDir(nn),xshot(nn),yShot(nn),2,shotChunkType(nn))
			EndIf
			Exit
		EndIf
	  Next
	EndIf
  EndIf
Next

For nn=1 To objAmount	;object x object collision
  If Not nn=n Then
	If obj(nn) And objHurt(nn) And objHurt(n) Then
		If xobj(n) => xobj(nn)-objSide(nn) And xobj(n) =< xobj(nn)+objSide(nn) Then 
		If (yObj(n) => yobj(nn)-objheight(nn) And yobj(n) <= yobj(nn)) Or (yobj(n)-objheight(n) => yobj(nn)-objheight(nn) And yobj(n)-objheight(n) <= yobj(nn)) Then
		  If objSuper(n)=0 Then
			objHitSolid(n)
			objLife(n)=objLife(n)-5
			If objLife(n) =< 0 Then obj(n)=0:makeChunk(0,xObj(n),yObj(n),2,objHitChunk(n))
			objYspeed(n)=-1
			objXspeed(n)=1
			ithit=1:objHit(n)=1
			makechunk(n,xobj(n),yobj(n),2,1)
			If xobj(n) > xobj(nn) Then
				objdir(n)=2
				Else
				objdir(n)=4
			EndIf
		  EndIf
		  If objSuper(nn)=0 Then	
			objhitsolid(nn)
			objLife(nn)=objLife(nn)-5
			If objExplosive(nn) > 0 Then objexp(nn,xObj(nn),yobj(nn),objExplosive(nn)):objHitSolid(nn):obj(nn)=0
			If objLife(nn) =< 0 Then obj(nn)=0:makeChunk(0,xObj(nn),yObj(nn),2,objHitChunk(nn))
			objYspeed(nn)=-1 : objXspeed(nn)=1
			If xobj(nn) > xobj(n) Then
				objdir(nn)=2
				Else
				objdir(nn)=4
			EndIf
		  EndIf
			
		EndIf
		EndIf
	EndIf
  EndIf
Next

If targetBox(tarN)=1 And objHurt(n) Then	;object x target collision
	If xobj(n) => xbox(tarN) And xobj(n) =< xbox(tarN)+boxWidth(tarN) And objZMade(n) =0 Then
	If (yobj(n) => ybox(tarN) And yobj(n) <= ybox(tarN)+boxheight(tarN)) Or (yobj(n)-objheight(n) => ybox(tarN) And yobj(n)-objheight(n) <= ybox(tarN)+boxheight(tarN)) Then	
		If gamesound Then PlaySound coinsnd
		ztargetScore(objOwner(n))=ztargetScore(objOwner(n))+1
		
		For i=1 To zzamount
			If zTeam(objOwner(n))=zTeam(i) And objOwner(n) <> i Then
		        ztargetScore(i)=ztargetScore(i)+1
			EndIf
		Next
		
		objLife(n)=objLife(n)-5:objLife(nn)=objLife(nn)-5
		If objLife(n) =< 0 Then obj(n)=0:makeChunk(0,xObj(n),yObj(n),2,objHitChunk(n))
		objhitsolid(n)
		objYspeed(n)=-1
		objXspeed(n)=1
		ithit=1:objHit(n)=1
		makechunk(n,xobj(n),yobj(n),2,1)
		If xobj(n) > xbox(tarN)+(boxWidth(tarN)/2) Then
			objdir(n)=2
			Else
			objdir(n)=4
		EndIf
		EndIf
	EndIf		
EndIf

If objHit(n) And objExplosive(n)>0 Then objexp(n,xObj(n),yobj(n)-5,objExplosive(n)):objHitSolid(n):obj(n)=0:Goto objdone
If ithit And gameSound And inSight(xObj(n),yObj(n)) Then PlaySound zhitwallsnd


Select objDir(n)	;object x player collision
Case  2
	
  For hh=1 To objHeight(n) Step 14
	For q=0 To objsize(n) Step 1
		For nn= 1 To zzamount
			oldshield=zshield(nn)
			If objSuper(n)=1 Then zShield(nn)=0
			;If teamAttack=0 And (zteam(n) = zteam(nn)) Then Goto tryz1
			If teamAttack=1 Or zTeam(objOwner(n)) <> zteam(nn) Then
			  If nn<>objOwner(n) And objHurt(n) And zShield(nn)=0  And ztempShield(nn)=0 And zon(nn)=1 Then
			  	
               If (zx(nn) => xObj(n)-(objSide(n)+zside(nn)+5) And zx(nn) =< xObj(n)+(objSide(n)+zside(nn)+5)) And (zy(nn) => yObj(n)-(objHeight(n)+5) And zy(nn) =< (yObj(n)+zHeight(nn)+5)) Then
				If ImageRectCollide(zCurPic(nn),zx(nn)-(ImageWidth(zCurPic(nn))/2),zy(nn)-ImageHeight(zCurPic(nn))+1,0,xobj(n)-objSide(n)+q,yobj(n)-hh,1,1) Then
					If objExplosive(n) > 0 And objHurt(n)=1 Then
						objexp(n,xObj(n),yobj(n)+10,objExplosive(n)):objHitSolid(n):obj(n)=0
						;Goto objDone
					End If
					objYSpeed(n)=0
					If objXspeed(n) < 1 Then  objXspeed(n)=1
					objHitSolid(n)
					objDir(n)=4
					objLife(n)=objLife(n)-5
					If objLife(n) =< 0 Then obj(n)=0:makeChunk(0,xObj(n),yObj(n),2,objHitChunk(n))
					If objSuper(n)=1 Then zBlock(nn)=0
					If zblock(nn)=1 Then
						zBlocked(nn)=1:zBlockSeq(nn)=0:zface(nn)=4:zblowDir(nn)=zface(nn)
						zBlockTime(nn)=objImpact(n)*2:zBlockDir(nn)=2
						makechunk(n,zx(nn),yobj(n),2,1)
						zBLockLife(nn)=zBlockLife(nn)-objDamage(n)
						If zBlockLife(nn) <1 Then
							zBlock(nn)=0:zBlocked(nn)=0
							If gameSound Then PlaySound brokensnd
						Else
							If gamesound Then PlaySound blockedsnd
						EndIf
					EndIf
					If Not zBlock(nn) Then
						zlife(nn)=zlife(nn)-objdamage(n)
						zDamage#(nn)=zDamage#(nn)+objDamage(n)
	
						If zStone(nn)=0 Then
							zFallDir(nn)=2
							zface(nn)=4:zjump(nn)=0:zBouncedgnd(nn)=0:zhit(nn)=1
							
							If objHitMode(n)=0 Then
								calcBlow(nn,n,0,zDamage(nn))
							Else
								calcObj(nn,n)
							EndIf
							
							zBlow(nn)=0:zBlowStill(nn)=0:zHitSeq(nn)=0
						EndIf
						makechunk(n,zx(nn),yobj(n),2,5)
						If gameSound =1 Then PlaySound objHitSound(n)
						If objTrailType(n) > 0 Then zTrail(nn)=1:zTrailSeq(nn)=0:zTrailType(nn)=objTrailType(n)
					EndIf
					If zLife(nn) < 1 Then zScore(objOwner(n))=zScore(objOwner(n))+1
					Goto objDone
				EndIf
				EndIf
			  EndIf
			EndIf
			zShield(nn)=oldshield
		Next	
	Next
  Next
	
Case 4
	
  For hh=1 To objHeight(n) Step 14
	For q=0 To objsize(n) Step 1
		For nn= 1 To zzamount
			oldshield=zshield(nn)
			If objSuper(n)=1 Then zShield(nn)=0
			If teamAttack=1 Or zTeam(objOwner(n)) <> zteam(nn) Then
			  If nn<>objOwner(n) And objHurt(n) And zShield(nn)=0 And zon(nn)=1 Then
                                If (zx(nn) => xObj(n)-(objSide(n)+zside(nn)+5) And zx(nn) =< xObj(n)+(objSide(n)+zside(nn)+5)) And (zy(nn) => yObj(n)-(objHeight(n)+5) And zy(nn) =< (yObj(n)+zHeight(nn)+5)) Then
				If ImageRectCollide(zCurPic(nn),zx(nn)-(ImageWidth(zCurPic(nn))/2),zy(nn)-ImageHeight(zCurPic(nn))+1,0,xobj(n)-objSide(n)+q,yobj(n)-hh,1,1) Then
					If objExplosive(n) > 0 And objHurt(n)=1 Then
						objexp(n,xObj(n),yobj(n)+10,objExplosive(n)):objHitSolid(n):obj(n)=0
						;Goto objDone
					End If
					objYSpeed(n)=0
					If objXspeed(n) < 1 Then  objXspeed(n)=1
					objDir(n)=2
					objHitSolid(n)
					objLife(n)=objLife(n)-5
					If objLife(n) =< 0 Then obj(n)=0:makeChunk(0,xObj(n),yObj(n),2,objHitChunk(n))
					If objSuper(n)=1 Then zBlock(nn)=0
					If zblock(nn)=1 Then
						zBlocked(nn)=1:zBlockSeq(nn)=0:zface(nn)=2:zblowDir(nn)=zface(nn)
						zBlockTime(nn)=objImpact(n)*2:zBlockDir(nn)=4
						makechunk(n,zx(nn),yobj(n),2,1)
						zBLockLife(nn)=zBlockLife(nn)-objDamage(n)
						If zBlockLife(nn) <1 Then
							zBlock(nn)=0:zBlocked(nn)=0
							If gameSound Then PlaySound brokensnd
						Else
							If gamesound Then PlaySound blockedsnd
						EndIf
					EndIf
					If Not zBlock(nn) Then
						zlife(nn)=zlife(nn)-objdamage(n)
						zDamage#(nn)=zDamage#(nn)+objDamage(n)
						If zstone(nn)=0 Then
							zFallDir(nn)=4
							zface(nn)=2:zjump(nn)=0:zBouncedgnd(nn)=0:zhit(nn)=1
							If objHitMode(n)=0 Then
								calcBlow(nn,n,0,zDamage(nn))
							Else
								calcObj(nn,n)
							EndIf
							
							zBlow(nn)=0:zBlowStill(nn)=0:zHitSeq(nn)=0
						EndIf
						makechunk(n,zx(nn),yobj(n),2,5)
						If gameSound Then PlaySound objHitSound(n)
						If objTrailType(n) > 0 Then zTrail(nn)=1:zTrailSeq(nn)=0:zTrailType(nn)=objTrailType(n)
					EndIf
					If zLife(nn) < 1 Then zScore(objOwner(n))=zScore(objOwner(n))+1
					Goto objDone
				EndIf
				EndIf
			  EndIf
			EndIf
			zShield(nn)=oldshield
		Next	
	Next
  Next	
End Select
.objDone

.objFalling

Select objDir(n)
Case 2:xobj(n)=xobj(n)+objXspeed(n)
Case 4:xobj(n)=xobj(n)-objXspeed(n)
End Select

EndIf

If yObj(n) > dscrlimit Or xobj(n) < lscrlimit Or xobj(n) > rscrlimit Or yobj(n) < uscrlimit Then objHitSolid(n):obj(n)=0

End Function


;------------------------------ consume item -------------------------------
Function objConsume(n,o)

Select objEat(n)
Case 1
	If gamesound Then PlaySound energySnd
	zTrail(o)=1 : zTrailSeq(o)=0 : zTrailType(o)=3
	zLife(o)=zLife(o)+100
	zDamage(o)=0
	zGotObj(o)=0
	objHitSolid(n)
	obj(n)=0

End Select

End Function
;----------------------------Object spawning-----------------------------------------------------
Function spawnObj()
sa=Rand(1,1000)
If obj(objAmount)=0 Then objAmount=objAmount-1
If objAmount < 0 Then objAMount=0
tries=0
For n=1 To maxobjAmount
	If alwaysSpawnObj And obj(n)=0 Then Goto AlwaysSO
	If obj(n)=0 And (sa > 0 And sa < objFrequency+1 ) And NoUserInput=0 Then
		If n > objAmount Then objAmount=n
		.AlwaysSO
		objType(n)=Rand(1,objTypeN)
		.TrySpawnAgain
		xobj(n)=Rand(100,540):yObj(n)=Rand(20,150)
		tries=tries+1
		If objTypeOut(objType(n))=1 Then
			If tries > 100 Then Exit
			Goto AlwaysSO
		EndIf
		If Not ImageRectCollide(map,0,0,0,xObj(n)-(objSide(n)+8),yObj(n)-(objheight(n)+30),30,30) Then
			For i=1 To platAmount
			If platHeight(i) > 1 Then	;If inside plat/wall Then try again
				If yobj(n) > yPlat(i) -10 And yobj(n)-30 =< yPlat(i)+platHeight(i) Then       
					If xobj(n) => xoldPlat(i)-15 And xobj(n) =< xoldPlat(i)+(platWidth(i)+15) Then
				    	Goto TrySpawnAgain  	
					EndIf
			    EndIf
		    EndIf
			Next
			
			If gamesound Then PlaySound clicksnd
			makechunk(n,xobj(n),yobj(n),2,1)
			objData(n,0)
			objXspeed(n)=0
			objYSpeed(n)=0
			Obj(n)=1
			objHurt(n)=0
			Goto ObjHasbeenSpawned
		EndIf
	EndIf
Next
.ObjHasbeenSpawned
End Function
;---------------------------ObjHitSolid--------------------------------------------------------
Function objHitSolid(n)

If objXspeed(n) > 1 Then objXspeed(n)=1
;ObjOwner(n)=0
ObjHurt(n)=0
objTaken(n)=0


End Function
;-------------------------- Platform -----------------------------------------------------------
Function plat(n)

For n=1 To platAmount


p=platCurPoint(n)
If platSpecial(n)=0 Then platXDir(n)=0:platYDir(n)=0
xOldplat(n)=xplat(n)

If (platUseTrigger(n) And eventN(platEventN(n))=1) Or platSpecial(n)=1 Then Goto skipPlatMove

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
		If platReachedDest(n)=0 And platEventN2(n) > 0 Then
			platReachedDest(n)=1
			If EventN(platEventN2(n))=1 Then eventN(platEventN2(n))=0 Else eventN(platEventN2(n))=1
		EndIf
	Else
		xplatDest(n)=0:yPlatDest(n)=0
		platCurPoint(n)=platCurPoint(n)+1
	EndIf
	If platCurPoint(n) > PlatPointsAmount(n) Then platCurPoint(n)=1
EndIf

.skipPlatMove

;players x plat collision
For nn=1 To zzamount
.tryPlatAgain

	If zon(nn)=1 And zx(nn) => xoldPlat(n)-zSide(nn) And zx(nn) =< xoldPlat(n)+(platWidth(n)+zSide(nn)) And zJump(nn)=0 Then

		If zy(nn) => yPlat(n)-3 And zy(nn) =< yPlat(n)+4 Then

			If zBeenHere(nn)=1 Then Goto platDone
			If platXspeed(n) > 0 Then zBeenHere(nn)=1
			If zantiplat(nn)=1 Then
				If zy(nn) => yplat(n) And zForceAntiPlat(nn)=0 Then
					If zCanFly(nn) = 1 And platHeight(n) < 1 Then
						nada=nada
					Else
						zy(nn)=yplat(n)-1
					EndIf
				EndIf
				 				
				;Goto platDone
			Else
				zonplat(nn)=1:zNoGrav(nn)=1
				If platHeight(n)>3 Then zonThickPlat(nn)=1
				zOnPlatN(nn)=n
				Select platXDir(n)
				Case 2:zx(nn)=zx(nn)+platXSpeed(n)
				Case 4:zx(nn)=zx(nn)-platXSpeed(n)
				End Select
				
				Select platYDir(n)
				Case 3:zy(nn)=zy(nn)+platYSpeed(n)
				Case 1:zy(nn)=zy(nn)-platYSpeed(n)
				End Select
				zy(nn)=yplat(n)-1
				If zCanFly(nn)=1 Then zy(nn)=zy(nn)-1
				;Goto platDone
			EndIf	
		EndIf
	EndIf
	
	hh=zHeight(nn)
	If platHeight(n) > 1 And zon(nn) Then 
	  If zy(nn) > yPlat(n) +5 And zy(nn)-hh =< yPlat(n)+platHeight(n) Then       
		If zx(nn) => xoldPlat(n)-zSide(nn) And zx(nn) =< xoldPlat(n)+(platWidth(n)+zSide(nn)) Then

		   	ph=36
			 If zy(nn)-hh > yplat(n)+(platHeight(n)-ph) And zx(nn) > xoldPlat(n) And zx(nn) < xoldPlat(n)+platWidth(n) Then
				zHitHead(nn)=1:zJump(nn)=0
				If platYspeed(n) > 2 Then zy(nn) = (yPlat(n)+platHeight(n))+zHeight(nn)
				If (zongnd(nn)=1 Or zonplat(nn)=1)And zGrabbed(nn)=0 Then	;crushes player
					makeChunk(nn,zx(nn),zy(nn)-15,2,zDeathChunk(nn))
					playDeathSnd(n)
					zlives(nn)=zlives(nn)-1
					killZ(nn)
					Goto platDone
					
				EndIf
				
				zUpFallSpeed(nn)=0:zUpFallTime(nn)=0
			Else
				If zoldx(nn) < xoldPlat(n)+(platWidth(n)/2) Then
                     If platYspeed(n) => 3 And platyDir(n)=1 And zy(nn) < yPlat(n)+15 Then
					   zongnd(nn)=1 : zjump(nn)=0
					   zy(nn)=yPlat(n)-1
					   Goto tryPlatAgain
					 Else
					   zx(nn)=xoldPlat(n)-(zSide(nn)+1)
					   zLeftCollide(nn)=1
                     EndIf
					
	       		Else
                    If platYspeed(n) => 3 And platyDir(n)=1 And zy(nn) < yPlat(n)+15 Then
                       zongnd(nn)=1 : zjump(nn)=0
                       zy(nn)=yPlat(n)-1
					   Goto tryPlatAgain
					Else
					   zx(nn)=xoldPlat(n)+(platWidth(n)+zSide(nn)+1)
					   zRightCollide(nn)=1
					EndIf
		       	EndIf
				
				If zLeftCollide(nn)=1 And zRightCollide(nn)=1 Then	;crushes player
					makeChunk(nn,zx(nn),zy(nn)-15,2,zDeathChunk(nn))
					playDeathSnd(n)
					zlives(nn)=zlives(nn)-1
					killZ(nn)
					Goto platDone
				EndIf
				
				If zhit(nn)=1 And zHitSeq(nn) > 2 And zongnd(nn)=0 And hitAlready=0 Then
                                        hitAlready=1
					Select zFalldir(nn)
						Case 4
							
							If platXDir(n) = 0 Or platXspeed(n) < .5 And zhithead(nn)=0 Then
							;If platXspeed(n) < 1 Then
								quake=1:quakeSeq=0
								zface(nn)=4:zFallDir(nn)=2
								makeChunk(nn,zx(nn)-zside(nn)+3,zy(nn)-5,2,16)
								makeChunk(nn,zx(nn)-zside(nn)+3,zy(nn)-25,2,16)
								If gameSound = 1 Then PlaySound zhitwallsnd
							EndIf
						Case 2
							
							If platXDir(n) = 0 Or platXspeed(n) < .5 Then
								quake=1:quakeSeq=0
								zFace(nn)=2:zFallDir(nn)=4
								makeChunk(nn,zx(nn)+zside(nn)+3,zy(nn)-5,2,16)
								makeChunk(nn,zx(nn)+zside(nn)+3,zy(nn)-25,2,16)
								If gameSound = 1 Then PlaySound zhitwallsnd
							EndIf
					End Select
					If blockKey(nn)=1 And fightMode=2 Then zHitSeq(nn)=zFallTime(nn)-5
				EndIf
			EndIf
	    EndIf
      EndIf
	EndIf
	
	
.platDone
Next

;objects x plat collision
For nn=1 To objAmount
    If xobj(nn) => xoldPlat(n) And xobj(nn) =< xoldPlat(n)+(platWidth(n)) And objYspeed(nn)=>0 Then
		If yobj(nn) => yPlat(n)-3 And yobj(nn) =< yPlat(n)+5 Then
			Select platXDir(n)
			Case 2:xobj(nn)=xobj(nn)+platXSpeed(n)
			Case 4:xobj(nn)=xobj(nn)-platXSpeed(n)
			End Select
			Select platYDir(n)
			Case 3:yobj(nn)=yobj(nn)+platYSpeed(n)
			Case 1:yobj(nn)=yobj(nn)-platYSpeed(n)
			End Select
			If objExplosive(nn)>0 And objHurt(nn) Then objexp(nn,xObj(nn),yobj(nn)+10,objExplosive(nn)):objHitSolid(nn):obj(nn)=0
			objHitSolid(nn)
			yobj(nn)=yplat(n)-1
			
			If objBounce(nn) = 0 Then
			    objBounce(nn) = 1
			    If objYspeed(nn) > 3 Then
					objYspeed(nn) = -2
				Else
				    objYspeed(nn) = -1
				EndIf
			    ;objXspeed(nn) = objXspeed(nn)-2
			    ;If objXspeed(nn) < 0 Then objXspeed(nn) = 0
			Else
			    objXspeed(nn)=0:objYSpeed(nn)=0
			EndIf

		EndIf
	EndIf
  	If platHeight(n) > 1 Then	
	If yobj(nn) > yPlat(n) +4 And yobj(nn)-objHeight(nn) =< yPlat(n)+platHeight(n) And objTaken(nn)=0 And obj(nn)=1 Then       
		If xobj(nn) => xoldPlat(n)-objSide(nn) And xobj(nn) =< xoldPlat(n)+(platWidth(n)+objSide(nn)) Then
	      	If objExplosive(nn) >0 And objHurt(nn)=1 Then objexp(nn,xObj(nn),yobj(nn)-5,objExplosive(nn)):objHitSolid(nn):obj(nn)=0
			If yobj(nn)-objHeight(nn) > yplat(n)+(platHeight(n)-objHeight(nn)) And xobj(nn) => xoldPlat(n)+5 And xobj(nn) =< xoldPlat(n)+(platWidth(n)-5) Then
				objHitSolid(nn):objYspeed(nn)=0
				yobj(nn)=yPlat(n)+platHeight(n)+objHeight(nn)+3
				If platYDir(n)=3 Then yobj(nn)=yobj(nn)+5 : objYspeed(nn)=(platYspeed(n))
			Else
				If gameSound =1 And objXspeed(nn) > 1 And platXspeed(n) = 0 And inSight(xObj(nn),yObj(nn)) Then PlaySound zhitwallsnd
				objHitSolid(nn):objYspeed(nn)=0
				If xobj(nn) < xoldPlat(n)+(platWidth(n)/2) Then
					xobj(nn)=xoldPlat(n)-(objSide(nn)+1)
					objDir(nn)=4       
	       		Else
	            	xobj(nn)=xoldPlat(n)+(platWidth(n)+(objSide(nn)+1))
					objDir(nn)=2
		       	EndIf
			EndIf
		EndIf
    EndIf
  EndIf
Next

;Flags x plat collision
For nn=1 To flagAmount	
	If yFlag(nn) > yPlat(n) And yFlag(nn) =< yPlat(n)+5 Then       
		If xFlag(nn) => xoldPlat(n) And xFlag(nn) =< xoldPlat(n)+(platWidth(n)) Then
	    	yFlag(nn)=yPlat(n) 		
		EndIf
	EndIf
Next


Next

End Function
;------------------------------Hazard moving boxes-----------------------------------
Function boxes(n)

p=boxCurPoint(n)
boxXDir(n)=0:boxYDir(n)=0
xOldbox(n)=xbox(n)

If (boxUseTrigger(n) And eventN(boxEventN(n))=1) Then Goto skipBoxMove

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
			makeChunk(0,xBox(n)+(boxWidth(n)/2),yBox(n)+(boxHeight(n)/2),0,boxChunkType(n))
			If gameSound Then PlaySound boxHitSound(n)
			boxon(n)=0:drawBox(n)=0
			ybox(n)=9999
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

For nn=1 To zzamount	;box x player collision
	If zx(nn) => xbox(n)-zside(nn) And zx(nn) =< xbox(n)+(boxWidth(n)+zside(nn)) And zhitbybox(nn)=0 And zon(nn)=1 And zTempShield(nn) =0 Then
		If zy(nn)-2 => ybox(n) And zy(nn)-(zheight(nn)-2) =< ybox(n)+ boxHeight(n) And targetBox(n)=0 Then
			If zx(nn) < (xbox(n)+boxWidth(n)/2) Then zface(nn)=2:zFallDir(nn)=4
			If zx(nn) => (xbox(n)+boxWidth(n)/2) Then zface(nn)=4:zFallDir(nn)=2
			If zGotobj(nn)>0 Then objhitsolid(zGotobj(nn)):zGotobj(nn)=0
			zjump(nn)=0
			zLife(nn)=zlife(nn)-boxDamage(n)
			zDamage#(nn)=zDamage#(nn)+boxDamage(n)
			zBouncedgnd(nn)=0
			zhitbybox(nn)=1
			If boxHitMode(n)=2 Then
				calcBox(nn,n)
			Else
				calcBlow(nn,n,boxHitMode(n),zDamage(nn))
			EndIf
				If zy(nn) < yBox(n) Then
					makechunk(n,zx(nn),yBox(n),2,boxChunkType(n))
				Else
					If zy(nn)-zHeight(nn) > yBox(n)+boxHeight(n) Then
						makechunk(n,zx(nn),yBox(n)+boxHeight(n),2,boxChunkType(n))
					Else
					    makechunk(n,zx(nn),zy(nn)-zDuckHeight(nn),2,boxChunkType(n))
					EndIf
				EndIf

				;Else
				;	makechunk(n,zx(nn),yBox(n)+boxHeight(n) ,2,boxChunkType(n))
				;EndIf
			
			If gameSound =1 Then PlaySound boxHitSound(n)
			
		EndIf
	EndIf
Next

End Function

;------------------- Explosion -----------------------
Function explo(n)

For nn=1 To zzamount
	If zx(nn)+zside(nn) => xExp(n)-expSide(n) And zx(nn)-zside(nn) =< xExp(n)+expSide(n) And zon(nn)=1 Then
		If zy(nn) > yExp(n)-expHeight(n) And zy(nn)-zheight(nn) < yExp(n) And zshield(nn)=0 Then
			If zStone(nn)=0 Then 
				If zx(nn) => xExp(n) Then zface(nn)=4:zFallDir(nn)=2:zblockDir(nn)=2
				If zx(nn) =< xExp(n) Then zface(nn)=2:zFallDir(nn)=4:zBlockDir(nn)=4
			EndIf
			If zblock(nn)=1 Then
				zBlocked(nn)=1:zBlockSeq(nn)=0:zblowDir(nn)=zface(nn)
				zBlockTime(nn)=expImpact(n)
				
				makechunk(n,zx(nn),zy(nn)-zduckheight(nn),2,1)
								
				zBLockLife(nn)=zBlockLife(nn)-expDamage(n)
				If zBlockLife(nn) <1 Then
					zBlock(nn)=0:zBlocked(nn)=0
					If gameSound=1 Then PlaySound brokensnd
				Else
					If gameSound=1 Then PlaySound blockedsnd
				EndIf
			EndIf
			
			If zblock(nn)=0 Then
				zLife(nn)=zlife(nn)-expDamage(n)
				zDamage#(nn)=zDamage#(nn)+expDamage(n)
				If zStone(nn)=0 Then
					zjump(nn)=0:zhit(nn)=1
					zBouncedgnd(nn)=0
					zBlow(nn)=0:zBlowStill(nn)=0:zHitSeq(nn)=0
					calcBlow(nn,n,0,zDamage(nn))
				EndIf
				makechunk(n,zx(nn),zy(nn)-zduckheight(nn),2,7)
			EndIf
			
		EndIf
	EndIf
Next
explosion(n)=0
If gamesound Then PlaySound explosionSound(n)

End Function
;-----------Make explosion ------------------------------------------------------------------------
Function makeExp(n,x,y,kind)

For i=1 To expAmount
	If explosion(i)=0 Then
		explosion(i)=1
		expType(i)=kind
		expData(i)
		xExp(i)=x:yExp(i)=y
		Exit
	EndIf	
Next

End Function

;---------------------- Shot explodes --------------------------------
Function shotExp(n,x,y,kind)

Select kind
Case 1	;shot explosion
	makeExp(n,x,y,kind)
	makeChunk(n,x,y,2,4)
	If gamesound Then PlaySound shotExplosionSound(n)

End Select

End Function

;-------------- Special A.I. -------------------------
Function zSpecialAI(n,nn)
:hitkey(n)=1

Select curGuy(n)

;-----------------------------------
Case 41	;turtle on cloud
;-----------------------------------

If Rand(1,30) = 25 Then
	aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf

If zon(aitarget(n))=0 Then aigettarget(n)

If zhitHead(n)=1 Then zy(n)=zy(n)+1
If zy(n) < yScr + 100 Then zy(n)=zy(n)+1 : zjump(n)=0
If zy(n) > yScr + 105 Then zy(n)=zy(n)-1 :

;flies to the target
If zx(nn) => zx(n)+2 Then rightkey(n)=1
If zx(nn) =< zx(n)-2 Then leftKey(n)=1

;aim on closest enemy
If zon(nn)=1 And zteam(nn) <> zteam(n) And aiCurLevel(n) > 3 Then
	If zx(nn) => zx(n)-70 And zx(nn) =< zx(n)+70 And zy(nn) > zy(n) Then
		shotKey(n)=1
	EndIf
EndIf

;-----------------------------------
Case 43	;laser shooter
;-----------------------------------
zHitByRect(n)=1
If aiLevel(n)=1 Then
	shotkey(n)=1
Else
	For nn=1 To zzamount
	If zon(nn)=1 And zteam(nn) <> zteam(n) Then
		If (zx(nn) => zx(n)-400 And zx(nn) < zx(n)+400) And (zy(n) > zy(nn)-60 And zy(n) < zy(nn)+40) Then
			;PlaySound clickSnd
			shotKey(n)=1
			aiTarget(n)=nn
			Exit
		EndIf
	EndIf
	Next
EndIf

;-----------------------------------
Case 45	; bombing ship	
;-----------------------------------

;If zy(n) < yScr + 100 Then zy(n)=zy(n)+2

;flies to the designated direction
If zFace(n)=2 Then rightkey(n)=1
If zFace(n)=4 Then leftKey(n)=1

;bombs when appropriate
If zx(n) < xScr+650 And zx(n) > xScr + 20 Then
	shotKey(n)=1:Goto aiDone1
EndIf
If zx(n) > xScr+800 Or zx(n) < xScr - 150 Then
	zblowseq2(n)=zblowseq2(n)+1
	If zBlowSeq2(n) > 200 Then zon(n)=0
EndIf


;-----------------------------------
Case 46	;ray ball shooter
;-----------------------------------
zHitByRect(n)=1
shotKey(n)=1

;-----------------------------------
Case 47	;soldier
;-----------------------------------
If Rand(1,30) = 25 Then
	aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf

If zon(aitarget(n))=0 Then aigettarget(n)
If rendert=1 Then aiGetNearByTarget(n)

;duck when being shot
For k=1 To shotAmount
	If shot(k)=1 And xshot(k) => zx(n)-120 And xshot(k) =< zx(n) + 120 And aiCurLevel(n) > 2 And zShield(n)=0 Then
		If yshot(k) => zy(n)-50 And yshot(k) =< zy(n) +10 And zTeam(n) <> zTeam(shotOwner(k)) Then
			downKey(n)=1
			Goto aiDone1
		EndIf
	EndIf
Next

nn = aitarget(n)
If zx(nn) => zx(n) Then rightkey(n)=1 Else leftKey(n)=1

If zon(nn)=1 And zteam(nn) <> zteam(n) Then
	If zx(nn) => zx(n)-500 And zx(nn) =< zx(n)+500 And zy(nn) > zy(n)-60 And zy(nn) < zy(n)+10 And aiCurLevel(n) > 3 Then
		shotKey(n)=1
	EndIf
EndIf

;-----------------------------------
Case 48	;flying cylinder
;-----------------------------------

If Rand(1,30) = 25 Then
	aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf
If zon(aitarget(n))=0 Then aigettarget(n)

If zhitHead(n)=1 Then zy(n)=zy(n)+1
If aiTarget(n) <> 0 Then 
		
	If zy(n) < zy(nn)-18 And zblow(n)=0 Then zy(n)=zy(n)+1
	If zy(n) > zy(nn)-15 And zblow(n)=0 Then zy(n)=zy(n)-1
		
	If zx(n) < zx(nn)-80 Then rightkey(n)=1 
	If zx(n) > zx(nn)+80 Then leftkey(n)=1 
	
	If zx(n) < zx(nn) And zface(n)=4 Then rightkey(n)=1 
	If zx(n) > zx(nn) And zface(n)=2 Then leftkey(n)=1 
	
	If zon(nn)=1 And zteam(nn) <> zteam(n) And aiCurLevel(n) > 3 Then
		If (zx(nn) => zx(n)-400 And zx(nn) < zx(n) And zface(n)=4) Or (zx(nn) =< zx(n)+400 And zx(nn) > zx(n) And zface(n)=2) And zy(n) > zy(nn)-20 And zy(n) < zy(nn)-14 Then
			shotKey(n)=1
		EndIf
	EndIf
EndIf

;-----------------------------------
Case 49	;Dragon
;-----------------------------------
a=1: b=a+5: c=b+5: d=c+5: e=d+5: f=e+5: g=f+5 	


If Rand(1,30) = 25 Then
	aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf

zBlow(n)=1
If zCurBlow(n) <> 2 Then zCurBlow(n)=1

;Flying animation control
zwalkseq2(n)=zwalkseq2(n)+1
If zwalkseq2(n) => g Then zwalkseq2(n)=1
If zwalkseq2(n) =>a And zwalkseq2(n) < b Then zFlyAni(n)=1:zfa(n)=0:Goto drewDragon
If zwalkseq2(n) =>b And zwalkseq2(n) < c Then zFlyAni(n)=1:zfa(n)=1:Goto drewDragon
If zwalkseq2(n) =>c And zwalkseq2(n) < d Then zFlyAni(n)=1:zfa(n)=2:Goto drewDragon
If zwalkseq2(n) =>d And zwalkseq2(n) < e Then zFlyAni(n)=1:zfa(n)=3:Goto drewDragon
If zwalkseq2(n) =>e And zwalkseq2(n) < f Then zFlyAni(n)=1:zfa(n)=2:Goto drewDragon
If zwalkseq2(n) =>f And zwalkseq2(n) < g Then zFlyAni(n)=1:zfa(n)=1:Goto drewDragon

.drewDragon
zani(n)=zFlyAni(n) : zf(n)=zfa(n)

If zon(aitarget(n))=0 Then aigettarget(n)

If zhitHead(n)=1 Then zy(n)=zy(n)+1.5
If aiTarget(n) <> 0 Then 
		
	If zy(n) < zy(nn)-34 Then zy(n)=zy(n)+1.5
	If zy(n) > zy(nn)-30 Then zy(n)=zy(n)-1.5
		
	If zx(n) < zx(nn)-120 Then rightkey(n)=1 
	If zx(n) > zx(nn)+120 Then leftkey(n)=1 
	
	If zx(n) < zx(nn) And zface(n)=4 Then rightkey(n)=1 
	If zx(n) > zx(nn) And zface(n)=2 Then leftkey(n)=1 
	
	If (zx(nn) => zx(n)-80 And zx(nn) < zx(n) And zface(n)=4) Or (zx(nn) =< zx(n)+80 And zx(nn) > zx(n) And zface(n)=2) And zy(nn) > zy(n)-15 And aiCurLevel(n) > 3 Then
		zCurBlow(n)=3	;spit acids
		Goto aiDone1
	EndIf
	
	If (zx(nn) => zx(n)-400 And zx(nn) < zx(n) And zface(n)=4) Or (zx(nn) =< zx(n)+400 And zx(nn) > zx(n) And zface(n)=2) And zy(n) > zy(nn)-38 And zy(n) < zy(nn)-5 And aiCurLevel(n) > 3 Then
		zCurBlow(n)=2	;spit fire
		If zx(nn) => zx(n)-150 And zx(nn) < zx(n)+150 Then
			zBlowSeq2(n)=99
		Else
			zBlowSeq2(n)=0
		EndIf
	EndIf

EndIf

;-----------------------------------
Case 50	;Laser beam
;-----------------------------------
Select zLives(n)
 Case 1:zy(n)=zy(n)-zvar3(n)
 Case 2:zx(n)=zx(n)+zvar3(n) 
 Case 3:zy(n)=zy(n)+zvar3(n) 
 Case 4:zx(n)=zx(n)-zvar3(n) 
End Select

zFace(n)=aiLevel(n)

shotKey(n)=1

;-----------------------------------
Case 52	;Bag
;-----------------------------------
If zVar1(n)=1 Then 	;If bag has energy limit
	If zLife(n) < 0 Then
		makeChunk(n,zx(n),zy(n)-15,2,zDeathChunk(n))
		playDeathSnd(n)
		zlives(n)=zlives(n)-1
		killZ(n)
	EndIf
Else
	zLife(n)=999
EndIf



End Select
.aiDone1

End Function
;--------------------A.I. -----------------------------
Function AI(n,e)
nn=e:hitkey(n)=1

saa=Rand(1,30)
If saa=25 Then
	aiCurLevel(n)=Rand(aiLevel(n),5)
	;If zBLow(n) =0 Then
		.TryOtherblow
		sa=Rand(1,6)
		Select sa
			Case 1:NextBLow(n)=1
			Case 2:NextBLow(n)=4
			Case 3:NextBLow(n)=7 
			Case 4:NextBLow(n)=9
			Case 5:NextBLow(n)=10
			Case 6:If zSuperBar(n)=> 100 Then NextBlow(n)=14 Else Goto TryOtherblow
		End Select
	;EndIf
EndIf

;chooses closest enemy For target
For k=1 To zzamount
If zon(k)=1 And (zteam(k) <> zteam(n)) And zHelper(k)=0 Then
	If zx(k) => zx(n)-70 And zx(k) =< zx(n)+70 Then
		If zy(k) => zy(n)-80 And zy(k) =< zy(n)+60 Then
			aiWalk(n)=1
			gotTarget=1
			aiTarget(n)=k:Exit
		EndIf
	EndIf
EndIf
Next

For k=1 To areaAmount    ;Detects what area player is located at
	If zx(n) > saLlimit(k) And zx(n) < saRlimit(k) And zy(n) > saY(k) And zy(n) < saY(k)+saH(k) Then
		If zx(n) > saLlimit(k)-15 And zx(n) < saLlimit(k)+105 Then onEdge=1
		If zx(n) < saRlimit(k)+15 And zx(n) > saRlimit(k)-105 Then onEdge=1
		safeArea=1
		curArea=k
		If zx(n) > saLlimit(k)-15 And zx(n) < saLlimit(k)+10 Then onEdge2=1
		If zx(n) < saRlimit(k)+15 And zx(n) > saRlimit(k)-10 Then onEdge2=1
		;Exit
	EndIf
Next

a=curArea


For k=1 To dAreaAmount	;If player is located in dangerous/jumping area
	If zx(n) > daLlimit(k) And zx(n) < daRlimit(k) And zy(n) > daY(k) And zy(n) < daY(k)+daH(k) Then
		;safeArea=0
		aiWalk(n)=1
		inDArea=1
		aa=k
		Exit
	EndIf
Next

If inDArea=1 Then
	If daType(aa)=1 Then		;daType 1 is For up special
		If dFleeDir(aa) =2 And zface(n)=2 Then
			rightkey(n)=1
			If zNoAirSpecial(n)=0 Then
				upkey(n)=1:specialKey(n)=1:Goto aidone
			Else
				shotKey(n)=1 :Goto AiDone
			EndIf
		EndIf	
		If dFleeDir(aa) =4 And zface(n)=4 Then
			leftkey(n)=1
			If zNoAirSpecial(n)=0 Then
				upkey(n)=1:specialKey(n)=1:Goto aidone
			Else
				shotKey(n)=1 :Goto AiDone
			EndIf
		EndIf
		Goto aiDone
	EndIf
	If daType(aa)=0 And zongnd(n)=1 And aiTarget(n) <> 0 Then	;daType 0 is For jumping
		If daTargetH(aa)=5 Then jumpKey(n)=1:aiJumpedRand(n)=1
		If zy(nn) < zy(n)-daTargetH(aa) And zon(nn) = 1 Then jumpKey(n)=1:aiJumpedRand(n)=1
	EndIf
EndIf

;try to dodge bad boxes
For k=1 To boxAmount
If boxXSpeed(k) > 0 And (boxXDir(k)=2 Or boxXDir(k)=4) And boxHarmless(k) =0 Then ;boxes going horizontally
	;duck
	If zx(n) => xBox(k)-15 And zx(n) =< xBox(k)+boxWidth(k)+15 And boxon(k) Then
		If zy(n)-zupheight(n) => yBox(k) And zy(n)-zupheight(n) =< yBox(k) + (boxheight(k)+10) Then
			downKey(n)=1:Goto aidone
		EndIf
	EndIf
	If zx(n) => xBox(k)-50 And zx(n) =< xBox(k)+boxWidth(k)+50 And boxon(k) Then
		If zy(n) => yBox(k)-7 And zy(n) =< yBox(k)+(boxheight(k)+10) Then
			If zx(n)+zSide(n) < xbox(k) And boxXDir(k)=2 Then Goto aidone
			If zx(n)-zSide(n) > xbox(k)+boxWidth(k) And boxXDir(k)=4 Then Goto aidone	
				If zongnd(n) Or (zjumpseq(n) > zjumplimit(n)-3 And zjumpseq(n) < zjumplimit(n)) Then jumpkey(n)=1:Goto aidone
				If zjump(n)=1 Then jumpkeydown(n)=1:Goto aidone
				If zjump(n)=0 Or zjump2(n)=0 Then
					
					If zNoAirSpecial(n)=0 Then
						upkey(n)=1:specialKey(n)=1:Goto aidone
					Else
						shotKey(n)=1 :Goto AiDone
					EndIf
				EndIf
			EndIf
	EndIf
EndIf

If boxYSpeed(k) > 0 And (boxYDir(k)=1 Or boxYDir(k)=3) And boxHarmless(k) = 0 Then	;boxes going vertically
	If zx(n) => xBox(k)-20 And zx(n) =< xBox(k)+boxWidth(k)+20 Then
		If zy(n) => yBox(k) And zy(n)-zupheight(n) =< yBox(k)+(boxYspeed(k)*30) Then
			If zx(n) =< xbox(k) Then leftKey(n)=1:Goto aidone
			If zx(n) => xbox(k)+(boxWidth(k)) Then rightKey(n)=1:Goto aidone
			If zx(n) =< xbox(k)+(boxWidth(k)/2) Then blockKey(n)=1:leftKeyHit(n)=1:leftKey(n)=1:Goto aidone
			If zx(n) > xbox(k)+(boxWidth(k)/2) Then blockKey(n)=1:rightKeyHit(n)=1:rightKey(n)=1:Goto aidone
		EndIf
	EndIf
	
EndIf
Next

;Flee area
If dangerArea(curArea)=1 Then	
	If FleeDir(curArea) =2 Then
		rightkey(n)=1
		If zjump(n)=0 Then jumpKey(n)=1:jumpKeyDown(n)=1:Goto aiDone
	EndIf
	If FleeDir(curArea) =4 Then
		leftkey(n)=1
		If zjump(n)=0 Then jumpKey(n)=1:jumpKeyDown(n)=1:Goto aiDone
	EndIf
	Goto aiDone
EndIf

;If grabbed target, Then throw it
If zGrabs(n) = 1 And zGrabbed(zGrabsThis(n)) =1 And aiCurLevel(n) > 3 Then
	shotKey(n)=1
	blockKey(n)=0
	Goto aiDone
EndIf

;If there`s no target Then do nothing
If aiTarget(n)=0 Then Goto aiDone

;Walking
If a=0 And aiCurLevel(n) > 3  Then	
	If zx(nn) => zx(n)+20 Then rightkey(n)=1
	If zx(nn) =< zx(n)-20 Then leftKey(n)=1
Else
	If aiCurLevel(n) > 3  Then
		If zx(nn) > zx(n) Then rightkey(n)=1
		If zx(nn) =< zx(n) Then leftKey(n)=1
		;If zx(nn) > zx(n) And zx(n) < sax(curArea) + saRlimit(curArea) Then rightkey(n)=1
		;If zx(nn) =< zx(n) And zx(n) > saX(curArea) Then leftKey(n)=1
	EndIf
EndIf

;If above target on solid ground, Then roll to side
If zx(n) > zx(nn)-2 And zx(n) < zx(nn)+2 Then
	If zy(n) < zy(nn)-zheight(nn) And zongnd(n) Then
	rightKey(n)=0:downkey(n)=0: 
	leftKeyHit(n)=1: blockKey(n)=1 :Goto aidone
	EndIf
EndIf

;go down from plataform
If zonplat(n)=1 And dangerPlat(zOnPlatN(n))=0 Then
 If zx(nn) => zx(n) - 60 And zx(nn) =< zx(n) + 60 Then
	If zy(n) < zy(nn)-2 And zongnd(nn)=1 Then
		If zDontJump(n)=0 Then downKey(n)=1:jumpKey(n)=1:Goto aidone
		;downKey(n)=1:jumpKey(n)=1:Goto aidone
	EndIf
 EndIf
EndIf

;pick up item If any
For k=1 To objAmount
	If OBJ(K)=1 And objTaken(k)=0 And zgotObj(n)=0 And zDontPickItem(n)=0 Then 	
		If xobj(k) => zx(n)-14 And xObj(k) =< zx(n)+14 And objHurt(k)=0 Then
			If yobj(k) => zy(n) -10 And yobj(k) =< zy(n) +3 Then 
				shotkey(n)=1:Goto aiDone
			EndIf
		EndIf
	EndIf
Next

;Throw Object If enemy is near
If zGotobj(n) > 0 And aiCurLevel(n) > 4 Then
 If zx(nn) => zx(n)-100 And zx(nn) =< zx(n)+100 And aiCurLevel(n) > 4 And zTempShield(nn)=0 Then
  If zy(nn) => zy(n)-120 And zy(nn) =< zy(n)+10 Then
  If (zx(n) < zx(nn) And zface(n)=2) Or (zx(n) => zx(nn) And zface(n)=4) Then
	If beatIten(zgotObj(n))=1 Then
		sn=Rand(1,10)
		If sn=1 Then grabkey(n)=1 Else shotKey(n)=1
		If shotsfired(zGotobj(n)) => objAmmo(zgotObj(n)) Then grabkey(n)=1 : shotKey(n)=0
		
	Else
		shotKey(n)=1
	EndIf
	If (zx(nn) => zx(n)-25 And zx(nn) =< zx(n)+25) And zy(nn) < zy(n)-zHeight(n) Then
		upKey(n)=1
		rightkey(n)=0:leftKey(n)=0
	EndIf
    If (zx(nn) < zx(n)-25 Or zx(nn) > zx(n)+25) Then
		If zy(nn)-zHeight(nn) > zy(n) Then downKey(n)=1
  		If zy(nn) < zy(n)-zheight(n) Then upkey(n)=1
	EndIf
	Goto aiDone
  EndIf  
  EndIf
 EndIf
EndIf


sn=Rand(1,180)	;sometimes will jump
If sn=47 And zGotobj(nn)=0 And zongnd(n)=1 Then downKey(n)=0: jumpKey(n)=1:aiJumpedRand(n)=0
If zjump(n)=1  Then jumpkeydown(n)=1
If zjumpseq(n)=zjumpLimit(n)-1 And aiJumpedRand(n)=1 Then jumpkey(n)=1:


;blocks thrown objects
For k=1 To objAmount
	If objHurt(k)=1 And obj(k)=1 And xObj(k) => zx(n)-60 And xobj(k) =< zx(n) +60 And aiCurLevel(n) > 4 And zShield(n)=0 Then
		If yObj(k) => zy(n)-60 And yobj(k) =< zy(n) +10 And zTeam(objOwner(k)) <> zTeam(n) Then
			aiWalk(n)=1
			blockKey(n)=1:Goto aidone
		EndIf
	EndIf
Next

;blocks shots
For k=1 To shotAmount
	If shot(k)=1 And xshot(k) => zx(n)-80 And xshot(k) =< zx(n) + 80 And aiCurLevel(n) > 4 And zShield(n)=0 Then
		If yshot(k) => zy(n)-50 And yshot(k) =< zy(n) +10 And zTeam(n) <> zTeam(shotOwner(k)) Then
			aiWalk(n)=1
			If (xshot(k) => zx(n)-60 And xshot(k) =< zx(n) + 60) Or shotSpeed(k) > 4 Then blockKey(n)=1:Goto aidone
			If zjump(n)=0 Then jumpkey(n)=1::aiJumpedRand(n)=1:Goto aidone
		EndIf
	EndIf
Next

blockDist(n)=zBlowDist(nn,zCurBlow(nn))

;block If enemy is attacking within the range
If zShield(n)=0 And zblow(nn)=1 And zCurBlow(nn)>0 And aiCurLevel(n) > 4 And zBlowDist(nn,zCurBlow(nn)) > 0 And zblowseq(nn) < 40 Then 
	If (zx(n) => zx(nn) And zx(n) =< zx(nn)+blockDist(n) And zface(nn)=2) Or (zx(n) => zx(nn)-blockDist(n) And zx(n) =< zx(nn) And zface(nn)=4) Then
		If zy(nn) < zy(n) + 50 And zy(nn) > zy(n) - 60 Then
			sa=Rand(1,3)
			If sa = 1 And zblowseq(nn) < 3 And zCurBlow(nn) <> 7 Then
				If curGuy(n)=9 And Rand(0,1) = 1 Then
				    downkey(n)=1:specialKey(n)=1: Goto aiDone
				EndIf
				blockKey(n)=1
				If zx(nn) > zx(n) Then rightKeyHit(n)=1 Else leftKeyHit(n)=1
				Goto aidone
			Else
				blockKey(n)=1 :Goto aidone
			EndIf
		EndIf
	EndIf
EndIf


If zTempShield(nn)=1 Then Goto aiDone
;high attack If enemy is on top
If aiCurLevel(n) > 4 Then
If (zx(nn) => zx(n)-70 And zx(nn) =< zx(n)+ 70) And (zy(nn) < zy(n) - (40+yRange(n)) And zy(nn) > zy(n) - (75+yRange(n))) Then 
	If (zongnd(n)=0 And onEdge2=0 And curArea > 0) Or (zongnd(n)=0 And dangerMove5(n)=0 And curArea > 0) Then

		If zNoAirSpecial(n)=0 Then
			upkey(n)=1:specialKey(n)=1:Goto aidone
		Else
			;shotKey(n)=1 :Goto AiDone
		EndIf
	EndIf
	a=Rand(1,4)
	If a=1 And onEdge=0 And curArea > 0 Then
		If zNoAirSpecial(n)=0 Then
			upkey(n)=1:specialKey(n)=1:Goto aidone
		Else
			shotKey(n)=1 :Goto AiDone
		EndIf
	EndIf
	a=2
	If a=>2 And a<=4 And zongnd(nn)=0 Then upkey(n)=1:shotKey(n)=1
	Goto aidone
EndIf
EndIf


;normal attack If near
If aiCurLevel(n) > 3 And safeArea=1 Then
If (zx(nn) => zx(n) And zx(nn) =< zx(n)+(zBlowDist(n,NextBlow(n))-4) And zface(n) =2) Or (zx(nn) => zx(n)-(zBlowDist(n,NextBlow(n))-4) And zx(nn) =< zx(n) And zface(n)=4) Then
	If zy(nn) < zy(n) + (20+yRange(n)) And zy(nn) > zy(n) - (40+yRange(n))  Then 
		If zx(n) < zx(nn) And zface(n)=2 Then attack=1
		If zx(n) => zx(nn) And zface(n)=4 Then attack=1
	EndIf
EndIf
EndIf

;Grabs enemy If near
If aiCurLevel(n) > 3 And safeArea=1 Then
If (zx(nn) => zx(n) And zx(nn) =< zx(n)+zGrabDist(n) And zface(n) =2) Or (zx(nn) => zx(n)-zGrabDist(n) And zx(nn) =< zx(n) And zface(n)=4) Then
	If zy(nn) < zy(n) + 3 And zy(nn) > zy(n) - 3  And zUngrabable(nn)=0 Then 
		grabKey(n)=1:Goto aiDone
	EndIf
EndIf
EndIf

;If enemy is under Then attack with flying normal attack
If aiCurLevel(n) > 4 And zongnd(n)=0 And safeArea=1 Then
	If zx(nn) => zx(n) - 80 And zx(nn) =< zx(n) + 80 Then
		If zy(n) < zy(nn) And zy(n) > zy(nn) - (85+yRange(n)) Then shotKey(n)=1:Goto aidone
	EndIf
EndIf

;If guy is Sub Zero or Sonya and enemy is on top
If curGuy(n) = 14 Or curGuy (n) = 12 Then
If (zx(nn) => zx(n)-70 And zx(nn) =< zx(n)+ 70) And (zy(nn) < zy(n) - (70+yRange(n)) And zy(nn) > zy(n) - (80+yRange(n))) Then 
	If (zongnd(n)=1 And onEdge2=0 And curArea > 0) Or (zongnd(n)=0 And dangerMove5(n)=0 And curArea > 0) Then

		If zNoAirSpecial(n)=0 Then
			counterkey(n)=1:Goto aidone
		Else
			If curGuy(n) = 14 And zongnd(nn)=0 Then upkey(n)=1:shotKey(n)=1
		EndIf
	EndIf	
EndIf
EndIf

If attack=1 Then
	a=Nextblow(n)
	If a=1 Then shotKey(n)=1 :Goto aiDone
	If a=4 Then shotKey(n)=1:downKey(n)=1 :Goto aiDone
	If a=7 And zongnd(n)=1 Then
		If zNoAirSpecial(n)=0 Then
			specialKey(n)=1 :Goto aiDone
		Else
			shotKey(n)=1 :Goto AiDone
		EndIf
	EndIf
	If a=10 Then shotKey(n)=1:upKey(n)=1 :Goto aiDone
	If a=14 Then superKey(n)=1:Goto aidone
	
	If a=9 And onEdge=0 And curArea > 0 Then
		If zNoAirSpecial(n)=0 Then
			specialKey(n)=1:downkey(n)=1  :Goto aiDone
		Else
			shotKey(n)=1:Goto aiDone
		EndIf
	Else
		If dangerMove9(n)=0 Then specialKey(n)=1:downkey(n)=1 Else shotkey(n)=1
	EndIf
		
EndIf

.aidone
If zDontJump(n)=1 Then jumpKey(n)=0:jumpKeyDown(n)=0
.aiDone2
If zon(aitarget(n))=0 Then aiTarget(n)=0 : aigettarget(n)

End Function

;-------------Define Team -----------------------------
Function defineStuff()

maxobjAmount=2			;default = 2
objFrequency=20			;default = 20 ,  valid# 0 - 1000
For n=1 To objTypeN
	objTypeOut(n)=0  ;default=0
Next
;objTypeOut(1)=1  ;yellow shell
;objTypeOut(2)=1  ;med kit
;objTypeOut(3)=1  ;green shell
;objTypeOut(4)=1  ;explosive barrel
;objTypeOut(5)=1  ;helper
;objTypeOut(6)=1  ;club
;objTypeOut(7)=1  ;gun
objTypeOut(8)=1  ;acid spit
objTypeOut(9)=1  ;little batman bomb
;objTypeOut(10)=1  ; bazooka
;objTypeOut(11)=1  ; Ray gun
objTypeOut(12)=1  ; Ray gun
objTypeOut(13)=1  ; hammer
objTypeOut(14)=1  ; dragon fire ball
objTypeOut(15)=1  ; cannon ball
objTypeOut(16)=1  ; bat
objTypeOut(17)=1  ; big rock
objTypeOut(18)=1  ; little rock
objTypeOut(19)=1  ; little lava rock

ifiniteLives=0

Select gameMode
Case 1	;DeathMatch
flagamount=0

Case 2	;CTF
ifiniteLives=1
flagAmount=2
xFlagStart(1)=xBase(1):yflagStart(1)=yBase(1)
xFlagStart(2)=xBase(2):yflagStart(2)=yBase(2)
xx=0
	For n=1 To zzamount
		If zteam(n)=1 Then zxStart(n)=xbase(1)+xx:zyStart(n)=yBase(1)
		If zteam(n)=2 Then zxStart(n)=xbase(2)+xx:zyStart(n)=yBase(2)
		xx=xx+5
	Next

Case 3	;Keep the flag
ifiniteLives=1
flagAmount=1
xbase(1)=320:ybase(1)=50

Case 4	;Hit the target
ifiniteLives=1
flagAmount=0
tarN=boxAmount+1:boxAmount=boxAmount+1
n=tarN
boxPointsAmount(n)=4:boxWidth(n)=20:boxHeight(n)=20:boxType(n)=3
boxCurpoint(n)=2:boxXSpeed(n)=2:boxYSpeed(n)=2
boxDamage(n)=0:boxHitMode(n)=2:boxHitSpeed(n)=4:boxHitYSpeed(n)=3:boxHitTime(n)=20
xBoxPoint(n,1)=50:yBoxPoint(n,1)=50
xBoxPoint(n,2)=600:yBoxPoint(n,2)=348
xBoxPoint(n,3)=600:yBoxPoint(n,3)=50
xBoxPoint(n,4)=50:yBoxPoint(n,4)=348
xbox(n)=xboxPoint(n,1):ybox(n)=yboxPoint(n,1)
targetBox(n)=1
drawBox(n)=1

objFrequency=50 	
maxobjAmount=4
For i=1 To objTypeN
	objTypeOut(i)=1	;take this itens out of this game mode!
Next
objTypeOut(3)=0	;<- only item available on this mode

End Select


End Function
;----------------------Render boxes -------------------------
Function renderBoxes(n)

Select boxType(n)
Case 1	;Buzz saw
	boxAniTime(n)=4
	boxAni(n)=boxAni(n)+1
	If boxAni(n) > boxAniTime(n) Then boxAni(n)=1
	a=2:b=4
	If boxAni(n) => 1 And boxAni(n) =< a Then boxPic(n)=boxImage(1,1)
	If boxAni(n) > a And boxAni(n) =< b Then boxPic(n)=boxImage(1,2)
	DrawImage boxPic(n),xbox(n)-xscr,ybox(n)-yscr;+boxheight(n)

Case 2	;Lava chunk
	boxAniTime(n)=4
	boxAni(n)=boxAni(n)+1
	If boxAni(n) > boxAniTime(n) Then boxAni(n)=1
	a=2:b=4
	If boxAni(n) => 1 And boxAni(n) =< a Then boxPic(n)=boxImage(2,1)
	If boxAni(n) > a And boxAni(n) =< b Then boxPic(n)=boxImage(2,2)
	DrawImage boxPic(n),xbox(n)-xscr,ybox(n)-yscr;-boxheight(n)

Case 3	;blue/red target
	boxPic(n)=boxImage(3,1)
	DrawImage boxPic(n),xbox(n)-xscr,ybox(n)-yscr;-boxheight(n)

Case 4	;vent
	boxPic(n)=boxImage(4,1)
	DrawImage boxPic(n),xbox(n)-xscr,ybox(n)-yscr

Case 5	;laser box
	boxPic(n)=boxImage(5,1)
	DrawImage boxPic(n),xbox(n)-xscr,ybox(n)-yscr

Case 6	;flower
	boxAniTime(n)=30
	boxAni(n)=boxAni(n)+1
	If boxAni(n) > boxAniTime(n) Then boxAni(n)=1
	a=15:b=30
	If boxAni(n) => 1 And boxAni(n) =< a Then boxPic(n)=boxImage(6,1)
	If boxAni(n) > a And boxAni(n) =< b Then boxPic(n)=boxImage(6,2)
	DrawImage boxPic(n),xbox(n)-xscr,ybox(n)-yscr;-boxheight(n)

Default
	Color 255,0,0
	Rect xbox(n)-xscr,ybox(n)-yscr,boxWidth(n),boxHeight(n),1 

End Select


End Function

;----------- A.I. chooses a target ----------------------------------------
Function aiGetTarget(n)

aiTarget(n)=0
For nn= 1 To zzamount
If n <> nn Then
	If zteam(n) <> zteam(nn) And zon(nn) = 1 And zHelper(nn)=0 Then
	  If vsMode=0 Then	;on aventure mode, will only get target If within screen
	    If zx(nn) > xScr And zx(nn) < xScr+640 And zy(nn) > yScr And zy(nn) < yScr+480 Then
		  aiTarget(n)=nn:Exit
		Else
		  nada=nada123
	    EndIf
	  Else	;vs mode, will get target no matter where target is
        aiTarget(n)=nn:Exit
      EndIf
	EndIf
EndIf
Next

End Function
;----------- A.I. chooses a nearby target ----------------------------------------
Function aiGetNearByTarget(n)

For nn= 1 To zzamount
 If n <> nn Then
	If zteam(n) <> zteam(nn) And zon(nn) = 1 And zHelper(nn)=0 Then
	
	    If zx(nn) > xScr And zx(nn) < xScr+640 And zy(nn) > zy(n)-60 And zy(nn) < zy(n)+30 Then
		  aiTarget(n)=nn:Exit
	    EndIf
	
	EndIf
 EndIf
Next

End Function
;--------CHECK PIXEL COLLISION DISTANCE-----------------------------------------------------------
Function checkDist(n,x,y,dir)

xDist(n)=600
Select dir
Case 2
For q=1 To 600 Step 5
	If ImageRectCollide(map,0,0,0,x+q,y,1,1) Then
		xDist(n)=q
		Goto distChecked
	EndIf
	For nn=1 To platAmount
		If x+q > xplat(nn) And x+q < xplat(nn)+platWidth(nn) And platHeight(nn)>1 Then
			If y > yplat(nn) And y < yplat(nn)+platHeight(nn) Then
				xDist(n)=q
				Goto distChecked	
			EndIf
		EndIf
	Next
Next

Case 4
For q=1 To 600 Step 5
	If ImageRectCollide(map,0,0,0,x-q,y,1,1) Then
		xDist(n)=q
		Goto distChecked
	EndIf
	For nn=1 To platAmount
		If x-q > xplat(nn) And x-q < xplat(nn)+platWidth(nn) And platHeight(nn)>1 Then
			If y > yplat(nn) And y < yplat(nn)+platHeight(nn) Then
				xDist(n)=q
				Goto distChecked	
			EndIf
		EndIf
	Next
Next

End Select
.distChecked

End Function
;------------ check If element is inside visible area--------------------------------------------------
Function inSight(x,y)

If x > xScr And x < xScr+640 And y > yScr And y < yScr+490 Then
	Return True
Else
	Return False
EndIf

End Function

;--------CHECK IF PLAYER IS INSIDE WALL AND FIX-----------------------------------------------------------
Function checkZvsWall(n,t)

For q=1 To zheight(n) Step 10
	If ImageRectCollide(map,0,0,0,zx(n)+zside(n),zy(n)-q,1,1) Or ImageRectCollide(map,0,0,0,zx(n)-zside(n),zy(n)-q,1,1)Then
		Select t	
			Case 0:zx(n)=zoldx(n): zy(n)=zoldy(n)	
			Case 1:zx(n)=zoldx(n)
			Case 2:zy(n)=zoldy(n)
		End Select
		Return True
	EndIf
Next

For nn=1 To platAmount
	If platHeight(nn)>1 And zx(n) => xplat(nn)-zside(n) And zx(n) <= xplat(nn)+platWidth(nn)+zside(n) Then
		If zy(n) > yplat(nn)+5 And (zy(n)-zheight(n)) < yplat(nn)+platHeight(nn) Then
			Select t
				Case 0:zx(n)=zoldx(n): zy(n)=zoldy(n)
				Case 1:zx(n)=zoldx(n)
				Case 2:zy(n)=zoldy(n)
			End Select
		Return True
		EndIf
	EndIf
Next

End Function

;--------CHECK PLAYER'S X,Y VS WALL-----------------------------------------------------------
Function checkzPosVsWall(n,x,y)

For q=1 To zheight(n) Step 15
	If ImageRectCollide(map,0,0,0,x+zside(n),y-q,1,1) Or ImageRectCollide(map,0,0,0,x-zside(n),y-q,1,1)Then

		Return True
	EndIf
Next

For nn=1 To platAmount
	If platHeight(nn)>1 And x => xplat(nn)-zside(n) And x <= xplat(nn)+platWidth(nn)+zside(n) Then
		If y > yplat(nn)+3 And (y-zheight(n)) < yplat(nn)+platHeight(nn) Then

			Return True
		EndIf
	EndIf
Next

End Function


;------------Flags----------------------------------------------
Function flags()

If flagAmount=2 Then ;If red flag captured!
	If xflag(1) > xBase(2) And xflag(1) < xBase(2)+20 Then
		If yflag(1) > yBase(2) - 50 And yflag(1) < yBase(2) Then
			xFlag(1)=xFlagStart(1):yFlag(1)=YFlagStart(1)
			flagCarried(1)=0:TeamScore(2)=TeamScore(2)+1
			If gameSound Then PlaySound ctfSnd
		EndIf
	EndIf

	If xflag(2) > xBase(1) And xflag(2) < xBase(1)+20 Then ;If green flag captured!
		If yflag(2) > yBase(1) - 50 And yflag(2) < yBase(1) Then
			xFlag(2)=xFlagStart(2):yFlag(2)=yFlagStart(2)
			flagCarried(2)=0:TeamScore(1)=TeamScore(1)+1
			If gameSound Then PlaySound ctfSnd
		EndIf
	EndIf
EndIf

If flagAmount=2 Then
For n= 1 To zzamount	; If player near flag than pick it up! (CTF mode)
  If zon(n) Then 
	For nn= 1 To flagAmount	;player pick up the flag
		If flagCarried(nn)=0 Then	
			If xFlag(nn) > zx(n)-zside(n) And xFlag(nn) < zx(n)+zside(n) Then
				If yFlag(nn)-10 > zy(n)-zheight(n) And yFlag(nn)-10 < zy(n) And zHit(n)=0 Then
					If zTeam(n) <> nn Then
						flagOwner(nn)=n
						flagCarried(nn)=1
					Else
						xFlag(nn)=xFlagStart(nn):yflag(nn)=yFlagStart(nn):flagCarried(nn)=0
					EndIf
				EndIf
			EndIf
		EndIf
	Next
  EndIf
Next

Else	;If not CTF Then it`s KTF

	For n= 1 To zzamount	; If player near flag than pick it up! (keep the flag mode)
	  If zon(n) Then
		For nn= 1 To flagAmount
			If flagCarried(nn)=0 Then	
				If xFlag(nn) > zx(n)-zside(n) And xFlag(nn) < zx(n)+zside(n) Then
					If yFlag(nn)-10 > zy(n)-zheight(n) And yFlag(nn)-10 < zy(n) Then
						If zhit(n)=0 Then flagOwner(nn)=n:flagCarried(nn)=1
					EndIf
				EndIf
			EndIf
		Next
	  EndIf
	Next

EndIf

For n=1 To flagAmount
	If zhit(flagOwner(n))=1 Then flagCarried(n)=0
	If flagCarried(n)=1 Then
		;If player got flag Then hold on to it
		If winner=0 And flagamount=1 Then zflagTime(flagOwner(n))=zflagTime(flagOwner(n))+.1
		xflag(n)=zx(flagOwner(n))-5:yFlag(n)=zy(flagOwner(n))-zheight(flagOwner(n))+15
		For i=1 To zzamount
			If zTeam(flagOwner(n))=zTeam(i) And flagOwner(n) <> i Then
				zflagTime(i)=zflagTime(i)+.1
			EndIf
		Next
	Else
		If Not ImageRectCollide(map,0,0,0,xflag(n),yflag(n)+1,1,1) Then
			yFlag(n)=yflag(n)+1
		EndIf
		If yflag(n) > dscrlimit Then
			If flagAmount=1 Then
				xFlag(n)=Rand(50,590):yflag(n)=Rand(0,100):flagCarried(n)=0
			Else
				xFlag(n)=xFlagStart(n):yflag(n)=yFlagStart(n):flagCarried(n)=0
			EndIf
			makeChunk(n,xFlag(n),yFlag(n),0,1)
		EndIf
	EndIf
	
Next

End Function

;-------------------------- MoveX -------------------------------------------------------------
Function moveX(n,dir,speed#)

;zx(n)=zx(n)+speed
Select dir
Case 2:zx(n)=zx(n)+speed#
Case 4:zx(n)=zx(n)-speed#
End Select

End Function

;---------------- Free VRAM --------------------
Function freeMap()

;If music <> 0 Then FreeSound music:music=0
If music2 <> 0 Then FreeSound music2:music2=0

If map <> 0 Then FreeImage map:map=0

For b=0 To 20
	For i=0 To 500
		If tileBmp(b,i) <> 0 Then FreeImage tileBmp(b,i):tileBmp(b,i)=0
		;If tilePic(b,i) <> 0 Then FreeImage tilePic(b,i):tilePic(b,i)=0
	Next
Next

For n=0 To characterAmount
	guyLoaded(n)=0
	For n1=0 To 50
		For n2=0 To 50
			If zpic(n,n1,n2) <> 0 Then FreeImage zpic(n,n1,n2):zpic(n,n1,n2)=0
			If zpic_(n,n1,n2) <> 0 Then FreeImage zpic_(n,n1,n2):zpic_(n,n1,n2)=0
		Next
	Next
Next

For n=30 To 52	;add character
	guyLoaded(n)=0
	For n1=0 To 50
		For n2=0 To 50
			If zpic(n,n1,n2) <> 0 Then FreeImage zpic(n,n1,n2):zpic(n,n1,n2)=0
			If zpic_(n,n1,n2) <> 0 Then FreeImage zpic_(n,n1,n2):zpic_(n,n1,n2)=0
		Next
	Next
Next

End Function
;----------------- MoveX2 ----------------------
Function moveX2(n,dir,speed)

If zBlowStill(n)=0 Then
	Select dir
	Case 2:zx(n)=zx(n)+speed
	Case 4:zx(n)=zx(n)-speed
	End Select
EndIf	

End Function
;------------ move y-----------------
Function moveY(n,speed)

If zBlowStill(n)=0 Then
	zy(n)=zy(n)+speed
EndIf
End Function

;------------ freeze victim -----------------
Function freezeVictim(n)
zNoJump(n)=1
zgravity(n)=0
zFrozen(n)=1
End Function

;------------ unfreeze unit -----------------
Function unFreeze(n)
zgravity(n)=3
zFrozen(n)=0
canGetTime(n)=0
If gameSound Then PlaySound subZeroFreeze3Snd
End Function

;------------ handle sub zero projectile attacks --------------
Function handleSubZeroProjectiles(targetPlayer, projectile, projectileXPos, projectileYPos)
	Local heightLoop, xAxisShotPos, yAxisShotPos, xAxisFreezeGroundPos, yAxisFreezeGroundPos, freezeGroundShotWidth, freezeGroundShotHeight

	objShotWidth=1
	objShotHeight=1
	xAxisShotPos=xshot(projectile)
	yAxisShotPos=yshot(projectile)
	If shotHitMode(shotOwner(projectile))=4 Then 
		xAxisShotPos=xshot(projectile)+20
		yAxisShotPos=yshot(projectile)+30
		xAxisFreezeGroundPos=xshot(projectile)
		yAxisFreezeGroundPos=yshot(projectile)+20
		freezeGroundShotWidth=64
		freezeGroundShotHeight=1
		If zface(shotOwner(projectile)) = 4 Then 
			xAxisFreezeGroundPos=xshot(projectile)+25
			freezeGroundShotWidth=65
		EndIf
	EndIf
		
	If shotHeight(projectile) < 0 Then 
		heightLoop = shotHeight(projectile) * -1
	Else 
		heightLoop = shotHeight(projectile)
	End If
	For qh=0 To heightLoop Step 6
	For q=0 To shotsizeL(projectile) Step 1
		For targetPlayer = 1 To zzamount
			oldshield = zShield(targetPlayer)
			If shotSuper(projectile)=1 Then zShield(targetPlayer)=0 
			If zShield(targetPlayer)=0 And zon(targetPlayer)=1 And (teamAttack=1 Or zteam(shotOwner(projectile) <> zteam(targetPlayer))) Then
				If Not targetPlayer=shotOwner(projectile) Then
					If Not (zShotByN(targetPlayer) = projectile And zShotHitSeq(targetPlayer, projectile) < shotImmuneTime(projectile)) Then 
					If teamAttack=0 And zteam(shotOwner(projectile)) = zteam(targetPlayer) Then Return
					If ImageRectCollide(zCurPic(targetPlayer),zx(targetPlayer)-(ImageWidth(zCurPic(targetPlayer))/2)+30,zy(targetPlayer)-ImageHeight(zCurPic(targetPlayer))+1,0,xAxisShotPos,yAxisShotPos,objShotWidth+50,objShotHeight) Then
						If Not shotDrill(projectile) Then shot(projectile)=0
							zShotByN(targetPlayer)=projectile : zShotHitSeq(targetPlayer,projectile)=0
							makechunk(shotDir(projectile),zx(targetPlayer),yShot(projectile),2,shotChunkType(projectile))
						If zblock(targetPlayer)=1 And shotHitMode(shotOwner(projectile)) <> 4 Then
							zBlocked(targetPlayer)=1:zBlockSeq(targetPlayer)=0
							zBlockTime(targetPlayer)=shotImpact(projectile)*2:zBlockDir(targetPlayer)=2
							zBLockLife(targetPlayer)=zBlockLife(targetPlayer)-shotDamage(projectile)
							If shotDir(projectile)=4 Then 
								zface(targetPlayer)=2
							Else
								zface(targetPlayer)=4
							EndIf
							zblowDir(targetPlayer)=zface(targetPlayer)
							If gameSound Then PlaySound blockedsnd
							If zBlockLife(targetPlayer) < 1 Then
								zBlock(targetPlayer)=0:zBlocked(targetPlayer)=0
								If gameSound Then PlaySound brokensnd
							EndIf
						EndIf
						If shotHitMode(shotOwner(projectile))=4 Then 
							zBlock(targetPlayer)=0:zBlocked(targetPlayer)=0
						EndIf
						If Not zBlock(targetPlayer) And shotSuper(projectile)=0 Then
							zlife(targetPlayer)=zlife(targetPlayer)-shotdamage(projectile)
							zDamage#(targetPlayer)=zDamage#(targetPlayer)+shotDamage(projectile)
							
							zjump(targetPlayer)=0:zBouncedgnd(targetPlayer)=0:zhit(targetPlayer)=1
							calcShot(targetPlayer, projectile)
							zBlow(targetPlayer)=0:zBlowStill(targetPlayer)=0:zHitSeq(targetPlayer)=0
				
							If shotHitTrail(projectile) > 0 Then zTrail(targetPlayer)=1:zTrailSeq(targetPlayer)=0:zTrailType(targetPlayer)=shotHitTrail(projectile)
							If gameSound = 1 Then PlaySound shotsound(projectile)	
							EndIf
						If zLife(targetPlayer) < 1 Then zScore(shotOwner(projectile))=zScore(shotOwner(projectile))+1
						Return
					EndIf 
				EndIf
				EndIf
			EndIf
			zShield(targetPlayer)=oldShield
		Next	
	Next
	Next
	
	If xshot(projectile) > rscrlimit Then shot(projectile)=0

End Function

;----------------- Draw Frozen State ------------------------------
Function drawFrozenState(unit)
	If zFrozen(unit)=1 Then
		Local freezeDuration = 2700: freezeDurationTillShake = 2000 ; in milliseconds
		currentFreezeTime(unit) = MilliSecs()
		If canGetTime(unit) = 0 Then
			canGetTime(unit) = 1
			startFreezeTime(unit) = MilliSecs()
		EndIf
		endFreezeTime(unit) = startFreezeTime(unit) + freezeDuration
		Local shakeXAxis=2
		freezeSeq(unit) = freezeSeq(unit) + 1
		zPrevAni(unit) = zani(unit):zPrevF(unit)=zf(unit)
		If currentFreezeTime(unit) => startFreezeTime(unit) + freezeDurationTillShake Then
			If freezeSeq(unit) Mod 4 = 0 Then zx(unit)=zx(unit)-shakeXAxis
			If freezeSeq(unit) Mod 4 = 1  Then zx(unit)=zx(unit)+shakeXAxis
		EndIf
		If currentFreezeTime(unit) =< endFreezeTime(unit) Then
			If curGuy(unit) = 40 Then 
				zani(unit)=0:zf(unit)=1
			Else
				If zpic(curguy(unit),0,2) <> 0 And zpic_(curguy(unit),0,2) <> 0 Then
					zani(unit)=0:zf(unit)=2
				Else
					zani(unit)=0:zf(unit)=0
				EndIf
			EndIf
		EndIf
		If currentFreezeTime(unit) => endFreezeTime(unit) Then canGetTime(unit)=0:unFreeze(unit):zani(unit)=zPrevAni(unit):zf(unit)=zPrevF(unit)
	Else
		canGetTime(unit)=0
	EndIf
End Function

;------------ Draw Rage Effect (Wolverine) ------------------------
Function drawRageEffect(player)	
	If wolverineRage(player) = 1 Then
		Local rageDuration = 23000 ; in milliseconds
		wolvSpdFctr(player) = 2
		ztopSpeed(player) = ztopSpeed(player) * wolvSpdFctr(player)
		currentRageTime(player) = MilliSecs()
		
		If canGetRageTime(player) = 0 Then
			canGetRageTime(player) = 1
			startRageTime(player) = MilliSecs()
		EndIf
		endRageTime(player) = startRageTime(player) + rageDuration
		
		Local shakeXAxis=4
		rageSeq(player) = rageSeq(player) + 1
		If rageSeq(player) Mod 15 = 0 Then extraObj(n,zx(player),0,zy(player),0,zblowdir(player),93)

		If rageSeq(player) Mod 4 = 0 Then zx(player)=zx(player)-shakeXAxis
		If rageSeq(player) Mod 4 = 1 Then zx(player)=zx(player)+shakeXAxis

		If currentRageTime(player) => endRageTime(player) Then canGetRageTime(player)=0:wolverineRage(player)=0:wolvSpdFctr(player)=1
	Else
		ztopSpeed(player) = ztopSpeed(player) / wolvSpdFctr(player)
		canGetRageTime(unit)=0
	EndIf
End Function

;------------ Draw Walk Sequence ----------------
Function drawWalkSequence(n)
	If zwalkseq(n) = 0 Then 
		If zStanceFrames(n) <> 0 Then 
			drawStanceSequence(n):Return
		Else
			zani(n)=1:zf(n)=0
		EndIf
		Return
	EndIf
	If zWalkFrames(n) <> 0 Then
		For frame=zWalkFrames(n) To 1 Step -1
			If (zwalkseq(n) / zWalkFrameSpeed#(n)) Mod frame = 0 Then 
				If zwalkseq(n) > (frame * 10) + 10 Then zwalkseq(n) = 1:Return
				zani(n)=1:zf(n)=frame
				Return
			EndIf
		Next
	Else
		If zwalkseq(n) > 40 Then zwalkseq(n)=1:Return
		If zwalkseq(n) => 1 And zwalkseq(n) =< 10 Then zani(n)=1:zf(n)=2:Return
		If zwalkseq(n) => 11 And zwalkseq(n) =< 20 Then zani(n)=1:zf(n)=3:Return
		If zwalkseq(n) => 20 And zwalkseq(n) =< 30 Then zani(n)=1:zf(n)=1:Return
		If zwalkseq(n) => 30 And zwalkseq(n) =< 40 Then zani(n)=1:zf(n)=3:Return
	EndIf
End Function

;------------ Draw Run Sequence ----------------
Function drawRunSequence(n)
	drawTrailingEffects(n, zRunSeq(n))
	If zStaminaBar(n) = 95 And zRunSeq(n)=5 And gameSound Then PlaySound zRunGruntSound(curGuy(n))
	If zRunSeq(n) Mod 12 = 0 And gameSound Then PlaySound zRunFootSound(curGuy(n))
	If zRunFrames(n) <> 0 Then
		For frame=zRunFrames(n) To 1 Step -1
			If (zRunSeq(n) / zRunFrameSpeed#(n)) Mod frame = 0 Then 
				If zRunSeq(n) > (frame * 10) + 10 Then zRunSeq(n) = zRunFrameSpeed#(n)-1:Return
				zani(n)=21:zf(n)=frame
				depleteStaminaBar(n, 5)
				Return
			EndIf
		Next
	End If
End Function

;----------- Draw Stance Sequence --------------
Function drawStanceSequence(n)
	If zStanceSeq(n) < zStanceSpeed(n) Then 
		zStanceSeq(n) = zStanceSpeed(n)
	Else
		zStanceSeq(n) = zStanceSeq(n) + 1
	End If
	For frame=zStanceFrames(n) To 1 Step -1
		If (zStanceSeq(n) / zStanceSpeed(n)) Mod frame = 0 Then
			zani(n)=19:zf(n)=frame
			If zStanceSeq(n)-1 > zStanceFrames(n)*zStanceSpeed(n) Then zStanceSeq(n) = zStanceSpeed(n)-1
			Return
		EndIf			
	Next
End Function

;----------- Play Death Sound -----------------
Function playDeathSnd(n)
	If deathSnd(curguy(n)) Then
		If gamesound Then PlaySound deathSnd(curguy(n))
	Else
		If gamesound Then PlaySound mikeKickSnd
	EndIf
End Function

;----------- Check controller Inputs -------------
Function checkInputs(n)
	If rightKeyHit(n) Then checkRightKeyHit(n)
	If leftKeyHit(n) Then checkLeftKeyHit(n)
End Function

;----------- Check right key hit ---------------
Function checkRightKeyHit(n)
	Local quarterSec=250, curTime=MilliSecs()
	If (curTime - rightKeyHitTimer(n)) < quarterSec Then
		If zOnGnd(n) And zStaminaBar(n) >= 70 And zRunFrames(n)>0 Then isRunning(n)=1
	End If
	rightKeyHitTimer(n) = curTime
End Function

;----------- Check right key hit ---------------
Function checkLeftKeyHit(n)
	Local quarterSec=250, curTime=MilliSecs()
	If (curTime - leftKeyHitTimer(n)) < quarterSec Then
		If zOnGnd(n) And zStaminaBar(n) >= 70 And zRunFrames(n)>0 Then isRunning(n)=1
	End If
	leftKeyHitTimer(n) = curTime
End Function

;------------ Deplete Stamina Bar --------------
Function depleteStaminaBar(n, amt)
	If zStaminaBar#(n) > 0 Then zStaminaBar#(n)=zStaminaBar#(n)-amt
	If zStaminaBar#(n) <= 0 Then isRunning(n)=0
End Function

;------------ Clear gameplay sub states ---------------
Function clearSubStates()
	If n > 0 And n < 30 Then ;unfreeze players in case they are frozen by sub zero previously and deactivate wolverine's rage
		For n=1 To zzamount
			If zFrozen(n)=1 Then unFreeze(n)
			If wolverineRage(n)=1 Then wolverineRage(n)=0
		Next
	EndIf
End Function

;-------------- Draw trailing effects ----------------
Function drawTrailingEffects(n, runSeq)
	If curGuy(n)=11 Then
		If runSeq Mod 5 = 0 And (zSpeed#(n) >= 3 Or zSpeed#(n) <= -3) Then extraObj(n,zx(n),-40,zy(n),-10,zFace(n),90)
	End If
End Function
