package pro.sky.telegrambot.animal_shelter.service.infoOfShelter;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;
import pro.sky.telegrambot.animal_shelter.model.MessageHistory;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserCommunication;
import pro.sky.telegrambot.animal_shelter.repository.MessageHistoryRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserCommunicationRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;

import java.net.MalformedURLException;

@Service
public class InformationOfShelterServiceImpl implements InformationOfShelterService{

    private final UserCommunicationRepository userCommunicationRepository;

    private final UserRepository userRepository;

    private final MessageHistoryRepository messageHistoryRepository;

    public InformationOfShelterServiceImpl(UserCommunicationRepository userCommunicationRepository, UserRepository userRepository, MessageHistoryRepository messageHistoryRepository) {
        this.userCommunicationRepository = userCommunicationRepository;
        this.userRepository = userRepository;
        this.messageHistoryRepository = messageHistoryRepository;
    }

    @Override
    public SendMessage distribution(Update update) {

        Message message = update.message();

        Long chatId = message.chat().id();

        String text = message.text();

        switch (text) {
            case ("Расписание работы"):
                return infoWorkSchedule(update);
            case ("Адрес приюта"):
                return infoOfAddress(update);
//            case ("Как добраться"):
//                return informationOfShelterService.infoOfAddress(update);
            case ("Информация о приюте"):
                return infoShelter(update);
            case ("Правила безопасности"):
                return safetyRegulations(update);
            case ("Оставить данные для связи"):
                saveMessage(chatId, text);
                return new SendMessage(chatId, "Жду твоих данных");
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }
    @Override
    public SendMessage infoWorkSchedule(Update update) {
        return new SendMessage(update.message().chat().id(),
                "•Понедельник с 9:00 до 18:00\n" +
                        "•Вторник с 9:00 до 18:00\n" +
                        "•Среда с 9:00 до 18:00\n" +
                        "•Четверг с 9:00 до 18:00\n" +
                        "•Пятница с 9:00 до 18:00\n" +
                        "•Суббота с 10:00 до 17:00\n" +
                        "•Воскресенье - выходной")
                .replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage infoOfAddress(Update update) {
        return new SendMessage(update.message().chat().id(), "г. Москва, ул. Родниковая, вл26")
                .replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public void Map() throws MalformedURLException {
//        URL url = new URL("https://yandex.ru/maps/?ll=86.307640%2C51.697250&mode=routes&rtext=43.096069%2C133.120554~55.624728%2C37.376078&rtt=auto&ruri=~ymapsbm1%3A%2F%2Forg%3Foid%3D1476817960&z=2");

    }

    @Override
    public SendMessage safetyRegulations(Update update) {
        return new SendMessage(update.message().chat().id(),
                "Посетители должны вести себя спокойно и тихо, поскольку животные не любят шума и суеты!\n" +
                "Бегать и кричать категорически запрещается!")
                .replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage infoShelter(Update update){
        return new SendMessage(update.message().chat().id(),
                "В нашем приюте вы можете взять щенка или взрослую собаку.\n" +
                "Выдаём собак бесплатно, привитых от бешенства, обработанных от паразитов")
                .replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage saveUserData(Update update, String dataUser){
        User user = userRepository.findByChatIdEquals(update.message().chat().id());

        UserCommunication userCommunication = userCommunicationRepository.save(new UserCommunication(user.getId(), dataUser));

        if(userCommunication != null){
            return new SendMessage(update.message().chat().id(), user.getFirstName() + ", твои данные успешно сохранены!").replyMarkup(Keyboards.SHELTER_KEYBOARD);
        }
        return new SendMessage(update.message().chat().id(), "Что то пошло не так, пожалуйста попробуй ввести заново!");

    }

    private void saveMessage (long chatId, String message){
        messageHistoryRepository.save(new MessageHistory(chatId, message));
    }
}
