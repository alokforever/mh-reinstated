Function setObjPos(n)
    For i=1 To 40
        zSpecialObjX(n,i)=3
        zSpecialObjY(n,i)=zHeight(n)/2
    Next

Select curGuy(n)
case 1 ; Evil Ryu
    zWalkObjX(n,0)=-3.0383 :zWalkObjY(n,0)=51
    zWalkObjX(n,1)=-4.27832 :zWalkObjY(n,1)=59.0
    zWalkObjX(n,2)=-3.03833 :zWalkObjY(n,2)=54
    zWalkObjX(n,3)=0.361664 :zWalkObjY(n,3)=56
    zWalkObjX(n,4)=-1.43832 :zWalkObjY(n,4)=55
    zWalkObjX(n,5)=-1.43832 :zWalkObjY(n,5)=55
    zWalkObjX(n,6)=-2.23831 :zWalkObjY(n,5)=53
    
    If stanceMode(n)=1 Then
        zStanceObjX#(n,1)=-5.82922 :zStanceObjY#(n,1)=57.0
        zStanceObjX#(n,2)=-5.82922 :zStanceObjY#(n,2)=57.0
        zStanceObjX#(n,3)=2.17078 :zStanceObjY#(n,3)=55.0
        zStanceObjX#(n,4)=-5.82922 :zStanceObjY#(n,4)=61.0
        zStanceObjX#(n,5)=-6.82922 :zStanceObjY#(n,5)=54.0
        zStanceObjX#(n,6)=-9.82922 :zStanceObjY#(n,6)=52.0
        zStanceObjX#(n,7)=-19.829 :zStanceObjY#(n,7)=44.0
        zStanceObjX#(n,8)=-18.8292 :zStanceObjY#(n,8)=42.0
        zStanceObjX#(n,9)=-19.8292 :zStanceObjY#(n,9)=43.0
        zStanceObjX#(n,10)=-32.8292 :zStanceObjY#(n,10)=32.0
        zStanceObjX#(n,11)=-32.8292 :zStanceObjY#(n,11)=34.0
        zStanceObjX#(n,12)=-35.2577 :zStanceObjY#(n,12)=31.0
        zStanceObjX#(n,13)=-35.1577 :zStanceObjY#(n,13)=31.0
        zStanceObjX#(n,14)=-34.9583 :zStanceObjY#(n,14)=32.0
        zStanceObjX#(n,15)=-34.9583 :zStanceObjY#(n,15)=39.0
        zStanceObjX#(n,16)=-33.9583 :zStanceObjY#(n,16)=39.0
        zStanceObjX#(n,17)=-33.9583 :zStanceObjY#(n,17)=35.0
        zStanceObjX#(n,18)=-33.9583 :zStanceObjY#(n,18)=37.0
        zStanceObjX#(n,19)=-33.9583 :zStanceObjY#(n,19)=37.0
        zStanceObjX#(n,20)=-33.9583 :zStanceObjY#(n,20)=39.0
        zStanceObjX#(n,21)=-33.9583 :zStanceObjY#(n,21)=34.0
        zStanceObjX#(n,22)=-30.9583 :zStanceObjY#(n,22)=36.0
        zStanceObjX#(n,23)=-31.9583 :zStanceObjY#(n,23)=36.0
        zStanceObjX#(n,24)=-30.9583 :zStanceObjY#(n,24)=36.0
        zStanceObjX#(n,25)=-30.9583 :zStanceObjY#(n,25)=36.0
        zStanceObjX#(n,26)=-17.9583 :zStanceObjY#(n,26)=41.0
        zStanceObjX#(n,27)=-14.9583 :zStanceObjY#(n,27)=33.0
        zStanceObjX#(n,28)=-16.9583 :zStanceObjY#(n,28)=31.0
        zStanceObjX#(n,29)=-16.9583 :zStanceObjY#(n,29)=31.0
        zStanceObjX#(n,30)=-21.9583 :zStanceObjY#(n,30)=35.0
        zStanceObjX#(n,31)=-30.9583 :zStanceObjY#(n,31)=34.0
        zStanceObjX#(n,32)=-30.9583 :zStanceObjY#(n,32)=37.0
        zStanceObjX#(n,33)=-33.9583 :zStanceObjY#(n,33)=35.0
        zStanceObjX#(n,34)=-33.9583 :zStanceObjY#(n,34)=35.0
    End If
    
