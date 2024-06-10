package com.comiee.mei.command;

import java.util.Hashtable;
import java.util.Map;

public class TimesLimitCommand extends CommandDecorator {
    private final int limit;
    private final Map<Long, Integer> timesMap;

    TimesLimitCommand(int limit, Command command) {
        super(command);
        this.limit = limit;
        this.timesMap = new Hashtable<>();
    }

    @Override
    boolean judge(Session session) {
        int times = timesMap.getOrDefault(session.id(), limit);
        return times > 0 && command.judge(session);
    }

    @Override
    void run(Session session) {
        int times = timesMap.getOrDefault(session.id(), limit);
        timesMap.put(session.id(), times - 1);
        System.out.print("TimesLimitCommand(" + times + ") ");
        command.run(session);
    }
}
