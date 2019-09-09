import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";


import {ProductSearchComponent} from "./product-search.component";
import {SliderComponent} from "./slider/slider.component";


@NgModule({
  declarations: [
    ProductSearchComponent,
    SliderComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule
  ],
  exports: [
    ProductSearchComponent,
    SliderComponent
  ],
  providers: []
})
export class ProductSearchModule {
  constructor(){}
}
