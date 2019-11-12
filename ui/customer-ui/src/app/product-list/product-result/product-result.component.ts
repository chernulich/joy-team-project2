import {
  Component, HostListener, OnDestroy,
  OnInit
} from '@angular/core';
import {BehaviorSubject, fromEvent, Observable, Subject, Subscription} from "rxjs";


import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";
import {MessageService} from "../../service/message-service/message.service";
import {ProductResultService} from "./services/product-result.service";
import {takeUntil} from "rxjs/operators";


@Component({
  selector: 'app-product-result',
  templateUrl: './product-result.component.html',
  styleUrls: ['./product-result.component.css']
})
export class ProductResultComponent implements OnInit, OnDestroy {

  constructor(public productDataStorage: ProductsDataStorageService,
              public messageService: MessageService,
              private productResultService: ProductResultService) { }

 maxCoffeeCharacteristic = 5;
 noProducts = false;
 characteristics = ['strong','sour','bitter'];

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
          this.noProducts = true;
          this.messageService.showMessage('danger','Your search gave no results, please try again!');
        }
        else{
          this.noProducts = false;
        }
      });
  }

  firstLetterUpper(value: string){
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

  getProductList(): BehaviorSubject<any>{
    if(!this.noProducts){
      return this.productDataStorage.getProductsFromStore() as BehaviorSubject<any>;
    }
    else{
      return new BehaviorSubject([]);
    }
  }


  onLeftCardClick(rightCard){
    if(!rightCard.classList.contains('open-right')){
      rightCard.style.cssText += 'display: flex!important;';
      rightCard.closeRight = false;
      setTimeout(() => {
        rightCard.openRight = true;
      } ,20);
    }
    else{
      rightCard.openRight = false;
      rightCard.closeRight = true;
      setTimeout(() => {
        rightCard.style.cssText += 'display: none!important;';
      } ,401);
    }
  }

  numberOfCoffeeBeans(){
    return new Array(this.maxCoffeeCharacteristic);
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
