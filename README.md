# MovieRater1

# Tema:

Filmsida där användare kan lämna recensioner, skapa nya filmer och lägga till direktörer.
Användare behöver ha ett eget konto.

# Gruppmedlemmar:

Connie Karlzen, Armin Akhtarzand, Ervin Torok, Josefine Berglund.

# Projektstart:

Vi röstade alla på ett tema först, sedan kom vi fram till ett system för filmklassificering.
Vi skapade ett dokument där vi delade in klasser och struktur som behövdes från var och en av oss.
Vi började arbeta med entiteter och gjorde tester till slut.
Genom hela processen såg vi till att vi var synkroniserade med våra uppgifter,
körde pulls samtidigt och frågade om vi tittade på samma ändringar.
Allt fungerade väldigt bra mellan oss, med minimala fel här och där som var lätta att åtgärda.

# Miljövariabler:

Dessa finns i neon, där vi har använt variablerna för både våra individuella grenar
och variablerna för produktion i slutändan. Dessa ställs in via konfiguration,
där du lägger till DB_URL, DB_USERNAME och DB_PASSWORD.
Du får miljövariablerna från anslutningssträngen i neon, där du väljer Java,
väljer visa lösenord och sedan har du url,
användarnamn och lösenord från det. Individuella branches skyddar production, med dem
undviker vi konflikter och får en säker utveckling. Production branch är den riktiga databasen
som körs i produktion, alltså den publika versionen. 