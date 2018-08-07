package frontEnd.View;

import backEnd.Game.SubScenario;
import backEnd.MapGenerators.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SubScenDisplayer extends Canvas {

    private SubScenario game;
    private double xLO;
    private double yLO;
    private double wLO;
    private double hLO;
    private double sxLO;
    private double syLO;
    private boolean start=false;

    public void setGame(SubScenario ss) {
        game=ss;
        if(!start){
            xLO=getLayoutX();
            yLO=getLayoutY();
            wLO=getWidth();
            hLO=getHeight();
            sxLO=getScaleX();
            syLO=getScaleY();
        }
        start=true;
        redraw();
    }

    public void redraw() {
        if (game!= null && game.getMap()!= null && game.getMap().getGrid()!=null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / game.getMap().getGrid().length;
            double cellWidth = canvasWidth / game.getMap().getGrid().length;
            try {
                Image treeImage=null;
                Image characterImage = null;
                Image outOfBounds=null;
                Image goal=null;
                Image visitedoutOfBounds=null;
                treeImage = new Image(this.getClass().getResourceAsStream("/Images/tree.png"));
                outOfBounds = new Image(this.getClass().getResourceAsStream("/Images/void.jpg"));
                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight());

                //Draw game.getMap().getGrid()
                for (int i = 0; i < game.getMap().getGrid().length; i++) {
                    for (int j = 0; j < game.getMap().getGrid()[i].length; j++) {
                        if (game.getMap().getGrid()[i][j] == 'T') {
                            gc.drawImage(treeImage, j * cellWidth,i * cellHeight , cellWidth, cellHeight);
                        }
                        else if(game.getMap().getGrid()[i][j]== '@'){
                            gc.drawImage(outOfBounds, j * cellWidth,i * cellHeight , cellWidth, cellHeight);
                        }
                    }
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
