package frontEnd.ViewModel;

import frontEnd.Model.*;
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

    public char[][] getMap() {
        return model.getMap();
    }
}
