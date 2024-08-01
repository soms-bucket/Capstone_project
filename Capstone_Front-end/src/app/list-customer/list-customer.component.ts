import { Component } from '@angular/core';
import { CustomerServiceService } from '../customer-service.service';

@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrl: './list-customer.component.css'
})
export class ListCustomerComponent {
  customers: any = [];
  constructor(private customerService: CustomerServiceService) {
    // this.customerService.getCustomerData().subscribe(data => {
    //   console.log(data);
    //   this.customers = data;
    // })
    this.getAllCustomersData();
  }

  getAllCustomersData() {
    this.customerService.getAllCustomer().subscribe(data => {
      console.log(data);
      this.customers = data;
    })
  }

  delete(id: number) {
    if (confirm("Are you sure?") == true) {
      this.customerService.delete(id).subscribe(data => {
        console.log(data);
        this.getAllCustomersData();
      })
    }
  }
}
