import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Menu',
                items: [
                    { label: 'Cliente', icon: 'pi pi-fw pi-user', routerLink: ['/client'] },
                    { label: 'Articulo', icon: 'pi pi-fw pi-user', routerLink: ['/article'] },
                    { label: 'Orden', icon: 'pi pi-fw pi-user', routerLink: ['/order'] },
                ]
            },
        ];
    }
}
