public class Main {
    public static void main(String[] args) {
        // Create a Grid instance with width 10 and height 10
        Grid grid = new Grid(10, 10);
        
        // Add obstacles at (2, 2) and (3, 5)
        grid.addObstacle(2, 2);
        grid.addObstacle(3, 5);

        // You can optionally print out the status of obstacles to verify
        System.out.println("Obstacle at (2, 2): " + grid.isObstacle(2, 2));
        System.out.println("Obstacle at (3, 5): " + grid.isObstacle(3, 5));
    }
}
