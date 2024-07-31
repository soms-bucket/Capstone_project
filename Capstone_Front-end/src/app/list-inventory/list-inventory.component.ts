import { Component } from '@angular/core';
import { InventoryServiceService } from '../inventory-service.service';

@Component({
  selector: 'app-list-inventory',
  templateUrl: './list-inventory.component.html',
  styleUrl: './list-inventory.component.css'
})
export class ListInventoryComponent {
  inventories: any = [];
  constructor(private inventoryService: InventoryServiceService) {
    // this.inventoryService.getInventoryData().subscribe(data => {
    //   console.log(data);
    //   this.inventories = data;
    // })
     this.getAllInventory();
  }

  getAllInventory() {
    this.inventoryService.getInventoryData().subscribe(data => {
      console.log(data);
      this.inventories = data;
    })
  }

  delete(id: number) {
    this.inventoryService.delete(id).subscribe(data => {
      console.log(data);
      this.getAllInventory();
    })
  }
}
