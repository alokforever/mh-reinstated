Function handleJuggernautCooldown(n, blowSeq, cooldownType)
	If zBlowSeq(n)=blowSeq And spellCooldownSeq(n, cooldownType) > 0 Then
		cdSeed=Rand(2)
		If cdSeed=1 And gameSound And zAI(n)=0 Then 
			PlaySound juggNoManaSnd
		Else If cdSeed=2 And gameSound And zAI(n)=0 Then
			PlaySound juggNoMana2Snd
		End If
		If gameSound Then PlaySound clockTickSnd
		zBlowSeq(n)=0:zBlow(n)=0
	End If
End Function

Function DoJuggernaut(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

	If zBlowStill(n)=1 Then
		zBlowStillSeq(n)=zBlowStillSeq(n)+1
		If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
		Goto noBlowSeq3
	EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq3

zchunkType(n)=10

Select zCurBlow(n)
Case 0	;Blocking
	zNoMove(n)=1:zNoJump(n)=1
	zBlock(n)=1:zani(n)=13:zf(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
		If zblocked(n)=1 Then 
		zani(n)=13:zf(n)=2
		zBlockSeqStart(n)=zBlockSeq(n)
	End If
	If zBlockSeq(n) = zBlockSeqStart(n)+4 Then zani(n)=13:zf(n)=3
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1	;Normal Punch
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 2	;Flying Kick
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 4	;Low kick
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 5	;UP + SPECIAL (Lateral press)
	zNoMove(n)=1
	zNoJump(n)=1:zJumping(n)=0:zJump(n)=0
	a=8:b=a+8:c=b+6:d=c+4:e=50000:f=e+4:g=f+4:h=g+3:i=h+3
	a2=60000:b2=a2+3:c2=b2+3:d2=c2+3:e2=d2+3:f2=e2+3:g2=f2+3:h2=g2+3:i2=h2+3

;================= Animation ==============
	If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=7:zf(n)=1
	If zBlowSeq(n) > a Then zani(n)=7:zf(n)=2
	;============= Miss ===================
	If zBlowSeq(n) >= a2 And zBlowSeq(n) < b2 Then zani(n)=7:zf(n)=7
	If zBlowSeq(n) >= b2 And zBlowSeq(n) < c2 Then zani(n)=7:zf(n)=8
	If zBlowSeq(n) >= c2 And zBlowSeq(n) < d2 Then zani(n)=7:zf(n)=9
	If zBlowSeq(n) >= d2 And zBlowSeq(n) < e2 Then zani(n)=7:zf(n)=10
	If zBlowSeq(n) >= e2 And zBlowSeq(n) < f2 Then zani(n)=7:zf(n)=11
	If zBlowSeq(n) >= f2 And zBlowSeq(n) < g2 Then zani(n)=7:zf(n)=12
	If zBlowSeq(n) >= g2 And zBlowSeq(n) < h2 Then zani(n)=7:zf(n)=13
	If zBlowSeq(n) >= h2 And zBlowSeq(n) < i2 Then zani(n)=7:zf(n)=14
	If zBlowSeq(n) = i2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	;======================================
	;============== Hit ===================
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zani(n)=7:zf(n)=3
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=7:zf(n)=4
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=7:zf(n)=5
	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then zani(n)=7:zf(n)=6
	If zBlowSeq(n) = i Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	;======================================
;==========================================

;================= Sounds =================
	If zBlowSeq(n) = 1 And gameSound Then PlaySound juggJumpSnd
	If zBlowSeq(n) = 3 And gameSound Then PlaySound juggLateralSnd
;==========================================

;================= Hit boxes ==============
	If zBlowSeq(n) >= b And zBlowSeq(n) < e Then
		zblowback(n)=1
		If zBlowStill(n)=1 Then zBlowSeq(n)=e
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=-48: yblow(n,nn)=46:wblow(n,nn)=97:hblow(n,nn)=28:nn=nn+1
		xblow(n,nn)=-63: yblow(n,nn)=22:wblow(n,nn)=75:hblow(n,nn)=24:nn=nn+1
		xblow(n,nn)=-63: yblow(n,nn)=5:wblow(n,nn)=75:hblow(n,nn)=10:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=1:zHitSpeed#(n)=6:zHitUpSpeed#(n)=3:zHitTime(n)=35:zBlowSound(n)=juggLateralHitSnd
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=90:zBlowStillTime(n)=1:zBlowBlockTime(n)=10
	End If 

;==========================================

;================= Movement ===============
	If zFace(n)=2 Then
		If leftKey(n)=1 Then moveX(n, zBlowDir(n), -1)
		If rightKey(n)=1 Then moveX(n, zBlowDir(n), 1)
	Else
		If leftKey(n)=1 Then moveX(n, zBlowDir(n), 1)
		If rightKey(n)=1 Then moveX(n, zBlowDir(n), -1)
	End If
	If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then moveX(n,zBlowdir(n),2):moveY(n, -7)
	If zBlowSeq(n) > a And zBlowSeq(n) <= b Then moveX(n,zBlowdir(n),2):moveY(n, -6)
	If zBlowSeq(n) > b And zBlowSeq(n) <= c Then moveX(n,zBlowdir(n),2):moveY(n, -5)
	If zBlowSeq(n) > c And zBlowSeq(n) <= d Then moveX(n,zBlowdir(n),2):moveY(n, -4)
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then moveX(n, zBlowDir(n), 2)
	If zBlowSeq(n) >= a2 Then moveX(n, zBlowDir(n), 1.2)
	If zBlowSeq(n) > d And zBlowSeq(n) < e And zOnGnd(n)=1 Then 
		If gameSound Then PlaySound walkQuakeSnd(curGuy(n))
		quake=1:quakeSeq=0
		zBlowSeq(n)=a2
	End If
;==========================================


Case 6	;throwing iten
	a=3:b=6:c=9
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n)= b Then
		throwObj(n,zx(n),zy(n)-20,zFace(n))
		If gameSound Then PlaySound throwsnd
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=3
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=4
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7	; Juggernaut punch (special)
	zNoMove(n)=1
	zNoJump(n)=1:zJumping(n)=0:zJump(n)=0
	a=2:b=a+3:c=b+3:d=c+2:e=d+10:f=e+5:g=f+2:h=g+2:i=h+2
	ex=1000:a2=ex+4:b2=a2+4:c2=b2+4:d2=c2+3:e2=d2+10:f2=e2+3:g2=f2+2:h2=g2+2:i2=h2+2
	a3=2000:b3=a3+12:c3=b3+8:d3=c3+4
	If zOnGnd(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) = 1 And isRunning(n) And zSuperBar(n) >= 10 Then zSuperBar(n)=zSuperBar(n)-10:zBlowSeq(n)=ex:isRunning(n)=0
	If zBlowSeq(n) = i+1 Or zBlowSeq(n) = i2+1 Then zBlowSeq(n)=a3
;================= Animation ==============
	;=========== Normal Punch =============	
	If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zani(n)=10:zf(n)=5
	If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zani(n)=10:zf(n)=7
	If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zani(n)=10:zf(n)=8
	If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zani(n)=10:zf(n)=9
	If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zani(n)=10:zf(n)=10
	;=======================================
	;============== Ex punch ===============
	If zBlowSeq(n) >= ex And zBlowSeq(n) <= a2 Then 
		zani(n)=10:zf(n)=1
		If zBlowSeq(n) = ex And gameSound Then PlaySound mvcExSnd
	End If
	If zBlowSeq(n) >= ex And zBlowSeq(n) <= a2 Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) >= a2 And zBlowSeq(n) <= b2 Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) >= b2 And zBlowSeq(n) <= c2 Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) >= c2 And zBlowSeq(n) <= d2 Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) >= d2 And zBlowSeq(n) <= e2 Then zani(n)=10:zf(n)=6
	If zBlowSeq(n) >= e2 And zBlowSeq(n) <= f2 Then zani(n)=10:zf(n)=11
	If zBlowSeq(n) >= f2 And zBlowSeq(n) <= g2 Then zani(n)=10:zf(n)=8
	If zBlowSeq(n) >= g2 And zBlowSeq(n) <= h2 Then zani(n)=10:zf(n)=9
	If zBlowSeq(n) >= h2 And zBlowSeq(n) <= i2 Then zani(n)=10:zf(n)=10
	;=======================================
	;============== Recovery ===============
	If zBlowSeq(n) >= a3 And zBlowSeq(n) <= b3 Then zani(n)=10:zf(n)=12
	If zBlowSeq(n) >= b3 And zBlowSeq(n) <= c3 Then zani(n)=10:zf(n)=13
	If zBlowSeq(n) >= c3 And zBlowSeq(n) <= d3 Then zani(n)=10:zf(n)=14
	If zBlowSeq(n) > d3 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	;=======================================
