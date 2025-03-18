import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProbA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNos = scanner.nextInt();
        int numTrajetos = scanner.nextInt();
        scanner.nextLine();

        List<Set<Integer>> grafo = new ArrayList<>(numNos);
        for (int i = 0; i < numNos; i++) {
            grafo.add(new HashSet<>());
        }

        for (int i = 0; i < numTrajetos; i++) {
            int numNosTrajeto = scanner.nextInt();
            int[] trajeto = new int[numNosTrajeto];
            for (int j = 0; j < numNosTrajeto; j++) {
                trajeto[j] = scanner.nextInt() - 1; // Ajustar para índice zero
            }
            for (int j = 0; j < numNosTrajeto - 1; j++) {
                grafo.get(trajeto[j]).add(trajeto[j + 1]);
            }
        }

        for (int i = 0; i < numNos; i++) {
            System.out.println(grafo.get(i).size());
        }

        scanner.close();
    }
}