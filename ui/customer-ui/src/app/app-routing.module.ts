import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ExampleComponent} from "./example/example.component";

const routes: Routes = [
  {path: '', component: ExampleComponent},
  {path: 'examples', component: ExampleComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
