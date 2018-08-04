package backEnd.Solvers;

import backEnd.Agents.AgentSolution;

import java.util.ArrayList;

public class Solution {
    private ArrayList agentsSolutions;

    public Solution() {
        agentsSolutions=new ArrayList();
    }

    public Solution(ArrayList agentsSolutions) {
        this.agentsSolutions = agentsSolutions;
    }

    public void addSolution(AgentSolution sol){
        agentsSolutions.add(sol);
    }

    public void removeSolution(AgentSolution sol){
        agentsSolutions.remove(sol);
    }
}
