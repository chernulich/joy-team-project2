import {NgModule} from "@angular/core";
import {DeliveryComponent} from "./delivery.component";
import {Delivery} from "../checkout-customer/model/delivery";
import {DeliveryRoutingModule} from "./delivery-routing.module";

@NgModule({
  declarations: [DeliveryComponent],
  imports: [DeliveryRoutingModule]
})
export class DeliveryModule {

}
