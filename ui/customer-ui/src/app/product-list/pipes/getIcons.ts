import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'getIcons',
  pure: true
})
export class GetIconsPipe implements PipeTransform {
  transform(value: any, index: number, characteristicName: string): any {

    let characteristicValue = value.productParametersResponse || value.characteristicResponse;

    if (index + 1 <= characteristicValue[characteristicName]) {
      return "../../../assets/images/bean_black.png";
    }
    else {
      return  "../../../assets/images/bean_black_light.png";
    }
  }
}
