/**************************************
 * Project:         WizardArmageddon
 * Program:         Staff.java
 **************************************/
package wizardarmageddon;

public class Staff {
    //Fields
    private final String name;
    private final double dmgMultiplier;
    private final int cost;
    
    //Constructors
    
    /*************************************************
     * Argument based contructor
     * @param name - The name of the staff
     * @param dmgMultiplier - The damage multiplier of the staff
     * @param cost - The cost of the staff
     *************************************************/
    Staff(String name, double dmgMultiplier, int cost){
        this.name = name;
        this.dmgMultiplier = dmgMultiplier;
        this.cost = cost;
    }
    
    //No Arg Constructor, this makes a default staff that the user starts with
    Staff(){
        name = "Wretched Staff";
        dmgMultiplier = 1;
        cost = 0;
    }
    
    //Get commands
    
    /**********************
     * getName
     * @return - The name of the staff
     **********************/
    public String getName(){
        return this.name;
    }
    
    /**********************************
     * getDamageMultiplier
     * @return - The damage multiplier of the staff
     **********************************/
    public double getDamageMultiplier(){
        return this.dmgMultiplier;
    }
    
    /*******************
     * getCost
     * @return - The cost of the staff
     *******************/
    public int getCost(){
        return this.cost;
    }
    
    
    /***********************
     * toString
     * This converts the staff's data to a string
     * @return - A string that has the staff's data
     ***********************/
    public String toString(){
        String string = "Name: " + name + "\n\nDamage Multiplier: " + dmgMultiplier + "\n\nCost: " + cost + " G";
        return string;
    }
}
