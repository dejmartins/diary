package africa.semicolon.diary.userProfile;

import africa.semicolon.diary.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public UserProfile addUserProfile(UserProfile userProfile){
        userProfile.setId(0);
        return userProfileRepository.save(userProfile);
    }

    public UserProfile findUserProfile(int id){
        return foundProfileWithThis(id);
    }

    private UserProfile foundProfileWithThis(int id){
        UserProfile profile = null;
        Optional<UserProfile> foundProfile = userProfileRepository.findById(id);
        if(foundProfile.isPresent()){
            profile = foundProfile.get();
        } else {
            throw new NotFoundException("User profile with id {" + id + "} not found");
        }
        return profile;
    }

    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }

    public UserProfile updateUserProfile(int id, UserProfile profile){
        UserProfile foundProfile = foundProfileWithThis(id);
        foundProfile.setFirstName(profile.getFirstName());
        foundProfile.setLastName(profile.getLastName());
        foundProfile.setPhoneNumber(profile.getPhoneNumber());
        foundProfile.setTwitter(profile.getTwitter());
        return userProfileRepository.save(foundProfile);
    }

    public void deleteProfile(int id){
        UserProfile foundProfile = foundProfileWithThis(id);
        userProfileRepository.delete(foundProfile);
    }

}
