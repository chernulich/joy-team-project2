export class SubmitOrderResponse {

  private _orderId: number;

  constructor(orderId: number) {
    this._orderId = orderId;
  }

  get orderId(): number {
    return this._orderId;
  }

  set orderId(value: number) {
    this._orderId = value;
  }
}
