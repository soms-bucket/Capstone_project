import { Component } from '@angular/core';
import { AutomobileServiceService } from '../automobile-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ManufacturerServiceService } from '../manufacturer-service.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-list-automobile',
  templateUrl: './list-automobile.component.html',
  styleUrl: './list-automobile.component.css'
})
export class ListAutomobileComponent {

  automobileList: any[] = []
  automap: Map<number,string> = new Map<number,string>
  manufactureId: number = 0;

  mList: any[] = []
  mid = 0
  yearList: Set<number> = new Set<number>
  year = 0

  constructor(private automobileSrv: AutomobileServiceService,
    private manufactureSrv: ManufacturerServiceService,
    private router: Router,
    private route: ActivatedRoute
  ) { }


  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      //editt:101 = @Path Varaible
      //this.manufactureId = Number(param.get('id')); // Read the product id from route
      //this.getAutomobileData(this.manufactureId);
      this.manufactureSrv.getAllData().subscribe(data => {
        this.mList = data
        data.forEach(d => this.automap.set(d.id, d.name))
        
      })
      this.loadAllAutomobileData() 
    });
  }

  // feign call
  getAutomobileData(id: number) {
    this.manufactureSrv.getAutomobileByManufacture(id).subscribe(data => {
      this.automobileList = data
    })
  }
  callForManufacturer() {
    this.year = 0
    this.getAutomobileData(this.mid)
  }

  reset() { this.loadAllAutomobileData() }
  // ______________________________________________________________________

  callForYear() {
    this.mid = 0
    this.getAutomobileByYear(this.year)

  }

  getAutomobileByYear(year: number) {
    this.automobileSrv.getByYear(year).subscribe(data => {
      this.automobileList = data
    })
  }
  loadAllAutomobileData() {
    this.automobileSrv.getAllAutomobile().subscribe(data => {
      this.automobileList = data
      data.forEach(auto => this.yearList.add(auto.year))
    })
  }
  delete(aId: number) {
    if (confirm("Are you sure?") == true) {
      this.automobileSrv.deletebyId(aId).subscribe({
        next: (data) => {

          if (this.mid) this.getAutomobileData(this.mid)
          else if (this.year) this.getAutomobileByYear(this.year)
          else this.loadAllAutomobileData()
        },
        error: (err) => {
          console.log(err);
        }
      })
    }

  }
}
