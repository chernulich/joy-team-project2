import {Injectable} from "@angular/core";
import {HttpService} from "../../service/http/http.service";
import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";

@Injectable()
export class ProductDetailsService {
  constructor(private http: HttpService,
              private dataStorage: ProductsDataStorageService){}


  public getSelectedProductForDetails(id){
    this.http.getProductDetails(id)
      .subscribe((productDetails) => {
          console.log(productDetails);
          this.dataStorage.setSelectedProductForDetails(productDetails);
      });
  }
}
