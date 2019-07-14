import {contactInfo} from './contactInfo';

export class CustomerInfo {
  private _entityName: string;
  private _email: string;
  private _phoneNumber: number;
  private _contactInfo: contactInfo;

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

  get phoneNumber(): number {
    return this._phoneNumber;
  }

  set phoneNumber(value: number) {
    this._phoneNumber = value;
  }

  get contactInfo(): contactInfo {
    return this._contactInfo;
  }

  set contactInfo(value: contactInfo) {
    this._contactInfo = value;
  }
  constructor(entityName: string, email: string, phoneNumber: number, contactInfo: contactInfo) {
    this._entityName = entityName;
    this._email = email;
    this._phoneNumber = phoneNumber;
    this._contactInfo = contactInfo;
  }





}
