package frontEnd.Model;

import backEnd.Game.SubScenario;
import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;

import java.io.File;

public interface IModel {

    SubScenario getGame();
    Map getMap();
    void generateMaze(int width, int height,double percentage);
    void generateMaze(File str);
    void generateMaze(String str);
    void moveCharacter(Position current,Position target);
    void addAgent(Position start, Position goal);
}
