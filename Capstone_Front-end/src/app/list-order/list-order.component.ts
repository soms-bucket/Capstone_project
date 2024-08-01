import { Component } from '@angular/core';
import { OrderServiceService } from '../order-service.service';
import { Order } from '../../model/Order';
import { ActivatedRoute } from '@angular/router';
import { CustomerServiceService } from '../customer-service.service';
import { AutomobileServiceService } from '../automobile-service.service';

@Component({
  selector: 'app-list-order',
  templateUrl: './list-order.component.html',
  styleUrl: './list-order.component.css'
})
export class ListOrderComponent {

  orderList: Order[] = [];
  
  constructor(private orderSrv: OrderServiceService,
    private customerSrv: CustomerServiceService,
    private automibleSrv: AutomobileServiceService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    //editt:101 = @Path Varaible
    // var customerId = Number(param.get('cusid')); // Read the product id from route
    // var automobileId = Number(param.get('autoId'));
    // console.log(customerId)
    // console.log(automobileId)
    // if(customerId>0){
    //   this.getByCustomer(customerId);
    // }
    // else if(automobileId>0){
    //   this.getByAutomobileId(automobileId);
    // }

    this.loadAllOrders()
    // get all customer
    this.customerSrv.getAllCustomer().subscribe(data => {
      this.cIdList = data
      console.log(this.cIdList)
    });
    this.automibleSrv.getAllAutomobile().subscribe(data =>{
      this.aIdList = data
    })

  }

  // ___________________________
  cIdList: any;
  cusId = 0

  aIdList: any;
  autoId = 0

  // Feign Call from customer
  getByCustomerId(id: number) {
    this.customerSrv.getOrderByCustomerId(id).subscribe((data) => {
      this.orderList = data;
      console.log(data)
    })
  }
  callForCustomer() {
    this.autoId = 0
    this.getByCustomerId(this.cusId)
  }

  // feign call from Automobile
  getByAutomobileId(id: number) {
    this.automibleSrv.getOrderAutomobileId(id).subscribe((data) => {
      this.orderList = data;
      console.log(data)
    })
  }

  callForAutoMobile() {
    this.cusId= 0
    this.getByAutomobileId(this.autoId)
  }



  reset() { this.loadAllOrders() }

  // ______________________________________________________________________________

  loadAllOrders() {
    this.orderSrv.getAllOrders().subscribe(data => {
      this.orderList = data
    })
  }

  delete(oId: number) {
    this.orderSrv.deleteOrderById(oId).subscribe({
      next: (data) => {
        if (this.cusId) this.getByCustomerId(this.cusId)
        else if(this.autoId) this.getByAutomobileId(this.autoId)
        else this.loadAllOrders()
      },
      error: (err) => {
        console.log(err);
      }
    })

  }
}
