Include "src\char\wolverine.bb"
Include "src\char\scorpion.bb"
Include "src\char\subzero.bb"
Include "src\char\wonderwoman.bb"
Include "src\char\juggernaut.bb"
Include "src\char\piccolo.bb"
Include "src\char\hiryu.bb"
Include "src\char\evilryu.bb"
Include "src\char\hulk.bb"
Include "src\char\thor.bb"
Include "src\char\leilei.bb"
Include "src\char\kenshiro.bb"

Function DoRyu(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq1
    EndIf
zBlowSeq(n)=zBlowSeq(n)+1
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
    
;add charater, nothing to add here, but PLEASE do take a minute to understand some variables below
;from ryu punch so you understand some basics behind the animation code and how to attack. As you can see
;there are some letters right in the beginning, a, b, c... they define how many frames you have between each
;animation(sprite) and what you do when within the number range.
;zBlowSeq(n) - this variable starts at 1 and is incremented by 1 each frame the game draws, look at LINE 4
;it says that when the animation is between 1 and 8(letter a) the animation sprite is zani=6 and zf=1. It means
;that the sprite used will be image file 'zblow1.bmp', Look NOW at the source MH2, look for zblow1.bmp and you 
;will understand why zani=6 and zf=1

;Now look at LINE 7, when zBlowSeq(n) is between 15(b) and 25(c) you do all this stuff to make an attack happen.
;zBlowPamount=defines how many collision lines there will be
;xblow,yblow,wblow,hblow=define the each line position relative to the character bottom center.
;zHitMode=zero means the victim will fly acording to his damage. 2 means it will get knocked back acording to
;other variables you can see on another animation
;zBlowHold=how many frames you hold the victim in place before it starts flying
;zBlowDamage=how much damage you inflict on the victim
;zBlowEffect=means there's an attack happening, withiout it, there's no collision between the collision lines and the victim
;zEnemyImmuneTime=how long(in frames) the victim stays immune to the attack once it got hit, this is important, 
;otherwise the victim would keep getting hit every frame during the attack animation from the attacker 
;zBlowStillTime=how long(in frames) the attacker holds his current frame when it hits the victim.
;zBlowBlockTime=how long the victim stays in the blocking position when it blocks the attack.
;zBlowSound=the sound the attack will play during this part of the animation
;zani and zf= defines what image to be displayed for the player as seen before

;Now look at LINE 17, notice the variables zBlowSeq(n) and zBlow(n), you MUST set them to zero to end the move.

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
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0    ;LINE 17
    
    
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

Case 8    ;Dogding
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=10:zf(n)=1:moveX(n,zBlowdir(n),1)

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

Case 17:
    zBlowSeq(n)=0:zBlow(n)=0
        
End Select

End Function
;----------------------------- make rash's moves! -----------------------------------
Function DoRash(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq2
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq2

zchunkType(n)=20

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 15 ;Rash throw
    a=8: b=a+8: c=b+8: d=c+8: e=d+8: f=e+8: g=f+8: h=g+8: i=h+25: j=i+3: l=j+30
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=2
    If zBlowSeq(n)= a+3 Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=c+1
    EndIf
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=1:n2=10    Else dir=2:dir2=4:n1=-1:n2=-10
    hh=45
    
    If zBlowSeq(n)=c-1 Then zBlowSeq(n)=0:zBlow(n)=0
    
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=15:zf(n)=1:zx(en)=zx(n)+n2:zy(en)=zy(n)-15:zAni(en)=2:zf(en)=4
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-hh:zAni(en)=2:zf(en)=4
        
    If zBlowSeq(n) > f And zBlowSeq(n) < g Then 
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=h+1
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=zFace(n)
            zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
        Else
            zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+n1:zy(en)=zy(n)-hh
            zAni(en)=2:zf(en)=4
            zGrabs(n)=1
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            zAni(n)=15:zf(n)=3
            If shotKey(en)=1 Or specialKey(en)=1 Then zLetGoSeq(en)=zLetGoSeq(en)+1
            If Blockkey(n)=1 Or zLetGoSeq(en) > zLetGoAmount(en) Then
                zhit(en)=0:zgrabbedby(en)=0:zgrabbed(en)=0
                zHitTime(en)=0:zFallTime(en)=0zHitSeq(en)=0
                zgrabsThis(n)=0
                zgrabs(n)=0
                zBlowSeq(n)=l
            EndIf
        EndIf
    EndIf
        zface(en)=dir
    If zBlowSeq(n) > h And zBlowSeq(n) < j Then zshield(n)=1
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-(hh-4):zAni(en)=2:zf(en)=4
    If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=15:zf(n)=5:zx(en)=zx(n)+n2:zy(en)=zy(n)-hh:zAni(en)=2:zf(en)=4
    If zblowseq(n) = j Then
        If gameSound Then PlaySound blowSnd
    EndIf
    If zBlowSeq(n) = j  Then
        zx(en)=zx(n)+n1:zy(en)=zy(n)-hh
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=95:zHitSeq(en)=40:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=zface(n)
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    If zBlowSeq(n) > j And zBlowSeq(n) < l Then zani(n)=15:zf(n)=5
    
    If zBlowSeq(n) => c And zBlowSeq(n) < j Then 
        zgrabbed(en)=1:checkZvsWall(en,0):zhit(en)=1
        zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
    EndIf
        
    If zBlowSeq(n) => l Then zBlowSeq(n)=0:zBlow(n)=0
    

Case 14        ; Rash's Super special move
    a=4:b=8:c=12:d=16:e=20:f=24
    zNoJump(n)=0
    zNoGrav(n)=1
    zNomove(n)=1
    zAntiPlat(n)=1
    If zblowseq(n) = a-2 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0:
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then
        zani(n)=8:zf(n)=1
        If zBlowseq2(n)=0 Then zy(n)=zy(n)-4
    EndIf
    If zBlowSeq(n) = a Then If gameSound Then PlaySound blowSnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowpamount(n)=4
        xblow(n,1)=7: yblow(n,1)=0:wblow(n,1)=32:hblow(n,1)=1
        xblow(n,2)=-3: yblow(n,2)=10:wblow(n,2)=53:hblow(n,2)=1
        xblow(n,3)=-3: yblow(n,3)=20:wblow(n,3)=53:hblow(n,3)=1
        xblow(n,4)=-3: yblow(n,4)=30:wblow(n,4)=45:hblow(n,4)=1
        If zBlowseq2(n) < 4 Then zHitMode(n)=2 Else zHitMode(n)=0
        zBlowHold(n)=8
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=1:zHitTime(n)=70
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=24:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
        zBlowSound(n)=RashHitSnd
        zani(n)=8:zf(n)=3
        If zblowseq(n)=b+1 And zBlowStill(n)=0 Then
            dir=zface(n):y=zy(n)
            If zface(n)=2 Then x=zx(n)+20
            If zface(n)=4 Then x=zx(n)-20
            If zBlowseq2(n) < 4 Then
                makeshot(n,11,x,y,dir)
            Else
                makeshot(n,12,x,y,dir)
            EndIf
        EndIf
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=8:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=8:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) =f Then
        zBlowSeq2(n)=zBlowSeq2(n)+1
        If zBlowSeq2(n) < 5 Then zBlowSeq(n)=a-1
    EndIf
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    
Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=25
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-26:yed(n)=26
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
        zani(n)=6:zf(n)=3
        eAni(n)=1:ef(n)=3:xED(n)=54:yed(n)=7
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=3 :eAni(n)=1:ef(n)=4:xed(n)=53:yed(n)=6
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-26:yed(n)=26
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
        zani(n)=6:zf(n)=4
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=4:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=4:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
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
    a=8:b=15:c=25:d=35
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowSnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=2
        xblow(n,1)=0: yblow(n,1)=40:wblow(n,1)=45:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=30:wblow(n,2)=45:hblow(n,2)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=21:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=RashHitSnd
        zani(n)=6:zf(n)=2
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=5:b=10:c=22:d=30:e=40
    zNoJump(n)=0;:ZJUMPING(N)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) =a Then If gameSound Then PlaySound blowSnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowpamount(n)=4
        xblow(n,1)=7: yblow(n,1)=0:wblow(n,1)=32:hblow(n,1)=1
        xblow(n,2)=-3: yblow(n,2)=10:wblow(n,2)=53:hblow(n,2)=1
        xblow(n,3)=-3: yblow(n,3)=20:wblow(n,3)=53:hblow(n,3)=1
        xblow(n,4)=-3: yblow(n,4)=30:wblow(n,4)=45:hblow(n,4)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
        zBlowSound(n)=RashHitSnd
        zani(n)=8:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=8:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=8:zf(n)=5
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    a=7:b=14:c=21:d=28:e=35
    If zblowseq(n) < c Then zheight(n)=zduckheight(n)
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowSnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        xblow(n,1)=0: yblow(n,1)=20:wblow(n,1)=34:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=10:wblow(n,2)=36:hblow(n,2)=1
        xblow(n,3)=0: yblow(n,3)=1:wblow(n,3)=34:hblow(n,3)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=RashHitSnd
        zani(n)=9:zf(n)=3
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=4
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=9:zf(n)=5
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Double flying kick (Up special)
    zNoMove(n)=0
    ztopspeed(n)=.5
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5:b=10:c=15:d=20:spinN=1
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zblowseq2(n)=zblowseq2(n)+1:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 Then zani(n)=4:zf(n)=1:ztopSpeed(n)=.5:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=4:zf(n)=1

    Else
    
        zantiplat(n)=1
        If zBlowSeq(n) =b-1 And gameSound Then PlaySound blowSnd
        If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-1.5
        If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=1:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-1.5
        If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=2
        If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=3:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-1.5
    
        If zblowseq(n) => d Then zblowseq(n)=1:zblowseq2(n)=zblowseq2(n)+1
            
        If zf(n)=2 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=31:wblow(n,nn)=33:hblow(n,nn)=1
            
            zHitmode(n)=2:zBlowHold(n)=0
            zHitSpeed#(n)=2:zHitUpSpeed#(n)=3:zHitTime(n)=50
            If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1):zy(n)=zy(n)-1.5
            zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=12:zBlowBlockTime(n)=36
            zBlowSound(n)=RashHitSnd
        EndIf
        
    EndIf
    
    If zhithead(n) Then zBlowSeq2(n)=spinN+1
    If zongnd(n)=1 And zBlowSeq2(n) > spinN Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=1
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
Case 7    ;Rash Special spike boot
    a=7:b=20:c=25:d=40:e=55
    zNoJump(n)=1
    zNoMove(n)=1
    If zongnd(n)=0 And zblowseq(n) < b Then zNoMove(n)=0
    zjump(n)=0
    If zBlowSeq(n) = b And gameSound=1 Then PlaySound blowSnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=1:movex(n,zBlowDir(n),.5)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then 
        zblowPamount(n)=3
        xblow(n,1)=0: yblow(n,1)=6:wblow(n,1)=24:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=14:wblow(n,2)=27:hblow(n,2)=1
        xblow(n,3)=0: yblow(n,3)=21:wblow(n,3)=25:hblow(n,3)=1
        zHitMode(n)=2:zBlowHold(n)=4
        zHitSpeed#(n)=2.5:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
        zBlowSound(n)=RashHitSnd
        If zblowstill(n)=0 Then zy(n)=zy(n)-3.5:movex(n,zBlowDir(n),.5)
        zani(n)=10:zf(n)=2
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=6
        xblow(n,1)=7: yblow(n,1)=32:wblow(n,1)=33:hblow(n,1)=1
        xblow(n,2)=13: yblow(n,2)=46:wblow(n,2)=30:hblow(n,2)=1
        xblow(n,3)=10: yblow(n,3)=53:wblow(n,3)=30:hblow(n,3)=1
        xblow(n,4)=1: yblow(n,4)=61:wblow(n,4)=31:hblow(n,4)=1
        xblow(n,5)=0: yblow(n,5)=71:wblow(n,5)=32:hblow(n,5)=1
        xblow(n,6)=7: yblow(n,6)=81:wblow(n,6)=16:hblow(n,6)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=28:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=RashHitSnd
        If zblowstill(n)=0 Then zy(n)=zy(n)-3.5:movex(n,zBlowDir(n),2)
        zani(n)=10:zf(n)=3
    EndIf
    
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=2
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=3:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=3:zf(n)=1:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;Down special
    zNoMove(n)=0
    ztopspeed(n)=.5
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=10: b=a+3: c=b+7: d=c+5: e=d+5
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zblowseq2(n)=zblowseq2(n)+1:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 Then zani(n)=4:zf(n)=1:ztopSpeed(n)=.5:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=4:zf(n)=1

    Else

        zantiplat(n)=1
        If zBlowSeq(n) =b-1 And gameSound Then PlaySound blowSnd
        If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:moveX(n,zBlowdir(n),2):zy(n)=zy(n)-1
        If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=1:moveX(n,zBlowdir(n),2):zy(n)=zy(n)-.5
        If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=7:zf(n)=2
        If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=3:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-0

        If zblowseq(n) => d Then zblowseq(n)=1:zblowseq2(n)=zblowseq2(n)+1

        If zf(n)=2 Then
            zblowPamount(n)=3:nn=1
            xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=0: yblow(n,nn)=31:wblow(n,nn)=33:hblow(n,nn)=1

            zHitmode(n)=2:zBlowHold(n)=8
            zHitSpeed#(n)=5:zHitUpSpeed#(n)=2:zHitTime(n)=50
            If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2):zy(n)=zy(n)-.3
            zBlowDamage(n)=7:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=32
            zBlowSound(n)=RashHitSnd
        EndIf

    EndIf

    If zhithead(n) Then zBlowSeq2(n)=spinN+1
    If zongnd(n)=1 And zBlowSeq2(n) > spinN Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High Kick 
    zNoMove(n)=1
    zNoJump(n)=1
    a=8:b=16:c=25:d=32
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=4: yblow(n,nn)=55:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=48:wblow(n,nn)=29:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=41:wblow(n,nn)=29:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=RashHitSnd
        zani(n)=14:zf(n)=2
    EndIf
    
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0
    
Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function
;------------------------------- Spider-Man moves! --------------------------------
Function DoSpiderMan(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

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
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 15 ;spider-man throw
    a=8: b=15: c=25: d=50: e=55: f=60: g=63: h=80: i=95
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=5
    If zBlowSeq(n)= a Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=c+4
    EndIf
    If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
    
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=1:n2=30    Else dir=2:dir2=4:n1=-1:n2=-30
    
    If zBlowSeq(n) > c And zBlowSeq(n) < d Then 
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=d
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            zFace(n)=dir:zBlowDir(n)=dir
            zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
        Else
            zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+n2:zy(en)=zy(n)
            zAni(en)=2:zf(en)=2
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
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=6:zf(n)=1:zx(en)=zx(n)+n1:zy(en)=zy(n)-25:zAni(en)=2:zf(en)=3:zface(en)=dir
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=6:zf(n)=2:zx(en)=zx(n)+n1:zy(en)=zy(n)-28:zAni(en)=2:zf(en)=3:zface(en)=dir
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=6:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-30:zAni(en)=2:zf(en)=4:zface(en)=dir
    If zblowseq(n) = f Then
        If gameSound Then PlaySound huaSnd
    EndIf
    If zBlowSeq(n) = g  Then
        zx(en)=zx(n)+0:zy(en)=zy(n)-30 :zHitSeq(en)=14
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=dir2
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=6:zf(n)=5
    
    If zBlowSeq(n) > d And zBlowSeq(n) < g Then zgrabbed(en)=1:checkZvsWall(en,0)
    If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0

Case 14    ;Super Special
    a=15:b=50
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
        If zBlowSeq(n) = a-1 And zblowseq2(n)=0 Then
        zSuperMove(n)=1:zSuperMoveSeq(n)=0
        If gameSound=1 Then PlaySound webshotsnd
     EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n)= a Then
        dir=zface(n):y=zy(n)-zheight(n)+22
        If zface(n)=2 Then x=zx(n)+10
        If zface(n)=4 Then x=zx(n)-10

        da = makeshot(n,6,x,y,dir)
        shotspeed(da)=6
        shotdamage(da)=25
        shotDrill(da)=1
        shotHitMode(da)=0
        shotImpact(da)=20
        shotImmuneTime(da)=10
        shotChunkType(da)=37
     EndIf
     
    If zBlowSeq(n) > 20 And zBlowSeq2(n)<3 Then zBlowSeq2(n)=zBlowSeq2(n)+1:zBlowSeq(n)=a-10
    
        If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0
    

