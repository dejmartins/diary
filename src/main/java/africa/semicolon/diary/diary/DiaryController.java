package africa.semicolon.diary.diary;

import africa.semicolon.diary.entry.Entry;
import africa.semicolon.diary.entry.EntryService;
import africa.semicolon.diary.requestsandresponses.response.ApiResponse;
import africa.semicolon.diary.requestsandresponses.DiaryAccessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    EntryService entryService;

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary){
        return diaryService.createDiary(diary);
    }

    @DeleteMapping("/{id}")
    public void deleteDiary(@PathVariable int id){
        diaryService.deleteDiary(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> accessDiary(@PathVariable int id,
                                                   @RequestBody DiaryAccessRequest diaryAccessRequest) {
        return diaryService.accessDiary(id, diaryAccessRequest);
    }

    @PutMapping("/{id}/add_entry/{entry_id}")
    public void addEntry(@PathVariable int id, @PathVariable int entry_id){
        Entry entry = entryService.getEntry(entry_id);
        diaryService.addEntry(id, entry);
    }

    @PutMapping("/{id}/remove_entry/{entry_id}")
    public void removeEntry(@PathVariable int id, @PathVariable int entry_id){
        Entry entry = entryService.getEntry(entry_id);
        diaryService.removeEntry(id, entry);
    }

    @GetMapping("/{id}/all_entries")
    public List<Entry> addEntry(@PathVariable int id){
        return diaryService.getAllEntries(id);
    }
}
