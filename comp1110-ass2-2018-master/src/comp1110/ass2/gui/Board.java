package comp1110.ass2.gui;

import comp1110.ass2.BigGrid;
import comp1110.ass2.PieceType;
import comp1110.ass2.TwistGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;


// this class create a visual board to play the game  - by Yang Kong(u6275465) and Ziang Xu(u6645802)


public class Board extends Application {
    private static final HashMap<Integer, String> verPosition = new HashMap<Integer, String>(){
        {
            put(0, "A");
            put(1, "B");
            put(2, "C");
            put(3, "D");
        }
    };
    private static final double BOARD_WIDTH = 933;
    private static final double BOARD_HEIGHT = 700;
    private static final double BACKGROUNDPIC_WIDTH = 603;
    private static final double BACKGROUNDPIC_HEIGHT = 330.4;
    private static final int EACH_LOOP_SIDE_LENGTH = 71;
    private static final String URI_BASE = "assets/";
    private double OX,OY,OTX,OTY;
    private Group root = new Group();

    // new game button
    private Button button1 = new Button("new game");

    //slider to change difficulty
    private Slider difficulty = new Slider();

    //real-time to show the current placement
    private Label placement = new Label("Current Placement: ");
    private TextField content = new TextField();

    //if player complete game, show "Good Job!"
    private Label goodJob = new Label("Good Job!");

    private ImageView backgroundPic = new ImageView(new Image(Viewer.class.getResource(URI_BASE + "background.png").toString()));
    private List<Piece> candiPiece= new ArrayList<>();


    static TwistGame twistGame;
    static BigGrid bg;

    // add not existing pieces to root
    // initialize game - by Yang Kong(u6275465)
    private void addCandidatePieceToRoot(){
        root.getChildren().clear();
        root.getChildren().add(button1);
        root.getChildren().add(backgroundPic);
        root.getChildren().add(difficulty);
        root.getChildren().add(placement);
        root.getChildren().add(content);
        root.getChildren().add(goodJob);
        goodJob.setOpacity(0);
        String startingPlacement = makeStartingPlacement();
        content.setText(startingPlacement);
        bg = new BigGrid();
        bg.setEntirePlacement(startingPlacement);
        twistGame = new TwistGame(bg);
        putStartingStatus(startingPlacement);
        addCandidatePiecesToList(startingPlacement);

        // put not existing pieces into two rows to make it look nicer - by Yang Kong(u6275465)
        root.getChildren().add(candiPiece.get(0));
        root.getChildren().add(candiPiece.get(4));
        candiPiece.get(0).setX(0);
        candiPiece.get(0).setY(BACKGROUNDPIC_HEIGHT);
        candiPiece.get(4).setX(0);
        candiPiece.get(4).setY(BACKGROUNDPIC_HEIGHT + 2 * EACH_LOOP_SIDE_LENGTH);
        for(int i = 1; i < 4; i ++){
            root.getChildren().add(candiPiece.get(i));
            candiPiece.get(i).setX(candiPiece.get(i-1).getX() + candiPiece.get(i-1).getFitWidth());
            candiPiece.get(i).setY(BACKGROUNDPIC_HEIGHT);
        }
        for(int j = 5; j < candiPiece.size(); j ++){
            root.getChildren().add(candiPiece.get(j));
            candiPiece.get(j).setX(candiPiece.get(j-1).getX() + candiPiece.get(j-1).getFitWidth());
            candiPiece.get(j).setY(BACKGROUNDPIC_HEIGHT + 2 * EACH_LOOP_SIDE_LENGTH);
        }
    }

