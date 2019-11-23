import {
  Component,
  OnInit,
  Input, forwardRef, Provider
} from '@angular/core';
import {SliderService} from "../service/slider-service/slider.service";
import {ControlValueAccessor, NG_VALUE_ACCESSOR} from "@angular/forms";

export interface SliderValue {
  priceMax: number;
  priceMin: number;
}

const controlValueAccessorProvider: Provider = {
  provide: NG_VALUE_ACCESSOR,
  useExisting:  forwardRef(() => SliderComponent),
  multi: true
};

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css'],
  providers: [controlValueAccessorProvider]
})
export class SliderComponent implements OnInit,ControlValueAccessor {


  constructor(private sliderService: SliderService) { }

  onChanges: Function = function() {}; // controlValueAccessor changes function
  onTouched: Function = function() {}; // controlValueAccessor on touched function

  handle1;
  handle2;
  sliderFill;
  slider;
  percent: number;
  currLower: number;
  currUpper: number;
  currHandle1PercentPosition: number;
  currHandle2PercentPosition: number;

  value: SliderValue = {
    priceMax: 100,
    priceMin: 10
  };



  @Input('handleSize') handleSize: number = 10;
  @Input('handleColor') handleColor: string = '';
  @Input('fillColor') fillColor: string = '';
  @Input('lowerValue') lowerValue: number = 100;
  @Input('upperValue') upperValue: number = 500;
  @Input('sliderHeight') sliderHeight: number = 4;
  @Input('handleType') handleType: string = 'square';

  writeValue(obj: SliderValue): void {
    this.value = obj;
    this.slideInit();
    this.calcHandlePositionPercent();
    this.calcRangeValues();
    this.sliderFill.style.left = (parseInt(getComputedStyle(this.handle1).left) + this.handle1.offsetWidth) + "px";
    this.sliderFill.style.width =  (parseInt(getComputedStyle(this.handle1).left) - this.handle1.offsetWidth) + parseInt(getComputedStyle(this.handle2).left) + "px";
  }

  registerOnChange(fn: any): void {
    this.onChanges = fn;
  }
  registerOnTouched(fn: (value: SliderValue) => void): void {
    this.onTouched = fn;
  }
  setDisabledState?(isDisabled: boolean): void {

  }

  ngOnInit() {
    this.currUpper = this.upperValue;
    this.currLower = this.lowerValue;

    this.registerElems();
    this.slideInit();
    this.calcHandlePositionPercent();
    this.calcRangeValues();
    window.addEventListener('resize',this.onResize.bind(this));
  }

  registerElems(){
    this.handle1 = document.querySelector('.handle-1');
    this.handle2 = document.querySelector('.handle-2');
    this.sliderFill = document.querySelector('.slider-fill');
    this.slider = document.querySelector('.slider');
  }

  calcHandlePositionPercent(){

    let handle1Left = parseInt(getComputedStyle(this.handle1).left);
    let handle2Left = parseInt(getComputedStyle(this.handle2).left);
    this.currHandle1PercentPosition = handle1Left / (Math.floor(this.slider.offsetWidth) - this.handle1.offsetWidth);
    this.currHandle2PercentPosition = handle2Left / (Math.floor(this.slider.offsetWidth) - this.handle2.offsetWidth);
  }

  handleMouseDown(event){
    let handle = event.target;
    let self = this;
    event.preventDefault();
    let shiftX = event.clientX - parseInt(getComputedStyle(handle).left);
    document.onmousemove = function (ev) {
      ev.preventDefault();
      if(!(handle.classList.contains('handle-1') ||
        handle.classList.contains('handle-2'))){
        return;
      }
      self.posititonAt.call(self,ev.clientX,shiftX,handle);
    }
  }

