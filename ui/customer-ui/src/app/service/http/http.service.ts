import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../../model/product.model";
import {ProductListRequest} from "../../product-list/model/product-list-request";

@Injectable({
  providedIn: "root"
})
export class HttpService {
  // when proper backend for the part of getting products, getting filtered, getting most popular product
  // will be ready i'll change the service according to it and some logic in components which using this service
  constructor(private http: HttpClient) {

  }

  getFilteredProducts(requestBody: {}){
    console.log(requestBody);
    // debugger;
    return this.http.post('api/customer/products',requestBody);
  }
}
