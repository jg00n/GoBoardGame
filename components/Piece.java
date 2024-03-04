package components;

public class Piece{
   final char[] VALID_PIECE_TYPES={' ', 'B', 'W'};

   private char pieceType;

   //Default constructor initializes the piece as empty.
   public Piece(){
    setPiece(VALID_PIECE_TYPES[0]);
   }
   public Piece(char pieceType){
    setPiece(pieceType);
   }

   public char getPiece(){
    return pieceType;
   }

   public void setPiece(char pieceType){
    //Validate the piece type against the array of valid piece types.
    for (char validType: VALID_PIECE_TYPES){
        if (pieceType ==validType){
            this.pieceType = validType;
            return;
        }
    }
    throw new IllegalArgumentException("Invalid piece type:"+ pieceType);
   }
}