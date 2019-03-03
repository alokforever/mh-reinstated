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
        Local rageDuration = 21000 ; in milliseconds
        wolvSpdFctr(player) = 2
        ztopSpeed(player) = ztopSpeed(player) * wolvSpdFctr(player)
        currentRageTime(player) = MilliSecs()
        
        If canGetRageTime(player) = 0 Then
            canGetRageTime(player) = 1
            startRageTime(player) = MilliSecs()
        EndIf
        endRageTime(player) = startRageTime(player) + rageDuration
        
        If gamePaused=0 Then rageSeq(player) = rageSeq(player) + 1

        If currentRageTime(player) => endRageTime(player) Then 
            canGetRageTime(player)=0:wolverineRage(player)=0:wolvSpdFctr(player)=1:clearAfterImages(player)
        End If
    Else
        ztopSpeed(player) = ztopSpeed(player) / wolvSpdFctr(player)
        canGetRageTime(unit)=0
    EndIf
End Function

Function handleQuakeWalkSeq(n, frame)
    If frame=zWalkQuakeSeq1(n) Or frame=zWalkQuakeSeq2(n) And frame <> 0 Then ;Shake screen
        quake=1:quakeSeq=0
        If gameSound Then PlaySound walkQuakeSnd(curGuy(n))
    End If
End Function

