package com.xww.Plu;

import com.xww.core.BasePlugins;
import com.xww.core.LogUtils;
import com.xww.model.BotPlugin;
import com.xww.model.Message;

@BotPlugin
public class Te extends BasePlugins {
    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void setState(boolean flag) {

    }

    @Override
    public String getVersion() {
        return "";
    }

    @Override
    public String getAuthor() {
        return "";
    }

    @Override
    public void privateHandle(Message message) {
    }

    @Override
    public void groupHandle(Message message) {
        LogUtils.debug(this.getClass(), "触发了群聊%s", message.toString());
    }

    @Override
    public void push() {
        postApi.createPrivateMessage(1271701079);
        postApi.chatMessage.addText("启动了");
        postApi.sendPrivateMsg();
        LogUtils.debug(this.getClass(), "发送了");
    }
}
