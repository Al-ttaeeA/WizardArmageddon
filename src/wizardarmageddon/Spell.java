/**************************************
 * Project:         WizardArmageddon
 * Program:         Spell.java
 **************************************/
package wizardarmageddon;

public class Spell {
    //Fields
    private final String name;
    private final int damageConstant;
    private final int damageMultiplier;
    private final double dodge;
    private final String element;
    
    //Constructors
    
    /*****************************************************************************************
     * Argument based constructor
     * @param name - The name of the spell
     * @param damageConstant - The damage constant of the spell
     * @param damageMultiplier - The damage multiplier of the spell
     * @param dodge - The dodge chance of the spell
     * @param element - The element of the spell
     *****************************************************************************************/
    Spell(String name, int damageConstant, int damageMultiplier, double dodge, String element){
        this.name = name;
        this.damageConstant = damageConstant;
        this.damageMultiplier = damageMultiplier;
        this.dodge = dodge;
        this.element = element;
    }
    
    //No Arg Constructor, this makes a default spell
    Spell(){
        name = "Nothing (literally does nothing)";
        damageConstant = 0;
        damageMultiplier = 0;
        dodge = 0;
        element = "[Nothing]";
    }
    
    /****************************************
     * getDamage
     * This method gets a random damage of the spell based on the staff modifier
     * @param staffModifier - The staff damage multiplier 
     * @return - The random damage of the spell
     ****************************************/
    public int getDamage(double staffModifier){
        return (int) ((this.damageConstant + (this.damageMultiplier * Commands.getRandom(10))) * staffModifier);
    }
    
    //Get commands
    
    /**********************
     * getName
     * @return - The name of the spell
     **********************/
    public String getName(){
        return this.name;
    }
    
    /*****************************
     * getDamageConstant
     * @return - The damage contant of the spell
     *****************************/
    public int getDamageConstant(){
        return this.damageConstant;
    }
    
    /*******************************
     * getDamageMultiplier
     * @return - The damage multiplier of the spell
     *******************************/
    public int getDamageMultiplier(){
        return this.damageMultiplier;
    }
    
    /***********************
     * getDodge
     * @return - The dodge chance of the spell
     ***********************/
    public double getDodge(){
        return this.dodge;
    }
    
    /*************************
     * getElement
     * @return - The element of the spell
     *************************/
    public String getElement(){
        return this.element;
    }
    
    
    /***********************
     * toString
     * This method converts the stats of the spell into a string
     * @return - A string that has the stats of the spell
     ***********************/
    public String toString(){
        int minDamage = damageConstant + damageMultiplier;
        int maxDamage = damageConstant + (damageMultiplier * 10);
        
        String string = "Name: " + name + "\n\nElement: " + element + "\n\nBase Minimum Damage: " + minDamage + "\n\nBase Maximum Damage: " + maxDamage + "\n\nDodge Chance: " + (dodge*100) + "%";
        return string;
    }
}
