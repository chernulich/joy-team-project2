import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListHeaderComponent } from './product-list-header.component';

// @ts-ignore
describe('ProductListHeaderComponent', () => {
  let component: ProductListHeaderComponent;
  let fixture: ComponentFixture<ProductListHeaderComponent>;

  // @ts-ignore
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductListHeaderComponent ]
    })
    .compileComponents();
  }));

  // @ts-ignore
  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // @ts-ignore
  it('should create', () => {
    // @ts-ignore
    expect(component).toBeTruthy();
  });
});
