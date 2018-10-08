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

Case 5    ;flying sword spin (Up special)
    zNoMove(n)=0
    ztopspeed(n)=.5
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0 : spinN=6
    a=4: b=a+3: c=b+3: d=c+3: e=d+3 
    If zblowseq(n)=1 Then
        zBlowUpLimit(n)=zy(n)-80
    EndIf
    j=2
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 Then zani(n)=4:zf(n)=1:ztopSpeed(n)=.5:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=4:zf(n)=1
    Else
        zantiplat(n)=1
        If zBlowSeq(n) =2 And zblowstill(n)=0 And gameSound Then PlaySound shurikenSnd
        If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-j
        
        If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=1
        If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=2
        If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=3
        If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=7:zf(n)=4
        
        If zBlowSeq(n) => a And zBlowSeq(n) =< e And zblowStill(n)=0 Then moveX(n,zBlowdir(n),1):zy(n)=zy(n)-j
        
        If (zf(n)=1 Or zf(n)=4 Or zf(n)=3) And (zblowseq(n) =>a And zblowseq(n) =< e) Then
          If zf(n)=1 Then
            zblowPamount(n)=4:nn=1
          Else
            zblowPamount(n)=5:nn=1
            xblow(n,nn)=-3: yblow(n,nn)=44:wblow(n,nn)=7:hblow(n,nn)=1:nn=nn+1
          EndIf
            xblow(n,nn)=0: yblow(n,nn)=37:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=28:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=29:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
            zHitmode(n)=2:zBlowHold(n)=4
            zHitSpeed#(n)=2:zHitUpSpeed#(n)=3:zHitTime(n)=45
            zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowStillTime(n)=5:zBlowBlockTime(n)=20
            zBlowSound(n)=SlashSnd
        EndIf
        If zblowseq(n) => e Then zblowseq(n) = a : zblowseq2(n)=zblowseq2(n)+1
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
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=5:zf(n)=7:moveX(n,zBlowdir(n),1)

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