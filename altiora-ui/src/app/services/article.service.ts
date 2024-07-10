import { Injectable } from '@angular/core';
import { Article } from '../model/article';
import { GenericService } from './generic.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArticleService  extends GenericService<Article>{

  constructor(protected override http: HttpClient) {
    super(http, 'http://localhost:8080/api/v1/articulos');
  }
}
