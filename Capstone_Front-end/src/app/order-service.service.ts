import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/Order';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private http: HttpClient) { }

  apiurl="http://localhost:9200/ordapi/order";
  

  getAllOrders() {
    return this.http.get<Order[]>(`${this.apiurl}/all`);
  }
  
  getOrderByCustomerId(id:number){
    return this.http.get<Order[]>(`${this.apiurl}/customer/${id}`);
    //return this.http.get<Order[]>(`${this.feignUrl}/${id}/order`);
  }

  getOrderAutomobileId(id:number){
    return this.http.get<Order[]>(`${this.apiurl}/automobile/${id}`);
  }

  createOrder(order: Order){
    return this.http.post<Order>(this.apiurl,order)
    .pipe(map((res:any)=>{
      console.log(res);
      return res;
    }));
  }

  deleteOrderById(id:number){
    return this.http.delete(`${this.apiurl}/${id}`)
  }

}
