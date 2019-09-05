package com.mos.cmd.command.impl.goods;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.impl.AbstractCommand;
import com.mos.entity.Goods;


@CommandMeta(
        name = "XJSP",
        desc = "下架商品",
        group = "商品信息"
)

@AdminCommand

public class GoodsSoldOutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("下架商品");
        //根据商品下架的编号，先去 查询 是否有此商品 如果有通过
       // this.goodsService.soldOutGoods(goodsId); 去操作DAO层
        //进行 删除数据库 对应项
        System.out.println("请输入下架商品编号：");
        int goodsId = scanner.nextInt();
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            warningPrintln("下架商品不存在");
        } else {
            System.out.println("下架商品如下：");
            System.out.println(goods);
            hitPrintln("确认是否下架（y/n yes/no）: ");
            String confirm = scanner.next();
            if ("y".equalsIgnoreCase(confirm) || "yes".equalsIgnoreCase(confirm)) {
                boolean effect = this.goodsService.soldOutGoods(goodsId);
                if (effect) {
                    System.out.println("商品成功下架");
                } else {
                    errorPrintln("商品下架失败，稍后重试");
                }
            }
        }
    }
}
