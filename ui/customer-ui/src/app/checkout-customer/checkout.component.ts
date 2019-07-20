import { Component, OnInit, Injectable } from '@angular/core';
import {CheckoutRequest} from './model/checkoutRequest';
import {CheckoutHttpService} from "./service/checkout-http.service";
import {SubmitOrderResponse} from "./model/submitOrderResponse";
import {Router} from "@angular/router";


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
              private router: Router){ }

  submitOrder(checkoutRequest : CheckoutRequest){
    return this.checkoutHttpService.submitOrder(checkoutRequest).subscribe(data => {
      this.orderResponse = data;
      this.json = JSON.stringify(this.orderResponse);
      this.router.navigate(['/received/' + this.orderResponse.orderId]);
    });
  }

  ngOnInit() {
    this.submitOrder(this.defaultRequest);
  }



}
