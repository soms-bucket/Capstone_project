import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';
import { Signin } from '../../model/SignIn';
import { SignUp } from '../../model/SignUp';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrl: './login-user.component.css'
})
export class LoginUserComponent {

  regis: FormGroup
  constructor(private fb: FormBuilder, private authSrv: AuthServiceService, private router: Router) {
    this.regis = this.fb.group({
      name: ['', [Validators.required]],
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email, Validators.pattern(/^[^\s@]+@[^\s@]+\.[a-zA-Z]{2,}$/)]],
      password: ['', [Validators.required, Validators.minLength(6)/*,Validators.pattern(/^(?=.[a-z])(?=.[A-Z])(?=.\d)(?=.[!@#$%^&*(),.?":{}|<>]).{6,}$/)*/]]
    });
  }

  

  isShow: boolean = false
  logError = ""

  enableSignUp() {
    this.logError = ""
    this.isShow = true
  }
  enableLogin() {
    this.logError = ""
    this.isShow = false
  }
  setShow(): boolean {
    return this.isShow
  }



  loginForm = new FormGroup({
    user: new FormControl('', Validators.required),
    pass: new FormControl('', Validators.required)
  })

  get user() {
    return this.loginForm.get('user');
  }

  get pass() {
    return this.loginForm.get('pass');
  }



  // signUpForm = new FormGroup({
  //   // name: ['', [Validators.required]],
  //   // username: ['', [Validators.required]],
  //   // email: ['', [Validators.required]],
  //   // password: ['', [Validators.required]],

  //   name: new FormControl(this.name, Validators.required),
  //   username: new FormControl('', Validators.required),
  //   email: new FormControl('', Validators.required),
  //   password: new FormControl('', Validators.required)
  // })

  get name() {
    return this.loginForm.get('name');
  }
  get username() {
    return this.loginForm.get('username');
  }
  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }


  loginUser() {
    // console.log(this.loginForm.value); 
    if (this.loginForm.valid) {

      const credential: Signin = new Signin();
      credential.usernameOrEmail = this.loginForm.value.user;
      credential.password = this.loginForm.value.pass;

      this.authSrv.authenticate(credential).subscribe({
        next: (data) => {
          this.authSrv.isAuthenticate = true
          this.logError = ""
          alert("Login Successfull")
          this.router.navigate(['/home']);
        },
        error: (err) => {
          this.logError = err.error.message
          console.log(err);
        }
      })
    }
  }


  createUser() {
    if (this.regis.valid) {

      const user: SignUp = this.regis.value
      // user.email = this.signUpForm.value.email
      // user.name = this.signUpForm.value.name
      // user.username = this.signUpForm.value.username
      // user.password = this.signUpForm.value.password

      console.log(this.regis.value)

      this.authSrv.newUser(user).subscribe({
        next: (data) => {
          //this.authSrv.isAuthenticate = true
          //this.router.navigate(['/home']);
          //console.log(data)
          this.regis.reset()
          this.logError = ""
          alert("user registered")
        },
        error: (err) => {
          this.logError = err.error.state
          console.log(err);
        }
      })
    }
  }






}
