package com.comiee.mei.robot;

import com.comiee.mei.communication.Client;
import com.comiee.mei.demo.DemoClient;
import com.comiee.mei.message.DebugMsg;
import com.google.gson.JsonElement;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

public class Main {
    public static void main(String[] args) throws Exception {
        Bot bot = BotFactory.INSTANCE.newBot(3031315187L, BotAuthorization.byQRCode(), new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_WATCH); // 切换协议
        }});
        bot.login();

        afterLogin(bot);
    }

    public static void afterLogin(Bot bot) throws Exception {
        Client client = new DemoClient();
        new Thread(client::listenServer).start();

        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            String text = event.getMessage().contentToString();
            System.out.println(text);
            String cmd = "测试 ";
            if (!text.startsWith(cmd)) return;
            if (event.getSender().getId() != 1440667228) return;

            String s = text.substring(cmd.length());
            JsonElement ret = client.send(new DebugMsg().build(s));
            event.getSubject().sendMessage(new MessageChainBuilder()
                    .append(new QuoteReply(event.getMessage()))
                    .append(ret.getAsString())
                    .build()
            );
        });
    }
}
