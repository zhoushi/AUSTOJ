package cn.edu.aust.dao;

import cn.edu.aust.entity.User;
import cn.edu.aust.util.PageUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户和数据库交互的DAO
 */
@Repository("userDao")
public class UserDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 得到用户排名展示信息
     * @param pageUtil
     * @return
     */
    public List<User> findUserRank(PageUtil pageUtil) throws Exception{
        return sqlSessionTemplate.selectList("userMapper.findUserRank",pageUtil);
    }
    /**
     * 得到一个用户的详细信息
     * @param id
     * @return
     */
    public User findUserById(int id) throws Exception{
        return sqlSessionTemplate.selectOne("userMapper.findUserById",id);
    }

    /**
     * 得到一个用户AC的所有题目
     * @param id
     * @return
     */
    public List<Integer> findUserACPro(int id) throws Exception{
        return sqlSessionTemplate.selectList("userMapper.findUserACPro",id);
    }

    /**
     * 得到一个用户正在解决的题目
     * @param id
     * @return
     */
    public List<Integer> findUserBeingAC(int id) throws Exception{
        return sqlSessionTemplate.selectList("userMapper.findUserBeingAC",id);
    }

    /**
     * 判断一个用户是否有权限登录
     * @param user
     * @return
     */
    public User findUserByLogin(User user) throws Exception{
        return sqlSessionTemplate.selectOne("userMapper.findUserByLogin",user);
    }

    /**
     * 根据id修改用户
     * @param user
     * @return
     */
    public boolean updateUserById(User user) throws Exception{
        int k = sqlSessionTemplate.update("userMapper.updateUserById",user);
        return k>0;
    }

    /**
     * 根据id修改用户头像路径
     * @param user
     * @return
     */
    public boolean updateImgById(User user) throws Exception{
        int k = sqlSessionTemplate.update("userMapper.updateImgById",user);
        return k>0;
    }

    /**
     * 根据用户的id更新用户登录时间
     * @param id
     * @return
     */
    public boolean updateDateById(int id) throws Exception{
        int k = sqlSessionTemplate.update("userMapper.updateDateById",id);
        return k>0;
    }
    /**
     * 查找出首页用于展示的用户列表
     * @return
     */
    public List<User> findUserToShow() throws Exception{
        return sqlSessionTemplate.selectList("userMapper.findUserToShow");
    }

    /**
     * 判断该用户名是否已用
     * @param username
     * @return
     */
    public boolean findUserByName(String username) throws Exception{
        User user = sqlSessionTemplate.selectOne("userMapper.findUserByName",username);
        return user==null;
    }

    /**
     * 插入一个新的用户
     * @param user
     * @return
     */
    public boolean addUser(User user) throws Exception{
        int k = sqlSessionTemplate.insert("userMapper.addUser",user);
        return k>0;
    }

}
