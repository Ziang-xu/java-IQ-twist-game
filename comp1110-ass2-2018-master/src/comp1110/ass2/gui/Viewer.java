package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * A very simple viewer for piece placements in the twist game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;
    private static final String URI_BASE = "assets/";
    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    // the idea of this part of code is discussed with Baihe Yang(u6304606)
    // I taught her how to set images to imageViewer - by Yang Kong(u6275465)
    void makePlacement(String placement) {
        final Group showImage = new Group();
        showImage.getChildren().clear();
        final int BACKGROUND_WIDTH = 730;
        final int BACKGROUND_HEIGHT = 400;
        final int EACH_LOOP_SIDE_LENGTH = 86;
        ImageView background = new ImageView(new Image(Viewer.class.getResource(URI_BASE + "background.png").toString()));
        background.setFitHeight(BACKGROUND_HEIGHT);
        background.setFitWidth(BACKGROUND_WIDTH);
        showImage.getChildren().add(background);
        int len = placement.length();
        String[] eachPlacement = new String[len/4];
        for(int i = 0,j = 0; i < len/4; i ++,j +=4){
            eachPlacement[i] = placement.substring(j,j+4);
        }
        for(String s : eachPlacement){
            ImageView imageView = new ImageView();
            switch (s.charAt(0)){
                case 'a':
                    switch (s.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "a4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'b':
                    switch (s.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "b4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'c':
                    switch (s.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-129,129);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-129,129);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-129,129);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "c4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-129,129);
                            imageView.setFitWidth(4*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'd':
                    switch (s.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "d4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'e':
                    switch (s.charAt(3)){
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
                    switch (s.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "f4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-43,43);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(2*EACH_LOOP_SIDE_LENGTH);
                            break;

                    }break;
                case 'g':
                    switch (s.charAt(3)){
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
                    switch (s.charAt(3)){
                        case '0':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '1':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-86,86);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '2':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '3':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-86,86);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '4':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(0);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '5':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(90);imageView.relocate(-86,86);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '6':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(180);
                            imageView.setFitWidth(3*EACH_LOOP_SIDE_LENGTH);imageView.setFitHeight(1*EACH_LOOP_SIDE_LENGTH);
                            break;
                        case '7':imageView.setImage(new Image(Viewer.class.getResource(URI_BASE + "h4.png").toString()));
                            imageView.setRotate(270);imageView.relocate(-86,86);
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
            switch (s.charAt(1)){
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
            switch (s.charAt(2)){
                case 'A':imageView.setY(0*EACH_LOOP_SIDE_LENGTH);break;
                case 'B':imageView.setY(1*EACH_LOOP_SIDE_LENGTH);break;
                case 'C':imageView.setY(2*EACH_LOOP_SIDE_LENGTH);break;
                case 'D':imageView.setY(3*EACH_LOOP_SIDE_LENGTH);break;
            }
            showImage.getChildren().add(imageView);
        }
        root.getChildren().add(showImage);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

