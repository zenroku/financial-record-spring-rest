package com.zenroku.financial.records.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.transaction_history.service.UserService;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getUser(){
        BaseResponseArray userList = userService.get();

        System.out.println(objectMapper.convertValue(userList, Map.class));
    }

    @Test
    @Disabled
    void createUserNotPass(){
        User user = new User();
        user.setFirstName("");
        user.setLastName("Voldigoad");
        user.setEmail("");
        BaseResponse response = userService.create(user);
        System.out.println(objectMapper.convertValue(response,Map.class));
    }

    @Test
    @Disabled
    void createUserPass(){
        User user = new User();
        user.setFirstName("Cid");
        user.setLastName("Kagenou");
        user.setEmail("cid.kagenou@gmail.com");
        BaseResponse response = userService.create(user);
        System.out.println(objectMapper.convertValue(response,Map.class));
    }
}
