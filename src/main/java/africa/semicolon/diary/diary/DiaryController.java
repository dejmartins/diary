package africa.semicolon.diary.diary;

import africa.semicolon.diary.requestsandresponses.DiaryAccessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary){
        return diaryService.createDiary(diary);
    }

    @DeleteMapping("/{id}")
    public void deleteDiary(@PathVariable int id){
        diaryService.deleteDiary(id);
    }

    @GetMapping("/{id}")
    public Diary accessDiary(@PathVariable int id, @RequestBody DiaryAccessRequest diaryAccessRequest) {
        return diaryService.accessDiary(id, diaryAccessRequest);
    }
}
