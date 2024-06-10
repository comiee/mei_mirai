package com.comiee.mei.command;

import java.util.function.Consumer;

public class NormalCommand extends Command {

    NormalCommand(String cmd, Consumer<Session> fun) {
        super(cmd, fun);
    }

    @Override
    boolean judge(Session session) {
        return session.text().startsWith(cmd);
    }

    @Override
    void run(Session session) {
        System.out.println("NormalCommand " + cmd);
        fun.accept(session);
    }
}
