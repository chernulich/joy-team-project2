
export class Charges{

  private _shipping: number;
  private _tax: number;

  get shipping(): number {
    return this._shipping;
  }

  set shipping(value: number) {
    this._shipping = value;
  }

  get tax(): number {
    return this._tax;
  }

  set tax(value: number) {
    this._tax = value;
  }

  constructor(shipping: number, tax: number) {
    this._shipping = shipping;
    this._tax = tax;
  }
}
