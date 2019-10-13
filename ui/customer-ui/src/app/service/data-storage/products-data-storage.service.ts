import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {HttpService} from "../http/http.service";

interface ProductsStore {
  products: [];
  popular: {};
}
@Injectable({
  providedIn: 'root'
})
export class ProductsDataStorageService {

  constructor(private http: HttpService) { }
  productsStore: BehaviorSubject<Array<any>> =
    new BehaviorSubject([]);


  httpGetFilteredProducts(requestBody: {}){
    this.http.getFilteredProducts(requestBody)
      .subscribe((products: ProductsStore) => {
        if(!products.popular || !products.products){
          this.productsStore.next(['no-products']);
          return;
        }
        const allProducts = (products.products.slice(0) as Array<any>);
        const popular = {...products.popular};
        allProducts.unshift(popular);
        this.productsStore.next(allProducts as any);
      });
  }
}
