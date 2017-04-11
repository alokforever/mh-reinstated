Function DoScorpion(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
	If zBlowStill(n)=1 Then
		zBlowStillSeq(n)=zBlowStillSeq(n)+1
		If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
		Goto noBlowSeq1
	EndIf
zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq1
zCHunkType(n)=10

Select zCurBlow(n)
Case 0	;Blocking
	zNoMove(n)=1:zNoJump(n)=1
	zBlock(n)=1:zani(n)=13:zf(n)=1	;normal blocking
	If zblocked(n)=1 Then zani(n)=13:zf(n)=2
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0
	
Case 15 ;Scorpion throw
	a=8: b=15: c=25: d=30: e=35: f=40: g=45: h=50: i=60
	zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=2
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
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1:zFace(en)=dir
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n2:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=5:zx(en)=zx(n)+n1:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
	If zblowseq(n) = f Then
		If gameSound Then PlaySound subZeroThrowSnd
	EndIf
	If zBlowSeq(n) => i-3 And zBlowSeq(n) < i Then zani(n)=15:zf(n)=6:zx(en)=zx(n)+n1:zy(en)=zy(n)-15:zAni(en)=2:zf(en)=6:zface(en)=dir2
	If zBlowSeq(n) = i  Then
		zx(en)=zx(n)+0:zy(en)=zy(n)-13
		zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
		zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
		zDamage(en)=zDamage(en)+10
		zLife(en)=zLife(en)-10
		zFace(en)=dir : zFallDir(en)=dir
		zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
	EndIf
	If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=7
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=8
	
	If zBlowSeq(n) > c And zBlowSeq(n) < g Then zgrabbed(en)=1:checkZvsWall(en,0)
	If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0

	
Case 14	;Super Special
	a=5:b=10:c=15:d=55
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2


Case 11	;club
	a=12:b=22:c=29:d=50:e=55
	zNoMove(n)=1
	zNoJump(n)=1
	extraDraw(n)=1: drawObjOnZ(n)=0
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-12:yed(n)=20
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-13:yed(n)=21
	If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
		zblowPamount(n)=6
		nn=1
		xblow(n,nn)=40: yblow(n,nn)=4:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=88:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=88:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=85:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=75:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0 :zBlowHold(n)=10
		zBlowDamage(n)=25:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
		zChunkType(n)=5
		zBlowSound(n)=smashsnd
		zani(n)=6:zf(n)=4
		eAni(n)=1:ef(n)=3:xED(n)=57:yed(n)=7
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4 :eAni(n)=1:ef(n)=4:xed(n)=56:yed(n)=6
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-13:yed(n)=21
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
			makechunk(n,x,zy(n)-27,2,50)
		Else
			If gameSound Then PlaySound shotwallsnd
		EndIf
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then 
		If zBlowDir(n)=2 Then dir=4 Else dir=2
		zani(n)=6:zf(n)=2
		eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
		zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
	EndIf
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
	b=2:c=8
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=5
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

Case 1	;Kick
	a=3: b=6: c=9: d=12: e=20: f=24: g=37: h=44
	zNoMove(n)=1
	zNoJump(n)=1
	If zBlowSeq(n) = c And gameSound Then PlaySound blow2snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=3
	If zBlowseq(n) > c And zblowseq(n) =< d Then zani(n)=14:zf(n)=4
	If zBlowseq(n) > d And zblowseq(n) =< e Then
		zblowPamount(n)=2:nn=1
		xblow(n,nn)=6: yblow(n,nn)=70:wblow(n,nn)=13:hblow(n,nn)=15:nn=nn+1
		xblow(n,nn)=6: yblow(n,nn)=42:wblow(n,nn)=13:hblow(n,nn)=17:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=14:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
		zBlowSound(n)=mkStrongHitSnd
		zani(n)=14:zf(n)=4
	EndIf
	If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=14:zf(n)=4
	If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=14:zf(n)=4
	If zBlowSeq(n) > g And zBlowSeq(n) <= h Then zani(n)=14:zf(n)=2
	
	If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0
	
	
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
		zBlowDamage(n)=9:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
		zBlowSound(n)=kicksnd
		zani(n)=8:zf(n)=2
	EndIf
	If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
	If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4	;Low kick
	zNoMove(n)=1:zNoJump(n)=1
	zheight(n)=zduckheight(n)
	a=2:b=5:c=7:d=10:e=12:f=15:g=18:h=21:i=24
	If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=9:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=9:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=9:zf(n)=4
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=9:zf(n)=5
	If zBlowSeq(n) = d And gameSound Then PlaySound mkKickSnd
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then
		zblowPamount(n)=3
		xblow(n,1)=0: yblow(n,1)=9:wblow(n,1)=25:hblow(n,1)=1
		xblow(n,2)=0: yblow(n,2)=5:wblow(n,2)=28:hblow(n,2)=1
		xblow(n,3)=0: yblow(n,3)=1:wblow(n,3)=28:hblow(n,3)=1
		zHitMode(n)=0:zBlowHold(n)=0
		zBlowDamage(n)=12:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=mkKickHitSnd
		zani(n)=9:zf(n)=6
	EndIf
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=9:zf(n)=7
	If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=9:zf(n)=8
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=9:zf(n)=9
	If zBlowSeq(n) > i Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5	;Uppercut
	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	a=3:b=6:c=9:d=12:e=21:f=30:g=38:h=47:i=56:j=64:k=73
	If zBlowSeq(n) =1 Then
		If gameSound Then PlaySound sonyaUppersnd
		zBlowUpLimit(n)=zy(n)-k:zJump(n)=0
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1:moveX(n,zBlowdir(n),.5):zblowAlert(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=2:moveX(n,zBlowdir(n),.5):zblowAlert(n)=1
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=3:moveX(n,zBlowdir(n),.5):zblowAlert(n)=1
	If zBlowSeq(n) > c And zBlowSeq(n) =< k
		zblowpamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=18:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=39:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
		If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
		zBlowDamage(n)=2:zBLowEffect(n)=1:zBlowImpact(n)=15:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=Highpunchsnd
		zani(n)=7:zf(n)=4
	EndIf
	If zBlowSeq(n) > d And zBlowSeq(n) <= k Then
		If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=7:zf(n)=4:zantiplat(n)=1
		If zBlowSeq(n) => e And zBlowSeq(n) =< f Then zani(n)=7:zf(n)=5:zantiplat(n)=1
		If zBlowSeq(n) => f And zBlowSeq(n) =< g Then zani(n)=7:zf(n)=6:zantiplat(n)=1
		If zBlowSeq(n) => g And zBlowSeq(n) =< h Then zani(n)=7:zf(n)=7:zantiplat(n)=1
		If zBlowSeq(n) => h And zBlowSeq(n) =< i Then zani(n)=7:zf(n)=6:zantiplat(n)=1
		If zBlowSeq(n) => i And zBlowSeq(n) =< j Then zani(n)=7:zf(n)=7:zantiplat(n)=1
		zblowpamount(n)=6:nn=1
		xblow(n,nn)=0: yblow(n,nn)=56:wblow(n,nn)=6:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=48:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=42:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=37:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=28:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
		If zBlowStill(n)=0 Then zy(n)=zy(n)-3:moveX(n,zBlowdir(n),2)
		zBlowDamage(n)=2:zBLowEffect(n)=1:zBlowImpact(n)=11:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=Highpunchsnd
	EndIf
	If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=k
	If zBlowSeq(n) => k Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	If zongnd(n)=1 And zBlowSeq(n) => k-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
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
Case 7	;Scorpion Spear
	a=7:b=15:c=55
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound sonyaballsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n)= b Then 
		dir=zface(n):y=zy(n)-zheight(n)+20
		If zface(n)=2 Then x=zx(n)+10
		If zface(n)=4 Then x=zx(n)-10
		makeshot(n,61,x,y,dir)
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 8	;Dodging
	zNoMove(n)=1
	zNoJump(n)=1
	zheight(n)=25
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

Case 9	;Teleport punch
	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	a=3:b=6:c=9:d=12:e=15:f=18: spinN=0

	If zongnd(n)=1 And zBlowSeq2(n) > spinN + 25 Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10	;Up + Attack
	a=3: b=6: c=9: d=12: e=20: f=24: g=37: h=44
	zNoMove(n)=1
	zNoJump(n)=1
	If zBlowSeq(n) = c And gameSound Then PlaySound blow2snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=3
	If zBlowseq(n) > c And zblowseq(n) =< d Then zani(n)=14:zf(n)=4
	If zBlowseq(n) > d And zblowseq(n) =< e Then
		zblowPamount(n)=2:nn=1
		xblow(n,nn)=6: yblow(n,nn)=70:wblow(n,nn)=13:hblow(n,nn)=15:nn=nn+1
		xblow(n,nn)=6: yblow(n,nn)=42:wblow(n,nn)=13:hblow(n,nn)=17:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=14:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
		zBlowSound(n)=mkStrongHitSnd
		zani(n)=14:zf(n)=5
	EndIf
	If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=14:zf(n)=5
	If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=14:zf(n)=5
	If zBlowSeq(n) > g And zBlowSeq(n) <= h Then zani(n)=14:zf(n)=2
	
	If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0
	
Case 16 ; taunt
	a=20:b=40
	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	If zongnd(n) = 0 And zBlowSeq(n) <= 2 Then
		zBlowSeq(n) = b
	EndIf
	If zBlowSeq(n) = 1 Then
		If gameSound Then PlaySound sonyaSpinsnd
		zBlowUpLimit(n)=zy(n)-73
	EndIf
	If zBlowSeq(n) => 2 And zBlowSeq(n) < a Then
		zani(n)=16:zf(n)=1
		moveY(n,-3)
		zblowpamount(n)=2
		xblow(n,1)=-20: yblow(n,1)=55:wblow(n,1)=40:hblow(n,1)=1
		xblow(n,2)=-20: yblow(n,2)=55:wblow(n,2)=40:hblow(n,2)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=10:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=Highpunchsnd
	EndIf
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then
		zani(n)=16:zf(n)=2
		moveX2(n,zBlowdir(n),4.5)
		zblowPamount(n)=2
		xblow(n,nn)=20: yblow(n,nn)=31:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=25:wblow(n,nn)=27:hblow(n,nn)=1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=10:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=Highpunchsnd
	EndIf
	If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then 
		zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
		zBlowUpLimit(n)=-9999
	EndIf
	
	;If zBlowSeq(n) => b Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	If zongnd(n)=1 And zBlowSeq(n) => b-2 Or zBlowSeq(n) > b Then 
		zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:
	EndIf
	
Case 17 ;flame
	zBlowSeq(n)=0:zBlow(n)=0
	
End Select	

End Function