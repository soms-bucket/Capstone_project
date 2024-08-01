import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { SignUp } from '../model/SignUp';
//import { CookieService } from 'ngx-cookie-service';       // for CookieService

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  isAuthenticate: boolean  = false;
  
  constructor(private http: HttpClient, ) { }
  //private cookieService: CookieService

  apiUrl = "http://localhost:9400/api/auth"

  authenticate(credentials: any){
    return this.http.post(`${this.apiUrl}/signin`,credentials)
    .pipe(
      map((res: any) => {
        // //token generate
        // if (res && res.token) {
        //   this.saveToken(res.token);
        // }
        console.log(res);
        return res;
      })
    );
  }

  newUser(nwuser: SignUp){
    return this.http.post(`${this.apiUrl}/signup`,nwuser)
    .pipe(
      map((res: any) => {
        console.log(res);
        return res;
      })
    );
  }

  logout():  Observable<boolean>{
    this.isAuthenticate = false;
    //this.removeToken();
      return of(this.isAuthenticate);
  }


  // login(username: string, password: string): Observable<{ valid: boolean, user?: any }> {
  //   return this.http.post<{ valid: boolean, user?: any }>(this.apiUrl, { username, password }).pipe(
  //     tap(response => {
  //       if (response.valid) {
  //         console.log("response", response);
  //         this.userid=response.user.userId;
  //         this.setAuthenticated(true);
  //         // Optionally, store user data in localStorage if needed
  //         localStorage.setItem('user', JSON.stringify(response.user));
  //       } else {
  //         this.setAuthenticated(false);
  //       }
  //     })
  //   );
  // }

  // logout(): void {
  //   localStorage.removeItem('isAuthenticated');
  //   localStorage.removeItem('user');
  // }

  // isAuthenticated(): Observable<boolean> {
  //   const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
  //   return of(isAuthenticated);
  // }

  // setAuthenticated(isAuthenticated: boolean): void {
  //   localStorage.setItem('isAuthenticated', isAuthenticated.toString());
  //   localStorage.setItem('userid',this.userid);
  // }

  
}
