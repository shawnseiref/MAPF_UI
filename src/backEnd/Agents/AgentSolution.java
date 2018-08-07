package backEnd.Agents;

import backEnd.MapGenerators.Position;

import java.util.ArrayList;

public class AgentSolution {
    private Agent agent;
    private ArrayList path;

    public AgentSolution() {
        path=new ArrayList();
    }

    public AgentSolution(ArrayList path) {
        this.path = path;
    }

    public void addPosition(Position pos){
        path.add(pos);
    }

    public void removePosition(Position pos){
        path.remove(pos);
    }
}
