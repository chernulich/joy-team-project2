import {NgModule} from "@angular/core";
import {Route, RouterModule, Routes} from "@angular/router";
import {ProductListComponent} from "./product-list.component";
import {ProductDetailsComponent} from "../product-details/product-details.component";

const productListRoutes: Routes = [
  {path: '', redirectTo: 'product-list', pathMatch: 'full'},
  {path: 'product-list', component: ProductListComponent},
  {path: 'product-list/:id', component: ProductDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(productListRoutes)],
  exports: [RouterModule]
})
export class ProductListRoutingModule {

}
