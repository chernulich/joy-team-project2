import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {BsDropdownModule } from 'ngx-bootstrap/dropdown';
import {AppComponent} from './app.component';
import {ExampleComponent} from './example/example.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './header/header.component';
import { OrderReceivedComponent } from './order-received/order-received.component';
import { CheckoutComponent } from './checkout-customer/checkout.component';
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    ExampleComponent,
    ProductDetailsComponent,
    ProductListComponent,
    HeaderComponent,
    OrderReceivedComponent,
    FooterComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
