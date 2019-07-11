import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ExampleComponent} from "./example/example.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {HeaderComponent} from "./header/header.component";

const routes: Routes = [
  //{path: '', component: ExampleComponent},
  // {path: 'examples', component: ExampleComponent},
  {path: 'products/:id' , component: ProductDetailsComponent},
  {path: 'header', component: HeaderComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
