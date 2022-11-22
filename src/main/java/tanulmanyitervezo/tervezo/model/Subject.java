package tanulmanyitervezo.tervezo.model;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private long id;
    private String name;
    private String description;
    private int kredit;
//    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
//    private List<Period> periods = new ArrayList<Period>();
//
//    public List<Period> getPeriods() {
//        return periods;
//    }
//
//    public void setPeriods(List<Period> periods) {
//        this.periods = periods;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
}