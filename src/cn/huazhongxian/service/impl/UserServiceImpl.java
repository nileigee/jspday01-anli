package cn.huazhongxian.service.impl;

import cn.huazhongxian.dao.UserDao;
import cn.huazhongxian.dao.impl.UserDaoImpl;
import cn.huazhongxian.domain.PageBean;
import cn.huazhongxian.domain.User;
import cn.huazhongxian.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public User finUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void upadteUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids!=null&& ids.length>0) {
            for (String id : ids) {
                dao.deleteUser(Integer.parseInt(id));

            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);
          if(currentPage<=0){
              currentPage=1;
          }

        //1.创建一个空的pagebean对象
        PageBean<User> pb=new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用dao查询总记录数
        int totalCount=dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询list集合
        //计算开始记录的索引
        int start=(currentPage-1)*rows;
        List<User> list=dao.findByPage(start,rows,condition);
        pb.setList(list);
        //5.计算总页码
        int totalPage=(totalCount % rows)==0 ? totalCount/rows : (totalCount/rows)+1;
        pb.setTotalPage(totalPage);
        if(currentPage>=pb.getTotalPage()){
            currentPage=pb.getTotalPage();
        }
        return pb;
    }
}
