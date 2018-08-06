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
            if (o == model) {
                if (arg.equals("mapGenerator") || arg.equals("movement")) {
                    setChanged();
                    notifyObservers("mapGenerator");
                }
                if (arg.equals("movement and endGame")) {
                    setChanged();
                    notifyObservers("movement and endGame");
                }
                if (arg.equals("solution")) {
                    setChanged();
                    notifyObservers("solution");
                }
            }
        }
    }

    public Map getMap() {
        return model.getMap();
    }

    public void addAgent(Position start, Position goal) {
        model.addAgent(start,goal);
    }

    public void loadMap(File file) {
    }
}
