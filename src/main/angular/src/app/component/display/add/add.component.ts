import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-add',
    templateUrl: './add.component.html',
    styleUrls: ['./add.component.css'],
    standalone: false
})
export class AddComponent implements OnInit {
  @Output()
  initialized = new EventEmitter<undefined>()
  @Input()
  addCount = 0;

  adds = [
    '/assets/adds/1386_2023.svg',
    '/assets/adds/Bell_2022.jpeg',
    '/assets/adds/Bistro-Restaurant-Adler_2025.svg',
    '/assets/adds/Blesshess_2022.PNG',
    '/assets/adds/CKW_2022.svg',
    '/assets/adds/Coiffure Flair_2022.tif',
    '/assets/adds/Die Mobiliar_2022.PNG',
    '/assets/adds/EROWA_2023.jpg',
    '/assets/adds/Elektro Widmer_2022.PNG',
    '/assets/adds/Fesartis_Logo.svg',
    '/assets/adds/Garage Fünfstern_2023.jpg',
    '/assets/adds/Garage Helfenstein_2023.jpg',
    '/assets/adds/Gebäudetechnik Estermann_2022.png',
    '/assets/adds/Knutwiler_2023.png',
    '/assets/adds/Konzept5_2022.svg',
    '/assets/adds/LUKB_2022.JPG',
    '/assets/adds/La Pista_2025.jpg',
    '/assets/adds/Limacher Treuhand_2022.png',
    '/assets/adds/Metzgerei Loosli_2023.jpg',
    '/assets/adds/Ofenbau_Burri_2025.png',
    '/assets/adds/Ottos AG_2022.jpg',
    '/assets/adds/Praxis für Homöopathie_2025.PNG',
    '/assets/adds/Raiffeisen_2023.jpg',
    '/assets/adds/Ristorante Pizzeria Dal Siciliano.jpg',
    '/assets/adds/Roter Löwen_2023.png',
    '/assets/adds/Schlosserei Daniel Estermann.png',
    '/assets/adds/Schürch Getränke_2023.png',
    '/assets/adds/Sportunion Hildisrieden.png',
    '/assets/adds/Sumag_2025.jpg',
    '/assets/adds/The Mate.jpg',
    '/assets/adds/Therapie & Trainingscenter Hildisrieden_2022.PNG',
    '/assets/adds/Tschopp Holzbau_2022.png',
    '/assets/adds/W-Vision_2022.png',
    '/assets/adds/Werkstück_2023.png',
    '/assets/adds/Z-Holzbau-Inserat.svg',
    '/assets/adds/Zwick Storen_2022.jpeg',
    '/assets/adds/Zwyssig Bäckerei_2022.jpeg',
    '/assets/adds/egli-storen.svg',
  ];

  ngOnInit(): void {
    this.initialized.next(undefined);
  }
}
