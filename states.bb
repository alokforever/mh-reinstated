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
	zheight(n)=45				;Player's current height
	zUpHeight(n)=45
	zDuckHeight(n)=25
	zside(n)=8	 				;Z width size / 2
	zSpeed#(n)=0				;Player current speed
	zShieldedTime(n)=150		;Time(frames) player stays invincible when recover
	zBlockFull(n)=80
	zBlockLife(n)=zBlockFull(n)
	zCurWeapon(n)=0
	zAcc#(n)=.2         ;.2
	zgravity(n)=3       ;3		;Gravity force when falling Or going up
	zjumplimit(n)=20    ;20		;Jump height (per frame), not pixels!
	zDtopSpeed#(n)=2    ;2
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
	zLetGoAmount(n)=7			;reach this number with 'zLetGoSeq(x)' to let go of grab
	zHelper(n)=0
	zTrail(n)=0
	zTrailType(n)=0
	zHitByRect(nn)=0
	zRollOnImpact(n)=0
	zStanceFrames(n)=0
	zStanceSpeed(n)=0
	zWalkFrames(n)=0
	zWalkFrameSpeed#(n)=0
	zRunFrames(n)=0
	zRunFrameSpeed#(n)=0
	zCharSpeed#(n)=2
	gender(n)=3
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

Select curGuy(n)	;Add character, add your new guy initial stuff, attack range, jump sound etc
Case 1: ;Ryu
	zBlowDist(n,1)=44
	zBlowDist(n,2)=48
	zBlowDist(n,4)=44
	zBlowDist(n,5)=48
	zBlowDist(n,7)=300
	zBlowDist(n,9)=70	:dangerMove9(n)=1
	zBlowDist(n,10)=44
	zBlowDist(n,11)=150
	zBlowDist(n,14)=600
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 2: ;Rash
	zBlowDist(n,1)=65
	zBlowDist(n,2)=75
	zBlowDist(n,4)=60
	zBlowDist(n,5)=55
	zBlowDist(n,7)=68
	zBlowDist(n,9)=80	:dangerMove9(n)=1
	zBlowDist(n,10)=45
	zBlowDist(n,11)=150
	zBlowDist(n,14)=280
	zxHand(n,0)=-5 :zyHand(n,0)=20
	zxHand(n,1)=6 :zyHand(n,1)=23
	zxHand(n,2)=-13 :zyHand(n,2)=22
	zxHand(n,3)=-8 :zyHand(n,3)=20
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 3: ;Spider-man
	zBlowDist(n,1)=55
	zBlowDist(n,2)=52
	zBlowDist(n,4)=70
	zBlowDist(n,5)=75
	zBlowDist(n,7)=300
	zBlowDist(n,9)=110	:dangerMove9(n)=1
	zBlowDist(n,10)=84
	zBlowDist(n,11)=150
	zBlowDist(n,14)=400
	zGrabDist(n)=25
	zxHand(n,0)=-10 :zyHand(n,0)=10
	zxHand(n,1)=-11 :zyHand(n,1)=11
	zxHand(n,2)=-13 :zyHand(n,2)=12
	zxHand(n,3)=-13 :zyHand(n,3)=10
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 4: ;Mario
	zBlowDist(n,1)=92
	zBlowDist(n,2)=43
	zBlowDist(n,4)=53
	zBlowDist(n,5)=42
	zBlowDist(n,7)=300
	zBlowDist(n,9)=39	:dangerMove9(n)=0
	zBlowDist(n,10)=34
	zBlowDist(n,11)=150
	zBlowDist(n,14)=500
	zxHand(n,0)=-11 :zyHand(n,0)=15
	zxHand(n,1)=7 :zyHand(n,1)=17
	zxHand(n,2)=-11 :zyHand(n,2)=14
	zxHand(n,3)=-6 :zyHand(n,3)=13
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 5: ;Michaelangelo
	zBlowDist(n,1)=64
	zBlowDist(n,2)=52
	zBlowDist(n,4)=53
	zBlowDist(n,5)=75
	zBlowDist(n,7)=300
	zBlowDist(n,9)=110	:dangerMove9(n)=1
	zBlowDist(n,10)=55
	zBlowDist(n,11)=150
	zBlowDist(n,14)=70
	zGrabDist(n)=zGrabDist(n)+5
	zxHand(n,0)=-6 :zyHand(n,0)=22
	zxHand(n,1)=-6 :zyHand(n,1)=20
	zxHand(n,2)=-5 :zyHand(n,2)=21
	zxHand(n,3)=-7 :zyHand(n,3)=20
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 6: ;Ryu (gaiden)
	zBlowDist(n,1)=60
	zBlowDist(n,2)=52
	zBlowDist(n,4)=53
	zBlowDist(n,5)=48
	zBlowDist(n,7)=300
	zBlowDist(n,9)=90	:dangerMove9(n)=1
	zBlowDist(n,10)=50
	zBlowDist(n,11)=150
	zBlowDist(n,14)=120
	zxHand(n,0)=-5 :zyHand(n,0)=21
	zxHand(n,1)=-5 :zyHand(n,1)=22
	zxHand(n,2)=-5 :zyHand(n,2)=21
	zxHand(n,3)=-4 :zyHand(n,3)=21
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 7: ;Batman
	zBlowDist(n,1)=48
	zBlowDist(n,2)=58
	zBlowDist(n,4)=55
	zBlowDist(n,5)=48
	zBlowDist(n,7)=250
	zBlowDist(n,9)=100	:dangerMove9(n)=0
	zBlowDist(n,10)=45
	zBlowDist(n,11)=150
	zBlowDist(n,14)=300
	zGrabDist(n)=zGrabDist(n)+5
	zxHand(n,0)=-4 :zyHand(n,0)=20
	zxHand(n,1)=-8 :zyHand(n,1)=20
	zxHand(n,2)=16 :zyHand(n,2)=14
	zxHand(n,3)=-2 :zyHand(n,3)=15
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 8: ;Predator
	zBlowDist(n,1)=48
	zBlowDist(n,2)=58
	zBlowDist(n,4)=55
	zBlowDist(n,5)=48
	zBlowDist(n,7)=500
	zBlowDist(n,9)=150	:dangerMove9(n)=0
	zBlowDist(n,10)=45
	zBlowDist(n,11)=150
	zBlowDist(n,14)=500
	zGrabDist(n)=zGrabDist(n)+7
	zxHand(n,0)=-5 :zyHand(n,0)=23
	zxHand(n,1)=4 :zyHand(n,1)=22
	zxHand(n,2)=-11 :zyHand(n,2)=24
	zxHand(n,3)=-5 :zyHand(n,3)=24
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1
	
Case 9: ;Goku
	zBlowDist(n,1)=54
	zBlowDist(n,2)=48
	zBlowDist(n,4)=44
	zBlowDist(n,5)=48
	zBlowDist(n,7)=300
	zBlowDist(n,9)=70	:dangerMove9(n)=1
	zBlowDist(n,10)=39
	zBlowDist(n,11)=150
	zBlowDist(n,14)=600
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 10: ;Richter Belmont
	zBlowDist(n,1)=110
	zBlowDist(n,2)=110
	zBlowDist(n,4)=110
	zBlowDist(n,5)=48
	zBlowDist(n,7)=200
	zBlowDist(n,9)=400	:dangerMove9(n)=0
	zBlowDist(n,10)=44
	zBlowDist(n,11)=150
	zBlowDist(n,14)=600
	zxHand(n,0)=15 :zyHand(n,0)=21
	zxHand(n,1)=7 :zyHand(n,1)=23
	zxHand(n,2)=-11 :zyHand(n,2)=20
	zxHand(n,3)=-6 :zyHand(n,3)=19
	zRollOnImpact(n)=1
	zJumpSnd(n)=shotwallsnd
	gender(n)=1

Case 11: ;Wolverine
	zBlowDist(n,1)=60
	zBlowDist(n,2)=52
	zBlowDist(n,4)=53
	zBlowDist(n,5)=48
	zBlowDist(n,7)=300
	zBlowDist(n,9)=90	:dangerMove9(n)=1
	zBlowDist(n,10)=50
	zBlowDist(n,11)=150
	zBlowDist(n,14)=120
	zBlowDist(n,16)=550
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=jumpsnd
	zJumpSnd2(n)=wolverinejumpsnd
	zWalkFrames(n)=16
	zStanceFrames(n)=17
	zStanceSpeed(n)=3
	zWalkFrameSpeed#(n)=3
	zRunFrames(n)=6
	zRunFrameSpeed#(n)=3
	zCharSpeed#(n)=2.5
	gender(n)=1
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
	zBlowDist(n,9)=90	:dangerMove9(n)=1
	zBlowDist(n,10)=50
	zBlowDist(n,11)=150
	zBlowDist(n,14)=120
	zBlowDist(n,16)=370
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=mkJumpSnd
	zJumpSnd2(n)=mkJump2Snd
	zStanceFrames(n)=9
	zStanceSpeed(n)=5
	zWalkFrames(n)=9
	zWalkFrameSpeed#(n)=4
	zRunFrames(n)=11
	zRunFrameSpeed#(n)=3
	zCharSpeed#(n)=2
	isMkCharacter(n)=1
	gender(n)=1
	flipFrames(n)=6
	dizzyFrames(n)=8
	dizzyFrameSpeed(n)=7
	zRunFootSoundSeq(n)=12

Case 13: ;Sub Zero
	zBlowDist(n,1)=45
	zBlowDist(n,2)=50
	zBlowDist(n,4)=64
	zBlowDist(n,5)=48
	zBlowDist(n,7)=250
	zBlowDist(n,9)=400	:dangerMove9(n)=1
	zBlowDist(n,10)=40
	zBlowDist(n,11)=150
	zBlowDist(n,14)=600
	zBlowDist(n,16)=370
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=mkJumpSnd
	zJumpSnd2(n)=mkJump2Snd
	zStanceFrames(n)=9
	zStanceSpeed(n)=5
	zWalkFrames(n)=9
	zWalkFrameSpeed#(n)=4
	zRunFrames(n)=11
	zRunFrameSpeed#(n)=3
	zCharSpeed#(n)=2
	isMkCharacter(n)=1
	gender(n)=1
	dizzyFrames(n)=8
	dizzyFrameSpeed(n)=7
	zRunFootSoundSeq(n)=12

Case 14: ;Wonder Woman
	zBlowDist(n,1)=45
	zBlowDist(n,2)=50
	zBlowDist(n,4)=64
	zBlowDist(n,5)=48
	zBlowDist(n,7)=250
	zBlowDist(n,9)=400	:dangerMove9(n)=1
	zBlowDist(n,10)=40
	zBlowDist(n,11)=150
	zBlowDist(n,14)=600
	zBlowDist(n,16)=370
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=jumpsnd
	zJumpSnd2(n)=wolverinejumpsnd
	zStanceFrames(n)=12
	zStanceSpeed(n)=6
	zWalkFrames(n)=27
	zWalkFrameSpeed#(n)=2
	zRunFrames(n)=7
	zRunFrameSpeed#(n)=3
	zCharSpeed#(n)=2.5
	dizzyFrames(n)=8
	dizzyFrameSpeed(n)=7
	gender(n)=2
	duckFrames(n)=5
	duckFrameSpeed(n)=5
	canAirGlide(n)=1
	zRunFootSoundSeq(n)=12
	hasSpecialAirFrames(n)=1

