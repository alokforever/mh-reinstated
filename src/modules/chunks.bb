;----------------- Chunks ---------------------------------
Function chunks(n)

isChunkRenderLowPrio(n)=0
isChunkSolid(n)=0
yChunkSpeed#(n)=0
chunkYAdj(n)=0
If isActiveCharacter(chunkOwner(n))=1 chunkSeq(n)=chunkSeq(n)+1
cc=chunkType(n)
Select chunkType(n)
Case 0: a=5:b=10:c=14:d=18    ;test
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
    If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
    If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
    If chunkSeq(n) > c Then chunk(n)=0

Case 1: a=5:b=10:c=14    ;Blocking
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(3,1):chunkPic_(n)= ptPic(3,1)
    If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
    If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic(cc,2)
    If chunkSeq(n) > c Then chunk(n)=0

Case 2: a=30:b=100:c=180:d=240        ;Round Introduction
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

Case 3: a=10:b=20        ;ryu blue ball impact
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 4: a=5:b=10:c=15:d=20    ;explosion 40
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > d Then chunk(n)=0:

Case 5: a=5:b=10:c=15        ;white star hit
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 6: a=10:b=20        ;web shot impact
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic_(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic_(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 7: a=10:b=20        ;fire ball impact
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 8: a=3:b=6:c=9        ;coins
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 9: a=10:b=17        ;lava rock breaking
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 10: a=3:b=6:c=9        ;M vs C hit
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2) :chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,3) :chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 11: a=10:b=25:c=35        ;volcano explosion
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > c Then chunk(n)=0

Case 12: a=5:b=10:c=14    ;air Trail going up
    yChunk(n)=yChunk(n)-1
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
    If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
    If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
    If chunkSeq(n) > c Then chunk(n)=0

Case 13: a=7            ;bright dot
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a Then chunk(n)=0

Case 14: a=6:b=12        ;blood
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):ChunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 15: a=5:b=10:c=14    ;Green pick up sign
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
    If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic(cc,2)
    If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,3):chunkPic_(n)= ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0
Case 16: a=5:b=10:c=14    ;smoke
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
    If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic(cc,2)
    If chunkSeq(n) => b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(cc,3):chunkPic_(n)= ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 17: a=1:b=2    ;red ray impact
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic_(cc,1)
    If chunkSeq(n) => a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(cc,2):chunkPic_(n)= ptPic_(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 18: a=5:b=10:c=14    ;blueRay
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
    If chunkSeq(n) > 1 Then chunk(n)=0
Case 19: a=5:b=10:c=14    ;blueRay 2
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
    If chunkSeq(n) > 1 Then chunk(n)=0

Case 20: a=5:b=10:c=15        ;rash hit
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 21: a=5:b=10:c=14    ;blueRay Impact
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic_(cc,1)
    If chunkSeq(n) > 1 Then chunk(n)=0
Case 22: a=5:b=10:c=14    ;blueRay Impact 2
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic_(cc,1)
    If chunkSeq(n) > 1 Then chunk(n)=0

Case 23: a=5:b=10:c=14    ;Power ball
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(cc,1):chunkPic_(n)= ptPic(cc,1)
    If chunkSeq(n) > 1 Then chunk(n)=0

Case 24: a=2:b=5:c=9    ;fire going up
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1):yChunk(n)=yChunk(n)-1
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2):yChunk(n)=yChunk(n)-1
    If chunkSeq(n) > b Then chunk(n)=0

Case 25: a=2:b=5:c=9:d=12    ;4 way explosion
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(cc,4):chunkPic_(n)=ptPic(cc,4)
    If chunkSeq(n) > d Then chunk(n)=0

Case 26: a=5:b=5:c=9:d=12    ;batman bomb smoke
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(cc,4):chunkPic_(n)=ptPic(cc,4)
    If chunkSeq(n) > d Then chunk(n)=0

