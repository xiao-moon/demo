package mock.b_listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:测试系统初始化器
 * 创建人: 小月
 * 创建时间: 2020-08-06 22:41
 */
@RestController
@RequestMapping("/init")
public class B_Controller {

    @Autowired
    private C_Service service;

    @RequestMapping("/first")
    public Object first() {
        return service.first();
    }

    @RequestMapping("/second")
    public Object second() {
        return service.second();
    }

    @RequestMapping("/third")
    public Object third() {
        return service.third();
    }

}
