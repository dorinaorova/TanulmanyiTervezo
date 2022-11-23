package tanulmanyitervezo.tervezo.model;

import javax.persistence.*;

@Entity
public class Holiday implements Comparable<Holiday> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    private String name;
    @Lob
    private String description;
    private Long date;
    private boolean repeating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    @Override
    public int compareTo(Holiday holiday){
        if(getDate()==0 || holiday.getDate()==0) return 0;
        else if(getDate()<holiday.getDate()) return -1;
        else return 1;
    }
}
