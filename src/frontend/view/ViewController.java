package frontend.view;

import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import frontend.viewmodel.ViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;

import javax.swing.*;
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
    public SubScenDisplayer subSceneDisplayer;
    public javafx.scene.control.ScrollPane scroll;
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
        File file=loadFile("Created Files");
        viewModel.loadSol(file);
        event.consume();
    }

    public void loadMap(ActionEvent event){
        File file=loadFile("Created Files");
        if (file != null) {
            viewModel.loadMap(file);
        }
        event.consume();
    }
    public void loadNew(ActionEvent event){
        String userDir = System.getProperty("user.home");
        File file=loadFile(userDir+"/Desktop");
        if (file != null) {
            viewModel.loadMap(file);
        }
        event.consume();
    }

    public void loadExist(ActionEvent event){
        File file=loadFile("SavedMaps");
        if (file != null) {
            viewModel.loadMap(file);
        }
        event.consume();
    }

    private File loadFile(String location) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load Map");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("map files", "*.map"));
        fc.setInitialDirectory(new File(location));
        //showing the file chooser
        return fc.showOpenDialog(null);

//        JFileChooser fileChooser=null;
//        if(location.equals("Desktop")){
//            String userDir = System.getProperty("user.home");
//            fileChooser = new JFileChooser(userDir + '/' + location);
//        }
//        else if (location.equals("SavedMaps"))
//            fileChooser = new JFileChooser("SavedMaps");
//        else if (location.equals("Created Files"))
//            fileChooser = new JFileChooser("Created Files");
//        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
//            return fileChooser.getSelectedFile();
//        return null;
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
                if(newSceneWidth.doubleValue()>tab.widthProperty().doubleValue())
                    scroll.setPrefWidth(newSceneWidth.doubleValue()-tab.widthProperty().doubleValue());
                double old=oldSceneWidth.doubleValue()-VB.getWidth();
                double neww=newSceneWidth.doubleValue()-VB.getWidth();
                if(subSceneDisplayer!=null){
                    newScale.setPivotX(subSceneDisplayer.getLayoutX() *(neww)/(old));
                    newScale.setX( subSceneDisplayer.getScaleX() * (neww)/(old) );
                    subSceneDisplayer.getTransforms().add(newScale);
                }
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                if(newSceneHeight.doubleValue()>tab.heightProperty().doubleValue())
                    scroll.setPrefHeight(newSceneHeight.doubleValue()-tab.heightProperty().doubleValue());
                Scale newScale = new Scale();
                double old=oldSceneHeight.doubleValue()-menu.getHeight();
                double neww=newSceneHeight.doubleValue()-menu.getHeight();
                if(subSceneDisplayer!=null){
                    newScale.setPivotY(subSceneDisplayer.getLayoutY() *(neww)/(old));
                    newScale.setY( subSceneDisplayer.getScaleY() * (neww)/(old) );
                    subSceneDisplayer.getTransforms().add(newScale);
                }
            }
        });
    }

    public void KeyPressed(KeyEvent keyEvent) {
        try {
            viewModel.getMap();
//            if (!mazeDisplayer.getFinished())
//                viewModel.moveCharacter(keyEvent.getCode());
            keyEvent.consume();
//            btn_solveMaze.setDisable(false);
        } catch (NullPointerException e) {
            keyEvent.consume();
        }
    }

    public void zoomInOut(ScrollEvent scrollEvent) {
        try {
//            btn_zoomBack.setDisable(false);
//            btn_zoomBack.setVisible(true);
//            btn_zoomBack.setEffect(new Glow(1));
            viewModel.getMap();
            AnimatedZoomOperation zoomOperator = new AnimatedZoomOperation();
            double zoomFactor;
            if (scrollEvent.isControlDown()) {
                zoomFactor = 1.5;
                double deltaY = scrollEvent.getDeltaY();
                if (deltaY < 0) {
                    zoomFactor = 1 / zoomFactor;
                }
                zoomOperator.zoom(scroll, zoomFactor, scrollEvent.getSceneX(), scrollEvent.getSceneY());
                scrollEvent.consume();
            }
        } catch (NullPointerException e) {
            scrollEvent.consume();
        }
    }


//    public void zoomBack(ActionEvent actionEvent) {
//        btn_zoomBack.setDisable(true);
//        Timeline timeline = new Timeline(60);
//        timeline.getKeyFrames().clear();
//        timeline.getKeyFrames().addAll(
//                new KeyFrame(Duration.millis(100), new KeyValue(scrollPane.translateXProperty(), 0)),
//                new KeyFrame(Duration.millis(100), new KeyValue(scrollPane.translateYProperty(), 0)),
//                new KeyFrame(Duration.millis(100), new KeyValue(scrollPane.scaleXProperty(), 1)),
//                new KeyFrame(Duration.millis(100), new KeyValue(scrollPane.scaleYProperty(), 1)));
//        timeline.play();
//        btn_zoomBack.setVisible(false);
//    }
}
