import cn.edu.aust.service.CatelogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * catelog的测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class TestCatelog {
    private ApplicationContext applicationContext;
    private CatelogService catelogService;
    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        catelogService = (CatelogService) applicationContext.getBean("catelogService");
    }
    @Test
    public void test(){
    }


}
