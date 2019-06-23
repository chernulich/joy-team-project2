import { Component, OnInit } from '@angular/core';
import {ExampleHttpService} from "../service/http/example-http.service";
import {Example} from "../model/example";

@Component({
  selector: 'app-example',
  templateUrl: './example.component.html',
  styleUrls: ['./example.component.css']
})
export class ExampleComponent implements OnInit {

  public isHeaderShadow: boolean = false;
  public tableRowsShadows: boolean [] = [];

  public examples: Example[] = [];
  public exampleName: string;

  constructor(private exampleHttpService: ExampleHttpService) {
  }

  getExamples() {
    return this.exampleHttpService.getExamples().subscribe(data => this.examples = data);
  }
  saveExample(exampleName: string) {
    return this.exampleHttpService.saveExample(exampleName).subscribe(data => {
      this.examples.push(data);
      this.exampleName = '';
    });
  }

  ngOnInit() {
    this.getExamples();
  }

}
