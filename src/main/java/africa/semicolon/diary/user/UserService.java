package africa.semicolon.diary.user;

import africa.semicolon.diary.diary.Diary;
import africa.semicolon.diary.exceptions.NotFoundException;
import africa.semicolon.diary.userProfile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
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
        if(foundUser.isPresent()){
            user = foundUser.get();
        } else {
            throw new NotFoundException("User with id {" + id + "} not found");
        }

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

    public User updateEmailAddress(int id, Map<String, Object> emailAddressPatched){
        Optional<User> foundUser = userRepository.findById(id);
        User user = null;

        // Using Reflection API to access the emailAddress field at runtime
        if(foundUser.isPresent()) {
            emailAddressPatched.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, foundUser.get(), value);
            });
        } else {
            throw new NotFoundException("User with id {" + id + "} not found");
        }

        return userRepository.save(user);
    }

    public User assignProfile(int id, UserProfile profile) {
        User user = foundUserWithThis(id);
        user.setUserProfile(profile);
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        User foundUser = foundUserWithThis(id);
        userRepository.delete(foundUser);
    }

    public User addDiary(int id, Diary diary) {
        User foundUser = foundUserWithThis(id);
        foundUser.addDiary(diary);
        return userRepository.save(foundUser);
    }

    public User removeDiary(int id, Diary diary){
        User foundUser = foundUserWithThis(id);
        foundUser.removeDiary(diary);
        return userRepository.save(foundUser);
    }

//    public List<Diary> allDiaries(int id){
//        User foundUser = foundUserWithThis(id);
//        return foundUser.getAllDiaries();
//    }


}
