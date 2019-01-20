Function doCounterPunchSeq(n)
    seqStart=50:seq1=seqStart+5:seq2=seq1+2:seq3=seq2+3:seq4=seq3+7
    endSeq=40

    If zBlowSeq(n)=seqStart Then zShield(n)=1
;======== Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=6:zf(n)=1:zShield(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=18:zf(n)=3:zShield(n)=1
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=18:zf(n)=4
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=18:zf(n)=5

;======== Sounds ==========
    If zBlowSeq(n)=seq1 And gameSound Then PlaySound mvcBlow3Snd
    
;======== Hit box =========
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq3 Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=3: yblow(n,nn)=39:wblow(n,nn)=41:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=30:wblow(n,nn)=60:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=65:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=20:wblow(n,nn)=65:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=15:wblow(n,nn)=65:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
        If zBlowStill(n)=1 Then makechunk(n,zx(n)+22,zy(n)-8,zFace(n),147)
    End If

    If zBlowSeq(n)>=seq4 Then zBlowSeq(n)=endSeq
    
End Function

Function doJuggHeadCrush(n)
    seqStart=0:seq1=seqStart+3:seq2=seq1+42:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:seq6=seq5+3:seq7=seq6+3
    seq8=seq7+3:seq9=seq8+3:seq10=seq9+3:seq11=seq10+3:seq12=seq11+3:seq13=seq12+3
    seq14=seq13+1:seq15=seq14+10:seq16=seq15+4:seq17=seq16+6:seq18=seq17+8
    
    zNoMove(n)=1:zNoJump(n)=1
    zNoGrav(n)=1
    
    If zBlowSeq(n)=1 Then preSuperEffect(n)=1
    
    If zBlowSeq(n)=seq1 Then
        zSuperMove(n)=1:zSuperMoveSeq(n)=0:superMoveMaxSeq(n)=45:superMovePortraitSeqStart(n)=1
    End If
;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=17:zf(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=17:zf(n)=2
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=17:zf(n)=3
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=17:zf(n)=4
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=17:zf(n)=5
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=17:zf(n)=6
    If zBlowSeq(n)>seq6 And zBlowSeq(n)<=seq7 Then zani(n)=17:zf(n)=7
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq8 Then zani(n)=17:zf(n)=8
    If zBlowSeq(n)>seq8 And zBlowSeq(n)<=seq9 Then zani(n)=17:zf(n)=9
    If zBlowSeq(n)>seq9 And zBlowSeq(n)<=seq10 Then zani(n)=17:zf(n)=3
    If zBlowSeq(n)>seq10 And zBlowSeq(n)<=seq11 Then zani(n)=17:zf(n)=4
    If zBlowSeq(n)>seq11 And zBlowSeq(n)<=seq12 Then zani(n)=17:zf(n)=5
    If zBlowSeq(n)>seq12 And zBlowSeq(n)<=seq13 Then zani(n)=17:zf(n)=6
    If zBlowSeq(n)>seq13 And zBlowSeq(n)<=seq14 Then zani(n)=21:zf(n)=3
    If zBlowSeq(n)>seq14 And zBlowSeq(n)<=seq15 Then zani(n)=17:zf(n)=10
    If zBlowSeq(n)>seq15 And zBlowSeq(n)<=seq16 Then zani(n)=21:zf(n)=2
    If zBlowSeq(n)>seq16 And zBlowSeq(n)<=seq17 Then zani(n)=21:zf(n)=4
    If zBlowSeq(n)>seq17 And zBlowSeq(n)<=seq18 Then zani(n)=21:zf(n)=5
    
;======== Sounds ==========
    If zBlowSeq(n)=seq1 And gameSound Then PlaySound juggHeadCrushGruntSnd
    If zBlowSeq(n)=seq2-1 And gameSound Then PlaySound juggHeadCrushSnd
    
;======== Movement =========
    If zBlowSeq(n) >= seq2 And zBlowSeq(n) < seq6 Then moveX(n,zBlowdir(n),8)
    If zBlowSeq(n) >= seq6 And zBlowSeq(n) < seq9 Then moveX(n,zBlowdir(n),6)
    If zBlowSeq(n) >= seq9 And zBlowSeq(n) < seq13 Then moveX(n,zBlowdir(n),3.5)
    If zBlowSeq(n) >= seq13 And zBlowSeq(n) < seq16 Then moveX(n,zBlowdir(n),1)
    
;======== Effects ==========
    If zBlowSeq(n) > seq1 And zBlowSeq(n) < seq2-2 Then 
        If zBlowSeq(n)-2 Mod 10 = 0 Then extraObj(n,zx(n),-40,zy(n),2,4,89) ;Dust
    End If
    
    If zBlowSeq(n)=seq2+1 Then zTempStone(n)=1:zStoneSeq(n)=0:zStoneMaxTime(n)=seq15-(seq2+1)
    
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq15-14 Then 
        If (zBlowSeq(n)-4) Mod 14 = 0 Then 
            extraObj(n,zx(n),-70,zy(n),20,zblowdir(n),144) ;Head Crush effect (head)
            extraObj(n,zx(n),-65,zy(n),0,zblowdir(n),145) ;Head Crush effect (ground)
        End If
    End If
;========= Hitbox ===========
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq18 Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=57:wblow(n,nn)=60:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=47:wblow(n,nn)=60:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=37:wblow(n,nn)=60:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=27:wblow(n,nn)=60:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=17:wblow(n,nn)=60:hblow(n,nn)=10:nn=nn+1
        zBlowHold(n)=0:zBlowStillTime(n)=1
        If zBlowSeq(n) >= seq9 Then
            zHitmode(n)=0 ;Enemy flies at this point during versus mode
            zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
            zEnemyImmuneTime(n)=99
        Else
            zHitmode(n)=2 ;Standard hit impact type
            If zBlowSeq(n) < seq9 Then 
                zHitSpeed#(n)=9:zHitUpSpeed#(n)=2:zHitTime(n)=40
            Else 
                zHitSpeed#(n)=3:zHitUpSpeed#(n)=2:zHitTime(n)=40
            End If
        End If
        
        zBlowDamage(n)=18:zBLowEffect(n)=1:zEnemyImmuneTime(n)=4:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
    End If
End Function

Function doJuggCrouchUpPunch(n, dmg)
    seqStart=100
    seq1=seqStart+6:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+11:seq6=seq5+9
    endSeq=49
    
;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=9:zf(n)=10
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=9:zf(n)=11
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=9:zf(n)=12
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=9:zf(n)=13
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=9:zf(n)=14
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=9:zf(n)=15
    
;======== Sounds ==========
    If zBlowSeq(n)=seq2-1 And gameSound Then PlaySound juggGrunt1Snd
    If zBlowSeq(n)=seq2-1 And gameSound Then PlaySound mvcBlow1Snd
    
;========= Hitboxes ==========
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then
        zblowPamount(n)=2:nn=1
        zBlowBack(n)=1
        xblow(n,nn)=15: yblow(n,nn)=40:wblow(n,nn)=32:hblow(n,nn)=14:nn=nn+1
        xblow(n,nn)=15: yblow(n,nn)=26:wblow(n,nn)=22:hblow(n,nn)=14:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=12:zBlowStillTime(n)=12
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=6:zHitTime(n)=40
        zBlowDamage(n)=dmg:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=juggHit1Snd
    End If
    
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq5 Then
        zblowPamount(n)=2:nn=1
        zBlowBack(n)=1
        xblow(n,nn)=-15: yblow(n,nn)=75:wblow(n,nn)=32:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=37:hblow(n,nn)=10:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=6:zHitTime(n)=40
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=juggHit1Snd
    End If
    
    If zBlowSeq(n)>seq6 Then zBlowSeq(n)=endSeq
End Function

Function doJuggLowKick(n)
    seqStart=30
    seq1=seqStart+4:seq2=seq1+1:seq3=seq2+1:seq4=seq3+15:seq5=seq4+10
    endSeq=18
    
;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=9:zf(n)=5
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=9:zf(n)=6
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=9:zf(n)=7
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=9:zf(n)=8
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=9:zf(n)=9
    
;======== Sounds ==========
    If zBlowSeq(n)=seqStart+1 And gameSound Then PlaySound juggLateralSnd
    If zBlowSeq(n)=seq2-1 And gameSound Then PlaySound mvcBlow1Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=5: yblow(n,nn)=16:wblow(n,nn)=38:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=11:wblow(n,nn)=43:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=6:wblow(n,nn)=43:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
    End If
    
    If zBlowSeq(n)>seq5 Then zBlowSeq(n)=endSeq
End Function

Function doJuggJumpPunch(n)
    seqStart=40:endSeq=37
    seq1=seqStart+4:seq2=seq1+4:seq3=seq2+4:seq4=seq3+3:seq5=seq4+1:seq6=seq5+4:seq7=seq6+3:seq8=seq7+5
    seq9=seq8+3:seq10=seq9+4
    
;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=8:zf(n)=6
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=8:zf(n)=7
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=8:zf(n)=8
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=8:zf(n)=9
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=8:zf(n)=10
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=8:zf(n)=11
    If zBlowSeq(n)>seq6 And zBlowSeq(n)<=seq7 Then zani(n)=8:zf(n)=12
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq8 Then zani(n)=8:zf(n)=13
    If zBlowSeq(n)>seq8 And zBlowSeq(n)<=seq9 Then zani(n)=8:zf(n)=4
    If zBlowSeq(n)>seq9 And zBlowSeq(n)<=seq10 Then zani(n)=8:zf(n)=5

;======== Sounds ==========
    If zBlowSeq(n)=seqStart+1 And gameSound Then PlaySound juggGrunt1Snd
    If zBlowSeq(n)=seq3-1 And gameSound Then PlaySound mvcBlow2Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=7: yblow(n,nn)=25:wblow(n,nn)=51:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=7: yblow(n,nn)=20:wblow(n,nn)=56:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
    End If
    
    If zBlowSeq(n)>seq10 Then zBlowSeq(n)=endSeq
End Function

Function doJuggJumpHighKick(n)
    seqStart=100:endSeq=37
    seq1=seqStart+10:seq2=seq1+1:seq3=seq2+10:seq4=seq3+4:seq5=seq4+3:seq6=seq5+4

;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=8:zf(n)=14
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=8:zf(n)=15
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=8:zf(n)=16
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=8:zf(n)=14
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=8:zf(n)=4
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=8:zf(n)=5
    
;======== Sounds ==========
    If zBlowSeq(n)=seqStart+1 And gameSound Then PlaySound juggLateralSnd
    If zBlowSeq(n)=seq1-1 And gameSound Then PlaySound mvcBlow2Snd
    
;========= Hit box ==========
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq3 Then
        zblowPamount(n)=2:nn=1
        zBlowBack(n)=1
        xblow(n,nn)=40: yblow(n,nn)=73:wblow(n,nn)=15:hblow(n,nn)=23:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=50:wblow(n,nn)=35:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=40:wblow(n,nn)=35:hblow(n,nn)=10:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=5:zHitTime(n)=40
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=juggHit1Snd
    End If

    If zBlowSeq(n)>seq6 Then zBlowSeq(n)=endSeq
    
End Function

Function doJuggHighKick(n)
    seqStart=50:seq1=seqStart+5:seq2=seq1+4:seq3=seq2+3:seq4=seq3+7:seq5=seq4+11:seq6=seq5+7
    endSeq=45
    
;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=14:zf(n)=8
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=14:zf(n)=9
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=14:zf(n)=10
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=14:zf(n)=11
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=14:zf(n)=12
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=14:zf(n)=13
    
;========= Sounds ===========
    If zBlowSeq(n)=seq1 And gameSound Then PlaySound juggGrunt2Snd
    If zBlowSeq(n)=seq2-1 And gameSound Then PlaySound mvcBlow3Snd
    
;========= Hitboxes ==========
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq5 Then
        zblowPamount(n)=2:nn=1
        zBlowBack(n)=1
        xblow(n,nn)=40: yblow(n,nn)=70:wblow(n,nn)=15:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=50:wblow(n,nn)=35:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=40:wblow(n,nn)=35:hblow(n,nn)=10:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=5:zHitTime(n)=40
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=juggHit1Snd
    End If
    
    If zBlowSeq(n)>=seq6 Then zBlowSeq(n)=endSeq
End Function

Function doJuggLongPunch(n)
    seqStart=50:seq1=seqStart+2:seq2=seq1+4:seq3=seq2+2:seq4=seq3+2:seq5=seq4+4:seq6=seq5+2
    seq7=seq6+4:seq8=seq7+2:seq9=seq8+4:seq10=seq9+2:seq11=seq10+4:seq12=seq11+4
    endSeq=26
    
;========= Animation =========
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<seq1 Then zani(n)=6:zf(n)=9
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<seq2 Then zani(n)=6:zf(n)=10
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<seq3 Then zani(n)=6:zf(n)=11
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<seq4 Then zani(n)=6:zf(n)=12
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<seq5 Then zani(n)=6:zf(n)=13
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<seq6 Then zani(n)=6:zf(n)=14
    If zBlowSeq(n)>seq6 And zBlowSeq(n)<seq7 Then zani(n)=6:zf(n)=15
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<seq8 Then zani(n)=6:zf(n)=16
    If zBlowSeq(n)>seq8 And zBlowSeq(n)<seq9 Then zani(n)=6:zf(n)=17
    If zBlowSeq(n)>seq9 And zBlowSeq(n)<seq10 Then zani(n)=6:zf(n)=18
    If zBlowSeq(n)>seq10 And zBlowSeq(n)<seq11 Then zani(n)=6:zf(n)=19
    If zBlowSeq(n)>seq11 And zBlowSeq(n)<seq12 Then zani(n)=6:zf(n)=20
    
;========= Sounds ===========
    If zBlowSeq(n)=seq1 And gameSound Then PlaySound juggLateralSnd
    If zBlowSeq(n)=seq4 And gameSound Then PlaySound mvcBlow3Snd
    
;========= Hitboxes ==========
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq11 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=57:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=20:wblow(n,nn)=62:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=15:wblow(n,nn)=62:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=12:zBlowStillTime(n)=12
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=5:zHitTime(n)=40
        zBlowDamage(n)=22:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=juggHit1Snd
    End If
    
    If zBlowSeq(n)>=seq12 Then zBlowSeq(n)=endSeq
    
End Function

Function doJuggTaunt2(n)
    seqStart=100:seq1=seqStart+4:seq2=seqStart+8:seq3=seqStart+12:seq4=seqStart+16:seq5=seqStart+31
    seq6=seqStart+51
    endSeq=81
    soundSeed=0
    
;============= Animation =============
    If zBlowSeq(n)>=seqStart And zBlowSeq(n)<seq1 Then zf(n)=3
    If zBlowSeq(n)>=seq1 And zBlowSeq(n)<seq2 Then zf(n)=4
    If zBlowSeq(n)>=seq2 And zBlowSeq(n)<seq3 Then zf(n)=5
    If zBlowSeq(n)>=seq3 And zBlowSeq(n)<seq4 Then zf(n)=6
    If zBlowSeq(n)>=seq4 And zBlowSeq(n)<seq5 Then zf(n)=7
    If zBlowSeq(n)>=seq5 And zBlowSeq(n)<seq6 Then zf(n)=8
    
    If zBlowSeq(n)=seq5 Then extraObj(n,zx(n),0,zy(n),-6,zblowdir(n),142)
    
;============= Sounds ================
    If zBlowSeq(n)=seqStart Then 
        soundSeed=Rand(2)
        If gameSound Then
            If soundSeed=1 Then PlaySound juggTaunt1Snd Else PlaySound juggTaunt2Snd
        End If
    End If
    
    If zBlowSeq(n)=seq6 Then zBlowSeq(n)=endSeq
End Function

Function handleJuggernautCooldown(n, blowSeq, cooldownType)
    If zBlowSeq(n)=blowSeq And spellCooldownSeq(n, cooldownType) > 0 Then
        cdSeed=Rand(2)
        If cantSoundCdVoice(n)=0 Then
            cantSoundCdVoice(n)=1:cooldownVoiceSeq(n)=0
            If cdSeed=1 And gameSound And zAI(n)=0 Then 
                PlaySound juggNoManaSnd
            Else If cdSeed=2 And gameSound And zAI(n)=0 Then
                PlaySound juggNoMana2Snd
            End If
        End If
        If gameSound And zAI(n)=0 Then PlaySound clockTickSnd
        zBlowSeq(n)=0:zBlow(n)=0
    End If
End Function

Function DoJuggernaut(n)
If zBlowSeq(n)=0 Then clearControlledPlayers(n)
zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq3
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq3

zchunkType(n)=10

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
        If zblocked(n)=1 Then 
        zani(n)=13:zf(n)=2
        zBlockSeqStart(n)=zBlockSeq(n)
    End If
    If zBlockSeq(n) = zBlockSeqStart(n)+4 Then zani(n)=13:zf(n)=3
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1    ;Normal Punch
    a=3:b=a+4:c=b+4:d=c+1:e=d+1:f=e+4:g=f+4:h=g+4
    endSeq=h+1:longPunchSeq=50
    zNoMove(n)=1:zNoJump(n)=1
    
    If zBlowSeq(n)=1 And (leftKey(n)=1 Or rightKey(n)=1) Then
        zBlowSeq(n)=longPunchSeq
    End If
    
    If zBlowSeq(n)>=longPunchSeq Then doJuggLongPunch(n)
    
;======== Animation ========
    If zBlowSeq(n)>0 And zBlowSeq(n)<a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n)>a And zBlowSeq(n)<b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n)>b And zBlowSeq(n)<c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n)>c And zBlowSeq(n)<d Then zani(n)=6:zf(n)=4
    If zBlowSeq(n)>d And zBlowSeq(n)<e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n)>e And zBlowSeq(n)<f Then zani(n)=6:zf(n)=6
    If zBlowSeq(n)>f And zBlowSeq(n)<g Then zani(n)=6:zf(n)=7
    If zBlowSeq(n)>g And zBlowSeq(n)<h Then zani(n)=6:zf(n)=8
    
