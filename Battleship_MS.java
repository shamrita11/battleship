/*
 * File Name: Battleship_MS
 * Name: Shamrita Saravanakumar
 * Description:  The program will simulate the strategic type guessing game, Battleship.
 * The game involves the player placing their ships anywhere on the playing board. 
 * the oponent (the computer) will try to guess where your ships are. When it's your turn, 
 * try to guess where their ships are by choosing a tile on the opposing board. The point of the 
 * game is to try to sink all their battleships to win the game.
 * Date Created: May 28, 2022
 */

import java.util.*;
import java.util.concurrent.TimeUnit; // for time lag 

public class Battleship_MS {
    
    //variables
    static int rows = 8;
    static int columns = 8;
    static int pShips;
    static int cShips;
    static String playAgain = "";
    static String [][] ships = new String [rows][columns];
    static String [][] guesses = new String [rows] [columns];
   
   public static void main(String[] args) throws Exception {
       
       boolean play = true; //for play again feature
       ships s = new ships(); //ship object
       Scanner sc = new Scanner(System.in);
      
       do { //loops entire game
       //Game Introduction
        System.out.println("***********WELCOME TO THE GAME OF BATTLESHIP***********");
        TimeUnit.MILLISECONDS.sleep(2500);
        System.out.println("Enter your username: ");
        s.setName(sc.nextLine ());
        System.out.println ("\nWelcome " + s.getName() + " to the game of Battleship!");
        System.out.println("\nObjective of the Game: Be the first to sink your opponent's ships!");
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("\nRules of the Game: ");
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("1. Choose 5 coordinates to place your ships!");
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("2. Enter the region where you would like to attack.");
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("3. First player to sink all of the opposing ships successfully wins!!");
        
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("\nOkay " + s.getName() + ", the following is the format of your grid.");
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("You will choose your ship locations based on the X & Y coordinates you choose :)");
        //creating the game grid 
        guessGrid();

        //Legend 
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("\nLEGEND FOR CHARACTERS: ");
        System.out.println("1. 'X' represents player ships");
        System.out.println("2. 'C' represents computer ships");
        System.out.println("3. '-' represents player missed attacks");
        System.out.println("4. '.' represents computer missed attacks");
        System.out.println("5. '*' represents sunken ships");
        
        TimeUnit.MILLISECONDS.sleep(2500);
        System.out.println("\n****************BEGIN****************");
        
        //prints updated grid
        gameGrid();
        
        //choosing player's ship
        playerShips(s);
        
        //generates computer's ship
        computerShips(s);
        
        //takes turn's between the computer and player to guess where the ships are
        do {
        
        Playing(s);
        
        }while(s.getPShips() != 0 && s.getCShips() != 0);
        
        winner(play, s);
        
    }while(play == true);
 }
    
    /**
     * Description:The following method prints out the correct format of the game grid using the for loops 
     * in order to create the correct X and Y coordinates. 
     * Precondition: None
     * Postcondition: The program should print out the correct formatted 7 by 7 grid.
     **/
    public static void gameGrid(){
        //prints out the x values for the very first row
        System.out.println();
        System.out.print(" ");
        for(int i = 0; i < columns; i++)
                System.out.print(i + " ");
        System.out.println();

        //prints out the y values and spaces inbetween 
        for(int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                ships[i][j] = "  "; 
                if (j == 0)
                    System.out.print(i + ships[i][j]); //prints out the number and then spaces (the left side numbers)
                else if (j == ships[i].length - 1) 
                    System.out.print(ships[i][j] + i); //prints out the spaces and then the number (the right side numbers)
                else
                    System.out.print(ships[i][j]); //prints out the space in the middle 
            }
            System.out.println();
        }

