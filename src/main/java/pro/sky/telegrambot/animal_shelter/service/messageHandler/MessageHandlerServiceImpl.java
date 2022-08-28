package pro.sky.telegrambot.animal_shelter.service.messageHandler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;
import pro.sky.telegrambot.animal_shelter.model.MessageHistory;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserMessageCounter;
import pro.sky.telegrambot.animal_shelter.repository.MessageHistoryRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserMessageCounterRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;
import pro.sky.telegrambot.animal_shelter.service.UserCat.UserCatService;
import pro.sky.telegrambot.animal_shelter.service.commands.CatCommandsServiceImpl;
import pro.sky.telegrambot.animal_shelter.service.commands.CommandsServiceImpl;
import pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner.InformationForPotentialOwnerCatServiceImpl;
import pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner.InformationForPotentialOwnerService;
import pro.sky.telegrambot.animal_shelter.service.infoOfShelter.InformationOfShelterServiceCat;
import pro.sky.telegrambot.animal_shelter.service.infoOfShelter.InformationOfShelterServiceImpl;
import pro.sky.telegrambot.animal_shelter.service.report.ReportCatService;
import pro.sky.telegrambot.animal_shelter.service.report.ReportDogService;
import pro.sky.telegrambot.animal_shelter.service.userDog.UserDogService;

import java.time.LocalDateTime;

