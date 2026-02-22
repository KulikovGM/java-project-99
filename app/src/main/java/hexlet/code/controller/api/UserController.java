package hexlet.code.controller.api;

import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("")
    public List<User> index(@RequestParam(defaultValue = "10") Integer limit) {
        return userRepository.findAll().stream().toList();
    }

}
