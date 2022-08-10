package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = ReportDogText.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class ReportDogText {

    public static final String TABLE_NAME = "report_dog_text";

    @Id
    @GeneratedValue
    long id;

    @Column(name = "diet")
    private String diet;

    @Column(name = "wellBeing")
    private String wellBeing;

    @Column(name = "changeInBehavior")
    private String changeInBehavior;

    @Column(name = "userId")
    private Long userId;

    public ReportDogText(String diet, String wellBeing, String changeInBehavior, Long userId) {
        this.diet = diet;
        this.wellBeing = wellBeing;
        this.changeInBehavior = changeInBehavior;
        this.userId = userId;
    }

}
