package com.xww.model;

import com.xww.client.Httpclient;

public interface Plugins {
    //事件
    void groupHandle(Message message);

    void privateHandle(Message message);

    void messageSendHandle(Message message);

    void noticeHandle(Message message);

    void push();

    void setApi(Httpclient httpclient);
}
