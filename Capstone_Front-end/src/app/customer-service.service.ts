import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Customer } from '../model/customer';
import { Order } from '../model/Order';

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

  apiurl="http://localhost:9090/api/customers";
  feignUrl = "http://localhost:9090/api/customers"
  
  constructor(private http: HttpClient) { }

  getAllCustomer(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiurl}/all`);
  }

  // feign call
  getOrderByCustomerId(cId: number){
    return this.http.get<Order[]>(`${this.feignUrl}/${cId}/order`);
  }


   //Method For Creating Customer
 createCustomer(data: any) {
   //return this.http.post<any>(" http://localhost:3000/products",data)
   return this.http
     .post<any>('http://localhost:9090/api/customers/', data)
     .pipe(
       map((res: any) => {
         console.log(res);
         return res;
       })
     );
 }

 //------------------Update----------------
 getById(id: number) {
   //return this.http.get<Product>(`http://localhost:3000/products/${id}`);
   console.log('');
   return this.http.get<Customer>(`http://localhost:9090/api/customers/${id}`);
  }

  update(payload:Customer){
   //return this.http.put(`http://localhost:3000/products/${payload.id}`,payload);
   return this.http.put(`http://localhost:9090/api/customers/${payload.id}`,payload);

  }

  delete(id: number){
   return this.http.delete(`http://localhost:9090/api/customers/${id}`);
 }

}

