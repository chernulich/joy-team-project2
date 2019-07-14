import { Component, OnInit } from '@angular/core';
import {ProductListHttpService} from "./service/product-list-http.service";
import {ProductList} from "./model/product-list";
import {RequestDto} from "./model/request-dto";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public json: string;
  public productsList: ProductList;
  public defaultRequestDto: RequestDto = RequestDto.prototype.getDefaultDto();
  public requestDto: RequestDto;

  constructor(private productListService: ProductListHttpService) { }


  ngOnInit() {
    this.getProductList(this.defaultRequestDto);
  }

  getProductList(requestDto: RequestDto){
    return this.productListService.getProductList(requestDto).subscribe(data => {
      this.productsList = data;
      this.json = JSON.stringify(this.productsList);
    });



  }

}
