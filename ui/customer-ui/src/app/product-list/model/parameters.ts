export class Parameters {

    private _decaf: boolean;
    private _coffeeType: string;
    private _strong: number;
    private _sour: number;
    private _bitter: number;


  constructor(decaf: boolean, coffeeType: string, strong: number, sour: number, bitter: number) {
    this._decaf = decaf;
    this._coffeeType = coffeeType;

  }


  get decaf(): boolean {
    return this._decaf;
  }

  set decaf(value: boolean) {
    this._decaf = value;
  }

  get coffeeType(): string {
    return this._coffeeType;
  }

  set coffeeType(value: string) {
    this._coffeeType = value;
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
