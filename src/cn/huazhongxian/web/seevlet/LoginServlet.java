package cn.huazhongxian.web.seevlet;

import cn.huazhongxian.dao.UserDao;
import cn.huazhongxian.dao.impl.UserDaoImpl;
import cn.huazhongxian.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request);
           //1.设置编码
           response.setContentType("text/html;charset=UTF-8");
           //2.获取参数
           String username = request.getParameter("username");
           String password = request.getParameter("password");
           String checkcode = request.getParameter("checkcode");
           //获取生成的验证码
           HttpSession session = request.getSession();
        String checkcode_sesssion = (String) session.getAttribute("checkcode_sesssion");
        //删除session中存储的验证码
        session.removeAttribute("checkcode_sesssion");
        //判断验证码是否正确(忽略大小写)
        if(checkcode_sesssion!=null&&checkcode_sesssion.equalsIgnoreCase(checkcode)){
            //验证码正确
            //3.封装user对象
            User loginuser=new User();
            loginuser.setUsername(username);
            loginuser.setPassword(password);

            //4.调用userdao中的login方法
            UserDao dao=new UserDaoImpl();
            User user = dao.login(loginuser);

            if(user!=null){
                    //5.向session中存储数据
                    session.setAttribute("user",user);
                    //6.重定向到success。jsp
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }else{
                    //存储信息到request
                    request.setAttribute("login_error","用户名或密码错误");
                    //转发
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }


        }else {
            //验证码不一致
            //存储提示信息到request
            request.setAttribute("cc_error","验证码错误");
            //转发岛登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
