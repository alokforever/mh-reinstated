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

Select zCurBlow(n)
Case 0    ;Blocking
    
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0
    
Case 15 ;ryu throw
    a=8: b=15: c=25: d=50: e=55: f=60: g=77: h=89: i=104
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
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1:zFace(en)=dir
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
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
    If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 14    ;Super Special
    a=7:b=15:c=130
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2.5 ;zNoGrav(n)=1 
    If zBlowSeq(n) = b-1 And gameSound=1 Then PlaySound  ryuballSnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) =b-2 Then
        checkDist(n,zx(n),zy(n)-20,zFace(n))
        zSuperMove(n)=1:zSuperMoveSeq(n)=0:
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

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1: drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-12:yed(n)=20
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-13:yed(n)=21
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=1
        xblow(n,nn)=40: yblow(n,nn)=4:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=88:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=88:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=85:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=75:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0 :zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=6:zf(n)=4
        eAni(n)=1:ef(n)=3:xED(n)=57:yed(n)=7
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4 :eAni(n)=1:ef(n)=4:xed(n)=56:yed(n)=6
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-13:yed(n)=21
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
        zani(n)=6:zf(n)=2
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=5
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
    a=8:b=10:c=25:d=30
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1    ;LINE 4
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then    ;LINE 7
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=300:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=30:hblow(n,nn)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=18
        zBlowSound(n)=Highpunchsnd
        zani(n)=6:zf(n)=3
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0
    
    
Case 2    ;Flying Kick
    a=7:b=35
    zNoJump(n)=0:ZJUMPING(N)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n) =a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowpamount(n)=2
        xblow(n,1)=-3: yblow(n,1)=16:wblow(n,1)=30:hblow(n,1)=1
        xblow(n,2)=-3: yblow(n,2)=11:wblow(n,2)=30:hblow(n,2)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
        zBlowSound(n)=kicksnd
        zani(n)=8:zf(n)=2
    EndIf
    If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n)
    a=5:b=10:c=25:d=32
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=1:zblowAlert(n)=1:zblowAlert(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        xblow(n,1)=0: yblow(n,1)=9:wblow(n,1)=24:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=5:wblow(n,2)=28:hblow(n,2)=1
        xblow(n,3)=0: yblow(n,3)=1:wblow(n,3)=28:hblow(n,3)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
        zani(n)=9:zf(n)=2
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=3:b=10:c=73
    If zBlowSeq(n) =1 Then
        If gameSound Then PlaySound uppercutsnd
        zBlowUpLimit(n)=zy(n)-c:zJump(n)=0
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1:moveX(n,zBlowdir(n),.5):zblowAlert(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowpamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=18:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=39:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=15:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=Highpunchsnd
        zani(n)=7:zf(n)=2
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) <= c Then
        zblowpamount(n)=6:nn=1
        xblow(n,nn)=0: yblow(n,nn)=66:wblow(n,nn)=6:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=60:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=52:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=44:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=28:wblow(n,nn)=13:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
        If zBlowStill(n)=0 Then zy(n)=zy(n)-3:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=2:zBLowEffect(n)=1:zEnemyImmuneTime(n)=11:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=Highpunchsnd
        zani(n)=7:zf(n)=3:zantiplat(n)=1
    EndIf
    If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=c
    If zBlowSeq(n) => c Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => c-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
Case 7    ;Ryu Ball
    a=7:b=15:c=55
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound ryuballsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n)= b Then 
        dir=zface(n):y=zy(n)-zheight(n)+22
        If zface(n)=2 Then x=zx(n)+10
        If zface(n)=4 Then x=zx(n)-10
        makeshot(n,5,x,y,dir)
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dodging
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=5
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;spinning kick
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=3:b=6:c=9:d=12:e=15:f=18: spinN=4
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zblowseq2(n)=zblowseq2(n)+1:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 Then zani(n)=4:zf(n)=1:ztopSpeed(n)=.5:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=10:zf(n)=1

    Else
    ;zantiplat(n)=1
    If zblowseq(n) => 1 And zblowseq(n) =< a Then zantiplat(n)=1
    
    If zBlowSeq(n) =c+1 And gameSound Then PlaySound blowsnd
    If zBlowSeq(n) =a And gameSound Then PlaySound ryuspinsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),0):zy(n)=zy(n)-2
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),1):zblowAlert(n)=1
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=4:moveX(n,zBlowdir(n),1):zblowAlert(n)=1
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=12:zf(n)=6:moveX(n,zBlowdir(n),1)
    If zblowseq(n) => f Then zblowseq(n)=b:zblowseq2(n)=zblowseq2(n)+1
    
        If zf(n)=3 Then
        zblowpamount(n)=2
        xblow(n,1)=-4: yblow(n,1)=30:wblow(n,1)=32:hblow(n,1)=1
        xblow(n,2)=-4: yblow(n,2)=22:wblow(n,2)=32:hblow(n,2)=1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=1.5:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=3:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=12:zBlowBlockTime(n)=36
        zBlowSound(n)=kicksnd
        EndIf
        
        If zf(n)=5 Then
        zblowpamount(n)=2:zblowback(n)=1
        xblow(n,1)=-18: yblow(n,1)=30:wblow(n,1)=14:hblow(n,1)=1
        xblow(n,2)=-18: yblow(n,2)=22:wblow(n,2)=14:hblow(n,2)=1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=1.5:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=3:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=12
        zBlowSound(n)=kicksnd
        EndIf

    EndIf
    
    If zHitHead(n)=1 Then zBlowseq2(n) = spinN+1
    If zongnd(n)=1 And zBlowSeq2(n) > spinN + 25 Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Kick
    zNoMove(n)=1:zNoJump(n)=1
    a=8:b=18:c=23:d=30
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowPamount(n)=3
        xblow(n,1)=0: yblow(n,1)=65:wblow(n,1)=1:hblow(n,1)=40
        xblow(n,2)=5: yblow(n,2)=65:wblow(n,2)=1:hblow(n,2)=35
        xblow(n,3)=10: yblow(n,3)=65:wblow(n,3)=1:hblow(n,3)=30
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
        zani(n)=14:zf(n)=2
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        xblow(n,1)=28: yblow(n,1)=50:wblow(n,1)=1:hblow(n,1)=20
        xblow(n,2)=20: yblow(n,2)=47:wblow(n,2)=1:hblow(n,2)=20
        xblow(n,3)=7: yblow(n,3)=42:wblow(n,3)=1:hblow(n,3)=20
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=3:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10
        zBlowSound(n)=kicksnd
        zani(n)=14:zf(n)=3
    EndIf
        If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0

Case 16:
    zNoMove(n)=1:zNoJump(n)=1
    zani(n)=16
    seq1=4:seq2=seq1+4:seq3=seq2+4:seq4=seq3+40
    
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
    
Case 17:
    zBlowSeq(n)=0:zBlow(n)=0
        
End Select

End Function