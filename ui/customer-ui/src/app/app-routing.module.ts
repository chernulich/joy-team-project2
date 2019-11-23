import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: 'about', loadChildren: './about/about.module#AboutModule'},
  {path: 'delivery', loadChildren: './delivery/delivery.module#DeliveryModule'},
  {path: 'checkout-customer', loadChildren: './checkout-customer/checkout-customer.module#CheckoutCustomerModule'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
