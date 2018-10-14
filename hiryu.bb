Function doWallLeave(n)
    seqStart=650:seq1=seqStart+3:seq2=seq1+3:seq3=seq2+3
    endSeq=350
    zNoMove(n)=1:zNoGrav(n)=1
    
    If zBlowSeq(n)>=seqStart And zBlowSeq(n) < seq1 Then zAni(n)=18:zf(n)=3
    If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then zAni(n)=18:zf(n)=2
    If zBlowSeq(n)>=seq2 And zBlowSeq(n) < seq3 Then zAni(n)=18:zf(n)=1
    
    If zBlowSeq(n)=seq3 Then zBlowSeq(n)=endSeq
    
End Function

Function doScaleWallDown(n)
    seqStart=600:seq1=seqStart+4:seq2=seq1+3
    zNoMove(n)=1:zNoGrav(n)=1
    wallLeaveSeq=650
    wallGrabSeq=450
    If zFace(n)=2 Then oppDir=4
    If zFace(n)=4 Then oppDir=2
    
    checkYDist(n,zx(n),zy(n),2)
    If yDist(n)<8 Then Goto dontMove
    checkDist(n,zx(n),zy(n),oppDir)
    If xDist(n)>11 Then Goto dontMove
    
;---------- Animation -------------
    If zBlowSeq(n)>=seqStart And zBlowSeq(n) < seq1 Then zAni(n)=18:zf(n)=12
    If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then zAni(n)=18:zf(n)=13
    
;---------- Movement --------------
    moveY(n,2)
    
    .dontMove
    If zBlowSeq(n)=seq2 Then zBlowSeq(n)=seqStart
    If downKey(n)=0 Then zBlowSeq(n)=wallGrabSeq+15
    If jumpKey(n)=1 Then zBlowSeq(n)=wallLeaveSeq
    
End Function

Function doScaleWallUp(n)
    seqStart=500:seq1=seqStart+4:seq2=seq1+4:seq3=seq2+3:seq4=seq3+4:seq5=seq4+3:seq6=seq5+4
    zNoMove(n)=1:zNoGrav(n)=1
    wallLeaveSeq=650
    wallGrabSeq=450
    If zFace(n)=2 Then oppDir=4
    If zFace(n)=4 Then oppDir=2
    
    checkDist(n,zx(n),zy(n)-70,oppDir)
    If xDist(n)>11 Then Goto dontMove
    
;---------- Animation -------------
    If zBlowSeq(n)>=seqStart And zBlowSeq(n) < seq1 Then zAni(n)=18:zf(n)=6
    If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then zAni(n)=18:zf(n)=7
    If zBlowSeq(n)>=seq2 And zBlowSeq(n) < seq3 Then zAni(n)=18:zf(n)=8
    If zBlowSeq(n)>=seq3 And zBlowSeq(n) < seq4 Then zAni(n)=18:zf(n)=9
    If zBlowSeq(n)>=seq4 And zBlowSeq(n) < seq5 Then zAni(n)=18:zf(n)=10
    If zBlowSeq(n)>=seq5 And zBlowSeq(n) < seq6 Then zAni(n)=18:zf(n)=11
    
;---------- Movement --------------
    moveY(n,-2)
    
    .dontMove
    If zBlowSeq(n)=seq6 Then zBlowSeq(n)=seqStart
    If upKey(n)=0 Then zBlowSeq(n)=wallGrabSeq+15
    If jumpKey(n)=1 Then zBlowSeq(n)=wallLeaveSeq
    
End Function

Function doWallGrab(n)
    seqStart=450:seq1=seqStart+3:seq2=seq1+3:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:seq6=seq5+3
    zNoMove(n)=1:zNoGrav(n)=1
    wallGrabUpSeq=500
    wallGrabDownSeq=600
    wallLeaveSeq=650
    
