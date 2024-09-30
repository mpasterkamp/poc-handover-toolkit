package nl.axxes.poc_handover_toolkit.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncryptionService passwordEncryptionService;

    public UserService(UserRepository userRepository, PasswordEncryptionService passwordEncryptionService) {
        this.userRepository = userRepository;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserDetails getUserDetails(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        String password = passwordEncryptionService.decryptPassword(user.getPassword());
        return new UserDetails(user.getId(), user.getUsername(), password);
    }

    public Long createUser(UserCreateDto userCreateDto) throws Exception {
        String encryptedPassword = passwordEncryptionService.encryptPassword(userCreateDto.password());
        User user = new User();
        user.setPassword(encryptedPassword);
        user.setUsername(userCreateDto.username());
        return userRepository.save(user).getId();
    }
}
