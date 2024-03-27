import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PresenceControlPageComponent } from './pages/presence-control-page/presence-control-page.component';

const routes: Routes = [
  {
    path: 'controle-presenca',
    pathMatch: 'full',
    component: PresenceControlPageComponent
  },
  {
    path: '',
    redirectTo: 'controle-presenca',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
