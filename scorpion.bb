Function performSweepKick(n)
	a=102:b=a+3:c=b+2:d=c+3:e=d+2:f=e+3:g=f+3:h=g+3:i=h+3
	aa=i+2:bb=aa+3:cc=bb+2:dd=cc+3:ee=dd+2:ff=ee+3:gg=ff+3:hh=gg+3:ii=hh+3
	endSeq=25

	If (zBlowSeq(n) => 100 And zBlowSeq(n) < a) Or (zBlowSeq(n) => i And zBlowSeq(n) < aa) Then zani(n)=9:zf(n)=1
	If (zBlowSeq(n) => a And zBlowSeq(n) < b) Or (zBlowSeq(n) => aa And zBlowSeq(n) < bb) Then zani(n)=9:zf(n)=2:moveX(n,zBlowdir(n),1.2)
	If (zBlowSeq(n) => b And zBlowSeq(n) < c) Or (zBlowSeq(n) => bb And zBlowSeq(n) < cc) Then zani(n)=9:zf(n)=3:moveX(n,zBlowdir(n),2.4)
	If (zBlowSeq(n) => c And zBlowSeq(n) < d) Or (zBlowSeq(n) => cc And zBlowSeq(n) < dd) Then zani(n)=9:zf(n)=4:moveX(n,zBlowdir(n),3.6)
	If (zBlowSeq(n) => d And zBlowSeq(n) < e) Or (zBlowSeq(n) => dd And zBlowSeq(n) < ee) Then zani(n)=9:zf(n)=5:moveX(n,zBlowdir(n),3.6)
	If (zBlowSeq(n) = d Or zBlowSeq(n) = dd) And gameSound Then PlaySound mkKickSnd
	If (zBlowSeq(n) => e And zBlowSeq(n) < f) Or (zBlowSeq(n) => ee And zBlowSeq(n) < ff) Then
		zblowPamount(n)=3
		xblow(n,1)=0: yblow(n,1)=9:wblow(n,1)=25:hblow(n,1)=1
		xblow(n,2)=0: yblow(n,2)=5:wblow(n,2)=28:hblow(n,2)=1
		xblow(n,3)=0: yblow(n,3)=1:wblow(n,3)=28:hblow(n,3)=1
		zHitMode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=12:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=mkKickHitSnd
		zani(n)=9:zf(n)=6
	EndIf
	If (zBlowSeq(n) => f And zBlowSeq(n) < g) Or (zBlowSeq(n) => ff And zBlowSeq(n) < gg) Then zani(n)=9:zf(n)=7:moveX(n,zBlowdir(n),2.4)
	If (zBlowSeq(n) => g And zBlowSeq(n) < h) Or (zBlowSeq(n) => gg And zBlowSeq(n) < hh) Then zani(n)=9:zf(n)=8:moveX(n,zBlowdir(n),1.2)
	If (zBlowSeq(n) => h And zBlowSeq(n) < i) Or (zBlowSeq(n) => hh And zBlowSeq(n) < ii) Then zani(n)=9:zf(n)=9
	If zBlowSeq(n) = ii Then zBlowSeq(n)=endSeq

End Function

Function applyScorpionComboHitBox(n, hitMode, damage, hitSnd)
	zblowPamount(n)=1:nn=1
	xblow(n,nn)=10: yblow(n,nn)=40:wblow(n,nn)=36:hblow(n,nn)=22:nn=nn+1
	zHitMode(n)=hitMode:zBlowHold(n)=0
	zHitSpeed#(n)=0:zHitUpSpeed#(n)=0:zHitTime(n)=0
	zBlowDamage(n)=damage:zBLowEffect(n)=1:zBlowImpact(n)=12:zBlowStillTime(n)=0:zBlowBlockTime(n)=25
	zBlowSound(n)=hitSnd
End Function

