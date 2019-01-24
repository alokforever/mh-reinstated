Function doThemysciraFar(n)
    zNoMove(n)=1:zNoJump(n)=1:zJump(n)=0:zJumping(n)=0:zNoGrav(n)=1
    seqStart=20050:seq1=seqStart+2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2
    seq6=seq5+2:seq7=seq6+2
    themysciraHitSeq=20001
    themysciraLandSeq=20030

    If zBlowSeq(n)>=seq7 And zOnGnd(n)=1 Then 
        If gameSound Then PlaySound mvcCrash2Snd
        makechunk(n,zx(n),zy(n)+15,zFace(n),148)
        quake=1:quakeSeq=0
        zBlowSeq(n)=themysciraLandSeq
    End If
    
;======== Animation =========
    If zBlowSeq(n)>=seqStart And zBlowSeq(n)<seq1 Then zani(n)=10:zf(n)=13
    If zBlowSeq(n)>=seq1 And zBlowSeq(n)<seq2 Then zani(n)=10:zf(n)=14
    If zBlowSeq(n)>=seq2 And zBlowSeq(n)<seq3 Then zani(n)=10:zf(n)=15
    If zBlowSeq(n)>=seq3 And zBlowSeq(n)<seq4 Then zani(n)=10:zf(n)=16
    If zBlowSeq(n)>=seq4 And zBlowSeq(n)<seq5 Then zani(n)=10:zf(n)=17
    If zBlowSeq(n)>=seq5 And zBlowSeq(n)<seq6 Then zani(n)=10:zf(n)=18
    If zBlowSeq(n)>=seq6 And zBlowSeq(n)<seq7 Then zani(n)=10:zf(n)=19
    If zBlowSeq(n)>=seq7 Then 
        zCheckWall(n)
        zani(n)=10
        If zBlowSeq(n) Mod 3 = 0 Then
            If zf(n)=38 Then
                zF(n)=39
            Else
                zF(n)=38
            End If
        End If
    End If
    
;========= Sounds ===========
    If zBlowSeq(n)=seq7+1 And gameSound Then PlaySound zRunFootSound(n)
    
;======= Hitbox ========
    If zBlowSeq(n)>=seq7 Then
        zblowPamount(n)=2:nn=1:zBlowBack(n)=1
        xblow(n,nn)=11: yblow(n,nn)=15:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=5:wblow(n,nn)=15:hblow(n,nn)=5:nn=nn+1
        zHitMode(n)=2:zBlowHold(n)=12
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=5:zHitTime(n)=40
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=mvcHit2Snd
        If zBlowStill(n)=1 Then zBlowSeq(n)=themysciraHitSeq-1
    End If
    
;======= Movement =======
    If zBlowSeq(n)>=seq7 And zOnGnd(n)=0 Then 
        If zFace(n)=2 Then xDst=zx(n)-10
        If zFace(n)=4 Then xDst=zx(n)+10
        checkYDist(n, xDst, zy(n), 2)
        If zBlowStill(n)=0 Then moveX(n,zBlowDir(n),10)
        If yDist(n)>=15 Then 
            If zBlowStill(n)=0 Then moveY(n,7) 
        Else 
            If zBlowStill(n)=0 Then moveY(n,4) 
        End If
    End If
End Function

Function doThemysciraLandSeq(n)
    zNoMove(n)=1:zNoJump(n)=1:zJump(n)=0:zJumping(n)=0
    seqStart=20030:seq1=seqStart+5:seq2=seq1+4:seq3=seq2+1:seq4=seq3+1:seq5=seq4+2:seq6=seq5+2
    seq7=seq6+2
    endSeq=2

;======== Animation =========
    If zBlowSeq(n)>=seqStart And zBlowSeq(n)<seq1 Then zani(n)=26:zf(n)=1
    If zBlowSeq(n)>=seq1 And zBlowSeq(n)<seq2 Then zani(n)=26:zf(n)=2
    If zBlowSeq(n)>=seq2 And zBlowSeq(n)<seq3 Then zani(n)=26:zf(n)=3
    If zBlowSeq(n)>=seq3 And zBlowSeq(n)<seq4 Then zani(n)=26:zf(n)=4
    If zBlowSeq(n)>=seq4 And zBlowSeq(n)<seq5 Then zani(n)=26:zf(n)=5
    If zBlowSeq(n)>=seq5 And zBlowSeq(n)<seq6 Then zani(n)=26:zf(n)=6
    If zBlowSeq(n)>=seq6 And zBlowSeq(n)<seq7 Then zani(n)=26:zf(n)=7
    
    If zBlowSeq(n)>seq7 Then zBlowSeq(n)=endSeq
End Function

Function doThemysciraHitSeq(n)
    zNoMove(n)=1:zNoJump(n)=1:zJump(n)=0:zJumping(n)=0
    seqStart=20001:seq1=seqStart+5:seq2=seq1+4:seq3=seq2+4:seq4=seq3+4:seq5=seq4+3:seq6=seq5+3
    ;20024
    endSeq=2
    
    If zBlowSeq(n)<seq5 Then 
        zNoGrav(n)=1 
    Else 
        zNoGrav(n)=0 
    End If
