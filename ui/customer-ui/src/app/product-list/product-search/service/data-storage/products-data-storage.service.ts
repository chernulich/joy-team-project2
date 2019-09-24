import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {Product} from "../../../../model/product.model";
import {HttpService} from "../../../../service/http/http.service";
import {ProductListRequest} from "../../../model/product-list-request";

interface ProductsStore {
  products: [];
  popular: {};
}
@Injectable({
  providedIn: 'root'
})
export class ProductsDataStorageService {

  constructor(private http: HttpService) { }
  productsStore: BehaviorSubject<[]> =
    new BehaviorSubject([]);


  httpGetFilteredProducts(requestBody: {}){
    this.http.getFilteredProducts(requestBody)
      .subscribe((products: ProductsStore) => {
        console.log(products);
        const allProducts = (products.products.slice(0) as Array<any>);
        const popular = {...products.popular};
        allProducts.unshift(popular);
        this.productsStore.next(allProducts as any);
      });
  }
}
