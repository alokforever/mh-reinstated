Function pow(x, y)
	Return x^y
End Function

Function performWolverineHeal(n)
	a=1000:b=a+10:c=b+5:d=c+30
	endSeq=58
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then
		If zBlowSeq(n)=a Then
			zSuperMove(n)=1:zSuperMoveSeq(n)=0
			If gameSound Then PlaySound wolverineSuper1Snd
			If gameSound Then PlaySound wolverineSuper3Snd
			zHealAmount(n)=10:zHealInterval(n)=50:zHealTimes(n)=7
		End If
		If zBlowSeq(n) Mod 3 = 0 And zBlowSeq(n) Mod 4 = 0 Then 
			zani(n)=17:zf(n)=1
		Else
			zani(n)=17:zf(n)=2
		End If
	End If
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zani(n)=17:zf(n)=3
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then
		If zBlowSeq(n) Mod 10 = 0 And zOnGnd(n)=1 Then 
			extraObj(n,zx(n),-40,zy(n),2,2,89)
			extraObj(n,zx(n),-40,zy(n),2,4,89)
		End If
		If zBlowSeq(n) = c Then 
			healMode(n)=1
			If gameSound Then PlaySound wolverineShoutSnd
		End If
		If zBlowSeq(n) Mod 3 = 0 And zBlowSeq(n) Mod 4 = 0 Then
			zani(n)=17:zf(n)=8
		Else
			zani(n)=17:zf(n)=18
		End If
	End If
	If zBlowSeq(n) > d Then zBlowSeq(n)=endSeq
End Function

Function performBerserkerSlash2(n)
	a=500:b=a+3:c=b+20/wolvSpdFctr(n):d=c+10:e=d+12:f=e+5:g=f+15
	endSeq=400
	zNoMove(n)=1
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then
		zani(n)=10:zf(n)=16
		If zBlowSeq(n)=a And zOnGnd(n)=1 Then extraObj(n,zx(n),-40,zy(n),2,zblowdir(n),89)
	End If
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then
		zani(n)=10:zf(n)=16
		If zBlowSeq(n) = c-1 And gameSound Then PlaySound wolverineSlash3Snd	
	End If
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then
		If zBlowSeq(n) = c Then 
			zani(n)=21:zf(n)=2
			If zOnGnd(n)=1 Then extraObj(n,zx(n),-40,zy(n),2,zblowdir(n),89)
		End If
		If zBlowSeq(n) = c+2 Then zani(n)=21:zf(n)=3
		If zBlowSeq(n) = c+4 Then zani(n)=21:zf(n)=4
		If zBlowSeq(n) = c+6 Then 
			zani(n)=21:zf(n)=5
			If zOnGnd(n)=1 Then extraObj(n,zx(n),-40,zy(n),2,zblowdir(n),89)
		End If
		If zBlowSeq(n) = c+8 Then zani(n)=21:zf(n)=6
		movex2(n,zface(n),5)
	End If
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then
		zani(n)=10:zf(n)=16
		movex2(n,zface(n),4)
		If zBlowSeq(n) Mod 3 = 0 Then extraObj(n,zx(n),0,zy(n),0,zBlowDir(n),102)
		If zBlowSeq(n) = e-1 And gameSound Then PlaySound wolverineShoutSnd
	End If
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then
		If zBlowSeq(n) = e Then extraObj(n,zx(n),40,zy(n),50,zblowdir(n),88)
		zani(n)=17:zf(n)=11
				
		zblowPamount(n)=4:nn=1
		For counter = 0 To 30
			xblow(n,nn)=0: yblow(n,nn)=counter:wblow(n,nn)=80:hblow(n,nn)=10:nn=nn+1
			counter = counter + 10
		Next

		zHitmode(n)=0:zBlowHold(n)=0:zBlowSound(n)=slashSnd
		zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=15	
	End If
	If zBlowSeq(n) > f And zBlowSeq(n) < g Then zani(n)=17:zf(n)=11
	
	If zBlowSeq(n) > g Then zBlowSeq(n)=endSeq
End Function

Function performGlidingKick(n)
	a=200:b=a+3:c=b+3:d=c+3:e=d+3
	f=1000000:g=f+10
	endSeq=190
	If zBlowSeq(n) >= a And zOnGnd(n)=0 Then zNoGrav(n)=1
	If zOnGnd(n)=1 Then zBlowSeq(n)=endSeq
	
	If zBlowSeq(n) = a And gameSound Then PlaySound wolverineShout3Snd
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zani(n)=8:zf(n)=14
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zani(n)=8:zf(n)=15
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zani(n)=8:zf(n)=16
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=8:zf(n)=17
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then
		If zBlowSeq(n) Mod 3 = 0 And zBlowSeq(n) Mod 4 = 0 Then
			zani(n)=8:zf(n)=18
		Else
			zani(n)=8:zf(n)=19
		End If
		zblowpamount(n)=2
		nn=1
		xblow(n,nn)=10: yblow(n,nn)=0:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+5
		xblow(n,nn)=12: yblow(n,nn)=5:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+5
		zHitMode(n)=0:zBlowHold(n)=0
		zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
		zBlowSound(n)=wolverineKickSnd
		
		If zBlowStill(n)=0 Then
			movex2(n,zface(n),1.5)
			moveY(n,5)
		Else
			zBlowSeq(n)=f
		End If
	End If
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then
		zNoMove(n)=1
		movex2(n,zface(n),-5)
		moveY(n,-3)
		zani(n)=4:zf(n)=1
		If zBlowStill(n)=1 Then zani(n)=8:zf(n)=19
	End If
	If zBlowSeq(n) = g Then
		zBlowSeq(n) = endSeq
	End If
End Function

Function handleWolverineCooldown(n, blowSeq, cooldownType)
	If zBlowSeq(n)=blowSeq And spellCooldownSeq(n, cooldownType) > 0 Then
		cdSeed=Rand(2)
		If cdSeed=1 And gameSound And zAI(n)=0 Then 
			PlaySound wolverineSpellCooldown1Snd
		Else If cdSeed=2 And gameSound And zAI(n)=0 Then
			PlaySound wolverineSpellCooldown2Snd
		End If
		If gameSound Then PlaySound clockTickSnd
		zBlowSeq(n)=0:zBlow(n)=0
	End If
End Function

Function applyWolverineComboHitBox(n, hitMode, damage, xPos, yPos, xWidth, yWidth, blowSound)
	zblowPamount(n)=1:nn=1
	xblow(n,nn)=xPos:yblow(n,nn)=yPos:wblow(n,nn)=xWidth:hblow(n,nn)=yWidth:nn=nn+1
	zHitMode(n)=hitMode:zBlowHold(n)=10
	zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitTime(n)=0
	zBlowDamage(n)=damage:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16
	zBlowStillTime(n)=0:zBlowBlockTime(n)=25
	If blowSound=0 Then 
		zBlowSound(n)=slashsnd
	Else If blowSound=1 Then
		zBlowSound(n)=wolverineKickSnd
	End If
End Function

