import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GenericService<T> {

  constructor(protected http: HttpClient, @Inject ('url') protected url: string) { }

  // findAll(){
  //   return this.http.get<T>(this.url);
  // }

  findAll(): Observable<T[]> {
    return this.http.get<{ codigoRespuesta: string; mensajeRespuesta: string; objeto: T[] }>(this.url).pipe(
      map(response => response.objeto)
    );
  }

  findById(id: number){
    return this.http.get<T>(`${this.url}/${id}`);
  }

  save(t: T):Observable<any>{
    return this.http.post(this.url, t);
  }

  update(id: number, t: T){
    return this.http.put<any>(`${this.url}/${id}`, t);
  }

  delete(id: number){
    return this.http.delete(`${this.url}/${id}`);
  }
}
