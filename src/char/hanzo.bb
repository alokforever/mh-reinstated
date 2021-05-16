Function setHanzoHitBoxOtoshi(n)
    If zF(n)=12 Or zF(n)=16 Then
        zblowPamount(n)=10:nn=1
        xblow(n,nn)=-22.52:yblow(n,nn)=94.0:wblow(n,nn)=22:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=-0.52002:yblow(n,nn)=93.0:wblow(n,nn)=34:hblow(n,nn)=19:nn=nn+1
        xblow(n,nn)=-22.52:yblow(n,nn)=79.0:wblow(n,nn)=26:hblow(n,nn)=21:nn=nn+1
        xblow(n,nn)=3.47998:yblow(n,nn)=74.0:wblow(n,nn)=34:hblow(n,nn)=15:nn=nn+1
        xblow(n,nn)=-23.52:yblow(n,nn)=57.0:wblow(n,nn)=26:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=3.47998:yblow(n,nn)=59.0:wblow(n,nn)=34:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=-23.52:yblow(n,nn)=42.0:wblow(n,nn)=28:hblow(n,nn)=19:nn=nn+1
        xblow(n,nn)=3.47998:yblow(n,nn)=42.0:wblow(n,nn)=34:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=-21.52:yblow(n,nn)=22.0:wblow(n,nn)=24:hblow(n,nn)=16:nn=nn+1
        xblow(n,nn)=2.47998:yblow(n,nn)=21.0:wblow(n,nn)=30:hblow(n,nn)=7:nn=nn+1
    Else If zF(n)=13 Or zF(n)=15 Or zF(n)=17 Then
        zblowPamount(n)=8:nn=1
        xblow(n,nn)=-35.52:yblow(n,nn)=93.0:wblow(n,nn)=18:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=-18.52:yblow(n,nn)=95.0:wblow(n,nn)=23:hblow(n,nn)=22:nn=nn+1
        xblow(n,nn)=-44.52:yblow(n,nn)=74.0:wblow(n,nn)=25:hblow(n,nn)=24:nn=nn+1
        xblow(n,nn)=-19.52:yblow(n,nn)=75.0:wblow(n,nn)=24:hblow(n,nn)=25:nn=nn+1
        xblow(n,nn)=-42.52:yblow(n,nn)=51.0:wblow(n,nn)=26:hblow(n,nn)=24:nn=nn+1
        xblow(n,nn)=-17.52:yblow(n,nn)=48.0:wblow(n,nn)=19:hblow(n,nn)=20:nn=nn+1
        xblow(n,nn)=-38.52:yblow(n,nn)=24.0:wblow(n,nn)=17:hblow(n,nn)=19:nn=nn+1
        xblow(n,nn)=-18.52:yblow(n,nn)=25.0:wblow(n,nn)=17:hblow(n,nn)=15:nn=nn+1
    Else If zF(n)=14 Or zF(n)=18 Then
        zblowPamount(n)=6:nn=1
        xblow(n,nn)=-58.52:yblow(n,nn)=93.15:wblow(n,nn)=29:hblow(n,nn)=26:nn=nn+1
        xblow(n,nn)=-29.52:yblow(n,nn)=94.15:wblow(n,nn)=30:hblow(n,nn)=29:nn=nn+1
        xblow(n,nn)=-58.52:yblow(n,nn)=66.15:wblow(n,nn)=32:hblow(n,nn)=27:nn=nn+1
        xblow(n,nn)=-27.52:yblow(n,nn)=65.15:wblow(n,nn)=29:hblow(n,nn)=30:nn=nn+1
        xblow(n,nn)=-63.52:yblow(n,nn)=40.15:wblow(n,nn)=36:hblow(n,nn)=23:nn=nn+1
        xblow(n,nn)=-28.52:yblow(n,nn)=36.15:wblow(n,nn)=26:hblow(n,nn)=22:nn=nn+1
    End If
