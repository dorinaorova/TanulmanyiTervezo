package tanulmanyitervezo.tervezo.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ZH implements Comparable<ZH>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    private Date date;
    private int maxPoints;
    private boolean countin;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Subject subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public boolean isCountin() {
        return countin;
    }

    public void setCountin(boolean countin) {
        this.countin = countin;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public int compareTo(ZH zh){
        if(getDate() == null || zh.getDate()==null) return 0;
        return getDate().compareTo(zh.getDate());
    }
}
