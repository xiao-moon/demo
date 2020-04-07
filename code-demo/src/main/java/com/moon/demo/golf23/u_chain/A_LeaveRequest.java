package com.moon.demo.golf23.u_chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:请假信息
 * @Author：xiaojiaxin
 * @Date：2020-04-07 15:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class A_LeaveRequest {

    //请假人
    private String empName;
    //请假天数
    private int leaveDays;
    //请假原因
    private String reason;

}
