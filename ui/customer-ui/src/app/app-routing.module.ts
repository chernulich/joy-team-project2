import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ExampleComponent} from "./example/example.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {HeaderComponent} from "./header/header.component";
import {ProductListComponent} from "./product-list/product-list.component";
import {OrderReceivedComponent} from "./order-received/order-received.component";

const routes: Routes = [
  {path: '', component: ExampleComponent},
  {path: 'examples', component: ExampleComponent},
  {path: 'header', component: HeaderComponent},
  {path: 'products/:id' , component: ProductDetailsComponent},
  {path: 'received', component: OrderReceivedComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
