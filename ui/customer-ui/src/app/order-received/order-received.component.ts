import { Component, OnInit } from '@angular/core';
import {CommonService} from "../service/common/common.service";

@Component({
  selector: 'app-order-received',
  templateUrl: './order-received.component.html',
  styleUrls: ['./order-received.component.css']
})
export class OrderReceivedComponent implements OnInit {

  public orderId: number

  constructor(private commonService: CommonService) { }

  ngOnInit() {
    this.orderId = this.commonService.orderId;
  }

}
