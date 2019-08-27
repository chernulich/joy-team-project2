import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductResultComponent } from './product-result.component';

describe('ProductResultComponent', () => {
  let component: ProductResultComponent;
  let fixture: ComponentFixture<ProductResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