;===========================================
;================= Sounds ==================
	If zBlowSeq(n) = 1 Or zBlowSeq(n) = ex And gameSound Then PlaySound juggPunchSnd
	If zBlowSeq(n) = e Or zBlowSeq(n) = e2 And gameSound Then PlaySound mvcBlow1Snd
	If zBlowSeq(n) = i Or zBlowSeq(n) = i2 And zOnGnd(n) And gameSound Then PlaySound juggPunchGroundSnd
;===========================================
;================= Effects =================
	If zBlowSeq(n) = f Or zBlowSeq(n) = f2 And zOnGnd(n) Then 
		quake=1:quakeSeq=0
		extraObj(n,zx(n),40,zy(n),3,zblowdir(n),112)
	End If
;===========================================

;================= Movement ================
	If zBlowSeq(n) >= e And zBlowSeq(n) <= f And zOnGnd(n) Then moveX(n, zBlowDir(n), 6)
	If zBlowSeq(n) >= e2 And zBlowSeq(n) <= f2 And zOnGnd(n) Then moveX(n, zBlowDir(n), 12)
;===========================================

;================ Hit boxes ================
	If (zBlowSeq(n) >= e2 And zBlowSeq(n) < f2) Or (zBlowSeq(n) >= e And zBlowSeq(n) < f) Then
		Local hitDamage
		If zBlowSeq(n) >= e2 And zBlowSeq(n) < f2 Then 
			hitDamage=16
		Else
			hitDamage=21
		End If
		zblowPamount(n)=3
		nn=1
		xblow(n,nn)=20: yblow(n,nn)=16:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=26:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=36:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=2:zBlowHold(n)=10
		zHitSpeed#(n)=6:zHitUpSpeed#(n)=3:zHitTime(n)=40;:zBlockSpeed#(n)=30
		zBlowDamage(n)=hitDamage:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=0:zBlowBlockTime(n)=35
		zBlowSound(n)=juggLateralHitSnd
	End If
	If (zBlowSeq(n) >= f2 And zBlowSeq(n) < g2) Or (zBlowSeq(n) >= f And zBlowSeq(n) < g) Then
		If zBlowSeq(n) >= f2 And zBlowSeq(n) < g2 Then 
			hitDamage=16
		Else
			hitDamage=21
		End If
		zblowPamount(n)=2
		nn=1
		xblow(n,nn)=20: yblow(n,nn)=0:wblow(n,nn)=54:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=10:wblow(n,nn)=54:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=2:zBlowHold(n)=10
		zHitSpeed#(n)=6:zHitUpSpeed#(n)=3:zHitTime(n)=40;:zBlockSpeed#(n)=40
		zBlowDamage(n)=hitDamage:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=0:zBlowBlockTime(n)=35
		zBlowSound(n)=juggLateralHitSnd
	End If
