Function DoBroly(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
	If zBlowStill(n)=1 Then
		zBlowStillSeq(n)=zBlowStillSeq(n)+1
		If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
		Goto noBlowSeq13
	EndIf
zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq13
i=1 : ii=1: 
zCHunkType(n)=10
zBlockLife(n)=zBlockFull(n)
Select zCurBlow(n)

Case 0	;Blocking
	zNoMove(n)=1:zNoJump(n)=1
	zBlock(n)=1:zani(n)=13:zf(n)=1
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 14	;Super Special
	a=6:b=9:c=70
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	zBlock(n)=1
	If zongnd(n)=0 Then zy(n)=zy(n)-2.5 ;zNoGrav(n)=1
	If zBlowSeq(n) = b-1 And gameSound=1 Then PlaySound  brolyBall2Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) =b-2 Then
		checkDist(n,zx(n),zy(n)-20,zFace(n))
		zSuperMove(n)=0:zSuperMoveSeq(n)=0:
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
		If rendert = 2 Then checkDist(n,zx(n),zy(n)-20,zFace(n))
		If zBlowSeq(n) < c-10 Then hm=2 Else hm=0
makeRectHit(n,zx(n),zy(n)-70,50,70,2,0,2,.5,1,0,17,highpunchSnd)
makeRectHit(n,zx(n)-50,zy(n)-70,50,70,4,0,2,.5,1,0,17,highpunchSnd)
		makechunk(n,zx(n),zy(n),zFace(n),102)
		zani(n)=10:zf(n)=2
	EndIf
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zBlock(n)=0



Case 11	;club
	zCurBlow(n)=ii:remImune(n):Goto noBlowSeq13

Case 12	;Shooting Position
	zCurBlow(n)=ii:remImune(n):Goto noBlowSeq13

Case 13 ; item pickup
	zCurBlow(n)=ii:remImune(n):Goto noBlowSeq13

Case 15	;Projectile
	a=7:b=15:c=55
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound brolyNHitSnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=7:zf(n)=2
	If zBlowSeq(n)= b Then 
		dir=zface(n):y=zy(n)-zheight(n)+22
		If zface(n)=2 Then x=zx(n)+10
		If zface(n)=4 Then x=zx(n)-10
		makeshot(n,63,x,y,dir)
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=3
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0


Case 1	;High Punch
	a=8:b=16:c=24:d=32:e=40:f=48
	zNoMove(n)=1
	zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1:zblowAlert(n)=1
	If zBlowSeq(n) = a And gameSound = 1 Then PlaySound brolyPunchSnd
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=30:hblow(n,nn)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=20:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=18
		zBlowSound(n)=brolyPunchHitSnd
		zani(n)=6:zf(n)=2
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=5
	If zBlowSeq(n) => e And zBlowSeq(n) =< f Then zani(n)=6:zf(n)=6:zBlowSeq(n)=0:zBlow(n)=0
	
	
Case 2	;Flying Kick
	a=7:b=35
	zNoJump(n)=0:ZJUMPING(N)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:zblowAlert(n)=1
	If zBlowSeq(n) =a Then If gameSound Then PlaySound blowsnd
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
		zblowpamount(n)=2
		xblow(n,1)=-3: yblow(n,1)=16:wblow(n,1)=30:hblow(n,1)=1
		xblow(n,2)=-3: yblow(n,2)=11:wblow(n,2)=30:hblow(n,2)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=15:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
		zBlowSound(n)=kicksnd
		zani(n)=8:zf(n)=2
	EndIf
	If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
	If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4	;Low kick
	zNoMove(n)=1:zNoJump(n)=1
	zheight(n)=28
	a=5:b=10:c=25:d=32
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=1:zblowAlert(n)=1:zblowAlert(n)=1
	If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
		zblowPamount(n)=3
		xblow(n,1)=0: yblow(n,1)=9:wblow(n,1)=26:hblow(n,1)=1
		xblow(n,2)=0: yblow(n,2)=5:wblow(n,2)=32:hblow(n,2)=1
		xblow(n,3)=0: yblow(n,3)=1:wblow(n,3)=32:hblow(n,3)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=12:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=kicksnd
		zani(n)=9:zf(n)=2
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5	;up special ; combo
	a=6:b=25:c=41:d=59:e=74

	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1:zblowAlert(n)=1
	If zBlowSeq(n)= a Then
		zani(n)=10:zf(n)=1
		zBlock(n)=1
		zNoMove(n)=1
	EndIf
	zBlock(n)=0
	zNoMove(n)=0
	If zBlowSeq(n)= b Then If gameSound Then PlaySound brolyKicksnd
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
		zNoJump(n)=1:zNograv(n)=1
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=30:hblow(n,nn)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=8:zBLowEffect(n)=1:zBlowImpact(n)=20:zBlowStillTime(n)=10:zBlowBlockTime(n)=18
		
		zBlowSound(n)=Highpunchsnd
		zani(n)=6:zf(n)=2
	EndIf
	;If zBlowSeq(n) = c Then zani(n)=6:zf(n)=3
	If zBlowSeq(n)= c Then If gameSound Then PlaySound brolyPunchSnd
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=36:hblow(n,nn)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=8:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=18
		zBlowSound(n)=Highpunchsnd
		zani(n)=6:zf(n)=4
	EndIf
	If zBlowSeq(n)= d Then If gameSound Then PlaySound brolyKickSnd
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=30:hblow(n,nn)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=8:zBLowEffect(n)=1:zBlowImpact(n)=20:zBlowStillTime(n)=10:zBlowBlockTime(n)=18
		
		zBlowSound(n)=Highpunchsnd
		zani(n)=6:zf(n)=2
	EndIf
	If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0
	
