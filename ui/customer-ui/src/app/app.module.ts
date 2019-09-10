import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {AppComponent} from './app.component';
import {ExampleComponent} from './example/example.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './header/header.component';
import {OrderReceivedComponent} from './order-received/order-received.component';
import {CheckoutComponent} from './checkout-customer/checkout.component';
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {FooterComponent} from './footer/footer.component';
import {ProductListHeaderComponent} from './product-list/product-list-header/product-list-header.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ProductResultComponent } from './product-list/product-result/product-result.component';
import {ProductSearchModule} from "./product-list/product-search/product-search.module";
import {GetIconsPipe} from "./product-list/pipes/getIcons";

@NgModule({
  declarations: [
    AppComponent,
    ExampleComponent,
    ProductDetailsComponent,
    ProductListComponent,
    HeaderComponent,
    OrderReceivedComponent,
    FooterComponent,
    CheckoutComponent,
    ProductListHeaderComponent,
    GetIconsPipe,
    ProductResultComponent
  ],
  imports: [
    BrowserModule,
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ProductSearchModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
