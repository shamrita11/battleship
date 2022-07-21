/**
 * File Name: ships
 * Name: Shamrita Saravanakumar and Mashal Niazi
 * Description: This class will represent the ships for the battleship game
 * name --> the players's name
 * cShips --> computer ships
 * pShips --> players ships
 */
public class ships {
    //private variables
    private String name;
    private int cShips;
    private int pShips;
 
  public ships () {
       cShips = 5;
       pShips = 5;
       name = null;
       
   }
   
   //accessor methods
   public String getName () {
        return name;
   }
   
   public int getCShips () {
       return cShips;
   }
   
   public int getPShips () {
       return pShips;
   }
   
   //mutator methods
   public void setName (String n) {
       name = n;
   }
   
   public void setCShips (int c) {
       cShips = c;
   }
   
   public void setPShips (int p) {
       pShips = p;
   }
   
   /**
 * Description: The following method decreases a computer ship whenever called in the main method
 * Preconditon: cShips should not equal 0
 * Postcondition: Method decreses a computer ship
 */
   public void minusCSHIPS (){
       cShips--;
   }
   
    /**
 * Description: The following method decreases a player ship whenever called in the main method
 * Preconditon: pShips should not equal 0
 * Postcondition: Method decreses a player ship
 */
   public void minusPSHIPS (){
       pShips--;
   }
   
}   
