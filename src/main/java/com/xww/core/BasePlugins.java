package com.xww.core;

import com.xww.client.Httpclient;
import com.xww.controller.ClientApi;
import com.xww.model.Message;
import com.xww.model.Plugins;


/**
 * 插件必须继承BasePlugins或者实现Plugins
 */
public abstract class BasePlugins implements Plugins {

    /**
     * 提供发送消息的 API 操作对象
     */
    protected ClientApi postApi;

    /**
     * 判断插件是否启用
     *
     * @return 如果插件启用则返回 true，否则返回 false
     */
    public abstract boolean isOpen();

    /**
     * 设置插件是否启用
     *
     * @param flag true 表示启用，false 表示禁用
     */
    public abstract void setState(boolean flag);

    /**
     * 获取插件版本号
     *
     * @return 插件版本号字符串
     */
    public abstract String getVersion();

    /**
     * 获取插件作者
     *
     * @return 作者名称字符串
     */
    public abstract String getAuthor();

    @Override
    public void setApi(Httpclient httpclient) {
        postApi = new ClientApi(httpclient);
    }

    @Override
    public void groupHandle(Message message) {
        // 默认不处理
    }

    @Override
    public void privateHandle(Message message) {
        // 默认不处理
    }

    @Override
    public void messageSendHandle(Message message) {
        // 默认不处理
    }

    @Override
    public void noticeHandle(Message message) {
        // 默认不处理
    }

    @Override
    public void push() {
        // 默认不处理
    }
}

