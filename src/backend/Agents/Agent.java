package backend.Agents;

import backend.MapGenerators.Position;

public class Agent {
    private int id;
    private Position location;
    private Position goalLocation;
    private AgentSolution path;

    public Agent(int id, Position location, Position goalLocation) {
        this.id = id;
        this.location = location;
        this.goalLocation = goalLocation;
        path=new AgentSolution();
        path.add(location);
    }
}
