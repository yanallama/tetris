package org.example.tetris;

import java.util.*;

public class TetrisMain {
    private static final int WIDTH = 10;

    // the shapes and their relative positions
    private static final Map<Character, int[][]> SHAPES = Map.of(
            'Q', new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            'Z', new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            'S', new int[][]{{0, 1}, {0, 2}, {1, 0}, {1, 1}},
            'T', new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            'I', new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            'L', new int[][]{{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            'J', new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 0}}
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

    public static boolean isRowComplete(boolean[] row) {
        for (boolean cell : row) {
            if (!cell) return false;
        }
        return true;
    }

    public static int simulateTetris(String[] commands) {
        List<boolean[]> grid = new ArrayList<>();
        int[] heights = new int[WIDTH];

        for (String command : commands) {
            char pieceType = command.charAt(0);
            int[][] shape = SHAPES.get(pieceType);
            int startCol = Character.getNumericValue(command.charAt(1));

            // Determine the starting row for this piece
            int startRow = 0;
            for (int[] coord : shape) {
                int col = coord[0] + startCol;
                startRow = Math.max(heights[col], startRow);
            }

            // Ensure the grid has enough rows to place this piece
            while (grid.size() <= startRow + 4) {
                grid.add(new boolean[WIDTH]);
            }

            // Place the piece in the grid
            for (int[] coord : shape) {
                int col = coord[0] + startCol;
                int row = coord[1] + startRow;

                grid.get(row)[col] = true;
                heights[col] = Math.max(heights[col], row + 1);
            }

            // Check for completed rows
            Set<Integer> completedRows = new HashSet<>();
            for (int[] coord : shape) {
                int row = coord[1] + startRow;

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
}
