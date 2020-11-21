package cn.huazhongxian.service;

import cn.huazhongxian.domain.PageBean;
import cn.huazhongxian.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();
    public void addUser(User user);
    public void deleteUser(String id);

    public User finUserById(String id);

    void upadteUser(User user);

    void delSelectedUser(String[] ids);

    /**
     * 分页,条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
