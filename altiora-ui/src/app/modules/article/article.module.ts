import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { DialogModule } from 'primeng/dialog';
import { ArticleRoutingModule } from './article-routing.module';
import { ArticleComponent } from './article.component';



@NgModule({
  imports: [
      CommonModule,
      ArticleRoutingModule,
      TableModule,
      FormsModule,
      ButtonModule,
      RippleModule,
      ToastModule,
      ToolbarModule,
      InputTextModule,
      InputNumberModule,
      DialogModule
  ],
  declarations: [ArticleComponent]
})
export class ArticleModule { }
