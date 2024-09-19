// Cell class
public class Cell {
    private int x;
    private int y;
    private boolean obstacle;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.obstacle = false;
    }

    public void setObstacle() {
        this.obstacle = true;
    }

    public boolean isObstacle() {
        return this.obstacle;
    }
}

// Grid class
public class Grid {
    private int width;
    private int height;
    private Cell[][] grid;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y] = new Cell(x, y);
            }
        }
    }

    public void addObstacle(int x, int y) {
        if (isValidPosition(x, y)) {
            this.grid[x][y].setObstacle();
        }
    }

    public boolean isObstacle(int x, int y) {
        if (isValidPosition(x, y)) {
            return this.grid[x][y].isObstacle();
        }
        return true; // Consider out of bounds as an obstacle
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
