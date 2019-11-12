import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {ProductDetailsService} from "./service/product-details.service";


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  public quantityValue: number = 1;
  public maxQuantity: number = 10;
  public minQuantity: number = 1;

  constructor(private activatedRoute: ActivatedRoute,
              private productDetailsService: ProductDetailsService) {}

  ngOnInit() {
    this.activatedRoute.params
      .subscribe((params: Params) => {
        this.productDetailsService.getSelectedProductForDetails(params.id);
      });
  }

  quantityMinus(){
    if(this.quantityValue > this.minQuantity){
      this.quantityValue--;
    }
  }

  quantityPlus(){
    if(this.quantityValue < this.maxQuantity){
      this.quantityValue++;
    }
  }
}
