package africa.semicolon.diary.exceptions.userProfile;

public class UserProfileNotFoundException extends RuntimeException{

    public UserProfileNotFoundException(String message){
        super(message);
    }
}
