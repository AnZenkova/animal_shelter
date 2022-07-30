package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Счётчик сообщений пользователя
 */
@Entity
@Table(name = UserMessageCounter.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class UserMessageCounter {

    public static final String TABLE_NAME = "user_message_counter";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "counter")
    private long counter;

    public void increaseCounter() {
        ++counter;
    }

}