Case 1    ;High Punch
    a=12:b=17:c=24:d=34:e=43
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n)= a Then If gameSound Then PlaySound huasnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        nn=1
        xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=44:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=44:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=18:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=Highpunchsnd
        zani(n)=6:zf(n)=3
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-22:yed(n)=20
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=22
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
        zHitMode(n)=0:zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=6:zf(n)=3
        eAni(n)=1:ef(n)=3:xED(n)=58:yed(n)=1
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=3 :eAni(n)=1:ef(n)=4:xed(n)=57:yed(n)=0
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=22
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
            If zface(n)=2 Then x=zx(n)+34
            If zface(n)=4 Then x=zx(n)-34
            makechunk(n,x,zy(n)-27,2,50)
        Else
            If gameSound Then PlaySound shotwallsnd
        EndIf
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then 
        If zBlowDir(n)=2 Then dir=4 Else dir=2
        zani(n)=12:zf(n)=7
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=7:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=25:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=7:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=9:zf(n)=6
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

Case 2    ;Flying Kick
    a=5:b=10:c=22:d=30:e=40
    zNoJump(n)=0;:ZJUMPING(N)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) =a Then If gameSound Then PlaySound huasnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowpamount(n)=4
        nn=1
        xblow(n,nn)=20: yblow(n,nn)=0:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=10:wblow(n,nn)=22:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
        zBlowSound(n)=highpunchsnd
        zani(n)=8:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=8:zf(n)=1
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n)
    a=7:b=14:c=21:d=28:e=35:f=40:g=45
    If zblowseq(n) < c Then zheight(n)=zduckheight(n)
    If zBlowSeq(n)= a Then If gameSound Then PlaySound huasnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        nn=1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=53:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=0:wblow(n,nn)=53:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=38:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
        zani(n)=9:zf(n)=3
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=4
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=9:zf(n)=5
    If zBlowSeq(n) => e And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=6
    If zBlowSeq(n) => f And zBlowSeq(n) =< g Then zani(n)=9:zf(n)=7
    
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5:b=10:c=16:d=150
    If zBlowSeq(n) =1 Then
        If gameSound Then PlaySound spiderstingsnd
        zBlowUpLimit(n)=zy(n)-70:zJump(n)=0
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=2:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=29:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=21:wblow(n,nn)=37:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=29:wblow(n,nn)=37:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=Highpunchsnd
        zani(n)=7:zf(n)=3:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=50:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=60:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-20: yblow(n,nn)=68:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=4:zHitTime(n)=45
        If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=Highpunchsnd
        zani(n)=7:zf(n)=4:zantiplat(n)=1
    EndIf
    If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=d
    If zBlowSeq(n) => d Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => d-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=3:b=6:c=9
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7    ;web shot
    a=15:b=50
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound webshotsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n)= a Then 
        dir=zface(n):y=zy(n)-zheight(n)+22
        If zface(n)=2 Then x=zx(n)+10
        If zface(n)=4 Then x=zx(n)-10
        
        makeshot(n,6,x,y,dir)
    EndIf
    
    If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0

