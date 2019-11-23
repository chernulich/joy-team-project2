import {Component, OnInit} from '@angular/core';
import {ProductResultService} from "./product-result/services/product-result.service";
import {ProductsDataStorageService} from "../service/data-storage/products-data-storage.service";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  constructor(private productResultService: ProductResultService,
              private productsStore: ProductsDataStorageService) {}

  ngOnInit() {
    this.productResultService.filterSearch = false;
    this.productsStore.setCurrentPage(1);
    this.productResultService.httpGetFilteredProducts({"page": 1, "results": 6});
  }
}
