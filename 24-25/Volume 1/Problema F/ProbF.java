import java.util.*;

class Submission {
    int teamId;
    int time;
    int problemId;
    int result;

    public Submission(int teamId, int time, int problemId, int result) {
        this.teamId = teamId;
        this.time = time;
        this.problemId = problemId;
        this.result = result;
    }
}

public class ProbF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int E = scanner.nextInt();
        int S = scanner.nextInt();
        int D = scanner.nextInt();
        int B = scanner.nextInt();
        int V = scanner.nextInt();
        scanner.nextLine();

        List<Submission> submissions = new ArrayList<>();
        for (int i = 0; i < S; i++) {
            int teamId = scanner.nextInt();
            int time = scanner.nextInt();
            int problemId = scanner.nextInt();
            int result = scanner.nextInt();
            submissions.add(new Submission(teamId, time, problemId, result));
        }

        int K = scanner.nextInt();
        scanner.nextLine();
        List<Integer> vSubmissions = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            vSubmissions.add(scanner.nextInt());
        }

        // Process submissions before blackout
        Map<Integer, Set<Integer>> teamSolvedProblems = new HashMap<>();
        Map<Integer, Integer> teamPenalty = new HashMap<>();
        for (Submission submission : submissions) {
            if (submission.time >= B) continue;
            teamSolvedProblems.putIfAbsent(submission.teamId, new HashSet<>());
            if (submission.result == 1) {
                if (!teamSolvedProblems.get(submission.teamId).contains(submission.problemId)) {
                    teamSolvedProblems.get(submission.teamId).add(submission.problemId);
                    teamPenalty.put(submission.teamId, teamPenalty.getOrDefault(submission.teamId, 0) + submission.time);
                }
            } else {
                if (!teamSolvedProblems.get(submission.teamId).contains(submission.problemId)) {
                    teamPenalty.put(submission.teamId, teamPenalty.getOrDefault(submission.teamId, 0) + 20);
                }
            }
        }

        // Process team V submissions during blackout
        int vPenalty = teamPenalty.getOrDefault(V, 0);
        int lastSubmissionTime = 0;
        for (int time : vSubmissions) {
            if (time - lastSubmissionTime < D) continue;
            lastSubmissionTime = time;
            vPenalty += time;
        }

        // Check if team V can be a winner
        boolean canWin = true;
        for (int teamId : teamPenalty.keySet()) {
            if (teamId == V) continue;
            if (teamSolvedProblems.get(teamId).size() == 7 && teamPenalty.get(teamId) <= vPenalty) {
                canWin = false;
                break;
            }
        }

        if (canWin) {
            System.out.println("Vencemos");
        } else {
            System.out.println("Nao sabemos");
        }

        scanner.close();
    }
}