import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductDetails} from "../model/product-details";

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsHttpService {

  constructor(private httpClient: HttpClient){}

  getProductDetails(productId: number) {
    return this.httpClient.get<ProductDetails>("api/customer/products/" + productId)
  }

}
