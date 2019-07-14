import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestCheckoutDto} from "../model/requestCheckoutDto";
import {Prodacts} from "../model/prodacts";

@Injectable({
  providedIn: 'root'
})
export class CheckoutListHttpService {

  constructor(private httpClient: HttpClient) { }

  getProductList(requestDto: RequestCheckoutDto) {
    return this.httpClient.post<Prodacts>('/api/customer/checkout', {'orderId':1});
  }


 }
