package africa.semicolon.diary.user;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public User createUser(User user){
        user.setId(0);
        return userRepository.save(user);
    }

    public User findUser(int id){
        return foundUserWithThis(id);
    }

    private User foundUserWithThis(int id){
        User user = null;
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()) user = foundUser.get();
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(int id, User user){
        User foundUser = foundUserWithThis(id);
        foundUser.setUsername(user.getUsername());
        foundUser.setEmailAddress(user.getEmailAddress());
        return userRepository.save(foundUser);
    }

    public User changeEmailAddress(int id, Map<String, Object> emailAddressPatched){
        Optional<User> foundUser = userRepository.findById(id);
        User user = null;

        // Using Reflection API to access the emailAddress field at runtime
        if(foundUser.isPresent()) {
            emailAddressPatched.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, foundUser.get(), value);
            });
        }
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
