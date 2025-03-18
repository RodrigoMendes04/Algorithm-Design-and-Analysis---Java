import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProbC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mapa para armazenar a última posição de cada local
        HashMap<Integer, Integer> ultimaPosicao = new HashMap<>();

        ArrayList<Integer> sequenciaLocais = new ArrayList<>();
        int local;

        // Ler a sequência de locais até encontrar 0
        while ((local = scanner.nextInt()) != 0) {
            // Adicionar o local à sequência
            sequenciaLocais.add(local);
            ultimaPosicao.put(local, sequenciaLocais.size() - 1);
        }

        // Nova sequência sem voltas desnecessárias
        ArrayList<Integer> novaSequencia = new ArrayList<>();

        for (Integer lugar : sequenciaLocais) {
            // Se o local ainda não foi adicionado à nova sequência
            if (!novaSequencia.contains(lugar)) {
                novaSequencia.add(lugar);
            } else {
                // Remover todas as ocorrências anteriores do local
                novaSequencia.subList(novaSequencia.indexOf(lugar), novaSequencia.size()).clear();
                // Adicionar o local à nova sequência
                novaSequencia.add(lugar);
            }
        }

        // Imprimir a nova sequência de locais
        for (Integer lugar : novaSequencia) {
            System.out.println(lugar);
        }
    }
}
