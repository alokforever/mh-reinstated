Function DoWonderWoman(n)

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
	If zblocked(n)=1 Then zani(n)=13:zf(n)=2
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1	;Attack
	zBlowSeq(n)=0:zBlow(n)=0

Case 2	;Flying Kick
	zBlowSeq(n)=0:zBlow(n)=0

Case 4	;Low kick
	zBlowSeq(n)=0:zBlow(n)=0

Case 5	;Up + Special (Warrior's heart)
   	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	zChunkType(n)=50
	zJump(n)=0
	a=3:b=a+3:c=b+3:d=c+2:e=d+2:f=e+1:g=f+1:h=g+2:i=h+2:j=i+2:k=j+2
	l=k+2:m=l+2:n1=m+1:o=n1+1:p=o+2:q=p+2:r=q+3:s=r+3
	aa=s+2:bb=aa+2:cc=bb+2:dd=cc+1:ee=dd+1:ff=ee+2:gg=ff+2:hh=gg+3:ii=hh+3
	
	If zBlowSeq(n)=1 And upKeyDoubleTap(n)=1 Then
		zBlowUpLimit(n)=zy(n)-80
		attackMode(n, 1)=1
	Else If zBlowSeq(n)=1 And upKeyDoubleTap(n)=0
		zBlowUpLimit(n)=zy(n)-70
		attackMode(n, 1)=0
	End If

;------------- Sounds ----------------
	If zBlowSeq(n) = c And attackMode(n, 1)=0 And gameSound Then PlaySound wonderwomanWH1Snd
	If zBlowSeq(n) = c And attackMode(n, 1)=1 And gameSound Then PlaySound wonderwomanWH2Snd
	If (zBlowSeq(n) = e-1 Or zBlowSeq(n) = m-1 Or (zBlowSeq(n) = cc-1 And attackMode(n, 1)=1)) And gameSound Then PlaySound wonderwomanWhWaveSnd
 
;------------ Animations -------------
	If zBlowSeq(n) => 1 And zBlowSeq(n) <= a Then zani(n)=7:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) <= b Then zani(n)=7:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) <= c Then zani(n)=7:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) <= d Then zani(n)=7:zf(n)=4:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) => d And zBlowSeq(n) <= e Then zani(n)=7:zf(n)=5:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) => e And zBlowSeq(n) <= f Then zani(n)=7:zf(n)=6:zy(n)=zy(n)-3
	If zBlowSeq(n) => f And zBlowSeq(n) <= g Then zani(n)=7:zf(n)=7:zy(n)=zy(n)-3
	If zBlowSeq(n) => g And zBlowSeq(n) <= h Then zani(n)=7:zf(n)=8:zy(n)=zy(n)-3
	If zBlowSeq(n) => h And zBlowSeq(n) <= i Then zani(n)=7:zf(n)=9:zy(n)=zy(n)-3
	If zBlowSeq(n) => i And zBlowSeq(n) <= j Then zani(n)=7:zf(n)=10:zNoGrav(n)=0
	If zBlowSeq(n) => j And zBlowSeq(n) <= k Then zani(n)=7:zf(n)=11:zNoGrav(n)=0
	If zBlowSeq(n) => k And zBlowSeq(n) <= l Then zani(n)=7:zf(n)=12:moveX(n,zBlowdir(n),3):zy(n)=zy(n)-4
	If zBlowSeq(n) => l And zBlowSeq(n) <= m Then zani(n)=7:zf(n)=5:zy(n)=zy(n)-3
	If zBlowSeq(n) => m And zBlowSeq(n) <= n1 Then zani(n)=7:zf(n)=6:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) => n1 And zBlowSeq(n) <= o Then zani(n)=7:zf(n)=7:zy(n)=zy(n)-3
	If zBlowSeq(n) => o And zBlowSeq(n) <= p Then zani(n)=7:zf(n)=8:zy(n)=zy(n)-3
	If zBlowSeq(n) => p And zBlowSeq(n) <= q Then zani(n)=7:zf(n)=9:zy(n)=zy(n)-3
	If zBlowSeq(n) => q And zBlowSeq(n) <= r Then zani(n)=7:zf(n)=10:zNoGrav(n)=0
	If zBlowSeq(n) => r And zBlowSeq(n) <= s Then zani(n)=7:zf(n)=11:zNoGrav(n)=0

	If attackMode(n, 1)=1 Then
		If zBlowSeq(n) => aa And zBlowSeq(n) <= bb Then zani(n)=7:zf(n)=12:moveX(n,zBlowdir(n),1.5):zy(n)=zy(n)-2
		If zBlowSeq(n) => bb And zBlowSeq(n) <= cc Then zani(n)=7:zf(n)=5:zy(n)=zy(n)-1.5
		If zBlowSeq(n) => cc And zBlowSeq(n) <= dd Then zani(n)=7:zf(n)=6:moveX(n,zBlowdir(n),1.5)
		If zBlowSeq(n) => dd And zBlowSeq(n) <= ee Then zani(n)=7:zf(n)=7:zy(n)=zy(n)-1.5
		If zBlowSeq(n) => ee And zBlowSeq(n) <= ff Then zani(n)=7:zf(n)=8:zy(n)=zy(n)-1.5
		If zBlowSeq(n) => ff And zBlowSeq(n) <= gg Then zani(n)=7:zf(n)=9:zy(n)=zy(n)-1.5
		If zBlowSeq(n) => gg And zBlowSeq(n) <= hh Then zani(n)=7:zf(n)=10:zNoGrav(n)=0
		If zBlowSeq(n) => hh And zBlowSeq(n) <= ii Then zani(n)=7:zf(n)=11:zNoGrav(n)=0
	End If

