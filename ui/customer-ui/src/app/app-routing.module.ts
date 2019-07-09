import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ExampleComponent} from "./example/example.component";
import {ProductListComponent} from "./product-list/product-list.component";
import {HeaderComponent} from "./header/header.component";

const routes: Routes = [
  {path: '', component: ProductListComponent},
  {path: 'examples', component: ExampleComponent},
  {path: 'header', component: HeaderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
