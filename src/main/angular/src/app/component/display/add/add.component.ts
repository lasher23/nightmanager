import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  @Output()
  initialized = new EventEmitter<undefined>()
  @Input()
  addCount = 0;

  adds = [
    '/assets/adds/1386_2022.svg',
    '/assets/adds/Aluart_2023.png',
    '/assets/adds/Bell_2022.jpeg',
    '/assets/adds/Blesshess_2022.PNG',
    '/assets/adds/CKW_2022.svg',
    '/assets/adds/Coiffure Flair_2022.tif',
    '/assets/adds/Contrast_2023.svg',
    '/assets/adds/Die Mobiliar_2022.PNG',
    '/assets/adds/Duss Küchen_2023.jpg',
    '/assets/adds/Elektro Widmer_2022.PNG',
    '/assets/adds/EROWA_2023.jpg',
    '/assets/adds/Fahrschule Müller_2022.PNG',
    '/assets/adds/Garage Fünfstern_2023.jpg',
    '/assets/adds/Garage Helfenstein_2023.jpg',
    '/assets/adds/Gebäudetechnik Estermann_2022.png',
    '/assets/adds/Huwyler-Intersport_2023.jpg',
    '/assets/adds/InseratWolf_Zimmerei_2022.svg',
    '/assets/adds/Knutwiler_2023.png',
    '/assets/adds/Konzept5_2022.svg',
    '/assets/adds/Limacher Treuhand_2022.png',
    '/assets/adds/Logo_Spartakus_2023.png',
    '/assets/adds/LUKB_2022.JPG',
    '/assets/adds/Marco Zemp Baloise_2022.svg',
    '/assets/adds/Metzgerei Loosli_2023.jpg',
    '/assets/adds/Migros_2023.jpg',
    '/assets/adds/Ottos AG_2022.jpg',
    '/assets/adds/Praxis für Homöopathie_2022.PNG',
    '/assets/adds/Raiffeisen_2023.jpg',
    '/assets/adds/Roter Löwen_2023.png',
    '/assets/adds/Schreinerei Schuler AG_2022.emf',
    '/assets/adds/Schürch Getränke_2023.png',
    '/assets/adds/Sportunion Hildisrieden.png',
    '/assets/adds/Technikkenner_2023.png',
    '/assets/adds/Therapie & Trainingscenter Hildisrieden_2022.PNG',
    '/assets/adds/Tschopp Holzbau_2022.png',
    '/assets/adds/Vitaluce_2022.svg',
    '/assets/adds/W-Vision_2022.png',
    '/assets/adds/Werkstück_2023.png',
    '/assets/adds/xhunt_2023.svg',
    '/assets/adds/Zahnarztpraxis Hildisrieden_2023.png',
    '/assets/adds/Zwick Storen_2022.jpeg',
  ];

  ngOnInit(): void {
    this.initialized.next(undefined);
  }
}
