package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.utils.Base64Coder;
import com.yufan.utils.VerifySign;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;

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
     * 充值页面 http://http://lirf-shop.51vip.biz/h5-web/recharge/rechargePage
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
     * 创建订单
     */
    @RequestMapping("createOrder")
    public void createOrder(HttpServletResponse response, HttpServletRequest request, String payWay, BigDecimal orderPrice) {
        PrintWriter writer;
        try {
            writer = response.getWriter();

            //
            String secretKey = "dsfsdfsd";
            String sysCode = "h5-web";
            String businessCode = "order_pay";
            String orderNo = String.valueOf(System.currentTimeMillis());

            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            JSONObject paramData = new JSONObject();
            paramData.put("sys_code", sysCode);
            paramData.put("business_code", businessCode);
            paramData.put("order_no", orderNo);
            paramData.put("pay_way", payWay);
            paramData.put("timestamp", timestamp);

            String sign = VerifySign.getSign(paramData, secretKey);
            paramData.put("sign", sign);
            String base64Str = Base64Coder.encodeString(paramData.toJSONString());
            LOG.info("----请求支付参数-----" + paramData);
            JSONObject out = new JSONObject();
            out.put("values", base64Str);
            writer.print(out);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询订单详情
     * http://127.0.0.1:8080/h5-web/recharge/orderDetail
     *
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
