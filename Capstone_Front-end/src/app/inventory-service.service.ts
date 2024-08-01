import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Inventory } from '../model/inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryServiceService {

  constructor(private http: HttpClient) { } //Dependency INjections
  getInventoryData() {
     let apiurl = "http://localhost:9600/api/inventories";
    return this.http.get(apiurl);
  }

    //Method For Creating new inventory
        createInventory(data: any) {
      //return this.http.post<any>(" http://localhost:3000/products",data)
      return this.http
        .post<any>('http://localhost:9600/api/inventories', data)
        .pipe(
          map((res: any) => {
            console.log(res);
            return res;
          })
        );
    }

    //------------------Update----------------
  getByInventoryId(id: number) {
    //return this.http.get<Product>(`http://localhost:3000/products/${id}`);
    console.log('');
    return this.http.get<Inventory>(`http://localhost:9600/api/inventories/${id}`);
   }

   update(payload:Inventory){
    //return this.http.put(`http://localhost:3000/products/${payload.id}`,payload);
    return this.http.put(`http://localhost:9600/api/inventories/${payload.id}`,payload);

   }

   delete(id: number){
    return this.http.delete(`http://localhost:9600/api/inventories/${id}`);
  }
}
