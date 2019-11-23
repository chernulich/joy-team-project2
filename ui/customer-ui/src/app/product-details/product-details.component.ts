import {Component, ElementRef, OnDestroy, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {ProductDetailsService} from "./service/product-details.service";
import {IProductDetails, ProductsDataStorageService} from "../service/data-storage/products-data-storage.service";
import {map, take} from "rxjs/operators";
import {asyncScheduler, combineLatest, Observable, of, Subscription} from "rxjs";
import {ICartProduct, ProductCartService} from "../checkout-customer/product-cart/services/product-cart.service";


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit, OnDestroy {

  public quantityValue: number = 1;
  public maxQuantity: number = 10;
  public minQuantity: number = 1;

  constructor(private activatedRoute: ActivatedRoute,
              private productDetailsService: ProductDetailsService,
              private dataStorage: ProductsDataStorageService,
              private renderer: Renderer2,
              private productCartService: ProductCartService) {
    window.scrollTo({left: 0, top: 0, behavior: "smooth"});
  }

  characteristics;
  maxCoffeeCharacteristic = 5;
  showMore: boolean = false;
  @ViewChild('details', {read: ElementRef, static: true}) detailsElem: ElementRef;

  activatedRouteSubscription: Subscription;
  getSelectedProductSubscription: Subscription;
  selectedProductForDetails;

  ngOnInit() {

    this.activatedRoute.data.subscribe((productForDetails) => {
      let product = productForDetails.productDetails;
      this.getCharacteristics(product);
      product = this.productCartService.updateDetailsProduct(product);
      this.dataStorage.setSelectedProductForDetails(product,0);
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

  getCharacteristics(product){
    // return this.dataStorage.getSelectedProductForDetails()
    //   .pipe(map((product) => {
    //
    //   }));
    if(!!product.characteristicResponse){
      return Object.keys(product.characteristicResponse);
    }
    else{ return [];}
  }

  getSelectedProductFromStorage(): Observable<IProductDetails>{
    return this.dataStorage.getSelectedProductFromDataStorage();
  }

  quantityMinus(){
    if(this.quantityValue > 1){
      this.quantityValue--;
    }
  }

  quantityPlus(quantity){
    if(this.quantityValue < quantity){
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

  onAddToCart(){

        // update data storage
     this.dataStorage.getSelectedProductFromDataStorage()
          .pipe(take(1))
          .subscribe((selectedProductFromStorage) => {
            const cartProduct: ICartProduct = this.productCartService.createCartProductObjectToAdd(selectedProductFromStorage,this.quantityValue);
            this.productCartService.updateCart(cartProduct);
            // selectedProductFromStorage.amountAvailable -= this.quantityValue;
            this.dataStorage.setSelectedProductForDetails(selectedProductFromStorage,this.quantityValue);
            this.quantityValue = selectedProductFromStorage.amountAvailable > 0 ? 1 : 0;
          });
        //this.selectedProductForDetails.amountAvailable -= this.quantityValue;
        // this.dataStorage.setSelectedProductForDetails(this.selectedProductForDetails);
  }

  ngOnDestroy(){
    if(!!this.getSelectedProductSubscription){
      this.getSelectedProductSubscription.unsubscribe();
    }
    if(!!this.activatedRouteSubscription){
      this.activatedRouteSubscription.unsubscribe();
    }
  }
}
