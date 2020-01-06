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
 * @date 2019/12/5 15:26
 * @describe 充值页面
 */
@Controller
@RequestMapping("/recharge/")
public class RechargeController {

    private Logger LOG = Logger.getLogger(RechargeController.class);

    /**
     * 充值页面 http://127.0.0.1:8080/h5-web/recharge/rechargePage
     *
     * @return
     */
    @RequestMapping("rechargePage")
    public ModelAndView rechargePage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("rechargePage");
        return modelAndView;
    }

    /**
     * 去支付(get)
     *
     * @param response
     * @param request
     */
    @RequestMapping("toPay")
    public void toPay(HttpServletResponse response, HttpServletRequest request, Integer payType, String rechargeValue) {
        try {
            LOG.info("------------>payType:" + payType + "  rechargeValue:" + rechargeValue);
            response.sendRedirect("http://127.0.0.1:8087/pay-center/pay/payEnter?payType=" + payType + "&rechargeValue=" + rechargeValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询订单详情
     * http://127.0.0.1:8080/h5-web/recharge/orderDetail
     * @param response
     * @param request
     * @param orderNo
     * @return
     */
    @RequestMapping("orderDetail")
    public ModelAndView toOrderDetail(HttpServletResponse response, HttpServletRequest request, String orderNo) {
        LOG.info("------查询订单详情-------orderNo: " + orderNo);

        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("orderDetail");
        return modelAndView;
    }

}
