import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {SliderService} from "../service/slider-service/slider.service";
import {SliderValues} from "../service/slider-service/slider.service";
import {Product} from "../../../model/product.model";
import {ProductsDataStorageService} from "../service/data-storage/products-data-storage.service";

interface Filter {
  search?: string,
  bitter?: Array <number>,
  sour?: Array<number>,
  strong?: Array<number>,
  instant?: boolean,
  ground?: boolean,
  decoffein?: boolean,
  price?: SliderValues
}

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit  {

  filter: Filter = {};

  constructor(private sliderService: SliderService,
              private productDataStorage: ProductsDataStorageService) { }

  productSearchForm: FormGroup;
  priceRange: SliderValues;
  products: Product [] = [];
  productsToShow: Product [] = [];
  coffeeCharacteristicsMarks = [1,2,3,4,5];
  initialSliderValues: SliderValues;

  ngOnInit() {
    this.productSearchForm = new FormGroup({
      productName: new FormControl('',[]),
      bitter: new FormGroup({
        bitter1: new FormControl(false,[]),
        bitter2: new FormControl(false,[]),
        bitter3: new FormControl(false,[]),
        bitter4: new FormControl(false,[]),
        bitter5: new FormControl(false,[])
      }),
      sour: new FormGroup({
        sour1: new FormControl(false,[]),
        sour2: new FormControl(false,[]),
        sour3: new FormControl(false,[]),
        sour4: new FormControl(false,[]),
        sour5: new FormControl(false,[])
      }),
      strong: new FormGroup({
        strong1: new FormControl(false,[]),
        strong2: new FormControl(false,[]),
        strong3: new FormControl(false,[]),
        strong4: new FormControl(false,[]),
        strong5: new FormControl(false,[])
      }),
      instant: new FormControl('',[]),
      ground: new FormControl('',[]),
      decoffein: new FormControl('',[]),
    });
    this.sliderService.sliderValueChanges.subscribe((priceRange) =>{
      this.priceRange = priceRange;
      this.filter.price = priceRange;
    });
    this.sliderService.initialSliderValues
      .subscribe((initialValues) => {
          this.initialSliderValues = initialValues;
      });
    this.productDataStorage.productStore
      .subscribe((productsState: Product[]) => {
        this.products = productsState;
        this.productsToShow = productsState;
        console.log("Our Products List: " , this.products);
      })
  }

  filterProducts(){
    let filtered: Product;
    let result: Product[] = this.products.filter((product) => {
      for(let key of Object.keys(this.filter)){
        if(typeof this.filter[key] === "boolean" ||
           typeof this.filter[key] === "number" ||
           typeof this.filter[key] === "string"){
              if(product[key] === this.filter[key]){
                filtered = product;
              }
              else{
                filtered = null;
                break;
              }
        }
        else if(this.filter[key] instanceof Array){
          let values: Array<number>  = this.filter[key];

          if(values.includes(+product[key])){
            filtered = product;
          }
          else {
            filtered = null;
            break;
          }
        }
        else if(this.filter[key]['lowerPrice'] &&
                this.filter[key]['upperPrice']){
            if(+product[key] >= +this.filter[key]['lowerPrice'] &&
               +product[key] <= +this.filter[key]['upperPrice']){
              filtered = product;
            }
            else{
              filtered = null;
              break;
            }
        }
      }
      return filtered !== null;
    });
    return result;
  }

  getArrayOfCheckboxesMarks(arr){
    let result = [];
    let counter = 0;

    for(let element of arr){
      counter++;
      if(element){
        result.push(counter);
      }
    }
    return result;
  }
  onFormSubmit() {
    let formValue = this.productSearchForm.value;
    console.log("eto nasha forma " ,formValue);
    for (let key of Object.keys(formValue)) {
      if (!!formValue[key]) {
        if (key === "bitter" || key === "sour" || key === "strong") {
          let values = this.getArrayOfCheckboxesMarks(Object.values(formValue[key]));
          if(!!values.length){
            this.filter[key] = values;
          }
          else{
            delete this.filter[key];
          }
        }
        else if(key === "instant" || key === "ground" || key === "decoffein"){
          this.filter[key] = formValue[key] === "yes";
        }
        else {
          this.filter[key] = formValue[key];
        }
      }
    }

    console.log(this.filter);
    this.productsToShow = this.filterProducts();
    console.log(this.productsToShow);
  }

  onReset(){
    this.filter = {};
    this.productsToShow = this.products.slice(0);
    this.sliderService.sliderReset.next();
  }
}
