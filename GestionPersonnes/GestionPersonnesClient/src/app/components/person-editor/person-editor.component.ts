import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Personne } from 'src/app/models/personne';
import { PersonneHttpService } from 'src/app/services/personne-http.service';

@Component({
  selector: 'app-person-editor',
  templateUrl: './person-editor.component.html',
  styleUrls: ['./person-editor.component.css']
})
export class PersonEditorComponent {
  personne: Personne;

  constructor( public dialogRef: MatDialogRef<PersonEditorComponent>,
    private route: ActivatedRoute, 
      private router: Router,  @Inject(MAT_DIALOG_DATA) public data:any,
        private personneHttpService: PersonneHttpService) {
    this.personne = new Personne();
  }

  ngOnInit() {
    if (this.data != null && this.data.personne.id != null) {
      this.personne = this.data.personne;
    } else {
      this.personne = new Personne();
    }
    // this.route.paramMap.subscribe(params => {
    //   let data: Personne = (params as any).params;
    //   if (data.id != null) {
    //     this.personne = data;
    //   } else {
    //     this.personne = new Personne();
    //   }
    // });
  }

  onSubmit() {
    // this.personneHttpService.save(this.personne).subscribe(result => this.gotoHome());
    // this.dialogRef.close();
    // this.gotoHome();
  }

  gotoHome() {
    this.router.navigate(['/home']);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
