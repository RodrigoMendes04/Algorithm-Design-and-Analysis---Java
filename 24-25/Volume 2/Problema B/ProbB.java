import java.util.*;

public class ProbB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of supermarkets
        int n = scanner.nextInt();
        int[] pumpkins = new int[n + 1];

        // Read number of pumpkins in each supermarket
        for (int i = 1; i <= n; i++) {
            pumpkins[i] = scanner.nextInt();
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

        // Read number of cases
        int k = scanner.nextInt();
        int[] cases = new int[k];
        for (int i = 0; i < k; i++) {
            cases[i] = scanner.nextInt();
        }

        // Process each case
        for (int start : cases) {
            int result = findBestSupermarket(start, pumpkins, graph);
            if (result == -1) {
                System.out.println("Impossivel");
            } else {
                System.out.println(result);
            }
        }

        scanner.close();
    }

    private static int findBestSupermarket(int start, int[] pumpkins, List<List<Integer>> graph) {
        if (pumpkins[start] > 0) {
            return start;
        }

        boolean[] visited = new boolean[pumpkins.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int maxPumpkins = -1;
        int bestSupermarket = -1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    if (pumpkins[neighbor] > maxPumpkins) {
                        maxPumpkins = pumpkins[neighbor];
                        bestSupermarket = neighbor;
                    } else if (pumpkins[neighbor] == maxPumpkins && neighbor < bestSupermarket) {
                        bestSupermarket = neighbor;
                    }
                }
            }
        }

        return maxPumpkins == 0 ? -1 : bestSupermarket;
    }
}