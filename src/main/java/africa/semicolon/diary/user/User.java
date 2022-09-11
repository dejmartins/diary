package africa.semicolon.diary.user;

import africa.semicolon.diary.diary.Diary;
import africa.semicolon.diary.userProfile.UserProfile;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String emailAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile userProfile;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diary_id")
    private List<Diary> diaryList = new ArrayList<>();

    public void setUsername(String username){
        this.username = "@"+username;
    }

    public void addDiary(Diary diary){
        diaryList.add(diary);
    }

    public void removeDiary(Diary diary){
        diaryList.remove(diary);
    }

    public List<Diary> getAllDiaries(){
        return diaryList;
    }

}
