package pro.sky.telegrambot.animal_shelter.service.infoOfShelter;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;
import pro.sky.telegrambot.animal_shelter.model.MessageHistory;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserCatCommunication;
import pro.sky.telegrambot.animal_shelter.repository.MessageHistoryRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserCatCommunicationRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserCommunicationRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;

@Service
public class InformationOfShelterServiceCat extends InformationOfShelterServiceImpl {


    private final UserRepository userRepository;
    private final MessageHistoryRepository messageHistoryRepository;
    private final UserCatCommunicationRepository userCatCommunicationRepository;

    public InformationOfShelterServiceCat(UserCommunicationRepository userCommunicationRepository, UserRepository userRepository, MessageHistoryRepository messageHistoryRepository, UserRepository userRepository1, MessageHistoryRepository messageHistoryRepository1, UserCatCommunicationRepository userCatCommunicationRepository) {
        super(userCommunicationRepository, userRepository, messageHistoryRepository);
        this.userRepository = userRepository1;
        this.messageHistoryRepository = messageHistoryRepository1;
        this.userCatCommunicationRepository = userCatCommunicationRepository;
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
            case ("Как добраться"):
                return map(update);
            case ("Информация о приюте"):
                return infoShelter(update);
            case ("Правила безопасности"):
                return safetyRegulations(update);
            case ("Оставить данные для связи"):
                saveMessage(chatId, text);
                return new SendMessage(chatId, "Жду твоих данных");
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage infoWorkSchedule(Update update) {
        return super.infoWorkSchedule(update);
    }

    @Override
    public SendMessage infoOfAddress(Update update) {
        return super.infoOfAddress(update);
    }

    @Override
    public SendMessage map(Update update) {
        return super.map(update);
    }

    @Override
    public SendMessage safetyRegulations(Update update) {
        return super.safetyRegulations(update);
    }

    @Override
    public SendMessage infoShelter(Update update) {
        return new SendMessage(update.message().chat().id(),
                "В нашем приюте вы можете взять котенка или взрослую кошку.\n" +
                        "Выдаём кошек бесплатно, привитых, обработанных от паразитов")
                .replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage saveUserData(Update update, String dataUser) {
        User user = userRepository.findByChatIdEquals(update.message().chat().id());

        userCatCommunicationRepository.save(new UserCatCommunication(user.getId(), dataUser));

        return new SendMessage(update.message().chat().id(), user.getFirstName() + ", твои данные успешно сохранены!").replyMarkup(Keyboards.SHELTER_KEYBOARD_CAT);

    }

    private void saveMessage(long chatId, String message) {
        messageHistoryRepository.save(new MessageHistory(chatId, message));
    }
}
