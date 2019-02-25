Function playPiccoloCooldownSnd(n)
    cdSeed=Rand(2)
    If cantSoundCdVoice(n)=0 Then
        cantSoundCdVoice(n)=1:cooldownVoiceSeq(n)=0
        If cdSeed=1 And gameSound And zAI(n)=0 Then 
            PlaySound piccoloCooldown1Snd
        Else If cdSeed=2 And gameSound And zAI(n)=0 Then
            PlaySound piccoloCooldown2Snd
        End If
    End If
    If gameSound And zAi(n)=0 Then PlaySound clockTickSnd
End Function

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
        xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=23:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=15: yblow(n,nn)=30:wblow(n,nn)=23:hblow(n,nn)=10:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0:zBlowStillTime(n)=0
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=1.5:zHitTime(n)=40
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=14:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzKneeHitSnd
    Else If zBlowSeq(n)>=seq4 And zBlowSeq(n)<seq5 Then
        zblowPamount(n)=1:nn=1
        xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=23:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=15: yblow(n,nn)=50:wblow(n,nn)=23:hblow(n,nn)=10:nn=nn+1
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
        zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=5:zHitTime(n)=40
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
            zHitmode(n)=2:zBlowHold(n)=16:zBlowStillTime(n)=0:zBlowType(n)=1
            zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=2:zHitTime(n)=20
            zBlowDamage(n)=dmg:zBLowEffect(n)=1:zEnemyImmuneTime(n)=7
            zBlowSound(n)=dbzKneeHitSnd:zBlowBlockTime(n)=40:zBlowTypeModulo(n)=5
            If zBlowSeq(n)>b-4 Then zHitmode(n)=0:zBlowHold(n)=0:zBlowType(n)=0:zEnemyImmuneTime(n)=20
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
    If zBlowSeq(n) >= b2 And zBlowSeq(n) < d2 Then 
        zNoGrav(n)=0
    Else
        zNoGrav(n)=1
    End If

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
            zBlowSeq(n)=a3:zy(n)=zy(n)+10
            If zFace(n)=2 Then zx(n)=zx(n)+15
            If zFace(n)=4 Then zx(n)=zx(n)-15
            zSpeed#(n)=0
        End If
    End If
    
    If zBlowSeq(n) >= a3 Then doBuukuKyaku2(n)
    
;----------- Sound -----------
    If zBlowSeq(n)=a1 And gameSound Then PlaySound piccoloBuukuKyakuSnd
    If zBlowSeq(n)=b1 And gameSound Then PlaySound piccoloGrunt1Snd
    
;--------- Movement ---------
    If zBlowSeq(n) >= d1 And zBlowSeq(n) < a2 Then moveX(n,zBlowdir(n),4.5):moveY(n,3)
    
End Function

Function doLongKick(n)
    seqStart=100:seqEnd=50
    seq1=seqStart+2:seq2=seq1+2:seq3=seq2+5:seq4=seq3+4:seq5=seq4+4:seq6=seq5+4:
    seq7=seq6+3:seq8=seq7+4:seq9=seq8+3
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=6:zf(n)=8
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=6:zf(n)=9
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=6:zf(n)=10
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=6:zf(n)=11
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=6:zf(n)=12
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=6:zf(n)=13
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=6:zf(n)=14
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=6:zf(n)=7
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq6 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=45:wblow(n,nn)=28:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=40:wblow(n,nn)=23:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=35:wblow(n,nn)=17:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit1Snd
    End If
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq1 And gameSound Then PlaySound piccoloGrunt7Snd
    
    If zBlowSeq(n)=seq9 Then zBlowSeq(n)=seqEnd
End Function

Function doChop(n)
    seqStart=300:seq1=seqStart+3:seq2=seq1+6:seq3=seq2+3:seq4=seq3+3:seq5=seq4+6:seq6=seq5+3
    seq7=seq6+3
    seqEnd=50
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=8:zf(n)=6
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=8:zf(n)=7
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=8:zf(n)=8
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=8:zf(n)=9
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=4:zf(n)=6
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=8:nn=1
        xblow(n,nn)=11: yblow(n,nn)=54:wblow(n,nn)=5:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=49:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=44:wblow(n,nn)=11:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=39:wblow(n,nn)=14:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=34:wblow(n,nn)=17:hblow(n,nn)=7:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=27:wblow(n,nn)=17:hblow(n,nn)=6:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=21:wblow(n,nn)=17:hblow(n,nn)=3:nn=nn+1
        xblow(n,nn)=11 yblow(n,nn)=17:wblow(n,nn)=14:hblow(n,nn)=3:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=16:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt7Snd
    
    If zBlowSeq(n)=seq7 Then zBlowSeq(n)=seqEnd
End Function

Function doPummel(n)
    seqStart=200:seq1=seqStart+3:seq2=seq1+7:seq3=seq2+4:seq4=seq3+10:
    seq5=seq4+6:seq6=seq5+3:seq7=seq6+3
    endSeq=50:
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=8:zf(n)=3
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=8:zf(n)=4
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=4:zf(n)=6
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt3Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
        zBlowBack(n)=1
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=-7: yblow(n,nn)=-7:wblow(n,nn)=7:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=-1: yblow(n,nn)=-2:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=3:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=4:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=5:zHitTime(n)=20
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
    If zBlowSeq(n)=seq7 Then zBlowSeq(n)=endSeq
    
End Function

Function doFlyKick2(n)
    seqStart=100:seq1=seqStart+2:seq2=seq1+5:seq3=seq2+4:seq4=seq3+12:
    seq5=seq4+4:seq6=seq5+5:seq7=seq6+5:seq7=seq6+3
    endSeq=50
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=12:zf(n)=13
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=12:zf(n)=14
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=12:zf(n)=20
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=12:zf(n)=14
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=12:zf(n)=13
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=4:zf(n)=4
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt7Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=-6: yblow(n,nn)=-15:wblow(n,nn)=7:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=-10:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=-5:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=18: yblow(n,nn)=0:wblow(n,nn)=8:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=18:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
    If zBlowSeq(n)=seq7 Then zBlowSeq(n)=endSeq
    
End Function

