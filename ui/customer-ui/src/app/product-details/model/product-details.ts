import {ProductCharacteristics} from "./product-characteristics";

export class ProductDetails {

  private _productId: number;
  private _productName: string;
  private _description: string;
  private _shortDescription: string;
  private _images: string[]=[];
  private _previewImage: string;
  private _amountAvailable: number;
  private _characteristics: ProductCharacteristics;
  private _price: number;


  constructor(productId: number, productName: string, description: string, shortDescription: string, images: string[], previewImage: string, amountAvailable: number, characteristics: ProductCharacteristics, price: number) {
    this._productId = productId;
    this._productName = productName;
    this._description = description;
    this._shortDescription = shortDescription;
    this._images = images;
    this._previewImage = previewImage;
    this._amountAvailable = amountAvailable;
    this._characteristics = characteristics;
    this._price = price;
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

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get shortDescription(): string {
    return this._shortDescription;
  }

  set shortDescription(value: string) {
    this._shortDescription = value;
  }

  get images(): string[] {
    return this._images;
  }

  set images(value: string[]) {
    this._images = value;
  }

  get previewImage(): string {
    return this._previewImage;
  }

  set previewImage(value: string) {
    this._previewImage = value;
  }

  get amountAvailable(): number {
    return this._amountAvailable;
  }

  set amountAvailable(value: number) {
    this._amountAvailable = value;
  }

  get characteristics(): ProductCharacteristics {
    return this._characteristics;
  }

  set characteristics(value: ProductCharacteristics) {
    this._characteristics = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }
}
