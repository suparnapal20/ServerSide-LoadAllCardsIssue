package com.github.splendor_mobile_game.websocket.communication;

import com.github.splendor_mobile_game.websocket.utils.json.JsonParser;
import com.github.splendor_mobile_game.websocket.utils.json.Optional;
import com.github.splendor_mobile_game.websocket.utils.json.exceptions.JsonParserException;
import com.google.gson.Gson;

public class ReceivedMessage {
    private String messageContextId;
    private String type;
    @Optional
    private Object data;

    public ReceivedMessage(String message) throws InvalidReceivedMessage {
        ReceivedMessage msg = ReceivedMessage.fromJson(message);
        this.messageContextId = msg.messageContextId;
        this.type = msg.type;
        this.data = msg.getData();
    }

    public void parseDataToClass(Class<?> clazz) throws InvalidReceivedMessage {
        try {
            // TODO: Perfomance loss because of redundant json parsing
            this.data = JsonParser.parseJson((new Gson()).toJson(this.data), clazz);
        } catch (JsonParserException e) {
            throw new InvalidReceivedMessage("Received message is invalid!", e);
        }
    }

    public static ReceivedMessage fromJson(String inputJson) throws InvalidReceivedMessage {
        try {
            return JsonParser.parseJson(inputJson, ReceivedMessage.class);
        } catch (JsonParserException e) {
            throw new InvalidReceivedMessage("Received message is invalid!", e);
        }
    }

    public String getMessageContextId() {
        return messageContextId;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((messageContextId == null) ? 0 : messageContextId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReceivedMessage other = (ReceivedMessage) obj;
        if (messageContextId == null) {
            if (other.messageContextId != null)
                return false;
        } else if (!messageContextId.equals(other.messageContextId))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }

}