;===========================================

Case 8	;Dodging
	zheight(n)=zduckHeight(n)
	zNoMove(n)=1
	zNoJump(n)=1
	a=7/wolvSpdFctr(n):b=15/wolvSpdFctr(n):c=20/wolvSpdFctr(n):d=25/wolvSpdFctr(n):e=30/wolvSpdFctr(n)
	:f=37/wolvSpdFctr(n)
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) = a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),1)

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	; Earthquake (down special)
	zNoMove(n)=1
	zNoJump(n)=1:zJumping(n)=0:zJump(n)=0
	a=2:b=a+4:c=b+2:d=c+2:e=d+36:f=e+5:g=f+5
	ex=1000:a2=ex+2:b2=a2+4:c2=b2+2:d2=c2+2:e2=d2+44:f2=e2+5:g2=f2+5
	If zOnGnd(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	;If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 And zSuperBar(n) >= 10 Then zSuperBar(n)=zSuperBar(n)-10:zBlowSeq(n)=ex
	If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 Then zBlowSeq(n)=ex
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n)=1 Then
		If zFace(n)=2 Then checkYDist(n,zx(n)+40,zy(n)+6,2)
		If zFace(n)=4 Then checkYDist(n,zx(n)-40,zy(n)+6,2)
	End If
;=============== Animation =================
	If (zBlowSeq(n) >= 1 And zBlowSeq(n) < a) Or (zBlowSeq(n) = ex And zBlowSeq(n) < a2) Then zani(n)=12:zf(n)=1
	If (zBlowSeq(n) >= a And zBlowSeq(n) < b) Or (zBlowSeq(n) = a2 And zBlowSeq(n) < b2) Then zani(n)=12:zf(n)=2
	If (zBlowSeq(n) >= b And zBlowSeq(n) < c) Or (zBlowSeq(n) = b2 And zBlowSeq(n) < c2) Then zani(n)=12:zf(n)=3
	If (zBlowSeq(n) >= c And zBlowSeq(n) < d) Or (zBlowSeq(n) = c2 And zBlowSeq(n) < d2) Then zani(n)=12:zf(n)=4
	If (zBlowSeq(n) >= d And zBlowSeq(n) < e) Or zBlowSeq(n) >= d2 And zBlowSeq(n) < e2 Then
		zani(n)=12
		If zf(n)=5 And (zBlowSeq(n) <> 28 Or zBlowSeq(n) <> 1028) Then 
			zf(n)=6
		Else
			zf(n)=5
		End If
	End If
	If (zBlowSeq(n) >= e And zBlowSeq(n) < f) Or (zBlowSeq(n) = e2 And zBlowSeq(n) < f2) Then zani(n)=12:zf(n)=7
	If (zBlowSeq(n) >= f And zBlowSeq(n) < g) Or (zBlowSeq(n) = f2 And zBlowSeq(n) < g2) Then zani(n)=12:zf(n)=8
