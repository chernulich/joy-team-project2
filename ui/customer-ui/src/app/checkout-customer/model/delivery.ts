 export class Delivery {

   private _officialName: string;
   private _city: string;
   private _street: string;
   private _houseNumber: string;
   private _apartment: string;
   private _floor: number;
   private _deliveryComment: string;

   get officialName(): string {
     return this._officialName;
   }

   set officialName(value: string) {
     this._officialName = value;
   }

   get city(): string {
     return this._city;
   }

   set city(value: string) {
     this._city = value;
   }

   get street(): string {
     return this._street;
   }

   set street(value: string) {
     this._street = value;
   }

   get houseNumber(): string {
     return this._houseNumber;
   }

   set houseNumber(value: string) {
     this._houseNumber = value;
   }

   get apartment(): string {
     return this._apartment;
   }

   set apartment(value: string) {
     this._apartment = value;
   }

   get floor(): number {
     return this._floor;
   }

   set floor(value: number) {
     this._floor = value;
   }

   get deliveryComment(): string {
     return this._deliveryComment;
   }

   set deliveryComment(value: string) {
     this._deliveryComment = value;
   }


   constructor(officialName: string, city: string, street: string, houseNumber: string, apartment: string, floor: number, deliveryComment: string) {
     this._officialName = officialName;
     this._city = city;
     this._street = street;
     this._houseNumber = houseNumber;
     this._apartment = apartment;
     this._floor = floor;
     this._deliveryComment = deliveryComment;
   }


 }
