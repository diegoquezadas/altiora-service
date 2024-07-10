import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { NotfoundComponent } from './demo/components/notfound/notfound.component';
import { AppLayoutComponent } from "./layout/app.layout.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppLayoutComponent,
                children: [
                    { path: '', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },
                    { path: 'client', loadChildren: () => import('./modules/client/client.module').then(m => m.ClientModule) },
                    { path: 'order', loadChildren: () => import('./modules/order/order.module').then(m => m.OrderModule) },
                    { path: 'article', loadChildren: () => import('./modules/article/article.module').then(m => m.ArticleModule) },
                ]
            },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
