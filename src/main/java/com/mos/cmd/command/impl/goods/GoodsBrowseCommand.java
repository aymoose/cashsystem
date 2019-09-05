package com.mos.cmd.command.impl.goods;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.annotation.CustomerCommand;

import com.mos.cmd.command.impl.AbstractCommand;
import com.mos.entity.Goods;


import java.util.List;


@CommandMeta(
        name = "LLSP",
        desc = "浏览商品",
        group = "商品信息"
)

@AdminCommand
@CustomerCommand
public class GoodsBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("商品浏览");
        List<Goods> goodsList = this.goodsService.queryAllGoods();

        if(goodsList.isEmpty()) {
            System.out.println("没有任何的商品");
        }else {
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }
}
