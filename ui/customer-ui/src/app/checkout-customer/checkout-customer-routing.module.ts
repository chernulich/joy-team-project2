import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {CheckoutComponent} from "./checkout.component";

const checkoutCustomerRoutes: Routes = [
  {path: '', component: CheckoutComponent}
];

@NgModule({
  imports: [RouterModule.forChild(checkoutCustomerRoutes)],
  exports: [RouterModule]
})
export class CheckoutCustomerRoutingModule {

}
