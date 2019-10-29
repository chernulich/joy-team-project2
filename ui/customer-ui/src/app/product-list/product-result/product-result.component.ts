import {
  Component, HostListener,
  OnInit
} from '@angular/core';
import {BehaviorSubject} from "rxjs";


import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";
import {MessageService} from "../../service/message-service/message.service";
import {ProductResultService} from "./services/product-result.service";


@Component({
  selector: 'app-product-result',
  templateUrl: './product-result.component.html',
  styleUrls: ['./product-result.component.css']
})
export class ProductResultComponent implements OnInit {

  constructor(public productDataStorage: ProductsDataStorageService,
              public messageService: MessageService,
              private productResultService: ProductResultService) { }

 maxCoffeeCharacteristic = 5;
 noProducts = false;
 characteristics = ['strong','sour','bitter'];


  ngOnInit() {
    this.productResultService.pagination().subscribe(() => {}); // test

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

}
