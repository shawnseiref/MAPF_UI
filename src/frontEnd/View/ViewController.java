package frontEnd.View;

import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import frontEnd.ViewModel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ViewController implements Observer,IView, Initializable {

    @FXML
    public BorderPane root;
    @FXML
    private ViewModel viewModel;
    @FXML
    public javafx.scene.canvas.Canvas subSceneDisplayer;
    public javafx.scene.control.TextField startRow;
    public javafx.scene.control.TextField startCol;
    public javafx.scene.control.TextField goalRow;
    public javafx.scene.control.TextField goalCol;
    public javafx.scene.control.Button createAgent;
    public javafx.scene.image.ImageView backward;
    public javafx.scene.image.ImageView pause;
    public javafx.scene.image.ImageView play;
    public javafx.scene.image.ImageView forward;


    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayGame(viewModel.getMap().getGrid());
//            btn_generateMaze.setDisable(false);
        }
    }

//    @Override
    public void displayGame(char[][] map) {
        //subScenDisplayer.setMaze(map);
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

    public void addAgent(ActionEvent actionEvent){
        try{
            Position start=new Position(Integer.parseInt(startRow.getText()),Integer.parseInt(startCol.getText()));
            Position goal=new Position(Integer.parseInt(goalRow.getText()),Integer.parseInt(goalCol.getText()));
            Map map=viewModel.getMap();
            if(!map.posExists(start) || !map.posExists(goal) || !map.posReachable(start) || !map.posReachable(goal))
                throw new Exception();
            viewModel.addAgent(start,goal);
        }
        catch (Exception e){
            showAlert("Agent's position must be a reachable location in the map");
        }
    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backward.setImage(new Image(this.getClass().getResourceAsStream("/Images/dd.png")));
        pause.setImage(new Image(this.getClass().getResourceAsStream("/Images/pause.png")));
        play.setImage(new Image(this.getClass().getResourceAsStream("/Images/play.png")));
        forward.setImage(new Image(this.getClass().getResourceAsStream("/Images/forward.png")));

    }
}
