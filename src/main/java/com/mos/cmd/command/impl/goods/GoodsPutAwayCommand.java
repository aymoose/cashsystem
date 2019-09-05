package com.mos.cmd.command.impl.goods;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.impl.AbstractCommand;
import com.mos.entity.Goods;


@CommandMeta(
        name = "SJSP",
        desc = "上架商品",
        group = "商品信息"
)

@AdminCommand

public class GoodsPutAwayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("上架商品");
        System.out.println("请输入商品名称：");
        String name = scanner.next();
        System.out.println("请输入商品简介：");
        String introduce = scanner.next();
        System.out.println("请输入商品单位：个，包，箱，瓶，千克");
        String unit = scanner.next();
        System.out.println("请输入商品库存：");
        int stock = scanner.nextInt();
        System.out.println("请输入商品价格：单位：元");
        //存入之前 把小数转换为整数存放到数据库  取的时候除以100就行
        int price = new Double(100 * scanner.nextDouble()).intValue();
        System.out.println("请输入商品折扣：75表示75折");
        int discount = scanner.nextInt();

        Goods goods = new Goods();
        goods.setName(name);
        goods.setIntroduce(introduce);
        goods.setStock(stock);
        goods.setUnit(unit);
        goods.setPrice(price);
        goods.setDiscount(discount);

        boolean effect = this.goodsService.putAwayGoods(goods);
        if(effect) {
            System.out.println("上架成功");
        }else{
            System.out.println("上架失败");
        }
    }
}
