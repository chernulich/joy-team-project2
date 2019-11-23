import {
  Component, HostListener, OnDestroy,
  OnInit
} from '@angular/core';
import {BehaviorSubject, fromEvent, Observable, Subject, Subscription} from "rxjs";


import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";
import {MessageService} from "../../service/message-service/message.service";
import {ProductResultService} from "./services/product-result.service";
import {takeUntil} from "rxjs/operators";
import {ICartProduct, ProductCartService} from "../../checkout-customer/product-cart/services/product-cart.service";


@Component({
  selector: 'app-product-result',
  templateUrl: './product-result.component.html',
  styleUrls: ['./product-result.component.css']
})
export class ProductResultComponent implements OnInit, OnDestroy {

  constructor(public productDataStorage: ProductsDataStorageService,
              public messageService: MessageService,
              private productResultService: ProductResultService,
              private productCartService: ProductCartService) { }

 noProducts = false;

 productList = [];
 paginationSubscription: Subscription;
 productsStoreSubscription: Subscription;

 stopPaginationEvent: Subject<any> = new Subject();

  ngOnInit() {

    this.paginationSubscription = this.productResultService
      .pagination()
      .pipe(takeUntil(this.stopPaginationEvent))
      .subscribe(() => {});

    this.productsStoreSubscription =
      this.productDataStorage.getProductsFromStore()
      .subscribe((products) => {
        if(products[0] === 'no-products'){
          this.productList = [];
          this.noProducts = true;
          this.messageService.showMessage('danger','Your search gave no results, please try again!');
        }
        else{
          this.productList = products;
          this.noProducts = false;
        }
      });
  }

  getProductList(): BehaviorSubject<any>{
    if(!this.noProducts){
      return this.productDataStorage.getProductsFromStore() as BehaviorSubject<any>;
    }
    else{
      return new BehaviorSubject([]);
    }
  }

  onAddToCart(product){
    const cartProduct: ICartProduct = this.productCartService.createCartProductObjectToAdd(product,1);
    this.productCartService.updateCart(cartProduct);
    if(product.availableAmount - 1 >= 0){
      product.availableAmount -= 1;
    }
    console.log(this.productList);
  }

  ngOnDestroy(): void {
    this.stopPaginationEvent.next('stop');
    if(!!this.paginationSubscription){
      this.paginationSubscription.unsubscribe();
    }
    if(!!this.productsStoreSubscription){
      this.productsStoreSubscription.unsubscribe();
    }
  }
}
