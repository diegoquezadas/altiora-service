import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/api/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ProductService } from 'src/app/demo/service/product.service';
import { ClientService } from 'src/app/services/client.service';
import { Client } from 'src/app/model/client';

@Component({
    selector: 'app-client',
    templateUrl: './client.component.html',
    styleUrls: ['./client.component.scss'],
    providers: [MessageService]
})
export class ClientComponent implements OnInit {
    clientDialog: boolean = false;
    deleteClientDialog: boolean = false;
    deleteClientsDialog: boolean = false;

    clients: Client[] = [];
    client: Client = new Client();

    selectedClients: Client[] = [];
    submitted: boolean = false;

    cols: any[] = [];
    rowsPerPageOptions = [5, 10, 20];

    constructor(private messageService: MessageService, private clientService: ClientService) { }

    ngOnInit(): void {
        this.loadClients();
    }

    loadClients() {
        this.clientService.findAll().subscribe(data => {
            this.clients = data;
        });
    }

    openNew() {
        this.client = new Client();
        this.submitted = false;
        this.clientDialog = true;
    }

    deleteSelectedClients() {
        this.deleteClientsDialog = true;
    }

    editClient(client: Client) {
        this.client = { ...client };
        this.clientDialog = true;
    }

    deleteClient(client: Client) {
        this.deleteClientDialog = true;
        this.client = { ...client };
    }

    confirmDeleteSelected() {
        this.deleteClientsDialog = false;
        this.selectedClients.forEach(client => {
            this.clientService.delete(client.id!).subscribe(() => {
                this.clients = this.clients.filter(val => val.id !== client.id);
                this.loadClients();
            });
        });
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Clients Deleted', life: 3000 });
        this.selectedClients = [];
    }

    confirmDelete() {
        this.deleteClientDialog = false;
        this.clientService.delete(this.client.id!).subscribe(() => {
            this.clients = this.clients.filter(val => val.id !== this.client.id);
            this.loadClients();
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Deleted', life: 3000 });
            this.client = new Client();
        });
    }

    hideDialog() {
        this.clientDialog = false;
        this.submitted = false;
    }

    saveClient() {
        this.submitted = true;

        if (this.client.nombre?.trim() && this.client.apellido?.trim()) {
            if (this.client.id) {
                this.clientService.update(this.client.id, this.client).subscribe(() => {
                    this.loadClients();
                    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Updated', life: 3000 });
                    this.clientDialog = false;
                    this.client = new Client();
                });
            } else {
                this.clientService.save(this.client).subscribe(() => {
                    this.loadClients();
                    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Created', life: 3000 });
                    this.clientDialog = false;
                    this.client = new Client();
                });
            }
        }
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }
}
