import {Injectable} from "@angular/core";
import { BehaviorSubject, Subscription} from "rxjs";
import set = Reflect.set;

export interface ICartProduct {
  productId: number;
  productTitle: string;
  shortDescription: string;
  price: number;
  quantity: number;
  previewImage: string;
  weight: number;
}

@Injectable()
export class ProductCartService {
  constructor(){}

  private timeoutId: number | number | object = null;

  private productCart: BehaviorSubject<Array<ICartProduct>> = new BehaviorSubject<Array<ICartProduct>>([]);

  getProductsInCart(): BehaviorSubject<Array<ICartProduct>>{
    return this.productCart;
  }


  updateCartProduct(product,target?): ICartProduct{
    const result: ICartProduct = {
       price: +(product.price * product.quantity).toFixed(2),
       quantity: !!target ? product.quantity + target.quantity : product.quantity,
       weight: !!target ?  product.weight * product.quantity / 1000 + target.weight : product.weight * product.quantity / 1000,
       shortDescription: product.shortDescription,
       productTitle: product.productTitle,
       previewImage: product.previewImage,
       productId: product.productId
    };
    return result;
  }

  createCartProductObjectToAdd(product,quantity){
    const cartProduct: ICartProduct = {
      weight: 250,
      quantity: quantity,
      price: product.price,
      productId: product.id || product.productId,
      previewImage: product.previewImage,
      productTitle: product.productName || product.title,
      shortDescription: product.shortDescription
    };
    return cartProduct;
  }

  writeToLocalStorage(cart){
    localStorage.setItem('cart',JSON.stringify(this.productCart.getValue()));
  }

  getCartFromLocalStorage(){
    return JSON.parse(localStorage.getItem('cart'));
  }

  setProductCart(cart){
    this.productCart.next(cart);
    if(!this.timeoutId){
      this.timeoutId = setTimeout(() => {

      },5000);
    }
  }

  removeItemFromCart(id){
    const index = this.productCart.getValue()
      .findIndex((cartProduct) => {
        return cartProduct.productId === id;
      });
    if(index >= 0){
      const cart = [...this.productCart.getValue()];
      cart.splice(index,1);
      this.productCart.next(cart);
      this.writeToLocalStorage(cart);
    }
    console.log(this.productCart.getValue());
  }

  updateCart(product: any){
   let index = this.productCart.getValue().findIndex((cartProduct) => {
      return cartProduct.productId === product.productId;
    });

    let productCartCopy = this.productCart.getValue().slice();

    if(index >= 0){
      productCartCopy[index] = this.updateCartProduct(product,productCartCopy[index]);
      this.productCart.next(productCartCopy);
      console.log(productCartCopy);
    }
    else{
      product = this.updateCartProduct(product);
      productCartCopy.push(product);
      console.log(productCartCopy);
      this.productCart.next(productCartCopy);
    }
    this.writeToLocalStorage(productCartCopy);
  }

  updateDetailsProduct(product){
    const foundCartProduct = this.productCart.getValue()
      .find((cartProduct) => {
        return cartProduct.productId === product.id;
      });

      if(!!foundCartProduct){
        product.amountAvailable -= foundCartProduct.quantity;
      }
    return product;
  }

  updateProductListQuantity(productListFromStorage){
    this.productCart.getValue()
      .forEach((cartProduct) => {
        let product = productListFromStorage.find((productFromStorage) => {
          return cartProduct.productId === productFromStorage.productId;
        });
        if(!!product){
          product.availableAmount -= cartProduct.quantity;
        }
      })
  }

  // updateProductList(product){
  //   // debugger;
  //   // this.subscription = from(this.productList,asyncScheduler)
  //   //   .pipe(find((productFromList) => {
  //   //     return productFromList.productId === product.productId;
  //   //   }))
  //   //   .subscribe((productFromList) => {
  //   //     productFromList.availableAmount -= product.quantity;
  //   //     this.subscription.unsubscribe();
  //   //   });
  // }

}
