package com.mos.cmd.command.impl.common;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.annotation.CustomerCommand;
import com.mos.cmd.command.annotation.EntranceCommand;
import com.mos.cmd.command.impl.AbstractCommand;


@CommandMeta(
        name = "GYXT",
        desc = "关于系统",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class AboutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("关于系统");
        System.out.println("作者：MOS");
        System.out.println("时间：20190803");
    }
}