Function performWolverineCombo(n)
	a=64:b=a+4:c=b+11:d=c+4:e=d+5:f=e+5:g=f+4:h=g+4:i=h+11:j=i+3:k=j+3:l=k+4:m=l+4
	n1=m+3:o=n1+3:p=o+3:q=p+3
	endSeq=58
	depleteStaminaBar(n, 1.5)
	If zBlowSeq(n)>=60 And zBlowSeq(n) < c Then movex2(n,zface(n),1+(Abs(zSpeed#(n))/1.5))
	If zBlowSeq(n) = 1 Then canPerformNextCombo(n)=0
;--------------------- animations -----------------------
	If zBlowSeq(n) >= 60 And zBlowSeq(n) < a Then zani(n)=22:zf(n)=3
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zani(n)=22:zf(n)=2
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then 
		zani(n)=22:zf(n)=1
		If zBlowSeq(n) > b+3 And KeyDown(specialK(n))=1 Then zBlowSeq(n)=c
		If zBlowSeq(n) = c-1 And KeyDown(specialK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zani(n)=22:zf(n)=4
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=22:zf(n)=5
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then 
		zani(n)=22:zf(n)=6
		If zBlowSeq(n) > e+3 And KeyDown(blockK(n))=1 Then zBlowSeq(n)=f
		If zBlowSeq(n) = f-1 And KeyDown(blockK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=22:zf(n)=7
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=22:zf(n)=8
	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then 
		zani(n)=22:zf(n)=9
		If zBlowSeq(n) > h+3 And KeyDown(grabK(n))=1 Then canPerformNextCombo(n)=1
		If zBlowSeq(n) = i-1 And canPerformNextCombo(n)=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n) >= i And zBlowSeq(n) < j Then 
		zani(n)=22:zf(n)=10
		If zBlowSeq(n) = i Then canPerformNextCombo(n)=0
	End If
	If zBlowSeq(n) >= j And zBlowSeq(n) < k Then zani(n)=22:zf(n)=11
	If zBlowSeq(n) >= k And zBlowSeq(n) < l Then zani(n)=22:zf(n)=12
	If zBlowSeq(n) >= l And zBlowSeq(n) < m Then zani(n)=22:zf(n)=13
	If zBlowSeq(n) >= m And zBlowSeq(n) < n1 Then zani(n)=22:zf(n)=14
	If zBlowSeq(n) >= n1 And zBlowSeq(n) < o Then zani(n)=22:zf(n)=15
	If zBlowSeq(n) >= o And zBlowSeq(n) < p Then zani(n)=22:zf(n)=16
	If zBlowSeq(n) = p And canPerformNextCombo(n)=1 Then zBlowSeq(n)=200
	If zBlowSeq(n) > 200 Then performBerserkerSlash(n, 58)	
		
	If zBlowSeq(n) > h+3 And KeyDown(grabK(n))=1 Then canPerformNextCombo(n)=1
	If zBlowSeq(n) > j+3 And KeyDown(shotK(n))=1 Then canPerformNextCombo(n)=1
	If zBlowSeq(n) = p-1 And KeyDown(shotK(n))=0 Then zBlowSeq(n)=endSeq
;--------------------------------------------------------
	
;------------------------ sounds ------------------------
	If zBlowSeq(n) = 60 And gameSound Then PlaySound wolverineSlashSnd
;--------------------------------------------------------
	isHitting=0
;----------------------- hitboxes -----------------------
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then
		applyWolverineComboHitBox(n, 2, 6, 14, 50, 20, 25, 0)
		If zOnGnd(zControlsThis(n))=0 Then 
			If zheight(zControlsThis(n)) <= 40 Then
				zy(zControlsThis(n))=zy(n)-20
			Else
				zy(zControlsThis(n))=zy(n)
			End If
		End If
		movex2(zControlsThis(n),zface(zControlsThis(n)),-1*(1+(Abs(zSpeed#(n))/1.5)))
		isHitting=1
	End If
	;If zBlowSeq(n)=b And zControls(n)=0 Then zBlowSeq(n)=endSeq
	If zBlowSeq(n) >= d And zBlowSeq(n) < f Then 
		If zBlowSeq(n) = d And gameSound Then PlaySound wolverineShout4Snd
		applyWolverineComboHitBox(n, 2, 6, 0, 43, 36, 46, 1)
		isHitting=1
	End If
	;If zBlowSeq(n)=d And zControls(n)=0 Then zBlowSeq(n)=endSeq
	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then 
		applyWolverineComboHitBox(n, 2, 6, 0, 15, 36, 16, 1)
		isHitting=1
	End If
	If zBlowSeq(n) >= k And zBlowSeq(n) < m Then
		If zBlowSeq(n) = k And gameSound Then PlaySound wolverineShout3Snd
		If zBlowSeq(n) = k And gameSound Then PlaySound wolverineSlashSnd
		applyWolverineComboHitBox(n, 2, 6, 0, 43, 37, 27, 0)
		isHitting=1
	End If
;--------------------------------------------------------
;---------------- target manipulation -------------------
	guardable=0
	If zBlowSeq(n) >= 60 And zBlowSeq(n) < c Then enemyControlInit(n,zx(n)+7,zy(n)-50,40,50,0,guardable)
	If zBlowSeq(n) >= c And zBlowSeq(n) < f Then enemyControlInit(n,zx(n),zy(n)-43,36,92,0,guardable)
	If zBlowSeq(n) >= f And zBlowSeq(n) < i Then enemyControlInit(n,zx(n),zy(n)-15,36,32,0,guardable)
	If zBlowSeq(n) >= j And zBlowSeq(n) < p Then enemyControlInit(n,zx(n),zy(n)-43,37,54,0,guardable)
	If zBlowSeq(n) >= 200 And zBlowSeq(n) < 214 Then enemyControlInit(n,zx(n),zy(n)-43,37,57,0,guardable)
	
	If (zBlowSeq(n)=b+2 Or zBlowSeq(n)=e+2 Or zBlowSeq(n)=h+2 Or zBlowSeq(n)=j+2 Or zBlowSeq(n)=202) And zControls(n)=0 Then zBlowSeq(n)=endSeq

	unitCounter=1
	While zControlsThese(n,unitCounter) <> 0
		en=zControlsThese(n,unitCounter)
		If zBlowSeq(en)=0 And zCurBlow(en)=0 Then zNoGrav(en)=1:zantiPlat(en)=1
	
		If isHitting=1 Then
			If zParalyzed(en)=1 Then zani(en)=2:zf(en)=3
		Else
			If zParalyzed(en)=1 Then zani(en)=2:zf(en)=1
		End If
		unitCounter=unitCounter+1
	Wend
	
	If zBlowSeq(n) > p And zBlowSeq(n) < 200 Then zControls(n)=0:zBlowSeq(n)=endSeq

End Function

Function performSuperSpecial2(n)
	a=1100+2:b=a+2:c=b+2:d=c+144:e=d+1:f=e+1:g=f+1
	h=g+1:i=h+1:j=i+1:k=j+1:l=k+20
	
	If zBlowSeq(n)=1100 Then
		zani(n)=9:zf(n)=7
		If gameSound Then PlaySound wolverineSuper2Snd
	End If
	If zBlowSeq(n)=b Then
		zSuperMove(n)=1:zSuperMoveSeq(n)=0
		If gameSound Then PlaySound wolverineSuper1Snd
	EndIf
	If zBlowSeq(n) >= c And zBlowSeq(n) < d
		If zBlowSeq(n) = c And gameSound Then PlaySound wolverineLetsGoSnd
		zani(n)=17
		moveX(n,zBlowDir(n),Float(0.5))
		If zBlowSeq(n) Mod 6 = 0 Then
			extraObj(n,zx(n),-70,zy(n),2,zblowdir(n),89)
			extraObj(n,zx(n),-60,zy(n),-10,zblowdir(n),90)
			extraObj(n,zx(n),34,zy(n),-2,zblowdir(n),91)
			extraObj(n,zx(n),34,zy(n),-2,zblowdir(n),92)
		End If
		If zBlowSeq(n) Mod 12 = 0 Then zf(n)=12:Goto confirmFrame
		If zBlowSeq(n) Mod 10 = 0 Then zf(n)=13:Goto confirmFrame
		If zBlowSeq(n) Mod 8 = 0 Then zf(n)=14:Goto confirmFrame
		If zBlowSeq(n) Mod 6 = 0 Then zf(n)=15:Goto confirmFrame
		If zBlowSeq(n) Mod 4 = 0 Then zf(n)=16:Goto confirmFrame
		If zBlowSeq(n) Mod 2 = 0 Then zf(n)=17
		.confirmFrame
		If zBlowSeq(n) Mod 5 = 0 Then
			If gameSound Then PlaySound wolverineSlashSnd
		End If
		
		zblowPamount(n)=5:nn=1
		For counter = -10 To 30 Step 10
			xblow(n,nn)=-30: yblow(n,nn)=counter:wblow(n,nn)=110:hblow(n,nn)=10:nn=nn+1
			counter = counter + 10
		Next
		zHitmode(n)=2:zBlowHold(n)=3:zBlowSound(n)=slashSnd
		zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=4:zBlowStillTime(n)=0:zBlowBlockTime(n)=15
		zHitSpeed#(n)=1.1:zHitUpSpeed#(n)=2:zHitTime(n)=5
	EndIf
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then 
		zani(n)=10:zf(n)=2
		If zBlowSeq(n) = e And gameSound Then PlaySound wolverineShoutSnd
	End If
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then 
		zani(n)=10:zf(n)=3
		If zBlowSeq(n) = f And gameSound Then PlaySound wolverineSlashSnd
	End If
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then zani(n)=10:zf(n)=5
	If zBlowSeq(n) >= i And zBlowSeq(n) < j Then zani(n)=10:zf(n)=6
	If zBlowSeq(n) >= j And zBlowSeq(n) < k Then zani(n)=10:zf(n)=7
	If zBlowSeq(n) >= k And zBlowSeq(n) < l Then
		zani(n)=17:zf(n)=11
		If zBlowSeq(n) = k Then extraObj(n,zx(n),40,zy(n),50,zblowdir(n),88)
				
		zblowPamount(n)=4:nn=1
		For counter = 0 To 30
			xblow(n,nn)=0: yblow(n,nn)=counter:wblow(n,nn)=80:hblow(n,nn)=10:nn=nn+1
			counter = counter + 10
		Next

		zHitmode(n)=0:zBlowHold(n)=0:zBlowSound(n)=slashSnd
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=15
	End If
	If zBlowSeq(n) = l Then 
		zNoGrav(n)=0:ztopSpeed(n)=2:zNomove(n)=0
		zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	End If	
End Function

Function performSlideKick(n)
	a=102:b=a+2:c=b+2:d=c+20:e=d+4:f=e+4:g=f+4:h=g+4:i=h+4
	If zBlowSeq(n)=a Then handleWolverineCooldown(n, a, 2)
	If zBlowSeq(n) >=a And zBlowSeq(n) < b Then zani(n)=9:zf(n)=7
	If zBlowSeq(n) >=b And zBlowSeq(n) < c Then zani(n)=9:zf(n)=9
	If zBlowSeq(n) >=c And zBlowSeq(n) < d Then 
		If zBlowSeq(n)=c Then
			spellCooldownMaxTime(n, 2)=130
			spellCooldownSeq(n, 2)=spellCooldownMaxTime(n, 2) 
		End If
		If zBlowSeq(n) = c And gameSound Then PlaySound floorSlideSnd
		If zBlowSeq(n) Mod 2 = 0 Then zani(n)=9:zf(n)=10
		If zBlowSeq(n) Mod 2 = 1 Then zani(n)=9:zf(n)=11
		zblowPamount(n)=1:nn=1
		xblow(n,nn)=20:yblow(n,nn)=10:wblow(n,nn)=33:hblow(n,nn)=10
		
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=9:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
		moveX(n,zBlowDir(n),3.5)
	End If
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=9:zf(n)=12
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zani(n)=9:zf(n)=13
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=9:zf(n)=14
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=9:zf(n)=15
	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then zani(n)=9:zf(n)=16
	If zBlowSeq(n) = i Then zBlowSeq(n) = 95
	
End Function

Function performBerserkerSlash(n, endSeq)
	a=200:b=a+2:c=b+3:d=c+2:e=d+3:f=e+2:g=f+7:h=g+37
	
	If zBlowSeq(n)=200 Then handleWolverineCooldown(n, zBlowSeq(n), 1)
;---------- animations -------------
	If zBlowSeq(n) >= 70 And zBlowSeq(n) < a Then zani(n)=10:zf(n)=9
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=10:moveX(n,zBlowDir(n),1+Abs(zCurSpeed#(n))):moveY(n,-6)
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=11:moveX(n,zBlowDir(n),0.8+Abs(zCurSpeed#(n))):moveY(n,-7)
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=12:moveX(n,zBlowDir(n),0.6+Abs(zCurSpeed#(n))):moveY(n,-7)
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=13:moveX(n,zBlowDir(n),0.4+Abs(zCurSpeed#(n))):moveY(n,-6)
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then 
		moveX(n,zBlowDir(n),0.3+Abs(zCurSpeed#(n)))
		zani(n)=10:zf(n)=14
		If zBlowSeq(n) = e And gameSound Then PlaySound wolverineShoutSnd
	End If
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=10:zf(n)=15:moveX(n,zBlowDir(n),0.2+Abs(zCurSpeed#(n)))
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=10:zf(n)=16
	If zBlowSeq(n) = f Then extraObj(n,zx(n),20,zy(n),17,zblowdir(n),97)
	
;---------- hitboxes ---------------
	If zBlowSeq(n) >= f And zBlowSeq(n) < g+15 Then
		If zBlowSeq(n) = f Then
			spellCooldownMaxTime(n, 1)=130
			spellCooldownSeq(n, 1)=spellCooldownMaxTime(n, 1) 
		End If
		zblowPamount(n)=6:nn=1
		xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=0
		zHitSpeed#(n)=1:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
		zBlowDamage(n)=7+(Abs(zCurSpeed(n))*2):zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
	End If
	If zBlowSeq(n) > g And zOnGnd(n)=0 Then zy(n)=zy(n)-2
	
	If zBlowSeq(n) = h Then zCurSpeed(n)=0:zBlowSeq(n) = endSeq
End Function

Function DoWolverine(n)

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
	If zblocked(n)=1 Then zani(n)=13:zf(n)=2
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1	;High Claw
	a=5/wolvSpdFctr(n):b=10/wolvSpdFctr(n):c=15/wolvSpdFctr(n):d=22/wolvSpdFctr(n):e=27/wolvSpdFctr(n)
	:f=32/wolvSpdFctr(n):g=36/wolvSpdFctr(n):h=40/wolvSpdFctr(n):i=55/wolvSpdFctr(n):j=60
	zNoMove(n)=1
	zNoJump(n)=1
	If zBlowSeq(n) = 1 And isRunning(n) Then zBlowSeq(n)=j:isRunning(n)=0
	If zBlowSeq(n) >= j Then performWolverineCombo(n)
	If zBlowSeq(n) = c Then If gameSound Then PlaySound wolverineShout2Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=12:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) < c Then 
		zblowPamount(n)=3
		nn=1
		xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=44:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=44:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=2:zBlowHold(n)=30/wolvSpdFctr(n)
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20/wolvSpdFctr(n):zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
		If zBlowSeq(n) = a Then zani(n)=12:zf(n)=2
		If zBlowSeq(n) = b Then zani(n)=12:zf(n)=3
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=12:zf(n)=4
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=12:zf(n)=5
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=12:zf(n)=6
	If zBlowSeq(n) = f-1 And gameSound Then PlaySound wolverineShout2Snd
	If zBlowSeq(n) => f And zBlowSeq(n) < h Then 
		zblowPamount(n)=3
		nn=1
		xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=52:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=46:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=38:hblow(n,nn)=5:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
		If zBlowSeq(n) = f Then zani(n)=12:zf(n)=7
		If zBlowSeq(n) = g Then zani(n)=12:zf(n)=8
	EndIf
	
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=12:zf(n)=9
	If zBlowSeq(n) => i And zBlowSeq(n) < j Then zBlowSeq(n)=0:zBlow(n)=0

Case 2	;Flying Kick
	a=5/wolvSpdFctr(n):b=10/wolvSpdFctr(n):c=22/wolvSpdFctr(n):d=25/wolvSpdFctr(n):e=30/wolvSpdFctr(n):
	f=40/wolvSpdFctr(n):a2=4/wolvSpdFctr(n):b2=8/wolvSpdFctr(n):c2=12/wolvSpdFctr(n):d2=16/wolvSpdFctr(n)
	:e2=20/wolvSpdFctr(n):f2=24/wolvSpdFctr(n):g2=28/wolvSpdFctr(n):h2=32/wolvSpdFctr(n):i2=36/wolvSpdFctr(n)
	:j2=42/wolvSpdFctr(n)
	aa=200
	zNoJump(n)=0

	If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 Then zBlowSeq(n)=aa:downKeyDoubleTap(n)=0
	If zBlowSeq(n) >= aa Then performGlidingKick(n)
	
	If zBlowSeq(n) >= 1 And downKey(n) = 0  Then
		If zBlowSeq(n) => 2 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
		If zBlowSeq(n) = a Then If gameSound Then PlaySound wolverineShout3Snd
		If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
		If zBlowSeq(n) > b And zBlowSeq(n) =< d Then
			zblowpamount(n)=4
			nn=1
			xblow(n,nn)=25: yblow(n,nn)=0:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
			xblow(n,nn)=15: yblow(n,nn)=10:wblow(n,nn)=22:hblow(n,nn)=1:nn=nn+1
			xblow(n,nn)=8: yblow(n,nn)=20:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
			xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
			zHitMode(n)=0:zBlowHold(n)=8
			zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
			zBlowSound(n)=wolverineKickSnd
			If zBlowSeq(n) = b Then zani(n)=8:zf(n)=3
			If zBlowSeq(n) = c Then zani(n)=8:zf(n)=4
		EndIf
		If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=8:zf(n)=2
		If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=8:zf(n)=1
	
		If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
		If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0
	Else
		If zBlowSeq(n) => 1 And zBlowSeq(n) =< a2 Then zani(n)=8:zf(n)=5
		If zBlowSeq(n) = a2 Then If gameSound Then PlaySound wolverineShout3Snd
		If zBlowSeq(n) > a2 And zBlowSeq(n) =< b2 Then zani(n)=8:zf(n)=6
		If zBlowSeq(n) > b2 And zBlowSeq(n) =< c2 Then zani(n)=8:zf(n)=7
		If zBlowSeq(n) > c2 And zBlowSeq(n) =< f2 Then
			zblowpamount(n)=4
			nn=1
			xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=37:hblow(n,nn)=1:nn=nn+5
			xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=37:hblow(n,nn)=1:nn=nn+5
			xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=37:hblow(n,nn)=1:nn=nn+5
			xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=37:hblow(n,nn)=1:nn=nn+5
			zHitMode(n)=0:zBlowHold(n)=20
			zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
			zBlowSound(n)=slashSnd
			If zBlowSeq(n) = c2 Then zani(n)=8:zf(n)=8
			If zBlowSeq(n) = d2 Then zani(n)=8:zf(n)=9
			If zBlowSeq(n) = e2 Then zani(n)=8:zf(n)=10
		EndIf
		If zBlowSeq(n) > f2 And zBlowSeq(n) =< g2 Then zani(n)=8:zf(n)=11
		If zBlowSeq(n) > g2 And zBlowSeq(n) =< h2 Then zani(n)=8:zf(n)=12
		If zBlowSeq(n) > h2 And zBlowSeq(n) =< i2 Then zani(n)=8:zf(n)=13
	
		If zBlowSeq(n) > e And zBlowSeq(n) < aa Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
		If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0
	EndIf
Case 4	;Low kick
	zNoMove(n)=1:zNoJump(n)=1
	zheight(n)=zduckheight(n)
	a=7/wolvSpdFctr(n):b=14/wolvSpdFctr(n):c=21/wolvSpdFctr(n):d=28/wolvSpdFctr(n):e=35/wolvSpdFctr(n)
	:f=40/wolvSpdFctr(n):g=45/wolvSpdFctr(n):h=100
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) = 1 Then
		If zBlowDir(n)=2 And rightKey(n) Then zBlowSeq(n)=h
		If zBlowDir(n)=4 And leftKey(n) Then zBlowSeq(n)=h
	End If
	If zBlowSeq(n) >= h Then performSlideKick(n)
	If zblowseq(n) < c Then zheight(n)=zduckheight(n)
	If zBlowSeq(n)= a Then If gameSound Then PlaySound wolverineShout3Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
		zblowPamount(n)=3
		nn=1
		xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=53:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=53:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=wolverineKickSnd
		zani(n)=9:zf(n)=3
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=4
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=9:zf(n)=5
	If zBlowSeq(n) => e And zBlowSeq(n) =< f Then zani(n)=9:zf(n)=6
	If zBlowSeq(n) => f And zBlowSeq(n) =< g Then zani(n)=9:zf(n)=7
	
	If zBlowSeq(n) > g And zBlowSeq(n) < h Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5	;Uppercut (Tornado claw)
	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	a=5/wolvSpdFctr(n):b=10/wolvSpdFctr(n):c=16/wolvSpdFctr(n):d=28/wolvSpdFctr(n):e=30/wolvSpdFctr(n)
	:f=32/wolvSpdFctr(n):g=34/wolvSpdFctr(n):h=36/wolvSpdFctr(n):i=41/wolvSpdFctr(n):j=43/wolvSpdFctr(n) 
	:k=45/wolvSpdFctr(n) :l=47/wolvSpdFctr(n) :m=52/wolvSpdFctr(n) :nnn=54/wolvSpdFctr(n) :o=56/wolvSpdFctr(n) 
	:p=58/wolvSpdFctr(n) :q=63/wolvSpdFctr(n) :r=75/wolvSpdFctr(n)
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) = 1 Then
		If gameSound Then PlaySound wolverineTornadoClawSnd
		zBlowUpLimit(n)=zy(n)-70:zJump(n)=0
	EndIf
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=2:moveX(n,zBlowdir(n),1)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=28:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=37:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=45:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=1:zHitUpSpeed#(n)=3.5:zHitTime(n)=20
		If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2*pow(wolvSpdFctr(n),1))
		zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=1:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
		zani(n)=7:zf(n)=3:zantiplat(n)=1
	EndIf
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
		zblowPamount(n)=8:nn=1
		zblowback(n)=1
		For counter = 5 To 75 Step 10
			xblow(n,nn)=-20: yblow(n,nn)=counter:wblow(n,nn)=55:hblow(n,nn)=1:nn=nn+1
		Next
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=45
		If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2*pow(wolvSpdFctr(n),1.5))
		zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=1:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
		zani(n)=7:zf(n)=4:zantiplat(n)=1
		If zBlowSeq(n) = d And gameSound Then PlaySound wolverineSlashSnd
	EndIf
	If zBlowSeq(n) > d And zBlowSeq(n) =< r Then
		zNoMove(n)=0
		ztopSpeed(n)=.5
		If upkey(n)=0 Then zBlowSeq(n) = r
		zblowPamount(n)=8:nn=1
		zblowback(n)=1
		For counter = 5 To 75 Step 10
			xblow(n,nn)=-30: yblow(n,nn)=counter:wblow(n,nn)=65:hblow(n,nn)=1:nn=nn+1
		Next

		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=45
		zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=18:zBlowStillTime(n)=2:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
		If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=7:zf(n)=5:zantiplat(n)=1
		If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=7:zf(n)=6:zantiplat(n)=1
		If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=7:zf(n)=7:zantiplat(n)=1
		If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=7:zf(n)=8:zantiplat(n)=1:moveY(n,-5*pow(wolvSpdFctr(n),2)):moveX(n,zBlowdir(n),1)
		If zBlowSeq(n) > h And zBlowSeq(n) =< i Then zani(n)=7:zf(n)=9:zantiplat(n)=1
		If zBlowSeq(n) > i And zBlowSeq(n) =< j Then zani(n)=7:zf(n)=2:zantiplat(n)=1
		If zBlowSeq(n) > j And zBlowSeq(n) =< k Then zani(n)=7:zf(n)=3:zantiplat(n)=1
		If zBlowSeq(n) > k And zBlowSeq(n) =< l Then zani(n)=7:zf(n)=4:zantiplat(n)=1:moveY(n,-5.5*pow(wolvSpdFctr(n),2)):moveX(n,zBlowdir(n),1.5)
		If zBlowSeq(n) > l And zBlowSeq(n) =< m Then zani(n)=7:zf(n)=5:zantiplat(n)=1
		If zBlowSeq(n) > m And zBlowSeq(n) =< nnn Then zani(n)=7:zf(n)=6:zantiplat(n)=1
		If zBlowSeq(n) > nnn And zBlowSeq(n) =< o Then zani(n)=7:zf(n)=7:zantiplat(n)=1
		If zBlowSeq(n) > o And zBlowSeq(n) =< p Then zani(n)=7:zf(n)=8:zantiplat(n)=1:moveY(n,-6*pow(wolvSpdFctr(n),2)):moveX(n,zBlowdir(n),2)
		If (zBlowSeq(n) = h Or zBlowSeq(n) = l Or zBlowSeq(n) = p) And gameSound Then PlaySound wolverineSlashSnd
	EndIf
	If zy(n) <= zBlowUpLimit(n)-15 Or zHitHead(n)=1 Then zBlowSeq(n)=r
	If zBlowSeq(n) => r Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	If zongnd(n)=1 And zBlowSeq(n) => r-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

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

Case 7	;berserker slash (special)
	a=7/wolvSpdFctr(n):b=14/wolvSpdFctr(n):c=21/wolvSpdFctr(n):d=28/wolvSpdFctr(n):e=35/wolvSpdFctr(n)
	:f=42/wolvSpdFctr(n):g=49/wolvSpdFctr(n):h=64/wolvSpdFctr(n):
	i=200
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	If isRunning(n) And zBlowSeq(n)=1 Then zBlowSeq(n)=i:isRunning(n)=0:zCurSpeed#(n)=zSpeed#(n)
	If zBlowSeq(n) >= i Then performBerserkerSlash(n, 68)
	If zongnd(n)=0 And zBlowSeq(n) < i Then zy(n)=zy(n)-2
	If zBlowSeq(n) = c And gameSound=1 Then PlaySound wolverineShoutSnd
	If zBlowSeq(n) = d And gameSound=1 Then PlaySound wolverineSlash3Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) =< f Then 
		If zBlowSeq(n) = c+1 Then extraObj(n,zx(n),20,zy(n),8,zblowdir(n),81)
		zblowPamount(n)=6:nn=1
		xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=0
		zHitSpeed#(n)=1:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
		zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
	EndIf
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=5
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=10:zf(n)=6
	If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=10:zf(n)=7
	If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=10:zf(n)=8

	If zBlowSeq(n) > h And zBlowSeq(n) < i Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0

Case 8	;Dodging
	zheight(n)=zduckHeight(n)
	zNoMove(n)=1
	zNoJump(n)=1
	a=7/wolvSpdFctr(n):b=15/wolvSpdFctr(n):c=20/wolvSpdFctr(n):d=25/wolvSpdFctr(n):e=30/wolvSpdFctr(n)
	:f=37/wolvSpdFctr(n)
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2*wolvSpdFctr(n))
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3*wolvSpdFctr(n))
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3*wolvSpdFctr(n))
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2*wolvSpdFctr(n))
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),1*wolvSpdFctr(n))

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	; berserker barrage (down special)
	zNoMove(n)=0
	ztopspeed(n)=.5
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	aa=11/wolvSpdFctr(n):a=21/wolvSpdFctr(n):b=24/wolvSpdFctr(n):c=31/wolvSpdFctr(n):d=34/wolvSpdFctr(n)
	:e=30/wolvSpdFctr(n):f=39/wolvSpdFctr(n):g=42/wolvSpdFctr(n):h=49/wolvSpdFctr(n):i=52/wolvSpdFctr(n)
	:j=(i+20):k=500
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 Then zBlowSeq(n)=k:downKeyDoubleTap(n)=0
	If zBlowSeq(n) >= k Then performBerserkerSlash2(n)
	If isRunning(n) And zBlowSeq(n)=1 Then 
		If Abs(zSpeed#(n)) >= 3 And zBlowSeq(n) < 4 Then zBlowSeq(n)=10
		If Abs(zSpeed#(n)) >= 4 Then zBlowSeq(n)=aa
		isRunning(n)=0
	End If
	If zBlowSeq(n) = b Then
		If gameSound Then PlaySound wolverineBarrageSnd
		zJump(n)=0
	EndIf
	If zHitHead(n)=1 Then zBlowSeq(n)=i:zy(n)=zy(n)+4
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< aa Then zani(n)=3:zf(n)=1
	If zBlowSeq(n) => aa And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),(1*wolvSpdFctr(n)))	
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),(1*wolvSpdFctr(n)))
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=3:moveX(n,zBlowdir(n),(1.5*wolvSpdFctr(n)))
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=4:moveX(n,zBlowdir(n),(1.5*wolvSpdFctr(n)))
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=5:moveX(n,zBlowdir(n),(2*wolvSpdFctr(n)))
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=12:zf(n)=6:moveX(n,zBlowdir(n),(2*wolvSpdFctr(n)))
	If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=12:zf(n)=7:moveX(n,zBlowdir(n),(1.5*wolvSpdFctr(n)))
	If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=12:zf(n)=8:moveX(n,zBlowdir(n),(1.5*wolvSpdFctr(n)))
	If zBlowSeq(n) > h And zBlowSeq(n) =< i Then zani(n)=12:zf(n)=9:moveX(n,zBlowdir(n),(1.5*wolvSpdFctr(n)))
	If zBlowSeq(n) > i And zBlowSeq(n) =< j Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),(1*wolvSpdFctr(n)))	
	If zBlowSeq(n) > j And zBlowSeq(n) =< k Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),(1*wolvSpdFctr(n)))
	If zBlowSeq(n) > k And zBlowSeq(n) =< l Then zani(n)=12:zf(n)=3:moveX(n,zBlowdir(n),(1.5*wolvSpdFctr(n)))

	If (zBlowSeq(n) => a And zBlowSeq(n) < c) Or (zBlowSeq(n) => g And zBlowSeq(n) < i) Then 
		If (zBlowSeq(n) = b Or zBlowSeq(n) = g) And gameSound Then PlaySound wolverineSlashSnd
	EndIf
	
	If zBlowSeq(n) = a Then
		extraObj(n,zx(n),30,zy(n),16,zblowdir(n),77)
		extraObj(n,zx(n),64,zy(n),-17,zblowdir(n),78)
	EndIf
	If zBlowSeq(n) = g Then
		extraObj(n,zx(n),40,zy(n),8,zblowdir(n),79)
		extraObj(n,zx(n),-17,zy(n),-22,zblowdir(n),80)
	EndIf

	If zBlowSeq(n) => a And zBlowSeq(n) =< c Then
		zblowPamount(n)=3
		xblow(n,1)=0: yblow(n,1)=5:wblow(n,1)=65:hblow(n,1)=15
		xblow(n,2)=10: yblow(n,2)=30:wblow(n,2)=65:hblow(n,2)=15
		xblow(n,3)=20: yblow(n,3)=55:wblow(n,3)=65:hblow(n,3)=20
		zHitmode(n)=2:zBlowHold(n)=0
		zHitSpeed#(n)=2:zHitUpSpeed#(n)=2:zHitTime(n)=35
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=30/wolvSpdFctr(n):zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=slashSnd
	EndIf
	If zBlowSeq(n) > g And zBlowSeq(n) =< i Then
		zblowPamount(n)=6:nn=1
		zblowback(n)=1
		For counter = 5 To 55
			xblow(n,nn)=-40: yblow(n,1)=counter:wblow(n,nn)=90:hblow(n,nn)=20:nn=nn+1
			counter = counter + 10
		Next

		zHitmode(n)=0:zBlowHold(n)=8
		zHitSpeed#(n)=6.5:zHitUpSpeed#(n)=2:zHitTime(n)=45
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=40
		zBlowSound(n)=slashSnd
	EndIf
	If zBlowSeq(n) > i And zBlowSeq(n) < j Then zani(n)=12:zf(n)=7:zNograv(n)=0
	
	If zBlowSeq(n) => i And zBlowSeq(n) < k Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5
	If zBlowSeq(n) >= j And zBlowSeq(n) < k Then zBlow(n)=0:zblowstill(n)=0
	If zongnd(n)=1 And zBlowSeq(n) >= j-2 And zBlowSeq(n) < k Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10	;High Kick 
	zNoMove(n)=1
	zNoJump(n)=1
	a=6/wolvSpdFctr(n):b=12/wolvSpdFctr(n):c=18/wolvSpdFctr(n):d=22/wolvSpdFctr(n):e=25/wolvSpdFctr(n)
	:f=28/wolvSpdFctr(n):g=36/wolvSpdFctr(n):h=44/wolvSpdFctr(n)
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
	If zBlowSeq(n)= a Then If gameSound Then PlaySound wolverineShout3Snd
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
		zblowPamount(n)=7:nn=1
		xblow(n,nn)=18: yblow(n,nn)=35:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=40:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=14: yblow(n,nn)=45:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=14: yblow(n,nn)=50:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=16: yblow(n,nn)=53:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=16: yblow(n,nn)=60:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=16: yblow(n,nn)=68:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=wolverineKickSnd
		zani(n)=14:zf(n)=3
	EndIf
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
		zblowPamount(n)=4:nn=1
		xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=7: yblow(n,nn)=45:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=52:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=60:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
		
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=wolverineKickSnd
		zani(n)=14:zf(n)=4
	EndIf
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then
		zblowPamount(n)=6:nn=1
		xblow(n,nn)=10: yblow(n,nn)=35:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=40:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=45:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=50:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=63:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=18: yblow(n,nn)=68:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=wolverineKickSnd
		zani(n)=14:zf(n)=5
	EndIf
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=14:zf(n)=6
	If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=14:zf(n)=7
	If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=14:zf(n)=8
	If zBlowSeq(n) > h Then zBlowSeq(n)=0:zBlow(n)=0
	
Case 11	;club
	a=12/wolvSpdFctr(n):b=22/wolvSpdFctr(n):c=29/wolvSpdFctr(n):d=50/wolvSpdFctr(n):e=55/wolvSpdFctr(n)
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
	a=15/wolvSpdFctr(n):b=25/wolvSpdFctr(n):c=32/wolvSpdFctr(n):d=34/wolvSpdFctr(n):e=36/wolvSpdFctr(n)
	:f=38/wolvSpdFctr(n):g=f+45:g2=g+10:h=g2+20:i=1000
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	zNoGrav(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) = 1 And downKey(n) = 1 Then zBlowSeq(n) = 1100
	If zBlowSeq(n) >= 1100 Then performSuperSpecial2(n)
	If zBlowSeq(n) = 1 And zOnGnd(n) = 0 Then zBlowSeq(n) = i
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then 
		zani(n)=10:zf(n)=6
		If zBlowSeq(n) = a And gameSound = 1 Then PlaySound wolverineSuper2Snd
	EndIf
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then 
		moveY(n, -5):moveX(n,zBlowDir(n),3.5)
		If zBlowSeq(n) < a+5 Then
			zani(n)=17:zf(n)=5
		Else
			zani(n)=17:zf(n)=6
		End If
	End If
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=17:zf(n)=7
	If zBlowSeq(n) = c Then 
		zSuperMove(n)=1:zSuperMoveSeq(n)=0
		If gameSound Then PlaySound wolverineSuper1Snd
	EndIf
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=17:zf(n)=2
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=17:zf(n)=3:extraObj(n,zx(n),10,zy(n),35,zblowdir(n),82)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=17:zf(n)=4:extraObj(n,zx(n),15,zy(n),50,zblowdir(n),83)
	If zBlowSeq(n) > f And zBlowSeq(n) =< g Then
		extraObj(n,zx(n),40,zy(n),50,zblowdir(n),84)
		extraObj(n,zx(n),40,zy(n),50,zblowdir(n),85)
		If zBlowSeq(n) Mod 2 = 1 Then 
			zani(n)=17:zf(n)=9
		Else
			zani(n)=17:zf(n)=10
		EndIf
		If zBlowSeq(n) Mod 5 = 0 And gameSound = 1 Then PlaySound slashSnd 
		zblowPamount(n)=14:nn=1
		zblowback(n)=1
		For counter = -40 To 100
			xblow(n,nn)=-55:yblow(n,nn)=counter:wblow(n,nn)=220:hblow(n,nn)=5:nn=nn+1
			counter = counter + 10
		Next
		zHitmode(n)=2:zBlowHold(n)=10
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=6:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=wolverineSlash4Snd
	EndIf
	If zBlowSeq(n) > g And zBlowSeq(n) =< g2 Then
		If zBlowSeq(n) Mod 5 = 0 And gameSound = 1 Then PlaySound slashSnd 
		zblowPamount(n)=14:nn=1
		zblowback(n)=1
		For counter = -40 To 100
			xblow(n,nn)=-55:yblow(n,nn)=counter:wblow(n,nn)=220:hblow(n,nn)=5:nn=nn+1
			counter = counter + 10
		Next
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
		zBlowSound(n)=wolverineSlash4Snd
		zani(n)=17:zf(n)=8
	EndIf
	
	picCounter = 1
	For counter = i To i+20
		If zBlowSeq(n) => counter And zBlowSeq(n) < counter+3 Then zani(n)=5:zf(n)=picCounter
		counter = counter + 2
		picCounter = picCounter+1
		If picCounter = 5 Then picCounter = 1
	Next
	If zBlowSeq(n) = i+30 And gameSound Then PlaySound wolverineSuper2Snd
	If zBlowSeq(n) = i+30 Then zBlowSeq(n) = b
	
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then
		extraObj(n,zx(n),40,zy(n),50,zblowdir(n),86)
		extraObj(n,zx(n),40,zy(n),50,zblowdir(n),87)
	EndIf
 	

	If zHitHead(n) = 1 Then zBlowSeq(n) = h+1
	If zBlowSeq(n) = g2 And gameSound Then PlaySound wolverineShoutSnd
	If zBlowSeq(n) > h And zBlowSeq(n) < i Then zani(n)=17:zf(n)=6:zNoGrav(n)=0:ztopSpeed(n)=1.5:zNomove(n)=0:
	If zongnd(n)=1 And zBlowSeq(n) => h-2 And zBlowSeq(n) < i Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 15 ;Wolverine throw
	a=8/wolvSpdFctr(n): b=15/wolvSpdFctr(n): c=25/wolvSpdFctr(n): d=50/wolvSpdFctr(n): e=60/wolvSpdFctr(n)
	:f=80/wolvSpdFctr(n): g=90/wolvSpdFctr(n): h=100/wolvSpdFctr(n): i=120/wolvSpdFctr(n): j=125/wolvSpdFctr(n):
	k=130/wolvSpdFctr(n): l=135/wolvSpdFctr(n): m=145/wolvSpdFctr(n): nnn=150/wolvSpdFctr(n)
	zNoMove(n)=1:zNoJump(n)=1:zNoGrav(n)=1
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=3:zNoGrav(n)=0
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=12:zf(n)=4:zNoGrav(n)=0
	If zBlowSeq(n)= a Then
		If gameSound Then PlaySound wolverineGrabSnd
		grabbing(n,zx(n),zy(n)-3,zGrabDist(n),7)
		If zGrabs(n)=1 Then zBlowSeq(n)=c+4
	EndIf
	If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
	
	en=zGrabsThis(n)
	
	If zBlowSeq(n) > c And zBlowSeq(n) < d Then 
		If shotKey(n)=1 Or grabKey(n)=1 Then
			zBlowSeq(n)=d
			zGrabbed(en)=1:zHit(en)=1
			zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
		Else
			zBlowSeq(n)=zBlowSeq(n)-1
			zAni(en)=2:zf(en)=3
			zGrabbed(en)=1:zHit(en)=1
			zAni(n)=12:zf(n)=5
			If shotKey(en)=1 Or specialKey(en)=1 Then zLetGoSeq(en)=zLetGoSeq(en)+1
			If Blockkey(n)=1 Or zLetGoSeq(en) > zLetGoAmount(en) Then
				zhit(en)=0:zgrabbedby(en)=0:zgrabbed(en)=0
				zHitTime(en)=0:zFallTime(en)=0:zHitSeq(en)=0
				zgrabsThis(n)=0
				zgrabs(n)=0
				zBlowSeq(n)=i
			EndIf
		EndIf
	EndIf
		
	If zBlowSeq(n) > d And zBlowSeq(n) < g Then zshield(n)=1
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then 
		moveX(n,zBlowDir(n),3.5)
		moveY(n,-3)
		zani(n)=15:zf(n)=1
	EndIf
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=2
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=3:zani(en)=2:zf(en)=2
	If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=2:zani(en)=2:zf(en)=3

	If zblowseq(n) = f Then
		extraObj(n,zx(n),-10,zy(n),10,zblowdir(n),95)
		zHitmodeTaken(en)=2:zHit(en)=1:
		zDamage(en)=zDamage(en)+5
		zLife(en)=zLife(en)-5
	EndIf
	
	If zBlowSeq(n) = g  Then
		PlaySound slashSnd
	EndIf

	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=4:zNoGrav(n)=1:moveX(n,zBlowDir(n),-1)
	If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=15:zf(n)=5:moveX(n,zBlowDir(n),-1)
	If zBlowSeq(n) => j And zBlowSeq(n) < k Then zani(n)=15:zf(n)=6:zNoGrav(n)=0
	If zBlowSeq(n) => k And zBlowSeq(n) < l Then zani(n)=15:zf(n)=7:zNoGrav(n)=0
	If zBlowSeq(n) => k And zBlowSeq(n) < l Then zani(n)=15:zf(n)=8:zNoGrav(n)=0
	If zBlowSeq(n) => l And zBlowSeq(n) < m Then zani(n)=15:zf(n)=9:zNoGrav(n)=0
	
	If zface(n)=2 Then dir=4:dir2=2:n1=1:n2=30	Else dir=2:dir2=4:n1=-1:n2=-30
	
	If zBlowSeq(n) => i And zBlowSeq(n) < m Then
		zx(en)=zx(n)+0:zy(en)=zy(n)-30 :zHitSeq(en)=14
		zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
		zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
		zDamage(en)=zDamage(en)+8
		zLife(en)=zLife(en)-8
		zFace(en)=dir : zFallDir(en)=dir
		zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
		
		If zBlowSeq(n) = l And gameSound Then PlaySound wolverineShout2Snd
	EndIf 
	
	
	If zBlowSeq(n) > d And zBlowSeq(n) < m Then zgrabbed(en)=1:checkZvsWall(en,0)
	If zBlowSeq(n) => nnn Then zBlowSeq(n)=0:zBlow(n)=0:zFace(n)=dir:zBlowDir(n)=dir:zNoGrav(n)=0

Case 16 ;Counter Key (Berserker Rage)
	a=4/wolvSpdFctr(n):b=8/wolvSpdFctr(n):c=12/wolvSpdFctr(n):d=16/wolvSpdFctr(n):e=50/wolvSpdFctr(n)
	:f=60:g=62:h=63:i=64:j=65:k=67:l=67:m=69:nnn=70:o=75
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	aa=1000
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0

	If zongnd(n)=0 Then zy(n)=zy(n)-2
	If zBlowSeq(n) = 1 And zSuperBar(n) >= 100 And downKeyDoubleTap(n)=1 Then zBlowSeq(n)=aa:downKeyDoubleTap(n)=0
	If zBlowSeq(n) >= aa Then performWolverineHeal(n)
	If zBlowSeq(n) = 1 And zSuperBar(n) >= 100 And downKey(n)=1 Then zBlowSeq(n) = f
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) => j And zBlowSeq(n) < k Then zani(n)=10:zf(n)=5
	If zBlowSeq(n) => k And zBlowSeq(n) < l Then zani(n)=10:zf(n)=6
	If zBlowSeq(n) => l And zBlowSeq(n) < m Then zani(n)=10:zf(n)=7
	If zBlowSeq(n) => m And zBlowSeq(n) < nnn Then zani(n)=10:zf(n)=8
	
	If zBlowSeq(n) = f Then
		wolverineRage(n)=1
		zSuperMove(n)=1:zSuperMoveSeq(n)=0
		If gameSound Then PlaySound wolverineSuper1Snd
	EndIf
	If zBlowSeq(n) = k And gameSound Then PlaySound wolverineClawSnd
	If zBlowSeq(n) = nnn And gameSound Then PlaySound wolverineShout4Snd
	If zBlowSeq(n) >= nnn And zBlowSeq(n) <= o Then
		zblowPamount(n)=4:nn=1
		For counter = 0 To 30
			xblow(n,nn)=0: yblow(n,nn)=counter:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
			counter = counter + 10
		Next

		zHitmode(n)=2:zBlowHold(n)=0:zBlowSound(n)=slashSnd
		zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=15
	EndIf
	;***** Taunt *****
	If zBlowSeq(n)=1 Then zTauntSeed(n)=Rand(3)
	If zTauntSeed(n)=1 Then
		If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=17:zf(n)=1
		If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=17:zf(n)=2
		If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=17:zf(n)=3
		If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=17:zf(n)=4
		If zBlowSeq(n) => d And zBlowSeq(n) < e Then 
			If zBlowSeq(n) = d And gameSound Then PlaySound wolverineShoutSnd
			If zBlowSeq(n) Mod 3 = 0 And zBlowSeq(n) Mod 4 = 0 Then 
				zani(n)=17:zf(n)=8
			Else
				zani(n)=17:zf(n)=18
			End If
		EndIf
	Else If zTauntSeed(n)=2 Then
		If zBlowSeq(n) = 1 Then zBlowSeq(n) = 100
		If zBlowSeq(n) = 100 And gameSound Then PlaySound wolverineLetsGoSnd
		If zBlowSeq(n) = 120 And gameSound Then PlaySound wolverineClawSnd
		If zBlowSeq(n) >= 100 And zBlowSeq(n) < 105 Then zani(n)=16:zf(n)=1
		If zBlowSeq(n) >= 105 And zBlowSeq(n) < 110 Then zani(n)=16:zf(n)=2
		If zBlowSeq(n) >= 110 And zBlowSeq(n) < 115 Then zani(n)=16:zf(n)=3
		If zBlowSeq(n) >= 115 And zBlowSeq(n) < 120 Then zani(n)=16:zf(n)=4
		If zBlowSeq(n) >= 120 And zBlowSeq(n) < 125 Then zani(n)=16:zf(n)=5
		If zBlowSeq(n) >= 125 And zBlowSeq(n) < 130 Then zani(n)=16:zf(n)=6
		If zBlowSeq(n) >= 130 And zBlowSeq(n) < 135 Then zani(n)=16:zf(n)=7
		If zBlowSeq(n) >= 135 And zBlowSeq(n) < 140 Then zani(n)=16:zf(n)=8
		If zBlowSeq(n) >= 140 And zBlowSeq(n) < 145 Then zani(n)=16:zf(n)=9
		If zBlowSeq(n) >= 145 And zBlowSeq(n) < 150 Then zani(n)=16:zf(n)=10
		If zBlowSeq(n) >= 150 And zBlowSeq(n) < 155 Then zani(n)=16:zf(n)=11
		If zBlowSeq(n) >= 150 And zBlowSeq(n) < 155 Then zani(n)=16:zf(n)=20
		If zBlowSeq(n) >= 155 And zBlowSeq(n) < 158 Then zani(n)=16:zf(n)=21
		If zBlowSeq(n) >= 158 And zBlowSeq(n) < 161 Then zani(n)=16:zf(n)=22
		If zBlowSeq(n) = 161 Then zBlowSeq(n) = e
	Else If zTauntSeed(n)=3 Then
		If zBlowSeq(n) = 1 Then zBlowSeq(n)=180
		If (zBlowSeq(n) = 190 Or zBlowSeq(n) = 210) And gameSound Then PlaySound wolverineClaw2Snd
		If zBlowSeq(n) >= 180 And zBlowSeq(n) < 185 Then zani(n)=16:zf(n)=12	
		If zBlowSeq(n) >= 185 And zBlowSeq(n) < 190 Then zani(n)=16:zf(n)=13
		If zBlowSeq(n) >= 190 And zBlowSeq(n) < 195 Then zani(n)=16:zf(n)=14
		If zBlowSeq(n) >= 195 And zBlowSeq(n) < 200 Then zani(n)=16:zf(n)=15
		If zBlowSeq(n) >= 200 And zBlowSeq(n) < 205 Then zani(n)=16:zf(n)=16
		If zBlowSeq(n) >= 205 And zBlowSeq(n) < 210 Then zani(n)=16:zf(n)=17
		If zBlowSeq(n) >= 210 And zBlowSeq(n) < 215 Then zani(n)=16:zf(n)=18
		If zBlowSeq(n) >= 215 And zBlowSeq(n) < 220 Then zani(n)=16:zf(n)=19
		If zBlowSeq(n) = 220 Then zBlowSeq(n) = e
	End If
	If zBlowSeq(n) = d Or zBlowSeq(n) = 160 Or zBlowSeq(n) = 219 Then 
		If zSuperBar(n) <= 100 And wolverineRage(n) = 0 And vsMode=1 Then zSuperBar(n)=zSuperBar(n)+5
	EndIf
	;***** Taunt *****
	
	If zBlowSeq(n) = o Then zBlowSeq(n) = e
	
	If zBlowSeq(n) > e And zBlowSeq(n) < f Then zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key (Drill Claw)
	a=12 :b=16 :c=20 :d=24 :e=28 
	:f=40 :g=52 :h=56 :i=60 :j=64 
	:k=68 :l=72 :m=84 :nnn=88 :o=92 
	:p=96 :q=100 :r=104 :s=116 :t=120 
	:u=124 :v=128 :w=132 :x=136 :y=140 
	:z2=152 :a2=156 :b2=160 :c2=164 :d2=170 
	:e2=174 
	If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	zNoGrav(n)=1
	If zHitHead(n)=1 Then zBlowSeq(n)=i:zy(n)=zy(n)+4
	zBlowSound(n)=wolverineDrillClawHitSnd
	If zBlowSeq(n) = 1 And gameSound Then PlaySound wolverineSlash3Snd
	If zBlowSeq(n) = 1 And upKey(n) And (leftKey(n) Or rightKey(n)) Then zBlowSeq(n) = r
	If zBlowSeq(n) = 1 And downKey(n) And (leftKey(n) Or rightKey(n)) Then zBlowSeq(n) = y
	If zBlowSeq(n) = 1 And upKey(n) Then zBlowSeq(n) = f
	If zBlowSeq(n) = 1 And downKey(n) Then zBlowSeq(n) = l
	
	If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=18:zf(n)=11
	If zBlowSeq(n) => a And zBlowSeq(n) < e Then
		If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=18:zf(n)=12:moveX(n,zBlowDir(n),4)
		If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=18:zf(n)=13:moveX(n,zBlowDir(n),4)
		If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=18:zf(n)=14:moveX(n,zBlowDir(n),4)
		If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=18:zf(n)=15:moveX(n,zBlowDir(n),4)
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=40: yblow(n,nn)=0:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=40: yblow(n,nn)=5:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
		xblow(n,nn)=40: yblow(n,nn)=10:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=10
	EndIf
	
	If zHitHead(n) = 1 Then zBlowSeq(n) = e+4/wolvSpdFctr(n)
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=18:zf(n)=1
	If zBlowSeq(n) => g And zBlowSeq(n) < k Then
		If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=18:zf(n)=2:moveY(n,-4)
		If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=18:zf(n)=3:moveY(n,-4)
		If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=18:zf(n)=4:moveY(n,-4)
		If zBlowSeq(n) => j And zBlowSeq(n) < k Then zani(n)=18:zf(n)=5:moveY(n,-4)
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=70:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=60:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=50:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=10
	EndIf
	If zBlowSeq(n) = k-1 Then zBlowSeq(n) = e+4/wolvSpdFctr(n)
	
	If zBlowSeq(n) => l And zBlowSeq(n) < m Then zani(n)=18:zf(n)=6
	If zBlowSeq(n) => m And zBlowSeq(n) < q Then
		If zBlowSeq(n) => m And zBlowSeq(n) < n Then zani(n)=18:zf(n)=7:moveY(n,4)
		If zBlowSeq(n) => n And zBlowSeq(n) < o Then zani(n)=18:zf(n)=8:moveY(n,4)
		If zBlowSeq(n) => o And zBlowSeq(n) < p Then zani(n)=18:zf(n)=9:moveY(n,4)
		If zBlowSeq(n) => p And zBlowSeq(n) < q Then zani(n)=18:zf(n)=10:moveY(n,4)
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=0: yblow(n,nn)=-20:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=5: yblow(n,nn)=-10:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=0:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=10
	EndIf
	If zBlowSeq(n) = q-1 Then zBlowSeq(n) = e+4/wolvSpdFctr(n)
	
	If zBlowSeq(n) => r And zBlowSeq(n) < s Then zani(n)=18:zf(n)=16
	If zBlowSeq(n) => s And zBlowSeq(n) < x Then
		If zBlowSeq(n) => s And zBlowSeq(n) < t Then zani(n)=18:zf(n)=17:moveY(n,-3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => t And zBlowSeq(n) < u Then zani(n)=18:zf(n)=18:moveY(n,-3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => u And zBlowSeq(n) < v Then zani(n)=18:zf(n)=19:moveY(n,-3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => v And zBlowSeq(n) < w Then zani(n)=18:zf(n)=20:moveY(n,-3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => w And zBlowSeq(n) < x Then zani(n)=18:zf(n)=21:moveY(n,-3.2):moveX(n,zBlowDir(n),3.3)
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=15: yblow(n,nn)=70:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=60:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=25: yblow(n,nn)=50:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=10
	EndIf
	If zBlowSeq(n) = x-1 Then zBlowSeq(n) = e+4/wolvSpdFctr(n)
	
	If zBlowSeq(n) => y And zBlowSeq(n) < z2 Then zani(n)=18:zf(n)=22
	If zBlowSeq(n) => z2 And zBlowSeq(n) < e2 Then
		If zBlowSeq(n) => z2 And zBlowSeq(n) < a2 Then zani(n)=18:zf(n)=23:moveY(n,3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => a2 And zBlowSeq(n) < b2 Then zani(n)=18:zf(n)=24:moveY(n,3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => b2 And zBlowSeq(n) < c2 Then zani(n)=18:zf(n)=25:moveY(n,3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => c2 And zBlowSeq(n) < d2 Then zani(n)=18:zf(n)=26:moveY(n,3.2):moveX(n,zBlowDir(n),3.3)
		If zBlowSeq(n) => d2 And zBlowSeq(n) < e2 Then zani(n)=18:zf(n)=27:moveY(n,3.2):moveX(n,zBlowDir(n),3.3)
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=15: yblow(n,nn)=-20:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=20: yblow(n,nn)=-10:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=25: yblow(n,nn)=0:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
		zHitmode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=10
	EndIf
	If zBlowSeq(n) = e2-1 Then zBlowSeq(n) = e+4/wolvSpdFctr(n)
	
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then
		xblow(n,1)=40: yblow(n,1)=0:wblow(n,1)=0:hblow(n,1)=0
		xblow(n,2)=40: yblow(n,2)=10:wblow(n,2)=0:hblow(n,2)=0
		xblow(n,3)=40: yblow(n,3)=20:wblow(n,3)=0:hblow(n,3)=0
	EndIf

	
	If (zBlowSeq(n) = d Or zBlowSeq(n) = j Or zBlowSeq(n) = p Or zBlowSeq(n) = w Or zBlowSeq(n) = d2) And gameSound Then PlaySound wolverineDrillClawSnd
	If zBlowSeq(n) = f-2 Then zBlowSeq(n) = e+4/wolvSpdFctr(n)
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=3:zNomove(n)=0
	If zongnd(n)=1 And zBlowSeq(n) => e+1 And zBlowSeq(n) < f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 18:	;wall jump
	zNoGrav(n)=1
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	zantiplat(n)=1
	checkYDist(n,zx(n),zy(n),4)
	If zBlowSeq(n) = 1 And gameSound Then PlaySound wolverineShout4Snd
	If zBlowSeq(n) = 1 And gameSound Then PlaySound wolverineJump2Snd
	If jumpKey(n)=1 And zBlowSeq(n) > 10 Then zBlowSeq(n)=30
	If zBlowSeq(n) >= 10 And zBlowSeq(n) < 23 Then 
		If yDist(n) > 50 Then zy#(n)=zy#(n)-2.5
		If zFace(n)=2 Then zx#(n)=zx#(n)-3.8
		If zFace(n)=4 Then zx#(n)=zx#(n)+3.8
	Else If zBlowSeq(n) >= 23 And zBlowSeq(n) < 27 Then
		If yDist(n) > 50 Then zy#(n)=zy#(n)-1.8
		If zFace(n)=2 Then zx#(n)=zx#(n)-2.6
		If zFace(n)=4 Then zx#(n)=zx#(n)+2.6
	Else If zBlowSeq(n) >= 27 And zBlowSeq(n) < 30 Then
		If yDist(n) > 50 Then zy#(n)=zy#(n)-1
		If zFace(n)=2 Then zx#(n)=zx#(n)-1
		If zFace(n)=4 Then zx#(n)=zx#(n)+1
	End If
	
;---------------- Animation frames sequence -----------------
	If zBlowSeq(n) >= 1 And zBlowSeq(n) < 4 Then zani(n)=4:zf(n)=2
	If zBlowSeq(n) >= 4 And zBlowSeq(n) < 7 Then zani(n)=4:zf(n)=3
	If zBlowSeq(n) >= 7 And zBlowSeq(n) < 10 Then zani(n)=4:zf(n)=4
	If zBlowSeq(n) >= 10 And zBlowSeq(n) < 23 Then zani(n)=4:zf(n)=5
	If zBlowSeq(n) >= 23 And zBlowSeq(n) < 30 Then zani(n)=4:zf(n)=6

	If zBlowSeq(n) > 30 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zNoJump(n)=0
	
End Select

End Function