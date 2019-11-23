import {ModuleWithProviders, NgModule} from "@angular/core";
import {CheckoutComponent} from "./checkout.component";
import {ProductCartComponent} from "./product-cart/product-cart.component";
import {CommonModule} from "@angular/common";
import {CheckoutCustomerRoutingModule} from "./checkout-customer-routing.module";


@NgModule({
  declarations: [
    CheckoutComponent,
    ProductCartComponent
  ],
  imports: [
    CheckoutCustomerRoutingModule,
    CommonModule
  ],
  exports: [],
  providers: [

  ]
})
export class CheckoutCustomerModule {

}
