package cn.huazhongxian.web.seevlet;

import cn.huazhongxian.dao.UserDao;
import cn.huazhongxian.dao.impl.UserDaoImpl;
import cn.huazhongxian.domain.User;
import cn.huazhongxian.service.UserService;
import cn.huazhongxian.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
         request.setCharacterEncoding("utf-8");
        //2.获取所有数据
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String sage = request.getParameter("age");
        int age = Integer.parseInt(sage);
        String address = request.getParameter("address");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        //3.封装对象
        User registerUser=new User();
        registerUser.setName(name);
        registerUser.setGender(gender);
        registerUser.setAge(age);
        registerUser.setAddress(address);
        registerUser.setQQ(qq);
        registerUser.setEmail(email);
        //4.调用userService中的add方法
        UserService userService=new UserServiceImpl();
        userService.addUser(registerUser);
        //5.跳转到userListServlet中再次查询
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
