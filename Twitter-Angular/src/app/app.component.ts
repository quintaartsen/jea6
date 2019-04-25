import { Component, AfterViewInit, ElementRef } from '@angular/core';
import { AuthService } from './shared';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  constructor(private elementRef: ElementRef, public authService: AuthService, public router: Router) {
  }

  public logout(): void {
    this.authService.logout();
  }

  // Change background color
  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#9e9e9e';
  }
}
