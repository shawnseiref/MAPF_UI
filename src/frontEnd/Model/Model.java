package frontEnd.Model;

import backEnd.Agents.Agent;
import backEnd.Game.SubScenario;
import backEnd.MapGenerators.FileMapGenerator;
import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import backEnd.MapGenerators.StringMapGenerator;

import java.io.File;
import java.util.Observable;

public class Model extends Observable implements IModel  {

    private SubScenario game;
    private int currentSolState;

    @Override
    public SubScenario getGame(){
        return game;
    }

    @Override
    public Map getMap() {
        return game.getMap();
    }

    @Override
    public void generateMaze(int width, int height, double percentage) {
        //stuff
        currentSolState=0;
    }

    @Override
    public void generateMaze(File str) {
        FileMapGenerator gen=new FileMapGenerator();
        game=new SubScenario(gen.generate(str));
        currentSolState=0;
}

    @Override
    public void generateMaze(String str) {
        StringMapGenerator gen=new StringMapGenerator();
        game=new SubScenario(gen.generate(str));
        currentSolState=0;
    }

    @Override
    public void moveCharacter(Position current, Position target) {
        game.moveAgent(current,target);
    }

    @Override
    public void addAgent(Position start, Position goal) {
        game.addAgent(new Agent(game.getNextAgentID(),start,goal));
    }
}
