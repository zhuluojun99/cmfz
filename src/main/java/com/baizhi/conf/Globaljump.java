/*
package com.baizhi.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Globaljump implements ErrorController {
    private static Logger log = LoggerFactory.getLogger(Globaljump.class);
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            default:
                log.info("默认异常跳转");
                return "redirect:/web/index.html";
        }
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
*/
