import { Injectable, inject } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { getAuth, signInWithEmailAndPassword, sendPasswordResetEmail } from 'firebase/auth';
import { users } from '../model/user.model';
import { AngularFirestore } from '@angular/fire/compat/firestore';
import { getFirestore, setDoc, getDoc, doc, updateDoc, collection, getDocs, query, where } from '@angular/fire/firestore';
import { UtilsService } from './utils.service';
import { Observable } from 'rxjs';
import { orderBy } from 'firebase/firestore';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  auth = inject(AngularFireAuth);
  firestore = inject(AngularFirestore);
  utilsSvc = inject(UtilsService);

  // =============ACCEDER
  sigIn(user: users) {
    return signInWithEmailAndPassword(getAuth(), user.email, user.clave);
  }

    //=========RESTABLECER CONTRASEÑA=========
sendRecoveryEmail(email: string){
  return sendPasswordResetEmail(getAuth(), email)
  }

  // BD
  setDocument(path: string, data: any) {
    return setDoc(doc(getFirestore(), path), data);
  }

  async getDocument(path: string) {
    return (await getDoc(doc(getFirestore(), path))).data();
  }

  //==================CERRAR SESION========
  signOut() {
    getAuth().signOut();
    localStorage.removeItem('user');
    this.utilsSvc.routerLink('/login')
  }

  // Método para actualizar un documento en Firestore
  async updateDocument(collection: string, docId: string, newData: any) {
    try {
      const docRef = doc(getFirestore(), collection, docId);
      await updateDoc(docRef, newData);
      console.log('Documento actualizado exitosamente en Firestore');
    } catch (error) {
      console.error('Error al actualizar documento en Firestore:', error);
      throw error;
    }
  }

  

  // =========== Obtener datos de salas ==============
  getSalasMantenimiento(): Observable<any[]> {
    return this.firestore.collection('mantenimiento').valueChanges({ idField: 'id' });
  }

  getSalaById(id: string): Observable<any> {
    return this.firestore.collection('mantenimiento').doc(id).valueChanges();
  }
}