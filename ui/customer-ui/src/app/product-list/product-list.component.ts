import { Component, OnInit } from '@angular/core';
import {ProductListService} from "./product-list.service";
import {ProductList} from "./model/product-list";
import {Requestdto} from "./model/requestdto";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  public productsList: ProductList;
  public requestdto: Requestdto = new Requestdto();

  constructor(private productListService: ProductListService) { }

  ngOnInit() {
    this.getProductList(this.requestdto);
  }

  getProductList(body: Requestdto){
    this.productListService.getProductList({body}).subscribe(data =>
              console.log(data));
  }

}
