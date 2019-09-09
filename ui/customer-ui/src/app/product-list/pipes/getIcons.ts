import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'getIcons',
  pure: true
})
export class GetIconsPipe implements PipeTransform{

  transform(value: any, index: number): any {
    if(index + 1 <= value){
      return "../../../assets/images/bean_black.png";
    }
    else{
      return  "../../../assets/images/bean_black_light.png"
    }
  }
}
