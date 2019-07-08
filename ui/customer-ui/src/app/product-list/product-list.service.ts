import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductList} from "./model/product-list";

@Injectable({
  providedIn: 'root'
})
export class ProductListService {

  constructor(private httpClient: HttpClient) { }

  getProductList(body) {
    return this.httpClient.post<ProductList>('/api/customer/products', {body});
  }
}
