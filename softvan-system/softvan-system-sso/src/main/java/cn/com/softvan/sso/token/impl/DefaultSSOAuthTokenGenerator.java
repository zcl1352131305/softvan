package cn.com.softvan.sso.token.impl;


import cn.com.softvan.sso.bean.SSOTicket;
import cn.com.softvan.sso.token.ISSOAuthTokenGenerator;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/15
 * Time: 9:47
 * 默认授权token生产器
 * 采用JWT方式生成票据
 */
@Component
public class DefaultSSOAuthTokenGenerator implements ISSOAuthTokenGenerator<SSOTicket> {
    @Override
    public String generateAuthToken(SSOTicket ticket) {
        String token = null;
        try {
            //算法
            Algorithm algorithm = Algorithm.HMAC256("AUTH");
            JWTCreator.Builder jwtBuilder= JWT.create();
            jwtBuilder.withExpiresAt(new Date(ticket.getExpiration() + 1000 * 60 * 60));
            jwtBuilder.withClaim("uid",ticket.getUid());
            jwtBuilder.withClaim("org",ticket.getOrganization());
            jwtBuilder.withClaim("ip", ticket.getIp());
            jwtBuilder.withClaim("e",ticket.getIsEnabled());
            /**权限列表*/
            jwtBuilder.withArrayClaim("r", ticket.getRoles().toArray(new String[ticket.getRoles().size()]));
            token = jwtBuilder.sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return token;
    }
}
