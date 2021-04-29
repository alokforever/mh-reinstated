Function doDownAirHadouken(n)
    seqStart=1000:endSeq=41
    seq1=seqStart+8:seq2=seq1+8:seq3=seq2+3:seq4=seq3+3:seq5=seq4+14
    seq6=seq5+2
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    
    If zBlowSeq(n) >= seq4 And zBlowSeq(n) < seq5 Then
        
        zNoGrav(n)=1
        If zBlowSeq(n) <= seq4+3 Then zy(n)=zy(n)+4.0:moveX(n,zBlowdir(n),-3)
        If zBlowSeq(n) > seq4+3 And zBlowSeq(n) <= seq4+6 Then zy(n)=zy(n)+3.8:moveX(n,zBlowdir(n),-2.2)
        If zBlowSeq(n) > seq4+6 And zBlowSeq(n) <= seq4+9 Then zy(n)=zy(n)+3.6:moveX(n,zBlowdir(n),-1.6)
        If zBlowSeq(n) > seq4+9 And zBlowSeq(n) <= seq4+12 Then zy(n)=zy(n)+3.4:moveX(n,zBlowdir(n),-0.8)
    End If
    
    If zBlowSeq(n) >= seq5 Then zNoGrav(n)=0
    
;============= Animation ===============
    zani(n)=10
    If zBlowSeq(n) >= seqStart And zBlowSeq(n) < seq1 Then zF(n)=15
    If zBlowSeq(n) >= seq1 And zBlowSeq(n) < seq2 Then zF(n)=16
    If zBlowSeq(n) >= seq2 And zBlowSeq(n) < seq3 Then zF(n)=15
    If zBlowSeq(n) >= seq3 And zBlowSeq(n) < seq4 Then zf(n)=17
    If zBlowSeq(n) >= seq4 And zBlowSeq(n) < seq5 Then
        If zBlowSeq(n) Mod 2 = 0 Then
            If zF(n)=18 Then
                zF(n)=19
            Else
                zF(n)=18
            End If
        End If
    End If
    If zBlowSeq(n) >= seq5 And zBlowSeq(n) < seq6 Then zf(n)=15
    
;============== Shot ===============
    If zBlowSeq(n) = seq4+2 Then
        If gameSound Then PlaySound evilRyuGrunt2Snd
        If zStaminaBar#(n) >= 15 Then
            If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
            dir=zface(n):y=zy(n)-zheight(n)+64
            If zface(n)=2 Then x=zx(n)+47
            If zface(n)=4 Then x=zx(n)-47
            makeshot(n,55,x,y,dir)
            zStaminaBar#(n)=zStaminaBar#(n)-15
        Else
            isFlashLowStamina(n)=1
        End If
    End If

    If zBlowSeq(n) >= seq6 Then zBlowSeq(n)=endSeq
    
End Function

Function doChargeHadouken(n)
    seqStart=2000:endSeq=41
    seq1=seqStart+208:seq2=seq1+2:seq3=seq2+2
    seq4=seq3+2:seq5=seq4+3:seq6=seq5+4:seq7=seq6+2:seq8=seq7+2
    seq9=seq8+3:seq10=seq9+2:seq11=seq10+2:seq12=seq11+2
    seq13=seq12+4:seq14=seq13+3
    
    If zBlowSeq(n)=seqStart Then
        attackChargeLvl(n)=0
        If zStaminaBar#(n) < 20 Then isFlashLowStamina(n)=1:zBlowSeq(n)=endSeq
    Else
        If zBlowSeq(n) < seq1 Then
            depleteStaminaBar(n,0.68)
            attackChargeLvl(n) = (zBlowSeq(n)-seqStart) / 40
            If zBlowSeq(n) <= seqStart+72 And zBlowSeq(n) Mod 9=0 Then
                dirSeed=Rand(2)
                If zFace(n)=2 Then xOffset=-40 Else xOffset=40
                xPosSeed=Rand(90)
                yPosSeed=Rand(180)
                makechunk(n,(zx(n)+xOffset)+xPosSeed,(zy(n)-45)+yPosSeed,zFace(n),171)
            End If
        End If
    End If
    
    Local chunkStart=seqStart+60
    If zBlowSeq(n) >= chunkStart And zBlowSeq(n) < seq1 Then
        If zBlowSeq(n) Mod 10=0 Then
            height=(zy(n)-42) + (attackChargeLvl(n) * 2)
            If zFace(n)=2 Then xPos=-28 Else xPos=28
            makechunk(n,zx(n)+xPos,height,zFace(n),170)
        End If
    End If
    
