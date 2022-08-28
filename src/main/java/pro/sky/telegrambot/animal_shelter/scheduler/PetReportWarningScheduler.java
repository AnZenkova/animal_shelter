package pro.sky.telegrambot.animal_shelter.scheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pro.sky.telegrambot.animal_shelter.model.ReportCatText;
import pro.sky.telegrambot.animal_shelter.model.ReportDogText;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.repository.ReportCatTextRepository;
import pro.sky.telegrambot.animal_shelter.repository.ReportDogRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PetReportWarningScheduler {

    private final ReportDogRepository reportDogRepository;
    private final ReportCatTextRepository reportCatTextRepository;
    private final UserRepository userRepository;
    private final TelegramBot telegramBot;

    public PetReportWarningScheduler(ReportDogRepository reportDogRepository, ReportCatTextRepository reportCatTextRepository, UserRepository userRepository, TelegramBot telegramBot) {
        this.reportDogRepository = reportDogRepository;
        this.reportCatTextRepository = reportCatTextRepository;
        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }


    @Scheduled(cron = "0 0 2 * * *")
    public void sendWarningDog() {
        final LocalDateTime nowDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);

        List<Long> users = reportDogRepository.listUsersReport();

        for (Long userId : users) {

            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User с id " + userId + "не найден"));

            ReportDogText endReportDogText = reportDogRepository.endReportText(userId);

            LocalDateTime dateTimeEndReport = endReportDogText.getDateTime();

            sendWarning(nowDateTime, user, dateTimeEndReport);
        }
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void sendWarningCat() {
        final LocalDateTime nowDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);

        List<Long> users = reportCatTextRepository.listUsersReport();

        for (Long userId : users) {

            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User с id " + userId + "не найден"));

            ReportCatText endReportCatText = reportCatTextRepository.endReportText(userId);

            LocalDateTime dateTimeEndReport = endReportCatText.getDateTime();

            sendWarning(nowDateTime, user, dateTimeEndReport);
        }
    }

    private void sendWarning(LocalDateTime nowDateTime, User user, LocalDateTime dateTimeEndReport) {
        if (nowDateTime.minusDays(1).isAfter(dateTimeEndReport)) {
            telegramBot.execute(new SendMessage(user.getChatId(),
                    "Вы не присылали отчет больше суток! Пришлите его как можно скорее!"));

        }

        if (nowDateTime.minusDays(2).isAfter(dateTimeEndReport)) {
            telegramBot.execute(new SendMessage(user.getChatId(),
                    "Вы не присылали отчет более 2 суток! я вызываю волонтера!"));

        }
    }
}
