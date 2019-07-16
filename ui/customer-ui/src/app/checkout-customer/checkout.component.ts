import { Component, OnInit, Injectable } from '@angular/core';
import {RequestCheckoutDto} from './model/requestCheckoutDto';
import {CheckoutListHttpService} from "./service/checkoutListHttp.service";
import {SubmitOrderResponse} from "./model/submitOrderResponse";


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

@Injectable()
export class CheckoutComponent implements OnInit {

  public json: string;
  public orderResponse : SubmitOrderResponse;
  public defaultRequestDto : RequestCheckoutDto = RequestCheckoutDto.prototype.getDefultRequestCheckoutDto();

  constructor(private checkoutListService : CheckoutListHttpService){ }

  postProductList(requestDto : RequestCheckoutDto){
    return console.log(this.checkoutListService.postProductList(requestDto).subscribe(data => {
      this.orderResponse = data;
      this.json = JSON.stringify(this.orderResponse);
    }));
  }

  ngOnInit() {
    this.postProductList(this.defaultRequestDto);
  }



}
