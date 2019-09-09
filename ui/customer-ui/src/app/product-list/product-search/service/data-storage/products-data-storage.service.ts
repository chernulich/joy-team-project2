import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {Product} from "../../../../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductsDataStorageService {

  constructor() { }
  productStore: BehaviorSubject<Product[]> = new BehaviorSubject([]);
  filteredProducts: BehaviorSubject<Product[]> = new BehaviorSubject(this.productStore.value);
  mostPopular: BehaviorSubject<Product> = new BehaviorSubject(
    new Product('','', '',
                0, -1,-1,'',
                -1,-1,-1,false,false,false)
  );
}
