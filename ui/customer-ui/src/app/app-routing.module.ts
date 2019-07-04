import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ExampleComponent} from "./example/example.component";

const routes: Routes = [
  {path: '', component: ExampleComponent},
  {path: 'examples', component: ExampleComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
