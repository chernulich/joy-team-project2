
export class Characteristics {
    private _bitterFrom: number;
    private _bitterTo: number;
    private _sourFrom: number;
    private _sourTo: number;
    private _strongFrom: number;
    private _strongTo: number;
    private _decaf: boolean;
    private _ground: boolean;
    private _coffeeType: string;

    constructor(bitterFrom: number, bitterTo: number, sourFrom: number, sourTo: number, strongFrom: number, strongTo: number, decaf: boolean, ground: boolean, coffeeType: string) {
        this._bitterFrom = bitterFrom;
        this._bitterTo = bitterTo;
        this._sourFrom = sourFrom;
        this._sourTo = sourTo;
        this._strongFrom = strongFrom;
        this._strongTo = strongTo;
        this._decaf = decaf;
        this._ground = ground;
        this._coffeeType = coffeeType;
    }

    get bitterFrom(): number {
        return this._bitterFrom;
    }

    set bitterFrom(value: number) {
        this._bitterFrom = value;
    }

    get bitterTo(): number {
        return this._bitterTo;
    }

    set bitterTo(value: number) {
        this._bitterTo = value;
    }

    get sourFrom(): number {
        return this._sourFrom;
    }

    set sourFrom(value: number) {
        this._sourFrom = value;
    }

    get sourTo(): number {
        return this._sourTo;
    }

    set sourTo(value: number) {
        this._sourTo = value;
    }

    get strongFrom(): number {
        return this._strongFrom;
    }

    set strongFrom(value: number) {
        this._strongFrom = value;
    }

    get strongTo(): number {
        return this._strongTo;
    }

    set strongTo(value: number) {
        this._strongTo = value;
    }

    get decaf(): boolean {
        return this._decaf;
    }

    set decaf(value: boolean) {
        this._decaf = value;
    }

    get ground(): boolean {
        return this._ground;
    }

    set ground(value: boolean) {
        this._ground = value;
    }

    get coffeeType(): string {
        return this._coffeeType;
    }

    set coffeeType(value: string) {
        this._coffeeType = value;
    }
}

