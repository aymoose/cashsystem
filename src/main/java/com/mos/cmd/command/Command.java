package com.mos.cmd.command;

import java.util.Scanner;


public interface Command {

    Scanner scanner = new Scanner(System.in);

    void execute(Subject subject);
}
