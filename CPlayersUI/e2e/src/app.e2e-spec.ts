import { AppPage } from './app.po';
import { browser, logging, by, element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title of Application ', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayersUI');
  });

  it('should be able to  login', () => {
    browser.element(by.id('username')).sendKeys('santhosh');
    browser.element(by.id('password')).sendKeys('password');
    browser.element(by.css('.login-click')).click();

  });
  

  it('should get default list ', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-player')).click();
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/findplayer');
    browser.driver.sleep(500);
  });

  it('should open player details ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.detailbutton')).click();
    browser.driver.sleep(1000);
  });

  it('should close player details ', () => {
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.close1')).click();
    browser.driver.sleep(1000);
  });

  it('should add to Favorite List ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });

  it('should display data from Favorite List ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-favorite')).click();
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/favorite');
  });

  it('should display recommened list ', () => {
    browser.element(by.css('.mat-button-recommended')).click();
    browser.driver.sleep(2000);
    browser.driver.manage().window().maximize();

  });

  it('should be able to logout ', () => {
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-logout')).click();
    browser.driver.sleep(1000);

  });

  afterEach(async () => {
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
