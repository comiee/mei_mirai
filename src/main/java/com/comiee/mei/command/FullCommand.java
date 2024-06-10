package com.comiee.mei.command;

import java.util.function.Consumer;

public class FullCommand extends Command {

    FullCommand(String cmd, Consumer<Session> fun) {
        super(cmd, fun);
    }

    @Override
    boolean judge(Session session) {
        return cmd.equals(session.text());
    }

    @Override
    void run(Session session) {
        System.out.println("FullCommand " + cmd);
        fun.accept(session);
    }
}
