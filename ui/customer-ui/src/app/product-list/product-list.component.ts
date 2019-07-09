import { Component, OnInit } from '@angular/core';
import {ProductListHttpService} from "./http/product-list-http.service";
import {ProductList} from "./model/product-list";
import {Requestdto} from "./model/requestdto";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public json: string
  public productsList: ProductList;
  public requestdto: Requestdto = new Requestdto();

  constructor(private productListService: ProductListHttpService) { }


  ngOnInit() {
    this.getProductList(this.requestdto);
  }

  getProductList(body: Requestdto){
    return this.productListService.getProductList({body}).subscribe(data => {
      this.productsList = data;
      this.json = JSON.stringify(this.productsList);
    });



  }

}
