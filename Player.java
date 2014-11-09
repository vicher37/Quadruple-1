import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Write a description of class Player here.
 * 
 * @Vicky Zhang (your name) 
 * @20141107 (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private static Scanner in;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        list.add(0, 1); 
        list.add(1, 1); 
        
    }

    /**
     * Use number a to hit number b. a is either a1 or a2, b is either b1 or b2. 
     * 
     * @param  a   one of player A's numbers
     * @param  b   one of player B's numbers
     * @return     the result of hitting a with b, which is the sum of a and b MOD by 10
     */
    
    public int hit(int a, int b) 
    {
    	return a = (a+b)%10;
    	
    }
    
    /**
     * Let user designate whom to hit and which hand to hit 
     * 
     * @param  playerb   the player to be hit
     * @param  meNum  the hand you want to hit with, 0 for left hand, 1 for right hand
     * @param  youNum  the opponent's hand you want to hit, 0 for left hand, 1 for right hand
     * @return     void
     */
    
    public void designatedHit(Player playerb, int meNum, int youNum)
    {
    	this.list.set(meNum, hit(this.list.get(meNum), playerb.list.get(youNum)));
    }
    
    public void HitProcess(Player playerb)
    {
    	while (this.winCheck() == false && playerb.winCheck() == false)
    	{
    	in = new Scanner(System.in);
    	if (in.hasNext())
    	{
			String input = in.next();
			if (input.equals("h"))
				{
					
					System.out.println("Please indicate which hand of yours you want to move, 0 for left, 1 for right");
					in = new Scanner(System.in);
					int meNum = in.nextInt();
					
					System.out.println("Please indicate which hand of your opponent you want to move, 0 for left, 1 for right");
					in = new Scanner(System.in);
					int youNum = in.nextInt();
					
					this.designatedHit(playerb, meNum, youNum);
					
					System.out.print("Player A: ");
					this.returnResult();
					
					System.out.print("Player B: ");
					playerb.returnResult();
					
					this.winCheckProcess(playerb);
					
				}
				else if (input.equals("r"))
				{
					this.moreRationalHit(playerb);
					System.out.print("Player A: ");
					this.returnResult();
					System.out.print("Player B: ");
					playerb.returnResult();
					
					this.winCheckProcess(playerb);
				}
			
    	}
		
    	}
    	
    }
    
    
    //randomly make a hit
    public void randomHit(Player player2)
	{
    	Random randomGenerator = new Random();
		int r1 = randomGenerator.nextInt(2);
		int r2 = randomGenerator.nextInt(2);
		this.list.set(r1, hit(this.list.get(r1), player2.list.get(r2)));
	}

    //A more calculated hit, with a winning strategy.
	public void moreRationalHit(Player playerb)
	{
		for (int num1 : this.list)
		{
			for (int num2 : playerb.list)
			{
				if ((num1+num2)%10 == 0)
				{
					hit(num1, num2);
					
				}
				
			}
		}
		
		randomHit(playerb);
	}


	//further improvement: extends to n players...each with two hands still :)

	//public void twoStepsRationalHit()


	public void returnResult()
	{
		System.out.println(this.list);
	}
	
	public boolean winCheck()
	{
		boolean win = false;
		for (int num : this.list)
		{
			if (num == 0)
			{
				win = true;
			}
			else
			{
				win = false;
			}
		}
		return win;
		
		
	}
	
	public void winCheckProcess(Player playerb)
	{
		if (this.winCheck() == false && playerb.winCheck() == false)
		{
			System.out.println("No one has won yet. Let's continue. If you want to make the hit yourself, press h. "
					+ "If you want the computer to make a rational move for you, press r.");
			in = new Scanner(System.in);
			this.HitProcess(playerb);
			
		}
		else if (this.winCheck() == true)
		{
			System.out.println("Player A has won!");
		}
		else if (playerb.winCheck() == true)
		{
			System.out.println("Player B has won!");
		}
		
		
	}
	

}
