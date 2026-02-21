package hexlet.code.controller.api;

import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public List<User> index(@RequestParam(defaultValue = "10") Integer limit) {
        return userRepository.findAll().stream().toList();
    }



}
