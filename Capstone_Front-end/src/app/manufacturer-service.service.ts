import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Manufacturer } from '../model/manufacturer';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerServiceService {

 
  constructor(private http: HttpClient) {} //Dependency INjections
  feignUrl = "http://localhost:9192/api/manufacturers" // /12/automobile

  getAllData() {
    let apiurl = 'http://localhost:9192/api/manufacturers';
    return this.http.get<any[]>(apiurl);
  }

  getAutomobileByManufacture(id:number){
    return this.http.get<any[]>(`${this.feignUrl}/${id}/automobile`)
  }


  createManufacturer(data: any) {
    //return this.http.post<any>(" http://localhost:3000/products",data)
    return this.http
      .post<any>('http://localhost:9192/api/manufacturers', data)
      .pipe(
        map((res: any) => {
          console.log(res);
          return res;
        })
      );
  }

  getById(id: number) {
    //return this.http.get<Product>(`http://localhost:3000/products/${id}`);
    console.log('');
    return this.http.get<Manufacturer>(`http://localhost:9192/api/manufacturers/${id}`);
   }

   update(payload:Manufacturer){
    //return this.http.put(`http://localhost:3000/products/${payload.id}`,payload);
    return this.http.put(`http://localhost:9192/api/manufacturers/${payload.id}`,payload);

   }
   deleteById(id: number) {
    //return this.http.get<Product>(`http://localhost:3000/products/${id}`);
    console.log('');
    return this.http.delete(`http://localhost:9192/api/manufacturers/${id}`);
   }
}
