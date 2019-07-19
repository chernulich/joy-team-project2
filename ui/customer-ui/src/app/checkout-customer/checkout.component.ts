import { Component, OnInit, Injectable } from '@angular/core';
import {CheckoutRequest} from './model/checkoutRequest';
import {CheckoutHttpService} from "./service/checkout-http.service";
import {SubmitOrderResponse} from "./model/submitOrderResponse";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {CommonService} from "../service/common/common.service";


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

@Injectable()
export class CheckoutComponent implements OnInit {

  public json: string;
  public orderResponse : SubmitOrderResponse;
  public defaultRequest : CheckoutRequest = CheckoutRequest.prototype.getDefaultCheckoutRequest();

  constructor(private checkoutHttpService : CheckoutHttpService,
              private commonService: CommonService){ }

  submitOrder(checkoutRequest : CheckoutRequest){
    return this.checkoutHttpService.submitOrder(checkoutRequest).subscribe(data => {
      this.orderResponse = data;
      this.json = JSON.stringify(this.orderResponse);
      this.commonService.orderId = this.orderResponse.orderId;
    });
  }

  ngOnInit() {
    this.submitOrder(this.defaultRequest);
  }



}
