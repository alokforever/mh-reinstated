Function isPriorityMoveFound(n, en)
    moveFound=0
    If zx(en) > zx(n) Then zFace(n)=2 Else zFace(n)=4
    Select curGuy(n)
    Case 12 ; Scorpion
        comboKey(n)=0
        If spellCooldownSeq(n, 1)>60 And (Abs(zx(en)-zx(n)) <= 40) Then
            shotKey(n)=1:comboKey(n)=1:moveFound=1
        Else If (Abs(zx(en)-zx(n)) >= 180 And Abs(zx(en)-zx(n)) <= 220) Then
            If (zy(en)-zy(n) >= 40) And (zy(en)-zy(n) <= 55)
                superKey(n)=1:downKey(n)=1:moveFound=1
            End If
        Else If (Abs(zx(en)-zx(n)) <= 200) And (Abs(zx(en)-zx(n)) >= 90) And (Abs(zy(en)-zy(n)) < 5)
            specialKey(n)=1:moveFound=1
        Else If (Abs(zx(en)-zx(n)) < 90) And (Abs(zx(en)-zx(n)) >= 80) And (Abs(zy(en)-zy(n)) < 40)
            extraSpecialkey(n)=1:moveFound=1
        End If
    Case 13 ; Sub Zero
        If Abs(zx(en)-zx(n)) < 5 And Abs(zy(en)-zy(n)) < 40 And zSuperBar(n)>=100 Then
            superKey(n)=1:upKey(n)=1
            moveFound=1
        Else If isFrozen(en)=1 Then
            If (Abs(zx(en)-zx(n)) > 40) And zStaminaBar(n)>=1 Then 
                isRunning(n)=1
                If zFace(n)=2 Then rightKey(n)=1
                If zFace(n)=4 Then leftKey(n)=1
            Else 
                shotKey(n)=1:comboKey(n)=1
            End If
            moveFound=1
        Else If (Abs(zx(en)-zx(n)) >= 175 And Abs(zx(en)-zx(n)) <= 180) Then
            If zFace(n)=2 Then rightKey(n)=1
            If zFace(n)=4 Then leftKey(n)=1
            extraSpecialkey(n)=1
            moveFound=1
        End If
    End Select
    return moveFound
End Function

;-------------- Special A.I. -------------------------
Function zSpecialAI(n,nn)
:hitkey(n)=1

Select curGuy(n)

;-----------------------------------
Case 41    ;turtle on cloud
;-----------------------------------

If Rand(1,30) = 25 Then
    aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf

If zon(aitarget(n))=0 Then aigettarget(n)

If zhitHead(n)=1 Then zy(n)=zy(n)+1
If zy(n) < yScr + 100 Then zy(n)=zy(n)+1 : zjump(n)=0
If zy(n) > yScr + 105 Then zy(n)=zy(n)-1 :

;flies to the target
If zx(nn) => zx(n)+2 Then rightkey(n)=1
If zx(nn) =< zx(n)-2 Then leftKey(n)=1

;aim on closest enemy
If zon(nn)=1 And zteam(nn) <> zteam(n) And aiCurLevel(n) > 3 Then
    If zx(nn) => zx(n)-70 And zx(nn) =< zx(n)+70 And zy(nn) > zy(n) Then
        shotKey(n)=1
    EndIf
EndIf

;-----------------------------------
Case 43    ;laser shooter
;-----------------------------------
zHitByRect(n)=1
If aiLevel(n)=1 Then
    shotkey(n)=1
Else
    For nn=1 To zzamount
    If zon(nn)=1 And zteam(nn) <> zteam(n) Then
        If (zx(nn) => zx(n)-400 And zx(nn) < zx(n)+400) And (zy(n) > zy(nn)-60 And zy(n) < zy(nn)+40) Then
            ;PlaySound clickSnd
            shotKey(n)=1
            aiTarget(n)=nn
            Exit
        EndIf
    EndIf
    Next
EndIf

;-----------------------------------
Case 45    ; bombing ship    
;-----------------------------------

;If zy(n) < yScr + 100 Then zy(n)=zy(n)+2

;flies to the designated direction
If zFace(n)=2 Then rightkey(n)=1
If zFace(n)=4 Then leftKey(n)=1

