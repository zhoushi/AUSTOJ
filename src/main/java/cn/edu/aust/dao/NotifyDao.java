package cn.edu.aust.dao;

import cn.edu.aust.entity.Notify;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理具体通知和数据库的DAO
 */
@Repository("notifyDao")
public class NotifyDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 得到要展示的通知
     * @return
     */
    public List<Notify> findAsideNotify(){
        return sqlSessionTemplate.selectList("notifyMapper.findAsideNotify");
    }
}
