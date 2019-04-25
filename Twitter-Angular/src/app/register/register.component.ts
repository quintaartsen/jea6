import { Component, OnInit } from '@angular/core';
import { AuthService, User } from '../shared';
import { Router } from '@angular/router';
import { RestApiService} from '../shared';
import { HttpClient } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { JwtToken } from '../shared/models/jwtToken.model';
import { UserApiService } from '../shared/services/user-api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user = new User();

  constructor(private userApiService: UserApiService, private authService: AuthService) { }

  ngOnInit() {
  }

  public register(): void {
    const token = new JwtToken();
    token.userName = this.user.userName;
    token.password = this.user.password;
    this.userApiService.register(this.user)
      .subscribe(() => this.authService.login(token));
  }
}
