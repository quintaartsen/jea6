import { Component, OnInit } from '@angular/core';
import { AuthService, User } from '../shared';
import { JwtToken } from '../shared/models/jwtToken.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  token = new JwtToken();

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  public login(): void {
    this.authService.login(this.token);
  }
}
