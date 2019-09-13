export class Product {

  constructor(productName: string,
              description: string,
              shortDescription: string,
              price: number,
              productId: number,
              available: number,
              previewImage: string,
              sour: number,
              bitter: number,
              strong: number,
              ground: boolean,
              decoffein: boolean,
              instant: boolean){
    this.productName = productName;
    this.description = description;
    this.shortDescription = shortDescription;
    this.price = price;
    this.productId = productId;
    this.available = available;
    this.previewImage = previewImage;
    this.sour = sour;
    this.bitter = bitter;
    this.strong = strong;
    this.ground = ground;
    this.decoffein = decoffein;
    this.instant = instant;

  }


  private _productName: string;
  private _description: string;
  private _shortDescription: string;
  private _price: number;
  private _productId: number;
  private _available: number;
  private _previewImage: string;
  private _sour: number;
  private _bitter: number;
  private _strong: number;
  private _ground: boolean;
  private _decoffein: boolean;
  private _instant: boolean;
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

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

  get productId(): number {
    return this._productId;
  }

  set productId(value: number) {
    this._productId = value;
  }

  get available(): number {
    return this._available;
  }

  set available(value: number) {
    this._available = value;
  }

  get previewImage(): string {
    return this._previewImage;
  }

  set previewImage(value: string) {
    this._previewImage = value;
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

  get strong(): number {
    return this._strong;
  }

  set strong(value: number) {
    this._strong = value;
  }

  get ground(): boolean {
    return this._ground;
  }

  set ground(value: boolean) {
    this._ground = value;
  }

  get decoffein(): boolean {
    return this._decoffein;
  }

  set decoffein(value: boolean) {
    this._decoffein = value;
  }

  get instant(): boolean {
    return this._instant;
  }

  set instant(value: boolean) {
    this._instant = value;
  }

}
