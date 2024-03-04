package components;

public class Rules {
    public Rules(){
        //Initialize any rules.
    }
    public void getRules(){
        //Return a string containing the rules of WeiChi
        System.out.println("Here are the rules:");
    }

    public boolean checkRules(int row, int col, char pieceType, Board gameBoard){
        //Check statement if a move perfomed at the specified position is valid based on current rule set.
        
        
        return true;
    }

    public void applyRules(int row, int col, char pieceType, Board gameBoard){
        //Apply a specific rule set or actions after a move has been made.
    }
}
