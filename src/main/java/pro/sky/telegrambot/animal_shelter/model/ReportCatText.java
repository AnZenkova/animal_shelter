package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = ReportCatText.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class ReportCatText {
    public static final String TABLE_NAME = "report_cat_text";

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

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public ReportCatText(String diet, String wellBeing, String changeInBehavior, Long userId, LocalDateTime dateTime) {
        this.diet = diet;
        this.wellBeing = wellBeing;
        this.changeInBehavior = changeInBehavior;
        this.userId = userId;
        this.dateTime = dateTime;
    }
}
