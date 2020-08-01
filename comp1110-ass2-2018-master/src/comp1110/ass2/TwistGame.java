package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the Twist Game
 * <p>
 * The game is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 */
public class TwistGame {

    private static BigGrid bg;

    public TwistGame(BigGrid bg){
        this.bg = bg;
    }

  /**
   * Determine whether a piece or Peg placement is well-formed according to the following:
   * - it consists of exactly four characters
   * - the first character is in the range a .. l (pieces and pegs)
   * - the second character is in the range 1 .. 8 (columns)
   * - the third character is in the range A .. D (rows)
   * - the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a Peg)
   *
   * @param piecePlacement A string describing a single piece or Peg placement
   * @return True if the placement is well-formed
   */
  public static boolean isPlacementWellFormed(String piecePlacement) {
    // FIXME Task 2: determine whether a piece or Peg placement is well-formed
    if(piecePlacement.length()==4){
      if(piecePlacement.charAt(0) >= 'a'&& piecePlacement.charAt(0)<= 'l'){
        if(piecePlacement.charAt(1) >= '1'&& piecePlacement.charAt(1)<= '8'){
          if(piecePlacement.charAt(2) >= 'A'&& piecePlacement.charAt(2)<= 'D'){
            if(piecePlacement.charAt(0) >= 'a'&& piecePlacement.charAt(0)<= 'h'){
              if(piecePlacement.charAt(3) >= '0' && piecePlacement.charAt(3)<= '7' ){
                return true;
              }
            }
            if(piecePlacement.charAt(0) >= 'i'&& piecePlacement.charAt(0)<= 'l'){
              if(piecePlacement.charAt(3) == '0'){
                return true;
              }
            }
          }
        }
      }
    }
  return false;
  }

  /**
   * Determine whether a placement string is well-formed:
   * - it consists of exactly N four-character piece placements (where N = 1 .. 15);
   * - each piece or Peg placement is well-formed
   * - each piece or Peg placement occurs in the correct alphabetical order (duplicate pegs can be in either order)
   * - no piece or red Peg appears more than once in the placement
   * - no green, blue or yellow Peg appears more than twice in the placement
   *
   * @param placement A string describing a placement of one or more pieces and pegs
   * @return True if the placement is well-formed
   */
  public static boolean isPlacementStringWellFormed(String placement) {
    // FIXME Task 3: determine whether a placement is well-formed
    if(placement.length()%4==0 && placement.length()/4>=1 && placement.length()/4 <= 15){
      for(int i = 0; i < placement.length(); i= i+4){
          if(!isPlacementWellFormed(placement.substring(i, i+4))){
              return false;
          }
      }
      for(int i = 0; i + 4 < placement.length(); i= i+4){
        if(placement.substring(i, i+4).charAt(0) > placement.substring(i+4, i+8).charAt(0)){
          return false;
        }
      }
      for(int i = 0; i  < placement.length(); i= i+4){
        for(int j = i + 4; j < placement.length(); j= j+4){
          if(     placement.substring(i, i+4).charAt(0) == placement.substring(j, j+4).charAt(0) &&
                  placement.substring(i, i+4).charAt(0)!= 'j' && placement.substring(i, i+4).charAt(0)!= 'k' &&
                  placement.substring(i, i+4).charAt(0)!= 'l') {
            return false;
          }
        }
      }
      int countGreen = 0;
      int countBlue = 0;
      int countYellow = 0;
      for(int i = 0; i < placement.length(); i= i+4){
        if(placement.substring(i, i+4).charAt(0) =='k'){
          countGreen++;
        }
        if(placement.substring(i, i+4).charAt(0) =='j'){
          countBlue++;
        }
        if(placement.substring(i, i+4).charAt(0) =='l'){
          countYellow++;
        }
      }
      if(countBlue>2||countGreen>2 || countYellow>2){
        return false;
      }
      return true;
    }
    return false;
  }
  /**
   * Determine whether a placement string is valid.  To be valid, the placement
   * string must be well-formed and each piece placement must be a valid placement
   * according to the rules of the game.
   * - pieces must be entirely on the board
   * - pieces must not overlap each other
   * - pieces may only overlap pegs when the a) Peg is of the same color and b) the
   *   point of overlap in the piece is a hole.
   *
   * @param placement A placement sequence string
   * @return True if the placement sequence is valid
   */
  public static boolean isPlacementStringValid(String placement) {
    // FIXME Task 5: determine whether a placement string is valid
      ArrayList<String> eachPiecePlacement = new ArrayList<>();
      ArrayList<String> eachPegPlacement = new ArrayList<>();
    if(isPlacementStringWellFormed(placement)){
        bg = new BigGrid();
        bg.setEntirePlacement(placement);
        for(int i = 0; i < placement.length(); i += 4) {
            if(placement.charAt(i) < 'i')
                eachPiecePlacement.add(placement.substring(i,i+4));
            else if(placement.charAt(i) >= 'i' )
                eachPegPlacement.add(placement.substring(i,i+4));
        }
        for(String eachPeg : eachPegPlacement){
            bg = bg.putPeg(Peg.valueOf(String.valueOf(eachPeg.charAt(0))), eachPeg.substring(1,3));
        }
        for(String eachPiece : eachPiecePlacement){
            if(bg.isPieceOnBoard(PieceType.valueOf(eachPiece.substring(0,1) + eachPiece.substring(3,4)), eachPiece.substring(1,3))){
                if(bg.isPiecePlaceLegal(PieceType.valueOf(eachPiece.substring(0,1) + eachPiece.substring(3,4)), eachPiece.substring(1,3))){
                    bg = bg.putPiece(PieceType.valueOf(eachPiece.substring(0,1) + eachPiece.substring(3,4)), eachPiece.substring(1,3));
                }else{
                    bg.resetBG();
                    return false;
                }
            }
            else{
                bg.resetBG();
                return false;
            }
        }
        bg.resetBG();
        return true;
    }
    bg.resetBG();
    return false;
  }

