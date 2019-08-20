import { Injectable } from '@angular/core';
import {Subject} from "rxjs";
import {Product} from "../../../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductsDataStorageService {

  constructor() { }
  productStore: Subject<Product[]> = new Subject();

}
