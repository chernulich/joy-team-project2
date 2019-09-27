import {Injectable} from "@angular/core";

@Injectable({providedIn:"root"})
export class MessageService {

  constructor(){}

  messageType = '';
  messageText = "";

  showMessage(messageType,messageText){
    this.messageType = messageType;
    this.messageText = messageText;
    setTimeout(() => {
      this.messageType = '';
      this.messageText = '';
    }, 3000);
  }
}
