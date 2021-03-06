import {NgModule} from "@angular/core";
import {Route, RouterModule, Routes} from "@angular/router";
import {ProductListComponent} from "./product-list.component";
import {ProductDetailsComponent} from "../product-details/product-details.component";
import {ProductDetailsResolverService} from "../product-details/product-details-resolver.service";

const productListRoutes: Routes = [
  {path: '', redirectTo: 'product-list', pathMatch: 'full'},
  {path: 'product-list', component: ProductListComponent},
  {path: 'product-list/:id', component: ProductDetailsComponent, resolve: {productDetails: ProductDetailsResolverService}}
];

@NgModule({
  imports: [RouterModule.forChild(productListRoutes)],
  exports: [RouterModule]
})
export class ProductListRoutingModule {

}
