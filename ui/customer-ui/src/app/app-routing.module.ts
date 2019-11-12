import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {HeaderComponent} from "./header/header.component";
import {ProductListComponent} from "./product-list/product-list.component";
import {OrderReceivedComponent} from "./order-received/order-received.component";
import {CheckoutComponent} from "./checkout-customer/checkout.component";
import {ExampleComponent} from "./example/example.component";
import {AboutComponent} from "./about/about.component";
import {DeliveryComponent} from "./delivery/delivery.component";

// const routes: Routes = [
//   {path: 'examples', component: ExampleComponent},
//   {path: 'header', component: HeaderComponent},
//   {path: 'product-list', component: ProductListComponent},
//   {path: 'product-details', component: ProductDetailsComponent},
//   {path: 'products/:id' , component: ProductDetailsComponent},
//   {path: 'received/:orderId', component: OrderReceivedComponent},
//   {path: 'checkout', component: CheckoutComponent},
// ];

const routes: Routes = [
  {path: 'about', loadChildren: './about/about.module#AboutModule'},
  {path: 'delivery', loadChildren: './delivery/delivery.module#DeliveryModule'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