Case 9    ;flipping + kick (down special)
    zNoMove(n)=0
    ztopspeed(n)=.5
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40:i=45:j=50:k=55:l=60
    If zBlowSeq(n) = h Then
        If gameSound Then PlaySound huasnd
        zJump(n)=0
    EndIf
    If zHitHead(n)=1 Then zBlowSeq(n)=k:zy(n)=zy(n)+4
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-3
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-3:zheight(n)=zduckheight(n)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),1.5):zy(n)=zy(n)-2:zheight(n)=zduckheight(n)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),1.5):zy(n)=zy(n)-1:zheight(n)=zduckheight(n)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2):zy(n)=zy(n)-1:zheight(n)=zduckheight(n)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2):zy(n)=zy(n)-0:zheight(n)=zduckheight(n)
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=12:zf(n)=3:moveX(n,zBlowdir(n),1.5):zNograv(n)=0
    If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=12:zf(n)=4:moveX(n,zBlowdir(n),1.5):zNograv(n)=0
    
    If zBlowSeq(n) > h And zBlowSeq(n) =< i Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=42:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=48:wblow(n,nn)=42:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=55:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=1:zHitTime(n)=20
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        :zNograv(n)=0
        zBlowSound(n)=Highpunchsnd
        zani(n)=12:zf(n)=5
    EndIf
    If zBlowSeq(n) > i And zBlowSeq(n) =< j Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=14:wblow(n,nn)=43:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=43:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=36:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=8
        zHitSpeed#(n)=6.5:zHitUpSpeed#(n)=2:zHitTime(n)=45
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=40
        :zNograv(n)=0
        zBlowSound(n)=Highpunchsnd
        zani(n)=12:zf(n)=6
    EndIf
    If zBlowSeq(n) > j And zBlowSeq(n) =< k Then zani(n)=12:zf(n)=7:moveX(n,zBlowdir(n),1):zNograv(n)=0
    
    If zBlowSeq(n) => k Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => k-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0


