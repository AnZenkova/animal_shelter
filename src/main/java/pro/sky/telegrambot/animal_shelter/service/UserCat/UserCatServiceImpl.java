package pro.sky.telegrambot.animal_shelter.service.UserCat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.model.UserCat;
import pro.sky.telegrambot.animal_shelter.model.UserDog;
import pro.sky.telegrambot.animal_shelter.repository.UserCatRepository;
import pro.sky.telegrambot.animal_shelter.repository.UserDogRepository;

import java.util.List;

@Service
public class UserCatServiceImpl implements UserCatService{

    @Autowired
    private UserCatRepository userCatRepository;

    public void createUserCat(User user) {
        UserCat userCat = new UserCat();
        userCat.setUsername(user.getUsername());
        userCat.setFirstName(user.getFirstName());
        userCat.setLastName(user.getLastName());
        userCatRepository.save(userCat);
    }

    public UserCat getUserCatById(Long id) {
        return userCatRepository.findUserCatById(id);
    }

    public List<UserCat> getAllUsersCat() {
        return userCatRepository.findAll();
    }

    public UserCat editUserCat(UserCat userCat) {
        return userCatRepository.save(userCat);
    }

    public void deleteUserCatById(Long id) {
        userCatRepository.deleteById(id);
    }
}
