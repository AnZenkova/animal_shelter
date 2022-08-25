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
import pro.sky.telegrambot.animal_shelter.model.ReportDogPhoto;
import pro.sky.telegrambot.animal_shelter.model.ReportDogText;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.repository.ReportDogPhotoRepository;
import pro.sky.telegrambot.animal_shelter.repository.ReportDogRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;

import java.io.IOException;

@Service
public class ReportDogService {

    private final UserRepository userRepository;
    private final ReportDogRepository reportDogRepository;
    private final ReportDogPhotoRepository reportDogPhotoRepository;

    private final TelegramBot telegramBot;

    public ReportDogService(UserRepository userRepository, ReportDogRepository reportDogRepository, ReportDogPhotoRepository reportDogPhotoRepository, TelegramBot telegramBot) {
        this.userRepository = userRepository;
        this.reportDogRepository = reportDogRepository;
        this.reportDogPhotoRepository = reportDogPhotoRepository;
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

        reportDogRepository.save(new ReportDogText(reports[0], reports[1], reports[2], user.getId()));

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
            ReportDogText reportDogText = reportDogRepository.endReportText(user.getId());

            ReportDogPhoto photo = new ReportDogPhoto(filePath, fileSize, fileContent, reportDogText);

            reportDogPhotoRepository.save(photo);
        } catch (IOException e) {
            new SendMessage(update.message().chat().id(), "Что то пошло не так! Попробуй еще раз!");
        }

        return new SendMessage(update.message().chat().id(), "Сохранил").replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

}
