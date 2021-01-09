import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import {AppRoutingModule} from "./app-routing.module";
import {ClubComponent} from "./club/club.component";
import {MatchComponent} from "./match/match.component";
import {NavBarComponent} from "./nav-bar/nav-bar.component";

const routes: Routes = [


];

@NgModule({
  declarations: [
    AppComponent,
    ClubComponent,
    MatchComponent,
    NavBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    RouterModule.forRoot(routes)
  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
