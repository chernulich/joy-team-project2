import {CustomerInfo} from "./customerInfo";
import {Delivery} from "./delivery";
import {Prodacts} from "./prodacts";
import {Charges} from "./charges";
import {contactInfo} from "./contactInfo";

export class RequestCheckoutDto {

  private _customerInfo: CustomerInfo;
  private _delivery: Delivery;
  private _products: Prodacts;
  private _charges: Charges;

  constructor(customerInfo: CustomerInfo, delivery: Delivery, products: Prodacts, charges: Charges) {
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

  get products(): Prodacts {
    return this._products;
  }

  set products(value: Prodacts) {
    this._products = value;
  }

  get charges(): Charges {
    return this._charges;
  }

  set charges(value: Charges) {
    this._charges = value;
  }

 getDefultRequestCheckoutDto (): RequestCheckoutDto {
    return new RequestCheckoutDto
    (new CustomerInfo
      ('Company name: relevant for business entities','doe@site.com', Number('+972501112233'),
                  new contactInfo('John', 'Doe', Number('+972581112233'))),
      new Delivery('name on the package', 'eilat', 'rotshild', Number('11'), '115', '1', 'comment'),
      new Prodacts(Number('123123'), Number('0.250'), Number('100500')),
      new Charges(Number('0'), Number('123.1')))
 };

 }
