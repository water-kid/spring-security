package com.cj.springsecuritymybatis;

import com.mysql.cj.util.Base64Decoder;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode("YWRtaW46MTY3OTA0MDQxNTI2MTpiMzE5YTM3YjdkMzIwNmJjODU5YzU0MjQ3ZGJmZjllMA");
        String s = new String(bytes,"utf-8");
        System.out.println("s = " + s);

        // 两周之后的时间，，， remember me 默认两周过期
        Long lt = new Long("1679040415261");
        Date date = new Date(lt);
        System.out.println("date = " + date);
    }
}
