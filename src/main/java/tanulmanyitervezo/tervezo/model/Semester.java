package tanulmanyitervezo.tervezo.model;

import javax.persistence.*;

@Entity
public class Semester implements Comparable<Semester>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    private String name;
    private Long start;
    private Long end;
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

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
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
