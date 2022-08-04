package pro.sky.telegrambot.animal_shelter.service.infoForPotentialOwner;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface InformationForPotentialOwnerService {

    SendMessage distribution(Update update);

    /**
     * Метод получения правил знакомства с собакой
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage getRulesForGettingToKnowAnAnimal(Update update);

    /**
     * Метод получения списка документов для того, чтобы забрать собаку из приюта
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage getListOfDocuments(Update update);

    /**
     * Метод получения списка рекомендаций для транспортировки собаки
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */

    SendMessage getListOfRecommendationsForTransportation(Update update);

    /**
     * Метод получения списка рекомендаций для обустройства дома для щенка
     *
     * @param update Объект сообщения чата
     * @return SendMessage
     */

    SendMessage getRecommendationsAboutHouseForPuppy(Update update);


    /**
     * Метод получения списка рекомендаций для обустройства дома для взрослой собаки
     *
     * @param update Объект сообщения чата
     * @return SendMessage
     */
    SendMessage getRecommendationsAboutHouseForDog(Update update);

    /**
     * Метод получения списка рекомендаций для обустройства дома для собаки с ограниченными возможнотями
     *
     * @param update Объект сообщения чата
     * @return SendMessage
     */
    SendMessage getRecommendationsAboutHouseForDisabledDog(Update update);

    /**
     * Метод получения списка рекомендаций от кинолога по первичному общению с собакой
     *
     * @param update Объект сообщения чата
     * @return SendMessage
     */
    SendMessage getInitialCommunicationWithDog(Update update);

    /**
     * Метод получения списка кинологов
     *
     * @param update Объект сообщения чата
     * @return SendMessage
     */
    SendMessage getListOfCynologist(Update update);

    /**
     * Метод получения списка причин отказа о заборе собаки из приюта
     *
     * @param update Объект сообщения чата
     * @return SendMessage
     */
    SendMessage getReasonsForRefusal(Update update);
}