Function doFollowUpCombo(n, mode)
    seqStart=2000:seq1=seqStart+18:seq2=seq1+6:seq3=seq2+4:seq4=seq3+19:seq5=seq4+4
    seq6=seq5+3:seq7=seq6+3:seq8=seq7+3:seq9=seq8+3:seq10=seq9+3:seq11=seq10+3
    seq12=seq11+3:seq13=seq12+3:seq14=seq13+3:seq15=seq14+3:seq16=seq15+3:seq17=seq16+13
    seq18=seq17+3:seq19=seq18+3:seq20=seq19+3
    endSeq=50
    hitMode=2
    blowHold=20
    blowType=1
    zNoGrav(n)=1
    If mode = 0 Then tempZani=9:zf1=18:zf2=19:enemyPullSpd=1.5
    If mode = 1 Then tempZani=18:zf1=31:zf2=26:enemyPullSpd=2.9
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then hitMode=0:blowHold=3:blowType=0
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=tempZani:zf(n)=zf1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=tempZani:zf(n)=zf2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=18:zf(n)=9
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=18:zf(n)=10
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=18:zf(n)=11
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=18:zf(n)=12
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=18:zf(n)=13
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=18:zf(n)=14
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=18:zf(n)=15
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=18:zf(n)=16
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=18:zf(n)=17
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=18:zf(n)=18
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=18:zf(n)=19
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zani(n)=18:zf(n)=20
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then zani(n)=18:zf(n)=21
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then zani(n)=18:zf(n)=22
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq17 Then zani(n)=18:zf(n)=23
    If zBlowSeq(n) > seq17 And zBlowSeq(n) <= seq18 Then zani(n)=18:zf(n)=24
    If zBlowSeq(n) > seq18 And zBlowSeq(n) <= seq19 Then zani(n)=18:zf(n)=12
    If zBlowSeq(n) > seq19 And zBlowSeq(n) <= seq20 Then zani(n)=18:zf(n)=25
    
;--------- Sounds --------------
    If zBlowSeq(n) = seqStart+13 And gameSound Then PlaySound pullSnd
    If zBlowSeq(n) = seq15 And gameSound Then PlaySound piccoloGrunt8Snd
    If zBlowSeq(n) = seq15 And gameSound Then PlaySound dbzSuperKickSnd
    
;--------- Hit box ----------
    If (zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq9) Or (zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq13) Or (zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16) Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=35:wblow(n,nn)=35:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=30:wblow(n,nn)=39:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=39:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=hitMode:zBlowHold(n)=blowHold:zBlowStillTime(n)=0:zBlowType(n)=blowType
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40:zBlowTypeModulo(n)=9
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=12:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
;-------- Effects ----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) < seq2 Then
        enemyControlInit(n,zx(n)+15,zy(n)-4,93,8,0,guardable)
        unitCounter=1
        While zControlsThese(n,unitCounter) <> 0
            en=zControlsThese(n,unitCounter)
            If zFace(n)=2 And zBlowSeq(n) > seqStart+13 Then moveX(en,4,enemyPullSpd)
            If zFace(n)=4 And zBlowSeq(n) > seqStart+13 Then moveX(en,2,enemyPullSpd)
            isDizzy(en)=1:dizzyDuration(en)=1500
            freezeVictim(en, 0)
            zNoGrav(en)=1
            unitCounter=unitCounter+1
        Wend
    End If
        
    If zBlowSeq(n)=seq20 Then zBlowSeq(n)=endSeq
End Function

Function doHighExtendingPunch(n)
    seqStart=100:seq1=seqStart+6:seq2=seq1+8:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:
    seq6=seq5+3:seq7=seq6+3:seq8=seq7+3:seq9=seq8+3:seq10=seq9+3:seq11=seq10+3:
    seq12=seq11+3
    endSeq=50
    guardable=0
    followUpComboSeq=2000
    If zBlowSeq(n) >= followUpComboSeq Then doFollowUpCombo(n, 1)

;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=18:zf(n)=12
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=18:zf(n)=10
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=18:zf(n)=26
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=18:zf(n)=27
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=18:zf(n)=28
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=18:zf(n)=29
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=18:zf(n)=30
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=18:zf(n)=31
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=18:zf(n)=26
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=18:zf(n)=10
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=6:zf(n)=1
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq8 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=45: yblow(n,nn)=39:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=78: yblow(n,nn)=39:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=60: yblow(n,nn)=39:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=30: yblow(n,nn)=39:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=15: yblow(n,nn)=39:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=40:zBlowBlockTime(n)=40
        If zBlowStill(n)=1 Then isMoveHit(n)=1
        zBlowSound(n)=dbzHit2Snd
    End If

;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt1Snd
    
;------- Extra effects ---------
    If isMoveHit(n)=1 Then 
        If zFace(n)=2 Then xPos=15
        If zFace(n)=4 Then xPos=-15
        enemyControlInit(n,zx(n)+xPos,zy(n),93,21,0,guardable)
        unitCounter=1
        While zControlsThese(n,unitCounter) <> 0
            en=zControlsThese(n,unitCounter)
            If zBlock(en)=1 Then clearControlledPlayers(n):Goto skipPull
            zani(en)=2:zf(en)=2
            If zBlowSeq(n) = seq8 And isMoveHit(n)=1 Then
                If zControlsThis(n) <> 0 Then 
                    zBlowSeq(n)=followUpComboSeq:isMoveHit(n)=0
                End If
            End If
            alignVerticalPosOfTarget(en)
            If zFace(n) = 2 Then zx(en)=zx(n)+78
            If zFace(n) = 4 Then zx(en)=zx(n)-78
            unitCounter=unitCounter+1
        Wend
        .skipPull
    End If
    
    If zBlowSeq(n) > seq12 And zBlowSeq(n) < followUpComboSeq Then 
        If zControlsThis(n) = 0 Then zBlowSeq(n)=endSeq
    End If
End Function

Function doLowExtendingPunch(n)
    seqStart=100:seq1=seqStart+6:seq2=seq1+8:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:
    seq6=seq5+3:seq7=seq6+3:seq8=seq7+3:seq9=seq8+3:seq10=seq9+3:seq11=seq10+3:
    seq12=seq11+3
    endSeq=50
    guardable=0
    followUpComboSeq=2000
    If zBlowSeq(n) >= followUpComboSeq Then doFollowUpCombo(n, 0)

