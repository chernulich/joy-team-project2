import {Injectable} from "@angular/core";
import {ProductListModule} from "../../../product-list.module";
import {BehaviorSubject} from "rxjs";


interface CharacteristicsState {
  from: number;
  to: number;
}

@Injectable({providedIn: ProductListModule})
export class CharacteristicsRangeService {
  constructor(){

  }

  bitterState: BehaviorSubject<CharacteristicsState> =
    new BehaviorSubject<CharacteristicsState>({from: 1,to:5});

  sourState: BehaviorSubject<CharacteristicsState> =
    new BehaviorSubject<CharacteristicsState>({from: 1,to:5});

  strongState: BehaviorSubject<CharacteristicsState> =
    new BehaviorSubject<CharacteristicsState>({from: 1,to:5});

  resetState(stateName){
    this[stateName].next({from: 1,to:5});
  }
}
