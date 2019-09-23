import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {SliderService, SliderValues} from "./service/slider-service/slider.service";
import {ProductsDataStorageService} from "./service/data-storage/products-data-storage.service";
import {FormControl, FormGroup} from "@angular/forms";
import {Product} from "../../model/product.model";
import {ProductListRequest} from "../model/product-list-request";
import {Characteristics} from "../model/characteristics";
import {CharacteristicRangeComponent} from "./characteristic-range/characteristic-range.component";
import {CharacteristicsRangeService} from "./service/charachteristics-range-service/characteristics-range.service";

interface Filter {
  search?: string,
  bitter?: Array <number>,
  sour?: Array<number>,
  strong?: Array<number>,
  instant?: boolean,
  ground?: boolean,
  decaf?: boolean,
  price?: SliderValues
}

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit, AfterViewInit {

  @ViewChild(CharacteristicRangeComponent,
    {static: false}) characteristicRangeComponent: CharacteristicRangeComponent;

  constructor(private sliderService: SliderService,
              private productDataStorage: ProductsDataStorageService,
              private characteristicsRangeService: CharacteristicsRangeService) { }

  productSearchForm: FormGroup;
  priceRange: SliderValues;
  products: Product [] = [];
  productsToShow: Product [] = [];
  coffeeCharacteristicsMarks = [1,2,3,4,5];
  initialSliderValues: SliderValues;
  characteristics: Array<string> = ['bitter','sour','strong'];
  booleanCharacteristics: Array<string> = ['decaf','ground'];

  private tempRequestBody: ProductListRequest = new ProductListRequest();
  private requestBody: {} = new ProductListRequest();
  private requestCharacteristics: Characteristics = new Characteristics();

  ngAfterViewInit(): void {
    console.log("range: ", this.characteristicRangeComponent);
  }

  ngOnInit() {
    this.productSearchForm = new FormGroup({
      productName: new FormControl('',[]),
      ground: new FormControl('',[]),
      decaf: new FormControl('',[]),
    });

    this.sliderService.sliderValueChanges.subscribe((priceRange) =>{
      this.priceRange = priceRange;
      this.tempRequestBody.priceMin = this.priceRange.lowerPrice;
      this.tempRequestBody.priceMax = this.priceRange.upperPrice;
    });

    this.sliderService.initialSliderValues
      .subscribe((initialValues) => {
        this.initialSliderValues = initialValues;
        this.tempRequestBody.priceMin = initialValues.lowerPrice;
        this.tempRequestBody.priceMax = initialValues.upperPrice;
      });

    this.characteristicsRangeService.bitterState
      .subscribe((bitterState) => {
        console.log('Bitter: ', bitterState);
        this.tempRequestBody.characteristics.bitterFrom = bitterState.from;
        this.tempRequestBody.characteristics.bitterTo = bitterState.to;
      });

    this.characteristicsRangeService.sourState
      .subscribe((sourState) => {
        console.log('Sour: ', sourState);
        this.tempRequestBody.characteristics.sourFrom = sourState.from;
        this.tempRequestBody.characteristics.sourTo = sourState.to;
      });

    this.characteristicsRangeService.strongState
      .subscribe((strongState) => {
        console.log('strongState: ', strongState);
        this.tempRequestBody.characteristics.strongFrom = strongState.from;
        this.tempRequestBody.characteristics.strongTo = strongState.to;
      });
  }

  createRequestObject(request): {}{
    const result = {};
    const keys = Object.keys(request);
    let x;
    keys.forEach((key) => {

      if(request[key] instanceof Object){
        // debugger;
        x = this.createRequestObject(request[key]);
      }

      if(!!x){
        result[key.slice(1)] = x;
      }
      else{
        result[key.slice(1)] = request[key];
      }
    });
    return result;
  }

  onFormSubmit() {
    console.log("Form: ", this.productSearchForm.value, "RequestBody: " , this.requestBody);
    this.tempRequestBody.characteristics.decaf = this.productSearchForm.value.decaf === 'yes';
    this.tempRequestBody.characteristics.ground = this.productSearchForm.value.ground === 'yes';
    this.tempRequestBody.search = this.productSearchForm.value.productName;
    this.requestBody = this.createRequestObject(this.tempRequestBody);
    //console.log(this.requestBody);
    this.productDataStorage.httpGetFilteredProducts(this.requestBody);
    // debugger;
  }

  onReset(){
    this.productsToShow = this.products.slice(0);
    this.sliderService.sliderReset.next();
  }

  firstLetterUpper(value: string){
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

}