End Function

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
    endSeq=seq6+1
    airSlashSeq=100
    seqB1=airSlashSeq+5:seqB2=seqB1+1:seqB3=seqB2+1:seqB4=seqB3+1:seqB5=seqB4+1:seqB6=seqB5+1:seqB7=seqB6+10
    
    zNoJump(n)=0:ZJUMPING(N)=1
    If zBlowSeq(n)=1 And upKey(n)=1 Then zBlowSeq(n)=airSlashSeq

;============ Animation =============
    zani(n)=8
    If zBlowSeq(n) < airSlashSeq Then
        If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zf(n)=1:zblowAlert(n)=1
        If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zf(n)=2
        If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zf(n)=3
        If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zf(n)=4
        If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zf(n)=5
        If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zf(n)=6
    Else
        ;--- airslashseq
        If zBlowSeq(n) > airSlashSeq And zBlowSeq(n) <= seqB1 Then zf(n)=7:zblowAlert(n)=1
        If zBlowSeq(n) > seqB1 And zBlowSeq(n) <= seqB2 Then zf(n)=8
        If zBlowSeq(n) > seqB2 And zBlowSeq(n) <= seqB3 Then zf(n)=9
        If zBlowSeq(n) > seqB3 And zBlowSeq(n) <= seqB4 Then zf(n)=10
        If zBlowSeq(n) > seqB4 And zBlowSeq(n) <= seqB5 Then zf(n)=11
        If zBlowSeq(n) > seqB5 And zBlowSeq(n) <= seqB6 Then zf(n)=12
        If zBlowSeq(n) > seqB6 And zBlowSeq(n) <= seqB7 Then zf(n)=13
    End If

;============ Sounds =============
    If gameSound Then
        If zBlowSeq(n)=seq4-1 Or zBlowSeq(n)=seqB1-1 Then
            PlaySound hanzoBlow2Snd
            If Rand(2)=1 Then
                PlayHanzoGruntSnd()
            End If
        End If
    End If

