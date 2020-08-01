package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// this class is to create a checkerboard to play the game - by Yang Kong(u6275465)

public class BigGrid {
    private HashMap<Character, Integer> verPosition = new HashMap<Character, Integer>(){
        {
            put('A', 0);
            put('B', 1);
            put('C', 2);
            put('D', 3);
        }
    };

    //real-time placement string - by Yang Kong(u6275465)
    private String entirePlacement;

    //each little square has a status number:
    //0: nothing, 1: solid, -1: loop, 2: peg, 100: peg + loop - by Yang Kong(u6275465)
    private int[][] gridStatus;

    //color of each little square - by Yang Kong(u6275465)
    private String[][] gridColor;

    public BigGrid(){
        this.gridStatus = new int[4][8];
        this.gridColor = new String[][]{{" "," "," "," "," "," "," "," "}, {" "," "," "," "," "," "," "," "}, {" "," "," "," "," "," "," "," "}, {" "," "," "," "," "," "," "," "}};
    }

    public int[][] getGridStatus(){
        return this.gridStatus;
    }


    public void setEntirePlacement(String entirePlacement){
        this.entirePlacement = entirePlacement;
    }

    public String[][] getGridColor(){
        return this.gridColor;
    }

    public String getEntirePlacement(){
        return entirePlacement;
    }


    public void setGridColor(int v, int h, String color){
        this.gridColor[v][h] = color;
    }

    public void setGridStatus(int v, int h, int code){
        gridStatus[v][h] = code;
    }

    //reset bg to nothing - by Yang Kong(u6275465)
    public void resetBG(){
        for(int i = 0; i < 4; i ++) {
            for (int j = 0; j < 8; j ++) {
                this.setGridStatus(i, j, 0);
                this.setGridColor(i, j, "");
            }
        }
        entirePlacement = "";
    }

    //add a single piece placement to entire placement - by Yang Kong(u6275465)
    public void addPieceToEntirePlacement(PieceType piece, String position){
        String result = "";
        List<String> c_placement = new ArrayList<>();
        for(int i = 0; i < this.getEntirePlacement().length(); i += 4){
            c_placement.add(this.getEntirePlacement().substring(i, i + 4));
        }
        String newPiece = String.valueOf(piece.name().charAt(0)) + position + String.valueOf(piece.name().charAt(1));
        c_placement.add(newPiece);
        Collections.sort(c_placement);
        for(String s : c_placement){
            result = result + s;
        }
        this.entirePlacement = result;
    }

    //remove a single piece placement from entire placement - by Yang Kong(u6275465)
    public void removePieceFromEntirePlacement(PieceType piece){
        this.entirePlacement = this.getEntirePlacement().replaceAll(String.valueOf(piece.name().charAt(0)) + "(...)", "");
    }

    //remove a single piece from bg - by Yang Kong(u6275465)
    public void removePieceFromBG(PieceType piece, String position){
        int horiPosition = Integer.parseInt(position.substring(0,1)) -1;
        int vertPosition = verPosition.get(position.charAt(1));
        for(int i = 0; i < piece.getEachLoopStatus().length; i ++){
            for(int j = 0; j < piece.getEachLoopStatus()[0].length; j ++){
                if(this.getGridStatus()[vertPosition + i][horiPosition + j] == 0
                        || this.getGridStatus()[vertPosition + i][horiPosition + j] == 1
                        || this.getGridStatus()[vertPosition + i][horiPosition + j] == -1){
                    this.setGridStatus(vertPosition + i, horiPosition + j, 0);
                    this.setGridColor(vertPosition + i, horiPosition + j, "");
                }else if(this.getGridStatus()[vertPosition + i][horiPosition + j] == 100){
                    this.setGridStatus(vertPosition + i, horiPosition + j, 2);
                    this.setGridColor(vertPosition + i, horiPosition + j, piece.getColor()[i][j]);
                }else if(this.getGridStatus()[vertPosition + i][horiPosition + j] == 2){
                    this.setGridStatus(vertPosition + i, horiPosition + j, 2);
                    this.setGridColor(vertPosition + i, horiPosition + j, this.getGridColor()[vertPosition + i][horiPosition + j]);
                }
            }
        }
        removePieceFromEntirePlacement(piece);
    }

    //put a single peg in bg - by Yang Kong(u6275465)
    public BigGrid putPeg(Peg peg, String position){
        assert position.length() == 2;
        int horiPosition = Integer.parseInt(position.substring(0,1)) -1;
        int vertPosition = verPosition.get(position.charAt(1));
        this.setGridStatus(vertPosition,horiPosition,2);
        this.setGridColor(vertPosition,horiPosition,peg.getColor());
        return this;
    }

