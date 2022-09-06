package africa.semicolon.diary.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable int id){
        return userService.findUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    public User changeEmail(@PathVariable int id,
                            @RequestBody Map<String, Object> emailAddressPatched){
        return userService.changeEmailAddress(id, emailAddressPatched);
    }

}
