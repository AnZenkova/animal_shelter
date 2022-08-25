package pro.sky.telegrambot.animal_shelter.service.report;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;
import pro.sky.telegrambot.animal_shelter.model.*;
import pro.sky.telegrambot.animal_shelter.repository.ReportCatPhotoRepository;
import pro.sky.telegrambot.animal_shelter.repository.ReportCatTextRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;

import java.io.IOException;

@Service
public class ReportCatService {
    private final UserRepository userRepository;
    private final ReportCatTextRepository reportCatTextRepository;
    private final ReportCatPhotoRepository reportCatPhotoRepository;

    private final TelegramBot telegramBot;

    public ReportCatService(UserRepository userRepository,
                            ReportCatTextRepository reportCatTextRepository,
                            ReportCatPhotoRepository reportCatPhotoRepository,
                            TelegramBot telegramBot) {
        this.userRepository = userRepository;
        this.reportCatTextRepository = reportCatTextRepository;
        this.reportCatPhotoRepository = reportCatPhotoRepository;
        this.telegramBot = telegramBot;
    }


    public SendMessage distribution(Update update){

        Message message = update.message();

        Long chatId = message.chat().id();

        String text = message.text();

        if(text.equals("Форма отчета")){
            return new SendMessage(chatId, "Я жду от тебя отчет в следующем формате:\n" +
                    "Рацион животного! Общее самочувствие и привыкание к новому месту!\n" +
                    "Изменение в поведении: отказ от старых привычек, приобретение новых!\n" +
                    "Пожалуйста опиши всё максимально подробно и не забудь разделить эти три пункта восклицательным знаком, что бы я мог правильно тебя понять.\n" +
                    "И в следующем сообщении я жду от тебя фото животного").replyMarkup(Keyboards.REPORT_KEYBOARD);
        }

        String [] reports = text.split("!");

        User user = userRepository.findByChatIdEquals(chatId);

        reportCatTextRepository.save(new ReportCatText(reports[0], reports[1], reports[2], user.getId()));

        return new SendMessage(chatId, "Отчет успешно сохранён! Жду от тебя фото!");
    }

    public SendMessage savePhotoReportDog (Update update){

        GetFile request = new GetFile(update.message().photo()[2].fileId());
        GetFileResponse getFileResponse = telegramBot.execute(request);

        File file = getFileResponse.file();
        try {
            byte[] fileContent = telegramBot.getFileContent(file);
            String filePath = file.filePath();
            long fileSize = file.fileSize();
            User user = userRepository.findByChatIdEquals(update.message().chat().id());
            ReportCatText reportCatText = reportCatTextRepository.endReportText(user.getId());

            ReportCatPhoto photo = new ReportCatPhoto(filePath, fileSize, fileContent, reportCatText);

            reportCatPhotoRepository.save(photo);
        } catch (IOException e) {
            new SendMessage(update.message().chat().id(), "Что то пошло не так! Попробуй еще раз!");
        }

        return new SendMessage(update.message().chat().id(), "Сохранил").replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

}
