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
              private renderer: Renderer2) {
    window.scrollTo({left: 0, top: 0, behavior: "smooth"});
  }

  characteristics;
  maxCoffeeCharacteristic = 5;
  showMore: boolean = false;
  @ViewChild('details', {read: ElementRef, static: true}) detailsElem: ElementRef;

  ngOnInit() {

    this.activatedRoute.data.subscribe((data) => {
      this.dataStorage.setSelectedProductForDetails(data.productDetails);
    });

    this.getSelectedProduct().subscribe((product) => {
      this.maxQuantity = product.amountAvailable;
      this.minQuantity = product.amountAvailable > 0 ? 1 : 0;
      this.quantityValue = this.minQuantity;
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
        if(!!product.characteristicResponse){
          return Object.keys(product.characteristicResponse);
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

  onSmallImageClick(imageElem: HTMLImageElement,previewImage: HTMLElement){
    this.renderer.setStyle(previewImage,'background-image',`url(${imageElem.src})`);
  }

}
