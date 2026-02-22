package hexlet.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/welcome")
    String welcome() {
        return "welcome";
    }

    @GetMapping("")
    String hello() {
        return "Hello!";
    }
}
