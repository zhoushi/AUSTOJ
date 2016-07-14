package cn.edu.aust.service;

import cn.edu.aust.dao.TagsDao;
import cn.edu.aust.entity.Tags;
import cn.edu.aust.util.FileUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 侧边栏tags的策略类
 */
@Service("tagsService")
public class TagsService{

    //日志记录器
    private Logger logger = Logger.getLogger(TagsService.class);

    @Resource(name = "tagsDao")
    private TagsDao tagsDao;

    @Scheduled(fixedRate = 1000*3600*2)
    public void getAlTagsDisk() {
        // F:\workspace\AUSTOJ\target\austoj\static\json\catelog.json
        String path = System.getProperty("web.root")+"static"+ File.separator+"json"+File.separator;
        String filename = "tags.json";
        List<Tags> lists = tagsDao.findAsideTags();
        String content = JSON.toJSONString(lists);
        try {
            FileUtil.saveToDisk(path,filename, content);
            logger.info("获取全部标签,存放到本地磁盘tags.json中");
        } catch (IOException e) {
            logger.error("获取全部标签出错",e);
            e.printStackTrace();
        }
    }

    /**
     * 记录用户搜索的字段,每次搜索相同字段,count++
     * @param tagname
     */
    public void updateOrInsertTag(String tagname){
        Tags tags = tagsDao.findTagByName(tagname);
        if (tags != null){
            tags.setCount(tags.getCount()+1);
            if (!tagsDao.updateTagById(tags)){
                logger.error("更新标签:"+tags.getTag()+"--失败");
            }
        }else {
            if (!tagsDao.addTag(tagname)){
                logger.error("插入标签:"+tagname+"--失败");
            }
        }
    }
}
