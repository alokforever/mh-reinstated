;-------------- Initialize player ---------------------
Function initZ(n)

If flagOwner(1)=n Then flagCarried(1)=0
If flagOwner(2)=n Then flagCarried(2)=0
zjump(n)=0:zjumpseq(n)=0
zHit(n)=0:zCurWeapon(n)=0:zjump2(n)=0:zhitbybox(n)=0
zBouncedgnd(n)=0:zBlowSeq(n)=0:zBlowseq2(n)=0:zhit(n)=0
zBlow(n)=0:zBlowStill(n)=0:zBlockLife(n)=zBlockFull(n):zDamage(n)=0
zBlocked(n)=0: aiGetTarget(n):

    zJumpSnd(n)=noSnd
    dangerMove5(n)=1
    zCurPic(n)=zPic_(CurGuy(n),1,0)
    aiTarget(n)=0
    zFace(n)=2
    zlife(n)=100
    zani(n)=1 : zf(n)=0
    zheight(n)=72                ;Player's current height
    zUpHeight(n)=72
    zDuckHeight(n)=40
    zside(n)=12.8                     ;Z width size / 2
    zSpeed#(n)=0                ;Player current speed
    zShieldedTime(n)=150        ;Time(frames) player stays invincible when recover
    zBlockFull(n)=80
    zBlockLife(n)=zBlockFull(n)
    zCurWeapon(n)=0
    zAcc#(n)=.32         ;.2
    zgravity(n)=4.8       ;3        ;Gravity force when falling Or going up
    zjumplimit(n)=20    ;20        ;Jump height (per frame), not pixels!
    zDtopSpeed#(n)=3.2    ;2
    zTopSpeed#(n)=zDtopSpeed(n)
    zBlockSpeed(n)=.8
    zJumping(n)=0
    zDontJump(n)=0
    zDontPickItem(n)=0
    zNoAirSpecial(n)=0
    zDeathChunk(n)=25
    zStone(n)=0
    zUngrabable(n)=0
    yRange(n)=0
    zUseSpecialAI(n)=0
    zCanFly(n)=0
    zGrabDist(n)=25
    zLetGoAmount(n)=7            ;reach this number with 'zLetGoSeq(x)' to let go of grab
    zHelperObj(n)=0
    zTrail(n)=0
    zTrailType(n)=0
    zHitByRect(nn)=0
    zRollOnImpact(n)=0
    zWalkFrames(n)=0
    zWalkFrameSpeed#(n)=0
    zRunFrames(n)=0
    zRunFrameSpeed#(n)=0
    zRunSpeed#(n)=3.2
    gender(n)=noGenderVal
    isMkCharacter(n)=0
    canWallJump(n)=0
    flipFrames(n)=0
    dizzyFrames(n)=0
    dizzyFrameSpeed(n)=0
    duckFrames(n)=0
    duckFrameSpeed(n)=0
    canAirGlide(n)=0
    zRunFootSoundSeq(n)=0
    zWalkQuakeSeq1(n)=0
    zWalkQuakeSeq2(n)=0
    isHeavy(n)=0
    hasSpecialAirFrames(n)=0
    specialHitFrames(n)=0
    hitFrameSpeed(n)=4
    superMoveMaxSeq(n)=50
    superPicNum(n)=1
    electrocuteFrames(n)=0
    electrocuteFrameSpd(n)=0
    maxHitSeq(n)=65
    zBouncedGndFrames(n)=0
    superMovePortraitSeqStart(n)=0
    zTempStone(n)=0
    zBlockedSnd(n)=blockedsnd
    isBoss(n)=0
    preSuperEffectX(n)=0
    preSuperEffectY(n)=30
    doesCharBleed(n)=1
    zFlipMaxSeq(n)=20

Select curGuy(n)    ;Add character, add your new guy initial stuff, attack range, jump sound etc
Case 1: ;Evil Ryu
    zBlowDist(n,1)=44
    zBlowDist(n,2)=48
    zBlowDist(n,4)=44
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=70    :dangerMove9(n)=1
    zBlowDist(n,10)=44
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal
    zWalkFrames(n)=6
    zWalkFrameSpeed#(n)=4
    zJumpSnd(n)=jumpsnd
    zJumpSnd2(n)=wolverinejumpsnd
    hasSpecialAirFrames(n)=1
    specialHitFrames(n)=11
    zRunFrames(n)=6
    zGrabDist(n)=15

Case 2: ;Rash
    zBlowDist(n,1)=65
    zBlowDist(n,2)=75
    zBlowDist(n,4)=60
    zBlowDist(n,5)=55
    zBlowDist(n,7)=68
    zBlowDist(n,9)=80    :dangerMove9(n)=1
    zBlowDist(n,10)=45
    zBlowDist(n,11)=150
    zBlowDist(n,14)=280
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal

Case 3: ;Spider-man
    zBlowDist(n,1)=55
    zBlowDist(n,2)=52
    zBlowDist(n,4)=70
    zBlowDist(n,5)=75
    zBlowDist(n,7)=300
    zBlowDist(n,9)=110    :dangerMove9(n)=1
    zBlowDist(n,10)=84
    zBlowDist(n,11)=150
    zBlowDist(n,14)=400
    zGrabDist(n)=25
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal
    zWalkFrames(n)=7
    zWalkFrameSpeed#(n)=4

Case 4: ;Mario
    zBlowDist(n,1)=92
    zBlowDist(n,2)=43
    zBlowDist(n,4)=53
    zBlowDist(n,5)=42
    zBlowDist(n,7)=300
    zBlowDist(n,9)=39    :dangerMove9(n)=0
    zBlowDist(n,10)=34
    zBlowDist(n,11)=150
    zBlowDist(n,14)=500
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal

Case 5: ;Michaelangelo
    zBlowDist(n,1)=64
    zBlowDist(n,2)=52
    zBlowDist(n,4)=53
    zBlowDist(n,5)=75
    zBlowDist(n,7)=300
    zBlowDist(n,9)=110    :dangerMove9(n)=1
    zBlowDist(n,10)=55
    zBlowDist(n,11)=150
    zBlowDist(n,14)=70
    zGrabDist(n)=zGrabDist(n)+5
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal

Case 6: ;Strider Hiryu
    zBlowDist(n,1)=60
    zBlowDist(n,2)=52
    zBlowDist(n,4)=53
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=90    :dangerMove9(n)=1
    zBlowDist(n,10)=50
    zBlowDist(n,11)=150
    zBlowDist(n,14)=120
    zRollOnImpact(n)=1
    zJumpSnd(n)=mvcJump1Snd
    zJumpSnd2(n)=mvcJump2Snd
    gender(n)=maleVal
    zheight(n)=78
    zRunSpeed#(n)=4
    duckFrames(n)=10
    duckFrameSpeed(n)=5
    flipFrames(n)=7
    zFlipMaxSeq(n)=21
    zWalkFrames(n)=9
    zWalkFrameSpeed#(n)=3
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=3
    zRunFootSoundSeq(n)=6
    hasSpecialAirFrames(n)=1
    dizzyFrames(n)=6
    dizzyFrameSpeed(n)=10
    specialHitFrames(n)=13
    hitFrameSpeed(n)=5
    zBouncedGndFrames(n)=3
    maxHitSeq(n)=65

Case 7: ;Batman
    zBlowDist(n,1)=48
    zBlowDist(n,2)=58
    zBlowDist(n,4)=55
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=100    :dangerMove9(n)=0
    zBlowDist(n,10)=45
    zBlowDist(n,11)=150
    zBlowDist(n,14)=300
    zGrabDist(n)=zGrabDist(n)+5
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal

Case 8: ;Predator
    zBlowDist(n,1)=48
    zBlowDist(n,2)=58
    zBlowDist(n,4)=55
    zBlowDist(n,5)=48
    zBlowDist(n,7)=500
    zBlowDist(n,9)=150    :dangerMove9(n)=0
    zBlowDist(n,10)=45
    zBlowDist(n,11)=150
    zBlowDist(n,14)=500
    zGrabDist(n)=zGrabDist(n)+7
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal
    
Case 9: ;Goku
    zBlowDist(n,1)=54
    zBlowDist(n,2)=48
    zBlowDist(n,4)=44
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=70    :dangerMove9(n)=1
    zBlowDist(n,10)=39
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal

Case 10: ;Deadpool
    zBlowDist(n,1)=110
    zBlowDist(n,2)=110
    zBlowDist(n,4)=110
    zBlowDist(n,5)=48
    zBlowDist(n,7)=200
    zBlowDist(n,9)=400    :dangerMove9(n)=0
    zBlowDist(n,10)=44
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zRollOnImpact(n)=1
    zJumpSnd(n)=shotwallsnd
    gender(n)=maleVal
    duckFrames(n)=2
    duckFrameSpeed(n)=5
    zWalkFrames(n)=14
    zWalkFrameSpeed#(n)=3
    hasSpecialAirFrames(n)=1
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=3
    zRunSpeed#(n)=3

Case 11: ;Wolverine
    zBlowDist(n,1)=60
    zBlowDist(n,2)=52
    zBlowDist(n,4)=53
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=90    :dangerMove9(n)=1
    zBlowDist(n,10)=50
    zBlowDist(n,11)=150
    zBlowDist(n,14)=120
    zBlowDist(n,16)=550
    zSide(n)=12
    zRollOnImpact(n)=1
    zJumpSnd(n)=jumpsnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=16
    zWalkFrameSpeed#(n)=3
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=3
    zRunSpeed#(n)=4
    gender(n)=maleVal
    canWallJump(n)=1
    dizzyFrames(n)=7
    dizzyFrameSpeed(n)=7
    zRunFootSoundSeq(n)=12
    duckFrames(n)=7
    duckFrameSpeed(n)=5
        
Case 12: ;Scorpion
    zBlowDist(n,1)=60
    zBlowDist(n,2)=52
    zBlowDist(n,4)=53
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=90    :dangerMove9(n)=1
    zBlowDist(n,10)=50
    zBlowDist(n,11)=150
    zBlowDist(n,14)=120
    zBlowDist(n,16)=370
    zheight(n)=85
    zUpHeight(n)=85
    zRollOnImpact(n)=1
    zJumpSnd(n)=mkJumpSnd
    zJumpSnd2(n)=mkJump2Snd
    zWalkFrames(n)=9
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=11
    zRunFrameSpeed#(n)=3
    zRunSpeed#(n)=3.2
    isMkCharacter(n)=1
    gender(n)=maleVal
    flipFrames(n)=6
    zFlipMaxSeq(n)=21
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    zRunFootSoundSeq(n)=12
    specialHitFrames(n)=8

Case 13: ;Sub Zero
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zheight(n)=85
    zUpHeight(n)=85
    zRollOnImpact(n)=1
    zJumpSnd(n)=mkJumpSnd
    zJumpSnd2(n)=mkJump2Snd
    zWalkFrames(n)=9
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=11
    zRunFrameSpeed#(n)=3
    zRunSpeed#(n)=3.2
    isMkCharacter(n)=1
    gender(n)=maleVal
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    zRunFootSoundSeq(n)=12
    specialHitFrames(n)=8

Case 14: ;Wonder Woman
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zSide(n)=11
    zheight(n)=80
    zUpHeight(n)=80
    zRollOnImpact(n)=1
    zJumpSnd(n)=jumpsnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=27
    zWalkFrameSpeed#(n)=2
    zRunFrames(n)=7
    zRunFrameSpeed#(n)=3
    zRunSpeed#(n)=4
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    gender(n)=femaleVal
    duckFrames(n)=5
    duckFrameSpeed(n)=5
    canAirGlide(n)=1
    hasSpecialAirFrames(n)=1
    superPicNum(n)=2
    electrocuteFrames(n)=3
    electrocuteFrameSpd(n)=2
    zBlockedSnd(n)=wwBlockedSnd
    zBouncedGndFrames(n)=3
    specialHitFrames(n)=8
    zFlipMaxSeq(n)=27

Case 15: ;Juggernaut
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zGrabDist(n)=zGrabDist(n)+15
    zSide(n)=16
    zheight(n)=90
    zUpHeight(n)=90
    zRollOnImpact(n)=1
    zJumpSnd(n)=juggJumpSnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=16
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=4
    zRunSpeed#(n)=4.8
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    gender(n)=maleVal
    zRunFootSoundSeq(n)=18
    zWalkQuakeSeq1(n)=2
    zWalkQuakeSeq2(n)=10
    isHeavy(n)=1
    hasSpecialAirFrames(n)=1

Case 16: ;Piccolo
    zBlowDist(n,1)=60
    zBlowDist(n,2)=52
    zBlowDist(n,4)=53
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=90    :dangerMove9(n)=1
    zBlowDist(n,10)=50
    zBlowDist(n,11)=150
    zBlowDist(n,14)=120
    zBlowDist(n,16)=370
    zheight(n)=86
    zUpHeight(n)=86
    zRollOnImpact(n)=1
    zJumpSnd(n)=dbzJmpSnd
    zJumpSnd2(n)=dbzJmp2Snd
    zWalkFrames(n)=4
    zWalkFrameSpeed#(n)=7
    zRunFrames(n)=1
    zRunFrameSpeed#(n)=3
    zRunSpeed#(n)=5.6
    gender(n)=maleVal
    flipFrames(n)=6
    zFlipMaxSeq(n)=21
    canAirGlide(n)=1
    dizzyFrames(n)=4
    dizzyFrameSpeed(n)=7
    hasSpecialAirFrames(n)=1
    specialHitFrames(n)=10
    hitFrameSpeed(n)=4
    zGrabDist(n)=zGrabDist(n)+15
    maxHitSeq(n)=35
    
Case 17: ;Hulk
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zGrabDist(n)=zGrabDist(n)+15
    zSide(n)=16
    zheight(n)=90
    zUpHeight(n)=90
    zRollOnImpact(n)=1
    zJumpSnd(n)=juggJumpSnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=15
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=4
    zRunSpeed#(n)=4.8
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    gender(n)=maleVal
    zRunFootSoundSeq(n)=18
    zWalkQuakeSeq1(n)=2
    zWalkQuakeSeq2(n)=10
    isHeavy(n)=1
    hasSpecialAirFrames(n)=1
    
Case 18: ;Thor
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zGrabDist(n)=zGrabDist(n)+15
    zSide(n)=16
    zheight(n)=90
    zUpHeight(n)=90
    zRollOnImpact(n)=1
    zJumpSnd(n)=juggJumpSnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=15
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=4
    zRunSpeed#(n)=4.8
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    gender(n)=maleVal
    zRunFootSoundSeq(n)=18
    zWalkQuakeSeq1(n)=2
    zWalkQuakeSeq2(n)=10
    isHeavy(n)=1
    hasSpecialAirFrames(n)=1
    
Case 19: ;Leilei
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zGrabDist(n)=zGrabDist(n)+15
    zSide(n)=16
    zheight(n)=90
    zUpHeight(n)=90
    zRollOnImpact(n)=1
    zJumpSnd(n)=juggJumpSnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=15
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=4
    zRunSpeed#(n)=4.8
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    gender(n)=maleVal
    zRunFootSoundSeq(n)=18
    zWalkQuakeSeq1(n)=2
    zWalkQuakeSeq2(n)=10
    isHeavy(n)=1
    hasSpecialAirFrames(n)=1
    
Case 20: ;kenshiro
    zBlowDist(n,1)=45
    zBlowDist(n,2)=50
    zBlowDist(n,4)=64
    zBlowDist(n,5)=48
    zBlowDist(n,7)=250
    zBlowDist(n,9)=400    :dangerMove9(n)=1
    zBlowDist(n,10)=40
    zBlowDist(n,11)=150
    zBlowDist(n,14)=600
    zBlowDist(n,16)=370
    zGrabDist(n)=zGrabDist(n)+15
    zSide(n)=16
    zheight(n)=90
    zUpHeight(n)=90
    zRollOnImpact(n)=1
    zJumpSnd(n)=juggJumpSnd
    zJumpSnd2(n)=wolverinejumpsnd
    zWalkFrames(n)=15
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=6
    zRunFrameSpeed#(n)=4
    zRunSpeed#(n)=4.8
    dizzyFrames(n)=8
    dizzyFrameSpeed(n)=7
    gender(n)=maleVal
    zRunFootSoundSeq(n)=18
    zWalkQuakeSeq1(n)=2
    zWalkQuakeSeq2(n)=10
    isHeavy(n)=1
    hasSpecialAirFrames(n)=1
    
Case 30: ;Pig
    zBlowDist(n,1)=64
    zBlowDist(n,2)=60
    zBlowDist(n,4)=60
    zBlowDist(n,5)=60
    zBlowDist(n,7)=60
    zBlowDist(n,9)=60    :dangerMove9(n)=1
    zBlowDist(n,10)=80
    zBlowDist(n,11)=60
    zBlowDist(n,14)=60
    zDuckHeight(n)=72
    zJumpLimit(n)=10
    zDontJump(n)=1
    zDontPickItem(n)=1
    zDtopSpeed#(n)=2.4
    zTopSpeed#(n)=zDtopSpeed(n)
    zNoAirSpecial(n)=1
    gender(n)=maleVal

