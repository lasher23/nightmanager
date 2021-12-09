package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Chat;
import ch.uhc_yetis.nightmanager.domain.model.Hall;
import ch.uhc_yetis.nightmanager.domain.repository.ChatRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

  private final ChatRepository chatRepository;
  private final SimpMessagingTemplate template;

  public ChatService(ChatRepository chatRepository, SimpMessagingTemplate template) {
    this.chatRepository = chatRepository;
    this.template = template;
  }

  public List<Chat> findByHall(Hall hall) {
    return chatRepository.findByHall(hall);
  }

  public Chat save(Chat chat) {
    var savedChat = this.chatRepository.save(chat);
    this.template.convertAndSend("/topic/nightmanager-chat-change", "{\"status\":\"ChatChanged\"}");
    return savedChat;
  }
}
