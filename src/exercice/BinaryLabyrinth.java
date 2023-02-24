package exercice;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryLabyrinth {
    private int[][] matrix;
    private int rows;
    private int cols;

    public BinaryLabyrinth(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    public int shortestPath(int[] start, int[] end) {
        if (matrix[start[0]][start[1]] == 0 || matrix[end[0]][end[1]] == 0) {
            return -1; // Le chemin n'existe pas si l'une des cellules est 0.
        }

        boolean[][] visited = new boolean[rows][cols]; // Matrice pour stocker les cellules visitées.
        visited[start[0]][start[1]] = true;

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // Les 4 directions possibles.
        Queue<int[]> queue = new LinkedList<>(); // La file d'attente pour stocker les cellules à visiter.
        queue.offer(start);

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                if (cell[0] == end[0] && cell[1] == end[1]) {
                    return distance;
                }
                for (int[] dir : directions) {
                    int r = cell[0] + dir[0];
                    int c = cell[1] + dir[1];
                    if (r >= 0 && r < rows && c >= 0 && c < cols && matrix[r][c] == 1 && !visited[r][c]) {
                        visited[r][c] = true;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
            distance++;
        }

        return -1; // Le chemin n'a pas été trouvé.
    }

    public static void main(String[] args) {
        int[][] matrix = {
        		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
                };

        BinaryLabyrinth bm = new BinaryLabyrinth(matrix);
        int[] start = {0, 0};
        int[] end = {3, 4};

        int shortestPath = bm.shortestPath(start, end);
        System.out.println("Le chemin le plus court est de " + shortestPath + " étapes.");
    }
}
