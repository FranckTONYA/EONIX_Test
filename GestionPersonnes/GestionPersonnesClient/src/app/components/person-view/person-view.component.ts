import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Personne } from 'src/app/models/personne';
import { PersonneHttpService } from 'src/app/services/personne-http.service';

@Component({
  selector: 'app-person-view',
  templateUrl: './person-view.component.html',
  styleUrls: ['./person-view.component.css']
})
export class PersonViewComponent {
  personne:Personne;

  constructor(private personneHttpService: PersonneHttpService, private route: ActivatedRoute,
    public dialogRef: MatDialogRef<PersonViewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,) { 
  }

  ngOnInit() {
    this.personne = this.data.personne;
    // this.route.paramMap.subscribe(params => {
    //   let data: Personne = (params as any).params;
    //   this.personne = data;
    // });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
