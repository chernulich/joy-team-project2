import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {ProductDetailsService} from "./service/product-details.service";
import {ProductsDataStorageService} from "../service/data-storage/products-data-storage.service";
import {map} from "rxjs/operators";
import {of} from "rxjs";


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  public quantityValue: number = 1;
  public maxQuantity: number = 10;
  public minQuantity: number = 1;

  constructor(private activatedRoute: ActivatedRoute,
              private productDetailsService: ProductDetailsService,
              private dataStorage: ProductsDataStorageService,
              private renderer: Renderer2) {}

  characteristics;
  maxCoffeeCharacteristic = 5;
  showMore: boolean = false;
  @ViewChild('details', {read: ElementRef, static: true}) detailsElem: ElementRef;

  ngOnInit() {
    this.activatedRoute.params
      .subscribe((params: Params) => {
        console.log(params);
        this.productDetailsService.getSelectedProductForDetails(params.id);
      });

    this.showMore =
      Math.floor(this.detailsElem.nativeElement.scrollHeight / 3) > Math.floor(this.detailsElem.nativeElement.offsetHeight / 3);
  }

  onShowMore(){
    if((this.detailsElem.nativeElement as HTMLElement).classList.contains('hide-details')){
      this.renderer.removeClass(this.detailsElem.nativeElement,'hide-details');
      this.renderer.addClass(this.detailsElem.nativeElement,'show-details');
    }
    else if((this.detailsElem.nativeElement as HTMLElement).classList.contains('show-details')){
      this.renderer.removeClass(this.detailsElem.nativeElement,'show-details');
      this.renderer.addClass(this.detailsElem.nativeElement,'hide-details');
    }
  }

  getCharacteristics(){
    return this.dataStorage.getSelectedProductForDetails()
      .pipe(map((product) => {
        if(!!product.characteristics){
          return Object.keys(product.characteristics);
        }
        else{ return [];}
      }));
  }

  getSelectedProduct(){
    return this.dataStorage.getSelectedProductForDetails();
  }

  quantityMinus(){
    if(this.quantityValue > this.minQuantity){
      this.quantityValue--;
    }
  }

  quantityPlus(){
    if(this.quantityValue < this.maxQuantity){
      this.quantityValue++;
    }
  }

  firstLetterUpper(value: string){
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

  numberOfCoffeeBeans(){
    return new Array(this.maxCoffeeCharacteristic);
  }

}
