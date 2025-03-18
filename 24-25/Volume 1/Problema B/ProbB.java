import java.util.Scanner;

public class ProbB {
    public static int[] calcularRota(Scanner scanner, int segmentos, int origem, int destino, int grupo, int[] rota) {
        int totalProblemas = 0;
        int minLugares = 0;
        boolean iniciou = false;
        boolean terminou = false;

        for (int i = 0; i < segmentos - 1; i++) {
            int inicio = scanner.nextInt();
            int problemas = scanner.nextInt();
            int lugares = scanner.nextInt();

            if (inicio == destino && !iniciou) {
                rota[0] = -1;
            } else if (iniciou && inicio == destino) {
                terminou = true;
            }
            if (!terminou) {
                totalProblemas += problemas;
            }

            if (iniciou && lugares >= grupo && minLugares > lugares && !terminou) {
                minLugares = lugares;
            }

            if (inicio == origem && lugares >= grupo) {
                minLugares = lugares;
                iniciou = true;
            }
            if (iniciou && lugares < grupo && !terminou) {
                rota[0] = -1;
            }
        }

        int ultimo = scanner.nextInt();
        if (!iniciou) {
            rota[0] = -1;
            return rota;
        }
        if (rota[0] == -1) {
            return rota;
        }
        if (ultimo == destino || (iniciou && terminou)) {
            rota[1] = totalProblemas;
            rota[2] = minLugares;
        }
        return rota;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int R = scanner.nextInt();
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        int T = scanner.nextInt();
        int[] melhorRota = {0, 31, 0};
        int segmentos = 0;
        int[] rotaAtual = {0, 31, 0};

        for (int i = 0; i < R; i++) {
            scanner.nextLine();
            segmentos = scanner.nextInt();
            scanner.nextLine();
            rotaAtual[0] = i + 1;
            rotaAtual = calcularRota(scanner, segmentos, S, T, N, rotaAtual);

            if (rotaAtual[0] != -1 && (melhorRota[1] > rotaAtual[1] || (melhorRota[1] == rotaAtual[1] && melhorRota[2] < rotaAtual[2]))) {
                melhorRota[0] = rotaAtual[0];
                melhorRota[1] = rotaAtual[1];
                melhorRota[2] = rotaAtual[2];
            }
        }

        if (melhorRota[1] == 31) {
            System.out.println("Impossivel");
        } else {
            System.out.printf("Rota = %d Probs = %d Lugares = %d%n", melhorRota[0], melhorRota[1], melhorRota[2]);
        }

        scanner.close();
    }
}