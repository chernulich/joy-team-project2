import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {HttpService} from "../http/http.service";
import {ProductResultService} from "../../product-list/product-result/services/product-result.service";


@Injectable()
export class ProductsDataStorageService {

  constructor() { }
  private productsStore: BehaviorSubject<Array<any>> =
    new BehaviorSubject([]);

  private currentPage: BehaviorSubject<number> = new BehaviorSubject<number>(1);


  updateProductStore(products: Array<any>){
    if(this.currentPage.getValue() > 1){
      this.productsStore.next([...this.productsStore.getValue(),...products]);
    }
    else{
      this.productsStore.next(products);
    }
    // console.log(this.productsStore.getValue());
  }

  getProductsFromStore(): Observable<any>{
    return this.productsStore.asObservable();
  }

  public getCurrentPage(): number{
    return this.currentPage.getValue();
  }

  public setCurrentPage(pageNum: number){
    this.currentPage.next(pageNum);
  }
}