Case 10    ;High Kick 
    zNoMove(n)=1
    zNoJump(n)=1
    a=5:b=8:c=15:d=18:e=21:f=24:g=30:h=35
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound huasnd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=43:wblow(n,nn)=50:hblow(n,nn)=1:nn=nn+1
        
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
        zani(n)=14:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=40:wblow(n,nn)=60:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=7: yblow(n,nn)=47:wblow(n,nn)=44:hblow(n,nn)=1:nn=nn+1
        
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=10:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=kicksnd
        zani(n)=14:zf(n)=4
    EndIf
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=14:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=14:zf(n)=6
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=14:zf(n)=7
    If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=14:zf(n)=8
    If zBlowSeq(n) > h Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=8
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=14:zf(n)=8:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0
    
Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function
;--------------------Marios`s moves----------------------------------------------
Function DoMario(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq4
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
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

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=1:zf(n)=0
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=1:zf(n)=0:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0
    
Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function
;--------------------- Michelangelo's moves ------------------------------------------
Function DoMike(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq5
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq5

zchunkType(n)=50

Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0
    
Case 15 ;mike throw
    a=8: b=15: c=25: d=50: e=55: f=60: g=63: h=80: i=90
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
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-28:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-30:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zblowseq(n) = f Then
        If gameSound Then PlaySound mikeSnd
    EndIf
    If zBlowSeq(n) => g-3 And zBlowSeq(n) < g Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n1:zy(en)=zy(n)-30:zAni(en)=2:zf(en)=6:zface(en)=dir2
    If zBlowSeq(n) = g  Then
        zx(en)=zx(n)+0:zy(en)=zy(n)-13
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=dir
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=4
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=4
    
    If zBlowSeq(n) > c And zBlowSeq(n) < g Then zgrabbed(en)=1:checkZvsWall(en,0)
    If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0

Case 14    ;Mike's Super Special    
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5:b=15:c=20:d=160:e=165:f=170
    If zBlowSeq(n) = 1 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0
    If zBlowSeq(n) = 2 Then
        If gameSound Then PlaySound mikeuppercutsnd
        zBlowUpLimit(n)=zy(n)-74:zJump(n)=0
        
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=4: yblow(n,nn)=15:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=24:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=24: yblow(n,nn)=30:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=24: yblow(n,nn)=36:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=24: yblow(n,nn)=42:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=4
        zHitSpeed#(n)=1.5:zHitUpSpeed#(n)=2:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)  ;17
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=4:zBlowStillTime(n)=4:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
        zani(n)=7:zf(n)=1:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=4: yblow(n,nn)=26:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=41:wblow(n,nn)=31:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=47:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=55:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=61:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=4
        zHitSpeed#(n)=1.5:zHitUpSpeed#(n)=2:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=4:zBlowStillTime(n)=4:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
        zani(n)=7:zf(n)=2:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=51:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=57:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=63:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=69:wblow(n,nn)=24:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=75:wblow(n,nn)=24:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=12
        If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),3)
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
        zani(n)=7:zf(n)=3:zantiplat(n)=1
    EndIf
    
    If zBlowSeq(n) > 1 And zBlowSeq(n) < d Then zShield(n)=1    
    If zblowseq(n) > 5 And zy(n) =< zBlowUpLimit(n) Then
        zBlowSeq(n) = d+1 :zBlowupLimit(n)=0
    EndIf
    If zhithead(n) Then zBlowSeq(n)=d+1:zBlowupLimit(n)=0 
    If zBlowSeq(n) > d Then zani(n)=7:zf(n)=4:zNoGrav(n)=0:ztopSpeed(n)=2:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=20
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-26:yed(n)=21
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
        zani(n)=6:zf(n)=3
        eAni(n)=1:ef(n)=3:xED(n)=54:yed(n)=-1
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=3 :eAni(n)=1:ef(n)=4:xed(n)=53:yed(n)=-2
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-26:yed(n)=21
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
        If zface(n)=2 Then x=zx(n)+17
        If zface(n)=4 Then x=zx(n)-17
        makechunk(n,x,zy(n)-25,2,50)
        Else
            If gameSound Then PlaySound shotwallsnd
        EndIf
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then 
        If zBlowDir(n)=2 Then dir=4 Else dir=2
        zani(n)=2:zf(n)=2
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=15:yed(n)=20
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=2:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=15:yed(n)=20
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=2:zf(n)=2:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=15:yed(n)=20
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=1
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

Case 1    ; High Punch    
    a=12:b=17:c=23:d=30:e=32:f=42
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n)= b Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=18:wblow(n,nn)=43:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=46:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=34:wblow(n,nn)=46:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=41:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=mikePunchsnd
        zani(n)=14:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=14:zf(n)=3
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=9:b=27:c=36
    zNoJump(n)=0
    If zBlowSeq(n) =a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowpamount(n)=4:nn=1
        xblow(n,nn)=20: yblow(n,nn)=0:wblow(n,nn)=8:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=17: yblow(n,nn)=5:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-8: yblow(n,nn)=10:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=15:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
        zBlowSound(n)=mikekicksnd
        zani(n)=8:zf(n)=2
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n)
    a=6:b=12:c=26:d=30:e=35
    If zBlowSeq(n)= b Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        nn=1
        xblow(n,nn)=0: yblow(n,nn)=1:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=mikeKicksnd
        zani(n)=9:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=7:b=15:c=20:d=160:e=165:f=170
    If zBlowSeq(n) = 1 Then
        If gameSound Then PlaySound mikeuppercutsnd
        zBlowUpLimit(n)=zy(n)-74:zJump(n)=0
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=4: yblow(n,nn)=15:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=24:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=24: yblow(n,nn)=30:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=24: yblow(n,nn)=36:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=24: yblow(n,nn)=42:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=1.5:zHitUpSpeed#(n)=2:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=17:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
        zani(n)=7:zf(n)=1:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=4: yblow(n,nn)=26:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=41:wblow(n,nn)=31:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=47:wblow(n,nn)=33:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=55:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=61:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=1.5:zHitUpSpeed#(n)=2:zHitTime(n)=50
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=15:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
        zani(n)=7:zf(n)=2:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=51:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=57:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=63:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=69:wblow(n,nn)=24:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-10: yblow(n,nn)=75:wblow(n,nn)=24:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=4:zHitTime(n)=60
        If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=3:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
        zani(n)=7:zf(n)=3:zantiplat(n)=1
    EndIf
        
    If zy(n) <= zBlowUpLimit(n)  Then zBlowSeq(n) = d+1 :zBlowupLimit(n)=0
    If zhithead(n) Then zBlowSeq(n)=d+1:zBlowupLimit(n)=0
    If zBlowSeq(n) > d Then zani(n)=7:zf(n)=4:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=3:b=6:c=9
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 7    ;dragon breath
    a=10:b=50
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = a And gameSound=1 Then PlaySound mikeBreathSnd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) = a+1 Then 
        dir=zface(n):y=zy(n)-zheight(n)+17
        If zface(n)=2 Then x=zx(n)+15
        If zface(n)=4 Then x=zx(n)-15
        
        makeshot(n,9,x,y,dir)
    EndIf
    
    If zBlowSeq(n) > b Then zBlowSeq(n)=0:zBlow(n)=0:

Case 9    ;flipping shell (down special)
    zNoMove(n)=0
    ztopspeed(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5:b=10:c=15:d=25:e=30:f=35:g=40:h=50:i=55
    If zBlowSeq(n) = a Then
        If gameSound Then PlaySound mikeFlipSnd
        zJump(n)=0
    EndIf
    If zHitHead(n)=1 Then zBlowSeq(n)= h+1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),1):zy(n)=zy(n)-1.5:zantiPlat(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),1):zantiPlat(n)=1
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),1):zNoGrav(n)=0 
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=2:zTopSpeed(n)=0
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=1:If zBlowStill(n)=0 Then zy(n)=zy(n)-4
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=2:If zBlowStill(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=5:zf(n)=3:zNograv(n)=0
    If zBlowSeq(n) > g And zBlowSeq(n) =< h Then zani(n)=5:zf(n)=4:zNograv(n)=0
    
    If zani(n)=5 Then
        zheight(n)=zduckheight(n)
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=-15: yblow(n,nn)=0:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-15: yblow(n,nn)=8:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-15: yblow(n,nn)=17:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-15: yblow(n,nn)=24:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-15: yblow(n,nn)=30:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=1:zHitTime(n)=25
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),3)
        zBlowDamage(n)=1.5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=12:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=mikepunchsnd
    EndIf
    
    If zBlowSeq(n) > h Then zani(n)=7:zf(n)=4:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;(upBlow)
    zNoMove(n)=1
    zNoJump(n)=1
    a=4:b=8:c=12:d=23:e=30:f=35
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blowsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=4: yblow(n,nn)=26:wblow(n,nn)=19:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=37:wblow(n,nn)=27:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=4: yblow(n,nn)=47:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=14: yblow(n,nn)=55:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=14: yblow(n,nn)=62:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=25
        zBlowSound(n)=mikepunchsnd
        zani(n)=14:zf(n)=4
    EndIf
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=14:zf(n)=3
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=14:zf(n)=1:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0
    
Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function

;----------------------------- make Ryu (ninja gaiden)'s moves! -----------------------------------
Function DoGaiden(n)

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

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=3:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=3:zf(n)=1:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

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
;------------------------- Batman ---------------------------------
Function DoBatman(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq7
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq7

zchunkType(n)=50
Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 14    ;Super special batman
    a=8: b=a+5: c=b+5: d=c+8: e=d+8: f=e+8
    zNoMove(n)=1: zNoJump(n)=1
    If zongnd(n)=0 Then zy(n)=zy(n)-2    
    If zblowseq(n) = a Then zSuperMove(n)=1:zSuperMoveSeq(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
    
    If zblowSeq(n)=b Then
        If gamesound Then PlaySound blow2snd
        originalObj=zGotObj(n)
        For k=1 To 3
            da = getObj(n,9)
            objDamage(da)=60
            objImpact(da)=10
            objHitMode(da)=0
            upkey(n)=0 : downKey(n)=0
            If zface(n)=2 Then x=zx(n)+10 Else x=zx(n)-10
            Select k
                Case 1:y=15:objXspeed(da)=5:objYspeed(da)=-2
                Case 2:y=42:objXspeed(da)=1:objYspeed(da)=-5
                Case 3:y=28:objXspeed(da)=2:objYspeed(da)=-3.5
                ;Case 1:y=15:objYspeed(da)=0
                ;Case 2:y=42:objYspeed(da)=-3
                ;Case 3:y=28:objYspeed(da)=-1.5
            End Select
            throwObj(n,x,zy(n)-y,zface(n))
        Next
        zGotObj(n)=originalObj
    EndIf
    
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=10:zf(n)=6
    
    If zblowseq(n) => f Then zBlowSeq(n)=0:zBlow(n)=0

Case 15 ;batman throw
    a=12: b=a+10: c=b+6: d=c+6: e=d+6: f=e+6: g=f+6: h=g+6: i=h+6: j=i+7: k=j+7: l=k+6: m=l+6: o=m+6
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n)= 3 Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=b+4
    EndIf
    If zBlowSeq(n)=a Then zBlowSeq(n)=0:zBlow(n)=0
    
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=3:n2=14:n3=23 Else dir=2:dir2=4:n1=-3: n2=-14: n3=-23
    
    If zBlowSeq(n) > b And zBlowSeq(n) < c Then 
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
                zBlowSeq(n)=l
            EndIf
        EndIf
    EndIf
    
    If zblowseq(n) = f Then
        zBlowDir(n)=dir : zFace(n)=zBlowDir(n)
        If zface(n)=2 Then dir=4:dir2=2:n1=3:n2=14:n3=25 Else dir=2:dir2=4:n1=-3: n2=-14: n3=-25
        If gameSound Then PlaySound capeSnd
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) < k Then zshield(n)=1
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-10:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n2:zy(en)=zy(n)-35:zAni(en)=2:zf(en)=5:zface(en)=dir2
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=5:zx(en)=zx(n)+n1:zy(en)=zy(n)-35:zAni(en)=2:zf(en)=5:zface(en)=dir
    If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=6:zx(en)=zx(n)+n3:zy(en)=zy(n)-18:zAni(en)=2:zf(en)=7:zface(en)=dir
        
    If zBlowSeq(n) = h  Then
        zx(en)=zx(n)+n3:zy(en)=zy(n)-18
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir2 : zFallDir(en)=dir2
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=7
    If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=15:zf(n)=8
    If zBlowSeq(n) => j And zBlowSeq(n) < k Then zani(n)=15:zf(n)=9
    If zBlowSeq(n) => k And zBlowSeq(n) < l Then zani(n)=15:zf(n)=10
    If zBlowSeq(n) => l And zBlowSeq(n) < m Then zani(n)=15:zf(n)=11
    
    If zBlowSeq(n) > b And zBlowSeq(n) < h Then zgrabbed(en)=1:checkZvsWall(en,0)
    If zBlowSeq(n) => m Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=15:zf(n)=9
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
        zani(n)=6:zf(n)=5
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=5:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=25:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=5:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0


Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-25:yed(n)=30
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-26:yed(n)=31
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
        zani(n)=15:zf(n)=9
        eAni(n)=1:ef(n)=3:xED(n)=54:yed(n)=7
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=15:zf(n)=9 :eAni(n)=1:ef(n)=4:xed(n)=53:yed(n)=6
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-26:yed(n)=31
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 1    ; High Punch    
    a=8: b=a+5: c=b+5: d=c+10: e=d+5
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) = c And gameSound Then PlaySound blow2snd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
    If zBlowseq(n) > c And zblowseq(n) =< d Then 
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=0: yblow(n,nn)=38:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=32:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=bhitSnd
        zani(n)=6:zf(n)=4
    EndIf
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=5: b=a+8: c=b+5: d=c+5: e=d+5: f=e+5: g=f+5: h=g+5: i=h+5
    zNoJump(n)=0
    If zBlowSeq(n) = a And gameSound Then PlaySound blow2Snd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=3
    If zblowseq(n) > a And zblowseq(n) < c Then 
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=40:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=40:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=30:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=bHitSnd
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=8:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=8:zf(n)=5
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    a=8: b=a+5: c=b+5: d=c+5: e=d+5
    zheight(n)=zduckheight(n)
    If zBlowSeq(n) = a And gameSound Then PlaySound blow2Snd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=9:zf(n)=3
    If zani(n)=9 And zf(n)=2 Then    
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=40:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=40:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=bHitSnd
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=9:zf(n)=1
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5: b=a+5: c=b+6: d=c+150 
    
    If zBlowSeq(n) =1 Then
        If gameSound Then PlaySound blow2snd
        zBlowUpLimit(n)=zy(n)-70:zJump(n)=0
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=6
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=5:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=25:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=35:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=bhitSnd
        zani(n)=7:zf(n)=1:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=32:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=42:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=53:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=4:zHitTime(n)=45
        If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=bHitSnd
        zani(n)=7:zf(n)=2:zantiplat(n)=1
    EndIf
    If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=d
    If zBlowSeq(n) => d Then zani(n)=15:zf(n)=9:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => d-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 7    ;batrang
    a=8: b=a+5: c=b+5: d=c+5: e=d+5: f=e+5
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = a And gameSound=1 Then PlaySound blow2Snd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) = b+1 Then 
        If shot(zMyShot(n)) =0 Or shotOwner(zMyshot(n)) <> n Or shotType(zMyShot(n)) <> 20 Then
            zMyShot(n)=getShot()
            dir=zface(n):y=zy(n)-zheight(n)+15
            If zface(n)=2 Then x=zx(n)+15
            If zface(n)=4 Then x=zx(n)-15
            makeshot(n,20,x,y,dir)
        EndIf
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=10:zf(n)=6
    
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:

Case 9    ;batman bomb throw (down special)
    a=8: b=a+5: c=b+5: d=c+8: e=d+8: f=e+8
    zNoMove(n)=1: zNoJump(n)=1
    zjump(n)=0    
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
    
    If zblowSeq(n)=b Then
        If gamesound Then PlaySound blow2Snd
        originalObj=zGotObj(n)
        da = getObj(n,9)    ;9 = type of obj
        objImpact(da)=10
        upkey(n)=0 : downKey(n)=0
        If zface(n)=2 Then x=zx(n)+10 Else x=zx(n)-10
        throwObj(n,x,zy(n)-17,zface(n))
        zGotObj(n)=originalObj
    EndIf
    
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=10:zf(n)=6
    
    If zblowseq(n) => f Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=6:zf(n)=5:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 10    ;High Kick 
    zNoMove(n)=1
    zNoJump(n)=1
    a=5: b=a+4: c=b+10: d=c+5: e=d+5
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=5
    If zBlowSeq(n)= a And gameSound Then PlaySound blow2Snd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=7:zf(n)=1
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=32:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=42:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=5: yblow(n,nn)=52:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=0
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=bHitSnd
        zani(n)=14:zf(n)=1
    EndIf
    
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=1
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=5
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function
;------------------------- Predator ---------------------------------
Function DoPredator(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0

    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq8
    EndIf

zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq8

zchunkType(n)=50
Select zCurBlow(n)
Case 0    ;Blocking
    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0

Case 14    ;Super special Predator
    a=5: b=a+5: c=b+5: d=c+5: e=d+5: f=e+5
    zNoMove(n)=1: zNoJump(n)=1
    If zBlowSeq(n) = c-1 And zBlowSeq2(n) < 1 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0    
    If gamesound And zBlowSeq(n) = c And zBlowseq2(n) < 1 Then PlaySound predatorSnd
    
    If zBlowseq(n) = a And rightKey(n)=1 Then zBlowDir(n)=2 
    If zBlowSeq(n) = a And leftKey(n)=1 Then zBlowDir(n)=4
    
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
    
    If zblowSeq(n)=c Then
        If gameSound Then PlaySound laserSnd
        dir=zface(n):y=zy(n)-zheight(n)+12
        If zface(n)=2 Then x=zx(n)+15
        If zface(n)=4 Then x=zx(n)-15
        makeChunk(n,x,y,dir,27)
        makeshot(n,21,x,y,dir)
        zBlowseq2(n)=zBlowSeq2(n)+1
    EndIf
    
    If zBlowseq(n) = e And zBlowSeq2(n) < 10 Then
        zBlowseq(n)=a 
        If rightKey(n)=1 Then zBlowDir(n)=2
        If leftKey(n)=1 Then zBlowDir(n)=4
    EndIf
    
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=10:zf(n)=1
    
    If zblowseq(n) => f Then zBlowSeq(n)=0:zBlow(n)=0

Case 15 ;predator throw
    a=12: b=a+10: c=b+6: d=c+6: e=d+6: f=e+6: g=f+6: h=g+6: i=h+6: j=i+7: k=j+7: l=k+2: m=l+2: o=m+2
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n)= 8 Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=b+4
    EndIf
    If zBlowSeq(n)=a Then zBlowSeq(n)=0:zBlow(n)=0
    
    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=3:n2=14:n3=23 Else dir=2:dir2=4:n1=-3: n2=-14: n3=-23
    
    If zBlowSeq(n) > b And zBlowSeq(n) < c Then 
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
                zBlowSeq(n)=l
            EndIf
        EndIf
    EndIf
    
    If zBlowSeq(n) > c And zBlowSeq(n) < h Then zshield(n)=1
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-10:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-35:zAni(en)=2:zf(en)=3:zface(en)=dir2
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-45:zAni(en)=2:zf(en)=4;:zface(en)=dir
    If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-45:zAni(en)=2:zf(en)=4;:zface(en)=dir
        
    If zBlowSeq(n) = h  Then
        zx(en)=zx(n)+n3:zy(en)=zy(n)-18
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir2 : zFallDir(en)=dir2
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf
    
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=2
    If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=10:zf(n)=1
        
    If zBlowSeq(n) > b And zBlowSeq(n) < h Then zgrabbed(en)=1:checkZvsWall(en,0)
    
    If zBlowSeq(n) => j Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=13:zf(n)=1
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
        zani(n)=15:zf(n)=1
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=25:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=15:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=25:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0


Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1
    drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-15:yed(n)=40
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-16:yed(n)=41
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
        eAni(n)=1:ef(n)=3:xED(n)=54:yed(n)=10
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=5 :eAni(n)=1:ef(n)=4:xed(n)=53:yed(n)=9
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=15:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-16:yed(n)=41
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 1    ; High Punch    
    a=9: b=a+8: c=b+10: d=c+8: e=d+9
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) = b And gameSound Then PlaySound blow2snd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowseq(n) > b And zblowseq(n) =< c Then 
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=38:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=32:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=43:wblow(n,nn)=34:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=19:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
        zBlowSound(n)=predHitSnd
        zani(n)=6:zf(n)=3
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=9: b=a+15: c=b+8
    zNoJump(n)=0
    If zBlowSeq(n) = a And gameSound Then PlaySound blow2Snd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zblowseq(n) > a And zblowseq(n) =< b Then 
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=35:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=12:wblow(n,nn)=35:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=28
        zBlowSound(n)=predHitSnd
        zani(n)=8:zf(n)=2
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    a=8: b=a+14: c=b+10: d=c+5: e=d+5
    zheight(n)=zduckheight(n)
    If zBlowSeq(n) = a And gameSound Then PlaySound blow2Snd
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then 
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=0: yblow(n,nn)=10:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=5:wblow(n,nn)=32:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        movex2(n,zface(n),2)
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=30
        zBlowSound(n)=predHitSnd
        zani(n)=9:zf(n)=1
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=3:zf(n)=1
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=5: b=a+5: c=b+6: d=c+150 
    
    If zBlowSeq(n) =1 Then
        If gameSound Then PlaySound blow2snd
        zBlowUpLimit(n)=zy(n)-70:zJump(n)=0
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1:moveX(n,zBlowdir(n),1)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=43:wblow(n,nn)=17:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=53:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=63:wblow(n,nn)=22:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=72:wblow(n,nn)=11:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=1:zHitUpSpeed#(n)=2.5:zHitTime(n)=20
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=8:zBLowEffect(n)=1:zEnemyImmuneTime(n)=16:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=highPunchSnd
        zani(n)=7:zf(n)=1:zantiplat(n)=1
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=43:wblow(n,nn)=17:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=53:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=63:wblow(n,nn)=22:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=72:wblow(n,nn)=11:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=4:zHitTime(n)=45
        If zBlowStill(n)=0 Then zy(n)=zy(n)-4:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=6:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=slashSnd
        zani(n)=7:zf(n)=1:zantiplat(n)=1
    EndIf
    If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=d
    If zBlowSeq(n) => d Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => d-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=3
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=15:zf(n)=3
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 7    ;Predator disc
    a=8: b=a+8: c=b+12: d=c+8: e=d+8: f=e+8
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) = b Then 
        If shot(zMyShot(n)) =0 Or shotOwner(zMyshot(n)) <> n Or shotType(zMyShot(n)) <> 22 Then  
            For i=1 To 200
                If shot(i)=0 Then
                    zMyShot(n)= i
                    Goto gotBat2
                EndIf    
            Next
            .gotBat2
            dir=zface(n):y=zy(n)-zheight(n)+15
            If zface(n)=2 Then x=zx(n)+15
            If zface(n)=4 Then x=zx(n)-15
            makeshot(n,22,x,y,dir)
            If gameSound=1 Then PlaySound shurikenSnd
        EndIf
    EndIf
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=4
    
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0:

Case 9    ;green ray

    a=10: b=a+10: c=b+10: d=c+10: e=d+10: f=e+10
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If gamesound And zBlowSeq(n) = a Then PlaySound predatorRaySnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
    
    If zblowSeq(n)=c Then
        dir=zface(n):y=zy(n)-zheight(n)+12
        If zface(n)=2 Then x=zx(n)+15
        If zface(n)=4 Then x=zx(n)-15
        makeChunk(n,x,y,dir,27)
        makeshot(n,21,x,y,dir)
    EndIf
    
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=10:zf(n)=1
    
    If zblowseq(n) => f Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dogding
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=6:zf(n)=1:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 10    ;High Kick 
    zNoMove(n)=1
    zNoJump(n)=1
    a=5: b=a+4: c=b+10: d=c+8: e=d+8
    If zBlowSeq(n)= a And gameSound Then PlaySound blow2Snd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then
        zblowPamount(n)=4:nn=1
        xblow(n,nn)=0: yblow(n,nn)=43:wblow(n,nn)=17:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=53:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=63:wblow(n,nn)=22:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=10: yblow(n,nn)=72:wblow(n,nn)=11:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=0:zBlowHold(n)=0
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=20
        zBlowSound(n)=bHitSnd
        zani(n)=14:zf(n)=1
    EndIf
    
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function

;---------------------------------- Goku --------------------------------
Function DoGoku(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq9
    EndIf
zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq9
zCHunkType(n)=10

Select zCurBlow(n)
Case 0    ;Blocking

    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 15 ;Goku throw
    a=8: b=15: c=25: d=50: e=55: f=60: g=63: h=80: i=90
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=1
    If zBlowSeq(n)= a Then
        If gameSound Then PlaySound blow2snd
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
            If gameSound Then PlaySound blow2snd
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

    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=2:zx(en)=zx(n)+n2:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=1:zface(en)=dir2

    If zBlowSeq(n) => g-3 And zBlowSeq(n) < g Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=1:zface(en)=dir2
    If zBlowSeq(n) = g  Then
        zx(en)=zx(n)+0:zy(en)=zy(n)-13
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=6:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir : zFallDir(en)=dir
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf

    If zBlowSeq(n) => d And zBlowSeq(n) < h Then zshield(n)=1
    If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=3
    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=3

    If zBlowSeq(n) > c And zBlowSeq(n) < g Then zgrabbed(en)=1:checkZvsWall(en,0)
    If zBlowSeq(n) => i Then zBlowSeq(n)=0:zBlow(n)=0


Case 14    ;Super Special
    a=7:b=15:c=130
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2.5
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=1
    If zBlowSeq(n) =b-2 Then
        ;checkDist(n,zx(n),zy(n)-20,zFace(n))
        If gameSound=1 Then PlaySound  gokuSnd
        zSuperMove(n)=1:zSuperMoveSeq(n)=0:
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        If rendert = 2 Then checkDist(n,zx(n),zy(n)-20,zFace(n))
        For x=1 To (xDist(n)-27) Step 5
            If zFace(n)=2 Then
                makeChunk(n,Int(zx(n)) + x +25, zy(n)-13,2,38+Rand(0,1))
            Else
                makeChunk(n,Int((zx(n))- x)-25, zy(n)-13,2,38+Rand(0,1))
            EndIf
        Next
        If zBlowSeq(n) < c-10 Then hm=2 Else hm=0
        ;makeRectHit(n, x, y, w, h, dir, hitMode, xHit, yHit, damage, hitHold, chunk, HitSOund)

        If zface(n)=2 Then
         If zBlowSeq(n) < c Then makeRectHit(n,zx(n)+5       ,zy(n)-38,xDist(n),20,zFace(n),hm,2,.5,2.6,6,10,highpunchSnd)
         makeChunk(n,zx(n) + xDist(n), zy(n)-11,2,51+Rand(0,1))
         makeChunk(n,zx(n) + 24, zy(n)-11,2,51+Rand(0,1))
        Else
         If zBlowSeq(n) < c Then makeRectHit(n,zx(n)-(xDist(n)+5),zy(n)-38,xDist(n),20,zFace(n),hm,2,.5,2.6,6,10,highpunchSnd)
         makeChunk(n,zx(n) - xDist(n) , zy(n)-11,4,51+Rand(0,1))
         makeChunk(n,zx(n) - 24, zy(n)-11,4,51+Rand(0,1))
        EndIf
        zani(n)=10:zf(n)=2
    EndIf
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1: drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=5 :eAni(n)=1:ef(n)=2:xed(n)=-15:yed(n)=20
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=5 :eAni(n)=1:ef(n)=2:xed(n)=-16:yed(n)=21
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=1
        xblow(n,nn)=40: yblow(n,nn)=4:wblow(n,nn)=28:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=70:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0 :zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=6:zf(n)=6
        eAni(n)=1:ef(n)=3:xED(n)=52:yed(n)=7
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=6 :eAni(n)=1:ef(n)=4:xed(n)=51:yed(n)=6
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=5 :eAni(n)=1:ef(n)=2:xed(n)=-16:yed(n)=21
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
        zani(n)=10:zf(n)=3
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=3:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=3:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=1
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

Case 1    ;kick
    a=8: b=a+8: c=b+10: d=c+5: e=d+5
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2:zblowAlert(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blow2snd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=37:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=31:wblow(n,nn)=36:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=24:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=20:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=18
        zBlowSound(n)=dbzHitSnd
        If zblowSeq(n) = c Then zBlowEffect(n)=0
        zani(n)=6:zf(n)=3
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 2    ;Flying Kick
    a=8: b=a+20: c=b+5
    zNoJump(n)=0:ZJUMPING(N)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:zblowAlert(n)=1
    If zBlowSeq(n) =a Then If gameSound Then PlaySound blow2snd
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then
        zblowpamount(n)=4:nn=1
           xblow(n,nn)=-5: yblow(n,nn)=22:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
           xblow(n,nn)=-5: yblow(n,nn)=15:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
           xblow(n,nn)=-5: yblow(n,nn)=8:wblow(n,nn)=26:hblow(n,nn)=1:nn=nn+1
           xblow(n,nn)=16: yblow(n,nn)=2:wblow(n,nn)=8:hblow(n,nn)=1:nn=nn+1
           
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=25
        zBlowSound(n)=dbzHitsnd
        zani(n)=8:zf(n)=2
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=1
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low kick
    zNoMove(n)=1:zNoJump(n)=1
    zheight(n)=zduckheight(n)
    a=5:b=10:c=25:d=32
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=9:zf(n)=1:zblowAlert(n)=1:zblowAlert(n)=1
    If zBlowSeq(n)= a Then If gameSound Then PlaySound blow2snd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=3
        xblow(n,1)=0: yblow(n,1)=17:wblow(n,1)=33:hblow(n,1)=1
        xblow(n,2)=0: yblow(n,2)=12:wblow(n,2)=33:hblow(n,2)=1
        xblow(n,3)=0: yblow(n,3)=7:wblow(n,3)=25:hblow(n,3)=1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=11:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=dbzHitSnd
        zani(n)=9:zf(n)=2
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=9:zf(n)=1
    If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Uppercut
    zNoMove(n)=1
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
    a=3: b=a+7: c=b+150: d=c+3: e=d+3: f=e+3

    If zBlowSeq(n) =1 Then
        If gameSound Then PlaySound goku1Snd
        zBlowUpLimit(n)=zy(n)-73:zJump(n)=0
        zani(n)=7:zf(n)=1
    EndIf
    
    If zBlowSeq(n) > 1 And zBlowSeq(n) =< b Then
        zblowpamount(n)=3:nn=1
        xblow(n,nn)=0: yblow(n,nn)=28:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=20:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=14:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=40
        If zBlowStill(n)=0 Then moveX(n,zBlowdir(n),1)
        zBlowDamage(n)=4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=9:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=dbzHitSnd
        zani(n)=7:zf(n)=2
    EndIf
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=7:zf(n)=1:moveX(n,zBlowdir(n),.5)
    If zBlowSeq(n) > b And zBlowSeq(n) <= c Then
        zblowpamount(n)=5:nn=1
        xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=22:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=38:wblow(n,nn)=23:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=30:wblow(n,nn)=23:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=22:wblow(n,nn)=17:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=15:wblow(n,nn)=11:hblow(n,nn)=1:nn=nn+1
        zHitmode(n)=2:zBlowHold(n)=0
        zHitSpeed#(n)=4:zHitUpSpeed#(n)=2:zHitTime(n)=45
        If zBlowStill(n)=0 Then zy(n)=zy(n)-3:moveX(n,zBlowdir(n),2)
        zBlowDamage(n)=5:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=dbzHitSnd
        zani(n)=7:zf(n)=3:zantiplat(n)=1
        If zBlowSeq(n) = c Then zBlowEffect(n)=0
    EndIf
    If zy(n) <= zBlowUpLimit(n) Or zHitHead(n)=1 Then zBlowSeq(n)=c+1:zBlowEffect(n)=0

    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=7:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=7:zf(n)=5
    If zBlowSeq(n) > e Then zani(n)=7:zf(n)=6

    If zBlowSeq(n) > c Then zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
    If zongnd(n)=1 And zBlowSeq(n) => c-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=5
    If zBlowSeq(n)= b Then
        throwObj(n,zx(n),zy(n)-20,zFace(n))
        If gameSound Then PlaySound throwsnd
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
    
Case 7    ;Goku Ball
    a=7:b=15:c=55
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2
    If zBlowSeq(n) = 1 And gameSound=1 Then PlaySound goku1Snd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=3
    If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=3
    If zBlowSeq(n)= b Then
        dir=zface(n):y=zy(n)-25
        If zface(n)=2 Then x=zx(n)+10
        If zface(n)=4 Then x=zx(n)-10
        makeshot(n,28,x,y,dir)
    EndIf
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=4
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dodge
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=1:zf(n)=0
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=1:zf(n)=0:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;Goku teleport
    a=1: b=a+8: c=b+3: d=c+3: e=d+4: f=e+4 : g=f+3
    zNoMove(n)=1
    zNoJump(n)=1
    
    If zBlowSeq(n) = a And gameSound Then PlaySound teleportSnd
    
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=1
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=2
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=3
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=3
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=12:zf(n)=2
    If zBlowSeq(n) > f And zBlowSeq(n) =< g Then zani(n)=12:zf(n)=1

    If zBlowSeq(n) > a And zBlowSeq(n) =< d Then
        If rightKey(n)=1 Then changed=1: xdi = 85 : dif=-42
        If leftKey(n) =1 Then changed=1: xdi = -85 : dif=42
        ;If upKey(n)=1 then changed=1: ydi = -60
        ;If downKey(n)=1 then changed=1: ydi = 60
    EndIf

    If zBlowSeq(n) = d Then ;check collision for teleporting
        If changed=0 Then
            If zBlowDir(n)=2 Then
                xdi=85 : ydi=0 : dif=-42
            Else
                xdi=-85: ydi=0 : dif=42
            EndIf
        EndIf
        zx(n) = zx(n) + xdi
        zy(n) = zy(n) + ydi
        zonplat(n)=0
    EndIf
    

    If zblowseq(n) => a And zblowseq(n) <= f Then zshield(n)=1
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10    ;High punch
    zNoMove(n)=1:zNoJump(n)=1
    a=8: b=a+5: c=b+5: d=c+5: e=d+6
    If zBlowSeq(n)= a And gameSound Then PlaySound blow2snd

    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=3
    If zBlowSeq(n) > b And zBlowSeq(n) < c Then
        zblowPamount(n)=3:nn=1
        xblow(n,nn)=2: yblow(n,nn)=54:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=2: yblow(n,nn)=44:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=2: yblow(n,nn)=34:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=9:zBLowEffect(n)=1:zEnemyImmuneTime(n)=20:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
        zBlowSound(n)=dbzHitSnd
    EndIf
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=14:zf(n)=2
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=14:zf(n)=1
    If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 16 ;Counter key
    zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function

;---------------------------------- Ritcher Belmont --------------------------------
Function DoRitcher(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
    If zBlowStill(n)=1 Then
        zBlowStillSeq(n)=zBlowStillSeq(n)+1
        If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
        Goto noBlowSeq10
    EndIf
zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq10
zCHunkType(n)=50

Select zCurBlow(n)
Case 0    ;Blocking

    zNoMove(n)=1:zNoJump(n)=1
    zBlock(n)=1:zani(n)=13:zf(n)=1
    If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0

Case 15 ;Ritcher throw
           a=12: b=a+10: c=b+6: d=c+6: e=d+6: f=e+6: g=f+6: h=g+6: i=h+6: j=i+7: k=j+7: l=k+2: m=l+2: o=m+2
    zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=6
    If zBlowSeq(n)= 8 Then
        If gameSound Then PlaySound blowsnd
        grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
        If zGrabs(n)=1 Then zBlowSeq(n)=b+4
    EndIf
    If zBlowSeq(n)=a Then zBlowSeq(n)=0:zBlow(n)=0

    en=zGrabsThis(n)
    If zface(n)=2 Then dir=4:dir2=2:n1=3:n2=14:n3=23 Else dir=2:dir2=4:n1=-3: n2=-14: n3=-23

    If zBlowSeq(n) > b And zBlowSeq(n) < c Then
        If shotKey(n)=1 Or grabKey(n)=1 Then
            zBlowSeq(n)=d+2
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            zFallTime(en)=40:zHitSeq(en)=0:zhitTime(en)=40
        Else
            zBlowSeq(n)=zBlowSeq(n)-1:zx(en)=zx(n)+n2:zy(en)=zy(n)
            zAni(en)=2:zf(en)=1
            zGrabbed(en)=1:zHit(en)=1:zFace(en)=dir
            zAni(n)=6:zf(n)=5
            If shotKey(en)=1 Or specialKey(en)=1 Then zLetGoSeq(en)=zLetGoSeq(en)+1
            If Blockkey(n)=1 Or zLetGoSeq(en) > zLetGoAmount(en) Then
                zhit(en)=0:zgrabbedby(en)=0:zgrabbed(en)=0
                zHitTime(en)=0:zFallTime(en)=0zHitSeq(en)=0
                zgrabsThis(n)=0
                zgrabs(n)=0
                zBlowSeq(n)=l
            EndIf
        EndIf
    EndIf

    If zBlowSeq(n) > c And zBlowSeq(n) < h Then zshield(n)=1
    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=6:zx(en)=zx(n)+n2:zy(en)=zy(n)-8:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=6:zf(n)=6:zx(en)=zx(n)+n2:zy(en)=zy(n)-10:zAni(en)=2:zf(en)=1
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=6:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-35:zAni(en)=2:zf(en)=3:zface(en)=dir2
    If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=6:zf(n)=3:zx(en)=zx(n)+n1:zy(en)=zy(n)-45:zAni(en)=2:zf(en)=4;:zface(en)=dir
    If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=6:zf(n)=6:zx(en)=zx(n)+n2:zy(en)=zy(n)-45:zAni(en)=2:zf(en)=4;:zface(en)=dir

    If zBlowSeq(n) = h  Then
        zx(en)=zx(n)+n3:zy(en)=zy(n)-18
        zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
        zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
        zDamage(en)=zDamage(en)+10
        zLife(en)=zLife(en)-10
        zFace(en)=dir2 : zFallDir(en)=dir2
        zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
    EndIf

    If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) => i And zBlowSeq(n) < j Then zani(n)=6:zf(n)=7

    If zBlowSeq(n) > b And zBlowSeq(n) < h Then zgrabbed(en)=1:checkZvsWall(en,0)

    If zBlowSeq(n) => j Then zBlowSeq(n)=0:zBlow(n)=0


Case 14    ;Super Special (swords)
    a=3: b=a+2: c=b+2: d=c+2: e=d+2: f=e+2 : g=f+2
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2

        If zblowseq(n)=1 Then zSuperMove(n)=1:zSuperMoveSeq(n)=0:
    If zblowseq(n)=2 And gameSound Then PlaySound richterSnd

    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) => a And zBlowSeq(n) <= b Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > b And zBlowSeq(n) <= c Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > c And zBlowSeq(n) <= d Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) > d And zBlowSeq(n) <= e Then zani(n)=9:zf(n)=4
    If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=9:zf(n)=5
    If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=9:zf(n)=6

    If zBlowSeq(n)= b Or zblowseq(n)=e Then
        dir=zface(n)
        If zface(n)=2 Then x=zx(n)+10
        If zface(n)=4 Then x=zx(n)-10
        If zblowseq(n)=b Then y=zy(n)-Rand(22,32)
        If zblowseq(n)=e Then y=zy(n)-Rand(10,20)
         If zblowseq2(n) < 7 Then
            makeshot(n,30,x,y,dir)
         Else
            sh = makeshot(n,30,x,y,dir)
                shotHitMode(sh)=0
         EndIf
    EndIf
    If zBlowSeq(n) > g Then
        zblowseq2(n)=zblowseq2(n)+1
        If zblowseq2(n) > 7 Then
            zBlowSeq(n)=0:zBlow(n)=0
        Else
                zblowseq(n)=a
        EndIf
    EndIf

Case 11    ;club
    a=12:b=22:c=29:d=50:e=55
    zNoMove(n)=1
    zNoJump(n)=1
    extraDraw(n)=1: drawObjOnZ(n)=0
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-28:yed(n)=20
    If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-29:yed(n)=21
    If zBlowSeq(n)= a Then If gameSound Then PlaySound voosnd
    If zBlowSeq(n) => b And zBlowSeq(n) =< c Then
        zblowPamount(n)=6
        nn=1
        xblow(n,nn)=40: yblow(n,nn)=4:wblow(n,nn)=28:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=26:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=16:wblow(n,nn)=83:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=36:wblow(n,nn)=80:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=0: yblow(n,nn)=46:wblow(n,nn)=70:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=-5: yblow(n,nn)=56:wblow(n,nn)=67:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0 :zBlowHold(n)=10
        zBlowDamage(n)=25:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
        zChunkType(n)=5
        zBlowSound(n)=smashsnd
        zani(n)=10:zf(n)=1
        eAni(n)=1:ef(n)=3:xED(n)=52:yed(n)=7
    EndIf
    If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=4:xed(n)=51:yed(n)=6
    If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=6:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-29:yed(n)=21
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
        zani(n)=6:zf(n)=1
        eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=23
        zDuck(n)=0 : moveX(n,dir,zPushedForce(n))
    EndIf
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=2:xED(n)=5:yed(n)=23
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=1:eAni(n)=zCurWeapon(n):ef(n)=1:xED(n)=5:yed(n)=22
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0

Case 13 ; item pickup
    b=2:c=8
    If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=10:zf(n)=1
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

Case 1    ;whip (normal attack ->)
    a=7: b=a+5: c=b+3: d=c+3: e=d+3: f=e+3: g=f+3
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=6:zf(n)=1 :extraObj(n,zx(n),-15,zy(n),3,zblowdir(n),53)
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=2:extraObj(n,zx(n),-30,zy(n),-1,zblowdir(n),54)
    If zBlowSeq(n)= a Then If gameSound Then PlaySound whipSnd
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=3 :extraObj(n,zx(n),-22,zy(n),-23,zblowdir(n),55)
        If zBlowSeq(n) => f And zBlowSeq(n) < g-1 Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=6: yblow(n,nn)=35:wblow(n,nn)=72:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=8: yblow(n,nn)=32:wblow(n,nn)=70:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=17:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=15
        zBlowSound(n)=mikepunchsnd
    EndIf

    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=4:extraObj(n,zx(n),-9,zy(n),-35,zblowdir(n),56)
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=6:zf(n)=5:extraObj(n,zx(n),30,zy(n),-33,zblowdir(n),57)
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=6:zf(n)=5:extraObj(n,zx(n),41,zy(n),-31,zblowdir(n),58)
    If zBlowSeq(n) => f And zBlowSeq(n) =< g Then zani(n)=6:zf(n)=6:extraObj(n,zx(n),50,zy(n),-30,zblowdir(n),59)
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0


Case 2    ;Flying whip
    a=5: b=a+5: c=b+8
    zNoJump(n)=0:ZJUMPING(N)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1:extraObj(n,zx(n),0,zy(n),-28,zblowdir(n),55)
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2:extraObj(n,zx(n),29,zy(n),-40,zblowdir(n),57)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=3:extraObj(n,zx(n),47,zy(n),-17,zblowdir(n),59)
    If zBlowSeq(n) = b And gameSound=1 Then PlaySound whipSnd
    If zBlowSeq(n) > b And zBlowSeq(n) < c Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=8: yblow(n,nn)=22:wblow(n,nn)=68:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=19:wblow(n,nn)=64:hblow(n,nn)=1:nn=nn+1

        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=13:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=15:zBlowBlockTime(n)=15
        zBlowSound(n)=mikepunchSnd
    EndIf
    If zBlowSeq(n) > c Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
    If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0

Case 4    ;Low whip
    a=7: b=a+5: c=b+3: d=c+3: e=d+3: f=e+3: g=f+3
    zNoMove(n)=1
    zNoJump(n)=1 : zheight(n)=zduckheight(n)
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=9:zf(n)=1 :extraObj(n,zx(n),-16,zy(n),21,zblowdir(n),53)
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=9:zf(n)=2:extraObj(n,zx(n),-29,zy(n),16,zblowdir(n),54)
    If zBlowSeq(n)= a Then If gameSound Then PlaySound whipSnd
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=9:zf(n)=3 :extraObj(n,zx(n),-20,zy(n),-9,zblowdir(n),55)
        If zBlowSeq(n) => f And zBlowSeq(n) < g-1 Then
        zblowPamount(n)=2:nn=1
        xblow(n,nn)=5: yblow(n,nn)=17:wblow(n,nn)=72:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=6: yblow(n,nn)=13:wblow(n,nn)=70:hblow(n,nn)=1:nn=nn+1
        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=12:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=15
        zBlowSound(n)=mikepunchsnd
    EndIf

    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=9:zf(n)=4:extraObj(n,zx(n),-11,zy(n),-23,zblowdir(n),56)
    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=9:zf(n)=5:extraObj(n,zx(n),31,zy(n),-15,zblowdir(n),57)
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=9:zf(n)=5:extraObj(n,zx(n),40,zy(n),-12,zblowdir(n),58)
    If zBlowSeq(n) => f And zBlowSeq(n) =< g Then zani(n)=9:zf(n)=6:extraObj(n,zx(n),49,zy(n),-12,zblowdir(n),59)
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5    ;Richter Uppercut
            zNoMove(n)=0
    ztopspeed(n)=.5
    zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0 : spinN=6
    a=4: b=a+3: c=b+3: d=c+3: e=d+3
    If zblowseq(n)=1 Then
        zBlowUpLimit(n)=zy(n)-74
        makeChunk(n,zx(n)+rand(-3,3),zy(n)-rand(0,3),zblowDir(n),13)
    EndIf
    j=3
    If zy(n) < (zblowuplimit(n)+20) Then j = 2
    If zBlowSeq2(n) > spinN Then
        zjump(n)=0:zjumping(n)=1:zNoGrav(n)=0
        If zongnd(n)=0 Then zani(n)=4:zf(n)=1:ztopSpeed(n)=.5:zNomove(n)=0
        If zongnd(n)=1 Then zani(n)=4:zf(n)=1
    Else
        zantiplat(n)=1
        If zBlowSeq(n) =2 And zblowstill(n)=0 And gameSound Then PlaySound shurikenSnd
        If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),1.3):zy(n)=zy(n)-j

        If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1
        If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2
        If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3
        If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4

        If zBlowSeq(n) => a And zBlowSeq(n) =< e And zblowStill(n)=0 Then moveX(n,zBlowdir(n),1.3):zy(n)=zy(n)-j

        If (zblowseq(n) =>a And zblowseq(n) < e) Then
                zblowPamount(n)=5:nn=1
            xblow(n,nn)=-4: yblow(n,nn)=28:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=-4: yblow(n,nn)=23:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=-3: yblow(n,nn)=18:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=-3: yblow(n,nn)=10:wblow(n,nn)=18:hblow(n,nn)=1:nn=nn+1
            xblow(n,nn)=-3: yblow(n,nn)=4:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1

            zHitmode(n)=2:zBlowHold(n)=3
            zHitSpeed#(n)=2:zHitUpSpeed#(n)=2.5:zHitTime(n)=45
                zBlowDamage(n)=1.4:zBLowEffect(n)=1:zEnemyImmuneTime(n)=5:zBlowStillTime(n)=3:zBlowBlockTime(n)=20
            If zy(n) < (zblowuplimit(n)+5) Then
                zHitSpeed#(n)=4:zHitTime(n)=65:zblowHold(n)=10:zBlowStillTime(n)=10
                zEnemyImmuneTime(n)=10
            EndIf
            zBlowSound(n)=mikepunchSnd
        EndIf
        If zblowseq(n) => e Then zblowseq(n) = a : zblowseq2(n)=zblowseq2(n)+1
    EndIf

    If zy(n) < zBlowupLimit(n) Or zHitHead(n)=1 Then zblowseq2(n) = 99:zblowuplimit(n)=-9999
    If zongnd(n)=1 And zBlowSeq2(n) > spinN Then zBlowSeq(n)=0:zBlowSeq2(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 6    ;throwing iten
    a=2:b=3:c=6:d=8
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

Case 7    ;Ritcher cross
    a=3: b=a+3: c=b+3: d=c+3: e=d+3: f=e+5 : g=f+5
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2

    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) <= b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) <= c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) <= d Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) <= e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=6:zf(n)=7

    If zBlowSeq(n)= e Then
        If shot(zMyShot(n)) = 0 Or shotOwner(zMyshot(n)) <> n Or shotType(zMyShot(n)) <> 29 Then
            zMyShot(n)=getShot()
            dir=zface(n):y=zy(n)-25
            If zface(n)=2 Then x=zx(n)+10
            If zface(n)=4 Then x=zx(n)-10
            makeshot(n,29,x,y,dir)
            If gameSound=1 Then PlaySound crossSnd
        EndIf
    EndIf
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0

Case 8    ;Dodge
    zheight(n)=zduckHeight(n)
    zNoMove(n)=1
    zNoJump(n)=1
    a=7:b=15:c=20:d=25:e=30:f=37
    If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
    If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=1:zf(n)=0
    If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=1:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
    If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),2)
    If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=1:zf(n)=0:moveX(n,zBlowdir(n),1)

    If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
    If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9    ;Ritcher axe (down special)
    a=5: b=a+3: c=b+3: d=c+3: e=d+3: f=e+8 : g=f+8: h=g+8
    zNoMove(n)=1
    zNoJump(n)=1
    zjump(n)=0
    If zongnd(n)=0 Then zy(n)=zy(n)-2

    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=6:zf(n)=1
    If zBlowSeq(n) > a And zBlowSeq(n) <= b Then zani(n)=6:zf(n)=2
    If zBlowSeq(n) > b And zBlowSeq(n) <= c Then zani(n)=6:zf(n)=3
    If zBlowSeq(n) > c And zBlowSeq(n) <= d Then zani(n)=6:zf(n)=4
    If zBlowSeq(n) > d And zBlowSeq(n) <= e Then zani(n)=6:zf(n)=5
    If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=6:zf(n)=6
    If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=6:zf(n)=7
    If zBlowSeq(n) > g And zBlowSeq(n) <= h Then zani(n)=6:zf(n)=7

    If zBlowSeq(n)= e Then
        If gamesound Then PlaySound fastThrowSnd
        originalObj=zGotObj(n)
        o = getObj(n,20)    ;20 = axe
            If zface(n)=2 Then x=zx(n)+10 Else x=zx(n)-10
        y=35
        If downKey(n)=1 Then
            objYspeed(o)=0:objXspeed(o)=4
            y=30
        Else
            objYspeed(o)=-3:objXspeed(o)=2
        EndIf
        downkey(n)=0 : upkey(n)=0
        throwObj(n,x,zy(n)-y,zface(n))
        zGotObj(n)=originalObj
    EndIf
    If zBlowSeq(n) > h Then zBlowSeq(n)=0:zBlow(n)=0
    
    
    
;    Case 9    ;batman bomb throw (down special)
;    a=8: b=a+5: c=b+5: d=c+8: e=d+8: f=e+8
;    zNoMove(n)=1: zNoJump(n)=1
;    zjump(n)=0
;    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=10:zf(n)=1
;    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
;    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3

;    If zblowSeq(n)=b Then
;        If gamesound Then PlaySound blow2Snd
;        originalObj=zGotObj(n)
;        da = getObj(n,9)    ;9 = type of obj
;        objImpact(da)=10
;        upkey(n)=0 : downKey(n)=0
;        If zface(n)=2 Then x=zx(n)+10 Else x=zx(n)-10
;        throwObj(n,x,zy(n)-17,zface(n))
;        zGotObj(n)=originalObj
;    EndIf

;    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=4
;    If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=10:zf(n)=5
;    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=10:zf(n)=6

;    If zblowseq(n) => f Then zBlowSeq(n)=0:zBlow(n)=0
    
    
Case 10    ;High whip
    a=5: b=a+3: c=b+3: d=c+3: e=d+3: f=e+3: g=f+3
    zNoMove(n)=1
    zNoJump(n)=1
    If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=6:zf(n)=1 :extraObj(n,zx(n),-15,zy(n),3,zblowdir(n),53)
    If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=6:zf(n)=2:extraObj(n,zx(n),-30,zy(n),-1,zblowdir(n),54)
    If zBlowSeq(n)= a Then If gameSound Then PlaySound whipSnd
    If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=6:zf(n)=3 :extraObj(n,zx(n),-22,zy(n),-23,zblowdir(n),55)
        If zBlowSeq(n) => e And zBlowSeq(n) < (f-1) Then
        zblowPamount(n)=5:nn=1
        xblow(n,nn)=7: yblow(n,nn)=52:wblow(n,nn)=8:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=11: yblow(n,nn)=46:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=39:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=31:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
        xblow(n,nn)=12: yblow(n,nn)=23:wblow(n,nn)=6:hblow(n,nn)=1:nn=nn+1

        zHitMode(n)=0:zBlowHold(n)=8
        zBlowDamage(n)=15:zBLowEffect(n)=1:zEnemyImmuneTime(n)=99:zBlowStillTime(n)=6:zBlowBlockTime(n)=15
        zBlowSound(n)=mikepunchsnd
    EndIf

    If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=6:zf(n)=4:extraObj(n,zx(n),-9,zy(n),-35,zblowdir(n),56)
    ;If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=6:zf(n)=5:extraObj(n,zx(n),30,zy(n),-33,zblowdir(n),57)
        If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=6:zf(n)=7:extraObj(n,zx(n),18,zy(n),-21,zblowdir(n),60)
    If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=6:zf(n)=7:extraObj(n,zx(n),18,zy(n),-21,zblowdir(n),60)
    If zBlowSeq(n) => f And zBlowSeq(n) =< g Then zani(n)=10:zf(n)=2
    If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0
    
Case 16 ;Taunt key
    zBlowSeq(n)=0:zBlow(n)=0
    
Case 17 ;Extra special key
    zBlowSeq(n)=0:zBlow(n)=0
    
End Select

End Function


;add character, copy a character function from above, paste it below, rename the function and start changing the moves!
;this new function will be called from the mh.bb source, where you were told to add a CASE call.



;--------------- draws extra stuff ------------------------
Function extraObj(player,xAxis,xx,yAxis,yy,dir,kind)

If dir = 2 Then
    makechunk(player,xAxis+xx,yAxis+yy,dir,kind)
Else
    makechunk(player,xAxis-xx,yAxis+yy,dir,kind)
EndIf

End Function