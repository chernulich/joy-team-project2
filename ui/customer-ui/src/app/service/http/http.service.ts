import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: "root"
})
export class HttpService {

  constructor(private http: HttpClient) {

  }

  getFilteredProducts(requestBody: {}): Observable<any> {
    return this.http.post('api/customer/products', requestBody);
  }

  getProductDetails(id: number): Observable<any>{
    return this.http.get(`/api/customer/products/${id}`);
  }
}
