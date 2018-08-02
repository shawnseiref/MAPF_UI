package backend.MapGenerators;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StrutMapGenerator {

    private static String printmap() throws IOException {
        Scanner in = new Scanner(new FileReader("src/backend/Agents/brc000d.map"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }
}