;============ Hitboxes =============
    If (zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq5) Or (zBlowSeq(n) > seqB1 And zBlowSeq(n) <= seqB6) Then
        If zBlowStill(n)=1 And (zF(n)=8 Or zF(n)=10) Then zF(n)=9
        If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then
            zblowPamount(n)=4:nn=1
            xblow(n,nn)=2.75998:yblow(n,nn)=68.8:wblow(n,nn)=22:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=3.75998:yblow(n,nn)=56.8:wblow(n,nn)=21:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=1.75998:yblow(n,nn)=45.8:wblow(n,nn)=24:hblow(n,nn)=14:nn=nn+1
            xblow(n,nn)=16.76:yblow(n,nn)=31.8:wblow(n,nn)=15:hblow(n,nn)=10:nn=nn+1
        Else If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then
            zblowPamount(n)=5:nn=1
            xblow(n,nn)=-14.4806:yblow(n,nn)=24.0001:wblow(n,nn)=16:hblow(n,nn)=22:nn=nn+1
            xblow(n,nn)=0.519409:yblow(n,nn)=25.0001:wblow(n,nn)=17:hblow(n,nn)=18:nn=nn+1
            xblow(n,nn)=15.5194:yblow(n,nn)=33.0001:wblow(n,nn)=17:hblow(n,nn)=17:nn=nn+1
            xblow(n,nn)=32.5194:yblow(n,nn)=49.0001:wblow(n,nn)=9:hblow(n,nn)=19:nn=nn+1
            xblow(n,nn)=38.5194:yblow(n,nn)=67.0001:wblow(n,nn)=10:hblow(n,nn)=21:nn=nn+1
        Else If zBlowSeq(n) > seqB1 And zBlowSeq(n) <= seqB4
            zblowPamount(n)=4:nn=1
            xblow(n,nn)=8.52014:yblow(n,nn)=86.0001:wblow(n,nn)=29:hblow(n,nn)=11:nn=nn+1
            xblow(n,nn)=7.52014:yblow(n,nn)=73.0001:wblow(n,nn)=38:hblow(n,nn)=14:nn=nn+1
            xblow(n,nn)=7.52014:yblow(n,nn)=57.0001:wblow(n,nn)=40:hblow(n,nn)=18:nn=nn+1
            xblow(n,nn)=46.5201:yblow(n,nn)=65.0001:wblow(n,nn)=4:hblow(n,nn)=12:nn=nn+1
        Else If zBlowSeq(n) > seqB4 And zBlowSeq(n) <= seqB6 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=10.0399:yblow(n,nn)=64.8:wblow(n,nn)=35:hblow(n,nn)=21:nn=nn+1
            xblow(n,nn)=7.03992:yblow(n,nn)=43.8:wblow(n,nn)=36:hblow(n,nn)=14:nn=nn+1
            xblow(n,nn)=1.03992:yblow(n,nn)=30.8:wblow(n,nn)=32:hblow(n,nn)=12:nn=nn+1
        End If
        zHitMode(n)=0:zBlowHold(n)=5
        zBlowDamage(n)=9:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=5:zBlowBlockTime(n)=25
        zBlowSound(n)=slashSnd
        zChunkType(n)=95 ;blood
    EndIf
    If zBlowSeq(n) > seq6 And zBlowSeq(n) < airSlashSeq Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zBlowSeq(n) > seqB7 Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
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
    seq10=seq9+1:seq11=seq10+1
    seq12=seq11+4:seq13=seq12+4:seq14=seq13+4
    
    If zblowseq(n)=1 Then
        zBlowUpLimit(n)=zy(n)-128
    EndIf
    
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 And zBlowSeq(n) > seq14 Then zani(n)=4:zf(n)=5:ztopSpeed(n)=1:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=4:zf(n)=6
        
        ;----- End animation ------
        If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zani(n)=7:zf(n)=12
        If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then zani(n)=7:zf(n)=13
        If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then zani(n)=7:zf(n)=14
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
        If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zani(n)=7:zf(n)=10
        If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zani(n)=7:zf(n)=11

        If zBlowSeq(n) > seq1 And zBlowSeq(n) =< seq11 And zblowStill(n)=0 Then moveX(n,zBlowdir(n),1.6):zy(n)=zy(n)-3.2
        
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
            Else If zF(n)=10
                zblowPamount(n)=4:nn=1
                xblow(n,nn)=-59.042:yblow(n,nn)=66.7997:wblow(n,nn)=8:hblow(n,nn)=9:nn=nn+1
                xblow(n,nn)=-50.042:yblow(n,nn)=66.7997:wblow(n,nn)=6:hblow(n,nn)=12:nn=nn+1
                xblow(n,nn)=-44.042:yblow(n,nn)=61.7997:wblow(n,nn)=6:hblow(n,nn)=10:nn=nn+1
                xblow(n,nn)=-37.042:yblow(n,nn)=57.7997:wblow(n,nn)=8:hblow(n,nn)=8:nn=nn+1
            Else If zF(n)=11
                zblowPamount(n)=3:nn=1
                xblow(n,nn)=-3.96387:yblow(n,nn)=96.1999:wblow(n,nn)=8:hblow(n,nn)=11:nn=nn+1
                xblow(n,nn)=3.03613:yblow(n,nn)=109.2:wblow(n,nn)=7:hblow(n,nn)=11:nn=nn+1
                xblow(n,nn)=8.03613:yblow(n,nn)=115.2:wblow(n,nn)=6:hblow(n,nn)=7:nn=nn+1
            End If
            zHitmode(n)=2:zBlowHold(n)=4
            zHitSpeed#(n)=2:zHitUpSpeed#(n)=3:zHitTime(n)=45
            zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowStillTime(n)=5:zBlowBlockTime(n)=20
            zBlowSound(n)=SlashSnd:zchunkType(n)=95 ;blood
        End If
        If zblowseq(n) >= seq11 Then zblowseq(n) = seq1 : zblowseq2(n)=zblowseq2(n)+1
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
    ztopspeed(n)=1.6
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
        If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq16
            zNograv(n)=0
            checkYDist(n,zx(n),zy(n),2)
            If yDist(n) >= 10 Then moveY(n,3.2)
        End If
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
        zBlowSound(n)=SlashSnd:zChunkType(n)=95 ;blood
    End If
    If zOnGnd(n)=0 And zBlowSeq(n) > seq15 Then zf(n)=15:zNoGrav(n)=0:moveX2(n,zface(n),.8):movey(n,1.6)
    If zOnGnd(n)=1 And zBlowSeq(n) > seq15 Then zf(n)=16:zNoMove(n)=1
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

