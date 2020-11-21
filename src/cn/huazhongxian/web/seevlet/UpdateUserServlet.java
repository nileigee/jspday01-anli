package cn.huazhongxian.web.seevlet;

import cn.huazhongxian.domain.User;
import cn.huazhongxian.service.UserService;
import cn.huazhongxian.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String sage = request.getParameter("age");
        int age = Integer.parseInt(sage);
        String address = request.getParameter("address");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        //3.封装对象
        User user=new User();
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        user.setAddress(address);
        user.setQQ(qq);
        user.setEmail(email);
        user.setId(id);
        //4.调用userservvsive
        UserService userService=new UserServiceImpl();
        userService.upadteUser(user);
        //5.跳转到查询所有的servlet中去
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
