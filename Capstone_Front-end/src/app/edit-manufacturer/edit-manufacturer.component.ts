import { Component } from '@angular/core';
import { Manufacturer } from '../../model/manufacturer';
import { ManufacturerServiceService } from '../manufacturer-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-manufacturer',
  templateUrl: './edit-manufacturer.component.html',
  styleUrl: './edit-manufacturer.component.css'
})
export class EditManufacturerComponent {
  manufacturerForm: Manufacturer = new Manufacturer();
  constructor(
    private manufacturerService: ManufacturerServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      //editt:101 = @Path Varaible
      var id = Number(param.get('id')); // Read the product id from route
      this.getById(id);

      //this.router.navigate(["/Productssss"]);
    });
  }

  getById(id: number) {
    this.manufacturerService.getById(id).subscribe((data) => {
      console.log(data);
      this.manufacturerForm = data;
    });
  }
  update() {
    this.manufacturerService.update(this.manufacturerForm)
    .subscribe({
      next:(data) => {
        this.router.navigate(["/list-manufacturer"]);
      },
      error:(err) => {
        console.log(err);
      }
    })
  }
}
