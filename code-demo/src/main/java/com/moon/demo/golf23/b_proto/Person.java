package com.moon.demo.golf23.b_proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-14 23:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    public String name;


}
