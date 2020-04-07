package com.moon.demo.golf23.u_chain;

/**
 * @Description:客户端
 * @Author：xiaojiaxin
 * @Date：2020-04-07 15:24
 */
public class F_Client {
    public static void main(String[] args) {
        B_LeaveHandler a = new C_DirectorHandler("张三");
        B_LeaveHandler b = new D_ManagerHandler("李四");
        B_LeaveHandler c = new E_CEOHandler("王五");

        a.setNextLeader(b);
        b.setNextLeader(c);

        A_LeaveRequest leaveRequest = new A_LeaveRequest("TOM", 3, "回家相亲");
        a.handleRequest(leaveRequest);

    }





}
