package africa.semicolon.diary.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    EntryService entryService;

    @PostMapping
    public Entry addEntry(@RequestBody Entry entry){
        return entryService.addEntry(entry);
    }

    @GetMapping("/{id}")
    public Entry getEntry(@PathVariable int id){
        return entryService.getEntry(id);
    }

    @GetMapping
    public List<Entry> getAllEntries(){
        return entryService.getAllEntries();
    }

    @PutMapping("/{id}")
    public Entry updateEntry(@PathVariable int id, @RequestBody Entry entry){
        return entryService.updateEntry(id, entry);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable int id){
        entryService.deleteEntry(id);
    }

}
