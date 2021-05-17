;--------------------Marios`s moves----------------------------------------------
Function DoMario(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq4
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1
.noBlowSeq4

zchunkType(n)=20

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 15 ;Mario Throw
    a=8: b=15: c=25: d=50: e=57: f=64: g=73: h=85: i=95
    Nspin=3
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=1
    If zBlowSeq(n)= a Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=c+4
    EndIf
    If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
    
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2 :n1=22:n2=-22:n11=20:n22=-20 Else dir=2:dir2=4 :n1=-22:n2=22:n11=-20:n22=20
    
    If zBlowSeq(n) > c And zBlowSeq(n) < d Then 
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=d+2
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
            zblowSeq2(n)=0
        Else
            zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+(n1):zy(en)=zy(n)
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
;makeRectHit(n, x, y, w, h, dir, hitMode, xHit, yHit, damage, hitHold, chunk, HitSOund)        
    If zBlowSeq(n) => d And zBlowSeq(n) =< g Then
        ;makeRectHit(n, zx(en)-zside(en), zy(en), 20, 20, zface(n), 2, 3, 2, 4, 50, 20, highPunchSnd)
        zshield(n)=1
    EndIf
    If gamesound And zblowseq(n)=d+1 Then PlaySound blowSnd
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=3
    If zBlowSeq(n) => e And zBlowSeq(n) < f-3 Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=3:zface(en)=dir2:zface(n)=dir
    If zBlowSeq(n) => f-3 And zBlowSeq(n) < f Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n22:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=3zface(n)=dir
    If zBlowSeq(n) => f And zBlowSeq(n) < g-3 Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n11:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=3:zface(en)=dir:zface(n)=dir2
    If zBlowSeq(n) => g-3 And zBlowSeq(n) < g Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=3
    If zBlowSeq(n) = g Then
        zBlowSeq2(n)=zBlowSeq2(n)+1:zBlowSeq(n)=d
        If zBlowseq2(n) => Nspin Then zBlowSeq(n) = g
    EndIf
    If zblowseq(n) = g And gameSound Then PlaySound hiahuuSnd
    If zBlowSeq(n) = g  Then
        zx(en)=zx(n)+n1:zy(en)=zy(n)-10
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=dir2
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    ;Collision area when spinning opponent
    If zblowseq(n) > d And zblowseq(n) < g Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=30:hblow(n,nn)=1 :nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=20:hblow(n,nn)=1 :nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=10:hblow(n,nn)=1 :nn=nn+1
        zHitMode(n)=2: zBlowHold(n)=8
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=2:zHitTime(n)=50
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=18:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=MarioFierceSnd
        
        zImune(en,n)=1:zImuneTo(en,n)=n:zImuneSeq(en,n)=0:zImuneTime(en,n)=99
    EndIf
    
    If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=3
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=3
    
    If zBlowSeq(n) > c And zBlowSeq(n) =< g Then
        zgrabbed(en)=1
        If checkZvsWall(en,4) Then zx(en) = zx(n)
    EndIf
   
    If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 14    ;Super special spinning punch
    zTopSpeed(n)=.5:znojump(n)=1:zjump(n)=0
    a=3:b=6:c=9:d=12:h=150: spinN=2
    If zBlowSeq(n) =1 Then
        zSuperMove(n)=1:zSuperMoveSeq(n)=0
        If gamesound Then PlaySound blowsnd
    EndIf
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),zSpeed(n))
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),zSpeed(n))
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=3:moveX(n,zBlowdir(n),zSpeed(n))
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=4:moveX(n,zBlowdir(n),zSpeed(n))
    If zblowseq(n) => d And zblowseq(n) < h And zblowseq2(n) = spinN Then
        zblowseq(n)=h:zf(n)=5
    EndIf
    If zblowseq(n) => d And zblowseq(n) < h Then zblowseq(n)=2:zblowseq2(n)=zblowseq2(n)+1
    If zongnd(n)=0 And zblowstill(n)=0 Then zy(n)=zy(n)-2
            
        sn=Rand(1,2)        
        If rendert>1 And zBlowSeq(n) > 2 And zBlowStill(n)=0 Then
            If sn=1 Then dir=2 Else dir=4
            If gamesound Then PlaySound fireballsnd
            If zBlowSeq(n) < h Then
                makeshot(n,14,zx(n),Rand(zy(n)-8,zy(n)-32),dir)
            Else
                makeshot(n,13,zx(n),Rand(zy(n)-8,zy(n)-32),dir)
            EndIf
        EndIf
        
        If zf(n) <> 5 And zblowseq(n) > 1 Then
        
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-18: yblow(n,nn)=22:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-18: yblow(n,nn)=36:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-13: yblow(n,nn)=12:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        zblowback(n)=1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=0:zHitUpSpeed#(n)=1:zHitTime(n)=30
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),zSpeed(n))
        zBlowDamage(n)=.6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=14:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
        zBlowSound(n)=marioWeaksnd
        EndIf
        
        If zblowseq(n) => h Then
        If gameSound And zBlowstill(n)=0 And zBlowSeq(n)=h Then PlaySound marioUahasnd
        zTopSpeed(n)=0
        zblowpamount(n)=3:nn=1
        xblow(n,nn)=-18: yblow(n,nn)=22:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-18: yblow(n,nn)=36:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-13: yblow(n,nn)=12:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        zblowback(n)=1
        zHitmode(n)=2:zBlowHold(n)=3
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=3:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),zSpeed(n))
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=30
        zBlowSound(n)=marioWeaksnd
        zani(n)=12:zf(n)=5
            If zblowseq(n) > h + 30 Then
                zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0
            EndIf
        EndIf

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-27:yed(n)=18
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-28:yed(n)=19
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=1
        xblow(n,nn)=40: yblow(n,nn)=0:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-4: yblow(n,nn)=26:wblow(n,nn)=86:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-4: yblow(n,nn)=16:wblow(n,nn)=86:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-4: yblow(n,nn)=36:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-4: yblow(n,nn)=46:wblow(n,nn)=73:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=65:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=10:zf(n)=4
        eAni(n)=1:ef(n)=3:xED(n)=51:yed(n)=-8
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=4 :eAni(n)=1:ef(n)=4:xed(n)=51:yed(n)=-8
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-28:yed(n)=19
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
        If zface(n)=2 Then x=zx(n)+20
        If zface(n)=4 Then x=zx(n)-20
        makechunk(n,x,zy(n)-27,2,50)
        Else
            If gameSound Then PlaySound shotwallsnd
        EndIf
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then 
        If zBlowDir(n)=2 Then dir=4 Else dir=2
        zani(n)=6:zf(n)=3
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=18:yed(n)=20
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=3:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=18:yed(n)=20
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=18:yed(n)=20
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=2
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
    a=3:b=7:c=15:d=20:e=24:f=30:g=40:h=43:i=47:j=50
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n)= d Then If gameSound Then PlaySound marioUahasnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=64:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=70:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=12
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=30
        zBlowSound(n)=marioFiercesnd
        zani(n)=6:zf(n)=6
    EndIf
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > h And zBlowSeq(n) =< i Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > i And zBlowSeq(n) =< j Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > j Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=9:b=27:c=36
    zNoJump(n)=0
    If zBlowSeq(n) =a Then If gameSound Then PlaySound hiasnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowpamount(n)=3:nn=1
        xblow(n,nn)=-3: yblow(n,nn)=7:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=15:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-3: yblow(n,nn)=25:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
        zBlowSound(n)=marioFiercesnd
        zani(n)=8:zf(n)=2
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n)
    a=6:b=12:c=18:d=25:e=30:f=35:g=40
    If zBlowSeq(n)= b Then If gameSound Then PlaySound hiasnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=9:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=4
        nn=1
        xblow(n,nn)=10: yblow(n,nn)=33:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=23:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=17:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=marioFiercesnd
        zani(n)=9:zf(n)=4
    EndIf
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=9:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=9:zf(n)=6
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=9:zf(n)=7
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zchunkType(n)=8
    zNoMove(n)=1
    ;zTopSpeed(n)=.5
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=8:b=150:c=155:d=160:e=165:f=170
    If zBlowSeq(n) =1 Then
        If gameSound Then PlaySound mariouppercutsnd
        zBlowUpLimit(n)=zy(n)-73:zJump(n)=0
    EndIf
    If zhithead(n) Then zBlowSeq(n)=f:zBlowupLimit(n)=-9999
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=3: yblow(n,nn)=12:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=26:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=3: yblow(n,nn)=38:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        If zblowseq(n) < 13 Then zHitSpeed#(n)=1.5 Else zhitspeed(n)=3
        zHitUpSpeed#(n)=2:zHitTime(n)=53
        If zBlowStill(n)=0 Then zy(n)=zy(n)-6:moveX(n,zBlowdir(n),3)
        zBlowDamage(n)=1.5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=10:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=coinsnd
        zani(n)=7:zf(n)=1:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=2:zy(n)=zy(n)-.3:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=3:zy(n)=zy(n)-.5:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=7:zf(n)=4:zNograv(n)=1:moveX(n,zBlowdir(n),1.5)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=7:zf(n)=5:zNograv(n)=1:moveX(n,zBlowdir(n),1)
    If zy(n) <= zBlowUpLimit(n)  Then zBlowSeq(n)=b+1:zBlowupLimit(n)=-9999
    
    If zBlowSeq(n) => f Then zani(n)=7:zf(n)=6:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => f-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=3:b=6:c=9
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7    ;fire ball
    a=10:b=20:c=50
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = b And gameSound=1 Then PlaySound fireballsnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) = b Then 
        dir=zface(n):y=zy(n)-zheight(n)+22
        If zface(n)=2 Then x=zx(n)+10
        If zface(n)=4 Then x=zx(n)-10
        
        makeshot(n,7,x,y,dir)
    EndIf
    
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 9    ;spinning punch (down special)
    zTopSpeed(n)=.5:znojump(n)=1:zjump(n)=0
    a=3:b=6:c=9:d=12:h=150: spinN=1
    If zBlowSeq(n) =1 And gameSound Then PlaySound blowsnd
    
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),zSpeed(n))
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),zSpeed(n))
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=3:moveX(n,zBlowdir(n),zSpeed(n))
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=4:moveX(n,zBlowdir(n),zSpeed(n))
            
    If zblowseq(n) => d And zblowseq(n) < h And zblowseq2(n) = spinN Then
        zblowseq(n)=h:zf(n)=5
    EndIf
    If zblowseq(n) => d And zblowseq(n) < h Then zblowseq(n)=2:zblowseq2(n)=zblowseq2(n)+1
    
    If zongnd(n)=0 And zblowstill(n)=0 Then zy(n)=zy(n)-2
                
        If zf(n) <> 5 And zblowseq(n) > 1 Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-18: yblow(n,nn)=22:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-18: yblow(n,nn)=36:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-13: yblow(n,nn)=12:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        zblowback(n)=1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=0:zHitUpSpeed#(n)=1:zHitTime(n)=30
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),zSpeed(n))
        zBlowDamage(n)=.6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=14:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
        zBlowSound(n)=marioWeaksnd

        EndIf
        
        If zblowseq(n) => h Then
        zTopSpeed(n)=0
        zblowpamount(n)=3:nn=1
        xblow(n,nn)=-18: yblow(n,nn)=22:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-18: yblow(n,nn)=36:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-13: yblow(n,nn)=12:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
        zblowback(n)=1
        zHitmode(n)=2:zBlowHold(n)=3
        zHitSpeed#(n)=3:zHitUpSpeed#(n)=3:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),zSpeed(n))
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=30
        zBlowSound(n)=marioWeaksnd
        zani(n)=12:zf(n)=5
            If zblowseq(n) > h + 30 Then
                zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0
            EndIf
        EndIf

Case 10    ;(upBlow)
    zNoMove(n)=1
    zNoJump(n)=1
    a=4:b=11:c=17:d=24:e=30:f=35
    If zBlowSeq(n)= a Then If gameSound Then PlaySound hiasnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=27:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=42:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=55:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0 :zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
        zBlowSound(n)=marioFiercesnd
        zani(n)=14:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=-8: yblow(n,nn)=27:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-8: yblow(n,nn)=42:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-8: yblow(n,nn)=55:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0 :zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
        zBlowSound(n)=marioFiercesnd
        zani(n)=14:zf(n)=4
    EndIf
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=14:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=14:zf(n)=6
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dodging
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    zAni(n)=5
    a=7:b=15:c=20:d=25:e=30:f=37
    
    If zBlowSeq(n) = a And gameSound=1 Then PlaySound marioDodgeSnd
;======= Animation ========
    If zBlowSeq(n)=1 Then
        zF(n)=31
    Else
        If (zBlowSeq(n)-1) Mod 2 = 0 Then zF(n)=zF(n)+1
        If zF(n) > 48 Then zF(n)=48
    End If
    
;======= Movement ========
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0
    
Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function