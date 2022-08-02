package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = UserCommunication.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class UserCommunication {

    public static final String TABLE_NAME = "user_communication_data";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "communication_data")
    private String communicationData;

    public UserCommunication(long userId, String communicationData) {
        this.userId = userId;
        this.communicationData = communicationData;
    }

}