Case 27: a=8:b=16    ;green ray impact
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic_(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 28: a=5:b=10:c=15    ;little smoke
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(cc,3):chunkPic_(n)=ptPic(cc,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 29: a=5:b=10:c=15    ;little smoke going up
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(28,1):chunkPic_(n)=ptPic(28,1):yChunk(n)=yChunk(n)-1
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(28,2):chunkPic_(n)=ptPic(28,2):yChunk(n)=yChunk(n)-1
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(28,3):chunkPic_(n)=ptPic(28,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 30: a=5:b=10:c=15    ;Ray ball impact
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(29,1):chunkPic_(n)=ptPic(29,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(29,2):chunkPic_(n)=ptPic(29,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(29,3):chunkPic_(n)=ptPic(29,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 31: a=4:b=8:c=12    ;electricity
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(30,1):chunkPic_(n)=ptPic(30,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(30,2):chunkPic_(n)=ptPic(30,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(30,3):chunkPic_(n)=ptPic(30,3)
    If chunkSeq(n) > c Then chunk(n)=0

Case 32: a=5:b=10:c=14    ;Red Ray
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(31,1):chunkPic_(n)= ptPic(31,1)
    If chunkSeq(n) > 1 Then chunk(n)=0
Case 33: a=5:b=10:c=14    ;Red Ray 2
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(32,1):chunkPic_(n)= ptPic(32,1)
    If chunkSeq(n) > 1 Then chunk(n)=0

Case 34: a=6:b=12        ;big rock breaking
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(33,1):chunkPic_(n)=ptPic(33,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(33,2):chunkPic_(n)=ptPic(33,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 35: a=6:b=12        ;little rock breaking
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(34,1):chunkPic_(n)=ptPic(34,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(34,2):chunkPic_(n)=ptPic(34,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 36: a=5:b=10:c=15:d=20:e=25:f=30        ;water splash
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(35,1):chunkPic_(n)=ptPic(35,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(35,2):chunkPic_(n)=ptPic(35,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(35,3):chunkPic_(n)=ptPic(35,3)
    If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(35,4):chunkPic_(n)=ptPic(35,4)
    If chunkSeq(n) > d And chunkSeq(n) =< e Then chunkPic(n)=ptPic(35,5):chunkPic_(n)=ptPic(35,5)
    If chunkSeq(n) > e And chunkSeq(n) =< f Then chunkPic(n)=ptPic(35,6):chunkPic_(n)=ptPic(35,6)
    If chunkSeq(n) > f Then chunk(n)=0

Case 37: a=5:b=10:c=20        ;big web
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)= zpic(3,10,3):chunkPic_(n)=zpic(3,10,3)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=zpic(3,10,4):chunkPic_(n)=zpic(3,10,4)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=zpic(3,10,5):chunkPic_(n)=zpic(3,10,5)
    If chunkSeq(n) > c Then chunk(n)=0

Case 38:     ;yellow ray
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(36,1):chunkPic_(n)= ptPic(36,1)
    If chunkSeq(n) > 1 Then chunk(n)=0
Case 39:     ;yellow ray 2
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(37,1):chunkPic_(n)= ptPic(37,1)
    If chunkSeq(n) > 1 Then chunk(n)=0
Case 51:     ;yellow Ray Impact
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(38,1):chunkPic_(n)= ptPic_(38,1)
    If chunkSeq(n) > 1 Then chunk(n)=0
Case 52:     ;yellow Ray Impact 2
    If chunkSeq(n) = 1 Then chunkPic(n)= ptPic(39,1):chunkPic_(n)= ptPic_(39,1)
    If chunkSeq(n) > 1 Then chunk(n)=0


Case 40:a=10    ;tutorial 1 - double jump
    
    message=1 : messageN=n
    chunkCategory(n)=2
    chunkPic(n)= noPic:chunkPic_(n)= noPic
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
        ln=1 
        chunkStr$(n,ln)=strInfo$(67):ln=ln+1
        chunkStr$(n,ln)=strInfo$(68)
        chunkLines(n)=ln
        chunkWidth(n)=800:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(1)=1
    EndIf
    If tutorial(1)=1 Then chunk(n)=0:message=0

Case 41:a=10    ;tutorial 2 - up special
    message=1 : messageN=n
    chunkCategory(n)=2
    chunkPic(n)= noPic:chunkPic_(n)= noPic
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
        ln=1 
        chunkStr$(n,ln)=strInfo$(69):ln=ln+1
        chunkStr$(n,ln)=strInfo$(70):ln=ln+1
        chunkStr$(n,ln)=strInfo$(71)
        chunkLines(n)=ln
        chunkWidth(n)=800:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(2)=1
    EndIf
       If tutorial(2)=1 Then chunk(n)=0:message=0
       
Case 42:a=10    ;tutorial 3 - fight
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
        chunkWidth(n)=736:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(3)=1
    EndIf
    If tutorial(3)=1 Then chunk(n)=0:message=0
 
Case 43:a=10    ;tutorial 4 - use switch
    message=1 : messageN=n
    chunkCategory(n)=2
    chunkPic(n)= noPic:chunkPic_(n)= noPic
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
        ln=1
        chunkStr$(n,ln)=strInfo$(76):ln=ln+1
        chunkStr$(n,ln)=strInfo$(77)
        chunkLines(n)=ln
        chunkWidth(n)=736:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(4)=1
    EndIf
    If tutorial(4)=1 Then chunk(n)=0:message=0
    
Case 44:a=10    ;tutorial 5 - pick up item
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
        chunkWidth(n)=736:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(5)=1
    EndIf
    If tutorial(5)=1 Then chunk(n)=0:message=0
    
Case 45:a=10    ;tutorial 6 - go down from platform
    message=1 : messageN=n
    chunkCategory(n)=2
    chunkPic(n)= noPic:chunkPic_(n)= noPic
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
        ln=1
        chunkStr$(n,ln)=strInfo$(82):ln=ln+1
        chunkStr$(n,ln)=strInfo$(83):ln=ln+1
        chunkStr$(n,ln)=strInfo$(84)
        chunkLines(n)=ln
        chunkWidth(n)=736:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(6)=1
    EndIf
    If tutorial(6)=1 Then chunk(n)=0:message=0
    
Case 46:a=10    ;tutorial 7 - throw item diagonally
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
        chunkWidth(n)=736:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
        waitInput()
        chunk(n)=0
        message=0
        tutorial(7)=1
    EndIf
    If tutorial(7)=1 Then chunk(n)=0:message=0

Case 47:a=10    ;tutorial 8 - super special
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
        chunkWidth(n)=896:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 3 Then
        Delay 500
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

Case 49:a=200    ;no air special allowed
    message=1 : messageN=n
    chunkCategory(n)=2
    chunkPic(n)= noPic:chunkPic_(n)= noPic
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then
        ln=1
        chunkStr$(n,ln)=strInfo$(94)
        chunkLines(n)=ln
        chunkWidth(n)=672:chunkHeight(n)=(40 * ln)
    EndIf
    If chunkSeq(n) = 200 Then
        tutorial(9)=1 : chunk(n)=0 : message=0
    EndIf
    If tutorial(9)=1 Then chunk(n)=0:message=0


Case 50: a=7:b=15        ;TMNT hit
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(cc,1):chunkPic_(n)=ptPic(cc,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(cc,2):chunkPic_(n)=ptPic(cc,2)
    If chunkSeq(n) > b Then chunk(n)=0

Case 53:         ;Whip
    chunkPic(n)=ptPic(40,1):chunkPic_(n)=ptPic_(40,1)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 54:         ;Whip
    chunkPic(n)=ptPic(40,2):chunkPic_(n)=ptPic_(40,2)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 55:         ;Whip
    chunkPic(n)=ptPic(40,3):chunkPic_(n)=ptPic_(40,3)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 56:         ;Whip
    chunkPic(n)=ptPic(40,4):chunkPic_(n)=ptPic_(40,4)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 57:         ;Whip
    chunkPic(n)=ptPic(40,5):chunkPic_(n)=ptPic_(40,5)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 58:         ;Whip
    chunkPic(n)=ptPic(40,6):chunkPic_(n)=ptPic_(40,6)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 59:         ;Whip
    chunkPic(n)=ptPic(40,7):chunkPic_(n)=ptPic_(40,7)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 60:         ;Whip
    chunkPic(n)=ptPic(40,8):chunkPic_(n)=ptPic_(40,8)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 61    ;scorpion spear
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

Case 62    ;scorpion spear rope
    If chunkSeq(n)=1 Then chunkPic(n)=ptPic(84,1):chunkPic_(n)=ptPic(84,1)
    
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 63:        ;pre-freeze ball
    chunkPic(n)=ptPic(41,1):chunkPic_(n)=ptPic_(41,1)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 64:        ;pre-freeze ball
    chunkPic(n)=ptPic(41,2):chunkPic_(n)=ptPic_(41,2)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 65:        ;pre-freeze ball
    chunkPic(n)=ptPic(41,3):chunkPic_(n)=ptPic_(41,3)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 66:        ;pre-freeze ball
    chunkPic(n)=ptPic(41,4):chunkPic_(n)=ptPic_(41,4)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 67:        ;pre-freeze ball
    chunkPic(n)=ptPic(41,5):chunkPic_(n)=ptPic_(41,5)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 68:        ;pre ground freeze
    chunkPic(n)=ptPic(42,1):chunkPic_(n)=ptPic_(42,1)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 69:        ;pre ground freeze
    chunkPic(n)=ptPic(42,2):chunkPic_(n)=ptPic_(42,2)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 70:        ;pre ground freeze
    chunkPic(n)=ptPic(42,3):chunkPic_(n)=ptPic_(42,3)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 71:        ;pre ground freeze
    chunkPic(n)=ptPic(42,4):chunkPic_(n)=ptPic_(42,4)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 72:        ;pre ground freeze
    chunkPic(n)=ptPic(42,5):chunkPic_(n)=ptPic_(42,5)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 73:        ;pre ground freeze
    chunkPic(n)=ptPic(42,6):chunkPic_(n)=ptPic_(42,6)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 74:        ;pre ground freeze
    chunkPic(n)=ptPic(42,7):chunkPic_(n)=ptPic_(42,7)
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 75: a=5:b=10:c=14:d=18         ;freeze ball hit
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(44,1):chunkPic_(n)=ptPic_(44,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(44,2):chunkPic_(n)=ptPic_(44,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(44,3):chunkPic_(n)=ptPic_(44,3)
    If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(44,4):chunkPic_(n)=ptPic_(44,4)
    If chunkSeq(n) > d Then chunk(n)=0
Case 76: a=5:b=10:c=14:d=18         ;ground freeze hit
    ;If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(43,1):chunkPic_(n)=ptPic(43,1)
    ;If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(43,2):chunkPic_(n)=ptPic(43,2)
    ;If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(43,3):chunkPic_(n)=ptPic(43,3)
    ;If chunkSeq(n) > c And chunkSeq(n) =< d Then chunkPic(n)=ptPic(43,4):chunkPic_(n)=ptPic(43,4)
    ;If chunkSeq(n) > d Then chunk(n)=0
    chunkPic(n)=noPic
Case 77:         ;berserker barrage slash1a
    a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40

    If (chunkSeq(n) >= 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(60,1):chunkPic_(n)=ptPic_(60,1)
    If (chunkSeq(n) >= a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(60,2):chunkPic_(n)=ptPic_(60,2)
    If (chunkSeq(n) >= b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(60,3):chunkPic_(n)=ptPic_(60,3)
    If (chunkSeq(n) >= c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(60,4):chunkPic_(n)=ptPic_(60,4)
    
    If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 78:        ;berserker barrage slash1b
    a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40
    If (chunkSeq(n) >= 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(61,1):chunkPic_(n)=ptPic_(61,1)
    If (chunkSeq(n) >= a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(61,2):chunkPic_(n)=ptPic_(61,2)
    If (chunkSeq(n) >= b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(61,3):chunkPic_(n)=ptPic_(61,3)
    If (chunkSeq(n) >= c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(61,4):chunkPic_(n)=ptPic_(61,4)

    If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
Case 79:        ;berserker barrage slash2a
    a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40
    If (chunkSeq(n) = 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(62,1):chunkPic_(n)=ptPic_(62,1)
    If (chunkSeq(n) = a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(62,2):chunkPic_(n)=ptPic_(62,2)
    If (chunkSeq(n) = b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(62,3):chunkPic_(n)=ptPic_(62,3)
    If (chunkSeq(n) = c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(62,4):chunkPic_(n)=ptPic_(62,4)
    
    If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 80:        ;berserker barrage slash2b
    a=5:b=10:c=15:d=20:e=25:f=30:g=35:h=40
    If (chunkSeq(n) >= 1 And chunkSeq(n) < a) Or (chunkSeq(n) >=d And chunkSeq(n) < e) Then chunkPic(n)=ptPic(63,1):chunkPic_(n)=ptPic_(63,1)
    If (chunkSeq(n) >= a And chunkSeq(n) < b) Or (chunkSeq(n) >=e And chunkSeq(n) < f) Then chunkPic(n)=ptPic(63,2):chunkPic_(n)=ptPic_(63,2)
    If (chunkSeq(n) >= b And chunkSeq(n) < c) Or (chunkSeq(n) >=f And chunkSeq(n) < g) Then chunkPic(n)=ptPic(63,3):chunkPic_(n)=ptPic_(63,3)
    If (chunkSeq(n) >= c And chunkSeq(n) < d) Or (chunkSeq(n) >=g And chunkSeq(n) < h) Then chunkPic(n)=ptPic(63,4):chunkPic_(n)=ptPic_(63,4)
    
    If chunkSeq(n) > h Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 81:        ;berserker slash chunk
    a=3:b=7:c=10:d=14:e=17:f=21
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(64,1):chunkPic_(n)=ptPic_(64,1)
    If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(64,2):chunkPic_(n)=ptPic_(64,2)
    If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(64,3):chunkPic_(n)=ptPic_(64,3)
    If chunkSeq(n) => c And chunkSeq(n) < d Then chunkPic(n)=ptPic(64,4):chunkPic_(n)=ptPic_(64,4)
    If chunkSeq(n) => d And chunkSeq(n) < e Then chunkPic(n)=ptPic(64,5):chunkPic_(n)=ptPic_(64,5)
    If chunkSeq(n) => e And chunkSeq(n) < f Then chunkPic(n)=ptPic(64,6):chunkPic_(n)=ptPic_(64,6)
    
    If chunkSeq(n) > f Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 82:        ;X slash1
    a=1
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(65,1):chunkPic_(n)=ptPic_(65,1)
    
    If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 83:        ;X slash2
    a=1
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(65,2):chunkPic_(n)=ptPic_(65,2)
    
    If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 84:        ;X slash3a
    a=13
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then 
        If chunkSeq(n) Mod 2 = 1 Then 
            chunkPic(n)=ptPic(65,3):chunkPic_(n)=ptPic_(65,3)
        Else
            chunkPic(n)=ptPic(65,4):chunkPic_(n)=ptPic_(65,4)
        EndIf
    EndIf
    
    If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 85:        ;X slash3b
    a=13
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then 
        If chunkSeq(n) Mod 2 = 1 Then 
            chunkPic(n)=ptPic(66,1):chunkPic_(n)=ptPic_(66,1)
        Else
            chunkPic(n)=ptPic(66,2):chunkPic_(n)=ptPic_(66,2)
        EndIf
    EndIf
    
    If chunkSeq(n) = a Or zhit(chunkOwner(n))=1 Then chunk(n)=0
    
Case 86:        ;X slash4a
    a=3:b=6:c=9
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(67,1):chunkPic_(n)=ptPic_(67,1)
    If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(67,2):chunkPic_(n)=ptPic_(67,2)
    If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(67,3):chunkPic_(n)=ptPic_(67,3)
    
    If chunkSeq(n) = c Then chunk(n)=0
    
Case 87:        ;X slash4b
    a=3:b=6:c=9
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(68,1):chunkPic_(n)=ptPic_(68,1)
    If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(68,2):chunkPic_(n)=ptPic_(68,2)
    If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(68,3):chunkPic_(n)=ptPic_(68,3)
    
    If chunkSeq(n) = c Then chunk(n)=0
        
Case 88:        ;Faint slash
    a=3:b=a+3:c=b+3:d=c+3:e=d+3
    If chunkSeq(n) => 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(69,1):chunkPic_(n)=ptPic(69,1)
    If chunkSeq(n) => a And chunkSeq(n) < b Then chunkPic(n)=ptPic(69,2):chunkPic_(n)=ptPic(69,2)
    If chunkSeq(n) => b And chunkSeq(n) < c Then chunkPic(n)=ptPic(69,3):chunkPic_(n)=ptPic(69,3)
    If chunkSeq(n) => c And chunkSeq(n) < d Then chunkPic(n)=ptPic(69,4):chunkPic_(n)=ptPic(69,4)
    If chunkSeq(n) => d And chunkSeq(n) < e Then chunkPic(n)=ptPic(69,5):chunkPic_(n)=ptPic(69,5)
    
    If chunkSeq(n) = e Then chunk(n)=0
    
Case 89:        ;Dust
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
    
Case 90:        ;Silhouette
    If chunkSeq(n) >= 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic(71,1):chunkPic_(n)=ptPic_(71,1)
    If chunkSeq(n) >= 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(71,2):chunkPic_(n)=ptPic_(71,2)
    If chunkSeq(n) >= 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic(71,3):chunkPic_(n)=ptPic_(71,3)
    If chunkSeq(n) >= 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(71,4):chunkPic_(n)=ptPic_(71,4)
    If chunkSeq(n) >= 5 And chunkSeq(n) < 6 Then chunkPic(n)=ptPic(71,5):chunkPic_(n)=ptPic_(71,5)
    
    If chunkSeq(n) = 6 Then chunk(n)=0
    
Case 91:        ;Small X slashA
    If chunkSeq(n) >= 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic(72,1):chunkPic_(n)=ptPic_(72,1)
    If chunkSeq(n) >= 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(72,2):chunkPic_(n)=ptPic_(72,2)
    If chunkSeq(n) >= 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic(72,3):chunkPic_(n)=ptPic_(72,3)
    If chunkSeq(n) >= 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(72,4):chunkPic_(n)=ptPic_(72,4)
    
    If chunkSeq(n) = 5 Then chunk(n)=0
    
Case 92:         ;Small X slashB
    If chunkSeq(n) >= 1 And chunkSeq(n) < 2 Then chunkPic(n)=ptPic_(72,1):chunkPic_(n)=ptPic(72,1)
    If chunkSeq(n) >= 2 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic_(72,2):chunkPic_(n)=ptPic(72,2)
    If chunkSeq(n) >= 3 And chunkSeq(n) < 4 Then chunkPic(n)=ptPic_(72,3):chunkPic_(n)=ptPic(72,3)
    If chunkSeq(n) >= 4 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic_(72,4):chunkPic_(n)=ptPic(72,4)
    
    If chunkSeq(n) = 5 Then chunk(n)=0
    
Case 93:        ;Berserker aura
    If chunkSeq(n) >= 1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(73,1):chunkPic_(n)=ptPic(73,1)
    If chunkSeq(n) >= 3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(73,2):chunkPic_(n)=ptPic(73,2)
    If chunkSeq(n) >= 5 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(73,3):chunkPic_(n)=ptPic(73,3)
    If chunkSeq(n) >= 7 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(73,4):chunkPic_(n)=ptPic(73,4)
    If chunkSeq(n) >= 9 And chunkSeq(n) < 11 Then chunkPic(n)=ptPic(73,5):chunkPic_(n)=ptPic(73,5)
    If chunkSeq(n) >= 11 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(73,6):chunkPic_(n)=ptPic(73,6)
    If chunkSeq(n) >= 13 And chunkSeq(n) < 15 Then chunkPic(n)=ptPic(73,7):chunkPic_(n)=ptPic(73,7)
    
    If chunkSeq(n) = 15 Then chunk(n)=0

Case 94:        ;no chunk
    If chunkSeq(n) >= 1 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(74,1):chunkPic_(n)=ptPic(74,1)
    
    If chunkSeq(n) = 5 Then chunk(n)=0
    
Case 95:        ;Blood
    If chunkSeq(n) >=1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(75,1):chunkPic_(n)=ptPic_(75,1)
    If chunkSeq(n) >=3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(75,2):chunkPic_(n)=ptPic_(75,2)
    If chunkSeq(n) >=5 And chunkSeq(n) < 7 Then chunkPic(n)=ptPic(75,3):chunkPic_(n)=ptPic_(75,3)
    If chunkSeq(n) >=7 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(75,4):chunkPic_(n)=ptPic_(75,4)
    If chunkSeq(n) >=9 And chunkSeq(n) < 11 Then chunkPic(n)=ptPic(75,5):chunkPic_(n)=ptPic_(75,5)
    If chunkSeq(n) >=11 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(75,6):chunkPic_(n)=ptPic_(75,6)
    If chunkSeq(n) >=13 And chunkSeq(n) < 15 Then chunkPic(n)=ptPic(75,7):chunkPic_(n)=ptPic_(75,7)
    If chunkSeq(n) >=15 And chunkSeq(n) < 17 Then chunkPic(n)=ptPic(75,8):chunkPic_(n)=ptPic_(75,8)
    
    If chunkSeq(n) = 17 Then chunk(n)=0

Case 96:        ;Blood2
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

Case 97:        ;Berserker slash2 FX
    If chunkSeq(n) >=1 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(77,1):chunkPic_(n)=ptPic_(77,1)
    If chunkSeq(n) >=5 And chunkSeq(n) < 9 Then chunkPic(n)=ptPic(77,2):chunkPic_(n)=ptPic_(77,2)
    If chunkSeq(n) >=9 And chunkSeq(n) < 13 Then chunkPic(n)=ptPic(77,3):chunkPic_(n)=ptPic_(77,3)
    If chunkSeq(n) >=13 And chunkSeq(n) < 17 Then chunkPic(n)=ptPic(77,4):chunkPic_(n)=ptPic_(77,4)
    
    If chunkSeq(n) = 17 Then chunk(n)=0

Case 98:        ;Toasty fx
    If chunkSeq(n) >= 1 And chunkSeq(n) < 60 Then chunkPic(n)=ptPic(78,1):chunkPic_(n)=ptPic(78,1)

    If chunkSeq(n) >= 60 Then chunk(n)=0
    
Case 99:        ;Ice shower chunk
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

Case 100:        ;Ice shower chunk2
    If chunkSeq(n) >= 1 And chunkSeq(n) < 3 Then chunkPic(n)=ptPic(80,1):chunkPic_(n)=ptPic(80,1)
    If chunkSeq(n) >= 3 And chunkSeq(n) < 5 Then chunkPic(n)=ptPic(80,2):chunkPic_(n)=ptPic(80,2)
    
    If chunkSeq(n) = 5 Then chunk(n)=0
    
Case 101:        ;Ice impale
    a=5:b=a+5:c=b+5:d=c+165
    If chunkSeq(n) >= 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(81,1):chunkPic_(n)=ptPic_(81,1)
    If chunkSeq(n) >= a And chunkSeq(n) < b Then chunkPic(n)=ptPic(81,2):chunkPic_(n)=ptPic_(81,2)
    If chunkSeq(n) >= b And chunkSeq(n) < c Then chunkPic(n)=ptPic(81,3):chunkPic_(n)=ptPic_(81,3)
    If chunkSeq(n) >= c And chunkSeq(n) < d Then chunkPic(n)=ptPic(81,4):chunkPic_(n)=ptPic_(81,4)
    
    If chunkSeq(n) = d Then chunk(n)=0

Case 102:         ;berserker slash2 after-image
    a=12

    If chunkSeq(n) >= 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(82,1):chunkPic_(n)=ptPic_(82,1)
    
    If chunkSeq(n) >= a Then chunk(n)=0
    
Case 103:        ;spear head
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(85,1):chunkPic_(n)=ptPic_(85,1)
    
    If chunkSeq(n) > 1 Or zhit(chunkOwner(n))=1 Then chunk(n)=0 
    
Case 104:        ;spear disappearing
    If chunkSeq(n) >= 1 And chunkSeq(n) < 6 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,1)
    If chunkSeq(n) >= 6 And chunkSeq(n) < 10 Then chunkPic(n)=ptPic(86,2):chunkPic_(n)=ptPic_(86,2)
    If chunkSeq(n) >= 10 And chunkSeq(n) < 16 Then chunkPic(n)=ptPic(86,3):chunkPic_(n)=ptPic_(86,3)
    If chunkSeq(n) >= 16 And chunkSeq(n) < 19 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,1)
    If chunkSeq(n) >= 19 And chunkSeq(n) < 22 Then chunkPic(n)=ptPic(86,2):chunkPic_(n)=ptPic_(86,2)
    If chunkSeq(n) >= 22 And chunkSeq(n) < 25 Then chunkPic(n)=ptPic(86,3):chunkPic_(n)=ptPic_(86,3)
    If chunkSeq(n) = 25 Then chunkPic(n)=ptPic(86,1):chunkPic_(n)=ptPic_(86,1)
    If chunkSeq(n) = 26 Then chunkPic(n)=ptPic(86,2):chunkPic_(n)=ptPic_(86,2)
    If chunkSeq(n) = 27 Then chunkPic(n)=ptPic(86,3):chunkPic_(n)=ptPic_(86,3)
    
    If chunkSeq(n) > 27 Then chunk(n)=0
    
Case 105:        ;flame
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
    
Case 106:        ;hand from hell
    If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(88,1):chunkPic_(n)=ptPic_(88,1)
    If chunkSeq(n) >= 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(88,2):chunkPic_(n)=ptPic_(88,2)
    If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(88,3):chunkPic_(n)=ptPic_(88,3)
    If chunkSeq(n) >= 10 And chunkSeq(n) <= 12 Then chunkPic(n)=ptPic(88,4):chunkPic_(n)=ptPic_(88,4)
    If chunkSeq(n) >= 13 And chunkSeq(n) <= 15 Then chunkPic(n)=ptPic(88,5):chunkPic_(n)=ptPic_(88,5)
    If chunkSeq(n) >= 16 And chunkSeq(n) <= 18 Then chunkPic(n)=ptPic(88,6):chunkPic_(n)=ptPic_(88,6)
    If chunkSeq(n) >= 19 And chunkSeq(n) <= 21 Then chunkPic(n)=ptPic(88,7):chunkPic_(n)=ptPic_(88,7)
    If chunkSeq(n) >= 22 And chunkSeq(n) <= 24 Then chunkPic(n)=ptPic(88,8):chunkPic_(n)=ptPic_(88,8)
    If chunkSeq(n) >= 25 And chunkSeq(n) <= 27 Then chunkPic(n)=ptPic(88,9):chunkPic_(n)=ptPic_(88,9)
    If chunkSeq(n) >= 28 And chunkSeq(n) <= 31 Then chunkPic(n)=ptPic(88,11):chunkPic_(n)=ptPic_(88,11)
    If chunkSeq(n) >= 36 And chunkSeq(n) <= 39 Then chunkPic(n)=ptPic(88,10):chunkPic_(n)=ptPic_(88,10)
    If chunkSeq(n) >= 32 And chunkSeq(n) <= 35 Then chunkPic(n)=ptPic(88,12):chunkPic_(n)=ptPic_(88,12)
    If chunkSeq(n) >= 40 And chunkSeq(n) <= 43 Then chunkPic(n)=ptPic(88,13):chunkPic_(n)=ptPic_(88,13)
    If chunkSeq(n) >= 44 And chunkSeq(n) <= 47 Then chunkPic(n)=ptPic(88,14):chunkPic_(n)=ptPic_(88,14)
    If chunkSeq(n) >= 48 And chunkSeq(n) <= 51 Then chunkPic(n)=ptPic(88,15):chunkPic_(n)=ptPic_(88,15)
    
    If chunkSeq(n)=51 Then chunk(n)=0
    
Case 107:        ;pre-fireball
    If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(89,1):chunkPic_(n)=ptPic_(89,1)
    
    If chunkSeq(n)=4 Then chunk(n)=0
    
Case 108:        ;post-fireball
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
    
Case 109:        ;Caught something with Lasso (Long)
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
    
Case 110:        ;Caught something with Lasso (short)
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
    
Case 111:        ;WW Dash


Case 112:         ;Ground effect 1
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

Case 113:        ;Earthquake chunk creation
    If chunkSeq(n) >= 1 And chunkSeq(n) <= 2 Then chunkPic(n)=ptPic(94,1):chunkPic_(n)=ptPic_(94,1)
    If chunkSeq(n) >= 3 And chunkSeq(n) <= 5 Then chunkPic(n)=ptPic(94,2):chunkPic_(n)=ptPic_(94,2)
    If chunkSeq(n) >= 5 And chunkSeq(n) <= 7 Then chunkPic(n)=ptPic(94,3):chunkPic_(n)=ptPic_(94,3)
    If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(94,4):chunkPic_(n)=ptPic_(94,4)
    If chunkSeq(n)=10 Then chunk(n)=0
    
Case 114:        ;Earthquake chunk destruction
    If chunkSeq(n) >= 1 And chunkSeq(n) <= 2 Then chunkPic(n)=ptPic(94,7):chunkPic_(n)=ptPic_(94,7)
    If chunkSeq(n) >= 3 And chunkSeq(n) <= 5 Then chunkPic(n)=ptPic(94,8):chunkPic_(n)=ptPic_(94,8)
    If chunkSeq(n) >= 5 And chunkSeq(n) <= 7 Then chunkPic(n)=ptPic(94,9):chunkPic_(n)=ptPic_(94,9)
    If chunkSeq(n) >= 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(94,10):chunkPic_(n)=ptPic_(94,10)
    If chunkSeq(n) >= 9 And chunkSeq(n) <= 11 Then chunkPic(n)=ptPic(94,11):chunkPic_(n)=ptPic_(94,11)
    If chunkSeq(n)=12 Then chunk(n)=0
    
Case 115:        ;Earthquake 2 chunk destruction
    If chunkSeq(n) >= 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(95,1):chunkPic_(n)=ptPic_(95,1)
    If chunkSeq(n) >= 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(95,2):chunkPic_(n)=ptPic_(95,2)
    If chunkSeq(n) >= 7 And chunkSeq(n) <= 10 Then chunkPic(n)=ptPic(95,3):chunkPic_(n)=ptPic_(95,3)
    If chunkSeq(n) >= 11 And chunkSeq(n) <= 13 Then chunkPic(n)=ptPic(95,4):chunkPic_(n)=ptPic_(95,4)
    If chunkSeq(n) >= 14 And chunkSeq(n) <= 16 Then chunkPic(n)=ptPic(95,5):chunkPic_(n)=ptPic_(95,5)
    If chunkSeq(n)=16 Then chunk(n)=0
    
Case 116:        ;Dust 2
    If chunkSeq(n) => 1 And chunkSeq(n) <= 2 Then chunkPic(n)=ptPic(96,1):chunkPic_(n)=ptPic_(96,1)
    If chunkSeq(n) => 3 And chunkSeq(n) <= 4 Then chunkPic(n)=ptPic(96,2):chunkPic_(n)=ptPic_(96,2)
    If chunkSeq(n) => 5 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(96,3):chunkPic_(n)=ptPic_(96,3)
    If chunkSeq(n) => 7 And chunkSeq(n) <= 8 Then chunkPic(n)=ptPic(96,4):chunkPic_(n)=ptPic_(96,4)
    If chunkSeq(n) => 9 And chunkSeq(n) <= 11 Then chunkPic(n)=ptPic(96,5):chunkPic_(n)=ptPic_(96,5)
    If chunkSeq(n) => 12 And chunkSeq(n) <= 14 Then chunkPic(n)=ptPic(96,6):chunkPic_(n)=ptPic_(96,6)
    If chunkSeq(n) => 15 And chunkSeq(n) <= 17 Then chunkPic(n)=ptPic(96,7):chunkPic_(n)=ptPic_(96,7)
    If chunkSeq(n) => 18 And chunkSeq(n) <= 20 Then chunkPic(n)=ptPic(96,8):chunkPic_(n)=ptPic_(96,8)
    If chunkSeq(n) => 21 And chunkSeq(n) <= 23 Then chunkPic(n)=ptPic(96,9):chunkPic_(n)=ptPic_(96,9)
    If chunkSeq(n) => 24 And chunkSeq(n) <= 26 Then chunkPic(n)=ptPic(96,10):chunkPic_(n)=ptPic_(96,10)
    
    If chunkSeq(n) = 26 Then chunk(n)=0

Case 117:        ;Dust 3
    If chunkSeq(n) => 1 And chunkSeq(n) <= 3 Then chunkPic(n)=ptPic(97,1):chunkPic_(n)=ptPic_(97,1)
    If chunkSeq(n) => 4 And chunkSeq(n) <= 6 Then chunkPic(n)=ptPic(97,2):chunkPic_(n)=ptPic_(97,2)
    If chunkSeq(n) => 7 And chunkSeq(n) <= 9 Then chunkPic(n)=ptPic(97,3):chunkPic_(n)=ptPic_(97,3)
    If chunkSeq(n) => 10 And chunkSeq(n) <= 12 Then chunkPic(n)=ptPic(97,4):chunkPic_(n)=ptPic_(97,4)
    If chunkSeq(n) => 13 And chunkSeq(n) <= 15 Then chunkPic(n)=ptPic(97,5):chunkPic_(n)=ptPic_(97,5)
    If chunkSeq(n) => 16 And chunkSeq(n) <= 18 Then chunkPic(n)=ptPic(97,6):chunkPic_(n)=ptPic_(97,6)
    If chunkSeq(n) => 19 And chunkSeq(n) <= 21 Then chunkPic(n)=ptPic(97,7):chunkPic_(n)=ptPic_(97,7)
    If chunkSeq(n) => 22 And chunkSeq(n) <= 24 Then chunkPic(n)=ptPic(97,8):chunkPic_(n)=ptPic_(97,8)
    If chunkSeq(n) => 25 And chunkSeq(n) <= 27 Then chunkPic(n)=ptPic(97,9):chunkPic_(n)=ptPic_(97,9)
    If chunkSeq(n) => 28 And chunkSeq(n) <= 30 Then chunkPic(n)=ptPic(97,10):chunkPic_(n)=ptPic_(97,10)
    If chunkSeq(n) => 30 And chunkSeq(n) <= 33 Then chunkPic(n)=ptPic(97,11):chunkPic_(n)=ptPic_(97,11)
    
    If chunkSeq(n) = 26 Then chunk(n)=0
    
Case 118:        ;Dbz run trail 1
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(98,1):chunkPic_(n)=ptPic_(98,1)
    
    If chunkSeq(n) >1 Then chunk(n)=0

Case 119:        ;Dbz run trail 2
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(98,2):chunkPic_(n)=ptPic_(98,2)
    
    If chunkSeq(n) >1 Then chunk(n)=0
    
Case 121:        ;Kaikousen
    a=3:b=6:c=9
    If chunkSeq(n)>0 And chunkSeq(n)<=a Then chunkPic(n)=ptPic(100,1):chunkPic_(n)=ptPic_(100,1)
    If chunkSeq(n)>a And chunkSeq(n)<=b Then chunkPic(n)=ptPic(100,2):chunkPic_(n)=ptPic_(100,2)
    If chunkSeq(n)>b And chunkSeq(n)<=c Then chunkPic(n)=ptPic(100,3):chunkPic_(n)=ptPic_(100,3)
    
    If chunkSeq(n) > c Then chunk(n)=0
    
Case 122:        ;Kiryoku-ryuu explosion
    a=1:b=2:endSeq=24
    If chunkSeq(n)>0 And chunkSeq(n)<=a Then chunkPic(n)=ptPic(101,1):chunkPic_(n)=ptPic_(101,1)
    If chunkSeq(n)>a And chunkSeq(n)<=b Then chunkPic(n)=ptPic(101,2):chunkPic_(n)=ptPic_(101,2)
    
    idx2=2:picIdx=3
    For idx=5 To endSeq Step 3
        If chunkSeq(n)>idx2 And chunkSeq(n)<=idx Then chunkPic(n)=ptPic(101,picIdx):chunkPic_(n)=ptPic_(101,picIdx)
        idx2=idx:picIdx=picIdx+1
    Next
    
    If chunkSeq(n) > endSeq Then chunk(n)=0
    
Case 123:        ;Pre-Kiryoku-ryuu
    endSeq=12
    
    picIdx=1
    For idx=1 To endSeq
        If chunkSeq(n)=idx Then chunkPic(n)=ptPic(102,picIdx):chunkPic_(n)=ptPic(102,picIdx)
        picIdx=picIdx+1
        If picIdx=9 Then picIdx=1
    Next

    If chunkSeq(n) > endSeq Then chunk(n)=0

Case 124:         ;jumping knee lift
    a=12

    If chunkSeq(n) >= 1 And chunkSeq(n) < a Then chunkPic(n)=ptPic(99,1):chunkPic_(n)=ptPic_(99,1)
    If chunkSeq(n) >= a Then chunk(n)=0
    
Case 125:        ;Buy a subzero doll
    a=1
    If chunkSeq(n)=a Then chunkPic(n)=ptPic(103,1):chunkPic_(n)=ptPic(103,1)
    If chunkSeq(n) > a Then chunk(n)=0
    
Case 126:        ;Buy a scorpion doll
    a=1
    If chunkSeq(n)=a Then chunkPic(n)=ptPic(104,1):chunkPic_(n)=ptPic(104,1)
    If chunkSeq(n)>a Then chunk(n)=0

Case 127:        ;Charge start (dbz)
    isChunkRenderLowPrio(n)=1
    chunkFollowOwner(n)=1
    seq1=3
    endSeq=16
    
    If chunkSeq(n) > 0 And chunkSeq(n) <= seq1 Then 
        chunkPic(n)=ptPic(105,1):chunkPic_(n)=ptPic(105,1)
    Else
        chunkPic(n)=ptPic(105,chunkSeq(n)-2):chunkPic_(n)=ptPic(105,chunkSeq(n)-2)    
    End If
        
    If chunkSeq(n) > endSeq Then chunk(n)=0

Case 128:        ;Charge continued (dbz)
    isChunkRenderLowPrio(n)=1
    chunkFollowOwner(n)=1
    seq1=1:seq2=2:seq3=3:seq4=4:seq5=5:seq6=6
    
    If chunkSeq(n) = seq1 Then chunkPic(n)=ptPic(106,1):chunkPic_(n)=ptPic_(106,1)
    If chunkSeq(n) = seq2 Then chunkPic(n)=ptPic_(106,1):chunkPic_(n)=ptPic(106,1)
    If chunkSeq(n) = seq3 Then chunkPic(n)=ptPic(106,1):chunkPic_(n)=ptPic_(106,1)
    If chunkSeq(n) = seq4 Then chunkPic(n)=ptPic(106,2):chunkPic_(n)=ptPic_(106,2)
    If chunkSeq(n) = seq5 Then chunkPic(n)=ptPic_(106,2):chunkPic_(n)=ptPic(106,2)
    If chunkSeq(n) = seq6 Then chunkPic(n)=ptPic(106,3):chunkPic_(n)=ptPic_(106,3)
    
    If chunkSeq(n) > seq6 Then chunk(n)=0
    
Case 129:        ;Pre-makuuhouidan
    seq1=3
    If chunkSeq(n) > 0 And chunkSeq(n) <= seq1 Then
        chunkPic(n)=ptPic(102,1):chunkPic_(n)=ptPic(102,1)
    End If
    
    If chunkSeq(n) > seq1 Then chunk(n)=0
    
Case 130:        ;Box test
    a=10            ;bright dot
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(13,1):chunkPic_(n)=ptPic(13,1)
    If chunkSeq(n) > a Then chunk(n)=0
    
Case 131:        ;Pre-makankousappou (ground)
    a=2:b=4
    If chunkSeq(n) > 0 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(107,1):chunkPic_(n)=ptPic_(107,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(107,2):chunkPic_(n)=ptPic_(107,2)
    
    If chunkSeq(n) > b Then chunk(n)=0
    
Case 132:        ;Makankousappou base (ground)
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(107,3):chunkPic_(n)=ptPic_(107,3)
    
    If chunkSeq(n) > 1 Then chunk(n)=0
    
Case 133:        ;Makankousappou 1st part A (ground)
    a=2
    If chunkSeq(n) <= a Then chunkPic(n)=ptPic(107,4):chunkPic_(n)=ptPic_(107,4)
    
    If chunkSeq(n) > a Then chunk(n)=0
    
Case 134:        ;Makankousappou 2nd part A (ground)
    a=2
    If chunkSeq(n) <= a Then chunkPic(n)=ptPic(107,5):chunkPic_(n)=ptPic_(107,5)
    
    If chunkSeq(n) > a Then chunk(n)=0
    
Case 135:        ;Makankousappou 1st part B (ground)
    a=2
    If chunkSeq(n) <= a Then chunkPic(n)=ptPic(107,6):chunkPic_(n)=ptPic_(107,6)
    
    If chunkSeq(n) > a Then chunk(n)=0
    
Case 136:        ;Makankousappou 2nd part B (ground)
    a=2
    If chunkSeq(n) <= a Then chunkPic(n)=ptPic(107,7):chunkPic_(n)=ptPic_(107,7)
    
    If chunkSeq(n) > a Then chunk(n)=0
    
Case 137:        ;Makankousappou impact (ground)
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(107,6):chunkPic_(n)=ptPic_(107,8)
    
    If chunkSeq(n) > 1 Then chunk(n)=0
    
Case 138:        ;Gohan
    seq1=6:seq2=seq1+1:seq3=seq2+1:seq4=seq3+1:seq5=seq4+1
    seq6=seq5+10:seq7=seq6+1
    owner=chunkOwner(n)
    
    If chunkSeq(n) > 0 And chunkSeq(n) <= seq1 Then chunkPic(n)=noPic:chunkPic_(n)=noPic
    If chunkSeq(n) > seq1 And chunkSeq(n) <= seq2 Then chunkPic(n)=ptPic(108,1):chunkPic_(n)=ptPic_(108,1)
    If chunkSeq(n) > seq2 And chunkSeq(n) < seq3 Then chunkPic(n)=noPic:chunkPic_(n)=noPic
    If chunkSeq(n) > seq3 And chunkSeq(n) < seq4 Then chunkPic(n)=ptPic(108,2):chunkPic_(n)=ptPic_(108,2)
    If chunkSeq(n) > seq4 And chunkSeq(n) < seq5 Then chunkPic(n)=noPic:chunkPic_(n)=noPic
    
    If chunkSeq(n) > seq5 And chunkSeq(n) < seq6 Then
        If chunkSeq(n) Mod 2 = 1 Then chunkPic(n)=ptPic(108,1):chunkPic_(n)=ptPic_(108,1)
        If chunkSeq(n) Mod 2 = 0 Then chunkPic(n)=ptPic(108,3):chunkPic_(n)=ptPic_(108,3)
    End If
    
    If chunkSeq(n) > seq6 And chunkSeq(n) < seq7 Then chunkPic(n)=noPic:chunkPic_(n)=noPic
    
    If chunkSeq(n) = seq7-1 Then
        y=zy(owner)-35
        If zFace(owner)=2 Then face=4:x=zx(n)+130
        If zFace(owner)=4 Then face=2:x=zx(n)-130
        spawnHelper(owner, x, y, face, 53)
    End If
    
    If chunkSeq(n) >= seq7 Then chunk(n)=0
    
Case 139:       ;Excalibur Up
    seq1=2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2
    isChunkRenderLowPrio(n)=1
    chunkFollowOwner(n)=1

    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(109,1):chunkPic_(n)=ptPic_(109,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(109,2):chunkPic_(n)=ptPic_(109,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(109,3):chunkPic_(n)=ptPic_(109,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(109,4):chunkPic_(n)=ptPic_(109,4)
    If chunkSeq(n)>seq4 Then chunk(n)=0
    
Case 140:       ;Excalibur Mid
    seq1=2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2
    isChunkRenderLowPrio(n)=1
    chunkFollowOwner(n)=1

    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(110,1):chunkPic_(n)=ptPic_(110,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(110,2):chunkPic_(n)=ptPic_(110,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(110,3):chunkPic_(n)=ptPic_(110,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(110,4):chunkPic_(n)=ptPic_(110,4)
    If chunkSeq(n)>seq4 Then chunk(n)=0

Case 141:       ;Hiryu Slash 1
    seq1=2:seq2=seq1+4:seq3=seq2+4:seq4=seq3+4
    chunkFollowOwner(n)=1
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(111,1):chunkPic_(n)=ptPic_(111,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(111,2):chunkPic_(n)=ptPic_(111,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(111,3):chunkPic_(n)=ptPic_(111,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(111,4):chunkPic_(n)=ptPic_(111,4)
    If chunkSeq(n)>seq4 Then chunk(n)=0
    
Case 142:       ;Juggernaut Blink
    seq1=2:seq2=4:seq3=6:seq4=8:seq5=10
    chunkFollowOwner(n)=1
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(112,1):chunkPic_(n)=ptPic_(112,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(112,2):chunkPic_(n)=ptPic_(112,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(112,3):chunkPic_(n)=ptPic_(112,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(112,4):chunkPic_(n)=ptPic_(112,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(112,5):chunkPic_(n)=ptPic_(112,5)
    
    If chunkSeq(n)>seq5 Then chunk(n)=0
    
Case 143:       ;Pre-super 2
    seq1=2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2:seq6=seq5+2:seq7=seq6+2
    chunkFollowOwner(n)=1
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(113,1):chunkPic_(n)=ptPic(113,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(113,2):chunkPic_(n)=ptPic(113,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(113,3):chunkPic_(n)=ptPic(113,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(113,4):chunkPic_(n)=ptPic(113,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(113,5):chunkPic_(n)=ptPic(113,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(113,6):chunkPic_(n)=ptPic(113,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(113,7):chunkPic_(n)=ptPic(113,7)

    If chunkSeq(n)>seq7 Then chunk(n)=0
    
Case 144:       ;Juggernaut Head Crush effect (head)
    seq1=2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2:seq6=seq5+2:seq7=seq6+2
    chunkFollowOwner(n)=1
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(114,1):chunkPic_(n)=ptPic_(114,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(114,2):chunkPic_(n)=ptPic_(114,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(114,3):chunkPic_(n)=ptPic_(114,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(114,4):chunkPic_(n)=ptPic_(114,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(114,5):chunkPic_(n)=ptPic_(114,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(114,6):chunkPic_(n)=ptPic_(114,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(114,7):chunkPic_(n)=ptPic_(114,7)

    If chunkSeq(n)>seq7 Then chunk(n)=0
    
Case 145:       ;Juggernaut Head Crush effect (ground)
    seq1=2:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2:seq6=seq5+2:seq7=seq6+2
    chunkFollowOwner(n)=1
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(115,1):chunkPic_(n)=ptPic_(115,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(115,2):chunkPic_(n)=ptPic_(115,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(115,3):chunkPic_(n)=ptPic_(115,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(115,4):chunkPic_(n)=ptPic_(115,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(115,5):chunkPic_(n)=ptPic_(115,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(115,6):chunkPic_(n)=ptPic_(115,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(115,7):chunkPic_(n)=ptPic_(115,7)

    If chunkSeq(n)>seq7 Then chunk(n)=0
    
Case 146:       ;Juggernaut dodge sillhouete
    If chunkSeq(n)<=2 Then chunkPic(n)=ptPic(116,1):chunkPic_(n)=ptPic_(116,1)
    
    If chunkSeq(n)>2 Then chunk(n)=0
    
Case 147:       ;Counter
    If chunkSeq(n)<=20 Then chunkPic(n)=ptPic(117,1):chunkPic_(n)=ptPic(117,1)
    
    If chunkSeq(n)>20 Then chunk(n)=0
    
Case 148:       ;Ground crack
    isChunkSolid(n)=1:chunkYAdj(n)=10:yChunkSpeed#(n)=4
    chunkWidth(n)=85:chunkHeight(n)=21
    seq1=3:seq2=6:seq3=141:seq4=146:seq5=151:seq6=156:seq7=161:seq8=166:seq9=171:seq10=176
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(118,1):chunkPic_(n)=ptPic_(118,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(118,2):chunkPic_(n)=ptPic_(118,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(118,3):chunkPic_(n)=ptPic_(118,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(118,4):chunkPic_(n)=ptPic_(118,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(118,5):chunkPic_(n)=ptPic_(118,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(118,6):chunkPic_(n)=ptPic_(118,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(118,7):chunkPic_(n)=ptPic_(118,7)
    If chunkSeq(n)>seq7 And chunkSeq(n)<=seq8 Then chunkPic(n)=ptPic(118,8):chunkPic_(n)=ptPic_(118,8)
    If chunkSeq(n)>seq8 And chunkSeq(n)<=seq9 Then chunkPic(n)=ptPic(118,9):chunkPic_(n)=ptPic_(118,9)
    If chunkSeq(n)>seq9 And chunkSeq(n)<=seq10 Then chunkPic(n)=ptPic(118,10):chunkPic_(n)=ptPic_(118,10)
    
    If chunkSeq(n)>seq10 Then chunk(n)=0
    
Case 149:       ;Wonderwoman Themyscira low 1 sillhouete
    If chunkSeq(n)<=4 Then chunkPic(n)=ptPic(119,1):chunkPic_(n)=ptPic_(119,1)
    
    If chunkSeq(n)>4 Then chunk(n)=0
    
Case 150:       ;Wonderwoman Themyscira low 2 sillhouete
    If chunkSeq(n)<=4 Then chunkPic(n)=ptPic(119,2):chunkPic_(n)=ptPic_(119,2)
    
    If chunkSeq(n)>4 Then chunk(n)=0
    
Case 151:       ;Ground crack half
    isChunkSolid(n)=1:chunkYAdj(n)=10:yChunkSpeed#(n)=4
    chunkWidth(n)=85:chunkHeight(n)=21
    seq1=3:seq2=6:seq3=141:seq4=146:seq5=151:seq6=156:seq7=161:seq8=166:seq9=171:seq10=176
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(121,1):chunkPic_(n)=ptPic_(121,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(121,2):chunkPic_(n)=ptPic_(121,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(121,3):chunkPic_(n)=ptPic_(121,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(121,4):chunkPic_(n)=ptPic_(121,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(121,5):chunkPic_(n)=ptPic_(121,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(121,6):chunkPic_(n)=ptPic_(121,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(121,7):chunkPic_(n)=ptPic_(121,7)
    If chunkSeq(n)>seq7 And chunkSeq(n)<=seq8 Then chunkPic(n)=ptPic(121,8):chunkPic_(n)=ptPic_(121,8)
    If chunkSeq(n)>seq8 And chunkSeq(n)<=seq9 Then chunkPic(n)=ptPic(121,9):chunkPic_(n)=ptPic_(121,9)
    If chunkSeq(n)>seq9 And chunkSeq(n)<=seq10 Then chunkPic(n)=ptPic(121,10):chunkPic_(n)=ptPic_(121,10)
    
    If chunkSeq(n)>seq10 Then chunk(n)=0

Case 152:       ;Falling rocks
    chunkEnd=1000
;========= Movement =============
    calcChunkXVelocity(n)
    calcChunkYVelocity(n)
;========== Render ==============
    If chunkSeq(n)>0 And chunkSeq(n)<=4 Then chunkPic(n)=ptPic(120,1):chunkPic_(n)=ptPic_(120,1)
    If chunkSeq(n) Mod 4=0 Then
        If chunkPic(n)=ptPic(120,1) Then
            chunkPic(n)=ptPic(120,2):chunkPic_(n)=ptPic_(120,2)
        Else If chunkPic(n)=ptPic(120,2)
            chunkPic(n)=ptPic(120,3):chunkPic_(n)=ptPic_(120,3)
        Else If chunkPic(n)=ptPic(120,3)
            chunkPic(n)=ptPic(120,4):chunkPic_(n)=ptPic_(120,4)
        Else If chunkPic(n)=ptPic(120,4):
            chunkPic(n)=ptPic(120,5):chunkPic_(n)=ptPic_(120,5)
        Else If chunkPic(n)=ptPic(120,5)
            chunkPic(n)=ptPic(120,6):chunkPic_(n)=ptPic_(120,6)
        Else If chunkPic(n)=ptPic(120,6)
            chunkPic(n)=ptPic(120,7):chunkPic_(n)=ptPic_(120,7)
        Else If chunkPic(n)=ptPic(120,7)
            chunkPic(n)=ptPic(120,8):chunkPic_(n)=ptPic_(120,8)
        Else If chunkPic(n)=ptPic(120,8)
            chunkPic(n)=ptPic(120,9):chunkPic_(n)=ptPic_(120,9)
        Else If chunkPic(n)=ptPic(120,9)
            chunkPic(n)=ptPic(120,10):chunkPic_(n)=ptPic_(120,10)
        Else If chunkPic(n)=ptPic(120,10)
            chunkPic(n)=ptPic(120,11):chunkPic_(n)=ptPic_(120,11)
        Else
            chunkPic(n)=ptPic(120,1):chunkPic_(n)=ptPic_(120,1)
        End If
    End If
    
    If chunkSeq(n)>chunkEnd Then chunk(n)=0
    
Case 153:       ;Falling rocks reverse
    chunkEnd=1000
;========= Movement =============
    calcChunkXVelocity(n)
    calcChunkYVelocity(n)
;========== Render ==============
    If chunkSeq(n)>0 And chunkSeq(n)<=4 Then chunkPic(n)=ptPic(120,11):chunkPic_(n)=ptPic_(120,11)
    If chunkSeq(n) Mod 4=0 Then
        If chunkPic(n)=ptPic(120,11) Then
            chunkPic(n)=ptPic(120,10):chunkPic_(n)=ptPic_(120,10)
        Else If chunkPic(n)=ptPic(120,10)
            chunkPic(n)=ptPic(120,9):chunkPic_(n)=ptPic_(120,9)
        Else If chunkPic(n)=ptPic(120,9)
            chunkPic(n)=ptPic(120,8):chunkPic_(n)=ptPic_(120,8)
        Else If chunkPic(n)=ptPic(120,8)
            chunkPic(n)=ptPic(120,7):chunkPic_(n)=ptPic_(120,7)
        Else If chunkPic(n)=ptPic(120,7)
            chunkPic(n)=ptPic(120,6):chunkPic_(n)=ptPic_(120,6)
        Else If chunkPic(n)=ptPic(120,6)
            chunkPic(n)=ptPic(120,5):chunkPic_(n)=ptPic_(120,5)
        Else If chunkPic(n)=ptPic(120,5)
            chunkPic(n)=ptPic(120,4):chunkPic_(n)=ptPic_(120,4)
        Else If chunkPic(n)=ptPic(120,4)
            chunkPic(n)=ptPic(120,3):chunkPic_(n)=ptPic_(120,3)
        Else If chunkPic(n)=ptPic(120,3)
            chunkPic(n)=ptPic(120,2):chunkPic_(n)=ptPic_(120,2)
        Else If chunkPic(n)=ptPic(120,2)
            chunkPic(n)=ptPic(120,1):chunkPic_(n)=ptPic_(120,1)
        Else
            chunkPic(n)=ptPic(120,11):chunkPic_(n)=ptPic_(120,11)
        End If
    End If
    
    If chunkSeq(n)>chunkEnd Then chunk(n)=0
    
Case 154:   ; Wonderwoman hit spark
    chunkEnd=21
    If chunkSeq(n)>0 And chunkSeq(n)<=3 Then chunkPic(n)=ptPic(122,1):chunkPic_(n)=ptPic(122,1)
    If chunkSeq(n) Mod 3=0 Then
        If chunkPic(n)=ptPic(122,1) Then
            chunkPic(n)=ptPic(122,2):chunkPic_(n)=ptPic(122,2)
        Else If chunkPic(n)=ptPic(122,2) Then
            chunkPic(n)=ptPic(122,3):chunkPic_(n)=ptPic(122,3)
        Else If chunkPic(n)=ptPic(122,3) Then
            chunkPic(n)=ptPic(122,4):chunkPic_(n)=ptPic(122,4)
        Else If chunkPic(n)=ptPic(122,4) Then
            chunkPic(n)=ptPic(122,5):chunkPic_(n)=ptPic(122,5)
        Else If chunkPic(n)=ptPic(122,5) Then
            chunkPic(n)=ptPic(122,6):chunkPic_(n)=ptPic(122,6)
        Else If chunkPic(n)=ptPic(122,6) Then
            chunkPic(n)=ptPic(122,7):chunkPic_(n)=ptPic(122,7)
        Else If chunkPic(n)=ptPic(122,7) Then
            chunkPic(n)=ptPic(122,8):chunkPic_(n)=ptPic(122,8)
        Else If chunkPic(n)=ptPic(122,8) Then
            chunkPic(n)=ptPic(122,9):chunkPic_(n)=ptPic(122,9)
        Else If chunkPic(n)=ptPic(122,9) Then
            chunkPic(n)=ptPic(122,10):chunkPic_(n)=ptPic(122,10)
        Else 
            chunkPic(n)=ptPic(122,1):chunkPic_(n)=ptPic(122,1)
        End If
    End If

    If chunkSeq(n)>chunkEnd Then chunk(n)=0
    
Case 155:   ;Fatality text
    seq1=1:seq2=seq1+3:seq3=seq2+3:seq4=seq3+3:seq5=seq4+3:seq6=seq5+3:seq7=seq6+3
    seq8=seq7+3:seq9=seq8+3:seq10=seq9+3:seq11=seq10+100
    
    If chunkSeq(n)=seq1 Then chunkPic(n)=ptPic(126,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(126,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(126,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(126,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(126,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(126,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(126,7)
    If chunkSeq(n)>seq7 And chunkSeq(n)<=seq8 Then chunkPic(n)=ptPic(126,8)
    If chunkSeq(n)>seq8 And chunkSeq(n)<=seq9 Then chunkPic(n)=ptPic(126,9)
    If chunkSeq(n)>seq9 And chunkSeq(n)<=seq10 Then chunkPic(n)=ptPic(126,10)
    If chunkSeq(n)>seq10 And chunkSeq(n)<=seq11 Then 
        If chunkSeq(n) Mod 5=0 Then
            If chunkPic(n)=ptPic(126,10) Then
                chunkPic(n)=ptPic(126,11)
            Else If chunkPic(n)=ptPic(126,11) Then
                chunkPic(n)=ptPic(126,12)
            Else If chunkPic(n)=ptPic(126,12) Then
                chunkPic(n)=ptPic(126,13)
            Else If chunkPic(n)=ptPic(126,13) Then
                chunkPic(n)=ptPic(126,14)
            Else If chunkPic(n)=ptPic(126,14) Then
                chunkPic(n)=ptPic(126,15)
            Else
                chunkPic(n)=ptPic(126,10)
            End If
        End If
    End If
    
    If chunkSeq(n)>seq11 Then chunk(n)=0
    
Case 156:   ;Ice spike hit
    a=10:b=14:c=18
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)=ptPic(44,3):chunkPic_(n)=ptPic_(44,3)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)=ptPic(44,4):chunkPic_(n)=ptPic_(44,4)
    If chunkSeq(n) > c Then chunk(n)=0
    
Case 157:   ;Raging spin (Large)
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(123,1):chunkPic_(n)=ptPic_(123,1)
    If chunkSeq(n) = 2 Then chunkPic(n)=ptPic(123,2):chunkPic_(n)=ptPic_(123,2)
    If chunkSeq(n) = 3 Then chunkPic(n)=ptPic(123,3):chunkPic_(n)=ptPic_(123,3)
    If chunkSeq(n) = 4 Then chunkPic(n)=ptPic(123,4):chunkPic_(n)=ptPic_(123,4)
    If chunkSeq(n) = 5 Then chunkPic(n)=ptPic(123,5):chunkPic_(n)=ptPic_(123,5)
    If chunkSeq(n) = 6 Then chunkPic(n)=ptPic(123,6):chunkPic_(n)=ptPic_(123,6)
    If chunkSeq(n) = 7 Then chunkPic(n)=ptPic(123,7):chunkPic_(n)=ptPic_(123,7)
    If chunkSeq(n) = 8 Then chunkPic(n)=ptPic(123,8):chunkPic_(n)=ptPic_(123,8)
    If chunkSeq(n) = 9 Then chunkPic(n)=ptPic(123,9):chunkPic_(n)=ptPic_(123,9)
    
    If chunkSeq(n) > 9 Then chunk(n)=0
    
Case 158:   ;Raging spin (Medium)
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(124,1):chunkPic_(n)=ptPic_(124,1)
    If chunkSeq(n) = 2 Then chunkPic(n)=ptPic(124,2):chunkPic_(n)=ptPic_(124,2)
    If chunkSeq(n) = 3 Then chunkPic(n)=ptPic(124,3):chunkPic_(n)=ptPic_(124,3)
    If chunkSeq(n) = 4 Then chunkPic(n)=ptPic(124,4):chunkPic_(n)=ptPic_(124,4)
    If chunkSeq(n) = 5 Then chunkPic(n)=ptPic(124,5):chunkPic_(n)=ptPic_(124,5)
    If chunkSeq(n) = 6 Then chunkPic(n)=ptPic(124,6):chunkPic_(n)=ptPic_(124,6)
    If chunkSeq(n) = 7 Then chunkPic(n)=ptPic(124,7):chunkPic_(n)=ptPic_(124,7)
    If chunkSeq(n) = 8 Then chunkPic(n)=ptPic(124,8):chunkPic_(n)=ptPic_(124,8)
    If chunkSeq(n) = 9 Then chunkPic(n)=ptPic(124,9):chunkPic_(n)=ptPic_(124,9)
    
    If chunkSeq(n) > 9 Then chunk(n)=0
    
Case 159:   ;Raging spin (Small)
    If chunkSeq(n) = 1 Then chunkPic(n)=ptPic(125,1):chunkPic_(n)=ptPic_(125,1)
    If chunkSeq(n) = 2 Then chunkPic(n)=ptPic(125,2):chunkPic_(n)=ptPic_(125,2)
    If chunkSeq(n) = 3 Then chunkPic(n)=ptPic(125,3):chunkPic_(n)=ptPic_(125,3)
    If chunkSeq(n) = 4 Then chunkPic(n)=ptPic(125,4):chunkPic_(n)=ptPic_(125,4)
    If chunkSeq(n) = 5 Then chunkPic(n)=ptPic(125,5):chunkPic_(n)=ptPic_(125,5)
    If chunkSeq(n) = 6 Then chunkPic(n)=ptPic(125,6):chunkPic_(n)=ptPic_(125,6)
    If chunkSeq(n) = 7 Then chunkPic(n)=ptPic(125,7):chunkPic_(n)=ptPic_(125,7)
    If chunkSeq(n) = 8 Then chunkPic(n)=ptPic(125,8):chunkPic_(n)=ptPic_(125,8)
    If chunkSeq(n) = 9 Then chunkPic(n)=ptPic(125,9):chunkPic_(n)=ptPic_(125,9)
    
    If chunkSeq(n) > 9 Then chunk(n)=0
    
Case 160:   ;Raging stars (Inward)
    endSeq=20
    chOwn=chunkOwner(n)
    If zFace(chOwn)=2 Then xAdj=3 Else xAdj=-3
    
    chunkPic(n)=ptPic(127,1):chunkPic_(n)=ptPic_(127,1)
    If xChunk#(n) > zx(chOwn)+xAdj Then xChunk#(n)=xChunk#(n)-3
    If xChunk#(n) <= zx(chOwn)+xAdj Then xChunk#(n)=xChunk#(n)+3
    
    If yChunk#(n) > zy(chOwn)-(zheight(chOwn)/2) Then yChunk#(n)=yChunk#(n)-3
    If yChunk#(n) <= zy(chOwn)-(zheight(chOwn)/2) Then yChunk#(n)=yChunk#(n)+3
    
    If chunkSeq(n)>endSeq Then chunk(n)=0
    
Case 161:   ;Axe Slash
    chunkFollowOwner(n)=1
    seq1=12
    If chunkSeq(n)>0 And chunkSeq(n)<=12 Then chunkPic(n)=ptPic(128,1):chunkPic_(n)=ptPic_(128,1)
    If chunkSeq(n)=13 Then chunkPic(n)=ptPic(128,2):chunkPic_(n)=ptPic_(128,2)
    If chunkSeq(n)=14 Then chunkPic(n)=ptPic(128,3):chunkPic_(n)=ptPic_(128,3)
    If chunkSeq(n)=15 Then chunkPic(n)=ptPic(128,4):chunkPic_(n)=ptPic_(128,4)
    If chunkSeq(n)=16 Then chunkPic(n)=ptPic(128,5):chunkPic_(n)=ptPic_(128,5)
    If chunkSeq(n)=17 Then chunkPic(n)=ptPic(128,6):chunkPic_(n)=ptPic_(128,6)
    If chunkSeq(n)=18 Then chunkPic(n)=ptPic(128,7):chunkPic_(n)=ptPic_(128,7)
    If chunkSeq(n)=19 Then chunkPic(n)=ptPic(128,8):chunkPic_(n)=ptPic_(128,8)
    If chunkSeq(n)=20 Then chunkPic(n)=ptPic(128,9):chunkPic_(n)=ptPic_(128,9)
    
    If chunkSeq(n)>20 Then chunk(n)=0
    
Case 162:   ;Axe disappear
    seq1=19:seq2=seq1+2:seq3=seq2+2:seq4=seq3+2:seq5=seq4+2:seq6=seq5+2:seq7=seq6+2
    
    If chunkSeq(n)>0 And chunkSeq(n)<=seq1 Then chunkPic(n)=ptPic(129,1):chunkPic_(n)=ptPic_(129,1)
    If chunkSeq(n)>seq1 And chunkSeq(n)<=seq2 Then chunkPic(n)=ptPic(129,2):chunkPic_(n)=ptPic_(129,2)
    If chunkSeq(n)>seq2 And chunkSeq(n)<=seq3 Then chunkPic(n)=ptPic(129,3):chunkPic_(n)=ptPic_(129,3)
    If chunkSeq(n)>seq3 And chunkSeq(n)<=seq4 Then chunkPic(n)=ptPic(129,4):chunkPic_(n)=ptPic_(129,4)
    If chunkSeq(n)>seq4 And chunkSeq(n)<=seq5 Then chunkPic(n)=ptPic(129,5):chunkPic_(n)=ptPic_(129,5)
    If chunkSeq(n)>seq5 And chunkSeq(n)<=seq6 Then chunkPic(n)=ptPic(129,6):chunkPic_(n)=ptPic_(129,6)
    If chunkSeq(n)>seq6 And chunkSeq(n)<=seq7 Then chunkPic(n)=ptPic(129,7):chunkPic_(n)=ptPic_(129,7)

    If chunkSeq(n)>seq7 Then chunk(n)=0
    
Case 163:   ;Raging stars (Outward)
    endSeq=20
    chOwn=chunkOwner(n)
    If zFace(chOwn)=2 Then xAdj=3 Else xAdj=-3
    
    chunkPic(n)=ptPic(127,1):chunkPic_(n)=ptPic_(127,1)
    If xChunk#(n) >= zx(chOwn)+5 Or xChunk#(n) <= zx(chOwn)-5 Then
        If xChunk#(n) > zx(chOwn)+xAdj Then xChunk#(n)=xChunk#(n)+3
        If xChunk#(n) <= zx(chOwn)+xAdj Then xChunk#(n)=xChunk#(n)-3
    End If
    If yChunk#(n) >= (zy(chOwn)-(zHeight(chOwn)/2))+5 Or yChunk#(n) <= (zy(chOwn)-(zHeight(chOwn)/2))-5 Then
        If yChunk#(n) > zy(chOwn)-(zheight(chOwn)/2) Then yChunk#(n)=yChunk#(n)+3
        If yChunk#(n) <= zy(chOwn)-(zheight(chOwn)/2) Then yChunk#(n)=yChunk#(n)-3
    End If
    
    If chunkSeq(n)>endSeq Then chunk(n)=0
    
Case 164: ;Evil Ryu Stance Electricity
    If chunkSeq(n)<=1 Then chunkPic(n)=ptPic(130,1):chunkPic_(n)=ptPic_(130,1)
    If chunkSeq(n) Mod 2 = 0 Then
        If chunkPic(n)=ptPic(130,1) Then 
            chunkPic(n)=ptPic(130,2):chunkPic_(n)=ptPic_(130,2)
        Else If chunkPic(n)=ptPic(130,2) Then
            chunkPic(n)=ptPic(130,3):chunkPic_(n)=ptPic_(130,3)
        Else If chunkPic(n)=ptPic(130,3) Then
            chunkPic(n)=ptPic(130,4):chunkPic_(n)=ptPic_(130,4)
        Else If chunkPic(n)=ptPic(130,4) Then
            chunkPic(n)=ptPic(130,5):chunkPic_(n)=ptPic_(130,5)
        Else If chunkPic(n)=ptPic(130,5) Then
            chunkPic(n)=ptPic(130,6):chunkPic_(n)=ptPic_(130,6)
        Else 
            chunkPic(n)=ptPic(130,1):chunkPic_(n)=ptPic_(130,1)
        End If
    End If
    
    If chunkSeq(n)>12 Then chunk(n)=0

Default
    a=5:b=10:c=14    ;Blocking
    If chunkSeq(n) => 1 And chunkSeq(n) =< a Then chunkPic(n)=ptPic(3,1):chunkPic_(n)= ptPic(3,1)
    If chunkSeq(n) > a And chunkSeq(n) =< b Then chunkPic(n)= ptPic(1,2):chunkPic_(n)= ptPic(1,2)
    If chunkSeq(n) > b And chunkSeq(n) =< c Then chunkPic(n)= ptPic(1,3):chunkPic_(n)= ptPic(1,3)
    If chunkSeq(n) > c Then chunk(n)=0

End Select

If chunk(n)=0 And n=chunkAmount Then chunkAmount=n-1

End Function

Function calcChunkXVelocity(n)
    If isActiveCharacter(chunkOwner(n))=1
        xChunkVelocity#(n)=(25 * xChunkForce#(n)) / (chunkSeq(n) / 52.0)
        xChunk(n)=xChunk#(n)+xChunkVelocity#(n)
    End If
End Function

Function calcChunkYVelocity(n)
    If isActiveCharacter(chunkOwner(n))=1
        yChunk(n)=yChunk#(n)-yChunkForce#(n)
        yChunkForce#(n)=yChunkForce#(n)-1
    End If
End Function