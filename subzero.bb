Function applyComboHitBox(n, hitMode, damage)
	enemyControlInit(n,zx(n),zy(n)-30,45,36)
	zblowPamount(n)=1:nn=1
	xblow(n,nn)=15: yblow(n,nn)=35:wblow(n,nn)=15:hblow(n,nn)=16:nn=nn+1
	zHitMode(n)=hitMode:zBlowHold(n)=0
	zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitTime(n)=0
	zBlowDamage(n)=damage:zBLowEffect(n)=1:zBlowImpact(n)=10:zBlowStillTime(n)=0:zBlowBlockTime(n)=25
	zBlowSound(n)=subZeroStrongHitSnd
	If (zBlowSeq(n)=56 Or zBlowSeq(n)=73 Or zBlowSeq(n)=96 Or zBlowSeq(n)=119) And zControls(n)=1 And zBlock(zControlsThis(n))=0 Then extraObj(n,zx(n),25,zy(n),-24,zblowdir(n),95)
End Function

Function performCombo(n)
	a=53:b=a+3:c=b+11:d=c+3:e=d+3:f=e+11:g=f+3:h=g+3:i=h+3
	j=i+3:k=j+11:l=k+3:m=l+3:n1=m+3:o=n1+3:p=o+18
	endSeq=45
	If zBlowSeq(n)>=50 And zBlowSeq(n) < c Then movex2(n,zface(n),1+(Abs(zSpeed#(n))/1.5))
	
;----- animations -----
	If zBlowSeq(n)>=50 And zBlowSeq(n) < a Then zani(n)=22:zf(n)=1
	If zBlowSeq(n)>=a And zBlowSeq(n) < b Then zani(n)=22:zf(n)=2
	If zBlowSeq(n)>=b And zBlowSeq(n) < c Then 
		zani(n)=22:zf(n)=3
		If zBlowSeq(n) > b+3 And KeyDown(specialK(n))=1 Then zBlowSeq(n)=c
		If zBlowSeq(n) = c-1 And KeyDown(specialK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=c And zBlowSeq(n) < d Then zani(n)=22:zf(n)=4
	If zBlowSeq(n)>=d And zBlowSeq(n) < e Then zani(n)=22:zf(n)=5
	If zBlowSeq(n)>=e And zBlowSeq(n) < f Then 
		zani(n)=22:zf(n)=6
		If zBlowSeq(n) > e+3 And KeyDown(blockK(n))=1 Then zBlowSeq(n)=f
		If zBlowSeq(n) = f-1 And KeyDown(blockK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=f And zBlowSeq(n) < g Then zani(n)=22:zf(n)=7
	If zBlowSeq(n)>=g And zBlowSeq(n) < h Then zani(n)=22:zf(n)=8
	If zBlowSeq(n)>=h And zBlowSeq(n) < i Then zani(n)=22:zf(n)=9
	If zBlowSeq(n)>=i And zBlowSeq(n) < j Then zani(n)=22:zf(n)=10
	If zBlowSeq(n)>=j And zBlowSeq(n) < k Then 
		zani(n)=22:zf(n)=11
		If zBlowSeq(n) > j+3 And KeyDown(grabK(n))=1 Then zBlowSeq(n)=k
		If zBlowSeq(n) = k-1 And KeyDown(grabK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=k And zBlowSeq(n) < l Then zani(n)=22:zf(n)=7
	If zBlowSeq(n)>=l And zBlowSeq(n) < m Then zani(n)=22:zf(n)=8
	If zBlowSeq(n)>=m And zBlowSeq(n) < n1 Then zani(n)=22:zf(n)=9
	If zBlowSeq(n)>=n1 And zBlowSeq(n) < o Then zani(n)=22:zf(n)=10
	If zBlowSeq(n)>=o And zBlowSeq(n) < p Then zani(n)=22:zf(n)=11
;----------------------
	isHitting=0
	zControls(n)=0
;----- hit boxes ------
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then
		applyComboHitBox(n, 2, 5)
		If zOnGnd(zControlsThis(n))=0 Then zy(zControlsThis(n))=zy(n)
		movex2(zControlsThis(n),zface(zControlsThis(n)),-1*(1+(Abs(zSpeed#(n))/1.5)))
		isHitting=1
	End If
	If zBlowSeq(n)=b And zControls(n)=0 Then zBlowSeq(n)=endSeq
	
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then
		applyComboHitBox(n, 2, 5)
		isHitting=1
	End If
	If zBlowSeq(n)=e And zControls(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) >= j And zBlowSeq(n) < k Then
		applyComboHitBox(n, 2, 6)
		isHitting=1
	End If
	If zBlowSeq(n)=j And zControls(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) >= o And zBlowSeq(n) < o+3 Then
		applyComboHitBox(n, 0, 6)
		isHitting=1
	End If
	zNoGrav(en)=0
	If zBlowSeq(n)=o And zControls(n)=0 Then zBlowSeq(n)=endSeq

;----------------------
	en=zControlsThis(n)
	If (zBlowSeq(n) <= o) And zBlocked(en)=0 Then zParalyzed(en)=1
	
	If isHitting=1 Then
		If zParalyzed(en)=1 Then zani(en)=2:zf(en)=3
	Else
		If zParalyzed(en)=1 Then zani(en)=2:zf(en)=1
	End If

	If zBlowSeq(n) = p Then zControls(n)=0:zBlowSeq(n)=endSeq
End Function

Function DoSubZero(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
	If zBlowStill(n)=1 Then
		zBlowStillSeq(n)=zBlowStillSeq(n)+1
		If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
		Goto noBlowSeq11
	EndIf
zBlowSeq(n)=zBlowSeq(n)+1
.noBlowSeq11
zCHunkType(n)=50

Select zCurBlow(n)
Case 0	;Blocking
	zNoMove(n)=1:zNoJump(n)=1
	zBlock(n)=1:zani(n)=13:zf(n)=1	;normal blocking
	If zblocked(n)=1 Then zani(n)=13:zf(n)=2
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 15 ;Subzero throw
	a=8: b=15: c=25: d=30: e=35: f=40: g=45: h=50: i=60
	zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=1
	If zBlowSeq(n)= a Then
		If gameSound Then PlaySound blowsnd
		grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
		If zGrabs(n)=1 Then zBlowSeq(n)=c+4
	EndIf
	If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
	
	en=zGrabsThis(n)
	If zface(n)=2 Then dir=4:dir2=2:n1=1:n2=14	Else dir=2:dir2=4:n1=-1:n2=-14
	
	If zBlowSeq(n) > c And zBlowSeq(n) < d Then 
		If shotKey(n)=1 Or grabKey(n)=1 Then
			zBlowSeq(n)=d+2
			zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
			zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
		Else
			zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+n2:zy(en)=zy(n)
			zAni(en)=2:zf(en)=1
			zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
			zAni(n)=15:zf(n)=1
			If shotKey(en)=1 Or specialKey(en)=1 Then zLetGoSeq(en)=zLetGoSeq(en)+1
			If Blockkey(n)=1 Or zLetGoSeq(en) > zLetGoAmount(en) Then 
				zhit(en)=0:zgrabbedby(en)=0:zgrabbed(en)=0
				zHitTime(en)=0:zFallTime(en)=0zHitSeq(en)=0
				zgrabsThis(n)=0
				zgrabs(n)=0
				zBlowSeq(n)=i
			EndIf
		EndIf
	EndIf
		
	If zBlowSeq(n) > d And zBlowSeq(n) < i Then zshield(n)=1
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1:zFace(en)=dir
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
	If zblowseq(n) = f Then
		If gameSound Then PlaySound subZeroThrowSnd
	EndIf
	If zBlowSeq(n) => i-3 And zBlowSeq(n) < i Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-15:zAni(en)=2:zf(en)=6:zface(en)=dir2
	If zBlowSeq(n) = i  Then
		zx(en)=zx(n)+0:zy(en)=zy(n)-13
		zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
		zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
		zDamage(en)=zDamage(en)+10
		zLife(en)=zLife(en)-10
		zFace(en)=dir : zFallDir(en)=dir
		zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
	EndIf
	If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=5
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=6
	
	If zBlowSeq(n) > c And zBlowSeq(n) < g Then zgrabbed(en)=1:checkZvsWall(en,0)
	If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0



Case 14	;Super Special (ice spikes)
	a=2:b=4:c=7:d=20:e=50
	Local numOfShots=6
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) < d Then 
		zani(n)=10:zf(n)=3
		If zBlowSeq(n)=c-1 And gameSound=1 Then PlaySound mkMaleGrunt1Snd
        If zBlowSeq(n) = d-1 And zblowseq2(n)=0 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0
	EndIf
	If zBlowSeq(n) > a And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) = d Then
		dir=zface(n):y=zy(n)-zheight(n)+21
		If zface(n)=2 Then x=zx(n)+14
		If zface(n)=4 Then x=zx(n)-14

		da = makeshot(n,41,x,y,dir)
 	EndIf
 	
	If zBlowSeq(n) > d And zBlowSeq2(n) < numOfShots Then zBlowSeq2(n)=zBlowSeq2(n)+1:zBlowSeq(n)=zBlowSeq(n)-10
	If zBlowSeq(n) = d+2 And gameSound=1 Then PlaySound subZeroWindSnd
	If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0
	

Case 11	;club
	a=12:b=22:c=29:d=50:e=55
	zNoMove(n)=1
	zNoJump(n)=1
	extraDraw(n)=1: drawObjOnZ(n)=0
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-18:yed(n)=22
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-19:yed(n)=23
	If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
		zblowPamount(n)=6
		nn=1
		xblow(n,nn)=40: yblow(n,nn)=4:wblow(n,nn)=28:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=70:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0 :zBlowHold(n)=10
		zBlowDamage(n)=25:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
		zChunkType(n)=5
		zBlowSound(n)=smashsnd
		zani(n)=10:zf(n)=1
		eAni(n)=1:ef(n)=3:xED(n)=35:yed(n)=18
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=4:xed(n)=40:yed(n)=18
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-29:yed(n)=19
	If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 12	;Shooting Position
	zNoMove(n)=1:zNoJump(n)=1
	extraDraw(n)=1:drawObjOnZ(n)=0
	a=8:b=22:c=28
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
			makechunk(n,x,zy(n)+27,2,50)
		Else
			If gameSound Then PlaySound shotwallsnd
		EndIf
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then
		If zBlowDir(n)=2 Then dir=4 Else dir=2
		zani(n)=12:zf(n)=1
		eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
		zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
	EndIf
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
	b=2:c=8
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=1
	If zBlowSeq(n)= b Then
		For nn=1 To objAmount
		If xobj(nn) => zx(n)-14 And xObj(nn) =< zx(n)+14 And objTaken(nn)=0 And objHurt(nn)=0 And obj(nn)=1 Then
			If yobj(nn) => zy(n) -10 And yobj(nn) =< zy(n) + 3 Then
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
	
Case 1	; Kick
	a= 5: b=10: c=15: d=20: e=27: f=26: g=30: h=35: i=50
	zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) = 1 And isRunning(n) Then zBlowSeq(n)=i:isRunning(n)=0
	If zBlowSeq(n) >= i Then performCombo(n)
	If zBlowSeq(n) = d And gameSound Then PlaySound subZeroKickSnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4
	If zBlowseq(n) > d And zblowseq(n) =< e Then 
		zani(n)=6:zf(n)=5
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=18: yblow(n,nn)=40:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=12: yblow(n,nn)=30:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=6: yblow(n,nn)=28:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=18:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
		zBlowSound(n)=subZeroStrongHitSnd
	EndIf
	If zBlowseq(n) > e And zblowseq(n) =< f Then zani(n)=6:zf(n)=4
	If zBlowseq(n) > f And zblowseq(n) =< g Then zani(n)=6:zf(n)=6
	If zBlowseq(n) > g And zblowseq(n) =< h Then zani(n)=6:zf(n)=7
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zBlowSeq(n)=0:zBlow(n)=0

Case 2	;Flying kick
	a=5: b=a+5: c=b+8: d=c+10
	zNoJump(n)=0:ZJUMPING(N)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=3
	If zBlowSeq(n) = b And gameSound=1 Then PlaySound subZeroPunchSnd
	If zBlowSeq(n) > b And zBlowSeq(n) < c Then
		zblowpamount(n)=4
		nn=1
		xblow(n,nn)=19: yblow(n,nn)=-7:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=12: yblow(n,nn)=1:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=16: yblow(n,nn)=14:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=3: yblow(n,nn)=22:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
		zBlowSound(n)=subZeroHitSnd
	EndIf
	If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
	If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4	;Low kick
	zNoMove(n)=1:zNoJump(n)=1
	zheight(n)=zduckheight(n)
	a=10: b=20: c=45
	If zBlowSeq(n) = a And gameSound Then PlaySound subZeroSlideKickSnd
	If zSpeed(n) <> 0 Then b=b+(Abs(zSpeed#(n))/2):c=c+(Abs(zSpeed#(n))/2)
	If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) < a And isRunning(n) Then zBlowSeq(n)=a
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then 
		zblowPamount(n)=2:nn=1
		xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		movex2(n,zface(n),5+(Abs(zSpeed#(n))/1.5))
		zBlowDamage(n)=12:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=30
		zBlowSound(n)=subZeroHitSnd
		zani(n)=9:zf(n)=2
	EndIf
	If zBlowSeq(n) >= b And zBlowSeq(n) =< c Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1:isRunning(n)=0

Case 5 ; Uppercut
   	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	a=5: b=a+5: c=b+6: d=c+150 
	
	If zBlowSeq(n) =1 Then
		If gameSound Then PlaySound subZeroAirSnd
		zBlowUpLimit(n)=zy(n)-70:zJump(n)=0
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=2:moveX(n,zBlowdir(n),1)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=1:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
		If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
		zBlowDamage(n)=6:zBLowEffect(n)=1:zBlowImpact(n)=16:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
		zBlowSound(n)=subZeroHitSnd
		zani(n)=7:zf(n)=3:zantiplat(n)=1
	EndIf
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
		zblowPamount(n)=4:nn=1
		xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=60:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=5: yblow(n,nn)=70:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=2:zHitUpSpeed#(n)=4:zHitTime(n)=45
		If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
		zBlowDamage(n)=6:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
		zBlowSound(n)=subZeroHitSnd
		zani(n)=7:zf(n)=3:zantiplat(n)=1
	EndIf
	If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=d
	If zBlowSeq(n) => d Then zani(n)=7:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	If zongnd(n)=1 And zBlowSeq(n) => d-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

	
Case 6	;throwing iten
	a=2:b=3:c=6:d=8
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=3
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=5
	If zBlowSeq(n)= b Then
		throwObj(n,zx(n),zy(n)-20,zFace(n))
		If gameSound Then PlaySound throwsnd
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=7
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=6
	If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 7	;Sub Zero Freeze Ball
	a=3:b=7:c=10:d=14:e=17:f=20:g=24:h=28:i=50
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) < i Then 
		zani(n)=10:zf(n)=3
		If zBlowSeq(n) = c And gameSound=1 Then	PlaySound subZeroFreeze1Snd
		If zBlowSeq(n) => c And zBlowSeq(n) < d Then extraObj(n,zx(n),25,zy(n),-24,zblowdir(n),63)
		If zBlowSeq(n) => d And zBlowSeq(n) < e Then extraObj(n,zx(n),30,zy(n),-18,zblowdir(n),64)
		If zBlowSeq(n) => e And zBlowSeq(n) < f Then extraObj(n,zx(n),36.5,zy(n),-15,zblowdir(n),65)
		If zBlowSeq(n) => f And zBlowSeq(n) < g Then extraObj(n,zx(n),41,zy(n),-18,zblowdir(n),66)
		If zBlowSeq(n) => g And zBlowSeq(n) < h Then extraObj(n,zx(n),45,zy(n),-24,zblowdir(n),67)
	EndIf
	
	If zBlowSeq(n)=h Then 
		dir=zface(n):y=zy(n)-zheight(n)+15
		If zface(n)=2 Then x=zx(n)+50
		If zface(n)=4 Then x=zx(n)-50
		makeshot(n,39,x,y,dir)
	EndIf
	If zBlowSeq(n) > i Then zBlowSeq(n)=0:zBlow(n)=0

Case 8	;Dodge
	zheight(n)=zduckHeight(n)
	zNoMove(n)=1
	zNoJump(n)=1
	a=7:b=15:c=20:d=25:e=30:f=37
	If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=1:zf(n)=0
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=1:zf(n)=0:moveX(n,zBlowdir(n),1)  

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	;Sub zero freeze ground
	a=3:b=6:c=10:d=13:e=d+2:f=e+2:g=f+2:h=g+2:i=h+3:j=h+12
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zFace(n)=2 Then checkYDist(n,zx(n)+40,zy(n)+6,2)
	If zFace(n)=4 Then checkYDist(n,zx(n)-40,zy(n)+6,2)
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=12:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) < j Then 
		If zBlowSeq(n) => b And zBlowSeq(n) < j Then zani(n)=12:zf(n)=3
		If zBlowSeq(n) = b And gameSound=1 Then PlaySound subZeroFreeze1Snd
		If zBlowSeq(n) => b And zBlowSeq(n) < c Then extraObj(n,zx(n),13,zy(n),-21,zblowdir(n),68)
		If zBlowSeq(n) => c And zBlowSeq(n) < d Then extraObj(n,zx(n),14,zy(n),-16,zblowdir(n),69)
		If zBlowSeq(n) => d And zBlowSeq(n) < e Then extraObj(n,zx(n),16,zy(n),-8,zblowdir(n),70)
		If zBlowSeq(n) => e And zBlowSeq(n) < f Then extraObj(n,zx(n),22,zy(n),-4,zblowdir(n),71)
		If zBlowSeq(n) => f And zBlowSeq(n) < g Then extraObj(n,zx(n),29,zy(n),-2,zblowdir(n),72)
		If zBlowSeq(n) => g And zBlowSeq(n) < h And zongnd(n)=1 Then extraObj(n,zx(n),37,zy(n),-6,zblowdir(n),73)
		If zBlowSeq(n) => h And zBlowSeq(n) < i Then
			If zongnd(n)=1 And (shot(zMyShot(n)) = 0 Or shotOwner(zMyshot(n)) <> n Or shotType(zMyShot(n)) <> 40) And yDist(n) < 2 Then
				zMyShot(n)=getShot()
				extraObj(n,zx(n),35,zy(n),-1,zblowdir(n),72)
				dir=zface(n):y=zy(n)-zheight(n)+5
				If zface(n)=2 Then x=zx(n)+30
				If zface(n)=4 Then x=zx(n)-60
				If zBlowSeq(n)=h Then 
					makeshot(n,40,x,y,dir)
					spellCooldownMaxTime(n, 1)=100
					spellCooldownSeq(n, 1)=spellCooldownMaxTime(n, 1) 
				End If
			Else If zongnd(n)=0 Or yDist(n) > 1 Then
				dir=zface(n):y=zy(n)-zheight(n)+5
				If zface(n)=2 Then x=zx(n)+24:y=zy(n)-21
				If zface(n)=4 Then x=zx(n)-24:y=zy(n)-15
				If zBlowSeq(n) = h Then makeshot(n,42,x,y,dir)	
			EndIf
		EndIf
	EndIf
			
	If zBlowSeq(n) => j Then zBlowSeq(n)=0:zBlow(n)=0

	
Case 10	; Up + Attack
	a=3: b=6: c=9: d=15: e=24: f=37: g=44
	zNoMove(n)=1
	zNoJump(n)=1
	If zBlowSeq(n) = c And gameSound Then PlaySound blow2snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=3
	If zBlowseq(n) > c And zblowseq(n) =< d Then 
		zblowPamount(n)=2:nn=1
		xblow(n,nn)=6: yblow(n,nn)=63:wblow(n,nn)=9:hblow(n,nn)=15:nn=nn+1
		xblow(n,nn)=6: yblow(n,nn)=42:wblow(n,nn)=9:hblow(n,nn)=17:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=14:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
		zBlowSound(n)=subZeroStrongHitSnd
		;If zBlowSeq(n)=d
		zani(n)=14:zf(n)=4
	EndIf
	If zBlowSeq(n) > d And zBlowSeq(n) <= e Then zani(n)=14:zf(n)=4
	If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=14:zf(n)=1
	
	If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0	
	
	
Case 16: ; ice clone
	a=3:b=6:c=9:d=11:e=13:f=16:g=19:h=22:i=25:j=28
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zHitHead(n)=1 Then zBlowSeq(n)=i:zy(n)=zy(n)+4
	If zongnd(n) = 0 And zBlowSeq(n) = 1 Then zBlowSeq(n) = j+1
	If zBlowSeq(n) = 1 Then PlaySound subZeroFreeze1Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=16:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=16:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=16:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=16:zf(n)=4
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=16:zf(n)=5
	If zBlowSeq(n) = e And (shot(zMyShot(n)) = 0 Or shotOwner(zMyshot(n)) <> n Or shotType(zMyShot(n)) <> 43) Then	
		zMyShot(n)=getShot()
		dir=zface(n)
		y=zy(n)
		x=zx(n)
		makeshot(n,43,x,y,dir)
	EndIf
	If zBlowSeq(n) => f Then
		If zface(n) = 2 Then dir = 4
		If zface(n) = 4 Then dir = 2
		If zBlowSeq(n) = f And zBlowSeq(n) <= g Then
			If gameSound Then PlaySound subZeroJumpSnd
			zani(n)=5:zf(n)=1::zantiplat(n)=1
			movex2(n,dir,16)
			movey(n,-30)
		EndIf
		If zBlowSeq(n) = g And zBlowSeq(n) <= h Then
			zani(n)=5:zf(n)=4::zantiplat(n)=1
			movex2(n,dir,15)
			movey(n,-25)
		EndIf
		If zBlowSeq(n) = h And zBlowSeq(n) <= i Then
			zani(n)=5:zf(n)=3::zantiplat(n)=1
			movex2(n,dir,14)
		EndIf
		If zBlowSeq(n) = i And zBlowSeq(n) <= j Then
			zani(n)=5:zf(n)=2::zantiplat(n)=1
			movex2(n,dir,12)
		EndIf
	EndIf
	
	If zBlowSeq(n) > j Then zBlowSeq(n)=0:zBlow(n)=0
	
Case 17: ;Ice shower
	a=3:b=a+3:c=b+3:d=c+3:e=d+3:f=e+17
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) >= 0 And zBlowSeq(n) < a Then
		If zBlowSeq(n) = 1 And gameSound Then PlaySound subZeroFreeze1Snd
		 zani(n)=18:zf(n)=1
	End If
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zani(n)=18:zf(n)=2
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zani(n)=18:zf(n)=3
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zani(n)=18:zf(n)=4
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=18:zf(n)=5
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zani(n)=18:zf(n)=6
	
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0
	
End Select

End Function