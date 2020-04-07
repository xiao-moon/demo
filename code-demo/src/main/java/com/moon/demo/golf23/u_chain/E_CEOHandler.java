package com.moon.demo.golf23.u_chain;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-04-07 15:21
 */
public class E_CEOHandler extends B_LeaveHandler {

    public E_CEOHandler(String name) {
        super(name);
    }

    @Override
    public void handleRequest(A_LeaveRequest request) {
        if (request.getLeaveDays() < 15 && request.getLeaveDays() >= 7) {
            System.out.println("员工：" + request.getEmpName() + "请假，天数：" + request.getLeaveDays() + ",理由：" + request.getReason());
            System.out.println("CEO：" + this.name + ",审批通过！");
        } else {
            if (this.nextLeader != null) {
                System.out.println(super.name+"--审批不通过");
                this.nextLeader.handleRequest(request);
            }
        }

    }
}
