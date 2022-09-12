package africa.semicolon.diary.diary;

import africa.semicolon.diary.entry.Entry;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "`lock`")
    private String lock;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "entry_id")
    private List<Entry> entryList = new ArrayList<>();

    public void addEntry(Entry entry){
        entryList.add(entry);
    }

    public void removeEntry(Entry entry){
        entryList.remove(entry);
    }

    public List<Entry> getAllEntries(){
        return entryList;
    }
}
