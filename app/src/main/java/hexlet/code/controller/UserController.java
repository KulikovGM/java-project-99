package hexlet.code.controller;

import hexlet.code.dto.UserDTO;
import hexlet.code.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public ResponseEntity<List<UserDTO>> index() {
        var users = userRepository.findAll();

        var result = users.stream()
                .toList();

        return (result);
    }
}
