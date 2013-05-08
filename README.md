A0B36PR2
========

Semestrální práce do pøedmìtu A0B36PR2

// Název práce: NETBoats

// Téma: Hra Lodì po síti

// Zadání práce:
V podstatì se jedná o klasickou hru Lodì, její pravidla se dají najít
na internetu (napø: http://lada.chytrackova.sweb.cz/hry/lode.htm). Hra bude probíhat
ve dvou po lokální síti.

//Návrh øešení:
- Kadá dùleitá èást je umístìna ve svém vlastním balíèku.
- Hlavní tøída SemestralniPrace pouze spustí menu v balíèku gui.

-Následuje monost pøipojení èi zaloení hry (Client a Server). Spojení se uskuteèní pomocí tøíd Client a Server v balíèku gui.

- Nejprve hráèi umístí své lodì a po kliknutí na ready se zapoène hra. Pøi umisování lodí ve høe se vyuívají vyuívat balíèky array a boat.
- Hráè umisuje lodì kliknutím na ikonku lodì a kliknutím na místo v poly na které chce umístit loï (kadá má svùj "defaultní" ètvereèek podle kterého se vše odvíjí).

- Poté co jsou oba hráèi pøipraveni a kliknou na ready støídají se v posílání dat. Vyhraje ten kterı první potopí všech 25 políèek, které lodì celkem mají.

//Pouité technologie:
- NetBeans IDE
- knihovna swing pro GUI

//Zdroje:
- Vlastní (pøedchozí) znalosti
- Pøednášky pana Doc.Ing. Ivana Jelínka, CSc.
- Cvièení pana Ing. Zdeòka Buka, Ph.D.
- Tutoriály od Buckyho Robertse (www.thenewboston.org)
