package com.mos.cmd.command.impl.order;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.annotation.CustomerCommand;
import com.mos.cmd.command.impl.AbstractCommand;
import com.mos.entity.Order;

import java.util.List;


@CommandMeta(
        name = "CKDD",
        desc = "查看订单",
        group = "我的订单"
)
@CustomerCommand
public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("我的订单列表：");
        List<Order> orderList = this.orderService.queryOrderByAccount(subject.getAccount().getId());
        if (orderList.isEmpty()) {
            System.out.println("暂时没有订单");
        } else {
            for (Order order : orderList) {
                System.out.println("-------------------- 开始分割线 ------------------------");
                System.out.println(order);
                System.out.println("-------------------- 结束分割线 ------------------------");

            }
        }
    }
}