Case 15: ;Juggernaut
	zBlowDist(n,1)=45
	zBlowDist(n,2)=50
	zBlowDist(n,4)=64
	zBlowDist(n,5)=48
	zBlowDist(n,7)=250
	zBlowDist(n,9)=400	:dangerMove9(n)=1
	zBlowDist(n,10)=40
	zBlowDist(n,11)=150
	zBlowDist(n,14)=600
	zBlowDist(n,16)=370
	zxHand(n,0)=2 :zyHand(n,0)=23
	zxHand(n,1)=2 :zyHand(n,1)=23
	zxHand(n,2)=-2 :zyHand(n,2)=23
	zxHand(n,3)=-2 :zyHand(n,3)=23
	zRollOnImpact(n)=1
	zJumpSnd(n)=juggJumpSnd
	zJumpSnd2(n)=wolverinejumpsnd
	zStanceFrames(n)=8
	zStanceSpeed(n)=6
	zWalkFrames(n)=16
	zWalkFrameSpeed#(n)=4
	zRunFrames(n)=6
	zRunFrameSpeed#(n)=4
	zCharSpeed#(n)=3
	dizzyFrames(n)=8
	dizzyFrameSpeed(n)=7
	gender(n)=1
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
	zBlowDist(n,9)=60	:dangerMove9(n)=1
	zBlowDist(n,10)=80
	zBlowDist(n,11)=60
	zBlowDist(n,14)=60
	zDuckHeight(n)=45
	zJumpLimit(n)=10
	zDontJump(n)=1
	zDontPickItem(n)=1
	zDtopSpeed#(n)=1.5
	zTopSpeed#(n)=zDtopSpeed(n)
	zNoAirSpecial(n)=1
	gender(n)=1

Case 31: ;Alien
	zBlowDist(n,1)=64
	zBlowDist(n,2)=60
	zBlowDist(n,4)=60
	zBlowDist(n,5)=60
	zBlowDist(n,7)=100
	zBlowDist(n,9)=60	:dangerMove9(n)=1
	zBlowDist(n,10)=80
	zBlowDist(n,11)=60
	zBlowDist(n,14)=60
	zheight(n)=40				;Player's current height
	zUpHeight(n)=40
	zDuckHeight(n)=40
	zDontPickItem(n)=1
	zNoAirSpecial(n)=1
	gender(n)=1

Case 32: ;Foot Clan
	zBlowDist(n,1)=50
	zBlowDist(n,2)=55
	zBlowDist(n,4)=70
	zBlowDist(n,5)=70
	zBlowDist(n,7)=70
	zBlowDist(n,9)=70	:dangerMove9(n)=1
	zBlowDist(n,10)=55
	zBlowDist(n,11)=55
	zBlowDist(n,14)=55
	zDuckHeight(n)=45
	zDontPickItem(n)=1
	zNoAirSpecial(n)=1
	gender(n)=1

Case 33: ;Shredder
	zBlowDist(n,1)=50
	zBlowDist(n,2)=50
	zBlowDist(n,4)=50
	zBlowDist(n,5)=50
	zBlowDist(n,7)=110
	zBlowDist(n,9)=110	:dangerMove9(n)=0
	zBlowDist(n,10)=50
	zBlowDist(n,11)=50
	zBlowDist(n,14)=50
	zDontPickItem(n)=1
	gender(n)=1

Case 34: ;Thug
	zBlowDist(n,1)=40
	zBlowDist(n,2)=60
	zBlowDist(n,4)=40
	zBlowDist(n,5)=40
	zBlowDist(n,7)=40
	zBlowDist(n,9)=40	:dangerMove9(n)=0
	zBlowDist(n,10)=40
	zBlowDist(n,11)=40
	zBlowDist(n,14)=40
	zDontPickItem(n)=1
	zNoAirSpecial(n)=1
	zRollOnImpact(n)=1
	gender(n)=1

Case 35: ;Red horns
	zBlowDist(n,1)=164
	zBlowDist(n,2)=160
	zBlowDist(n,4)=160
	zBlowDist(n,5)=160
	zBlowDist(n,7)=160
	zBlowDist(n,9)=160	:dangerMove9(n)=0
	zBlowDist(n,10)=160
	zBlowDist(n,11)=160
	zBlowDist(n,14)=160
	zDuckHeight(n)=45
	zJumpLimit(n)=10
	zDontJump(n)=1
	zDontPickItem(n)=1
	zDtopSpeed#(n)=.5
	zTopSpeed#(n)=zDtopSpeed(n)
	zNoAirSpecial(n)=1
	gender(n)=1

Case 36: ;Gargola
	zBlowDist(n,1)=364
	zBlowDist(n,2)=360
	zBlowDist(n,4)=360
	zBlowDist(n,5)=360
	zBlowDist(n,7)=360
	zBlowDist(n,9)=360	:dangerMove9(n)=0
	zBlowDist(n,10)=360
	zBlowDist(n,11)=360
	zBlowDist(n,14)=360
	zSide(n)=25
	zDuckHeight(n)=45
	zJumpLimit(n)=10
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
	zBlowDist(n,9)=360	:dangerMove9(n)=0
	zBlowDist(n,10)=360
	zBlowDist(n,11)=360
	zBlowDist(n,14)=360
	zDuckHeight(n)=45
	zJumpLimit(n)=0
	zDontJump(n)=1
	zDontPickItem(n)=1
	zDtopSpeed#(n)=0
	zTopSpeed#(n)=zDtopSpeed(n)
	zNoAirSpecial(n)=1
	zStone(n)=1
	zUngrabable(n)=1

Case 38: ;Bowser
	zBlowDist(n,1)=120
	zBlowDist(n,2)=360
	zBlowDist(n,4)=360
	zBlowDist(n,5)=100
	zBlowDist(n,7)=360
	zBlowDist(n,9)=120	:dangerMove9(n)=0
	zBlowDist(n,10)=120
	zBlowDist(n,11)=360
	zBlowDist(n,14)=360
	zheight(n)=52
	zUpHeight(n)=52
	zDuckHeight(n)=52
	zSide(n)=18
	zJumpLimit(n)=0
	zDontJump(n)=1
	zNoAirSpecial(n)=1
	zDontPickItem(n)=1
	zStone(n)=1
	zUngrabable(n)=1
	yRange(n)=100
	gender(n)=1

Case 39	;thief
	zBlowDist(n,1)=200
	zBlowDist(n,2)=200
	zBlowDist(n,4)=200
	zBlowDist(n,5)=200
	zBlowDist(n,7)=200
	zBlowDist(n,9)=200	:dangerMove9(n)=0
	zBlowDist(n,10)=200
	zBlowDist(n,11)=200
	zBlowDist(n,14)=200
	zDuckHeight(n)=45
	zDontJump(n)=1
	zDontPickItem(n)=1
	zNoAirSpecial(n)=1
	gender(n)=1

Case 40	;turtle
	zBlowDist(n,1)=200
	zBlowDist(n,2)=200
	zBlowDist(n,4)=200
	zBlowDist(n,5)=200
	zBlowDist(n,7)=200
	zBlowDist(n,9)=200	:dangerMove9(n)=0
	zBlowDist(n,10)=200
	zBlowDist(n,11)=200
	zBlowDist(n,14)=200
	zheight(n)=40
	zUpHeight(n)=40
	zDuckHeight(n)=40
	zDontJump(n)=1
	zDontPickItem(n)=1
	zNoAirSpecial(n)=1
	gender(n)=3

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
	zhelper(n)=1
	If zvar1(n) > 0 Then
		zDtopSpeed(n)=zvar1(n)
		zTopSpeed(n)=zDTopSpeed(n)
	EndIf

Case 42	;Joker
	zBlowDist(n,1)=50       ;1  - blow
	zBlowDist(n,2)=200      ;2  - flying blow
	zBlowDist(n,4)=50       ;4  - low blow
	zBlowDist(n,5)=50       ;5  - up special
	zBlowDist(n,7)=200      ;7  - special
	zBlowDist(n,9)=200	:dangerMove9(n)=0;9  - down special
	zBlowDist(n,10)=50      ;10 - high blow
	zBlowDist(n,11)=200
	zBlowDist(n,14)=200
	zDuckHeight(n)=45
	zNoAirSpecial(n)=1
	zDtopSpeed#(n)=2.5
	zDontPickItem(n)=1
	zTopSpeed#(n)=zDtopSpeed(n)
	gender(n)=1

Case 43;Laser helper
	zUpHeight(n)=20
	zDuckHeight(n)=20
	zNoAirSpecial(n)=1
	zDtopSpeed#(n)=0
	zTopSpeed#(n)=zDtopSpeed(n)
	zDontPickItem(n)=1
	zDontJump(n)=1
	zStone(n)=1
	zUngrabable(n)=1
	zhelper(n)=1
	zUseSpecialAI(n)=1

Case 44	;Venom
	zBlowDist(n,1)=120
	zBlowDist(n,2)=120
	zBlowDist(n,4)=120
	zBlowDist(n,5)=60   : dangerMove5(n)=0
	zBlowDist(n,7)=120
	zBlowDist(n,9)=120	:dangerMove9(n)=0
	zBlowDist(n,10)=60
	zBlowDist(n,11)=50
	zBlowDist(n,14)=50
	zGrabDist(n)=zGrabDist(n)+15
	zDontPickItem(n)=1
	zStone(n)=1
	zUngrabable(n)=1
	zBlockFull(n)=500
	gender(n)=1

Case 45	;bombing ship
	zUpHeight(n)=40
	zDtopSpeed#(n)=8
	zTopSpeed#(n)=zDtopSpeed(n)
	zUseSpecialAI(n)=1
	zStone(n)=1
	zUngrabable(n)=1
	zhelper(n)=1
	zDontPickItem(n)=1
	If zvar1(n) = 0 Then zvar1(n) = 12
	If zvar2(n) > 0 Then zFace(n)=zvar2(n)
	If gamesound Then PlaySound flyBySnd
	zBlowSeq2(n)=0
	zCanFly(n)=1
	zUseSpecialAI(n)=1

Case 46	;Ray balls
	zUpHeight(n)=20
	zDuckHeight(n)=20
	zNoAirSpecial(n)=1
	zDtopSpeed#(n)=0
	zTopSpeed#(n)=zDtopSpeed(n)
	zDontPickItem(n)=1
	zDontJump(n)=1
	zStone(n)=1
	zUngrabable(n)=1
	zhelper(n)=1
	zUseSpecialAI(n)=1

Case 47	;soldier
	zDontJump(n)=1
	zNoAirSpecial(n)=1
	zDtopSpeed#(n)=0
	zTopSpeed#(n)=zDtopSpeed(n)
	zDontPickItem(n)=1
	zUseSpecialAI(n)=1
	gender(n)=1

Case 48: ;Cylinder
	zUpHeight(n)=20
	zDuckHeight(n)=20
	zDontPickItem(n)=1
	zDtopSpeed(n)=1
	zTopSpeed(n)=zDtopSpeed(n)
	zStone(n)=1
	zUngrabable(n)=1
	zAcc(n)=.1
	zUseSpecialAI(n)=1
	zCanFly(n)=1

Case 49: ;Dragon
	zUpHeight(n)=65
	zDuckHeight(n)=zUpHeight(n)
	zSide(n)=50
	zDontPickItem(n)=1
	zDtopSpeed(n)=1
	zTopSpeed(n)=zDtopSpeed(n)
	zStone(n)=1
	zUngrabable(n)=1
	zAcc(n)=.1
	zUseSpecialAI(n)=1
	zCanFly(n)=1

Case 50: ;Laser beam
	zUpHeight(n)=20
	zDuckHeight(n)=20
	zSide(n)=2
	zDontPickItem(n)=1
	zDtopSpeed(n)=0
	zTopSpeed(n)=zDtopSpeed(n)
	zStone(n)=1
	zUngrabable(n)=1
	zAcc(n)=0
	zUseSpecialAI(n)=1
	zCanFly(n)=1
	zHelper(n)=1

	;zxrespawn(n)=zx(n)
	;zyrespawn(n)=zy(n)

Case 51: ;Gray Ninja
	zBlowDist(n,1)=60
	zBlowDist(n,2)=52
	zBlowDist(n,4)=53
	zBlowDist(n,5)=48
	zBlowDist(n,7)=300
	zBlowDist(n,9)=90	:dangerMove9(n)=1
	zBlowDist(n,10)=50
	zBlowDist(n,11)=150
	zBlowDist(n,14)=120
	zxHand(n,0)=-5 :zyHand(n,0)=21
	zxHand(n,1)=-5 :zyHand(n,1)=22
	zxHand(n,2)=-5 :zyHand(n,2)=21
	zxHand(n,3)=-4 :zyHand(n,3)=21
	zDtopSpeed(n)=2.5
	zTopSpeed(n)=zDtopSpeed(n)
	zjumplimit(n)=22
	zRollOnImpact(n)=1
	gender(n)=1

