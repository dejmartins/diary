package africa.semicolon.diary.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

//    @GetMapping
//    public List<Entry> getAllEntries(){
//        return entryService.getAllEntries();
//    }

    @GetMapping
    public Map<String, Object> getAllEntries(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size){
        Pageable pageSort = PageRequest.of(page, size);
        Page<Entry> pageEntry = entryService.getAllEntries(pageSort);
        Map<String, Object> entryDetails = new HashMap<>();
        entryDetails.put("Entries", pageEntry.getContent());
        entryDetails.put("CurrentPage", pageEntry.getNumber());
        entryDetails.put("TotalItems", pageEntry.getTotalElements());
        entryDetails.put("TotalPages", pageEntry.getTotalPages());
        entryDetails.put("NextPage", pageEntry.nextPageable().next());
        return entryDetails;
    }

    @PutMapping("/{id}")
    public Entry updateEntry(@PathVariable int id, @RequestBody Entry entry){
        return entryService.updateEntry(id, entry);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable int id) {
        entryService.deleteEntry(id);
    }

}