Case 31: ;Alien
    zBlowDist(n,1)=64
    zBlowDist(n,2)=60
    zBlowDist(n,4)=60
    zBlowDist(n,5)=60
    zBlowDist(n,7)=100
    zBlowDist(n,9)=60    :dangerMove9(n)=1
    zBlowDist(n,10)=80
    zBlowDist(n,11)=60
    zBlowDist(n,14)=60
    zheight(n)=64                ;Player's current height
    zUpHeight(n)=64
    zDuckHeight(n)=64
    zDontPickItem(n)=1
    zNoAirSpecial(n)=1
    gender(n)=maleVal

Case 32: ;Foot Clan
    zBlowDist(n,1)=50
    zBlowDist(n,2)=55
    zBlowDist(n,4)=70
    zBlowDist(n,5)=70
    zBlowDist(n,7)=70
    zBlowDist(n,9)=70    :dangerMove9(n)=1
    zBlowDist(n,10)=55
    zBlowDist(n,11)=55
    zBlowDist(n,14)=55
    zDuckHeight(n)=72
    zDontPickItem(n)=1
    zNoAirSpecial(n)=1
    gender(n)=maleVal

Case 33: ;Shredder
    zBlowDist(n,1)=50
    zBlowDist(n,2)=50
    zBlowDist(n,4)=50
    zBlowDist(n,5)=50
    zBlowDist(n,7)=110
    zBlowDist(n,9)=110    :dangerMove9(n)=0
    zBlowDist(n,10)=50
    zBlowDist(n,11)=50
    zBlowDist(n,14)=50
    zDontPickItem(n)=1
    gender(n)=maleVal
    isBoss(n)=1

Case 34: ;Thug
    zBlowDist(n,1)=40
    zBlowDist(n,2)=60
    zBlowDist(n,4)=40
    zBlowDist(n,5)=40
    zBlowDist(n,7)=40
    zBlowDist(n,9)=40    :dangerMove9(n)=0
    zBlowDist(n,10)=40
    zBlowDist(n,11)=40
    zBlowDist(n,14)=40
    zDontPickItem(n)=1
    zNoAirSpecial(n)=1
    zRollOnImpact(n)=1
    gender(n)=maleVal

Case 35: ;Red horns
    zBlowDist(n,1)=164
    zBlowDist(n,2)=160
    zBlowDist(n,4)=160
    zBlowDist(n,5)=160
    zBlowDist(n,7)=160
    zBlowDist(n,9)=160    :dangerMove9(n)=0
    zBlowDist(n,10)=160
    zBlowDist(n,11)=160
    zBlowDist(n,14)=160
    zDuckHeight(n)=72
    zJumpLimit(n)=16
    zDontJump(n)=1
    zDontPickItem(n)=1
    zDtopSpeed#(n)=.8
    zTopSpeed#(n)=zDtopSpeed(n)
    zNoAirSpecial(n)=1
    gender(n)=maleVal

