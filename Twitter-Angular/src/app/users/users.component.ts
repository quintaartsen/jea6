import { Component, OnInit } from '@angular/core';
import { RestApiService, AuthService, User } from '../shared';
import { HttpClient, HttpParams } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { UserApiService } from '../shared/services/user-api.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  username = '';
  users: User[];

  constructor(private userApiSERVICE: UserApiService) { }

  ngOnInit() {
    this.getUsers();

  }

  getUsers() {
    this.userApiSERVICE.getUsers()
    .subscribe(data => this.users = data);
  }

  searchUsers() {
    this.userApiSERVICE.searchUsers(this.username)
    .subscribe(data => this.users = data);
  }
}
