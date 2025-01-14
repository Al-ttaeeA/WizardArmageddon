/**************************************
 * Project:         WizardArmageddon
 * Program:         Armor.java
 **************************************/
package wizardarmageddon;

public class Armor {
    //Fields
    private final String name;
    private final int armor;
    private final int cost;
    
    //Constructor
    
    /**************************************
     * Argument based constructor
     * @param name - The name of the armor
     * @param armor - The armor value of the armor
     * @param cost - The cost of the armor
     **************************************/
    Armor(String name, int armor, int cost){
        this.name = name;
        this.armor = armor;
        this.cost = cost;
    }
    
    //No Arg Constructor, this makes a default armor that the user starts with
    Armor(){
        name = "Leather Armor";
        armor = 80;
        cost = 0;
    }
    
    //Get commands
    
    /**********************
     * getName
     * @return - The name of the armor
     **********************/
    public String getName(){
        return this.name;
    }
    
    /********************
     * getArmor
     * @return - The armor value of the armor
     ********************/
    public int getArmor(){
        return this.armor;
    }
    
    /*******************
     * getCost
     * @return - The cost of the armor
     *******************/
    public int getCost(){
        return this.cost;
    }
    
    
    /***********************
     * toString
     * This converts the armor's data to a string
     * @return - A string that has the armor's data
     ***********************/
    public String toString(){
        String string = "Name: " + name + "\n\nArmor: " + armor + "\n\nCost: " + cost + " G";
        return string;
    }
}
