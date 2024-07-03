import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore';
import { Observable } from 'rxjs';

interface Day {
  day: string;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class DaysService {

  constructor(private firestore: AngularFirestore) { }

  getDays(): Observable<Day[]> {
    return this.firestore.collection<Day>('days').valueChanges();
  }
}