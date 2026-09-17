package emergency;

public class Validator {
    public static String required(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public static String priority(String value) {
        String p = required(value, "Priority").toUpperCase();
        if (!p.equals("HIGH") && !p.equals("MEDIUM") && !p.equals("LOW")) {
            throw new IllegalArgumentException("Priority must be HIGH, MEDIUM or LOW.");
        }
        return p;
    }

    public static String incidentStatus(String value) {
        String s = required(value, "Status").toUpperCase();
        if (!s.equals("OPEN") && !s.equals("ASSIGNED") && !s.equals("RESOLVED")) {
            throw new IllegalArgumentException("Status must be OPEN, ASSIGNED or RESOLVED.");
        }
        return s;
    }
}
