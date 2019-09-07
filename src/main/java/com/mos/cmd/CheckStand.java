package com.mos.cmd;

import com.mos.cmd.command.Subject;
import com.mos.cmd.command.impl.AbstractCommand;
import com.mos.cmd.command.impl.Commands;
import com.mos.entity.Account;


public class CheckStand extends AbstractCommand {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new CheckStand().execute(subject);
    }


    @Override
    public void execute(Subject subject) {
        //System.out.println("帮助命令"); 先把进入程序的命令进行缓存
        Commands.getCachedHelpCommands().execute(subject);
        while(true) {
            System.out.println(">>");
            String line = scanner.nextLine();
            String commandCode = line.trim().toUpperCase();
            Account account = subject.getAccount();
            if(account == null) {
                Commands.getEntranceCommand(commandCode).execute(subject);
            }else {
                //System.out.println("account != null");
                switch (account.getAccountType()) {
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).execute(subject);
                        break;
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).execute(subject);
                        break;
                    default:
                }
            }
        }
    }
}
