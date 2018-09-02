Function doSonicSlash(n)
	startSeq=1000:seq1=startSeq+3:seq2=seq1+3:seq3=seq2+3:
	seq4=seq3+14:seq5=seq4+5:seq6=seq5+15:seq7=seq6+5:seq8=seq7+3
	endSeq=2000
	zNoGrav(n)=1
;--------- Animation ----------
	If zBlowSeq(n)>=startSeq And zBlowSeq(n) < seq1 Then zani(n)=18:zf(n)=1
	If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then zani(n)=18:zf(n)=2
	If zBlowSeq(n)>=seq2 And zBlowSeq(n) < seq3 Then zani(n)=18:zf(n)=3
	If zBlowSeq(n)>=seq3 And zBlowSeq(n) < seq4 Then zani(n)=18:zf(n)=4
	If zBlowSeq(n)>=seq4 And zBlowSeq(n) < seq5 Then zani(n)=18:zf(n)=5
	If zBlowSeq(n)>=seq5 And zBlowSeq(n) < seq6 Then zani(n)=18:zf(n)=6
	If zBlowSeq(n)>=seq6 And zBlowSeq(n) < seq7 Then zani(n)=18:zf(n)=7
	If zBlowSeq(n)>=seq7 And zBlowSeq(n) < seq8 Then zani(n)=18:zf(n)=8
	
;--------- Sounds -----------
	If zBlowSeq(n)=seq3 And gameSound Then PlaySound piccoloGrunt2Snd
	
;-------- Movement ----------
	If zBlowSeq(n)>=startSeq And zBlowSeq(n) < seq2 Then moveX(n,zBlowdir(n),8)
	If zBlowSeq(n)>=seq2 And zBlowSeq(n) < seq3 Then moveX(n,zBlowdir(n),4)
	If zBlowSeq(n)>=seq3 And zBlowSeq(n) < seq4 Then moveX(n,zBlowdir(n),2)
	If zBlowSeq(n)>=seq4 And zBlowSeq(n) < seq5 Then moveX(n,zBlowdir(n),1)

;--------- Hit box ----------
	If zBlowSeq(n)>=seq2 And zBlowSeq(n)<seq4 Then
		zblowPamount(n)=1:nn=1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=30:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0:zBlowStillTime(n)=0
		zHitSpeed#(n)=4:zHitUpSpeed#(n)=1.5:zHitTime(n)=40
		zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=14:zBlowBlockTime(n)=40
		zBlowSound(n)=dbzKneeHitSnd
	Else If zBlowSeq(n)>=seq4 And zBlowSeq(n)<seq5 Then
		zblowPamount(n)=1:nn=1
		xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=50:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
		zHitSpeed#(n)=5:zHitUpSpeed#(n)=1.5
		zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=0:zHitTime(n)=40
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=40:zBlowBlockTime(n)=40
		zBlowSound(n)=dbzKneeHitSnd
	End If
	
	If zBlowSeq(n)=seq8 Then zBlowSeq(n)=endSeq
End Function

Function performFollowUpKneeHit(n)
	seq0=100:seq1=seq0+12:seq2=seq1+3:seq3=seq2+3:seq4=seq3+3:seq5=seq4+8
	seq6=seq5+4:seq7=seq6+4:seq8=seq7+4:endSeq=300
	If zBlowSeq(n) >= seq1 Then 
		zNoGrav(n)=0
		moveX(n,zBlowdir(n),2)
		zy(n)=zy(n)-2
	End If
;--------- Animation ----------
	If zBlowSeq(n) > seq0 And zBlowSeq(n) <= seq1 Then zani(n)=7:zf(n)=7
	If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=7:zf(n)=9
	If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=7:zf(n)=10
	If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=7:zf(n)=11
	If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=7:zf(n)=12
	If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=7:zf(n)=13
	If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=7:zf(n)=14
	If zBlowSeq(n) > seq7 Then zBlowSeq(n)=endSeq
;--------- Sounds -----------
	If zBlowSeq(n)=seq1 And gameSound Then PlaySound piccoloFollowUpHitSnd
;--------- Hit box ----------
	If zBlowSeq(n)>seq5 And zBlowSeq(n)<seq6 Then
		zblowPamount(n)=4:nn=1
		zBlowBack(n)=1
		xblow(n,nn)=-10: yblow(n,nn)=10:wblow(n,nn)=60:hblow(n,nn)=25:nn=nn+1
		xblow(n,nn)=-10: yblow(n,nn)=30:wblow(n,nn)=60:hblow(n,nn)=20:nn=nn+1
		xblow(n,nn)=-10: yblow(n,nn)=30:wblow(n,nn)=60:hblow(n,nn)=20:nn=nn+1
		xblow(n,nn)=-10: yblow(n,nn)=70:wblow(n,nn)=60:hblow(n,nn)=20:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=8:zBlowStillTime(n)=6
		zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=5:zHitTime(n)=40
		zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowBlockTime(n)=40
		zBlowSound(n)=dbzKneeHitSnd
	End If
