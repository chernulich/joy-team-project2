import {AfterViewInit, Component, ElementRef, OnInit} from '@angular/core';
import {ProductCartService} from "./checkout-customer/product-cart/services/product-cart.service";
import set = Reflect.set;


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnInit {
  constructor(private elementRef: ElementRef,
              private productCartService: ProductCartService) {

  }

  ngOnInit(){
    const cart = this.productCartService.getCartFromLocalStorage();
    if(!!cart){
      this.productCartService.setProductCart(cart);
    }
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = 'gainsboro';
  }
}