;======== Sounds ==========
    If zBlowSeq(n)=1 And gameSound Then PlaySound juggGrunt1Snd
    If zBlowSeq(n)=a And gameSound Then PlaySound mvcBlow2Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > b And zBlowSeq(n) <= d Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=50:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=20:wblow(n,nn)=55:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=15:wblow(n,nn)=55:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
    End If

    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<longPunchSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 2    ;Flying Kick
    seq1=4:seq2=seq1+1:seq3=seq2+18:seq4=seq3+6:seq5=seq4+3:seq6=seq5+4
    endSeq=seq6+1 ;37
    jumpPunchSeq=40
    jumpHighKickSeq=100
    
    If zBlowSeq(n)=1 And downKey(n)=1 Then zBlowSeq(n)=jumpPunchSeq
    If zBlowSeq(n)=1 And upKey(n)=1 Then zBlowSeq(n)=jumpHighKickSeq
    
    If zBlowSeq(n)>=jumpPunchSeq And zBlowSeq(n)<jumpHighKickSeq Then doJuggJumpPunch(n)
    If zBlowSeq(n)>=jumpHighKickSeq Then doJuggJumpHighKick(n)
    
;========= Animation =========
    If zBlowSeq(n)>0 And zBlowSeq(n)<=seq1 Then zani(n)=8:zf(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=8:zf(n)=2
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=8:zf(n)=3
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=8:zf(n)=1
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=8:zf(n)=4
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=8:zf(n)=5
    
;========= Sounds ==========
    If zBlowSeq(n)=1 And gameSound Then PlaySound juggGrunt1Snd
    If zBlowSeq(n)=seq1-1 And gameSound Then PlaySound mvcBlow3Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq3 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=45:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=20:wblow(n,nn)=50:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=15:wblow(n,nn)=50:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=12:zBlowStillTime(n)=12
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
    End If

    if zBlowSeq(n)>=endSeq And zBlowSeq(n)<jumpPunchSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    seq1=4:seq2=seq1+1:seq3=seq2+3:seq4=seq3+2:seq5=seq4+7
    lowKickSeq=30
    endSeq=seq5+1 ;18
    
    If zBlowSeq(n)=1 And (leftKey(n)=1 Or rightKey(n)=1) Then zBlowSeq(n)=lowKickSeq
    If zBlowSeq(n)>=lowKickSeq Then doJuggLowKick(n)
    
;========= Animation =========
    If zBlowSeq(n)>0 And zBlowSeq(n)<=seq1 Then zani(n)=9:zf(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=9:zf(n)=2
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=9:zf(n)=3
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=9:zf(n)=3
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=9:zf(n)=4
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=12: yblow(n,nn)=22:wblow(n,nn)=50:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=17:wblow(n,nn)=55:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=12:wblow(n,nn)=55:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit3Snd
    End If

;======== Sounds ==========
    If zBlowSeq(n)=seq1-1 And gameSound Then PlaySound mvcBlow2Snd
    
    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<lowKickSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 5    ;UP + SPECIAL (Lateral press)
    zNoMove(n)=1
    zNoJump(n)=1:zJumping(n)=0:zJump(n)=0
    a=8:b=a+8:c=b+6:d=c+4:e=50000:f=e+4:g=f+4:h=g+3:i=h+3
    a2=60000:b2=a2+3:c2=b2+3:d2=c2+3:e2=d2+3:f2=e2+3:g2=f2+3:h2=g2+3:i2=h2+3

;================= Animation ==============
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=7:zf(n)=1
    If zBlowSeq(n) > a Then zani(n)=7:zf(n)=2
    ;============= Miss ===================
    If zBlowSeq(n) >= a2 And zBlowSeq(n) < b2 Then zani(n)=7:zf(n)=7
    If zBlowSeq(n) >= b2 And zBlowSeq(n) < c2 Then zani(n)=7:zf(n)=8
    If zBlowSeq(n) >= c2 And zBlowSeq(n) < d2 Then zani(n)=7:zf(n)=9
    If zBlowSeq(n) >= d2 And zBlowSeq(n) < e2 Then zani(n)=7:zf(n)=10
    If zBlowSeq(n) >= e2 And zBlowSeq(n) < f2 Then zani(n)=7:zf(n)=11
    If zBlowSeq(n) >= f2 And zBlowSeq(n) < g2 Then zani(n)=7:zf(n)=12
    If zBlowSeq(n) >= g2 And zBlowSeq(n) < h2 Then zani(n)=7:zf(n)=13
    If zBlowSeq(n) >= h2 And zBlowSeq(n) < i2 Then zani(n)=7:zf(n)=14
    If zBlowSeq(n) = i2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    ;======================================
    ;============== Hit ===================
    If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zani(n)=7:zf(n)=3
    If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=7:zf(n)=4
    If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=7:zf(n)=5
    If zBlowSeq(n) >= h And zBlowSeq(n) < i Then zani(n)=7:zf(n)=6
    If zBlowSeq(n) = i Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    ;======================================
;==========================================

;================= Sounds =================
    If zBlowSeq(n) = 1 And gameSound Then PlaySound juggJumpSnd
    If zBlowSeq(n) = 3 And gameSound Then PlaySound juggLateralSnd
;==========================================

;================= Hit boxes ==============
    If zBlowSeq(n) >= b And zBlowSeq(n) < e Then
        zblowback(n)=1
        If zBlowStill(n)=1 Then zBlowSeq(n)=e
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-48: yblow(n,nn)=46:wblow(n,nn)=97:hblow(n,nn)=28:nn=nn+1
        xblow(n,nn)=-63: yblow(n,nn)=22:wblow(n,nn)=75:hblow(n,nn)=24:nn=nn+1
        xblow(n,nn)=-63: yblow(n,nn)=5:wblow(n,nn)=75:hblow(n,nn)=10:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=1:zHitSpeed#(n)=6:zHitUpSpeed#(n)=3:zHitTime(n)=35:zBlowSound(n)=juggLateralHitSnd
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=90:zBlowStillTime(n)=1:zBlowBlockTime(n)=10
    End If 

;==========================================

;================= Movement ===============
    If zFace(n)=2 Then
        If leftKey(n)=1 Then moveX(n, zBlowDir(n), -1)
        If rightKey(n)=1 Then moveX(n, zBlowDir(n), 1)
    Else
        If leftKey(n)=1 Then moveX(n, zBlowDir(n), 1)
        If rightKey(n)=1 Then moveX(n, zBlowDir(n), -1)
    End If
    
    If zHitHead(n)=0 Then
        If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then moveX(n,zBlowdir(n),2):moveY(n, -7)
        If zBlowSeq(n) > a And zBlowSeq(n) <= b Then moveX(n,zBlowdir(n),2):moveY(n, -6)
        If zBlowSeq(n) > b And zBlowSeq(n) <= c Then moveX(n,zBlowdir(n),2):moveY(n, -5)
        If zBlowSeq(n) > c And zBlowSeq(n) <= d Then moveX(n,zBlowdir(n),2):moveY(n, -4)
    End If
    If zBlowSeq(n) >= d And zBlowSeq(n) < e Then moveX(n, zBlowDir(n), 2)
    If zBlowSeq(n) >= a2 Then moveX(n, zBlowDir(n), 1.2)
    If zBlowSeq(n) > d And zBlowSeq(n) < e And zOnGnd(n)=1 Then 
        If gameSound Then PlaySound walkQuakeSnd(curGuy(n))
        quake=1:quakeSeq=0
        zBlowSeq(n)=a2
    End If
;==========================================

Case 6    ;throwing iten
    a=3:b=6:c=9
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=2
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=3
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=4
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7    ; Juggernaut punch (special)
    zNoMove(n)=1
    zNoJump(n)=1:zJumping(n)=0:zJump(n)=0
    a=2:b=a+3:c=b+3:d=c+2:e=d+10:f=e+5:g=f+2:h=g+2:i=h+2
    ex=1000:a2=ex+4:b2=a2+4:c2=b2+4:d2=c2+3:e2=d2+10:f2=e2+3:g2=f2+2:h2=g2+2:i2=h2+2
    a3=2000:b3=a3+12:c3=b3+8:d3=c3+4
    If zOnGnd(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    If zBlowSeq(n)=1 And isRunning(n) Then 
        If zStaminaBar(n) >= 60 Then zStaminaBar(n)=zStaminaBar(n)-60:zBlowSeq(n)=ex:isRunning(n)=0
    End If
    If zBlowSeq(n) = i+1 Or zBlowSeq(n) = i2+1 Then zBlowSeq(n)=a3
;================= Animation ==============
    ;=========== Normal Punch =============    
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zani(n)=10:zf(n)=7
    If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zani(n)=10:zf(n)=8
    If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zani(n)=10:zf(n)=9
    If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zani(n)=10:zf(n)=10
    ;=======================================
    ;============== Ex punch ===============
    If zBlowSeq(n) >= ex And zBlowSeq(n) <= a2 Then 
        zani(n)=10:zf(n)=1
        If zBlowSeq(n) = ex And gameSound Then PlaySound mvcExSnd
    End If
    If zBlowSeq(n) >= ex And zBlowSeq(n) <= a2 Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) >= a2 And zBlowSeq(n) <= b2 Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) >= b2 And zBlowSeq(n) <= c2 Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) >= c2 And zBlowSeq(n) <= d2 Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) >= d2 And zBlowSeq(n) <= e2 Then zani(n)=10:zf(n)=6
    If zBlowSeq(n) >= e2 And zBlowSeq(n) <= f2 Then zani(n)=10:zf(n)=11
    If zBlowSeq(n) >= f2 And zBlowSeq(n) <= g2 Then zani(n)=10:zf(n)=8
    If zBlowSeq(n) >= g2 And zBlowSeq(n) <= h2 Then zani(n)=10:zf(n)=9
    If zBlowSeq(n) >= h2 And zBlowSeq(n) <= i2 Then zani(n)=10:zf(n)=10
    ;=======================================
    ;============== Recovery ===============
    If zBlowSeq(n) >= a3 And zBlowSeq(n) <= b3 Then zani(n)=10:zf(n)=12
    If zBlowSeq(n) >= b3 And zBlowSeq(n) <= c3 Then zani(n)=10:zf(n)=13
    If zBlowSeq(n) >= c3 And zBlowSeq(n) <= d3 Then zani(n)=10:zf(n)=14
    If zBlowSeq(n) > d3 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    ;=======================================
