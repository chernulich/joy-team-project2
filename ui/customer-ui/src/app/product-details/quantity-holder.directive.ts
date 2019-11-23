import {Directive, Input} from "@angular/core";

@Directive({
  selector: '[quantity]',
  exportAs: 'quantity'
})
export class QuantityHolderDirective {
  constructor(){}
  @Input('quantity') quantity;
}
