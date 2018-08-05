package frontEnd.View;

import frontEnd.ViewModel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.util.Observable;
import java.util.Observer;

public class ViewController implements Observer,IView {

    @FXML
    public BorderPane root;
    @FXML
    private ViewModel viewModel;
    @FXML
    public SubScenDisplayer subScenDisplayer;

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayGame(viewModel.getMap());
//            btn_generateMaze.setDisable(false);
        }
    }

//    @Override
    public void displayGame(char[][] map) {
        subScenDisplayer.setMaze(map);
//        int characterPositionRow = viewModel.getCharacterPositionRow();
//        int characterPositionColumn = viewModel.getCharacterPositionColumn();
//        subScenDisplayer.setCharacterPosition(characterPositionRow, characterPositionColumn);
//        this.characterPositionRow.set(characterPositionRow + "");
//        this.characterPositionColumn.set(characterPositionColumn + "");
    }


    public ViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
