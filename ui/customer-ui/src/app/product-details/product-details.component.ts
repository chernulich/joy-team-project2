import { Component, OnInit } from '@angular/core';
import {ProductDetails} from "./model/product-details";
import {ProductDetailsHttpService} from "./service/product-details-http.service";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {


  public productDetails: ProductDetails;
  private subscription: Subscription;
  public productId: number;

  constructor(private productDetailsHttpService: ProductDetailsHttpService, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params=>this.productId=params['id']);
  }

  getProductDetails(productId: number) {
    return this.productDetailsHttpService.getProductDetails(productId).subscribe(data => this.productDetails = data);
  }

  ngOnInit() {
    this.getProductDetails(this.productId);
  }

}
