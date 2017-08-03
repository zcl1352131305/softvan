package cn.com.softvan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/05
 * Time: 14:16
 */
public class OrderIdGenerator {
    //循环自增上限,超出则从初始值开始
    private static int maxLoop = 999;
    private static int initNum  = 100;
    private volatile static AtomicInteger atomicInteger;
    /**
     * author: Vino
     * date: 2016/5/8
     * function: DCL获取单例atomicInteger
     * @return
     */
    private static AtomicInteger getAtomicInteger() {
        if (atomicInteger == null) {
            synchronized (OrderIdGenerator.class) {
                if (atomicInteger == null)
                    atomicInteger = new AtomicInteger(initNum);//初始值为100
            }
        }
        return atomicInteger;
    }
    /**
     * author: Vino
     * date: 2016/5/8
     * function: 订单号生成
     * @return
     */
    public static Long next() {
        //获取单例
        AtomicInteger atomicInteger = getAtomicInteger();
        //原子性自增并返回自增前的值
        int nextValue = atomicInteger.getAndIncrement();
        //若循环次数已超过上限
        if (nextValue > maxLoop) {
            //CAS更新值成功
            if (atomicInteger.compareAndSet(nextValue + 1, initNum)) {
                atomicInteger.getAndIncrement();
                return getNextNormally(initNum);
            }
            else {//CAS更新值失败
                //重新获取
                nextValue = atomicInteger.getAndIncrement();
                if (nextValue <= maxLoop)
                    return getNextNormally(nextValue);
                else{//非正常情况下
                    return getNextUnNormally();
                }
            }
        }
        return getNextNormally(nextValue);
    }
    public static Long getNextNormally(int nextValue) {
        int randomNum=(int)(Math.random()*90)+10;//2位随机码
        return Long.valueOf(getDateNum()+nextValue+randomNum);
    }
    public static Long getNextUnNormally() {
        int randomNum = (int)(Math.random()*90000)+10000;//5位随机码
        return Long.valueOf(getDateNum()+randomNum);
    }
    /**
     * author: Vino
     * date: 2016/5/8
     * function: 获取订单号中的时间部分
     * @return
     */
    public static String getDateNum(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String sTime = simpleDateFormat.format(date);
        Integer nSecond = Integer.valueOf(sTime.substring(6,8)) * 60 * 60 +Integer.valueOf(sTime.substring(8,10)) * 60
                + Integer.valueOf(sTime.substring(10,12));
        String sSecond = String.valueOf(nSecond);
        if(sSecond.length() < 5){
            for(int i = 5,j = sSecond.length(); i > j; i--){
                sSecond = "0" + sSecond;
            }
        }
        return sTime.substring(0,6)+sSecond;
    }

    public static Long g(){
        long currentMiles = System.nanoTime();
        long random = getNextNormally(3);
        return currentMiles + random;
    }

    public static void main(String[] args){
        Set<Long> id = new HashSet<>();
        for(int i=0;i<100000;i++){
            //
            id.add(g());
        }
        System.out.println(g());
        System.out.println(id.size());
    }
}
