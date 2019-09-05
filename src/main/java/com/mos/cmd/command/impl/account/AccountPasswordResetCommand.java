package com.mos.cmd.command.impl.account;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.impl.AbstractCommand;


@CommandMeta(
        name = "CZMM",
        desc = "重置密码",
        group = "账号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("密码重置");
    }
}
