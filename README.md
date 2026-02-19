# MovieRater1

# Tema:

Filmsida där användare kan lämna recensioner, skapa nya filmer och lägga till direktörer.
Användare behöver ha ett eget konto.

# Gruppmedlemmar:

Connie Karlzen, Armin Akhtarzand, Ervin Torok, Josefine Berglund.

# Projektstart:

Projektet är en Spring Boot-applikation som körs i en Docker-container och driftsätts i molnet via Koyeb.

Lokalt startas applikationen genom att projektet först byggs med Maven och därefter körs via IDE. För att applikationen
ska fungera krävs att miljövariabler för databasen är korrekt inställda, exempelvis URL, användarnamn och lösenord.
Dessa används för att ansluta till vår PostgreSQL-databas som ligger i molnet via Neon. Genom att använda miljövariabler
undviker vi att hårdkoda känslig information i koden.

När applikationen ska driftsättas paketeras den som en Docker image. Docker gör att hela applikationen körs i en
isolerad miljö, vilket innebär att den fungerar på samma sätt oavsett var den startas. Den färdiga imagen laddas upp och
används av Koyeb, som startar containern och gör applikationen tillgänglig via en publik webbadress. I Koyeb
konfigureras miljövariablerna direkt i plattformens inställningar.
 
------

När applikationen startas möts användaren av en startsida. Den fungerar som en ingångspunkt till systemet och innehåller
navigeringsval till de olika delarna: User, Movie, Review och Director.

Varje alternativ leder vidare till sin egen del av applikationen där användaren kan hantera information kopplad till
respektive entitet. I varje del visas en lista över befintliga objekt. Det finns även möjlighet att skapa nya objekt
samt uppdatera eller radera befintliga.

Varje entitet har egna regler och krav på vilka attribut som måste anges vid skapande eller uppdatering. Dessa regler
hanteras i applikationens affärslogik och validering för att säkerställa att endast korrekt och giltig data sparas i
databasen.

# Miljövariabler:

Dessa finns i neon, där vi har använt variablerna för både våra individuella grenar
och variablerna för produktion i slutändan. Dessa ställs in via konfiguration,
där du lägger till DB_URL, DB_USERNAME och DB_PASSWORD.
Du får miljövariablerna från anslutningssträngen i neon, där du väljer Java,
väljer visa lösenord och sedan har du url,
användarnamn och lösenord från det. Individuella branches skyddar production, med dem
undviker vi konflikter och får en säker utveckling. Production branch är den riktiga databasen
som körs i produktion, alltså den publika versionen. 