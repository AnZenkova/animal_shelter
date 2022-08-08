package pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;

@Service
public class InformationForPotentialOwnerCatServiceImpl implements InformationForPotentialOwnerCatService {
    @Override
    public SendMessage distribution(Update update) {

        Message message = update.message();

        Long chatId = message.chat().id();

        String text = message.text();

        switch (text) {
            case ("Правила знакомства с кошкой"):
                return getRulesForGettingToKnowAnAnimal(update);
            case ("Список документов"):
                return getListOfDocuments(update);
            case ("О перевозке"):
                return getListOfRecommendationsForTransportation(update);
            case ("Об-во дома для котенка"):
                return getRecommendationsAboutHouseForKitty(update);
            case ("Об-во для взрослой кошки"):
                return getRecommendationsAboutHouseForCat(update);
            case ("Об-во для кошки с ограниченными возможностями"):
                return getRecommendationsAboutHouseForDisabledCat(update);
            case ("Причины об отказе"):
                return getReasonsForRefusal(update);
        }

        return new SendMessage(chatId, "Выбери, пожалуйста, один из пунктов!")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getReasonsForRefusal(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Берут животное на подарок ребенку или члену семьи (бывают исключения);\n" +
                        "2. Наличие маленьких детей и отсутствие опыта содержания кошек и одиноким молодым мамам тоже откажем;\n" +
                        "3. Съемное жилье;\n" +
                        "4. Если люди раздраженно или грубо отвечают на вопросы;\n" +
                        "5. Если у человека было много животных и человек не скажет, причины их исчезновения;\n" +
                        "6. Лояльное отношение к самовыгулу кошек;\n" +
                        "7. Если люди передаривали своих бывших животных по просьбе друзей/родственников;\n" +
                        "8. Если люди с легкостью и не задумываясь отвечают на все наши вопросы и согласны на все наши условия, при этом не задавая своих вопросов (чаще это молодые люди в возрасте 18 + лет);\n" +
                        "9. Если люди не искренне и не уверенно отвечают на вопросы, уходят от ответов;\n" +
                        "10. Если в общении не найдем компромисс по питанию животного;\n" +
                        "13. Если люди отказываются показать условия содержания кошки);")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getRulesForGettingToKnowAnAnimal(Update update) {
        return new SendMessage(update.message().chat().id(),
                "Правила знакомства с кошкой...")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getListOfDocuments(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Паспорт.\n" +
                        "2. Свидетельство о собственности жилого помещения / договор аренды жилья.\n" +
                        "3. Информация о графике работы.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getListOfRecommendationsForTransportation(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Приобретите переноску.\n" +
                        "2. Перед поездкой дайте кошке вдоволь нагуляться.\n" +
                        "3. Перед поездкой не кормить.\n" +
                        "4. Сделайте место кошки комфортным, постелив одеяло или лежанку."
        ).replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getRecommendationsAboutHouseForKitty(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Выбрать правильное место(малыш должен быть защищен от сквозняков и мог спокойно спать).\n" +
                        "2. В первые дни после появления в доме кошки необходимо постараться ограничить количество людей, которые будут находиться рядом со котенком.\n" +
                        "3. Следует объяснить детям, что с ним не следует обращаться как с игрушкой.\n" +
                        "4. На лежанку котенка можно положить старую меховую шапку или игрушки.\n" +
                        "5. Оборудовать место для мисок с водой и пищей.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getRecommendationsAboutHouseForCat(Update update) {
        return new SendMessage(update.message().chat().id(),
                        "1. Подготовить место для кошки. Спальное место должно соответствовать её размерам.\n" +
                        "2. Оборудовать место для мисок с водой и пищей.\n" +
                        "3. Подобрать полезные акссесуары(расческа, игрушки и тд.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage getRecommendationsAboutHouseForDisabledCat(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Подготовить место для кошки. Спальное место должно соответствовать её размерам.\n" +
                        "2. Оборудовать место для мисок с водой и пищей.\n" +
                        "3. Подобрать полезные акссесуары(расческа, игрушки и тд." +
                        "4. Предоставить свободный доступ к мискам и спальному месту.")
                .replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }
}
