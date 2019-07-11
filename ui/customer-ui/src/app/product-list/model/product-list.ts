import {Product} from "./product";

export class ProductList {
    private _popular: Product;
    private _products: Product[];

    constructor(popular: Product, products: Product[]) {
        this._popular = popular;
        this._products = products;
    }

    get popular(): Product {
        return this._popular;
    }

    set popular(value: Product) {
        this._popular = value;
    }

    get products(): Product[] {
        return this._products;
    }

    set products(value: Product[]) {
        this._products = value;
    }
}
