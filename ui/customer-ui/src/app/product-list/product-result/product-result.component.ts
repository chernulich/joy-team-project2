import { Component, OnInit } from '@angular/core';
import {ProductsDataStorageService} from "../product-search/service/data-storage/products-data-storage.service";
import {Product} from "../model/product";
import {BehaviorSubject} from "rxjs";


@Component({
  selector: 'app-product-result',
  templateUrl: './product-result.component.html',
  styleUrls: ['./product-result.component.css']
})
export class ProductResultComponent implements OnInit {

  constructor(public productDataStorage: ProductsDataStorageService) { }

 maxCoffeeCharacteristic = 5;
 characteristics = ['strong','sour','bitter'];

  ngOnInit() {
     // this.getProductList();
    this.productDataStorage.productsStore
      .subscribe((products) => {
        console.log("Product Store: ", products);
      });
  }

  firstLetterUpper(value: string){
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

  getProductList(): BehaviorSubject<Product[]>{
    return this.productDataStorage.productsStore;
  }


  onLeftCardClick(leftCard){
    let rightCard = leftCard.nextElementSibling;
    if(!rightCard.classList.contains('open-right')){
      rightCard.style.cssText += 'display: flex!important;';
      rightCard.classList.remove('close-right');
      setTimeout(() => {
        // rightCard.style.opacity = '1';
        rightCard.classList.add('open-right');
      } ,20);
    }
    else{
      // rightCard.style.opacity = '0';
      rightCard.classList.remove('open-right');
      rightCard.classList.add('close-right');
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