;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=9:zf(n)=10
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=9:zf(n)=11
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=9:zf(n)=12
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=9:zf(n)=13
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=9:zf(n)=14
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=9:zf(n)=15
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=9:zf(n)=16
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=9:zf(n)=17
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=9:zf(n)=18
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=9:zf(n)=19
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=9:zf(n)=20
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=9:zf(n)=21
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq8 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=45: yblow(n,nn)=24:wblow(n,nn)=6:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=78: yblow(n,nn)=24:wblow(n,nn)=6:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=60: yblow(n,nn)=24:wblow(n,nn)=6:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=30: yblow(n,nn)=24:wblow(n,nn)=6:hblow(n,nn)=8:nn=nn+1
        xblow(n,nn)=15: yblow(n,nn)=24:wblow(n,nn)=6:hblow(n,nn)=8:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=40:zBlowBlockTime(n)=40
        If zBlowStill(n)=1 Then isMoveHit(n)=1
        zBlowSound(n)=dbzHit2Snd
    End If

;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt1Snd
    
;------- Extra effects ---------
    If isMoveHit(n)=1 Then 
        If zFace(n)=2 Then xPos=15
        If zFace(n)=4 Then xPos=-15
        enemyControlInit(n,zx(n)+xPos,zy(n)-7,93,14,0,guardable)
        unitCounter=1
        While zControlsThese(n,unitCounter) <> 0
            en=zControlsThese(n,unitCounter)
            If zBlock(en)=1 Then clearControlledPlayers(n):Goto skipPull
            zani(en)=2:zf(en)=2
            If zBlowSeq(n) = seq8 And isMoveHit(n)=1 Then
                If zControlsThis(n) <> 0 Then 
                    zBlowSeq(n)=followUpComboSeq:isMoveHit(n)=0
                End If
            End If
            alignVerticalPosOfTarget(en)
            If zFace(n) = 2 Then zx(en)=zx(n)+78
            If zFace(n) = 4 Then zx(en)=zx(n)-78
            unitCounter=unitCounter+1
        Wend
        .skipPull
    End If
    
    If zBlowSeq(n) = seq12 And zControlsThis(n) = 0 Then zBlowSeq(n)=endSeq
End Function

Function doMakuuHouidan(n)
    seqStart=1000:seq1=seqStart+3:seq2=seq1+5:seq3=seq2+10:seq4=seq3+10:seq5=seq4+5
    seq6=seq5+10:seq7=seq6+5:seq8=seq7+10
    seq9=seq8+118
    seq10=seq9+16:seq11=seq10+6:seq12=seq11+20:seq13=seq12+4:seq14=seq13+50
    seq15=seq14+4:seq16=seq15+4:seq17=seq16+4:seq18=seq17+3
    endSeq=900
    zNoMove(n)=1:zNoJump(n)=1
    zNoGrav(n)=1
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=18:zf(n)=8
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=17:zf(n)=1
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=17:zf(n)=2
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=17:zf(n)=3
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=17:zf(n)=4
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=17:zf(n)=3
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=17:zf(n)=4
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=17:zf(n)=3
    
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9
        If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq8+3 Then
            zani(n)=18:zF(n)=8
        Else
            If zani(n)=18 And zF(n)=8 Then 
                seqMod1=zBlowSeq(n)+4
                seqMod2=zBlowSeq(n)-5
                If (seqMod1 Mod 22 = 0) Then ;2
                    zAni(n)=10:zf(n)=2
                    extraObj(n,zx(n),5,zy(n),-20,zFace(n),129)
                End If
                If (seqMod2 Mod 22 = 0) Then ;4
                    zAni(n)=18:zf(n)=12
                    extraObj(n,zx(n),-8,zy(n),-29,zFace(n),129)
                End If
            Else If zani(n)=10 And zF(n)=2 Then
                seqMod=zBlowSeq(n)-1
                If (seqMod Mod 22=0) Then zAni(n)=10:zf(n)=3 ;3
            Else If (zani(n)=10 And zF(n)=3) Or (zani(n)=17 And zF(n)=5) Then 
                seqMod1=zBlowSeq(n)-4
                seqMod2=zBlowSeq(n)+7
                If (seqMod1 Mod 22=0) Or (seqMod2 Mod 22=0) Then zani(n)=18:zF(n)=8 ;1
            Else If zani(n)=18 And zF(n)=12 Then 
                seqMod=zBlowSeq(n)+10
                If (seqMod Mod 22=0) Then zAni(n)=17:zf(n)=5 ;5
            End If
        End If
    End If
    
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=18:zF(n)=9
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=17:zF(n)=1
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=17:zF(n)=6
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=17:zF(n)=7
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zani(n)=17:zF(n)=8
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then zani(n)=17:zF(n)=9
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then zani(n)=17:zF(n)=10
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq17 Then zani(n)=18:zF(n)=12
    If zBlowSeq(n) > seq17 And zBlowSeq(n) <= seq18 Then zani(n)=18:zF(n)=8
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq4 And gameSound Then PlaySound piccoloGrunt9Snd
    If zBlowSeq(n) = seq4 And gameSound Then PlaySound dbzChargeSnd
    If zBlowSeq(n) = seq11 And gameSound Then PlaySound piccoloKiCtrlSnd
    If zBlowSeq(n) = seq13 And gameSound Then PlaySound piccoloKutabare2Snd
    If (zani(n)=10 And zF(n)=3) Or (zani(n)=17 And zF(n)=5) Then 
        If ((zBlowSeq(n)-1) Mod 22=0) Or ((zBlowSeq(n)+10) Mod 22=0) Then
            If gameSound Then PlaySound piccoloKiSnd
        End If
    End If
    
;--------- Effects -------------
    If zBlowSeq(n) = seq8 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0:superMoveMaxSeq(n)=211
    
    If (zani(n)=10 And zF(n)=3) Or (zani(n)=17 And zF(n)=5) Then 
        If ((zBlowSeq(n)-1) Mod 22=0) Or ((zBlowSeq(n)+10) Mod 22=0) Then
            y=zy(n)-zheight(n)+30
            If zFace(n)=2 Then x=zx(n)+30
            If zFace(n)=4 Then x=zx(n)-30
            makeshot(n,51,x,y,zface(n))
            zSuperBar(n)=zSuperBar(n)-11
        End If
    End If
    
    If zBlowSeq(n)=seq13+1 Then getShots(n):doApplySeekingBalls(n)
    
;----------- Chunks ------------
    If zBlowSeq(n)=seq3+1 Then ;seq 1019
        If zFace(n)=2 Then makechunk(n,zx(n)+7,zy(n)+4,zFace(n),127)
        If zFace(n)=4 Then makechunk(n,zx(n)-7,zy(n)+4,zFace(n),127)
    End If
    
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq8 Then 
        extraObj(n,zx(n),21,zy(n),-15,zFace(n),129)
        extraObj(n,zx(n),-8,zy(n),-15,zFace(n),129)
    End If
