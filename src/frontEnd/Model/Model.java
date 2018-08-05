package frontEnd.Model;

import backEnd.MapGenerators.Map;

import java.util.Observable;

public class Model extends Observable implements IModel  {

    private Map map;

    @Override
    public char [][] getMap(){
        return map.getGrid();
    }
}
