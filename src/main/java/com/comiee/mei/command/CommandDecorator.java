package com.comiee.mei.command;

public abstract class CommandDecorator extends Command {
    protected final Command command;

    CommandDecorator(Command command) {
        super(command.cmd, command.fun);
        this.command = command;
    }
}
