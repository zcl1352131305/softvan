package cn.com.softvan.sso;

import cn.com.softvan.sso.utils.Base64Util;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/12
 * Time: 14:41
 */
@Slf4j
public class SSOHelper {
    /**
     * 校验Token是否正确
     * @param token
     * @param ip
     * @return
     */
    public static JSONObject verifyToken(String token, String ip){
        JSONObject result = new JSONObject();
        try {
            Algorithm algorithm = Algorithm.HMAC256("AUTH");
            JWTVerifier verifier = JWT.require(algorithm).withClaim("ip", ip)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            //验证成功
            result.put("header", JSONObject.parse(Base64Util.strDecode(jwt.getHeader())));
            result.put("payload", JSONObject.parse(Base64Util.strDecode(jwt.getPayload())));
            result.put("isOK", true);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result.put("isOK", false);
        }catch (JWTVerificationException exception){
            //Invalid signature/claims
            exception.printStackTrace();
            result.put("isOK", false);
        }
        return result;
    }


    /**
     * getIpAddr：获取用户真实IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static void main(String[] args){
       System.out.println(BCrypt.hashpw("123456", BCrypt.gensalt(10)));
        System.out.println(BCrypt.hashpw("1098e972fdbcc93f33deb9b9ef8620fa","$2a$10$fqsW3WO.331jmU8jolKg8u"));

        System.out.println(BCrypt.gensalt(10));

    }

}
