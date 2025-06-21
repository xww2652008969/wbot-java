package com.xww.Plu;

import com.xww.core.BasePlugins;
import com.xww.model.Message;

public class A extends BasePlugins {
    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void setState(boolean flag) {

    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public void PrivateHandle(Message message) {
        postApi.setPrivateMessag(1271701079);
        postApi.chatmessage.addText("嘻嘻");
        postApi.sendPrivateMsg();
    }
}
