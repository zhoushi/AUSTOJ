package cn.edu.aust.controller;

import cn.edu.aust.dao.UserDao;
import cn.edu.aust.entity.User;
import cn.edu.aust.util.FileUtil;
import cn.edu.aust.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体和用户的控制器
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 查询出用户排名
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/userRank",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> findUserRank(@RequestBody PageUtil pageUtil){
        Map<String,Object> maps = new HashMap<>();
        PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
        List<User> lists = userDao.findUserRank(pageUtil);
        PageInfo<User> info = new PageInfo<>(lists);
        maps.put("total",info.getTotal());
        maps.put("rows",lists);
        return maps;
    }

    /**
     * 获取指定用户的详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView UserDeatil(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        if (id <= 0){
            model.addObject("error","该用户不存在");
        }else {
            User user = userDao.findUserDetail(id);
            List<Integer> userAC = userDao.findUserACPro(id);
            List<Integer> userBeingAC = userDao.findUserBeingAC(id);
            userBeingAC.removeAll(userAC);
            model.addObject("user",user);
            model.addObject("userAC",userAC);
            model.addObject("userBeingAC",userBeingAC);
        }
        return model;
    }

    /**
     * 用户自助修改资料
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView updateUser(User user, HttpSession session){
        ModelAndView model = new ModelAndView();
        User loginUser = (User) session.getAttribute("userLogin");
        if (loginUser.getId() != user.getId()){
            model.setViewName("404");
            return model;
        }
        if (!userDao.updateUserById(user)){
            logger.error("用户自助更新用户资料失败:"+user.getUsername());
        }
        //更新session
        user  = userDao.findUserDetail(user.getId());
        session.setAttribute("userLogin",user);
        model.setViewName("redirect:/user/"+user.getId());
        return model;
    }
    /**
     * 用户自助修改头像
     * @return
     */
    @RequestMapping(value = "/updateimg",method = RequestMethod.POST)
    public ModelAndView updateUserImg( User user,MultipartFile pictureFile, HttpSession session){
        ModelAndView model = new ModelAndView();
        User loginUser = (User) session.getAttribute("userLogin");
        if (user.getId() != loginUser.getId()){
            model.setViewName("404");
            return model;
        }
        if (pictureFile.getSize()>1242880){
            model.setViewName("404");
            model.addObject("error","图片尺寸过大,无法上传");
            return model;
        }
        String filesuffix = pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
        String newFileDir = System.getProperty("web.root")+ File.separator+"/uploadimg/";
        String fileName = user.getId()+filesuffix;
        try {
            FileUtil.saveImgToDisk(newFileDir,fileName,pictureFile);
            //更新到数据库
            user.setAvatar("/uploadimg/"+fileName);
            userDao.updateImgById(user);
            //更新session
            loginUser = userDao.findUserDetail(user.getId());
            session.setAttribute("userLogin",loginUser);
            model.setViewName("redirect:/user/"+user.getId());
        } catch (IOException e) {
            logger.error(user.getId()+"上传头像失败",e);
            e.printStackTrace();
        }
        return model;
    }

}
