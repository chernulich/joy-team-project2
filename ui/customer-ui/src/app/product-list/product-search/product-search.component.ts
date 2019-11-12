import {Component, OnDestroy, OnInit, QueryList, ViewChildren} from '@angular/core';
import {SliderService, SliderValues} from "./service/slider-service/slider.service";
import {ProductsDataStorageService} from "../../service/data-storage/products-data-storage.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Product} from "../../model/product.model";
import {ProductListRequest} from "../model/product-list-request";
import {CharacteristicRangeComponent} from "./characteristic-range/characteristic-range.component";
import {CharacteristicsRangeService} from "./service/charachteristics-range-service/characteristics-range.service";
import {ProductResultService} from "../product-result/services/product-result.service";
import {Characteristics} from "../model/characteristics";

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
export class ProductSearchComponent implements OnInit, OnDestroy {

  @ViewChildren(CharacteristicRangeComponent) characteristicRangeComponents: QueryList<CharacteristicRangeComponent>;

  constructor(private sliderService: SliderService,
              private productDataStorage: ProductsDataStorageService,
              private characteristicsRangeService: CharacteristicsRangeService,
              private productResultService: ProductResultService,
              private fb: FormBuilder) { }

  productSearchForm: FormGroup;
  priceRange: SliderValues;
  products: Product [] = [];
  coffeeCharacteristicsMarks = [1,2,3,4,5];
  characteristics: Array<string> = ['bitter','sour','strong'];
  booleanCharacteristics: Array<string> = ['decaf','ground'];

  private tempRequestBody: ProductListRequest = new ProductListRequest();
  private requestBody: {} = new ProductListRequest();

  ngOnInit() {


    this.productSearchForm = this.fb.group({
      productName: this.fb.control('',[]),
      ground: this.fb.control('', []),
      decaf: this.fb.control('',[]),
      priceRange: this.fb.control({minPrice: 10, maxPrice: 100}, []),
      bitter: this.fb.control({bitterFrom: 1, bitterTo: 5}, []),
      sour: this.fb.control({sourFrom: 1, sourTo: 5}, []),
      strong: this.fb.control({strongFrom: 1, strongTo: 5}, [])
    });
  }

  transformRequestObject(request): ProductListRequest{
    const result = {};
    const keys = Object.keys(request);
    let midResult;
    keys.forEach((key) => {

      if(request[key] instanceof Object && !(request[key] instanceof Array)){
        midResult = this.transformRequestObject(request[key]);
      }

      if(!!midResult){
        result[key.slice(1)] = midResult;
      }
      else{
        result[key.slice(1)] = request[key];
      }
    });
    return result as ProductListRequest;
  }

  createRequestObject(){

    let requestObject: ProductListRequest = new ProductListRequest(1, 6, "",
      this.productSearchForm.value.priceRange['minPrice'],
      this.productSearchForm.value.priceRange['maxPrice'],
      "PRICE",
      new Characteristics(
        this.productSearchForm.value.bitter.from,
        this.productSearchForm.value.bitter.to,
        this.productSearchForm.value.sour.from,
        this.productSearchForm.value.sour.to,
        this.productSearchForm.value.strong.from,
        this.productSearchForm.value.strong.to,
        this.productSearchForm.value.decaf === 'yes',
        this.productSearchForm.value.ground === 'yes',
        "")
    );

    return  requestObject = this.transformRequestObject(requestObject);
  }


  onFormSubmit() {
    console.log(this.productSearchForm);
    this.productDataStorage.setCurrentPage(1);
    this.productResultService.filterSearch = true;

    this.requestBody = this.createRequestObject();

    console.log(this.requestBody);
    // this.tempRequestBody.characteristics.decaf = this.productSearchForm.value.decaf === 'yes';
    // this.tempRequestBody.characteristics.ground = this.productSearchForm.value.ground === 'yes';
    // this.tempRequestBody.search = this.productSearchForm.value.productName;
    // this.tempRequestBody.priceMax = this.productSearchForm.value.priceRange['maxPrice'];
    // this.tempRequestBody.priceMin = this.productSearchForm.value.priceRange['minPrice'];
    // this.requestBody = this.createRequestObject(this.tempRequestBody);
    this.productResultService.requestBody.next(this.requestBody);
    this.productResultService.httpGetFilteredProducts(this.requestBody);
  }

  onReset(){
    this.sliderService.sliderReset.next();
    //this.productResultService.requestBody.next({});
    if(!this.productResultService.requestBody.getValue()['characteristics']) {return;}
    this.productSearchForm.setValue({
      productName: "",
      decaf: "",
      ground: "",
      bitter: {from: -1, to: -1},
      sour: {from: -1, to: -1},
      strong: {from: -1, to: -1},
      priceRange: {priceMax: 100, priceMin: 10}
    });
    this.requestBody = {"page": 1, "results": 6};
    this.productDataStorage.setCurrentPage(1);
    this.productResultService.requestBody.next(this.requestBody);
    this.productResultService.httpGetFilteredProducts(this.requestBody);
    // this.tempRequestBody = new ProductListRequest();
    // this.characteristicRangeComponents.forEach((component: CharacteristicRangeComponent) => {
    //   component.reset();
    // })
  }

  firstLetterUpper(value: string){
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

  ngOnDestroy(): void {
    this.productResultService.requestBody.next({});
  }
}
