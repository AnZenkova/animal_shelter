package pro.sky.telegrambot.animal_shelter.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * Модель пользователя, который хочет взять собаку
 */
@Entity
@Table(name = "users_dogs")
public class UserDog {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public UserDog() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDog userDog = (UserDog) o;
        return Objects.equals(id, userDog.id) && Objects.equals(username, userDog.username) && Objects.equals(firstName, userDog.firstName) && Objects.equals(lastName, userDog.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserDog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