;-------------------------------
        
    If zBlowSeq(n) = seq18+1 Then zBlowSeq(n)=endSeq
End Function

Function doApplySeekingBalls(n)
    Local numOfShots=9
    For i=0 To numOfShots
        shotDuration(myShots(n, i))=200
        shotSeekType(myShots(n, i))=seekTypeFull
        xSpdRand#=rand(20)
        shotSeekSpeed#(myShots(n, i))=4
    Next
End Function

Function doMakankousappou(n)
    seqStart=0:seq1=seqStart+3:seq2=seq1+5:seq3=seq2+10:seq4=seq3+3:seq5=seq4+25 ;46
    seq6=seq5+3:seq7=seq6+3:seq8=seq7+29 ;81
    seq9=seq8+1:seq10=seq9+3:seq11=seq10+3:seq12=seq11+3:seq13=seq12+3 ;94
    seq14=seq13+33 ;127
    seq15=seq14+3:seq16=seq15+3:seq17=seq16+3:seq18=seq17+5:seq19=seq18+24 ;165
    seq20=seq19+34:seq21=seq20+3:seq22=seq21+3 ;205
    
    zNoMove(n)=1:zNoJump(n)=1
    zNoGrav(n)=1
    endSeq=900
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=18:zf(n)=11
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=17:zf(n)=1
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=17:zf(n)=2
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=17:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=17:zf(n)=3
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=17:zf(n)=4
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=17:zf(n)=11
    
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 And zAni(n)=17 Then
        If ((zBlowSeq(n)+2) Mod 6 = 0) And zF(n)=13 Then 
            zF(n)=11 ;1
        Else If ((zBlowSeq(n)+1) Mod 6 = 0) And zF(n)=11 Then 
            zF(n)=12 ;2
        Else If ((zBlowSeq(n)+0) Mod 6 = 0) And zF(n)=12 Then 
            zF(n)=13 ;3
        Else If ((zBlowSeq(n)-1) Mod 6 = 0) And zF(n)=13 Then 
            zF(n)=4  ;4
        Else If ((zBlowSeq(n)-2) Mod 6 = 0) And zF(n)=4  Then 
            zF(n)=12 ;5
        Else If ((zBlowSeq(n)-3) Mod 6 = 0) And zF(n)=12 Then 
            zF(n)=13 ;6
        End If
    End If
    
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=17:zf(n)=11
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=18:zf(n)=11
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=18:zf(n)=12
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=12:zf(n)=1
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=12:zf(n)=2

    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 And zAni(n)=12 Then
        If ((zBlowSeq(n)+1) Mod 4 = 0) And zF(n)=4 Then 
            zf(n)=3 ;1
        Else If ((zBlowSeq(n)+0) Mod 4 = 0) And zF(n)=3 Then 
            zF(n)=4 ;2
        Else If ((zBlowSeq(n)-1) Mod 4 = 0) And zF(n)=4 Then 
            zf(n)=5 ;3
        Else If ((zBlowSeq(n)-2) Mod 4 = 0) And zF(n)=5 Then 
            zf(n)=4 ;4
        End If
    End If
    
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then zani(n)=17:zf(n)=14
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then zani(n)=17:zf(n)=15
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq17 Then zani(n)=17:zf(n)=16
    If zBlowSeq(n) > seq17 And zBlowSeq(n) <= seq18 Then zani(n)=17:zf(n)=17
    If zBlowSeq(n) > seq18 And zBlowSeq(n) <= seq19 Then zani(n)=17:zf(n)=18
    If zBlowSeq(n) > seq19 And zBlowSeq(n) <= seq20 Then zani(n)=17:zf(n)=18
    If zBlowSeq(n) > seq20 And zBlowSeq(n) <= seq21 Then zani(n)=18:zf(n)=12
    If zBlowSeq(n) > seq21 And zBlowSeq(n) <= seq22 Then zani(n)=18:zf(n)=11
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq7+1 And gameSound Then 
        PlaySound piccoloGrunt9Snd
        PlaySound dbzChargeSnd
        PlaySound dbzAuraSnd
    End If
    
    If zBlowSeq(n) = seq7 And gameSound Then PlaySound dbzHyperSnd
    If zBlowSeq(n) = seq13+1 And gameSound Then PlaySound piccoloMakkankousappouSnd
    If zBlowSeq(n) = seq15+1 And gameSound Then PlaySound dbzKiHyperAttackSnd
    
;----------- Chunks ------------
    If zBlowSeq(n)=seq3+1 Then ;seq 1019
        If zFace(n)=2 Then makechunk(n,zx(n)+5,zy(n)+4,zFace(n),127)
        If zFace(n)=4 Then makechunk(n,zx(n)-5,zy(n)+4,zFace(n),127)
    End If
    
    If zBlowSeq(n) = seq17+1 Then 
        If zFace(n)=2 Then makechunk(n,zx(n)+79,zy(n)-25,zFace(n),131)
        If zFace(n)=4 Then makechunk(n,zx(n)-79,zy(n)-25,zFace(n),131)
    End If

;---------- Effects ------------
    If zBlowSeq(n) = 1 Then 
        checkDist(n,zx(n),zy(n)-23,zFace(n))
        zSuperMove(n)=1:zSuperMoveSeq(n)=0:superMoveMaxSeq(n)=135:superMovePortraitSeqStart(n)=80
    End If
    
    If zBlowSeq(n) > seq17+4 And zBlowSeq(n) <= seq20 Then
        If rendert = 2 Then checkDist(n,zx(n),zy(n)-23,zFace(n))
        
        If zBlowSeq(n) Mod 3 = 0 And zBlowSeq(n) Mod 2 = 0 Then drawWavePart1(n)
        If zBlowSeq(n) Mod 3 = 0 And zBlowSeq(n) Mod 2 = 1 Then drawWavePart2(n)
        
        If zBlowSeq(n) < seq22-15 Then hm=2 Else hm=0
        
        If zface(n)=2 Then
            makeRectHit(n,zx(n)+61,zy(n)-32,xDist(n),7,zFace(n),hm,2,.5,14,6,95,dbzPierceSnd)
            If zBlowSeq(n) Mod 24=0 Then makeChunk(n,zx(n) + xDist(n)-5, zy(n)-24,2,122)
            makechunk(n,zx(n)+53,zy(n)-32,zFace(n),132)
        Else
            makeRectHit(n,zx(n)-(xDist(n)+61),zy(n)-32,xDist(n),7,zFace(n),hm,2,.5,14,6,95,dbzPierceSnd)
            If zBlowSeq(n) Mod 24=0 Then makeChunk(n,zx(n) - xDist(n)-5, zy(n)-24,4,122)
            makechunk(n,zx(n)-53,zy(n)-32,zFace(n),132)
        EndIf
    End If
    
    If zBlowSeq(n) > seq22 Then zBlowSeq(n)=endSeq
    
