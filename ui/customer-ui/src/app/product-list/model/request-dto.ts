import {Characteristics} from "./characteristics";

export class RequestDto {

    private _page: number;
    private _results: number;
    private _search: string;
    private _priceMin: number;
    private _priceMax: number;
    private _sortBy: string;
    private _characteristics: Characteristics;

    constructor(page: number, results: number, search: string, priceMin: number, priceMax: number, sortBy: string, characteristics: Characteristics) {
        this._page = page;
        this._results = results;
        this._search = search;
        this._priceMin = priceMin;
        this._priceMax = priceMax;
        this._sortBy = sortBy;
        this._characteristics = characteristics;
    }

    get page(): number {
        return this._page;
    }

    set page(value: number) {
        this._page = value;
    }

    get results(): number {
        return this._results;
    }

    set results(value: number) {
        this._results = value;
    }

    get search(): string {
        return this._search;
    }

    set search(value: string) {
        this._search = value;
    }

    get priceMin(): number {
        return this._priceMin;
    }

    set priceMin(value: number) {
        this._priceMin = value;
    }

    get priceMax(): number {
        return this._priceMax;
    }

    set priceMax(value: number) {
        this._priceMax = value;
    }

    get sortBy(): string {
        return this._sortBy;
    }

    set sortBy(value: string) {
        this._sortBy = value;
    }

    get characteristics(): Characteristics {
        return this._characteristics;
    }

    set characteristics(value: Characteristics) {
        this._characteristics = value;
    }

    getDefaultDto(): RequestDto {
        return new RequestDto(1,
            10,
            "search product",
            10,
            100,
            "popular | price | name",
            new Characteristics(1,2,3,4,
                5,6,true,true,'arabika')
            );
    }
}