;bombs when appropriate
If zx(n) < xScr+650 And zx(n) > xScr + 20 Then
    shotKey(n)=1:Goto aiDone1
EndIf
If zx(n) > xScr+800 Or zx(n) < xScr - 150 Then
    zblowseq2(n)=zblowseq2(n)+1
    If zBlowSeq2(n) > 200 Then zon(n)=0
EndIf


;-----------------------------------
Case 46    ;ray ball shooter
;-----------------------------------
zHitByRect(n)=1
shotKey(n)=1

;-----------------------------------
Case 47    ;soldier
;-----------------------------------
If Rand(1,30) = 25 Then
    aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf

If zon(aitarget(n))=0 Then aigettarget(n)
If rendert=1 Then aiGetNearByTarget(n)

;duck when being shot
For k=1 To shotAmount
    If shot(k)=1 And xshot(k) => zx(n)-120 And xshot(k) =< zx(n) + 120 And aiCurLevel(n) > 2 And zShield(n)=0 Then
        If yshot(k) => zy(n)-50 And yshot(k) =< zy(n) +10 And zTeam(n) <> zTeam(shotOwner(k)) Then
            downKey(n)=1
            Goto aiDone1
        EndIf
    EndIf
Next

nn = aitarget(n)
If zx(nn) => zx(n) Then rightkey(n)=1 Else leftKey(n)=1

If zon(nn)=1 And zteam(nn) <> zteam(n) Then
    If zx(nn) => zx(n)-500 And zx(nn) =< zx(n)+500 And zy(nn) > zy(n)-60 And zy(nn) < zy(n)+10 And aiCurLevel(n) > 3 Then
        shotKey(n)=1
    EndIf
EndIf

;-----------------------------------
Case 48    ;flying cylinder
;-----------------------------------

If Rand(1,30) = 25 Then
    aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf
If zon(aitarget(n))=0 Then aigettarget(n)

If zhitHead(n)=1 Then zy(n)=zy(n)+1
If aiTarget(n) <> 0 Then 
        
    If zy(n) < zy(nn)-18 And zblow(n)=0 Then zy(n)=zy(n)+1
    If zy(n) > zy(nn)-15 And zblow(n)=0 Then zy(n)=zy(n)-1
        
    If zx(n) < zx(nn)-80 Then rightkey(n)=1 
    If zx(n) > zx(nn)+80 Then leftkey(n)=1 
    
    If zx(n) < zx(nn) And zface(n)=4 Then rightkey(n)=1 
    If zx(n) > zx(nn) And zface(n)=2 Then leftkey(n)=1 
    
    If zon(nn)=1 And zteam(nn) <> zteam(n) And aiCurLevel(n) > 3 Then
        If (zx(nn) => zx(n)-400 And zx(nn) < zx(n) And zface(n)=4) Or (zx(nn) =< zx(n)+400 And zx(nn) > zx(n) And zface(n)=2) And zy(n) > zy(nn)-20 And zy(n) < zy(nn)-14 Then
            shotKey(n)=1
        EndIf
    EndIf
EndIf

;-----------------------------------
Case 49    ;Dragon
;-----------------------------------
a=1: b=a+5: c=b+5: d=c+5: e=d+5: f=e+5: g=f+5

If Rand(1,30) = 25 Then
    aiCurLevel(n)=Rand(aiLevel(n),5)
EndIf

zBlow(n)=1
If zCurBlow(n) <> 2 Then zCurBlow(n)=1

;Flying animation control
zwalkseq2(n)=zwalkseq2(n)+1
If zwalkseq2(n) => g Then zwalkseq2(n)=1
If zwalkseq2(n) =>a And zwalkseq2(n) < b Then zFlyAni(n)=1:zfa(n)=0:Goto drewDragon
If zwalkseq2(n) =>b And zwalkseq2(n) < c Then zFlyAni(n)=1:zfa(n)=1:Goto drewDragon
If zwalkseq2(n) =>c And zwalkseq2(n) < d Then zFlyAni(n)=1:zfa(n)=2:Goto drewDragon
If zwalkseq2(n) =>d And zwalkseq2(n) < e Then zFlyAni(n)=1:zfa(n)=3:Goto drewDragon
If zwalkseq2(n) =>e And zwalkseq2(n) < f Then zFlyAni(n)=1:zfa(n)=2:Goto drewDragon
If zwalkseq2(n) =>f And zwalkseq2(n) < g Then zFlyAni(n)=1:zfa(n)=1:Goto drewDragon

