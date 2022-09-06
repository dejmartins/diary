package africa.semicolon.diary.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @PostMapping
    public UserProfile addUserProfile(@RequestBody UserProfile userProfile){
        return userProfileService.addUserProfile(userProfile);
    }

    @GetMapping("/{id}")
    public UserProfile findUserProfile(@PathVariable int id){
        return userProfileService.findUserProfile(id);
    }

    @GetMapping
    public List<UserProfile> getAllUserProfiles(){
        return userProfileService.getAllUserProfiles();
    }

    @PutMapping("/{id}")
    public UserProfile updateUserProfile(@PathVariable int id, @RequestBody UserProfile userProfile){
        return userProfileService.updateUserProfile(id, userProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteUserProfile(@PathVariable int id){
        userProfileService.deleteProfile(id);
    }
}
