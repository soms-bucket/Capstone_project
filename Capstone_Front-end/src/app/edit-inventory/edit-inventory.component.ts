import { Component } from '@angular/core';
import { Inventory } from '../../model/inventory';
import { InventoryServiceService } from '../inventory-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-inventory',
  templateUrl: './edit-inventory.component.html',
  styleUrl: './edit-inventory.component.css'
})
export class EditInventoryComponent {

  inventoryForm: Inventory = new Inventory();;
  constructor(
    private inventoryService: InventoryServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      //editt:101 = @Path Varaible
      var id = Number(param.get('id')); // Read the product id from route
      this.getByInventoryId(id);
    });
  }

  getByInventoryId(id: number) {
    this.inventoryService.getByInventoryId(id).subscribe((data) => {
      console.log(data);
      this.inventoryForm = data;
    });
  }
  update() {
    this.inventoryService.update(this.inventoryForm)
    .subscribe({
      next:(data) => {
        this.router.navigate(["/list-inventory"]);
      },
      error:(err) => {
        console.log(err);
      }
    })
  }
}
