package cn.edu.aust.dao;

import cn.edu.aust.entity.Article;
import cn.edu.aust.util.PageUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 *关于文章处理的dao
 */
@Repository("articleDao")
public class ArticleDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 得到侧边栏展示的最近文章
     * @return
     */
    public List<Article> findAsideArticle(){
        return sqlSessionTemplate.selectList("articleMapper.findAsideArticle");
    }

    /**
     * 查找用于列表展示的文章
     * @param pageUtil
     * @return
     */
    public List<Article> findAllArticle(PageUtil pageUtil){
        return sqlSessionTemplate.selectList("articleMapper.findAllArticle",pageUtil);
    }

    /**
     *
     * @param id
     * @return
     */
    public Article findArticleById(int id){
        return sqlSessionTemplate.selectOne("articleMapper.findArticleById",id);
    }
}
