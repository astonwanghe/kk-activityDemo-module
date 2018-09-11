package com.melot.kk.springcloud.activity.demo.service;

import com.melot.kk.springcloud.activity.demo.model.UserRankDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/demo")
public interface DemoService {

    @RequestMapping(value = "/getUserRank", method = RequestMethod.GET)
    UserRankDTO getUserRank(@RequestParam("userId") int userId);
}