;===========================================

;================= Sounds ==================
	If gameSound Then
		If zBlowSeq(n) = ex+1 Then PlaySound mvcExSnd
		If zBlowSeq(n) = a Or zBlowSeq(n) = a2 Then PlaySound juggEarthquakeSnd
		If (zBlowSeq(n) = d+2 Or zBlowSeq(n) = d2+2) And (yDist(n) <= 6 Or zOnGnd(n)=1) Then PlaySound mvcCrash2Snd
	End If
;===========================================

;================ Effects ==================
	If yDist(n) <= 6 Or zOnGnd(n)=1 Then
		If zBlowSeq(n) = d+2 Or zBlowSeq(n) = d+8 Or zBlowSeq(n) = d+16 Then quake=1:quakeSeq=0
		If zBlowSeq(n) = d2+2 Or zBlowSeq(n) = d2+8 Or zBlowSeq(n) = d2+16 Then quake=1:quakeSeq=0
		If zBlowSeq(n) = d+2 Then extraObj(n,zx(n),55,zy(n),3,zblowdir(n),113)
		If zBlowSeq(n) = d+8  Then
			dir=zface(n):y=zy(n)-(zheight(n)-44)
			If zface(n)=2 Then x=zx(n)+38
			If zface(n)=4 Then x=zx(n)-38
			makeshot(n,47,x,y,dir)
		End If
		If zBlowSeq(n) = d2+8 Or zBlowSeq(n) = d2+24 Or zBlowSeq(n) = d2+40 Then
			dir=zface(n):y=zy(n)-(zheight(n)-44)
			If zface(n)=2 Then x=zx(n)+42+((zBlowSeq(n)-1000)*3)
			If zface(n)=4 Then x=zx(n)-42-((zBlowSeq(n)-1000)*3)
			makeshot(n,48,x,y,dir)
		End If
	End If
