import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchplayerComponent } from './searchplayer.component';

describe('SearchplayerComponent', () => {
  let component: SearchplayerComponent;
  let fixture: ComponentFixture<SearchplayerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchplayerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchplayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
