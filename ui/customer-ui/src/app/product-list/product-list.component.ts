import { Component, OnInit } from '@angular/core';

import {ProductsDataStorageService} from "./product-search/service/data-storage/products-data-storage.service";
import {Product} from "../model/product.model";
import {HttpService} from "../service/http/http.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  constructor(private httpService: HttpService,
              private productDataStorage: ProductsDataStorageService) { }

  ngOnInit() {
    // this.httpService.getAllProducts()
    //   .subscribe((products: Product[]) => {
    //     this.productDataStorage.productStore.next(products);
    //     console.log(products);
    //   });
    //
    // this.httpService.getMostPopular()
    //   .subscribe((mostPopular) => {
    //     this.productDataStorage.mostPopular.next(mostPopular);
    //   })
  }

}