;===========================================
;================= Sounds ==================
    If zBlowSeq(n) = 1 Or zBlowSeq(n) = ex And gameSound Then PlaySound juggPunchSnd
    If zBlowSeq(n) = e Or zBlowSeq(n) = e2 And gameSound Then PlaySound mvcBlow1Snd
    If zBlowSeq(n) = i Or zBlowSeq(n) = i2 And zOnGnd(n) And gameSound Then PlaySound juggPunchGroundSnd
;===========================================
;================= Effects =================
    If zBlowSeq(n) = f Or zBlowSeq(n) = f2 And zOnGnd(n) Then 
        quake=1:quakeSeq=0
        extraObj(n,zx(n),40,zy(n),3,zblowdir(n),112)
    End If
;===========================================

;================= Movement ================
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f And zOnGnd(n) Then moveX(n, zBlowDir(n), 10)
    If zBlowSeq(n) >= e2 And zBlowSeq(n) <= f2 And zOnGnd(n) Then moveX(n, zBlowDir(n), 26)
;===========================================

;================ Hit boxes ================
    If (zBlowSeq(n) >= e2 And zBlowSeq(n) < f2) Or (zBlowSeq(n) >= e And zBlowSeq(n) < f) Then
        Local hitDamage
        If zBlowSeq(n) >= e2 And zBlowSeq(n) < f2 Then 
            hitDamage=16
        Else
            hitDamage=21
        End If
        zblowPamount(n)=3
        nn=1
        xblow(n,nn)=20: yblow(n,nn)=16:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=26:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=36:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=2:zBlowHold(n)=10
        zHitSpeed#(n)=6:zHitUpSpeed#(n)=3:zHitTime(n)=40;:zBlockSpeed#(n)=30
        zBlowDamage(n)=hitDamage:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=0:zBlowBlockTime(n)=50
        zBlowSound(n)=juggLateralHitSnd
    End If
    If (zBlowSeq(n) >= f2 And zBlowSeq(n) < g2) Or (zBlowSeq(n) >= f And zBlowSeq(n) < g) Then
        If zBlowSeq(n) >= f2 And zBlowSeq(n) < g2 Then 
            hitDamage=16
        Else
            hitDamage=21
        End If
        zblowPamount(n)=2
        nn=1
        xblow(n,nn)=20: yblow(n,nn)=0:wblow(n,nn)=54:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=10:wblow(n,nn)=54:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=36:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=2:zBlowHold(n)=10
        zHitSpeed#(n)=6:zHitUpSpeed#(n)=3:zHitTime(n)=40;:zBlockSpeed#(n)=40
        zBlowDamage(n)=hitDamage:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=0:zBlowBlockTime(n)=60
        zBlowSound(n)=juggLateralHitSnd
    End If
