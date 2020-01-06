package com.yufan.socket.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

/**
 * @author lirf
 * @version 1.0
 * @date 2019/12/6 14:20
 * @describe
 */
@Controller
@RequestMapping("/client/")
public class ClientController {

    private Logger LOG = Logger.getLogger(ClientController.class);

    /* @GetMapping("/login/{cid}")
     public ModelAndView userLogin(@PathVariable String cid) {
         ModelAndView modelAndView = new ModelAndView();
         //LOG.info("--------用户进入------cid：" + cid);
         modelAndView.addObject("cid", cid);
         modelAndView.setViewName("socket");
         return modelAndView;
     }
 */
    @GetMapping("/login/{cid}")
    public ModelAndView userLogin2(@PathVariable String cid) {
        ModelAndView modelAndView = new ModelAndView();
        //LOG.info("--------1用户进入------cid：" + cid);
        String sid = "all";
        if ("1".equals(cid)) {
            sid = "2";
        } else if ("2".equals(cid)) {
            sid = "1";
        }

        modelAndView.addObject("cid", cid);
        modelAndView.addObject("sid", sid);
        modelAndView.setViewName("socket3");
        return modelAndView;
    }
/*
    @GetMapping("/login/{cid}")
    public ModelAndView userLogin2(@PathVariable String cid) {
        ModelAndView modelAndView = new ModelAndView();
        //LOG.info("--------1用户进入------cid：" + cid);
        modelAndView.addObject("cid", cid);
        modelAndView.setViewName("socket2");
        return modelAndView;
    }
*/

    @GetMapping("queue/{user}")
    public ModelAndView queue(@PathVariable String user) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user",user);//消息接收

        modelAndView.setViewName("queue");
        return modelAndView;
    }
    @GetMapping("topic")
    public ModelAndView topic() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("topic");
        return modelAndView;
    }



}
