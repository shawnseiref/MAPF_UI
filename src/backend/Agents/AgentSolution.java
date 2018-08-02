package backend.Agents;

import backend.MapGenerators.Position;
import sun.misc.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class AgentSolution {
    private ArrayList path;

    public void add(Position pos){
        path.add(pos);
    }
}
