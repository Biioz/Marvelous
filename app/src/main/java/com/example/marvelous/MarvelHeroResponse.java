package com.example.marvelous;
import java.util.List ;

public class MarvelHeroResponse {
    private int code;
    private String status;
    private HeroData data;

    public HeroData getData() { return data; }

    public static class HeroData {
        private List<Hero> results;
        public List<Hero> getResults() { return results; }
    }
}


/* Exemple de JSON produit par l'API
{
  "code": 200,
  "status": "Ok",
  "data": {
    "results": [
      {
        "id": 1011334,
        "name": "Iron Man",
        "description": "Genius inventor Tony Stark...",
        "thumbnail": {
          "path": "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55",
          "extension": "jpg"
        }
      }
    ]
  }
}
 */