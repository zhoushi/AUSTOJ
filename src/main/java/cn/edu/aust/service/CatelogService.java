package cn.edu.aust.service;

import cn.edu.aust.dao.CatelogDao;
import cn.edu.aust.entity.Catelog;
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
 * 题目分类的service,处理catelog的一些逻辑操作
 */
@Service("catelogService")
public class CatelogService implements InitializingBean {
    //日志记录器
    private static Logger logger = Logger.getLogger(CatelogService.class);

    @Resource(name = "catelogDao")
    private CatelogDao catelogDao;

    /**
     * 获取类别记录存放到本地磁盘
     */
    public void getAllCateToDisk() {
//        F:\workspace\AUSTOJ\target\austoj\static\json\catelog.json
        String path = System.getProperty("web.root")+"static"+File.separator+"json"+File.separator;
        String filename = "catelog.json";
        List<Catelog> lists = catelogDao.findAsideCatelog();
        String content = JSON.toJSONString(lists);
        try {
            FileUtil.saveToDisk(path,filename, content);
            logger.info("获取全部目录,存放到本地磁盘catelog.json中");
        } catch (IOException e) {
            logger.error("获取全部目录出错",e);
            e.printStackTrace();
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //spring加载完成后执行
        getAllCateToDisk();
    }
}
