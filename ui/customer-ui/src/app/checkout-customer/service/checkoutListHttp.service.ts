import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestCheckoutDto} from "../model/requestCheckoutDto";
import {SubmitOrderResponse} from "../model/submitOrderResponse";

@Injectable({
  providedIn: 'root'
})
export class CheckoutListHttpService {

  constructor(private httpClient: HttpClient) { }

  postProductList(requestDto: RequestCheckoutDto) {
    return this.httpClient.post<SubmitOrderResponse>('/api/customer/checkout', {requestDto});
  }


 }
