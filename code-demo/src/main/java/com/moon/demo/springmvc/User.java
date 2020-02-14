package com.moon.demo.springmvc;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-13 16:09
 */
public class User {

    /**
     *@JsonView使用步骤
     * 使用接口来声明多个视图
     * 在值对象的get方法上指定视图
     * 在Controller方法上指定视图
     */
    /*JsonView*/
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    @ApiModelProperty(value = "swager2用于对参数属性的备注")
    private Integer id;

    @ApiModelProperty(value = "swager2用于对参数属性的备注")
    private Integer age;

    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past
    private Date birthday;//保存格式为时间戳，传到前台后由前台来决定转换为什么样的展示形式


    @JsonView(UserSimpleView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
