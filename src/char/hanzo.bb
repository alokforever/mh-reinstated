Function PlayHanzoGruntSnd()
    randSeed=Rand(5)
    If randSeed=1 Then PlaySound hanzoGrunt1Snd
    If randSeed=2 Then PlaySound hanzoGrunt2Snd
    If randSeed=3 Then PlaySound hanzoGrunt3Snd
    If randSeed=4 Then PlaySound hanzoGrunt4Snd
    If randSeed=5 Then PlaySound hanzoGrunt5Snd
End Function

;----------------------------- make Hanzo (ninja)'s moves! -----------------------------------
Function DoHanzo(n)

initMoveStates(n)
Local randSeed
zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq6
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1
.noBlowSeq6

zchunkType(n)=20

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1:zani(n)=13
    zBlock(n)=1
    
    If zblocked(n)=0 And zBlowSeq(n) <= 4 Then zF(n)=1 Else zF(n)=2
    
    If zblocked(n)=1 Then
        zani(n)=13:zf(n)=3
    End If
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0:zBlock(n)=0

Case 1    ;High Punch
    seq1=5:seq2=seq1+3:seq3=seq2+1:seq4=seq3+1:seq5=seq4+1:seq6=seq5+1:seq7=seq6+1:seq8=seq7+1:seq9=seq8+2
    seq10=seq9+1:seq11=seq10+3:seq12=seq11+8:seq13=seq12+5:seq14=seq13+5
    zNoMove(n)=1: zNoJump(n)=1
    
;========== Sounds ===========
    If gameSound Then
        If zBlowSeq(n) = seq2-1 Then
            PlaySound hanzoBlow2Snd
            If Rand(2)=1 Then
                PlayHanzoGruntSnd()
            End If
        End If
        If zBlowSeq(n) = seq13 Then PlaySound hanzoSwordSheatheSnd
    End If

;========== Animation ===========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) >= seq1 And zBlowSeq(n) <= seq2 Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) >= seq2 And zBlowSeq(n) <= seq3 Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) >= seq3 And zBlowSeq(n) <= seq4 Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) >= seq4 And zBlowSeq(n) <= seq5 Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) >= seq5 And zBlowSeq(n) <= seq6 Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) >= seq6 And zBlowSeq(n) <= seq7 Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) >= seq7 And zBlowSeq(n) <= seq8 Then zani(n)=6:zf(n)=8
    If zBlowSeq(n) >= seq8 And zBlowSeq(n) <= seq9 Then zani(n)=6:zf(n)=9
    If zBlowSeq(n) >= seq9 And zBlowSeq(n) <= seq10 Then zani(n)=6:zf(n)=10
    If zBlowSeq(n) >= seq10 And zBlowSeq(n) <= seq11 Then zani(n)=6:zf(n)=11
    If zBlowSeq(n) >= seq11 And zBlowSeq(n) <= seq12 Then zani(n)=6:zf(n)=12
    If zBlowSeq(n) >= seq12 And zBlowSeq(n) <= seq13 Then zani(n)=6:zf(n)=13
    If zBlowSeq(n) >= seq13 And zBlowSeq(n) <= seq14 Then zani(n)=6:zf(n)=14
    
    If zBlowStill(n)=1 And (zF(n)=4 Or zF(n)=6) Then zF(n)=5
    
;========== Hitboxes ============
    If zF(n)>=4 And zF(n)<=6 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=61.9999:yblow(n,nn)=93.0:wblow(n,nn)=29:hblow(n,nn)=17:nn=nn+1
        xblow(n,nn)=66.9999:yblow(n,nn)=77.0:wblow(n,nn)=36:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=66.9999:yblow(n,nn)=61.0:wblow(n,nn)=36:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=69.9999:yblow(n,nn)=44.0:wblow(n,nn)=22:hblow(n,nn)=9:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=slashSnd
        zchunkType(n)=95 ;blood
    End If
    
    If zBlowSeq(n) > seq14 Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    seq1=10:seq2=seq1+2:seq3=seq2+2:seq4=seq3+1:seq5=seq4+1:seq6=seq5+15
    
    zNoJump(n)=0:ZJUMPING(N)=1

;============ Animation =============
    zani(n)=8
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zf(n)=6

