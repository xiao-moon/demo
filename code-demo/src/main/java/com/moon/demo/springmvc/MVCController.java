package com.moon.demo.springmvc;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-13 15:42
 */
@RestController
@EnableSwagger2//启动swagger,可以写在启动类上;访问地址为http://地址:端口/swagger-ui.html
public class MVCController {


    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++++++以下是接收前台传参的一些用法+++++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    /**
     * 接收请求路径中占位符的值，映射url片段到java方法的参数
     */
    @GetMapping(value = "/user/{id}")
    public Object getUser(@PathVariable Integer id) {
        return "测试PathVariable";
    }


    /**
     * 当HTTP的Content-Type为application/x-www-form-urlencoded时【即表单默认的提交数据的格式】，get和post都适用
     * 当HTTP的Content-Type为multipart/form-data时【即上传文件类型格式】
     */
    @PostMapping(value = "")
    public Object postUser(@RequestParam(value = "phoneNum", required = true) String phoneNum, String userName) {
        return "测试RequestParam";
    }


    /**
     * 一般用于处理非 Content-Type: application/x-www-form-urlencoded编码格式的数据，
     * 比如：application/json、application/xml等类型的数据。
     * 且必须是基于post请求的
     */
    @PostMapping(value = "/testUser")
    public Object testUser(@RequestBody User user) {
        System.out.print(user.getAge());
        return "测试RequestBody";
    }
    /*-------------------------------------------------------------------------------*/
    /*----------------------------以上是接收前台传参的一些用法---------------------------*/
    /*-------------------------------------------------------------------------------*/









    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++以下是后台传参给前台的一些用法++++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    /**
     * 使用Map、Model、ModelMap和HttpServletRequest的方式
     */
    @RequestMapping("/test")
    public String test(Map<String, Object> map, Model model, ModelMap modelMap, HttpServletRequest request) {
        //1.放在map里
        map.put("names", Arrays.asList("caoyc", "zhh", "cjx"));
        //2.放在model里 建议使用
        model.addAttribute("time", new Date());
        //3.放在request里
        request.setAttribute("request", "requestValue");
        //4.放在modelMap中
        modelMap.addAttribute("city", "ChengDu");//会检查key是否为空
        modelMap.put("gender", "male");//不会检查key是否为空
        return "hello";
    }


    /**
     * 使用ModelAndView的方式
     */
    @RequestMapping(value = "/test2.do", method = RequestMethod.POST)
    public ModelAndView checknameIsExist2(@RequestParam("sid") String sid, Model model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("ModelAndView", "ModelAndViewValue");
        //设置要跳转的页面，与返回值时String时返回success类似，以下跳转到/student/studentList.jsp
        mav.setViewName("/student/studentList");
        return mav;
    }

    /*-------------------------------------------------------------------------------*/
    /*---------------------------以上是后台传参给前台的一些用法--------------------------*/
    /*-------------------------------------------------------------------------------*/







    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++++++以下Controller间重定向跳转参数传递的方法+++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    /**
     * 1、地址后面进行拼接
     */
    @RequestMapping(value = "/save")
    public String save() {
        return "redirect:update?name=zhangsan&age=23";
    }


    /**
     * 采用RedirectAttributes 对象的 addFlashAttribute()
     * 此方法不会在url出现要传递的数据，实际上存储在session中，请求完成后立即删除session。
     */
    //传递参数
    @RequestMapping(value = "/redirect")
    public String red(RedirectAttributes attributes) {
        attributes.addFlashAttribute("test", "hello");
        return "redirect:/getRedirect";
    }

    //接收参数
    @RequestMapping(value = "/getRedirect")
    public String test2(@ModelAttribute("test") String str) {
        System.out.println(str);
        return "/test/hello";
    }

    /*-------------------------------------------------------------------------------*/
    /*---------------------------以上Controller间重定向跳转参数传递的方法----------------*/
    /*-------------------------------------------------------------------------------*/






    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*+++++++++++++++++++++以上Controller间请求转发跳转参数传递的方法++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    @RequestMapping(value = "/save")
    public String forward1(HttpServletRequest request) {
        request.setAttribute("hello","hello");
        return "redirect:update?name=zhangsan&age=23";
    }

    /*-------------------------------------------------------------------------------*/
    /*---------------------以上Controller间请求转发跳转参数传递的方法--------------------*/
    /*-------------------------------------------------------------------------------*/








    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++++++以下是RESTFUL风格的用法++++++++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    /**
     * 获取资源
     * :\d+为正则表达式:传入参数必须为数字
     */
    @GetMapping(value = "/getRequest/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)//使用jsonView指定输出的视图
    @ApiOperation(value = "测试GET请求")//用于方法的描述
    public Object getRequest(@ApiParam(value = "swager2针对基本数据类型的备注")
                             @PathVariable(name = "id", required = true) Integer id) {
        return "GET请求";
    }

    /**
     * 新增资源
     */
    @PostMapping(value = "/postRequest")
    public Object postRequest(@Valid @RequestBody User user, BindingResult error) {
        //BindingResult配合@Valid一起使用,将错误信息直接返回回去
        if (error.hasErrors()) {
            return "密码不能为空";
        }
        return "POST请求";
    }

    /**
     * 更新资源
     * post不需要指定被创建资源的id，而put需要指定
     */
    //@PutMapping(value = "/putRequest/{id}")
    @PutMapping(value = "/putRequest")
    public Object putRequest(@RequestBody User user) {
        return "PUT请求";
    }

    /**
     * 删除资源
     */
    @DeleteMapping(value = "/deleteRequest/{id}")
    public Object deleteRequest(@PathVariable(name = "id", required = true) Integer id) {
        return "DELETE请求";
    }
    /*-------------------------------------------------------------------------------*/
    /*----------------------------以上是RESTFUL风格的用法------------------------------*/
    /*-------------------------------------------------------------------------------*/

}