Case 6	;throwing iten
	a=2:b=3:c=6:d=8
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=2
	If zBlowSeq(n)= b Then
		throwObj(n,zx(n),zy(n)-20,zFace(n))
		If gameSound Then PlaySound throwsnd
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=2
	If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 7	;Broly Ball 2; normal special
	a=8:b=30::c=40:d=50:e=60:f=110
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	j=zBlowSeq(n)
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound brolyBall2Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=4
	If zBlowSeq(n)= d Then 
		dir=zface(n):y=zy(n)-zheight(n)+10
		If zface(n)=2 Then x=zx(n)+10
		If zface(n)=4 Then x=zx(n)-10
		makeshot(n,64,x,y,dir)
	EndIf
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=5
	If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 8	;Dogding
	zNoMove(n)=1
	zNoJump(n)=1
	a=7:b=15:c=20:d=25:e=30:f=37
	If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=10:zf(n)=1:moveX(n,zBlowdir(n),1)

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	;down special
	a=8:b=80::c=90:d=100:e=110:f=170
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound brolyBallSnd

	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=4
	If zBlowSeq(n)= d Then 
		dir=zface(n):y=zy(n)-zheight(n)+10
		If zface(n)=2 Then x=zx(n)+10
		If zface(n)=4 Then x=zx(n)-10
		makeshot(n,62,x,y,dir)
	EndIf
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=5
	If zBlowSeq(n) => e And zBlowSeq(n) =< f Then zani(n)=10:zf(n)=6
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0

Case 10	;High Kick
	zNoMove(n)=1:zNoJump(n)=1
	a=8:b=18:c=23:d=30
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1:zblowAlert(n)=1
	If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
		zblowPamount(n)=3
		xblow(n,1)=0: yblow(n,1)=65:wblow(n,1)=1:hblow(n,1)=40
		xblow(n,2)=5: yblow(n,2)=65:wblow(n,2)=1:hblow(n,2)=35
		xblow(n,3)=10: yblow(n,3)=65:wblow(n,3)=1:hblow(n,3)=30
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=10:zBLowEffect(n)=1:zBlowImpact(n)=20:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=kicksnd
		zani(n)=14:zf(n)=2
	EndIf
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
		zblowPamount(n)=3
		xblow(n,1)=28: yblow(n,1)=50:wblow(n,1)=1:hblow(n,1)=20
		xblow(n,2)=20: yblow(n,2)=47:wblow(n,2)=1:hblow(n,2)=20
		xblow(n,3)=7: yblow(n,3)=42:wblow(n,3)=1:hblow(n,3)=20
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=6:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10
		zBlowSound(n)=kicksnd
		zani(n)=6:zf(n)=2
	EndIf
		If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=14:zf(n)=1
	If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0
	
Case 16 ;Counter key
	a=1: b=a+8: c=b+3: d=c+3: e=d+4: f=e+4 : g=f+3
	zNoMove(n)=1
	zNoJump(n)=1
	
	If zBlowSeq(n) = a And gameSound Then PlaySound teleportSnd
	
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=13:zf(n)=1
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=13:zf(n)=1
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=13:zf(n)=1
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=13:zf(n)=1
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=13:zf(n)=1
	If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=13:zf(n)=1

	If zBlowSeq(n) > a And zBlowSeq(n) =< d Then
	    If rightKey(n)=1 Then changed=1: xdi = 145 : dif=-42
	    If leftKey(n) =1 Then changed=1: xdi = -145 : dif=42
	    ;If upKey(n)=1 then changed=1: ydi = -60
	    ;If downKey(n)=1 then changed=1: ydi = 60
	EndIf

	If zBlowSeq(n) = d Then ;check collision for teleporting
		If changed=0 Then
			If zBlowDir(n)=2 Then
				xdi=145 : ydi=0 : dif=-42
			Else
				xdi=-145: ydi=0 : dif=42
			EndIf
		EndIf
		zx(n) = zx(n) + xdi
		zy(n) = zy(n) + ydi
		zonplat(n)=0
    EndIf
	

    If zblowseq(n) => a And zblowseq(n) <= f Then zshield(n)=1
	If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 17 ;Extra special key
	zCanFly(n)=1
	zBlowSeq(n)=0:zBlow(n)=0
	
End Select

End Function