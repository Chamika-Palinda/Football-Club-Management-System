import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClubComponent} from "./club/club.component";
import { MatchComponent} from "./match/match.component";

const routes: Routes = [
  { path :'club',component: ClubComponent},
  { path : 'match', component: MatchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
