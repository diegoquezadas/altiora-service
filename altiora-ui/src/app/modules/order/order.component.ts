import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Order } from 'src/app/model/order';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrl: './order.component.scss',
  providers: [MessageService]
})
export class OrderComponent implements OnInit  {

  orderDialog: boolean = false;
  deleteOrderDialog: boolean = false;
  deleteOrdersDialog: boolean = false;

  orders: Order[] = [];
  order: Order = new Order();
  selectedOrders: Order[] = [];
  submitted: boolean = false;

  cols: any[] = [];
  rowsPerPageOptions = [5, 10, 20];

  constructor(private orderService: OrderService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.orderService.findAll().subscribe(data => {
      this.orders = data;
    });
  }

  openNew() {
    this.order = new Order();
    this.submitted = false;
    this.orderDialog = true;
  }

  deleteSelectedOrders() {
    this.deleteOrdersDialog = true;
  }

  editOrder(order: Order) {
    this.order = { ...order };
    this.orderDialog = true;
  }

  deleteOrder(order: Order) {
    this.deleteOrderDialog = true;
    this.order = { ...order };
  }

  confirmDeleteSelected() {
    this.deleteOrdersDialog = false;
    this.selectedOrders.forEach(order => {
      this.orderService.delete(order.id!).subscribe(() => {
        this.orders = this.orders.filter(val => val.id !== order.id);
      });
    });
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Orders Deleted', life: 3000 });
    this.selectedOrders = [];
  }

  confirmDelete() {
    this.deleteOrderDialog = false;
    this.orderService.delete(this.order.id!).subscribe(() => {
      this.orders = this.orders.filter(val => val.id !== this.order.id);
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Order Deleted', life: 3000 });
      this.order = new Order();
    });
  }

  hideDialog() {
    this.orderDialog = false;
    this.submitted = false;
  }

  saveOrder() {
    this.submitted = true;

    if (this.order.codigo?.trim()) {
      if (this.order.id) {
        this.orderService.update(this.order.id, this.order).subscribe(() => {
          this.orders = this.orders.map(val => val.id === this.order.id ? this.order : val);
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Order Updated', life: 3000 });
          this.orderDialog = false;
          this.order = new Order();
        });
      } else {
        this.orderService.save(this.order).subscribe((newOrder: Order) => {
          this.orders.push(newOrder);
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Order Created', life: 3000 });
          this.orderDialog = false;
          this.order = new Order();
        });
      }
    }
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
}
