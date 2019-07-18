import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CheckoutRequest} from "../model/checkoutRequest";
import {SubmitOrderResponse} from "../model/submitOrderResponse";

@Injectable({
  providedIn: 'root'
})
export class CheckoutHttpService {

  constructor(private httpClient: HttpClient) { }

  submitOrder(checkoutRequest: CheckoutRequest) {
    return this.httpClient.post<SubmitOrderResponse>('/api/customer/checkout', {checkoutRequest});
  }


 }
