import {Parameters} from "./parameters";

export class Product {

    private _productId: number;
    private _title: string;
    private _shortDescription: string;
    private _type: string;
    private _previewImage: string;
    private _price: number;
    private _availableAmount: number;
    private _productParameters: Parameters;

    constructor(productId: number, title: string, shortDescription: string, type: string, previewImage: string,
                price: number, availableAmount: number, productParameters: Parameters) {
        this._productId = productId;
        this._title = title;
        this._shortDescription = shortDescription;
        this._type = type;
        this._previewImage = previewImage;
        this._price = price;
        this._availableAmount = availableAmount;
        this._productParameters = productParameters;
    }

    get productId(): number {
        return this._productId;
    }

    set productId(value: number) {
        this._productId = value;
    }

    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    get shortDescription(): string {
        return this._shortDescription;
    }

    set shortDescription(value: string) {
        this._shortDescription = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
    }

    get previewImage(): string {
        return this._previewImage;
    }

    set previewImage(value: string) {
        this._previewImage = value;
    }

    get price(): number {
        return this._price;
    }

    set price(value: number) {
        this._price = value;
    }

    get availableAmount(): number {
        return this._availableAmount;
    }

    set availableAmount(value: number) {
        this._availableAmount = value;
    }

    get productParameters(): Parameters {
        return this._productParameters;
    }

    set productParameters(value: Parameters) {
        this._productParameters = value;
    }
}