;============= Animation ===============
    zani(n)=10
    If zBlowSeq(n) >= seqStart And zBlowSeq(n) < seq1 Then
        If zBlowSeq(n) Mod 3 = 0 Then
            If zF(n)=14 Then
                zF(n)=13
            Else
                zF(n)=14
            End If
        End If
    End If
    
    If zBlowSeq(n) >= seq1 And zBlowSeq(n) < seq2 Then zF(n)=2
    If zBlowSeq(n) >= seq2 And zBlowSeq(n) < seq3 Then zF(n)=3
    If zBlowSeq(n) >= seq3 And zBlowSeq(n) < seq4 Then zf(n)=4
    If zBlowSeq(n) >= seq4 And zBlowSeq(n) < seq5 Then zf(n)=5
    If zBlowSeq(n) >= seq5 And zBlowSeq(n) < seq6 Then zf(n)=6
    If zBlowSeq(n) >= seq6 And zBlowSeq(n) < seq7 Then zf(n)=4
    If zBlowSeq(n) >= seq7 And zBlowSeq(n) < seq8 Then zf(n)=5
    If zBlowSeq(n) >= seq8 And zBlowSeq(n) < seq9 Then zf(n)=6
    If zBlowSeq(n) >= seq9 And zBlowSeq(n) < seq10 Then zf(n)=4
    If zBlowSeq(n) >= seq10 And zBlowSeq(n) < seq11 Then zf(n)=5
    If zBlowSeq(n) >= seq11 And zBlowSeq(n) < seq12 Then zf(n)=6
    If zBlowSeq(n) >= seq12 And zBlowSeq(n) < seq13 Then zf(n)=7
    If zBlowSeq(n) >= seq13 And zBlowSeq(n) < seq14 Then zf(n)=8
    
    If zBlowSeq(n) >= seqStart+30 And zBlowSeq(n) < seq1 Then
        If KeyDown(specialK(n))=0 Then zBlowSeq(n)=seq1
        If zStaminaBar#(n) <= 0 Then zStaminaBar#(n)=0:zBlowSeq(n)=seq1
    End If
    
;=============== Sounds ================
    If zBlowSeq(n) = seqStart And gameSound Then PlaySound evilRyuHadoukenChargeSnd
    If zBlowSeq(n) = seq3 And gameSound Then PlaySound evilRyuHadoukenSnd
    
;================ Shot =================
    If zBlowSeq(n) = seq3 Then 
        dir=zface(n):y=zy(n)-zheight(n)+37
        If zface(n)=2 Then x=zx(n)+65
        If zface(n)=4 Then x=zx(n)-68
        If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
        If attackChargeLvl(n) > 1 Then makeshot(n,54,x,y,dir)
        If attackChargeLvl(n) <= 1 Then makeshot(n,53,x,y,dir)
    EndIf
    
    If zBlowSeq(n)=seq14 Then zBlowSeq(n)=endSeq

End Function

Function doAirHadouken(n)
    seqStart=100:endSeq=41
    seq1=seqStart+14:seq2=seq1+3:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2
    seq6=seq5+2:seq7=seq6+2:seq8=seq7+2:seq9=seq8+2:seq10=seq9+2
    seq11=seq10+2:seq12=seq11+2:seq13=seq12+2
    
    If zBlowSeq(n)=seq2 Then
        If zFace(n)=2 And rightKey(n)=1 Then attackMode(n)=1
        If zFace(n)=4 And leftKey(n)=1 Then attackMode(n)=1
    End If
    
    Local shotRecoil#
    If attackMode(n)=1 Then
        shotRecoil#=-4
    Else
        shotRecoil#=-3.2
    End If
    
;========= Animation ==========
    If zBlowSeq(n) >= seqStart And zBlowSeq(n) <= seq1 Then zani(n)=10:zf(n)=9
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=10:zf(n)=10
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=10:zf(n)=11
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=10:zf(n)=10
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=10:zf(n)=11
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=10:zf(n)=10
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=10:zf(n)=11
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=10:zf(n)=10
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=10:zf(n)=11
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=10:zf(n)=10
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=10:zf(n)=11
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=10:zf(n)=12

;========== Sounds ===========
    If zBlowSeq(n) = seqStart+1 And gameSound Then PlaySound evilRyuHadoukenSnd
    
;========= Movement ==========
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then moveX(n,zBlowdir(n),shotRecoil)
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then moveX(n,zBlowdir(n),shotRecoil+0.4)
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then moveX(n,zBlowdir(n),shotRecoil+0.8)
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then moveX(n,zBlowdir(n),shotRecoil+1.2)
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then moveX(n,zBlowdir(n),shotRecoil+1.6)
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then moveX(n,zBlowdir(n),shotRecoil+2.0)
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then moveX(n,zBlowdir(n),shotRecoil+2.4)
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then moveX(n,zBlowdir(n),shotRecoil+2.8)
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then moveX(n,zBlowdir(n),shotRecoil+3.2)
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 And attackMode(n)=1 Then moveX(n,zBlowdir(n),shotRecoil+3.6)
    
