package cn.com.softvan.sso.token;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/14
 * Time: 17:47
 * 认证token生成器
 */
public interface ISSOAuthTokenGenerator<T> {
    String generateAuthToken(T obj);
}
