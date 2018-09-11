package com.melot.kk.springcloud.activity.demo.service;

import com.melot.kk.springcloud.user.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "kk-user-server", fallback = UserService.UserServiceFallBack.class)
public interface UserService extends com.melot.kk.springcloud.user.service.UserService {

    @Component
    @RequestMapping("fallback/user")
    class UserServiceFallBack implements UserService {
        private static final Logger logger = LogManager.getLogger(UserServiceFallBack.class);

        @Override
        public UserDTO getUser(int i) {
            logger.warn("UserServiceFallBack.getUser, userId:{}", i);
            return null;
        }
    }
}

