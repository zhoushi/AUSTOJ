package cn.edu.aust.service;

import cn.edu.aust.dao.ArticleDao;
import cn.edu.aust.entity.Article;
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
 * 具体Article的策略类
 */
@Service("articleService")
public class ArticleService implements InitializingBean{
    //日志记录器
    private Logger logger = Logger.getLogger(ArticleService.class);

    @Resource(name = "articleDao")
    private ArticleDao articleDao;

    /**
     * 获取类别记录存放到本地磁盘
     */
    public void getAllArticleToDisk() {
//        F:\workspace\AUSTOJ\target\austoj\static\json\catelog.json
        String path = System.getProperty("web.root")+"static"+ File.separator+"json"+File.separator;
        String filename = "article.json";
        List<Article> lists = articleDao.findAsideArticle();
        String content = JSON.toJSONString(lists);
        try {
            FileUtil.saveToDisk(path,filename, content);
            logger.info("获取全部文章,存放到本地磁盘article.json中");
        } catch (IOException e) {
            logger.error("获取全部文章出错",e);
            e.printStackTrace();
        }

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //加载容器后执行获取目录
        getAllArticleToDisk();
    }
}
