import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MaintenanceService } from '../maintenance.service';
import { Maintenance } from '../../model/Maintenance';
import { AutomobileServiceService } from '../automobile-service.service';

@Component({
  selector: 'app-add-maintenance',
  templateUrl: './add-maintenance.component.html',
  styleUrls: ['./add-maintenance.component.css']
})
export class AddMaintenanceComponent {
  newMaintenance: Maintenance = new Maintenance();
  autoList: any[] = []

  constructor(private maintenanceService: MaintenanceService,
    private automobileSrv: AutomobileServiceService,
    private router: Router) {
      this.getAllAutomobile()
    }

  saveMaintenance(): void {
    this.maintenanceService.createMaintenance(this.newMaintenance).subscribe((res) => {
      this.newMaintenance = res
      this.router.navigate(['/list-maintenance']);
    });
  }

  cancel(): void {
    console.log(this.newMaintenance.automobileId)
    this.router.navigate(['/list-maintenance']);
  }
  getAllAutomobile(){
    this.automobileSrv.getAllAutomobile().subscribe(data =>{
      this.autoList = data
    })
  }

  aido(autoId:number){
    this.newMaintenance.automobileId = autoId;
  }


}



// import { Component } from '@angular/core';
// import { Maintenance } from '../model/Maintenance';
// import { MaintenanceService } from '../maintenance.service';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-add-maintenance',
//   templateUrl: './add-maintenance.component.html',
//   styleUrl: './add-maintenance.component.css'
// })
// export class AddMaintenanceComponent {
//   maintenances: Maintenance[] = [];
//   selectedMaintenance: Maintenance | null = null;
  
//   constructor(private maintenanceService: MaintenanceService, private router: Router) {
//     this.loadMaintenances();
//   }
  
//   // Method to load maintenances from the service
//   loadMaintenances(): void {
//     this.maintenanceService.getMaintenances().subscribe(data => {
//       this.maintenances = data;
//     });
//   }

//   // Method to select a maintenance record
//   selectMaintenance(maintenance: Maintenance): void {
//     this.selectedMaintenance = { ...maintenance };
//   }

//   createNewMaintenance(): void {
//     this.selectedMaintenance = {
//       id: null,
//       automobileId: null,
//       date: '',
//       description: ''
//     } as Maintenance;
//   }

//   // Method to clear the selected maintenance record
//   clearSelection(): void {
//     this.selectedMaintenance = null;
//   }

//   // Method to save a maintenance record
//   saveMaintenance(): void {
//     if (this.selectedMaintenance) {
//       if (this.selectedMaintenance.id) {
//         this.maintenanceService.updateMaintenance(this.selectedMaintenance.id, this.selectedMaintenance).subscribe(() => {
//           this.loadMaintenances();
//           this.clearSelection();
//         });
//       } else {
//         this.maintenanceService.createMaintenance(this.selectedMaintenance).subscribe(() => {
//           this.loadMaintenances();
//           this.clearSelection();
//         });
//       }
//     }
//   }
//   selectAndNavigate(maintenance: Maintenance): void {
//     this.selectMaintenance(maintenance);
//     this.navigateToPage();
//   }
//   navigateToPage() :void {
//     this.router.navigate(['/add-maintenance']);
//   }
// }
