import { Component, EventEmitter, Input, SimpleChanges } from '@angular/core';
import { Personne } from './models/personne';
import { PersonneHttpService } from './services/personne-http.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup } from '@angular/forms';
import { PersonEditorComponent } from './components/person-editor/person-editor.component';
import { PersonDeleteComponent } from './components/person-delete/person-delete.component';
import { PersonViewComponent } from './components/person-view/person-view.component';
import { Subject, Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  personDataSubscription: Subscription;
  personDataSubject: Subject<Personne[]> = new Subject<Personne[]>();
  tableDataSource: MatTableDataSource<Personne>= new MatTableDataSource<Personne>
  private personDatas: Personne[];;

  personne = new Personne();
  personFormGrp: FormGroup;
  displayedColumns: string[] = ['id', 'firstname' ,'name', 'edit', 'delete', 'view'];
  

  constructor(public dialog: MatDialog, public personneHttpService: PersonneHttpService) { 
    this.personFormGrp = new FormGroup({
    });

    this.personneHttpService.findAll().subscribe ( (result) => {
      console.log(result)
      this.tableDataSource=new MatTableDataSource(result);
   });
  
    
  }

  ngOnInit() {
    this.personDataSubscription = this.personDataSubject.subscribe((data) => {
      this.tableDataSource = new MatTableDataSource<Personne>(data);
    });
  }

  addPerson(){
    const dialogRef = this.dialog.open(PersonEditorComponent, {
      height: '800px',
      width: '600px',
      data: {personne: this.personne}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result)
      this.personne= result;
      this.personneHttpService.save(this.personne).subscribe(result =>{
        if(result != null){
          this.personneHttpService.findAll().subscribe( (result) => {
            this.tableDataSource.data = result;
            console.log(result)
          });
        }
      });
    });
  }

  editPerson(personne: Personne){
    console.log(personne)

    const dialogRef = this.dialog.open(PersonEditorComponent, {
      height: '700px',
      width: '700px',
      data: {personne: personne}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.personne= result;
      this.personneHttpService.save(this.personne).subscribe(result =>{
        if(result != null){
          this.personneHttpService.findAll().subscribe( (result) => {
            this.tableDataSource.data = result;
            console.log(result)
          });
        }
      });
    });
  }

  deletePerson(personne: Personne){
    const dialogRef = this.dialog.open(PersonDeleteComponent, {
      data: {personne: personne},
    });

    dialogRef.afterClosed().subscribe(result => {
      this.personne= result.personne;
      this.personneHttpService.delete(this.personne).subscribe(result =>{
        if(result != null){
          this.personneHttpService.findAll().subscribe( (result) => {
            this.tableDataSource.data = result;
            console.log(result)
          });
        }
      });
    });
  }
  

  viewPerson(element: Personne){
    const dialogRef = this.dialog.open(PersonViewComponent,{
      height: '200px',
      width: '700px',
      disableClose: true,
      data: { personne: element}
    });
  }

  emitData(){
    this.personDataSubject.next(this.personDatas);
  }

}
