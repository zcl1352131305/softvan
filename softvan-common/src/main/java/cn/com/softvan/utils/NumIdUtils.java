package cn.com.softvan.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/23
 * Time: 15:30
 * 生成数字主键
 */
public class NumIdUtils {
    private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    public static String generateCompanyId(){

        return null;
    }
    public static String createRoleId(){
        String id =  RandomStringUtils.randomNumeric(4);


        return id;
    }


    private static String generateRandomNumStr(int length){
        return getPrefix()+RandomStringUtils.randomNumeric(length-1);
    }

    private static String getPrefix(){
        return String.valueOf(prefix[RandomUtils.nextInt(0,9)]);
    }

}
