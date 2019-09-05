package com.mos.cmd.command.impl.common;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.annotation.CustomerCommand;
import com.mos.cmd.command.annotation.EntranceCommand;
import com.mos.cmd.command.impl.AbstractCommand;

@CommandMeta(
        name = "TCXT",
        desc = "退出系统",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("退出系统,欢迎下次使用");
        this.scanner.close();
        System.exit(0);//正常退出
    }
}
