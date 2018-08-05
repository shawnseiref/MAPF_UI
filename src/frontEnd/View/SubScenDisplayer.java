package frontEnd.View;

import backEnd.MapGenerators.Map;
import javafx.scene.canvas.Canvas;

public class SubScenDisplayer extends Canvas {


    private Map map;


    public void redraw(){

    }

    public void setMaze(char[][] map) {
        this.map.setGrid(map);
    }
}
