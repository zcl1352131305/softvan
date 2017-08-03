package cn.com.softvan.utils;

/**
 * Created by zcl on 2017/6/1 0001.
 * 返回结果类
 */
public class Result {




    /** 结果代码  参考ResultCode */
    private String code;
    /** 信息 */
    private String message;
    /** 数据 */
    private Object data;

    public Result() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("成功！");

    }

    public Result(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public Result(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Result(ResultCode code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    /**
     * 业务处理成功
     * @param message 返回信息
     * @param data 返回数据
     * @return
     */
    public static Result success(String message,Object data){
        return new Result(ResultCode.SUCCESS,message,data);
    }

    /**
     * 业务处理成功
     * @param message 返回信息
     * @return
     */
    public static Result success(String message){
        return success(message,null);
    }

    /**
     * 系统错误
     * @param message 错误信息
     * @param e 异常
     * @return
     */
    public static Result sysError(String message, Exception e){
        e.printStackTrace();
        return new Result(ResultCode.SYS_ERROR,message);
    }
    /**
     * 系统错误
     * @return
     */
    public static Result sysError(String message){
        return new Result(ResultCode.SYS_ERROR,message);
    }

    /**
     * 系统错误
     * @param e 异常
     * @return
     */
    public static Result sysError(Exception e){
        e.printStackTrace();
        return new Result(ResultCode.SYS_ERROR,"系统错误！");
    }

    /**
     * 参数错误
     * @param message 错误信息
     * @return
     */
    public static Result paramError(String message){
        return new Result(ResultCode.PARAMS_ERROR,message);
    }

    /**
     * 参数错误
     * @return
     */
    public static Result paramError(){
        return new Result(ResultCode.PARAMS_ERROR,"参数错误！");
    }



    public String getCode() {
        return code;
    }
    public void setCode(ResultCode code) {
        this.code = code.val();
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
