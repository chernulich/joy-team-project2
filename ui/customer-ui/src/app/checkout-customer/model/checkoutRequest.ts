import {CustomerInfo} from "./customerInfo";
import {Delivery} from "./delivery";
import {Charges} from "./charges";
import {Products} from "./products";
import {ContactInfo} from "./contactInfo";

export class CheckoutRequest {

  private _customerInfo: CustomerInfo;
  private _delivery: Delivery;
  private _products: Products;
  private _charges: Charges;

  constructor(customerInfo: CustomerInfo, delivery: Delivery, products: Products, charges: Charges) {
    this._customerInfo = customerInfo;
    this._delivery = delivery;
    this._products = products;
    this._charges = charges;
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

  get products(): Products {
    return this._products;
  }

  set products(value: Products) {
    this._products = value;
  }

  get charges(): Charges {
    return this._charges;
  }

  set charges(value: Charges) {
    this._charges = value;
  }

 getDefaultCheckoutRequest (): CheckoutRequest {
    return new CheckoutRequest
    (new CustomerInfo
      ('Company name: relevant for business entities','doe@site.com', '+972501112233',
                  new ContactInfo('John', 'Doe', '+972581112233')),
      new Delivery('name on the package', 'Alabasta', 'rotshild', '11', '115', 1, 'comment'),
      new Products(123123, 0.250, 100500),
      new Charges(0, 123.1))
 };

 }