    // get pieces which are not existed in starting placement - by Yang Kong(u6275465)
    private List<String> getCandidatePieceType(String startingPlacement){
        List<String> pieceType = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));
        for(int i = 0; i < startingPlacement.length(); i += 4){
            if(startingPlacement.charAt(i) < 'i')
                pieceType.remove(String.valueOf(startingPlacement.charAt(i)));
        }
        return pieceType;
    }

    private void addCandidatePiecesToList(String startingPlacement){
        candiPiece.clear();
        for(String type : getCandidatePieceType(startingPlacement)){
            candiPiece.add(new Piece(type));
        }
    }


    // make starting placement with different difficulties: starter, junior, expert, master, wizard - by Ziang Xu(u6645802)
    private String makeStartingPlacement(){
        Random random = new Random();
        int index = random.nextInt(4);
        final String[] STARTER = {
                "h4B2j5D0j6D0k7D0k8D0l3D0l4D0",
                "h8B3i5B0j3A0j2B0k3C0l3B0l4B0",
                "i8B0j1A0j2A0k1C0k4C0l7A0l8A0",
                "i2A0j6D0j7C0k6A0k6B0l1A0l6C0"

        };
        final String[] JUNIOR = {
                "i5A0j2B0j3C0k2C0k2D0l8C0l8D0",
                "j3B0j7D0k1C0k1D0l6B0l1A0",
                "i7B0j6C0l6A0l5B0",
                "i2D0l2C0"
        };
        final String[] EXPERT = {
                "j3B0j5C0",
                "i1B0j7A0j7B0k1A0k2B0l3B0l4C0",
                "k5D0l4D0l6D0",
                "j4C0l2D0l5B0"
        };
        final String[] MASTER = {
                "i7B0j3D0j7D0k3A0l6A0",
                "k1B0k6B0l5A0l3C0",
                "j8C0k6A0l2B0l6C0",
                "i3B0j3D0j8B0k4A0k2C0"
        };
        final String[] WIZARD = {
                "j4B0k8B0k5D0l3C0",
                "k3D0k5D0l6C0",
                "i6D0j2B0k3D0k4D0l4A0l6B0",
                "i3D0j5A0l7B0l5C0"
        };
        if(difficulty.getValue() == 1)
            return STARTER[index];
        else if(difficulty.getValue() == 2)
            return JUNIOR[index];
        else if(difficulty.getValue() == 3)
            return EXPERT[index];
        else if(difficulty.getValue() == 4)
            return MASTER[index];
        else
            return WIZARD[index];
    }

    // this class is to make different types of pieces
    // set event to pieces - by Yang Kong(u6275465)
    class Piece extends ImageView {

        TwistGame twistGame;
        String pieceType;
        double homeX, homeY;
        String row = " ", colonm = " ", rotateStatus = "0";
        boolean isFlipped = false;

        Piece(String pieceType){
            // set piece image. - by Yang Kong(u6275465)
            setImage(new Image(Viewer.class.getResource(URI_BASE + pieceType + ".png").toString()));

            // set piece type. eg:a, b,... - by Yang Kong(u6275465)
            this.pieceType = pieceType;

            // set home X and Y positions. - by Yang Kong(u6275465)
                switch (pieceType) {
                    case "a":
                        homeX = 0;
                        homeY = BACKGROUNDPIC_HEIGHT;
                        break;
                    case "b":
                        homeX = 3 * EACH_LOOP_SIDE_LENGTH;
                        homeY = BACKGROUNDPIC_HEIGHT;
                        break;
                    case "c":
                        homeX = 6 * EACH_LOOP_SIDE_LENGTH;
                        homeY = BACKGROUNDPIC_HEIGHT;
                        break;
                    case "d":
                        homeX = 10 * EACH_LOOP_SIDE_LENGTH;
                        homeY = BACKGROUNDPIC_HEIGHT;
                        break;
                    case "e":
                        homeX = 0;
                        homeY = BACKGROUNDPIC_HEIGHT + 2 * EACH_LOOP_SIDE_LENGTH;
                        break;
                    case "f":
                        homeX = 2 * EACH_LOOP_SIDE_LENGTH;
                        homeY = BACKGROUNDPIC_HEIGHT + 2 * EACH_LOOP_SIDE_LENGTH;
                        break;
                    case "g":
                        homeX = 5 * EACH_LOOP_SIDE_LENGTH;
                        homeY = BACKGROUNDPIC_HEIGHT + 2 * EACH_LOOP_SIDE_LENGTH;
                        break;
                    case "h":
                        homeX = 8 * EACH_LOOP_SIDE_LENGTH;
                        homeY = BACKGROUNDPIC_HEIGHT + 2 * EACH_LOOP_SIDE_LENGTH;
                        break;
                }

            // set Width and height based on piece type. - by Yang Kong(u6275465)
            switch (this.pieceType){
                case"a": case"b": case"d": case"f":setFitWidth(3 * EACH_LOOP_SIDE_LENGTH);setFitHeight(2 * EACH_LOOP_SIDE_LENGTH);break;
                case"c": setFitWidth(4 * EACH_LOOP_SIDE_LENGTH);setFitHeight(EACH_LOOP_SIDE_LENGTH);break;
                case"e": setFitHeight(2 * EACH_LOOP_SIDE_LENGTH);setFitWidth(2 * EACH_LOOP_SIDE_LENGTH);break;
                case"g": setFitWidth(3 * EACH_LOOP_SIDE_LENGTH);setFitHeight(3 * EACH_LOOP_SIDE_LENGTH);break;
                case"h": setFitWidth(3 * EACH_LOOP_SIDE_LENGTH);setFitHeight(EACH_LOOP_SIDE_LENGTH);break;
            }

            // scroll to rotate piece. - by Yang Kong(u6275465)
            this.setOnScroll(event -> {
                this.setRotate(this.getRotate() + 90);
                this.setRotateStatus();
            });

            // double-click to flip piece. - by Yang Kong(u6275465)
            this.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2){
                    if(!this.isFlipped) {
                        setImage(new Image(Viewer.class.getResource(URI_BASE + pieceType + "4.png").toString()));
                        this.isFlipped = true;
                        this.setRotateStatus();
                    }
                    else{
                        setImage(new Image(Viewer.class.getResource(URI_BASE + pieceType + ".png").toString()));
                        this.isFlipped = false;
                        this.setRotateStatus();
                    }
                }
            });

            // press mouse to get mouse position and current piece translate position.
            // this idea came from the last lab course
            this.setOnMousePressed(event -> {
                OX = event.getSceneX();
                OY = event.getSceneY();
                OTX = this.getTranslateX();
                OTY = this.getTranslateY();
            });

            // drag mouse to update piece translate position.
            // this idea came from the last lab course
            this.setOnMouseDragged(event -> {
                bg.removePieceFromEntirePlacement(PieceType.valueOf(pieceType + this.rotateStatus));
                this.setTranslateX(OTX + event.getSceneX() - OX);
                this.setTranslateY(OTY + event.getSceneY() - OY);
            });

            // release mouse to put piece and update entire placement.
            // entire placment can be shown real-time in the UI  - by Yang Kong(u6275465)
            this.setOnMouseReleased(event -> {
                this.snapToGrid();
                bg.addPieceToEntirePlacement(PieceType.valueOf(pieceType + this.rotateStatus), colonm + row);
                System.out.println(bg.getEntirePlacement());
                if(!this.isOnBoard() || !twistGame.isPlacementStringValid(bg.getEntirePlacement())){
                    this.backHome();
                    bg.removePieceFromEntirePlacement(PieceType.valueOf(pieceType + this.rotateStatus));
                    bg.removePieceFromBG(PieceType.valueOf(pieceType + this.rotateStatus), colonm + row);
                    content.setText(bg.getEntirePlacement());
                    goodJob.setOpacity(0);
                    System.out.println(bg.getEntirePlacement());
                }else {
                    this.snapToGrid();
                    bg.putPiece(PieceType.valueOf(pieceType + this.rotateStatus), colonm + row);
                    content.setText(bg.getEntirePlacement());
                    System.out.println(bg.getEntirePlacement());
                }
                if(bg.isCompleteGame()){
                    goodJob.setOpacity(100);
                }
            });

        }

        // get piece type. - by Yang Kong(u6275465)
        private String getPieceType(){
            return this.pieceType;
        }

        // set piece position to its home position. - by Yang Kong(u6275465)
        private void backHome(){
            this.setTranslateX(0);
            this.setTranslateY(0);
            this.setRotate(0);
            setImage(new Image(Viewer.class.getResource(URI_BASE + pieceType + ".png").toString()));
        }

        private String getPiecePlacement(){
            return pieceType + colonm + row + rotateStatus;
        }

        // set rotate status based on rotation and flipped or not - by Yang Kong(u6275465)
        private void setRotateStatus(){
            if(!this.isFlipped){
                if((this.getRotate() - 0) % 360 == 0){
                    this.rotateStatus = "0";
                }else if ((this.getRotate() - 90) % 360 == 0){
                    this.rotateStatus = "1";
                }else if ((this.getRotate() - 180) % 360 == 0){
                    this.rotateStatus = "2";
                }else if ((this.getRotate() - 270) % 360 == 0){
                    this.rotateStatus = "3";
                }
            }else if(this.isFlipped){
                if((this.getRotate() - 0) % 360 == 0){
                    this.rotateStatus = "4";
                }else if ((this.getRotate() - 90) % 360 == 0){
                    this.rotateStatus = "5";
                }else if ((this.getRotate() - 180) % 360 == 0){
                    this.rotateStatus = "6";
                }else if ((this.getRotate() - 270) % 360 == 0){
                    this.rotateStatus = "7";
                }
            }
        }

        // identify if this piece is on board or not - by Yang Kong(u6275465)
        private boolean isOnBoard(){
            if ((this.getRotate() - 0) % 360 == 0 || (this.getRotate() - 180) % 360 == 0) {
                if(this.getTranslateX() + homeX < -35 || this.getTranslateX() + homeX + this.getFitWidth() > EACH_LOOP_SIDE_LENGTH * 8 + 35
                        || this.getTranslateY() + homeY < -35 || this.getTranslateY() + homeY + this.getFitHeight() > EACH_LOOP_SIDE_LENGTH * 4 + 35){
                    return false;
                }
                else
                    return true;
            }else if((this.getRotate() - 90) % 360 == 0 || (this.getRotate() -270) % 360 == 0){
                switch (this.pieceType){
                    case "a": case"b": case"d": case"f":
                        if(this.getTranslateX() + homeX < -35 - 35.5|| this.getTranslateX() + homeX + this.getFitWidth() > EACH_LOOP_SIDE_LENGTH * 8 + 36 + 35.5
                                || this.getTranslateY() + homeY < -35 + 35.5 || this.getTranslateY() + homeY + this.getFitHeight() > EACH_LOOP_SIDE_LENGTH * 4 + 35 - 35.5){
                            return false;
                        }
                        else
                            return true;
                    case "e": case "g":
                        if(this.getTranslateX() + homeX < -35 || this.getTranslateX() + homeX + this.getFitWidth() > EACH_LOOP_SIDE_LENGTH * 8 + 35
                                || this.getTranslateY() + homeY < -35 || this.getTranslateY() + homeY + this.getFitHeight() > EACH_LOOP_SIDE_LENGTH * 4 + 35){
                            return false;
                        }
                        else
                            return true;
                    case"c":
                        if(this.getTranslateX() + homeX < -35 - 106.5 || this.getTranslateX() + homeX + this.getFitWidth() > EACH_LOOP_SIDE_LENGTH * 8 + 108 + 35
                                || this.getTranslateY() + homeY < -35 + 106.5|| this.getTranslateY() + homeY + this.getFitHeight() > EACH_LOOP_SIDE_LENGTH * 4 + 35 - 106.5){
                            return false;
                        }
                        else
                            return true;
                    case"h":
                        if(this.getTranslateX() + homeX < -35 - 71 || this.getTranslateX() + homeX + this.getFitWidth() > EACH_LOOP_SIDE_LENGTH * 8 + 71 + 35
                                || this.getTranslateY() + homeY < -35 + 71|| this.getTranslateY() + homeY + this.getFitHeight() > EACH_LOOP_SIDE_LENGTH * 4 + 35 -71){
                            return false;
                        }
                        else
                            return true;
                }
            }
            return false;
        }

        // snap piece to grid - by Yang Kong(u6275465)
        private void snapToGrid() {
            for (int i = 0; i < 8; i++) {
                if ((this.getRotate() - 0) % 360 == 0 || (this.getRotate() - 180) % 360 == 0) {
                    if (this.isOnBoard() && (this.getTranslateX() + homeX) >= -35 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateX() + homeX) <= 35 + i * EACH_LOOP_SIDE_LENGTH){
                        this.setTranslateX(i * EACH_LOOP_SIDE_LENGTH - homeX);
                        this.colonm = String.valueOf(i + 1);
                    }
                }else if ((this.getRotate() - 90) % 360 == 0 || (this.getRotate() - 270) % 360 == 0){
                    switch (this.pieceType){
                        case "a": case "b": case "d": case"f":
                            if(this.isOnBoard() && (this.getTranslateX() + homeX + 35.5) >= -36 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateX() + homeX + 35.5) <= 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateX(i * EACH_LOOP_SIDE_LENGTH - 35.5 - homeX);
                                this.colonm = String.valueOf(i + 1);
                            }break;
                        case "e": case "g":
                            if (this.isOnBoard() && (this.getTranslateX() + homeX) >= -35 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateX() + homeX) <= 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateX(i * EACH_LOOP_SIDE_LENGTH - homeX);
                                this.colonm = String.valueOf(i + 1);
                            }break;
                        case"c":
                            if(this.isOnBoard() && (this.getTranslateX() + homeX + 106.5) >= -36 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateX() + homeX + 106.5) <= 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateX(i * EACH_LOOP_SIDE_LENGTH - 106.5 - homeX);
                                this.colonm = String.valueOf(i + 1);
                            }break;
                        case"h":
                            if(this.isOnBoard() && (this.getTranslateX() + homeX + 71) >= -36 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateX() + homeX + 71) <= 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateX(i * EACH_LOOP_SIDE_LENGTH - 71 - homeX);
                                this.colonm = String.valueOf(i + 1);
                            }break;
                    }
                }
            }
            for(int i = 0; i < 4; i ++){
                if ((this.getRotate() - 0) % 360 == 0 || (this.getRotate() - 180) % 360 == 0) {
                    if (this.isOnBoard() && this.getTranslateY() + homeY >= -35 + i * EACH_LOOP_SIDE_LENGTH && this.getTranslateY() + homeY < 35 + i * EACH_LOOP_SIDE_LENGTH){
                        this.setTranslateY(i * EACH_LOOP_SIDE_LENGTH - homeY);
                        this.row = verPosition.get(i);
                    }
                }else if ((this.getRotate() - 90) % 360 == 0 || (this.getRotate() - 270) % 360 == 0){
                    switch (this.pieceType){
                        case "a": case "b": case "d": case"f":
                            if(this.isOnBoard() && (this.getTranslateY() + homeY - 35.5) >= -36 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateY() + homeY - 35.5) < 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateY(i * EACH_LOOP_SIDE_LENGTH  + 35.5 - homeY);
                                this.row = verPosition.get(i);
                            }break;
                        case "e": case "g":
                            if (this.isOnBoard() && (this.getTranslateY() + homeY >= -35 + i * EACH_LOOP_SIDE_LENGTH && this.getTranslateY() + homeY < 35 + i * EACH_LOOP_SIDE_LENGTH)){
                                this.setTranslateY(i * EACH_LOOP_SIDE_LENGTH - homeY);
                                this.row = verPosition.get(i);
                            }break;
                        case"c":
                            if(this.isOnBoard() && (this.getTranslateY() + homeY -106.5) >= -36 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateY() + homeY -106.5) < 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateY(i * EACH_LOOP_SIDE_LENGTH  + 106.5 - homeY);
                                this.row = verPosition.get(i);
                            }break;
                        case"h":
                            if(this.isOnBoard() && (this.getTranslateY() + homeY - 71) >= -36 + i * EACH_LOOP_SIDE_LENGTH && (this.getTranslateY() + homeY - 71) < 35 + i * EACH_LOOP_SIDE_LENGTH){
                                this.setTranslateY(i * EACH_LOOP_SIDE_LENGTH + 71 - homeY);
                                this.row = verPosition.get(i);
                            }break;
                    }
                }
            }
        }
    }

    private void setButtonEvent(){
        button1.setOnAction(event -> {
            addCandidatePieceToRoot();
        });
    }

    // set difficulty slider attributes - by Yang Kong(u6275465)
    private void setDifficulty(){
        difficulty.setMin(1);
        difficulty.setMax(5);
        difficulty.setValue(1);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(1);
        difficulty.setSnapToTicks(true);
    }

    // put pieces into board based on starting placement - by Yang Kong(u6275465)
    private void putStartingStatus(String startingPlacement){
        List<String> pieces = new ArrayList<>();
        for(int i = 0; i < startingPlacement.length(); i += 4){
            pieces.add(startingPlacement.substring(i, i + 4));
        }
        for(String piece : pieces){
            ImageView imageView = new ImageView();
            switch (piece.charAt(0)){
                case 'a':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'b':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'c':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-1.5 * EACH_LOOP_SIDE_LENGTH,1.5 * EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-1.5 * EACH_LOOP_SIDE_LENGTH,1.5 * EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-1.5 * EACH_LOOP_SIDE_LENGTH,1.5 * EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-1.5 * EACH_LOOP_SIDE_LENGTH,1.5 * EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'd':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'e':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e.png").toString()));
                            imageView.setRotate(90);//imageView.relocate(-43,43);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e.png").toString()));
                            imageView.setRotate(270);//imageView.relocate(-43,43);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e4.png").toString()));
                            imageView.setRotate(90);//imageView.relocate(-43,43);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "e4.png").toString()));
                            imageView.setRotate(270);//imageView.relocate(-43,43);
                            imageView.setFitWidth(2*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'f':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH/2,EACH_LOOP_SIDE_LENGTH/2);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'g':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g.png").toString()));
                            imageView.setRotate(90);//imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g.png").toString()));
                            imageView.setRotate(270);//imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g4.png").toString()));
                            imageView.setRotate(90);//imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "g4.png").toString()));
                            imageView.setRotate(270);//imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(3*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'h':
                    switch (piece.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH,EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH,EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-EACH_LOOP_SIDE_LENGTH,EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-EACH_LOOP_SIDE_LENGTH,EACH_LOOP_SIDE_LENGTH);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'i':
                    imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "i.png").toString()));
                    imageView.setFitWidth(1*EACH_LOOP_SIDE_LENGTH);
                    imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                    break;
                case 'j':
                    imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "j.png").toString()));
                    imageView.setFitWidth(1*EACH_LOOP_SIDE_LENGTH);
                    imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                    break;
                case 'k':
                    imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "k.png").toString()));
                    imageView.setFitWidth(1*EACH_LOOP_SIDE_LENGTH);
                    imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                    break;
                case 'l':
                    imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "l.png").toString()));
                    imageView.setFitWidth(1*EACH_LOOP_SIDE_LENGTH);
                    imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                    break;
            }
            switch (piece.charAt(1)){
                case '1':
                    imageView.setX(0*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '2':
                    imageView.setX(1*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '3':
                    imageView.setX(2*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '4':
                    imageView.setX(3*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '5':
                    imageView.setX(4*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '6':
                    imageView.setX(5*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '7':
                    imageView.setX(6*EACH_LOOP_SIDE_LENGTH);
                    break;
                case '8':
                    imageView.setX(7*EACH_LOOP_SIDE_LENGTH);
                    break;
            }
            switch (piece.charAt(2)){
                case 'A':imageView.setY(0*EACH_LOOP_SIDE_LENGTH);break;
                case 'B':imageView.setY(1*EACH_LOOP_SIDE_LENGTH);break;
                case 'C':imageView.setY(2*EACH_LOOP_SIDE_LENGTH);break;
                case 'D':imageView.setY(3*EACH_LOOP_SIDE_LENGTH);break;
            }
            root.getChildren().add(imageView);
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        setDifficulty();
        difficulty.setLayoutX(BACKGROUNDPIC_WIDTH + 30);
        difficulty.setLayoutY(100);
        placement.setLayoutX(BACKGROUNDPIC_WIDTH + 30);
        placement.setLayoutY(140);
        content.setLayoutX(BACKGROUNDPIC_WIDTH + 30);
        content.setLayoutY(165);
        button1.setLayoutX(BACKGROUNDPIC_WIDTH + 30);
        button1.setLayoutY(60);
        backgroundPic.setFitWidth(BACKGROUNDPIC_WIDTH);
        backgroundPic.setFitHeight(BACKGROUNDPIC_HEIGHT);
        goodJob.setLayoutX(BACKGROUNDPIC_WIDTH + 30);
        goodJob.setLayoutY(190);
        goodJob.setTextFill(Color.RED);
        Scene scene = new Scene(root,BOARD_WIDTH,BOARD_HEIGHT);

        addCandidatePieceToRoot();
        setButtonEvent();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
