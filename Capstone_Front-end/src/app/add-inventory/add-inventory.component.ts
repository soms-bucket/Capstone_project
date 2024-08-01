import { Component } from '@angular/core';
import { Inventory } from '../../model/inventory';
import { InventoryServiceService } from '../inventory-service.service';
import { Router } from '@angular/router';
import { AutomobileServiceService } from '../automobile-service.service';
import { Automobile } from '../../model/Automobile';

@Component({
  selector: 'app-add-inventory',
  templateUrl: './add-inventory.component.html',
  styleUrl: './add-inventory.component.css'
})
export class AddInventoryComponent {

  inventoryForm: Inventory = new Inventory();
  constructor(private inventoryService: InventoryServiceService,
    private automobileSrv: AutomobileServiceService,
    private router: Router) {
    this.loadAutomobileData()
  }

  autoList: any[] = []
  loadAutomobileData() {
    this.automobileSrv.getAllAutomobile().subscribe(data => {
      this.autoList = data
    })
  }

  create() {
    this.inventoryService.createInventory(this.inventoryForm).subscribe({
      next: (data) => {
        this.router.navigate(["/list-inventory"])
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  aido(autoId: number) {
    this.inventoryForm.automobileId = autoId
  }

}
