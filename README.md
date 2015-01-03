1111-game
=========

A fun numerical game, runs in Java SE 1.8

By Vicky Qijing Zhang, a beginner in Java and Python.

How this game works:
=========
0) Open Player.java and Game.java in Eclipse or any other IDE of your choice. Run main method in Game.java and the game starts in the console!

1) This game starts with two players, each having two numbers in their two hands, one hand displaying one number. At initialization, they are both 1. 

2) If player A uses his one hand to hit player B’s one hand, the value on player A’s hand is renewed to be the sum of the two numbers on two hands. If the sum exceeds 10, take the last digit, or MOD 10. 

3) The players take turns and can choose to hit either hand of his opponent with either hand of his own, but can’t hit his own hand. The player who first reaches 0 (or 10, whose MOD is 0) wins. 
