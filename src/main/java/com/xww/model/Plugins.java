package com.xww.model;

import com.xww.client.Httpclient;
import okhttp3.OkHttpClient;

public interface Plugins {
    void setHttpclient(Httpclient httpclient);

    public Httpclient httpclient();

    void GroupHandle(Message message);

    void PrivateHandle(Message message);

    void MessageSendhandle(Message message);

    void NoticeHandle(Message message);

    void Push();
}
