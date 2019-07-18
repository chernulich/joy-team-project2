import {ContactInfo} from "./contactInfo";

export class CustomerInfo {

  private _entityName: string;
  private _email: string;
  private _phoneNumber: string
  private _contactInfo: ContactInfo;

  get entityName(): string {
    return this._entityName;
  }

  set entityName(value: string) {
    this._entityName = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get phoneNumber(): string {
    return this._phoneNumber;
  }

  set phoneNumber(value: string) {
    this._phoneNumber = value;
  }

  get contactInfo(): ContactInfo {
    return this._contactInfo;
  }

  set contactInfo(value: ContactInfo) {
    this._contactInfo = value;
  }
  constructor(entityName: string, email: string, phoneNumber: string, contactInfo: ContactInfo) {
    this._entityName = entityName;
    this._email = email;
    this._phoneNumber = phoneNumber;
    this._contactInfo = contactInfo;
  }





}
