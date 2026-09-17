package emergency;

public class IdGenerator {
    public static String incidentId(int number) {
        return String.format("INC%03d", number);
    }

    public static String resourceId(int number) {
        return String.format("RES%03d", number);
    }
}
