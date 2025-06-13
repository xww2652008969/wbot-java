package com.xww.Plu;

import com.xww.bot.BasePlugins;
import com.xww.core.MessagesApi;
import com.xww.model.AutomaticRegistration;
import com.xww.model.Message;
import okhttp3.Response;

@AutomaticRegistration
public class a extends BasePlugins {
    @Override
    public void GroupHandle(Message message) {
        if (message.getUserId() != 1271701079) {
            return;
        }
        System.out.println("A插件处理群聊" + message.getRawMessage());
        var a = super.Api();
        var mes = new MessagesApi(message);
        mes.AddText("呜呜呜");
        a.SetChatmessage(mes);
        Response response = a.SendGroupMsg();
        System.out.println(response.code());
    }

    @Override
    public void PrivateHandle(Message message) {
        var a = Api();
        var mes = new MessagesApi(message);
        mes.AddText("嘻嘻");
        a.SetChatmessage(mes);
        a.SendPrivateMsg();
    }
}
