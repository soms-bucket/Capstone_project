import { Component } from '@angular/core';
import { Customer } from '../../model/customer';
import { CustomerServiceService } from '../customer-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrl: './edit-customer.component.css'
})
export class EditCustomerComponent {

  customerForm: Customer = new Customer();
  constructor(
    private customerService: CustomerServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      //editt:101 = @Path Varaible
      var id = Number(param.get('id')); // Read the product id from route
      this.getById(id);

    });
  }

  getById(id: number) {
    this.customerService.getById(id).subscribe((data) => {
      console.log(data);
      this.customerForm = data;
    });
  }
  logError: any;
  update() {
    this.customerService.update(this.customerForm)
    .subscribe({
      next:(data) => {
        this.router.navigate(["/list-customer"]);
      },
      error:(err) => {
        this.logError=err.error.message
        // console.log(this.logError);
        console.log(err);
      }
    })
  }
}
