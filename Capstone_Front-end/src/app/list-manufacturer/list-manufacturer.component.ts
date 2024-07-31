import { Component } from '@angular/core';
import { ManufacturerServiceService } from '../manufacturer-service.service';

@Component({
  selector: 'app-list-manufacturer',
  templateUrl: './list-manufacturer.component.html',
  styleUrl: './list-manufacturer.component.css'
})
export class ListManufacturerComponent {
  manufacturer: any
  tableState = ""

  constructor(private manufacturerService: ManufacturerServiceService) {
    this.getManufacturers()
  }
  getManufacturers() {
    this.manufacturerService.getAllData().subscribe({
      next: (data) => {
        console.log(data);
        this.manufacturer = data;
        //if(this.manufacturer.length == 0) this.tableState = "No records found"
      },
      error: (err) => {
        //if(this.manufacturer.length == 0) this.tableState = "No records found"
      }
    })
  }
  delete(id: number) {
    if (confirm("Are you sure?") == true) {
      this.manufacturerService.deleteById(id).subscribe(data => {
        this.getManufacturers()

      })
    }
  }

}
