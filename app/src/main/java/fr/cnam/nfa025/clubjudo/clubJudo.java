package fr.cnam.nfa025.clubjudo;

public class clubJudo {

        // Notez que l'identifiant est un long
        private long id;
        private String nom;
        private String ville;

        public clubJudo(long id, String nom_p, String ville_p) {

            this.id = id;
            this.nom = nom_p;
            this.ville = ville_p;
        }

        public long getId() {
            return id;
        }

        public void setId(long id_p) {
            this.id = id_p;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom_p) {
            this.nom = nom_p;
        }

        public String getVille() {
            return ville;
        }

        public void setVille(String ville_p) {
            this.ville = ville_p;
        }

}
