import {Component,
  OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";


import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";
import {MessageService} from "../../service/message-service/message.service";


@Component({
  selector: 'app-product-result',
  templateUrl: './product-result.component.html',
  styleUrls: ['./product-result.component.css']
})
export class ProductResultComponent implements OnInit {

  constructor(public productDataStorage: ProductsDataStorageService,
              public messageService: MessageService) { }

 maxCoffeeCharacteristic = 5;
 noProducts = false;
 characteristics = ['strong','sour','bitter'];

  ngOnInit() {
    this.productDataStorage.productsStore
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
      return this.productDataStorage.productsStore;
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

  getQuantity(quantity){
    return +quantity.value;
  }

  numberOfCoffeeBeans(){
    return new Array(this.maxCoffeeCharacteristic);
  }

}
