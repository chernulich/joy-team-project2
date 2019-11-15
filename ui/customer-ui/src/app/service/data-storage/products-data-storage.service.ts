import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";


@Injectable()
export class ProductsDataStorageService {

  constructor() { }
  private productsStore: BehaviorSubject<Array<any>> =
    new BehaviorSubject([]);

  private currentPage: BehaviorSubject<number> = new BehaviorSubject<number>(1);

  private selectedProductForDetails: BehaviorSubject<any> = new BehaviorSubject<any>({});


  updateProductStore(products: Array<any>){
    if(this.currentPage.getValue() > 1){
      this.productsStore.next([...this.productsStore.getValue(),...products]);
    }
    else{
      this.productsStore.next(products);
    }
    // console.log(this.productsStore.getValue());
  }

  public getProductsFromStore(): Observable<any>{
    return this.productsStore.asObservable();
  }

  public getCurrentPage(): number{
    return this.currentPage.getValue();
  }

  public setCurrentPage(pageNum: number){
    this.currentPage.next(pageNum);
  }

  public setSelectedProductForDetails(product){
    this.selectedProductForDetails.next(product);
  }

  public getSelectedProductForDetails(): Observable<any>{
    return this.selectedProductForDetails.asObservable();
  }
}
