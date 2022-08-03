package pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class InformationForPotentialOwnerServiceImpl implements InformationForPotentialOwnerService{

    public SendMessage getRulesForGettingToKnowAnAnimal(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Подходите к собаке медленно.\n" +
                        "2. Медленно протяни руку.\n" +
                        "3. Лучше не смотреть собаке в глаза.\n" +
                        "4. Общайтесь с собакой, если она вас примет.\n" +
                        "5. Сделайте встречу короткой.");
    }

    public SendMessage getListOfDocuments(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Паспорт.\n" +
                        "2. Свидетельство о собственност жилого помещения / договор аренды жилья.\n" +
                        "3. Информация о графике работы.");
    }

    public SendMessage getListOfRecommendationsForTransportation(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Приобретите переноску.\n" +
                        "2. Перед поездкой дайте собаке вдоволь нагуляться.\n" +
                        "3. Перед поездкой не кормить.\n" +
                        "4. Сделайте место собаки комфортным, постелив одеяло или лежанку."
        );
    }

    public SendMessage getRecommendationsAboutHouseForPuppy(Update update) {
        return new SendMessage(update.message().chat().id(),
                "1. Выбрать правильное место(малыш должен быть защищен от сквозняков и мог спокойно спать).\n" +
                        "2. В первые дни после появления в доме собаки необходимо постараться ограничить количество людей, которые будут находиться рядом со щенком.\n" +
                        "3. Следует объяснить детям, что с ним не следует обращаться как с игрушкой.\n" +
                        "4. На лежанку щенка можно положить старую меховую шапку или игрушки.\n" +
                        "5. Оборудовать место для мисок с водой и пищей.");
    }
}
