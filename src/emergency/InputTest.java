package emergency;

public class InputTest {
    public static void main(String[] args) {
        check("HIGH", "HIGH");
        check("medium", "MEDIUM");
        check("low", "LOW");
        System.out.println("Validation tests passed.");
    }

    private static void check(String input, String expected) {
        String result = Validator.priority(input);
        if (!result.equals(expected)) {
            throw new RuntimeException("Test failed.");
        }
    }
}
