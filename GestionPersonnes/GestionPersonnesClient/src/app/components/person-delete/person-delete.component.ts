import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Personne } from 'src/app/models/personne';
import { PersonneHttpService } from 'src/app/services/personne-http.service';

@Component({
  selector: 'app-person-delete',
  templateUrl: './person-delete.component.html',
  styleUrls: ['./person-delete.component.css']
})
export class PersonDeleteComponent {
  personne: Personne;

  constructor(
    public dialogRef: MatDialogRef<PersonDeleteComponent>,private route: ActivatedRoute,
    private router: Router,  @Inject(MAT_DIALOG_DATA) public data: Personne,
     public personneHttpService: PersonneHttpService) {}


    ngOnInit() {
      this.personne = this.data;
      // this.route.paramMap.subscribe(params => {
      //   let data: Personne = (params as any).params;
      //     this.personne = data;
      // });
    }

    // onValid(): void {
    //   console.log(this.personne)
    //   this.personneHttpService.delete(this.personne).subscribe(result => this.gotoHome());;
    // }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // gotoHome() {
  //   this.router.navigate(['/home']);
  // }
}
