import {Case} from '../beans/Case';
import {CaseSearch} from '../beans/CaseSearch';
import {Instance} from '../beans/Instance';
import {Lawyer} from '../beans/Lawyer';
import {Belezhka} from '../beans/belezhka';
import {Izpulnitelno} from '../beans/izpulnitelno';
import {Nakazatelno} from '../beans/nakazatelno';
import {Payment} from '../beans/payment';
import {urlLinks} from '../utility/url-links';
import {HttpService} from './http.service';
import {HttpHeaders} from '@angular/common/http';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {RequestOptions} from '@angular/http';
import {Observable} from 'rxjs';
import {Income} from '../beans/income';
import {AuthService} from './auth.service';



@Injectable()
export class CaseService {

  constructor(private http: HttpService, private httpFile: HttpClient, private authService: AuthService) {}

  saveCase(caseObj: Case): Observable<any> {
    if (caseObj.id) {
      return this.http.post(urlLinks.rest.cases.updateCase, caseObj);
    } else {
      return this.http.post(urlLinks.rest.cases.addCase, caseObj);
    }
  }

  saveIzpalnitelno(obj: Izpulnitelno, lawsuiteId: number): Observable<any> {
    return this.http.post(urlLinks.rest.cases.izpulnitelno.save + lawsuiteId, obj);

  }

  saveNakazatelno(obj: Nakazatelno, lawsuiteId: number): Observable<any> {
    obj.lawsuiteId = lawsuiteId;
    return this.http.post(urlLinks.rest.cases.nakazatelno.save, obj);
  }

  getNakazatelnoLawyer(id: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.nakazatelno.getOtgovornik + id);
  }

  deleteNakazatelno(id: number): Observable<any> {
    return this.http.post(urlLinks.rest.cases.nakazatelno.remove + id, {});
  }

  getCases(): Observable<any> {
    return this.http.get(urlLinks.rest.cases.getCases);
  }

  searchCases(searchObj: CaseSearch): Observable<any> {
    if (searchObj.traumaType && searchObj.traumaType.name) {
      searchObj.traumaTypeName = searchObj.traumaType.name;
    }
    console.log(searchObj.traumaTypeName);
    return this.http.post(urlLinks.rest.cases.searchCases, searchObj);
  }

  getCase(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.load + lawsuiteId);
  }

  deleteCase(caseId: Number): Observable<any> {
    return this.http.delete(urlLinks.rest.cases.deleteCase + caseId);
  }

  getNakazatelni(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.nakazatelno.getLawsuiteNakazatelnoStatuses + lawsuiteId);
  }

  getIncomes(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.income.list + lawsuiteId);
  }
  addIncome(incomeObj: Income, lawsuiteId: number): Observable<any> {
    incomeObj.lawsuiteId = lawsuiteId;
    return this.http.post(urlLinks.rest.cases.income.add, incomeObj);
  }

  deleteIncome(id: number): Observable<any> {
    return this.http.post(urlLinks.rest.cases.income.remove + id, {});
  }

  getPayments(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.payment.list + lawsuiteId);
  }

  addPayment(paymentObj: Payment, lawsuiteId: number): Observable<any> {
    paymentObj.lawsuiteId = lawsuiteId;
    return this.http.post(urlLinks.rest.cases.payment.add, paymentObj);
  }

  deletePayment(id: number): Observable<any> {
    return this.http.post(urlLinks.rest.cases.payment.remove + id, {});
  }


  getInstance(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.instance.list + lawsuiteId);
  }

  addInstance(obj: Instance, lawsuiteId: number): Observable<any> {
    obj.lawsuiteId = lawsuiteId;
    return this.http.post(urlLinks.rest.cases.instance.add, obj);
  }

  deleteInstance(id: number): Observable<any> {
    return this.http.post(urlLinks.rest.cases.instance.remove + id, {});
  }

  getNotes(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.belezhka.list + lawsuiteId);
  }

  addNote(obj: Belezhka, lawsuiteId: number): Observable<any> {
    obj.lawsuiteId = lawsuiteId;
    return this.http.post(urlLinks.rest.cases.belezhka.add, obj);
  }

  addFile(event: EventTarget, lawsuiteId: number, file: string, opisanie: string): Observable<any> {
    const eventObj: MSInputMethodContext = <MSInputMethodContext>event;
    const target: HTMLInputElement = <HTMLInputElement>eventObj.target;
    const files: FileList = target.files;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', new Blob([files[i]], {type: 'text/csv'}));
    }
    const headers = new HttpHeaders().set('Authorization', 'Basic ' + this.authService.getToken());
    return this.httpFile.post(urlLinks.rest.cases.file.add + lawsuiteId + '&fileName=' + file + '&opisanie=' + opisanie, formData, {headers});
  }
  getFiles(lawsuiteId: number): Observable<any> {
    return this.http.get(urlLinks.rest.cases.file.list + lawsuiteId);
  }
  deleteFile(fileId: number): Observable<any> {
    return this.http.delete(urlLinks.rest.cases.file.delete + fileId);
  }
  downloadFile(fileId: number, file: string): Observable<any> {
    return this.http.post(urlLinks.rest.cases.file.download + 'fileId=' + fileId + '&fileName=' + file, file);
  }
  deleteNote(id: number): Observable<any> {
    return this.http.post(urlLinks.rest.cases.belezhka.remove + id, {});
  }

  getOtgovorniciNakazatelno(): Observable<any> {
    return this.http.get(urlLinks.rest.cases.nakazatelno.otgovornici);
  }

  changeOtgovornikNakazatelno(id: number, obj: Lawyer): Observable<any> {
    return this.http.post(urlLinks.rest.cases.nakazatelno.changeotgovornik + id, obj);
  }

  getNasrocheniDelaByUser(isExtended: boolean): Observable<any> {
    return this.http.get(urlLinks.rest.cases.nasrocheniDela + '?isExtended=' + isExtended);
  }
}
