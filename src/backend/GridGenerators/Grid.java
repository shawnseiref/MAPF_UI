package backend.GridGenerators;

public class Grid {
    private char [][] grid;

    public Grid(char[][] grid) {
        this.grid = grid;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public boolean posExists(Position position){
        return posExists(position.getX(), position.getY());
    }

    public boolean posExists(int x, int y){
        return grid!=null && grid[0]!=null && x>=0 && x<grid.length && y>=0 && y<grid[0].length;
    }
}