Case 52: ;punching bag
	zUpHeight(n)=34
	zDuckHeight(n)=34
	zSide(n)=7
	zDontPickItem(n)=1
	zDtopSpeed(n)=0
	zTopSpeed(n)=zDtopSpeed(n)
	zUngrabable(n)=1
	zAcc(n)=0
	zUseSpecialAI(n)=1
	zHelper(n)=1

End Select


End Function
;-----------shotData--------------------------------------------
Function shotData(weaponChosen,n)

;Defaut Values
	shotType(n)=weaponChosen
	shotOwner(n)=0
	shotspeed(n)=3
	shotYspeed(n)=0
	shotsize(n)=10	;width
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
	shotAcc(n)=.2			;shot acceleration
	shotMaxSpeed(n)=shotSpeed(n)
	shotDrill(n)=0
	shotChunkType(n)=20		;type of the animation when the shot hits
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
	shotExplosionSound(n)=explodeSnd
	shotWidth(n)=1
	shotId(n)=0
	isShotDisappearOnHit(n)=0
	shotChunkHitType(n)=0
	doesShotBurn(n)=0
	shotGroundType(n)=0

Select weaponChosen	
	
Case 5	;ryu ball
	shotspeed(n)=3
	shotsize(n)=18
	shotheight(n)=16
	shotSide(n)=shotsize(n)/2
	shotdamage(n)=4
	shotHitXspeed(n)=2
	shotHitYspeed(n)=2
	shotFallTime(n)=20
	shotDuration(n)=200
	shotMaxSpeed(n)=shotSpeed(n)
	shotChunkType(n)=3
	shotPic(n,1)=shotImage(5)
	shotPic_(n,1)=shotImage_(5)
	shotSound(n)=HighPunchsnd

Case 6	;web shot
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

Case 7	; fire ball
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

Case 8	;laser
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

Case 9	;Mike's dragon fire ball
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

Case 10	;bullet
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

Case 11	;Rash Axe
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

Case 12	;Rash Axe (hit mode 0)
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

Case 13	;fire ball (hitMode=0)
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

Case 14	;fire ball (weak And fast for super special)
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

Case 15	;ryu hayabusa ninja star
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

Case 16	;ninja star Uturn boomerang effect
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

Case 17	;Red Horns Uturn fire
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

Case 18	;pink blob
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

Case 19	; bowser fire ball
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

Case 20	;batrang
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

Case 21	;predator green ray
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

Case 22	;predator disc
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
	shotDuration2(n)=52 ; 52
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

Case 23	;missile (hitMode=0)
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

Case 24	;big laser
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

Case 25	;ray blue ball
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

Case 26	;evil black missile
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

Case 27	;cannon ball
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

Case 28	;Goku ball
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

Case 29	;Ritcher cross
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
	
Case 30	;Ritcher sword
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
	
Case 39	;Sub Zero freeze ball
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
	shotDuration(n)=60
	shotMaxSpeed(n)=shotSpeed(n)
	shotChunkType(n)=75
	shotPic(n,1)=shotImage(47)
	shotPic_(n,1)=shotImage_(47)
	shotSound(n)=subZeroFreeze2Snd
	
Case 40 ;Sub Zero ground freeze
	shotId(n)=40
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
	
Case 41	;Sub Zero ice spikes
	shotspeed(n)=6
	shotsize(n)=42
	shotheight(n)=40
	shotWidth(n)=50
	shotVerticalSize(n)=1
	shotSide(n)=shotsize(n)/2
	shotdamage(n)=9
	shotHitMode(n)=3
	shotFallTime(n)=0
	shotImmuneTime(n)=0
	shotHitXspeed(n)=0
	shotHitYspeed(n)=0
	shotDuration(n)=300
	shotMaxSpeed(n)=shotSpeed(n)
	shotChunkType(n)=75
	shotPic(n,1)=shotImage(49)
	shotPic_(n,1)=shotImage_(49)
	shotSound(n)=subZeroIceBlastSnd
	
Case 42	;Sub Zero diagonal freeze ball
	shotspeed(n)=3
	shotYspeed(n)=3
	shotsize(n)=40
	shotheight(n)=3
	shotWidth(n)=50
	shotVerticalSize(n)=1
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
	shotId(n)=43
	shotVerticalSize(n)=28
	shotSide(n)=shotsize(n)/2
	shotdamage(n)=0
	shotHitMode(n)=3
	shotFallTime(n)=0
	shotHitXspeed(n)=0
	shotHitYspeed(n)=0
	shotDuration(n)=100
	shotMaxSpeed(n)=shotSpeed(n)
	shotChunkType(n)=75
	shotPic(n,1)=shotImage(51)
	shotPic_(n,1)=shotImage_(51)
	shotSound(n)=subZeroFreeze2Snd
	
Case 44	;Sub Zero ice shower
	shotId(n)=44
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
	
Case 46	;Scorpion Fireball
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
	shotPic_(n,1)=shotImage(56)
	shotPic(n,2)=shotImage(57)
	shotPic_(n,2)=shotImage(57)
	shotFrameTime(n)=2
	shotSound(n)=juggLateralHitSnd

Case 48 ;Juggernaut Earthquake 2
	shotGroundType(n)=2
	shotspeed(n)=0
	shotYspeed(n)=-4
	shotsize(n)=61
	shotWidth(n)=25
	shotHeight(n)=16
	shotDamage(n)=11
	shotHitMode(n)=2
	shotSide(n)=shotsize(n)/2
	shotImmuneTime(n)=15
	shotHitXspeed(n)=5
	shotHitYspeed(n)=2.5
	shotFallTime(n)=40
	shotDuration(n)=2
	shotMaxSpeed(n)=shotSpeed(n)
	shotDrill(n)=0
	shotChunkType(n)=115
	shotPic(n,1)=shotImage(58)
	shotPic_(n,1)=shotImage(58)
	shotSound(n)=juggLateralHitSnd

End Select
End Function
;----------------- Chunks ---------------------------------
Function chunks(n)

chunkSeq(n)=chunkSeq(n)+1
cc=chunkType(n)
Select chunkType(n)
Case 0: a=5:b=10:c=14:d=18	;test
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
	If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
	If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
	If chunkSeq(n) > c Then chunk(n)=0

Case 1: a=5:b=10:c=14	;Blocking
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
	If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
	If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic(cc,2)
	If chunkSeq(n) > c Then chunk(n)=0

Case 2: a=30:b=100:c=180:d=240		;Round Introduction
	If chunkSeq(n) < b Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) => b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) =a Then
		If gameSound Then PlaySound readySnd
	EndIf

	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then ychunk(n)=ychunk(n)+10
	If chunkSeq(n) > a And chunkSeq(n) =< b Then q=0
	If chunkSeq(n) > b And chunkSeq(n) =< c Then q=0
	If chunkSeq(n) = c Then
		FlushKeys:FlushJoy
		If gameSound Then PlaySound fightSnd
	EndIf
	If chunkSeq(n) > c And chunkSeq(n) =< d Then ychunk(n)=ychunk(n)-10:NoUserInput=0
	If chunkSeq(n) > d Then chunk(n)=0

Case 3: a=10:b=20		;ryu blue ball impact
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 4: a=5:b=10:c=15:d=20	;explosion 40
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > d Then chunk(n)=0:

Case 5: a=5:b=10:c=15		;white star hit
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 6: a=10:b=20		;web shot impact
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic_(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic_(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 7: a=10:b=20		;fire ball impact
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 8: a=3:b=6:c=9		;coins
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 9: a=10:b=17		;lava rock breaking
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 10: a=3:b=6:c=9		;M vs C hit
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2) :chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,3) :chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 11: a=10:b=25:c=35		;vulcano explosion
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > c Then chunk(n)=0

Case 12: a=5:b=10:c=14	;air Trail going up
	yChunk(n)=yChunk(n)-1
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
	If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
	If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
	If chunkSeq(n) > c Then chunk(n)=0

Case 13: a=7			;bright dot
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a Then chunk(n)=0

Case 14: a=6:b=12		;blood
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):ChunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 15: a=5:b=10:c=14	;Green pick up sign
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
	If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic(cc,2)
	If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,3):chunkPic_(n)= ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0
Case 16: a=5:b=10:c=14	;smoke
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
	If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic(cc,2)
	If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,3):chunkPic_(n)= ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 17: a=1:b=2	;red ray impact
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic_(cc,1)
	If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic_(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 18: a=5:b=10:c=14	;blueRay
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
	If chunkSeq(n) > 1 Then chunk(n)=0
Case 19: a=5:b=10:c=14	;blueRay 2
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
	If chunkSeq(n) > 1 Then chunk(n)=0

Case 20: a=5:b=10:c=15		;rash hit
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 21: a=5:b=10:c=14	;blueRay Impact
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic_(cc,1)
	If chunkSeq(n) > 1 Then chunk(n)=0
Case 22: a=5:b=10:c=14	;blueRay Impact 2
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic_(cc,1)
	If chunkSeq(n) > 1 Then chunk(n)=0

Case 23: a=5:b=10:c=14	;Power ball
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
	If chunkSeq(n) > 1 Then chunk(n)=0

Case 24: a=2:b=5:c=9	;fire going up
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1):yChunk(n)=yChunk(n)-1
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2):yChunk(n)=yChunk(n)-1
	If chunkSeq(n) > b Then chunk(n)=0

Case 25: a=2:b=5:c=9:d=12	;4 way explosion
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(cc,4):chunkPic_(n)=ptPic(cc,4)
	If chunkSeq(n) > d Then chunk(n)=0

Case 26: a=5:b=5:c=9:d=12	;batman bomb smoke
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(cc,4):chunkPic_(n)=ptPic(cc,4)
	If chunkSeq(n) > d Then chunk(n)=0

