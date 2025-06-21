package com.xww.core;

import com.xww.client.Httpclient;
import com.xww.Controller.ClientApi;
import com.xww.model.Message;
import com.xww.model.Plugins;


/**
 * 插件必须继承BasePlugins或者实现Plugins
 */
public abstract class BasePlugins implements Plugins {


    public ClientApi postApi;


    /**
     * @return 插件是否启用
     */
    public abstract boolean isOpen();

    /**
     * @param flag 设置插件是否启用
     */
    public abstract void setState(boolean flag);

    /**
     * @return 获取插件版本号
     */
    public abstract String getVersion();

    /**
     * @return 获取作者
     */
    public abstract String getAuthor();


    @Override
    public void setApi(Httpclient httpclient) {
        postApi = new ClientApi(httpclient);
    }

    @Override
    public void GroupHandle(Message message) {
    }

    @Override
    public void PrivateHandle(Message message) {

    }

    @Override
    public void MessageSendhandle(Message message) {

    }

    @Override
    public void NoticeHandle(Message message) {

    }

    @Override
    public void Push() {

    }
}
