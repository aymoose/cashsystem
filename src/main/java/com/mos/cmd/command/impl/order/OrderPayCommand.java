package com.mos.cmd.command.impl.order;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.annotation.CustomerCommand;
import com.mos.cmd.command.impl.AbstractCommand;
import com.mos.common.OrderStatus;
import com.mos.entity.Goods;
import com.mos.entity.Order;
import com.mos.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@CommandMeta(
        name = "ZFDD",
        desc = "支付订单",
        group = "我的订单"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {

    @Override
    public void execute(Subject subject) {//货物id   账户List<Goods>,String int userid,userAccout
        System.out.println("请输入你要购买的货物id以" +
                "及数量多个货物之间使用,号隔开：格式：1-8,3-5");
        String string = scanner.nextLine();
        String[] strings = string.split(",");

        List<Goods> goodsList =  new ArrayList<>();

        for (String str : strings ) {//1-8
            String[] strings2 = str.split("-");
            Goods goods = this.goodsService.getGoods(Integer.parseInt(strings2[0]));
            goods.setBuyNum(Integer.parseInt(strings2[1]));
            goodsList.add(goods);
        }

        Order order = new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccountId(subject.getAccount().getId());
        order.setAccountName(subject.getAccount().getName());
        order.setCreateTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PAYING);

        int totalMoney = 0;
        int actualAmount = 0;
        //计算需要的总金额
        for (Goods goods : goodsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId( goods.getId());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPrice());
            orderItem.setGoodsDiscount(goods.getDiscount());
            orderItem.setGoodsNum(goods.getBuyNum());
            orderItem.setGoodsIntroduce(goods.getIntroduce());

            order.getOrderItemList().add(orderItem);

            int currentMoney = goods.getPrice()*goods.getBuyNum();
            totalMoney += currentMoney;
            actualAmount += currentMoney*goods.getDiscount() / 100;
        }

        order.setActualAmount(actualAmount);
        order.setTotalMoney(totalMoney);

        /*
         * 加入订单项当中
         * */
        System.out.println(order);
        System.out.println("以上为订单信息，请支付：zf");
        String confirm = scanner.next();
        if("zf".equalsIgnoreCase(confirm)) {
            order.setFinishTime(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.OK);

            boolean effect = this.orderService.commitOrder(order);

            if(effect) { //说明插入订单和订单项成功
                //将goods表中的 具体货物数量更新
                for (Goods goods : goodsList) {
                    boolean isUpdate = this.goodsService.updateGoodsAfterPay(goods,goods.getBuyNum());
                    if(isUpdate) {
                        System.out.println("库存已经更新");
                    }
                }
            }
        }else {
            System.out.println("您未成功支付！");
        }
    }
}