Case 2: ;Rash
    zWalkObjX(n,0)=-5 :zWalkObjY(n,0)=20
    zWalkObjX(n,1)=6 :zWalkObjY(n,1)=23
    zWalkObjX(n,2)=-13 :zWalkObjY(n,2)=22
    zWalkObjX(n,3)=-8 :zWalkObjY(n,3)=20
    
Case 3: ;Spider-man
    zWalkObjX(n,0)=-10 :zWalkObjY(n,0)=10
    zWalkObjX(n,1)=-11 :zWalkObjY(n,1)=11
    zWalkObjX(n,2)=-13 :zWalkObjY(n,2)=12
    zWalkObjX(n,3)=-13 :zWalkObjY(n,3)=10
    
Case 4: ;Mario
    zWalkObjX(n,0)=-11 :zWalkObjY(n,0)=15
    zWalkObjX(n,1)=7 :zWalkObjY(n,1)=17
    zWalkObjX(n,2)=-11 :zWalkObjY(n,2)=14
    zWalkObjX(n,3)=-6 :zWalkObjY(n,3)=13
    
Case 5: ;Michaelangelo
    zWalkObjX(n,0)=-6 :zWalkObjY(n,0)=22
    zWalkObjX(n,1)=-6 :zWalkObjY(n,1)=20
    zWalkObjX(n,2)=-5 :zWalkObjY(n,2)=21
    zWalkObjX(n,3)=-7 :zWalkObjY(n,3)=20
    
Case 6: ;Strider Hiryu
    zWalkObjX(n,0)=-5 :zWalkObjY(n,0)=21
    zWalkObjX(n,1)=-5 :zWalkObjY(n,1)=22
    zWalkObjX(n,2)=-5 :zWalkObjY(n,2)=21
    zWalkObjX(n,3)=-4 :zWalkObjY(n,3)=21
    
Case 7: ;Batman
    zWalkObjX(n,0)=-4 :zWalkObjY(n,0)=20
    zWalkObjX(n,1)=-8 :zWalkObjY(n,1)=20
    zWalkObjX(n,2)=16 :zWalkObjY(n,2)=14
    zWalkObjX(n,3)=-2 :zWalkObjY(n,3)=15
    
Case 8: ;Predator
    zWalkObjX(n,0)=-5 :zWalkObjY(n,0)=23
    zWalkObjX(n,1)=4 :zWalkObjY(n,1)=22
    zWalkObjX(n,2)=-11 :zWalkObjY(n,2)=24
    zWalkObjX(n,3)=-5 :zWalkObjY(n,3)=24
    
Case 9: ;Goku
    zWalkObjX(n,0)=2 :zWalkObjY(n,0)=23
    zWalkObjX(n,1)=2 :zWalkObjY(n,1)=23
    zWalkObjX(n,2)=-2 :zWalkObjY(n,2)=23
    zWalkObjX(n,3)=-2 :zWalkObjY(n,3)=23
    
Case 10: ;Richter Belmont
    zWalkObjX(n,0)=15 :zWalkObjY(n,0)=21
    zWalkObjX(n,1)=7 :zWalkObjY(n,1)=23
    zWalkObjX(n,2)=-11 :zWalkObjY(n,2)=20
    zWalkObjX(n,3)=-6 :zWalkObjY(n,3)=19
    
Case 11: ;Wolverine
    For iter=0 To zStanceFrames(n)
        zStanceObjX(n,iter)=-2 :zStanceObjY(n,iter)=23
    Next
    
    For iter=0 To zWalkFrames(n)
        zWalkObjX(n,iter)=-2 :zWalkObjY(n,iter)=23
    Next
    
Case 12: ;Scorpion
    For iter=0 To zStanceFrames(n)
        zStanceObjX(n,iter)=-2 :zStanceObjY(n,iter)=23
    Next
    
    For iter=0 To zWalkFrames(n)
        zWalkObjX(n,iter)=-2 :zWalkObjY(n,iter)=23
    Next
    
Case 13: ;Sub Zero
    For iter=0 To zStanceFrames(n)
        zStanceObjX(n,iter)=-2 :zStanceObjY(n,iter)=23
    Next

    For iter=0 To zWalkFrames(n)
        zWalkObjX(n,iter)=-2 :zWalkObjY(n,iter)=23
    Next
    
