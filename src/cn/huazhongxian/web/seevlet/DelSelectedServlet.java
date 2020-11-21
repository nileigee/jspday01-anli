package cn.huazhongxian.web.seevlet;

import cn.huazhongxian.service.UserService;
import cn.huazhongxian.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       //1.获取所有的uid
        String[] ids = request.getParameterValues("uid");
        //2.调用service中的方法删除
        UserService userService=new UserServiceImpl();
        userService.delSelectedUser(ids);
        //3.跳转查询所有的servlet
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
