package pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;

@Service
public class InformationForPotentialOwnerServiceImpl implements InformationForPotentialOwnerService {

    @Override
    public SendMessage distribution(Update update) {

        Message message = update.message();

        Long chatId = message.chat().id();

        String text = message.text();

        switch (text) {
            case ("Правила знакомства с собакой"):
                return getRulesForGettingToKnowAnAnimal(update);
            case ("Список документов"):
                return getListOfDocuments(update);
            case ("О перевозке"):
                return getListOfRecommendationsForTransportation(update);
            case ("Об-во дома для щенка"):
                return getRecommendationsAboutHouseForPuppy(update);
            case ("Об-во для взрослой собаки"):
                return getRecommendationsAboutHouseForDog(update);
            case ("Об-во для собаки с ограниченными возможностями"):
                return getRecommendationsAboutHouseForDisabledDog(update);
            case ("Кинолог. С чего начать"):
                return getInitialCommunicationWithDog(update);
            case ("Список кинологов"):
                return getListOfCynologist(update);
            case ("Причины об отказе"):
                return getReasonsForRefusal(update);
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getRulesForGettingToKnowAnAnimal(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Подходите к собаке медленно.\n" +
                        "2. Медленно протяни руку.\n" +
                        "3. Лучше не смотреть собаке в глаза.\n" +
                        "4. Общайтесь с собакой, если она вас примет.\n" +
                        "5. Сделайте встречу короткой.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getListOfDocuments(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Паспорт.\n" +
                        "2. Свидетельство о собственност жилого помещения / договор аренды жилья.\n" +
                        "3. Информация о графике работы.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getListOfRecommendationsForTransportation(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Приобретите переноску.\n" +
                        "2. Перед поездкой дайте собаке вдоволь нагуляться.\n" +
                        "3. Перед поездкой не кормить.\n" +
                        "4. Сделайте место собаки комфортным, постелив одеяло или лежанку."
        ).replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getRecommendationsAboutHouseForPuppy(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Выбрать правильное место(малыш должен быть защищен от сквозняков и мог спокойно спать).\n" +
                        "2. В первые дни после появления в доме собаки необходимо постараться ограничить количество людей, которые будут находиться рядом со щенком.\n" +
                        "3. Следует объяснить детям, что с ним не следует обращаться как с игрушкой.\n" +
                        "4. На лежанку щенка можно положить старую меховую шапку или игрушки.\n" +
                        "5. Оборудовать место для мисок с водой и пищей.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getRecommendationsAboutHouseForDog(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Не держать собаку в вальере или на привязи.\n" +
                        "2. Подготовить место для собаки. Спальное место должно соответствовать её размерам.\n" +
                        "3. Оборудовать место для мисок с водой и пищей.\n" +
                        "4. Подобрать полезные акссесуары(расческа, игрушки, ошейник, поводок и т.д.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getRecommendationsAboutHouseForDisabledDog(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Не держать собаку в вальере или на привязи.\n" +
                        "2. Подготовить место для собаки. Спальное место должно соответствовать её размерам.\n" +
                        "3. Оборудовать место для мисок с водой и пищей.\n" +
                        "4. Подобрать полезные акссесуары(расческа, игрушки, ошейник, поводок и т.д.\n" +
                        "5. Предоставить свободный доступ к мискам и спальному месту.\n" +
                        "6. Не перегружать собаку физическими нагрузками.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getInitialCommunicationWithDog(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Разговаривайте со своей собакой.\n" +
                        "2. Следите за своими эмоциями.\n" +
                        "3. Наблюдайте за выражением внутреннего состояния собаки и приводите его в норму.\n" +
                        "4. Учитывайте желания собаки.\n" +
                        "5. Предоставьте собаке выбор.\n" +
                        "6. Введите сигналы, которые вам помогут строить контакт.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getListOfCynologist(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Иванов Иван Иванович +79568542358\n" +
                        "2. Петров Петр Петрович +79525489564")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }

    @Override
    public SendMessage getReasonsForRefusal(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Берут животное на подарок ребенку или члену семьи (бывают исключения);\n" +
                        "2. Наличие маленьких детей и отсутствие опыта содержания собак и одиноким молодым мамам тоже откажем;\n" +
                        "3. Съемное жилье;\n" +
                        "4. Если люди раздраженно или грубо отвечают на вопросы;\n" +
                        "5. Если люди, еще не взяв собаку, просят у нас и поводок и ошейник и цепь и будку, так как у них нет денег на это;\n" +
                        "6. Если у человека было много животных и человек не скажет, причины их исчезновения;\n" +
                        "7. Лояльное отношение к самовыгулу собак;\n" +
                        "8. Если люди передаривали своих бывших животных по просьбе друзей/родственников;\n" +
                        "9. Если люди с легкостью и не задумываясь отвечают на все наши вопросы и согласны на все наши условия, при этом не задавая своих вопросов (чаще это молодые люди в возрасте 18 + лет);\n" +
                        "10. Если люди не искренне и не уверенно отвечают на вопросы, уходят от ответов;\n" +
                        "11. Если хотят взять собаку для охраны предприятия/организации;\n" +
                        "12. Если в общении не найдем компромисс по питанию животного;\n" +
                        "13. Если люди отказываются показать условия содержания собаки (касается только если собаку берут в частный дом для содержания в будке или вольере);")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER);
    }
}