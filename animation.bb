;----------------- Draw Frozen State ------------------------------
Function drawFrozenState(unit)
	If isFrozen(unit)=1 Then
		Local freezeDuration = 2700: freezeDurationTillShake = 2000 ; in milliseconds
		currentFreezeTime(unit) = MilliSecs()
		If cantGetTime(unit) = 0 Then
			cantGetTime(unit) = 1
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
		If currentFreezeTime(unit) => endFreezeTime(unit) Then cantGetTime(unit)=0:unFreeze(unit,1):zani(unit)=zPrevAni(unit):zf(unit)=zPrevF(unit)
	Else
		cantGetTime(unit)=0
	EndIf
End Function

;----------------- Draw Dizzy State ------------------------------
Function drawDizzyState(unit)
	If isDizzy(unit)=1 Then
		Local dizzyDuration = dizzyDuration(unit) ; in milliseconds
		currentDizzyTime(unit) = MilliSecs()
		If cantGetDizzyTime(unit) = 0 Then
			zHit(unit)=0
			cantGetDizzyTime(unit) = 1
			startDizzyTime(unit) = MilliSecs()
		EndIf
		endDizzyTime(unit) = startDizzyTime(unit) + dizzyDuration
		dizzySeq(unit)=dizzySeq(unit)+1
		Local fallingFrames=3: fallingFrameSpeed=8
		If currentDizzyTime(unit) =< endDizzyTime(unit) Then
			If dizzyFrames(unit) > 0 Then
				For frame=dizzyFrames(unit) To 1 Step -1
					If (dizzySeq(unit) / dizzyFrameSpeed(unit)) Mod frame = 0 Then
						If zHit(unit)=0 Then zani(unit)=23:zf(unit)=frame
						If dizzySeq(unit)-1 > dizzyFrames(unit)*dizzyFrameSpeed(unit) Then dizzySeq(unit) = dizzyFrameSpeed(unit)-1
						Return
					EndIf			
				Next
			Else
				For frame=fallingFrames To 1 Step -1
					If (dizzySeq(unit) / fallingFrameSpeed) Mod frame = 0 Then
						If zHit(unit)=0 Then zani(unit)=2:zf(unit)=frame
						If dizzySeq(unit)-1 > fallingFrames*fallingFrameSpeed Then dizzySeq(unit) = fallingFrameSpeed-1
						Return
					EndIf			
				Next
			End If
		EndIf
		If currentDizzyTime(unit) => endDizzyTime(unit) Then cantGetDizzyTime(unit)=0:unFreeze(unit,0)
	Else
		cantGetDizzyTime(unit)=0
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
				If frame=zWalkQuakeSeq1(n) Or frame=zWalkQuakeSeq2(n) And frame <> 0 Then ;Shake screen
					quake=1:quakeSeq=0
					If gameSound Then PlaySound walkQuakeSnd(curGuy(n))
				End If
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

Function handleJuggernautRun(n)
	If zFace(n)=2 Then 
		If zani(n)=21 And zf(n)=5 Then zSpeed#(n)=2
		If zani(n)=21 And zf(n)=6 Then zSpeed#(n)=1
	Else
		If zani(n)=21 And zf(n)=5 Then zSpeed#(n)=-2
		If zani(n)=21 And zf(n)=6 Then zSpeed#(n)=-1
	End If
	If zRunSeq(n) Mod zRunFootSoundSeq(n) = 0 Then extraObj(n,zx(n),-40,zy(n),2,zFace(n),89)
End Function

Function getPiccoloRunStatus(n)
	If zRunSeq2(n)>=1 And zRunSeq2(n)<=6 And zOnGnd(n)=1 Then 
		zSpeed#(n)=0
		If zRunSeq2(n)>=1 And zRunSeq2(n)<=3 Then zani(n)=21:zf(n)=2
		If zRunSeq2(n)>=3 And zRunSeq2(n)<=6 Then zani(n)=21:zf(n)=3
		Return 1
	End If
	If zRunSeq2(n) = 7 Then 
		extraObj(n,zx(n),-40,zy(n),2,zFace(n),116) ; Dust 2
		If gameSound Then PlaySound dbzGlideSnd
	End If
	Return 0
