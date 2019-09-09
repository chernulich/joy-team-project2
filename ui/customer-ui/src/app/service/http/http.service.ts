import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../../model/product.model";

@Injectable({
  providedIn: "root"
})
export class HttpService {
  // when proper backend for the part of getting products, getting filtered, getting most popular product
  // will be ready i'll change the service according to it and some logic in components which using this service
  constructor(private http: HttpClient) {

  }

  getMostPopular(): Observable<Product>{
    return this.http.get<Product>(`http://localhost:3000/popular`);
  }

  getAllProducts(){
    return this.http.get<Product[]>("http://localhost:3000/products");
  }
}