;=========== Shot ============
    If zBlowSeq(n) = seq2 Then 
        dir=zface(n):y=zy(n)-zheight(n)+45
        If zface(n)=2 Then x=zx(n)+70
        If zface(n)=4 Then x=zx(n)-70
        If (zFace(n)=2 And rightKey(n)=1) Or (zFace(n)=4 And leftKey(n)=1) Then
            If zStaminaBar#(n) >= 25 Then
                zStaminaBar#(n)=zStaminaBar#(n)-25
                If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
                makeshot(n,53,x,y,dir)
            Else
                If zStaminaBar#(n) >= 15 Then
                    zStaminaBar#(n)=zStaminaBar#(n)-15
                    If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
                    makeshot(n,5,x,y,dir)
                Else
                    isFlashLowStamina(n)=1
                End If
            End If
        Else
            If zStaminaBar#(n) >= 15 Then
                zStaminaBar#(n)=zStaminaBar#(n)-15
                If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
                makeshot(n,5,x,y,dir)
            Else
                isFlashLowStamina(n)=1
            End If
        End If
    EndIf
    
    If zBlowSeq(n) > seq13 Then zBlowSeq(n)=endSeq
    
End Function

Function DoEvilRyu(n)

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
If zCurBlow(n)=9 And downKeyDoubleTap(n)=1 Then zCurBlow(n)=7

Select zCurBlow(n)
Case 0    ;Blocking
zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If zblocked(n)=1 Then 
        zani(n)=13:zf(n)=2
        zBlockSeqStart(n)=zBlockSeq(n)
    End If
    If zBlockSeq(n) = zBlockSeqStart(n)+3 Then zani(n)=13:zf(n)=3
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1    ;Attack (Kick)
    seq1=1:seq2=seq1+1:seq3=seq2+1:seq4=seq3+2:seq5=seq4+2:seq6=seq5+4
    seq7=seq6+6:seq8=seq7+3:seq9=seq8+2:seq10=seq9+2
    zNoMove(n)=1
    zNoJump(n)=1

;======== Animation =========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=6:zf(n)=8
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=6:zf(n)=9
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=6:zf(n)=10

;======== Hitboxes =========
    If zBlowSeq(n) > seq5 And zBlowSeq(n) =< seq7 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=37.7596:yblow(n,nn)=69.0:wblow(n,nn)=15:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=21.7596:yblow(n,nn)=68.0:wblow(n,nn)=14:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=7.75961:yblow(n,nn)=60.0:wblow(n,nn)=14:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=-0.240387:yblow(n,nn)=52.0:wblow(n,nn)=9:hblow(n,nn)=10:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=18
        zBlowSound(n)=Highpunchsnd
    EndIf
    
;======== Sounds =========
    If zBlowSeq(n) = seq3-1 And gameSound Then PlaySound blow5Snd
    If zBlowSeq(n) = seq1 And gameSound Then PlaySound evilRyuGrunt1Snd

    If zBlowSeq(n) > seq10 Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 2    ;Flying Kick
    seq1=3:seq2=seq1+2:seq3=seq2+12:seq4=seq3+4:seq5=seq4+8
    seq1b=102:seq2b=seq1b+22:seq3b=seq2b+3:seq4b=seq3b+3
    chargingKickSeq=200:endSeq=seq4b+1
    zNoJump(n)=0:ZJUMPING(N)=1:zani(n)=8
    
    If zBlowSeq(n)<=1 And upKey(n)=1 Then zBlowSeq(n)=100
    If zBlowSeq(n)<=1 And downKey(n)=1 Then zBlowSeq(n)=chargingKickSeq
    
;======== Animation (down fly kick) =========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=5
    
;======== Animation (up fly kick) =========
    If zBlowSeq(n) > 100 And zBlowSeq(n) <= seq1b Then zf(n)=6
    If zBlowSeq(n) > seq1b And zBlowSeq(n) <= seq2b Then zf(n)=7
    If zBlowSeq(n) > seq2b And zBlowSeq(n) <= seq3b Then zf(n)=8
    If zBlowSeq(n) > seq3b And zBlowSeq(n) <= seq4b Then zf(n)=9
    
;======== Animation (charging kick) =========
    If zBlowSeq(n) >= chargingKickSeq And zBlowSeq(n) <= seq1b Then zf(n)=6

;======== Hitboxes (down fly kick) =========
    If zBlowSeq(n) > seq2 And zBlowSeq(n) =< seq3 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=28.7006:yblow(n,nn)=55.4001:wblow(n,nn)=19:hblow(n,nn)=17:nn=nn+1
        xblow(n,nn)=9.70056:yblow(n,nn)=61.4001:wblow(n,nn)=18:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=-10.2994:yblow(n,nn)=64.4001:wblow(n,nn)=18:hblow(n,nn)=19:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf

