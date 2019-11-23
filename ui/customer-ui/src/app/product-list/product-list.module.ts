
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
import {HttpClientModule} from "@angular/common/http";
import {ProductResultService} from "./product-result/services/product-result.service";
import {RouterModule} from "@angular/router";
import {ProductsDataStorageService} from "../service/data-storage/products-data-storage.service";
import {ProductDetailsComponent} from "../product-details/product-details.component";
import {ProductListRoutingModule} from "./product-list-routing.module";
import {ProductDetailsService} from "../product-details/service/product-details.service";
import {ProductDetailsResolverService} from "../product-details/product-details-resolver.service";
import {QuantityHolderDirective} from "../product-details/quantity-holder.directive";

@NgModule({
  declarations: [
    ProductListHeaderComponent,
    GetIconsPipe,
    ProductResultComponent,
    ProductListComponent,
    ProductSearchComponent,
    SliderComponent,
    CharacteristicRangeComponent,
    ProductDetailsComponent,
    QuantityHolderDirective
  ],
  imports: [
    RouterModule,
    CommonModule,
    ReactiveFormsModule,
    ProductListRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [
    ProductDetailsService,
    ProductDetailsResolverService
  ],
  exports: [
    GetIconsPipe
  ]
})
export class ProductListModule {

}
