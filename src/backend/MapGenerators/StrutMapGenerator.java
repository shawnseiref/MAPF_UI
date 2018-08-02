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
        int rowsNum=0;
        int colNum=0;
        String mapStr="";
        String tmp="";
        try {
            mapStr = this.getMapAsString();
        } catch (Exception e){
        }
        int counter=0;
        counter=skipToNum(mapStr,counter);
        while(Character.isDigit(mapStr.charAt(counter))){
            rowsNum = rowsNum * 10 + mapStr.charAt(counter);
            counter++;
        }
        counter=skipToNum(mapStr,counter);
        while(Character.isDigit(mapStr.charAt(counter))){
            colNum=colNum*10+mapStr.charAt(counter);
            counter++;
        }
        counter=counter+3;
        char[][] map=new char[rowsNum][colNum];
        for(int rows=0;rows<rowsNum;rows++) {
            for (int cols = 0; cols < colNum; cols++) {
                map[rows][cols]=mapStr.charAt(counter);
                counter++;
            }
        }
        return new Map(map);
    }

    private int skipToNum(String mapStr, int counter) {
        while(!Character.isDigit(mapStr.charAt(counter))){
            counter++;
        }
        return counter;
    }
}