;---------- Animation -------------
    If zBlowSeq(n)>=seqStart And zBlowSeq(n) < seq1 Then zAni(n)=18:zf(n)=1
    If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then zAni(n)=18:zf(n)=2
    If zBlowSeq(n)>=seq3 And zBlowSeq(n) < seq4 Then zAni(n)=18:zf(n)=3
    If zBlowSeq(n)>=seq4 And zBlowSeq(n) < seq5 Then zAni(n)=18:zf(n)=4
    If zBlowSeq(n)>=seq5 And zBlowSeq(n) < seq6 Then zAni(n)=18:zf(n)=5

;---------- Sounds ----------------
    If zBlowSeq(n)=seqStart And gameSound Then PlaySound grabSnd
    
    If upKey(n)=1 Then zBlowSeq(n)=wallGrabUpSeq
    If downKey(n)=1 Then zBlowSeq(n)=wallGrabDownSeq
    If jumpKey(n)=1 Then zBlowSeq(n)=wallLeaveSeq

    If zBlowSeq(n)>=seq6 And zBlowSeq(n) < wallGrabUpSeq Then zBlowSeq(n)=seq5
    
End Function

Function doJumpToWall(n)
    seqStart=400
    seq1=seqStart+3:seq2=seq1+4:seq3=seq2+3:seq4=seq3+4:seq5=seq4+3:seq6=seq5+3:seq7=seq6+4:
    seq8=seq7+3
    endSeq=350
    wallGrabSeq=450
    zNoMove(n)=1
    If zFace(n)=2 Then oppDir=4
    If zFace(n)=4 Then oppDir=2
;---------- Animation -------------
    If zBlowSeq(n)>=seqStart And zBlowSeq(n) < seq1 Then zani(n)=4:zf(n)=6
    If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then zani(n)=4:zf(n)=7
    If zBlowSeq(n)>=seq2 And zBlowSeq(n) < seq3 Then zani(n)=4:zf(n)=8
    If zBlowSeq(n)>=seq3 And zBlowSeq(n) < seq4 Then zani(n)=4:zf(n)=9
    If zBlowSeq(n)>=seq4 And zBlowSeq(n) < seq5 Then zani(n)=4:zf(n)=10
    If zBlowSeq(n)>=seq5 And zBlowSeq(n) < seq6 Then zani(n)=4:zf(n)=1
    If zBlowSeq(n)>=seq6 And zBlowSeq(n) < seq7 Then zani(n)=4:zf(n)=2
    If zBlowSeq(n)>=seq7 And zBlowSeq(n) < seq8 Then zani(n)=4:zf(n)=3
;---------- Movement --------------
    If zBlowSeq(n)>=seqStart And zBlowSeq(n) < seq1 Then moveX(n,oppDir,3):moveY(n,-8)
    If zBlowSeq(n)>=seq1 And zBlowSeq(n) < seq2 Then moveX(n,oppDir,3):moveY(n,-8)
    If zBlowSeq(n)>=seq2 And zBlowSeq(n) < seq3 Then moveX(n,oppDir,3):moveY(n,-6)
    If zBlowSeq(n)>=seq3 And zBlowSeq(n) < seq4 Then moveX(n,oppDir,3):moveY(n,-4)
    If zBlowSeq(n)>=seq4 And zBlowSeq(n) < seq5 Then moveX(n,oppDir,3):moveY(n,-3)
    If zBlowSeq(n)>=seq7 And zBlowSeq(n) < seq6 Then moveX(n,oppDir,3):moveY(n,-2)

;---------- Sounds ----------------
    If zBlowSeq(n)=seqStart And gameSound Then PlaySound mvcJump1Snd

;---------- Extras ----------------
    checkDist(n,zx(n),zy(n),oppDir)
    If xDist(n)<=16 Then 
        zBlowSeq(n)=wallGrabSeq
        xGap=11
        If zFace(n)=2 Then zx(n)=zx(n)-(xDist(n)-xGap)
        If zFace(n)=4 Then zx(n)=zx(n)+(xDist(n)-xGap)
    End If
    
    If zBlowSeq(n)=seq8 Then zJumpFallSeq(n)=11:zBlowSeq(n)=endSeq
