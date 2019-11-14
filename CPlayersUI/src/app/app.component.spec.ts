import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { HeaderComponent } from './cplayer/header/header.component';
import { MatToolbarModule, MatSidenavModule, MatIconModule,  MatListModule, MatFormFieldModule, MatInputModule, MatCardModule} from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { PlayerlistComponent } from './cplayer/playerlist/playerlist.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatToolbarModule,
        MatSidenavModule,
        HttpClientModule,
        MatCardModule
      ],
      declarations: [
        AppComponent,
        HeaderComponent,
        PlayerlistComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'CPlayersUI'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('CPlayersUI');
  });

  /*it('should render title in a h1 tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to CPlayersUI!');
  });*/
});
