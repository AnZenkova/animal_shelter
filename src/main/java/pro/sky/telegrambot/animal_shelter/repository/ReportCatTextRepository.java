package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.animal_shelter.model.ReportCatText;

import java.util.List;

public interface ReportCatTextRepository extends JpaRepository<ReportCatText, Long> {

    @Query(value = "SELECT * FROM report_cat_text WHERE user_id = :userId ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ReportCatText endReportText(@Param("userId") long userId);

    @Query(value = "SELECT DISTINCT user_id from report_dog_text", nativeQuery = true)
    List<Long> listUsersReport();
}
