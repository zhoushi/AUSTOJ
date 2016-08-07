package cn.edu.aust.controller;

import cn.edu.aust.entity.Article;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.TagsService;
import cn.edu.aust.util.Contants;
import cn.edu.aust.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 具体关于文章的控制器
 */
@Controller
public class ArticleController {
    @Resource(name = "articleService")
    private ArticleService articleService;
    @Resource(name = "tagsService")
    private TagsService tagsService;
    /**
     * 前往文章列表,可以附带搜索条件
     */
    @RequestMapping(value = "/articles",method = RequestMethod.GET)
    public String toArticleList(Model model,PageUtil pageUtil){
        //开始分页
        pageUtil.setLimit(Contants.ARTICLE_NUM);
        PageHelper.startPage(pageUtil.getOffset(),pageUtil.getLimit());

        List<Article> lists = articleService.findAllArticle(pageUtil);

        //分析结果
        PageInfo<Article> info = new PageInfo<>(lists);
        model.addAttribute("pageinfo",info);

        if (pageUtil.getSearch() != null){
            model.addAttribute("search",pageUtil.getSearch());
            //记录搜索内容
            if (pageUtil.getSearch().length()-2 <= Contants.ARTICLE_SEARCH__NUM){
               tagsService.updateOrInsertTag(pageUtil.getSearch().substring(1,pageUtil.getSearch().length()-1));
            }
        }
        return "articlelist";
    }

    /**
     * 查询指定id的文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/articles/{id}",method = RequestMethod.GET)
    public ModelAndView toArticleDetail(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.setViewName("article");
        if (id <= 0){
            model.addObject("error","该文章不存在");
        }else {
            Article article = articleService.findArticleById(id);
            if (article != null){
                model.addObject("article",article);
            }else {
                model.addObject("error","该文章不存在");
            }
        }
        return model;
    }

}
