import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductList} from "../model/product-list";
import {RequestDto} from "../model/request-dto";

@Injectable({
  providedIn: 'root'
})
export class ProductListHttpService {

  constructor(private httpClient: HttpClient) { }

  getProductList(requestDto: RequestDto) {
    return this.httpClient.post<ProductList>('/api/customer/products', {requestDto});
  }
}