;======== Animation =========
    If zBlowSeq(n)>=seqStart And zBlowSeq(n)<seq1 Then zani(n)=4:zf(n)=10:moveY(n,-1.5)
    If zBlowSeq(n)>=seq1 And zBlowSeq(n)<seq2 Then zani(n)=4:zf(n)=11:moveY(n,-1):moveX(n,zBlowDir(n),-3)
    If zBlowSeq(n)>=seq2 And zBlowSeq(n)<seq3 Then zani(n)=4:zf(n)=12:moveY(n,0.5):moveX(n,zBlowDir(n),-2.4)
    If zBlowSeq(n)>=seq3 And zBlowSeq(n)<seq4 Then zani(n)=4:zf(n)=12:moveY(n,1.5):moveX(n,zBlowDir(n),-1.6)
    If zBlowSeq(n)>=seq4 And zBlowSeq(n)<seq5 Then zani(n)=4:zf(n)=14:moveY(n,2.5):moveX(n,zBlowDir(n),-1.2)
    If zBlowSeq(n)>=seq5 And zBlowSeq(n)<seq6 Then zani(n)=4:zf(n)=15:moveX(n,zBlowDir(n),-0.8)
    
    If zBlowSeq(n)>=seq6 Then zBlowSeq(n)=endSeq
End Function

Function doThemysciraNear(n)
    zNoGrav(n)=1:zNoMove(n)=1:zNoJump(n)=1:zJump(n)=0:zJumping(n)=0
    seqStart=222:seq1=seqStart+2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2 ; 236
    seq6=seq5+2:seq7=seq6+2 ; 236
    seq8=seq7+20000
    themysciraHitSeq=20001
    themysciraLandSeq=20030
    endSeq=2

    If zBlowSeq(n)>seq7 And zOnGnd(n)=1 Then 
        If gameSound Then PlaySound mvcCrash2Snd
        checkYDist(n, zx(n), zy(n), 2)
        If yDist(n)<10 Then makechunk(n,zx(n),zy(n)+15,zFace(n),148)
        quake=1:quakeSeq=0
        zBlowSeq(n)=themysciraLandSeq
    End If
    
;======= Animation =======
    If zBlowSeq(n)>seqStart And zBlowSeq(n)<=seq1 Then zani(n)=5:zf(n)=5
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=5:zf(n)=6
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=5:zf(n)=7
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=5:zf(n)=8
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then zani(n)=5:zf(n)=9
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then zani(n)=5:zf(n)=10
    If zBlowSeq(n)>seq6 And zBlowSeq(n)<=seq7 Then zani(n)=5:zf(n)=11
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq8 Then
        zCheckWall(n)
        zani(n)=10
        If zBlowSeq(n) Mod 3 = 0 Then
            If zf(n)=30 Then
                zF(n)=29
            Else
                zF(n)=30
            End If
        End If
    End If
    
;========= Sounds ===========
    If zBlowSeq(n)=seq7+1 And gameSound Then PlaySound zRunFootSound(n)
    
;======= Hitbox ========
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq8 Then
        zblowPamount(n)=4:nn=1:zBlowBack(n)=1
        xblow(n,nn)=-7: yblow(n,nn)=30:wblow(n,nn)=13:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=-4: yblow(n,nn)=20:wblow(n,nn)=13:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=-2: yblow(n,nn)=10:wblow(n,nn)=13:hblow(n,nn)=5:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=14:hblow(n,nn)=10:nn=nn+1
        zHitMode(n)=2:zBlowHold(n)=12
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=0:zHitDownSpeed#(n)=5:zHitTime(n)=40
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=mvcHit2Snd
        If zBlowStill(n)=1 Then zBlowSeq(n)=themysciraHitSeq-1
    End If
    
;======= Movement =======
    If zBlowSeq(n)>seq5 And zBlowSeq(n)<=seq6 Then moveY(n,2):moveX(n,zBlowdir(n),1)
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq8 And zOnGnd(n)=0 Then 
        If zFace(n)=2 Then xDst=zx(n)-10
        If zFace(n)=4 Then xDst=zx(n)+10
        checkYDist(n, xDst, zy(n), 2)
        If zBlowStill(n)=0 Then moveX(n,zBlowDir(n),2.5)
        If yDist(n)>=15 Then 
            If zBlowStill(n)=0 Then moveY(n,9) 
        Else 
            If zBlowStill(n)=0 Then moveY(n,4) 
        End If
    End If
    
;======= Effects ========
    If zBlowSeq(n)>seq7 And zBlowSeq(n)<=seq8 And zBlowStill(n)=0 Then 
        If zF(n)=30 Then extraObj(n,zx(n),0,zy(n),0,zFace(n),149)
        If zF(n)=29 Then extraObj(n,zx(n),0,zy(n),0,zFace(n),150)
    End If
    
End Function

