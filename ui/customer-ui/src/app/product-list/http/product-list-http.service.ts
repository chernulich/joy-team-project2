import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductList} from "../model/product-list";
import {Requestdto} from "../model/requestdto";

@Injectable({
  providedIn: 'root'
})
export class ProductListHttpService {

public body: Requestdto;

  constructor(private httpClient: HttpClient) { }

  getProductList(body) {
    return this.httpClient.post<ProductList>('/api/customer/products', {body});
  }
}
