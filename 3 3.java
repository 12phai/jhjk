// Rover class
public class Rover {
    private static final String[] DIRECTIONS = {"N", "E", "S", "W"};
    
    private int x;
    private int y;
    private String direction;
    private Grid grid;

    public Rover(int x, int y, String direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void moveForward() {
        int[] newPosition = getNewPosition();
        int newX = newPosition[0];
        int newY = newPosition[1];
        
        if (!grid.isObstacle(newX, newY)) {
            this.x = newX;
            this.y = newY;
            System.out.println("Moved to (" + this.x + ", " + this.y + ")");
        } else {
            System.out.println("Obstacle detected. Cannot move forward.");
        }
    }

    public void turnLeft() {
        int index = (findDirectionIndex(direction) - 1 + DIRECTIONS.length) % DIRECTIONS.length;
        direction = DIRECTIONS[index];
        System.out.println("Turned left. Now facing " + direction);
    }

    public void turnRight() {
        int index = (findDirectionIndex(direction) + 1) % DIRECTIONS.length;
        direction = DIRECTIONS[index];
        System.out.println("Turned right. Now facing " + direction);
    }

    private int[] getNewPosition() {
        int newX = x;
        int newY = y;
        switch (direction) {
            case "N":
                newY += 1;
                break;
            case "E":
                newX += 1;
                break;
            case "S":
                newY -= 1;
                break;
            case "W":
                newX -= 1;
                break;
        }
        return new int[]{newX, newY};
    }

    public void statusReport() {
        System.out.println("Rover is at (" + x + ", " + y + ") facing " + direction);
    }

    private int findDirectionIndex(String direction) {
        for (int i = 0; i < DIRECTIONS.length; i++) {
            if (DIRECTIONS[i].equals(direction)) {
                return i;
            }
        }
        return -1; // Should never happen if direction is valid
    }
}