Case 27: a=8:b=16	;green ray impact
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic_(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 28: a=5:b=10:c=15	;little smoke
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 29: a=5:b=10:c=15	;little smoke going up
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(28,1):chunkPic_(n)=ptPic(28,1):yChunk(n)=yChunk(n)-1
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(28,2):chunkPic_(n)=ptPic(28,2):yChunk(n)=yChunk(n)-1
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(28,3):chunkPic_(n)=ptPic(28,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 30: a=5:b=10:c=15	;Ray ball impact
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(29,1):chunkPic_(n)=ptPic(29,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(29,2):chunkPic_(n)=ptPic(29,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(29,3):chunkPic_(n)=ptPic(29,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 31: a=4:b=8:c=12	;electricity
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(30,1):chunkPic_(n)=ptPic(30,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(30,2):chunkPic_(n)=ptPic(30,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(30,3):chunkPic_(n)=ptPic(30,3)
	If chunkSeq(n) > c Then chunk(n)=0

Case 32: a=5:b=10:c=14	;Red Ray
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(31,1):chunkPic_(n)= ptPic(31,1)
	If chunkSeq(n) > 1 Then chunk(n)=0
Case 33: a=5:b=10:c=14	;Red Ray 2
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(32,1):chunkPic_(n)= ptPic(32,1)
	If chunkSeq(n) > 1 Then chunk(n)=0

Case 34: a=6:b=12		;big rock breaking
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(33,1):chunkPic_(n)=ptPic(33,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(33,2):chunkPic_(n)=ptPic(33,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 35: a=6:b=12		;little rock breaking
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(34,1):chunkPic_(n)=ptPic(34,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(34,2):chunkPic_(n)=ptPic(34,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 36: a=5:b=10:c=15:d=20:e=25:f=30		;water splash
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(35,1):chunkPic_(n)=ptPic(35,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(35,2):chunkPic_(n)=ptPic(35,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(35,3):chunkPic_(n)=ptPic(35,3)
	If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(35,4):chunkPic_(n)=ptPic(35,4)
	If chunkSeq(n) > d And chunkSeq(n) =< e Then chunkPic(n)=ptPic(35,5):chunkPic_(n)=ptPic(35,5)
	If chunkSeq(n) > e And chunkSeq(n) =< f Then chunkPic(n)=ptPic(35,6):chunkPic_(n)=ptPic(35,6)
	If chunkSeq(n) > f Then chunk(n)=0

Case 37: a=5:b=10:c=20		;big web
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= zpic(3,10,3):chunkPic_(n)=zpic(3,10,3)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=zpic(3,10,4):chunkPic_(n)=zpic(3,10,4)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=zpic(3,10,5):chunkPic_(n)=zpic(3,10,5)
	If chunkSeq(n) > c Then chunk(n)=0

Case 38: 	;yellow ray
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(36,1):chunkPic_(n)= ptPic(36,1)
	If chunkSeq(n) > 1 Then chunk(n)=0
Case 39: 	;yellow ray 2
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(37,1):chunkPic_(n)= ptPic(37,1)
	If chunkSeq(n) > 1 Then chunk(n)=0
Case 51: 	;yellow Ray Impact
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(38,1):chunkPic_(n)= ptPic_(38,1)
	If chunkSeq(n) > 1 Then chunk(n)=0
Case 52: 	;yellow Ray Impact 2
	If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(39,1):chunkPic_(n)= ptPic_(39,1)
	If chunkSeq(n) > 1 Then chunk(n)=0


Case 40:a=10	;tutorial 1 - double jump
	
	message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1 
		chunkStr$(n,ln)=strInfo$(67):ln=ln+1
		chunkStr$(n,ln)=strInfo$(68)
		chunkLines(n)=ln
		chunkWidth(n)=500:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(1)=1
	EndIf
    If tutorial(1)=1 Then chunk(n)=0:message=0

Case 41:a=10	;tutorial 2 - up special
	message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1 
		chunkStr$(n,ln)=strInfo$(69):ln=ln+1
		chunkStr$(n,ln)=strInfo$(70):ln=ln+1
		chunkStr$(n,ln)=strInfo$(71)
		chunkLines(n)=ln
		chunkWidth(n)=500:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(2)=1
	EndIf
   	If tutorial(2)=1 Then chunk(n)=0:message=0
   	
Case 42:a=10	;tutorial 3 - fight
    message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(72):ln=ln+1
		chunkStr$(n,ln)=strInfo$(73):ln=ln+1
		chunkStr$(n,ln)=strInfo$(74):ln=ln+1
		chunkStr$(n,ln)=strInfo$(75)
		chunkLines(n)=ln
		chunkWidth(n)=460:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(3)=1
	EndIf
	If tutorial(3)=1 Then chunk(n)=0:message=0
 
Case 43:a=10	;tutorial 4 - use switch
    message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(76):ln=ln+1
		chunkStr$(n,ln)=strInfo$(77)
		chunkLines(n)=ln
		chunkWidth(n)=460:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(4)=1
	EndIf
	If tutorial(4)=1 Then chunk(n)=0:message=0
	
Case 44:a=10	;tutorial 5 - pick up item
	message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(78):ln=ln+1
		chunkStr$(n,ln)=strInfo$(79):ln=ln+1
		chunkStr$(n,ln)=strInfo$(80):ln=ln+1
		chunkStr$(n,ln)=strInfo$(81)
		chunkLines(n)=ln
		chunkWidth(n)=460:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(5)=1
	EndIf
	If tutorial(5)=1 Then chunk(n)=0:message=0
	
Case 45:a=10	;tutorial 6 - go down from platform
	message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(82):ln=ln+1
		chunkStr$(n,ln)=strInfo$(83):ln=ln+1
		chunkStr$(n,ln)=strInfo$(84)
		chunkLines(n)=ln
		chunkWidth(n)=460:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(6)=1
	EndIf
	If tutorial(6)=1 Then chunk(n)=0:message=0
	
Case 46:a=10	;tutorial 7 - throw item diagonally
    message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(85):ln=ln+1
		chunkStr$(n,ln)=strInfo$(86):ln=ln+1
		chunkStr$(n,ln)=strInfo$(87):ln=ln+1
 		chunkStr$(n,ln)=strInfo$(88):ln=ln+1
		chunkStr$(n,ln)=strInfo$(89)
		chunkLines(n)=ln
		chunkWidth(n)=460:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(7)=1
	EndIf
	If tutorial(7)=1 Then chunk(n)=0:message=0

Case 47:a=10	;tutorial 8 - super special
    message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(90):ln=ln+1
		chunkStr$(n,ln)=strInfo$(91):ln=ln+1
		chunkStr$(n,ln)=strInfo$(92):ln=ln+1
		chunkStr$(n,ln)=strInfo$(93)
		chunkLines(n)=ln
		chunkWidth(n)=560:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 3 Then
		Delay 1000
		waitInput()
		chunk(n)=0
		message=0
		tutorial(8)=1
	EndIf
	If tutorial(8)=1 Then chunk(n)=0:message=0

Case 48 ;special event for level #50, in the beginning, set Venom life according to amount of players
		;Delay item respawn from factory #2
    chunkPic(n)=ptPic(35,1):chunkPic_(n)=ptPic(35,1)
	If chunkSeq(n)=1 Then
      Select zamountPlaying
		Case 2:zLife(5)=zLife(5)+50
		Case 3:zLife(5)=zLife(5)+100
		Case 4:zLife(5)=zLife(5)+150
      End Select
	EndIf
	If chunkSeq(n) > 100 Then chunkSeq(n)=2
	If objAmount => 2 Then FdelaySeq(2) = FdelaySeq(2)-1

Case 49:a=200	;no air special allowed
    message=1 : messageN=n
	chunkCategory(n)=2
	chunkPic(n)= noPic:chunkPic_(n)= noPic
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
		ln=1
		chunkStr$(n,ln)=strInfo$(94)
		chunkLines(n)=ln
		chunkWidth(n)=420:chunkHeight(n)=(25 * ln)
	EndIf
	If chunkSeq(n) = 200 Then
		tutorial(9)=1 : chunk(n)=0 : message=0
	EndIf
	If tutorial(9)=1 Then chunk(n)=0:message=0


Case 50: a=7:b=15		;TMNT hit
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
	If chunkSeq(n) > b Then chunk(n)=0

Case 53: 		;Whip
	chunkPic(n)=ptPic(40,1):chunkPic_(n)=ptPic_(40,1)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 54: 		;Whip
	chunkPic(n)=ptPic(40,2):chunkPic_(n)=ptPic_(40,2)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 55: 		;Whip
	chunkPic(n)=ptPic(40,3):chunkPic_(n)=ptPic_(40,3)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 56: 		;Whip
	chunkPic(n)=ptPic(40,4):chunkPic_(n)=ptPic_(40,4)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 57: 		;Whip
	chunkPic(n)=ptPic(40,5):chunkPic_(n)=ptPic_(40,5)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 58: 		;Whip
	chunkPic(n)=ptPic(40,6):chunkPic_(n)=ptPic_(40,6)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 59: 		;Whip
	chunkPic(n)=ptPic(40,7):chunkPic_(n)=ptPic_(40,7)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 60: 		;Whip
	chunkPic(n)=ptPic(40,8):chunkPic_(n)=ptPic_(40,8)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 61	;scorpion spear
	a=3:b=a+3:c=b+3:d=c+3:e=d+3:f=e+3:g=f+72
	
	If chunkSeq(n) >= 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(83,1):chunkPic_(n)=ptPic_(83,1)
	If chunkSeq(n) >= a And chunkSeq(n) < b Then chunkPic(n)=ptPic(83,2):chunkPic_(n)=ptPic_(83,2)
	If chunkSeq(n) >= b And chunkSeq(n) < c Then chunkPic(n)=ptPic(83,3):chunkPic_(n)=ptPic_(83,3)
	If chunkSeq(n) >= c And chunkSeq(n) < d Then chunkPic(n)=ptPic(83,4):chunkPic_(n)=ptPic_(83,4)
	If chunkSeq(n) >= d And chunkSeq(n) < e Then chunkPic(n)=ptPic(83,5):chunkPic_(n)=ptPic_(83,5)
	If chunkSeq(n) >= e And chunkSeq(n) < f Then chunkPic(n)=ptPic(83,6):chunkPic_(n)=ptPic_(83,6)
	If chunkSeq(n) >= f And chunkSeq(n) < g Then chunkPic(n)=ptPic(83,7):chunkPic_(n)=ptPic_(83,7)

	If shotOwner(zMyShot(chunkOwner(n))) <> chunkOwner(n) Then
		If chunkDir(n)=2 Then 
			chunkDir(n)=4
		Else
			chunkDir(n)=2
		End If
		prevShot=zMyShot(chunkOwner(n))
		chunkOwner(n)=shotOwner(zMyShot(chunkOwner(n)))
		shotOwner(zMyShot(chunkOwner(n)))=chunkOwner(n)
		zMyShot(chunkOwner(n))=prevShot
		If chunkDir(n)=2 Then xChunk#(n)=xChunk#(n)-100
		If chunkDir(n)=4 Then xChunk#(n)=xChunk#(n)+100
	End If

	If projectileDeflectSpeed#(chunkOwner(n)) = 0 Then
		If chunkDir(n)=2 Then xChunk#(n)=xChunk#(n)+6
		If chunkDir(n)=4 Then xChunk#(n)=xChunk#(n)-6
	Else	
		If chunkDir(n)=2 Then xChunk#(n)=xChunk#(n)+(6*projectileDeflectSpeed#(chunkOwner(n)))
		If chunkDir(n)=4 Then xChunk#(n)=xChunk#(n)-(6*projectileDeflectSpeed#(chunkOwner(n)))
	End If

	If chunkSeq(n) <= e And zhit(chunkOwner(n))=1 Then chunk(n)=0

	If chunkSeq(n) >= g Or (shot(zMyShot(chunkOwner(n)))=0) Then chunk(n)=0
	
Case 62	;scorpion spear rope
	If chunkSeq(n)=1 Then chunkPic(n)=ptPic(84,1):chunkPic_(n)=ptPic(84,1)
	
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 63:		;pre-freeze ball
	chunkPic(n)=ptPic(41,1):chunkPic_(n)=ptPic_(41,1)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 64:		;pre-freeze ball
	chunkPic(n)=ptPic(41,2):chunkPic_(n)=ptPic_(41,2)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 65:		;pre-freeze ball
	chunkPic(n)=ptPic(41,3):chunkPic_(n)=ptPic_(41,3)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 66:		;pre-freeze ball
	chunkPic(n)=ptPic(41,4):chunkPic_(n)=ptPic_(41,4)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 67:		;pre-freeze ball
	chunkPic(n)=ptPic(41,5):chunkPic_(n)=ptPic_(41,5)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 68:		;pre ground freeze
	chunkPic(n)=ptPic(42,1):chunkPic_(n)=ptPic_(42,1)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 69:		;pre ground freeze
	chunkPic(n)=ptPic(42,2):chunkPic_(n)=ptPic_(42,2)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 70:		;pre ground freeze
	chunkPic(n)=ptPic(42,3):chunkPic_(n)=ptPic_(42,3)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 71:		;pre ground freeze
	chunkPic(n)=ptPic(42,4):chunkPic_(n)=ptPic_(42,4)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 72:		;pre ground freeze
	chunkPic(n)=ptPic(42,5):chunkPic_(n)=ptPic_(42,5)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 73:		;pre ground freeze
	chunkPic(n)=ptPic(42,6):chunkPic_(n)=ptPic_(42,6)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 74:		;pre ground freeze
	chunkPic(n)=ptPic(42,7):chunkPic_(n)=ptPic_(42,7)
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 75: a=5:b=10:c=14:d=18 		;freeze ball hit
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(44,1):chunkPic_(n)=ptPic_(44,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(44,2):chunkPic_(n)=ptPic_(44,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(44,3):chunkPic_(n)=ptPic_(44,3)
	If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(44,4):chunkPic_(n)=ptPic_(44,4)
	If chunkSeq(n) > d Then chunk(n)=0
Case 76: a=5:b=10:c=14:d=18 		;ground freeze hit
	;If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(43,1):chunkPic_(n)=ptPic(43,1)
	;If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(43,2):chunkPic_(n)=ptPic(43,2)
	;If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(43,3):chunkPic_(n)=ptPic(43,3)
	;If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(43,4):chunkPic_(n)=ptPic(43,4)
	;If chunkSeq(n) > d Then chunk(n)=0
	chunkPic(n)=noPic
Case 77: 		;berserker barrage slash1a
	a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40

	If (chunkSeq(n) >= 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(60,1):chunkPic_(n)=ptPic_(60,1)
	If (chunkSeq(n) >= a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(60,2):chunkPic_(n)=ptPic_(60,2)
	If (chunkSeq(n) >= b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(60,3):chunkPic_(n)=ptPic_(60,3)
	If (chunkSeq(n) >= c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(60,4):chunkPic_(n)=ptPic_(60,4)
	
	If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 78:		;berserker barrage slash1b
	a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40
	If (chunkSeq(n) >= 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(61,1):chunkPic_(n)=ptPic_(61,1)
	If (chunkSeq(n) >= a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(61,2):chunkPic_(n)=ptPic_(61,2)
	If (chunkSeq(n) >= b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(61,3):chunkPic_(n)=ptPic_(61,3)
	If (chunkSeq(n) >= c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(61,4):chunkPic_(n)=ptPic_(61,4)

	If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 79:		;berserker barrage slash2a
	a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40
	If (chunkSeq(n) = 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(62,1):chunkPic_(n)=ptPic_(62,1)
	If (chunkSeq(n) = a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(62,2):chunkPic_(n)=ptPic_(62,2)
	If (chunkSeq(n) = b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(62,3):chunkPic_(n)=ptPic_(62,3)
	If (chunkSeq(n) = c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(62,4):chunkPic_(n)=ptPic_(62,4)
	
	If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 80:		;berserker barrage slash2b
	a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40
	If (chunkSeq(n) >= 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(63,1):chunkPic_(n)=ptPic_(63,1)
	If (chunkSeq(n) >= a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(63,2):chunkPic_(n)=ptPic_(63,2)
	If (chunkSeq(n) >= b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(63,3):chunkPic_(n)=ptPic_(63,3)
	If (chunkSeq(n) >= c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(63,4):chunkPic_(n)=ptPic_(63,4)
	
	If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 81:		;berserker slash chunk
	a=3:b=7:c=10:d=14:e=17:f=21
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(64,1):chunkPic_(n)=ptPic_(64,1)
	If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(64,2):chunkPic_(n)=ptPic_(64,2)
	If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(64,3):chunkPic_(n)=ptPic_(64,3)
	If chunkSeq(n) => c And chunkSeq(n) < d Then chunkPic(n)=ptPic(64,4):chunkPic_(n)=ptPic_(64,4)
	If chunkSeq(n) => d And chunkSeq(n) < e Then chunkPic(n)=ptPic(64,5):chunkPic_(n)=ptPic_(64,5)
	If chunkSeq(n) => e And chunkSeq(n) < f Then chunkPic(n)=ptPic(64,6):chunkPic_(n)=ptPic_(64,6)
	
	If chunkSeq(n) > f Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 82:		;X slash1
	a=1
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(65,1):chunkPic_(n)=ptPic_(65,1)
	
	If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 83:		;X slash2
	a=1
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(65,2):chunkPic_(n)=ptPic_(65,2)
	
	If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 84:		;X slash3a
	a=13
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then 
		If chunkSeq(n) Mod 2 = 1 Then 
			chunkPic(n)=ptPic(65,3):chunkPic_(n)=ptPic_(65,3)
		Else
			chunkPic(n)=ptPic(65,4):chunkPic_(n)=ptPic_(65,4)
		EndIf
	EndIf
	
	If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 85:		;X slash3b
	a=13
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then 
		If chunkSeq(n) Mod 2 = 1 Then 
			chunkPic(n)=ptPic(66,1):chunkPic_(n)=ptPic_(66,1)
		Else
			chunkPic(n)=ptPic(66,2):chunkPic_(n)=ptPic_(66,2)
		EndIf
	EndIf
	
	If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
	
Case 86:		;X slash4a
	a=3:b=6:c=9
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(67,1):chunkPic_(n)=ptPic_(67,1)
	If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(67,2):chunkPic_(n)=ptPic_(67,2)
	If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(67,3):chunkPic_(n)=ptPic_(67,3)
	
	If chunkSeq(n) = c Then chunk(n)=0
	
Case 87:		;X slash4b
	a=3:b=6:c=9
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(68,1):chunkPic_(n)=ptPic_(68,1)
	If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(68,2):chunkPic_(n)=ptPic_(68,2)
	If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(68,3):chunkPic_(n)=ptPic_(68,3)
	
	If chunkSeq(n) = c Then chunk(n)=0
		
Case 88:		;Faint slash
	a=3:b=a+3:c=b+3:d=c+3:e=d+3
	If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(69,1):chunkPic_(n)=ptPic(69,1)
	If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(69,2):chunkPic_(n)=ptPic(69,2)
	If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(69,3):chunkPic_(n)=ptPic(69,3)
	If chunkSeq(n) => c And chunkSeq(n) < d Then chunkPic(n)=ptPic(69,4):chunkPic_(n)=ptPic(69,4)
	If chunkSeq(n) => d And chunkSeq(n) < e Then chunkPic(n)=ptPic(69,5):chunkPic_(n)=ptPic(69,5)
	
	If chunkSeq(n) = e Then chunk(n)=0
	
Case 89:		;Dust
	If chunkSeq(n) => 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic(70,1):chunkPic_(n)=ptPic_(70,1)
	If chunkSeq(n) => 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(70,2):chunkPic_(n)=ptPic_(70,2)
	If chunkSeq(n) => 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic(70,3):chunkPic_(n)=ptPic_(70,3)
	If chunkSeq(n) => 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(70,4):chunkPic_(n)=ptPic_(70,4)
	If chunkSeq(n) => 5 And chunkSeq(n) < 6 Then chunkPic(n)=ptPic(70,5):chunkPic_(n)=ptPic_(70,5)
	If chunkSeq(n) => 6 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(70,6):chunkPic_(n)=ptPic_(70,6)
	If chunkSeq(n) => 7 And chunkSeq(n) < 8 Then chunkPic(n)=ptPic(70,7):chunkPic_(n)=ptPic_(70,7)
	If chunkSeq(n) => 8 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(70,8):chunkPic_(n)=ptPic_(70,8)
	If chunkSeq(n) => 9 And chunkSeq(n) < 10 Then chunkPic(n)=ptPic(70,9):chunkPic_(n)=ptPic_(70,9)
	
	If chunkSeq(n) = 10 Then chunk(n)=0
	
Case 90:		;Silhouette
	If chunkSeq(n) >= 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic(71,1):chunkPic_(n)=ptPic_(71,1)
	If chunkSeq(n) >= 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(71,2):chunkPic_(n)=ptPic_(71,2)
	If chunkSeq(n) >= 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic(71,3):chunkPic_(n)=ptPic_(71,3)
	If chunkSeq(n) >= 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(71,4):chunkPic_(n)=ptPic_(71,4)
	If chunkSeq(n) >= 5 And chunkSeq(n) < 6 Then chunkPic(n)=ptPic(71,5):chunkPic_(n)=ptPic_(71,5)
	
	If chunkSeq(n) = 6 Then chunk(n)=0
	
Case 91:		;Small X slashA
	If chunkSeq(n) >= 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic(72,1):chunkPic_(n)=ptPic_(72,1)
	If chunkSeq(n) >= 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(72,2):chunkPic_(n)=ptPic_(72,2)
	If chunkSeq(n) >= 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic(72,3):chunkPic_(n)=ptPic_(72,3)
	If chunkSeq(n) >= 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(72,4):chunkPic_(n)=ptPic_(72,4)
	
	If chunkSeq(n) = 5 Then chunk(n)=0
	
Case 92: 		;Small X slashB
	If chunkSeq(n) >= 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic_(72,1):chunkPic_(n)=ptPic(72,1)
	If chunkSeq(n) >= 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic_(72,2):chunkPic_(n)=ptPic(72,2)
	If chunkSeq(n) >= 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic_(72,3):chunkPic_(n)=ptPic(72,3)
	If chunkSeq(n) >= 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic_(72,4):chunkPic_(n)=ptPic(72,4)
	
	If chunkSeq(n) = 5 Then chunk(n)=0
	
Case 93:		;Berserker aura
	If chunkSeq(n) >= 1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(73,1):chunkPic_(n)=ptPic(73,1)
	If chunkSeq(n) >= 3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(73,2):chunkPic_(n)=ptPic(73,2)
	If chunkSeq(n) >= 5 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(73,3):chunkPic_(n)=ptPic(73,3)
	If chunkSeq(n) >= 7 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(73,4):chunkPic_(n)=ptPic(73,4)
	If chunkSeq(n) >= 9 And chunkSeq(n) < 11 Then chunkPic(n)=ptPic(73,5):chunkPic_(n)=ptPic(73,5)
	If chunkSeq(n) >= 11 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(73,6):chunkPic_(n)=ptPic(73,6)
	If chunkSeq(n) >= 13 And chunkSeq(n) < 15 Then chunkPic(n)=ptPic(73,7):chunkPic_(n)=ptPic(73,7)
	
	If chunkSeq(n) = 15 Then chunk(n)=0

Case 94:		;no chunk
	If chunkSeq(n) >= 1 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(74,1):chunkPic_(n)=ptPic(74,1)
	
	If chunkSeq(n) = 5 Then chunk(n)=0
	
Case 95:		;Blood
	If chunkSeq(n) >=1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(75,1):chunkPic_(n)=ptPic_(75,1)
	If chunkSeq(n) >=3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(75,2):chunkPic_(n)=ptPic_(75,2)
	If chunkSeq(n) >=5 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(75,3):chunkPic_(n)=ptPic_(75,3)
	If chunkSeq(n) >=7 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(75,4):chunkPic_(n)=ptPic_(75,4)
	If chunkSeq(n) >=9 And chunkSeq(n) < 11 Then chunkPic(n)=ptPic(75,5):chunkPic_(n)=ptPic_(75,5)
	If chunkSeq(n) >=11 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(75,6):chunkPic_(n)=ptPic_(75,6)
	If chunkSeq(n) >=13 And chunkSeq(n) < 15 Then chunkPic(n)=ptPic(75,7):chunkPic_(n)=ptPic_(75,7)
	If chunkSeq(n) >=15 And chunkSeq(n) < 17 Then chunkPic(n)=ptPic(75,8):chunkPic_(n)=ptPic_(75,8)
	
	If chunkSeq(n) = 17 Then chunk(n)=0

Case 96:		;Blood2
	If chunkSeq(n) >=1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(76,1):chunkPic_(n)=ptPic(76,1)
	If chunkSeq(n) >=3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(76,2):chunkPic_(n)=ptPic(76,2)
	If chunkSeq(n) >=5 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(76,3):chunkPic_(n)=ptPic(76,3)
	If chunkSeq(n) >=7 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(76,4):chunkPic_(n)=ptPic(76,4)
	If chunkSeq(n) >=9 And chunkSeq(n) < 11 Then chunkPic(n)=ptPic(76,5):chunkPic_(n)=ptPic(76,5)
	If chunkSeq(n) >=11 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(76,6):chunkPic_(n)=ptPic(76,6)
	If chunkSeq(n) >=13 And chunkSeq(n) < 15 Then chunkPic(n)=ptPic(76,7):chunkPic_(n)=ptPic(76,7)
	If chunkSeq(n) >=15 And chunkSeq(n) < 17 Then chunkPic(n)=ptPic(76,8):chunkPic_(n)=ptPic(76,8)
	If chunkSeq(n) >=17 And chunkSeq(n) < 19 Then chunkPic(n)=ptPic(76,9):chunkPic_(n)=ptPic(76,9)
	If chunkSeq(n) >=19 And chunkSeq(n) < 21 Then chunkPic(n)=ptPic(76,10):chunkPic_(n)=ptPic(76,10)
	If chunkSeq(n) >=21 And chunkSeq(n) < 23 Then chunkPic(n)=ptPic(76,11):chunkPic_(n)=ptPic(76,11)
	If chunkSeq(n) >=23 And chunkSeq(n) < 25 Then chunkPic(n)=ptPic(76,12):chunkPic_(n)=ptPic(76,12)
	If chunkSeq(n) >=25 And chunkSeq(n) < 27 Then chunkPic(n)=ptPic(76,13):chunkPic_(n)=ptPic(76,13)

	If chunkSeq(n) = 27 Then chunk(n)=0

Case 97:		;Berserker slash2 FX
	If chunkSeq(n) >=1 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(77,1):chunkPic_(n)=ptPic_(77,1)
	If chunkSeq(n) >=5 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(77,2):chunkPic_(n)=ptPic_(77,2)
	If chunkSeq(n) >=9 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(77,3):chunkPic_(n)=ptPic_(77,3)
	If chunkSeq(n) >=13 And chunkSeq(n) < 17 Then chunkPic(n)=ptPic(77,4):chunkPic_(n)=ptPic_(77,4)
	
	If chunkSeq(n) = 17 Then chunk(n)=0

Case 98:		;Toasty fx
	If chunkSeq(n) >= 1 And chunkSeq(n) < 60 Then chunkPic(n)=ptPic(78,1):chunkPic_(n)=ptPic(78,1)

	If chunkSeq(n) >= 60 Then chunk(n)=0
	
Case 99:		;Ice shower chunk
	If chunkSeq(n) >= 1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(79,10):chunkPic_(n)=ptPic_(79,10)
	If chunkSeq(n) >= 3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(79,9):chunkPic_(n)=ptPic_(79,9)
	If chunkSeq(n) >= 5 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(79,8):chunkPic_(n)=ptPic_(79,8)
	If chunkSeq(n) >= 7 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(79,7):chunkPic_(n)=ptPic_(79,7)
	If chunkSeq(n) >= 9 And chunkSeq(n) < 11 Then chunkPic(n)=ptPic(79,6):chunkPic_(n)=ptPic_(79,6)
	If chunkSeq(n) >= 11 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(79,5):chunkPic_(n)=ptPic_(79,5)
	If chunkSeq(n) >= 13 And chunkSeq(n) < 15 Then chunkPic(n)=ptPic(79,4):chunkPic_(n)=ptPic_(79,4)
	If chunkSeq(n) >= 15 And chunkSeq(n) < 17 Then chunkPic(n)=ptPic(79,3):chunkPic_(n)=ptPic_(79,3)
	If chunkSeq(n) >= 17 And chunkSeq(n) < 19 Then chunkPic(n)=ptPic(79,2):chunkPic_(n)=ptPic_(79,2)
	If chunkSeq(n) >= 19 And chunkSeq(n) < 21 Then chunkPic(n)=ptPic(79,1):chunkPic_(n)=ptPic_(79,1)
	
	If chunkSeq(n) = 21 Then chunk(n)=0

Case 100:		;Ice shower chunk2
	If chunkSeq(n) >= 1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(80,1):chunkPic_(n)=ptPic(80,1)
	If chunkSeq(n) >= 3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(80,2):chunkPic_(n)=ptPic(80,2)
	
	If chunkSeq(n) = 5 Then chunk(n)=0
	
Case 101:		;Ice impale
	a=5:b=a+5:c=b+5:d=c+165
	If chunkSeq(n) >= 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(81,1):chunkPic_(n)=ptPic_(81,1)
	If chunkSeq(n) >= a And chunkSeq(n) < b Then chunkPic(n)=ptPic(81,2):chunkPic_(n)=ptPic_(81,2)
	If chunkSeq(n) >= b And chunkSeq(n) < c Then chunkPic(n)=ptPic(81,3):chunkPic_(n)=ptPic_(81,3)
	If chunkSeq(n) >= c And chunkSeq(n) < d Then chunkPic(n)=ptPic(81,4):chunkPic_(n)=ptPic_(81,4)
	
	If chunkSeq(n) = d Then chunk(n)=0

Case 102: 		;berserker slash2 after-image
	a=12

	If chunkSeq(n) >= 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(82,1):chunkPic_(n)=ptPic_(82,1)
	
	If chunkSeq(n) >= a Then chunk(n)=0
	
Case 103:		;spear head
	If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(85,1):chunkPic_(n)=ptPic_(85,1)
	
	If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0 
	
Case 104:		;spear disappearing
	If chunkSeq(n) >= 1 And chunkSeq(n) < 6 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,1)
	If chunkSeq(n) >= 6 And chunkSeq(n) < 10 Then chunkPic(n)=ptPic(86,2):chunkPic_(n)=ptPic_(86,2)
	If chunkSeq(n) >= 10 And chunkSeq(n) < 16 Then chunkPic(n)=ptPic(86,3):chunkPic_(n)=ptPic_(86,3)
	If chunkSeq(n) >= 16 And chunkSeq(n) < 19 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,1)
	If chunkSeq(n) >= 19 And chunkSeq(n) < 22 Then chunkPic(n)=ptPic(86,2):chunkPic_(n)=ptPic_(86,2)
	If chunkSeq(n) >= 22 And chunkSeq(n) < 25 Then chunkPic(n)=ptPic(86,3):chunkPic_(n)=ptPic_(86,3)
	If chunkSeq(n) = 25 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,1)
	If chunkSeq(n) = 26 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,2)
	If chunkSeq(n) = 27 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,3)
	
	If chunkSeq(n) > 27 Then chunk(n)=0
	
Case 105:		;flame
	If chunkSeq(n) >= 1 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic(87,1):chunkPic_(n)=ptPic_(87,1)
	If chunkSeq(n) >= 4 And chunkSeq(n) < 8 Then chunkPic(n)=ptPic(87,2):chunkPic_(n)=ptPic_(87,2)
	If chunkSeq(n) >= 8 And chunkSeq(n) < 12 Then chunkPic(n)=ptPic(87,3):chunkPic_(n)=ptPic_(87,3)
	If chunkSeq(n) >= 12 And chunkSeq(n) < 16 Then chunkPic(n)=ptPic(87,4):chunkPic_(n)=ptPic_(87,4)
	If chunkSeq(n) >= 16 And chunkSeq(n) < 20 Then chunkPic(n)=ptPic(87,5):chunkPic_(n)=ptPic_(87,5)
	If chunkSeq(n) >= 20 And chunkSeq(n) < 24 Then chunkPic(n)=ptPic(87,6):chunkPic_(n)=ptPic_(87,6)
	If chunkSeq(n) >= 24 And chunkSeq(n) < 28 Then chunkPic(n)=ptPic(87,7):chunkPic_(n)=ptPic_(87,7)
	If chunkSeq(n) >= 28 And chunkSeq(n) < 32 Then chunkPic(n)=ptPic(87,8):chunkPic_(n)=ptPic_(87,8)
	If chunkSeq(n) >= 32 And chunkSeq(n) < 36 Then chunkPic(n)=ptPic(87,9):chunkPic_(n)=ptPic_(87,9)
	
	If chunkSeq(n)=36 Or (chunkSeq(n) >= 28 And zhit(chunkOwner(n))=1) Then chunk(n)=0
	
Case 106:		;hand from hell
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(88,1):chunkPic_(n)=ptPic_(88,1)
	If chunkSeq(n) >= 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(88,2):chunkPic_(n)=ptPic_(88,2)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(88,3):chunkPic_(n)=ptPic_(88,3)
	If chunkSeq(n) >= 10 And chunkSeq(n) <= 12 Then chunkPic(n)=ptPic(88,4):chunkPic_(n)=ptPic_(88,4)
	If chunkSeq(n) >= 13 And chunkSeq(n) <= 15 Then chunkPic(n)=ptPic(88,5):chunkPic_(n)=ptPic_(88,5)
	If chunkSeq(n) >= 16 And chunkSeq(n) <= 18 Then chunkPic(n)=ptPic(88,6):chunkPic_(n)=ptPic_(88,6)
	If chunkSeq(n) >= 19 And chunkSeq(n) <= 21 Then chunkPic(n)=ptPic(88,7):chunkPic_(n)=ptPic_(88,7)
	If chunkSeq(n) >= 22 And chunkSeq(n) <= 24 Then chunkPic(n)=ptPic(88,8):chunkPic_(n)=ptPic_(88,8)
	If chunkSeq(n) >= 25 And chunkSeq(n) <= 27 Then chunkPic(n)=ptPic(88,9):chunkPic_(n)=ptPic_(88,9)
	If chunkSeq(n) >= 28 And chunkSeq(n) <= 31 Then chunkPic(n)=ptPic(88,10):chunkPic_(n)=ptPic_(88,10)
	If chunkSeq(n) >= 32 And chunkSeq(n) <= 35 Then chunkPic(n)=ptPic(88,11):chunkPic_(n)=ptPic_(88,11)
	If chunkSeq(n) >= 36 And chunkSeq(n) <= 39 Then chunkPic(n)=ptPic(88,12):chunkPic_(n)=ptPic_(88,12)
	If chunkSeq(n) >= 40 And chunkSeq(n) <= 43 Then chunkPic(n)=ptPic(88,13):chunkPic_(n)=ptPic_(88,13)
	If chunkSeq(n) >= 44 And chunkSeq(n) <= 47 Then chunkPic(n)=ptPic(88,14):chunkPic_(n)=ptPic_(88,14)
	If chunkSeq(n) >= 48 And chunkSeq(n) <= 51 Then chunkPic(n)=ptPic(88,15):chunkPic_(n)=ptPic_(88,15)
	
	If chunkSeq(n)=51 Or (chunkSeq(n) < 25 And zHit(n)=1) Then chunk(n)=0
	
Case 107:		;pre-fireball
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(89,1):chunkPic_(n)=ptPic_(89,1)
	
	If chunkSeq(n)=4 Then chunk(n)=0
	
Case 108:		;post-fireball
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(90,1):chunkPic_(n)=ptPic_(90,1)
	If chunkSeq(n) >= 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(90,2):chunkPic_(n)=ptPic_(90,2)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(90,3):chunkPic_(n)=ptPic_(90,3)
	If chunkSeq(n) >= 10 And chunkSeq(n) <= 12 Then chunkPic(n)=ptPic(90,4):chunkPic_(n)=ptPic_(90,4)
	If chunkSeq(n) >= 13 And chunkSeq(n) <= 15 Then chunkPic(n)=ptPic(90,5):chunkPic_(n)=ptPic_(90,5)
	If chunkSeq(n) >= 16 And chunkSeq(n) <= 18 Then chunkPic(n)=ptPic(90,6):chunkPic_(n)=ptPic_(90,6)
	If chunkSeq(n) >= 19 And chunkSeq(n) <= 21 Then chunkPic(n)=ptPic(90,7):chunkPic_(n)=ptPic_(90,7)
	If chunkSeq(n) >= 22 And chunkSeq(n) <= 24 Then chunkPic(n)=ptPic(90,8):chunkPic_(n)=ptPic_(90,8)
	If chunkSeq(n) >= 25 And chunkSeq(n) <= 27 Then chunkPic(n)=ptPic(90,9):chunkPic_(n)=ptPic_(90,9)
	If chunkSeq(n) >= 28 And chunkSeq(n) <= 30 Then chunkPic(n)=ptPic(90,10):chunkPic_(n)=ptPic_(90,10)
	If chunkSeq(n) >= 31 And chunkSeq(n) <= 33 Then chunkPic(n)=ptPic(90,11):chunkPic_(n)=ptPic_(90,11)
	If chunkSeq(n) >= 34 And chunkSeq(n) <= 36 Then chunkPic(n)=ptPic(90,12):chunkPic_(n)=ptPic_(90,12)
	If chunkSeq(n) >= 37 And chunkSeq(n) <= 39 Then chunkPic(n)=ptPic(90,13):chunkPic_(n)=ptPic_(90,13)
	If chunkSeq(n) >= 40 And chunkSeq(n) <= 42 Then chunkPic(n)=ptPic(90,14):chunkPic_(n)=ptPic_(90,14)
	
	If chunkSeq(n)=42 Then chunk(n)=0
	
Case 109:		;Caught something with Lasso (Long)
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(91,1):chunkPic_(n)=ptPic_(91,1)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 10 Then chunkPic(n)=ptPic(91,2):chunkPic_(n)=ptPic_(91,2)
	If chunkSeq(n) >= 11 And chunkSeq(n) <= 14 Then chunkPic(n)=ptPic(91,3):chunkPic_(n)=ptPic_(91,3)
	If chunkSeq(n) >= 15 And chunkSeq(n) <= 23 Then chunkPic(n)=ptPic(91,4):chunkPic_(n)=ptPic_(91,4)
	If chunkSeq(n) >= 24 And chunkSeq(n) <= 30 Then chunkPic(n)=ptPic(91,5):chunkPic_(n)=ptPic_(91,5)
	If chunkSeq(n) >= 31 And chunkSeq(n) <= 36 Then chunkPic(n)=ptPic(91,6):chunkPic_(n)=ptPic_(91,6)
	If chunkSeq(n) >= 37 And chunkSeq(n) <= 41 Then chunkPic(n)=ptPic(91,7):chunkPic_(n)=ptPic_(91,7)
	If chunkSeq(n) >= 42 And chunkSeq(n) <= 45 Then chunkPic(n)=ptPic(91,8):chunkPic_(n)=ptPic_(91,8)
	If chunkSeq(n) >= 46 And chunkSeq(n) <= 53 Then chunkPic(n)=ptPic(91,9):chunkPic_(n)=ptPic_(91,9)
	If chunkSeq(n) >= 54 And chunkSeq(n) <= 60 Then chunkPic(n)=ptPic(91,10):chunkPic_(n)=ptPic_(91,10)
	If chunkSeq(n) >= 61 And chunkSeq(n) <= 66 Then chunkPic(n)=ptPic(91,11):chunkPic_(n)=ptPic_(91,11)
	If chunkSeq(n) >= 67 And chunkSeq(n) <= 71 Then chunkPic(n)=ptPic(91,12):chunkPic_(n)=ptPic_(91,12)
	
	If chunkSeq(n)=71 Then chunk(n)=0
	
Case 110:		;Caught something with Lasso (short)
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(92,1):chunkPic_(n)=ptPic_(92,1)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 10 Then chunkPic(n)=ptPic(92,2):chunkPic_(n)=ptPic_(92,2)
	If chunkSeq(n) >= 11 And chunkSeq(n) <= 14 Then chunkPic(n)=ptPic(92,3):chunkPic_(n)=ptPic_(92,3)
	If chunkSeq(n) >= 15 And chunkSeq(n) <= 23 Then chunkPic(n)=ptPic(92,4):chunkPic_(n)=ptPic_(92,4)
	If chunkSeq(n) >= 24 And chunkSeq(n) <= 30 Then chunkPic(n)=ptPic(92,5):chunkPic_(n)=ptPic_(92,5)
	If chunkSeq(n) >= 31 And chunkSeq(n) <= 36 Then chunkPic(n)=ptPic(92,6):chunkPic_(n)=ptPic_(92,6)
	If chunkSeq(n) >= 37 And chunkSeq(n) <= 41 Then chunkPic(n)=ptPic(92,7):chunkPic_(n)=ptPic_(92,7)
	If chunkSeq(n) >= 42 And chunkSeq(n) <= 45 Then chunkPic(n)=ptPic(92,8):chunkPic_(n)=ptPic_(92,8)
	If chunkSeq(n) >= 46 And chunkSeq(n) <= 53 Then chunkPic(n)=ptPic(92,9):chunkPic_(n)=ptPic_(92,9)
	If chunkSeq(n) >= 54 And chunkSeq(n) <= 60 Then chunkPic(n)=ptPic(91,10):chunkPic_(n)=ptPic_(91,10)
	If chunkSeq(n) >= 61 And chunkSeq(n) <= 66 Then chunkPic(n)=ptPic(91,11):chunkPic_(n)=ptPic_(91,11)
	If chunkSeq(n) >= 67 And chunkSeq(n) <= 71 Then chunkPic(n)=ptPic(91,12):chunkPic_(n)=ptPic_(91,12)
	
	If chunkSeq(n)=71 Then chunk(n)=0
	
Case 111:		;WW Dash


Case 112: 		;Ground effect 1
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(93,1):chunkPic_(n)=ptPic_(93,1)
	If chunkSeq(n) >= 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(93,2):chunkPic_(n)=ptPic_(93,2)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(93,3):chunkPic_(n)=ptPic_(93,3)
	If chunkSeq(n) >= 10 And chunkSeq(n) <= 12 Then chunkPic(n)=ptPic(93,4):chunkPic_(n)=ptPic_(93,4)
	If chunkSeq(n) >= 13 And chunkSeq(n) <= 15 Then chunkPic(n)=ptPic(93,5):chunkPic_(n)=ptPic_(93,5)
	If chunkSeq(n) >= 16 And chunkSeq(n) <= 18 Then chunkPic(n)=ptPic(93,6):chunkPic_(n)=ptPic_(93,6)
	If chunkSeq(n) >= 19 And chunkSeq(n) <= 21 Then chunkPic(n)=ptPic(93,7):chunkPic_(n)=ptPic_(93,7)
	If chunkSeq(n) >= 22 And chunkSeq(n) <= 24 Then chunkPic(n)=ptPic(93,8):chunkPic_(n)=ptPic_(93,8)
	If chunkSeq(n) >= 25 And chunkSeq(n) <= 27 Then chunkPic(n)=ptPic(93,9):chunkPic_(n)=ptPic_(93,9)
	If chunkSeq(n) >= 28 And chunkSeq(n) <= 30 Then chunkPic(n)=ptPic(93,10):chunkPic_(n)=ptPic_(93,10)
	If chunkSeq(n) >= 31 And chunkSeq(n) <= 33 Then chunkPic(n)=ptPic(93,11):chunkPic_(n)=ptPic_(93,11)
	
	If chunkSeq(n)=34 Then chunk(n)=0

Case 113:		;Earthquake chunk creation
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 2 Then chunkPic(n)=ptPic(94,1):chunkPic_(n)=ptPic_(94,1)
	If chunkSeq(n) >= 3 And chunkSeq(n) <= 5 Then chunkPic(n)=ptPic(94,2):chunkPic_(n)=ptPic_(94,2)
	If chunkSeq(n) >= 5 And chunkSeq(n) <= 7 Then chunkPic(n)=ptPic(94,3):chunkPic_(n)=ptPic_(94,3)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(94,4):chunkPic_(n)=ptPic_(94,4)
	If chunkSeq(n)=10 Then chunk(n)=0
	
Case 114:		;Earthquake chunk destruction
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 2 Then chunkPic(n)=ptPic(94,7):chunkPic_(n)=ptPic_(94,7)
	If chunkSeq(n) >= 3 And chunkSeq(n) <= 5 Then chunkPic(n)=ptPic(94,8):chunkPic_(n)=ptPic_(94,8)
	If chunkSeq(n) >= 5 And chunkSeq(n) <= 7 Then chunkPic(n)=ptPic(94,9):chunkPic_(n)=ptPic_(94,9)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(94,10):chunkPic_(n)=ptPic_(94,10)
	If chunkSeq(n) >= 9 And chunkSeq(n) <= 11 Then chunkPic(n)=ptPic(94,11):chunkPic_(n)=ptPic_(94,11)
	If chunkSeq(n)=12 Then chunk(n)=0
	
Case 115:		;Earthquake 2 chunk destruction
	If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(95,1):chunkPic_(n)=ptPic_(95,1)
	If chunkSeq(n) >= 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(95,2):chunkPic_(n)=ptPic_(95,2)
	If chunkSeq(n) >= 7 And chunkSeq(n) <= 10 Then chunkPic(n)=ptPic(95,3):chunkPic_(n)=ptPic_(95,3)
	If chunkSeq(n) >= 11 And chunkSeq(n) <= 13 Then chunkPic(n)=ptPic(95,4):chunkPic_(n)=ptPic_(95,4)
	If chunkSeq(n) >= 14 And chunkSeq(n) <= 16 Then chunkPic(n)=ptPic(95,5):chunkPic_(n)=ptPic_(95,5)
	If chunkSeq(n)=16 Then chunk(n)=0

Default
	a=5:b=10:c=14	;Blocking
	If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
	If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
	If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(1,3):chunkPic_(n)= ptPic(1,3)
	If chunkSeq(n) > c Then chunk(n)=0

End Select

If chunk(n)=0 And n = chunkAmount Then chunkAmount=n-1

End Function
;----------------------------- Object Data-------------------------------------------------------
Function objData(n,e)

objHitChunk(n)=5
objNoGrav(n)=0
objTrailType(n)=0
objSuper(n)=0
objHitMode(n)=0
objHitXspeed(n)=3
objHitYspeed(n)=2
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

Select objType(n)
Case 1	;yellow shell
	objXspeed(n)=7
	objSize(n)=14
	objYSpeed(n)=-1
	objYForce(n)=-6
	objdamage(n)=25
	objHeight(n)=16
	objSide(n)=7
	objImpact(n)=12
	If e=0 Then objLife(n)=50
	BeatIten(n)=0
	objHitSound(n)=tucupSnd
	objPic(n,1)=obj1P
	objPic_(n,1)=obj1P

Case 2	;MedKit
	objXspeed(n)=3
	objSize(n)=14
	objYSpeed(n)=-1
	objYForce(n)=-3
	objdamage(n)=10
	objHeight(n)=16
	objSide(n)=7
	objImpact(n)=15
	If e=0 Then objLife(n)=20
	BeatIten(n)=0
	objHitSound(n)=highpunchSnd
	objPic(n,1)=obj2P
	objPic_(n,1)=obj2P
	objEat(n)=1

Case 3	;Green shell
	objXspeed(n)=7
	objSize(n)=14
	objYSpeed(n)=-1
	objYForce(n)=-6
	objdamage(n)=20
	objHeight(n)=16
	objSide(n)=7
	objImpact(n)=10
	If e=0 Then objLife(n)=20
	BeatIten(n)=0
	objHitSound(n)=tucupSnd
	objPic(n,1)=obj3P
	objPic_(n,1)=obj3P

Case 4  ;Explosive Barrel
	objXspeed(n)=6
	objSize(n)=14
	objYSpeed(n)=-1
	objYForce(n)=-5
	objdamage(n)=3
	objHeight(n)=16
	objSide(n)=7
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
	objXspeed(n)=2.5
	objSize(n)=14
	objYSpeed(n)=-1
	objYForce(n)=-2.5
	objdamage(n)=2
	objHeight(n)=16
	objSide(n)=objSize(n)/2
	objImpact(n)=20
	objExplosive(n)=4	;defines what will do when obj hits something!
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
	zCurWeapon(e)=2		;type # for weapon drawing
	zShootThis(e)=10	;type of bullet from weapon
	zPushedForce(e)=shotPushForce(n)
	xObjHand(n)=8

Case 8	;Acid spit
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

Case 9	;batman little bomb
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

Case 10	;Bazooka
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
	zCurWeapon(e)=3		;type # for weapon drawing
	zShootThis(e)=23	;type of bullet from weapon
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
	zCurWeapon(e)=4		;type # for weapon drawing
	zShootThis(e)=25	;type of bullet from weapon
	zPushedForce(e)=shotPushForce(n)
	xObjHand(n)=12 ;: yObjHand(n)=-8

Case 12	;Ray ball
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

Case 13	;Hammer
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

Case 14	;dragon fire ball
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

Case 15	;cannon ball
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

Case 16	;Flying bat
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

Case 17	;big rock
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

Case 18	;little rock
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


Case 19	;little lava rock
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

Case 20	;axe
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
Case 1	;obj explosion
	makeExp(n,x,y,kind)
	makeChunk(n,x,y,2,4)
Case 2	;alien spit
	makeChunk(n,x,y,2,objHitChunk(n))
Case 3	;explosion = object hit chunk
	If gamesound And inSight(x,y) Then PlaySound objExplosionSound(n)
	makeChunk(n,x,y,2,objHitChunk(n))
Case 4	;helpers
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
	;sn=4	;test
	If sn=1 Then	; laser turret
		curGuy(nn)=43
		initZ(nn)
		zon(nn)=1
		zx(nn)=x
		zy(nn)=y-15
		zFace(nn)=4 ;facDir(n,curF(n))
		zLives(nn)=0
		AiLevel(nn)=1	;makes it last only for a while (see moves2.bb)
		zTeam(nn)=zTeam(objOwner(n))
		zDeadEvent(nn)=0

		If gamesound Then PlaySound clickSnd
		ztempShield(nn)=0
		zTrail(nn)=0
		aiGetTarget(nn)
	EndIf
	If sn=2 And noAirStrike=0 Then	; bombing ship (air strike)
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
	If sn=3 Then	; ray balls cannon
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
Select expType(n)
Case 1
	expDamage(n)=40
	expHeight(n)=40
	expSide(n)=20
	expImpact(n)=50
	explosionSound(n)=explodeSnd

End Select

End Function
;------------------Load sprites ------------------------------------
Function loadPics(n)

gfxdir$="gfx\" + n + "\"
guyLoaded(n)=1

zpic(n,20,1)=LoadImage(gfxdir$ + "zSuperPic.bmp")
zpic(n,0,0)=LoadImage(gfxStuffDir$ + "frozen.bmp")
zpic_(n,0,0)=LoadImage(gfxStuffDir$ + "frozen_.bmp")
zpic(n,0,1)=LoadImage(gfxStuffDir$ + "frozen_small.bmp")
zpic_(n,0,1)=LoadImage(gfxStuffDir$ + "frozen_small_.bmp")
zpic(n,0,2)=LoadImage(gfxdir$ + "zfrozen.bmp")
zpic_(n,0,2)=LoadImage(gfxdir$ + "zfrozen_.bmp")

For i=0 To 30
	zpic(n,1,i)=LoadImage(gfxdir$ + "zwalk" + i + ".bmp")
	zpic_(n,1,i)=LoadImage(gfxdir$ + "zwalk" + i + "_.bmp")
Next

zpic(n,2,0)=LoadImage(gfxdir$ + "zfallen.bmp")
zpic_(n,2,0)=LoadImage(gfxdir$ + "zfallen_.bmp")

For i=1 To 7
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

For i=1 To 6
	zpic(n,5,i)=LoadImage(gfxdir$ + "zFlip" + i + ".bmp")
	zpic_(n,5,i)=LoadImage(gfxdir$ + "zFlip" + i + "_.bmp")
Next

For i=1 To 15
	zpic(n,6,i)=LoadImage(gfxdir$ + "zblow" + i + ".bmp")
	zpic_(n,6,i)=LoadImage(gfxdir$ + "zblow" + i + "_.bmp")
Next

For i=1 To 20
	zpic(n,7,i)=LoadImage(gfxdir$ + "zuspecial" + i + ".bmp")
	zpic_(n,7,i)=LoadImage(gfxdir$ + "zuspecial" + i + "_.bmp")
Next

For i=1 To 20
	zpic(n,8,i)=LoadImage(gfxdir$ + "zflykick" + i + ".bmp")
	zpic_(n,8,i)=LoadImage(gfxdir$ + "zflykick" + i + "_.bmp")
Next

For i=1 To 16
	zpic(n,9,i)=LoadImage(gfxdir$ + "zlowkick" + i + ".bmp")
	zpic_(n,9,i)=LoadImage(gfxdir$ + "zlowkick" + i + "_.bmp")
Next

For i=1 To 20
	zpic(n,10,i)=LoadImage(gfxdir$ + "zspecial" + i + ".bmp")
	zpic_(n,10,i)=LoadImage(gfxdir$ + "zspecial" + i + "_.bmp")
Next

For i=1 To 2
	zpic(n,11,i)=LoadImage(gfxdir$ + "zshot" + i + ".bmp")
	zpic_(n,11,i)=LoadImage(gfxdir$ + "zshot" + i + "_.bmp")
Next

For i=1 To 9
	zpic(n,12,i)=LoadImage(gfxdir$ + "zDspecial" + i + ".bmp")
	zpic_(n,12,i)=LoadImage(gfxdir$ + "zDspecial" + i + "_.bmp")
Next

For i=1 To 3
	zpic(n,13,i)=LoadImage(gfxdir$ + "zblock" + i + ".bmp")
	zpic_(n,13,i)=LoadImage(gfxdir$ + "zblock" + i + "_.bmp")
Next

For counter = 1 To 16
	zpic(n,14,counter)=LoadImage(gfxdir$ + "zupblow" + counter + ".bmp")
	zpic_(n,14,counter)=LoadImage(gfxdir$ + "zupblow" + counter + "_.bmp")
Next

For counter = 1 To 40
	zpic(n,15,counter)=LoadImage(gfxdir$ + "zgrab" + counter + ".bmp")
	zpic_(n,15,counter)=LoadImage(gfxdir$ + "zgrab" + counter + "_.bmp")
Next

For counter = 1 To 22
	zpic(n,16,counter)=LoadImage(gfxdir$ + "counter\zcounter" + counter + ".bmp")
	zpic_(n,16,counter)=LoadImage(gfxdir$ + "counter\zcounter" + counter + "_.bmp")
Next

For counter = 1 To 20
	zpic(n,17,counter)=LoadImage(gfxdir$ + "superspecial\zsuper" + counter + ".bmp")
	zpic_(n,17,counter)=LoadImage(gfxdir$ + "superspecial\zsuper" + counter + "_.bmp")
Next

For counter = 1 To 40
	zpic(n,18,counter)=LoadImage(gfxdir$ + "extraspecial\zextraspecial" + counter + ".bmp")
	zpic_(n,18,counter)=LoadImage(gfxdir$ + "extraspecial\zextraspecial" + counter + "_.bmp")
Next

For counter = 1 To 20
	zpic(n,19,counter)=LoadImage(gfxdir$ + "stance\zstance" + counter + ".bmp")
	zpic_(n,19,counter)=LoadImage(gfxdir$ + "stance\zstance" + counter + "_.bmp")
Next

For counter = 1 To 20
	zpic(n,21,counter)=LoadImage(gfxdir$ + "run\zRun" + counter + ".bmp")
	zpic_(n,21,counter)=LoadImage(gfxdir$ + "run\zRun" + counter + "_.bmp")
Next

For counter = 1 To 20
	zpic(n,22,counter)=LoadImage(gfxdir$ + "combo\zCombo" + counter + ".bmp")
	zpic_(n,22,counter)=LoadImage(gfxdir$ + "combo\zCombo" + counter + "_.bmp")
Next

For counter = 1 To 15
	zpic(n,23,counter)=LoadImage(gfxdir$ + "dizzy\zDizzy" + counter + ".bmp")
	zpic_(n,23,counter)=LoadImage(gfxdir$ + "dizzy\zDizzy" + counter + "_.bmp")
Next

;add character (stuff the must be loaded the first time, such as sounds. Don't worry about the pics)

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

If n=49 Then	;Dragon
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

If n=47 Then	;soldier
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

If n=42 Then	;Joker
	zpic(n,3,1)=zpic(n,1,0)
	zpic_(n,3,1)=zpic_(n,1,0)
	For i=1 To 4
		zpic(n,5,i)=zpic(n,1,2)
		zpic_(n,5,i)=zpic_(n,1,2)
	Next
	If jokerSnd=0 Then jokerSnd=LoadSound(soundsdir$ + "joker.wav")
EndIf

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
	If wwGrunt1Snd=0 Then wwGrunt1Snd=LoadSound(soundsdir$ + "wonderwoman\wwGrunt1.wav")
	If wwWhip1Snd=0 Then wwWhip1Snd=LoadSound(soundsdir$ + "wonderwoman\wwWhip1.wav")
	If wwWhip2Snd=0 Then wwWhip2Snd=LoadSound(soundsdir$ + "wonderwoman\wwWhip2.wav")
	If wwWhipHitSnd=0 Then wwWhipHitSnd=LoadSound(soundsdir$ + "wonderwoman\wwWhipHit.wav")
	If wwLassoSnd=0 Then wwLassoSnd=LoadSound(soundsdir$ + "wonderwoman\wwLasso.wav")
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
EndIf

If n=12 Then	;Scorpion
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

If n=10 Then ;Ritcher
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

If n=8 Then 	;Predator
	zpic(n,2,0)=zpic(n,2,4)
	zpic_(n,2,0)=zpic_(n,2,4)
	If PredatorSnd=0 Then PredatorSnd=LoadSound(soundsdir$ + "predator.wav")
EndIf

If n=7 Then ;Batman
	If CapeSnd=0 Then CapeSnd=LoadSound(soundsdir$ + "cape.wav")
EndIf

If n=6 Or n=51 Then
    If swordSnd=0 Then swordSnd=LoadSound(soundsdir$ + "sword.wav")
	If hayabusaSnd=0 Then hayabusaSnd=LoadSound(soundsdir$ + "hayabusa.wav")
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

If n=1 Then
    If hueSnd=0 Then hueSnd=LoadSound(soundsdir$ + "hue.wav")
    If uppercutsnd=0 Then uppercutsnd=LoadSound(soundsdir$ + "uppercut.wav")
    If ryuBallsnd=0 Then ryuBallsnd=LoadSound(soundsdir$ + "ryuball.wav")
    If ryuSpinsnd=0 Then ryuSpinsnd=LoadSound(soundsdir$ + "ryuspin.wav")
EndIf

If n=41 Then 	;Turtle CLoud
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

If n=40 Then	;Turtle
	zpic(n,1,3)=zpic(n,1,0)
	zpic_(n,1,3)=zpic_(n,1,0)
	zpic(n,2,2)=zpic(n,2,1)
	zpic_(n,2,2)=zpic_(n,2,1)
	zpic(n,2,4)=zpic(n,2,0)
	zpic_(n,2,4)=zpic_(n,2,0)
	zpic(n,2,7)=zpic(n,2,6)
	zpic_(n,2,7)=zpic_(n,2,6)
	zpic(n,4,1)=zpic(n,1,3)
	zpic_(n,4,1)=zpic_(n,1,3)
	zpic(n,3,1)=zpic(n,1,0)
	zpic_(n,3,1)=zpic_(n,1,0)
	zpic(n,6,5)=zpic_(n,6,3)
	zpic_(n,6,5)=zpic(n,6,3)
	For i=1 To 4
	zpic(n,5,i)=zpic(n,1,0)
	zpic_(n,5,i)=zpic_(n,1,0)
	Next
EndIf

If n=39 Then	;Thief
	zpic(n,4,1)=zpic(n,1,3)
	zpic_(n,4,1)=zpic_(n,1,3)
	zpic(n,3,1)=zpic(n,1,0)
	zpic_(n,3,1)=zpic_(n,1,0)
	For i=1 To 4
	zpic(n,5,i)=zpic(n,1,0)
	zpic_(n,5,i)=zpic_(n,1,0)
	Next
EndIf

If n=38 Then	;Bowser
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

If n=36 Or n=37 Then	;gargola, Red plant
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

If n=35 Then	;red horns with shield
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

If n=30 Then	;pig
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

If n=33 Then	;Shredder
	For i=2 To 4
	zpic(n,5,i)=zpic(n,5,1)
	zpic_(n,5,i)=zpic_(n,5,1)
	Next
	If shredderSnd=0 Then shredderSnd=LoadSound(soundsdir$ + "shredder.wav")
	If shredder2Snd=0 Then shredder2Snd=LoadSound(soundsdir$ + "shredder2.wav")
EndIf

If n=32 Then	;foot clan
	zpic(n,3,1)=zpic(n,1,0)
	zpic_(n,3,1)=zpic_(n,1,0)
	For i=1 To 4
	zpic(n,5,i)=zpic(n,4,1)
	zpic_(n,5,i)=zpic_(n,4,1)
	Next
EndIf

If n=31 Then
	zpic(n,2,2)=zpic(n,2,1)		;alien
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

End Function