package cn.edu.aust.service;

import cn.edu.aust.dao.NotifyDao;
import cn.edu.aust.entity.Notify;
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
 * 具体有关通知的策略类
 */
@Service("notifyService")
public class NotifyService implements InitializingBean{
    //日志管理器
    private Logger logger = Logger.getLogger(NotifyService.class);

    @Resource(name = "notifyDao")
    private NotifyDao notifyDao;

    /**
     * 获取类别记录存放到本地磁盘
     */
    public void getAllNotifyToDisk() {
//        F:\workspace\AUSTOJ\target\austoj\static\json\catelog.json
        String path = System.getProperty("web.root")+"static"+ File.separator+"json"+File.separator;
        String filename = "notify.json";
        List<Notify> lists = notifyDao.findAsideNotify();
        String content = JSON.toJSONString(lists);
        try {
            FileUtil.saveToDisk(path,filename, content);
            logger.info("获取全部目录,存放到本地磁盘notify.json中");
        } catch (IOException e) {
            logger.error("获取全部通知出错",e);
            e.printStackTrace();
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化后,加载通知类
        getAllNotifyToDisk();
    }
}
