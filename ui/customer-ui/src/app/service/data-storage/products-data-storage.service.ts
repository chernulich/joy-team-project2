import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of, Subject} from "rxjs";
import {ProductCartService} from "../../checkout-customer/product-cart/services/product-cart.service";
import {debug} from "util";

export interface IProductDetails {
  amountAvailable: number;
  characteristicResponse: {strong: number; sour: number; bitter: number;};
  description: string;
  id: number;
  previewImage: string;
  price: number;
  productImages: Array<string>;
  productName: string;
  shortDescription: string;
}

@Injectable()
export class ProductsDataStorageService {

  constructor(private productCartService: ProductCartService) { }
  private productsStore: BehaviorSubject<Array<any>> =
    new BehaviorSubject([]);

  private currentPage: BehaviorSubject<number> = new BehaviorSubject<number>(1);

  private selectedProductForDetails: BehaviorSubject<any> = new BehaviorSubject<any>({});


  updateProductStore(products: Array<any>){
    this.productCartService.updateProductListQuantity(products);
    if(this.currentPage.getValue() > 1){
      this.productsStore.next([...this.productsStore.getValue(),...products]);
    }
    else{
      this.productsStore.next(products);
    }
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

  public setSelectedProductForDetails(product,quantity){

    if(!!product.amountAvailable){
      product.amountAvailable -= quantity;
    }
    else if(!!product.availableAmount){
      product.availableAmount -= quantity;
    }
    this.selectedProductForDetails.next(product);
    console.log("Details Product: " , this.selectedProductForDetails.getValue());
  }

  public getSelectedProductFromDataStorage(): Observable<IProductDetails>{
    return this.selectedProductForDetails.asObservable();
  }
}