;======== Hitboxes (up fly kick) =========
    If zBlowSeq(n) > seq1b And zBlowSeq(n) =< seq2b Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=19.7006:yblow(n,nn)=89.8002:wblow(n,nn)=12:hblow(n,nn)=19:nn=nn+1
        xblow(n,nn)=8.70056:yblow(n,nn)=75.8002:wblow(n,nn)=14:hblow(n,nn)=11:nn=nn+1
        xblow(n,nn)=-3.29944:yblow(n,nn)=70.8002:wblow(n,nn)=12:hblow(n,nn)=10:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf
    
;======== Sounds =========
    If zBlowSeq(n) = seq2-1 And gameSound Then PlaySound blow5Snd
    If zBlowSeq(n) = seq1b-1 And gameSound Then PlaySound blow5Snd
    
    If zBlowSeq(n)=seq5 Then zBlowSeq(n)=endSeq
    If zBlowSeq(n) >= endSeq Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n):zani(n)=9
    seq1=4:seq2=seq1+6:seq3=seq2+5:seq4=seq3+7:seq5=seq4+8:seq6=seq5+3:seq7=seq6+2

;======== Animation (low kick) =========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zf(n)=7

;======== Hitboxes (low kick) =========
    If zBlowSeq(n) > seq1 And zBlowSeq(n) =< seq2 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=39.7182:yblow(n,nn)=19.0:wblow(n,nn)=10:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=25.7182:yblow(n,nn)=18.0:wblow(n,nn)=12:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=10.7182:yblow(n,nn)=20.0:wblow(n,nn)=11:hblow(n,nn)=11:nn=nn+1
        xblow(n,nn)=-7.2818:yblow(n,nn)=25.0:wblow(n,nn)=15:hblow(n,nn)=15:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf

;======== Sounds =========
    If zBlowSeq(n) = seq1-1 And gameSound Then PlaySound blow5Snd

    If zBlowSeq(n) >= seq7 Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Up + Special (Shoryuken)
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    seq1=5:seq2=seq1+4:seq3=seq2+6:seq4=seq3+74:seq5=seq4+4:seq6=seq5+4

    If zBlowSeq(n) = seq1-1 Then If gameSound Then PlaySound evilRyuShoryukenSnd
    If zBlowSeq(n) = 1 Then
        attackChargeLvl(n)=0
        zJump(n)=0
        If upkeyDoubleTap(n)=1 Then
            attackChargeLvl(n)=1
            zBlowUpLimit(n)=zy(n)-124
        Else
            zBlowUpLimit(n)=zy(n)-118
        End If
    End If
    
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=7:zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then
        zblowPamount(n)=1:nn=1
        xblow(n,nn)=10.2993:yblow(n,nn)=41.0:wblow(n,nn)=14:hblow(n,nn)=14:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1.6)
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=5:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mvcHit1Snd
        zani(n)=7:zf(n)=2
    EndIf
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=22.8807:yblow(n,nn)=61.0:wblow(n,nn)=11:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=7.88074:yblow(n,nn)=53.0:wblow(n,nn)=17:hblow(n,nn)=13:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=3.5:zHitTime(n)=40
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),0.8)
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=7:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mvcHit1Snd
        zani(n)=7:zf(n)=3
    EndIf
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-4.07935:yblow(n,nn)=109.0:wblow(n,nn)=15:hblow(n,nn)=12:nn=nn+1
        xblow(n,nn)=-2.07935:yblow(n,nn)=97.0:wblow(n,nn)=17:hblow(n,nn)=11:nn=nn+1
        xblow(n,nn)=-1.07935:yblow(n,nn)=86.0:wblow(n,nn)=13:hblow(n,nn)=15:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
        If zBlowStill(n)=0 Then zy(n)=zy(n)-6:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=11:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mvcHit1Snd
        zani(n)=7:zf(n)=4:zantiplat(n)=1
    EndIf
    
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=7:zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=7:zf(n)=6
    If zBlowSeq(n) > seq6 Then
        zani(n)=4
        If zBlowSeq(n)=seq6+1 Then zF(n)=6
        If zBlowSeq(n) Mod 2 = 0 Then
            If zF(n)=7 Then
                zF(n)=6
            Else
                zF(n)=7
            End If
        End If
    End If
    
