package backEnd.Game;

import backEnd.Agents.Agent;
import backEnd.Agents.AgentSolution;
import backEnd.MapGenerators.Map;
import backEnd.MapGenerators.Position;
import backEnd.MapGenerators.StringMapGenerator;
import backEnd.Solvers.Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SubScenario {
    private Map map;
    private ArrayList<Agent> agentsList;
    private HashMap<Position,Agent> agents;
    private int nextAgentID;
    private Solution sol;

    public SubScenario(Map map) {
        this.map = map;
        this.agents = new HashMap();
        this.nextAgentID = 0;
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

    public SubScenario(Map map, File file) {
        Scanner in = null;

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        while(in.hasNext()) {
            sb.append(in.next());
            sb.append('\n');
        }

        in.close();
        String game = sb.toString();
        int agentCounter = -1;

        for(int i = 0; i < game.length(); ++i) {
            if (game.charAt(i) == '\n' || i == 0) {
                ++agentCounter;
                new Agent(agentCounter);

                while(game.charAt(i) != '(') {
                    ++i;
                }
            }
        }
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

    private String fileToStr(File file){
        Scanner in=null;
        try {
            in = new Scanner((file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
            sb.append('\n');
        }
        in.close();
        return sb.toString();
    }
}
