package cn.com.softvan.utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/6 0006.
 */
public class ImgUtils {
    public static void resizeNx(String srcPath, String optPath, String picFrom,
                                String picTo, int width, int height, boolean forceSize)
            throws IOException {

        File srcImg = new File(srcPath, picFrom);
        File destImg = new File(optPath, picTo);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(srcImg);
        } catch (IOException e) {
            // 取分辨率出问题则忽略转换记日志
            return;
        }
        if (forceSize) {
            //forceSize=true时强制转为指定尺寸,不保持原图比例
            Thumbnails.of(srcImg).forceSize(width, height).toFile(destImg);
        } else {
            if (bi.getWidth() < width && bi.getHeight() < height) {
                // 原图宽高都小于转换目标,则直接复制
                copyFile(srcImg, destImg);
            } else {
                // 否则进行图象转换
                Thumbnails.of(srcImg).size(width, height).toFile(destImg);
            }
        }
    }

    public static boolean copyFile(File srcFile, File destFile) {
        // 复制文件
        int byteread = 0; // 读取的字节数
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024 * 32];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
	 * 生成时间的方法
	 */
    public static String getTime(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getRandomString(int size) {
        char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q',
                'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
                'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        Random random = new Random(); // 初始化随机数产生器
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[Math.abs(random.nextInt()) % c.length]);
        }
        return sb.toString();
    }
}
