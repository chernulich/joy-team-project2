import {NgModule} from "@angular/core";

import {ProductSearchComponent} from "./product-search.component";
import {SearchComponent} from "./search/search.component";

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {SliderComponent} from "./search/slider/slider.component";


@NgModule({
  declarations: [
    ProductSearchComponent,
    SearchComponent,
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
    SliderComponent
  ],
  providers: []
})
export class ProductSearchModule {
  constructor(){}
}
