import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Example} from "../../model/example";

@Injectable({
  providedIn: 'root'
})
export class ExampleHttpService {

  constructor(private httpClient: HttpClient) {
  }

  getExamples() {
    return this.httpClient.get<Example[]>("api/examples");
  }

  getExamplesById(id: number) {
    return this.httpClient.get<Example[]>("api/examples/" + id);
  }

  saveExample(name: string) {
    return this.httpClient.post<Example>('/api/examples', {name: name});
  }
}