.drewDragon
zani(n)=zFlyAni(n) : zf(n)=zfa(n)

If zon(aitarget(n))=0 Then aigettarget(n)

If zhitHead(n)=1 Then zy(n)=zy(n)+1.5
If aiTarget(n) <> 0 Then 
        
    If zy(n) < zy(nn)-34 Then zy(n)=zy(n)+1.5
    If zy(n) > zy(nn)-30 Then zy(n)=zy(n)-1.5
        
    If zx(n) < zx(nn)-120 Then rightkey(n)=1 
    If zx(n) > zx(nn)+120 Then leftkey(n)=1 
    
    If zx(n) < zx(nn) And zface(n)=4 Then rightkey(n)=1 
    If zx(n) > zx(nn) And zface(n)=2 Then leftkey(n)=1 
    
    If (zx(nn) => zx(n)-80 And zx(nn) < zx(n) And zface(n)=4) Or (zx(nn) =< zx(n)+80 And zx(nn) > zx(n) And zface(n)=2) And zy(nn) > zy(n)-15 And aiCurLevel(n) > 3 Then
        zCurBlow(n)=3    ;spit acids
        Goto aiDone1
    EndIf
    
    If (zx(nn) => zx(n)-400 And zx(nn) < zx(n) And zface(n)=4) Or (zx(nn) =< zx(n)+400 And zx(nn) > zx(n) And zface(n)=2) And zy(n) > zy(nn)-38 And zy(n) < zy(nn)-5 And aiCurLevel(n) > 3 Then
        zCurBlow(n)=2    ;spit fire
        If zx(nn) => zx(n)-150 And zx(nn) < zx(n)+150 Then
            zBlowSeq2(n)=99
        Else
            zBlowSeq2(n)=0
        EndIf
    EndIf

EndIf

;-----------------------------------
Case 50    ;Laser beam
;-----------------------------------
Select zLives(n)
 Case 1:zy(n)=zy(n)-zvar3(n)
 Case 2:zx(n)=zx(n)+zvar3(n) 
 Case 3:zy(n)=zy(n)+zvar3(n) 
 Case 4:zx(n)=zx(n)-zvar3(n) 
End Select

zFace(n)=aiLevel(n)

shotKey(n)=1

;-----------------------------------
Case 52    ;Bag
;-----------------------------------
If zVar1(n)=1 Then     ;If bag has energy limit
    If zLife(n) < 0 Then
        killMan(n)
    EndIf
Else
    zLife(n)=999
EndIf

;-----------------------------------
Case 53    ;Gohan Helper
;-----------------------------------
;Find nearest exit route
If isHelperAttackDone(n)=1 And zBlow(n)=0 Then
    checkDist(n,zx(n),zy(n)-15,2)
    xRightUp=xDist(n)
    checkDist(n,zx(n),zy(n)-15,4)
    xLeftUp=xDist(n)
    checkDist(n,zx(n),zy(n)+15,2)
    xRightDown=xDist(n)
    checkDist(n,zx(n),zy(n)+15,4)
    xLeftDown=xDist(n)
    
    If xLeftUp > xRightUp Then
        If xLeftUp >= 600 And xLeftDown >= 600 And zani(n)=4 Then 
            leftKey(n)=1:zFace(n)=4 
        Else 
            If zHitHead(n)=0 Then zy#(n)=zy#(n)-4
        End If
    Else If xLeftUp < xRightUp Then
        If xRightUp >= 600 And xRightDown >= 600 And zani(n)=4 Then 
            rightKey(n)=1:zFace(n)=2
        Else 
            If zHitHead(n)=0 Then zy#(n)=zy#(n)-4 Else rightKey(n)=1:zFace(n)=2
        End If
    Else If xLeftUp=xRightUp And xLeftDown=xRightDown Then
        If zFace(n)=2 And zani(n)=4 Then rightKey(n)=1
        If zFace(n)=4 And zani(n)=4 Then leftKey(n)=1
    Else
        zy#(n)=zy#(n)-4
    End If
    
    If prevZx(n)=zx(n) And zFace(n)=4 Then zFace(n)=2 
    If prevZx(n)=zx(n) And zFace(n)=2 Then zFace(n)=4 
    prevZx(n)=zx(n)
    If zHitHead(n)=1 And zFace(n)=4 Then leftKey(n)=1
    If zHitHead(n)=1 And zFace(n)=2 Then rightKey(n)=1
