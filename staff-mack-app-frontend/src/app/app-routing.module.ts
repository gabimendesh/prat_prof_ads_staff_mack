import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PresenceControlPageComponent } from './pages/presence-control-page/presence-control-page.component';
import { ReportPageComponent } from './pages/report-page/report-page.component';
import { LoginComponent } from './pages/login/login.component';
import { RecuperarAcessoComponent } from './pages/recuperar-acesso/recuperar-acesso.component';

const routes: Routes = [
  {
    path: 'controle-presenca',
    pathMatch: 'full',
    component: PresenceControlPageComponent,
  },
  {
    path: 'relatorio',
    pathMatch: 'full',
    component: ReportPageComponent,
  },
  {
    path: 'ajuda',
    pathMatch: 'full',
    component: PresenceControlPageComponent,
  },
  {
  path: 'login',
  pathMatch: 'full',
  component: LoginComponent
  },
  {
    path: 'recuperar-acesso',
    pathMatch: 'full',
    component: RecuperarAcessoComponent
}
, {
   path: '',
   redirectTo: 'controle-presenca',
   pathMatch: 'full',
 }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