End Function

Function doBuukuKyaku2(n)
	a=20000:b=a+96:c=b+16:d=c+4:e=d+5:f=e+3
	endSeq=95
	zani(n)=12
	zNoGrav(n)=1
;--------- Animation ----------
	If zBlowSeq(n)>=a And zBlowSeq(n) Mod 4=0 Then
		If zf(n)=14 Then 
			zf(n)=20
		Else If zf(n)=20 Then 
			zf(n)=21
		Else If zf(n)=21 Then 
			zf(n)=17
		Else
			zf(n)=14
		End If
		If zBlowSeq(n)>=b And zBlowSeq(n)<c Then zf(n)=17
		If zBlowSeq(n)>=c And zBlowSeq(n)<d Then zf(n)=14
		If zBlowSeq(n)>=d And zBlowSeq(n)<e Then zf(n)=13
		If zBlowSeq(n)>=e And zBlowSeq(n)<e Then zani(n)=5:zf(n)=2
	End If
	
;------------- Sounds --------------
	If zBlowSeq(n)=b-1 And gameSound Then PlaySound dbzSuperKickSnd
;------------- Hitboxes ------------
		If zf(n)=20 Or zf(n)=17 Then
			dmg=Rand(2)
			zblowPamount(n)=5:nn=1
			zBlowBack(n)=1
			xblow(n,nn)=-5: yblow(n,nn)=27:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
			xblow(n,nn)=-5: yblow(n,nn)=20:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
			xblow(n,nn)=0: yblow(n,nn)=13:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
			xblow(n,nn)=5: yblow(n,nn)=6:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
			xblow(n,nn)=10: yblow(n,nn)=-1:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
			zHitmode(n)=2:zBlowHold(n)=16:zBlowStillTime(n)=0
			zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=2:zHitTime(n)=20
			zBlowDamage(n)=dmg:zBLowEffect(n)=1:zEnemyImmuneTime(n)=7:zBlowBlockTime(n)=40
			zBlowSound(n)=dbzKneeHitSnd
			If zBlowSeq(n)>b-4 Then zHitmode(n)=0:zBlowHold(n)=0:zEnemyImmuneTime(n)=20
		End If
	If zBlowSeq(n)=e Then zBlowSeq(n)=endSeq
End Function

Function doBuukuKyaku(n)
	a1=100:b1=102:c1=105:d1=110
	a2=10000:b2=10004:c2=b2+5:d2=c2+3
	a3=20000
	endSeq=95
	zNoMove(n)=1
	If downKey(n)=0 And zBlowSeq(n) < a2 Then zBlowSeq(n)=a2
	If zOnGnd(n)=1 Then zBlowSeq(n)=endSeq
	zNoGrav(n)=1

;-------- Animation ---------
	If zBlowSeq(n) > a1 And zBlowSeq(n) <= b1 Then zani(n)=12:zf(n)=13
	If zBlowSeq(n) > b1 And zBlowSeq(n) <= c1 Then zani(n)=12:zf(n)=14
	If zBlowSeq(n) > c1 And zBlowSeq(n) <= d1 Then zani(n)=12:zf(n)=19
	If zBlowSeq(n) > d1 Then
		If zBlowSeq(n) Mod 2 = 0 Then zani(n)=12:zf(n)=15
		If zBlowSeq(n) Mod 4 = 0 Then zani(n)=12:zf(n)=16
	End If
	
	If zBlowSeq(n) > a2 And zBlowSeq(n) <= b2 Then zani(n)=12:zf(n)=14
	If zBlowSeq(n) > b2 And zBlowSeq(n) <= c2 Then zani(n)=12:zf(n)=13
	If zBlowSeq(n) > c2 And zBlowSeq(n) <= d2 Then zani(n)=5:zf(n)=2
	If zBlowSeq(n) > d2 Then zBlowSeq(n)=endSeq

;-------- Hitboxes ----------
	If zBlowSeq(n) > d1 And zBlowSeq(n)<=a2 Then
		zblowPamount(n)=4:nn=1
		zBlowBack(n)=1
		xblow(n,nn)=5: yblow(n,nn)=27:wblow(n,nn)=9:hblow(n,nn)=7:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=20:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=13:wblow(n,nn)=11:hblow(n,nn)=7:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=7:wblow(n,nn)=12:hblow(n,nn)=12:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=24:zBlowStillTime(n)=6
		zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=2:zHitTime(n)=20
		zBlowDamage(n)=3:zBLowEffect(n)=1:zEnemyImmuneTime(n)=7:zBlowBlockTime(n)=40
		zBlowSound(n)=dbzKneeHitSnd
		If zBlowStill(n) Then 
			zBlowSeq(n)=a3:zy(n)=zy(n)+15
			If zFace(n)=2 Then zx(n)=zx(n)+15
			If zFace(n)=4 Then zx(n)=zx(n)-15
		End If
	End If
	
	If zBlowSeq(n) >= a3 Then doBuukuKyaku2(n)
	
