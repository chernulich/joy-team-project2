import { Component, OnInit } from '@angular/core';
import {ProductDetails} from "./model/product-details";
import {ProductDetailsHttpService} from "./service/product-details-http.service";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  public productDetails: ProductDetails;
  public productId: number;

  constructor(private productDetailsHttpService: ProductDetailsHttpService, private activateRoute: ActivatedRoute) {
  }

  getProductDetails(productId: number) {
    return this.productDetailsHttpService.getProductDetails(productId).subscribe(data => this.productDetails = data);
  }

  ngOnInit() {
    //this.activateRoute.paramMap.subscribe(params => params.get('productId'))
    this.getProductDetails(this.productId);
  }

}
