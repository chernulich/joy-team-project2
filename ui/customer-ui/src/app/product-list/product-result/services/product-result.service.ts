
import {HttpService} from "../../../service/http/http.service";
import {ProductsDataStorageService} from "../../../service/data-storage/products-data-storage.service";
import {BehaviorSubject, combineLatest, forkJoin, fromEvent, of, Subject} from "rxjs";
import {catchError, debounceTime, delay, map, take} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {ProductCartService} from "../../../checkout-customer/product-cart/services/product-cart.service";

interface ProductsStore {
  products: [];
  popular: {};
}

@Injectable()
export class ProductResultService {

  constructor(private http: HttpService,
              private productsStore: ProductsDataStorageService,
              private productCart: ProductCartService) { }

  public isScrollable = true;
  public filterSearch = false;
  requestBody: BehaviorSubject<any>  = new BehaviorSubject<any>({});
  public httpGetFilteredProducts(requestBody: {}){
    this.http.getFilteredProducts(requestBody)
      .subscribe((products: any) => {
        if(products.products.length === 0){
          this.isScrollable = false;
          if(this.filterSearch){
            this.productsStore.updateProductStore(['no-products']);
          }
          if(!this.filterSearch){
            this.productsStore.setCurrentPage(this.productsStore.getCurrentPage() - 1);
          }
          return;
        }
        this.filterSearch = false;
        this.isScrollable = true;

        const allProducts = [{...products.popular},...products.products];

        console.log(allProducts);
        this.productsStore.updateProductStore(allProducts);
      });
  }

  public pagination(){
    return fromEvent(document,'wheel')
      .pipe(
        debounceTime(500),
        map((event: WheelEvent) => {
          if(Math.ceil((pageYOffset + window.innerHeight) / document.documentElement.offsetHeight * 100) >= 70 &&
          event.deltaY > 0 && this.isScrollable){
            this.productsStore.setCurrentPage(this.productsStore.getCurrentPage() + 1);
            this.httpGetFilteredProducts(
              { ...this.requestBody.getValue(),"page":this.productsStore.getCurrentPage(), "results": 6}
              );
          }
      }));
  }

}
