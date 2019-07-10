import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductList} from "../model/product-list";
import {RequestDto} from "../model/request-dto";

@Injectable({
  providedIn: 'root'
})
export class ProductListHttpService {

public body: RequestDto;

  constructor(private httpClient: HttpClient) { }

  getProductList(body) {
    return this.httpClient.post<ProductList>('/api/customer/products', {body});
  }
}