;========== Chunks ============
    ;If zBlowSeq(n) >
    
    If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=seq4
    If zBlowSeq(n) >= seq4 Then zNoGrav(n)=0:ztopSpeed(n)=1:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) >= seq4-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=28:zf(n)=6
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=28:zf(n)=7
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=28:zf(n)=8
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=28:zf(n)=9
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 7    ;Special (Hadouken)
    seq1=3:seq2=seq1+7:seq3=seq2+4:seq4=seq3+2:seq5=seq4+3:seq6=seq5+4
    seq7=seq6+2:seq8=seq7+2:seq9=seq8+3:seq10=seq9+2:seq11=seq10+2:seq12=seq11+2
    seq13=seq12+2:seq14=seq13+2
    endSeq=seq14+1 ;41
    hadoukenAirSeq=100
    hadoukenDownAirSeq=1000
    hadoukenChargeSeq=2000
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-3.2
    
    If zBlowSeq(n)=1 Then
        attackMode(n)=0
        If zOnGnd(n)=0 Then ; If on air
            If downKeyDoubleTap(n)=1 Then
                zBlowSeq(n)=hadoukenDownAirSeq
            Else
                zBlowSeq(n)=hadoukenAirSeq
            End If
        Else                ; If on ground
            If downKeyDoubleTap(n)=1 Then zBlowSeq(n)=hadoukenChargeSeq
        End If
    End If
    
    If zBlowSeq(n) >= hadoukenAirSeq And zBlowSeq(n) < hadoukenDownAirSeq Then
        doAirHadouken(n)
    Else If zBlowSeq(n) >= hadoukenDownAirSeq And zBlowSeq(n) < hadoukenChargeSeq Then
        doDownAirHadouken(n)
    Else If zBlowSeq(n) >= hadoukenChargeSeq Then
        doChargeHadouken(n)
    End If

;========= Animation ==========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=10:zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=10:zf(n)=6
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=10:zf(n)=6
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=10:zf(n)=7
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zani(n)=10:zf(n)=8

;========== Sounds ===========
    If zBlowSeq(n) = 1 And gameSound Then PlaySound evilRyuHadoukenSnd