Function doThemysciraDiveInit(n)
    zNoGrav(n)=1:zNoMove(n)=1:zNoJump(n)=1:zJump(n)=0:zJumping(n)=0
    seqStart=198:seq1=seqStart+3:seq2=seq1+3:seq3=seq2+18 ; 222
    endSeq=2:themysciraFarSeq=20050

    If zBlowSeq(n)=seq3 Then
        If zFace(n)=2 And rightKey(n)=1 Then zBlowSeq(n)=themysciraFarSeq
        If zFace(n)=4 And leftKey(n)=1 Then zBlowSeq(n)=themysciraFarSeq
    End If
;======= Animation =======
    If zBlowSeq(n)>=seqStart And zBlowSeq(n)<=seq1 Then zani(n)=10:zf(n)=24
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=10:zf(n)=25
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then 
        zani(n)=10
        If zBlowSeq(n) Mod 3 = 0 Then
            If zf(n)=28 Then
                zF(n)=27
            Else
                zF(n)=28
            End If
        End If
    End If
    
;====== Sounds =======
    If zBlowSeq(n)=seqStart+1 And gameSound Then PlaySound wwThemysciraSnd
    If zBlowSeq(n)=seq2+1 And gameSound Then PlaySound wwRun2Snd
    
;======= Movement =======
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 And zHitHead(n)=0 Then moveY(n,-7)
    
;======= Effects ========
    If zBlowSeq(n)=seqStart Then extraObj(n,zx(n),10,zy(n),2,zFace(n),117)
End Function

Function doWwTaunt2(n)
    seqStart=150:seq1=seqStart+3:seq2=seq1+6:seq3=seq2+6:seq4=seq3+45:seq5=seq4+3
    seq6=seq5+3:seq7=seq6+3:seq8=seq7+3:seq9=seq8+3
    endSeq=140
    
;========= Animation ==========
    If zBlowSeq(n)>=seqStart And zBlowSeq(n)<=seq1 Then zf(n)=14
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zf(n)=15
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zf(n)=16
    
    If zBlowSeq(n)>=seq3 And zBlowSeq(n)<seq4 Then
        If zBlowSeq(n) Mod 3 = 0 Then
            If zF(n)=17 Then 
                zF(n)=18
            Else If zF(n)=18 Then 
                zF(n)=19
            Else If zF(n)=19 Then 
                zF(n)=20
            Else If zF(n)=20 Then 
                zF(n)=21
            Else 
                zF(n)=17 
            End If
        End If
    End If
    
    If zBlowSeq(n)>=seq4 And zBlowSeq(n)=seq5 Then zf(n)=15
    If zBlowSeq(n)>=seq5 And zBlowSeq(n)=seq6 Then zf(n)=14
    If zBlowSeq(n)>=seq6 And zBlowSeq(n)=seq7 Then zf(n)=22
    If zBlowSeq(n)>=seq7 And zBlowSeq(n)=seq8 Then zf(n)=23
    If zBlowSeq(n)>=seq8 And zBlowSeq(n)=seq9 Then zf(n)=24
    
;========= Sounds ==========
    If zBlowSeq(n)=seqStart+1 And gameSound Then PlaySound wwTauntSnd
    
    If zBlowSeq(n)>seq9 Then zBlowSeq(n)=endSeq
End Function

Function performFierceAmazon(n)
    zNoMove(n)=0:zNoJump(n)=1:zNoGrav(n)=1
    a=10:b=16:c=18:d=20:e=41:f=43:g=46:h=49
    endSeq=2
    If zBlowSeq(n)=a Then 
        zy(n)=zy(n)-15
        extraObj(n,zx(n),-40,zy(n),2,zblowdir(n),89)
    End If
;----------- Sounds -----------
    If zBlowSeq(n)=a+3 And gameSound Then PlaySound wwFierceAmazonSnd
    If zBlowSeq(n)=a+6 And gameSound Then PlaySound zRunFootSound(curGuy(n))
    
;---------- Animation -------------
    If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) >= d And zBlowSeq(n) < e Then 
        If zBlowSeq(n) Mod 3 = 0 Then 
            zani(n)=10:zf(n)=5
        Else
            zani(n)=10:zf(n)=4
        End If
    End If
    
    If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zani(n)=10:zf(n)=6
    
;---------- Movement -------------
    If zBlowSeq(n) >= a And zBlowSeq(n) < b Then 
        If zFace(n) = 2 Then zx(n)=zx(n)-2
        If zFace(n) = 4 Then zx(n)=zx(n)+2
        zy(n)=zy(n)-1
    End If
    If zBlowSeq(n) >= b And zBlowSeq(n) < d Then 
        If zFace(n) = 2 Then zx(n)=zx(n)-1
        If zFace(n) = 4 Then zx(n)=zx(n)+1
        zy(n)=zy(n)+1
    End If
    If zBlowSeq(n) >= d And zBlowSeq(n) < e Then 
        If zFace(n) = 2 Then zx(n)=zx(n)+6
        If zFace(n) = 4 Then zx(n)=zx(n)-6
        zy(n)=zy(n)-1
    End If
    
    If zBlowSeq(n) > h Then zBlowSeq(n) = endSeq

