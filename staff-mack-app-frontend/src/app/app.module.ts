import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationMenuComponent } from './core/navigation-menu/navigation-menu.component';
import { ReportPageComponent } from './pages/report-page/report-page.component';
import { PresenceControlPageComponent } from './pages/presence-control-page/presence-control-page.component';
import { FilterComponent } from './shared/components/filter/filter.component';
import { SearchComponent } from './shared/components/search/search.component';
import { SharedModule } from './shared/shared.module';
import { LoginComponent } from './pages/login/login.component';
import { AuthService } from './auth.service';
import { RecuperarAcessoComponent } from './pages/recuperar-acesso/recuperar-acesso.component';

@NgModule({
  declarations: [
    AppComponent,
    PresenceControlPageComponent,
    ReportPageComponent,
    NavigationMenuComponent,
    LoginComponent,
    RecuperarAcessoComponent,
  ],
  imports: [
    FormsModule,
    CommonModule,
    BrowserModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    SharedModule
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
