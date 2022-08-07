package pro.sky.telegrambot.animal_shelter;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.animal_shelter.controller.UserDogController;
import pro.sky.telegrambot.animal_shelter.model.UserDog;
import pro.sky.telegrambot.animal_shelter.repository.UserDogRepository;
import pro.sky.telegrambot.animal_shelter.service.userDog.UserDogServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class UserDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDogRepository userDogRepository;

    @SpyBean
    UserDogServiceImpl userDogService;

    @InjectMocks
    private UserDogController userController;

    @Test
    public void getAllUserTest() throws Exception{

        Long id = 1L;
        String userName = "khvbka";
        String firstName = "hhcdh";
        String lastName = "dbhjs";

        UserDog user = new UserDog();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        List<UserDog> users = new ArrayList<>();
        users.add(user);

        when(userDogRepository.save(any(UserDog.class))).thenReturn(user);
        when(userDogRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/userDog/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").value(userName))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));
    }

    @Test
    public void editUserByIdTest() throws Exception{

        Long id = 1L;
        String userName = "khvbka";
        String firstName = "hhcdh";
        String lastName = "dbhjs";

        JSONObject userDogObject = new JSONObject();
        userDogObject.put("username", userName);
        userDogObject.put("firstName", firstName);
        userDogObject.put("lastName", lastName);

        UserDog editUserDog = new UserDog();
        editUserDog.setId(id);
        editUserDog.setUsername(userName);
        editUserDog.setFirstName(firstName);
        editUserDog.setLastName(lastName);


        when(userDogRepository.save(any(UserDog.class))).thenReturn(editUserDog);


            mockMvc.perform(MockMvcRequestBuilders
                            .put("/userDog")
                            .content(userDogObject.toString())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.username").value(userName))
                    .andExpect(jsonPath("$.firstName").value(firstName))
                    .andExpect(jsonPath("$.lastName").value(lastName));
    }

    @Test
    public void deleteUserDogTest() throws Exception {

        Long id = 1L;
        String userName = "khvbka";
        String firstName = "hhcdh";
        String lastName = "dbhjs";

        UserDog user = new UserDog();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userDogRepository.save(user);
        doNothing().when(userDogRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/userDog/" + id))
                .andExpect(status().isOk());
    }
}
