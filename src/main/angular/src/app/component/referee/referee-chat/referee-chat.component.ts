import { AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { HallService } from '../../../service/hall.service';
import { ChatService } from '../../../service/chat.service';
import { Chat } from '../../../model/Chat';
import { Hall } from '../../../model/Hall';

@Component({
  selector: 'app-referee-chat',
  templateUrl: './referee-chat.component.html',
  styleUrls: ['./referee-chat.component.scss']
})
export class RefereeChatComponent implements OnInit, AfterViewInit {
  chats: Array<Chat>;
  message: string;
  private hall: Hall;
  @ViewChild('messages') messagesRef: ElementRef;
  @Input()
  hallId: Hall['id'];

  @Input()
  creator: string = 'REFEREE';

  constructor(private hallService: HallService, private chatService: ChatService,) { }

  async ngOnInit(): Promise<void> {
    await this.loadChat();
    (await this.chatService.getChatEvents()).subscribe(() => this.loadChat());
  }

  ngAfterViewInit(): void {
    this.scroll();
  }

  private scroll() {
    setTimeout(() => {
      if (this.messagesRef?.nativeElement?.scrollHeight) {
        this.messagesRef.nativeElement.scrollTop = this.messagesRef.nativeElement.scrollHeight;
      }
    }, 200);
  }

  private async loadChat() {
    await this.loadHall();
    this.chats = await this.chatService.getChatsByHall(this.hall.id);
    this.scroll();
  }

  private async loadHall() {
    if (this.hallId) {
      this.hall = await this.hallService.getHall(this.hallId);
      return;
    }
    this.hall = await this.hallService.getCurrentHall();
  }

  async createChat() {
    this.chatService.save({ message: this.message, hall: this.hall, creator: this.creator });
  }
}