  calcRangeValues(){

    let lowerValue = this.lowerValue +
      Math.floor(+this.currHandle1PercentPosition.toFixed(2) * (+this.upperValue - +this.lowerValue));

    let upperValue = this.lowerValue +
      Math.floor(+this.currHandle2PercentPosition.toFixed(2) * (+this.upperValue - +this.lowerValue));

    this.currLower =  lowerValue;
    this.currUpper =  upperValue;

    this.onChanges({maxPrice: upperValue, minPrice: lowerValue}); // controlValueAccessor change emit

  }

  posititonAt(clientX,shiftX,element){
    let positionVal = clientX - shiftX;
    if(element.classList.contains('handle-1')){
      if(positionVal >= 0 &&
        (positionVal + element.offsetWidth) <=
        parseInt(getComputedStyle(this.handle2).left)){
        element.style.left = positionVal + 'px';
        (<HTMLElement>this.sliderFill).style.width = parseInt(getComputedStyle(this.handle2).left) - element.offsetWidth - positionVal + "px";
        (<HTMLElement>this.sliderFill).style.left = parseInt(getComputedStyle(element).left) +  element.offsetWidth + "px";
      }
    }
    else if(element.classList.contains('handle-2')){
      if(positionVal <= this.slider.offsetWidth - element.offsetWidth &&
        positionVal >=
        parseInt(getComputedStyle(this.handle1).left) + element.offsetWidth){
        element.style.left = positionVal + 'px';
        (<HTMLElement>this.sliderFill).style.width =  positionVal - parseInt(getComputedStyle(this.handle1).left) - element.offsetWidth + "px";
        (<HTMLElement>this.sliderFill).style.left = parseInt(getComputedStyle(this.handle1).left) + element.offsetWidth + 'px';
      }
    }
    this.calcHandlePositionPercent();
    this.calcRangeValues();
  }

  handleMouseUp(){
    document.onmousemove =  null;
  }

  onResize(){

    let sliderWidth = Math.ceil(this.slider.offsetWidth);
    this.handle1.style.left =  Math.ceil(sliderWidth * this.currHandle1PercentPosition) + 'px';
    this.handle2.style.left = Math.ceil(sliderWidth * this.currHandle2PercentPosition - this.handle2.offsetWidth) + 'px';
    let handle1Left = parseInt(getComputedStyle(this.handle1).left);
    let handle2Left = parseInt(getComputedStyle(this.handle2).left);
    let resizedWidth =  handle2Left - (handle1Left + this.handle1.offsetWidth);
    (<HTMLElement>this.sliderFill).style.width = resizedWidth + "px";
    (<HTMLElement>this.sliderFill).style.left = parseInt(getComputedStyle(this.handle1).left) +  this.handle1.offsetWidth + "px";

  }

  slideInit(){
    let handle1 = document.querySelector('.handle-1');
    let handle2 = document.querySelector('.handle-2');
    (<HTMLElement>handle1).onmousedown = this.handleMouseDown.bind(this);
    (<HTMLElement>handle2).onmousedown = this.handleMouseDown.bind(this);
    document.onmouseup = this.handleMouseUp.bind(this);
     this.handle1.style.height = this.handleSize + "px";
      this.handle1.style.width = this.handleSize + "px";
      this.handle2.style.height = this.handleSize + "px";
      this.handle2.style.width = this.handleSize + "px";
      this.slider.style.height = this.sliderHeight + "px";
      this.sliderFill.style.background = this.fillColor;
      this.handle1.style.background = this.handleColor;
      this.handle2.style.background = this.handleColor;
      this.handle1.style.left = 0 + "px";
      this.handle2.style.left = Math.ceil(this.slider.offsetWidth) - this.handle2.offsetWidth + "px";
      this.sliderFill.style.width = this.sliderFill.offsetWidth - this.handle1.offsetWidth * 2 + "px";
      this.sliderFill.style.left = this.handle1.offsetWidth + "px";
      if(this.handleType === 'square'){
        this.handle2.style.borderRadius = '';
        this.handle1.style.borderRadius = '';
      }
      else if(this.handleType === 'circle'){
        this.handle2.style.borderRadius = '50%';
        this.handle1.style.borderRadius = '50%';
      }
  }
}
