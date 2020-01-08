package com.yufan.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lirf
 * @version 1.0
 * @date 2020/1/7 13:36
 * @describe
 */
@Controller
@RequestMapping("/pay/")
public class PayResultController {

    private Logger LOG = Logger.getLogger(PayResultController.class);


    /**
     * 支付成功页面 http://127.0.0.1:8080/h5-web/pay/success
     *
     * @return
     */
    @RequestMapping("success")
    public ModelAndView paySuccessPage(HttpServletRequest request, HttpServletResponse response, String orderNo) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("orderNo", orderNo);
        modelAndView.setViewName("paySuccessPage");
        return modelAndView;

    }

    /**
     * 支付失败页面 http://127.0.0.1:8080/h5-web/pay/fail
     *
     * @return
     */
    @RequestMapping("fail")
    public ModelAndView payFailPage(HttpServletRequest request, HttpServletResponse response, String orderNo) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("orderNo", orderNo);
        modelAndView.setViewName("payFailPage");
        return modelAndView;

    }

    /**
     * 支付失败页面 http://127.0.0.1:8080/h5-web/pay/timeOut
     *
     * @return
     */
    @RequestMapping("timeOut")
    public ModelAndView payTimeOutPage(HttpServletRequest request, HttpServletResponse response, String orderNo) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("orderNo", orderNo);
        modelAndView.setViewName("payTimeOutPage");
        return modelAndView;

    }


}