Case 15 ;hanzo throw
    zNoJump(n)=1
    ;---- grab start ----
    seq1=8:seq2=seq1+3:seq3=seq2+3:seq4=seq3+3:seq5=seq4+12:seq6=seq5+3:seq7=seq6+3:seq8=seq7+3 ;38
    ;--- grab success ---
    seq9=seq8:seq10=seq9+8:seq11=seq10+16:seq12=seq11+8 ;70
    ;--- izuna otoshi up (fast) ---
    seq13=seq10+32 ;102
    ;--- izuna otoshi up (slow) ---
    seq14=seq13+64 ;166
    ;--- izuna otoshi down ---
    seq15=seq14+1000 ;1166
    ;--- izuna otoshi wait ---
    seq16=seq15+32
    ;--- back flip ---
    seq17=seq16+7:seq18=seq17+8:seq19=seq18+5:seq20=seq19+5:seq21=seq20+5:seq22=seq21+7

    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq8 Then zY(n)=zy(n)-3.2
    If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq14 Then zNoGrav(n)=1
    
    If zBlowSeq(n) > seq12 And zBlowseq(n) <= seq15 Then
        ztopspeed(n)=4.0
    Else
        zNoMove(n)=1
    End If
    
    ;--- Grabbing ---
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq6 Then
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=seq9+2
    End If
    en=zGrabsThis(n)
    If zface(n)=2 Then
        dir=4:dir2=2:enXOffset1=15:enXOffset2=-20:enXOffset3=-45
    Else
        dir=2:dir2=4:enXOffset1=-15:enXOffset2=20:enXOffset3=45
    End If

    If zBlowSeq(n) > seq9 And zBlowSeq(n) < seq10 Then
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=seq10+1
            zGrabbed(en)=1:zFace(en)=dir:zBouncedGnd(en)=0
        Else
            zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+enXOffset1:zy(en)=zy(n)
            zAni(en)=2:zf(en)=1
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            If shotKey(en)=1 Or specialKey(en)=1 Then zLetGoSeq(en)=zLetGoSeq(en)+1
            If Blockkey(n)=1 Or zLetGoSeq(en) > zLetGoAmount(en) Then
                zhit(en)=0:zgrabbedby(en)=0:zgrabbed(en)=0
                zHitTime(en)=0:zFallTime(en)=0:zHitSeq(en)=0
                zgrabsThis(n)=0
                zgrabs(n)=0
                zBlowSeq(n)=seq22
            EndIf
        EndIf
    EndIf
    
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq15 Then initParalysis(n, en, 1)
    
    If zBlowSeq(n) = seq15+1 Then
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10:zAni(en)=2:zF(n)=0
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
        quake=1:quakeSeq=0
        extraObj(n,zx(en),-40,zy(en)+10,0,2,173)
        extraObj(n,zx(en),40,zy(en)+10,0,2,173)
        If gameSound Then PlaySound hanzoExplodeSnd
    EndIf
    
    ;----------------
    
    If zBlowSeq(n)=seq8 Then zBlowSeq(n)=seq22+1
    
