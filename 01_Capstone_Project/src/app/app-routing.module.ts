import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListManufacturerComponent } from './list-manufacturer/list-manufacturer.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: "home", component:HomeComponent},
  {path:"list-manufacturer", component:ListManufacturerComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


