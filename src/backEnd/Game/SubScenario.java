package backEnd.Game;

import backEnd.Agents.Agent;
import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import backEnd.Solvers.Solution;

import java.util.HashMap;

public class SubScenario {
    private Map map;
    private HashMap<Position,Agent> agents;
    private int nextAgentID;
    private Solution sol;

    public SubScenario(Map map) {
        this.map = map;
        agents=new HashMap<>();
        nextAgentID=0;
    }

    public SubScenario(Map map, HashMap<Position, Agent> agents, Solution sol) {
        this.map = map;
        this.agents = agents;
        this.sol = sol;
    }

    public SubScenario(Map map, HashMap<Position, Agent> agents) {
        this.map = map;
        this.agents = agents;
        nextAgentID=agents.size();
    }

    public int getNextAgentID() {
        return nextAgentID;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public HashMap<Position, Agent> getAgents() {
        return agents;
    }

    public void setAgents(HashMap<Position, Agent> agents) {
        this.agents = agents;
    }

    public void addAgent(Agent agent){

        agents.put(agent.getLocation(),agent);
        nextAgentID++;
    }

    public void removeAgent(Agent agent){
        agents.remove(agent.getLocation());
    }

    public void moveAgent(Position current, Position target){
        agents.put(target,agents.get(current));
        agents.remove(current);
    }
}
