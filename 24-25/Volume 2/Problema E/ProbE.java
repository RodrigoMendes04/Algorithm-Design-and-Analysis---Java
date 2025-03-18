import java.util.*;

public class ProbE {
    private static final int SIZE = 9;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];

        // Read the board state
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        // Read the selected ball position and target position
        int startX = scanner.nextInt() - 1;
        int startY = scanner.nextInt() - 1;
        int targetX = scanner.nextInt() - 1;
        int targetY = scanner.nextInt() - 1;

        // Check if the move is possible
        if (isPathExists(board, startX, startY, targetX, targetY)) {
            // Move the ball
            int color = board[startX][startY];
            board[startX][startY] = 0;
            board[targetX][targetY] = color;

            // Check for alignments and count removed balls
            int removedBalls = removeAlignments(board, targetX, targetY, color);
            System.out.println(removedBalls);
        } else {
            System.out.println(0);
        }

        scanner.close();
    }

    private static boolean isPathExists(int[][] board, int startX, int startY, int targetX, int targetY) {
        boolean[][] visited = new boolean[SIZE][SIZE];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == targetX && y == targetY) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + DX[i];
                int ny = y + DY[i];

                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && !visited[nx][ny] && board[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }

    private static int removeAlignments(int[][] board, int x, int y, int color) {
        int totalRemoved = 0;
        totalRemoved += checkAndRemove(board, x, y, color, 1, 0); // Horizontal
        totalRemoved += checkAndRemove(board, x, y, color, 0, 1); // Vertical
        totalRemoved += checkAndRemove(board, x, y, color, 1, 1); // Diagonal \
        totalRemoved += checkAndRemove(board, x, y, color, 1, -1); // Diagonal /
        return totalRemoved;
    }

    private static int checkAndRemove(int[][] board, int x, int y, int color, int dx, int dy) {
        List<int[]> positions = new ArrayList<>();
        int count = 0;

        // Check in the positive direction
        for (int i = 0; i < 5; i++) {
            int nx = x + i * dx;
            int ny = y + i * dy;
            if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && board[nx][ny] == color) {
                positions.add(new int[]{nx, ny});
                count++;
            } else {
                break;
            }
        }

        // Check in the negative direction
        for (int i = 1; i < 5; i++) {
            int nx = x - i * dx;
            int ny = y - i * dy;
            if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && board[nx][ny] == color) {
                positions.add(new int[]{nx, ny});
                count++;
            } else {
                break;
            }
        }

        // Remove balls if there are 5 or more in a line
        if (count >= 5) {
            for (int[] pos : positions) {
                board[pos[0]][pos[1]] = 0;
            }
            return count;
        }

        return 0;
    }
}