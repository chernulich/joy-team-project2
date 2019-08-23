import {
  AfterViewChecked,
  Component,
  OnInit,
  Input
} from '@angular/core';
import {SliderService} from "../../service/slider-service/slider.service";

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css']
})
export class SliderComponent implements OnInit {

  constructor(private sliderService: SliderService) { }

  handle1;
  handle2;
  sliderFill;
  slider;
  percent: number;
  currLower: number;
  currUpper: number;
  currHandle1PercentPosition: number;
  currHandle2PercentPosition: number;



  @Input('handleSize') handleSize: number = 10;
  @Input('handleColor') handleColor: string = '';
  @Input('fillColor') fillColor: string = '';
  @Input('lowerValue') lowerValue: number = 100;
  @Input('upperValue') upperValue: number = 500;
  @Input('sliderHeight') sliderHeight: number = 4;
  @Input('handleType') handleType: string = 'square';


  ngOnInit() {
    this.sliderService.initialSliderValues.next({lowerPrice: this.lowerValue, upperPrice: this.upperValue});
    this.sliderService.sliderReset
      .subscribe(() => {
        this.slideInit();
        this.calcHandlePositionPercent();
        this.calcRangeValues();
        this.sliderFill.style.left = (parseInt(getComputedStyle(this.handle1).left) + this.handle1.offsetWidth) + "px";
        this.sliderFill.style.width =  (parseInt(getComputedStyle(this.handle1).left) - this.handle1.offsetWidth) + parseInt(getComputedStyle(this.handle2).left) + "px";
      });
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
    this.currHandle1PercentPosition = (handle1Left / (Math.ceil(this.slider.offsetWidth)));
    this.currHandle2PercentPosition = (handle2Left / (Math.ceil(this.slider.offsetWidth) - this.handle2.offsetWidth));
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

  // calcPercent(){
  //   let sliderFillWidth = parseInt(getComputedStyle(this.sliderFill).width);
  //   let sliderWidth = parseInt(getComputedStyle(this.slider).width);
  //   let handleWidth = this.handle1.offsetWidth;
  //   this.percent = Math.ceil((sliderFillWidth + handleWidth * 2) / sliderWidth * 100);
  // }

  calcRangeValues(){
    let lowerValue = Math.floor(+this.currHandle1PercentPosition.toFixed(2) * +this.upperValue);
    let upperValue = Math.floor(+this.currHandle2PercentPosition.toFixed(2) * +this.upperValue);
    this.currLower =  lowerValue;
    this.currUpper =  upperValue;
    this.sliderService.sliderValueChanges.next({
      lowerPrice: lowerValue,
      upperPrice: upperValue
    })
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
    // this.calcHandlePositionPercent();
    //console.log("Handle 1: " + this.currHandle1PercentPosition, "Handle 2: " + this.currHandle2PercentPosition);
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
