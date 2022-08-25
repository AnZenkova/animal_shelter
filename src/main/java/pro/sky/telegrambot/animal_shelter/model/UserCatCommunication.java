package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = UserCatCommunication.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class UserCatCommunication {

    public static final String TABLE_NAME = "user_cat_communication_data";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "communication_data")
    private String communicationData;

    public UserCatCommunication(long userId, String communicationData) {
        this.userId = userId;
        this.communicationData = communicationData;
    }
}