Function performScorpionCombo1(n)
	a=390:b=a+4:c=b+11:d=c+3:e=d+3:f=e+3:g=f+11:h=g+2:i=h+2
	j=i+2:k=j+2:l=k+2:m=l+11:n1=m+3:o=n1+3:p=o+3:q=p+3:r=q+3:s=r+3:t=s+3
	endSeq=32
	If (zBlowSeq(n) <= o+3) Then enemyControlInit(n,zx(n),zy(n)-39,40,39,0,guardable)
	
	If zBlowSeq(n)>=387 And zBlowSeq(n) < c And zComboMode(n)=0 Then movex2(n,zface(n),1+(Abs(zSpeed#(n))/1.5))
	
;----- animations -----
	If zBlowSeq(n)>=387 And zBlowSeq(n) < a Then zani(n)=22:zf(n)=1
	If zBlowSeq(n)>=a And zBlowSeq(n) < b Then zani(n)=22:zf(n)=2
	If zBlowSeq(n)>=b And zBlowSeq(n) < c Then
		zani(n)=22:zf(n)=3
		If zBlowSeq(n) > b+3 And KeyDown(specialK(n))=1 Then zBlowSeq(n)=c
		If zBlowSeq(n) = c-1 And KeyDown(specialK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=c And zBlowSeq(n) < d Then zani(n)=22:zf(n)=4
	If zBlowSeq(n)>=d And zBlowSeq(n) < e Then zani(n)=22:zf(n)=5
	If zBlowSeq(n)>=e And zBlowSeq(n) < f Then zani(n)=22:zf(n)=6
	If zBlowSeq(n)>=f And zBlowSeq(n) < g Then
		zani(n)=22:zf(n)=7
		If zBlowSeq(n) > f+3 And KeyDown(blockK(n))=1 Then zBlowSeq(n)=g
		If zBlowSeq(n) = g-1 And KeyDown(blockK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=g And zBlowSeq(n) < h Then zani(n)=22:zf(n)=8
	If zBlowSeq(n)>=h And zBlowSeq(n) < i Then zani(n)=22:zf(n)=9
	If zBlowSeq(n)>=i And zBlowSeq(n) < j Then zani(n)=22:zf(n)=10
	If zBlowSeq(n)>=j And zBlowSeq(n) < k Then zani(n)=22:zf(n)=11
	If zBlowSeq(n)>=k And zBlowSeq(n) < l Then zani(n)=22:zf(n)=12
	If zBlowSeq(n)>=l And zBlowSeq(n) < m Then 
		zani(n)=22:zf(n)=13
		If zBlowSeq(n) > l+3 And KeyDown(grabK(n))=1 Then zBlowSeq(n)=m
		If zBlowSeq(n) = m-1 And KeyDown(grabK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=m And zBlowSeq(n) < n1 Then zani(n)=6:zf(n)=1
	If zBlowSeq(n)>=n1 And zBlowSeq(n) < o Then zani(n)=6:zf(n)=2
	If zBlowSeq(n)>=o And zBlowSeq(n) < p Then zani(n)=6:zf(n)=3
	If zBlowSeq(n)>=p And zBlowSeq(n) < q Then zani(n)=6:zf(n)=4
	If zBlowSeq(n)>=q And zBlowSeq(n) < r Then zani(n)=6:zf(n)=5
	If zBlowSeq(n)>=r And zBlowSeq(n) < s Then zani(n)=6:zf(n)=6
	If zBlowSeq(n)>=s And zBlowSeq(n) < t Then zani(n)=6:zf(n)=7
	If zBlowSeq(n)>=t And zBlowSeq(n) < u Then zani(n)=6:zf(n)=8
;----------------------
	isHitting=0
;----- hit boxes ------
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then
		applyScorpionComboHitBox(n, 2, 5, scorpionSpearHitSnd)
		If zOnGnd(zControlsThis(n))=0 Then zy(zControlsThis(n))=zy(n)
		If zComboMode(n)=0 movex2(zControlsThis(n),zface(zControlsThis(n)),-1*(1+(Abs(zSpeed#(n))/1.5)))
		isHitting=1
	End If
	If zBlowSeq(n)=c-1 And zControls(n)=0 Then zBlowSeq(n)=endSeq
	
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then
		applyScorpionComboHitBox(n, 2, 5, mkStrongHitSnd)
		isHitting=1
	End If
	If zBlowSeq(n)=f And zControls(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) >= l And zBlowSeq(n) < m Then
		applyScorpionComboHitBox(n, 2, 6, mkStrongHitSnd)
		isHitting=1
	End If
	If zBlowSeq(n)=j And zControls(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) >= o+3 And zBlowSeq(n) < o+6 Then
		applyScorpionComboHitBox(n, 0, 6, mkStrongHitSnd)
		isHitting=1
	End If
	If zBlowSeq(n)=o-3 And zControls(n)=0 Then zBlowSeq(n)=endSeq
;------ target manipulation --------
	en=zControlsThis(n)
	If zBlowSeq(en)=0 And zCurBlow(en)=0 Then zNoGrav(en)=1:zantiPlat(en)=1
	
	If isHitting=1 Then
		If zParalyzed(en)=1 Then zani(en)=2:zf(en)=3
	Else
		If zParalyzed(en)=1 Then zani(en)=2:zf(en)=1
	End If

	If zBlowSeq(n) = s Then zControls(n)=0:zBlowSeq(n)=endSeq
End Function


Function performScorpionCombo2(n)
	a=41:b=a+11:c=b+5:d=c+11:e=d+4:f=e+4:g=f+4:h=g+4:i=h+11:j=i+4:k=j+11:l=k+4:m=l+4
	endSeq=32
	If (zBlowSeq(n) <= j+3) Then enemyControlInit(n,zx(n),zy(n)-39,40,39,0,guardable)
	
	If zBlowSeq(n)>=36 And zBlowSeq(n) < b And zComboMode(n)=0 Then movex2(n,zface(n),1+(Abs(zSpeed#(n))/1.5))
	
;----- animations -----
	If zBlowSeq(n)>=36 And zBlowSeq(n) < a Then zani(n)=22:zf(n)=14
	If zBlowSeq(n)>=a And zBlowSeq(n) < b Then
		zani(n)=22:zf(n)=15
		If zBlowSeq(n) > a+3 And KeyDown(specialK(n))=1 Then zBlowSeq(n)=b
		If zBlowSeq(n) = b-1 And KeyDown(specialK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=b And zBlowSeq(n) < c Then zani(n)=22:zf(n)=16
	If zBlowSeq(n)>=c And zBlowSeq(n) < d Then
		zani(n)=22:zf(n)=17
		If zBlowSeq(n) > c+3 And KeyDown(blockK(n))=1 Then zBlowSeq(n)=d
		If zBlowSeq(n) = d-1 And KeyDown(blockK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=e And zBlowSeq(n) < f Then zani(n)=22:zf(n)=18
	If zBlowSeq(n)>=f And zBlowSeq(n) < g Then zani(n)=22:zf(n)=19
	If zBlowSeq(n)>=g And zBlowSeq(n) < h Then zani(n)=22:zf(n)=14
	If zBlowSeq(n)>=h And zBlowSeq(n) < i Then 
		zani(n)=22:zf(n)=15
		If zBlowSeq(n) > h+3 And KeyDown(grabK(n))=1 Then zBlowSeq(n)=i
		If zBlowSeq(n) = i-1 And KeyDown(grabK(n))=0 Then zBlowSeq(n)=endSeq
	End If
	If zBlowSeq(n)>=i And zBlowSeq(n) < j Then zani(n)=22:zf(n)=16
	If zBlowSeq(n)>=j And zBlowSeq(n) < k Then zani(n)=22:zf(n)=17
	If zBlowSeq(n)>=k And zBlowSeq(n) < l Then zani(n)=22:zf(n)=18
	If zBlowSeq(n)>=l And zBlowSeq(n) < m Then zani(n)=22:zf(n)=19

;----------------------
	isHitting=0
;----- hit boxes ------
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then
		applyScorpionComboHitBox(n, 2, 5, mkStrongHitSnd)
		If zOnGnd(zControlsThis(n))=0 Then zy(zControlsThis(n))=zy(n)
		If zComboMode(n)=0 movex2(zControlsThis(n),zface(zControlsThis(n)),-1*(1+(Abs(zSpeed#(n))/1.5)))
		isHitting=1
	End If
	If zBlowSeq(n)=b-1 And zControls(n)=0 Then zBlowSeq(n)=endSeq
	
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then
		applyScorpionComboHitBox(n, 2, 5, mkStrongHitSnd)
		isHitting=1
	End If
	If zBlowSeq(n)=c And zControls(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then
		applyScorpionComboHitBox(n, 2, 6, mkStrongHitSnd)
		isHitting=1
	End If
	If zBlowSeq(n)=h And zControls(n)=0 Then zBlowSeq(n)=endSeq

	If zBlowSeq(n) >= j+3 And zBlowSeq(n) < k Then
		applyScorpionComboHitBox(n, 0, 6, mkStrongHitSnd)
		isHitting=1
	End If

;------ target manipulation --------
	en=zControlsThis(n)
	If zBlowSeq(en)=0 And zCurBlow(en)=0 Then zNoGrav(en)=1:zantiPlat(en)=1
	
	If isHitting=1 Then
		If zParalyzed(en)=1 Then zani(en)=2:zf(en)=3
	Else
		If zParalyzed(en)=1 Then zani(en)=2:zf(en)=1
	End If
	If zBlowSeq(n) = m Then zControls(n)=0:zBlowSeq(n)=endSeq
End Function


Function DoScorpion(n)

zFace(n)=zBlowDir(n)
zBlowEffect(n)=0
	If zBlowStill(n)=1 Then
		zBlowStillSeq(n)=zBlowStillSeq(n)+1
		If zBlowStillSeq(n) > zBlowStillTime(n) Then zBlowStill(n)=0
		Goto noBlowSeq1
	EndIf
zBlowSeq(n)=zBlowSeq(n)+1:
.noBlowSeq1
zCHunkType(n)=50

DebugLog "zBlowSeq(n): " + zBlowSeq(n)

Select zCurBlow(n)
Case 0	;Blocking
	zNoMove(n)=1:zNoJump(n)=1:zSuperBar(n)=100
	zBlock(n)=1:zani(n)=13:zf(n)=1	;normal blocking
	If zblocked(n)=1 Then zani(n)=13:zf(n)=2
	If blockKey(n)=0 And zBLocked(n)=0 Then zBlowSeq(n)=0:zBlow(n)=0;:zBlock(n)=0
	
Case 1	;Kick
	a=4: b=8: c=12: d=16: e=20: f=24: g=28: h=32: i=36: j=387
	zNoMove(n)=1:zNoJump(n)=1
	zChunkType(n)=95

	If zFace(n)=2 Then
		If zBlowSeq(n) = 1 And (isRunning(n) Or (zComboMode(n)=1 And rightKey(n))) Then zBlowSeq(n)=i:isRunning(n)=0
		If zBlowSeq(n) = 1 And zComboMode(n)=1 And rightKey(n)=0 Then zBlowSeq(n)=j
	Else
		If zBlowSeq(n) = 1 And (isRunning(n) Or (zComboMode(n)=1 And leftKey(n))) Then zBlowSeq(n)=i:isRunning(n)=0
		If zBlowSeq(n) = 1 And zComboMode(n)=1 And leftKey(n)=0 Then zBlowSeq(n)=j
	End If
	If zBlowSeq(n) >= i And zBlowSeq(n) < j Then performScorpionCombo2(n)
	If zBlowSeq(n) >= j Then performScorpionCombo1(n)
	
	If zBlowSeq(n) = d And gameSound Then PlaySound mkKick2Snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=6:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=6:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=6:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=6:zf(n)=4
	If zBlowseq(n) > d And zblowseq(n) =< e Then 
		zani(n)=6:zf(n)=5
		zblowPamount(n)=3:nn=1
		xblow(n,nn)=18: yblow(n,nn)=40:wblow(n,nn)=15:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=12: yblow(n,nn)=30:wblow(n,nn)=12:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=6: yblow(n,nn)=28:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=18:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
		zBlowSound(n)=mkStrongHitSnd
	EndIf
	If zBlowseq(n) > e And zblowseq(n) =< f Then zani(n)=6:zf(n)=4
	If zBlowseq(n) > f And zblowseq(n) =< g Then zani(n)=6:zf(n)=6
	If zBlowseq(n) > g And zblowseq(n) =< h Then zani(n)=6:zf(n)=7
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zBlowSeq(n)=0:zBlow(n)=0
	
Case 2	;Flying Kick
	a=5: b=a+5: c=b+8: d=c+10
	zNoJump(n)=0:ZJUMPING(N)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=8:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=8:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=8:zf(n)=3
	If zBlowSeq(n) = b And gameSound=1 Then PlaySound mkPunchSnd
	If zBlowSeq(n) > b And zBlowSeq(n) < c Then
		zblowpamount(n)=4
		nn=1
		xblow(n,nn)=19: yblow(n,nn)=-7:wblow(n,nn)=9:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=12: yblow(n,nn)=1:wblow(n,nn)=14:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=16: yblow(n,nn)=14:wblow(n,nn)=20:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=3: yblow(n,nn)=22:wblow(n,nn)=25:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=16:zBlowBlockTime(n)=25
		zBlowSound(n)=mkHitSnd
	EndIf
	If zBlowSeq(n) > d Then zBlowSeq(n)=0:zBlow(n)=0:zBlowStill(n)=0
	If zongnd(n)=1 And zBlowStill(n)=0 Then zBlow(n)=0:zblowseq(n)=0
	
Case 4	;Low kick
	zNoMove(n)=1:zNoJump(n)=1
	zheight(n)=zduckheight(n)
	a=2:b=5:c=7:d=10:e=12:f=15:g=18:h=21:i=24
	aa=100
	
	If zBlowSeq(n)=1 And downKeyDoubleTap(n)=1 Then zBlowSeq(n)=aa:downKeyDoubleTap(n)=0
	If zBlowSeq(n) >= aa Then performSweepKick(n)
	
	If zBlowSeq(n) => 1 And zBlowSeq(n) < a Then zani(n)=9:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=9:zf(n)=2
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=9:zf(n)=3
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=9:zf(n)=4
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=9:zf(n)=5
	If zBlowSeq(n) = d And gameSound Then PlaySound mkKickSnd
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then
		zblowPamount(n)=3
		xblow(n,1)=0: yblow(n,1)=9:wblow(n,1)=25:hblow(n,1)=1
		xblow(n,2)=0: yblow(n,2)=5:wblow(n,2)=28:hblow(n,2)=1
		xblow(n,3)=0: yblow(n,3)=1:wblow(n,3)=28:hblow(n,3)=1
		zHitMode(n)=0:zBlowHold(n)=10
		zBlowDamage(n)=12:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=10:zBlowBlockTime(n)=30
		zBlowSound(n)=mkKickHitSnd
		zani(n)=9:zf(n)=6
	EndIf
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=9:zf(n)=7
	If zBlowSeq(n) => g And zBlowSeq(n) < h Then zani(n)=9:zf(n)=8
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=9:zf(n)=9
	If zBlowSeq(n) >= i And zBlowSeq(n) < aa Then zBlowSeq(n)=0:zBlow(n)=0:zduck(n)=1

Case 5	;Upward Teleport
	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	a=2:b=2:c=6:d=9:e=13:f=e+8:g=f+15:h=g+8
	upLimit=77
	zBlowUpLimit(n)=zy(n)-upLimit:zJump(n)=0

	If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then zani(n)=1:zf(n)=0:moveX(n,zBlowdir(n),.5)
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=12:zf(n)=1:moveX(n,zBlowdir(n),.5)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=12:zf(n)=2:moveX(n,zBlowdir(n),.5)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=12:zf(n)=3:moveX(n,zBlowdir(n),.5)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=12:zf(n)=4:moveX(n,zBlowdir(n),.5)
	If zBlowSeq(n) = e-1 And gameSound Then PlaySound scorpionTeleportSnd
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then 
		zani(n)=7:zf(n)=1
		zy(n)=zy(n)-5:moveX(n,zBlowdir(n),3.5)
		zblowpamount(n)=4
		nn=1
		xblow(n,nn)=23: yblow(n,nn)=14:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=19: yblow(n,nn)=26:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=31:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=36:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=2:zBlowHold(n)=8
		zFallSpeed(en)=5:zUpFallSpeed(en)=7:zFallTime(en)=80
		zBlowDamage(n)=5:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=2:zBlowBlockTime(n)=5
		zBlowSound(n)=mkHitSnd
	End If
	
	If zBlowSeq(n) = f Then
		If zFace(n)=2 Then zx(n)=zx(n)-55
		If zFace(n)=4 Then zx(n)=zx(n)+55
	End If
	
	If zBlowSeq(n) = g-1 And gameSound Then PlaySound scorpionTeleportSnd
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then 
		zani(n)=7:zf(n)=1
		If zHitHead(n)=0 Then zy(n)=zy(n)-7
		moveX(n,zBlowdir(n),9.4)
		zblowpamount(n)=4
		nn=1
		xblow(n,nn)=23: yblow(n,nn)=14:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=19: yblow(n,nn)=26:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=31:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=36:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=2:zBlowHold(n)=8
		zBlowDamage(n)=8:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=2:zBlowBlockTime(n)=5
		zBlowSound(n)=mkHitSnd
	End If

	If zy(n) <= zBlowUpLimit(n) Then zBlowSeq(n)=h
	If zBlowSeq(n) => h Then zani(n)=4:zf(n)=1:zNoGrav(n)=0:ztopSpeed(n)=.5:zNomove(n)=0
	If zongnd(n)=1 And zBlowSeq(n) => h-2 Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
	
Case 6	;throwing iten
	a=2:b=3:c=6:d=8
	If zongnd(n)=1 Then zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) => a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=3
	If zBlowSeq(n)= b Then
		throwObj(n,zx(n),zy(n)-20,zFace(n))
		If gameSound Then PlaySound throwsnd
	EndIf
	If zBlowSeq(n) => b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) => c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) => d Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0
Case 7	;Scorpion Spear
	a=3:b=6:c=9:d=52:e=100:f=e+40:g=f+4:h=g+4:i=h+4:j=i+36:k=j+100:l=k+30
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	zNoGrav(n)=1
	Local guardable=0
	If zBlowSeq(n) = 1 And shot(zMyShot(n))=1 And (shotOwner(zMyshot(n)) = n Or shotType(zMyShot(n)) = 45) Then zBlowSeq(n)=0:zBlow(n)=0
	If zBlowSeq(n) < c And shot(zMyShot(n))=1 Then zNoGrav(n)=0 
	If zBlowSeq(n) = 1 And shot(zMyShot(n))=0 Then zControlsThis(n)=0
	If zBlowSeq(n)=1 And spellCooldownSeq(n, 1) > 0 Then
		If gameSound And zAi(n)=0 Then PlaySound clockTickSnd
		zBlowSeq(n)=0:zBlow(n)=0
	End If

	If zhit(n)=1 And zBlowSeq(n) > d-15 Then shot(zMyShot(n))=0
	If zBlowSeq(n) = c And gameSound=1 And (shot(zMyShot(n)) = 0 Or shotOwner(zMyShot(n)) <> n Or shotType(zMyShot(n)) <> 45) Then 
		PlaySound scorpionSpearSnd
	End If
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) < b Then zani(n)=10:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) < c Then zani(n)=10:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) < d Then zani(n)=10:zf(n)=4
	If zBlowSeq(n) = c Then
		dir=zface(n):y=zy(n)-zheight(n)+10:y2=y-3
		If zface(n)=2 Then x=zx(n)+14:x2=x-50
		If zface(n)=4 Then x=zx(n)-14:x2=x+50
		If (shot(zMyShot(n)) = 0 Or shotOwner(zMyshot(n)) <> n Or shotType(zMyShot(n)) <> 45)
			zMyShot(n)=getShot()
			makeshot(n,45,x,y-5,dir)
			extraObj(n,x2,0,y2,0,zblowdir(n),61)
		End If
	EndIf

	If zBlowSeq(n) >= c And zBlowSeq(n) < d-15 Then
		If zControlsThis(n) <> 0 And shot(zMyShot(n)) = 0 And shotOwner(zMyShot(n)) = n Then zBlowSeq(n)=e
		If zControlsThis(n) = 0 Then enemyControlInit(n,xShot(zMyShot(n))+5,yShot(zMyShot(n))-10,15,60,0,guardable)
		If zBlock(zControlsThis(n))=1 Then zBlowSeq(n)=k
		If zControlsThis(n) <> 0 And zBlock(zControlsThis(n))=0 And shot(zMyShot(n)) = 0 Then 
			If zOnGnd(n)=0 And zOn(zControlsThis(n))=1 Then 
				zy(n) = zy(zControlsThis(n))-1
				zy(zControlsThis(n))=zy(n)
				If zHeight(zControlsThis(n)) <= 40 Then zy(zControlsThis(n))=zy(n)-20
			End If
			isDizzy(zControlsThis(n))=1:dizzyDuration(zControlsThis(n))=3000
			freezeVictim(zControlsThis(n), 0)
			zBlowSeq(n)=e
		End If
	End If
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then
		If zBlowSeq(n) = e+2 Then shot(zMyShot(n))=0
		zani(n)=10:zf(n)=4
	End If
	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then
		zani(n)=10:zf(n)=5
		If zBlowSeq(n) = f Then
			randSeed = Rand(2)
			If randSeed = 1 And gameSound Then PlaySound scorpionComeHereSnd
			If randSeed = 2 And gameSound Then PlaySound scorpionGetOverHereSnd
		End If
	End If
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then
		zani(zControlsThis(n))=2:zf(zControlsThis(n))=1
		zani(n)=10:zf(n)=6
	End If
	If zBlowSeq(n) >= h And zBlowSeq(n) < i Then
		zani(zControlsThis(n))=2:zf(zControlsThis(n))=2
		zani(n)=10:zf(n)=7
	End If
	If zBlowSeq(n) >= i And zBlowSeq(n) < j Then
		zani(zControlsThis(n))=2:zf(zControlsThis(n))=1
		zani(n)=10:zf(n)=8
	End If
	If zBlowSeq(n) >= e And zBlowSeq(n) < j Then
		en=zControlsThis(n)
		If zOn(en)=0 Then zBlowSeq(n)=d
		enemyControlInit(n,zx(en),zy(en),0,0,en,guardable)
		If zhit(en) And zongnd(en)=1 And zhitseq(en) > 15 Then zBlowSeq(n)=d
		checkYDist(en,zx(en),zy(en),2)
		zy(en) = zy(n)
		If zHeight(zControlsThis(n)) <= 40 Then zy(zControlsThis(n))=zy(n)-20
		y=zy(n)-zheight(n)+7
		If zBlowSeq(n) >= e+2 Then
			If zface(n)=2 Then 
				x=zx(n)+14
				For distance=1 To Abs(x-zx(en)) Step 1
					If Abs(x-zx(en)) >= 37 Then
						extraObj(n,x+distance-10,0,y,0,zblowdir(n),62)
					End If
				Next
				distance=zx(en)-10
				extraObj(n,distance,0,y,0,zblowdir(n),103)
				extraObj(n,distance-13,0,y,0,zblowdir(n),62)
				extraObj(n,distance-26,0,y,0,zblowdir(n),62)
			Else If zface(n)=4 Then 
				x=zx(n)-14
				For distance=(x-zx(en)) To -1 Step -1
					If Abs(x-zx(en)) >= 37 Then
						extraObj(n,x-distance+10,0,y,0,zblowdir(n),62)
					End If
				Next
				distance=zx(en)+10
				extraObj(n,distance,0,y,0,zblowdir(n),103)
				extraObj(n,distance+13,0,y,0,zblowdir(n),62)
				extraObj(n,distance+26,0,y,0,zblowdir(n),62)
			End If
		End If
	End If

	If zBlowSeq(n) >= f And zBlowSeq(n) < j Then
		zani(zControlsThis(n))=2:zf(zControlsThis(n))=3
		If zFace(n)=2 Then moveX(zControlsThis(n),4,3)
		If zFace(n)=4 Then moveX(zControlsThis(n),2,3)
		If zBlowSeq(n) = j-1 Or Abs(zx(n)-zx(zControlsThis(n))) <= 25 Then
			isDizzy(zControlsThis(n))=1:dizzyDuration(zControlsThis(n))=3000
			freezeVictim(zControlsThis(n), 0)
			spellCooldownMaxTime(n, 1)=105
			spellCooldownSeq(n, 1)=spellCooldownMaxTime(n, 1)
			zComboMode(n)=1
			comboModeDuration(n)=2000
			zBlowSeq(n)=d
		End If
	End If
	
	If zBlowSeq(n) = j Then zBlowSeq(n)=d
	If zBlowSeq(n) = l Then zBlowSeq(n)=d

	If zBlowSeq(n) > d And zBlowSeq(n) < e Then zBlowSeq(n)=0:zBlow(n)=0:zNoGrav(n)=0

Case 8	;Dodging
	zNoMove(n)=1
	zNoJump(n)=1
	zheight(n)=25
	a=7:b=15:c=20:d=25:e=30:f=37
	If zBlowSeq(n) =a And gameSound=1 Then PlaySound shotwallsnd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=5:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=5:zf(n)=2:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=5:zf(n)=3:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > c And zBlowSeq(n) =< d Then zani(n)=5:zf(n)=4:moveX(n,zBlowdir(n),3)
	If zBlowSeq(n) > d And zBlowSeq(n) =< e Then zani(n)=5:zf(n)=5:moveX(n,zBlowdir(n),2)
	If zBlowSeq(n) > e And zBlowSeq(n) =< f Then zani(n)=5:zf(n)=6:moveX(n,zBlowdir(n),1)

	If zblowseq(n) > a And zblowseq(n) <= e Then zshield(n)=1
	If zBlowSeq(n) > f Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0:zShield(n)=0

Case 9	;Teleport punch
	zNoMove(n)=1
	zNoJump(n)=1:zNograv(n)=1:zJumping(n)=0
	a=2:b=2:c=6:d=9:e=13:f=21:g=f+15:h=g+8:i=100:j=i+24
	If zBlowSeq(n) >= 1 And zBlowSeq(n) < a Then zani(n)=1:zf(n)=0
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then zani(n)=12:zf(n)=1
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zani(n)=12:zf(n)=2
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zani(n)=12:zf(n)=3
	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then zani(n)=12:zf(n)=4
	If zBlowSeq(n) = e-1 And gameSound Then PlaySound scorpionTeleportSnd
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then 
		zani(n)=12:zf(n)=5
		zblowpamount(n)=4
		nn=1
		xblow(n,nn)=23: yblow(n,nn)=-1:wblow(n,nn)=5:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=19: yblow(n,nn)=7:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=21:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=28:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=2:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=2:zBlowBlockTime(n)=5
		zBlowSound(n)=mkHitSnd
	End If
	
	If zBlowSeq(n) >= c And zBlowSeq(n) < e Then
		moveX2(n,zBlowdir(n),4.5)
		checkYDist(n,zx(n),zy(n),2)
		If yDist(n) <= 25 Then moveY(n,-3)
	End If
	If zBlowSeq(n) >= e And zBlowSeq(n) < f Then
		moveX2(n,zBlowdir(n),8)
		If zBlowSeq(n) < e+4 Then moveY(n,1)
		If zBlowSeq(n) >= e+4 Then moveY(n,-1)
	End If
	If zBlowSeq(n) = f Then
		If zFace(n)=2 Then zx(n)=zx(n)-200
		If zFace(n)=4 Then zx(n)=zx(n)+200
	End If
	If zBlowSeq(n) = g-1 And gameSound Then PlaySound scorpionTeleportSnd
	If zBlowSeq(n) >= g And zBlowSeq(n) < h Then 
		zani(n)=12:zf(n)=5
		moveX2(n,zBlowdir(n),8)
		If zBlowSeq(n) < e+4 Then moveY(n,1)
		If zBlowSeq(n) >= e+4 Then moveY(n,-1)
		
		zblowpamount(n)=4
		nn=1
		xblow(n,nn)=23: yblow(n,nn)=-1:wblow(n,nn)=5:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=19: yblow(n,nn)=7:wblow(n,nn)=10:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=15: yblow(n,nn)=21:wblow(n,nn)=16:hblow(n,nn)=1:nn=nn+1
		xblow(n,nn)=10: yblow(n,nn)=28:wblow(n,nn)=21:hblow(n,nn)=1:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=11:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=2:zBlowBlockTime(n)=5
		zBlowSound(n)=mkHitSnd
	End If
	If zBlowStill(n)=1 Then
		zBlowSeq(n) = i
	End If
	If zBlowSeq(n) >= i And zBlowSeq(n) < i+4 Then zani(n)=5:zf(n)=1
	If zBlowSeq(n) >= i+4 And zBlowSeq(n) < i+8 Then zani(n)=5:zf(n)=2
	If zBlowSeq(n) >= i+8 And zBlowSeq(n) < i+12 Then zani(n)=5:zf(n)=3
	If zBlowSeq(n) >= i+12 And zBlowSeq(n) < i+16 Then zani(n)=5:zf(n)=4
	If zBlowSeq(n) >= i+16 And zBlowSeq(n) < i+20 Then zani(n)=5:zf(n)=5
	If zBlowSeq(n) >= i+20 And zBlowSeq(n) < i+24 Then zani(n)=5:zf(n)=6
	If zBlowSeq(n) = j Then zBlowSeq(n) = h
	
	If zBlowSeq(n) = h And zBlowSeq(n) < i Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 10	;Up + Attack
	a=3: b=6: c=9: d=12: e=20: f=24: g=37: h=44
	zNoMove(n)=1
	zNoJump(n)=1
	If zBlowSeq(n) = c And gameSound Then PlaySound blow2snd
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=14:zf(n)=1
	If zBlowSeq(n) > a And zBlowSeq(n) =< b Then zani(n)=14:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) =< c Then zani(n)=14:zf(n)=3
	If zBlowseq(n) > c And zblowseq(n) =< d Then zani(n)=14:zf(n)=4
	If zBlowseq(n) > d And zblowseq(n) =< e Then
		zblowPamount(n)=2:nn=1
		xblow(n,nn)=6: yblow(n,nn)=70:wblow(n,nn)=13:hblow(n,nn)=15:nn=nn+1
		xblow(n,nn)=6: yblow(n,nn)=42:wblow(n,nn)=13:hblow(n,nn)=17:nn=nn+1
		zHitMode(n)=0:zBlowHold(n)=8
		zBlowDamage(n)=14:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=12:zBlowBlockTime(n)=25
		zBlowSound(n)=mkStrongHitSnd
		zani(n)=14:zf(n)=5
	EndIf
	If zBlowSeq(n) > e And zBlowSeq(n) <= f Then zani(n)=14:zf(n)=5
	If zBlowSeq(n) > f And zBlowSeq(n) <= g Then zani(n)=14:zf(n)=5
	If zBlowSeq(n) > g And zBlowSeq(n) <= h Then zani(n)=14:zf(n)=2

	If zBlowSeq(n) > g Then zBlowSeq(n)=0:zBlow(n)=0

Case 11	;club
	a=12:b=22:c=29:d=50:e=55
	zNoMove(n)=1
	zNoJump(n)=1
	extraDraw(n)=1: drawObjOnZ(n)=0
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=10:zf(n)=1 :eAni(n)=1:ef(n)=2:xed(n)=-21:yed(n)=24
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=10:zf(n)=2 :eAni(n)=1:ef(n)=2:xed(n)=-22:yed(n)=25
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
		zBlowDamage(n)=25:zBLowEffect(n)=1:zBlowImpact(n)=99:zBlowStillTime(n)=13:zBlowBlockTime(n)=35
		zChunkType(n)=5
		zBlowSound(n)=smashsnd
		zani(n)=10:zf(n)=4
		eAni(n)=1:ef(n)=3:xED(n)=40:yed(n)=28
	EndIf
	If zBlowSeq(n) => c And zBlowSeq(n) =< d Then zani(n)=10:zf(n)=4 :eAni(n)=1:ef(n)=4:xed(n)=40:yed(n)=28
	If zBlowSeq(n) => d And zBlowSeq(n) =< e Then zani(n)=10:zf(n)=3 :eAni(n)=1:ef(n)=2:xed(n)=-29:yed(n)=28
	If zBlowSeq(n) > e Then zBlowSeq(n)=0:zBlow(n)=0

Case 12	;Shooting Position
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
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< c Then zani(n)=9:zf(n)=3
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
	
Case 14	;Super Special
	a=4:b=8:c=12:c2=45:d=50:e=100
	zNoMove(n)=1
	zNoJump(n)=1
	zjump(n)=0
	
	If zOnGnd(n)=0 Then zBlowSeq(n)=d
	If zBlowSeq(n) >= 1 And zBlowSeq(n) <= a Then
		zani(n)=17:zf(n)=1
		If zBlowSeq(n) = 1 Then
			clearControlledPlayers(n)
			If gameSound=1 Then PlaySound mkFatalitySnd
        	zSuperMove(n)=1:zSuperMoveSeq(n)=0
		End If
	End If
	If zBlowSeq(n) > a And zBlowSeq(n) <= b Then zani(n)=17:zf(n)=2
	If zBlowSeq(n) > b And zBlowSeq(n) <= c Then zani(n)=17:zf(n)=3
	If zBlowSeq(n) > c And zBlowSeq(n) <= d Then 
		zani(n)=17:zf(n)=4
		If zBlowSeq(n) <= c2 Then
			If zFace(n)=2 Then xCenter=zx(n)+100
			If zFace(n)=4 Then xCenter=zx(n)-100
			enemyControlInit(n,zx(n)+40,zy(n)-60,120,60,0,guardable)
			unitCounter=1
			While zControlsThese(n,unitCounter) <> 0
				en=zControlsThese(n,unitCounter)
				zani(en)=2:zf(en)=2
				If zBlowSeq(n)=c2 Then 
					zBurning(en)=1:zBurnDuration(en)=200
					blocking=0
					If blockKey(en)=1 And zCurBlow(en)=0 And zHit(en)=0 And zBlockLife(en) > 0 Then
						blocking=1
						zBlockLife(en)=zBlockLife(en)-70
						If zBlockLife(en) <= 0 Then
							If gameSound Then PlaySound brokenSnd
						Else
							If gameSound Then PlaySound blockedsnd
						End If				
					End If
					If blocking=0 Or zBlockLife(en) <= 0 Then
						isDizzy(en)=1:dizzyDuration(en)=2000
						freezeVictim(en, 0)
						zLife(en)=zLife(en)-70
						zDamage(en)=zDamage(en)+70
						If gameSound Then PlaySound scorpionSpearHitSnd
						If zBlockLife(en) <= 0 Then zBlockLife(en)=zBlockFull(n)
					End If
				End If
				If zx(en) < xCenter Then moveX(en,2,1.2)
				If zx(en) > xCenter Then moveX(en,4,1.2)
				If zy(en) < zy(n) Then moveY(en,1.2)
				If zy(en) > zy(n) Then moveY(en,-1.2)
				unitCounter=unitCounter+1
			Wend
		End If
	End If

	If zBlowSeq(n) = c Then
		checkDist(n, zx(n), zy(n), zFace(n))
		DebugLog "xDist: " + xDist(n)
		extraObj(n,zx(n),80,zy(n),0,zblowdir(n),106)
		If gameSound Then PlaySound mkExtraSpecialSnd
	End If

	If zongnd(n)=0 Then zy(n)=zy(n)-2
	
	If zBlowSeq(n) > d And zBlowSeq(n) < e Then zBlowSeq(n)=0:zBlow(n)=0:zblowstill(n)=0

Case 15 ;Scorpion throw
	a=8: b=15: c=25: d=30: e=35: f=40: g=45: h=50: i=60
	zNoMove(n)=1:zNoJump(n)=1
	If zBlowSeq(n) => 1 And zBlowSeq(n) =< a Then zani(n)=15:zf(n)=1
	If zBlowSeq(n) => a And zBlowSeq(n) =< b Then zani(n)=15:zf(n)=2
	If zBlowSeq(n)= a Then
		If gameSound Then PlaySound blowsnd
		grabbing(n,zx(n),zy(n)-3,zGrabDist(n),5)
		If zGrabs(n)=1 Then zBlowSeq(n)=c+4
	EndIf
	If zBlowSeq(n)=b Then zBlowSeq(n)=0:zBlow(n)=0
	
	en=zGrabsThis(n)
	If zface(n)=2 Then dir=4:dir2=2:n1=1:n2=14	Else dir=2:dir2=4:n1=-1:n2=-14
	
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
		
	If zBlowSeq(n) > d And zBlowSeq(n) < i Then zshield(n)=1
	If zBlowSeq(n) => d And zBlowSeq(n) < e Then zani(n)=15:zf(n)=3:zx(en)=zx(n)+n2:zy(en)=zy(n)-5:zAni(en)=2:zf(en)=1:zFace(en)=dir
	If zBlowSeq(n) => e And zBlowSeq(n) < f Then zani(n)=15:zf(n)=4:zx(en)=zx(n)+n2:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
	If zBlowSeq(n) => f And zBlowSeq(n) < g Then zani(n)=15:zf(n)=5:zx(en)=zx(n)+n1:zy(en)=zy(n)-16:zAni(en)=2:zf(en)=5:zface(en)=dir2
	If zblowseq(n) = f Then
		If gameSound Then PlaySound scorpionThrowSnd
	EndIf
	If zBlowSeq(n) => i-3 And zBlowSeq(n) < i Then zani(n)=15:zf(n)=6:zx(en)=zx(n)+n1:zy(en)=zy(n)-15:zAni(en)=2:zf(en)=6:zface(en)=dir2
	If zBlowSeq(n) = i  Then
		zx(en)=zx(n)+0:zy(en)=zy(n)-13
		zHitmodeTaken(en)=2 : zHit(en)=1:zBouncedGnd(en)=0
		zFallSpeed(en)=5:zUpFallSpeed(en)=5:zFallTime(en)=80:zHitSeq(en)=30:zHitHold(en)=0
		zDamage(en)=zDamage(en)+10
		zLife(en)=zLife(en)-10
		zFace(en)=dir : zFallDir(en)=dir
		zgrabs(n)=0:zGrabsThis(n)=0:zGrabbedBy(en)=0
	EndIf
	If zBlowSeq(n) > g And zBlowSeq(n) < h Then zani(n)=15:zf(n)=7
	If zBlowSeq(n) => h And zBlowSeq(n) < i Then zani(n)=15:zf(n)=8
	
	If zBlowSeq(n) > c And zBlowSeq(n) < g Then zgrabbed(en)=1:checkZvsWall(en,0)
	If zBlowSeq(n) = i Then zBlowSeq(n)=0:zBlow(n)=0

Case 16 ; taunt
	a=15:b=a+84:c=b+8:d=c+32:e=d+22:f=e+20
	zNoMove(n)=1
	zNoJump(n)=1:zJump(n)=0
	If zOnGnd(n)=0 Then zy(n)=zy(n)-2
	
	zani(n)=16
	If zBlowSeq(n)=a And gameSound Then PlaySound mkFriendshipSnd
	If zBlowSeq(n)=1 And zBlowSeq(n) < a Then zf(n)=1
	If zBlowSeq(n) >= a And zBlowSeq(n) < b Then
		If zBlowSeq(n) Mod 4 = 0 Then zf(n)=2
		If zBlowSeq(n) Mod 8 = 0 Then zf(n)=3
		If zBlowSeq(n) Mod 12 = 0 Then zf(n)=4
	End If
	If zBlowSeq(n) >= b And zBlowSeq(n) < c Then zf(n)=5
	If zBlowSeq(n) >= c And zBlowSeq(n) < d Then zf(n)=6

	If zBlowSeq(n) >= d And zBlowSeq(n) < e Then
		If zBlowSeq(n)=d And gameSound Then PlaySound scorpionSkullSnd
		If zBlowSeq(n) Mod 2 = 0 Then zf(n)=(zBlowSeq(n)-125)/2
	End If
	
	If zBlowSeq(n) = f Then zBlowSeq(n)=0:zBlow(n)=0

Case 17 ;flame
	zNoMove(n)=1
	zNoJump(n)=1:zjump(n)=0
	If zOnGnd(n)=0 Then zy(n)=zy(n)-2
	a=3:b=a+3:c=b+3:d=c+3:e=d+3:f=e+10:g=f+30:h=g+2:i=h+2:j=i+2:k=j+2:l=k+2:m=l+2
	
	If zBlowSeq(n) = 1 And gameSound Then PlaySound mkExtraSpecialSnd
	If zBlowSeq(n)=1 And zBlowSeq(n) < a Then zani(n)=18:zf(n)=1
	If zBlowSeq(n)=a And zBlowSeq(n) < b Then zani(n)=18:zf(n)=2
	If zBlowSeq(n)=b And zBlowSeq(n) < c Then zani(n)=18:zf(n)=3
	If zBlowSeq(n)=c And zBlowSeq(n) < d Then zani(n)=18:zf(n)=4
	If zBlowSeq(n)=d And zBlowSeq(n) < e Then zani(n)=18:zf(n)=5
	If zBlowSeq(n)=e And zBlowSeq(n) < f Then zani(n)=18:zf(n)=6
	If zBlowSeq(n)=f And zBlowSeq(n) < g Then zani(n)=18:zf(n)=7
	If zBlowSeq(n)=f And gameSound Then PlaySound scorpionBurnSnd
	If zBlowSeq(n)=g And zBlowSeq(n) < h Then zani(n)=18:zf(n)=6
	If zBlowSeq(n)=h And zBlowSeq(n) < i Then zani(n)=18:zf(n)=5
	If zBlowSeq(n)=i And zBlowSeq(n) < j Then zani(n)=18:zf(n)=4
	If zBlowSeq(n)=j And zBlowSeq(n) < k Then zani(n)=18:zf(n)=3
	If zBlowSeq(n)=k And zBlowSeq(n) < l Then zani(n)=18:zf(n)=2
	If zBlowSeq(n)=l And zBlowSeq(n) < m Then zani(n)=18:zf(n)=1
	
	If zBlowSeq(n) = f Then extraObj(n,zx(n),50,zy(n),-10,zblowdir(n),105)
	
	If zBlowSeq(n) = f-1 Then clearControlledPlayers(n)

	If zBlowSeq(n) >= f And zBlowSeq(n) < g Then
		If zFace(n)=2 Then enemyControlInit(n,zx(n)+10,zy(n)-50,75,56,0,guardable)
		If zFace(n)=4 Then enemyControlInit(n,zx(n)-10,zy(n)-50,75,56,0,guardable)
		unitCounter=1
		While zControlsThese(n,unitCounter) <> 0
			en=zControlsThese(n,unitCounter)
			zani(en)=2:zf(en)=2
			zBurning(en)=1:zBurnDuration(en)=200
			unitCounter=unitCounter+1
		Wend
	End If
	
	If zBlowSeq(n) = m Then	zBlowSeq(n)=0:zBlow(n)=0

End Select	

End Function