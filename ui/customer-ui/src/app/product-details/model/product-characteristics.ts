export class ProductCharacteristics {

  private _strong: number;
  private _sour: number;
  private _bitter: number;

  constructor(strong: number, sour: number, bitter: number) {
    this._strong = strong;
    this._sour = sour;
    this._bitter = bitter;
  }

  get strong(): number {
    return this._strong;
  }

  set strong(value: number) {
    this._strong = value;
  }

  get sour(): number {
    return this._sour;
  }

  set sour(value: number) {
    this._sour = value;
  }

  get bitter(): number {
    return this._bitter;
  }

  set bitter(value: number) {
    this._bitter = value;
  }
}
