import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Personne } from '../models/personne';

@Injectable({
  providedIn: 'root'
})
export class PersonneHttpService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/gestion/';
  }

  public findAll(): Observable<Personne[]> {
    return this.http.get<Personne[]>(this.url+"personne/find-all");
  }

  public save(personne: Personne) {
    return this.http.post<Personne>(this.url+"personne/save", personne);
  }

  public delete(personne: Personne) {
    console.log(personne.id)
    return this.http.delete<Personne>(this.url+"personne/delete-by-id/"+personne.id);
  }
}
