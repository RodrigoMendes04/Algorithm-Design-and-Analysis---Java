import java.util.*;


public class ProbA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of people
        int n = scanner.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt() - 1; // Convert to 0-based index
        }

        // Process the information
        List<List<Integer>> groups = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int peopleNotInGroups = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> group = new ArrayList<>();
                int current = i;

                // Traverse the cycle of the current group
                while (!visited[current]) {
                    visited[current] = true;
                    group.add(current + 1); // Convert back to 1-based index
                    current = sequence[current];
                }

                // Check if the group has three or more people
                if (group.size() >= 3) {
                    groups.add(group);
                } else {
                    peopleNotInGroups += group.size();
                }
            }
        }

        // Sort groups by the smallest identifier in each group
        groups.sort((g1, g2) -> Integer.compare(g1.get(0), g2.get(0)));

        // Output the groups
        for (List<Integer> group : groups) {
            printCycle(group);
        }

        // Output the number of people not in groups with three or more elements
        System.out.println(peopleNotInGroups);
    }

    private static void printCycle(List<Integer> cycle) {
        int posCycle = cycle.size();
        System.out.print(posCycle);
        int max = cycle.get(0);
        int maxPos = 0;
        for (int i = 1; i < posCycle; i++) {
            if (cycle.get(i) > max) {
                max = cycle.get(i);
                maxPos = i;
            }
        }
        for (int i = 0; i < posCycle; i++) {
            System.out.print(" " + cycle.get((maxPos + i) % posCycle));
        }
        System.out.println();
    }
}