;=========== Shot ============
    If zBlowSeq(n) = seq4 Then 
        dir=zface(n):y=zy(n)-zheight(n)+45
        If zface(n)=2 Then x=zx(n)+70
        If zface(n)=4 Then x=zx(n)-70
        If (zFace(n)=2 And rightKey(n)=1) Or (zFace(n)=4 And leftKey(n)=1) Then
            If zStaminaBar#(n) >= 25 Then
                zStaminaBar#(n)=zStaminaBar#(n)-25
                If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
                makeshot(n,53,x,y,dir)
            Else
                If zStaminaBar#(n) >= 15 Then
                    zStaminaBar#(n)=zStaminaBar#(n)-15
                    If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
                    makeshot(n,5,x,y,dir)
                Else
                    isFlashLowStamina(n)=1
                End If
            End If
        Else
            If zStaminaBar#(n) >= 15 Then
                zStaminaBar#(n)=zStaminaBar#(n)-15
                If gameSound Then PlaySound evilRyuHadoukenReleaseSnd
                makeshot(n,5,x,y,dir)
            Else
                isFlashLowStamina(n)=1
            End If
        End If
    EndIf

    If zBlowSeq(n) = endSeq Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dodging
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=5
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;Down + Special (Tatsumaki senpu kyaku)
    zNoMove(n)=1:isRunning(n)=0:zblowback(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    seq1=2:seq2=seq1+1:seq3=seq2+1:seq4=seq3+2 ;intro
    seq5=seq4+4:seq6=seq5+4:seq7=seq6+4:seq8=seq7+4 ;spin
    seq9=seq7+3:seq10=seq9+3:seq11=seq10+4:seq12=seq11+4 ;end
    
    If attackMode(n)=2 Then zChunkType(n)=31
    
    If zBlowSeq(n)=1 Then
        attackChargeLvl(n)=2:attackMode(n)=1
        If (zFace(n)=2 And rightKey(n)=1) Or (zFace(n)=4 And leftKey(n)=1) Then
            If zStaminaBar#(n) >= 30 Then
                attackChargeLvl(n)=3:attackMode(n)=2
                zStaminaBar#(n)=zStaminaBar#(n)-30
            Else
                isFlashLowStamina(n)=1
            End If
        End If
    End If
    
    If zBlowSeq(n)=seq8 Then
        attackChargeLvl(n)=attackChargeLvl(n)-1
        If attackChargeLvl(n) > 0 Then zBlowSeq(n)=seq4+1
    End If
    
;======== Movement =========
    If zBlowSeq(n) <= seq4 Then
        zantiplat(n)=1
        If zBlowSeq(n) <= seq3 Then
            zy(n)=zy(n)-1.6
        Else
            zy(n)=zy(n)-0.8
        End If
    End If
    
    If zBlowSeq(n) > 4 And zBlowSeq(n) <= seq8 Then
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
    End If
    
;======== Animation =========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=12:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=12:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=12:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=12:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=12:zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=12:zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=12:zf(n)=7
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=12:zf(n)=8
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=12:zf(n)=9
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=12:zf(n)=10
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=12:zf(n)=11
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=12:zf(n)=12
    
    Local oppFace
    If zFace(n)=2 Then oppFace=4 Else oppFace=2
    If zBlowSeq(n)=seq4+1 And attackMode(n)=2 Then
        makechunk(n,zx(n),zy(n)-27,zFace(n),168)
        If zBlowStill(n)=0 And gameSound Then PlaySound evilryuShortElectricSnd
    End If
    If zBlowSeq(n)=seq6+1 And attackMode(n)=2 Then
        makechunk(n,zx(n),zy(n)-27,oppFace,168)
        If zBlowStill(n)=0 And gameSound And zBlowStill(n)=0 Then PlaySound evilryuShortElectricSnd
    End If

;======== Hitboxes =========
    If zani(n)=12 And zf(n)=5 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-0.476318:yblow(n,nn)=57.0:wblow(n,nn)=16:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=16.5237:yblow(n,nn)=55.0:wblow(n,nn)=12:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=28.5237:yblow(n,nn)=52.0:wblow(n,nn)=22:hblow(n,nn)=14:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=1.5:zHitTime(n)=50
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=12:zBlowBlockTime(n)=36
        zBlowSound(n)=evilRyuHit1Snd
    EndIf
        
    If zani(n)=12 And zf(n)=7 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-52.9952:yblow(n,nn)=51.0:wblow(n,nn)=16:hblow(n,nn)=15:nn=nn+1
        xblow(n,nn)=-36.9952:yblow(n,nn)=51.0:wblow(n,nn)=18:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=-18.9952:yblow(n,nn)=51.0:wblow(n,nn)=18:hblow(n,nn)=15:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=1.5:zHitTime(n)=50
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=12
        zBlowSound(n)=evilRyuHit1Snd
    EndIf
    
;======== Sound =========
    If zBlowSeq(n) = seq3 And gameSound Then PlaySound evilryuTatsumakiSnd
    If zBlowStill(n)=0 And gameSound Then
        If zBlowSeq(n)=seq4+1 Or zBlowSeq(n)=seq6+1 Then PlaySound evilRyuBlow1Snd
    End If
    
    If zBlowSeq(n) > seq8 Then zNoGrav(n)=0
    
    If zBlowSeq(n) > seq12 Then
        zjump(n)=0:zjumping(n)=1
        ztopSpeed(n)=1.5:zNomove(n)=0
        zani(n)=4
        If zBlowSeq(n)=seq12+1 Then zF(n)=6
        If zBlowSeq(n) Mod 2 = 0 Then
            If zF(n)=7 Then
                zF(n)=6
            Else
                zF(n)=7
            End If
        End If
    End If
    
    If zongnd(n)=1 And zBlowSeq(n) > seq12 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;Up + Attack (High Punch)
    zNoMove(n)=1:zNoJump(n)=1:zani(n)=14
    seq1=5:seq2=seq1+4:seq3=seq2+5:seq4=seq3+4:seq5=seq4+4
    seq1b=103:seq2b=seq1b+3:seq3b=seq2b+7:seq4b=seq3b+4:seq5b=seq4b+3
    endSeq=seq5b+1
    
    Local forwardKey=0
    If zFace(n)=2 And rightKey(n)=1 Then forwardKey=1
    If zFace(n)=4 And leftKey(n)=1 Then forwardKey=1
    If zBlowSeq(n)=1 And forwardKey=0 Then zBlowSeq(n)=100
    
;======== Animation (high kick) =========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=4
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=5
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=6
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=7
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=8
    
;======== Animation (uppercut) =========
    If zBlowSeq(n) > 100 And zBlowSeq(n) <= seq1b Then zf(n)=1
    If zBlowSeq(n) > seq1b And zBlowSeq(n) <= seq2b Then zf(n)=2
    If zBlowSeq(n) > seq2b And zBlowSeq(n) <= seq3b Then zf(n)=3
    If zBlowSeq(n) > seq3b And zBlowSeq(n) <= seq4b Then zf(n)=2
    If zBlowSeq(n) > seq4b And zBlowSeq(n) <= seq5b Then zf(n)=1
    
;======== Hitboxes (uppercut) =========
    If zBlowSeq(n) > seq1b And zBlowSeq(n) <= seq2b Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=19.9999:yblow(n,nn)=74.0:wblow(n,nn)=14:hblow(n,nn)=13:nn=nn+1
        xblow(n,nn)=18.9999:yblow(n,nn)=64.0:wblow(n,nn)=14:hblow(n,nn)=13:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf
    If zBlowSeq(n) > seq2b And zBlowSeq(n) <= seq3b Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=1.99994:yblow(n,nn)=104.0:wblow(n,nn)=14:hblow(n,nn)=11:nn=nn+1
        xblow(n,nn)=6.99994:yblow(n,nn)=93.0:wblow(n,nn)=11:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=6.99994:yblow(n,nn)=79.0:wblow(n,nn)=11:hblow(n,nn)=10:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf
    
;======== Hitboxes (high kick) =========
    If zBlowSeq(n) > 0 And zBlowSeq(n) < seq1-1 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-10.92:yblow(n,nn)=93.0:wblow(n,nn)=24:hblow(n,nn)=11:nn=nn+1
        xblow(n,nn)=-5.92004:yblow(n,nn)=84.0:wblow(n,nn)=19:hblow(n,nn)=13:nn=nn+1
        xblow(n,nn)=-4.92004:yblow(n,nn)=71.0:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=30.08:yblow(n,nn)=70.0:wblow(n,nn)=16:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=13.08:yblow(n,nn)=62.0:wblow(n,nn)=16:hblow(n,nn)=13:nn=nn+1
        xblow(n,nn)=-2.92004:yblow(n,nn)=60.0:wblow(n,nn)=13:hblow(n,nn)=14:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
    EndIf
    
;======== Sounds =========
    If zBlowSeq(n) = 0 And gameSound Then PlaySound blow5Snd
    If zBlowSeq(n) = seq1-1 And gameSound Then PlaySound blow5Snd
    If zBlowSeq(n) = seq3b-1 And gameSound Then PlaySound blow5Snd

    If zBlowSeq(n) = seq5 Then zBlowSeq(n)=endSeq
    If zBlowSeq(n) > endSeq Then zBlowSeq(n)=0:zBlow(n)=0

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1: drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=28:zf(n)=7 :eAni(n)=1:ef(n)=2:xed(n)=-43.0007:yed(n)=55
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=28:zf(n)=7 :eAni(n)=1:ef(n)=2:xed(n)=-43.0007:yed(n)=55
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=14:nn=1
        xblow(n,nn)=59.3988:yblow(n,nn)=56.0:wblow(n,nn)=21:hblow(n,nn)=11:nn=nn+1
        xblow(n,nn)=80.3988:yblow(n,nn)=90.0:wblow(n,nn)=17:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=98.3988:yblow(n,nn)=89.0:wblow(n,nn)=25:hblow(n,nn)=15:nn=nn+1
        xblow(n,nn)=80.3988:yblow(n,nn)=74.0:wblow(n,nn)=16:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=96.3988:yblow(n,nn)=72.0:wblow(n,nn)=17:hblow(n,nn)=15:nn=nn+1
        xblow(n,nn)=83.3988:yblow(n,nn)=58.0:wblow(n,nn)=12:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=94.3988:yblow(n,nn)=57.0:wblow(n,nn)=18:hblow(n,nn)=13:nn=nn+1
        xblow(n,nn)=115.399:yblow(n,nn)=71.0:wblow(n,nn)=12:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=114.399:yblow(n,nn)=57.0:wblow(n,nn)=11:hblow(n,nn)=13:nn=nn+1
        xblow(n,nn)=126.399:yblow(n,nn)=57.0:wblow(n,nn)=6:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=84.3988:yblow(n,nn)=44.0:wblow(n,nn)=16:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=99.3988:yblow(n,nn)=46.0:wblow(n,nn)=17:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=117.399:yblow(n,nn)=46.0:wblow(n,nn)=8:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=34.8107:yblow(n,nn)=55.4082:wblow(n,nn)=23:hblow(n,nn)=11:nn=nn+1
        zHitMode(n)=0 :zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=28:zf(n)=8
        eAni(n)=1:ef(n)=3:xED(n)=85:yed(n)=30
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=28:zf(n)=8 :eAni(n)=1:ef(n)=4:xed(n)=85:yed(n)=30
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=28:zf(n)=9 :eAni(n)=1:ef(n)=2:xed(n)=-43.0007:yed(n)=55
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 12    ;Shooting Position
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
        zani(n)=28:zf(n)=7
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=35:yed(n)=40
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=28:zf(n)=9:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=35:yed(n)=40
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=28:zf(n)=9:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=35:yed(n)=38
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

Case 14    ;Super Special
    a=7:b=15:c=130
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2.5 ;zNoGrav(n)=1 
    If zBlowSeq(n) = b-1 And gameSound Then PlaySound  ryuballSnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) =b-2 Then
        checkDist(n,zx(n),zy(n)-20,zFace(n))
        zSuperMove(n)=1:zSuperMoveSeq(n)=0
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then 
        If rendert = 2 Then checkDist(n,zx(n),zy(n)-20,zFace(n))
        For x=1 To (xDist(n)-27) Step 5
            If zFace(n)=2 Then
                makeChunk(n,Int(zx(n)) + x +25, zy(n)-13,2,18+Rand(0,1))
            Else
                makeChunk(n,Int((zx(n))- x)-25, zy(n)-13,2,18+Rand(0,1))
            EndIf
        Next
        If zBlowSeq(n) < c-10 Then hm=2 Else hm=0
        ;makeRectHit(n, x, y, w, h, dir, hitMode, xHit, yHit, damage, hitHold, chunk, HitSOund)
        
        If zface(n)=2 Then
         If zBlowSeq(n) < c Then makeRectHit(n,zx(n)+5,zy(n)-38,xDist(n),20,zFace(n),hm,2,.5,2.6,6,17,highpunchSnd)
         makeChunk(n,zx(n) + xDist(n), zy(n)-11,2,21+Rand(0,1))
         makeChunk(n,zx(n) + 24, zy(n)-11,2,21+Rand(0,1))
        Else
         If zBlowSeq(n) < c Then makeRectHit(n,zx(n)-(xDist(n)+5),zy(n)-38,xDist(n),20,zFace(n),hm,2,.5,2.6,6,17,highpunchSnd)
         makeChunk(n,zx(n) - xDist(n) , zy(n)-11,4,21+Rand(0,1))
         makeChunk(n,zx(n) - 24, zy(n)-11,4,21+Rand(0,1))
        EndIf
        zani(n)=10:zf(n)=3
    EndIf
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 15 ;ryu throw
    a=8: b=15: c=25: d=50: e=55: f=60: g=77: h=89: i=104: j=106: k=108
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=15:zf(n)=1
    If zBlowSeq(n)= a Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n)+1.11935,zy(n)-66.0,zGrabDist(n),18)
        If zGrabs(n)=1 Then zBlowSeq(n)=c+4
    EndIf
    If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
    
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=1:n2=14    Else dir=2:dir2=4:n1=-1:n2=-14
    
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
        
    If zBlowSeq(n) > d And zBlowSeq(n) < g Then zshield(n)=1
    If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1:zFace(en)=dir
    If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zblowseq(n) = f Then
        If gameSound Then PlaySound hueSnd
    EndIf
    If zBlowSeq(n) => g-3 And zBlowSeq(n) < g Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-15:zAni(en)=2:zf(en)=6:zface(en)=dir2
    If zBlowSeq(n) = g  Then
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
    If zBlowSeq(n)=i Then
        If zFace(n)=2 Then
            zFace(n)=4
        Else
            zFace(n)=2
        End If
        zBlowDir(n)=zFace(n)
    End If
    If zBlowSeq(n) >= k Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 16: ;Taunt
    zNoMove(n)=1:zNoJump(n)=1
    zani(n)=16
    seq1=4:seq2=seq1+4:seq3=seq2+4:seq4=seq3+40
    
    If zongnd(n)=0 Then zy(n)=zy(n)-3.2
    
