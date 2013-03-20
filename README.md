A0B36PR2
========

Semestrální práce do pøedmìtu A0B36PR2

//Název práce: (pracovní) LANShips

//Téma: Hra Lodì po síti

//Zadání práce:
V podstatì se jedná o klasickou hru Lodì, její pravidla se dají najít
na internetu (napø: http://lada.chytrackova.sweb.cz/hry/lode.htm). Hra bude probíhat
ve dvou po lokální síti.

//Návrh øešení:
- Kadá dùleitá èást je umístìna ve svém vlastním balíèku.
- Hlavní tøída SemestralniPrace pouze spustí menu v balíèku gui.

Následuje monost pøipojení èi zaloení hry (Lobby a Lobby_Connect)
Spojení se uskuteèní pomocí tøíd v balíèku net (zatím jsem sítì nezvládl,
brzy snad pochopím jak na to).

Po spojení se pøeneseme do balíèku game, kde se spustí samotná hra.
- Tato èást je napsána pomocí Slick2D.
Nejprve (za danı èasovı limit) hráèi umístí své lodì (BoatPlacing) a následnì
se pøenesou do hry.
- Pøi umisování lodí a høe se budou (pravdìpodobnì) vyuívat balíèky array a boat.

//Pouité technologie:
- NetBeans IDE
- knihovna swing pro GUI menu
- knihovna Slick2D pro samotnou hru