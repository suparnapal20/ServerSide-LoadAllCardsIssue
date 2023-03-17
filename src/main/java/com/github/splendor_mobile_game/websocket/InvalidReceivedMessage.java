package com.github.splendor_mobile_game.websocket;

import com.github.splendor_mobile_game.utils.CustomException;

public class InvalidReceivedMessage extends CustomException {

    public InvalidReceivedMessage() {
    }

    public InvalidReceivedMessage(String message) {
        super(message);
    }

    public InvalidReceivedMessage(Throwable cause) {
        super(cause);
    }

    public InvalidReceivedMessage(String message, Throwable cause) {
        super(message, cause);
    }

}
