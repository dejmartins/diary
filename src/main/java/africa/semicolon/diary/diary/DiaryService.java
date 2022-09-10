package africa.semicolon.diary.diary;

import africa.semicolon.diary.exceptions.diary.InvalidLockException;
import africa.semicolon.diary.exceptions.user.UserNotFoundException;
import africa.semicolon.diary.requestsandresponses.DiaryAccessRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    public Diary createDiary(Diary diary){
        diary.setId(0);
        diary.setLock(toHashedLock(diary.getLock()));
        return diaryRepository.save(diary);
    }

    public Diary accessDiary(int id, DiaryAccessRequest diaryAccessRequest) {
        Diary foundDiary = foundDiaryWithThis(id);
        if (locksMatches(diaryAccessRequest.getLock(), foundDiary.getLock())) return foundDiary;
        else {
            throw new InvalidLockException("Incorrect lock");
        }
    }

    public void deleteDiary(int id){
        Diary foundDiary = foundDiaryWithThis(id);
        diaryRepository.delete(foundDiary);
    }

    private Diary foundDiaryWithThis(int id){
        Diary diary = null;
        Optional<Diary> foundDiary = diaryRepository.findById(id);
        if(foundDiary.isPresent()){
            diary = foundDiary.get();
        } else {
            throw new UserNotFoundException("User with id {" + id + "} not found");
        }
        return diary;
    }

    private String toHashedLock(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean locksMatches(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