;========== Animation =========
    zAni(n)=15
    ;---- grab start ----
    If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zF(n)=1
    If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zF(n)=2
    If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zF(n)=3
    If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zF(n)=4
    If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zF(n)=5
    If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zF(n)=6
    If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zF(n)=7
    If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zF(n)=8
    ;---- grab success ----
    If zBlowSeq(n) > seq9 And zBlowSeq(n) <= seq10 Then zF(n)=9
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq11 Then zF(n)=10
    If zBlowSeq(n) > seq11 And zBlowSeq(n) <= seq12 Then zF(n)=11
    ;--- izuna otoshi up (fast) ---
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 Then
        If zBlowSeq(n) Mod 4 = 0 Then
            If zF(n)=12 Then
                zF(n)=13
            Else If zF(n)=13 Then
                zF(n)=14
            Else If zF(n)=14 Then
                zF(n)=15
            Else
                zF(n)=12
            End If
        End If
    End If
    ;--- izuna otoshi up (slow) ---
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 Then
        If zBlowSeq(n) Mod 8 = 0 Then
            If zF(n)=12 Then
                zF(n)=13
            Else If zF(n)=13 Then
                zF(n)=14
            Else If zF(n)=14 Then
                zF(n)=15
            Else
                zF(n)=12
            End If
        End If
    End If
    ;--- izuna otoshi down ---
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then
        If zBlowSeq(n) Mod 4 = 0 Then
            If zF(n)=16 Then
                zF(n)=17
            Else If zF(n)=17 Then
                zF(n)=18
            Else If zF(n)=18 Then
                zF(n)=19
            Else
                zF(n)=16
            End If
        End If
    End If
    
    ;--- swap render priority ---
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq15 Then
        If n < en And (zF(n)=15 Or zF(n)=19) Then swapCharRenderQueuePos(n, en)
        If n > en And (zF(n) <> 15 And zF(n) <> 19) Then swapCharRenderQueuePos(n, en)
    End If
    
    If zBlowSeq(n) > seq14 And zBlowSeq(n) < seq15 And zOnGnd(n)=1 Then zBlowSeq(n)=seq15
    ;--- izuna otoshi wait ---
    If zBlowSeq(n) > seq15 And zBlowSeq(n) <= seq16 Then zF(n)=20
    ;--- back flip ---
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq17 Then zani(n)=5:zf(n)=5
    If zBlowSeq(n) > seq17 And zBlowSeq(n) <= seq18 Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > seq18 And zBlowSeq(n) <= seq19 Then zani(n)=5:zf(n)=7:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > seq19 And zBlowSeq(n) <= seq20 Then zani(n)=5:zf(n)=8:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > seq20 And zBlowSeq(n) <= seq21 Then zani(n)=5:zf(n)=9:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > seq21 And zBlowSeq(n) <= seq22 Then zani(n)=5:zf(n)=10:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > seq16 And zBlowSeq(n) <= seq22 Then zshield(n)=1:zheight(n)=zduckHeight(n):zantiplat(n)=1

