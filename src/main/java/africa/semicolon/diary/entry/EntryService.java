package africa.semicolon.diary.entry;

import africa.semicolon.diary.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    public Entry addEntry(Entry entry){
        entry.setId(0);
        return entryRepository.save(entry);
    }

    public Entry getEntry(int id){
        return foundEntryWithThis(id);
    }

    public List<Entry> getAllEntries(){
        return entryRepository.findAll();
    }

    public Entry updateEntry(int id, Entry entry){
        Entry foundEntry = foundEntryWithThis(id);
        return entryRepository.save(foundEntry);
    }

    public void deleteEntry(int id){
        Entry foundEntry = foundEntryWithThis(id);
        entryRepository.delete(foundEntry);
    }

    private Entry foundEntryWithThis(int id){
        Entry entry = null;
        Optional<Entry> foundEntry = entryRepository.findById(id);
        if(foundEntry.isPresent()){
            entry = foundEntry.get();
        } else {
            throw new NotFoundException("Entry with id {" + id + "} not found");
        }
        return entry;
    }
}
