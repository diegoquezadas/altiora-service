import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../model/client';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
 export class ClientService extends GenericService<any> {
// export class ClientService  {

   constructor(protected override http: HttpClient) {
     super(http, 'http://localhost:9000/api/v1/clientes');
   }

  // url: string = "http://localhost:8080/api/v1/clientes";
  // constructor(private  http: HttpClient) {
  //   this.url
  // }

  // findAll():Observable<any>{
  //   return this.http.get(this.url);
  // }

  // // findById(id: number){
  // //   return this.http.get<T>(`${this.url}/${id}`);
  // // }


  // save(client: any):Observable<any>{
  //   return this.http.post(this.url, client);
  // }

  // // update(id: number, t: T){
  // //   return this.http.put<any>(`${this.url}/${id}`, t);
  // // }

  // delete(id: number){
  //   return this.http.delete(`${this.url}/${id}`);
  // }


}
