package com.ys.crm.ExceptionHandler;

import com.ys.crm.exception.ConvertException;
import com.ys.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public Map doLoginException(Exception e) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("flag", false);
        map.put("msg", e.getMessage());
        return map;
    }

    @ExceptionHandler(value = ConvertException.class)
    public void doConvertException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        request.setAttribute("msg",e.getMessage());
        try {
            request.getRequestDispatcher("/Exception.jsp").forward(request, response);
        } catch (ServletException servletException) {
            servletException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
  /*  @ExceptionHandler
    public void doException(HttpServletResponse response) {
        try {
            response.getWriter().write(JacksonUtil.flagJson(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
