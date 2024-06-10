package com.comiee.mei.command;

public class testCommand {
    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler();
        handler.register(new AdminCommand(new TimesLimitCommand(3, new FullCommand("test",
                session -> System.out.println(session.id() + "->" + session.text())
        ))));
        handler.register(new NormalCommand("test",
                session -> System.out.println(session.id() + "->" + session.text())
        ));

        Session session1 = new Session(1234, "test 666");
        handler.handle(session1);
        Session session2 = new Session(1234, "test");
        handler.handle(session2);
        handler.handle(session2);
        handler.handle(session2);
        handler.handle(session2);
    }
}