    //put a single piece in bg - by Yang Kong(u6275465)
    public BigGrid putPiece(PieceType piece, String position){
        assert position.length() == 2;
        int[][] c_status = this.getGridStatus();
        String[][] c_color = this.getGridColor();
        int horiPosition = Integer.parseInt(position.substring(0,1)) -1;
        int vertPosition = verPosition.get(position.charAt(1));
        if (this.isPieceOnBoard(piece, position) && this.isPiecePlaceLegal(piece, position)) {
            for (int i = 0; i < piece.getEachLoopStatus().length; i++) {
                for (int j = 0; j < piece.getEachLoopStatus()[0].length; j++) {
                    if (c_status[vertPosition + i][horiPosition + j] == 0
                            && piece.getEachLoopStatus()[i][j] == 0) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, 0);
                    } else if (c_status[vertPosition + i][horiPosition + j] == 0
                            && piece.getEachLoopStatus()[i][j] == 1) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, 1);
                        this.setGridColor(vertPosition + i, horiPosition + j, piece.getColor()[i][j]);
                    } else if (c_status[vertPosition + i][horiPosition + j] == 0
                            && piece.getEachLoopStatus()[i][j] == -1) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, -1);
                        this.setGridColor(vertPosition + i, horiPosition + j, piece.getColor()[i][j]);
                    } else if (c_status[vertPosition + i][horiPosition + j] == 1
                            && piece.getEachLoopStatus()[i][j] == 0) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, 1);
                    } else if (c_status[vertPosition + i][horiPosition + j] == -1
                            && piece.getEachLoopStatus()[i][j] == 0) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, -1);
                    } else if (c_status[vertPosition + i][horiPosition + j] == 2
                            && piece.getEachLoopStatus()[i][j] == -1 && c_color[vertPosition + i][horiPosition + j].equals(piece.getColor()[i][j])) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, 100);
                        this.setGridColor(vertPosition + i, horiPosition + j, piece.getColor()[i][j]);
                    } else if (c_status[vertPosition + i][horiPosition + j] == 2
                            && piece.getEachLoopStatus()[i][j] == 0) {
                        this.setGridStatus(vertPosition + i, horiPosition + j, 2);
                    }
                }
            }
        }
        return this;
    }

    // identify if player complete the game - by Yang Kong(u6275465)
    public boolean isCompleteGame(){
        int flag = 0;
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 8; j ++){
                if(this.getGridStatus()[i][j] != 0)
                    flag ++;
            }
        }
        if(flag == 32)
            return true;
        else
            return false;
    }

    // identify if a piece is on board or not - by Yang Kong(u6275465)
    public boolean isPieceOnBoard(PieceType piece, String position){
        assert position.length() == 2;
        int horiPosition = Integer.parseInt(position.substring(0,1)) - 1;
        int vertPosition = verPosition.get(position.charAt(1));
        if(horiPosition + piece.getEachLoopStatus()[0].length - 1 <= 7 && vertPosition + piece.getEachLoopStatus().length - 1 <= 3)
            return true;
        else
            return false;
    }

    // identify if a piece is legal or not
    // to implement task 5 - by Yang Kong(u6275465)
    public boolean isPiecePlaceLegal(PieceType piece, String position){
        int[][] c_status = this.getGridStatus();
        String[][] c_color = this.getGridColor();
        int horiPosition = Integer.parseInt(position.substring(0,1)) -1;
        int vertPosition = verPosition.get(position.charAt(1));
        for(int i = 0; i < piece.getEachLoopStatus().length; i ++){
            for(int j = 0; j < piece.getEachLoopStatus()[0].length; j ++){
                if(c_status[vertPosition + i][horiPosition + j] == 0
                        && (piece.getEachLoopStatus()[i][j] == 0 || piece.getEachLoopStatus()[i][j] == 1 || piece.getEachLoopStatus()[i][j] == -1)){

                }else if(c_status[vertPosition + i][horiPosition + j] == 1
                        && (piece.getEachLoopStatus()[i][j] == 1 || piece.getEachLoopStatus()[i][j] == -1)){
                    return false;
                }else if(c_status[vertPosition + i][horiPosition + j] == 1
                        && piece.getEachLoopStatus()[i][j] == 0){

                }else if(c_status[vertPosition + i][horiPosition + j] == -1
                        && (piece.getEachLoopStatus()[i][j] == 1 || piece.getEachLoopStatus()[i][j] == -1)){
                    return false;
                }else if(c_status[vertPosition + i][horiPosition + j] == -1
                        && piece.getEachLoopStatus()[i][j] == 0){

                }else if(c_status[vertPosition + i][horiPosition + j] == 2
                        && piece.getEachLoopStatus()[i][j] == -1
                        && c_color[vertPosition + i][horiPosition + j].equals(piece.getColor()[i][j])){

                }else if(c_status[vertPosition + i][horiPosition + j] == 2
                        && piece.getEachLoopStatus()[i][j] == 0){

                }else if(c_status[vertPosition + i][horiPosition + j] == 2
                        && piece.getEachLoopStatus()[i][j] == -1 && !(c_color[vertPosition + i][horiPosition + j].equals(piece.getColor()[i][j]))){
                    return false;
                }else if(c_status[vertPosition + i][horiPosition + j] == 2
                        && piece.getEachLoopStatus()[i][j] == 1){
                    return false;
                }else if(c_status[vertPosition + i][horiPosition + j] == 100
                        && (piece.getEachLoopStatus()[i][j] == 1 || piece.getEachLoopStatus()[i][j] == -1)){
                    return false;
                }
            }
        }
        return true;
    }

}
