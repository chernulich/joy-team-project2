import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListResultComponent } from './product-list-result.component';

describe('ProductListResultComponent', () => {
  let component: ProductListResultComponent;
  let fixture: ComponentFixture<ProductListResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductListResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
