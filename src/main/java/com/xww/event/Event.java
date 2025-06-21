package com.xww.event;

import com.xww.constants.EventKind;
import com.xww.core.BasePlugins;
import com.xww.model.Message;
import com.xww.model.Plugins;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
@Slf4j
public class Event {
    private static Set<BasePlugins> plugins;
    private static final ExecutorService PLUGIN_EXECUTOR = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2,
            r -> new Thread(r, "Plugin-Executor-" + r.hashCode())
    );
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10);

    public static void postEvent(Set<BasePlugins> plugins, LinkedBlockingQueue<Message> messages) {
        Event.plugins = plugins;
        for (; ; ) {
            try {
                Message message = messages.take();
                switch (message.getPostType()) {
                    case "message": {
                        switch (message.getMessageType()) {
                            case "group": {
                                sendEvent(message, EventKind.GroupMessage);
                            }
                            case "private": {
                                sendEvent(message, EventKind.PrivateMessage);
                            }
                        }
                    }
                    case "notice": {
                        sendEvent(message, EventKind.NoticeMessage);
                    }
                    case "message_sent": {
                        sendEvent(message, EventKind.MessageSend);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static void sendEvent(Message message, EventKind eventKind) {
        int timeoutSeconds = 10000;
        List<Future<?>> futures = new ArrayList<>();
        for (Plugins plugin : plugins) {
            Future<?> future = PLUGIN_EXECUTOR.submit(() -> {
                switch (eventKind) {
                    case MessageSend -> plugin.messageSendHandle(message);
                    case GroupMessage -> plugin.groupHandle(message);
                    case NoticeMessage -> plugin.noticeHandle(message);
                    case PrivateMessage -> plugin.privateHandle(message);
                }
            });
            futures.add(future);
        }
        // 启动超时监控任务（后台执行，不阻塞主线程）
        SCHEDULER.schedule(() -> {
            cancelPendingTasks(futures); // 超时后取消未完成的任务
        }, timeoutSeconds, TimeUnit.SECONDS);
    }

    private static void cancelPendingTasks(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            if (!future.isDone()) { // 仅取消未完成的任务
                boolean cancelled = future.cancel(true); // 中断正在执行的任务
                if (cancelled) {
                    System.out.println("成功取消未完成的任务");
                } else {
                    System.err.println("任务已完成或无法取消");
                }
            }
        }
    }
}

