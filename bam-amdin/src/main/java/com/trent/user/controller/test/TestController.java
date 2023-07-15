package com.trent.user.controller.test;

import com.trent.common.utils.result.ResultVo;
import com.trent.system.pojo.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class TestController {


    @RequestMapping("/getTest")
    public ResultVo getTest(){
        List<User> users = new ArrayList<>();
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        users.add(user);
        Long num = 5656L;
        int num5 = -56;
        double num2 = 565.23;
        double num3 = 8999.3;
        double num4 = -8999.3;
        boolean bool = false;
        Map<String,Object> objectMap =new HashMap<>();
        objectMap.put("str","sfbuwbu");
        objectMap.put("str1","是的v怒不被时代进步保守党");
        objectMap.put("str2","的是短 \n---裤女in");
        objectMap.put("str3","dsvsv\n16565");
        objectMap.put("str4","sdvnuj55\n5ddd");
        objectMap.put("str5","84165465461655");
        objectMap.put("str6","84165esrgsgvdsff");
        objectMap.put("arr",users);
        objectMap.put("obj",user);
        objectMap.put("null",null);
        objectMap.put("bool",bool);
        objectMap.put("num",num);
        objectMap.put("num2",num2);
        objectMap.put("num3",num3);
        objectMap.put("num4",num4);
        objectMap.put("num5",num5);
        return new ResultVo(objectMap);
    }
}
