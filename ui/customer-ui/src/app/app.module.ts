import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {AppComponent} from './app.component';
import {ExampleComponent} from './example/example.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './header/header.component';
import {OrderReceivedComponent} from './order-received/order-received.component';
import {CheckoutComponent} from './checkout-customer/checkout.component';
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {FooterComponent} from './footer/footer.component';
import {ProductListModule} from "./product-list/product-list.module";
import {ProductResultService} from "./product-list/product-result/services/product-result.service";
import {ProductsDataStorageService} from "./service/data-storage/products-data-storage.service";
import {AboutModule} from "./about/about.module";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    AppComponent,
    ExampleComponent,
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
    RouterModule,
    HttpClientModule,
    NgbModule,
    ProductListModule,
    AboutModule
  ],
  providers: [
    ProductResultService,
    ProductsDataStorageService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

}
