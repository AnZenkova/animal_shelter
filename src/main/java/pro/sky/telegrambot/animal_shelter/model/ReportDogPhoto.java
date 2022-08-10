package pro.sky.telegrambot.animal_shelter.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = ReportDogPhoto.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class ReportDogPhoto {

    public static final String TABLE_NAME = "report_dog_photo";

    @Id
    @GeneratedValue
    long id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "data")
    private byte[] data;

    @OneToOne
    private ReportDogText reportDogText;

    public ReportDogPhoto(String filePath, long fileSize, byte[] data, ReportDogText reportDogText) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.data = data;
        this.reportDogText = reportDogText;
    }
}
