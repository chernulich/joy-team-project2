import {Component, OnInit} from '@angular/core';
import {ProductResultService} from "./product-result/services/product-result.service";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  constructor(private productResultService: ProductResultService) {}

  ngOnInit() {
    this.productResultService.httpGetFilteredProducts({"page": 1, "results": 6});
  }
}
