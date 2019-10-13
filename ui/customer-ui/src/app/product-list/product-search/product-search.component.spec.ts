import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductSearchComponent } from './product-search.component';

// @ts-ignore
describe('ProductSearchComponent', () => {
  let component: ProductSearchComponent;
  let fixture: ComponentFixture<ProductSearchComponent>;

  // @ts-ignore
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductSearchComponent ]
    })
    .compileComponents();
  }));

  // @ts-ignore
  beforeEach(() => {
    fixture = TestBed.createComponent(ProductSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // @ts-ignore
  it('should create', () => {
    // @ts-ignore
    expect(component).toBeTruthy();
  });
});