;------------ Draw Walk Sequence ----------------
Function drawWalkSequence(n)
    If zwalkseq(n) = 0 Then 
        If zStanceFrames(n) <> 0 Then 
            If curGuy(n)=1 Then
                zani(n)=19
                If zFace(n)=2 Then x=zx(n)-5
                If zFace(n)=4 Then x=zx(n)+5
                zF(n)=getEvilRyuStance(n, x, zy(n)-10)
            Else
                drawStanceFrame(n)
            End If
            Return
        Else
            zani(n)=1:zf(n)=0
        EndIf
        Return
    EndIf
    If zWalkFrames(n) <> 0 Then
        If zwalkseq(n)>=((zWalkFrames(n)*zWalkFrameSpeed(n))+zWalkFrameSpeed(n))-1 Then zwalkseq(n)=1:Return
        For frame=zWalkFrames(n) To 1 Step -1
            If (zwalkseq(n) / zWalkFrameSpeed#(n)) Mod frame = 0 Then 
                zani(n)=1:zf(n)=frame
                handleQuakeWalkSeq(n, frame)
                Return
            Else If zwalkseq(n) < zWalkFrameSpeed(n) Then
                zani(n)=1:zf(n)=1
            End If
        Next
    Else
        If zwalkseq(n) > 40 Then zwalkseq(n)=1:Return
        If zwalkseq(n) => 1 And zwalkseq(n) =< 10 Then zani(n)=1:zf(n)=2:Return
        If zwalkseq(n) => 11 And zwalkseq(n) =< 20 Then zani(n)=1:zf(n)=3:Return
        If zwalkseq(n) => 20 And zwalkseq(n) =< 30 Then zani(n)=1:zf(n)=1:Return
        If zwalkseq(n) => 30 And zwalkseq(n) =< 40 Then zani(n)=1:zf(n)=3:Return
    EndIf
End Function

Function getHiryuRunStatus(n)
    ret=0
    If zRunSeqNoReset(n)=1 Then 
        If gameSound Then PlaySound hiryuRunStartSnd
        extraObj(n,zx(n),-40,zy(n),2,zFace(n),116) ; Dust 2
    End If
    
    If leftKey(n)=0 And rightKey(n)=0 Then 
        ret=1
        If abs(zSpeed#(n))<=4.8 And abs(zSpeed#(n))>3.0 Then zani(n)=21:zf(n)=7
        If abs(zSpeed#(n))=4.8 And gameSound Then PlaySound hiryuRunEndSnd
    
        If abs(zSpeed#(n))<=3.0 And abs(zSpeed#(n))>2.0  Then zani(n)=21:zf(n)=8
        If abs(zSpeed#(n))<=2.0 And abs(zSpeed#(n))>1.0 Then zani(n)=21:zf(n)=9
        If abs(zSpeed#(n))<=1.0 Then zani(n)=21:zf(n)=10
    End If
    
    Return ret
End Function

Function getWonderwomanRunStatus(n)
    ret=0
    If zRunSeqNoReset(n)=7 And gameSound Then PlaySound zRunFootSound(curGuy(n))
    
    If leftKey(n)=0 And rightKey(n)=0 Then 
        ret=1
        If abs(zSpeed#(n))<=4.8 And abs(zSpeed#(n))>4.3 Then zani(n)=21:zf(n)=8
        If zOnGnd(n)=1 And abs(zSpeed#(n))=4.8 And gameSound Then PlaySound pullSnd

        If abs(zSpeed#(n))<=3.8 And abs(zSpeed#(n))>3.3 Then zani(n)=21:zf(n)=8
        If abs(zSpeed#(n))<=3.3 And abs(zSpeed#(n))>2.8 Then zani(n)=21:zf(n)=9
        If abs(zSpeed#(n))<=2.8 And abs(zSpeed#(n))>2.3 Then zani(n)=21:zf(n)=10
        If abs(zSpeed#(n))<=1.8 And abs(zSpeed#(n))>1.3 Then zani(n)=21:zf(n)=12
        If abs(zSpeed#(n))<=1.3 And abs(zSpeed#(n))>0.8 Then zani(n)=21:zf(n)=13
        If abs(zSpeed#(n))<=0.8 Then zani(n)=21:zf(n)=14
    End If
    Return ret
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
    If zRunSeqNoReset(n)>=1 And zRunSeqNoReset(n)<=6 And zOnGnd(n)=1 Then 
        zSpeed#(n)=0
        If zRunSeqNoReset(n)>=1 And zRunSeqNoReset(n)<=3 Then zani(n)=21:zf(n)=2
        If zRunSeqNoReset(n)>=3 And zRunSeqNoReset(n)<=6 Then zani(n)=21:zf(n)=3
        Return 1
    End If
    If zRunSeqNoReset(n) = 7 Then 
        extraObj(n,zx(n),-40,zy(n),2,zFace(n),116) ; Dust 2
        If gameSound Then PlaySound dbzGlideSnd
    End If
    Return 0
End Function

;-------------- Draw trailing effects ----------------
Function drawTrailingEffects(n)
    If curGuy(n)=14 Or curGuy(n)=11 Then
        If zRunSeqNoReset(n)=1 Then extraObj(n,zx(n),-40,zy(n),2,zFace(n),116) ; Dust 2
    End If
    If curGuy(n)=16 Then
        If zRunSeqNoReset(n) Mod 4 = 0 Then 
            extraObj(n,zx(n),-15,zy(n),-30,zFace(n),118)
            extraObj(n,zx(n),-60,zy(n),-15,zFace(n),119)
        End If
    End If
End Function


;------------ Draw Run Sequence ----------------
Function drawRunSequence(n)
    drawTrailingEffects(n)
    If (zRunSeqNoReset(n)=5 Or (canAirGlide(n) And zRunSeqNoReset(n)=5 And zOnGnd(n)=0)) And gameSound Then PlaySound zRunGruntSound(curGuy(n))
    If zRunFootSoundSeq(n) <> 0 Then
        If zRunSeq(n) Mod zRunFootSoundSeq(n) = 0 Then
            If gameSound Then PlaySound zRunFootSound(curGuy(n))
        End If
    End If
    
    If getSpecialRunStatus(n)=1 Then Return
    
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

Function getSpecialRunStatus(n)
    Local ret=0
    If curGuy(n)=6 Then ret=getHiryuRunStatus(n)
    If curGuy(n)=14 Then ret=getWonderwomanRunStatus(n)
    If curGuy(n)=15 Then handleJuggernautRun(n)
    If curGuy(n)=16 Then If getPiccoloRunStatus(n)=1 Then ret=1
    
    Return ret
End Function

Function getEvilRyuStance(n, x, y)
    zStanceSeq(n)=zStanceSeq(n)+1
    Local frame
    
;======== Animation =========
    If zStanceSeq(n)=1 Then frame=1:stanceLevel(n)=1
    If zStanceSeq(n)=2 Then frame=2
    If zStanceSeq(n)=3 Then frame=3
    If zStanceSeq(n)>3 And zStanceSeq(n)<=6 Then frame=4
    If zStanceSeq(n)>6 And zStanceSeq(n)<=8 Then frame=5
    If zStanceSeq(n)>8 And zStanceSeq(n)<=10 Then frame=6
    If zStanceSeq(n)>10 And zStanceSeq(n)<=12 Then frame=7
    If zStanceSeq(n)>12 And zStanceSeq(n)<=15 Then frame=8
    If zStanceSeq(n)>15 And zStanceSeq(n)<=17 Then frame=9
    If zStanceSeq(n)>17 And zStanceSeq(n)<=20 Then frame=10
    If zStanceSeq(n)>20 And zStanceSeq(n)<=22 Then frame=11
    
    If zStanceSeq(n)>22 And zStanceSeq(n)<=40 Then frame=12
    If zStanceSeq(n)>40 And zStanceSeq(n)<=46 Then frame=13
    If zStanceSeq(n)>46 And zStanceSeq(n)<=52 Then frame=14
    If zStanceSeq(n)>52 And zStanceSeq(n)<=58 Then frame=15
    If zStanceSeq(n)>58 And zStanceSeq(n)<=62 Then frame=16
    If zStanceSeq(n)>62 And zStanceSeq(n)<=64 Then frame=17
    If zStanceSeq(n)>64 And zStanceSeq(n)<=66 Then frame=18
    If zStanceSeq(n)>66 And zStanceSeq(n)<=80 Then frame=19
    If zStanceSeq(n)>80 And zStanceSeq(n)<=86 Then frame=20
    If zStanceSeq(n)>86 And zStanceSeq(n)<=92 Then frame=21
    If zStanceSeq(n)>92 And zStanceSeq(n)<=98 Then 
        frame=22:stanceLevel(n)=stanceLevel(n)+1
        If stanceLevel(n)<4 Then zStanceSeq(n)=22
    End If
    
    If zStanceSeq(n)>98 And zStanceSeq(n)<=104 Then frame=23
    If zStanceSeq(n)>104 And zStanceSeq(n)<=110 Then frame=24
    If zStanceSeq(n)>110 And zStanceSeq(n)<=116 Then frame=25
    If zStanceSeq(n)>116 And zStanceSeq(n)<=123 Then frame=26
    If zStanceSeq(n)>123 And zStanceSeq(n)<=130 Then frame=27
    If zStanceSeq(n)>130 And zStanceSeq(n)<=135 Then frame=28
    If zStanceSeq(n)>135 And zStanceSeq(n)<=138 Then frame=29
    If zStanceSeq(n)>138 And zStanceSeq(n)<=143 Then frame=30
    If zStanceSeq(n)>143 And zStanceSeq(n)<=146 Then frame=31
    If zStanceSeq(n)>146 And zStanceSeq(n)<=149 Then frame=32
    If zStanceSeq(n)>149 And zStanceSeq(n)<=179 Then frame=33
    If zStanceSeq(n)>179 And zStanceSeq(n)<=184 Then frame=34
    
    If zStanceSeq(n)>=184 Then zStanceSeq(n)=22:stanceLevel(n)=1
    
;======== Sounds =========
    If gameSound
        If zStanceSeq(n)=18 Then PlaySound evilryuLightStepSnd
        If zStanceSeq(n)=59 Then PlaySound evilryuElectricSnd
    End If
    
;======== Effects =========
    If zStanceSeq(n)=18 Then quake=1:quakeSeq=0
    If zStanceSeq(n)=59 Then makechunk(n,x,y,zFace(n),164)
    
    Return frame
End Function

;----------- Draw Stance Sequence --------------
Function drawStanceFrame(n)
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
    If curGuy(n)=14 Then ;Wonderwoman
        Select True                                                        ;Jump flip
            Case (zjump2seq(n)>0 And zjump2seq(n)<=3):zani(n)=5:zf(n)=1
            Case (zjump2seq(n)>3 And zjump2seq(n)<=6):zani(n)=5:zf(n)=2
            Case (zjump2seq(n)>6 And zjump2seq(n)<=9):zani(n)=5:zf(n)=3
            Case (zjump2seq(n)>9 And zjump2seq(n)<=12):zani(n)=5:zf(n)=4
            Case (zjump2seq(n)>12 And zjump2seq(n)<=15):zani(n)=5:zf(n)=5
            Case (zjump2seq(n)>15 And zjump2seq(n)<=18):zani(n)=5:zf(n)=6
            Case (zjump2seq(n)>18 And zjump2seq(n)<=21):zani(n)=5:zf(n)=7
            Case (zjump2seq(n)>21 And zjump2seq(n)<=24):zani(n)=5:zf(n)=8
            Case (zjump2seq(n)>24 And zjump2seq(n)<=27):zani(n)=5:zf(n)=9
        End Select
    Else If flipFrames(n)=0 Then
        Select True                                                        ;Jump flip
            Case (zjump2seq(n)=>1 And zjump2seq(n)=<5):zani(n)=5:zf(n)=1
            Case (zjump2seq(n)=>6 And zjump2seq(n)=<10):zani(n)=5:zf(n)=2
            Case (zjump2seq(n)=>11 And zjump2seq(n)=<15):zani(n)=5:zf(n)=3
            Case (zjump2seq(n)=>16 And zjump2seq(n)=<20):zani(n)=5:zf(n)=4
        End Select
    Else If flipFrames(n)=6 Then
        Select True                                                        ;Jump flip
            Case (zjump2seq(n)=>1 And zjump2seq(n)=<3):zani(n)=5:zf(n)=1
            Case (zjump2seq(n)=>3 And zjump2seq(n)=<7):zani(n)=5:zf(n)=2
            Case (zjump2seq(n)=>7 And zjump2seq(n)=<10):zani(n)=5:zf(n)=3
            Case (zjump2seq(n)=>10 And zjump2seq(n)=<14):zani(n)=5:zf(n)=4
            Case (zjump2seq(n)=>14 And zjump2seq(n)=<17):zani(n)=5:zf(n)=5
            Case (zjump2seq(n)=>17 And zjump2seq(n)=<21):zani(n)=5:zf(n)=6
        End Select
    Else If flipFrames(n)=7 Then
        Select True                                                        ;Jump flip
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

Function processEvilRyuAirFrames(n)
    If zjump(n)=0 Then ;Falling
        zJumpFallSeq(n)=zjumpfallseq(n)+1
        If zJumpFallSeq(n) >= 0 And zJumpFallSeq(n) < 4 Then zani(n)=4:zf(n)=3
        If zJumpFallSeq(n) >= 4 And zJumpFallSeq(n) < 9 Then zani(n)=4:zf(n)=4
        If zJumpFallSeq(n) >= 9 And zJumpFallSeq(n) < 13 Then zani(n)=4:zf(n)=5
        If zJumpFallSeq(n) >= 13 And zJumpFallSeq(n) Mod 3 = 0 Then
            If zf(n)=6 Then 
                zani(n)=4:zf(n)=7
            Else
                zani(n)=4:zf(n)=6
            End If
        End If
    Else
        If zjumpfallseq(n) <> 0 Then zjumpfallseq(n)=0
        If zjumpseq(n) >= 0 And zjumpseq(n) <= 17 Then zani(n)=4:zf(n)=1
        If zjumpseq(n) >= 17 And zjumpseq(n) <= 20 Then zani(n)=4:zf(n)=2
    End If
End Function

Function processHiryuAirFrames(n)
    If zjump(n)=0 Then ;Falling
        zJumpFallSeq(n)=zjumpfallseq(n)+1
        If zJumpFallSeq(n) >= 1 And zJumpFallSeq(n) < 4 Then zani(n)=4:zf(n)=1
        If zJumpFallSeq(n) >= 4 And zJumpFallSeq(n) < 7 Then zani(n)=4:zf(n)=2
        If zJumpFallSeq(n) >= 7 And zJumpFallSeq(n) < 11 Then zani(n)=4:zf(n)=3
        If zJumpFallSeq(n) >= 11 And zJumpFallSeq(n) Mod 3 = 0 Then
            If zf(n)=4 Then 
                zani(n)=4:zf(n)=5
            Else If zf(n)=5 Then 
                zani(n)=4:zf(n)=4
            Else
                zani(n)=4:zf(n)=4
            End If
        End If
    Else
        If zjumpfallseq(n) <> 0 Then zjumpfallseq(n)=0
        If zjumpseq(n)>0 And zJumpSeq(n) <= 3 Then zani(n)=4:zf(n)=6
        If zjumpseq(n)>3 And zJumpSeq(n) <= 6 Then zani(n)=4:zf(n)=7
        If zjumpseq(n)>6 And zJumpSeq(n) <= 9 Then zani(n)=4:zf(n)=8
        If zjumpseq(n)>9 And zJumpSeq(n) <= 13 Then zani(n)=4:zf(n)=9
        If zjumpseq(n)>13 And zJumpSeq(n) <= 19 Then zani(n)=4:zf(n)=10
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
    If zjump(n)=0 Then    ;Falling
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

;----------------- Process Picccolo Air Frames -------------------
Function processGohanHelperAirFrames(n)
    If abs(zSpeed#(n)) >= 1.8 And abs(zSpeed#(n)) < 2.7 And isHelperAttackDone(n)=1 Then 
        zani(n)=4:zf(n)=4
    Else If abs(zSpeed#(n)) >= 0.9 And abs(zSpeed#(n)) < 1.8 And isHelperAttackDone(n)=1
        zani(n)=4:zf(n)=3
    Else If abs(zSpeed#(n)) >= 0 And abs(zSpeed#(n)) < 0.9 And isHelperAttackDone(n)=1
        zani(n)=4:zf(n)=2
    Else 
        zani(n)=4:zf(n)=1
    End If
End Function

;------------------- Process On Air Frames -----------------------
Function processOnAirFrames(n)
    If curGuy(n)=1 Then processEvilRyuAirFrames(n)
    If curGuy(n)=6 Then processHiryuAirFrames(n)
    If curGuy(n)=14 And isRunning(n)=0 Then processWonderWomanAirFrames(n)
    If curGuy(n)=15 Then processJuggernautAirFrames(n)
    If curGuy(n)=16 Then processPiccoloAirFrames(n)
    If curGuy(n)=53 Then processGohanHelperAirFrames(n)
End Function

;------------------- Process End of Run --------------------------
Function processEndRun(n)
    If curGuy(n)=16 Then extraObj(n,zx(n),10,zy(n),2,zFace(n),117)
End Function

;------------------- Do Special hit frames ------------------
Function doSpecialHitFrames(n)
    If curGuy(n)=14 Then
        drawWwHitFrames(n)
    Else
        drawSpecialHitFrames(n)
    End If
End Function

;------------------- Draw Wonderwoman Hit frames ----------------------
Function drawWwHitFrames(n)
    seq1=4:seq2=seq1+4:seq3=seq2+4:seq4=seq3+4:seq5=seq4+4:seq6=seq5+4:seq7=seq6+4
    If zhitseq(n)>0 And zHitSeq(n)<=seq1 Then zani(n)=2:zf(n)=8
    If zhitseq(n)>seq1 And zHitSeq(n)<=seq2 Then zani(n)=2:zf(n)=9
    If zhitseq(n)>seq2 And zHitSeq(n)<=seq3 Then zani(n)=2:zf(n)=10
    If zhitseq(n)>seq3 And zHitSeq(n)<=seq4 Then zani(n)=2:zf(n)=11
    If zhitseq(n)>seq4 And zHitSeq(n)<=seq5 Then zani(n)=2:zf(n)=12
    If zhitseq(n)>seq5 And zHitSeq(n)<=seq6 Then zani(n)=2:zf(n)=13
    If zhitseq(n)>seq6 And zHitSeq(n)<=seq7 Then zani(n)=2:zf(n)=14

    If zhitseq(n)>seq7 And zHitSeq(n) Mod 2=0 Then
        zani(n)=2
        If zF(n)=15 Then
            zF(n)=16
        Else
            zF(n)=15
        End If
    End If
End Function

Function drawSpecialHitFrames(n)
    Local frame
    If zhitseq(n) > maxHitSeq(n) Then zani(n)=2:zf(n)=0:Return
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
    If electrocuteFrames(n)=0 Then
        If electrocuteSeq(n) Mod 15 = 0 Then zani(n)=2:zf(n)=1
        If electrocuteSeq(n) Mod 30 = 0 Then zani(n)=2:zf(n)=2
    Else
        For frame=electrocuteFrames(n) To 1 Step -1
            If (electrocuteSeq(n) / electrocuteFrameSpd(n)) Mod frame = 0 Then
                zani(n)=24:zf(n)=frame
                Return
            EndIf
        Next
    End If
End Function

Function doNormalHitSeq(n)
    a=10:b=25:c=35
    If zhitseq(n) => 1 And zhitseq(n) =< a Then zani(n)=2:zf(n)=1
    If zhitseq(n) > a And zhitseq(n) =< b Then zani(n)=2:zf(n)=2
    If zhitseq(n) > b And zhitseq(n) =< c Then zani(n)=2:zf(n)=3
    If zhitseq(n) > c Then zani(n)=2:zf(n)=4
End Function

Function drawBouncedOnGnd(n)
    If zBouncedGndFrames(n) > 0 Then
        If zBouncedGndSeq(n)>=0 And zBouncedGndSeq(n)<4 Then zani(n)=25:zf(n)=1
        If zBouncedGndSeq(n)>=4 And zBouncedGndSeq(n)<8 Then zani(n)=25:zf(n)=2
        If zBouncedGndSeq(n)>=8 And zBouncedGndSeq(n)<12 Then zani(n)=25:zf(n)=3
        If zBouncedGndSeq(n)>=12 Then zani(n)=2:zf(n)=0
    Else
        zani(n)=2:zf(n)=0
    End If
End Function