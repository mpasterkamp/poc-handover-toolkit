package nl.axxes.poc_handover_toolkit.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDetails getUserDetails(@PathVariable Long id) throws Exception {
        return userService.getUserDetails(id);
    }

    @PostMapping
    public Long createUser(@RequestBody UserCreateDto user) throws Exception {
        return userService.createUser(user);
    }
}
