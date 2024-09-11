import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GameOfLife {

    // Function to print the grid
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "■ " : "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to calculate the next generation
    public static int[][] nextGeneration(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] newGrid = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = countLiveNeighbors(grid, r, c);

                // Apply the rules
                if (grid[r][c] == 1) {
                    // Rule 1 and 3
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        newGrid[r][c] = 0;
                    } else {
                        newGrid[r][c] = 1; // Rule 2
                    }
                } else {
                    if (liveNeighbors == 3) { // Rule 4
                        newGrid[r][c] = 1;
                    }
                }
            }
        }
        return newGrid;
    }
// Helper function to count live neighbors
    private static int countLiveNeighbors(int[][] grid, int row, int col) {
        int liveNeighbors = 0;
        int[] directions = {-1, 0, 1};

        for (int dr : directions) {
            for (int dc : directions) {
                if (!(dr == 0 && dc == 0)) { // Skip the cell itself
                    int newRow = row + dr;
                    int newCol = col + dc;

                    // Check bounds
                    if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {
                        liveNeighbors += grid[newRow][newCol];
                    }
                }
            }
        }
        return liveNeighbors;
    }

    public static void main(String[] args) throws InterruptedException {
        // Define three starting configurations
        // Glider configuration
        int[][] glider = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };

        // Blinker configuration
        int[][] blinker = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        // Toad configuration
        int[][] toad = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
// Choose the configuration to run
        int[][] currentGrid = glider;  // Change to blinker or toad as needed

        // Run the animation
        for (int i = 0; i < 10; i++) {  // Run for 10 generations
            printGrid(currentGrid);
            currentGrid = nextGeneration(currentGrid);
            TimeUnit.SECONDS.sleep(1);  // Pause for a second
            System.out.print("\033[H\033[2J");
            System.out.flush();         // Clear the console
        }
    }
}