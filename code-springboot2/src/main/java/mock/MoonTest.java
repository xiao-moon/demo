package mock;

import mock.initializer.B_Controller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
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
 * 描述:测试用法
 * 创建人: 小月
 * 创建时间: 2020-08-05 23:57
 */
//@WebMvcTest()
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc //启动自动配置MockMVC
public class MoonTest {

    @Autowired
    private MockMvc mockMvc; //只需 autowire

//    @Autowired
//    private B_Controller controller;

    @org.junit.Test
    public void testFirst() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/init/first").contentType(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = actions.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);

    }


    @org.junit.Test
    public void testEquipmenttypes() throws Exception {
        MvcResult authResult = null;
        authResult = mockMvc.perform(get("/api/workitem/equipmenttypes")//使用get方式来调用接口。
                .contentType(MediaType.APPLICATION_XHTML_XML)//请求参数的类型
                .param("sessionid", "ZlbpLxXw")//请求的参数（可多个）
        ).andExpect(status().isOk())
                .andReturn();
        //获取数据
        JSONObject jsonObject =new  JSONObject(authResult.getResponse().getContentAsString());
        JSONArray jsonArrayData = (JSONArray)jsonObject.get("data");

        //获取第一个Array中的值,判断查询到的结果。
        JSONObject jsonObject_data = null;
        if(jsonArrayData.length()>0){
            jsonObject_data = (JSONObject) jsonArrayData.get(0);
        }
        //加断言，判断属性值的问题。
        Assert.assertNotNull(jsonObject.get("error_code"));
        Assert.assertEquals(jsonObject.get("error_code"),0);
        Assert.assertNotNull(jsonObject.get("error_msg"));
        Assert.assertEquals(jsonObject.get("error_msg"),"操作成功");
        Assert.assertNotNull(jsonObject.get("data"));
        Assert.assertNotNull(jsonObject_data);
        Assert.assertEquals(jsonObject_data.get("equipmentty"),1);
        Assert.assertEquals(jsonObject_data.get("equipmenttypename"),"xxxxx");
    }

}