;===========================================

Case 8    ;Dodging
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7/wolvSpdFctr(n):b=15/wolvSpdFctr(n):c=20/wolvSpdFctr(n):d=25/wolvSpdFctr(n):e=30/wolvSpdFctr(n)
    :f=37/wolvSpdFctr(n)
    If zBlowSeq(n) = a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ; Earthquake (down special)
    zNoMove(n)=1
    zNoJump(n)=1:zJumping(n)=0:zJump(n)=0
    a=2:b=a+4:c=b+2:d=c+2:e=d+36:f=e+5:g=f+5
    ex=1000:a2=ex+2:b2=a2+4:c2=b2+2:d2=c2+2:e2=d2+44:f2=e2+5:g2=f2+5
    If zOnGnd(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    If zBlowSeq(n)=1 Then
        If zFace(n)=2 Then checkYDist(n,zx(n)+40,zy(n)+6,2)
        If zFace(n)=4 Then checkYDist(n,zx(n)-40,zy(n)+6,2)
        checkDist(n,zx(n),zy(n)-20,zFace(n))
    End If
    If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 Then 
        If zStaminaBar(n) >= 80 Then zStaminaBar(n)=zStaminaBar(n)-80:zBlowSeq(n)=ex
    End If

;=============== Animation =================
    If (zBlowSeq(n) >= 1 And zBlowSeq(n) < a) Or (zBlowSeq(n) = ex And zBlowSeq(n) < a2) Then zani(n)=12:zf(n)=1
    If (zBlowSeq(n) >= a And zBlowSeq(n) < b) Or (zBlowSeq(n) = a2 And zBlowSeq(n) < b2) Then zani(n)=12:zf(n)=2
    If (zBlowSeq(n) >= b And zBlowSeq(n) < c) Or (zBlowSeq(n) = b2 And zBlowSeq(n) < c2) Then zani(n)=12:zf(n)=3
    If (zBlowSeq(n) >= c And zBlowSeq(n) < d) Or (zBlowSeq(n) = c2 And zBlowSeq(n) < d2) Then zani(n)=12:zf(n)=4
    If (zBlowSeq(n) >= d And zBlowSeq(n) < e) Or zBlowSeq(n) >= d2 And zBlowSeq(n) < e2 Then
        zani(n)=12
        If zf(n)=5 And (zBlowSeq(n) <> 28 Or zBlowSeq(n) <> 1028) Then 
            zf(n)=6
        Else
            zf(n)=5
        End If
    End If
    If (zBlowSeq(n) >= e And zBlowSeq(n) < f) Or (zBlowSeq(n) = e2 And zBlowSeq(n) < f2) Then zani(n)=12:zf(n)=7
    If (zBlowSeq(n) >= f And zBlowSeq(n) < g) Or (zBlowSeq(n) = f2 And zBlowSeq(n) < g2) Then zani(n)=12:zf(n)=8
;===========================================

;================= Sounds ==================
    If gameSound Then
        If zBlowSeq(n) = ex+1 Then PlaySound mvcExSnd
        If zBlowSeq(n) = a Or zBlowSeq(n) = a2 Then PlaySound juggEarthquakeSnd
        If (zBlowSeq(n) = d+2 Or zBlowSeq(n) = d2+2) And (yDist(n) <= 6 Or zOnGnd(n)=1) Then PlaySound mvcCrash2Snd
    End If
;===========================================

;================ Effects ==================
    If yDist(n) <= 6 Or zOnGnd(n)=1 Then
        If zBlowSeq(n) = d+2 Or zBlowSeq(n) = d+8 Or zBlowSeq(n) = d+16 Then quake=1:quakeSeq=0
        If zBlowSeq(n) = d2+2 Or zBlowSeq(n) = d2+8 Or zBlowSeq(n) = d2+16 Then quake=1:quakeSeq=0
        If zBlowSeq(n) = d+2 Then extraObj(n,zx(n),55,zy(n),3,zblowdir(n),113)
        If zBlowSeq(n) = d+8  Then
            dir=zface(n):y=zy(n)-(zheight(n)-55)
            If zface(n)=2 Then x=zx(n)+38
            If zface(n)=4 Then x=zx(n)-38
            makeshot(n,47,x,y,dir)
        End If
        If zBlowSeq(n) = d2+8 Or zBlowSeq(n) = d2+24 Or zBlowSeq(n) = d2+40 Then
            dir=zface(n):y=zy(n)-(zheight(n)-44)
            If zface(n)=2 Then x=zx(n)+42+((zBlowSeq(n)-1000)*3)
            If zface(n)=4 Then x=zx(n)-42-((zBlowSeq(n)-1000)*3)
            If zBlowSeq(n) = d2+8 And xDist(n)>105 Then makeshot(n,48,x,y,dir)
            If zBlowSeq(n) = d2+24 And xDist(n)>160 Then makeshot(n,48,x,y,dir)
            If zBlowSeq(n) = d2+40 And xDist(n)>220 Then makeshot(n,48,x,y,dir)
        End If
    End If
;===========================================
    If zBlowSeq(n) = g Or zBlowSeq(n) = g2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Punch 
    seq1=7:seq2=seq1+2:seq3=seq2+2:seq4=seq3+1:seq5=seq4+6:seq6=seq5+8:seq7=seq6+5
    endSeq=seq7+5:highKickSeq=50:crouchUpPunchSeq=100
    zNoMove(n)=1:zNoJump(n)=1
    
    If zBlowSeq(n)=1 And (leftKey(n)=1 Or rightKey(n)=1) Then
        zBlowSeq(n)=highKickSeq
    End If
    If zBlowSeq(n)=1 And zDuck(n)=1 Then zBlowSeq(n)=crouchUpPunchSeq
    
    If zBlowSeq(n)>=highKickSeq And zBlowSeq(n)<crouchUpPunchSeq Then doJuggHighKick(n)
    If zBlowSeq(n)>=crouchUpPunchSeq Then doJuggCrouchUpPunch(n, 20)
    
;========= Animation ===========
    If zBlowSeq(n)>0 And zBlowSeq(n)<seq1 Then zani(n)=14:zf(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<seq2 Then zani(n)=14:zf(n)=2
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<seq3 Then zani(n)=14:zf(n)=3
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<seq4 Then zani(n)=14:zf(n)=4
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<seq5 Then zani(n)=14:zf(n)=5
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<seq6 Then zani(n)=14:zf(n)=6
    If zBlowSeq(n)>seq6 And zBlowSeq(n)<seq7 Then zani(n)=14:zf(n)=7
    
;========= Sounds ===========
    If zBlowSeq(n)=seq1 And gameSound Then PlaySound juggLateralSnd
    If zBlowSeq(n)=seq3-1 And gameSound Then PlaySound mvcBlow3Snd
    
;========= Hitboxes ==========
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq6 Then
        zblowPamount(n)=2:nn=1
        zBlowBack(n)=1
        xblow(n,nn)=-15: yblow(n,nn)=75:wblow(n,nn)=40:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=45:hblow(n,nn)=10:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=4:zBlowStillTime(n)=4
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=5:zHitTime(n)=40
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=juggHit1Snd
    End If

    If zBlowSeq(n)>endSeq And zBlowSeq(n)<highKickSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-42:yed(n)=50
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=4 :eAni(n)=1:ef(n)=2:xed(n)=30:yed(n)=36
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=6
        xblow(n,nn)=68: yblow(n,nn)=4:wblow(n,nn)=42:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=28: yblow(n,nn)=26:wblow(n,nn)=98:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=28: yblow(n,nn)=16:wblow(n,nn)=98:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=28: yblow(n,nn)=36:wblow(n,nn)=95:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=28: yblow(n,nn)=46:wblow(n,nn)=85:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=77:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=12:zf(n)=5
        eAni(n)=1:ef(n)=3:xED(n)=85:yed(n)=-5
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=5 :eAni(n)=1:ef(n)=4:xed(n)=85:yed(n)=-5
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=5 :eAni(n)=1:ef(n)=4:xed(n)=85:yed(n)=-5
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
        zani(n)=12:zf(n)=1
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=50:yed(n)=38
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=50:yed(n)=38
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=50:yed(n)=38
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
    endSeq=100
    powerUpSeq=200
    
    If zBlowSeq(n) < endSeq Then doJuggHeadCrush(n)
    
    If zBlowSeq(n)>=endSeq And zBlowSeq(n) < powerUpSeq Then 
        zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    End If

Case 15 ;Juggernaut throw
    seq1=4:seq2=seq1+2:seq3=seq2+6:seq4=seq3+6
    seq5=seq4+18:seq6=seq5+6:seq7=seq6+6 ;48
    endSeq=49
    finishingPunchSeq=100
    zNoMove(n)=1:zNoJump(n)=1
    
    If zBlowSeq(n)=seq7+1 Then zBlowSeq(n)=finishingPunchSeq
    If zBlowSeq(n) >= finishingPunchSeq Then doJuggCrouchUpPunch(n, 4)
    If zBlowSeq(n)=1 Then moveRepeatTimes(n)=0
;======= Animation ========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=15:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=15:zf(n)=2
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=15:zf(n)=3
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=15:zf(n)=4
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=15:zf(n)=5
    
    If zBlowSeq(n) = seq7 Then moveRepeatTimes(n)=moveRepeatTimes(n)+1
;======= Grabbing =========
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq3 Then
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),10)
        If zGrabs(n)=1 Then 
            If gameSound Then PlaySound wolverineGrabSnd
            zBlowSeq(n)=seq4+1
        End If
    End If
    If zBlowSeq(n)=seq3+1 And zGrabs(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
    en=zGrabsThis(n)
    immuneToCollide(en)=1
    If zBlowSeq(n)=seq1+1 Then
        If zFace(n)=2 Then zx(en)=zx(n)+30
        If zFace(n)=4 Then zx(en)=zx(n)-30
    End If
    If zBlowSeq(n)=seq4+2 Or zBlowSeq(n)=seq7 Then zy(en)=zy(n)-30
    If zBlowSeq(n)=seq5+2 Then zy(en)=zy(n)+10
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq7 Then initParalysis(n, en, 1)
    If zBlowSeq(n) = seq5+2 Then zHitSeq(en)=0
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then 
        zani(en)=2
        If curGuy(en)=16 Then ;Piccolo
            zf(en)=10
        Else
            zf(en)=3
        End If
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=20:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=20:wblow(n,nn)=20:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=15:wblow(n,nn)=20:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=30:zBlowStillTime(n)=1:zBlowType(n)=1
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowBlockTime(n)=40
        zBlowSound(n)=mvcHit2Snd
    Else
        zani(en)=2:zf(en)=1
    End If
    
    If zBlowSeq(n) = seq7 And moveRepeatTimes(n) < 6 Then zBlowSeq(n)=seq4+1
    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<finishingPunchSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 16 ;Counter Key (Taunt and Power up)
    taunt1=80:endSeq=81
    taunt2=100
    randSeed=0
    zNoMove(n)=1
    zNoJump(n)=1:zJump(n)=0
    If zOnGnd(n)=0 Then zy(n)=zy(n)-2
    zani(n)=16
    
    If zBlowSeq(n)=1 Then
        randSeed=Rand(5)
        If randSeed<>1 Then zBlowSeq(n)=taunt2
    End If
    
    If zBlowSeq(n)>=taunt2 Then doJuggTaunt2(n)
    
;============ Animation =============
    If zBlowSeq(n)>=1 And zBlowSeq(n)<taunt1 Then 
        If zBlowSeq(n)<=2 Then zf(n)=1
        If zBlowSeq(n)>2 And ((zBlowSeq(n)-1) Mod 3) = 0 Then 
            If zf(n)=1 Then zf(n)=2 Else zf(n)=1
        End If
    End If
    
;============ Sounds ===============
    If zBlowSeq(n)=1 And gameSound Then PlaySound juggLateralSnd
    
    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<taunt2 Then 
        If zSuperBar(n)+5 > 100 Then
            If vsMode=1 Then zSuperBar(n) = 100
        Else
            If vsMode=1 Then zSuperBar(n)=zSuperBar(n)+5
        End If
        zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    End If

Case 17 ;Extra special key 
    seq1=2:seq2=seq1+20:seq3=seq2+5
    counterPunchSeq=50
    endSeq=seq3+1
    zNoMove(n)=1
    zNoJump(n)=1:zJump(n)=0
    If zOnGnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 And KeyDown(shotK(n))=1 Then 
        For nn=1 To zzamount
            If zControlsThese(n, nn)>0 Then
                en=zControlsThese(n, nn)
                If isDoingAttackMove(en)=1 And zBLowEffect(en)=1 Then zBlowSeq(n)=counterPunchSeq
            End If
        Next
    End If
    If zBlowSeq(n) >= counterPunchSeq Then doCounterPunchSeq(n)

;======= Animation ========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=18:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then 
        zani(n)=18:zf(n)=2:zshield(n)=1
        checkEnemy(n, zx(n)+3, zy(n)-39, 41, 5)
        checkEnemy(n, zx(n)+3, zy(n)-30, 65, 35)
    End If
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=18:zf(n)=1
    
;======= Sound =========
    If zBlowSeq(n)=seq1+1 And gameSound Then 
        PlaySound juggGrunt1Snd
        PlaySound mvcBlow3Snd
    End If
    
    If zBlowSeq(n)=seq1+1 Or zBlowSeq(n)=seq1+4 Then makechunk(n,zx(n),zy(n),zFace(n),146)
    
    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<counterPunchSeq Then flushKeys():zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
End Select

End Function