End Function

Function drawWavePart1(n)
    For x=1 To (xDist(n)-57) Step 8
        If zFace(n)=2 Then
            makeChunk(n,Int(zx(n)) + x +60, zy(n)-32,2,133)
        Else
            makeChunk(n,Int((zx(n))- x)-60, zy(n)-32,2,133)
        EndIf
    Next
        
    For x=4 To (xDist(n)-57) Step 8
        If zFace(n)=2 Then
            makeChunk(n,Int(zx(n)) + x +60, zy(n)-32,2,134)
        Else
            makeChunk(n,Int((zx(n))- x)-60, zy(n)-32,2,134)
        EndIf
    Next
End Function

Function drawWavePart2(n)
    For x=1 To (xDist(n)-57) Step 8
        If zFace(n)=2 Then
            makeChunk(n,Int(zx(n)) + x +60, zy(n)-32,2,135)
        Else
            makeChunk(n,Int((zx(n))- x)-60, zy(n)-32,2,135)
        EndIf
    Next
        
    For x=5 To (xDist(n)-57) Step 8
        If zFace(n)=2 Then
            makeChunk(n,Int(zx(n)) + x +60, zy(n)-32,2,136)
        Else
            makeChunk(n,Int((zx(n))- x)-60, zy(n)-32,2,136)
        EndIf
    Next
End Function

Function doGohanCounter(n)
    seqStart=3000:seq1=seqStart+4:seq2=seq1+1:seq3=seq2+1:seq4=seq3+1:seq5=seq4+1:seq6=seq5+1:seq7=seq6+1
    seq8=seq7+3:seq9=seq8+1:seq10=seq9+9:seq11=seq10+3:seq12=seq11+3:seq13=seq12+4:seq14=seq13+2
    seq15=seq14+2:seq16=seq15+2
    seq17=seq16+4:seq18=seq17+20:seq19=seq18+6:seq20=seq19+3
    endSeq=50
    
    If zBlowSeq(n)=1 And zSuperBar(n) < 15 Then zBlowSeq(n)=0:zBlow(n)=0
    
    If zBlowSeq(n)=seqStart+1 And spellCooldownSeq(n, 2) > 0 Then
        playPiccoloCooldownSnd(n)
        zBlowSeq(n)=0:zBlow(n)=0
    End If

;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=18:zf(n)=32
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=18:zf(n)=33
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=18:zf(n)=32
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=18:zf(n)=33
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=18:zf(n)=32
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=18:zf(n)=33
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=18:zf(n)=32
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=18:zf(n)=33
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=18:zf(n)=34
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=18:zf(n)=35
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=18:zf(n)=35
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=18:zf(n)=33
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=12:zf(n)=13
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then zani(n)=4:zf(n)=6
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then zani(n)=18:zf(n)=37
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq17 Then zani(n)=16:zf(n)=6
    If zBlowSeq(n) > seq17 And zBlowSeq(n) <= seq18 Then zani(n)=16:zf(n)=7
    If zBlowSeq(n) > seq18 And zBlowSeq(n) <= seq19 Then zani(n)=16:zf(n)=8
    If zBlowSeq(n) > seq19 And zBlowSeq(n) <= seq20 Then zani(n)=16:zf(n)=9
    
;----------- Sounds -------------
    If zBlowSeq(n) = seqStart+1 And gameSound Then PlaySound dbzTeleSnd
    If zBlowSeq(n) = seq17+1 And gameSound Then PlaySound piccoloGohanSnd
    If zBlowSeq(n) = seq18 And gameSound Then PlaySound dbzTeleSnd
    If zBlowSeq(n) = seq20 And gameSound Then PlaySound piccoloGohanHaSnd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq10 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=35:wblow(n,nn)=35:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=30:wblow(n,nn)=39:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=39:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=1:zBlowStillTime(n)=1
        zHitSpeed#(n)=3.5:zHitUpSpeed#(n)=5:zHitDownSpeed#(n)=0:zHitTime(n)=110
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=40:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit3Snd
        If zBlowStill(n)=1 Then isMoveHit(n)=1
        If zBlowSeq(n)=seq8 Then zSuperBar(n)=zSuperBar(n)-15
    End If
    
;----------- Effects ------------
    If zFace(n)=2 Then face=4:x=zx(n)+130
    If zFace(n)=4 Then face=2:x=zx(n)-130
    If zBlowSeq(n) = seq18 And isMoveHit(n)=1 Then 
        spellCooldownMaxTime(n, 2)=2080
        spellCooldownSeq(n, 2)=spellCooldownMaxTime(n, 2) 
        makechunk(n,x,zy(n)-20,face,138)
    End If

    If zBlowSeq(n) >= seq20 Then zBlowSeq(n)=endSeq
End Function

