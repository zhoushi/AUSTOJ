package cn.edu.aust.dao;

import cn.edu.aust.entity.Tags;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 侧边栏标签类的dao
 */
@Repository("tagsDao")
public class TagsDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查找出全部的tags,按照count从大到小
     * @return
     */
    public List<Tags> findAsideTags(){
        return sqlSessionTemplate.selectList("tagsMapper.findAsideTags");
    }

    /**
     * 判断是否存在当前tags
     * @param tag
     * @return
     */
    public Tags findTagByName(String tag){
        return sqlSessionTemplate.selectOne("tagsMapper.findTagByName",tag);
    }

    /**
     * 更新一个tag
     * @param tag
     * @return
     */
    public boolean updateTagById(Tags tag){
        int k = sqlSessionTemplate.update("tagsMapper.updateTagById",tag);
        return k>0;
    }

    /**
     * 插入一个tag
     * @param tag
     * @return
     */
    public boolean addTag(String tag){
        int k = sqlSessionTemplate.insert("tagsMapper.addTag",tag);
        return k>0;
    }

}