Else If isHelperAttackDone(n)=0
    If zon(aitarget(n))=0 Then aigettarget(n)
    If aitarget(n)=0 Then killZ(n)
    ;flies to the target
    If zx(nn) >= zx(n)+2 Then rightkey(n)=1
    If zx(nn) <= zx(n)-2 Then leftKey(n)=1
    If zy(n) >= zy(nn) Then zy(n)=zy(n)-5
    If zy(n) <= zy(nn) Then zy(n)=zy(n)+5
    ;aim on closest enemy
    If zon(nn)=1 And zteam(nn) <> zteam(n) And isHelperAttackDone(n)=0 Then
        If zx(nn) >= zx(n)-40 And zx(nn) <= zx(n)+40 And Abs(zy(n)-zy(nn)) < 3 Then
            shotKey(n)=1
        End If
    End If
    If prevZx(n)=zx(n) And zFace(n)=4 Then zy#(n)=zy#(n)-4
    If prevZx(n)=zx(n) And zFace(n)=2 Then zy#(n)=zy#(n)-4
    prevZx(n)=zx(n)
End If

helperSeq(n)=helperSeq(n)+1
If helperSeq(n) > 572 Then killZ(n)
    
End Select
.aiDone1

End Function
;--------------------A.I. -----------------------------
Function AI(n,e)
nn=e:hitkey(n)=1

If isPriorityMoveFound(n, nn)=1 Then GoTo AiDone

saa=Rand(1,30)
If saa=25 Then
    aiCurLevel(n)=Rand(aiLevel(n),5)
    ;If zBLow(n) =0 Then
        .TryOtherblow
        sa=Rand(1,6)
        Select sa
            Case 1:NextBLow(n)=1
            Case 2:NextBLow(n)=4
            Case 3:NextBLow(n)=7 
            Case 4:NextBLow(n)=9
            Case 5:NextBLow(n)=10
            Case 6:If zSuperBar(n)=> 100 Then NextBlow(n)=14 Else Goto TryOtherblow
        End Select
    ;EndIf
EndIf

;chooses closest enemy For target
For k=1 To zzamount
If zon(k)=1 And (zteam(k) <> zteam(n)) And zHelperObj(k)=0 Then
    If zx(k) => zx(n)-70 And zx(k) =< zx(n)+70 Then
        If zy(k) => zy(n)-80 And zy(k) =< zy(n)+60 Then
            aiWalk(n)=1
            gotTarget=1
            aiTarget(n)=k:Exit
        EndIf
    EndIf
EndIf
Next

For k=1 To areaAmount    ;Detects what area player is located at
    If zx(n) > saLlimit(k) And zx(n) < saRlimit(k) And zy(n) > saY(k) And zy(n) < saY(k)+saH(k) Then
        If zx(n) > saLlimit(k)-15 And zx(n) < saLlimit(k)+105 Then onEdge=1
        If zx(n) < saRlimit(k)+15 And zx(n) > saRlimit(k)-105 Then onEdge=1
        safeArea=1
        curArea=k
        If zx(n) > saLlimit(k)-15 And zx(n) < saLlimit(k)+10 Then onEdge2=1
        If zx(n) < saRlimit(k)+15 And zx(n) > saRlimit(k)-10 Then onEdge2=1
        ;Exit
    EndIf
Next

a=curArea


For k=1 To dAreaAmount    ;If player is located in dangerous/jumping area
    If zx(n) > daLlimit(k) And zx(n) < daRlimit(k) And zy(n) > daY(k) And zy(n) < daY(k)+daH(k) Then
        ;safeArea=0
        aiWalk(n)=1
        inDArea=1
        aa=k
        Exit
    EndIf
