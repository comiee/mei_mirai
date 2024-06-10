package com.comiee.mei.command;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private final List<Command> commandList;

    CommandHandler() {
        this.commandList = new ArrayList<>();
    }

    void register(Command command) {
        commandList.add(command);
    }

    void handle(Session session) {
        for (Command command : commandList) {
            if (command.judge(session)) {
                command.run(session);
                break;
            }
        }
    }
}
