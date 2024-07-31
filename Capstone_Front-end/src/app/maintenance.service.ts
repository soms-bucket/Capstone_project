import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Maintenance } from '../model/Maintenance';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {
  // private apiUrl = 'http://localhost:3000/maintenance';
  private apiUrl = 'http://localhost:9300/api/maintenance';

  constructor(private http: HttpClient) { }

  getMaintenances(): Observable<Maintenance[]> {
    return this.http.get<Maintenance[]>(this.apiUrl);
  }
  getByAutomobileId(autoId:number): Observable<Maintenance[]> {
    return this.http.get<Maintenance[]>(`${this.apiUrl}/automobile/${autoId}`)
  }

  getMaintenanceById(id: number): Observable<Maintenance> {
    return this.http.get<Maintenance>(`${this.apiUrl}/${id}`);
  }

  updateMaintenance(id: number, maintenance: Maintenance): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, maintenance);
  }

  createMaintenance(maintenance: Maintenance): Observable<Maintenance> {
    return this.http.post<Maintenance>(this.apiUrl, maintenance);
  }

  deleteMaintenance(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}




// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { Maintenance } from './model/Maintenance';

// @Injectable({
//   providedIn: 'root'
// })
// export class MaintenanceService {
//   private apiUrl = 'http://localhost:9300/api/maintenance'; 

//   constructor(private http: HttpClient) { }

//   getMaintenances(): Observable<Maintenance[]> {
//     return this.http.get<Maintenance[]>(this.apiUrl);
//   }

//   getMaintenance(id: number): Observable<Maintenance> {
//     return this.http.get<Maintenance>(`${this.apiUrl}/${id}`);
//   }

//   createMaintenance(maintenance: Maintenance): Observable<Maintenance> {
//     return this.http.post<Maintenance>(this.apiUrl, maintenance);
//   }

//   updateMaintenance(id: number, maintenance: Maintenance): Observable<Maintenance> {
//     return this.http.put<Maintenance>(`${this.apiUrl}/${id}`, maintenance);
//   }

//   deleteMaintenance(id: number): Observable<void> {
//     return this.http.delete<void>(`${this.apiUrl}/${id}`);
//   }
// }


// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class MaintenanceService {

//   constructor() { }
// }
