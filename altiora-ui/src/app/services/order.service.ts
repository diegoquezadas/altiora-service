import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Order } from '../model/order';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService extends GenericService<Order> {

  constructor(protected override http: HttpClient) {
    super(http, 'http://localhost:8080/api/v1/ordenes');
  }
}
