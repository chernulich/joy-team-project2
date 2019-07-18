export class SubmitOrderResponse {

  private _orderId: number;
  private _message: string;

  get message(): string {
    return this._message;
  }

  set message(value: string) {
    this._message = value;
  }

  get orderId(): number {
    return this._orderId;
  }

  set orderId(value: number) {
    this._orderId = value;
  }

  constructor(orderId: number, message: string) {
    this._orderId = orderId;
    this._message = message;
  }
}