;------------ Hit boxes --------------
	If (zBlowSeq(n) > e And zBlowSeq(n) =< g) Or (zBlowSeq(n) > m And zBlowSeq(n) < o) Or (attackMode(n, 1)=1 And zBlowSeq(n) > cc And zBlowSeq(n) < ee) Then
		zblowPamount(n)=3:nn=1
		zBlowBack(n)=1
		xblow(n,nn)=-10: yblow(n,nn)=30:wblow(n,nn)=20:hblow(n,nn)=20:nn=nn+1
		xblow(n,nn)=-10: yblow(n,nn)=50:wblow(n,nn)=20:hblow(n,nn)=20:nn=nn+1
		xblow(n,nn)=-10: yblow(n,nn)=70:wblow(n,nn)=20:hblow(n,nn)=20:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=3:zHitUpSpeed#(n)=4.5:zHitTime(n)=20
		If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
		zBlowDamage(n)=5:zBLowEffect(n)=1:zBlowImpact(n)=10:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=mvcHit1Snd
		zani(n)=7:zf(n)=6:zantiplat(n)=1
	EndIf
	If (zBlowSeq(n) > g And zBlowSeq(n) =< i) Or (zBlowSeq(n) > p And zBlowSeq(n) <= r) Or (attackMode(n, 1)=1 And zBlowSeq(n) > ff And zBlowSeq(n) <= hh) Then
		zBlowPamount(n)=1:nn=1
		xblow(n,nn)=-27: yblow(n,nn)=73:wblow(n,nn)=68:hblow(n,nn)=17
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=3:zHitUpSpeed#(n)=4.5:zHitTime(n)=20
		If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
		zBlowDamage(n)=5:zBLowEffect(n)=1:zBlowImpact(n)=15:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=mvcHit1Snd
		zantiplat(n)=1
	EndIf

	If zHitHead(n)=1 Then zBlowSeq(n)=ii
	If (zBlowSeq(n) => s And attackMode(n, 1)=0) Or (zBlowSeq(n) >= ii And attackMode(n, 1)=1) Then 
		If zBlowSeq(n) Mod 2=0 Or zBlowSeq(n) Mod 3=0 Or zBlowSeq(n) Mod 4=0 Then 
			zani(n)=7:zf(n)=10
		Else If zBlowSeq(n) Mod 5=0 Or zBlowSeq(n) Mod 6=0 Or zBlowSeq(n) Mod 7=0 
			zani(n)=7:zf(n)=11
		End If
		zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	End If
	If zongnd(n)=1 And zBlowSeq(n) >= s-2 And attackMode(n, 1)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	If zongnd(n)=1 And zBlowSeq(n) >= ii-2 And attackMode(n, 1)=1 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0