  /**
   * Given a string describing a placement of pieces and pegs, return a set
   * of all possible next viable piece placements.   To be viable, a piece
   * placement must be a valid placement of a single piece.  The piece must
   * not have already been placed (ie not already in the placement string),
   * and its placement must be valid.   If there are no valid piece placements
   * for the given placement string, return null.
   *
   * When symmetric placements of the same piece are viable, only the placement
   * with the lowest rotation should be included in the set.
   *
   * @param placement A valid placement string (comprised of Peg and piece placements)
   * @return An set of viable piece placements, or null if there are none.
   */
  public static Set<String> getViablePiecePlacements(String placement) {
    // FIXME Task 6: determine the set of valid next piece placements
      List<Character> allPiecesType = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h'));
      List<Character> verticalPosition = new ArrayList<>(Arrays.asList('A','B','C','D'));
      List<Character> horizentalPosition = new ArrayList<>(Arrays.asList('1','2','3','4','5','6','7','8'));
      List<Character> pieceRotation = new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7'));
      Set<String> result = new HashSet<>();
      Set<String> symmetric = new HashSet<>();
      if(isPlacementStringValid(placement)){
          bg = new BigGrid();
          bg.setEntirePlacement(placement);
          List<Character> piecesExist= new ArrayList<>();
          List<String> piecePlacements = new ArrayList<>();
          List<String> pegPlacements = new ArrayList<>();
          for(int i = 0; i < placement.length(); i += 4) {
              if(placement.charAt(i) < 'i'){
                  piecesExist.add(placement.charAt(i));
                  piecePlacements.add(placement.substring(i,i+4));
              }else if(placement.charAt(i) >= 'i'){
                  pegPlacements.add(placement.substring(i,i+4));
              }
          }
          for(Character pieceType : piecesExist) {
              if (allPiecesType.contains(pieceType))
                  allPiecesType.remove(pieceType);
          }
          System.out.println(allPiecesType);
          System.out.println(pegPlacements);
          System.out.println(piecePlacements);
          for(String pegs : pegPlacements){
              bg = bg.putPeg(Peg.valueOf(pegs.substring(0,1)), pegs.substring(1,3));
          }
          for(int i = 0; i < 4; i ++){
              for(int j = 0; j < 8; j ++){
                  System.out.print(bg.getGridStatus()[i][j] + "      ");}
              System.out.println();
          }
          for(String pieces : piecePlacements){
              bg = bg.putPiece(PieceType.valueOf(pieces.substring(0,1) + pieces.substring(3,4)), pieces.substring(1,3));
          }
          System.out.println();
          for(int i = 0; i < 4; i ++){
              for(int j = 0; j < 8; j ++){
                  System.out.print(bg.getGridStatus()[i][j] + "      ");}
              System.out.println();
          }
          System.out.println();
          for(int i = 0; i < 4; i ++){
              for(int j = 0; j < 8; j ++){
                  System.out.print(bg.getGridColor()[i][j] + "      ");}
              System.out.println();
              }
          for(Character remainingPiece : allPiecesType){
              for(Character hPosition : horizentalPosition){
                  for(Character vPosition : verticalPosition){
                      for(Character pieceRota : pieceRotation) {
                          if(bg.isPieceOnBoard(PieceType.valueOf(remainingPiece.toString() + pieceRota.toString()), hPosition.toString() + vPosition.toString())
                                  && bg.isPiecePlaceLegal(PieceType.valueOf(remainingPiece.toString() + pieceRota.toString()), hPosition.toString() + vPosition.toString())){
                              result.add(remainingPiece.toString() + hPosition.toString() + vPosition.toString() + pieceRota.toString());
                          }
                      }
                  }
              }
          }
      }
      if(result.size() != 0) {
          for(String sy : result){
              if((sy.charAt(0) == 'c' && (sy.charAt(3) == '4' || sy.charAt(3) == '5' || sy.charAt(3) == '6' || sy.charAt(3) == '7'))
                      ||(sy.charAt(0) == 'h' && (sy.charAt(3) == '4' || sy.charAt(3) == '5' || sy.charAt(3) == '6' || sy.charAt(3) == '7'))){
                  symmetric.add(sy);
              }
          }
          result.removeAll(symmetric);
          bg.resetBG();
          return result;
      }
      else{
          bg.resetBG();
          return null;
      }
  }

  /**
   * Return an array of all unique solutions for a given starting placement.
   *
   * Each solution should be a 32-character string giving the placement sequence
   * of all eight pieces, given the starting placement.
   *
   * The set of solutions should not include any symmetric piece placements.
   *
   * In the IQ-Twist game, valid challenges can have only one solution, but
   * other starting placements that are not valid challenges may have more
   * than one solution.  The most obvious example is the unconstrained board,
   * which has very many solutions.
   *
   * @param placement A valid piece placement string.
   * @return An array of strings, each 32-characters long, describing a unique
   * unordered solution to the game given the starting point provided by placement.
   */
  public static String[] getSolutions(String placement) {
    // FIXME Task 9: determine all solutions to the game, given a particular starting placement
    return null;
  }
}
