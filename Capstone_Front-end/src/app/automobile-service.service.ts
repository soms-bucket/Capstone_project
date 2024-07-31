import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Automobile } from '../model/Automobile';
import { Order } from '../model/Order';

@Injectable({
  providedIn: 'root'
})
export class AutomobileServiceService {

  apiurl="http://localhost:9100/autoapi/automobile"; // http://localhost:9100/autoapi/automobile/manufacture/
  constructor(private http: HttpClient) { }

  getAllAutomobile(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiurl}/all`);
  }
  getByid(id:number){
    return this.http.get<Automobile>(`${this.apiurl}/${id}`);
  }
  getByYear(year:number){
    return this.http.get<any[]>(`${this.apiurl}/year/${year}`)
  }
  // feign call 
  getOrderAutomobileId(autoid:number){
    return this.http.get<Order[]>(`${this.apiurl}/${autoid}/order`);
  }

  getAutomobileByManufacture(id:number){
    return this.http.get<any[]>(`${this.apiurl}/manufacture/${id}`);
  }

  createAutomobile(auto: Automobile){
    return this.http.post<Automobile>(`${this.apiurl}`,auto);
  }

  updateAutomobile(id:number, auto:Automobile){
    return this.http.put<Automobile>(`${this.apiurl}/${id}`,auto)
  }

  deletebyId(id:number){
    return this.http.delete(`${this.apiurl}/${id}`);
  }
  
}
