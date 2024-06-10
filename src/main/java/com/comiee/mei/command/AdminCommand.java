package com.comiee.mei.command;

public class AdminCommand extends CommandDecorator {

    AdminCommand(Command command) {
        super(command);
    }

    @Override
    boolean judge(Session session) {
        return session.id() == 1234 && command.judge(session);
    }

    @Override
    void run(Session session) {
        System.out.print("AdminCommand ");
        try {
            command.run(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
