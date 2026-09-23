package in.raghav.greet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GreetControllerTest {

    private final GreetController controller = new GreetController();

    @Test
    void greetingIsNotEmpty() {
        assertFalse(controller.greeting().isEmpty());
    }

    @Test
    void greetingMentionsJenkins() {
        assertTrue(controller.greeting().contains("Jenkins"));
    }
}
