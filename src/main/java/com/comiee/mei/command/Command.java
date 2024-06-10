package com.comiee.mei.command;

import java.util.function.Consumer;

public abstract class Command {
    protected final String cmd;
    protected final Consumer<Session> fun;

    Command(String cmd, Consumer<Session> fun) {
        this.cmd = cmd;
        this.fun = fun;
    }

    abstract boolean judge(Session session);

    abstract void run(Session session);
}
