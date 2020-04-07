package com.moon.demo.golf23.u_chain;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-04-07 15:21
 */
public class D_ManagerHandler extends B_LeaveHandler {

    public D_ManagerHandler(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void handleRequest(A_LeaveRequest request) {
        if (request.getLeaveDays() < 7 && request.getLeaveDays() >= 3) {
            System.out.println("员工：" + request.getEmpName() + "请假，天数：" + request.getLeaveDays() + ",理由：" + request.getReason());
            System.out.println("经理：" + this.name + ",审批通过！");
        } else {
            if (this.nextLeader != null) {
                System.out.println(super.name+"--审批不通过");
                this.nextLeader.handleRequest(request);
            }
        }

    }
}
