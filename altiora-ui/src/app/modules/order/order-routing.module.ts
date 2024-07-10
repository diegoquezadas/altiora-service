import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
// import { DashboardComponent } from './dashboard.component';
import { OrderComponent } from './order.component';

@NgModule({
    imports: [RouterModule.forChild([
        { path: '', component: OrderComponent }
    ])],
    exports: [RouterModule]
})
export class OrderRoutingModule { }
