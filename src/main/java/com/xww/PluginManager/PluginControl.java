package com.xww.PluginManager;

import com.xww.core.BasePlugins;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Set;
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
    private Set<BasePlugins> Plugins;
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
    private ScheduledExecutorService SCHEDULER;

    public PluginControl() {
        Plugins = PluginScanner.scanAllPlugins();
        pluginPushFuture = new HashMap<>();
        pluginEventExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * 2,
                r -> new Thread(r, "Event-" + r.hashCode())
        );
        pluginPushExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * 2,
                r -> new Thread(r, "Push-" + r.hashCode())
        );
        SCHEDULER = Executors.newScheduledThreadPool(10);
    }
}
