import java.util.Scanner;

public class ProbD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of chair types
        int numTiposCadeiras = scanner.nextInt();
        int[] cadeiras = new int[numTiposCadeiras + 1];
        int totalCadeiras = 0;
        int habitantesEmPe = 0;

        // Read the types and quantities of chairs
        for (int i = 1; i <= numTiposCadeiras; i++) {
            int tipo = scanner.nextInt();
            cadeiras[tipo] = scanner.nextInt();
            totalCadeiras += cadeiras[tipo];
        }

        // Read the number of inhabitants
        int numHabitantes = scanner.nextInt();

        // Process each inhabitant's preferences
        for (int i = 0; i < numHabitantes; i++) {
            int numOpcoes = scanner.nextInt();
            boolean seated = false;

            for (int j = 0; j < numOpcoes; j++) {
                int tipoCadeira = scanner.nextInt();
                if (!seated && cadeiras[tipoCadeira] > 0) {
                    cadeiras[tipoCadeira]--;
                    totalCadeiras--;
                    seated = true;
                }
            }

            if (!seated) {
                habitantesEmPe++;
            }
        }

        // Output the results
        System.out.println(habitantesEmPe);
        System.out.println(totalCadeiras);

        scanner.close();
    }
}