import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

export interface SliderValues {
  lowerPrice: number;
  upperPrice: number;
}

@Injectable({
  providedIn: 'root'
})
export class SliderService {

  sliderValueChanges: Subject<SliderValues> = new Subject();
  initialSliderValues: Subject<SliderValues> = new Subject();
  sliderReset: Subject<any> = new Subject();

  constructor() { }
}
