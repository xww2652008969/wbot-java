package com.xww.bot;

import com.xww.client.Httpclient;
import com.xww.core.ClientApi;
import com.xww.model.Message;
import com.xww.model.Plugins;

public class BasePlugins implements Plugins {
    private Httpclient httpclient = null;

    public ClientApi Api() {
        var a = new ClientApi(this.httpclient);
        return a;
    }

    @Override
    public Httpclient httpclient() {
        return httpclient;
    }

    @Override
    public void setHttpclient(Httpclient httpclient) {
        this.httpclient = httpclient;
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
