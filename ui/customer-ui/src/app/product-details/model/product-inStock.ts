export class  ProductInStock {

  private _isAvailable: boolean;
  private _quantityAvailable: number;

  constructor(isAvailable: boolean, quantityAvailable: number) {
    this._isAvailable = isAvailable;
    this._quantityAvailable = quantityAvailable;
  }

  get isAvailable(): boolean {
    return this._isAvailable;
  }

  set isAvailable(value: boolean) {
    this._isAvailable = value;
  }

  get quantityAvailable(): number {
    return this._quantityAvailable;
  }

  set quantityAvailable(value: number) {
    this._quantityAvailable = value;
  }
}
