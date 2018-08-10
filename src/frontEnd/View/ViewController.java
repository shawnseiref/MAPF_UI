package frontEnd.View;

import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import frontEnd.ViewModel.ViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
    public SubScenDisplayer createDisplayer;
    public SubScenDisplayer simulateDisplayer;
    public javafx.scene.control.MenuBar menu;
    public javafx.scene.layout.VBox VB;
    public javafx.scene.control.TabPane tab;
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

    public void loadSol(ActionEvent event){
        File file=loadFile("\"Created Files\"");
        String path = file.getAbsolutePath();
        viewModel.loadMapSimulate(new File(path));
        event.consume();
    }

    public void loadMap(ActionEvent event){
        File file=loadFile("\"Created Files\"");
        String path = file.getAbsolutePath();
        viewModel.loadMapSimulate(new File(path));
        event.consume();
    }
    public void loadNew(ActionEvent event){
        File file=loadFile("Desktop");
        String path = file.getAbsolutePath();
        viewModel.loadMap(new File(path));
        event.consume();
    }

    public void loadExist(ActionEvent event){
        File file=loadFile("SavedMaps");
        String path = file.getAbsolutePath();
        viewModel.loadMap(new File(path));
        event.consume();
    }

    public void load(File file){
        String path = file.getAbsolutePath();
        viewModel.loadMap(new File(path));
    }

    private File loadFile(String location) {
        JFileChooser fileChooser=null;
        if(location.equals("Desktop")){
            String userDir = System.getProperty("user.home");
            fileChooser = new JFileChooser(userDir + '/' + location);
        }
        else if (location.equals("SavedMaps"))
            fileChooser = new JFileChooser("SavedMaps");
        else if (location.equals("Created Files"))
            fileChooser = new JFileChooser("Created Files");
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile();
        return null;
    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backward.setImage(new Image(this.getClass().getResourceAsStream("/Images/backwards.png")));
        pause.setImage(new Image(this.getClass().getResourceAsStream("/Images/pause.png")));
        play.setImage(new Image(this.getClass().getResourceAsStream("/Images/play.png")));
        forward.setImage(new Image(this.getClass().getResourceAsStream("/Images/forwards.png")));
    }

    public void setResizeEvent(Scene scene) {
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Scale newScale = new Scale();
                tab.setPrefWidth(newSceneWidth.doubleValue());
                double old=oldSceneWidth.doubleValue()-VB.getWidth();
                double neww=newSceneWidth.doubleValue()-VB.getWidth();
                if(createDisplayer!=null){
                    newScale.setPivotX(createDisplayer.getLayoutX() *(neww)/(old));
                    newScale.setX( createDisplayer.getScaleX() * (neww)/(old) );
                    createDisplayer.getTransforms().add(newScale);
                }
                if(simulateDisplayer!=null){
                    newScale.setX( simulateDisplayer.getScaleX() * (neww)/(old) );
                    newScale.setPivotX(simulateDisplayer.getLayoutX() *(neww)/(old));
                    simulateDisplayer.getTransforms().add(newScale);
                }
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                tab.setPrefHeight(newSceneHeight.doubleValue());
                Scale newScale = new Scale();
                double old=oldSceneHeight.doubleValue()-menu.getHeight();
                double neww=newSceneHeight.doubleValue()-menu.getHeight();
                if(createDisplayer!=null){
                    newScale.setPivotY(createDisplayer.getLayoutY() *(neww)/(old));
                    newScale.setY( createDisplayer.getScaleY() * (neww)/(old) );
                    createDisplayer.getTransforms().add(newScale);
                }
                if(simulateDisplayer!=null){
                    newScale.setPivotY(simulateDisplayer.getLayoutY() *(neww)/(old));
                    newScale.setY( simulateDisplayer.getScaleY() * (neww)/(old) );
                    simulateDisplayer.getTransforms().add(newScale);
                }
            }
        });
    }
}
