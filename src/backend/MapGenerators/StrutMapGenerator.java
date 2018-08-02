package backend.MapGenerators;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StrutMapGenerator extends AMapGenerator{

    private String getMapAsString() throws IOException {
        Scanner in = new Scanner(new FileReader("src/backend/Agents/brc000d.map"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }


    @Override
    public Map generate(Object o) {
        try {
            String mapStr = this.getMapAsString();
        } catch (Exception e){

        }
        return null;
    }
}
