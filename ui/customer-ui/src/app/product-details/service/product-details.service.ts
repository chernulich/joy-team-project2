import {Injectable} from "@angular/core";
import {HttpService} from "../../service/http/http.service";

@Injectable()
export class ProductDetailsService {
  constructor(private http: HttpService){}

  public getSelectedProductForDetails(id){
    this.http.getProductDetails(id)
      .subscribe((productDetails) => {
          console.log(productDetails);
      });
  }
}