Next

If inDArea=1 Then
    If daType(aa)=1 Then        ;daType 1 is For up special
        If dFleeDir(aa) =2 And zface(n)=2 Then
            rightkey(n)=1
            If zNoAirSpecial(n)=0 Then
                upkey(n)=1:specialKey(n)=1:Goto aidone
            Else
                shotKey(n)=1 :Goto AiDone
            EndIf
        EndIf    
        If dFleeDir(aa) =4 And zface(n)=4 Then
            leftkey(n)=1
            If zNoAirSpecial(n)=0 Then
                upkey(n)=1:specialKey(n)=1:Goto aidone
            Else
                shotKey(n)=1 :Goto AiDone
            EndIf
        EndIf
        Goto aiDone
    EndIf
    If daType(aa)=0 And zongnd(n)=1 And aiTarget(n) <> 0 Then    ;daType 0 is For jumping
        If daTargetH(aa)=5 Then jumpKey(n)=1:aiJumpedRand(n)=1
        If zy(nn) < zy(n)-daTargetH(aa) And zon(nn) = 1 Then jumpKey(n)=1:aiJumpedRand(n)=1
    EndIf
EndIf

;try to dodge bad boxes
For k=1 To boxAmount
If boxXSpeed(k) > 0 And (boxXDir(k)=2 Or boxXDir(k)=4) And boxHarmless(k) =0 Then ;boxes going horizontally
    ;duck
    If zx(n) => xBox(k)-15 And zx(n) =< xBox(k)+boxWidth(k)+15 And boxon(k) Then
        If zy(n)-zupheight(n) => yBox(k) And zy(n)-zupheight(n) =< yBox(k) + (boxheight(k)+10) Then
            downKey(n)=1:Goto aidone
        EndIf
    EndIf
    If zx(n) => xBox(k)-50 And zx(n) =< xBox(k)+boxWidth(k)+50 And boxon(k) Then
        If zy(n) => yBox(k)-7 And zy(n) =< yBox(k)+(boxheight(k)+10) Then
            If zx(n)+zSide(n) < xbox(k) And boxXDir(k)=2 Then Goto aidone
            If zx(n)-zSide(n) > xbox(k)+boxWidth(k) And boxXDir(k)=4 Then Goto aidone    
                If zongnd(n) Or (zjumpseq(n) > zjumplimit(n)-3 And zjumpseq(n) < zjumplimit(n)) Then jumpkey(n)=1:Goto aidone
                If zjump(n)=1 Then jumpkeydown(n)=1:Goto aidone
                If zjump(n)=0 Or zjump2(n)=0 Then
                    
                    If zNoAirSpecial(n)=0 Then
                        upkey(n)=1:specialKey(n)=1:Goto aidone
                    Else
                        shotKey(n)=1 :Goto AiDone
                    EndIf
                EndIf
            EndIf
    EndIf
EndIf

If boxYSpeed(k) > 0 And (boxYDir(k)=1 Or boxYDir(k)=3) And boxHarmless(k) = 0 Then    ;boxes going vertically
    If zx(n) => xBox(k)-20 And zx(n) =< xBox(k)+boxWidth(k)+20 Then
        If zy(n) => yBox(k) And zy(n)-zupheight(n) =< yBox(k)+(boxYspeed(k)*30) Then
            If zx(n) =< xbox(k) Then leftKey(n)=1:Goto aidone
            If zx(n) => xbox(k)+(boxWidth(k)) Then rightKey(n)=1:Goto aidone
            If zx(n) =< xbox(k)+(boxWidth(k)/2) Then blockKey(n)=1:leftKeyHit(n)=1:leftKey(n)=1:Goto aidone
            If zx(n) > xbox(k)+(boxWidth(k)/2) Then blockKey(n)=1:rightKeyHit(n)=1:rightKey(n)=1:Goto aidone
        EndIf
    EndIf
    
EndIf
Next

;Flee area
If dangerArea(curArea)=1 Then    
    If FleeDir(curArea) =2 Then
        rightkey(n)=1
        If zjump(n)=0 Then jumpKey(n)=1:jumpKeyDown(n)=1:Goto aiDone
    EndIf
    If FleeDir(curArea) =4 Then
        leftkey(n)=1
        If zjump(n)=0 Then jumpKey(n)=1:jumpKeyDown(n)=1:Goto aiDone
    EndIf
    Goto aiDone
