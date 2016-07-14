package cn.edu.aust.dao;

import cn.edu.aust.entity.Catelog;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目列别DAO,处理题目分类和数据库有关的操作
 */
@Repository("catelogDao")
public class CatelogDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 获取全部目录
     * @return
     */
    public List<Catelog> findAsideCatelog(){
        return sqlSessionTemplate.selectList("catelogMapper.findAsideCatelog");
    }
}
