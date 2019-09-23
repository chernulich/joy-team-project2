import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {Product} from "../../../../model/product.model";
import {HttpService} from "../../../../service/http/http.service";
import {ProductListRequest} from "../../../model/product-list-request";

@Injectable({
  providedIn: 'root'
})
export class ProductsDataStorageService {

  constructor(private http: HttpService) { }
  productStore: BehaviorSubject<Product[]> = new BehaviorSubject([]);

  httpGetFilteredProducts(requestBody: {}){
    this.http.getFilteredProducts(requestBody)
      .subscribe((products) => {
        console.log(products);
      });
  }
}
