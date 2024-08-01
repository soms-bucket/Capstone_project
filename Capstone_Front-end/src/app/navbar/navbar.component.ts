import { Component, ViewChild } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { Event, NavigationEnd, Router } from '@angular/router';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private authService: AuthServiceService, private router: Router) { }

  @ViewChild('sidenav')
  sidenav!: MatSidenav;

  ngOnInit(): void {
    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationEnd) {
        if (this.sidenav) {
          this.sidenav.close();
        }
      }
    });
  }

  isShow = false
  setShow(): boolean {
    this.isShow = this.authService.isAuthenticate;
    // this.isShow = this.authService.isLoggedIn();
    // console.log("jjjjjjjjjjjjjj")
    // console.log(this.authService.isLoggedIn())
    return this.isShow;
  }

  doLogout() {
    if (confirm("Log-Out?") == true) {
      this.authService.logout();
      this.router.navigate(["/home"]);
      //prompt("Log-Out SuccessFull!")
    }


  }
}
