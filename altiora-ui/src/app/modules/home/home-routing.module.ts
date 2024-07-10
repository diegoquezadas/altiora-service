import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
// import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home.component';

@NgModule({
    imports: [RouterModule.forChild([
        { path: '', component: HomeComponent }
    ])],
    exports: [RouterModule]
})
export class HomeRoutingModule { }
