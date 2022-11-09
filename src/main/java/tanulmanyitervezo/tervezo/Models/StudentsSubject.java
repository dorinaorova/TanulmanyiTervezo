package tanulmanyitervezo.tervezo.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudentsSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    @ManyToOne
    private User student;
    @ManyToMany
    private List<Subject> subject;
    @ManyToOne
    private Semester semester;

    public StudentsSubject(){
        this.subject= new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void addSubject(Subject subject){
        this.subject.add(subject);
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
