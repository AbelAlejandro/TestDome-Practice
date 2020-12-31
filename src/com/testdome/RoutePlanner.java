package com.testdome;

import java.util.*;

public class RoutePlanner {
    private static int rowCount = 0;
    private static int colCount = 0;
    private static boolean exists = false;
    public static boolean routeExistsDFS(int fromRow, int fromColumn, int toRow, int toColumn,
                                      boolean[][] mapMatrix) {
        rowCount = mapMatrix.length;
        if(rowCount == 0) return false;
        colCount = mapMatrix[0].length;
        if(mapMatrix[fromRow][fromColumn]) {
            dfs(fromRow, fromColumn, mapMatrix, toRow, toColumn);
        }
        return exists;
    }

    private static void dfs(int row, int col, boolean[][] mapMatrix, int toRow, int toColumn) {
        if(row == toRow && col == toColumn && mapMatrix[row][col]) {
            exists = true;
            return;
        }
        if(row >= rowCount || col >= colCount || row < 0 || col < 0) {
            return;
        }
        if(!mapMatrix[row][col]) return;
        if(mapMatrix[row][col]) mapMatrix[row][col] = false;
        dfs(row - 1, col, mapMatrix, toRow, toColumn);
        dfs(row + 1, col, mapMatrix, toRow, toColumn);
        dfs(row, col - 1, mapMatrix, toRow, toColumn);
        dfs(row, col + 1, mapMatrix, toRow, toColumn);
    }

    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn,
                                         boolean[][] mapMatrix) {
        rowCount = mapMatrix.length;
        if(rowCount == 0) return false;
        colCount = mapMatrix[0].length;
        boolean[][] visited = new boolean[rowCount][colCount];
        Queue<String> queue = new LinkedList<>();
        queue.add(fromRow + "," + fromColumn);
        while (!queue.isEmpty()) {

            String x = queue.remove();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            if (row < 0 || col < 0 || row >= rowCount || col >= colCount || visited[row][col])
                continue;
            if (row == toRow && col == toColumn && mapMatrix[row][col]) return true;

            visited[row][col] = true;
            queue.add(row + "," + (col - 1)); //go left
            queue.add(row + "," + (col + 1)); //go right
            queue.add((row - 1) + "," + col); //go up
            queue.add((row + 1) + "," + col); //go down
        }
        return false;
    }

    public static void main(String[] args) {
        boolean[][] mapMatrix = {
                {true,  false, false},
                {true,  true,  false},
                {false, true,  true}
        };

        System.out.println(routeExists(0, 0, 2, 2, mapMatrix));
    }
}