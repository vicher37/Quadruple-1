import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * The class Player defines what players can do. It can make a hit, make a designated hit, make a random hit, and make a rational hit.
 * It can check whether either of the players has won.
 * It can also return the current numbers and the index of the next player.
 * 
 * @Vicky Zhang
 * @20141111
 */
public class Player
{
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private static Scanner in;
    private int playerNum;
    private int playerNumToPlay;

    /**
     * Constructor for objects of class Player
     */
    public Player(int num)
    {
        list.add(0, 1); 
        list.add(1, 1); 
        this.playerNum = num;
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
        if (playerNumToPlay == 0)
        {
            this.list.set(meNum, hit(this.list.get(meNum), playerb.list.get(youNum)));
        }
        else if (playerNumToPlay == 1)
        {
            playerb.list.set(meNum, hit(playerb.list.get(meNum), this.list.get(youNum)));
        }
    }
    
    /**
     * Use number a to hit number b. a is either a1 or a2, b is either b1 or b2. 
     * 
     * @param  a   one of player A's numbers
     * @param  b   one of player B's numbers
     * @return     the result of hitting a with b, which is the sum of a and b MOD by 10
     */
    
    public void HitProcess(Player playerb)
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
                    
                    System.out.println("Please indicate which hand of your opponent you want to hit, 0 for left, 1 for right");
                    in = new Scanner(System.in);
                    int youNum = in.nextInt();

                    this.designatedHit(playerb, meNum, youNum);
                    
                    System.out.print("Player A (You): ");
                    this.returnResult();
                    
                    System.out.print("Player B (Computer): ");
                    playerb.returnResult();
                    
                    this.changePlayer();
                    
                    this.winCheckProcess(playerb);
                    
                }
                else if (input.equals("r"))
                {
                    this.moreRationalHit(playerb);
                    System.out.print("Player A (You): ");
                    this.returnResult();
                    System.out.print("Player B (Computer): ");
                    playerb.returnResult();
                    
                    this.changePlayer();
                    
                    this.winCheckProcess(playerb);
                }
                    
        }
        
    }
    
    
    /**
     * randomly make a hit
     * 
     * @param  playerb   the player to be hit
     * @return  void
     */
    public void randomHit(Player playerb)
    {
        Random randomGenerator = new Random();
        int r1 = randomGenerator.nextInt(2);
        int r2 = randomGenerator.nextInt(2);
        this.list.set(r1, hit(this.list.get(r1), playerb.list.get(r2)));
    }

    /**
     * A more calculated hit. It checks whether the current player can arrive at 10 (i.e. 0) in the current situation. If yes, take the move that leads to 10.
     * 
     * @param  playerb   the player to be hit
     * @return  void
     */
    public void moreRationalHit(Player playerb)
    {
        boolean moved = false;
        
        for (int i = 0; i < this.list.size(); i++)
        {
            for (int num : playerb.list)
            {
                if ((this.list.get(i)+num)%10 == 0)
                {
                    this.list.set(i, hit(this.list.get(i), num));
                    moved = true;
                }
                
            }
        }
        
        if (moved == false)
        {
            randomHit(playerb);
        }

    }

   
    /**
     * print out name of the player and the numbers it currently has.
     * 
     * @param  void
     * @return  void
     */
    public void returnResult()
    {
        
        System.out.println(this.list);
    }
    
    
    /**
     * check whether the player has won (i.e. has 0 in its number list).
     * 
     * @param  void
     * @return  whether the player has won (boolean variable win)
     */
    public boolean winCheck()
    {
        boolean win = false;
        for (int num : this.list)
        {
            if (num == 0)
            {
                win = true;
            }            
        }
        return win;
    }
    
    
     /**
     * checks if either of the player has won. If not, decide whose turn is next, and trigger respective processes. If yes, 
     * announce the winner.
     * 
     * @param  playerb  the other player in the game
     * @return  void
     */
    public void winCheckProcess(Player playerb)
    {
        if (this.winCheck() == false && playerb.winCheck() == false)
        {
            System.out.println("No one has won yet. Let's continue.");
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            if (returnPlayerToPlay() == 0)
            {
               
                System.out.println("It's your turn. If you want to make the hit yourself, press h. "
                    + "If you want the computer to make a more rational move for you, press r.");
                in = new Scanner(System.in);
                this.HitProcess(playerb);
            }
            else if (returnPlayerToPlay() == 1)
            {
                System.out.println("It's Player B (Computer)'s turn. The computer will make a move now.");
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            ;
                playerb.moreRationalHit(this);
                
                System.out.print("Player A (You): ");
                this.returnResult();
                
                System.out.print("Player B (Computer): ");
                playerb.returnResult();
                
                this.changePlayer();
                
                this.winCheckProcess(playerb);
                
            }
            
        }
        else if (this.winCheck() == true)
        {
            System.out.println("Player A (You) has won!");
        }
        else if (playerb.winCheck() == true)
        {
            System.out.println("Player B (Computer) has won!");
        }
        
    }
    
    
     /**
     * change player after the previous player has made a move.
     * 
     * @param  void
     * @return  void
     */
    public void changePlayer()
    {
        
        if (playerNumToPlay == 0)
        {
            playerNumToPlay = 1;
        }
        else if (playerNumToPlay == 1)
        {
            playerNumToPlay = 0;
        }
        
    }
    
    
     /**
     * return the next player to play.
     * 
     * @param  void
     * @return  the index of the next player to play
     */
    public int returnPlayerToPlay()
    {
        return playerNumToPlay;
        
    }
    

}