End Function

;----------------------------- Make Strider Hiryu's moves! -----------------------------------
Function DoHiryu(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq6
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq6

zchunkType(n)=20

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If zblocked(n)=1 Then zani(n)=13:zf(n)=2
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

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
    jumpToWallSeq=400
    wallGrabSeq=450
    wallGrabUpSeq=500
    wallGrabDownSeq=600
    wallLeaveSeq=650
    endSeq=350
    
    If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 Then zBlowSeq(n)=jumpToWallSeq:downKeyDoubleTap(n)=0
    If zBlowSeq(n) >= jumpToWallSeq And zBlowSeq(n) < wallGrabSeq Then doJumpToWall(n)
    If zBlowSeq(n) >= wallGrabSeq And zBlowSeq(n) < wallGrabUpSeq Then doWallGrab(n)
    If zBlowSeq(n) >= wallGrabUpSeq And zBlowSeq(n) < wallGrabDownSeq Then doScaleWallUp(n)
    If zBlowSeq(n) >= wallGrabDownSeq And zBlowSeq(n) < wallLeaveSeq Then doScaleWallDown(n)
    If zBlowSeq(n) >= wallLeaveSeq Then doWallLeave(n)
    
    If zBlowSeq(n)>=endSeq And zBlowSeq(n)<jumpToWallSeq Then zBlowSeq(n)=0:zBlow(n)=0
    
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

Case 1    ;High Punch
    a=13:b=15:c=25
    zNoMove(n)=1: zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n)= a-1 Then If gameSound Then PlaySound swordSnd
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then
        zblowPamount(n)=2
        xblow(n,1)=0: yblow(n,1)=39:wblow(n,1)=45:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=32:wblow(n,2)=42:hblow(n,2)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=slashSnd
        zani(n)=6:zf(n)=2
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=7:b=35
    zNoJump(n)=0:ZJUMPING(N)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n) =a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowpamount(n)=2:nn=1
        xblow(n,nn)=-3: yblow(n,nn)=15:wblow(n,nn)=24:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=7:wblow(n,nn)=24:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=9:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
        zBlowSound(n)=kicksnd
        zani(n)=8:zf(n)=2
    EndIf
    If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    a=7:b=14:c=21:d=28:e=35
    zheight(n)=zduckheight(n)
    If zBlowSeq(n)= a Then If gameSound Then PlaySound swordSnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        xblow(n,1)=0: yblow(n,1)=31:wblow(n,1)=51:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=25:wblow(n,2)=45:hblow(n,2)=1
        xblow(n,3)=0: yblow(n,3)=15:wblow(n,3)=42:hblow(n,3)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=SlashSnd
        zani(n)=9:zf(n)=2
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=3
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Excalibur (Up special)
    seq1=3:seq2=seq1+3:seq3=seq2+3:seq4=seq3+4
    seq5=seq4+21
    endSeq=35
    zNoMove(n)=0
    ztopspeed(n)=1
    zNoJump(n)=1:zJumping(n)=0:zjump(n)=0
    zNoGrav(n)=1
    
;---------- Animation ----------
    If zBlowSeq(n)>0 And zBlowSeq(n)<=seq1 Then zani(n)=7:zf(n)=1
    If zBlowSeq(n)>seq1 And zBlowSeq(n)<=seq2 Then zani(n)=7:zf(n)=2
    If zBlowSeq(n)>seq2 And zBlowSeq(n)<=seq3 Then zani(n)=7:zf(n)=3
    If zBlowSeq(n)>seq3 And zBlowSeq(n)<=seq4 Then zani(n)=7:zf(n)=4
    If zBlowSeq(n)>seq4 And zBlowSeq(n)<=seq5 Then 
        If zBlowSeq(n) Mod 7 = 0 Then extraObj(n,zx(n),-15,zy(n),50,zFace(n),139)
        If zBlowSeq(n) Mod 3=0 Then
            If zf(n)=5 Then 
                zf(n)=6 
            Else If zf(n)=6 Then 
                zf(n)=7
            Else
                zf(n)=5
            End If
        End If
        moveX(n,zBlowdir(n),2):zantiplat(n)=1
        If zHitHead(n)=0 Then moveY(n,-5)
        
