package backend.MapGenerators;

public class Map {
    private char [][] map;
    int height;
    int width;

    public Map(char[][] map) {
        this.map = map;
    }

    public char[][] getGrid() {
        return map;
    }

    public void setGrid(char[][] map) {
        this.map = map;
    }

    public boolean posExists(Position position){
        return posExists(position.getX(), position.getY());
    }

    public boolean posExists(int x, int y){
        return map!=null && map[0]!=null && x>=0 && x<map.length && y>=0 && y<map[0].length;
    }
}
