package com.moon.demo.golf23.u_chain;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-04-07 15:18
 */
public abstract class B_LeaveHandler {
    protected String name;
    protected B_LeaveHandler nextLeader; //责任链上的后继对象

    public B_LeaveHandler(String name) {
        super();
        this.name = name;
    }

    //设定责任链上的后继对象
    public void setNextLeader(B_LeaveHandler nextLeader) {
        this.nextLeader = nextLeader;
    }

    /**
     * 处理请求的核心的业务方法
     */
    public abstract void handleRequest(A_LeaveRequest request);

}
