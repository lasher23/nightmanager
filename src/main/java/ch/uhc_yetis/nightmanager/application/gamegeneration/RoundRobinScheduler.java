package ch.uhc_yetis.nightmanager.application.gamegeneration;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds round-robin pairings using the standard "circle method": one team is
 * fixed, the others rotate around it each round. Guarantees that within the
 * rounds it produces, no pairing repeats and no team plays itself.
 */
public final class RoundRobinScheduler {

    private RoundRobinScheduler() {
    }

    /**
     * Full round robin, grouped by round: every team plays every other team
     * exactly once. For n teams this produces n-1 rounds (n if n is odd, with
     * one bye per round), each round being a list of simultaneous pairings.
     */
    public static List<List<int[]>> fullRoundRobinRounds(int teamCount) {
        return roundsGrouped(teamCount, teamCount % 2 == 0 ? teamCount - 1 : teamCount);
    }

    /**
     * Partial round robin, grouped by round: only the first {@code rounds}
     * rounds of the full schedule are returned (still guaranteed to contain no
     * repeated pairing), each round being a list of simultaneous pairings.
     */
    public static List<List<int[]>> partialRoundRobinRounds(int teamCount, int rounds) {
        return roundsGrouped(teamCount, rounds);
    }

    private static List<List<int[]>> roundsGrouped(int teamCount, int rounds) {
        List<List<int[]>> result = new ArrayList<>();
        if (teamCount < 2) {
            return result;
        }
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < teamCount; i++) {
            ids.add(i);
        }
        boolean hasBye = teamCount % 2 != 0;
        if (hasBye) {
            ids.add(-1);
        }
        int n = ids.size();

        for (int round = 0; round < rounds; round++) {
            List<int[]> pairs = new ArrayList<>();
            for (int i = 0; i < n / 2; i++) {
                int a = ids.get(i);
                int b = ids.get(n - 1 - i);
                if (a != -1 && b != -1) {
                    pairs.add(new int[]{a, b});
                }
            }
            result.add(pairs);
            // rotate all but the first element
            Integer last = ids.remove(ids.size() - 1);
            ids.add(1, last);
        }
        return result;
    }
}
