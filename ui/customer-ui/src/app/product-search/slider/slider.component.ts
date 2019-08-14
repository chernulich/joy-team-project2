import {
  AfterViewChecked,
  Component,
  OnInit,
  Input,
} from '@angular/core';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css']
})
export class SliderComponent implements OnInit,AfterViewChecked {

  constructor() { }

  handle1;
  handle2;
  sliderFill;
  slider;
  percent: number;
  isInitialized = false;
  currLower: number;
  currUpper: number;


  @Input('handleSize') handleSize: number = 10;
  @Input('handleColor') handleColor: string = '';
  @Input('fillColor') fillColor: string = '';
  @Input('lowerValue') lowerValue: number = 100;
  @Input('upperValue') upperValue: number = 500;
  @Input('sliderHeight') sliderHeight: number = 4;
  @Input('handleType') handleType: string = 'square';

  ngOnInit() {
    this.registerElems();
    this.slideInit();
    this.calcRangeValues();
  }

  registerElems(){
    this.handle1 = document.querySelector('.handle-1');
    this.handle2 = document.querySelector('.handle-2');
    this.sliderFill = document.querySelector('.slider-fill');
    this.slider = document.querySelector('.slider');
  }

  ngAfterViewChecked(){

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

  // calPercent(){
  //   let sliderFillWidth = parseInt(getComputedStyle(this.sliderFill).width);
  //   let sliderWidth = parseInt(getComputedStyle(this.slider).width);
  //   let handleWidth = this.handle1.offsetWidth;
  //   this.percent = Math.ceil((sliderFillWidth + handleWidth * 2) / sliderWidth * 100);
  // }

  calcRangeValues(){
    let handle1Left = this.handle1.offsetLeft;
    this.currLower =  Math.floor(this.lowerValue + (this.upperValue - this.lowerValue) *  (handle1Left / (this.slider.offsetWidth - this.handle1.offsetWidth * 2)));
    let handle2Left = this.handle2.offsetLeft - this.handle2.offsetWidth;
    this.currUpper =  Math.floor(this.lowerValue + (this.upperValue - this.lowerValue) *  (handle2Left / (this.slider.offsetWidth - this.handle2.offsetWidth * 2)));
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
    this.calcRangeValues();
  }

  handleMouseUp(){
    document.onmousemove =  null;
  }

  slideInit(){
    let handle1 = document.querySelector('.handle-1');
    let handle2 = document.querySelector('.handle-2');
    (<HTMLElement>handle1).onmousedown = this.handleMouseDown.bind(this);
    (<HTMLElement>handle2).onmousedown = this.handleMouseDown.bind(this);
    document.onmouseup = this.handleMouseUp.bind(this);

    if(!this.isInitialized){
      this.sliderFill.style.width = this.sliderFill.offsetWidth - this.handle1.offsetWidth * 2 + "px";
      this.sliderFill.style.left = this.handle1.offsetWidth + "px";
      this.handle1.style.height = this.handleSize + "px";
      this.handle1.style.width = this.handleSize + "px";
      this.handle2.style.height = this.handleSize + "px";
      this.handle2.style.width = this.handleSize + "px";
      this.slider.style.height = this.sliderHeight + "px";
      this.sliderFill.style.background = this.fillColor;
      this.handle1.style.background = this.handleColor;
      this.handle2.style.background = this.handleColor;
      if(this.handleType === 'square'){
        this.handle2.style.borderRadius = '';
        this.handle1.style.borderRadius = '';
      }
      else if(this.handleType === 'circle'){
        this.handle2.style.borderRadius = '50%';
        this.handle1.style.borderRadius = '50%';
      }
      this.isInitialized = true;
    }
  }


  // onHClick(event,t){
  //   let target = event.target;
  //   console.log(t);
  // }
}
