import {Injectable} from "@angular/core";
import {HttpService} from "../../service/http/http.service";
import {Observable} from "rxjs";
import {IProductDetails} from "../../service/data-storage/products-data-storage.service";

@Injectable()
export class ProductDetailsService {
  constructor(private http: HttpService){}


  public getSelectedProductForDetails(id): Observable<IProductDetails>{
    return this.http.getProductDetails(id);
  }
}
