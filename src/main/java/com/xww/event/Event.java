package com.xww.event;

import com.xww.model.Message;
import com.xww.model.Plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Event {
    private static Set<Plugins> plugins;
    // 线程池（建议复用，避免频繁创建）
    private static final ExecutorService PLUGIN_EXECUTOR = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2, // 根据 CPU 核心数调整
            r -> new Thread(r, "Plugin-Executor-" + r.hashCode()) // 自定义线程名
    );
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10);

    public static void PostEvent(Set<Plugins> Plugins, LinkedBlockingQueue<Message> MessageQueue) {
        plugins = Plugins;
        for (; ; ) {
            try {
                Message message = MessageQueue.take();
                switch (message.getPostType()) {
                    case "message": {
                        switch (message.getMessageType()) {
                            case "group": {
                                sendevent(message, EventKind.MessageGroup);
                            }
                            case "private": {
                                sendevent(message, EventKind.MessagePrivate);
                            }
                        }
                    }
                    case "notice": {
                        sendevent(message, EventKind.MessageNotice);
                    }
                    case "message_sent": {
                        sendevent(message, EventKind.MessageSent);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static void sendevent(Message message, EventKind eventKind) {
        int timeoutSeconds = 10000;
        List<Future<?>> futures = new ArrayList<>();
        for (Plugins plugin : plugins) {
            Future<?> future = PLUGIN_EXECUTOR.submit(() -> {
                switch (eventKind) {
                    case MessageSent -> plugin.MessageSendhandle(message);
                    case MessageGroup -> plugin.GroupHandle(message);
                    case MessageNotice -> plugin.NoticeHandle(message);
                    case MessagePrivate -> plugin.PrivateHandle(message);
                }
            });
            futures.add(future);
        }
        // 启动超时监控任务（后台执行，不阻塞主线程）
        SCHEDULER.schedule(() -> {
            cancelPendingTasks(futures); // 超时后取消未完成的任务
        }, timeoutSeconds, TimeUnit.SECONDS);
    }

    /**
     * 取消所有未完成的插件任务
     *
     * @param futures 任务 Future 列表
     */
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

enum EventKind {
    MessageGroup,
    MessagePrivate,
    MessageNotice,
    MessageSent
}