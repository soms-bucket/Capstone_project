import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAutomobileComponent } from './edit-automobile.component';

describe('EditAutomobileComponent', () => {
  let component: EditAutomobileComponent;
  let fixture: ComponentFixture<EditAutomobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditAutomobileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAutomobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
