package com.xww;

import com.xww.core.BootConfig;
import com.xww.core.Bot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Te {
    public static void main(String[] args) {
        var config = new BootConfig();
        config.setHttpUrl("http://192.168.2.3:4000");
        config.setWsUrl("ws://192.168.2.3:3001");
        config.setDebug(true);
        var b = new Bot(config);
        b.run();
    }
}
