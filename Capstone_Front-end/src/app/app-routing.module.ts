import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListManufacturerComponent } from './list-manufacturer/list-manufacturer.component';
import { HomeComponent } from './home/home.component';
import { ListOrderComponent } from './list-order/list-order.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { EditOrderComponent } from './edit-order/edit-order.component';
import { EditManufacturerComponent } from './edit-manufacturer/edit-manufacturer.component';
import { AddManufacturerComponent } from './add-manufacturer/add-manufacturer.component';
import { ListCustomerComponent } from './list-customer/list-customer.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { EditCustomerComponent } from './edit-customer/edit-customer.component';
import { ListAutomobileComponent } from './list-automobile/list-automobile.component';
import { AddAutomobileComponent } from './add-automobile/add-automobile.component';
import { EditAutomobileComponent } from './edit-automobile/edit-automobile.component';
import { ListMaintenanceComponent } from './list-maintenance/list-maintenance.component';
import { EditMaintenanceComponent } from './edit-maintenance/edit-maintenance.component';
import { AddMaintenanceComponent } from './add-maintenance/add-maintenance.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { authGuard } from './auth.guard';
import { RecordComponent } from './record/record.component';
import { ListInventoryComponent } from './list-inventory/list-inventory.component';
import { AddInventoryComponent } from './add-inventory/add-inventory.component';
import { EditInventoryComponent } from './edit-inventory/edit-inventory.component';

const routes: Routes = [
  {path: "home", component:HomeComponent},
  {path: "login", component:LoginUserComponent},
 

  {path: "record", component: RecordComponent, canActivate:[authGuard] },

  {path: "list-order", component:ListOrderComponent, canActivate:[authGuard]},
  //{path: "list-order-automobile/:autoId", component:ListOrderComponent},
  {path:"add-order", component:AddOrderComponent, canActivate:[authGuard]},
  // {path:"edit-order/:id", component:EditOrderComponent},
  
  {path:"list-manufacturer", component:ListManufacturerComponent, canActivate:[authGuard]}, 
  {path:"add-manufacturer", component:AddManufacturerComponent, canActivate:[authGuard]},
  {path:"edit-manufacturer/:id", component:EditManufacturerComponent, canActivate:[authGuard]},

  {path:"list-customer", component:ListCustomerComponent, canActivate:[authGuard]},
  {path:"add-customer", component:AddCustomerComponent, canActivate:[authGuard]},
  {path:"edit-customer/:id", component:EditCustomerComponent, canActivate:[authGuard]},

  {path:"list-automobile", component:ListAutomobileComponent, canActivate:[authGuard]},
  {path:"add-automobile", component:AddAutomobileComponent, canActivate:[authGuard]},
  {path:"edit-automobile/:id", component:EditAutomobileComponent, canActivate:[authGuard]},

  {path:"list-maintenance", component:ListMaintenanceComponent, canActivate:[authGuard]},
  {path:"add-maintenance", component:AddMaintenanceComponent, canActivate:[authGuard]},
  //{path: "edit-maintenance", component:EditMaintenanceComponent },
  {path: "edit-maintenance/:id", component:EditMaintenanceComponent, canActivate:[authGuard] },
  

  {path:"list-inventory", component:ListInventoryComponent, canActivate:[authGuard]},
  {path:"add-inventory", component:AddInventoryComponent, canActivate:[authGuard]},
  {path:"edit-inventory/:id", component:EditInventoryComponent, canActivate:[authGuard]},


  {path:'', redirectTo:'home',pathMatch:'full'},
  {path:' ', redirectTo:'home',pathMatch:'full'},
  { path: '**', component:  LoginUserComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


