package fr.laerce.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Projet thyme-security
 * Pour LAERCE SAS
 * <p>
 * Créé le  21/03/2017.
 *
 * @author fred
 */
//@Entity
//@Table(name="user")
@Entity(name = "User")
@Table(name="user", schema = "public")
public class User {
    //@GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)//#
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)//#
    private String name;
    @Basic
    @Column(name = "password", nullable = false, length = 120)//#
    private String password;
//    private boolean active;//#
    @Basic
    @Column(name = "mail", nullable = false, length = 100)
    private String mail;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore//#
    @JoinTable(name="user_group",
            joinColumns =@JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id"))//#
    private Set<Groups> groups;

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }//#

    public Set<Groups> getGroups() {
        return groups;
    }//# //SET ?????? pas GET ????

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*@Basic
    @Column(name = "ACTIVE", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }*/         //#

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
//                active == user.active &&//#
/*                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(mail, user.mail);*/      //#

                Objects.equals(getName(), user.getName()) &&//#
//                Objects.equals(getGivenname(), user.getGivenname()) &&
//                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&//#
                Objects.equals(getMail(), user.getMail());//#
    }

    @Override
    public int hashCode() {
//        return Objects.hash(id, name, password, active, mail);
        return Objects.hash(getId(), getName(), /*getGivenname(),*//* getLogin(),*/ getPassword(), getMail());//#
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", givenname='" + givenname + '\'' +
//                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}



