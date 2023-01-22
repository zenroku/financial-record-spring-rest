package com.zenroku.financial.records.api.app.user.controller;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.service.UserService;
import com.zenroku.financial.records.api.settings.constant.Route;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Route.userPath)
public class UserControllerImpl implements UserController{

    @Autowired
    UserService userService;
    @Override
    @GetMapping
    public BaseResponseArray get() throws Exception {
        return userService.get();
    }

    @Override
    @PostMapping
    public BaseResponse create(@RequestBody User user) throws Exception {
        return userService.create(user);
    }

    @Override
    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable("id") Long id,@RequestBody User user) throws Exception {
        return userService.update(id,user);
    }

    @Override
    @GetMapping("/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) throws Exception {
        return userService.getById(id);
    }
}
