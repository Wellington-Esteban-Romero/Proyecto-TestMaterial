import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule } from '@angular/material/icon';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { MaterialesComponent } from './materiales/materiales.component';
import { FormComponent } from './materiales/form/form.component';
import { PaginadorComponent } from './materiales/paginador/paginador.component';

import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatTooltipModule} from '@angular/material/tooltip';
import { PruebasComponent } from './materiales/pruebas/pruebas.component';
import { FormPruebasComponent } from './materiales/pruebas/form-pruebas/form-pruebas.component';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';

import { registerLocaleData } from '@angular/common';
import { DatePipe } from '@angular/common';
import { DetallesComponent } from './materiales/detalles/detalles.component';
import { NoexisteComponent } from './noexiste/noexiste.component';
import { MAT_DATE_LOCALE } from '@angular/material/core';;
import { DoughNutChartComponent } from './doughnutchart/doughnutchart.component';

import localES from '@angular/common/locales/es';
import { NgChartsModule } from 'ng2-charts';
import { AuthGuard } from './materiales/guards/auth.guard';
import { RoleGuard } from './materiales/guards/role.guard';

registerLocaleData(localES, 'es')

const routes: Routes = [
  {path: '', redirectTo:'/login',pathMatch:'full'},
  {path: 'login', component:LoginComponent},
  {path: 'materiales', component:MaterialesComponent},
  {path: 'materiales/page/:page', component:MaterialesComponent},
  {path: 'materiales/form', component:FormComponent, canActivate:[AuthGuard, RoleGuard], data:{role:'ROLE_ADMIN'}}, //canacntiva registrarmo el guard
  {path: 'materiales/form/:id', component:FormComponent, canActivate:[AuthGuard, RoleGuard], data:{role:'ROLE_ADMIN'}},
  {path: 'pruebas/:id', component:PruebasComponent},
  {path: 'dashboard', component:DoughNutChartComponent},
  {path:'**', component:NoexisteComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    MaterialesComponent,
    FormComponent,
    PaginadorComponent,
    PruebasComponent,
    FormPruebasComponent,
    DetallesComponent,
    NoexisteComponent,
    DoughNutChartComponent
  ],
  imports: [
    BrowserModule,
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatMomentDateModule,
    MatTooltipModule,
    NgChartsModule,
    MatCardModule,
    MatInputModule
  ],
  providers: [DatePipe,{ provide: MAT_DATE_LOCALE, useValue: 'es-ES' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
