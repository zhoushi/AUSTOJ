package cn.edu.aust.service;

import cn.edu.aust.dao.UserDao;
import cn.edu.aust.entity.User;
import cn.edu.aust.util.FileUtil;
import cn.edu.aust.util.PageUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 用户部分策略类
 */
@Service("userService")
public class UserService implements InitializingBean{

    private Logger logger = Logger.getLogger(UserService.class);
    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 得到用户排名展示信息
     * @param pageUtil
     * @return
     */
    public List<User> findUserRank(PageUtil pageUtil){
        return userDao.findUserRank(pageUtil);
    }

    /**
     * 得到一个用户的详细信息
     * @param id
     * @return
     */
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    /**
     * 得到一个用户AC的所有题目
     * @param id
     * @return
     */
    public List<Integer> findUserACPro(int id) {
        return userDao.findUserACPro(id);
    }
    /**
     * 得到一个用户正在解决的题目
     * @param id
     * @return
     */
    public List<Integer> findUserBeingAC(int id){
        return userDao.findUserBeingAC(id);
    }
    /**
     * 判断一个用户是否有权限登录
     * @param user
     * @return
     */
    public User findUserByLogin(User user){
        return userDao.findUserByLogin(user);
    }
    /**
     * 根据id修改用户
     * @param user
     * @return
     */
    public boolean updateUserById(User user){
        return userDao.updateUserById(user);
    }
    /**
     * 根据id修改用户头像路径
     * @param user
     * @return
     */
    public boolean updateImgById(User user){
        return userDao.updateImgById(user);
    }
    /**
     * 根据用户的id更新用户登录时间
     * @param id
     * @return
     */
    public boolean updateDateById(int id){
        return userDao.updateDateById(id);
    }
    /**
     * 判断该用户名是否已用
     * @param username
     * @return
     */
    public boolean findUserByName(String username){
       return userDao.findUserByName(username);
    }
    /**
     * 插入一个新的用户
     * @param user
     * @return
     */
    public boolean addUser(User user){
        return userDao.addUser(user);
    }
    /**
     * 获取首页展示用户信息放入指定路径
     */
    public void getUserToShowDisk() throws Exception {
        // F:\workspace\AUSTOJ\target\austoj\static\json\catelog.json
        String path = System.getProperty("web.root")+"static"+ File.separator+"json"+File.separator;
        String filename = "user.json";
        List<User> lists = userDao.findUserToShow();
        String content = JSON.toJSONString(lists);
        try {
            FileUtil.saveToDisk(path,filename, content);
            logger.info("获取展示用户,存放到本地磁盘user.json中");
        } catch (IOException e) {
            logger.error("获取展示用户出错",e);
            e.printStackTrace();
        }
    }

    /**
     * 初始化执行该方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        getUserToShowDisk();
    }
}
