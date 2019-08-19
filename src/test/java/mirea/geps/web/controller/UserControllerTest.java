package mirea.geps.web.controller;

import mirea.geps.core.dto.User;
import mirea.geps.core.service.AuthService;
import mirea.geps.core.service.RegisterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegisterService registerService;

    @MockBean
    private AuthService authService;

    @Before
    public void init() throws Exception {
        User user = new User("login",
                "password",
                "Афанасий",
                "Кукуев",
                "Олегович",
                "к.т.н.");


        when(registerService.registerUser(user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getDegree())).thenReturn(user);
        when(authService.authUser("login", "password"))
                .thenReturn(user);
    }

    @Test
    public void shouldAuthUser() throws Exception {
        String request = "{\n" +
                "  \"login\" : \"login\",\n" +
                "  \"password\": \"password\"\n" +
                "}";
        String response = "{\n" +
                "  \"status\" : \"SUCCESS\",\n" +
                "  \"user\": {\n" +
                "    \"name\" : \"Афанасий\",\n" +
                "    \"patronymic\": \"Олегович\",\n" +
                "    \"surname\": \"Кукуев\",\n" +
                "    \"degree\": \"к.т.н.\"\n" +
                "  }\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/eps/user/auth").contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));

    }

    @Test
    public void shouldReturnErrorStatusIfAuthFailed() throws Exception {
        String request = "{\n" +
                "  \"login\" : \"123\",\n" +
                "  \"password\": \"password\"\n" +
                "}";
        String response = "{\n" +
                "  \"status\" : \"ERROR\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/eps/user/auth").contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));

    }

    @Test
    public void shouldRegisterUser() throws Exception {
        String registerRequest = "{\n" +
                "  \"login\": \"login\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"Афанасий\",\n" +
                "  \"patronymic\": \"Олегович\",\n" +
                "  \"surname\": \"Кукуев\",\n" +
                "  \"degree\": \"к.т.н.\"\n" +
                "}";

        String registerResponse = "{\n" +
                "  \"login\": \"login\",\n" +
                "  \"name\": \"Афанасий\",\n" +
                "  \"patronymic\": \"Олегович\",\n" +
                "  \"surname\": \"Кукуев\",\n" +
                "  \"degree\": \"к.т.н.\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/eps/user/register").contentType(MediaType.APPLICATION_JSON)
                .content(registerRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(registerResponse));

    }

    @Test
    public void shouldReturnBadRequestIfLoginIsNotSet() throws Exception {
        String registerRequest = "{\n" +
                "  \"login\": \"\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"Афанасий\",\n" +
                "  \"patronymic\": \"Олегович\",\n" +
                "  \"surname\": \"Кукуев\",\n" +
                "  \"degree\": \"к.т.н.\"\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post("/eps/user/register").contentType(MediaType.APPLICATION_JSON)
                .content(registerRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturnBadRequestIfPasswordIsNotSet() throws Exception {
        String registerRequest = "{\n" +
                "  \"login\": \"login\",\n" +
                "  \"password\": \"\",\n" +
                "  \"name\": \"Афанасий\",\n" +
                "  \"patronymic\": \"Олегович\",\n" +
                "  \"surname\": \"Кукуев\",\n" +
                "  \"degree\": \"к.т.н.\"\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post("/eps/user/register").contentType(MediaType.APPLICATION_JSON)
                .content(registerRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturnBadRequestIfSurnameIsNotSet() throws Exception {
        String registerRequest = "{\n" +
                "  \"login\": \"login\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"Афанасий\",\n" +
                "  \"patronymic\": \"Олегович\",\n" +
                "  \"surname\": \" \",\n" +
                "  \"degree\": \"к.т.н.\"\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post("/eps/user/register").contentType(MediaType.APPLICATION_JSON)
                .content(registerRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());


    }
}