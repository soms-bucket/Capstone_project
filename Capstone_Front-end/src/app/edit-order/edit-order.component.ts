import { Component } from '@angular/core';
import { OrderServiceService } from '../order-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrl: './edit-order.component.css'
})
export class EditOrderComponent {

    constructor(private orderSrv: OrderServiceService,
      private router: Router,
      private route:ActivatedRoute
     ){}
}
