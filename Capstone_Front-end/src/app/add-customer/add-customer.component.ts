import { Component } from '@angular/core';
import { CustomerServiceService } from '../customer-service.service';
import { Router } from '@angular/router';
import { Customer } from '../../model/customer';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrl: './add-customer.component.css'
})
export class AddCustomerComponent {
  customerForm: FormGroup;

  constructor(private fb: FormBuilder,private customerService : CustomerServiceService,
    private router: Router) {
      
    this.customerForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email, Validators.pattern(/^[^\s@]+@[^\s@]+\.[a-zA-Z]{2,}$/)]],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]+$'),Validators.minLength(10)]],
    });
  }

  get name() {
    return this.customerForm.get('name');
  }

  get email() {
    return this.customerForm.get('email');
  }

  get phone() {
    return this.customerForm.get('phone');
  }

  // create() {
  //   this.customerService.createCustomer(this.customerForm).subscribe({
  //     next: (data) => {
  //       // this.router.navigate(["/Products"])
  //     },
  //     error: (err) => {
  //       console.log(err);
  //     },
  //   });
  // }
  create() {
    if (this.customerForm.valid) {
      const newCustomer: Customer = this.customerForm.value;
      this.customerService.createCustomer(newCustomer).subscribe({
        next: (data) => {
          this.router.navigate(['/list-customer']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
