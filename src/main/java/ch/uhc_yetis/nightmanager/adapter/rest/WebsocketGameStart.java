package ch.uhc_yetis.nightmanager.adapter.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketGameStart {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebsocketGameStart(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("game-started")
    public void sendToClients() {
        simpMessagingTemplate.convertAndSend("/topic/game-started", "Game started");

    }
}
