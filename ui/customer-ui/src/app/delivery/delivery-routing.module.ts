import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DeliveryComponent} from "./delivery.component";

const aboutRoutes: Routes = [
  {path: '', component: DeliveryComponent}
];
@NgModule({
  imports:[RouterModule.forChild(aboutRoutes)],
  exports:[RouterModule]
})
export class DeliveryRoutingModule {

}
