package pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface InformationForPotentialOwnerCatService {
    SendMessage distribution(Update update);

    SendMessage getReasonsForRefusal(Update update);

    SendMessage getRulesForGettingToKnowAnAnimal(Update update);

    SendMessage getListOfDocuments(Update update);

    SendMessage getListOfRecommendationsForTransportation(Update update);

    SendMessage getRecommendationsAboutHouseForKitty(Update update);

    SendMessage getRecommendationsAboutHouseForCat(Update update);

    SendMessage getRecommendationsAboutHouseForDisabledCat(Update update);
}
