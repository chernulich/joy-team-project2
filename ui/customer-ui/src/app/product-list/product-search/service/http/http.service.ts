import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../../../../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  getAllProducts(){
   return this.http.get<Product[]>("http://localhost:3000/products");
  }
}
