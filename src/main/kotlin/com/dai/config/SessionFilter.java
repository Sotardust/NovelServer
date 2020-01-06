package com.dai.config;

import com.alibaba.fastjson.JSON;
import com.dai.bean.model.BaseModel;
import com.dai.bean.model.HttpStatusCode;
import com.dai.dao.StatusMapper;
import com.dai.service.StatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {


    @Autowired
    private StatusMapper statusMapper;

    private final Logger logger = Logger.getLogger(SessionFilter.class);
    private String[] includeUrls = new String[]{"login", "register"};

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        String uri = request.getRequestURI();

        StatusService statusService = new StatusService(statusMapper);

        List<String> list = statusService.findSessionList();

        if (uri.contains("login") || uri.contains("register")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (list != null && list.contains(session.getId())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            BaseModel<String> baseModel = new BaseModel<>();
            baseModel.setCode(HttpStatusCode.CODE_103);
            baseModel.setMsg("sessionId timeout");
            baseModel.setResult("");
            response.getWriter().write(JSON.toJSONString(baseModel));
        }
    }

    /**
     * @param uri
     * @Author: xxxxx
     * @Description: 是否需要过滤
     * @Date: 2018-03-12 13:20:54
     */
    private boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if (includeUrl.contains(uri)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void destroy() {

    }
}
