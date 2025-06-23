package com.time.axis.config;

import com.time.axis.emnus.CommonConstant;
import com.time.axis.util.SM4Util;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author carl
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头获取 openId 和 token
        String openId = request.getHeader("openId");
        String token = request.getHeader("token");
        
        // 2. 验证参数是否存在
        if (openId == null || token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\": 401, \"message\": \"Missing authentication parameters\"}");
            return false;
        }
        
        // 3. 验证参数是否符合要求（这里替换为你的实际验证逻辑）
        if (!validateCredentials(openId, token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\": 401, \"message\": \"Invalid credentials\"}");
            return false;
        }
        
        // 4. 验证通过，继续后续流程
        return true;
    }

    /**
     * 简单实现token验证逻辑
     * @param openId
     * @param token
     * @return
     */
    private boolean validateCredentials(String openId, String token) {
        boolean result = false;
        String key = null;
        try {
            key = SM4Util.generateKey();
            String decrypt = SM4Util.decrypt(token, key, CommonConstant.SM4_MODE_CBC);
            result = openId.equals(decrypt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}