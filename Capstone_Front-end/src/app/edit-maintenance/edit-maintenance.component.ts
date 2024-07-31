import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaintenanceService } from '../maintenance.service';
import { Maintenance } from '../../model/Maintenance';

@Component({
  selector: 'app-edit-maintenance',
  templateUrl: './edit-maintenance.component.html',
  styleUrls: ['./edit-maintenance.component.css']
})
export class EditMaintenanceComponent implements OnInit {
  selectedMaintenance: Maintenance | null = null;

  constructor(
    private route: ActivatedRoute,
    private maintenanceService: MaintenanceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.maintenanceService.getMaintenanceById(+id).subscribe(data => {
        this.selectedMaintenance = data;
      });
    }
  }

  saveMaintenance(): void {
    if (this.selectedMaintenance) {
      if (this.selectedMaintenance.id) {
        this.maintenanceService.updateMaintenance(this.selectedMaintenance.id, this.selectedMaintenance).subscribe(() => {
          this.router.navigate(['/list-maintenance']);
        });
      } else {
        this.maintenanceService.createMaintenance(this.selectedMaintenance).subscribe(() => {
          this.router.navigate(['/list-maintenance']);
        });
      }
    }
  }

  clearSelection(): void {
    //this.selectedMaintenance = null;
    this.router.navigate(['/list-maintenance']);
  }
}