;----------- Sound -----------
	If zBlowSeq(n)=a1 And gameSound Then PlaySound piccoloBuukuKyakuSnd
	If zBlowSeq(n)=b1 And gameSound Then PlaySound piccoloGrunt1Snd
	
;--------- Movement ---------
	If zBlowSeq(n) >= d1 And zBlowSeq(n) < a2 Then moveX(n,zBlowdir(n),4.5):moveY(n,2.5)
	
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

zchunkType(n)=20

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

Case 5	;UP + SPECIAL (jumping knee list)
	a=3:b=6:c=9:d=12:e=16:f=20:g=34:h=g+7:i=h+8:
	seq1=100:endSeq=seq1+200
	zNoMove(n)=1:zNoGrav(n)=1
	zNoJump(n)=1:zJumping(n)=0
	If zBlowSeq(n)=1 Then zBlowUpLimit(n)=zy(n)-70:zJump(n)=0:isMoveHit(n)=0
;--------- animation -----------
	If zBlowSeq(n)>0 And zBlowSeq(n)<=a Then zani(n)=7:zf(n)=1
	If zBlowSeq(n)>a And zBlowSeq(n)<=b Then zani(n)=7:zf(n)=2
	If zBlowSeq(n)>b And zBlowSeq(n)<=c Then zani(n)=7:zf(n)=3
	If zBlowSeq(n)>c And zBlowSeq(n)<=d Then zani(n)=7:zf(n)=4
	If zBlowSeq(n)>d And zBlowSeq(n)<=e Then zani(n)=7:zf(n)=5
	If zBlowSeq(n)>e And zBlowSeq(n)<=f Then zani(n)=7:zf(n)=6
	If zBlowSeq(n)>f And zBlowSeq(n)<=g Then 
		zani(n)=7:zf(n)=7
		If zBlowSeq(n) Mod 4 = 0 Then extraObj(n,zx(n),0,zy(n),0,zFace(n),124)
	End If
	If zBlowSeq(n)>g And zBlowSeq(n)<=h Then zani(n)=7:zf(n)=8
	If zBlowSeq(n)>h And zBlowSeq(n)<=i Then zani(n)=7:zf(n)=9

;--------- movement ----------
	If zBlowSeq(n)>f And zBlowSeq(n)<g And zBlowStill(n)=0 And zHitHead(n)=0 Then moveX(n,zBlowdir(n),4.5):moveY(n,-5)
;--------- Sounds ------------
	If zBlowSeq(n)=e And gameSound Then PlaySound piccoloUpSpecialSnd
	If zBlowSeq(n)=f And gameSound Then PlaySound piccoloUpSpecialBlowSnd
;--------- hitbox ------------
	If zBlowSeq(n) > f And zBlowSeq(n) =< g Then
		zblowPamount(n)=5:nn=1
		xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=45:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=26:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=55:wblow(n,nn)=26:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=60:wblow(n,nn)=26:hblow(n,nn)=5:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=6:zBlowStillTime(n)=6
		zHitSpeed#(n)=4:zHitUpSpeed#(n)=3.8:zHitDownSpeed#(n)=0:zHitTime(n)=50
		zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=11:zBlowBlockTime(n)=40
		zBlowSound(n)=dbzKneeHitSnd
		zani(n)=7:zf(n)=6:zantiplat(n)=1
		If zBlowStill(n)=1 Then isMoveHit(n)=1
		If zBlowSeq(n) >= 27 And zBlowSeq(n) < 32 Then zHitUpSpeed#(n)=2
		If zBlowSeq(n) >= 32 Then zHitSpeed#(n)=3:zHitUpSpeed#(n)=2
	EndIf
	
	If zy(n) <= zBlowUpLimit(n)-15 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) = g And isMoveHit(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) = g And isMoveHit(n)=1 Then zBlowSeq(n)=seq1:isMoveHit(n)=0
	If zBlowSeq(n) >= seq1 And zBlowSeq(n) < endSeq Then performFollowUpKneeHit(n)
	
	If zBlowSeq(n) >= endSeq Then
		zani(n)=7:zf(n)=9:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	End If
	
	If zongnd(n)=1 And zBlowSeq(n) => i-2 Then 
		zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	End If
	
