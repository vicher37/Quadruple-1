## This file outlines the general process of the game, and also initializes a new game.

import java.util.Scanner;

public class Game {

	private static Scanner in;

	public static void main(String[] args)
	{
		Player playera = new Player(0);
		Player playerb = new Player(1);
				
		in = new Scanner(System.in);
		
		System.out.println("Welcome to the 1111 game! You are Player A, and the computer is Player B. You both start with 1 in both hands.");
		System.out.println("It's your turn now.");
		
		System.out.print("Player A: ");
        	playera.returnResult();
                
        	System.out.print("Player B: ");
        	playerb.returnResult();
		System.out.println("If you want to make the hit yourself, press h. ");
		System.out.println("If you want the computer to make a more rational move for you, press r.");
			
		playera.HitProcess(playerb);
			
	}
	
}
