package africa.semicolon.diary.user;

import africa.semicolon.diary.diary.Diary;
import africa.semicolon.diary.diary.DiaryService;
import africa.semicolon.diary.userProfile.UserProfile;
import africa.semicolon.diary.userProfile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DiaryService diaryService;

    @Autowired
    UserProfileService userProfileService;

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

    @PatchMapping("/{id}")
    public User changeEmail(@PathVariable int id,
                            @RequestBody Map<String, Object> emailAddressPatched){
        return userService.updateEmailAddress(id, emailAddressPatched);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @PutMapping({"/{id}/profiles/{profile_id}"})
    public User assignProfile(@PathVariable int id, @PathVariable int profile_id ){
        UserProfile profile = userProfileService.findUserProfile(profile_id);
        return userService.assignProfile(id, profile);
    }

    @PutMapping("/{id}/add_diary/{diary_id}")
    public User addDiary(@PathVariable int id, @PathVariable int diary_id){
        Diary diary = diaryService.getDiary(diary_id);
        return userService.addDiary(id, diary);
    }

    @PutMapping("/{id}/remove_diary/{diary_id}")
    public User removeDiary(@PathVariable int id, @PathVariable int diary_id){
        Diary diary = diaryService.getDiary(id);
        return userService.removeDiary(id, diary);
    }

}
