package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.ChatService;
import ch.uhc_yetis.nightmanager.application.HallService;
import ch.uhc_yetis.nightmanager.domain.model.Chat;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

  private final ChatService chatService;
  private final HallService hallService;

  public ChatController(ChatService chatService, HallService hallService) {
    this.chatService = chatService;
    this.hallService = hallService;
  }

  @GetMapping("/{hallId}")
  public ResponseEntity<List<Chat>> getChatsByHallId(@PathVariable("hallId") long hallId) {
        return hallService.findById(hallId)
        .map(chatService::findByHall)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @PostMapping
  public ResponseEntity<Void> saveChat(@RequestBody Chat chat) {
    if (Objects.nonNull(chat.getId())) {
      return ResponseEntity.badRequest().build();
    }
    chatService.save(chat);
    return ResponseEntity.ok().build();
  }
}
