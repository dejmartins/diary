package africa.semicolon.diary.diary;

import africa.semicolon.diary.exceptions.diary.DiaryNotFoundException;
import africa.semicolon.diary.exceptions.diary.InvalidLockException;
import africa.semicolon.diary.requestsandresponses.response.ApiResponse;
import africa.semicolon.diary.requestsandresponses.DiaryAccessRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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

    public Diary getDiary(int id){
        return foundDiaryWithThis(id);
    }

    public ResponseEntity<ApiResponse> accessDiary(int id, DiaryAccessRequest diaryAccessRequest) {
        Diary foundDiary = foundDiaryWithThis(id);
        if (locksMatches(diaryAccessRequest.getLock(), foundDiary.getLock())){
            return diarySuccessfullyAccessedResponse();
        }
        else {
            throw new InvalidLockException("Incorrect lock");
        }
    }

    public void deleteDiary(int id){
        Diary foundDiary = foundDiaryWithThis(id);
        diaryRepository.delete(foundDiary);
    }

    //Helper Methods
    private ResponseEntity<ApiResponse> diarySuccessfullyAccessedResponse(){
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .message("Diary accessed successfully")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    private Diary foundDiaryWithThis(int id){
        Diary diary = null;
        Optional<Diary> foundDiary = diaryRepository.findById(id);
        if(foundDiary.isPresent()){
            diary = foundDiary.get();
        } else {
            throw new DiaryNotFoundException("Diary with id {" + id + "} not found");
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
