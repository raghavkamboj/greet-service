package in.raghav.greet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    public String greeting() {
        return "Hello from the Jenkins CI/CD pipeline - greet-service";
    }

    @GetMapping("/greet")
    public String greet() {
        return greeting();
    }
}