;========== Movement =========
    If zBlowSeq(n) > seq12 And zBlowSeq(n) <= seq13 And zHitHead(n)=0 Then moveY(n,-5.0)
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq14 And zHitHead(n)=0 Then
        upSpeed# = ((seq14 - (zBlowSeq(n)-1)) / 64.0) * -1.0
        moveY(n,upSpeed#)
    End If
    If zBlowSeq(n) > seq14 And zBlowSeq(n) <= seq15 Then moveY(n,3.2)
    
    If zBlowSeq(n) > seq10 And zBlowSeq(n) <= seq15 Then
        checkDist(n,zx(n),zy(n),4)
        leftWallDist=xDist(n)
        checkDist(n,zx(n),zy(n),2)
        rightWallDist=xDist(n)
        If leftWallDist <= 60 Then zx(n)=zx(n)+(65-leftWallDist)
        If rightWallDist <= 60 Then zx(n)=zx(n)-(65-rightWallDist)
        
        zAni(en)=2
        If zBlowSeq(n) <= seq14 Then zF(en)=1 Else zF(en)=6
        If zBlowSeq(n) > seq12 Then
            zy(en)=zy(n)-20:zface(en)=dir
            If zF(n)=12 Or zF(n)=16 Then zx(en)=zx(n)+enXOffset1
            If zF(n)=13 Or zF(n)=17 Then zx(en)=zx(n)+enXOffset2
            If zF(n)=14 Or zF(n)=18 Then zx(en)=zx(n)+enXOffset3
            If zF(n)=15 Or zF(n)=19 Then zx(en)=zx(n)+enXOffset2
        End If
    End If
    
    ; Hitboxes
    If zblowseq(n) > seq12 And zblowseq(n) <= seq13 Then
        setHanzoHitBoxOtoshi(n)
        zHitMode(n)=2: zBlowHold(n)=8
        zHitSpeed#(n)=4.8:zHitUpSpeed#(n)=3.2:zHitTime(n)=50:zHitType(n)=0
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=50:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mikeKickSnd
        
        zImune(en,n)=1:zImuneTo(en,n)=n:zImuneSeq(en,n)=0:zImuneTime(en,n)=zEnemyImmuneTime(n)
    EndIf
    
    If zblowseq(n) > seq14 And zblowseq(n) <= seq16 Then
        setHanzoHitBoxOtoshi(n)
        zHitMode(n)=2: zBlowHold(n)=8
        zHitSpeed#(n)=4.8:zHitUpSpeed#(n)=3.2:zHitTime(n)=50:zHitType(n)=0
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=50:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mikeKickSnd
        
        If zBlowSeq(n) < seq16 Then zImune(en,n)=1:zImuneTo(en,n)=n:zImuneSeq(en,n)=0:zImuneTime(en,n)=zEnemyImmuneTime(n)
    EndIf
    
;========== Sounds =========
    If gameSound Then
        If zBlowSeq(n)=seq2 PlayHanzoGruntSnd()
        If zBlowSeq(n) = seq11 Then PlaySound hanzoThrow1Snd
        If zBlowSeq(n) = seq14-10 Then PlaySound hanzoThrow2Snd
        If zBlowSeq(n) = seq16+1 Then PlaySound shotwallsnd
    End If
    
    If zBlowSeq(n) > seq13 And zBlowSeq(n) <= seq15 Then
        zgrabbed(en)=1
        checkZvsWall(en,1)
    EndIf
    
    If zBlowSeq(n) >= seq22 Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 16 ;Taunt key
    zNoMove(n)=1
    zNoJump(n)=1
    seq1=16:seq2=seq1+4:seq3=seq2+4:seq4=seq3+4:seq5=seq4+90:seq6=seq5+5:seq7=seq6+5:seq8=seq7+5:seq9=seq8+5
    taunt2Seq=150
    seq1b=taunt2Seq+16:seq2b=seq1b+5:seq3b=seq2b+5:seq4b=seq3b+5:seq5b=seq4b+24:seq6b=seq5b+3:seq7b=seq6b+3:seq8b=seq7b+5
    seq9b=seq8b+16:seq10b=seq9b+6:seq11b=seq10b+6:seq12b=seq11b+6:seq13b=seq12b+6:seq14b=seq13b+6:seq15b=seq14b+6
    seq16b=seq15b+16:seq17b=seq16b+3:seq18b=seq17b+3:seq19b=seq18b+10
    
    If zOnGnd(n)=0 Then zy(n)=zy(n)-3.2
    
    If zBlowSeq(n)=1 Then
        zTauntSeed(n)=1
        If blockKeyDoubleTap(n)=1 Then
            zBlowSeq(n)=taunt2Seq:zTauntSeed(n)=2
        End If
    End If
    
;========== Animation ============
    zAni(n)=16
    If zTauntSeed(n) = 1 Then
        If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1 Then zF(n)=1
        If zBlowSeq(n) > seq1 And zBlowSeq(n) <= seq2 Then zF(n)=2
        If zBlowSeq(n) > seq2 And zBlowSeq(n) <= seq3 Then zF(n)=3
        If zBlowSeq(n) > seq3 And zBlowSeq(n) <= seq4 Then zF(n)=4
        If zBlowSeq(n) > seq4 And zBlowSeq(n) <= seq5 Then zF(n)=5
        If zBlowSeq(n) > seq5 And zBlowSeq(n) <= seq6 Then zF(n)=4
        If zBlowSeq(n) > seq6 And zBlowSeq(n) <= seq7 Then zF(n)=3
        If zBlowSeq(n) > seq7 And zBlowSeq(n) <= seq8 Then zF(n)=2
        If zBlowSeq(n) > seq8 And zBlowSeq(n) <= seq9 Then zF(n)=1
    Else
        If zBlowSeq(n) > 0 And zBlowSeq(n) <= seq1b Then zF(n)=6
        If zBlowSeq(n) > seq1b And zBlowSeq(n) <= seq2b Then zF(n)=7
        If zBlowSeq(n) > seq2b And zBlowSeq(n) <= seq3b Then zF(n)=8
        If zBlowSeq(n) > seq3b And zBlowSeq(n) <= seq4b Then zF(n)=9
        If zBlowSeq(n) > seq4b And zBlowSeq(n) <= seq5b Then zF(n)=10
        If zBlowSeq(n) > seq5b And zBlowSeq(n) <= seq6b Then zF(n)=11
        If zBlowSeq(n) > seq6b And zBlowSeq(n) <= seq7b Then zF(n)=12
        If zBlowSeq(n) > seq7b And zBlowSeq(n) <= seq8b Then zF(n)=13
        If zBlowSeq(n) > seq8b And zBlowSeq(n) <= seq9b Then zF(n)=14
        If zBlowSeq(n) > seq9b And zBlowSeq(n) <= seq10b Then zF(n)=15
        If zBlowSeq(n) > seq10b And zBlowSeq(n) <= seq11b Then zF(n)=16
        If zBlowSeq(n) > seq11b And zBlowSeq(n) <= seq12b Then zF(n)=17
        If zBlowSeq(n) > seq12b And zBlowSeq(n) <= seq13b Then zF(n)=18
        If zBlowSeq(n) > seq13b And zBlowSeq(n) <= seq14b Then zF(n)=19
        If zBlowSeq(n) > seq14b And zBlowSeq(n) <= seq15b Then zF(n)=20
        If zBlowSeq(n) > seq15b And zBlowSeq(n) <= seq16b Then zF(n)=21
        If zBlowSeq(n) > seq16b And zBlowSeq(n) <= seq17b Then zF(n)=22
        If zBlowSeq(n) > seq17b And zBlowSeq(n) <= seq18b Then zF(n)=23
        If zBlowSeq(n) > seq18b And zBlowSeq(n) <= seq19b Then zF(n)=24
    End If
    
;========== Sounds ============
    If gameSound Then
        If zTauntSeed(n)=1 And zBlowSeq(n)=1 Then PlaySound hanzoTaunt1Snd
        If zBlowSeq(n)=seq5 Then PlaySound hanzoTaunt2Snd
        If zBlowSeq(n)=seq5b Then PlaySound hanzoBlow1Snd
        If zBlowSeq(n)=seq16b Then PlaySound hanzoSwordSheatheSnd
    End If
    
    If zBlowDir(n)=2 Then dir=2 Else dir=4
    If zBlowSeq(n)=seq1 Then extraObj(n,zx(n),8,zy(n),-75,zBlowDir(n),172)
    
    If (zBlowSeq(n) > seq9 And zBlowSeq(n) < taunt2Seq) Or zBlowSeq(n) > seq19b Then zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function