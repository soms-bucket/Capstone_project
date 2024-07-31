import { Component } from '@angular/core';
import { Automobile } from '../../model/Automobile';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AutomobileServiceService } from '../automobile-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-automobile',
  templateUrl: './edit-automobile.component.html',
  styleUrl: './edit-automobile.component.css'
})
export class EditAutomobileComponent {

  constructor(private automobileSrv: AutomobileServiceService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  automobile: Automobile = new Automobile()


  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      //editt:101 = @Path Varaible
      var id = Number(param.get('id')); // Read the product id from route
      this.getById(id);


    });
  }

  automobileForm = new FormGroup({
    make: new FormControl('', Validators.required),
    price: new FormControl(0, Validators.required),
    model: new FormControl('', Validators.required),
    year: new FormControl(0, Validators.required)
  })

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

  checkYear = ""
  checkPrice = ""

  check() {

    if (!this.automobileForm.value.price) {
      //alert()
      this.checkPrice = "price should not 0"
    }
    else if (!this.automobileForm.value.year) {
      //alert("year should not 0")
      this.checkPrice = ""
      this.checkYear = "year should not 0"
    }
    else {
      this.checkYear = ""
    }

  }

  getById(id: number) {
    this.automobileSrv.getByid(id).subscribe((data) => {
      console.log(data);
      this.automobile = data;
    });
  }

  updateAutomobile() {
    console.log(this.automobileForm.value);
    //console.log(this.manufacturerId)
    if (this.automobileForm.valid && this.automobileForm.value.year && this.automobileForm.value.price
    ) {



      this.automobile.make = this.automobileForm.value.make
      this.automobile.price = this.automobileForm.value.price
      this.automobile.model = this.automobileForm.value.model
      this.automobile.year = this.automobileForm.value.year


      this.automobileSrv.updateAutomobile(this.automobile.id, this.automobile).subscribe({
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
