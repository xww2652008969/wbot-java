package com.xww.Plu;

import com.xww.core.BasePlugins;
import com.xww.model.BotPlugin;

@BotPlugin
public class Tee extends BasePlugins {
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
    public String getName() {
        return "qwq";
    }

    @Override
    public void push() {
    }
}
