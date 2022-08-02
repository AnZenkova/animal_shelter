package pro.sky.telegrambot.animal_shelter.service.messageHandler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;
import pro.sky.telegrambot.animal_shelter.model.MessageHistory;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserMessageCounter;
import pro.sky.telegrambot.animal_shelter.repository.MessageHistoryRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserMessageCounterRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;
import pro.sky.telegrambot.animal_shelter.service.commands.CommandsService;
import pro.sky.telegrambot.animal_shelter.service.infoOfShelter.InformationOfShelterService;

import java.time.LocalDateTime;

@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {
    private final CommandsService commandsService;
    private final UserRepository userRepository;
    private final UserMessageCounterRepository userMessageCounterRepository;
    private final InformationOfShelterService informationOfShelterService;

    private final MessageHistoryRepository messageHistoryRepository;

    public MessageHandlerServiceImpl(
            CommandsService commandsService,
            UserRepository userRepository,
            UserMessageCounterRepository userMessageCounterRepository,
            InformationOfShelterService informationOfShelterService,
            MessageHistoryRepository messageHistoryRepository) {
        this.commandsService = commandsService;
        this.userRepository = userRepository;
        this.userMessageCounterRepository = userMessageCounterRepository;
        this.informationOfShelterService = informationOfShelterService;
        this.messageHistoryRepository = messageHistoryRepository;
    }

    public SendMessage handle(Update update) {
        Message message = update.message();
        User user = getUser(message);
        increaseUserMessageCounter(user.getId());


        Long chatId = message.chat().id();

        String text = message.text();

        switch (text) {
            case "/start":
                return commandsService.start(update);
            case "О приюте":
                saveMessage(chatId, text);
                return commandsService.aboutShelter(update);
            case ("Как взять собаку из приюта"):
                return commandsService.howGetDogFromShelter(update);
            case ("Прислать отчет о питомце"):
                return commandsService.petReport(update);
            case ("Позвать волонтёра"):
                return commandsService.volunteerCall(update);
            case ("Расписание работы"):
                return informationOfShelterService.infoWorkSchedule(update);
            case ("Адрес приюта"):
                return informationOfShelterService.infoOfAddress(update);
//            case ("Как добраться"):
//                return informationOfShelterService.infoOfAddress(update);
            case ("Информация о приюте"):
                return informationOfShelterService.infoShelter(update);
            case ("Правила безопасности"):
                return informationOfShelterService.safetyRegulations(update);
            case ("Оставить данные для связи"):
                saveMessage(chatId, text);
                return new SendMessage(message.chat().id(), "Жду твоих данных");
        }

        if(messageHistoryRepository.getEndMessage().equals("Оставить данные для связи")){
            saveMessage(chatId, text);
            return informationOfShelterService.saveUserData(update, text);
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

    /**
     * Поиск (или создание) пользователя в БД
     *
     * @param message Объект сообщения чата
     * @return User
     */

    private User getUser(Message message) {

        User user = userRepository.findByChatIdEquals(message.chat().id());

        if (user != null) {
            return user;
        }

        user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setTelegramId(message.from().id());
        user.setChatId(message.chat().id());
        user.setUsername(message.from().username());
        user.setFirstName(message.from().firstName());
        user.setLastName(message.from().lastName());
        user.setBot(message.from().isBot());

        return userRepository.save(user);
    }

    /**
     * Поиск (или создание) счётчика сообщений пользователя в БД
     * Увеличение его на единицу
     *
     * @param userId Идентификатор пользователя
     */
    private void increaseUserMessageCounter(Long userId) {
        UserMessageCounter userMessageCounter = userMessageCounterRepository.findByUserIdEquals(userId);

        if (userMessageCounter == null) {
            userMessageCounter = new UserMessageCounter();
            userMessageCounter.setUserId(userId);
            userMessageCounter.setCounter(0);
        }

        userMessageCounter.increaseCounter();

        userMessageCounterRepository.save(userMessageCounter);
    }

    private void saveMessage (long chatId, String message){
        messageHistoryRepository.save(new MessageHistory(chatId, message));
    }
}
