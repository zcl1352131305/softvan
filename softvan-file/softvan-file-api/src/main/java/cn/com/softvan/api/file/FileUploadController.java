package cn.com.softvan.api.file;

import cn.com.softvan.config.AppConfig;
import cn.com.softvan.api.BaseController;
import cn.com.softvan.utils.ImgUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/06
 * Time: 9:23
 */
@Controller
@AutoConfigureBefore(AppConfig.class)
@RequestMapping(value = "/proxy/file")
@Slf4j
public class FileUploadController extends BaseController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file) {
        JSONObject json = new JSONObject();
        if (!file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                //项目部署地址
                //根目录路径，可以指定绝对路径
                String savePath = AppConfig.getProperty("fileServer.upload");
                //根目录URL，可以指定绝对路径
                String rootUrl =  AppConfig.getProperty("fileServer.download");

                //文件后缀名
                String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

                //创建目录
                String dirName = "";
                if("gif,jpg,jpeg,png,bmp".indexOf(fileExt) != -1){
                    dirName = "img";
                    savePath += dirName + "/";
                    rootUrl += dirName + "/";

                    String savePathN4 = savePath + "n4/";
                    rootUrl += "n1/";

                    File saveDirFileN4 = new File(savePathN4);
                    if (!saveDirFileN4.exists()) {
                        saveDirFileN4.mkdirs();
                    }

                    // 创建新的文件名
                    String newFileName = ImgUtils.getTime("yyyyMMddHHmmss")
                            + ImgUtils.getRandomString(5) + "." + fileExt;

                    //原图
                    File uploadedFile = new File(saveDirFileN4, newFileName);
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(uploadedFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();

                    reSizePic(savePath,newFileName);
                    rootUrl += newFileName;

                }
                else{
                    dirName = "file";
                    savePath += dirName + "/";
                    rootUrl += dirName + "/";
                    File saveDirFile = new File(savePath);
                    if (!saveDirFile.exists()) {
                        saveDirFile.mkdirs();
                    }

                    // 创建新的文件名
                    String newFileName = ImgUtils.getTime("yyyyMMddHHmmss")
                            + ImgUtils.getRandomString(5) + "." + fileExt;

                    File uploadedFile = new File(savePath, newFileName);
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(uploadedFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    rootUrl += newFileName;
                }

                json.put("fileName",originalFileName);
                json.put("fileUrl",rootUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    private void reSizePic(String savePath,String newFileName) throws IOException {
        String n0W = AppConfig.getProperty("fileServer.image.n0.width");
        String n0H = AppConfig.getProperty("fileServer.image.n0.height");
        String n1W = AppConfig.getProperty("fileServer.image.n1.width");
        String n1H = AppConfig.getProperty("fileServer.image.n1.height");
        String n2W = AppConfig.getProperty("fileServer.image.n2.width");
        String n2H = AppConfig.getProperty("fileServer.image.n2.height");
        String n3W = AppConfig.getProperty("fileServer.image.n3.width");
        String n3H = AppConfig.getProperty("fileServer.image.n3.height");

        String savePathN0 = savePath + "n0/",savePathN1 = savePath + "n1/",savePathN2 = savePath + "n2/",savePathN3 = savePath + "n3/",savePathN4 = savePath + "n4/";

        File saveDirFileN0 = new File(savePathN0);
        if (!saveDirFileN0.exists()) {
            saveDirFileN0.mkdirs();
        }
        File saveDirFileN1 = new File(savePathN1);
        if (!saveDirFileN1.exists()) {
            saveDirFileN1.mkdirs();
        }
        File saveDirFileN2 = new File(savePathN2);
        if (!saveDirFileN2.exists()) {
            saveDirFileN2.mkdirs();
        }
        File saveDirFileN3 = new File(savePathN3);
        if (!saveDirFileN3.exists()) {
            saveDirFileN3.mkdirs();
        }

        ImgUtils.resizeNx(savePathN4, savePathN0, newFileName,newFileName, Integer.parseInt(n0W), Integer.parseInt(n0H),true);
        ImgUtils.resizeNx(savePathN4, savePathN1, newFileName,newFileName, Integer.parseInt(n1W), Integer.parseInt(n1H),true);
        ImgUtils.resizeNx(savePathN4, savePathN2, newFileName,newFileName, Integer.parseInt(n2W), Integer.parseInt(n2H),true);
        ImgUtils.resizeNx(savePathN4, savePathN3, newFileName,newFileName, Integer.parseInt(n3W), Integer.parseInt(n3H),true);




    }




}
