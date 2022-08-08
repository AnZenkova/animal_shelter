package pro.sky.telegrambot.animal_shelter.service.UserCat;

import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserCat;

import java.util.List;

public interface UserCatService {

    void createUserCat(User user);

    UserCat getUserCatById(Long id);

    List<UserCat> getAllUsersCat();

    UserCat editUserCat(UserCat userCat);

    void deleteUserCatById(Long id);
}
