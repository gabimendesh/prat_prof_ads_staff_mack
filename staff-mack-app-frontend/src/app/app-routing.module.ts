import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PresenceControlPageComponent } from './pages/presence-control-page/presence-control-page.component';
import { ReportPageComponent } from './pages/report-page/report-page.component';
import { LoginComponent } from './pages/login/login.component';
import { RecuperarAcessoComponent } from './pages/recuperar-acesso/recuperar-acesso.component';
import { AuthGuard } from './core/guard/auth/auth.guard';
import { LoginGuard } from './core/guard/login/login.guard';

const routes: Routes = [
  {
    path: 'controle-presenca',
    pathMatch: 'full',
    component: PresenceControlPageComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'relatorio',
    pathMatch: 'full',
    component: ReportPageComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'ajuda',
    pathMatch: 'full',
    component: PresenceControlPageComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    pathMatch: 'full',
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'recuperar-acesso',
    pathMatch: 'full',
    component: RecuperarAcessoComponent,
    canActivate: [LoginGuard]
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'controle-presenca'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
