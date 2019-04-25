import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { HomeComponent } from './home';
import { ProfileComponent } from './profile';
import { AuthGuard } from './shared/guards/auth.guard';
import { UsersComponent } from './users';
import { TimelineComponent } from './timeline';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { canActivate: [AuthGuard], path: 'home', component: HomeComponent },
  { canActivate: [AuthGuard], path: 'timeline', component: TimelineComponent },
  { canActivate: [AuthGuard], path: 'users', component: UsersComponent },
  { canActivate: [AuthGuard], path: 'profile', component: ProfileComponent },
  { canActivate: [AuthGuard], path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
