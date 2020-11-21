package cn.huazhongxian.web.seevlet;

import cn.huazhongxian.domain.PageBean;
import cn.huazhongxian.domain.User;
import cn.huazhongxian.service.UserService;
import cn.huazhongxian.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示的条数

        if(currentPage==null || "".equals(currentPage)){
            currentPage ="1";
        }
        if(rows==null || "".equals(rows)){
            rows="5";
        }

        //获取条件查询的参数
        Map<String, String[]> condition = request.getParameterMap();

        //2.调用service查询
        UserService userService=new UserServiceImpl();
        PageBean<User> pb=userService.findUserByPage(currentPage,rows,condition);
        //System.out.println(pb);
        //3.存储数据
        request.setAttribute("pb",pb);
        //存储查询条件
        request.setAttribute("condition",condition);
        //4.转发到list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
