package tanulmanyitervezo.tervezo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Holiday implements Comparable<Holiday> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    private String description;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Holiday holiday){
        if(getDate() == null || holiday.getDate()==null) return 0;
        return getDate().compareTo(holiday.getDate());
    }
}
