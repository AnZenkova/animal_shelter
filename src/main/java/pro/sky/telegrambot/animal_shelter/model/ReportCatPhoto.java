package pro.sky.telegrambot.animal_shelter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = ReportCatPhoto.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class ReportCatPhoto {
    public static final String TABLE_NAME = "report_cat_photo";

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
    private ReportCatText reportCatText;

    public ReportCatPhoto(String filePath, long fileSize, byte[] data, ReportCatText reportCatText) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.data = data;
        this.reportCatText = reportCatText;
    }
}
