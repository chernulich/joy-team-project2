import { Component, OnInit } from '@angular/core';
import {ProductListHttpService} from "./service/product-list-http.service";
import {ProductList} from "./model/product-list";
import {ProductListRequest} from "./model/product-list-request";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public json: string;
  public productsList: ProductList;
  public defaultProductListRequest: ProductListRequest = ProductListRequest.prototype.getDefaultProductListRequest();

  constructor(private productListService: ProductListHttpService) { }


  ngOnInit() {
    this.getProductList(this.defaultProductListRequest);
  }

  getProductList(productListRequest: ProductListRequest){
    return this.productListService.getProductList(productListRequest).subscribe(data => {
      this.productsList = data;
      this.json = JSON.stringify(this.productsList);
    });



  }

}
