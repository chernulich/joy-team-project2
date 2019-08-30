import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeaderComponent} from './header.component';


describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  // @ts-ignore
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent]
    })
      .compileComponents();
  }));

  // @ts-ignore
  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    // @ts-ignore
    expect(component).toBeTruthy();
  });
});
