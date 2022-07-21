//Kaggle Netflix

//Create constraints
CREATE CONSTRAINT FOR (p:Production) REQUIRE p.productionId IS UNIQUE;
CREATE CONSTRAINT FOR (g:Genre) REQUIRE g.name IS UNIQUE;
CREATE CONSTRAINT FOR (c:Country) REQUIRE c.iso2Code IS UNIQUE;

//Load Productions
LOAD CSV WITH HEADERS FROM "https://raw.githubusercontent.com/JMHReif/graph-demo-datasets/main/kaggle-netflix/titles.csv" as row
CALL apoc.merge.node(["Production",apoc.text.capitalize(toLower(row.type))], {productionId: row.id}, {title: row.title, seasons: toFloat(row.seasons), releaseDate: datetime(row.release_year), description: row.description, runtime: toInteger(row.runtime), rating: row.age_certification}, {}) YIELD node as p
WITH row, p
CALL { 
    WITH row, p
    WITH row, p, split(substring(row.genres, 1, size(row.genres)-2), ", ") AS genres
    UNWIND genres as g
    WITH row, p, substring(g, 1, size(g)-2) as genre
    WHERE genre <> ""
    MERGE (g:Genre {name: apoc.text.capitalize(genre)})
    MERGE (p)-[r:CATEGORIZED_BY]->(g) 
    RETURN g
}
WITH row, p
CALL {
    WITH row, p
    WITH row, p, split(substring(row.production_countries, 1, size(row.production_countries)-2), ", ") AS countries
    UNWIND countries as c
    WITH row, p, substring(c, 1, size(c)-2) as country
    WHERE country <> ""
    MERGE (c:Country {iso2Code: country})
    MERGE (p)-[r2:PRODUCED_IN]->(c)
    RETURN c
}
RETURN count(row);