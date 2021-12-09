import { Component, OnInit } from '@angular/core';
import { HallService } from '../../../service/hall.service';
import { ChatService } from '../../../service/chat.service';
import { Hall } from '../../../model/Hall';
import { Chat } from '../../../model/Chat';

@Component({
  selector: 'app-admin-chat',
  templateUrl: './admin-chat.component.html',
  styleUrls: ['./admin-chat.component.scss']
})
export class AdminChatComponent implements OnInit {
  halls: Array<Hall>;

  constructor(private hallService: HallService, private chatService: ChatService,) { }

  async ngOnInit(): Promise<void> {
    this.halls = await this.hallService.getAllHalls();
  }

}
