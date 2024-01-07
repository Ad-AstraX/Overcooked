## Projekt: Lineare Datenstrukturen - OVERCOOKED! (Das beste Spiel obviously)

### Projektbeschreibung
Der Spieler/die Spieler ist/sind in einem kleinen Burgerladen, indem er/sie als Koch fungiert und für die Gäste ihre Bestellung zubereitet. Dabei müssen bspw. Salat und Tomate geschnitten und das Fleisch gegrillt werden, um sie auf einem Teller zu stapeln und dem Kunden zu übergeben. Die Kunden verlieren mit der Zeit ihre Zufriedenheit und geben dem Spieler je nach Zufriedenheit auch unterschiedliche "tips". Das Geld, das/die der/die Player sammelt/sammeln können dann genutzt werden, um Upgrades zu kaufen.
Man kann zwischen den Modes Singleplayer und Multiplayer togglen.

Mögliches Feature, das vielleicht bald eingebaut wird: _special customers_
#

### Projektstruktur
Alle Textures, Sounds und Font(s) befinden sich in der **assets directory**. Dort sind sie dann alle weiter unterteilt.

Der Code ist in der directory **core > src > com.mygdx.game**, wo sie ebenfalls weiterhin in MVC (Model, View & Control) unterteilt sind. Die Klassen in Control kontrollieren die Models, die dann auf dem Screen ausgegeben werden. Alle Controller werden in der Klasse GameController instanziiert und von dort aus verwaltet. In View gibt es nur eine Klasse: Main. Sie sammelt mithilfe verschiedener Lists alle Objekte, die zu zeichnen sind und gibt sie dann aus. Die Models sind (selbsterklärenderweise) die Klassen, die die Objekte repräsentieren, die auszugeben sind.

Es wurde mit dem Framework <a href="https://libgdx.com/"> libGdx</a> gearbeitet. Alle Textures sind selbstgezeichnet, hierzu also keine Quellen.
#

### Weiteres
Link zur GitHub Repo: <a href="https://github.com/Ad-AstraX/Overcooked"> Ad-AstraX/Overcooked </a> <p>
Link zum Entwurfsdiagramm: <a href="https://lucid.app/lucidchart/f7e2f732-a207-4831-a8a4-a84ec467c564/edit?viewport_loc=-10456%2C-12024%2C16580%2C9057%2C0_0&invitationId=inv_7ea10c1d-5fce-4e73-a1c7-80163e3e9f17"> Overcooked UML </a> <p>
Link zur Präsentation: <a href="https://docs.google.com/presentation/d/147CH1_1AjvHHhZZqAYQ-XJWtZZmI2CicsfYmM5irqCY/edit?usp=sharing"> Overcooked Präsentation </a> <p>
Mitglieder: Konstantin, Rugan und Haya
#