Function doPiccoloThrow(n)
    seq1=8: seq2=15: seq3=25: seq4=30: seq5=35: seq6=50: seq7=54: seq8=58: seq9=62
    zNoMove(n)=1:zNoJump(n)=1
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) => seq2 And zBlowSeq(n) =< seq3 Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) = seq1 Then
        If gameSound Then PlaySound grabSnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=seq3+4
    EndIf
    If zBlowSeq(n)=seq2 Then zBlowSeq(n)=0:zBlow(n)=0
    
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=2:dir2=2:n1=1:n2=28:n3=56    Else dir=4:dir2=4:n1=-1:n2=-28:n3=-56
    
    If zBlowSeq(n) > seq3 And zBlowSeq(n) < seq4 Then 
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=seq4+2
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
                zHitTime(en)=0:zFallTime(en)=0:zHitSeq(en)=0
                zgrabsThis(n)=0
                zgrabs(n)=0
                zBlowSeq(n)=seq9
            EndIf
        EndIf
    EndIf
        
    If zBlowSeq(n) > seq4 And zBlowSeq(n) < seq9 Then zshield(n)=1
    If zBlowSeq(n) >= seq4 And zBlowSeq(n) < seq5 Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n3:zAni(en)=2:zf(en)=1:zFace(en)=dir
    If zBlowSeq(n) >= seq5 And zBlowSeq(n) < seq6 Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-48:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zBlowSeq(n) >= seq6 And zBlowSeq(n) < seq7 Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n2:zy(en)=zy(n)-12:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zblowseq(n) = seq6 Then
        If gameSound Then PlaySound piccoloGrunt8Snd
    EndIf
    If zBlowSeq(n) >= seq9-3 And zBlowSeq(n) < seq9 Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n2:zy(en)=zy(n)-3:zAni(en)=2:zf(en)=6:zface(en)=dir2
    If zBlowSeq(n) = seq9  Then
        zx(en)=zx(n)+n2:zy(en)=zy(n)-3
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=0:zDownFallSpeed(en)=5:zUpFallSpeed(en)=0:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=dir
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    If zBlowSeq(n) > seq7 And zBlowSeq(n) < seq8 Then zani(n)=15:zf(n)=5
    If zBlowSeq(n) >= seq8 And zBlowSeq(n) < seq9 Then zani(n)=15:zf(n)=6
    
    If zBlowSeq(n) > seq3 And zBlowSeq(n) < seq7 Then zgrabbed(en)=1:checkZvsWall(en,0)
    If zBlowSeq(n) >= seq9 Then zBlowSeq(n)=0:zBlow(n)=0
End Function

Function doPiccoloCombo(n)
    seqStart=200
    endSeq=50
    seq1=seqStart+3:seq2=seq1+3:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:seq6=seq5+3
    seq7=seq6+4:seq8=seq7+5:seq9=seq8+5:seq10=seq9+3
    seq11=seq10+3:seq12=seq11+2:seq13=seq12+3:seq14=seq13+2:seq15=seq14+3:seq16=seq15+2
    seq17=seq16+3:seq18=seq17+2:seq19=seq18+3:seq20=seq19+2:seq21=seq20+3:seq22=seq21+2
    seq23=seq22+3:seq24=seq23+7:seq25=seq24+4:seq26=seq25+4:seq27=seq26+5:seq28=seq27+5:
    seq29=seq28+5:seq30=seq29+3

;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=18:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=22:zf(n)=1
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=22:zf(n)=2
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=22:zf(n)=3
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=22:zf(n)=4
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=18:zf(n)=1
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=22:zf(n)=5
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=22:zf(n)=6
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=22:zf(n)=7
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=18:zf(n)=1
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=22:zf(n)=8
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=22:zf(n)=9
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=22:zf(n)=10
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zani(n)=22:zf(n)=11
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then zani(n)=22:zf(n)=8
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then zani(n)=18:zf(n)=1
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq17 Then zani(n)=22:zf(n)=8
    If zBlowSeq(n) > seq17 And zBlowSeq(n) <= seq18 Then zani(n)=22:zf(n)=12
    If zBlowSeq(n) > seq18 And zBlowSeq(n) <= seq19 Then zani(n)=22:zf(n)=13
    If zBlowSeq(n) > seq19 And zBlowSeq(n) <= seq20 Then zani(n)=22:zf(n)=14
    If zBlowSeq(n) > seq20 And zBlowSeq(n) <= seq21 Then zani(n)=22:zf(n)=8
    If zBlowSeq(n) > seq21 And zBlowSeq(n) <= seq22 Then zani(n)=18:zf(n)=1
    If zBlowSeq(n) > seq22 And zBlowSeq(n) <= seq23 Then zani(n)=22:zf(n)=8
    If zBlowSeq(n) > seq23 And zBlowSeq(n) <= seq24 Then zani(n)=22:zf(n)=15
    If zBlowSeq(n) > seq24 And zBlowSeq(n) <= seq25 Then zani(n)=22:zf(n)=16
    If zBlowSeq(n) > seq25 And zBlowSeq(n) <= seq26 Then zani(n)=22:zf(n)=17
    If zBlowSeq(n) > seq26 And zBlowSeq(n) <= seq27 Then zani(n)=22:zf(n)=18
    If zBlowSeq(n) > seq27 And zBlowSeq(n) <= seq28 Then zani(n)=22:zf(n)=19
    If zBlowSeq(n) > seq28 And zBlowSeq(n) <= seq29 Then zani(n)=22:zf(n)=8
    If zBlowSeq(n) > seq29 And zBlowSeq(n) <= seq30 Then zani(n)=18:zf(n)=9
    
;--------- Hit box ----------
    If zBlowSeq(n)=seq3 Or zBlowSeq(n)=seq7 Then isMoveHit(n)=0
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq5 Or zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq9 Then
        If zBlowStill(n)=1 Then isMoveHit(n)=1
        If (zBlowSeq(n)=seq5) And (isMoveHit(n)=0 Or isAttackKeyDown(n)=0) Then zBlowSeq(n)=endSeq
        If (zBlowSeq(n)=seq9) And (isMoveHit(n)=0 Or isAttackKeyDown(n)=0) Then zBlowSeq(n)=endSeq
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=35:wblow(n,nn)=25:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=30:wblow(n,nn)=30:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=30:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=16:zBlowStillTime(n)=1:zBlowType(n)=1
        zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitDownSpeed(n)=0:zHitTime(n)=40
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=13:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit4Snd
    End If
   
    If zBlowSeq(n)=seq12 Then isMoveHit(n)=0
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq14 Then
        If zBlowStill(n)=1 Then isMoveHit(n)=1
        If (zBlowSeq(n)=seq14) And (isMoveHit(n)=0 Or isAttackKeyDown(n)=0) Then zBlowSeq(n)=endSeq
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=20:wblow(n,nn)=10:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=15:wblow(n,nn)=30:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=3: wblow(n,nn)=30:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=16:zBlowStillTime(n)=1:zBlowType(n)=1
        zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitDownSpeed(n)=0:zHitTime(n)=40
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=12:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit1Snd
    End If
    
    If (zBlowSeq(n)>seq18 And zBlowSeq(n)<=seq20) Or (zBlowSeq(n)>seq25 And zBlowSeq(n)<=seq27) Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=45:wblow(n,nn)=28:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=40:wblow(n,nn)=23:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=35:wblow(n,nn)=17:hblow(n,nn)=5:nn=nn+1
    End If
    
    If zBlowSeq(n)=seq18 Then isMoveHit(n)=0
    If zBlowSeq(n)>seq18 And zBlowSeq(n)<=seq20 Then
        If zBlowStill(n)=1 Then isMoveHit(n)=1
        If (zBlowSeq(n)=seq20) And (isMoveHit(n)=0 Or isAttackKeyDown(n)=0) Then zBlowSeq(n)=endSeq
        zHitmode(n)=2:zBlowHold(n)=28:zBlowStillTime(n)=1:zBlowType(n)=1
        zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitDownSpeed(n)=0:zHitTime(n)=40
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit1Snd
    End If
    
    If zBlowSeq(n)>seq25 And zBlowSeq(n)<=seq27 Then
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6:zBlowType(n)=0
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitDownSpeed(n)=0:zHitTime(n)=40
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit3Snd
    End If
    