;--------- Hit box ------------
        zblowPamount(n)=4:nn=1:zBlowBack(n)=1
        xblow(n,nn)=-10: yblow(n,nn)=48:wblow(n,nn)=33:hblow(n,nn)=18:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=30:wblow(n,nn)=33:hblow(n,nn)=15:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=15:wblow(n,nn)=33:hblow(n,nn)=15:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=0:wblow(n,nn)=33:hblow(n,nn)=5:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=4:zBlowStillTime(n)=1
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=3.8:zHitDownSpeed#(n)=0:zHitTime(n)=50
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=9:zBlowBlockTime(n)=40
        zBlowSound(n)=hiryuSlashSnd
    End If
    
    If zBlowSeq(n) >= endSeq Then
        zNoGrav(n)=0:ztopSpeed(n)=1:zNomove(n)=0
        zani(n)=4
        If zBlowSeq(n) Mod 3 = 0 Then
            If zf(n)=4 Then 
                zf(n)=5
            Else If zf(n)=5 Then 
                zf(n)=4
            Else
                zf(n)=4
            End If
        End If
    End If
    
;---------- Sounds -------------
    If zBlowSeq(n)=seq4 And gameSound Then PlaySound hiryuGrunt1Snd
    If zBlowSeq(n)=seq4 And gameSound Then PlaySound mvcJump3Snd
    
    If zongnd(n)=1 And zBlowSeq(n) >= endSeq-2 Then 
        zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0
    End If
    
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
    a=7:b=45
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = a And gameSound=1 Then PlaySound shurikenSnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) = a+1 Then 
        dir=zface(n):y=zy(n)-zheight(n)+15
        If zface(n)=2 Then x=zx(n)+15
        If zface(n)=4 Then x=zx(n)-15
        
        makeshot(n,15,x,y,dir)
    EndIf
    
    If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0:

Case 8    ;Dodging
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=5:b=10:c=15:d=20:e=25:f=30:g=35
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=8
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=9:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=10:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=11:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=12:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=13:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=5:zf(n)=14:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= f Then zshield(n)=1
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;sword flipping jump (down special)
    zNoMove(n)=0
    ztopspeed(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5: b=a+5: c=b+5: d=c+5: e=d+5: f=e+5: g=f+5: h=g+5: i=h+5
    If zBlowSeq(n) = c Then
        If gameSound Then PlaySound hayabusaSnd
        zJump(n)=0
    EndIf
    If zHitHead(n)=1 Then zBlowSeq(n)= e+1
    
    If zBlowSeq(n) > 0 And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=2:moveX(n,zface(n),2):moveY(n,-3):zantiplat(n)=1
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=3:moveX(n,zface(n),2):moveY(n,-1.5):zantiplat(n)=1
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=4:moveX2(n,zface(n),2)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=7:zf(n)=1::moveX2(n,zface(n),2):zNograv(n)=0
    
    If zf(n)=1 Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=37:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=28:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=29:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=15
        zHitSpeed#(n)=5:zHitUpSpeed#(n)=2:zHitTime(n)=55
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=20
        zBlowSound(n)=SlashSnd
        
    EndIf
    
    If zBlowSeq(n) > e Then zani(n)=7:zf(n)=1:zNoGrav(n)=0:moveX2(n,zface(n),.5):movey(n,1)
    If zongnd(n)=1 And zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Kick 
    zNoMove(n)=1
    zNoJump(n)=1
    a=8: b=a+4: c=b+10: d=c+3: e=d+8
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound swordSnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=10: yblow(n,nn)=72:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=62:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=52:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=42:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=32:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=slashSnd
        zani(n)=14:zf(n)=1
    EndIf
    
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function