Case 14: ;Wonder Woman
    zWalkObjX(n,0)=-9.80109 :zWalkObjY(n,0)=62.0
    zWalkObjX(n,1)=-10.2011 :zWalkObjY(n,1)=63.0
    zWalkObjX(n,2)=-9.60114 :zWalkObjY(n,2)=66.0
    zWalkObjX(n,3)=-10.0012 :zWalkObjY(n,3)=67.0
    zWalkObjX(n,4)=-12.4012 :zWalkObjY(n,4)=63.0
    zWalkObjX(n,5)=-11.8809 :zWalkObjY(n,5)=64.0
    zWalkObjX(n,6)=-11.2809 :zWalkObjY(n,6)=60.0
    zWalkObjX(n,7)=-11.2809 :zWalkObjY(n,7)=60.0
    zWalkObjX(n,8)=-10.681 :zWalkObjY(n,8)=60.0
    zWalkObjX(n,9)=-12.0006 :zWalkObjY(n,9)=58.0
    zWalkObjX(n,10)=-12.4006 :zWalkObjY(n,10)=60.0
    zWalkObjX(n,11)=-9.80066 :zWalkObjY(n,11)=61.0
    zWalkObjX(n,12)=-10.2007 :zWalkObjY(n,12)=58.0
    zWalkObjX(n,13)=-9.60071 :zWalkObjY(n,13)=61.0
    zWalkObjX(n,14)=-7.00073 :zWalkObjY(n,14)=64.0
    zWalkObjX(n,15)=-7.40076 :zWalkObjY(n,15)=64.0
    zWalkObjX(n,16)=-5.80078 :zWalkObjY(n,16)=66.0
    zWalkObjX(n,17)=-6.14063 :zWalkObjY(n,17)=65.0
    zWalkObjX(n,18)=-6.02063 :zWalkObjY(n,18)=64.0
    zWalkObjX(n,19)=-7.46063 :zWalkObjY(n,19)=64.0
    zWalkObjX(n,20)=-5.86066 :zWalkObjY(n,20)=61.0
    zWalkObjX(n,21)=-7.26068 :zWalkObjY(n,21)=61.0
    zWalkObjX(n,22)=-7.26068 :zWalkObjY(n,22)=61.0
    zWalkObjX(n,23)=-8.20099 :zWalkObjY(n,23)=57.0
    zWalkObjX(n,24)=-8.60101 :zWalkObjY(n,24)=59.0
    zWalkObjX(n,25)=-9.00104 :zWalkObjY(n,25)=58.0
    zWalkObjX(n,26)=-8.40106 :zWalkObjY(n,26)=59.0
    
    zStanceObjX#(n,1)=-18.7203 :zStanceObjY#(n,1)=43.0
    zStanceObjX#(n,2)=-18.82 :zStanceObjY#(n,2)=42.0
    zStanceObjX#(n,3)=-20.82 :zStanceObjY#(n,3)=40.0
    zStanceObjX#(n,4)=-19.82 :zStanceObjY#(n,4)=40.0
    zStanceObjX#(n,5)=-21.6805 :zStanceObjY#(n,5)=38.0
    zStanceObjX#(n,6)=-23.6805 :zStanceObjY#(n,6)=39.0
    zStanceObjX#(n,7)=-23.4402 :zStanceObjY#(n,7)=39.0
    zStanceObjX#(n,8)=-22.7203 :zStanceObjY#(n,8)=39.0
    zStanceObjX#(n,9)=-24.7203 :zStanceObjY#(n,9)=41.0
    zStanceObjX#(n,10)=-24.7203 :zStanceObjY#(n,10)=40.0
    zStanceObjX#(n,11)=-20.7203 :zStanceObjY#(n,11)=40.0
    zStanceObjX#(n,12)=-18.7203 :zStanceObjY#(n,12)=43.0
    
    For i = 3 To 12
        If i <> 6 Then zSpecialObjX(n,i)=51.8491:zSpecialObjY(n,i)=21.4001
    Next
    
    zSpecialObjX(n,1)=19.9636:zSpecialObjY(n,1)=32.6002
    zSpecialObjX(n,2)=19.9636:zSpecialObjY(n,2)=32.6002
    zSpecialObjX(n,6)=21.6436:zSpecialObjY(n,6)=78.6002
    zSpecialObjX(n,13)=-19.6:zSpecialObjY(n,13)=49.5998
    zSpecialObjX(n,14)=-19.6:zSpecialObjY(n,14)=63.5998
    zSpecialObjX(n,15)=-13.6:zSpecialObjY(n,15)=51.5998
    zSpecialObjX(n,16)=-5.59996:zSpecialObjY(n,16)=49.5998
    zSpecialObjX(n,17)=-5.59996:zSpecialObjY(n,17)=49.5998
    zSpecialObjX(n,18)=14.4:zSpecialObjY(n,18)=18.5998
    zSpecialObjX(n,19)=22.4:zSpecialObjY(n,19)=21.5998
    zSpecialObjX(n,24)=23.3586:zSpecialObjY(n,24)=41.0
    zSpecialObjX(n,25)=1.35857:zSpecialObjY(n,25)=67.0
    zSpecialObjX(n,27)=-1.12891:zSpecialObjY(n,27)=95.5999
    zSpecialObjX(n,28)=-1.12891:zSpecialObjY(n,28)=95.5999
    zSpecialObjX(n,29)=16.3955:zSpecialObjY(n,29)=78.6
    zSpecialObjX(n,30)=16.3955:zSpecialObjY(n,30)=78.6
    zSpecialObjX(n,38)=40.4:zSpecialObjY(n,38)=16.7998
    zSpecialObjX(n,39)=40.4:zSpecialObjY(n,39)=16.7998
    
