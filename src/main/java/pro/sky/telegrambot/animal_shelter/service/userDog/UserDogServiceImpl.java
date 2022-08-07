package pro.sky.telegrambot.animal_shelter.service.userDog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserDog;
import pro.sky.telegrambot.animal_shelter.repository.UserDogRepository;

import java.util.List;

@Service
public class UserDogServiceImpl implements UserDogService{

    @Autowired
    private UserDogRepository userDogRepository;

    public void createUserDog(User user) {
        UserDog userDog = new UserDog();
        userDog.setUsername(user.getUsername());
        userDog.setFirstName(user.getFirstName());
        userDog.setLastName(user.getLastName());
        userDogRepository.save(userDog);
    }

    public UserDog getUserDogById(Long id) {
        return userDogRepository.findUserDogById(id);
    }

    public List<UserDog> getAllUsersDog() {
        return userDogRepository.findAll();
    }

    public UserDog editUserDog(UserDog userDog) {
        return userDogRepository.save(userDog);
    }

    public void deleteUserDogById(Long id) {
        userDogRepository.deleteById(id);
    }
}
