package emergency;

import java.util.*;

public class PriorityManager {
    private static int rank(String priority) {
        if (priority.equals("HIGH")) return 1;
        if (priority.equals("MEDIUM")) return 2;
        return 3;
    }

    public static void sort(List<Incident> incidents) {
        incidents.sort(Comparator.comparingInt(i -> rank(i.getPriority())));
    }
}
