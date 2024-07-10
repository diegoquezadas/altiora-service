import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/api/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ProductService } from 'src/app/demo/service/product.service';
import { ArticleService } from 'src/app/services/article.service';
import { Article } from 'src/app/model/article';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
  providers: [MessageService]
})
export class ArticleComponent implements OnInit {
  productDialog: boolean = false;
  articleDialog: boolean = false;

  deleteProductDialog: boolean = false;
  deleteArticleDialog: boolean = false;

  deleteProductsDialog: boolean = false;
  deleteArticlesDialog: boolean = false;

  products: Product[] = [];
  articles: Article[] = [];

  product: Product = {};
  article: Article = new Article();

  selectedProducts: Product[] = [];
  selectedArticles: Article[] = [];

  submitted: boolean = false;

  cols: any[] = [];
  statuses: any[] = [];

  rowsPerPageOptions = [5, 10, 20];

  constructor(private productService: ProductService, private messageService: MessageService, private articleService: ArticleService) { }

  ngOnInit(): void {
    this.articleService.findAll().subscribe(data => {
      console.log("data", data)
      this.articles = data;
    });
  }

  openNew() {
    this.article = new Article();
    this.submitted = false;
    this.articleDialog = true;
  }

  deleteSelectedArticles() {
    this.deleteArticlesDialog = true;
  }

  editArticle(article: Article) {
    this.article = { ...article };
    this.articleDialog = true;
  }

  deleteArticle(article: Article) {
    this.deleteArticleDialog = true;
    this.article = { ...article };
  }

  confirmDeleteSelected() {
    this.deleteArticlesDialog = false;
    this.selectedArticles.forEach(article => {
      this.articleService.delete(article.id!).subscribe(() => {
        this.articles = this.articles.filter(val => val.id !== article.id);
      });
    });
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Articles Deleted', life: 3000 });
    this.selectedArticles = [];
  }

  confirmDelete() {
    this.deleteArticleDialog = false;
    this.articleService.delete(this.article.id!).subscribe(() => {
      this.articles = this.articles.filter(val => val.id !== this.article.id);
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Article Deleted', life: 3000 });
      this.article = new Article();
    });
  }

  hideDialog() {
    this.articleDialog = false;
    this.submitted = false;
  }

  saveArticle() {
    this.submitted = true;

    if (this.article.nombre?.trim()) {
      if (this.article.id) {
        this.articleService.update(this.article.id, this.article).subscribe(() => {
          this.articles = this.articles.map(val => val.id === this.article.id ? this.article : val);
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Article Updated', life: 3000 });
          this.articleDialog = false;
          this.article = new Article();
        });
      } else {
        this.articleService.save(this.article).subscribe((newArticle: Article) => {
          this.articles.push(newArticle);
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Article Created', life: 3000 });
          this.articleDialog = false;
          this.article = new Article();
        });
      }
    }
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
}
