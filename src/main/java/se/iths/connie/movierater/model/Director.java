package se.iths.connie.movierater.model;

import jakarta.persistence.*;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int birthYear;
    @Column(nullable = false)
    private String nationality;
    @Column(nullable = false)
    private int numberOfMoviesDirected;
    @Column(nullable = false)
    private boolean active;

    public Director() {
    }

    public Director(String name, int birthYear, String nationality,
                    int numberOfMoviesDirected, boolean active) {
        this.name = name;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.numberOfMoviesDirected = numberOfMoviesDirected;
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getNumberOfMoviesDirected() {
        return numberOfMoviesDirected;
    }

    public void setNumberOfMoviesDirected(int numberOfMoviesDirected) {
        this.numberOfMoviesDirected = numberOfMoviesDirected;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Director { " +
                " Id= " + id +
                " | Name= '" + name + '\'' +
                " | Birth year= " + birthYear +
                "  \n | Nationality= '" + nationality + '\'' +
                " \n | Number of Movies= " + numberOfMoviesDirected +
                " | Active today= " + active +
                '}';
    }
}