;----------- Sounds -------------
    If zBlowSeq(n)=seq3 Or zBlowSeq(n)=seq12 Or zBlowSeq(n)=seq7 Then 
        If gameSound Then PlaySound blow3Snd
    End If
    If zBlowSeq(n)=seq18 And gameSound Then PlaySound blow4Snd
    If zBlowSeq(n) = seq3 And gameSound Then PlaySound piccoloGrunt5Snd
    If zBlowSeq(n) = seq7 And gameSound Then PlaySound piccoloGrunt3Snd
    If zBlowSeq(n) = seq12 Or zBlowSeq(n)=seq18 And gameSound Then PlaySound piccoloGrunt4Snd
    If zBlowSeq(n) = seq24 And gameSound Then PlaySound piccoloGrunt1Snd

    If zBlowSeq(n) > seq30 Then zBlowSeq(n)=endSeq

End Function

Function DoPiccolo(n)
initMoveStates(n)
zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq3
    EndIf
zBlowSeq(n)=zBlowSeq(n)+1
.noBlowSeq3

zchunkType(n)=20

Select zCurBlow(n)
Case 0    ;Blocking
    ;zSuperBar(n)=100
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    
    If zBlocked(n)=1 And KeyDown(grabK(n))=1 Then 
        isCounterAttack(n)=1:zBlowSeq(n)=0:zBlow(n)=0:zBlocked(n)=0
    End If
    
    If blockKey(n)=0 And zBlocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1    ;Normal Punch
    seq1=3:seq2=seq1+3:seq3=seq2+2:seq4=seq3+7:seq5=seq4+2:seq6=seq5+5:seq7=seq6+3
    comboSeq=200
    kickSeq=100
    seqEnd=50
    zNoMove(n)=1
    zNoJump(n)=1
    
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowSeq(n) = 1 And isRunning(n) Then zBlowSeq(n)=comboSeq
    If zBlowSeq(n) >= comboSeq Then doPiccoloCombo(n)
    If zBlowSeq(n)=1 And isRunning(n)=0 And (rightKey(n)=1 Or leftKey(n)=1) Then zBlowSeq(n)=kickSeq
    If zBlowSeq(n) >= kickSeq And zBlowSeq(n) < comboSeq Then doLongKick(n)
    
;--------- Animation -----------
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=6:zf(n)=7
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq1 And gameSound Then PlaySound piccoloGrunt3Snd

;--------- Hit box ----------
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=35:wblow(n,nn)=25:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=30:wblow(n,nn)=30:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=25:wblow(n,nn)=30:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
    If zBlowSeq(n)=seq7 Then zBlowSeq(n)=seqEnd
    If zBlowSeq(n)=seqEnd Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 2    ;Flying Kick
    seq1=2:seq2=seq1+5:seq3=seq2+3:seq4=seq3+10:seq5=seq4+5:seq6=seq5+4
    flyKick2Seq=100
    pummelSeq=200
    chopSeq=300
    endSeq=50
    If zBlowSeq(n)=1 And downKey(n)=1 Then zBlowSeq(n)=pummelSeq
    If zBlowSeq(n)=1 And upKey(n)=1 Then zBlowSeq(n)=chopSeq
    If zBlowSeq(n)=1 And (leftKey(n)=1 Or RightKey(n)=1) Then zBlowSeq(n)=flyKick2Seq
    If zBlowSeq(n) >= flyKick2Seq Then doFlyKick2(n)
    If zBlowSeq(n) >= pummelSeq Then doPummel(n)
    If zBlowSeq(n) >= chopSeq Then doChop(n)
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=4:zf(n)=4
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=12:zf(n)=20
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=12:zf(n)=17
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=12:zf(n)=13
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=4:zf(n)=4
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt7Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=-5: yblow(n,nn)=-15:wblow(n,nn)=7:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=-10:wblow(n,nn)=7:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=9: yblow(n,nn)=-5:wblow(n,nn)=7:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=16: yblow(n,nn)=0:wblow(n,nn)=7:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
    If zBlowSeq(n)=seq6 Then zBlowSeq(n)=endSeq
    If zBlowSeq(n)=endSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 4    ;Low kick
    seq1=3:seq2=seq1+7:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:seq6=seq5+3:seq7=seq6+3
    seq8=seq7+3:seq9=seq8+2:seq10=seq9+3
    extendingPunchSeq=100
    followUpComboSeq=2000
    endSeq=50
    zNoMove(n)=1:zNoJump(n)=1
    
    If zBlowSeq(n) = 1 And (leftKey(n)=1 Or RightKey(n)=1) Then zBlowSeq(n)=extendingPunchSeq
    If zBlowSeq(n) >= extendingPunchSeq < followUpComboSeq Then doLowExtendingPunch(n)
    If zBlowSeq(n) >= followUpComboSeq Then doFollowUpCombo(n, 0)
    
;--------- Animation -----------
    If zBlowSeq(n) > seqStart And zBlowSeq(n) <= seq1 Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=9:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=9:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=9:zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=9:zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=9:zf(n)=7
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=9:zf(n)=8
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=9:zf(n)=9
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=9:zf(n)=10
    
;--------- Sounds --------------
    If zBlowSeq(n) = seq2 And gameSound Then PlaySound piccoloGrunt7Snd
    
;--------- Hit box ----------
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq5 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=7:wblow(n,nn)=17:hblow(n,nn)=7:nn=nn+1
        xblow(n,nn)=16: yblow(n,nn)=7:wblow(n,nn)=17:hblow(n,nn)=7:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=17:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=16: yblow(n,nn)=0:wblow(n,nn)=17:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=8:zBlowStillTime(n)=6
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=40
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit2Snd
    End If
    
    If zBlowSeq(n)=seq6 Then zBlowSeq(n)=endSeq
    If zBlowSeq(n)=endSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 5    ;UP + SPECIAL (jumping knee lift)
    a=3:b=6:c=9:d=12:e=16:f=20:g=34:h=g+7:i=h+8:
    seq1=100:endSeq=seq1+200
    zNoMove(n)=1:zNoGrav(n)=1
    zNoJump(n)=1:zJumping(n)=0
    If zBlowSeq(n)=1 Then zBlowUpLimit(n)=zy(n)-70:zJump(n)=0
