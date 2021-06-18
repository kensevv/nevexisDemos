import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCaseSecondInstanceComponent } from './edit-case-second-instance.component';

describe('EditCaseSecondInstanceComponent', () => {
  let component: EditCaseSecondInstanceComponent;
  let fixture: ComponentFixture<EditCaseSecondInstanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCaseSecondInstanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCaseSecondInstanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
