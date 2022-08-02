package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = MessageHistory.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class MessageHistory {

    public static final String TABLE_NAME = "message_history";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "message")
    private String message;

    public MessageHistory(long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }
}
