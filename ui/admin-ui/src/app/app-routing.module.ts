import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ExampleComponent} from "./example/example.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: 'examples', component: ExampleComponent},
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
