import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationMenuComponent } from './core/navigation-menu/navigation-menu.component';
import { ReportPageComponent } from './pages/report-page/report-page.component';
import { PresenceControlPageComponent } from './pages/presence-control-page/presence-control-page.component';

@NgModule({
  declarations: [
    AppComponent,
    PresenceControlPageComponent,
    ReportPageComponent,
    NavigationMenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
