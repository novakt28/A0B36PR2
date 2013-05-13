A0B36PR2
========

Semestr�ln� pr�ce do p�edm�tu A0B36PR2

// N�zev pr�ce: NETBoats

// T�ma: Hra Lod� po s�ti

// Zad�n� pr�ce:
V podstat� se jedn� o klasickou hru Lod�, jej� pravidla se daj� naj�t
na internetu (nap�: http://lada.chytrackova.sweb.cz/hry/lode.htm). Hra bude prob�hat
ve dvou po lok�ln� s�ti.

// N�vrh �e�en�:
- Ka�d� d�le�it� ��st je um�st�na ve sv�m vlastn�m bal��ku.
- Hlavn� t��da SemestralniPrace pouze spust� menu v bal��ku gui.

- N�sleduje mo�nost p�ipojen� �i zalo�en� hry (Client a Server). Spojen� se uskute�n� pomoc� t��d Client a Server v bal��ku gui.

- Nejprve hr��i um�st� sv� lod� a po kliknut� na ready se zapo�ne hra. P�i umis�ov�n� lod� ve h�e se vyu��vaj� vyu��vat bal��ky array a boat.
- Hr�� umis�uje lod� kliknut�m na ikonku lod� a kliknut�m na m�sto v poly na kter� chce um�stit lo� (ka�d� m� sv�j "defaultn�" �tvere�ek podle kter�ho se v�e odv�j�).

- Pot� co jsou oba hr��i p�ipraveni a kliknou na ready st��daj� se v pos�l�n� dat. Vyhraje ten kter� prvn� potop� v�ech 25 pol��ek, kter� lod� celkem maj�.

// N�vod na obsluhu:
- Nejprve si hr�� zvol�, jestli chce zalo�it hru (New Game), nebo se p�ipojit k zalo�en� h�e (Join Game). Pokud se chce p�ipojit, mus� zadat IP adresu hr��e, kter� zakl�d� a p�ipojit se a� po zalo�en� hry.
- Po spojen� hr��� si ka�d� rozm�st� sv� lod�. Kliknut�m na obr�zek lod� a n�sledn�m kliknut�m do pole na vkl�d�n� lod� (vlevo) m��e vlo�it lo�. Lo� lze vlo�it, pouze pokud by nekolidovala s jinou lod�, nebo okrajem pole. N�sledn� m��e kliknut�m na p��slu�nou lodi�ku a klik�n�m na �ipky a R (rotate) s n� pohybovat, av�ak zase pouze v r�mci pravidel (lod� nesm� kolidovat ani se sebe dot�kat jinak, ne� rohem.
- Hr�� d� najevo, �e je p�ipraven kliknut�m na tla��tko READY. Jakmile jsou p�ipraveni oba hr��i, m��e ten, kter� se p�ipojoval poprv� vyst�elit.
- N�sledn� se hr��i st��daj� v pos�l�n� tah�, dokud se n�kter�mu z nich nepoda�� vyst��let v�echny lod� soupe�e. Pot� se zobraz� hl�ka o tom, jestli hr�� vyhr�l, nebo prohr�l a program se mo�n� ukon�it.

// Pou�it� technologie:
- NetBeans IDE
- knihovna swing pro GUI

// Zdroje:
- Vlastn� (p�edchoz�) znalosti
- P�edn�ky pana Doc.Ing. Ivana Jel�nka, CSc.
- Cvi�en� pana Ing. Zde�ka Buka, Ph.D.
- Tutori�ly od Buckyho Robertse (www.thenewboston.org)
