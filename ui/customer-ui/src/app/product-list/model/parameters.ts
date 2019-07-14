export class Parameters {
    private _flavour: string;
    private _rate: number;
    private _decaf: boolean;
    private _coffeeType: string;

    constructor(flavour: string, rate: number, decaf: boolean, coffeeType: string) {
        this._flavour = flavour;
        this._rate = rate;
        this._decaf = decaf;
        this._coffeeType = coffeeType;
    }

    get flavour(): string {
        return this._flavour;
    }

    set flavour(value: string) {
        this._flavour = value;
    }

    get rate(): number {
        return this._rate;
    }

    set rate(value: number) {
        this._rate = value;
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
