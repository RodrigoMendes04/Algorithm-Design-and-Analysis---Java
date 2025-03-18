import java.util.*;

public class ProbG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int D = scanner.nextInt();
        int T = scanner.nextInt();
        scanner.nextLine();

        List<String> tentativas = new ArrayList<>();
        List<String> feedbacks = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            tentativas.add(scanner.nextLine());
            feedbacks.add(scanner.nextLine());
        }

        List<Set<Character>> possibleDigits = new ArrayList<>(D);
        for (int i = 0; i < D; i++) {
            Set<Character> digitSet = new HashSet<>();
            for (char c = '0'; c <= '9'; c++) {
                digitSet.add(c);
            }
            possibleDigits.add(digitSet);
        }

        for (int i = 0; i < T; i++) {
            String tentativa = tentativas.get(i);
            String feedback = feedbacks.get(i);

            for (int j = 0; j < D; j++) {
                char tentativaChar = tentativa.charAt(j);
                char feedbackChar = feedback.charAt(j);

                if (feedbackChar == 'X') {
                    for (int k = 0; k < D; k++) {
                        possibleDigits.get(k).remove(tentativaChar);
                    }
                } else if (feedbackChar == '-') {
                    possibleDigits.get(j).remove(tentativaChar);
                } else {
                    possibleDigits.get(j).clear();
                    possibleDigits.get(j).add(feedbackChar);
                }
            }
        }

        char[] possivelNumero = new char[D];
        boolean aindaNaoSei = false;

        for (int i = 0; i < D; i++) {
            if (possibleDigits.get(i).size() == 1) {
                possivelNumero[i] = possibleDigits.get(i).iterator().next();
            } else {
                aindaNaoSei = true;
                break;
            }
        }

        if (aindaNaoSei) {
            System.out.println("AINDA NAO SEI");
        } else {
            System.out.print("RESPOSTA ");
            for (char c : possivelNumero) {
                System.out.print(c);
            }
            System.out.println();
        }

        scanner.close();
    }
}