;========= Animation ============
    If zBlowSeq(n)>0 And zBlowSeq(n)<=seq1 Then zF(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zF(n)=2
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zF(n)=3
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then 
        If zBlowSeq(n) Mod 2 = 0 Then
            If zF(n)=4 Then zF(n)=5 Else zF(n)=4
        End If
    End If
    
;========= Sounds ==========
    If gameSound Then
        If zBlowSeq(n)=seq3+1 And zOnGnd(n)=1 Then PlaySound evilryuStepSnd
        If zBlowSeq(n)=seq3+5 Then PlaySound evilryuKorosuSnd
    End If
    
    If zBlowSeq(n)=seq3+1 And zOnGnd(n)=1 Then quake=1:quakeSeq=0
    If zBlowSeq(n) > seq4 Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 17: ;Extra special (Evil dodge)
    zNoMove(n)=1:zNoJump(n)=1:zNoGrav(n)=0
    seq1=16:seq2=seq1+30

    If zBlowSeq(n)=1 Then isDrawAfterImage(n)=1:afterImageMaxSeq(n)=46

;========= Animation ============
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=16:zF(n)=4
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=18:zF(n)=7
    
;========= Sound ============
    If gameSound Then
        If zBlowSeq(n)=1 Then PlaySound evilryuDodge2Snd
        If zBlowSeq(n)=seq1+10 Then PlaySound evilryuDodgeSnd
    End If
    
;========= Movement ============
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= 4 Then moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > 4 And zBlowSeq(n) <= 8 Then moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > 8 And zBlowSeq(n) <= 12 Then moveX(n,zBlowdir(n),-2.5)
    If zBlowSeq(n) > 12 And zBlowSeq(n) <= 16 Then moveX(n,zBlowdir(n),-1.2)
    
    If zBlowSeq(n) > seq1-6 And zBlowSeq(n) < seq2 Then zShield(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) < seq2-10 Then zNoGrav(n)=1
    
    If zBlowSeq(n) > seq1 And zBlowSeq(n) < seq2 Then 
        If zBlowSeq(n) > seq1 And zBlowSeq(n) < seq1+10 Then moveX(n,zBlowdir(n),7.0)
        If zBlowSeq(n) > seq1+10 And zBlowSeq(n) < seq1+15 Then moveX(n,zBlowdir(n),5.5)
        If zBlowSeq(n) > seq1+15 And zBlowSeq(n) < seq1+20 Then moveX(n,zBlowdir(n),4.0)
        If zBlowSeq(n) > seq1+20 And zBlowSeq(n) < seq1+25 Then moveX(n,zBlowdir(n),2.5)
        If zBlowSeq(n) > seq1+25 And zBlowSeq(n) < seq1+30 Then moveX(n,zBlowdir(n),0.9)
    End If

    If zBlowSeq(n) > seq2 zBlowSeq(n)=0:zBlow(n)=0
End Select

End Function