package mock.a_initializer;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 描述:测试
 * 创建人: 小月
 * 创建时间: 2020-08-05 23:57
 */
//@WebMvcTest()
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc //启动自动配置MockMVC
@Log
public class A_Test {

    @Autowired
    private MockMvc mockMvc; //只需 autowire

    @Test
    public void testFirst() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/init/first").contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = actions.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.info("结果:"+contentAsString);
    }

    @Test
    public void testSecond() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/init/second").contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = actions.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.info("结果:"+contentAsString);
    }

    @Test
    public void testThird() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/init/third").contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = actions.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.info("结果:"+contentAsString);
    }


}
