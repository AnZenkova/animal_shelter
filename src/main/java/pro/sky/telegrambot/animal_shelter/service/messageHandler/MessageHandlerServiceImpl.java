package pro.sky.telegrambot.animal_shelter.service.messageHandler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserMessageCounter;
import pro.sky.telegrambot.animal_shelter.repository.UserMessageCounterRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;
import pro.sky.telegrambot.animal_shelter.service.commands.CommandsService;

import java.time.LocalDateTime;

@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {
    private final CommandsService commandsService;
    private final UserRepository userRepository;
    private final UserMessageCounterRepository userMessageCounterRepository;

    public MessageHandlerServiceImpl(
            CommandsService commandsService,
            UserRepository userRepository,
            UserMessageCounterRepository userMessageCounterRepository
    ) {
        this.commandsService = commandsService;
        this.userRepository = userRepository;
        this.userMessageCounterRepository = userMessageCounterRepository;
    }

    public SendMessage handle(Update update) {
        Message message = update.message();
        User user = getUser(message);
        increaseUserMessageCounter(user.getId());

        Long chatId = message.chat().id();

        String text = message.text();

        switch (text){
            case "/start":
                return commandsService.start(update);
            case "О приюте":
                return commandsService.aboutShelter(update);
            case ("Как взять собаку из приюта"):
                return commandsService.howGetDogFromShelter(update);
            case ("Прислать отчет о питомце"):
                return commandsService.petReport(update);
            case ("Позвать волонтёра"):
                return commandsService.volunteerCall(update);
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

}
