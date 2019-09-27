import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class HttpService {

  constructor(private http: HttpClient) {

  }

  getFilteredProducts(requestBody: {}) {
    return this.http.post('api/customer/products', requestBody);
  }
}
