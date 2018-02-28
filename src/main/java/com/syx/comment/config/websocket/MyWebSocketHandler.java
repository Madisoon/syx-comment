package com.syx.comment.config.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Socket处理器(比较重要的)
 *
 * @author caizi
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    /**
     * 最重要的websocket处理程序（包括发送信息，接收信息，信息错误等方法。）
     */

    //先注册一个websocket服务器，将连接上的所有用户放进去
    public static final Map<Long, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<Long, WebSocketSession>();
    }

    /**
     * 前台连接并且注册了账户
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long uid = (Long) session.getAttributes().get("uid");
        System.out.println(userSocketSessionMap.toString());
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, session);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message.getPayloadLength() == 0) {
            return;
        }
        Message msg = new Gson().fromJson(message.getPayload().toString(), Message.class);
        msg.setDate(new Date());
        sendMessageToUser(msg.getTo(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    /**
     * 消息传输错误处理，如果出现错误直接断开连接
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Websocket:" + session.getId() + "已经关闭");
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void broadcast(final TextMessage message) throws IOException {
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 多线程群发（给所有在线的用户发送消息）  先判断是否里面有用户（）然后循环发消息
        /*后台调用sendMessage方法的时候，前台会触发onmessage*/
        while (it.hasNext()) {
            final Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                // entry.getValue().sendMessage(message);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

    /**
     * 单个用户发消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(Long uid, TextMessage message) throws IOException {
        //根据传过来的账号，在websocketseesion的服务器里面找，接收者注册的账号
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }
}