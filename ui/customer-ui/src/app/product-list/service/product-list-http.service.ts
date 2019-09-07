import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductList} from "../model/product-list";
import {ProductListRequest} from "../model/product-list-request";

@Injectable({
  providedIn: 'root'
})
export class ProductListHttpService {

  constructor(private httpClient: HttpClient) { }

  getProductList(requestDto: ProductListRequest) {
    return this.httpClient.post<ProductList>('/api/customer/products', {requestDto});
  }
}
