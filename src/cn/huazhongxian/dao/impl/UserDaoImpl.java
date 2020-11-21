package cn.huazhongxian.dao.impl;

import cn.huazhongxian.dao.UserDao;
import cn.huazhongxian.domain.User;
import cn.huazhongxian.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
   private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //使用jdbc操作数据库
        String sql="select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    /**
     * 登陆方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户的全部数据
     */
    public  User login(User loginUser){
        try {

            //1.编写sql
            String sql="select * from user where username=? and password=?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());

            return user;
        }catch (DataAccessException e){
            e.printStackTrace();
            //如果出现异常就返回null
            return null;
        }
    }

    /**
     * 操作数据库添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        //1.编写sql语句
        String sql="insert into user values(null,?,?,?,?,?,?,null,null)";
        //2.调用query语句
        System.out.println(user.getName());
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQQ(),user.getEmail());
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(int id) {
     String sql="delete from user where id=?";
     template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        String sql="select *  from user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);

    }

    @Override
    public void update(User user) {
        String sql="update user set name=?,gender=?,age=?,address=?,QQ=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQQ(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义一个模板sql
        String sql="select count(*) from user where 1 = 1 ";
        StringBuilder sb=new StringBuilder(sql);
        //2.遍历map集合condition
        Set<String> keySet = condition.keySet();
        //定义参数集合
        List<Object> params=new ArrayList<Object>();
        for (String key: keySet) {
            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;//结束当前循环，下面代码不执行，进入下一次循环
            }



            //获取value
            String value = condition.get(key)[0];
            //判断value
            if(value!=null && !"".equals(value)){
                //有值
                sb.append(" and "+key+"  like ? ");
                params.add("%"+value+"%");//？条件的值的集合
            }

        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user where 1 = 1";
        StringBuilder sb=new StringBuilder(sql);
        //2.遍历map集合condition
        Set<String> keySet = condition.keySet();
        //定义参数集合
        List<Object> params=new ArrayList<Object>();
        for (String key: keySet) {
            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;//结束当前循环，下面代码不执行，进入下一次循环
            }



            //获取value
            String value = condition.get(key)[0];
            //判断value
            if(value!=null && !"".equals(value)){
                //有值
                sb.append(" and "+key+"  like ? ");
                params.add("%"+value+"%");//？条件的值的集合
            }

        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数
        params.add(start);
        params.add(rows);

        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
