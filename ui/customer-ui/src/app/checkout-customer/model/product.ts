 export class Product {

   private _productId: number;
   private _weight: number;
   private _quantity: number;

   get productId(): number {
     return this._productId;
   }

   set productId(value: number) {
     this._productId = value;
   }

   get weight(): number {
     return this._weight;
   }

   set weight(value: number) {
     this._weight = value;
   }

   get quantity(): number {
     return this._quantity;
   }

   set quantity(value: number) {
     this._quantity = value;
   }

   constructor(productId: number, weight: number, quantity: number) {
     this._productId = productId;
     this._weight = weight;
     this._quantity = quantity;
   }


 }
