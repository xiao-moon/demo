package mock.b_listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 创建人: 小月
 * 创建时间: 2020-08-06 22:42
 */
@Service
public class C_Service implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String first() {
        return applicationContext.getEnvironment().getProperty("first");
    }

    public String second() {
        return applicationContext.getEnvironment().getProperty("second");
    }

    public String third() {
        return applicationContext.getEnvironment().getProperty("third");
    }
}
