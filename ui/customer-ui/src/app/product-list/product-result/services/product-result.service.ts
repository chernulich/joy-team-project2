
import {HttpService} from "../../../service/http/http.service";
import {ProductsDataStorageService} from "../../../service/data-storage/products-data-storage.service";
import {BehaviorSubject, fromEvent, of} from "rxjs";
import {catchError, debounceTime, delay, map} from "rxjs/operators";
import {Injectable} from "@angular/core";

interface ProductsStore {
  products: [];
  popular: {};
}

@Injectable()
export class ProductResultService {

  constructor(private http: HttpService,
              private productsStore: ProductsDataStorageService) { }

  isScrollable = true;
  requestBody: BehaviorSubject<any>  = new BehaviorSubject<any>({});
  public httpGetFilteredProducts(requestBody: {}){
    this.http.getFilteredProducts(requestBody)
      .pipe(
        catchError((err) => {
          return of([])
        })
      )
      .subscribe((products: any) => {
        if(products.length === 0){
          this.isScrollable = false;
          this.productsStore.setCurrentPage(this.productsStore.getCurrentPage() - 1);
          return;
        }
        if(!products.products){
          this.productsStore.updateProductStore(['no-products']);
          return;
        }
        const allProducts = [{...products.popular},...products.products];
        console.log(allProducts);
        this.productsStore.updateProductStore(allProducts);
      });
  }

  public pagination(){
    return fromEvent(document,'wheel')
      .pipe(
        debounceTime(700),
        map((event: WheelEvent) => {
          if(Math.ceil((pageYOffset + window.innerHeight) / document.documentElement.offsetHeight * 100) >= 70 &&
          event.deltaY > 0 && this.isScrollable){
            this.productsStore.setCurrentPage(this.productsStore.getCurrentPage() + 1);
            console.log("curr page: ", this.productsStore.getCurrentPage(), "curr request body: ",
              { ...this.requestBody.getValue(),"page":this.productsStore.getCurrentPage(), "results": 6});
            this.httpGetFilteredProducts(
              { ...this.requestBody.getValue(),"page":this.productsStore.getCurrentPage(), "results": 6}
              );
          }
      }))
  }

}
