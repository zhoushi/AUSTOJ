package cn.edu.aust.service;

import cn.edu.aust.dao.UserDao;
import cn.edu.aust.entity.User;
import cn.edu.aust.util.FileUtil;
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

    public void getUserToShowDisk() {
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

    @Override
    public void afterPropertiesSet() throws Exception {
        getUserToShowDisk();
    }
}
