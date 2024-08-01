import { Component, OnInit } from '@angular/core';
import { MaintenanceService } from '../maintenance.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Maintenance } from '../../model/Maintenance';

@Component({
  selector: 'app-list-maintenance',
  templateUrl: './list-maintenance.component.html',
  styleUrls: ['./list-maintenance.component.css']
})
export class ListMaintenanceComponent implements OnInit {
  maintenances: Maintenance[] = [];
  selectedMaintenance: Maintenance | null = null;
  automobileId = 0;
  
  constructor(private maintenanceService: MaintenanceService,
     private router: Router,
     private route: ActivatedRoute
    )  { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      //editt:101 = @Path Varaible
      //this.automobileId = Number(param.get('autoId')); // Read the product id from route
      this.loadMaintenances();
      //this.getMaintenancesByAutomobileId(this.automobileId)

    });

    //this.loadMaintenances();
  }
  
  getMaintenancesByAutomobileId(autoId:number){
    return this.maintenanceService.getByAutomobileId(autoId).subscribe(data =>{
      this.maintenances = data;
    })

  }

  // Method to load maintenances from the service
  loadMaintenances(): void {
    this.maintenanceService.getMaintenances().subscribe(data => {
      this.maintenances = data;
    });
  }

  // Method to select a maintenance record
  selectMaintenance(maintenance: Maintenance): void {
    this.selectedMaintenance = { ...maintenance };
  }

  createAndNavigate(): void {
    this.router.navigate(['/add-maintenance']);
  }

  // Method to clear the selected maintenance record
  clearSelection(): void {
    this.selectedMaintenance = null;
  }

  // Method to save a maintenance record
  saveMaintenance(): void {
    if (this.selectedMaintenance) {
      if (this.selectedMaintenance.id) {
        this.maintenanceService.updateMaintenance(this.selectedMaintenance.id, this.selectedMaintenance).subscribe(() => {
          this.loadMaintenances();
          this.clearSelection();
        });
      } else {
        this.maintenanceService.createMaintenance(this.selectedMaintenance).subscribe(() => {
          this.loadMaintenances();
          this.clearSelection();
        });
      }
    }
  }

  // Method to delete a maintenance record
  deleteMaintenance(id: number): void {
    this.maintenanceService.deleteMaintenance(id).subscribe(() => {
      //this.getMaintenancesByAutomobileId(this.automobileId);
      this.loadMaintenances();
    });
  }

  navigateToPage(id: number | null): void {
    if (id !== null) {
      this.router.navigate([`/edit-maintenance/${id}`]);
    } else {
      // Handle the case where id is null if necessary
      console.error("Maintenance ID is null");
    }
  }
  

}


// createNewMaintenance(): void {
  //   this.selectedMaintenance = {
  //     id: null,
  //     automobileId: null,
  //     date: '',
  //     description: ''
  //   } as Maintenance;
  // }

  
  // selectAndNavigate(maintenance: Maintenance): void {
  //   this.selectMaintenance(maintenance);
  //   this.navigateToPage();
  // }

  // navigateToPage(id: number): void {
  //   this.router.navigate([`/edit-maintenance/${id}`]);
  // }