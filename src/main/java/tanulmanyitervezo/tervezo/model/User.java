package tanulmanyitervezo.tervezo.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false )
    private int id;
    @Column(unique = true)
    private String email;
    private  String password;
    @Column(nullable= false,updatable = false )
    private String roles;
    private String name;
    private Long birthDate;
    private String neptun;

    public User(){}

    public User(   String name, String email, String password, String neptun, Long birthDate, String roles){
        this.name=name;
        this.email=email;
        this.password=password;
        this.neptun=neptun;
        this.birthDate=birthDate;
        this.roles=roles;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userName) {
        this.email = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNeptun() {
        return neptun;
    }

    public void setNeptun(String neptun) {
        this.neptun = neptun;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
