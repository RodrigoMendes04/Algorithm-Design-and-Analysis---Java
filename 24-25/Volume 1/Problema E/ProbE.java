import java.util.PriorityQueue;
import java.util.Scanner;

class Cliente implements Comparable<Cliente> {
    int hora;
    int minuto;
    int tempo;

    public Cliente(int hora, int minuto, int tempo) {
        this.hora = hora;
        this.minuto = minuto;
        this.tempo = tempo;
    }

    @Override
    public int compareTo(Cliente outro) {
        if (hora == outro.hora) {
            return minuto - outro.minuto;
        }
        return hora - outro.hora;
    }
}

class Barbeiro {
    private final int inicio_1 = 9 * 60;   // horas em minutos a que abre
    private final int fim_1 = 12 * 60;     // horas a que fecha de manhã em minutos
    private final int inicio_2 = 15 * 60;  // horas a que abre de tarde em minutos
    private final int fim_2 = 19 * 60;     // horas a que fecha em minutos
    private final int[] pessoas = {0, 0, 0, 0};  // guarda a hora a que as pessoas irão sair (a que está a ser atendida em 0 e as restantes a seguir)
    private int pos;
    private int control_break = 0;

    Barbeiro() {
        pos = 0;
    }

    public boolean hora_possivel(int hora, int minuto) {
        int chegada = hora * 60 + minuto;
        if (chegada >= inicio_1 && chegada <= fim_1) {
            return true;    // pode ser atendido durante a manhã
        } else if (chegada >= inicio_2 && chegada <= fim_2) {
            return true;    // pode ser atendido durante a tarde
        }
        return false;   // fora de horas
    }

    public void atualizar_pessoas() {
        pessoas[0] = pessoas[1];
        pessoas[1] = pessoas[2];
        pessoas[2] = pessoas[3];
        pessoas[3] = 0;
    }

    public void saiu(int hora, int minuto) {
        int tempo = hora * 60 + minuto;
        for (int i = 0; i < pos; i++) {
            if (tempo >= pessoas[i]) {
                atualizar_pessoas();
                pos -= 1;
            }
        }
    }

    public void atendido(int hora, int minuto, int duracao) {
        if (pos == 0) {
            pessoas[0] = hora * 60 + minuto + duracao;
        } else {
            pessoas[pos] = pessoas[pos - 1] + duracao;
        }
        pos += 1;
    }

    public boolean possivel_atender(int hora, int minuto, int duracao) {
        if (control_break == 0) {
            if (hora >= 15) {
                for (int i = 0; i < 4; i++) {
                    atualizar_pessoas();
                    pos = 0;
                }
                control_break = 1;
            }
        }

        if (hora_possivel(hora, minuto)) {
            saiu(hora, minuto);
            if (pos <= 3) {
                atendido(hora, minuto, duracao);
                return true;
            }
        }
        return false;
    }
}

public class ProbE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Barbeiro barbeiro = new Barbeiro();
        int perdidos = 0;

        int K = scanner.nextInt();
        for (int i = 0; i < K; i++) {
            int hora = scanner.nextInt();
            int minuto = scanner.nextInt();
            int duracao = scanner.nextInt();

            if (!barbeiro.possivel_atender(hora, minuto, duracao)) {
                perdidos += 1;
            }
        }

        System.out.println("Perdeu = " + perdidos);
        scanner.close();
    }
}