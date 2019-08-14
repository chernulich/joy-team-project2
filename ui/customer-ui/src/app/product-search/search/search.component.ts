import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit  {

  constructor() { }

  productSearchForm: FormGroup;

  ngOnInit() {
    this.productSearchForm = new FormGroup({
      search: new FormControl('',[]),
      bitter: new FormControl('',[]),
      sour: new FormControl('',[]),
      strong: new FormControl('',[]),
      instant: new FormControl('',[]),
      ground: new FormControl('',[]),
      decoffein: new FormControl('',[]),
    })
  }

  onFormSubmit(){
    console.log(this.productSearchForm);
  }
}