;--------- Animation -----------
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
        zblowPamount(n)=6:nn=1
        xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=15:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=32:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=39:wblow(n,nn)=18:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=26:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=53:wblow(n,nn)=26:hblow(n,nn)=5:nn=nn+1
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
    
    If zongnd(n)=1 And zBlowSeq(n) >= i-2 Then 
        zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    End If
    
Case 6    ;throwing iten
    a=3:b=6:c=9
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7    ; Seeking ki blast (special)
    a=3:b=15:c=45:d=48:e=51
    zNoMove(n)=1:zNoJump(n)=1:zjump(n)=0
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zongnd(n)=0 And canAirGlideUp(n)=0 Then zy(n)=zy(n)-2

    If zBlowSeq(n)=1 And spellCooldownSeq(n, 3) > 0 Then
        playPiccoloCooldownSnd(n)
        zBlowSeq(n)=0:zBlow(n)=0
    End If
    
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
        spellCooldownMaxTime(n, 3)=104
        spellCooldownSeq(n, 3)=spellCooldownMaxTime(n, 3) 
    End If
    
;---------- Sounds --------------
    If zBlowSeq(n)=b And gameSound Then PlaySound piccoloGrunt1Snd
    If zBlowSeq(n)=b+1 And gameSound Then PlaySound piccoloKiSnd
    
    If zBlowSeq(n)>e Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    

Case 8    ;Dodging
    a=7:b=15:c=20:d=25:e=30:f=37
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) = a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ; Kaikousen (down special) / BuukuKyaku
    a=3:b=6:c=9:d=12:e=15:f=18:g=21:h=24:i=27:j=39:k=42:l=45
    a2=100:a3=20000
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n)=1 And zOnGnd(n)=0 Then zBlowSeq(n)=a2
    If zBlowSeq(n) > a2 And zBlowSeq(n) < a3 Then doBuukuKyaku(n)
    If zBlowSeq(n) >= a3 Then doBuukuKyaku2(n)
    If zBlowSeq(n) <= l Then
        If zBlowSeq(n)=1 And spellCooldownSeq(n, 1) > 0 Then
            playPiccoloCooldownSnd(n)
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
            y=zy(n)-(zheight(n)-51)
            If zface(n)=2 Then x=zx(n)+36
            If zface(n)=4 Then x=zx(n)-100
            makeshot(n,49,x,y,zface(n))
            spellCooldownMaxTime(n, 1)=100
            spellCooldownSeq(n, 1)=spellCooldownMaxTime(n, 1) 
        End If
    End If

    If zBlowSeq(n) > l And zBlowSeq(n) < a2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Punch
    seq1=4:seq2=seq1+2:seq3=seq2+2:seq4=seq3+10:seq5=seq4+3:seq6=seq5+3:seq7=seq6+3
    zNoMove(n)=1
    zNoJump(n)=1
    
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
        zBlowDamage(n)=14:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowBlockTime(n)=40
        zBlowSound(n)=dbzHit1Snd
    End If

;------------ Sounds ----------------
    If zBlowSeq(n)=1 And gameSound Then PlaySound piccoloGrunt3Snd
    
    If zBlowSeq(n)=seq7 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-5:yed(n)=37
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-5:yed(n)=37
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=6
        xblow(n,nn)=48: yblow(n,nn)=4:wblow(n,nn)=42:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=26:wblow(n,nn)=98:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=16:wblow(n,nn)=98:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=36:wblow(n,nn)=95:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=46:wblow(n,nn)=85:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=77:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=6:zf(n)=4
        eAni(n)=1:ef(n)=3:xED(n)=65:yed(n)=14
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4 :eAni(n)=1:ef(n)=4:xed(n)=65:yed(n)=14
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=22
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
        zani(n)=12:zf(n)=2
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
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
    endSeq=900
    makuuhouidanSeq=1000
    choubakuretsuSeq=2000
    If zBlowSeq(n)=1 And upKey(n)=1 Then zBlowSeq(n) = makuuhouidanSeq
    If zBlowSeq(n) >= makuuhouidanSeq And zBlowSeq(n) < choubakuretsuSeq Then doMakuuHouidan(n)
    
    If zBlowSeq(n) < endSeq Then doMakankousappou(n)
    
    If zBlowSeq(n)>=endSeq And zBlowSeq(n) < makuuhouidanSeq Then 
        zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    End If
    
Case 15 ;Piccolo throw
    sonicSlashSeqStart=1000
    endSeq=2000
    zNoMove(n)=1:zNoJump(n)=1
    if zBlowSeq(n)=1 And isRunning(n) And Abs(zSpeed#(n)) >= 0.1 Then zSpeed(n)=0:zBlowSeq(n)=sonicSlashSeqStart
    
    If zBlowSeq(n) >= sonicSlashSeqStart Then
        doSonicSlash(n)
    Else
        ;do throw
        doPiccoloThrow(n)
    End If
    
    if zBlowSeq(n)=endSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 16 ;Taunt Key
    a=2:b=10:c=20:d=90
    a2=8:b2=18:c2=48:d2=58:e2=68
    zNoMove(n)=1:zNoJump(n)=1
    If zongnd(n)=0 And canAirGlideUp(n)=0 Then zy(n)=zy(n)-2
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
    zNoMove(n)=1:zNoJump(n)=1
    seqStart=100:endSeq=50
    comboSeq=2000
    gohanSeq=3000
    
    If zBlowSeq(n)=1 Then
        If isCounterAttack(n)=1 Then
            zBlowSeq(n)=gohanSeq:isCounterAttack(n)=0
        Else
            zBlowSeq(n)=seqStart
        End If
    End If

    If zBlowSeq(n) >= seqStart And zBlowSeq(n) < comboSeq Then doHighExtendingPunch(n)
    If zBlowSeq(n) >= comboSeq And zBlowSeq(n) < gohanSeq Then doFollowUpCombo(n, 1)
    If zBlowSeq(n) >= gohanSeq Then doGohanCounter(n)
    
    If zBlowSeq(n) > 1 And zBlowSeq(n) < seqStart Then 
        zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zCurBlow(n)=0
    End If
End Select

End Function