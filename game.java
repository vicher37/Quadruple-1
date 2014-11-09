import java.util.Scanner;

public class Game {

	private static Scanner in;

	public static void main(String[] args)
	{
		Player playera = new Player();
		Player playerb = new Player();
		
		
		in = new Scanner(System.in);
		
		System.out.println("Welcome to the 1111 game! If you want to make the hit yourself, press h. "
				+ "If you want the computer to make a rational move for you, press r.");
		
		playera.HitProcess(playerb);
		
		
				
		
		
	
	}
	
}
