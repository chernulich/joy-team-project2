
import {NgModule} from "@angular/core";
import {GetIconsPipe} from "./pipes/getIcons";
import {ProductListHeaderComponent} from "./product-list-header/product-list-header.component";
import {ProductResultComponent} from "./product-result/product-result.component";
import {ProductListComponent} from "./product-list.component";
import {CommonModule} from "@angular/common";
import {ProductSearchComponent} from "./product-search/product-search.component";
import {SliderComponent} from "./product-search/slider/slider.component";
import {CharacteristicRangeComponent} from "./product-search/characteristic-range/characteristic-range.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    ProductListHeaderComponent,
    GetIconsPipe,
    ProductResultComponent,
    ProductListComponent,
    ProductSearchComponent,
    SliderComponent,
    CharacteristicRangeComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule,
    RouterModule,
    HttpClientModule
  ],
  exports: [
    GetIconsPipe
  ]
})
export class ProductListModule {

}
