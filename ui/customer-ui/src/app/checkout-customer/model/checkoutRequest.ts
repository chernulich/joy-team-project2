import {CustomerInfo} from "./customerInfo";
import {Delivery} from "./delivery";
import {ContactInfo} from "./contactInfo";
import {Product} from "./product";

export class CheckoutRequest {

  private _customerInfo: CustomerInfo;
  private _delivery: Delivery;
  private _product: Product;

  constructor(customerInfo: CustomerInfo, delivery: Delivery, product: Product) {
    this._customerInfo = customerInfo;
    this._delivery = delivery;
    this._product = product;
  }


  get customerInfo(): CustomerInfo {
    return this._customerInfo;
  }

  set customerInfo(value: CustomerInfo) {
    this._customerInfo = value;
  }

  get delivery(): Delivery {
    return this._delivery;
  }

  set delivery(value: Delivery) {
    this._delivery = value;
  }

  get product(): Product {
    return this._product;
  }

  set product(value: Product) {
    this._product = value;
  }


 getDefaultCheckoutRequest (): CheckoutRequest {
    return new CheckoutRequest
    (new CustomerInfo
      ('Company name: relevant for business entities','doe@site.com', '+972501112233',
                  new ContactInfo('John', 'Doe', '+972581112233')),
      new Delivery('name on the package', 'Alabasta', 'rotshild', '11', '115', 1, 'comment'),
      new Product(123123, 100500))
 };

 }
