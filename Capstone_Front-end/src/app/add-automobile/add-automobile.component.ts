import { Component, model } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Automobile } from '../../model/Automobile';
import { AutomobileServiceService } from '../automobile-service.service';
import { ManufacturerServiceService } from '../manufacturer-service.service';
import { Manufacturer } from '../../model/manufacturer';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-automobile',
  templateUrl: './add-automobile.component.html',
  styleUrl: './add-automobile.component.css'
})
export class AddAutomobileComponent {

  automobileForm: FormGroup;

  constructor(private fb: FormBuilder, private automobileSrv: AutomobileServiceService,
    private manufacturerSrv: ManufacturerServiceService,
    private router: Router
  ) {
    this.automobileForm = this.fb.group({
      make: ['', [Validators.required]],
      price: [0, [Validators.required, Validators.min(1)]],
      model: ['', [Validators.required]],
      year: [0, [Validators.required, Validators.min(1)]],
      manufacturerId: [0, [Validators.required, Validators.min(1)]],
    })
  }

  mlist: Manufacturer[] = []
  ngOnInit(): void {
    this.manufacturerSrv.getAllData().subscribe(data => {
      this.mlist = data
      console.log(this.mlist)
    });
    //this.order.customerId = 1
  }

  // automobileForm = new FormGroup({
  //   make: new FormControl('', Validators.required),
  //   price: new FormControl(0, Validators.required),
  //   model: new FormControl('', Validators.required),
  //   year: new FormControl(0, Validators.required),
  //   manufacturerId: new FormControl(0, Validators.required),
  // })

  get make() {
    return this.automobileForm.get('make');
  }
  get price() {
    return this.automobileForm.get('price');
  }
  get model() {
    return this.automobileForm.get('model');
  }
  get year() {
    return this.automobileForm.get('year');
  }
  get manufacturerId() {
    return this.automobileForm.get('manufacturerId');
  }

  addAutomobile() {
    console.log(this.automobileForm.value);
    //console.log(this.manufacturerId)
    if (this.automobileForm.valid) {

      const automobile: Automobile = this.automobileForm.value

      // automobile.make = this.automobileForm.value.make
      // automobile.price = this.automobileForm.value.price
      // automobile.model = this.automobileForm.value.model
      // automobile.year = this.automobileForm.value.year
      // automobile.manufacturerId = this.automobileForm.value.manufacturerId

      this.automobileSrv.createAutomobile(automobile).subscribe({
        next: (data) => {
          console.log("done")
          this.router.navigate(['/list-automobile'])
        },
        error: (err) => {
          console.log(err);
        }
      })
    }
  }

}
