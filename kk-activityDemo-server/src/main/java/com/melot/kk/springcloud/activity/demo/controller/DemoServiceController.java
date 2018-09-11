package com.melot.kk.springcloud.activity.demo.controller;

import com.melot.kk.springcloud.activity.demo.model.UserRankDTO;
import com.melot.kk.springcloud.activity.demo.service.DemoService;
import com.melot.kk.springcloud.activity.demo.service.UserService;
import com.melot.kk.springcloud.user.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoServiceController implements DemoService {

    @Autowired
    UserService userService;

    @Override
    public UserRankDTO getUserRank(int userId) {

        UserDTO user = userService.getUser(userId);

        if (user == null) {
            return null;
        }

        UserRankDTO userRankDTO = new UserRankDTO();
        userRankDTO.setUserId(user.getUserId());
        userRankDTO.setNickname(user.getNickname());
        userRankDTO.setRank(1);
        userRankDTO.setScore(new Random().nextInt(100));

        return userRankDTO;
    }
}
