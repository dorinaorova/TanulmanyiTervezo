package tanulmanyitervezo.tervezo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Semester implements Comparable<Semester>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    private String name;
    private Date start;
    private Date end;
    private boolean current;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    @Override
    public int compareTo(Semester semester){
        if(getName() == null || semester.getName()==null) return 0;
        return getName().compareTo(semester.getName());
    }
}
