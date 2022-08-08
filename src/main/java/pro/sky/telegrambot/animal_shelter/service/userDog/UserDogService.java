package pro.sky.telegrambot.animal_shelter.service.userDog;

import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserDog;

import java.util.List;

public interface UserDogService {

    void createUserDog(User user);
    UserDog getUserDogById(Long id);
    public List<UserDog> getAllUsersDog();
    UserDog editUserDog(UserDog userDog);
    public void deleteUserDogById(Long id);
}