Case 15: ;Juggernaut
    For iter=0 To zWalkFrames(n)
        zWalkObjX(n,iter)=-6 :zWalkObjY(n,iter)=9
    Next
    
    For iter=0 To zStanceFrames(n)
        zStanceObjX(n,iter)=0 :zStanceObjY(n,iter)=25
    Next
    
Case 16: ;Piccolo
    zWalkObjX(n,0)=14 :zWalkObjY(n,0)=36
    zWalkObjX(n,1)=14 :zWalkObjY(n,1)=36
    zWalkObjX(n,2)=15 :zWalkObjY(n,2)=36
    zWalkObjX(n,3)=15 :zWalkObjY(n,3)=36
    
    For iter=0 To zStanceFrames(n)
        zStanceObjX(n,iter)=15 :zStanceObjY(n,iter)=36
    Next

Case 40    ;turtle
    For i = 1 To 7
        zSpecialObjX(n,i)=0
        zSpecialObjY(n,i)=5
    Next
    
    zWalkObjX(n,0)=6.04126 :zWalkObjY(n,0)=28.0
    zWalkObjX(n,1)=8.44122 :zWalkObjY(n,1)=29.0
    zWalkObjX(n,2)=4.40143 :zWalkObjY(n,2)=28.0
    zWalkObjX(n,3)=6.52142 :zWalkObjY(n,3)=27.0
    zWalkObjX(n,4)=8.12134 :zWalkObjY(n,4)=27.0
    zWalkObjX(n,5)=6.52124 :zWalkObjY(n,5)=28.0

    zStanceObjX#(n,1)=9.83569 :zStanceObjY#(n,1)=25.0
    zStanceObjX#(n,2)=9.83569 :zStanceObjY#(n,2)=26.0
    zStanceObjX#(n,3)=10.8357 :zStanceObjY#(n,3)=25.0
    zStanceObjX#(n,4)=10.8357 :zStanceObjY#(n,4)=26.0
    zStanceObjX#(n,5)=9.83569 :zStanceObjY#(n,5)=27.0
    zStanceObjX#(n,6)=10.8357 :zStanceObjY#(n,6)=26.0
    zStanceObjX#(n,7)=10.8357 :zStanceObjY#(n,7)=24.0
    zStanceObjX#(n,8)=9.83569 :zStanceObjY#(n,8)=26.0
    zStanceObjX#(n,9)=8.83569 :zStanceObjY#(n,9)=29.0
    zStanceObjX#(n,10)=9.83569 :zStanceObjY#(n,10)=26.0
    zStanceObjX#(n,11)=10.8357 :zStanceObjY#(n,11)=23.0
    zStanceObjX#(n,12)=10.8357 :zStanceObjY#(n,12)=26.0
    zStanceObjX#(n,13)=8.83569 :zStanceObjY#(n,13)=26.0
    zStanceObjX#(n,14)=10.8357 :zStanceObjY#(n,14)=25.0
    zStanceObjX#(n,15)=10.8357 :zStanceObjY#(n,15)=26.0
    zStanceObjX#(n,16)=10.8357 :zStanceObjY#(n,16)=26.0
    
Case 51: ;Gray Ninja
    zWalkObjX(n,0)=-5 :zWalkObjY(n,0)=21
    zWalkObjX(n,1)=-5 :zWalkObjY(n,1)=22
    zWalkObjX(n,2)=-5 :zWalkObjY(n,2)=21
    zWalkObjX(n,3)=-4 :zWalkObjY(n,3)=21
    
End Select
End Function