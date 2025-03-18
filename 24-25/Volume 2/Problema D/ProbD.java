import java.util.*;

public class ProbD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of nodes and edges
        int n = scanner.nextInt();
        int r = scanner.nextInt();

        // Create adjacency list
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < r; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Array to store the highest node identifier for each component
        int[] component = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        // Function to perform DFS and mark components
        for (int i = n; i >= 1; i--) {
            if (!visited[i]) {
                dfs(i, i, graph, visited, component);
            }
        }

        // Read number of queries
        int q = scanner.nextInt();

        // Answer each query
        for (int i = 0; i < q; i++) {
            int v = scanner.nextInt();
            System.out.println("No " + v + ": " + component[v]);
        }

        scanner.close();
    }

    private static void dfs(int node, int maxNode, List<List<Integer>> graph, boolean[] visited, int[] component) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;
        component[node] = maxNode;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    component[neighbor] = maxNode;
                    stack.push(neighbor);
                }
            }
        }
    }
}