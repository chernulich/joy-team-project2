export class Parameters {
    private _decaf: boolean;
    private _coffeeType: string;


  constructor(decaf: boolean, coffeeType: string) {
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
}
