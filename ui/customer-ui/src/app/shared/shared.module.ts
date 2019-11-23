import {ModuleWithProviders, NgModule} from "@angular/core";
import {ProductCartService} from "../checkout-customer/product-cart/services/product-cart.service";
import {ProductResultService} from "../product-list/product-result/services/product-result.service";
import {ProductsDataStorageService} from "../service/data-storage/products-data-storage.service";

@NgModule({

})
export class SharedModule {
  static forRoot(): ModuleWithProviders{
    return {
      ngModule: SharedModule,
      providers: [
        ProductCartService,
        ProductResultService,
        ProductsDataStorageService
      ]
    }
  }
}
