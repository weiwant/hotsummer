package com.example.sprint2.config;

import cn.hutool.extra.spring.SpringUtil;
import com.example.sprint2.models.abc.Token;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.utils.Result;
import com.example.sprint2.utils.annotations.IdentitiesContainer;
import com.example.sprint2.utils.annotations.IdentityAccessible;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/7
 */
public class JwtInterceptorConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            Token tokenFactory = SpringUtil.getBean("tokenType");
            tokenFactory.verify(token);
            tokenFactory.validateDate(token);
            tokenFactory.validateAlgorithm(token);
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Set<Integer> identityCodeSet = new HashSet<>();
                if (handlerMethod.getBeanType().getAnnotation(IdentityAccessible.class) != null || handlerMethod.getBeanType().getAnnotation(IdentitiesContainer.class) != null) {
                    if (handlerMethod.getBeanType().getAnnotation(IdentitiesContainer.class) != null) {
                        for (IdentityAccessible identityOnly : handlerMethod.getBeanType().getAnnotation(IdentitiesContainer.class).value()) {
                            identityCodeSet.add(identityOnly.value().getIdentityCode());
                        }
                    }
                    if (handlerMethod.getBeanType().getAnnotation(IdentityAccessible.class) != null) {
                        identityCodeSet.add(handlerMethod.getBeanType().getAnnotation(IdentityAccessible.class).value().getIdentityCode());
                    }
                }
                if (handlerMethod.getMethodAnnotation(IdentityAccessible.class) != null || handlerMethod.getMethodAnnotation(IdentitiesContainer.class) != null) {
                    if (handlerMethod.getMethodAnnotation(IdentitiesContainer.class) != null) {
                        for (IdentityAccessible identityOnly : Objects.requireNonNull(handlerMethod.getMethodAnnotation(IdentitiesContainer.class)).value()) {
                            identityCodeSet.add(identityOnly.value().getIdentityCode());
                        }
                    }
                    if (handlerMethod.getMethodAnnotation(IdentityAccessible.class) != null) {
                        identityCodeSet.add(Objects.requireNonNull(handlerMethod.getMethodAnnotation(IdentityAccessible.class)).value().getIdentityCode());
                    }
                }
                if (!identityCodeSet.isEmpty() && !identityCodeSet.contains(tokenFactory.getPayload(token).get("identity"))) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().print(new Result(ResponseCode.PermissionDenied));
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(new Result(ResponseCode.AuthenticationDenied));
        return false;
    }
}
