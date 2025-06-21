package com.xww.model;

import com.xww.client.Httpclient;

public interface Plugins {
    

    //事件
    void GroupHandle(Message message);

    void PrivateHandle(Message message);

    void MessageSendhandle(Message message);

    void NoticeHandle(Message message);

    void Push();

    void setApi(Httpclient httpclient);
}