        //prints out the x values for the very last row 
        System.out.print(" ");
        for(int i = 0; i < columns; i++)
            System.out.print(" " + i);
        System.out.println();
    }
    
    /**
     * Description: This method prints out the grid that is used for the computer and players to guess
     * their attacks. 
     * Precondition: None
     * Postcondition: The program should print out the correct 7 by 7 formatted grid.
     **/
    public static void guessGrid(){
        //prints out the x values for the very first row
        System.out.println();
        System.out.print(" ");
        for(int i = 0; i < columns; i++)
                System.out.print(i + " ");
        System.out.println();

        //prints out the y values and spaces inbetween 
        for(int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].length; j++) {
                guesses[i][j] = "  "; 
                if (j == 0)
                    System.out.print(i + guesses[i][j]); //prints out the number and then spaces (the left side numbers)
                else if (j == guesses[i].length - 1) 
                    System.out.print(guesses[i][j] + i); //prints out the spaces and then the number (the right side numbers)
                else
                    System.out.print(guesses[i][j]); //prints out the space in the middle 
            }
            System.out.println();
        }

        //prints out the x values for the very last row 
        System.out.print(" ");
        for(int i = 0; i < columns; i++)
            System.out.print(" " + i);
        System.out.println();
    }
    
    /**
     * Description: This method prints out the updated version of the grid including player and computer attacks
     * and each of their sunken ships. 
     * Precondition: None
     * Postcondition: The program should print out the correct formatted grid including misses and attacks. 
     **/
    public static void updateGrid(){
        System.out.println(); //prints a line
        
        //prints out the first row of x values
        System.out.print(" ");
        for(int i = 0; i < columns; i++)
            System.out.print(" " + i);
        System.out.println();

        //prints out the y values on either sides and space inbetween and adds player's ship
        for(int y = 0; y < guesses.length; y++) {
            System.out.print(" " + y);

            for (int x = 0; x < guesses[y].length; x++){
                System.out.print(guesses[x][y]);
            }

            System.out.println(y);
        }

        //prints out the last row of x values
        System.out.print(" ");
        for(int i = 0; i < columns; i++)
            System.out.print(" " + i);
        System.out.println();
    }
    
    /**
     * Description: This method deploys the users 5 ships by asking where they would place it based on the 
     * X and Y coordinates on the grid. The ships will be marked with the symbol 'X'.
     * Precondition: None
     * Postcondition: Users ships must be all correctly deployed on the correct coordinates, within the correct range.
     * @param - objects ships s
     */
    public static void playerShips(ships s){
        Scanner scan = new Scanner(System.in);
        int x = 0, y = 0;

        System.out.println("\nChoose where you would like to place your ships:");
        
        for (int i = 1; i <= s.getPShips(); ) { //prompts user input for all 5 ship coordinates
            System.out.print("Enter X coordinate (horizontal coordinate) for " + "ship " + i + ": ");
            x = scan.nextInt();
            System.out.print("Enter Y coordinate (vertical coordinate) for " + "ship " + i + ": ");
            y = scan.nextInt();

            if((x >= 0 && x < rows) && (y >= 0 && y < columns) && (ships[x][y] == "  "))
            {
                ships[x][y] =  "X" + " "; //places ship in appropriate location
                i++;

            }
            else if((x >= 0 && x < rows) && (y >= 0 && y < columns) && ships[x][y] == "X ") 
                System.out.println("\n****Sorry, you already have a ship here, choose another location.****\n"); //condition if the ships is put at the same place
            else if((x < 0 || x >= rows) || (y < 0 || y >= columns))
                System.out.println("\n\n*****Sorry, you have placed ships outside of the 7 by 7 grid range, please choose another location.****\n\n");
        }
        System.out.println();
        System.out.println (s.getName() + " ships have been chosen..............");
    }
    
    /**
     * Description: This method deploys the computer 5 ships randomly. The computers ships will be marked with the 
     * symbol 'C'.
     * Precondition: None
     * Postcondition: Computer ships must be all correctly deployed on the correct coordinates, within the correct range.
     * @param - objects ships s
     */
     public static void computerShips(ships s){
        int x = 0, y = 0;
        
        for (int i = 1; i <= s.getCShips(); ) {
            x = (int)(Math.random() * 8); //generates random x coordinate
            y = (int)(Math.random() * 8);//generates random y coordinate

            if((x >= 0 && x < rows) && (y >= 0 && y < columns) && (ships[x][y] == "  "))
            {
                ships[x][y] = "C" + " ";
                i++;
            }
        }
        
        System.out.println("\nComputer Ships have been chosen...........\n");
    }
    
    /**
     * Description: This method takes the respective turns between the player and the computer and will 
     * update the grid after each attack. During each time, the players ship counts will be displayed. 
     * Precondition: playersTurn(), computersTurn(), updateGrid() methods must be functioning. 
     * Postcondition: Ship counts for each player must be updated after each respective turn. 
     * @param - objects ships s
     */
    public static void Playing(ships s) throws Exception {
        playersTurn(s);
        computersTurn(s);
        updateGrid();
        System.out.println();
        
        //prints appropriate ship counts for the computer and player
        System.out.println("*****SHIP COUNT*****"); 
        System.out.println(s.getName() + "'s ships: " + s.getPShips() + "\nComputer ships: " + s.getCShips());
        System.out.println();
    }
    
    /**
     * Description: This method prompts user input on where they would like to attack and according to 
     * their response, either the opponent ships with be sunken or their attack is simply a miss. 
     * Precondition: None
     * Postcondition: According to users coordinates, attack will result in sunken ship or miss. 
     * @param - objects ship s
     */
    public static void playersTurn(ships s){
        System.out.println("Guess where your opponents ships are!");
        int x = -1, y = -1;
        
        do {
            Scanner s2 = new Scanner(System.in);
            //prompts user input on where they would like to attack
            System.out.print("Enter X coordinate (horizontal coordinate): ");
            x = s2.nextInt();
            System.out.print("Enter Y coordinate (vertical coordinate): ");
            y = s2.nextInt();

            if ((x >= 0 && x < rows) && (y >= 0 && y < columns))
            {
                if (guesses[x][y] == "- " || guesses [x][y] == "* ")
                    System.out.println("\n****Sorry, you alredy guessed this location.****\n"); //condition if the ships is put at the same place
                else if (ships[x][y] == "C ") //if computer ship is already there; computer loses ship
                {
                    System.out.println("\nBOOOOMM, You sunk a  ship!\n");
                    guesses[x][y] = "* ";
                    s.minusCSHIPS();
                }
                else if (ships[x][y] == "  "|| ships [x][y] == "X ") {
                    System.out.println("\n****Sorry, you missed****");
                    guesses[x][y] = "-" + " ";
                }
            }
            else if ((x < 0 || x >= rows) || (y < 0 || y >= columns))
                System.out.println("\nSorry that doesn't fall in the 7 by 7 grid\n");
        } while((x < 0 || x >= rows) || (y < 0 || y >= columns));
    }
    
    /**
     * Description: This method generates random x and y values for the computer attacks coordinates, 
     * and will return whether the opponents ships have been sunken or missed. 
     * Precondition: None
     * Postcondition: Random computer coordinates will be processed and appropriate action should be taken 
     * depending on the if statements.
     * @param - object ships s
     */
    public static void computersTurn(ships s) throws Exception {
        System.out.println("\nComputer's guess....");
        TimeUnit.MILLISECONDS.sleep(2500);
        
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 8); //generates random x coordinate
            y = (int)(Math.random() * 8); //generates random y coordinate

            if ((x >= 0 && x < rows) && (y >= 0 && y < columns))
            {
                if (guesses[x][y] == ". " || guesses [x][y] == "% ")
                    System.out.println("\n****Sorry, you alredy guessed this location.****\n"); //condition if the ships is put at the same place
                else if (ships[x][y] == "X ") //attacks player ship
                {
                    System.out.println("\nARGH, The computer sunk one of your ships!\n");
                    guesses[x][y] = "%" + " ";
                    s.minusPSHIPS();
                }
                else if (ships[x][y] == "  "|| ships [x][y] == "C ") { //attacks nothing
                    System.out.println("\nYAY, computer missed\n");
                    guesses [x][y] = "." + " ";
                }
            }   
               
        } while((x < 0 || x >= rows) || (y < 0 || y >= columns));  //keep re-prompting till valid guess
    }
    
     /**
     * Description: This method displays the winner and calls on the playAgain method to determine
     * if game will be played again.
     * Precondition: Either pShips or cShips must equal zero and playAgain method must be functioning.
     * Postcondition: boolean play must be updated and final scoreboard and winner must be displayed.
     * @param - the boolean variable play, object ships s. 
     * @return - the boolean variable play.
     */
    public static boolean winner(boolean play, ships s) {
        System.out.println("****************************\nFinal Scoreboard: \n" + s.getName() + "'s ships: " + s.getPShips() + "\nComputer ships: " + s.getCShips());
        if(s.getPShips() > 0 && s.getCShips() <= 0){
            System.out.println("\n***************YAY! CONGRATS! " + s.getName() + "won the battle :)***************");
                play = playAgain(playAgain, s, play); //calls on playAgain method
                if(pShips == -1)
                    play = false; //ends game
                }  
        
        else
            System.out.println("Sorry, " + s.getName() + ". Unfortunately, you lost the battle :(");
        System.out.println();
        
        return play; //returns the boolean method 
    }
    
    /**
     * Description: the method is to determine if the game should be played again based on user input.
     * Precondition: Once the game comes to an end, the user must input yes or no to determine if the game will run again  
     * Postcondition: The game will loop again if player says 'yes', or will not if the player says 'no'.
     * @param - the string of playAgain, the object ships s, and boolean play. 
     * @return boolean variable play (which will determine whether the game should be played again)   
     */
    public static boolean playAgain(String playAgain, ships s, boolean play) { 
        Scanner s3 = new Scanner(System.in);
        do{ 
            System.out.println("\nWould you like to play again? (yes or no)");
            playAgain = s3.nextLine();
            
            if (playAgain.equalsIgnoreCase("yes")) {
                s.setPShips(5); //restarts lives in order to play again
                s.setCShips(5); 
                play = true;
            }
                
            else if (playAgain.equalsIgnoreCase("no")) {
                System.out.println("\nThanks for playing Battleship:)\n");
                s.setPShips(-1); //exits the game
                s.setCShips(-1);
                play = false; 
            }
        } while ((!playAgain.equalsIgnoreCase("yes")) && (!playAgain.equalsIgnoreCase("no")));
       
        return play; 
        }
    }
