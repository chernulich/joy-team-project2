import { Component, OnInit } from '@angular/core';
import {HttpService} from "./service/http/http.service";
import {Product} from "../model/product.model";
import {ProductsDataStorageService} from "./service/data-storage/products-data-storage.service";

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  constructor(private httpService: HttpService,
              private productDataStorage: ProductsDataStorageService) { }

  ngOnInit() {
    this.httpService.getAllProducts()
      .subscribe((products: Product[]) => {
        this.productDataStorage.productStore.next(products);
      })
  }

}
