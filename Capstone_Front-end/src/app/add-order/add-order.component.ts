import { Component } from '@angular/core';
import { CustomerServiceService } from '../customer-service.service';
import { Order } from '../../model/Order';
import { AutomobileServiceService } from '../automobile-service.service';
import { OrderServiceService } from '../order-service.service';
import { Router } from '@angular/router';
import { Customer } from '../../model/customer';
import { Automobile } from '../../model/Automobile';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrl: './add-order.component.css'
})
export class AddOrderComponent {

  order: Order = new Order;

  cIdList: any;
  aIdList: any;

  constructor(private customerSrv: CustomerServiceService, 
    private autoSrv: AutomobileServiceService,
    private orderSrv: OrderServiceService,
    private router: Router

    ){}


  ngOnInit(): void {
    this.customerSrv.getAllCustomer().subscribe(data =>{
      this.cIdList = data
      console.log(this.cIdList)
    });
    this.autoSrv.getAllAutomobile().subscribe(data =>{
      this.aIdList = data
      console.log(this.aIdList)
    })
    //this.order.customerId = 1
  }
  // cusdo(id:number){
  //   this.order.customerId = id;
  // }
  // aido(id:number){
  //   this.order.automobileId = id;
  // }

  create(){
    this.orderSrv.createOrder(this.order).subscribe({
      next:(data) => {
        this.router.navigate(["/list-order"])
      },
      error:(err) => {
        console.log(err);
      }
    })
  }

  
}
