import {NgModule} from "@angular/core";

import {ProductSearchComponent} from "./product-search.component";
import {SearchComponent} from "./search/search.component";
import {ResultComponent} from "./result/result.component";

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {SliderComponent} from "./search/slider/slider.component";


@NgModule({
  declarations: [
    ProductSearchComponent,
    SearchComponent,
    ResultComponent,
    SliderComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule
  ],
  exports: [
    ProductSearchComponent,
    SearchComponent,
    ResultComponent,
    SliderComponent
  ],
  providers: []
})
export class ProductSearchModule {
  constructor(){}
}
