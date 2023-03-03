package com.cj.springsecuritymybatis;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }


    @GetMapping("/admin/hello")
    public String admin() {

        return "admin";
    }

    @GetMapping("/user/hello")
    public String user() {

        return "user";
    }

    @Autowired
    Producer producer;

    @GetMapping("/vf")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        // 生成的文本
        String text = producer.createText();
        session.setAttribute("vf",text);
        // 生成图片
        BufferedImage image = producer.createImage(text);
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image,"jpg",out);
        }
    }
}
