import { Component } from '@angular/core';
import { ManufacturerServiceService } from '../manufacturer-service.service';
import { Manufacturer } from '../../model/manufacturer';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-manufacturer',
  templateUrl: './add-manufacturer.component.html',
  styleUrl: './add-manufacturer.component.css'
})
export class AddManufacturerComponent {
  manufacturerForm:Manufacturer =  new Manufacturer();
  constructor(private manufacturerService:ManufacturerServiceService,
    private router:Router) {}
  create() {
    this.manufacturerService.createManufacturer(this.manufacturerForm).subscribe({
      next: (data) => {
         this.router.navigate(["/list-manufacturer"])
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
