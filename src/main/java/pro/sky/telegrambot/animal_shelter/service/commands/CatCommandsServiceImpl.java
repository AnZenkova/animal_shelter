package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;

@Service
public class CatCommandsServiceImpl implements CommandsService {
    private final Logger logger = LoggerFactory.getLogger(CatCommandsServiceImpl.class);

    @Override
    public SendMessage start(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        String message = "Привет, " + update.message().from().firstName() +
                "\nДобро пожаловать в приют для кошек!" +
                "\nВыбери, пожалуйста, один из пунктов!";

        return new SendMessage(update.message().chat().id(), message)
                .replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage aboutShelter(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        String message = "Привет, " + update.message().from().firstName() +
                "!\nЯ могу кое-что рассказать тебе о нашем приюте! \nВыбери что бы ты хотел узнать";

        return new SendMessage(update.message().chat().id(), message).replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage howGetDogFromShelter(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        String message = "Привет, " + update.message().from().firstName() +
                "!\nЯ могу кое-что рассказать тебе о том как взять кошку из приюта! \nВыбери что бы ты хотел узнать";

        return new SendMessage(update.message().chat().id(), message).replyMarkup(Keyboards.ABOUT_HOW_TO_TAKE_CAT_FROM_SHELTER);
    }

    @Override
    public SendMessage petReport(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        String message = "Привет, " + update.message().from().firstName() +
                "!\nЯ жду от тебя отчет в следующем формате:" +
                "\nРацион животного! Общее самочувствие и привыкание к новому месту!" +
                "\nИзменение в поведении: отказ от старых привычек, приобретение новых!" +
                "\nПожалуйста опиши всё максимально подробно и не забудь разделить эти три пункта восклицательным знаком, что бы я мог правильно тебя понять." +
                "\nИ в следующем сообщении я жду от тебя фото животного";

        return new SendMessage(update.message().chat().id(), message).replyMarkup(Keyboards.REPORT_KEYBOARD);
    }

    @Override
    public SendMessage volunteerCall(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        String message = "Привет, " + update.message().from().username() +
                "!\nВолонтёр ответит тебе в течении нескольких минут!\nПриносим свои извинения за ожидание!" +
                "\nМожет быть я чем то ещё могу тебе помочь!?";

        return new SendMessage(update.message().chat().id(), message).replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage back(Update update) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return new SendMessage(update.message().chat().id(), "Вы вернулись назад")
                .replyMarkup(Keyboards.SHELTER_KEYBOARD_CAT);
    }
}