End Function

Function DoWonderWoman(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq3
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1
.noBlowSeq3

zchunkType(n)=10

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    ;If isRunning(n) Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zblocked(n)=1 Then 
        zani(n)=13:zf(n)=2
        zBlockSeqStart(n)=zBlockSeq(n)
    End If
    If zBlockSeq(n) = zBlockSeqStart(n)+3 Then zani(n)=13:zf(n)=3
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 1    ;Attack
    zNoMove(n)=1:zNoJump(n)=1
    a=6:b=a+5:c=b+2:d=c+2:e=d+2:f=e+2:g=f+2:h=g+2:i=h+2:j=i+2:k=j+2:l=k+3
    m=l+4:n1=m+4
    If isRunning(n) Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
;----------- Sounds -----------
    randSeed=Rand(3)
    If zBlowSeq(n)=1 And gameSound Then
        If randSeed=1 Then PlaySound wwShout1Snd
        If randSeed=2 Then PlaySound wwShout2Snd
        If randSeed=3 Then PlaySound wwShout3Snd
    End If
    If zBlowSeq(n)=d-1 And gameSound Then PlaySound mvcBlow1Snd
    
;---------- Animation -------------
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zani(n)=6:zf(n)=8
    If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zani(n)=6:zf(n)=9
    If zBlowSeq(n) >= i And zBlowSeq(n) <= j Then zani(n)=6:zf(n)=10
    If zBlowSeq(n) >= j And zBlowSeq(n) <= k Then zani(n)=6:zf(n)=11
    If zBlowSeq(n) >= k And zBlowSeq(n) <= l Then zani(n)=6:zf(n)=12
    If zBlowSeq(n) >= l And zBlowSeq(n) <= m Then zani(n)=6:zf(n)=13
    If zBlowSeq(n) >= m And zBlowSeq(n) <= n1 Then zani(n)=6:zf(n)=14
    
;---------- Hit box --------------
    If zBlowSeq(n) >= d And zBlowSeq(n) < i Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=10: yblow(n,nn)=30:wblow(n,nn)=35:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=20:wblow(n,nn)=35:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=18:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=mvcHit2Snd
    End If

    If zBlowSeq(n) = n1 Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    zNoJump(n)=0:ZJUMPING(N)=1
    a=5:b=a+4:c=b+1:d=c+1:e=d+1:f=e+3:g=f+4:h=g+5:i=h+5:j=i+6

;----------- Sounds ------------
    randSeed=Rand(3)
    If zBlowSeq(n)=1 And gameSound Then
        If randSeed=1 Then PlaySound wwShout1Snd
        If randSeed=2 Then PlaySound wwShout2Snd
        If randSeed=3 Then PlaySound wwShout3Snd
    End If
    If zBlowSeq(n)=c-1 And gameSound Then PlaySound mvcBlow1Snd

;---------- Animation ------------    
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zani(n)=8:zf(n)=3
    If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zani(n)=8:zf(n)=4
    If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zani(n)=8:zf(n)=5
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zani(n)=8:zf(n)=6
    If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zani(n)=8:zf(n)=7
    If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zani(n)=8:zf(n)=8
    If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zani(n)=8:zf(n)=9
    If zBlowSeq(n) >= i And zBlowSeq(n) <= j Then zani(n)=8:zf(n)=10
    
;---------- Hit box --------------
    If zBlowSeq(n) >= c And zBlowSeq(n) < h Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=5: yblow(n,nn)=30:wblow(n,nn)=18:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=20:wblow(n,nn)=22:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=15: yblow(n,nn)=15:wblow(n,nn)=25:hblow(n,nn)=5:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=mvcHit2Snd
    End If
    
    If zBlowSeq(n) = j Then zBlowSeq(n)=0:zBlow(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n)
    a=3:b=a+3:c=b+3:d=c+3:e=d+3:f=e+4:g=f+5:h=g+5:i=h+4:j=i+4:k=j+4
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
;----------- Sounds ------------
    randSeed=Rand(2)
    If zBlowSeq(n)=1 And gameSound Then
        If randSeed=1 Then PlaySound wwShout1Snd
        If randSeed=2 Then PlaySound wwShout2Snd
    End If
    If zBlowSeq(n)=c-1 And gameSound Then PlaySound mvcBlow1Snd
    
;---------- Animation ------------    
    zani(n)=9
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zf(n)=1
    If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zf(n)=2
    If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zf(n)=3
    If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zf(n)=4
    If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zf(n)=5
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zf(n)=6
    If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zf(n)=7
    If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zf(n)=8
    If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zf(n)=9
    If zBlowSeq(n) >= i And zBlowSeq(n) <= j Then zf(n)=10
    If zBlowSeq(n) >= j And zBlowSeq(n) <= k Then zf(n)=11

;---------- Hit box --------------
    If zBlowSeq(n) => e And zBlowSeq(n) =< g Then
        zblowPamount(n)=4
        nn=1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=53:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=53:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=18:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mvcHit2Snd
    EndIf

    If zBlowSeq(n) = k Then zBlowSeq(n)=0:zBlow(n)=0

Case 5    ;Up + Special (Warrior's heart)
       zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    zChunkType(n)=50
    zJump(n)=0
    a=3:b=a+3:c=b+3:d=c+2:e=d+2:f=e+1:g=f+1:h=g+2:i=h+2:j=i+2:k=j+2
    l=k+2:m=l+2:n1=m+1:o=n1+1:p=o+2:q=p+2:r=q+3:s=r+3
    aa=s+2:bb=aa+2:cc=bb+2:dd=cc+1:ee=dd+1:ff=ee+2:gg=ff+2:hh=gg+3:ii=hh+3
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
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
        xblow(n,nn)=-10: yblow(n,nn)=80:wblow(n,nn)=30:hblow(n,nn)=20:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=4.5:zHitTime(n)=20
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
        zBlowSound(n)=mvcHit1Snd
        zani(n)=7:zf(n)=6:zantiplat(n)=1
    EndIf
    If (zBlowSeq(n) > g And zBlowSeq(n) =< i) Or (zBlowSeq(n) > p And zBlowSeq(n) <= r) Or (attackMode(n, 1)=1 And zBlowSeq(n) > ff And zBlowSeq(n) <= hh) Then
        zBlowPamount(n)=1:nn=1
        xblow(n,nn)=-27: yblow(n,nn)=80:wblow(n,nn)=68:hblow(n,nn)=25
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=4.5:zHitTime(n)=20
        If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=15:zBlowStillTime(n)=0:zBlowBlockTime(n)=20
        zBlowSound(n)=mvcHit1Snd
        zantiplat(n)=1
    EndIf

    If zHitHead(n)=1 Then zBlowSeq(n)=ii
    If (zBlowSeq(n) => s And attackMode(n, 1)=0) Or (zBlowSeq(n) >= ii And attackMode(n, 1)=1) Then 
        If zBlowSeq(n) Mod 3=0 Then 
            If zf(n)=11 Then 
                zani(n)=7:zf(n)=10
            Else
                zani(n)=7:zf(n)=11
            End If
        End If
        zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    End If
    If zongnd(n)=1 And zBlowSeq(n) >= s-2 And attackMode(n, 1)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    If zongnd(n)=1 And zBlowSeq(n) >= ii-2 And attackMode(n, 1)=1 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0


Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=5
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0


Case 7    ;Special (THEMYSCIRA FALL / Fierce Amazon)
    faSeq=10:endSeq=2:themysciraSeq=198:themysciraNearSeq=223:themysciraHitSeq=20001
    themysciraLandSeq=20030:themysciraFarSeq=20050

    If zBlowSeq(n)=1 And isRunning(n) Then zBlowSeq(n)=faSeq
    If zBlowSeq(n)=1 And isRunning(n)=0 Then zBlowSeq(n)=themysciraSeq
    If zBlowSeq(n) >= faSeq And zBlowSeq(n)<themysciraSeq Then performFierceAmazon(n)
    If zBlowSeq(n) >= themysciraSeq And zBlowSeq(n)<themysciraNearSeq Then doThemysciraDiveInit(n)
    If zBlowSeq(n) >= themysciraNearSeq And zBlowSeq(n)<themysciraHitSeq Then doThemysciraNear(n)
    
    If zBlowSeq(n) >= themysciraHitSeq And zBlowSeq(n)<themysciraLandSeq Then doThemysciraHitSeq(n)
    If zBlowSeq(n) >= themysciraLandSeq And zBlowSeq(n)<themysciraFarSeq Then doThemysciraLandSeq(n)
    If zBlowSeq(n) >= themysciraFarSeq Then doThemysciraFar(n)

    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<faSeq Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 8    ;Dodging
    zNoMove(n)=1
    zNoJump(n)=1
    zheight(n)=25
    a=7:b=15:c=20:d=25:e=30:f=37
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=1:zf(n)=0
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=1:zf(n)=0:moveX(n,zBlowdir(n),1)  

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;Down + Special (Amazon Aegis (Projectile deflector))
    zNoMove(n)=1:zNoJump(n)=1:ZJUMPING(N)=1
    a=3:b=a+7:c=b+4:d=c+4:e=d+4:f=e+4:g=f+4:h=g+4:i=h+4:j=i+4:k=j+6:l=k+4
    aa=100:bb=aa+1:cc=bb+5:dd=cc+5:ee=dd+2:ff=ee+2:gg=ff+2:hh=gg+12:ii=hh+4

    If zOnGnd(n)=0 Then zy(n)=zy(n)-2

    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowSeq(n)=1 Then projectileDeflectSpeed#(n)=1+(Abs(zSpeed#(n)/5))

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
        projectileDeflectMode(n)=1
        If isDeflecting(n)=1 Then
            If gameSound Then PlaySound wwAegisHitSnd
            isDeflecting(n)=0
            zBlowSeq(n)=aa
        End If
    End If

    If zBlowSeq(n) > l And zBlowSeq(n) < aa Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Attack
    zNoMove(n)=1:zNoJump(n)=1
    a=3:b=a+2:c=b+3:d=c+2:e=d+3:f=e+2:g=f+2:h=g+1:i=h+2:j=i+2:k=j+2:l=k+3:m=l+4:n1=m+5:o=n1+6:p=o+5:q=p+3
    
;----------- Sounds ------------
    randSeed=Rand(3)
    If zBlowSeq(n)=1 And gameSound Then
        If randSeed=1 Then PlaySound wwShout1Snd
        If randSeed=2 Then PlaySound wwShout2Snd
        If randSeed=3 Then PlaySound wwShout3Snd
    End If
    If zBlowSeq(n)=c-1 And gameSound Then PlaySound mvcBlow1Snd
    
;---------- Animation ------------    
    zani(n)=14
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zf(n)=1
    If zBlowSeq(n) >= a And zBlowSeq(n) <= b Then zf(n)=2
    If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zf(n)=3
    If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zf(n)=4
    If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zf(n)=5
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zf(n)=6
    If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zf(n)=7
    If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zf(n)=8
    If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zf(n)=9
    If zBlowSeq(n) >= i And zBlowSeq(n) <= j Then zf(n)=10
    If zBlowSeq(n) >= j And zBlowSeq(n) <= k Then zf(n)=11
    If zBlowSeq(n) >= k And zBlowSeq(n) <= l Then zf(n)=12
    If zBlowSeq(n) >= l And zBlowSeq(n) <= m Then zf(n)=13
    If zBlowSeq(n) >= m And zBlowSeq(n) <= n1 Then zf(n)=14
    If zBlowSeq(n) >= n1 And zBlowSeq(n) <= o Then zf(n)=15
    If zBlowSeq(n) >= o And zBlowSeq(n) <= p Then zf(n)=16
    If zBlowSeq(n) >= p And zBlowSeq(n) <= q Then zani(n)=12:zf(n)=1

;---------- Hit box --------------
    If zBlowSeq(n) => g And zBlowSeq(n) =< n1 Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=20: yblow(n,nn)=67:wblow(n,nn)=6:hblow(n,nn)=10:nn=nn+1
        xblow(n,nn)=20: yblow(n,nn)=57:wblow(n,nn)=6:hblow(n,nn)=10:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=mvcHit2Snd
    EndIf

    If zBlowSeq(n) = q Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 11    ;club
    a=12/wolvSpdFctr(n):b=22/wolvSpdFctr(n):c=29/wolvSpdFctr(n):d=50/wolvSpdFctr(n):e=55/wolvSpdFctr(n)
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-40:yed(n)=40
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-40:yed(n)=42
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
        zani(n)=6:zf(n)=12
        eAni(n)=1:ef(n)=3:xED(n)=65:yed(n)=20
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=11 :eAni(n)=1:ef(n)=4:xed(n)=65:yed(n)=18
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-40:yed(n)=40
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 12    ;Shooting Position
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
        zani(n)=6:zf(n)=10
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=50:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=10:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=50:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=10:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=50:yed(n)=22
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
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 15 ;WW throw
    zNoMove(n)=1:zNoJump(n)=1
    a=144:b=a+2:c=b+2:d=c+4:e=d+4:f=e+4:g=f+4:h=g+1:i=h+1:j=i+2:k=j+3:l=k+3:m=l+3:n1=m+3:o=n1+4:p=o+3
    aa=200:bb=aa+6:cc=bb+4:dd=cc+4:ee=dd+9:ff=ee+7:gg=ff+6:hh=gg+5:ii=hh+4:jj=ii+8:kk=jj+7:ll=kk+6:mm=ll+5:nn=mm+3
    aaa=300
    If zOnGnd(n)=0 And zBlowSeq(n) < b Then zy(n)=zy(n)-2
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)

;----------------- Animations and Sounds
    If zBlowSeq(n)=1 And downKey(n)=0 Then zBlowSeq(n)=aaa
    zani(n)=15
    If zBlowSeq(n) >= b Then zNoGrav(n)=1
    If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then
        If zBlowSeq(n) Mod 3 = 0 Then
            If zf(n)=1 Then
                zf(n)=2
            Else If zf(n)=2 Then
                zf(n)=3
            Else
                zf(n)=1
            End If
        End If
        If zBlowSeq(n) Mod 12=0 And gameSound Then PlaySound wwWhip1Snd
        If downKey(n)=0 Then zBlowSeq(n)=b
    End If
    
    If zBlowSeq(n) = b And gameSound Then PlaySound wwWhip2Snd
    If zBlowSeq(n) = b+1 And gameSound Then 
        soundSeed=Rand(5)
        If soundSeed <> 1 Then
            PlaySound wwLassoSnd
        Else
            PlaySound wwGrunt1Snd
        EndIf
    End If
    
    If zBlowSeq(n) >= b And zBlowSeq(n) <= c Then zf(n)=5
    If zBlowSeq(n) >= c And zBlowSeq(n) <= d Then zf(n)=6
    If zBlowSeq(n) >= d And zBlowSeq(n) <= e Then zf(n)=7
    If zBlowSeq(n) >= e And zBlowSeq(n) <= f Then zf(n)=8
    If zBlowSeq(n) >= f And zBlowSeq(n) <= g Then zf(n)=9
    If zBlowSeq(n) >= g And zBlowSeq(n) <= h Then zf(n)=10
    If zBlowSeq(n) >= h And zBlowSeq(n) <= i Then zf(n)=11
    If zBlowSeq(n) >= i And zBlowSeq(n) <= j Then zf(n)=12
    If zBlowSeq(n) >= j And zBlowSeq(n) <= k Then zf(n)=13
    If zBlowSeq(n) >= k And zBlowSeq(n) <= l Then zf(n)=14
    
    If zBlowSeq(n) >= aa And zBlowSeq(n) <= bb Then zf(n)=15
    If zBlowSeq(n) >= bb And zBlowSeq(n) <= cc Then zf(n)=16
    If zBlowSeq(n) >= cc And zBlowSeq(n) <= dd Then zf(n)=17
    If zBlowSeq(n) >= dd And zBlowSeq(n) <= ee Then zf(n)=18
    If zBlowSeq(n) >= ee And zBlowSeq(n) <= ff Then zf(n)=19
    If zBlowSeq(n) >= ff And zBlowSeq(n) <= gg Then zf(n)=20
    If zBlowSeq(n) >= gg And zBlowSeq(n) <= hh Then zf(n)=21
    If zBlowSeq(n) >= hh And zBlowSeq(n) <= ii Then zf(n)=22
    If zBlowSeq(n) >= ii And zBlowSeq(n) <= jj Then zf(n)=23
    If zBlowSeq(n) >= jj And zBlowSeq(n) <= kk Then zf(n)=24
    If zBlowSeq(n) >= kk And zBlowSeq(n) <= ll Then zf(n)=25
    If zBlowSeq(n) >= ll And zBlowSeq(n) <= mm Then zf(n)=26
    If zBlowSeq(n) >= mm And zBlowSeq(n) <= nn Then zf(n)=27
    
;------------------ Effects ------------------
    If zBlowSeq(n) >= e And zBlowSeq(n) <= g Then
        If zBlowDir(n)=2 Then 
            x=25
        Else
            x=-25
        End If
        enemyControlInit(n,zx(n)+x,zy(n)-30,105,40,0,guardable)
        If zControlsThis(n) <> 0 Then zBlowSeq(n)=aa
    End If
    en=zControlsThis(n)
    If zBlowSeq(n) >= aa And zBlowSeq(n) <= kk Then
        zNoGrav(en)=1
        If zBlowSeq(n)=aa Then 
            If zBlowDir(n)=2 Then
                If Abs(zx(en)-zx(n)) > 80 Then 
                    zx(en)=zx(n)+130:wwLassoLong(n)=1
                    extraObj(n,zx(n),20,zy(n),0,zBlowDir(n),109)
                Else
                    zx(en)=zx(n)+90:wwLassoLong(n)=0
                    extraObj(n,zx(n),20,zy(n),0,zBlowDir(n),110)
                End If
            Else
                If Abs(zx(en)-zx(n)) > 80 Then
                    zx(en)=zx(n)-130:wwLassoLong(n)=1
                    extraObj(n,zx(n),20,zy(n),0,zBlowDir(n),109)
                Else
                    zx(en)=zx(n)-90:wwLassoLong(n)=0
                    extraObj(n,zx(n),20,zy(n),0,zBlowDir(n),110)
                End If
            End If
        End If
        If zBlowDir(n)=2 Then 
            dir=4
        Else
            dir=2
        End If
        If wwLassoLong(n)=1 Then
            moveFactor=2
        Else
            moveFactor=1
        End If
        If zBlowSeq(n) < jj Then
            If zFace(n)=2 Then
                hitBoxXPos=(zx(en)-zx(n))-20
            Else
                hitBoxXPos=((zx(en)-zx(n))*-1)-20
            End If
            zImune(en,n)=1:zImuneTo(en,n)=n:zImuneSeq(en,n)=0:zImuneTime(en,n)=99
            hitBoxYPos=(zy(n)-zy(en))+40
            zblowPamount(n)=1:nn=1
            zBlowBack(n)=1
            xblow(n,nn)=hitBoxXPos:yblow(n,nn)=hitBoxYPos:wblow(n,nn)=zSide(en)+20:hblow(n,nn)=zHeight(en)+10
            zHitMode(n)=2:zBlowHold(n)=0:zHitSpeed#(n)=7:zHitUpSpeed#(n)=2:zHitTime(n)=25
            zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=0:zBlowBlockTime(n)=25
            zBlowSound(n)=RashHitSnd
            zFallSpeed#(en)=zFallSpeed#(en)*-1
        Else
            zImune(en,n)=0:zImuneTo(en,n)=0
        End If

        If zBlowSeq(n) = kk And zOnGnd(en)=1 Then
            If gameSound Then PlaySound mvcCrashSnd
            quake=1:quakeSeq=0
            zDamage(en)=zDamage(en)+15
            zLife(en)=zLife(en)-15
        End If
        If zBlowSeq(n) >= bb And zBlowSeq(n) <= cc Then moveX(en, dir, -1*moveFactor)
        If zBlowSeq(n) >= cc And zBlowSeq(n) <= dd Then moveX(en, dir, 0.5*moveFactor):moveY(en, -1)
        If zBlowSeq(n) >= dd And zBlowSeq(n) <= ee Then moveX(en, dir, 3*moveFactor):moveY(en, -3)
        If zBlowSeq(n) >= ee And zBlowSeq(n) <= ff Then moveX(en, dir, 3*moveFactor):moveY(en, -2)
        If zBlowSeq(n) >= ff And zBlowSeq(n) <= gg Then moveX(en, dir, 4*moveFactor):moveY(en, -1.5)
        If zBlowSeq(n) >= gg And zBlowSeq(n) <= hh Then moveX(en, dir, 4*moveFactor):moveY(en, 3)
        If zBlowSeq(n) >= hh And zBlowSeq(n) <= ii Then moveX(en, dir, 3*moveFactor):moveY(en, 3)
        If zBlowSeq(n) >= ii And zBlowSeq(n) <= jj Then moveX(en, dir, 3):moveY(en, 4)
        If zBlowSeq(n) >= jj And zBlowSeq(n) <= kk Then moveX(en, dir, -1)
        unGuardable=1
        If zFace(n)=2 Then 
            enemyControlInit(n,zx(n)-300,zy(n)-300,600,600,en,unGuardable)
        Else
            enemyControlInit(n,zx(n)+300,zy(n)-300,600,600,en,unGuardable)
        End If
        If zBlowSeq(n) < jj Then
            zani(en)=2:zf(en)=3
        Else
            zani(en)=2:zf(en)=0
        End If
    End If
    If zBlowSeq(n)=aa And gameSound Then PlaySound wwWhipHitSnd
    If zBlowSeq(n)=bb And gameSound Then PlaySound wwShout4Snd
    
    If zBlowSeq(n) = nn+1 Then zBlowSeq(n)=p+5
    If zBlowSeq(n)=aaa Then zBlowSeq(n)=p
    
    If zBlowSeq(n) >= p And zBlowSeq(n) < aa Then 
        If zBlowSeq(n) >= p+5 Then
            If zFace(n)=2 Then
                zFace(n)=4
            Else
                zFace(n)=2
            End If
        End If
        zBlowSeq(n)=0:zBlow(n)=0
    End If

Case 16 ;Counter Key (Taunt)
    zNoMove(n)=1:zNoJump(n)=1
    a=72:b=a+6:c=b+6:d=c+6:e=d+4:f=e+4:g=f+3:h=g+3:i=h+3:j=i+4
    zani(n)=16
    endSeq=140
    taunt2Seq=150
    If isRunning(n) And zSpeed#(n) <> 0 Then moveX(n,zBlowdir(n),Abs(zSpeed#(n))/1.5):decelerate(n)
    If zOnGnd(n)=0 Then zy(n)=zy(n)-2

    If zBlowSeq(n)>=taunt2Seq Then doWwTaunt2(n)
;---------- Sound effects ------------
    If zBlowSeq(n)=1 Then
        zTauntSeed(n)=Rand(2)
        If zTauntSeed(n)=1 Then zBlowSeq(n)=taunt2Seq
    End If
    If zBlowSeq(n)=1 And gameSound Then PlaySound wwTaunt1Snd
    If zBlowSeq(n) < taunt2Seq And (zBlowSeq(n) Mod 50=0 Or zBlowSeq(n)=1) Then 
        If gameSound PlaySound wwCapeSnd
    End If
;---------- Animations -------------
    If zBlowSeq(n) >= 1 And zBlowSeq(n) < a And zTauntSeed(n)=2 Then
        If zBlowSeq(n)=1 Then zf(n)=1
        If zBlowSeq(n) Mod 3 = 0 Then
            If zf(n)=1 Then 
                zf(n)=2
            Else If zf(n)=2 Then
                zf(n)=3
            Else If zf(n)=3 Then
                zf(n)=4
            Else
                zf(n)=1
            End If
        End If
    End If
    If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zf(n)=5
    If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zf(n)=6
    If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zf(n)=7
    If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zf(n)=8
    If zBlowSeq(n) >= e And zBlowSeq(n) < f Then zf(n)=9
    If zBlowSeq(n) >= f And zBlowSeq(n) < g Then zf(n)=10
    If zBlowSeq(n) >= g And zBlowSeq(n) < h Then zf(n)=11
    If zBlowSeq(n) >= h And zBlowSeq(n) < i Then zf(n)=12
    If zBlowSeq(n) >= i And zBlowSeq(n) < j Then zf(n)=13
    
    If zBlowSeq(n)=taunt2Seq Then zani(n)=16:zF(n)=14
    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<taunt2Seq Then zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key (Flight)
    zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
End Select

End Function