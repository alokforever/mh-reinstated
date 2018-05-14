Function doMysticAttack(n)

End Function

Function DoPiccolo(n)

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
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1	;Normal Punch
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 2	;Flying Kick
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 4	;Low kick
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 5	;UP + SPECIAL
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

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

Case 7	; Seeking ki blast (special)
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	

Case 8	;Dodging
	a=7:b=15:c=20:d=25:e=30:f=37
	zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),1)

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	; Kaikousen (down special)
	a=3:b=6:c=9:d=12:e=15:f=18:g=21:h=24:i=27:j=39:k=42:l=45
	a2=100
	zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n)=1 And zOnGnd(n)=0 Then zBlowSeq(n)=a2
	If zBlowSeq(n) >= a2 Then doMysticAttack(n)
	
	If zBlowSeq(n) <= l Then
		If zBlowSeq(n)=1 And spellCooldownSeq(n, 1) > 0 Then
			If gameSound And zAi(n)=0 Then PlaySound clockTickSnd
			zBlowSeq(n)=0:zBlow(n)=0
		End If
;------------ Animation ------------
		If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=12:zf(n)=1
		If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=12:zf(n)=2
		If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zani(n)=12:zf(n)=3
		If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zani(n)=12:zf(n)=4
		If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zani(n)=12:zf(n)=5
		If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zani(n)=12:zf(n)=6
		If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zani(n)=12:zf(n)=7
		If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zani(n)=12:zf(n)=8
		If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zani(n)=12:zf(n)=9
		If zBlowSeq(n) >= i And zBlowSeq(n) <= j Then zani(n)=12:zf(n)=10
		If zBlowSeq(n) >= j And zBlowSeq(n) <= k Then zani(n)=12:zf(n)=11
		If zBlowSeq(n) >= k And zBlowSeq(n) <= l Then zani(n)=12:zf(n)=12
	
;------------ Sounds ------------
		If gameSound And zBlowSeq(n) = f Then PlaySound piccoloGrunt1Snd
		If gameSound And zBlowSeq(n) = g Then PlaySound piccoloKaikousenSnd
	
;------------ Effect -------------
		If zBlowSeq(n)=i Then 
			y=zy(n)-(zheight(n)-42)
			If zface(n)=2 Then x=zx(n)+36
			If zface(n)=4 Then x=zx(n)-100
			makeshot(n,49,x,y,zface(n))
			spellCooldownMaxTime(n, 1)=100
			spellCooldownSeq(n, 1)=spellCooldownMaxTime(n, 1) 
		End If
	End If

	If zBlowSeq(n) > l Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

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

Case 16 ;Taunt Key
	a=2:b=10:c=20:d=90
	a2=8:b2=18:c2=48:d2=58:e2=68
	zNoMove(n)=1:zNoJump(n)=1
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n)=1 Then zTauntSeed(n)=Rand(3)
;------------ Animation -------------
	If zTauntSeed(n)=1 Then
		If zBlowSeq(n)>=1 And zBlowSeq(n)<=a2 Then zani(n)=16:zf(n)=5
		If zBlowSeq(n)>=a2+1 And zBlowSeq(n)<=b2 Then zani(n)=16:zf(n)=6
		If zBlowSeq(n)>=b2+1 And zBlowSeq(n)<=c2 Then zani(n)=16:zf(n)=7
		If zBlowSeq(n)>=c2+1 And zBlowSeq(n)<=d2 Then zani(n)=16:zf(n)=8
		If zBlowSeq(n)>=d2+1 And zBlowSeq(n)<=e2 Then zani(n)=16:zf(n)=9
		If zBlowSeq(n)>e2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	Else
		If zBlowSeq(n)>=1 And zBlowSeq(n)<=a Then zani(n)=16:zf(n)=1
		If zBlowSeq(n)>=a+1 And zBlowSeq(n)<=b Then zani(n)=16:zf(n)=2
		If zBlowSeq(n)>=b+1 And zBlowSeq(n)<=c Then zani(n)=16:zf(n)=3
		If zBlowSeq(n)>=c+1 And zBlowSeq(n)<=d Then zani(n)=16:zf(n)=4
		If zBlowSeq(n)>d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	End If
	
;------------ Sounds ----------------
	If zTauntSeed(n)=1 Then 
		If zBlowSeq(n)=1 And gameSound Then PlaySound piccoloTaunt1Snd
	Else If zTauntSeed(n)=2 Then 
		If zBlowSeq(n)=1 And gameSound Then PlaySound piccoloTaunt2Snd
	Else
		If zBlowSeq(n)=1 And gameSound Then PlaySound piccoloTaunt3Snd
	End If

Case 17 ;Extra special key 
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
End Select

End Function