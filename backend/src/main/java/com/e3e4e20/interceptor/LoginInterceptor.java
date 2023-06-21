package com.e3e4e20.interceptor;

import com.e3e4e20.config.TokenConfig;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.exception.UnverifiedException;
import com.e3e4e20.service.UserInfoService;
import com.e3e4e20.service.implement.UserInfoServiceImplement;
import com.e3e4e20.utils.AuthorizedThread;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
Filename: AuthorizedInterceptor
Created: 2023年06月02日 10时45分55秒 星期五
Author: 天龙梦雪
*/
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger("Class:AuthorizedInterceptor");
    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;

    /**
     * 获取前端请求头（Header）中的 authorized 字段对应的 token ,
     * 并对token执行校验,不通过校验 throw exception,通过校验返回 true,
     * 并封装数据 userid(人员唯一标识),name(姓名)
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return true or throws exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        log.debug("preHandle: @Resource(name = \"userInfoService\") :" + userInfoService);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
        String token = request.getHeader("Authorization");
        /*String menuParentId = request.getHeader("parent");
        String menuChildId = request.getHeader("child");
        String apiId = request.getHeader("api");*/
        if (null == token || token.length() <= 0) {
            log.error("preHandle: token is null ! 没有token认证! 非法请求!");
            throw new UnverifiedException();
        }
        /*if (null == menuParentId || menuParentId.length() <= 0) {
            log.error("preHandle: menuParentId is null ! 没有提供父级菜单的id无法执行权限认证!");
            throw new FailureMessageException("请先登录!");
            return false;
        }
        if (null == menuChildId || menuChildId.length() <= 0) {
            log.error("preHandle: menuChildId is null ! 没有提供子级菜单的id无法执行权限认证!");
            throw new FailureMessageException("请先登录!");
            return false;
        }
        if (null == apiId || apiId.length() <= 0) {
            log.error("preHandle: apiId is null ! 没有提供api的id无法执行权限认证!");
            throw new FailureMessageException("请先登录!");
            return false;
        }*/
        Claims claims = new TokenConfig().getClaimsByToken(token);
        if (claims.getExpiration().before(new Date())) {
            log.error("preHandle: token is expiration ! token 已过期,请重新登录!");
            throw new UnverifiedException();
        }
        String userid = claims.getSubject();
        log.debug("preHandle: 当前请求的请求头中包含的人员唯一标识为: " + userid);
        String name = userInfoService.getUserInfoByUserid(userid).getName();
        log.debug("preHandle: 当前请求请求头中包含的人员姓名为: " + name);
        if (null == name || name.length() <= 0) {
            log.error("preHandle: user is none! 人员: " + userid + " 不存在!");
            throw new ErrorMessageException("不存在该人员!");
        }
        Map<String, Object> userBaseInfo = new HashMap<>();
        userBaseInfo.put("userid", userid);
        userBaseInfo.put("name", name);
        AuthorizedThread.setAuthorizedThread(userBaseInfo);
        log.debug("preHandle: 当前新建线程内容: " + AuthorizedThread.getAuthorizedThread().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 注销使用的线程
     *
     * @param request   request
     * @param response  response
     * @param handler   handler
     * @param exception exception
     * @throws Exception Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception exception) throws Exception {
        AuthorizedThread.removeAuthorizedThread();
        HandlerInterceptor.super.afterCompletion(request, response, handler, exception);
    }
}
