package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    private int id;
    private String title;
    private GameType type;
    private List<Console> consoles;
    private List<Owner> ownerFav;
    private Console hostConsole;

    public Game(){

    }

    public Game(String title, GameType type) {
        this.title = title;
        this.type = type;
        this.consoles = new ArrayList<Console>();
        this.hostConsole = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Enumerated(value = EnumType.STRING)
    public GameType getType() {
        return type;
    }

    @ManyToMany
    @JoinTable(name = "console_game",
            joinColumns = {@JoinColumn(name = "game_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "console_id", nullable = false, updatable = false)})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Console> getConsoles() {
        return consoles;
    }

    @OneToMany(mappedBy = "favouriteGame", fetch = FetchType.LAZY)
    public List<Owner> getOwnerFav() {
        return ownerFav;
    }

   @OneToOne(mappedBy = "gameBeingPlayed", fetch = FetchType.LAZY)
    public Console getHostConsole() {
        return hostConsole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    public void setOwnerFav(List<Owner> ownerFav) {
        this.ownerFav = ownerFav;
    }

    public void setHostConsole(Console hostConsole) {
        this.hostConsole = hostConsole;
    }

    public void addConsole(Console console) {
        this.consoles.add(console);
    }


}
