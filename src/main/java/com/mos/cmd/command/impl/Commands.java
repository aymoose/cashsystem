package com.mos.cmd.command.impl;

import com.mos.cmd.command.Command;
import com.mos.cmd.command.annotation.AdminCommand;
import com.mos.cmd.command.annotation.CommandMeta;
import com.mos.cmd.command.annotation.CustomerCommand;
import com.mos.cmd.command.annotation.EntranceCommand;
import com.mos.cmd.command.impl.account.AccountBrowseCommand;
import com.mos.cmd.command.impl.account.AccountPasswordResetCommand;
import com.mos.cmd.command.impl.account.AccountStatusSetCommand;
import com.mos.cmd.command.impl.common.AboutCommand;
import com.mos.cmd.command.impl.common.HelpCommand;
import com.mos.cmd.command.impl.common.QuitCommand;
import com.mos.cmd.command.impl.entrance.LoginCommand;
import com.mos.cmd.command.impl.entrance.RegisterCommand;
import com.mos.cmd.command.impl.goods.GoodsBrowseCommand;
import com.mos.cmd.command.impl.goods.GoodsPutAwayCommand;
import com.mos.cmd.command.impl.goods.GoodsSoldOutCommand;
import com.mos.cmd.command.impl.goods.GoodsUpdateCommand;
import com.mos.cmd.command.impl.order.OrderBrowseCommand;
import com.mos.cmd.command.impl.order.OrderPayCommand;

import java.util.*;



public class Commands {
    //    先将三种命令分别存放在对应的Map集合中
    public static Map<String, Command> ADMIN_COMMANDS = new HashMap<>();
    public static Map<String, Command> CUSTOMER_COMMANDS = new HashMap<>();
    public static Map<String, Command> ENTRANCE_COMMANDS = new HashMap<>();
    //存放所有命令的集合
    private static final Set<Command> COMMANDS = new HashSet<>();

    private static final Command CACHED_HELP_COMMANDS;

    static {
        Collections.addAll(COMMANDS,
                new AccountBrowseCommand(),
                new AccountPasswordResetCommand(),
                new AccountStatusSetCommand(),
                new AboutCommand(),
                //将helpCommand进行缓存
                CACHED_HELP_COMMANDS = new HelpCommand(),
                new HelpCommand(),
                new QuitCommand(),
                new LoginCommand(),
                new RegisterCommand(),
                new GoodsBrowseCommand(),
                new GoodsPutAwayCommand(),
                new GoodsSoldOutCommand(),
                new GoodsUpdateCommand(),
                new OrderBrowseCommand(),
                new OrderPayCommand());
        for (Command command : COMMANDS) {
            //利用反射，将命令进行分类到不同的map
            Class<?> cls = command.getClass();
            AdminCommand adminCommand = cls.getDeclaredAnnotation(AdminCommand.class);
            CustomerCommand customerCommand = cls.getDeclaredAnnotation(CustomerCommand.class);
            EntranceCommand entranceCommand = cls.getDeclaredAnnotation(EntranceCommand.class);
            CommandMeta commandMeta = cls.getDeclaredAnnotation(CommandMeta.class);
            if (commandMeta == null) {
                continue;
            }
            String commandKey = commandMeta.name();
            if (adminCommand != null) {
                ADMIN_COMMANDS.put(commandKey, command);
            }
            if (customerCommand != null) {
                CUSTOMER_COMMANDS.put(commandKey, command);
            }
            if (entranceCommand != null) {
                ENTRANCE_COMMANDS.put(commandKey, command);
            }
        }
    }

    //得到缓存的命令帮助
    public static Command getCachedHelpCommands() {
        return CACHED_HELP_COMMANDS;
    }

    public static Command getAdminCommand(String commandKey) {
        return getCommand(commandKey, ADMIN_COMMANDS);
    }

    public static Command getCustomerCommand(String commandKey) {
        return getCommand(commandKey, CUSTOMER_COMMANDS);
    }

    public static Command getEntranceCommand(String commandKey) {
        return getCommand(commandKey, ENTRANCE_COMMANDS);
    }

    public static Command getCommand(String commandKey, Map<String, Command> commandMap) {

        //遍历相应的Map，根据commandkey得到对应的value
        return commandMap.getOrDefault(commandKey, CACHED_HELP_COMMANDS);
    }

}