@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {

    private final Logger logger = LoggerFactory.getLogger(MessageHandlerServiceImpl.class);
    private final CommandsServiceImpl commandsService;
    private final UserRepository userRepository;
    private final UserMessageCounterRepository userMessageCounterRepository;
    private final InformationOfShelterServiceImpl informationOfShelterService;
    private final MessageHistoryRepository messageHistoryRepository;
    private final InformationForPotentialOwnerService informationForPotentialOwnerService;
    private final UserDogService userDogService;
    private final UserCatService userCatService;
    private final CatCommandsServiceImpl catCommandsService;
    private final InformationOfShelterServiceCat informationOfShelterServiceCat;
    private final InformationForPotentialOwnerCatServiceImpl informationForPotentialOwnerCatService;
    private final ReportDogService reportDogService;
    private final ReportCatService reportCatService;

    public MessageHandlerServiceImpl(
            CommandsServiceImpl commandsService, UserRepository userRepository,
            UserMessageCounterRepository userMessageCounterRepository,
            @Qualifier("informationOfShelterServiceImpl") InformationOfShelterServiceImpl informationOfShelterService, MessageHistoryRepository messageHistoryRepository,
            InformationForPotentialOwnerService informationForPotentialOwnerService,
            UserDogService userDogService, UserCatService userCatService,
            CatCommandsServiceImpl catCommandsService,
            InformationOfShelterServiceCat informationOfShelterServiceCat, InformationForPotentialOwnerCatServiceImpl informationForPotentialOwnerCatService,
            ReportDogService reportDogService,
            ReportCatService reportCatService) {
        this.commandsService = commandsService;
        this.userRepository = userRepository;
        this.userMessageCounterRepository = userMessageCounterRepository;
        this.informationOfShelterService = informationOfShelterService;
        this.messageHistoryRepository = messageHistoryRepository;
        this.informationForPotentialOwnerService = informationForPotentialOwnerService;
        this.userDogService = userDogService;
        this.userCatService = userCatService;
        this.catCommandsService = catCommandsService;
        this.informationOfShelterServiceCat = informationOfShelterServiceCat;
        this.informationForPotentialOwnerCatService = informationForPotentialOwnerCatService;
        this.reportDogService = reportDogService;
        this.reportCatService = reportCatService;
    }

    @Override
    public SendMessage choiceOfShelter(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        if (update.message().photo() != null &&
                userRepository.findByChatIdEquals(update.message().chat().id()).getPet().equals("dog")) {
            return reportDogService.savePhotoReportDog(update);
        }
        if (update.message().photo() != null &&
                userRepository.findByChatIdEquals(update.message().chat().id()).getPet().equals("cat")) {
            return reportCatService.savePhotoReportDog(update);
        }
        if (update.message() != null && update.message().text().equals("Приют для собак")
                || update.message().text().equals("Приют для кошек")) {
            Message message = update.message();
            User user = getUser(message);
            if (update.message().text().equals("Приют для собак")) {
                user.setPet("dog");
                userRepository.save(user);
            } else {
                user.setPet("cat");
                userRepository.save(user);
            }
            increaseUserMessageCounter(user.getId());
        }

        if (update.message() != null && update.message().text().equals("Другой приют")) {
            return new SendMessage(update.message().chat().id(),
                    "Выбери пожалуйста приют").replyMarkup(Keyboards.CHOICE_OF_SHELTER_KEYBOARD);
        }

        if (update.message().text().equals("Приют для собак") ||
                userRepository.findByChatIdEquals(update.message().chat().id()) != null &&
                        userRepository.findByChatIdEquals(update.message().chat().id()).getPet().equals("dog")
        ) {
            return handle(update);
        }

        if (update.message().text().equals("Приют для кошек") ||
                userRepository.findByChatIdEquals(update.message().chat().id()) != null &&
                        userRepository.findByChatIdEquals(update.message().chat().id()).getPet().equals("cat")
        ) {
            return handleCAt(update);
        }

        return new SendMessage(update.message().chat().id(),
                "Привет" + update.message().from().firstName() +
                        "\nВыбери пожалуйста приют").replyMarkup(Keyboards.CHOICE_OF_SHELTER_KEYBOARD);
    }

    @SneakyThrows
    public SendMessage handle(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
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
                userDogService.createUserDog(user);
                saveMessage(chatId, text);
                return commandsService.howGetDogFromShelter(update);
            case ("Прислать отчет о питомце"):
                saveMessage(chatId, text);
                return commandsService.petReport(update);
            case ("Позвать волонтёра"):
                return commandsService.volunteerCall(update);
            case ("Оставить данные для связи"):
                saveMessage(chatId, text);
                return new SendMessage(chatId, "Жду твоих данных");
            case ("Назад"):
                saveMessage(chatId, text);
                return commandsService.back(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("О приюте")) {
            return informationOfShelterService.distribution(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("Как взять собаку из приюта")) {
            return informationForPotentialOwnerService.distribution(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("Прислать отчет о питомце")) {
            return reportDogService.distribution(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("Оставить данные для связи")) {
            saveMessage(chatId, text);
            return informationOfShelterService.saveUserData(update, text);
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

    @SneakyThrows
    public SendMessage handleCAt(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        Message message = update.message();
        User user = getUser(message);
        increaseUserMessageCounter(user.getId());

        Long chatId = message.chat().id();

        String text = message.text();

        switch (text) {
            case "/start":
                return catCommandsService.start(update);
            case "О приюте":
                saveMessage(chatId, text);
                return catCommandsService.aboutShelter(update);
            case ("Как взять кошку из приюта"):
                userCatService.createUserCat(user);
                saveMessage(chatId, text);
                return catCommandsService.howGetDogFromShelter(update);
            case ("Прислать отчет о питомце"):
                saveMessage(chatId, text);
                return catCommandsService.petReport(update);
            case ("Позвать волонтёра"):
                return catCommandsService.volunteerCall(update);
            case ("Оставить данные для связи"):
                saveMessage(chatId, text);
                return new SendMessage(chatId, "Жду твоих данных");
            case ("Назад"):
                saveMessage(chatId, text);
                return catCommandsService.back(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("О приюте")) {
            return informationOfShelterServiceCat.distribution(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("Как взять кошку из приюта")) {
            return informationForPotentialOwnerCatService.distribution(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("Прислать отчет о питомце")) {
            return reportCatService.distribution(update);
        }

        if (messageHistoryRepository.getEndMessage().equals("Оставить данные для связи")) {
            saveMessage(chatId, text);
            return informationOfShelterServiceCat.saveUserData(update, text);
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.SHELTER_KEYBOARD_CAT);
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
        if (message.text().equals("Приют для собак")) {
            user.setPet("dog");
        } else {
            user.setPet("cat");
        }


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

    /**
     * Сохранение сообщения в историю сообщений
     *
     * @param chatId  Идентификатор чата
     * @param message Сообщение для сохранения в историю
     */
    private void saveMessage(long chatId, String message) {
        messageHistoryRepository.save(new MessageHistory(chatId, message));
    }
}
