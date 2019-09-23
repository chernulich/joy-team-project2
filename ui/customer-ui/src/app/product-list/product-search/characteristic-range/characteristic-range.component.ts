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

 reset(){
    const children = Array.from(this.beansContainer.nativeElement.children);
    children.forEach((image: HTMLImageElement) => {
      image.src = this.lightBean;
    });
  }

  rangeSelect(start,end){
    const children = Array
      .from(this.beansContainer.nativeElement.children);
    children.slice(start,end + 1).forEach((e,i) => {
        this.renderer.setAttribute(e,'src',this.blackBean);
    });
  }

  onBeanHover(element: HTMLImageElement,index){
    if(!this.isSelected){
      this.reset();
      if(this.rangeStart > index){
        this.rangeStart = -1;
        this.rangeEnd = -1;
      }
      if(this.rangeStart < 0){
        this.rangeStart = index;
      }
      if(this.rangeStart >= 0){
        this.rangeEnd = index;
      }
      this.rangeSelect(this.rangeStart,this.rangeEnd);
    }
  }

  onWidgetLeave(){
    if(!this.isSelected){
      this.reset();
      this.rangeStart = -1;
      this.rangeEnd = -1;
    }
  }

  onBeanClick(element: HTMLImageElement,index) {
    // console.log("selectedIndex: " + selectedIndex, "endIndex: " + this.rangeEnd);
    if (index === this.rangeEnd && this.isSelected) {
      this.isSelected = false;
      this.onWidgetLeave();
      this.characteristicsRangeService[this.characteristicName + 'State']
        .next({from: 1, to: 5});
      return;
    }
    if (!this.isSelected) {
      this.isSelected = true;
      this.characteristicsRangeService[this.characteristicName + 'State']
        .next({from: this.rangeStart + 1, to: this.rangeEnd + 1});

    }
  }
}
