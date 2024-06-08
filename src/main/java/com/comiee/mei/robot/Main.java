package com.comiee.mei.robot;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

public class Main {
    public static void main(String[] args) {
        Bot bot = BotFactory.INSTANCE.newBot(3031315187L, BotAuthorization.byQRCode(), new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH); // 切换协议
        }});
        bot.login();

        Main.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 1440667228;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .build()
                );
            }
        });
    }
}