;===========================================
	If zBlowSeq(n) = g Or zBlowSeq(n) = g2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10	;High Punch 
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 11	;club
	a=12:b=22:c=29:d=50:e=55
	zNoMove(n)=1
	zNoJump(n)=1
	extraDraw(n)=1
	drawObjOnZ(n)=0
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-45:yed(n)=30
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-47:yed(n)=25
	If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
		zblowPamount(n)=6
		nn=6
		xblow(n,nn)=40: yblow(n,nn)=4:wblow(n,nn)=42:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=98:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=98:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=95:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=85:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=77:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
		zChunkType(n)=5
		zBlowSound(n)=smashsnd
		zani(n)=12:zf(n)=3
		eAni(n)=1:ef(n)=3:xED(n)=65:yed(n)=20
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=3 :eAni(n)=1:ef(n)=4:xed(n)=65:yed(n)=20
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=22
	If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 12	;Shooting Position
	zNoMove(n)=1:zNoJump(n)=1
	extraDraw(n)=1:drawObjOnZ(n)=0
	a=8:b=22:c=28
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zblowSeq(n) =1 Then
		If shotsfired(zgotobj(n)) < objAmmo(zgotobj(n)) Then	
			shotsfired(zgotobj(n))=shotsfired(zgotobj(n))+1
			If gameSound Then PlaySound shotFireSound(n)
			dir=zface(n):y=zy(n)-27
			If zface(n)=2 Then x=zx(n)+10
			If zface(n)=4 Then x=zx(n)-10
			makeshot(n,zShootThis(n),x,y,dir)
			If zface(n)=2 Then x=zx(n)+14
			If zface(n)=4 Then x=zx(n)-14
			makechunk(n,x,zy(n)-27,2,50)
		Else
			If gameSound Then PlaySound shotwallsnd
		EndIf
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then 
		If zBlowDir(n)=2 Then dir=4 Else dir=2
		zani(n)=12:zf(n)=7
		eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
		zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
	EndIf
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=7:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=7:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
	b=2:c=8
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=3:zf(n)=1
	If zBlowSeq(n)= b Then
		For nn=1 To objAmount
		If xobj(nn) => zx(n)-14 And xObj(nn) =< zx(n)+14 And objTaken(nn)=0 And objHurt(nn)=0 And obj(nn)=1 Then
			If yobj(nn) => zy(n) -10 And yobj(nn) =< zy(n) +3 Then 
				objOwner(nn)=n
				zGotObj(n)=nn
				objData(nn,n)
				objThrow(nn)=0:objTaken(nn)=1
				If gameSound Then PlaySound pickupSnd
				makeChunk(n,zx(n),zy(n)-8,2,15)
				If objEat(nn) > 0 Then objConsume(nn,n)
				Exit
			EndIf
		EndIf
		Next	
		
	EndIf
	If zBlowSeq(n) => c Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 14	;Super Special
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 15 ;Juggernaut throw
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 16 ;Counter Key (Taunt and Power up)
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 17 ;Extra special key (Drill Claw)
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
End Select

End Function