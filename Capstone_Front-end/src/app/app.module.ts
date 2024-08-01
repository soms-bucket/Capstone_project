import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ListManufacturerComponent } from './list-manufacturer/list-manufacturer.component';
import { HomeComponent } from './home/home.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { EditOrderComponent } from './edit-order/edit-order.component';
import { ListOrderComponent } from './list-order/list-order.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddManufacturerComponent } from './add-manufacturer/add-manufacturer.component';
import { EditManufacturerComponent } from './edit-manufacturer/edit-manufacturer.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { ListCustomerComponent } from './list-customer/list-customer.component';
import { EditCustomerComponent } from './edit-customer/edit-customer.component';
import { AddAutomobileComponent } from './add-automobile/add-automobile.component';
import { EditAutomobileComponent } from './edit-automobile/edit-automobile.component';
import { ListAutomobileComponent } from './list-automobile/list-automobile.component';
import { AddMaintenanceComponent } from './add-maintenance/add-maintenance.component';
import { EditMaintenanceComponent } from './edit-maintenance/edit-maintenance.component';
import { ListMaintenanceComponent } from './list-maintenance/list-maintenance.component';
import { AddInventoryComponent } from './add-inventory/add-inventory.component';
import { EditInventoryComponent } from './edit-inventory/edit-inventory.component';
import { ListInventoryComponent } from './list-inventory/list-inventory.component';

import { LoginUserComponent } from './login-user/login-user.component';
import { RecordComponent } from './record/record.component';

import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from "@angular/material/button";
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';

import {CookieService} from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,

    AddOrderComponent,
    EditOrderComponent,
    ListOrderComponent,

    AddManufacturerComponent,
    EditManufacturerComponent,
    ListManufacturerComponent,

    AddCustomerComponent,
    ListCustomerComponent,
    EditCustomerComponent,
    
    AddAutomobileComponent,
    EditAutomobileComponent,
    ListAutomobileComponent,

    AddMaintenanceComponent,
    EditMaintenanceComponent,
    ListMaintenanceComponent,

    AddInventoryComponent,
    EditInventoryComponent,
    ListInventoryComponent,

    LoginUserComponent,
    RecordComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    BrowserAnimationsModule,

    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatListModule,
    MatTableModule,
    FlexLayoutModule
  ],
  providers: [ provideAnimationsAsync(),provideAnimationsAsync('noop'), ], 
  bootstrap: [AppComponent]
})
export class AppModule { }

// CookieService
