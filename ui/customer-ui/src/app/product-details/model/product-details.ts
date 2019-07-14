import {ProductCharacteristics} from "./product-characteristics";
import {ProductInStock} from "./product-inStock";

export class ProductDetails {

  private _productId: number;
  private _productName: string;
  private _quantityAvailableKg: number;
  private _images: string[]=[];
  private _characteristics: ProductCharacteristics;
  private _description: string;
  private _inStock: ProductInStock;
  private _unitPrice: number;


  constructor(productId: number, productName: string, quantityAvailableKg: number, images: string[],
              characteristics: ProductCharacteristics, description: string, inStock: ProductInStock, unitPrice: number) {
    this._productId = productId;
    this._productName = productName;
    this._quantityAvailableKg = quantityAvailableKg;
    this._images = images;
    this._characteristics = characteristics;
    this._description = description;
    this._inStock = inStock;
    this._unitPrice = unitPrice;
  }

  get productId(): number {
    return this._productId;
  }

  set productId(value: number) {
    this._productId = value;
  }

  get productName(): string {
    return this._productName;
  }

  set productName(value: string) {
    this._productName = value;
  }

  get quantityAvailableKg(): number {
    return this._quantityAvailableKg;
  }

  set quantityAvailableKg(value: number) {
    this._quantityAvailableKg = value;
  }

  get images(): string[] {
    return this._images;
  }

  set images(value: string[]) {
    this._images = value;
  }

  get characteristics(): ProductCharacteristics {
    return this._characteristics;
  }

  set characteristics(value: ProductCharacteristics) {
    this._characteristics = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get inStock(): ProductInStock {
    return this._inStock;
  }

  set inStock(value: ProductInStock) {
    this._inStock = value;
  }

  get unitPrice(): number {
    return this._unitPrice;
  }

  set unitPrice(value: number) {
    this._unitPrice = value;
  }
}
