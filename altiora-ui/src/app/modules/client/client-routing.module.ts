import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
// import { DashboardComponent } from './dashboard.component';
import { ClientComponent } from './client.component';

@NgModule({
    imports: [RouterModule.forChild([
        { path: '', component: ClientComponent }
    ])],
    exports: [RouterModule]
})
export class ClientRoutingModule { }
