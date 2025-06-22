package com.xww.core;

import com.xww.PluginManager.PluginControl;
import com.xww.constants.EventKind;
import com.xww.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Event {

    private static PluginControl pluginControl;

    public static void postEvent(PluginControl control, LinkedBlockingQueue<Message> messages) {
        pluginControl = control;
        for (BasePlugins plugin : control.getPlugins().values()) {
            Future<?> submit = control.getPluginPushExecutor().submit(() -> {
                plugin.push();
            });
            control.getPluginPushFuture().put(plugin.getName(), submit);
        }
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
        for (BasePlugins plugin : pluginControl.getPlugins().values()) {
            if (!plugin.isOpen())
                continue;
            Future<?> future = pluginControl.getPluginEventExecutor().submit(() -> {
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
        pluginControl.getScheduledExecutorService().schedule(() -> {
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

