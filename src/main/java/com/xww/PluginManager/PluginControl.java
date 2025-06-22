package com.xww.PluginManager;

import com.xww.client.Httpclient;
import com.xww.core.BasePlugins;
import com.xww.core.Bot;
import com.xww.core.LogUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

@Getter
@Setter
public class PluginControl {
    /**
     * 插件列表
     */
    private Map<String, BasePlugins> plugins;
    /**
     * 主动行为的Future池
     */
    private HashMap<String, Future> pluginPushFuture;
    /**
     * 基础事件的线程池
     */
    private ExecutorService pluginEventExecutor;
    /**
     * 主动行为的线程池
     */
    private ExecutorService pluginPushExecutor;
    /**
     * 处理超时的
     */
    private ScheduledExecutorService scheduledExecutorService;

    public PluginControl(Bot bot) {
        plugins = new HashMap<>();
        pluginPushFuture = new HashMap<>();
        pluginEventExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * 2,
                r -> new Thread(r, "Event-" + r.hashCode())
        );
        pluginPushExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * 2,
                r -> new Thread(r, "Push-" + r.hashCode())
        );
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (var p : PluginScanner.scanAllPlugins()) {
            String pluginName = p.getName();
            if (plugins.putIfAbsent(pluginName, p) == null) {
                p.setApi(new Httpclient(bot.getConfig()));
                p.setPluginControl(this);
            } else {
                LogUtils.error(PluginControl.class, "有重复的插件名不加载: " + pluginName);
            }
        }
    }
}
