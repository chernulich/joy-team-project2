import {ActivatedRoute, ActivatedRouteSnapshot, Params, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {ProductDetailsService} from "./service/product-details.service";

export class ProductDetailsResolverService implements Resolve<any> {

  constructor(private activateRoute: ActivatedRoute, private productDetailsService: ProductDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
      return this.productDetailsService.getSelectedProductForDetails(+route.params.id);
  }

}