Case 6	;throwing iten
	zBlowSeq(n)=0:zBlow(n)=0

Case 7	;Special (Magic Lasso)
	zBlowSeq(n)=0:zBlow(n)=0

Case 8	;Dodging
	zNoMove(n)=1
	zNoJump(n)=1
	zheight(n)=25
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

Case 9	;Down + Special (Amazon Aegis (Projectile deflector))
	zNoMove(n)=1:zNoJump(n)=1
	a=3:b=a+7:c=b+4:d=c+4:e=d+4:f=e+4:g=f+4:h=g+4:i=h+4:j=i+4:k=j+6:l=k+4
	aa=100:bb=aa+1:cc=bb+5:dd=cc+5:ee=dd+2:ff=ee+2:gg=ff+2:hh=gg+12:ii=hh+4
	
	If zOnGnd(n)=0 Then zy(n)=zy(n)-2
;---------- Sound effects ----------
	If zBlowSeq(n) = a And gameSound Then PlaySound wwAegisMetalSnd
	If zBlowSeq(n) = b And gameSound Then PlaySound wwAegisSnd

;---------- Animation ------------
	If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=12:zf(n)=1
	If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zani(n)=12:zf(n)=3
	If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zani(n)=12:zf(n)=3
	If zBlowSeq(n) >= c And zBlowSeq(n) <= f Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) >= d And zBlowSeq(n) <= g Then zani(n)=12:zf(n)=3
	If zBlowSeq(n) >= e And zBlowSeq(n) <= h Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) >= f And zBlowSeq(n) <= i Then zani(n)=12:zf(n)=3
	If zBlowSeq(n) >= g And zBlowSeq(n) <= j Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) >= h And zBlowSeq(n) <= k Then zani(n)=12:zf(n)=4
	If zBlowSeq(n) >= i And zBlowSeq(n) <= l Then zani(n)=12:zf(n)=1
	
	If zBlowSeq(n) >= aa And zBlowSeq(n) <= bb Then zani(n)=12:zf(n)=4
	If zBlowSeq(n) >= bb And zBlowSeq(n) <= cc Then zani(n)=12:zf(n)=5
	If zBlowSeq(n) >= cc And zBlowSeq(n) <= dd Then zani(n)=12:zf(n)=6
	If zBlowSeq(n) >= dd And zBlowSeq(n) <= ee Then zani(n)=12:zf(n)=7
	If zBlowSeq(n) >= ee And zBlowSeq(n) <= ff Then zani(n)=12:zf(n)=8
	If zBlowSeq(n) >= ff And zBlowSeq(n) <= gg Then zani(n)=12:zf(n)=7
	If zBlowSeq(n) >= gg And zBlowSeq(n) <= hh Then zani(n)=12:zf(n)=8
	If zBlowSeq(n) >= hh And zBlowSeq(n) <= ii Then zani(n)=12:zf(n)=1

	If zBlowSeq(n) > ii Then zBlowSeq(n) = l
;---------- Special Effect ---------
	If zBlowSeq(n) >= a And zBlowSeq(n) < j Then 
		projectileDeflectMode(n)=1:projectileDeflectSpeed(n)=1
		If isDeflecting(n)=1 Then
			If gameSound Then PlaySound wwAegisHitSnd
			isDeflecting(n)=0
			zBlowSeq(n)=aa
		End If
	End If

	If zBlowSeq(n) > l And zBlowSeq(n) < aa Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10	;High Kick 
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 11	;club
	a=12/wolvSpdFctr(n):b=22/wolvSpdFctr(n):c=29/wolvSpdFctr(n):d=50/wolvSpdFctr(n):e=55/wolvSpdFctr(n)
	zNoMove(n)=1
	zNoJump(n)=1
	extraDraw(n)=1
	drawObjOnZ(n)=0
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
		zBlowDamage(n)=25:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
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

Case 15 ;WW throw
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 16 ;Counter Key (Taunt)
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 17 ;Extra special key (Flight)
	zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
End Select

End Function