EndIf

;If grabbed target, Then throw it
If zGrabs(n) = 1 And zGrabbed(zGrabsThis(n)) =1 And aiCurLevel(n) > 3 Then
    shotKey(n)=1
    blockKey(n)=0
    Goto aiDone
EndIf

;If there`s no target Then do nothing
If aiTarget(n)=0 Then Goto aiDone

;Walking
If a=0 And aiCurLevel(n) > 3  Then    
    If zx(nn) => zx(n)+20 Then rightkey(n)=1
    If zx(nn) =< zx(n)-20 Then leftKey(n)=1
Else
    If aiCurLevel(n) > 3  Then
        If zx(nn) > zx(n) Then rightkey(n)=1
        If zx(nn) =< zx(n) Then leftKey(n)=1
        ;If zx(nn) > zx(n) And zx(n) < sax(curArea) + saRlimit(curArea) Then rightkey(n)=1
        ;If zx(nn) =< zx(n) And zx(n) > saX(curArea) Then leftKey(n)=1
    EndIf
EndIf

;If above target on solid ground, Then roll to side
If zx(n) > zx(nn)-2 And zx(n) < zx(nn)+2 Then
    If zy(n) < zy(nn)-zheight(nn) And zongnd(n) Then
    rightKey(n)=0:downkey(n)=0: 
    leftKeyHit(n)=1: blockKey(n)=1 :Goto aidone
    EndIf
EndIf

;go down from plataform
If zonplat(n)=1 And dangerPlat(zOnPlatN(n))=0 Then
 If zx(nn) => zx(n) - 60 And zx(nn) =< zx(n) + 60 Then
    If zy(n) < zy(nn)-2 And zongnd(nn)=1 Then
        If zDontJump(n)=0 Then downKey(n)=1:jumpKey(n)=1:Goto aidone
        ;downKey(n)=1:jumpKey(n)=1:Goto aidone
    EndIf
 EndIf
EndIf

;pick up item If any
For k=1 To objAmount
    If OBJ(K)=1 And objTaken(k)=0 And zgotObj(n)=0 And zDontPickItem(n)=0 Then     
        If xobj(k) => zx(n)-14 And xObj(k) =< zx(n)+14 And objHurt(k)=0 Then
            If yobj(k) => zy(n) -10 And yobj(k) =< zy(n) +3 Then 
                shotkey(n)=1:Goto aiDone
            EndIf
        EndIf
    EndIf
Next

;Throw Object If enemy is near
If zGotobj(n) > 0 And aiCurLevel(n) > 4 Then
 If zx(nn) => zx(n)-100 And zx(nn) =< zx(n)+100 And aiCurLevel(n) > 4 And zTempShield(nn)=0 Then
  If zy(nn) => zy(n)-120 And zy(nn) =< zy(n)+10 Then
  If (zx(n) < zx(nn) And zface(n)=2) Or (zx(n) => zx(nn) And zface(n)=4) Then
    If beatIten(zgotObj(n))=1 Then
        sn=Rand(1,10)
        If sn=1 Then grabkey(n)=1 Else shotKey(n)=1
        If shotsfired(zGotobj(n)) => objAmmo(zgotObj(n)) Then grabkey(n)=1 : shotKey(n)=0
        
    Else
        shotKey(n)=1
    EndIf
    If (zx(nn) => zx(n)-25 And zx(nn) =< zx(n)+25) And zy(nn) < zy(n)-zHeight(n) Then
        upKey(n)=1
        rightkey(n)=0:leftKey(n)=0
    EndIf
    If (zx(nn) < zx(n)-25 Or zx(nn) > zx(n)+25) Then
        If zy(nn)-zHeight(nn) > zy(n) Then downKey(n)=1
          If zy(nn) < zy(n)-zheight(n) Then upkey(n)=1
    EndIf
    Goto aiDone
  EndIf  
  EndIf
 EndIf
EndIf


sn=Rand(1,180)    ;sometimes will jump
If sn=47 And zGotobj(nn)=0 And zongnd(n)=1 Then downKey(n)=0: jumpKey(n)=1:aiJumpedRand(n)=0
If zjump(n)=1  Then jumpkeydown(n)=1
If zjumpseq(n)=zjumpLimit(n)-1 And aiJumpedRand(n)=1 Then jumpkey(n)=1:


;blocks thrown objects
For k=1 To objAmount
    If objHurt(k)=1 And obj(k)=1 And xObj(k) => zx(n)-60 And xobj(k) =< zx(n) +60 And aiCurLevel(n) > 4 And zShield(n)=0 Then
        If yObj(k) => zy(n)-60 And yobj(k) =< zy(n) +10 And zTeam(objOwner(k)) <> zTeam(n) Then
            aiWalk(n)=1
            blockKey(n)=1:Goto aidone
        EndIf
    EndIf
Next

;blocks shots
For k=1 To shotAmount
    If shot(k)=1 And xshot(k) => zx(n)-80 And xshot(k) =< zx(n) + 80 And aiCurLevel(n) > 4 And zShield(n)=0 Then
        If yshot(k) => zy(n)-50 And yshot(k) =< zy(n) +10 And zTeam(n) <> zTeam(shotOwner(k)) Then
            aiWalk(n)=1
            If (xshot(k) => zx(n)-60 And xshot(k) =< zx(n) + 60) Or shotSpeed(k) > 4 Then blockKey(n)=1:Goto aidone
            If zjump(n)=0 Then jumpkey(n)=1::aiJumpedRand(n)=1:Goto aidone
        EndIf
    EndIf
Next

blockDist(n)=zBlowDist(nn,zCurBlow(nn))

;block If enemy is attacking within the range
If zShield(n)=0 And zblow(nn)=1 And zCurBlow(nn)>0 And aiCurLevel(n) > 4 And zBlowDist(nn,zCurBlow(nn)) > 0 And zblowseq(nn) < 40 Then 
    If (zx(n) => zx(nn) And zx(n) =< zx(nn)+blockDist(n) And zface(nn)=2) Or (zx(n) => zx(nn)-blockDist(n) And zx(n) =< zx(nn) And zface(nn)=4) Then
        If zy(nn) < zy(n) + 50 And zy(nn) > zy(n) - 60 Then
            sa=Rand(1,3)
            If sa = 1 And zblowseq(nn) < 3 And zCurBlow(nn) <> 7 Then
                If curGuy(n)=9 And Rand(0,1) = 1 Then
                    downkey(n)=1:specialKey(n)=1: Goto aiDone
                EndIf
                blockKey(n)=1
                If zx(nn) > zx(n) Then rightKeyHit(n)=1 Else leftKeyHit(n)=1
                Goto aidone
            Else
                blockKey(n)=1 :Goto aidone
            EndIf
        EndIf
    EndIf
EndIf


If zTempShield(nn)=1 Then Goto aiDone
;high attack If enemy is on top
If aiCurLevel(n) > 4 Then
If (zx(nn) => zx(n)-70 And zx(nn) =< zx(n)+ 70) And (zy(nn) < zy(n) - (40+yRange(n)) And zy(nn) > zy(n) - (75+yRange(n))) Then 
    If (zongnd(n)=0 And onEdge2=0 And curArea > 0) Or (zongnd(n)=0 And dangerMove5(n)=0 And curArea > 0) Then
        If zNoAirSpecial(n)=0 Then
            upkey(n)=1:specialKey(n)=1:Goto aidone
        Else
            ;shotKey(n)=1 :Goto AiDone
        EndIf
    EndIf
    a=Rand(1,4)
    If a=1 And onEdge=0 And curArea > 0 Then
        If zNoAirSpecial(n)=0 Then
            upkey(n)=1:specialKey(n)=1:Goto aidone
        Else
            shotKey(n)=1 :Goto AiDone
        EndIf
    EndIf
    a=2
    If a=>2 And a<=4 And zongnd(nn)=0 Then 
        Select curGuy(n)
        Case 13 
            If Abs(zx(nn)-zx(n)) < 35 Then upkey(n)=1:shotKey(n)=1
        Default
            upkey(n)=1:shotKey(n)=1
        End Select
    End If
    Goto aidone
EndIf
EndIf


;normal attack If near
If aiCurLevel(n) > 3 And safeArea=1 Then
If (zx(nn) => zx(n) And zx(nn) =< zx(n)+(zBlowDist(n,NextBlow(n))-4) And zface(n) =2) Or (zx(nn) => zx(n)-(zBlowDist(n,NextBlow(n))-4) And zx(nn) =< zx(n) And zface(n)=4) Then
    If zy(nn) < zy(n) + (20+yRange(n)) And zy(nn) > zy(n) - (40+yRange(n))  Then 
        If zx(n) < zx(nn) And zface(n)=2 Then attack=1
        If zx(n) => zx(nn) And zface(n)=4 Then attack=1
    EndIf
EndIf
EndIf

;Grabs enemy If near
If aiCurLevel(n) > 3 And safeArea=1 Then
If (zx(nn) => zx(n) And zx(nn) =< zx(n)+zGrabDist(n) And zface(n) =2) Or (zx(nn) => zx(n)-zGrabDist(n) And zx(nn) =< zx(n) And zface(n)=4) Then
    If zy(nn) < zy(n) + 3 And zy(nn) > zy(n) - 3  And zUngrabable(nn)=0 Then 
        grabKey(n)=1:Goto aiDone
    EndIf
EndIf
EndIf

;If enemy is under Then attack with flying normal attack
If aiCurLevel(n) > 4 And zongnd(n)=0 And safeArea=1 Then
    If zx(nn) => zx(n) - 80 And zx(nn) =< zx(n) + 80 Then
        If zy(n) < zy(nn) And zy(n) > zy(nn) - (85+yRange(n)) Then shotKey(n)=1:Goto aidone
    EndIf
EndIf

;If guy is Sub Zero or Scorpion and enemy is on top
If curGuy(n) = 13 Or curGuy (n) = 12 Then
If (zx(nn) => zx(n)-50 And zx(nn) =< zx(n)+50) And (zy(nn) < zy(n) - (70+yRange(n)) And zy(nn) > zy(n) - (70+yRange(n))) Then 
    If (zongnd(n)=1 And onEdge2=0 And curArea > 0) Or (zongnd(n)=0 And dangerMove5(n)=0 And curArea > 0) Then
        If zNoAirSpecial(n)=0 Then
            tauntKey(n)=1:Goto aidone
        Else
            If curGuy(n)=13 Then 
                upKey(n)=1
                If zSuperBar(n)>=100 Then superKey(n)=1 Else shotKey(n)=1
            End If
        EndIf
    EndIf
EndIf
EndIf

If attack=1 Then
    a=Nextblow(n)
    If a=1 Then shotKey(n)=1 :Goto aiDone
    If a=4 Then shotKey(n)=1:downKey(n)=1 :Goto aiDone
    If a=7 And zongnd(n)=1 Then
        If zNoAirSpecial(n)=0 Then
            If curGuy(n)=13 And isFrozen(nn)=1 Then Goto aiDone
            specialKey(n)=1 :Goto aiDone
        Else
            shotKey(n)=1 :Goto AiDone
        EndIf
    EndIf
    If a=10 Then shotKey(n)=1:upKey(n)=1 :Goto aiDone
    If a=14 Then superKey(n)=1:upKey(n)=1:Goto aidone
    If a=15 Then superKey(n)=1:upKey(n)=1: DebugLog "PUTANG INA" :Goto aidone
    
    If a=9 And onEdge=0 And curArea > 0 Then
        If zNoAirSpecial(n)=0 Then
            If curGuy(n)=13 And isFrozen(nn)=1 Then Goto aiDone
            specialKey(n)=1:downkey(n)=1  :Goto aiDone
        Else
            shotKey(n)=1:Goto aiDone
        EndIf
    Else
        If dangerMove9(n)=0 Then specialKey(n)=1:downkey(n)=1 Else shotkey(n)=1
    EndIf
        
EndIf

.aidone
If zDontJump(n)=1 Then jumpKey(n)=0:jumpKeyDown(n)=0
.aiDone2
If zon(aitarget(n))=0 Then aiTarget(n)=0 : aigettarget(n)

End Function