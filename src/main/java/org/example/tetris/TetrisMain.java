package org.example.tetris;

import java.util.*;

public class TetrisMain {
    private static final int WIDTH = 10;

    // the shapes and their relative positions
    private static final Map<Character, int[][]> SHAPES = Map.of(
            'Q', new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            'Z', new int[][]{{0, 1}, {0, 2}, {1, 0}, {1, 1}},
            'S', new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            'T', new int[][]{{0, 1}, {1, 0}, {1, 1}, {1, 2}},
            'I', new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            'L', new int[][]{{0, 0}, {1, 0}, {2, 0}, {0, 1}},
            'J', new int[][]{{0, 0}, {0, 1}, {1, 1}, {2, 1}}
    );

    // the shapes and their heights
    private static final Map<Character, Integer> HEIGHTS = Map.of(
            'Q', 2,
            'Z', 2,
            'S', 2,
            'T', 2,
            'I', 1,
            'L', 3,
            'J', 3
    );


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.split(",");
            System.out.println(simulateTetris(pieces));
        }
        scanner.close();
    }

    public static int simulateTetris(String[] commands) {
        List<boolean[]> grid = new ArrayList<>();
        int[] heights = new int[WIDTH];

        for (String command : commands) {
            char pieceType = command.charAt(0);
            int[][] shape = SHAPES.get(pieceType);
            int shapeHeight = HEIGHTS.get(pieceType);
            int startCol = Character.getNumericValue(command.charAt(1));

            // Determine the starting row for this piece
            int startRow = 0;
            for (int[] coord : shape) {
                int row = coord[0];
                int col = coord[1] + startCol;
                startRow = Math.max(heights[col] - row, startRow); // "drop" adjustment
            }

            // Ensure the grid has enough rows to place this piece
            while (grid.size() <= startRow + shapeHeight) {
                grid.add(new boolean[WIDTH]);
            }

            // Place the piece in the grid
            for (int[] coord : shape) {
                int row = coord[0] + startRow;
                int col = coord[1] + startCol;

                grid.get(row)[col] = true;
                heights[col] = Math.max(heights[col], row + 1);
            }

            // Check for completed rows
            List<Integer> completedRows = new ArrayList<>();
            for (int row = startRow; row <= startRow + shapeHeight; row++) {
                if (isRowComplete(grid.get(row))) {
                    completedRows.add(row);
                }
            }

            // Remove completed rows and reduce heights for each row
            for (int idx : completedRows) {
                grid.remove(idx);
                for (int i = 0; i < WIDTH; i++) {
                    if (heights[i] > 0)
                        heights[i] -= 1;
                }
            }
        }

        // Get max height
        return Arrays.stream(heights).max().orElse(0);
    }

    public static boolean isRowComplete(boolean[] row) {
        for (boolean cell : row) {
            if (!cell) return false;
        }
        return true;
    }
}