;============ Sounds =============
    If gameSound And zBlowSeq(n)=seq4-1 Then
        PlaySound hanzoBlow2Snd
        If Rand(2)=1 Then
            PlayHanzoGruntSnd()
        End If
    End If

;============ Hitboxes =============
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq6 Then
        If zF(n)=4 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=0.519409:yblow(n,nn)=52.4:wblow(n,nn)=16:hblow(n,nn)=15:nn=nn+1
            xblow(n,nn)=13.5194:yblow(n,nn)=40.4:wblow(n,nn)=11:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=22.5194:yblow(n,nn)=32.4:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        Else
            zblowPamount(n)=5:nn=1
            xblow(n,nn)=-14.4806:yblow(n,nn)=24.0001:wblow(n,nn)=16:hblow(n,nn)=22:nn=nn+1
            xblow(n,nn)=0.519409:yblow(n,nn)=25.0001:wblow(n,nn)=17:hblow(n,nn)=18:nn=nn+1
            xblow(n,nn)=15.5194:yblow(n,nn)=33.0001:wblow(n,nn)=17:hblow(n,nn)=17:nn=nn+1
            xblow(n,nn)=32.5194:yblow(n,nn)=49.0001:wblow(n,nn)=9:hblow(n,nn)=19:nn=nn+1
            xblow(n,nn)=38.5194:yblow(n,nn)=67.0001:wblow(n,nn)=10:hblow(n,nn)=21:nn=nn+1
        End If
        zHitMode(n)=0:zBlowHold(n)=5
        zBlowDamage(n)=9:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=5:zBlowBlockTime(n)=25
        zBlowSound(n)=slashSnd
        zChunkType(n)=95 ;blood
    EndIf
    If zBlowSeq(n) > seq6 Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    seq1=12:seq2=seq1+1:seq3=seq2+1:seq4=seq3+1:seq5=seq4+1:seq6=seq5+12:seq7=seq6+5:seq8=seq7+5
    zheight(n)=zduckheight(n)
    
;=========== Sound ==========
    If gameSound And zBlowSeq(n) = seq1-1 Then
        PlaySound hanzoBlow2Snd
        If Rand(2)=1 Then
            PlayHanzoGruntSnd()
        End If
    End If
    
;=========== Animation ==========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=9:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=9:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=9:zf(n)=6
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=9:zf(n)=5
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=9:zf(n)=7
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=9:zf(n)=8