Case 6	;throwing iten
	a=3:b=6:c=9
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n)= b Then
		throwObj(n,zx(n),zy(n)-20,zFace(n))
		If gameSound Then PlaySound throwsnd
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7	; Seeking ki blast (special)
	a=3:b=15:c=45:d=48:e=51
	zNoMove(n)=1:zNoJump(n)=1:zjump(n)=0
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zongnd(n)=0 And canAirGlideUp(n)=0 Then zy(n)=zy(n)-2

;----------- Animation -------------
	If zBlowSeq(n)>0 And zBlowSeq(n)<=a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n)>a And zBlowSeq(n)<=b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n)>b And zBlowSeq(n)<=c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n)>c And zBlowSeq(n)<=d Then zani(n)=12:zf(n)=11
	If zBlowSeq(n)>d And zBlowSeq(n)<=e Then zani(n)=12:zf(n)=12

	If zBlowSeq(n)=a Then 
		extraObj(n,zx(n),5,zy(n),-20,zFace(n),123)
	End If
	If zBlowSeq(n)=b Then 
		y=zy(n)-zheight(n)+30
		If zFace(n)=2 Then x=zx(n)+30
		If zFace(n)=4 Then x=zx(n)-30
		makeshot(n,50,x,y,zface(n))
	End If
	
;---------- Sounds --------------
	If zBlowSeq(n)=b And gameSound Then PlaySound piccoloGrunt1Snd
	If zBlowSeq(n)=b+1 And gameSound Then PlaySound piccoloKiSnd
	
	If zBlowSeq(n)>e Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	

Case 8	;Dodging
	a=7:b=15:c=20:d=25:e=30:f=37
	zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) = a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),1)

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	; Kaikousen (down special) / BuukuKyaku
	a=3:b=6:c=9:d=12:e=15:f=18:g=21:h=24:i=27:j=39:k=42:l=45
	a2=100:a3=20000
	zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n)=1 And zOnGnd(n)=0 Then zBlowSeq(n)=a2
	If zBlowSeq(n) > a2 And zBlowSeq(n) < a3 Then doBuukuKyaku(n)
	If zBlowSeq(n) >= a3 Then doBuukuKyaku2(n)
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

	If zBlowSeq(n) > l And zBlowSeq(n) < a2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10	;High Punch
	seq1=4:seq2=seq1+2:seq3=seq2+2:seq4=seq3+10:seq5=seq4+3:seq6=seq5+3:seq7=seq6+300
	zNoMove(n)=1
	zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	
;------------ Animation ------------
	If zBlowSeq(n) > 0 And zBlowSeq(n) < seq1 Then zani(n)=14:zf(n)=1
	If zBlowSeq(n) > seq1 And zBlowSeq(n) < seq2 Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > seq2 And zBlowSeq(n) < seq3 Then zani(n)=14:zf(n)=3
	If zBlowSeq(n) > seq3 And zBlowSeq(n) < seq4 Then zani(n)=14:zf(n)=4
	If zBlowSeq(n) > seq4 And zBlowSeq(n) < seq5 Then zani(n)=14:zf(n)=5
	If zBlowSeq(n) > seq5 And zBlowSeq(n) < seq6 Then zani(n)=14:zf(n)=6
	If zBlowSeq(n) > seq6 And zBlowSeq(n) < seq7 Then zani(n)=14:zf(n)=7
	
;--------- Hit box ----------
	If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq4 Then
		zblowPamount(n)=5:nn=1
		xblow(n,nn)=25: yblow(n,nn)=60:wblow(n,nn)=10:hblow(n,nn)=10:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=50:wblow(n,nn)=10:hblow(n,nn)=10:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=45:wblow(n,nn)=10:hblow(n,nn)=10:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=40:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
		xblow(n,nn)=5: yblow(n,nn)=35:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
		zHitSpeed#(n)=4:zHitUpSpeed#(n)=5:zHitTime(n)=40
		zBlowDamage(n)=14:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowBlockTime(n)=40
		zBlowSound(n)=dbzHit1Snd
	End If

;------------ Sounds ----------------
	If zBlowSeq(n)=1 And gameSound Then PlaySound piccoloGrunt3Snd
	
	If zBlowSeq(n)=seq7 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
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

Case 15 ;Piccolo throw
	sonicSlashSeqStart=1000
	endSeq=2000
	zNoMove(n)=1:zNoJump(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	if zBlowSeq(n)=1 And isRunning(n) Then zSpeed(n)=0:zBlowSeq(n)=sonicSlashSeqStart
	
	If zBlowSeq(n) >= sonicSlashSeqStart Then
		doSonicSlash(n)
	Else
		;do throw
		zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	End If
	
	if zBlowSeq(n)=endSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 16 ;Taunt Key
	a=2:b=10:c=20:d=90
	a2=8:b2=18:c2=48:d2=58:e2=68
	zNoMove(n)=1:zNoJump(n)=1
	If zongnd(n)=0 And canAirGlideUp(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n)=1 Then zTauntSeed(n)=Rand(3)
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
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