End Function

;-------------- Draw trailing effects ----------------
Function drawTrailingEffects(n)
	If curGuy(n)=11 Then
		If zRunSeq(n) Mod 5 = 0 And Abs(zSpeed#(n)) >= 5 Then extraObj(n,zx(n),-40,zy(n),-10,zFace(n),90)
	End If
	If curGuy(n)=14 Then
		If zRunSeq2(n)=1 Then extraObj(n,zx(n),-40,zy(n),2,zFace(n),116) ; Dust 2
	End If
	If curGuy(n)=16 Then
		If zRunSeq2(n) Mod 4 = 0 Then 
			extraObj(n,zx(n),-15,zy(n),-30,zFace(n),118)
			extraObj(n,zx(n),-60,zy(n),-15,zFace(n),119)
		End If
	End If
End Function


;------------ Draw Run Sequence ----------------
Function drawRunSequence(n)
	drawTrailingEffects(n)
	If (zRunSeq2(n)=5 Or (canAirGlide(n) And zRunSeq2(n)=5 And zOnGnd(n)=0)) And gameSound Then PlaySound zRunGruntSound(curGuy(n))
	If zRunFootSoundSeq(n) <> 0 Then
		If zRunSeq(n) Mod zRunFootSoundSeq(n) = 0 Then
			If gameSound Then PlaySound zRunFootSound(curGuy(n))
		End If
	End If
	If curGuy(n)=15 Then handleJuggernautRun(n)
	If curGuy(n)=16 Then If getPiccoloRunStatus(n)=1 Then Return
	If zRunFrames(n) <> 0 Then
		For frame=zRunFrames(n) To 1 Step -1
			If (zRunSeq(n) / zRunFrameSpeed#(n)) Mod frame = 0 Then 
				If zRunSeq(n) > (frame * 10) + 10 Then zRunSeq(n) = zRunFrameSpeed#(n)-1:Return
				zani(n)=21:zf(n)=frame
				Return
			EndIf
		Next
		depleteStaminaBar(n, 1)
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

;----------- Draw Duck Sequence --------------
Function drawDuckSequence(n)
	If duckSeq(n) < duckFrameSpeed(n) Then 
		duckSeq(n) = duckFrameSpeed(n)
	Else
		duckSeq(n) = duckSeq(n) + 1
	End If
	For frame=duckFrames(n) To 1 Step -1
		If (duckSeq(n) / duckFrameSpeed(n)) Mod frame = 0 Then
			zani(n)=3:zf(n)=frame
			If duckSeq(n)-1 > duckFrameSpeed(n)*duckFrameSpeed(n) Then duckSeq(n) = duckFrameSpeed(n)-1
			Return
		EndIf			
	Next
End Function

;----------------  Draw Flip Frames ----------------
Function drawFlipFrames(n)
	If flipFrames(n)=0 Then
		Select True														;Jump flip
			Case (zjump2seq(n)=>1 And zjump2seq(n)=<5):zani(n)=5:zf(n)=1
			Case (zjump2seq(n)=>6 And zjump2seq(n)=<10):zani(n)=5:zf(n)=2
			Case (zjump2seq(n)=>11 And zjump2seq(n)=<15):zani(n)=5:zf(n)=3
			Case (zjump2seq(n)=>16 And zjump2seq(n)=<20):zani(n)=5:zf(n)=4
		End Select
	Else If flipFrames(n)=6 Then
		Select True														;Jump flip
			Case (zjump2seq(n)=>1 And zjump2seq(n)=<3):zani(n)=5:zf(n)=1
			Case (zjump2seq(n)=>3 And zjump2seq(n)=<7):zani(n)=5:zf(n)=2
			Case (zjump2seq(n)=>7 And zjump2seq(n)=<10):zani(n)=5:zf(n)=3
			Case (zjump2seq(n)=>10 And zjump2seq(n)=<14):zani(n)=5:zf(n)=4
			Case (zjump2seq(n)=>14 And zjump2seq(n)=<17):zani(n)=5:zf(n)=5
			Case (zjump2seq(n)=>17 And zjump2seq(n)=<21):zani(n)=5:zf(n)=6
		End Select
	Else If flipFrames(n)=7 Then
		Select True														;Jump flip
			Case (zjump2seq(n)>0 And zjump2seq(n)=<3):zani(n)=5:zf(n)=1
			Case (zjump2seq(n)>3 And zjump2seq(n)=<6):zani(n)=5:zf(n)=2
			Case (zjump2seq(n)>6 And zjump2seq(n)=<9):zani(n)=5:zf(n)=3
			Case (zjump2seq(n)>9 And zjump2seq(n)=<12):zani(n)=5:zf(n)=4
			Case (zjump2seq(n)>12 And zjump2seq(n)=<15):zani(n)=5:zf(n)=5
			Case (zjump2seq(n)>15 And zjump2seq(n)=<18):zani(n)=5:zf(n)=6
			Case (zjump2seq(n)>18 And zjump2seq(n)=<21):zani(n)=5:zf(n)=7
		End Select
	End If
	If zjump2seq(n)>20 Then 
		If hasSpecialAirFrames(n)=1 Then
			processOnAirFrames(n)
		Else
			zani(n)=4:zf(n)=1
		End If
	End If
End Function


;--------------- Process Wonder Woman Air Frames -----------------
Function processWonderWomanAirFrames(n)
	If zjump(n)=0 Then ;Falling
		zJumpFallSeq(n)=zjumpfallseq(n)+1
		If zJumpFallSeq(n) >= 0 And zJumpFallSeq(n) < 3 Then zani(n)=4:zf(n)=9
		If zJumpFallSeq(n) >= 3 And zJumpFallSeq(n) < 6 Then zani(n)=4:zf(n)=10
		If zJumpFallSeq(n) >= 6 And zJumpFallSeq(n) < 9 Then zani(n)=4:zf(n)=11
		If zJumpFallSeq(n) >= 9 And zJumpFallSeq(n) < 12 Then zani(n)=4:zf(n)=12
		If zJumpFallSeq(n) >= 12 And zJumpFallSeq(n) < 15 Then zani(n)=4:zf(n)=13
		If zJumpFallSeq(n) >= 15 And zJumpFallSeq(n) Mod 3 = 0 Then
			If zf(n)=14 Then  
				zani(n)=4:zf(n)=15
			Else If zf(n)=15 Then 
				zani(n)=4:zf(n)=14
			Else
				zani(n)=4:zf(n)=14
			End If
		End If
	Else
		If zjumpfallseq(n) <> 0 Then zjumpfallseq(n)=0
		If zjumpseq(n)=1 Then zani(n)=4:zf(n)=2
		If zjumpseq(n)=2 Or zjumpseq(n)=3 Then zani(n)=4:zf(n)=3
		If (zjumpseq(n) >= 4 And zjumpseq(n) <= 5) Or (zjumpseq(n) >= 8 And zjumpseq(n) <= 9) Then zani(n)=4:zf(n)=4
		If (zjumpseq(n) >= 6 And zjumpseq(n) <= 7) Or (zjumpseq(n) >= 10 And zjumpseq(n) <= 11) Then zani(n)=4:zf(n)=5
		If zjumpseq(n) >= 12 And zjumpseq(n) <= 14 Then zani(n)=4:zf(n)=6
		If zjumpseq(n) >= 15 And zjumpseq(n) <= 17 Then zani(n)=4:zf(n)=7
		If zjumpseq(n) >= 18 And zjumpseq(n) <= 20 Then zani(n)=4:zf(n)=8
	End If
End Function

;---------------- Process Juggernaut Air Frames ------------------
Function processJuggernautAirFrames(n)
	If zjump(n)=0 Then	;Falling
		zJumpFallSeq(n)=zJumpFallSeq(n)+1
		If zJumpFallSeq(n) >= 0 And zJumpFallSeq(n) < 4 Then zani(n)=4:zf(n)=7
		If zJumpFallSeq(n) >= 4 And zJumpFallSeq(n) Mod 5 = 0 Then
			If zf(n)=8 Then  
				zani(n)=4:zf(n)=9
			Else If zf(n)=15 Then 
				zani(n)=4:zf(n)=8
			Else
				zani(n)=4:zf(n)=8
			End If
		End If
	Else
		If zJumpFallSeq(n) <> 0 Then zJumpFallSeq(n)=0
		If zjumpseq(n) >= 0 And zjumpseq(n) <= 3 Then zani(n)=4:zf(n)=2
		If zjumpseq(n) >= 4 And zjumpseq(n) <= 6 Then zani(n)=4:zf(n)=3
		If zjumpseq(n) >= 7 And zjumpseq(n) <= 9 Then zani(n)=4:zf(n)=4
		If zjumpseq(n) >= 10 And zjumpseq(n) <= 13 Then zani(n)=4:zf(n)=5
		If zjumpseq(n) >= 14 And zjumpseq(n) <= 17 Then zani(n)=4:zf(n)=6
		If zjumpseq(n) >= 18 And zjumpseq(n) <= 20 Then zani(n)=4:zf(n)=7
	End If
End Function

;----------------- Process Picccolo Air Frames -------------------
Function processPiccoloAirFrames(n)
	If zJump(n)=0 Then ;Falling
		zJumpFallSeq(n)=zJumpFallSeq(n)+1
		If zJumpFallSeq(n) >= 0 And zJumpFallSeq(n) < 4 Then zani(n)=4:zf(n)=4
		If zJumpFallSeq(n) >= 4 Then zani(n)=4:zf(n)=1
	Else ;Jump start
		If zJumpFallSeq(n) <> 0 Then zJumpFallSeq(n)=0
		If zJumpSeq(n) = 1 Then extraObj(n,zx(n),10,zy(n),2,zFace(n),117)
		If zJumpSeq(n) > 0 And zJumpSeq(n) <= 2 Then zani(n)=4:zf(n)=2
		If zJumpSeq(n) > 2 And zJumpSeq(n) <= 7 Then zani(n)=4:zf(n)=3
		If zJumpSeq(n) > 7 And zJumpSeq(n) <= 10 Then zani(n)=4:zf(n)=4
		If zJumpSeq(n) > 10 And zJumpSeq(n) <= 20 Then zani(n)=4:zf(n)=5
	End If
End Function

;------------------- Process On Air Frames -----------------------
Function processOnAirFrames(n)
	If curGuy(n)=14 And isRunning(n)=0 Then processWonderWomanAirFrames(n)
	If curGuy(n)=15 Then processJuggernautAirFrames(n)
	If curGuy(n)=16 Then processPiccoloAirFrames(n)
End Function

;------------------- Process End of Run --------------------------
Function processEndRun(n)
	If curGuy(n)=16 Then extraObj(n,zx(n),10,zy(n),2,zFace(n),117)
End Function

;------------------- Process Special hit frames ------------------
Function processSpecialHitFrames(n)
	Local frame, maxHitSeq=35
	If zhitseq(n) > maxHitSeq Then zani(n)=2:zf(n)=0:Return
	For frame=specialHitFrames(n) To 1 Step -1
		If (zhitseq(n) / hitFrameSpeed(n)) Mod frame = 0 Then
			zani(n)=2:zf(n)=frame
			Return
		EndIf			
	Next
End Function

;------------------- Draw Electrocution -----------------------
Function drawElectrocution(n)
	zNoMove(n)=1:zNoJump(n)=1
	zBlow(n)=0:zBlowStill(n)=0:zHitSeq(n)=0
	x=Rand(-8,8) : y=Rand(-30,-10)
	If electrocuteSeq(n) Mod 12 = 0 Then makechunk(n,zx(n)+x,(zy(n)+10)+y,zFace(n),31)
	electrocuteSeq(n)=electrocuteSeq(n)-1
	If electrocuteSeq(n) Mod 15 = 0 Then zani(n)=2:zf(n)=1
	If electrocuteSeq(n) Mod 30 = 0 Then zani(n)=2:zf(n)=2
End Function