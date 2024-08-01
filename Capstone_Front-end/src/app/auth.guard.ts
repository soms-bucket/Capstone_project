import { CanActivateFn } from '@angular/router';
import { AuthServiceService } from './auth-service.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthServiceService);


  // if (authService.isLoggedIn()) { 
  //   return true;
  // } else {
  //   return false;
  // }



  if (authService.isAuthenticate) { 
    return true;
  } else {
    return false;
  }
};
