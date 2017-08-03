package cn.com.softvan.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/15
 * Time: 17:06
 * Base64编解码
 */
public class Base64Util {

    public static byte[] encode(byte[] bytes){
        return Base64.encodeBase64(bytes);
    }

    public static String strEncode(String str){
        return Base64.encodeBase64String(str.getBytes());
    }

    public static byte[] decode(byte[] bytes){
        return Base64.decodeBase64(bytes);
    }


    public static String strDecode(String str){
        return new String(Base64.decodeBase64(str.getBytes()));
    }

}