;=========== Hitboxes ==========
    If zF(n) >= 2 And zF(n) =< 6 Then
        If zF(n) >=2 And zF(n) <= 4 Then
            zblowPamount(n)=2:nn=1
            xblow(n,nn)=25.5189:yblow(n,nn)=30.0:wblow(n,nn)=27:hblow(n,nn)=19:nn=nn+1
            xblow(n,nn)=52.5189:yblow(n,nn)=19.0:wblow(n,nn)=16:hblow(n,nn)=14:nn=nn+1
        Else
            zblowPamount(n)=5:nn=1
            xblow(n,nn)=29.199:yblow(n,nn)=43.0:wblow(n,nn)=31:hblow(n,nn)=9:nn=nn+1
            xblow(n,nn)=29.199:yblow(n,nn)=33.0:wblow(n,nn)=21:hblow(n,nn)=7:nn=nn+1
            xblow(n,nn)=51.199:yblow(n,nn)=33.0:wblow(n,nn)=21:hblow(n,nn)=10:nn=nn+1
            xblow(n,nn)=27.199:yblow(n,nn)=25.0:wblow(n,nn)=30:hblow(n,nn)=18:nn=nn+1
            xblow(n,nn)=56.199:yblow(n,nn)=22.0:wblow(n,nn)=18:hblow(n,nn)=14:nn=nn+1
        End If
        zHitMode(n)=0:zBlowHold(n)=5
        zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=5:zBlowBlockTime(n)=30
        zBlowSound(n)=SlashSnd:zChunkType(n)=95 ;blood
    EndIf

    If zBlowSeq(n) > seq8 Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;flying sword spin (Up special)
    zNoMove(n)=0
    ztopspeed(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0 : spinN=5
    seq1=1:seq2=seq1+1:seq3=seq2+1:seq4=seq3+1:seq5=seq4+1:seq6=seq5+1:seq7=seq6+1:seq8=seq7+1:seq9=seq8+1
    seq10=seq9+4:seq11=seq10+4:seq12=seq11+4
    
    If zblowseq(n)=1 Then
        zBlowUpLimit(n)=zy(n)-128
    EndIf
    
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 And zBlowSeq(n) > seq12 Then zani(n)=4:zf(n)=5:ztopSpeed(n)=1:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=4:zf(n)=6
        
        ;----- End animation ------
        If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=7:zf(n)=10
        If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=7:zf(n)=11
        If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=7:zf(n)=12
    Else
        zantiplat(n)=1
        ;-------- Sound --------
        If zBlowSeq(n) = 2 And zblowstill(n)=0 And gameSound Then
            PlaySound hanzoSword1Snd
            If Rand(6)=3 Then
                randSeed=Rand(3)
                If randSeed=1 Then
                    PlaySound hanzoGrunt1Snd
                Else If randSeed=2 Then
                    PlaySound hanzoGrunt4Snd
                Else
                    PlaySound hanzoGrunt5Snd
                End If
            End If
        End If
        
        ;----- Animation and movement -----
        If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=7:zf(n)=1
        If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=7:zf(n)=2:moveX(n,zBlowdir(n),1.6):zy(n)=zy(n)-3.2
        If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=7:zf(n)=3
        If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=7:zf(n)=4
        If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=7:zf(n)=5
        If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=7:zf(n)=6
        If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zani(n)=7:zf(n)=7
        If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zani(n)=7:zf(n)=8
        If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zani(n)=7:zf(n)=9

        If zBlowSeq(n) > seq1 And zBlowSeq(n) =< seq9 And zblowStill(n)=0 Then moveX(n,zBlowdir(n),1.6):zy(n)=zy(n)-3.2
        
        ;------------ Hitboxes ------------
        If zF(n) >= 2 And zF(n) <= 9 Then
            If zf(n)=2 Then
                zblowPamount(n)=11:nn=1
                xblow(n,nn)=-41.32:yblow(n,nn)=106.6:wblow(n,nn)=19:hblow(n,nn)=16:nn=nn+1
                xblow(n,nn)=-30.32:yblow(n,nn)=114.6:wblow(n,nn)=16:hblow(n,nn)=7:nn=nn+1
                xblow(n,nn)=-14.32:yblow(n,nn)=115.6:wblow(n,nn)=17:hblow(n,nn)=9:nn=nn+1
                xblow(n,nn)=2.67999:yblow(n,nn)=115.6:wblow(n,nn)=13:hblow(n,nn)=13:nn=nn+1
                xblow(n,nn)=-21.32:yblow(n,nn)=105.6:wblow(n,nn)=18:hblow(n,nn)=11:nn=nn+1
                xblow(n,nn)=-2.32001:yblow(n,nn)=105.6:wblow(n,nn)=15:hblow(n,nn)=15:nn=nn+1
                xblow(n,nn)=16.68:yblow(n,nn)=110.6:wblow(n,nn)=9:hblow(n,nn)=8:nn=nn+1
                xblow(n,nn)=15.68:yblow(n,nn)=103.6:wblow(n,nn)=10:hblow(n,nn)=14:nn=nn+1
                xblow(n,nn)=26.68:yblow(n,nn)=103.6:wblow(n,nn)=11:hblow(n,nn)=12:nn=nn+1
                xblow(n,nn)=17.68:yblow(n,nn)=89.6:wblow(n,nn)=11:hblow(n,nn)=5:nn=nn+1
                xblow(n,nn)=3.67999:yblow(n,nn)=89.6:wblow(n,nn)=13:hblow(n,nn)=12:nn=nn+1
            Else If zF(n)=3 Then
                zblowPamount(n)=3:nn=1
                xblow(n,nn)=1.84052:yblow(n,nn)=81.8003:wblow(n,nn)=12:hblow(n,nn)=9:nn=nn+1
                xblow(n,nn)=10.8405:yblow(n,nn)=87.8003:wblow(n,nn)=13:hblow(n,nn)=6:nn=nn+1
                xblow(n,nn)=21.8405:yblow(n,nn)=95.8003:wblow(n,nn)=14:hblow(n,nn)=10:nn=nn+1
            Else If zF(n)=4
                zblowPamount(n)=9:nn=1
                xblow(n,nn)=-2.75946:yblow(n,nn)=114.8:wblow(n,nn)=16:hblow(n,nn)=25:nn=nn+1
                xblow(n,nn)=13.2405:yblow(n,nn)=109.8:wblow(n,nn)=12:hblow(n,nn)=24:nn=nn+1
                xblow(n,nn)=26.2405:yblow(n,nn)=101.8:wblow(n,nn)=9:hblow(n,nn)=17:nn=nn+1
                xblow(n,nn)=22.2405:yblow(n,nn)=85.8002:wblow(n,nn)=14:hblow(n,nn)=14:nn=nn+1
                xblow(n,nn)=36.2405:yblow(n,nn)=93.8002:wblow(n,nn)=7:hblow(n,nn)=14:nn=nn+1
                xblow(n,nn)=37.2405:yblow(n,nn)=79.8002:wblow(n,nn)=14:hblow(n,nn)=12:nn=nn+1
                xblow(n,nn)=31.2405:yblow(n,nn)=67.8002:wblow(n,nn)=20:hblow(n,nn)=12:nn=nn+1
                xblow(n,nn)=17.2405:yblow(n,nn)=61.8002:wblow(n,nn)=14:hblow(n,nn)=6:nn=nn+1
                xblow(n,nn)=31.2405:yblow(n,nn)=57.8002:wblow(n,nn)=22:hblow(n,nn)=14:nn=nn+1
            Else If zF(n)=5
                zblowPamount(n)=3:nn=1
                xblow(n,nn)=13.6406:yblow(n,nn)=63.8003:wblow(n,nn)=14:hblow(n,nn)=11:nn=nn+1
                xblow(n,nn)=28.6406:yblow(n,nn)=59.8003:wblow(n,nn)=9:hblow(n,nn)=10:nn=nn+1
                xblow(n,nn)=37.6406:yblow(n,nn)=55.8003:wblow(n,nn)=16:hblow(n,nn)=12:nn=nn+1
            Else If zF(n)=6
                zblowPamount(n)=6:nn=1
                xblow(n,nn)=22.2007:yblow(n,nn)=84.4002:wblow(n,nn)=21:hblow(n,nn)=17:nn=nn+1
                xblow(n,nn)=30.2007:yblow(n,nn)=65.4002:wblow(n,nn)=20:hblow(n,nn)=14:nn=nn+1
                xblow(n,nn)=31.2007:yblow(n,nn)=50.4002:wblow(n,nn)=16:hblow(n,nn)=16:nn=nn+1
                xblow(n,nn)=24.2007:yblow(n,nn)=37.4002:wblow(n,nn)=19:hblow(n,nn)=15:nn=nn+1
                xblow(n,nn)=7.20074:yblow(n,nn)=37.4002:wblow(n,nn)=18:hblow(n,nn)=13:nn=nn+1
                xblow(n,nn)=17.2007:yblow(n,nn)=24.4002:wblow(n,nn)=18:hblow(n,nn)=16:nn=nn+1
            Else If zF(n)=7
                zblowPamount(n)=3:nn=1
                xblow(n,nn)=11.5608:yblow(n,nn)=42.6002:wblow(n,nn)=15:hblow(n,nn)=17:nn=nn+1
                xblow(n,nn)=18.5608:yblow(n,nn)=25.6002:wblow(n,nn)=11:hblow(n,nn)=11:nn=nn+1
                xblow(n,nn)=25.5608:yblow(n,nn)=13.6002:wblow(n,nn)=9:hblow(n,nn)=6:nn=nn+1
            Else If zF(n)=8
                zblowPamount(n)=3:nn=1
                xblow(n,nn)=-14.6391:yblow(n,nn)=7.80029:wblow(n,nn)=6:hblow(n,nn)=10:nn=nn+1
                xblow(n,nn)=-9.6391:yblow(n,nn)=17.8003:wblow(n,nn)=5:hblow(n,nn)=8:nn=nn+1
                xblow(n,nn)=-5.6391:yblow(n,nn)=33.8003:wblow(n,nn)=9:hblow(n,nn)=14:nn=nn+1
            Else If zF(n)=9
                zblowPamount(n)=3:nn=1
                xblow(n,nn)=-47.2391:yblow(n,nn)=19.8002:wblow(n,nn)=14:hblow(n,nn)=5:nn=nn+1
                xblow(n,nn)=-37.2391:yblow(n,nn)=27.8002:wblow(n,nn)=15:hblow(n,nn)=9:nn=nn+1
                xblow(n,nn)=-23.2391:yblow(n,nn)=33.8002:wblow(n,nn)=12:hblow(n,nn)=9:nn=nn+1
            End If
            zHitmode(n)=2:zBlowHold(n)=4
            zHitSpeed#(n)=2:zHitUpSpeed#(n)=3:zHitTime(n)=45
            zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowStillTime(n)=5:zBlowBlockTime(n)=20
            zBlowSound(n)=SlashSnd:zchunkType(n)=95 ;blood
        End If
        If zblowseq(n) >= seq9 Then zblowseq(n) = seq1 : zblowseq2(n)=zblowseq2(n)+1
    EndIf
    
    If zy(n) < zBlowupLimit(n) Or zHitHead(n)=1 Then zblowseq2(n) = 99:zblowuplimit(n)=-9999
    If zongnd(n)=1 And zBlowSeq2(n) > spinN Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 7    ;ninja star
    seq1=8:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2:seq6=seq5+2
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    Local maxStars=3

    If zongnd(n)=0 Then zy(n)=zy(n)-3.2
    
;========== Sounds =============
    If gameSound = 1 Then
        If zBlowSeq(n) = seq3 Then PlaySound hanzoShurikenSnd
        If zBlowSeq(n) = seq3 And zBlowSeq2(n) <> 1 Then PlaySound hanzoGrunt1Snd
    End If
    
;========= Animation ==========
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zani(n)=10:zf(n)=5
    
    If zBlowSeq(n) = seq2+1 Then
        If zStaminaBar#(n) >= 20
            zStaminaBar#(n)=zStaminaBar#(n)-20
            dir=zface(n)
            If zBlowSeq2(n) = 1 Then y=zy(n)-zheight(n)+35 Else y=zy(n)-zheight(n)+50
            If zface(n)=2 Then x=zx(n)+38
            If zface(n)=4 Then x=zx(n)-38
            makeshot(n,56,x,y,dir)
        Else
            isFlashLowStamina(n)=1:zBlowSeq(n)=seq6+1
        End If
    EndIf
    
    If zBlowSeq(n)=seq6 And KeyDown(specialK(n)) And zBlowSeq2(n) < maxStars-1 Then
        zBlowSeq(n)=seq1:zBlowSeq2(n)=zBlowSeq2(n)+1
    End If

    If zBlowSeq(n) > seq6 Then zBlowSeq(n)=0:zBlow(n)=0:zBlowSeq2(n)=0

Case 8    ;Dodging
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=5
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=7:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=8:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=9:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=10:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;sword flipping jump (down special)
    ztopspeed(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    seq1=10:seq2=seq1+4:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2:seq6=seq5+2:seq7=seq6+2:seq8=seq7+2
    seq9=seq8+1:seq10=seq9+1:seq11=seq10+1:seq12=seq11+1:seq13=seq12+1:seq14=seq13+1
    seq15=seq14+12:seq16=seq15+6

    zNoMove(n)=1
    If zBlowSeq(n) > seq1 Then zNoMove(n)=0

;=========== Sounds ===========
    If gameSound Then
        If zBlowSeq(n) = seq2 Then
            zJump(n)=0
        End If
        
        If zBlowSeq(n) = seq11 Then PlaySound hanzoBlow2Snd
    End If
    
    If zHitHead(n)=1 Then zBlowSeq(n)= seq16+1
    
;=========== Animation ===========
    zani(n)=12
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zf(n)=7
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zf(n)=8
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zf(n)=9
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zf(n)=10
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zf(n)=11
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zf(n)=12
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zf(n)=13
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zf(n)=14
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then zf(n)=15
    
;=========== Movement ============
    If zBlowStill(n)=0 Then
        If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq4 moveX(n,zface(n),3.2):moveY(n,-3.2):zantiplat(n)=1
        If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq7 moveX(n,zface(n),3.2):moveY(n,-2.0):zantiplat(n)=1
        If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq11 moveX(n,zface(n),3.2):moveY(n,-0.5):zantiplat(n)=1
        If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 moveX(n,zface(n),2.6):moveY(n,0.5):zNograv(n)=0
        If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq14 moveX(n,zface(n),1.0):moveY(n,0.5):zNograv(n)=0
        If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq16 moveY(n,3.2):zNograv(n)=0
    End If
    
;=========== Hitboxes ===========
    If zF(n)>=3 And zF(n)<=15
        If zf(n)=3 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=7.78638:yblow(n,nn)=84.8803:wblow(n,nn)=11:hblow(n,nn)=10:nn=nn+1
            xblow(n,nn)=18.7864:yblow(n,nn)=89.8803:wblow(n,nn)=9:hblow(n,nn)=9:nn=nn+1
            xblow(n,nn)=27.7864:yblow(n,nn)=95.8803:wblow(n,nn)=12:hblow(n,nn)=11:nn=nn+1
        Else If zF(n)=4 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=13.5461:yblow(n,nn)=39.6403:wblow(n,nn)=17:hblow(n,nn)=10:nn=nn+1
            xblow(n,nn)=16.5461:yblow(n,nn)=29.6403:wblow(n,nn)=19:hblow(n,nn)=9:nn=nn+1
            xblow(n,nn)=21.5461:yblow(n,nn)=19.6403:wblow(n,nn)=16:hblow(n,nn)=6:nn=nn+1
        Else If zF(n)=5 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=-12.5593:yblow(n,nn)=13.4402:wblow(n,nn)=9:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=-7.55933:yblow(n,nn)=23.4402:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
            xblow(n,nn)=-4.55933:yblow(n,nn)=36.4402:wblow(n,nn)=11:hblow(n,nn)=11:nn=nn+1
        Else If zF(n)=6 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=-42.7993:yblow(n,nn)=25.0402:wblow(n,nn)=12:hblow(n,nn)=6:nn=nn+1
            xblow(n,nn)=-31.7993:yblow(n,nn)=31.0402:wblow(n,nn)=10:hblow(n,nn)=7:nn=nn+1
            xblow(n,nn)=-22.7993:yblow(n,nn)=38.0402:wblow(n,nn)=14:hblow(n,nn)=8:nn=nn+1
        Else If zF(n)=7 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=-58.0393:yblow(n,nn)=64.6401:wblow(n,nn)=11:hblow(n,nn)=8:nn=nn+1
            xblow(n,nn)=-47.0393:yblow(n,nn)=62.6401:wblow(n,nn)=11:hblow(n,nn)=13:nn=nn+1
            xblow(n,nn)=-36.0393:yblow(n,nn)=57.6401:wblow(n,nn)=12:hblow(n,nn)=14:nn=nn+1
        Else If zF(n)=8 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=-41.2793:yblow(n,nn)=98.0402:wblow(n,nn)=13:hblow(n,nn)=10:nn=nn+1
            xblow(n,nn)=-36.2793:yblow(n,nn)=87.0402:wblow(n,nn)=12:hblow(n,nn)=6:nn=nn+1
            xblow(n,nn)=-32.2793:yblow(n,nn)=81.0402:wblow(n,nn)=13:hblow(n,nn)=11:nn=nn+1
        Else If zF(n)=9 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=-10.5195:yblow(n,nn)=87.6401:wblow(n,nn)=9:hblow(n,nn)=8:nn=nn+1
            xblow(n,nn)=-4.51953:yblow(n,nn)=98.6401:wblow(n,nn)=8:hblow(n,nn)=10:nn=nn+1
            xblow(n,nn)=-1.51953:yblow(n,nn)=112.64:wblow(n,nn)=10:hblow(n,nn)=14:nn=nn+1
        Else If zF(n)=10 Then
            zblowPamount(n)=2:nn=1
            xblow(n,nn)=9.12024:yblow(n,nn)=91.8401:wblow(n,nn)=18:hblow(n,nn)=13:nn=nn+1
            xblow(n,nn)=20.1202:yblow(n,nn)=105.84:wblow(n,nn)=22:hblow(n,nn)=21:nn=nn+1
        Else If zF(n)>=11 And zF(n)<=13 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=21.2402:yblow(n,nn)=70.2401:wblow(n,nn)=12:hblow(n,nn)=13:nn=nn+1
            xblow(n,nn)=35.2402:yblow(n,nn)=65.2401:wblow(n,nn)=12:hblow(n,nn)=13:nn=nn+1
            xblow(n,nn)=47.2402:yblow(n,nn)=59.2401:wblow(n,nn)=10:hblow(n,nn)=12:nn=nn+1
        Else If zF(n)=14 Or zF(n)=15 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=8.27783:yblow(n,nn)=13.4403:wblow(n,nn)=17:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=24.2778:yblow(n,nn)=14.4403:wblow(n,nn)=12:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=37.2778:yblow(n,nn)=12.4403:wblow(n,nn)=10:hblow(n,nn)=8:nn=nn+1
        EndIf
        zBlowStillTime(n)=15:zBlowHold(n)=13
        zHitmode(n)=2:zBlowBack(n)=1
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=2:zHitTime(n)=55
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=30:zBlowBlockTime(n)=20
        zBlowSound(n)=SlashSnd
    End If
    If zOnGnd(n)=0 And zBlowSeq(n) > seq15 Then zf(n)=15:zNoGrav(n)=0:moveX2(n,zface(n),.8):movey(n,1.6)
    If zOnGnd(n)=1 And zBlowSeq(n) > seq15 Then zf(n)=16
    If zongnd(n)=1 And zBlowSeq(n) > seq16 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Kick
    zNoMove(n)=1
    zNoJump(n)=1:zani(n)=14
    seq1=3:seq2=seq1+3:seq3=seq2+2:seq4=seq3+2:seq5=seq4+10:seq6=seq5+4:seq7=seq6+4:seq8=seq7+3
    
;=============== Animation ================
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zf(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zf(n)=2
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zf(n)=1

;=============== Animation ================
    If zBlowSeq(n) > seq3 And zBlowSeq(n) =< seq5 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=-3.95996:yblow(n,nn)=72.0:wblow(n,nn)=14:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=4.04004:yblow(n,nn)=88.0:wblow(n,nn)=17:hblow(n,nn)=19:nn=nn+1
        xblow(n,nn)=19.04:yblow(n,nn)=94.0:wblow(n,nn)=11:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=30.04:yblow(n,nn)=95.0:wblow(n,nn)=8:hblow(n,nn)=11:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=hanzoHit1Snd
    EndIf
    
;=============== Sounds ================
    If zBlowSeq(n) = seq1 And gameSound Then
        randSeed=Rand(6)
        PlaySound hanzoBlow1Snd
        If randSeed=1 Then PlaySound hanzoGrunt1Snd
        If randSeed=2 Then PlaySound hanzoGrunt2Snd
        If randSeed=3 Then PlaySound hanzoGrunt3Snd
        If randSeed=4 Then PlaySound hanzoGrunt4Snd
        If randSeed=5 Then PlaySound hanzoGrunt5Snd
    End If
    
    If zBlowSeq(n) > seq8 Then zBlowSeq(n)=0:zBlow(n)=0

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-15:yed(n)=25
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-16:yed(n)=26
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=1
        xblow(n,nn)=37: yblow(n,nn)=4:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=26:wblow(n,nn)=88:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=16:wblow(n,nn)=88:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=36:wblow(n,nn)=85:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=46:wblow(n,nn)=75:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-8: yblow(n,nn)=56:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=6:zf(n)=5
        eAni(n)=1:ef(n)=3:xED(n)=54:yed(n)=7
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=5 :eAni(n)=1:ef(n)=4:xed(n)=53:yed(n)=6
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-16:yed(n)=26
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
            If zface(n)=2 Then x=zx(n)+15
            If zface(n)=4 Then x=zx(n)-15
            makeshot(n,zShootThis(n),x,y,dir)
            If zface(n)=2 Then x=zx(n)+34
            If zface(n)=4 Then x=zx(n)-34
            makechunk(n,x,zy(n)-27,2,50)
        Else
            If gameSound Then PlaySound shotwallsnd
        EndIf
    
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then
        If zBlowDir(n)=2 Then dir=4 Else dir=2
        zani(n)=10:zf(n)=2
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=25:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=4
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
    
Case 14    ;super ninja stars
    a=5: b=a+5: c=b+8: d=c+8
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0 : spin=4
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = 1 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0:zBlowSeq2(n)=0
    If zBlowSeq2(n) => spin Then zBlowSeq(n)=b
    If zBlowSeq(n) = b-1 Then
        zBlowseq(n)=1
        If zface(n)=2 Then zBlowDir(n)=4:zface(n)=4 Else zBlowDir(n)=2:zface(n)=2
        zblowseq2(n)=zblowseq2(n)+1
        If gameSound=1 Then PlaySound shurikenSnd
    EndIf
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) = a+1 Then 
        dir=zface(n):y=zy(n)-zheight(n)+18
        If zface(n)=2 Then x=zx(n)+15
        If zface(n)=4 Then x=zx(n)-15
        makeshot(n,16,x,y,dir)
    EndIf
    If zBlowSeq(n) => b Then zBlowSeq(n)=0:zBlow(n)=0:

Case 15 ;gaiden throw
    a=12: b=a+8: c=b+8: d=c+8: e=d+30: f=e+500: g=f+8: h=g+15: i=h+15 
    If zblowseq(n) > d And zblowseq(n) < g Then ztopSpeed(n)=1 Else zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=1
    If zBlowSeq(n)= a Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=c+4
    EndIf
    If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=15 Else dir=2:dir2=4:n1=-15
    If zBlowSeq(n) > c And zBlowSeq(n) < d Then 
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=d
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
        Else
            zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+n1:zy(en)=zy(n)
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
    If zblowseq(n) = d+1 And gameSound Then PlaySound hayabusaSnd
    
    If zBlowSeq(n) > d And zBlowSeq(n) < g Then zshield(n)=1    
    If zhithead(n) And zblowseq(n) < f Then zblowseq(n)=e
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zy(n)=zy(n)-8
    If zBlowSeq(n) > e And zblowSeq(n) < g And zongnd(n)=1 Then zBlowseq(n)=g
    
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n1:zy(en)=zy(n):zAni(en)=2:zf(en)=1:zface(en)=dir
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n):zAni(en)=2:zf(en)=6:zface(en)=dir:zy(n)=zy(n)+1
    If zBlowSeq(n) = g  Then
        zx(en)=zx(n)+0:zy(en)=zy(n)
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=55:zHitSeq(en)=0:zHitHold(en)=15
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=dir2
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
        quake=1:quakeSeq=0
        makechunk(n,zx(en),zy(en),2,5)
        If gameSound Then PlaySound mikeKickSnd
    EndIf
    
    If zblowseq(n) > e And zblowseq(n) < f Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=20:hblow(n,nn)=1 :nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=20:hblow(n,nn)=1 :nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=20:hblow(n,nn)=1 :nn=nn+1
        zHitMode(n)=2: zBlowHold(n)=8
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=2:zHitTime(n)=50
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mikeKickSnd
        
        zImune(en,n)=1:zImuneTo(en,n)=n:zImuneSeq(en,n)=0:zImuneTime(en,n)=zEnemyImmuneTime(n)
    EndIf
    If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=3
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=4:zf(n)=1:movex(n,dir,1):zy(n)=zy(n)-5
    
    If zBlowSeq(n) > c And zBlowSeq(n) <= g Then
        zgrabbed(en)=1
        checkZvsWall(en,1)
        ;    zx(en) = zx(n): zy(en) = zy(n)
    EndIf

    If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 16 ;Taunt key
    zNoMove(n)=1
    zNoJump(n)=1
    seq1=16:seq2=seq1+4:seq3=seq2+4:seq4=seq3+4:seq5=seq4+90:seq6=seq5+5:seq7=seq6+5:seq8=seq7+5:seq9=seq8+5
    
    If zOnGnd(n)=0 Then zy(n)=zy(n)-3.2
    
;========== Animation ============
    zAni(n)=16
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zF(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zF(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zF(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zF(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zF(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zF(n)=4
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zF(n)=3
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zF(n)=2
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zF(n)=1
    
;========== Sounds ============
    If gameSound Then
        If zBlowSeq(n)=1 Then PlaySound hanzoTaunt1Snd
        If zBlowSeq(n)=seq5 Then PlaySound hanzoTaunt2Snd
    End If
    
    If zBlowDir(n)=2 Then dir=2 Else dir=4
    If zBlowSeq(n)=seq1 Then extraObj(n,zx(n),8,zy(n),-75,zBlowDir(n),172)
    
    If zBlowSeq(n) > seq9 Then zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function