import {
  Component,
  ElementRef,
  Input,
  OnInit,
  Renderer2,
  ViewChild
} from '@angular/core';
import {CharacteristicsRangeService} from "../service/charachteristics-range-service/characteristics-range.service";

@Component({
  selector: 'app-characteristic-range',
  templateUrl: './characteristic-range.component.html',
  styleUrls: ['./characteristic-range.component.css']
})
export class CharacteristicRangeComponent implements OnInit {

  constructor(private renderer: Renderer2,
              private characteristicsRangeService: CharacteristicsRangeService) { }

  @Input('coffeeCharacteristicsMarks') coffeeCharacteristicsMarks: Array<number>;
  @Input('characteristicName') characteristicName;
  @ViewChild('beansContainer',{static: true}) beansContainer: ElementRef;

  isSelected: boolean = false;
  rangeStart: number = -1;
  rangeEnd: number = -1;

  blackBean = '../../../assets/images/bean_black.png';
  lightBean = '../../../assets/images/bean_black_light.png';

  ngOnInit() {

  }

  numberOfCoffeeBeans(){
    return this.coffeeCharacteristicsMarks;
  }

 setToInitial(){
    const children = Array.from(this.beansContainer.nativeElement.children);
    children.forEach((image: HTMLImageElement) => {
      image.src = this.lightBean;
    });
  }

  reset(){
    this.setToInitial();
    this.rangeStart = -1;
    this.rangeEnd = -1;
    this.isSelected = false;
  }



  rangeSelect(start,end){ // change images in range to selected (black Bean)
    const children = Array
      .from(this.beansContainer.nativeElement.children);
    children.slice(start,end + 1).forEach((e,i) => {
        this.renderer.setAttribute(e,'src',this.blackBean);
    });
  }

  onBeanHover(element: HTMLImageElement,index){
    // if not selected only start to select beans
    if(!this.isSelected){
      // set to initial state
      this.setToInitial();
      // if moving backwards and start index grater then current bean
      // set start and end indexes to initial
      if(this.rangeStart > index){
        this.rangeStart = -1;
        this.rangeEnd = -1;
      }
      // if currently no start index put current index to range start
      if(this.rangeStart < 0){
        this.rangeStart = index;
      }
      // if currently there is start index put current index to  range end
      if(this.rangeStart >= 0){
        this.rangeEnd = index;
      }

      // call range select to chane images for selected
      this.rangeSelect(this.rangeStart,this.rangeEnd);
    }
  }

  onWidgetLeave(){
    // if leaving beans area and no beans selected reset to initial state
    if(!this.isSelected){
      this.reset();
    }
  }

  onBeanClick(element: HTMLImageElement,index) {

    // if clicked on last bean make deselection
    if (index === this.rangeEnd && this.isSelected) {
      this.isSelected = false;
      this.onWidgetLeave();
      this.characteristicsRangeService[this.characteristicName + 'State']
        .next({from: 1, to: 5});
      return;
    }
    // if previously not selected select current range
    if (!this.isSelected) {
      this.isSelected = true;
      this.characteristicsRangeService[this.characteristicName + 'State']
        .next({from: this.rangeStart + 1, to: this.rangeEnd + 1});
    }
  }
}
