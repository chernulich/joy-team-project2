import {Component, OnInit, Renderer2} from '@angular/core';
import {ICartProduct, ProductCartService} from "./services/product-cart.service";
import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";

@Component({
  selector: 'app-product-cart',
  templateUrl: './product-cart.component.html',
  styleUrls: ['./product-cart.component.css']
})
export class ProductCartComponent implements OnInit {

  constructor(private productCartService: ProductCartService,
              private productDataStorageService: ProductsDataStorageService,
              private renderer: Renderer2) { }

  cartProducts: Array<ICartProduct> = [];
  ngOnInit() {
    this.productCartService
      .getProductsInCart()
      .subscribe((products) => {
        this.cartProducts = products;
      });
  }

  removeFromCart(id){
    this.productCartService.removeItemFromCart(id);
  }

}