Case 36: ;Gargola
    isBoss(n)=1
    zBlowDist(n,1)=364
    zBlowDist(n,2)=360
    zBlowDist(n,4)=360
    zBlowDist(n,5)=360
    zBlowDist(n,7)=360
    zBlowDist(n,9)=360    :dangerMove9(n)=0
    zBlowDist(n,10)=360
    zBlowDist(n,11)=360
    zBlowDist(n,14)=360
    zSide(n)=40
    zDuckHeight(n)=72
    zJumpLimit(n)=16
    zDontJump(n)=1
    zDontPickItem(n)=1
    zDtopSpeed#(n)=0
    zTopSpeed#(n)=zDtopSpeed(n)
    zNoAirSpecial(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    yRange(n)=200

Case 37: ;Red plant
    zBlowDist(n,1)=364
    zBlowDist(n,2)=360
    zBlowDist(n,4)=360
    zBlowDist(n,5)=360
    zBlowDist(n,7)=360
    zBlowDist(n,9)=360    :dangerMove9(n)=0
    zBlowDist(n,10)=360
    zBlowDist(n,11)=360
    zBlowDist(n,14)=360
    zDuckHeight(n)=72
    zJumpLimit(n)=0
    zDontJump(n)=1
    zDontPickItem(n)=1
    zDtopSpeed#(n)=0
    zTopSpeed#(n)=zDtopSpeed(n)
    zNoAirSpecial(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    doesCharBleed(n)=0

Case 38: ;Bowser
    isBoss(n)=1
    zBlowDist(n,1)=120
    zBlowDist(n,2)=360
    zBlowDist(n,4)=360
    zBlowDist(n,5)=100
    zBlowDist(n,7)=360
    zBlowDist(n,9)=120    :dangerMove9(n)=0
    zBlowDist(n,10)=120
    zBlowDist(n,11)=360
    zBlowDist(n,14)=360
    zheight(n)=83
    zUpHeight(n)=83
    zDuckHeight(n)=83
    zSide(n)=29
    zJumpLimit(n)=0
    zDontJump(n)=1
    zNoAirSpecial(n)=1
    zDontPickItem(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    yRange(n)=100
    gender(n)=maleVal

Case 39    ;thief
    zBlowDist(n,1)=200
    zBlowDist(n,2)=200
    zBlowDist(n,4)=200
    zBlowDist(n,5)=200
    zBlowDist(n,7)=200
    zBlowDist(n,9)=200    :dangerMove9(n)=0
    zBlowDist(n,10)=200
    zBlowDist(n,11)=200
    zBlowDist(n,14)=200
    zDuckHeight(n)=72
    zDontJump(n)=1
    zDontPickItem(n)=1
    zNoAirSpecial(n)=1
    gender(n)=maleVal

Case 40    ;turtle
    zBlowDist(n,1)=50
    zBlowDist(n,2)=200
    zBlowDist(n,4)=200
    zBlowDist(n,5)=200
    zBlowDist(n,7)=200
    zBlowDist(n,9)=200    :dangerMove9(n)=0
    zBlowDist(n,10)=200
    zBlowDist(n,11)=200
    zBlowDist(n,14)=200
    hasSpecialAirFrames(n)=1
    zheight(n)=50
    zUpHeight(n)=50
    zDuckHeight(n)=40
    zDontJump(n)=1
    zDontPickItem(n)=0
    zNoAirSpecial(n)=1
    gender(n)=noGenderVal
    dizzyFrames(n)=2
    dizzyFrameSpeed(n)=7
    electrocuteFrames(n)=3
    electrocuteFrameSpd(n)=2
    zJumpSnd(n)=mvcJump1Snd
    zJumpSnd2(n)=mvcJump2Snd
    zBouncedGndFrames(n)=1
    zWalkFrames(n)=6
    zWalkFrameSpeed#(n)=7

Case 41: ;Turtle Cloud
    zJumpLimit(n)=0
    zDontJump(n)=1
    zDontPickItem(n)=1
    zDtopSpeed#(n)=1.5
    zTopSpeed#(n)=zDtopSpeed(n)
    zNoAirSpecial(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    zAcc(n)=.1
    zUseSpecialAI(n)=1
    zCanFly(n)=1
    zHelperObj(n)=1
    If zvar1(n) > 0 Then
        zDtopSpeed(n)=zvar1(n)
        zTopSpeed(n)=zDTopSpeed(n)
    EndIf

Case 42    ;Joker
    isBoss(n)=1
    zBlowDist(n,1)=50       ;1  - blow
    zBlowDist(n,2)=200      ;2  - flying blow
    zBlowDist(n,4)=50       ;4  - low blow
    zBlowDist(n,5)=50       ;5  - up special
    zBlowDist(n,7)=200      ;7  - special
    zBlowDist(n,9)=200    :dangerMove9(n)=0;9  - down special
    zBlowDist(n,10)=50      ;10 - high blow
    zBlowDist(n,11)=200
    zBlowDist(n,14)=200
    zDuckHeight(n)=72
    zNoAirSpecial(n)=1
    zDtopSpeed#(n)=4
    zDontPickItem(n)=1
    zTopSpeed#(n)=zDtopSpeed(n)
    gender(n)=maleVal

Case 43;Laser helper
    zUpHeight(n)=32
    zDuckHeight(n)=32
    zNoAirSpecial(n)=1
    zDtopSpeed#(n)=0
    zTopSpeed#(n)=zDtopSpeed(n)
    zDontPickItem(n)=1
    zDontJump(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    zHelperObj(n)=1
    zUseSpecialAI(n)=1
    doesCharBleed(n)=0

Case 44    ;Venom
    isBoss(n)=1
    zBlowDist(n,1)=120
    zBlowDist(n,2)=120
    zBlowDist(n,4)=120
    zBlowDist(n,5)=60   : dangerMove5(n)=0
    zBlowDist(n,7)=120
    zBlowDist(n,9)=120    :dangerMove9(n)=0
    zBlowDist(n,10)=60
    zBlowDist(n,11)=50
    zBlowDist(n,14)=50
    zGrabDist(n)=zGrabDist(n)+15
    zDontPickItem(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    zBlockFull(n)=500
    gender(n)=maleVal

Case 45    ;bombing ship
    zUpHeight(n)=64
    zDtopSpeed#(n)=12.8
    zTopSpeed#(n)=zDtopSpeed(n)
    zUseSpecialAI(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    zHelperObj(n)=1
    zDontPickItem(n)=1
    If zvar1(n) = 0 Then zvar1(n) = 12
    If zvar2(n) > 0 Then zFace(n)=zvar2(n)
    If gamesound Then PlaySound flyBySnd
    zBlowSeq2(n)=0
    zCanFly(n)=1
    doesCharBleed(n)=0

Case 46    ;Ray balls
    zUpHeight(n)=32
    zDuckHeight(n)=32
    zNoAirSpecial(n)=1
    zDtopSpeed#(n)=0
    zTopSpeed#(n)=zDtopSpeed(n)
    zDontPickItem(n)=1
    zDontJump(n)=1
    zStone(n)=1
    zUngrabable(n)=1
    zHelperObj(n)=1
    zUseSpecialAI(n)=1
    doesCharBleed(n)=0

Case 47    ;soldier
    zDontJump(n)=1
    zNoAirSpecial(n)=1
    zDtopSpeed#(n)=0
    zTopSpeed#(n)=zDtopSpeed(n)
    zDontPickItem(n)=1
    zUseSpecialAI(n)=1
    gender(n)=maleVal

Case 48: ;Cylinder
    zUpHeight(n)=32
    zDuckHeight(n)=32
    zDontPickItem(n)=1
    zDtopSpeed(n)=1
    zTopSpeed(n)=zDtopSpeed(n)
    zStone(n)=1
    zUngrabable(n)=1
    zAcc(n)=.1
    zUseSpecialAI(n)=1
    zCanFly(n)=1
    doesCharBleed(n)=0

Case 49: ;Dragon
    isBoss(n)=1
    zUpHeight(n)=104
    zDuckHeight(n)=zUpHeight(n)
    zSide(n)=80
    zDontPickItem(n)=1
    zDtopSpeed#(n)=1.6
    zTopSpeed(n)=zDtopSpeed(n)
    zStone(n)=1
    zUngrabable(n)=1
    zAcc(n)=.16
    zUseSpecialAI(n)=1
    zCanFly(n)=1

Case 50: ;Laser beam
    zUpHeight(n)=32
    zDuckHeight(n)=32
    zSide(n)=3
    zDontPickItem(n)=1
    zDtopSpeed(n)=0
    zTopSpeed(n)=zDtopSpeed(n)
    zStone(n)=1
    zUngrabable(n)=1
    zAcc(n)=0
    zUseSpecialAI(n)=1
    zCanFly(n)=1
    zHelperObj(n)=1
    doesCharBleed(n)=0

    ;zxrespawn(n)=zx(n)
    ;zyrespawn(n)=zy(n)

Case 51: ;Hattori Hanzo
    zBlowDist(n,1)=60
    zBlowDist(n,2)=52
    zBlowDist(n,4)=53
    zBlowDist(n,5)=48
    zBlowDist(n,7)=300
    zBlowDist(n,9)=90    :dangerMove9(n)=1
    zBlowDist(n,10)=50
    zBlowDist(n,11)=150
    zBlowDist(n,14)=120
    zDtopSpeed(n)=4
    hasSpecialAirFrames(n)=1
    zJumpSnd(n)=mvcJump4Snd
    zJumpSnd2(n)=wolverinejumpsnd
    zTopSpeed(n)=zDtopSpeed(n)
    zRollOnImpact(n)=1
    gender(n)=maleVal
    zWalkFrames(n)=10
    zWalkFrameSpeed#(n)=4
    zRunFrames(n)=4
    zRunFrameSpeed#(n)=4
    zRunFootSoundSeq(n)=18
    zBlockedSnd(n)=wwBlockedSnd
    zBouncedGndFrames(n)=1
    specialHitFrames(n)=1
    dizzyFrames(n)=1

Case 52: ;punching bag
    zUpHeight(n)=54
    zDuckHeight(n)=54
    zSide(n)=11
    zDontPickItem(n)=1
    zDtopSpeed(n)=0
    zTopSpeed(n)=zDtopSpeed(n)
    zUngrabable(n)=1
    zAcc(n)=0
    zUseSpecialAI(n)=1
    zHelperObj(n)=1
    doesCharBleed(n)=0
    
Case 53: ;Gohan helper
    zUpHeight(n)=51
    zHeight(n)=51
    zSide(n)=80
    zDontPickItem(n)=1
    zDtopSpeed#(n)=12.8
    zTopSpeed(n)=zDtopSpeed(n)
    zUngrabable(n)=1
    zAcc(n)=.16
    zUseSpecialAI(n)=1
    zCanFly(n)=1
    hasSpecialAirFrames(n)=1
End Select

setObjPos(n)

End Function
;-----------shotData--------------------------------------------
Function shotData(weaponChosen,n)

;Defaut Values
    shotType(n)=weaponChosen
    shotspeed(n)=3
    shotYspeed(n)=0
    shotsize(n)=10    ;width
    shotheight(n)=10
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=3
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=30
    shotHitMode(n)=2
    shotImpact(n)=8
    shotDuration(n)=50
    shotDuration2(n)=80
    shotDurationSeq(n)=0
    shotBounce(n)=0
    shotUturn(n)=0
    shotUturnSeq(n)=0
    shotUturnAmount(n)=0
    shotFollowOwner(n)=0
    shotAcc(n)=.2            ;shot acceleration
    shotMaxSpeed(n)=shotSpeed(n)
    shotDrill(n)=0
    shotChunkType(n)=20        ;type of the animation when the shot hits
    zShotLimit(nn)=99
    shotCurFrame(n)=1
    shotFrameTime(n)=999
    shotFramesAmount(n)=1
    shotImmuneTime(n)=20
    shotSound(n)=mikekicksnd
    shotHitTrail(n)=0
    shotTrailType(n)=0
    shotSuper(n)=0
    shotUseAcc(n)=0
    shotHold(n)=8
    shotExplosive(n)=0
    shotExplosiveDamage(n)=0
    shotExplosionSound(n)=explodeSnd
    shotExplosiveSide(n)=20
    shotExplosiveHeight(n)=40
    shotExpImpact(n)=50
    shotExplodeChunk(n)=4
    shotWidth(n)=1
    isShotDisappearOnHit(n)=0
    shotChunkHitType(n)=0
    doesShotBurn(n)=0
    shotGroundType(n)=0
    electrocuteTime(n)=0
    shotSeekType(n)=shotSeekNone
    shotVerticalSize(n)=1
    shotSeekSpeed#(n)=0
    shotGroundXDestroy(n)=0
    shotStopDuration(n)=0
    zShotHitType(n)=0
    zShotHitTypeModulo(n)=0
    shotHasAfterImg(n)=0
    shotReturnOnHit(n)=0
    isShotReturning(n)=0
    shotReturnXDest(n)=0
    shotReturnYDest(n)=0
    shotHits(n)=0
    shotHitBeforeReturn(n)=0
    shotHitBeforeFade(n)=0
    shotChunkYAdj(n)=0
    shotStillSeq(n)=0
    shotStillMaxTime(n)=0

Select weaponChosen
    
Case 5    ;hadouken slow
    shotspeed(n)=4
    shotsize(n)=57
    shotheight(n)=35
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=20
    shotDuration(n)=240
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=169
    shotChunkYAdj(n)=16
    shotFramesAmount(n)=4
    shotFrameTime(n)=2
    shotPic(n,1)=shotImage(78)
    shotPic_(n,1)=shotImage_(78)
    shotPic(n,2)=shotImage(79)
    shotPic_(n,2)=shotImage_(79)
    shotPic(n,3)=shotImage(80)
    shotPic_(n,3)=shotImage_(80)
    shotPic(n,4)=shotImage(81)
    shotPic_(n,4)=shotImage_(81)
    shotSound(n)=evilRyuHit1Snd

Case 6    ;web shot
    shotspeed(n)=3
    shotsize(n)=24
    shotheight(n)=7
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=4
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=20
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=6
    shotPic(n,1)=shotImage(6)
    shotPic_(n,1)=shotImage_(6)
    shotSound(n)=webhitsnd

Case 7    ; fire ball
    shotspeed(n)=3
    shotsize(n)=12
    shotheight(n)=15
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=4
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=20
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(7)
    shotPic_(n,1)=shotImage(7)
    shotSound(n)=firehitsnd
    shotHitTrail(n)=1

Case 8    ;laser
    shotspeed(n)=5
    shotsize(n)=12
    shotheight(n)=6
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=15
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotHitMode(n)=0
    shotImpact(n)=15
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(8)
    shotPic_(n,1)=shotImage(8)
    shotSound(n)=firehitsnd
    shotHitTrail(n)=1

Case 9    ;Mike's dragon fire ball
    shotspeed(n)=3
    shotsize(n)=14
    shotheight(n)=12
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=4
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=20
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=3
    shotPic(n,1)=shotImage(9)
    shotPic_(n,1)=shotImage_(9)
    shotSound(n)=highPunchSnd

Case 10    ;bullet
    shotspeed(n)=7
    shotsize(n)=16
    shotheight(n)=3
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=3
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=30
    shotImpact(n)=10
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=5
    zShotLimit(nn)=10
    shotPic(n,1)=shotImage(2)
    shotPic_(n,1)=shotImage(2)
    shotSound(n)=gotShotSnd

Case 11    ;Rash Axe
    shotspeed(n)=6
    shotsize(n)=48
    shotheight(n)=29
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=8
    shotHitXspeed(n)=1
    shotHitYspeed(n)=1
    shotFallTime(n)=30
    shotImpact(n)=20
    shotDuration(n)=40
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=20
    shotPic(n,1)=shotImage(11)
    shotPic_(n,1)=shotImage_(11)
    shotSound(n)=RashHitSnd

Case 12    ;Rash Axe (hit mode 0)
    shotspeed(n)=6
    shotsize(n)=48
    shotheight(n)=29
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=8
    shotHitXspeed(n)=1
    shotHitYspeed(n)=1
    shotFallTime(n)=30
    shotHitMode(n)=0
    shotImpact(n)=20
    shotDuration(n)=40
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=20
    shotPic(n,1)=shotImage(11)
    shotPic_(n,1)=shotImage_(11)
    shotSound(n)=RashHitSnd

Case 13    ;fire ball (hitMode=0)
    shotspeed(n)=6
    shotsize(n)=12
    shotheight(n)=15
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=4
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=20
    shotHitMode(n)=0
    shotImpact(n)=20
    shotDuration(n)=50
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(7)
    shotPic_(n,1)=shotImage(7)
    shotSound(n)=firehitsnd
    shotHitTrail(n)=1

Case 14    ;fire ball (weak And fast for super special)
    shotspeed(n)=6
    shotsize(n)=12
    shotheight(n)=15
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=1
    shotHitXspeed(n)=1
    shotHitYspeed(n)=0
    shotFallTime(n)=30
    shotDuration(n)=50
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(7)
    shotPic_(n,1)=shotImage(7)
    shotSound(n)=firehitsnd
    shotHitTrail(n)=1

Case 15    ;ryu hayabusa ninja star
    shotspeed(n)=4
    shotsize(n)=12
    shotheight(n)=12
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=3
    shotHitXspeed(n)=2
    shotHitYspeed(n)=2
    shotFallTime(n)=20
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=5
    shotPic(n,1)=shotImage(12)
    shotPic_(n,1)=shotImage(12)
    shotPic(n,2)=shotImage(13)
    shotPic_(n,2)=shotImage(13)
    shotFramesAmount(n)=2
    shotFrameTime(n)=2
    shotSound(n)=slashSnd

Case 16    ;ninja star Uturn boomerang effect
    shotspeed(n)=5
    shotsize(n)=16
    shotheight(n)=16
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=2
    shotHitMode(n)=0
    shotFallTime(n)=40
    shotDuration(n)=15
    shotDuration2(n)=55
    shotFollowOwner(n)=1
    shotBounce(n)=1
    shotUseAcc(n)=1
    shotImmuneTime(n)=30
    shotUturn(n)=1
    shotUturnAmount(n)=4
    shotAcc(n)=.2
    shotDrill(n)=1
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=5
    shotPic(n,1)=shotImage(14)
    shotPic_(n,1)=shotImage(14)
    shotPic(n,2)=shotImage(15)
    shotPic_(n,2)=shotImage(15)
    shotFramesAmount(n)=2
    shotFrameTime(n)=3
    shotSound(n)=slashsnd

Case 17    ;Red Horns Uturn fire
    shotspeed(n)=4
    shotsize(n)=16
    shotheight(n)=16
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=3
    shotHitMode(n)=0
    shotHitXspeed(n)=2
    shotHitYspeed(n)=3
    shotFallTime(n)=40
    shotDuration(n)=25
    shotDuration2(n)=45
    shotFollowOwner(n)=1
    shotBounce(n)=1
    shotImmuneTime(n)=30
    shotUturn(n)=1
    shotUturnAmount(n)=0
    shotAcc(n)=.2
    shotUseAcc(n)=1
    shotDrill(n)=1
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=20
    shotPic(n,1)=shotImage(16)
    shotPic_(n,1)=shotImage(16)
    shotPic(n,2)=shotImage(17)
    shotPic_(n,2)=shotImage(17)
    shotFramesAmount(n)=2
    shotFrameTime(n)=3
    shotSound(n)=firehitsnd

Case 18    ;pink blob
    shotspeed(n)=3
    shotsize(n)=24
    shotheight(n)=24
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitMode(n)=0
    shotHitXspeed(n)=3
    shotHitYspeed(n)=3
    shotFallTime(n)=40
    shotDuration(n)=300
    shotChunkType(n)=5
    shotPic(n,1)=shotImage(18)
    shotPic_(n,1)=shotImage(18)
    shotPic(n,2)=shotImage(19)
    shotPic_(n,2)=shotImage(19)
    shotFramesAmount(n)=2
    shotFrameTime(n)=3
    shotSound(n)=webhitsnd

Case 19    ; bowser fire ball
    shotspeed(n)=3
    shotsize(n)=24
    shotheight(n)=14
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitXspeed(n)=3
    shotHitYspeed(n)=2
    shotFallTime(n)=50
    shotDuration(n)=400
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(20)
    shotPic_(n,1)=shotImage_(20)
    shotPic(n,2)=shotImage(21)
    shotPic_(n,2)=shotImage_(21)
    shotPic(n,3)=shotImage(22)
    shotPic_(n,3)=shotImage_(22)
    shotFramesAmount(n)=3
    shotFrameTime(n)=3
    shotSound(n)=firehitsnd
    shotHitTrail(n)=1

Case 20    ;batrang
    shotspeed(n)=4
    shotsize(n)=14
    shotheight(n)=10
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitMode(n)=2
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotDuration(n)=30
    shotDuration2(n)=52
    shotFollowOwner(n)=1
    shotBounce(n)=1
    shotUseAcc(n)=1
    shotImmuneTime(n)=15
    shotUturn(n)=1
    shotAcc(n)=.2
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=50
    shotPic(n,1)=shotImage(23)
    shotPic_(n,1)=shotImage_(23)
    shotSound(n)=bhitSnd

Case 21    ;predator green ray
    shotspeed(n)=5
    shotsize(n)=8
    shotheight(n)=6
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=7
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=50
    shotDuration(n)=150
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=50
    shotDrill(n)=1
    shotPic(n,1)=shotImage(24)
    shotPic_(n,1)=shotImage_(24)
    shotPic(n,2)=shotImage(25)
    shotPic_(n,2)=shotImage_(25)
    shotFramesAmount(n)=2
    shotFrameTime(n)=4
    shotImmuneTime(n)=20
    shotHitTrail(n)=2
    shotSound(n)=predHitSnd

Case 22    ;predator disc
    shotspeed(n)=4
    shotsize(n)=11
    shotheight(n)=18
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitMode(n)=2
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotDuration(n)=30
    shotDuration2(n)=52
    shotFollowOwner(n)=1
    shotBounce(n)=1
    shotUseAcc(n)=1
    shotDrill(n)=1
    shotImmuneTime(n)=30
    shotUturn(n)=1
    shotAcc(n)=.2 ;2
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=5
    shotPic(n,1)=shotImage(26)
    shotPic_(n,1)=shotImage(26)
    shotPic(n,2)=shotImage(27)
    shotPic_(n,2)=shotImage(27)
    shotFramesAmount(n)=2
    shotFrameTime(n)=2
    shotSound(n)=slashSnd

Case 23    ;missile (hitMode=0)
    shotspeed(n)=6
    shotsize(n)=15
    shotheight(n)=6
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=2
    shotHitMode(n)=0
    shotImpact(n)=40
    shotDuration(n)=9999
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(28)
    shotPic_(n,1)=shotImage_(28)
    shotSound(n)=noSnd
    shotHitTrail(n)=1
    shotTrailType(n)=1
    shotExplosive(n)=1

Case 24    ;big laser
    shotspeed(n)=7
    shotsize(n)=40
    shotheight(n)=4
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=10
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotHitMode(n)=0
    shotImpact(n)=10
    shotDuration(n)=100
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=7
    shotPic(n,1)=shotImage(29)
    shotPic_(n,1)=shotImage_(29)
    shotSound(n)=firehitsnd
    shotHitTrail(n)=1

Case 25    ;ray blue ball
    shotspeed(n)=4
    shotsize(n)=14
    shotheight(n)=12
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=6
    shotHitXspeed(n)=5
    shotHitYspeed(n)=3
    shotFallTime(n)=60
    shotHitMode(n)=2
    shotImpact(n)=15
    shotDuration(n)=300
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=30
    shotPic(n,1)=shotImage(30)
    shotPic_(n,1)=shotImage(30)
    shotPic(n,2)=shotImage(31)
    shotPic_(n,2)=shotImage(31)
    shotFramesAmount(n)=2
    shotFrameTime(n)=3
    shotSound(n)=shockSnd
    shotHitTrail(n)=4

Case 26    ;evil black missile
    shotspeed(n)=4
    shotsize(n)=16
    shotheight(n)=14
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=7
    shotHitXspeed(n)=5
    shotHitYspeed(n)=3
    shotFallTime(n)=60
    shotHitMode(n)=0
    shotImpact(n)=15
    shotDuration(n)=600
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=20
    shotPic(n,1)=shotImage(32)
    shotPic_(n,1)=shotImage_(32)
    shotSound(n)=marioFierceSnd

Case 27    ;cannon ball
    shotspeed(n)=4
    shotsize(n)=12
    shotheight(n)=12
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=7
    shotHitXspeed(n)=5
    shotHitYspeed(n)=3
    shotFallTime(n)=60
    shotHitMode(n)=0
    shotImpact(n)=15
    shotDuration(n)=600
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=20
    shotPic(n,1)=shotImage(33)
    shotPic_(n,1)=shotImage(33)
    shotSound(n)=marioFierceSnd

Case 28    ;Goku ball
    shotspeed(n)=3
    shotsize(n)=16
    shotheight(n)=10
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitXspeed(n)=4
    shotHitYspeed(n)=1.5
    shotFallTime(n)=40
    shotImpact(n)=16
    shotDuration(n)=50
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=3
    shotPic(n,1)=shotImage(34)
    shotPic_(n,1)=shotImage_(34)
    shotPic(n,2)=shotImage(35)
    shotPic_(n,2)=shotImage_(35)
    shotFramesAmount(n)=2
    shotFrameTime(n)=3
    shotSound(n)=highPunchSnd

Case 29    ;Ritcher cross
    shotspeed(n)=4
    shotsize(n)=20
    shotheight(n)=18
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=4
    shotHitMode(n)=2
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotDuration(n)=30
    shotDuration2(n)=150
    shotFollowOwner(n)=0
    shotBounce(n)=1
    shotTrailType(n)=2
    shotUseAcc(n)=1
    shotDrill(n)=1
    shotImmuneTime(n)=30
    shotUturn(n)=1
    shotAcc(n)=.2
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=10
    shotFramesAmount(n)=4
    shotPic(n,1)=shotImage(36)
    shotPic_(n,1)=shotImage(36)
    shotPic(n,2)=shotImage(37)
    shotPic_(n,2)=shotImage(37)
    shotPic(n,3)=shotImage(36)
    shotPic_(n,3)=shotImage(36)
    shotPic(n,4)=shotImage(37)
    shotPic_(n,4)=shotImage(37)
    shotFrameTime(n)=8
    shotSound(n)=mikepunchSnd
    
Case 30    ;Ritcher sword
    shotspeed(n)=8
    shotsize(n)=40
    shotheight(n)=2
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=4
    shotImmuneTime(n)=2
    shotHitXspeed(n)=2
    shotHitYspeed(n)=1.5
    shotFallTime(n)=40
    shotDuration(n)=100
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=50
    shotPic(n,1)=shotImage(38)
    shotPic_(n,1)=shotImage_(38)
    shotSound(n)=mikePunchSnd
    
Case 39    ;Sub Zero freeze ball
    shotspeed(n)=3.5
    shotsize(n)=40
    shotheight(n)=3
    shotWidth(n)=60
    shotVerticalSize(n)=1
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=0
    shotHitMode(n)=3
    shotFallTime(n)=0
    shotHitXspeed(n)=0
    shotHitYspeed(n)=0
    shotDuration(n)=70
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=75
    shotPic(n,1)=shotImage(47)
    shotPic_(n,1)=shotImage_(47)
    shotSound(n)=subZeroFreeze2Snd
    
Case 40 ;Sub Zero ground freeze
    shotspeed(n)=0
    shotsize(n)=40
    shotWidth(n)=87
    shotheight(n)=-28
    shotDamage(n)=4
    shotVerticalSize(n)=1
    shotHitMode(n)=4
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=200
    shotHitXspeed(n)=3
    shotHitYspeed(n)=3
    shotFallTime(n)=25
    shotDuration(n)=100
    shotMaxSpeed(n)=shotSpeed(n)
    shotDrill(n)=1
    shotChunkType(n)=94
    shotPic(n,1)=shotImage(48)
    shotPic_(n,1)=shotImage_(48)
    shotSound(n)=NoSnd
    
Case 41    ;Sub Zero ice spikes
    shotspeed(n)=6
    shotsize(n)=42
    shotheight(n)=40
    shotWidth(n)=50
    shotVerticalSize(n)=40
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=9
    shotHitMode(n)=3
    shotFallTime(n)=0
    shotImmuneTime(n)=0
    shotHitXspeed(n)=0
    shotHitYspeed(n)=0
    shotDuration(n)=300
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=156
    shotPic(n,1)=shotImage(49)
    shotPic_(n,1)=shotImage_(49)
    shotSound(n)=subZeroIceBlastSnd
    
Case 42    ;Sub Zero diagonal freeze ball
    shotspeed(n)=3
    shotYspeed(n)=3
    shotsize(n)=40
    shotheight(n)=3
    shotWidth(n)=50
    shotVerticalSize(n)=49
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=0
    shotHitMode(n)=3
    shotFallTime(n)=0
    shotHitXspeed(n)=0
    shotHitYspeed(n)=0
    shotDuration(n)=100
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=75
    shotPic(n,1)=shotImage(50)
    shotPic_(n,1)=shotImage_(50)
    shotSound(n)=subZeroFreeze2Snd
    
Case 43 ;Sub Zero ice clone
    shotspeed(n)=0
    shotsize(n)=44
    shotheight(n)=58
    shotWidth(n)=48
    shotVerticalSize(n)=28
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=0
    shotHitMode(n)=3
    shotFallTime(n)=0
    shotHitXspeed(n)=0
    shotHitYspeed(n)=0
    shotDuration(n)=100
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=156
    shotPic(n,1)=shotImage(51)
    shotPic_(n,1)=shotImage_(51)
    shotSound(n)=subZeroFreeze2Snd
    
Case 44    ;Sub Zero ice shower
    shotspeed(n)=0
    shotYspeed(n)=3
    shotsize(n)=16
    shotheight(n)=57
    shotWidth(n)=8
    shotVerticalSize(n)=57
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=0
    shotHitMode(n)=3
    shotFallTime(n)=0
    shotHitXspeed(n)=0
    shotHitYspeed(n)=0
    shotDuration(n)=100
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=100
    shotPic(n,1)=shotImage(52)
    shotPic_(n,1)=shotImage(52)
    shotInvertPic(n,1)=shotImage_(52)
    shotSound(n)=subZeroFreeze2Snd
    
Case 45 ;Scorpion Spear
    shotspeed(n)=6
    shotAcc(n)=0
    shotsize(n)=17
    shotheight(n)=7
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=9
    shotHitMode(n)=2
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=0
    shotDuration(n)=90
    shotFollowOwner(n)=0
    shotDrill(n)=0
    shotImmuneTime(n)=30
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=104
    shotChunkHitType(n)=95
    shotFramesAmount(n)=2
    shotPic(n,1)=shotImage(53)
    shotPic_(n,1)=shotImage(53)
    shotPic(n,2)=shotImage(54)
    shotPic_(n,2)=shotImage(54)
    shotFrameTime(n)=6
    shotSound(n)=scorpionSpearHitSnd
    isShotDisappearOnHit(n)=1
    
Case 46    ;Scorpion Fireball
    doesShotBurn(n)=1
    shotspeed(n)=6
    shotYspeed(n)=2.5
    shotsize(n)=38
    shotheight(n)=26
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=60
    shotHitMode(n)=0
    shotFallTime(n)=60
    shotHitXspeed(n)=6
    shotHitYspeed(n)=4
    shotDuration(n)=600
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=108
    shotPic(n,1)=shotImage(55)
    shotPic_(n,1)=shotImage_(55)
    shotExplosive(n)=1
    shotSound(n)=scorpionBurnSnd

Case 47 ;Juggernaut Earthquake
    shotGroundType(n)=1
    shotspeed(n)=5
    shotsize(n)=46
    shotheight(n)=26
    shotDamage(n)=9
    shotHitMode(n)=2
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=200
    shotHitXspeed(n)=5
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotDuration(n)=40
    shotMaxSpeed(n)=shotSpeed(n)
    shotDrill(n)=1
    shotChunkType(n)=114
    shotFramesAmount(n)=2
    shotPic(n,1)=shotImage(56)
    shotPic_(n,1)=shotImage_(56)
    shotPic(n,2)=shotImage(57)
    shotPic_(n,2)=shotImage_(57)
    shotFrameTime(n)=2
    shotSound(n)=juggLateralHitSnd
    shotGroundXDestroy(n)=-10

Case 48 ;Juggernaut Earthquake 2
    shotGroundType(n)=2
    shotspeed(n)=0
    shotYspeed(n)=-4
    shotsize(n)=61
    shotWidth(n)=65
    shotHeight(n)=35
    shotVerticalSize(n)=35
    shotDamage(n)=11
    shotHitMode(n)=2
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=5
    shotHitXspeed(n)=5.3
    shotHitYspeed(n)=2.3
    shotFallTime(n)=40
    shotDuration(n)=2
    shotMaxSpeed(n)=shotSpeed(n)
    shotDrill(n)=0
    shotChunkType(n)=115
    shotPic(n,1)=shotImage(58)
    shotPic_(n,1)=shotImage_(58)
    shotSound(n)=juggLateralHitSnd
    
Case 49    ;Kaikousen
    shotGroundType(n)=1
    shotspeed(n)=3
    shotWidth(n)=40
    shotsize(n)=40
    shotheight(n)=37
    shotDamage(n)=7
    shotHitMode(n)=2
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=100
    shotHitXspeed(n)=0
    shotHitYspeed(n)=0
    shotFallTime(n)=40
    shotDuration(n)=66
    shotMaxSpeed(n)=shotSpeed(n)
    shotDrill(n)=0
    shotChunkType(n)=121
    shotFramesAmount(n)=11
    shotFrameTime(n)=3
    shotSound(n)=shockSnd
    electrocuteTime(n)=110
    shotGroundXDestroy(n)=50

    shotPicIdx=1
    For shotImg=59 To 69
        shotPic(n,shotPicIdx)=shotImage(shotImg)
        shotPic_(n,shotPicIdx)=shotImage_(shotImg)
        shotPicIdx=shotPicIdx+1
    Next

Case 50    ;Kiryoku-ryuu
    shotspeed#(n)=3
    shotWidth(n)=20
    shotsize(n)=20
    shotheight(n)=20
    shotDamage(n)=5
    shotHitMode(n)=2
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=50
    shotHitXspeed(n)=3
    shotHitYspeed(n)=2
    shotFallTime(n)=40
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed#(n)
    shotDrill(n)=0
    shotChunkType(n)=122
    shotFramesAmount(n)=2
    shotFrameTime(n)=2
    shotSound(n)=dbzKiHitSnd
    shotSeekType(n)=seekTypeSemi
    shotSeekSpeed#(n)=2
    shotPic(n,1)=shotImage(70)
    shotPic_(n,1)=shotImage(70)
    shotPic(n,2)=shotImage(71)
    shotPic_(n,2)=shotImage_(71)
    
Case 51 ;Makuuhouidan
    xSpdRand#=rand(40)
    durationRand#=rand(8)
    shotspeed#(n)=3+(xSpdRand#/10.0)
    shotYspeed#(n)=-6
    shotWidth(n)=20
    shotsize(n)=20
    shotheight(n)=20
    shotDamage(n)=5
    shotHitMode(n)=2
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=1
    shotHitXspeed(n)=4
    shotHitYspeed(n)=2
    shotFallTime(n)=60
    shotDuration(n)=20+durationRand
    shotStopDuration(n)=200
    shotMaxSpeed(n)=shotSpeed#(n)
    shotDrill(n)=0
    shotFramesAmount(n)=2
    shotFrameTime(n)=2
    shotExplosive(n)=2
    shotExplosiveDamage(n)=6
    shotExplosiveSide(n)=40
    shotExplosiveHeight(n)=20
    shotExplosionSound(n)=piccoloMakuuSnd
    shotExplodeChunk(n)=122
    shotSound(n)=piccoloMakuuSnd
    shotPic(n,1)=shotImage(70)
    shotPic_(n,1)=shotImage(70)
    shotPic(n,2)=shotImage(71)
    shotPic_(n,2)=shotImage_(71)
    
Case 52:    ;Wonderwoman's Tiara
    shotReturnOnHit(n)=1
    shotspeed#(n)=0
    shotWidth(n)=9
    shotsize(n)=9
    shotheight(n)=9
    shotDamage(n)=15
    shotHitMode(n)=2
    shotSide(n)=shotsize(n)/2
    shotImmuneTime(n)=40
    shotHitXspeed(n)=1
    shotHitYspeed(n)=1
    shotFallTime(n)=60
    shotDuration(n)=300
    shotMaxSpeed(n)=shotSpeed#(n)
    shotDrill(n)=1
    shotChunkType(n)=20
    shotChunkHitType(n)=95
    shotSound(n)=wwTiaraBackSnd
    shotSeekType(n)=seekTypeFull
    shotSeekSpeed#(n)=12
    shotPic(n,1)=shotImage(72)
    shotPic_(n,1)=shotImage_(72)
    shotYPic(n,1)=shotImage(75)
    shotYPic_(n,1)=shotImage(75)
    zShotHitType(n)=hitTypeBySeq
    zShotHitTypeModulo(n)=5
    shotHasAfterImg(n)=1
    shotHold(n)=40
    shotAfterImage(n, 1)=shotImage(73)
    shotAfterImage_(n, 1)=shotImage_(73)
    shotAfterImage(n, 2)=shotImage(74)
    shotAfterImage_(n, 2)=shotImage_(74)
    shotAfterImage(n, 3)=shotImage(76)
    shotAfterImage_(n, 3)=shotImage_(76)
    shotAfterImage(n, 4)=shotImage(77)
    shotAfterImage_(n, 4)=shotImage_(77)
    shotReturnXDest(n)=-10
    shotReturnYDest(n)=-33
    shotHitBeforeReturn(n)=1
    
Case 53    ;hadouken fast
    shotspeed(n)=6.5
    shotsize(n)=56
    shotheight(n)=35
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitXspeed(n)=4
    shotHitYspeed(n)=4
    shotFallTime(n)=20
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=169
    shotChunkYAdj(n)=16
    shotFramesAmount(n)=4
    shotFrameTime(n)=2
    shotPic(n,1)=shotImage(78)
    shotPic_(n,1)=shotImage_(78)
    shotPic(n,2)=shotImage(79)
    shotPic_(n,2)=shotImage_(79)
    shotPic(n,3)=shotImage(80)
    shotPic_(n,3)=shotImage_(80)
    shotPic(n,4)=shotImage(81)
    shotPic_(n,4)=shotImage_(81)
    shotSound(n)=evilRyuHit1Snd
    
Case 54    ;Charged hadouken
    Local chargeLvl=attackChargeLvl(shotOwner(n))
    shotspeed(n)=6 + (chargeLvl / 2)
    shotsize(n)=56
    shotheight(n)=35
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitXspeed(n)=4
    shotHitYspeed(n)=4
    shotFallTime(n)=20
    shotImmuneTime(n)=10
    shotHold(n)=12
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=169
    shotChunkYAdj(n)=16
    shotFramesAmount(n)=4
    shotFrameTime(n)=2
    shotHitBeforeFade(n)=chargeLvl
    shotPic(n,1)=shotImage(82)
    shotPic_(n,1)=shotImage_(82)
    shotPic(n,2)=shotImage(83)
    shotPic_(n,2)=shotImage_(83)
    shotPic(n,3)=shotImage(84)
    shotPic_(n,3)=shotImage_(84)
    shotPic(n,4)=shotImage(85)
    shotPic_(n,4)=shotImage_(85)
    shotSound(n)=evilRyuHit1Snd
    shotStillMaxTime(n)=10
    
Case 55    ;Down air hadouken
    shotspeed(n)=5
    shotYspeed(n)=5
    shotsize(n)=46
    shotheight(n)=47
    shotSide(n)=shotsize(n)/2
    shotdamage(n)=5
    shotHitXspeed(n)=4
    shotHitYspeed(n)=-4
    shotFallTime(n)=20
    shotImmuneTime(n)=99
    shotHold(n)=12
    shotDuration(n)=200
    shotMaxSpeed(n)=shotSpeed(n)
    shotChunkType(n)=169
    shotChunkYAdj(n)=16
    shotFramesAmount(n)=4
    shotFrameTime(n)=2
    shotHitBeforeFade(n)=chargeLvl
    shotPic(n,1)=shotImage(86)
    shotPic_(n,1)=shotImage_(86)
    shotPic(n,2)=shotImage(87)
    shotPic_(n,2)=shotImage_(87)
    shotPic(n,3)=shotImage(88)
    shotPic_(n,3)=shotImage_(88)
    shotPic(n,4)=shotImage(89)
    shotPic_(n,4)=shotImage_(89)
    shotSound(n)=evilRyuHit1Snd
    
End Select

End Function

;----------------------------- Object Data-------------------------------------------------------
Function objData(n,e)

objHitChunk(n)=5
objNoGrav(n)=0
objTrailType(n)=0
objSuper(n)=0
objHitMode(n)=0
objHitXspeed(n)=4.8
objHitYspeed(n)=3.2
objFallTime(n)=30
objEat(n)=0
BeatIten(n)=0
objExplosive(n)=0
xObjHand(n)=0
yObjHand(n)=0
objFrameTime(n)=999
objFrameAmount(n)=1
objCurFrame(n)=1
objFrameSeq(n)=0
objZMade(n)=0
objBounce(n)=0
shotExplosionSound(n)=explodeSnd

Select objType(n)
Case 1    ;yellow shell
    objXspeed(n)=11.2
    objSize(n)=16.8
    objYSpeed(n)=-1.6
    objYForce(n)=-8
    objdamage(n)=25
    objHeight(n)=27
    objSide(n)=11.2
    objImpact(n)=12
    If e=0 Then objLife(n)=50
    BeatIten(n)=0
    objHitSound(n)=tucupSnd
    objPic(n,1)=obj1P
    objPic_(n,1)=obj1P

Case 2    ;MedKit
    objXspeed(n)=4.8
    objSize(n)=22.4
    objYSpeed(n)=-1.6
    objYForce(n)=-8
    objdamage(n)=10
    objHeight(n)=26
    objSide(n)=11.2
    objImpact(n)=15
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objHitSound(n)=highpunchSnd
    objPic(n,1)=obj2P
    objPic_(n,1)=obj2P
    objEat(n)=1

Case 3    ;Green shell
    objXspeed(n)=11.2
    objSize(n)=22.4
    objYSpeed(n)=-1.6
    objYForce(n)=-8
    objdamage(n)=20
    objHeight(n)=27
    objSide(n)=11.2
    objImpact(n)=10
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objHitSound(n)=tucupSnd
    objPic(n,1)=obj3P
    objPic_(n,1)=obj3P

Case 4  ;Explosive Barrel
    objXspeed(n)=9.6
    objSize(n)=22.4
    objYSpeed(n)=-1.6
    objYForce(n)=-8
    objdamage(n)=3
    objHeight(n)=26
    objSide(n)=11.2
    objImpact(n)=20
    objExplosive(n)=1
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objTrailType(n)=1
    objExplosionSound(n)=explodeSnd
    objHitSound(n)=highpunchSnd
    objPic(n,1)=obj4p
    objPic_(n,1)=obj4P

Case 5  ;helper
    objXspeed(n)=4
    objSize(n)=22.4
    objYSpeed(n)=-1.6
    objYForce(n)=-4
    objdamage(n)=2
    objHeight(n)=26
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=4    ;defines what will do when obj hits something!
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objHitSound(n)=highpunchSnd
    objPic(n,1)=obj5p
    objPic_(n,1)=obj5P

Case 6  ;Club
    objXspeed(n)=3
    objSize(n)=12
    objYSpeed(n)=-1
    objYForce(n)=-2.5
    objdamage(n)=20
    objHitMode(n)=2
    objHitXspeed(n)=4
    objHitYspeed(n)=2
    If e=0 Then objAmmo(n)=1
    objFallTime(n)=40
    objHeight(n)=52
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    If e=0 Then objLife(n)=20
    BeatIten(n)=1
    objHitSound(n)=highpunchSnd
    BeatItenType(n)=11
    objPic(n,1)=epic(1,1)
    objPic_(n,1)=epic_(1,1)

Case 7  ; gun
    objXspeed(n)=5
    objSize(n)=16
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=12
    objHeight(n)=10
    objSide(n)=objSize(n)/2
    objImpact(n)=15
    BeatIten(n)=1
    If e=0 Then ShotsFired(n)=0
    If e=0 Then objAmmo(n)=15
    If e=0 Then objLife(n)=10
    shotFireSound(e)=shotSnd
    shotPushForce(n)=0
    BeatItenType(n)=12
    objHitSound(n)=highpunchSnd
    objPic(n,1)=ePic(2,1)
    objPic_(n,1)=ePic_(2,1)
    zCurWeapon(e)=2        ;type # for weapon drawing
    zShootThis(e)=10    ;type of bullet from weapon
    zPushedForce(e)=shotPushForce(n)
    xObjHand(n)=8

Case 8    ;Acid spit
    objXspeed(n)=4
    objSize(n)=14
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=5
    objHeight(n)=6
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=2
    objHitMode(n)=2
    objHitXspeed(n)=4
    objHitYspeed(n)=2
    objFallTime(n)=30
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objZmade(n)=1
    objTrailType(n)=2
    objExplosionSound(n)=highpunchSnd
    objHitSound(n)=highpunchSnd
    objPic(n,1)=obj8p
    objPic_(n,1)=obj8P
    objHitChunk(n)=10

Case 9    ;batman little bomb
    objXspeed(n)=2.5
    objSize(n)=8
    objYSpeed(n)=-2
    objYForce(n)=-3
    objdamage(n)=5
    objHeight(n)=8
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=3
    objHitMode(n)=2
    objHitXspeed(n)=4
    objHitYspeed(n)=2
    objFallTime(n)=30
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objZmade(n)=1
    objTrailType(n)=1
    objExplosionSound(n)=explodeSnd
    objHitSound(n)=NoSnd
    objPic(n,1)=obj9p
    objPic_(n,1)=obj9P_
    objHitChunk(n)=26

Case 10    ;Bazooka
    objXspeed(n)=4
    objSize(n)=16
    objYSpeed(n)=-1
    objYForce(n)=-4
    objdamage(n)=10
    objHeight(n)=14
    objSide(n)=objSize(n)/2
    objImpact(n)=15
    BeatIten(n)=1
    If e=0 Then ShotsFired(n)=0
    If e=0 Then objAmmo(n)=1
    If e=0 Then objLife(n)=6
    shotFireSound(e)=bazookaSnd
    shotPushForce(n)=1.5
    BeatItenType(n)=12
    objHitSound(n)=highpunchSnd
    objPic(n,1)=ePic(3,1)
    objPic_(n,1)=ePic_(3,1)
    zCurWeapon(e)=3        ;type # for weapon drawing
    zShootThis(e)=23    ;type of bullet from weapon
    zPushedForce(e)=shotPushForce(n)
    xObjHand(n)=10

Case 11  ; ray GUN
    objXspeed(n)=5
    objSize(n)=20
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=12
    objHeight(n)=15
    objSide(n)=objSize(n)/2
    objImpact(n)=15
    BeatIten(n)=1
    If e=0 Then ShotsFired(n)=0
    If e=0 Then objAmmo(n)=4
    If e=0 Then objLife(n)=6
    shotFireSound(e)=raySnd
    shotPushForce(n)=.5
    BeatItenType(n)=12
    objHitSound(n)=highpunchSnd
    objPic(n,1)=ePic(4,1)
    objPic_(n,1)=ePic_(4,1)
    zCurWeapon(e)=4        ;type # for weapon drawing
    zShootThis(e)=25    ;type of bullet from weapon
    zPushedForce(e)=shotPushForce(n)
    xObjHand(n)=12 ;: yObjHand(n)=-8

Case 12    ;Ray ball
    objXspeed(n)=4
    objSize(n)=12
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=5
    objHeight(n)=12
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=2
    objHitMode(n)=2
    objHitXspeed(n)=4
    objHitYspeed(n)=3
    objFallTime(n)=40
    If e=0 Then objLife(n)=2
    BeatIten(n)=0
    objTrailType(n)=4
    objHitSound(n)=shockSnd
    objPic(n,1)=shotImage(30)
    objPic_(n,1)=shotImage(30)
    objPic(n,2)=shotImage(31)
    objPic_(n,2)=shotImage(31)
    objFrameTime(n)=3
    objFrameAmount(n)=2
    objHitChunk(n)=30

Case 13    ;Hammer
    objXspeed(n)=4
    objSize(n)=12
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=8
    objHeight(n)=12
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=2
    objHitMode(n)=0
    objHitXspeed(n)=4
    objHitYspeed(n)=3
    objFallTime(n)=40
    If e=0 Then objLife(n)=20
    objHitSound(n)=marioFierceSnd
    objZmade(n)=1

    objPic(n,1)=ePic(5,1)
    objPic_(n,1)=ePic_(5,1)
    objPic(n,2)=ePic(5,2)
    objPic_(n,2)=ePic_(5,2)
    objPic(n,3)=ePic(5,3)
    objPic_(n,3)=ePic_(5,3)
    objPic(n,4)=ePic(5,4)
    objPic_(n,4)=ePic_(5,4)

    objPic(n,5)=ePic_(5,4)
    objPic_(n,5)=ePic(5,4)
    objPic(n,6)=ePic_(5,3)
    objPic_(n,6)=ePic(5,3)
    objPic(n,7)=ePic_(5,2)
    objPic_(n,7)=ePic(5,2)
    objPic(n,8)=ePic_(5,1)
    objPic_(n,8)=ePic(5,1)

    objFrameTime(n)=3
    objFrameAmount(n)=8
    objHitChunk(n)=10

Case 14    ;dragon fire ball
    objXspeed(n)=4
    objSize(n)=22
    objYSpeed(n)=1
    objYForce(n)=-5
    objdamage(n)=5
    objHeight(n)=16
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=2
    objHitMode(n)=0
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objNoGrav(n)=1
    objTrailType(n)=1
    objExplosionSound(n)=fireHitSnd
    objHitSound(n)=fireHitSnd
    objPic(n,1)=ePic(6,1)
    objPic_(n,1)=ePic_(6,1)
    objPic(n,2)=ePic(6,2)
    objPic_(n,2)=ePic_(6,2)
    objFrameTime(n)=3
    objFrameAmount(n)=2
    objHitChunk(n)=7

Case 15    ;cannon ball
    objXspeed(n)=2.5
    objSize(n)=12
    objYSpeed(n)=-2
    objYForce(n)=-3
    objdamage(n)=8
    objHeight(n)=12
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=3
    objHitMode(n)=0
    objHitXspeed(n)=4
    objHitYspeed(n)=2
    objFallTime(n)=30
    If e=0 Then objLife(n)=20
    objExplosionSound(n)=marioFierceSnd
    objHitSound(n)=marioFierceSnd
    objPic(n,1)=shotImage(33)
    objPic_(n,1)=shotImage(33)
    objHitChunk(n)=20

Case 16    ;Flying bat
    objXspeed(n)=4
    objSize(n)=16
    objYSpeed(n)=0
    objYForce(n)=-5
    objdamage(n)=10
    objHeight(n)=16
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=2
    objHitMode(n)=2
    objHitXspeed(n)=2
    objHitYspeed(n)=2
    objFallTime(n)=40
    If e=0 Then objLife(n)=20
    BeatIten(n)=0
    objNoGrav(n)=1
    objExplosionSound(n)=marioFierceSnd
    objHitSound(n)=marioFierceSnd
    objPic(n,1)=ePic(7,1)
    objPic_(n,1)=ePic_(7,1)
    objPic(n,2)=ePic(7,2)
    objPic_(n,2)=ePic_(7,2)
    objFrameTime(n)=4
    objFrameAmount(n)=2
    objHitChunk(n)=20
    objSuper(n)=1

Case 17    ;big rock
    objXspeed(n)=4
    objSize(n)=42
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=12
    objHeight(n)=42
    objSide(n)=objSize(n)/2
    objImpact(n)=25
    objExplosive(n)=3
    objHitMode(n)=0
    If e=0 Then objLife(n)=20
    objHitSound(n)=marioFierceSnd
    objExplosionSound(n)=fireHitSnd

    objPic(n,1)=ePic(8,1)
    objPic_(n,1)=ePic(8,1)
    objPic(n,2)=ePic(8,2)
    objPic_(n,2)=ePic(8,2)
    objPic(n,3)=ePic(8,3)
    objPic_(n,3)=ePic(8,3)
    objPic(n,4)=ePic(8,4)
    objPic_(n,4)=ePic(8,4)

    objFrameTime(n)=8
    objFrameAmount(n)=4
    objHitChunk(n)=34
    objSuper(n)=1

Case 18    ;little rock
    objXspeed(n)=4
    objSize(n)=16
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=8
    objHeight(n)=16
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=3
    objHitMode(n)=0
    If e=0 Then objLife(n)=20
    objHitSound(n)=marioFierceSnd
    objExplosionSound(n)=fireHitSnd

    objPic(n,1)=ePic(9,1)
    objPic_(n,1)=ePic(9,1)
    objPic(n,2)=ePic(9,2)
    objPic_(n,2)=ePic(9,2)
    objPic(n,3)=ePic(9,3)
    objPic_(n,3)=ePic(9,3)
    objPic(n,4)=ePic(9,4)
    objPic_(n,4)=ePic(9,4)

    objFrameTime(n)=8
    objFrameAmount(n)=4
    objHitChunk(n)=35


Case 19    ;little lava rock
    objXspeed(n)=4
    objSize(n)=16
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=8
    objHeight(n)=16
    objSide(n)=objSize(n)/2
    objImpact(n)=20
    objExplosive(n)=3
    objHitMode(n)=0
    If e=0 Then objLife(n)=20
    objHitSound(n)=fireHitSnd
    objExplosionSound(n)=NoSnd

    objPic(n,1)=ePic(10,1)
    objPic_(n,1)=ePic(10,1)
    objPic(n,2)=ePic(10,2)
    objPic_(n,2)=ePic(10,2)
    objPic(n,3)=ePic(10,3)
    objPic_(n,3)=ePic(10,3)
    objPic(n,4)=ePic(10,4)
    objPic_(n,4)=ePic(10,4)

    objFrameTime(n)=8
    objFrameAmount(n)=4
    objHitChunk(n)=7

Case 20    ;axe
    objXspeed(n)=2
    objSize(n)=14
    objYSpeed(n)=-1
    objYForce(n)=-5
    objdamage(n)=5
    objHeight(n)=26
    objSide(n)=objSize(n)/2
    objImpact(n)=12
    objExplosive(n)=3
    objHitMode(n)=2
    objHitXspeed(n)=3
    objHitYspeed(n)=2
    objFallTime(n)=40
    objHitSound(n)=mikePunchSnd
    objExplosionSound(n)=noSnd

    objPic(n,1) = ePic(11,1)
    objPic_(n,1)= ePic_(11,1)
    objPic(n,2) = ePic(11,2)
    objPic_(n,2)= ePic_(11,2)
    objPic(n,3) = ePic(11,3)
    objPic_(n,3)= ePic_(11,3)
    objPic(n,4) = ePic(11,4)
    objPic_(n,4)= ePic_(11,4)
    objPic(n,5) = ePic(11,5)
    objPic_(n,5)= ePic_(11,5)
    objPic(n,6) = ePic(11,6)
    objPic_(n,6)= ePic_(11,6)
    objPic(n,7) = ePic(11,7)
    objPic_(n,7)= ePic_(11,7)
    objPic(n,8) = ePic(11,8)
    objPic_(n,8)= ePic_(11,8)

    objFrameTime(n)=3
    objFrameAmount(n)=8
    objHitChunk(n)=10

End Select

End Function
;---------------------- Object explodes --------------------------------
Function objExp(n,x,y,kind)

Select kind
Case 1    ;obj explosion
    makeExp(n,x,y,kind)
    makeChunk(n,x,y,2,4)
Case 2    ;alien spit
    makeChunk(n,x,y,2,objHitChunk(n))
Case 3    ;explosion = object hit chunk
    If gamesound And inSight(x,y) Then PlaySound objExplosionSound(n)
    makeChunk(n,x,y,2,objHitChunk(n))
Case 4    ;helpers
    found=0
    For i=5 To 30
        If zon(i)=0 Then
            nn=i : found=1
            If nn > zzamount Then zzamount=nn
        EndIf
        If found=1 Then Exit
    Next
    makeChunk(nn,zx(nn),zy(nn),2,10)
    .tryOtherHelper
    sn=Rand(1,4)
    ;sn=4    ;test
    If sn=1 Then    ; laser turret
        curGuy(nn)=43
        initZ(nn)
        zon(nn)=1
        zx(nn)=x
        zy(nn)=y-15
        zFace(nn)=4 ;facDir(n,curF(n))
        zLives(nn)=0
        AiLevel(nn)=1    ;makes it last only for a while (see moves2.bb)
        zTeam(nn)=zTeam(objOwner(n))
        zDeadEvent(nn)=0

        If gamesound Then PlaySound clickSnd
        ztempShield(nn)=0
        zTrail(nn)=0
        aiGetTarget(nn)
    EndIf
    If sn=2 And noAirStrike=0 Then    ; bombing ship (air strike)
        If gameMode=2 Or vsMode=0 Then Goto tryOtherHelper
        curGuy(nn)=45
        initZ(nn)
        zCurPic(nn)=zPic(CurGuy(nn),1,0)
        zon(nn)=1
        zx(nn)=xScr+710
        zy(nn)=yScr+100
        zFace(nn)=4 ;facDir(n,curF(n))
        zLives(nn)=0
        AiLevel(nn)=5
        zTeam(nn)=zTeam(objOwner(n))
        zDeadEvent(nn)=0
        ztempShield(nn)=0
        zTrail(nn)=0
    EndIf
    If sn=3 Then    ; ray balls cannon
        curGuy(nn)=46
        initZ(nn)
        zCurPic(nn)=zPic(CurGuy(nn),1,0)
        zon(nn)=1
        zx(nn)=x
        zy(nn)=y-15
        zFace(nn)=4 ;facDir(n,curF(n))
        zLives(nn)=0
        AiLevel(nn)=5
        zTeam(nn)=zTeam(objOwner(n))
        zDeadEvent(nn)=0

        If gamesound Then PlaySound clickSnd
        ztempShield(nn)=0
        zTrail(nn)=0
    EndIf
    If sn=4 Then ;bunch of flying bats
        If gameMode=2 Or vsMode=0 Then Goto tryOtherHelper
        If gameSound Then PlaySound batsSnd
        makeChunk(nn,zx(nn),zy(nn),2,5)
        For i=1 To 10
            xx=xx+50
            oo = getObjAway(objOwner(n),16)

            objHitXspeed(oo)=0
            objHitYspeed(oo)=4
            objFallTime(oo)=80

            objTaken(oo)=0:objHurt(oo)=1
            objThrow(oo)=1:
            objdir(oo)=4
            xobj(oo)=xScr+(580+xx)
            yobj(oo)=y-(Rand(2,150))


        Next
    EndIf

End Select

End Function
;----------------------------- Explosion data------------------------------------
Function expData(n)

explosionSound(n)=explodesnd
Select expType(n)
Case 1
    expDamage(n)=40
    expHeight(n)=64
    expSide(n)=32
    expImpact(n)=80
    explodeChunkType(n)=7

Case 2
    expDamage(n)=shotExplosiveDamage(n)
    expHeight(n)=shotExplosiveHeight(n)
    expSide(n)=shotExplosiveSide(n)
    expImpact(n)=shotExpImpact(n)
    explodeChunkType(n)=shotExplodeChunk(n)
    
Case 3
    expDamage(n)=20
    expHeight(n)=64
    expSide(n)=32
    expImpact(n)=80
    explosionSound(n)=ktKartExplodeSnd
    explodeChunkType(n)=76
    
End Select

End Function
;------------------Load sprites ------------------------------------
Function loadPics(n)
local loadTimeStart, loadTimeEnd
loadTimeStart=MilliSecs()

gfxdir$="gfx\" + n + "\"
guyLoaded(n)=1

zpic(n,20,1)=LoadImage(gfxdir$ + "zSuperPic.bmp")
zpic(n,20,2)=LoadImage(gfxdir$ + "zSuperPic2.bmp")
zpic_(n,20,1)=LoadImage(gfxdir$ + "zSuperPic_.bmp")
zpic_(n,20,2)=LoadImage(gfxdir$ + "zSuperPic2_.bmp")
zpic(n,0,0)=LoadImage(gfxStuffDir$ + "frozen.bmp")
zpic_(n,0,0)=LoadImage(gfxStuffDir$ + "frozen_.bmp")
zpic(n,0,1)=LoadImage(gfxStuffDir$ + "frozen_small.bmp")
zpic_(n,0,1)=LoadImage(gfxStuffDir$ + "frozen_small_.bmp")
zpic(n,0,2)=LoadImage(gfxdir$ + "zfrozen.bmp")
zpic_(n,0,2)=LoadImage(gfxdir$ + "zfrozen_.bmp")

For i=0 To 30
    zpic(n,1,i)=LoadImage(gfxdir$ + "walk/zwalk" + i + ".bmp")
    zpic_(n,1,i)=LoadImage(gfxdir$ + "walk/zwalk" + i + "_.bmp")
Next

zpic(n,2,0)=LoadImage(gfxdir$ + "zfallen.bmp")
zpic_(n,2,0)=LoadImage(gfxdir$ + "zfallen_.bmp")

For i=1 To 16
    zpic(n,2,i)=LoadImage(gfxdir$ + "zfalling" + i + ".bmp")
    zpic_(n,2,i)=LoadImage(gfxdir$ + "zfalling" + i + "_.bmp")
Next

zpic(n,3,1)=LoadImage(gfxdir$ + "zduck.bmp")
zpic_(n,3,1)=LoadImage(gfxdir$ + "zduck_.bmp")

For i=2 To 10
    zpic(n,3,i)=LoadImage(gfxdir$ + "zduck" + i + ".bmp")
    zpic_(n,3,i)=LoadImage(gfxdir$ + "zduck" + i + "_.bmp")
Next

zpic(n,4,1)=LoadImage(gfxdir$ + "zair.bmp")
zPic_(n,4,1)=LoadImage(gfxdir$ + "zair_.bmp")

For i=2 To 20
    zpic(n,4,i)=LoadImage(gfxdir$ + "air/zair" + i + ".bmp")
    zPic_(n,4,i)=LoadImage(gfxdir$ + "air/zair" + i + "_.bmp")
Next

For i=1 To 15
    zpic(n,5,i)=LoadImage(gfxdir$ + "zFlip" + i + ".bmp")
    zpic_(n,5,i)=LoadImage(gfxdir$ + "zFlip" + i + "_.bmp")
Next

For i=1 To 20
    zpic(n,6,i)=LoadImage(gfxdir$ + "zblow" + i + ".bmp")
    zpic_(n,6,i)=LoadImage(gfxdir$ + "zblow" + i + "_.bmp")
Next

For i=1 To 20
    zpic(n,7,i)=LoadImage(gfxdir$ + "upspecial/zuspecial" + i + ".bmp")
    zpic_(n,7,i)=LoadImage(gfxdir$ + "upspecial/zuspecial" + i + "_.bmp")
Next

For i=1 To 20
    zpic(n,8,i)=LoadImage(gfxdir$ + "zflykick" + i + ".bmp")
    zpic_(n,8,i)=LoadImage(gfxdir$ + "zflykick" + i + "_.bmp")
Next

For i=1 To 30
    zpic(n,9,i)=LoadImage(gfxdir$ + "zlowkick" + i + ".bmp")
    zpic_(n,9,i)=LoadImage(gfxdir$ + "zlowkick" + i + "_.bmp")
Next

For i=1 To 50
    zpic(n,10,i)=LoadImage(gfxdir$ + "special/zspecial" + i + ".bmp")
    zpic_(n,10,i)=LoadImage(gfxdir$ + "special/zspecial" + i + "_.bmp")
Next

For i=1 To 2
    zpic(n,11,i)=LoadImage(gfxdir$ + "zshot" + i + ".bmp")
    zpic_(n,11,i)=LoadImage(gfxdir$ + "zshot" + i + "_.bmp")
Next

For i=1 To 21
    zpic(n,12,i)=LoadImage(gfxdir$ + "downspecial/zDspecial" + i + ".bmp")
    zpic_(n,12,i)=LoadImage(gfxdir$ + "downspecial/zDspecial" + i + "_.bmp")
Next

For i=1 To 3
    zpic(n,13,i)=LoadImage(gfxdir$ + "zblock" + i + ".bmp")
    zpic_(n,13,i)=LoadImage(gfxdir$ + "zblock" + i + "_.bmp")
Next

For i = 1 To 16
    zpic(n,14,i)=LoadImage(gfxdir$ + "zupblow" + i + ".bmp")
    zpic_(n,14,i)=LoadImage(gfxdir$ + "zupblow" + i + "_.bmp")
Next

For i = 1 To 40
    zpic(n,15,i)=LoadImage(gfxdir$ + "zgrab" + i + ".bmp")
    zpic_(n,15,i)=LoadImage(gfxdir$ + "zgrab" + i + "_.bmp")
Next

For i = 1 To 25
    zpic(n,16,i)=LoadImage(gfxdir$ + "taunt\zTaunt" + i + ".bmp")
    zpic_(n,16,i)=LoadImage(gfxdir$ + "taunt\zTaunt" + i + "_.bmp")
Next

For i = 1 To maxPicFrames
    zpic(n,17,i)=LoadImage(gfxdir$ + "superspecial\zsuper" + i + ".bmp")
    zpic_(n,17,i)=LoadImage(gfxdir$ + "superspecial\zsuper" + i + "_.bmp")
Next

For i = 1 To 40
    zpic(n,18,i)=LoadImage(gfxdir$ + "extraspecial\zextraspecial" + i + ".bmp")
    zpic_(n,18,i)=LoadImage(gfxdir$ + "extraspecial\zextraspecial" + i + "_.bmp")
Next

For i = 1 To 35
    zpic(n,19,i)=LoadImage(gfxdir$ + "stance\zStance_a" + i + ".bmp")
    zpic_(n,19,i)=LoadImage(gfxdir$ + "stance\zStance_a" + i + "_.bmp")
    zpic(n,27,i)=LoadImage(gfxdir$ + "stance\zStance_b" + i + ".bmp")
    zpic_(n,27,i)=LoadImage(gfxdir$ + "stance\zStance_b" + i + "_.bmp")
Next

For i = 1 To 20
    zpic(n,21,i)=LoadImage(gfxdir$ + "run\zRun" + i + ".bmp")
    zpic_(n,21,i)=LoadImage(gfxdir$ + "run\zRun" + i + "_.bmp")
Next

For i = 1 To 20
    zpic(n,22,i)=LoadImage(gfxdir$ + "combo\zCombo" + i + ".bmp")
    zpic_(n,22,i)=LoadImage(gfxdir$ + "combo\zCombo" + i + "_.bmp")
Next

For i = 1 To 15
    zpic(n,23,i)=LoadImage(gfxdir$ + "dizzy\zDizzy" + i + ".bmp")
    zpic_(n,23,i)=LoadImage(gfxdir$ + "dizzy\zDizzy" + i + "_.bmp")
Next

For i = 1 To 4
    zpic(n,24,i)=LoadImage(gfxdir$ + "electrocute\zElectrocute" + i + ".bmp")
    zpic_(n,24,i)=LoadImage(gfxdir$ + "electrocute\zElectrocute" + i + "_.bmp")
Next

For i = 1 To 5
    zpic(n,25,i)=LoadImage(gfxdir$ + "fallBounce\zFallBounce" + i + ".bmp")
    zpic_(n,25,i)=LoadImage(gfxdir$ + "fallBounce\zFallBounce" + i + "_.bmp")
Next

For i = 1 To 50
    zpic(n,26,i)=LoadImage(gfxdir$ + "extras\zExtra" + i + ".bmp")
    zpic_(n,26,i)=LoadImage(gfxdir$ + "extras\zExtra" + i + "_.bmp")
Next

For i = 1 To 30
    zpic(n,28,i)=LoadImage(gfxdir$ + "combo\zCombo" + i + ".bmp")
    zpic_(n,28,i)=LoadImage(gfxdir$ + "combo\zCombo" + i + "_.bmp")
Next

For j=0 To 50
    For i=0 To maxPicFrames
        If zpic(n,j,i) <> 0 And imgScaleFactor#(n) <> 0 And imgScaleFactor#(n) <> 1 Then
            ScaleImage zpic(n,j,i),imgScaleFactor#(n),imgScaleFactor#(n)
            ScaleImage zpic_(n,j,i),imgScaleFactor#(n),imgScaleFactor#(n)
        End If
    Next
Next


;Hyper Bgs and Cooldown Pics
If n <= maxZ
    For i=1 To maxHyperBg
        If hyperBgPic(n,i)=0 Then hyperBgPic(n,i)=LoadImage("gfx\stuff\hyperbg\hyperBg" + n + "_" + i + ".bmp")
    Next

    For i=1 To 4
        cooldownPic(n, i)=LoadImage("gfx\stuff\cooldown\cd" + n + "_" + i + ".bmp")
    Next
End If

;add character (stuff that must be loaded the first time, such as sounds. Don't worry about the pics)

If n=44 Then    ;Venom
    For i=0 To 7
     zpic(n,2,i)=zpic(n,1,0)
     zpic_(n,2,i)=zpic_(n,1,0)
    Next
    For i=2 To 4
     zpic(n,5,i)=zpic(n,5,1)
     zpic_(n,5,i)=zpic_(n,5,1)
    Next
EndIf

If n=51 Then    ;Dark ninja
    If swordSnd=0 Then swordSnd=LoadSound(soundsdir$ + "sword.wav")
    If hayabusaSnd=0 Then hayabusaSnd=LoadSound(soundsdir$ + "hayabusa.wav")
    If mvcJump4Snd=0 Then mvcJump4Snd=LoadSound(soundsdir$ + "mvc\mvcJump4Snd.wav")
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "hanzo\hanzoFootSnd.wav")
    If hanzoRunEndSnd=0 Then hanzoRunEndSnd=LoadSound(soundsdir$ + "hanzo\hanzoRunEndSnd.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "hanzo\hanzoDieSnd.wav")
    If wwBlockedSnd=0 Then wwBlockedSnd=LoadSound(soundsdir$ + "wonderwoman\wwBlockedSnd.wav")
    If hanzoGrunt1Snd=0 Then hanzoGrunt1Snd=LoadSound(soundsdir$ + "hanzo\hanzoGrunt1Snd.wav")
    If hanzoGrunt2Snd=0 Then hanzoGrunt2Snd=LoadSound(soundsdir$ + "hanzo\hanzoGrunt2Snd.wav")
    If hanzoGrunt3Snd=0 Then hanzoGrunt3Snd=LoadSound(soundsdir$ + "hanzo\hanzoGrunt3Snd.wav")
    If hanzoGrunt4Snd=0 Then hanzoGrunt4Snd=LoadSound(soundsdir$ + "hanzo\hanzoGrunt4Snd.wav")
    If hanzoGrunt5Snd=0 Then hanzoGrunt5Snd=LoadSound(soundsdir$ + "hanzo\hanzoGrunt5Snd.wav")
    If hanzoBlow1Snd=0 Then hanzoBlow1Snd=LoadSound(soundsdir$ + "hanzo\hanzoBlow1Snd.wav")
    If hanzoHit1Snd=0 Then hanzoHit1Snd=LoadSound(soundsdir$ + "hanzo\hanzoHit1Snd.wav")
    If hanzoSword1Snd=0 Then hanzoSword1Snd=LoadSound(soundsdir$ + "hanzo\hanzoSword1Snd.wav")
EndIf

If n=49 Then    ;Dragon
    zpic(n,4,1)=zpic(n,1,0)
    zpic_(n,4,1)=zpic_(n,1,0)
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=0 To 7
    zpic(n,2,i)=zpic(n,1,0)
    zpic_(n,2,i)=zpic_(n,1,0)
    Next
    For i=1 To 4
    zpic(n,5,i)=zpic(n,1,0)
    zpic_(n,5,i)=zpic_(n,1,0)
    Next
    If dragonRoarSnd=0 Then dragonRoarSnd=LoadSound(soundsdir$ + "dragonRoar.wav")
EndIf

If n=47 Then    ;soldier
    zpic(n,4,1)=zpic(n,1,0)
    zpic_(n,4,1)=zpic_(n,1,0)
    For i=1 To 3
     zpic(n,1,i)=zpic(n,1,0)
     zpic_(n,1,i)=zpic_(n,1,0)
    Next
    For i=1 To 4
     zpic(n,5,i)=zpic(n,1,0)
     zpic_(n,5,i)=zpic_(n,1,0)
    Next
EndIf

;laser helper / bombing ship / ray balls / cylinder / Laser Beam / punching bag
If n=43 Or n=45 Or n=46 Or n= 48 Or n=50 Or n=52 Then
    zpic(n,1,1)=zpic(n,1,0)
    zpic_(n,1,1)=zpic_(n,1,0)
    zpic(n,1,2)=zpic(n,1,0)
    zpic_(n,1,2)=zpic_(n,1,0)
    zpic(n,1,3)=zpic(n,1,0)
    zpic_(n,1,3)=zpic_(n,1,0)
    zpic(n,4,1)=zpic(n,1,0)
    zpic_(n,4,1)=zpic_(n,1,0)
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=0 To 7
    zpic(n,2,i)=zpic(n,1,0)
    zpic_(n,2,i)=zpic_(n,1,0)
    Next
    For i=1 To 4
    zpic(n,5,i)=zpic(n,1,0)
    zpic_(n,5,i)=zpic_(n,1,0)
    Next

EndIf

If n=42 Then    ;Joker
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=1 To 4
        zpic(n,5,i)=zpic(n,1,2)
        zpic_(n,5,i)=zpic_(n,1,2)
    Next
    If jokerSnd=0 Then jokerSnd=LoadSound(soundsdir$ + "joker.wav")
EndIf

If n=40 Then ;Turtle
    If ktKickSnd=0 Then ktKickSnd=LoadSound(soundsDir$ + "koopaTroopa\ktKick.wav")
    If ktGrunt1Snd=0 Then ktGrunt1Snd=LoadSound(soundsDir$ + "koopaTroopa\ktGrunt1.wav")
    If ktGrunt2Snd=0 Then ktGrunt2Snd=LoadSound(soundsDir$ + "koopaTroopa\ktGrunt2.wav")
    If ktGrunt3Snd=0 Then ktGrunt3Snd=LoadSound(soundsDir$ + "koopaTroopa\ktGrunt3.wav")
    If ktGrunt4Snd=0 Then ktGrunt4Snd=LoadSound(soundsDir$ + "koopaTroopa\ktGrunt4.wav")
    If ktSpinSnd=0 Then ktSpinSnd=LoadSound(soundsDir$ + "koopaTroopa\ktSpin.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsDir$ + "koopaTroopa\ktDie.wav")
    If mvcJump1Snd=0 Then mvcJump1Snd=LoadSound(soundsdir$ + "mvc\mvcJmp1Snd.wav")
    If mvcJump2Snd=0 Then mvcJump2Snd=LoadSound(soundsdir$ + "mvc\mvcJmp2Snd.wav")
    If ktOneMoreTimeSnd=0 Then ktOneMoreTimeSnd=LoadSound(soundsdir$ + "koopaTroopa\ktOneMoreTime.wav")
    If mvcSuper1Snd=0 Then mvcSuper1Snd=LoadSound(soundsdir$ + "mvc\mvcSuper1Snd.wav")
    If ktKartScreechSnd=0 Then ktKartScreechSnd=LoadSound(soundsdir$ + "koopaTroopa\ktKartScreech.wav")
    If ktKartExplodeSnd=0 Then ktKartExplodeSnd=LoadSound(soundsdir$ + "koopaTroopa\ktKartExplode.wav")
    If turtleSlideSnd=0 Then turtleSlideSnd=LoadSound(soundsdir$ + "koopaTroopa\turtleSlide.wav")
End If

If n=16 Then ;Piccolo
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsDir$ + "piccolo\piccoloDieSnd.wav")
    If piccoloTaunt1Snd=0 Then piccoloTaunt1Snd=LoadSound(soundsDir$ + "piccolo\piccoloTaunt1Snd.wav")
    If piccoloTaunt2Snd=0 Then piccoloTaunt2Snd=LoadSound(soundsDir$ + "piccolo\piccoloTaunt2Snd.wav")
    If piccoloTaunt3Snd=0 Then piccoloTaunt3Snd=LoadSound(soundsDir$ + "piccolo\piccoloTaunt3Snd.wav")
    If piccoloGrunt1Snd=0 Then piccoloGrunt1Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt1Snd.wav")
    If piccoloGrunt2Snd=0 Then piccoloGrunt2Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt2Snd.wav")
    If piccoloGrunt3Snd=0 Then piccoloGrunt3Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt3Snd.wav")
    If piccoloGrunt4Snd=0 Then piccoloGrunt4Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt4Snd.wav")
    If piccoloGrunt5Snd=0 Then piccoloGrunt5Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt5Snd.wav")
    If piccoloGrunt6Snd=0 Then piccoloGrunt6Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt6Snd.wav")
    If piccoloGrunt7Snd=0 Then piccoloGrunt7Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt7Snd.wav")
    If piccoloGrunt8Snd=0 Then piccoloGrunt8Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt8Snd.wav")
    If piccoloGrunt9Snd=0 Then piccoloGrunt9Snd=LoadSound(soundsDir$ + "piccolo\piccoloGrunt9Snd.wav")
    If piccoloKaikousenSnd=0 Then piccoloKaikousenSnd=LoadSound(soundsDir$ + "piccolo\piccoloKaikousenSnd.wav")
    If piccoloKiSnd=0 Then piccoloKiSnd=LoadSound(soundsDir$ + "piccolo\piccoloKiSnd.wav")
    If piccoloUpSpecialBlowSnd=0 Then piccoloUpSpecialBlowSnd=LoadSound(soundsDir$ + "piccolo\piccoloUpSpecialBlowSnd.wav")
    If piccoloUpSpecialSnd=0 Then piccoloUpSpecialSnd=LoadSound(soundsDir$ + "piccolo\piccoloUpSpecialSnd.wav")
    If dbzKneeHitSnd=0 Then dbzKneeHitSnd=LoadSound(soundsDir$ + "dbz\dbzKneeHitSnd.wav")
    If piccoloFollowUpHitSnd=0 Then piccoloFollowUpHitSnd=LoadSound(soundsDir$ + "piccolo\piccoloFollowUpHitSnd.wav")
    If piccoloBuukuKyakuSnd=0 Then piccoloBuukuKyakuSnd=LoadSound(soundsDir$ + "piccolo\piccoloBuukuKyakuSnd.wav")
    If dbzSuperKickSnd=0 Then dbzSuperKickSnd=LoadSound(soundsDir$ + "dbz\dbzSuperKickSnd.wav")
    If dbzChargeSnd=0 Then dbzChargeSnd=LoadSound(soundsDir$ + "dbz\dbzChargeSnd.wav")
    If dbzAuraSnd=0 Then dbzAuraSnd=LoadSound(soundsDir$ + "dbz\dbzAuraSnd.wav")
    If dbzHyperSnd=0 Then dbzHyperSnd=LoadSound(soundsDir$ + "dbz\dbzHyperSnd.wav")
    If dbzPierceSnd=0 Then dbzPierceSnd=LoadSound(soundsDir$ + "dbz\dbzPierceSnd.wav")
    If dbzKiHyperAttackSnd=0 Then dbzKiHyperAttackSnd=LoadSound(soundsDir$ + "dbz\dbzKiHyperAttackSnd.wav")
    If dbzSuperSpeedSnd=0 Then dbzSuperSpeedSnd=LoadSound(soundsDir$ + "dbz\dbzSuperSpeedSnd.wav")
    If dbzTeleSnd=0 Then dbzTeleSnd=LoadSound(soundsDir$ + "dbz\dbzTeleSnd.wav")
    If piccoloKiCtrlSnd=0 Then piccoloKiCtrlSnd=LoadSound(soundsDir$ + "piccolo\piccoloKiCtrlSnd.wav")
    If piccoloKutabare1Snd=0 Then piccoloKutabare1Snd=LoadSound(soundsDir$ + "piccolo\piccoloKutabare1Snd.wav")
    If piccoloKutabare2Snd=0 Then piccoloKutabare2Snd=LoadSound(soundsDir$ + "piccolo\piccoloKutabare2Snd.wav")
    If piccoloMakuuSnd=0 Then piccoloMakuuSnd=LoadSound(soundsDir$ + "piccolo\piccoloMakuuSnd.wav")
    If piccoloMakkankousappouSnd=0 Then piccoloMakkankousappouSnd=LoadSound(soundsDir$ + "piccolo\piccoloMakkankousappouSnd.wav")
    If piccoloGohanSnd=0 Then piccoloGohanSnd=LoadSound(soundsDir$ + "piccolo\piccoloGohanSnd.wav")
    If piccoloGohanHaSnd=0 Then piccoloGohanHaSnd=LoadSound(soundsDir$ + "piccolo\piccoloGohanHaSnd.wav")
    If piccoloGohanYahaSnd=0 Then piccoloGohanYahaSnd=LoadSound(soundsDir$ + "piccolo\piccoloGohanYahaSnd.wav")
    If piccoloCooldown1Snd=0 Then piccoloCooldown1Snd=LoadSound(soundsDir$ + "piccolo\piccoloCooldown1Snd.mp3")
    If piccoloCooldown2Snd=0 Then piccoloCooldown2Snd=LoadSound(soundsDir$ + "piccolo\piccoloCooldown2Snd.mp3")
End If

If n=15 Then ;Juggernaut
    If walkQuakeSnd(n)=0 Then walkQuakeSnd(n)=LoadSound(soundsdir$ + "juggernaut\juggWalk.wav")
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "juggernaut\juggDash.wav")
    If juggJumpSnd=0 Then juggJumpSnd=LoadSound(soundsdir$ + "juggernaut\juggJump.wav")
    If juggLateralSnd=0 Then juggLateralSnd=LoadSound(soundsdir$ + "juggernaut\juggLateral.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "juggernaut\juggDie.wav")
    If juggLateralHitSnd=0 Then juggLateralHitSnd=LoadSound(soundsdir$ + "juggernaut\juggLateralHit.wav")
    If juggPunchSnd=0 Then juggPunchSnd=LoadSound(soundsdir$ + "juggernaut\juggPunch.wav")
    If juggPunchGroundSnd=0 Then juggPunchGroundSnd=LoadSound(soundsdir$ + "juggernaut\juggPunchGround.wav")
    If juggNoManaSnd=0 Then juggNoManaSnd=LoadSound(soundsdir$ + "juggernaut\juggNoMana.mp3")
    If juggNoMana2Snd=0 Then juggNoMana2Snd=LoadSound(soundsdir$ + "juggernaut\juggNoMana2.mp3")
    If juggEarthquakeSnd=0 Then juggEarthquakeSnd=LoadSound(soundsdir$ + "juggernaut\juggEarthquake.wav")
    If juggTaunt1Snd=0 Then juggTaunt1Snd=LoadSound(soundsdir$ + "juggernaut\juggTaunt1Snd.wav")
    If juggTaunt2Snd=0 Then juggTaunt2Snd=LoadSound(soundsdir$ + "juggernaut\juggTaunt2Snd.wav")
    If juggGrunt1Snd=0 Then juggGrunt1Snd=LoadSound(soundsdir$ + "juggernaut\juggGrunt1Snd.wav")
    If juggGrunt2Snd=0 Then juggGrunt2Snd=LoadSound(soundsdir$ + "juggernaut\juggGrunt2Snd.wav")
    If juggHit1Snd=0 Then juggHit1Snd=LoadSound(soundsdir$ + "juggernaut\juggHit1Snd.wav")
    If juggHit2nd=0 Then juggHit2nd=LoadSound(soundsdir$ + "juggernaut\juggHit2nd.wav")
    If juggHeadCrushGruntSnd=0 Then juggHeadCrushGruntSnd=LoadSound(soundsdir$ + "juggernaut\juggHeadCrushGruntSnd.wav")
    If juggHeadCrushSnd=0 Then juggHeadCrushSnd=LoadSound(soundsdir$ + "juggernaut\juggHeadCrushSnd.wav")
    If mvcSuper1Snd=0 Then mvcSuper1Snd=LoadSound(soundsdir$ + "mvc\mvcSuper1Snd.wav")
    If wolverineGrabSnd=0 Then wolverineGrabSnd=LoadSound(soundsdir$ + "wolverine\wolverineGrab.wav")
End If

If n=14 Then ;WonderWoman
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "wonderwoman\wwRun.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "wonderwoman\wwDie.wav")
    If wonderwomanWH1Snd=0 Then wonderwomanWH1Snd=LoadSound(soundsdir$ + "wonderwoman\wonderwomanWH1.wav")
    If wonderwomanWH2Snd=0 Then wonderwomanWH2Snd=LoadSound(soundsdir$ + "wonderwoman\wonderwomanWH2.wav")
    If wonderwomanWhWaveSnd=0 Then wonderwomanWhWaveSnd=LoadSound(soundsdir$ + "wonderwoman\wonderwomanWhWave.wav")
    If wwAegisSnd=0 Then wwAegisSnd=LoadSound(soundsdir$ + "wonderwoman\wwAegis.wav")
    If wwAegisMetalSnd=0 Then wwAegisMetalSnd=LoadSound(soundsdir$ + "wonderwoman\wwAegisMetal.wav")
    If wwAegisHitSnd=0 Then wwAegisHitSnd=LoadSound(soundsdir$ + "wonderwoman\wwAegisHit.wav")
    If wwTaunt1Snd=0 Then wwTaunt1Snd=LoadSound(soundsdir$ + "wonderwoman\wwTaunt1.wav")
    If wwCapeSnd=0 Then wwCapeSnd=LoadSound(soundsdir$ + "wonderwoman\wwCape.wav")
    If wwShout1Snd=0 Then wwShout1Snd=LoadSound(soundsdir$ + "wonderwoman\wwShout1.wav")
    If wwShout2Snd=0 Then wwShout2Snd=LoadSound(soundsdir$ + "wonderwoman\wwShout2.wav")
    If wwShout3Snd=0 Then wwShout3Snd=LoadSound(soundsdir$ + "wonderwoman\wwShout3.wav")
    If wwShout4Snd=0 Then wwShout4Snd=LoadSound(soundsdir$ + "wonderwoman\wwShout4.wav")
    If wwShout5Snd=0 Then wwShout5Snd=LoadSound(soundsdir$ + "wonderwoman\wwShout5.wav")
    If wwShout6Snd=0 Then wwShout6Snd=LoadSound(soundsdir$ + "wonderwoman\wwShout6.wav")
    If wwGrunt1Snd=0 Then wwGrunt1Snd=LoadSound(soundsdir$ + "wonderwoman\wwGrunt1.wav")
    If wwWhip1Snd=0 Then wwWhip1Snd=LoadSound(soundsdir$ + "wonderwoman\wwWhip1.wav")
    If wwWhip2Snd=0 Then wwWhip2Snd=LoadSound(soundsdir$ + "wonderwoman\wwWhip2.wav")
    If wwWhipHitSnd=0 Then wwWhipHitSnd=LoadSound(soundsdir$ + "wonderwoman\wwWhipHit.wav")
    If wwLassoSnd=0 Then wwLassoSnd=LoadSound(soundsdir$ + "wonderwoman\wwLasso.wav")
    If wwFierceAmazonSnd=0 Then wwFierceAmazonSnd=LoadSound(soundsdir$ + "wonderwoman\wwFierceAmazon.wav")
    If wwTauntSnd=0 Then wwTauntSnd=LoadSound(soundsdir$ + "wonderwoman\wwTauntSnd.wav")
    If wwThemysciraSnd=0 Then wwThemysciraSnd=LoadSound(soundsdir$ + "wonderwoman\wwThemysciraSnd.wav")
    If wwRun1Snd=0 Then wwRun1Snd=LoadSound(soundsdir$ + "wonderwoman\wwRun.wav")
    If wwRun2Snd=0 Then wwRun2Snd=LoadSound(soundsdir$ + "wonderwoman\wwRun2Snd.wav")
    If wwBlockedSnd=0 Then wwBlockedSnd=LoadSound(soundsdir$ + "wonderwoman\wwBlockedSnd.wav")
    If wwCd1Snd=0 Then wwCd1Snd=LoadSound(soundsdir$ + "wonderwoman\wwCooldown1Snd.mp3")
    If wwCd2Snd=0 Then wwCd2Snd=LoadSound(soundsdir$ + "wonderwoman\wwCooldown2Snd.mp3")
    If wwRageSpinSnd=0 Then wwRageSpinSnd=LoadSound(soundsdir$ + "wonderwoman\wwRageSpinSnd.wav")
    If wwRagingPaladinNoiseSnd=0 Then wwRagingPaladinNoiseSnd=LoadSound(soundsdir$ + "wonderwoman\wwRagingPaladinNoiseSnd.wav")
    If wwRagingPaladinTransformSnd=0 Then wwRagingPaladinTransformSnd=LoadSound(soundsdir$ + "wonderwoman\wwRagingPaladinTransformSnd.wav")
    If wwSeriousTimeSnd=0 Then wwSeriousTimeSnd=LoadSound(soundsdir$ + "wonderwoman\wwSeriousTimeSnd.wav")
    If mvcSuper2Snd=0 Then mvcSuper2Snd=LoadSound(soundsdir$ + "mvc\mvcSuper2Snd.wav")
    If wwBlow1Snd=0 Then wwBlow1Snd=LoadSound(soundsdir$ + "wonderwoman\wwBlow1Snd.wav")
    If wwAxeSnd=0 Then wwAxeSnd=LoadSound(soundsdir$ + "wonderwoman\wwAxeSnd.wav")
    If wwAxePlaceSnd=0 Then wwAxePlaceSnd=LoadSound(soundsdir$ + "wonderwoman\wwAxePlaceSnd.wav")
    If wwRoyalThrustStartSnd=0 Then wwRoyalThrustStartSnd=LoadSound(soundsdir$ + "wonderwoman\wwRoyalThrustStartSnd.wav")
    If wwTiaraThrowSnd=0 Then wwTiaraThrowSnd=LoadSound(soundsdir$ + "wonderwoman\wwTiaraThrowSnd.wav")
    If wwTiaraBackSnd=0 Then wwTiaraBackSnd=LoadSound(soundsdir$ + "wonderwoman\wwTiaraBackSnd.wav")
    If wwTiaraReturnedSnd=0 Then wwTiaraReturnedSnd=LoadSound(soundsdir$ + "wonderwoman\wwTiaraReturnedSnd.wav")
End If

If n=13 Then ;SubZero
    If subZeroAirSnd=0 Then subZeroAirSnd=LoadSound(soundsdir$ + "subzero\subAir.mp3")
    If subZeroFreeze1Snd=0 Then subZeroFreeze1Snd=LoadSound(soundsdir$ + "subzero\subFreeze1.mp3")
    If subZeroFreeze2Snd=0 Then subZeroFreeze2Snd=LoadSound(soundsdir$ + "subzero\subFreeze2.mp3")
    If subZeroFreeze3Snd=0 Then subZeroFreeze3Snd=LoadSound(soundsdir$ + "subzero\subFreeze3.mp3")
    If subZeroIceBlastSnd=0 Then subZeroIceBlastSnd=LoadSound(soundsdir$ + "subzero\subIceBlast.wav")
    If subZeroSlideKickSnd=0 Then subZeroSlideKickSnd=LoadSound(soundsdir$ + "subzero\subSlideKick.mp3")
    If subZeroPunch2Snd=0 Then subZeroPunch2Snd=LoadSound(soundsdir$ + "subzero\subPunch2.wav")
    If subZeroWindSnd=0 Then subZeroWindSnd=LoadSound(soundsdir$ + "subzero\subWind.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "subzero\subDie.mp3")    
    If zRunGruntSound(n)=0 Then zRunGruntSound(n)=LoadSound(soundsdir$ + "mk\mkMaleGrunt1.mp3")
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "mk\mkFootstep.mp3")
    If subZeroCooldown1Snd=0 Then subZeroCooldown1Snd=LoadSound(soundsdir$ + "subzero\subzeroCooldown1.mp3")
    If subZeroCooldown2Snd=0 Then subZeroCooldown2Snd=LoadSound(soundsdir$ + "subzero\subzeroCooldown2.mp3")
    If scorpionSkullSnd=0 Then scorpionSkullSnd=LoadSound(soundsdir$ + "scorpion\scorpionSkull.mp3")
    If mkFriendShipAgainSnd=0 Then mkFriendShipAgainSnd=LoadSound(soundsdir$ + "mk\mkFriendShipAgain.wav")
EndIf

If n=12 Then    ;Scorpion
    If scorpionGruntSnd=0 Then scorpionGruntSnd=LoadSound(soundsdir$ + "mk\scorpionGrunt.mp3")
    If scorpionGrunt2Snd=0 Then scorpionGrunt2Snd=LoadSound(soundsdir$ + "mk\scorpionGrunt2.mp3")
    If scorpionThrowSnd=0 Then scorpionThrowSnd=LoadSound(soundsdir$ + "scorpion\scorpionThrow.mp3")
    If scorpionSpearSnd=0 Then scorpionSpearSnd=LoadSound(soundsdir$ + "scorpion\scorpionSpear.mp3")
    If zRunGruntSound(n)=0 Then zRunGruntSound(n)=LoadSound(soundsdir$ + "mk\scorpionGrunt.mp3")
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "mk\mkFootstep.mp3")
    If scorpionSpearHitSnd=0 Then scorpionSpearHitSnd=LoadSound(soundsdir$ + "scorpion\scorpionSpearHit.mp3")
    If scorpionComeHereSnd=0 Then scorpionComeHereSnd=LoadSound(soundsdir$ + "scorpion\scorpionComeHere.wav")
    If scorpionGetOverHereSnd=0 Then scorpionGetOverHereSnd=LoadSound(soundsdir$ + "scorpion\scorpionGetOverHere.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "scorpion\scorpionDie.mp3")
    If scorpionTeleportSnd=0 Then scorpionTeleportSnd=LoadSound(soundsdir$ + "scorpion\scorpionTeleport.mp3")
    If scorpionBurnSnd=0 Then scorpionBurnSnd=LoadSound(soundsdir$ + "scorpion\scorpionBurn.mp3")
    If scorpionSkullSnd=0 Then scorpionSkullSnd=LoadSound(soundsdir$ + "scorpion\scorpionSkull.mp3")
    If scorpionFireballSnd=0 Then scorpionFireballSnd=LoadSound(soundsdir$ + "scorpion\scorpionFireBall.mp3")
    If scorpionCooldown1Snd=0 Then scorpionCooldown1Snd=LoadSound(soundsdir$ + "scorpion\scorpionCooldown1Snd.mp3")
    If scorpionCooldown2Snd=0 Then scorpionCooldown2Snd=LoadSound(soundsdir$ + "scorpion\scorpionCooldown2Snd.mp3")
    If mkFriendShipAgainSnd=0 Then mkFriendShipAgainSnd=LoadSound(soundsdir$ + "mk\mkFriendShipAgain.wav")
EndIf

If n=11 Then
    If wolverineBarrageSnd=0 Then wolverineBarrageSnd=LoadSound(soundsdir$ + "wolverine\berserker_barrage.wav")
    If wolverineSlashSnd=0 Then wolverineSlashSnd=LoadSound(soundsdir$ + "wolverine\wolverine_slash1.wav")
    If wolverineSlash2Snd=0 Then wolverineSlash2Snd=LoadSound(soundsdir$ + "wolverine\wolverine_slash2.wav")
    If wolverineSlash3Snd=0 Then wolverineSlash3Snd=LoadSound(soundsdir$ + "wolverine\wolverine_slash3.wav")
    If wolverineSlash4Snd=0 Then wolverineSlash4Snd=LoadSound(soundsdir$ + "wolverine\wolverine_slash4.wav")
    If wolverineTornadoClawSnd=0 Then wolverineTornadoClawSnd=LoadSound(soundsdir$ + "wolverine\wolverineTornadoClaw.wav")
    If wolverineShoutSnd=0 Then wolverineShoutSnd=LoadSound(soundsdir$ + "wolverine\wolverineShout.wav")
    If wolverineShout2Snd=0 Then wolverineShout2Snd=LoadSound(soundsdir$ + "wolverine\wolverineShout2.wav")
    If wolverineShout3Snd=0 Then wolverineShout3Snd=LoadSound(soundsdir$ + "wolverine\wolverineShout3.wav")
    If wolverineShout4Snd=0 Then wolverineShout4Snd=LoadSound(soundsdir$ + "wolverine\wolverineShout4.wav")
    If wolverineKickSnd=0 Then wolverineKickSnd=LoadSound(soundsdir$ + "wolverine\wolverineKick.wav")
    If wolverineSuper1Snd=0 Then wolverineSuper1Snd=LoadSound(soundsdir$ + "wolverine\wolverineSuper1.wav")
    If wolverineSuper2Snd=0 Then wolverineSuper2Snd=LoadSound(soundsdir$ + "wolverine\wolverineSuper2.wav")
    If wolverineGrabSnd=0 Then wolverineGrabSnd=LoadSound(soundsdir$ + "wolverine\wolverineGrab.wav")
    If wolverineDrillClawSnd=0 Then wolverineDrillClawSnd=LoadSound(soundsdir$ + "wolverine\wolverineDrillClaw.wav")
    If wolverineDrillClawHitSnd=0 Then wolverineDrillClawHitSnd=LoadSound(soundsdir$ + "wolverine\wolverineDrillClawHit.wav")
    If wolverineClawSnd=0 Then wolverineClawSnd=LoadSound(soundsdir$ + "wolverine\wolverineClaw.wav")
    If wolverineClaw2Snd=0 Then wolverineClaw2Snd=LoadSound(soundsdir$ + "wolverine\wolverineClaw2.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "wolverine\wolverineDie.wav")
    If wolverineLetsGoSnd=0 Then wolverineLetsGoSnd=LoadSound(soundsdir$ + "wolverine\wolverineLetsGo.wav")
    If zRunGruntSound(n)=0 Then zRunGruntSound(n)=LoadSound(soundsdir$ + "wolverine\wolverineShout2.wav")
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "mk\mkFootstep.mp3")
    If wolverineSpellCooldown1Snd=0 Then wolverineSpellCooldown1Snd=LoadSound(soundsdir$ + "wolverine\wolverineSpellCooldown1.mp3")
    If wolverineSpellCooldown2Snd=0 Then wolverineSpellCooldown2Snd=LoadSound(soundsdir$ + "wolverine\wolverineSpellCooldown2.mp3")
    If wolverineSuper3Snd=0 Then wolverineSuper3Snd=LoadSound(soundsdir$ + "wolverine\wolverineSuper3.wav")
EndIf

If n=10 Then ;Deadpool
    If whipSnd=0 Then whipSnd=LoadSound(soundsdir$ + "whip.wav")
    If crossSnd=0 Then crossSnd=LoadSound(soundsdir$ + "cross.wav")
    If richterSnd=0 Then richterSnd=LoadSound(soundsdir$ + "richter.wav")
    If fastThrowSnd=0 Then fastThrowSnd=LoadSound(soundsdir$ + "fastThrow.wav")
EndIf

If n=9 Then ;Goku
    If goku1Snd=0 Then goku1Snd=LoadSound(soundsdir$ + "goku1.wav")
    If gokuSnd=0 Then gokuSnd=LoadSound(soundsdir$ + "goku.wav")
    If teleportSnd=0 Then teleportSnd=LoadSound(soundsdir$ + "teleport.wav")
EndIf

If n=8 Then     ;Sol
    zpic(n,2,0)=zpic(n,2,4)
    zpic_(n,2,0)=zpic_(n,2,4)
EndIf

If n=7 Then ;Batman
    If CapeSnd=0 Then CapeSnd=LoadSound(soundsdir$ + "cape.wav")
EndIf

If n=6 Then ;Strider Hiryu
    If mvcJump1Snd=0 Then mvcJump1Snd=LoadSound(soundsdir$ + "mvc\mvcJmp1Snd.wav")
    If mvcJump2Snd=0 Then mvcJump2Snd=LoadSound(soundsdir$ + "mvc\mvcJmp2Snd.wav")
    If mvcJump3Snd=0 Then mvcJump3Snd=LoadSound(soundsdir$ + "mvc\mvcJmp3Snd.wav")
    If hiryuRunStartSnd=0 Then hiryuRunStartSnd=LoadSound(soundsdir$ + "hiryu\hiryuRunStartSnd.wav")
    If hiryuRunEndSnd=0 Then hiryuRunEndSnd=LoadSound(soundsdir$ + "juggernaut\juggDash.wav")
    If zRunFootSound(n)=0 Then zRunFootSound(n)=LoadSound(soundsdir$ + "hiryu\hiryuFootSnd.wav")
    If hiryuGrunt1Snd=0 Then hiryuGrunt1Snd=LoadSound(soundsdir$ + "hiryu\hiryuGrunt1Snd.wav")
    If hiryuGrunt2Snd=0 Then hiryuGrunt2Snd=LoadSound(soundsdir$ + "hiryu\hiryuGrunt2Snd.wav")
    If hiryuGrunt3Snd=0 Then hiryuGrunt3Snd=LoadSound(soundsdir$ + "hiryu\hiryuGrunt3Snd.wav")
    If hiryuSlashSnd=0 Then hiryuSlashSnd=LoadSound(soundsdir$ + "hiryu\hiryuSlashSnd.wav")
    If hiryuSwordSnd=0 Then hiryuSwordSnd=LoadSound(soundsdir$ + "hiryu\hiryuSwordSnd.wav")
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsdir$ + "hiryu\hiryuDieSnd.wav")
    If hiryuCooldown1Snd=0 Then hiryuCooldown1Snd=LoadSound(soundsdir$ + "hiryu\hiryuCooldown1Snd.mp3")
    If hiryuCooldown2Snd=0 Then hiryuCooldown2Snd=LoadSound(soundsdir$ + "hiryu\hiryuCooldown2Snd.mp3")
EndIf

If n=5 Then
    If mikeSnd=0 Then mikeSnd=LoadSound(soundsdir$ + "mikeSnd.wav")
    If mikeUpperCutSnd=0 Then mikeUpperCutSnd=LoadSound(soundsdir$ + "mikeUpperCut.wav")
    If mikeFlipSnd=0 Then mikeFlipSnd=LoadSound(soundsdir$ + "mikeFlip.wav")
    If mikeBreathSnd=0 Then mikeBreathSnd=LoadSound(soundsdir$ + "mikeBreath.wav")
EndIf

If n=4 Then ;Mario
    If MarioUahaSnd=0 Then MarioUahaSnd=LoadSound(soundsdir$ + "uaha.wav")
    If hiasnd=0 Then hiasnd=LoadSound(soundsdir$ + "hia.wav")
    If hiahuusnd=0 Then hiahuusnd=LoadSound(soundsdir$ + "hiahuu.wav")
    If mariouppercutsnd=0 Then mariouppercutsnd=LoadSound(soundsdir$ + "mariouppercut.wav")
EndIf

If n=3 Then     ;Spider-man
    If spiderstingsnd=0 Then spiderstingsnd=LoadSound(soundsdir$ + "spidersting.wav")
    If huasnd=0 Then huasnd=LoadSound(soundsdir$ + "hua.wav")
    If webshotsnd=0 Then webshotsnd=LoadSound(soundsdir$ + "webshot.wav")
EndIf

If n=1 Then     ;Evil Ryu
    If deathSnd(n)=0 Then deathSnd(n)=LoadSound(soundsDir$ + "evilryu\evilryuDieSnd.wav")
    If evilryuKorosuSnd=0 Then evilryuKorosuSnd=LoadSound(soundsdir$ + "evilryu\evilryuKorosuSnd.wav")
    If evilryuStepSnd=0 Then evilryuStepSnd=LoadSound(soundsdir$ + "evilryu\evilryuStepSnd.wav")
    If evilRyuShoryukenSnd=0 Then evilRyuShoryukenSnd=LoadSound(soundsdir$ + "evilryu\evilRyuShoryuken.wav")
    If evilRyuHadoukenSnd=0 Then evilRyuHadoukenSnd=LoadSound(soundsdir$ + "evilryu\evilRyuHadouken.wav")
    If evilRyuHadoukenReleaseSnd=0 Then evilRyuHadoukenReleaseSnd=LoadSound(soundsdir$ + "evilryu\evilRyuHadoukenRelease.wav")
    If evilRyuShinkuuSnd=0 Then evilRyuShinkuuSnd=LoadSound(soundsdir$ + "evilryu\evilRyuShinkuu.wav")
    If evilRyuGrunt1Snd=0 Then evilRyuGrunt1Snd=LoadSound(soundsdir$ + "evilryu\evilRyuGrunt1.wav")
    If evilryuGrunt2Snd=0 Then evilRyuGrunt2Snd=LoadSound(soundsdir$ + "evilryu\evilryuGrunt2.wav")
    If evilRyuBlow1Snd=0 Then evilRyuBlow1Snd=LoadSound(soundsdir$ + "evilryu\evilRyuBlow1.wav")
    If evilryuTatsumakiSnd=0 Then evilryuTatsumakiSnd=LoadSound(soundsdir$ + "evilryu\evilryuTatsumaki.wav")
    If evilRyuHit1Snd=0 Then evilRyuHit1Snd=LoadSound(soundsdir$ + "evilryu\evilRyuHit1.wav")
    If evilryuShortElectricSnd=0 Then evilryuShortElectricSnd=LoadSound(soundsdir$ + "evilryu\evilryuShortElectric.wav")
    If evilRyuDodgeSnd=0 Then evilRyuDodgeSnd=LoadSound(soundsdir$ + "evilryu\evilRyuDodge.wav")
    If evilryuDodge2Snd=0 Then evilryuDodge2Snd=LoadSound(soundsdir$ + "evilryu\evilRyuDodge2.wav")
    If evilRyuHadoukenChargeSnd=0 Then evilRyuHadoukenChargeSnd=LoadSound(soundsdir$ + "evilryu\evilRyuHadoukenCharge.wav")
EndIf

If n=41 Then     ;Turtle CLoud
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    zpic(n,4,1)=zpic(n,1,0)
    zpic_(n,4,1)=zpic_(n,1,0)
        For i=0 To 7
     zpic(n,2,i)=zpic(n,1,0)
     zpic_(n,2,i)=zpic_(n,1,0)
    Next
    For i=1 To 3
     zpic(n,1,i)=zpic(n,1,0)
     zpic_(n,1,i)=zpic_(n,1,0)
    Next
EndIf

If n=39 Then    ;Thief
    zpic(n,4,1)=zpic(n,1,3)
    zpic_(n,4,1)=zpic_(n,1,3)
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,1,0)
    zpic_(n,5,i)=zpic_(n,1,0)
    Next
EndIf

If n=38 Then    ;Bowser
    For i=0 To 7
    zpic(n,2,i)=zpic(n,1,0)
    zpic_(n,2,i)=zpic_(n,1,0)
    Next
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,4,1)
    zpic_(n,5,i)=zpic_(n,4,1)
    Next
EndIf

If n=36 Or n=37 Then    ;gargola, Red plant
    zpic(n,1,2)=zpic(n,1,0)
    zpic_(n,1,2)=zpic_(n,1,0)
    zpic(n,1,3)=zpic(n,1,0)
    zpic_(n,1,3)=zpic_(n,1,0)
    For i=0 To 7
    zpic(n,2,i)=zpic(n,1,1)
    zpic_(n,2,i)=zpic_(n,1,1)
    Next
    zpic(n,4,1)=zpic(n,1,1)
    zpic_(n,4,1)=zpic_(n,1,1)
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,1,1)
    zpic_(n,5,i)=zpic_(n,1,1)
    Next
EndIf

If n=35 Then    ;red horns with shield
    zpic(n,1,2)=zpic(n,1,1)
    zpic_(n,1,2)=zpic_(n,1,1)
    zpic(n,1,3)=zpic(n,1,0)
    zpic_(n,1,3)=zpic_(n,1,0)

    zpic(n,2,2)=zpic(n,2,1)
    zpic_(n,2,2)=zpic_(n,2,1)
    zpic(n,2,4)=zpic(n,2,0)
    zpic_(n,2,4)=zpic_(n,2,0)
    zpic(n,2,7)=zpic_(n,2,6)
    zpic_(n,2,7)=zpic(n,2,6)
    zpic(n,4,1)=zpic(n,1,1)
    zpic_(n,4,1)=zpic_(n,1,1)
    zpic(n,3,1)=zpic(n,1,1)
    zpic_(n,3,1)=zpic_(n,1,1)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,1,1)
    zpic_(n,5,i)=zpic_(n,1,1)
    Next
EndIf

If n=30 Then    ;pig
    zpic(n,2,3)=zpic(n,2,2)
    zpic_(n,2,3)=zpic_(n,2,2)
    zpic(n,4,1)=zpic(n,3,1)
    zpic_(n,4,1)=zpic_(n,3,1)
    zpic(n,6,2)=zpic_(n,2,5)
    zpic_(n,6,2)=zpic(n,2,5)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,4,1)
    zpic_(n,5,i)=zpic_(n,4,1)
    Next
EndIf

If n=33 Then    ;Shredder
    For i=2 To 4
    zpic(n,5,i)=zpic(n,5,1)
    zpic_(n,5,i)=zpic_(n,5,1)
    Next
    If shredderSnd=0 Then shredderSnd=LoadSound(soundsdir$ + "shredder.wav")
    If shredder2Snd=0 Then shredder2Snd=LoadSound(soundsdir$ + "shredder2.wav")
EndIf

If n=32 Then    ;foot clan
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,4,1)
    zpic_(n,5,i)=zpic_(n,4,1)
    Next
EndIf

If n=31 Then
    zpic(n,2,2)=zpic(n,2,1)        ;alien
    zpic_(n,2,2)=zpic_(n,2,1)
    zpic(n,1,3)=zpic(n,1,0)
    zpic_(n,1,3)=zpic_(n,1,0)
    zpic(n,3,1)=zpic(n,1,0)
    zpic_(n,3,1)=zpic_(n,1,0)
    zpic(n,2,4)=zpic(n,2,0)
    zpic_(n,2,4)=zpic_(n,2,0)
    zpic(n,2,7)=zpic(n,2,6)
    zpic_(n,2,7)=zpic_(n,2,6)
    zpic(n,4,1)=zpic(n,14,1)
    zpic_(n,4,1)=zpic_(n,14,1)
    For i=1 To 4
    zpic(n,5,i)=zpic(n,4,1)
    zpic_(n,5,i)=zpic_(n,4,1)
    Next
EndIf

loadTimeEnd=MilliSecs()
DebugLog "n: " + n + ", loadTime: " + (loadTimeEnd-loadTimeStart)

End Function

Function initStance(n)
    zStanceFrames(n)=0
    zStanceSpeed(n)=0
    stanceMode(n)=1
    
    select(n)
    Case 1  ; Evil Ryu
        zStanceFrames(n)=34
        zStance2Frames(n)=10
    Case 2  ; Rash
        zStanceFrames(n)=4
    Case 3  ; Spiderman
        zStanceFrames(n)=9
        zStanceSpeed(n)=4
    Case 4  ; Mario
        zStanceFrames(n)=16
        zStanceSpeed(n)=3
    Case 5 ; Leonardo
        zStanceFrames(n)=4
    Case 6  ; Strider Hiryu
        zStanceFrames(n)=20
        zStanceSpeed(n)=3
    Case 7  ; Batman
        zStanceFrames(n)=6
        zStanceSpeed(n)=4
    Case 8  ; Sol Badguy
        zStanceFrames(n)=11
        zStanceSpeed(n)=4
    Case 9  ; Goku
        zStanceFrames(n)=9
        zStanceSpeed(n)=4
    Case 10  ; Deadpool
        zStanceFrames(n)=11
        zStanceSpeed(n)=6
    case 11 ; Wolverine
        zStanceFrames(n)=18
        zStanceSpeed(n)=3
    case 12 ; Scorpion
        zStanceFrames(n)=9
        zStanceSpeed(n)=5
    case 13 ; Sub Zero
        zStanceFrames(n)=9
        zStanceSpeed(n)=5
    Case 14 ; WonderWoman
        zStanceFrames(n)=12
        zStanceSpeed(n)=6
    Case 15 ; Juggernaut
        zStanceFrames(n)=8
        zStance2Frames(n)=9
        zStanceSpeed(n)=6
    Case 16 ; Piccolo
        zStanceFrames(n)=7
        zStanceSpeed(n)=8
    Case 17 ; Hulk
        zStanceFrames(n)=8
        zStanceSpeed(n)=6
    Case 18 ; Thor
        zStanceFrames(n)=8
        zStanceSpeed(n)=6
    Case 19 ; LeiLei
        zStanceFrames(n)=13
        zStanceSpeed(n)=5
    Case 20 ; Kenshiro
        zStanceFrames(n)=9
        zStanceSpeed(n)=5
    Case 40 ; Turtle
        zStanceFrames(n)=16
        zStanceSpeed(n)=4
    Case 51 ; Hanzo
        zStanceFrames(n)=10
        zStanceSpeed(n)=6
    End Select
End Function