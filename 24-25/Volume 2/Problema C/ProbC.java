import java.util.*;

public class ProbC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of stores
        int n = scanner.nextInt();
        int[] santas = new int[n + 1];

        // Read number of Santas in each store
        for (int i = 1; i <= n; i++) {
            santas[i] = scanner.nextInt();
        }

        // Read number of connections
        int r = scanner.nextInt();
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read connections
        for (int i = 0; i < r; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        // Read starting store and maximum distance
        int P = scanner.nextInt();
        int K = scanner.nextInt();

        // Check if the starting store has Santas
        if (santas[P] > 0) {
            System.out.println("Que sorte");
        } else {
            int result = findStoresWithSantas(P, K, santas, graph);
            System.out.println(result);
        }

        scanner.close();
    }

    private static int findStoresWithSantas(int start, int maxDistance, int[] santas, List<List<Integer>> graph) {
        boolean[] visited = new boolean[santas.length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            if (distance > maxDistance) {
                continue;
            }

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    if (santas[neighbor] > 0 && distance + 1 <= maxDistance) {
                        count++;
                    }
                    queue.add(new int[]{neighbor, distance + 1});
                }
            }
        }

        return count;
    }
}