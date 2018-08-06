package frontEnd.ViewModel;

import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import frontEnd.Model.*;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class ViewModel  extends Observable implements Observer {


    private IModel model;


    public ViewModel(IModel model) {
        this.model = model;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            setChanged();
            notifyObservers();
        }
    }

    public Map getMap() {
        return model.getMap();
    }

    public void addAgent(Position start, Position goal) {
        model.addAgent(start,goal);
    }

    public void loadMap(File file) {
        model.generateMaze(file);
    }
}
